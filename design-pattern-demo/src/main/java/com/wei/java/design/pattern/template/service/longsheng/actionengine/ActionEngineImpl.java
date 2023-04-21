package com.wei.java.design.pattern.template.service.longsheng.actionengine;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * action 分发的内部服务实现类
 *
 * @author zhoulongsheng
 * @version $Id: ActionEngineImpl.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
@Slf4j
@Service
public class ActionEngineImpl implements ActionEngine, ApplicationContextAware {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public ActionResult executeAction(Map<IProcEnum, Object> map) {
        if (map == null || map.size() == 0) {
            throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        ActionResult actionResult = new ActionResult();
        for (Map.Entry<IProcEnum, Object> entry : map.entrySet()) {

            Object result = null;
            String className = null;
            String methodName = null;
            try {
                Method method = ProcContextHelper.getProcMethod(entry.getKey());
                Class clz = method.getDeclaringClass();
                className = clz.getName();
                methodName = method.getName();
                method.setAccessible(true);
                result = method.invoke(applicationContext.getBean(method.getDeclaringClass()), entry.getValue());

                //一些rpc接口未返回错误码，直接抛错的情况，特殊处理
                handleSpecialException(result);
                log.info("ActionEngine executeAction success, processor:{}, class:{}, method:{}, result:{}",
                        entry.getKey().procName(), clz.getName(), method.getName(), result);

                actionResult.addProcessorResult(entry.getKey(), result);
            } catch (InvocationTargetException e) {
                log.error("ActionEngine executeAction InvocationTargetException, processor:{}, class:{}, method:{}, result:{}, error:{}",
                        entry.getKey().procName(), className, methodName, result, e);
                Throwable throwable = e.getTargetException();
                if (throwable instanceof ProdserviceBaseException) {
                    ProdserviceBaseException exception = (ProdserviceBaseException) throwable;
                    String errorCode = StringUtils.defaultIfBlank(exception.getCode(), exception.getErrorCode());
                    List<Object> params = exception.getParams();
                    ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.getResultCodeEnumByCode(errorCode);
                    errorCodeEnum = errorCodeEnum != null ? errorCodeEnum : ErrorCodeEnum.SYSTEM_ERROR;
                    log.warn("ActionEngine executeAction InvocationTargetException, errorCode: {}", errorCode);
                    throw new ProdserviceBaseException(errorCodeEnum, params);
                } else if (throwable instanceof ValidationException) {
                    throw new ValidationException(((ValidationException) throwable).getCodeEnum());
                } else if (throwable instanceof BaseException) {
                    throw new BaseException(((BaseException) throwable).getCode(), throwable.getMessage());
                } else {
                    throw new BaseException(throwable.getMessage());
                }
            } catch (Exception e) {
                log.error("ActionEngine executeAction Exception, processor:{}, class:{}, method:{}, result:{}, error:{}",
                        entry.getKey().procName(), className, methodName, result, e);
                throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
            }
        }
        return actionResult;
    }


    private void handleSpecialException(Object result) {
        if (result != null && result instanceof CommonRspBO) {
            CommonRspBO rspBO = (CommonRspBO) result;
            if (rspBO.isSuccess()) {
                return;
            }
            ErrorCodeEnum errCode = ErrorCodeEnum.getResultCodeEnumByCode(rspBO.getErrorCode());
            if (errCode != null) {
                throw new ProdserviceBaseException(errCode.getCode(), errCode.getMessage());
            } else {
                throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
            }
        }
    }

    /**
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
