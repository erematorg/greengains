package com.fasterxml.jackson.databind.cfg;

public enum JsonNodeFeature implements DatatypeFeature {
    READ_NULL_PROPERTIES(true),
    WRITE_NULL_PROPERTIES(true);
    
    private static final int FEATURE_INDEX = 1;
    private final boolean _enabledByDefault;
    private final int _mask;

    private JsonNodeFeature(boolean z2) {
        this._enabledByDefault = z2;
        this._mask = 1 << ordinal();
    }

    public boolean enabledByDefault() {
        return this._enabledByDefault;
    }

    public boolean enabledIn(int i3) {
        return (this._mask & i3) != 0;
    }

    public int featureIndex() {
        return 1;
    }

    public int getMask() {
        return this._mask;
    }
}
