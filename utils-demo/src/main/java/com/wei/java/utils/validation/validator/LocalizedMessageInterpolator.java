package com.wei.java.utils.validation.validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

/**
 * @author wujun
 * create time: 7:42 下午
 */
public class LocalizedMessageInterpolator extends ResourceBundleMessageInterpolator {

    public LocalizedMessageInterpolator() {
    }

    /**
     * 将用户的 locale 信息传递给消息解释器，而不是使用默认的 JVM locale 信息
     */
    @Override
    public String interpolate(String message, Context context) {
        return message;
    }

}
