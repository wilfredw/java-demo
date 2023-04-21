package com.wei.java.design.pattern.template.service.longsheng.test;

import com.alibaba.fastjson.JSON;
import com.wei.java.design.pattern.template.service.longsheng.AbstractAtopAction;
import com.wei.java.design.pattern.template.service.longsheng.ApiRequestDO;
import com.wei.java.design.pattern.template.service.longsheng.AtopResult;
import com.wei.java.design.pattern.template.service.longsheng.AtopTemplateImpl;
import com.wei.java.design.pattern.template.service.longsheng.IAtopTemplate;
import com.wei.java.design.pattern.template.service.longsheng.actionengine.ActionResult;
import com.wei.java.design.pattern.template.service.longsheng.actionengine.enums.IProcEnum;

import javax.xml.bind.ValidationException;
import java.util.Map;

/**
 * 类的详细说明
 * 如果继承某个接口{@link StateContext}，提供一个链接，方便使用
 * <p>
 * 如果是过期的接口，那么请尽量提供用于替换的类的链接地址{@link StateContext}
 *
 * @param <S> 如果用到泛型，可以这样注释
 * @param <E> 如果用到泛型，可以这样注释
 * @author buhuan.wang
 * @since 2023/4/19
 */
public class TestService {
    private IAtopTemplate atopTemplate = new AtopTemplateImpl();

    public static String getPrefixName(Object o) {
        return o.getClass().getSimpleName() + "_";
    }

    public AtopResult<Boolean> addFirmwareUpgrade(String param, ApiRequestDO apiRequestDO) {
        String serviceName = getPrefixName(this) + Thread.currentThread().getStackTrace()[1].getMethodName();
        return this.atopTemplate.execute(new AbstractAtopAction<Boolean, String>(serviceName, param, apiRequestDO) {
            @Override
            public void checkParams() throws ValidationException {
                atopReqVO = JSON.parseObject(param, FwUpgradeAddReqBO.class);
                CheckParamUtil.checkPositiveNumber(atopReqVO.getProductId());
                CheckParamUtil.checkNotNull(atopReqVO.getFirmwareKey());
                CheckParamUtil.checkNotNull(atopReqVO.getVersion());
                CheckParamUtil.checkNotNull(atopReqVO.getLangTexts());
                CheckParamUtil.checkNotNull(atopReqVO.getAutoUpgrade());
            }

            @Override
            public void checkPermission() throws ValidationException {

            }

            @Override
            public void initializeProcessors(Map<IProcEnum, Object> map) {
                FwUpgradeAddReqBO fwUpgradeAddReqBO = new FwUpgradeAddReqBO();
                fwUpgradeAddReqBO.setI18nShortName("name");
                map.put(OTAProcEnum.FIRMWARE_UPGRADE_CONFIG_ADD, fwUpgradeAddReqBO);
            }

            @Override
            public Boolean dataHandler(ActionResult actionResult) {
                return (Boolean) actionResult.getProcessorResult(OTAProcEnum.FIRMWARE_UPGRADE_CONFIG_ADD);
            }
        });
    }
}
