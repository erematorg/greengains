package com.fasterxml.jackson.databind.cfg;

public enum EnumFeature implements DatatypeFeature {
    BOGUS_FEATURE(false);
    
    private static final int FEATURE_INDEX = 0;
    private final boolean _enabledByDefault;
    private final int _mask;

    private EnumFeature(boolean z2) {
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
        return 0;
    }

    public int getMask() {
        return this._mask;
    }
}
