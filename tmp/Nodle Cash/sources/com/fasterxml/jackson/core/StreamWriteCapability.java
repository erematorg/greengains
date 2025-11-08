package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamWriteCapability implements JacksonFeature {
    CAN_WRITE_BINARY_NATIVELY(false),
    CAN_WRITE_FORMATTED_NUMBERS(false);
    
    private final boolean _defaultState;
    private final int _mask;

    private StreamWriteCapability(boolean z2) {
        this._defaultState = z2;
        this._mask = 1 << ordinal();
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public boolean enabledIn(int i3) {
        return (this._mask & i3) != 0;
    }

    public int getMask() {
        return this._mask;
    }
}
