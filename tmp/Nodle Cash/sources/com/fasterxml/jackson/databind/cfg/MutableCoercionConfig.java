package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;

public class MutableCoercionConfig extends CoercionConfig implements Serializable {
    private static final long serialVersionUID = 1;

    public MutableCoercionConfig() {
    }

    public MutableCoercionConfig copy() {
        return new MutableCoercionConfig(this);
    }

    public MutableCoercionConfig setAcceptBlankAsEmpty(Boolean bool) {
        this._acceptBlankAsEmpty = bool;
        return this;
    }

    public MutableCoercionConfig setCoercion(CoercionInputShape coercionInputShape, CoercionAction coercionAction) {
        this._coercionsByShape[coercionInputShape.ordinal()] = coercionAction;
        return this;
    }

    public MutableCoercionConfig(MutableCoercionConfig mutableCoercionConfig) {
        super(mutableCoercionConfig);
    }
}
