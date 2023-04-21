package com.wei.java.design.pattern.template.service.longsheng;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.wei.java.design.pattern.template.service.longsheng.actionengine.ActionEngine;
import com.wei.java.design.pattern.template.service.longsheng.actionengine.ActionResult;
import com.wei.java.design.pattern.template.service.longsheng.actionengine.enums.IProcEnum;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Atop服务实现模板接口
 *
 * @author zhoulongsheng
 * @version $Id: IAtopTemplate.java, v 0.1 2020年6月9日 下午11:14:55 zhoulongsheng Exp $
 */
public class AtopTemplateImpl implements IAtopTemplate {

    protected static final Logger logger = LoggerFactory.getLogger(AtopTemplateImpl.class);

    @Resource
    private ActionEngine actionEngine;

    /**
     * 不做多语言处理的异常Code
     */
    private static final Set<String> NOT_TRANSFER_EXCEPTION_CODE = new HashSet<>() {
        private static final long serialVersionUID = 6699286597821493031L;

        {
            this.add("xxx");
        }
    };

    @Override
    public <R, P> AtopResult<R> execute(AbstractAtopAction<R, P> action) {
        long start = System.currentTimeMillis();

        String serviceName = action.getServiceName();
        ApiRequestDO apiRequestDO = action.getApiRequestDO();
        String message =
                "ServiceName:" + serviceName + ", request:" + (action.getParameter() == null ? "" : action.getParameter().toString());
        R result = null;
        String errorCode = null;

        RuntimeContext context = new RuntimeContext();
        RuntimeContextHolder.setCurrentRuntimeContext(context);
        Map.Entry entry = null;
        Object[] limitArgs = new Object[2];
        try {
            logger.info("Prodservice Atop Service Start ----- " + message);
            //限流处理
            if (Objects.nonNull(apiRequestDO)) {
                String api = apiRequestDO.getApi();
                limitArgs[0] = api;
//                limitArgs[1] = ApiRequestDOUtil.getCurrentSpaceOwnerUid(apiRequestDO);
                // dubbo
//                entry = SphU.entry(String.format("prodservice-atop-[%s]", api), ResourceTypeConstants.COMMON_API_GATEWAY, EntryType.IN,
//                        limitArgs);
            }
            //参数处理
            action.checkParams();

            //权限校验
            action.checkPermission();

            //业务流程处理
            ActionResult actionResult = doService(action);

            //数据处理
            result = action.dataHandler(actionResult);

        } catch (BusinessException e) {
            errorCode = e.getCode();
            logger.warn("Prodservice Atop exception : errorCode:{}", errorCode, e);
            String errorMessage = getErrorMessage(e.getCode(), e.getMessage(), getLang(apiRequestDO), null);
            if (StringUtils.isBlank(errorMessage)) {
                return new AtopResult<>(errorCode, LangUtils.toLang(errorCode));
            } else {
                return new AtopResult<>(e.getCode(), errorMessage);
            }
        } catch (Exception e) {
            logger.warn("Prodservice Atop exception : systemFailed, {}, {}", e.getMessage(), e);
            errorCode = ErrorCodeEnum.SYSTEM_ERROR.getCode();
            return new AtopResult<>(errorCode, LangUtils.toLang(errorCode));
        } finally {
            if (entry != null) {
                // sentinel
//                entry.exit(1, limitArgs);
            }
            RuntimeContextHolder.clear();
            //日志信息
            logger.info("Prodservice Atop Service End Success ----- {} ,response: {} ,useTime(ms): [{}]", message,
                    (result == null ? "" : JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect)),
                    System.currentTimeMillis() - start);
        }

        return new AtopResult<R>(result);
    }


    private ActionResult doService(AbstractAtopAction action) throws ProdserviceBaseException {
        Map<IProcEnum, Object> map = new LinkedHashMap<>();
        //由各个具体Controller实现processor信息的初始化
        action.initializeProcessors(map);
        if (map.size() == 0) {
            throw new BusinessException("SYSTEM_ERROR");
        }

        return actionEngine.executeAction(map);
    }


    /**
     * 获取语种(默认简体中文)
     *
     * @param apiRequestDO
     * @return
     */
    private String getLang(ApiRequestDO apiRequestDO) {
        return apiRequestDO != null ? apiRequestDO.getApiContextDo().getLang() :
                I18n2LangEnum.SIMPLIFIED_CHINESE.getShortName();
    }


    /**
     * 获取错误信息
     *
     * @param errorCode
     * @param errorMessage
     * @param lang
     * @param params       需要转换的参数
     * @return
     */
    private String getErrorMessage(String errorCode, String errorMessage, String lang, List<Object> params) {
        String i18nErrorMessage = null;
        try {
            if (NOT_TRANSFER_EXCEPTION_CODE.contains(errorCode)) {
                return errorMessage;
            }
            if (CollectionUtils.isEmpty(params)) {
                i18nErrorMessage = LangUtils.toLangByBizId(I18N_MODULE_NAME, I18N_BIZ_ID, errorCode, lang);
            } else {
                i18nErrorMessage =
                        LangUtils.toLangByBizId(I18N_MODULE_NAME, I18N_BIZ_ID, errorCode, lang, I18nTplContentType.ANONYMOUS_PARAM, params);
            }
        } catch (Exception e) {
            logger.warn("查询多语言配置失败，errorCode:" + errorCode);
        }

        if (i18nErrorMessage != null && i18nErrorMessage.length() != 0) {
            return i18nErrorMessage;
        }

        //如果没配置错误码多语言，兜底
        if (I18n2LangEnum.SIMPLIFIED_CHINESE.equals(I18n2LangEnum.getCompatibleLangEnum(lang))) {
            return errorMessage;
        } else {
            return errorCode;
        }
    }

}
