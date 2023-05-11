package com.wei.java.algorithm.testcase.runnermachine;

import com.wei.java.thread.pool.exception.BusinessException;

/**
 * 跑步机抽象接口
 */
public interface RunnerMachine {
    /**
     * 启动跑步机
     * @return 默认表示成功
     * @exception BusinessException 表示失败，具体看业务错误码
     */
    void start() throws BusinessException;

    /**
     * 停止跑步机
     * @return 默认表示成功
     * @exception BusinessException 表示失败，具体看业务错误码
     */
    void end() throws BusinessException;

    /**
     * 设置跑步机速度
     * @param speed 速度
     * @return 默认表示成功
     * @exception BusinessException 表示失败，具体看业务错误码
     */
    void setSpeed(int speed) throws BusinessException;

    /**
     * 调整速度
     * @param speed 速度改变量
     * @param changeSpeedEnum 调整动作，INCREASE是加快速度，DECREASE是减慢速度
     * @return 默认表示成功
     * @exception BusinessException 表示失败，具体看业务错误码
     */
    void changeSpeed(int speed, ChangeSpeedEnum changeSpeedEnum) throws BusinessException;

    /**
     * 获取跑步统计数据
     * @return RunStats 跑步统计数据
     * @exception BusinessException 表示失败，具体看业务错误码
     */
    RunStats getRunStats() throws BusinessException;
}
