package com.wei.java.design.pattern.template.service.v2;


import com.alibaba.fastjson.JSONObject;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.wei.java.design.pattern.template.service.longsheng.AbstractAction;
import com.wei.java.design.pattern.template.service.longsheng.ApiRequestDO;

import java.util.logging.Logger;

public abstract class AbstractApiAction<R, P> extends AbstractAction<R, P> implements IAtopAction<R, P> {

    protected static final Logger logger = LoggerFactory.getLogger(ApiTemplateImpl.class);

    private final ApiRequestDO fusionContext;


    public AbstractApiAction(String serviceName, P parameter, ApiRequestDO fusionContext) {
        super(serviceName, parameter);
        if (fusionContext == null || fusionContext.getApi() == null || fusionContext.getClientId() == null) {
            logger.info("error input fusionContext :{}" + JSONObject.toJSONString(fusionContext));
            throw new RuntimeException("ErrorCodeEnum.SYSTEM_ERROR");
        }
        this.fusionContext = fusionContext;
    }


    public ApiRequestDO getFusionContext() {
        return fusionContext;
    }
}
