package com.wei.java.design.pattern.template.service.v2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.wei.java.design.pattern.template.service.longsheng.ApiRequestDO;
import com.wei.java.design.pattern.template.service.longsheng.AtopResult;
import com.wei.java.design.pattern.template.service.longsheng.BusinessException;

import java.util.logging.Logger;

public class ApiTemplateImpl implements IApiTemplate {

    protected static final Logger logger = LoggerFactory.getLogger(ApiTemplateImpl.class);

    @Override
    public <R, P> AtopResult<R> execute(AbstractApiAction<R, P> action) {
        long start = System.currentTimeMillis();

        String serviceName = action.getServiceName();
        ApiRequestDO fusionContext = action.getFusionContext();
        StringBuilder sb = new StringBuilder();
        String message = sb
                .append("ApiName: ").append(fusionContext.getApiName())
                .append(", apiVersion: ").append(fusionContext.getApiVersion())
                .append(", serviceName: ").append(serviceName)
                .append(", clientID: ").append(fusionContext.getClientId())
                .append(", request: ").append(action.getParameter() == null ? "null" : JSON.toJSONString(action.getParameter()))
                .toString();
        R result = null;
        long useTime = 0;
        String errorCode = null;

//        RuntimeContext context = new RuntimeContext();
//        context.setApiContextDO(apiContextDO);
//        RuntimeContextHolder.setCurrentRuntimeContext(context);
        logger.info("Operation Api Service Start ----- " + message);

        try {
            //限流
            // currentLimiting();

            //参数处理
            action.checkParams(action.getParameter());
            //数据处理
            result = action.doAction(action.getParameter());

            return FusionResult.buildSuccess(result);
        } catch (BusinessException e) {
            errorCode = getApiErrorCode(e.getCode());
            logger.warn("Operation Api BaseException : errorCode:{}", errorCode, e);
            return FusionResult.buildFail(errorCode, LangUtils.toLang(errorCode));
        } catch (Exception e) {
            logger.error("Operation Api exception : systemFailed, {}, {}", e.getMessage(), e);
            errorCode = ApiErrorEnum.SYSTEM_ERROR.getErrorCodeStr();
            return FusionResult.buildFail(errorCode, LangUtils.toLang(errorCode));
        } finally {

            //日志信息
            useTime = System.currentTimeMillis() - start;
            logger.info("Operation Api Service End Success ----- {} ,response: {} ,useTime(ms): [{}]", message,
                    (result == null ? "" : JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect)),
                    useTime);
        }
    }

    public static String getApiErrorCode(String errorCode) {
        return getApiError(errorCode).getErrorCodeStr();
    }

    public static ApiErrorEnum getApiError(String errorCode) {
        ApiErrorEnum apiErrorEnum = ApiErrorEnum.getByErrorCode(errorCode);
        if (apiErrorEnum != null) {
            return apiErrorEnum;
        }
        apiErrorEnum = ApiErrorEnum.getByMappingErrorCode(errorCode);
        if (apiErrorEnum != null) {
            return apiErrorEnum;
        }
        return ApiErrorEnum.SYSTEM_ERROR;
    }
}
