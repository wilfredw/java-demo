package com.wei.java.design.pattern.template.service.v3;

import java.util.Arrays;

public class PaladinException extends BaseException {
    private static final long serialVersionUID = 2215802707587683879L;
    private Object[] params;

    public PaladinException(String errorCode) {
        super(errorCode, (String) null);
    }

    public PaladinException(String errorCode, String message) {
        super(errorCode, message);
    }

    public PaladinException(String errorCode, String message, Object... params) {
        super(errorCode, message);
        if (params != null) {
            this.params = Arrays.copyOf(params, params.length);
        }

    }

    public PaladinException(Throwable cause) {
        super(cause);
    }

    public PaladinException(IEnum enums) {
        super(enums);
    }

    public Object[] getParams() {
        return this.params;
    }

}
