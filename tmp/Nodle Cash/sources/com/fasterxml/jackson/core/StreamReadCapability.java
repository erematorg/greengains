package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamReadCapability implements JacksonFeature {
    DUPLICATE_PROPERTIES(false),
    SCALARS_AS_OBJECTS(false),
    UNTYPED_SCALARS(false),
    EXACT_FLOATS(false);
    
    private final boolean _defaultState;
    private final int _mask;

    private StreamReadCapability(boolean z2) {
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
