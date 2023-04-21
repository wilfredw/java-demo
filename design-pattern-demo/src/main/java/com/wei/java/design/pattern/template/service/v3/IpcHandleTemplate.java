package com.wei.java.design.pattern.template.service.v3;


import lombok.extern.slf4j.Slf4j;

/**
 * @author buhuan.wang
 * @since 2023/4/19
 */
@Slf4j
public class IpcHandleTemplate {

    public <P, R> AbsResponse<R> handle(String serviceName, P param, IpcHandler<P, R> handler) {
        long start = System.currentTimeMillis();
        log.info("IpcHandleTemplate handle start. serviceName:{} param:{}", serviceName, param);
        AbsResponse<R> absResponse = null;
        try {
            // validate params
            // validate permission

            absResponse = handler.handle(param);
        } catch (PaladinException paladinException) {
            IpcHestiaErrorEnums ipcHestiaErrorEnums = IpcHestiaErrorEnums.getByMappingErrorCode(paladinException.getCode());
            if (ipcHestiaErrorEnums != null) {
                absResponse = IpcAbsResponseUtils.getResp(ipcHestiaErrorEnums, paladinException.getParams());
            } else {
                Object[] params = new Object[] {paladinException.getCode(), paladinException.getErrorMsg()};
                absResponse = IpcAbsResponseUtils.getResp(IpcHestiaErrorEnums.BUSINESS_EXCEPTION, params);
            }
        } catch (Throwable t) {
            log.error("System error", t);
            absResponse = IpcAbsResponseUtils.getResp(IpcHestiaErrorEnums.SYSTEM_ERROR);
        } finally {
            long end = System.currentTimeMillis();
            log.info("IpcHandleTemplate handle end. useTime(ms): [{}], result:{}", (end - start), absResponse);
        }

        return absResponse;
    }

}
