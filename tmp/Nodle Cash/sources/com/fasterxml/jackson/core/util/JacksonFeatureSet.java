package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.util.JacksonFeature;

public final class JacksonFeatureSet<F extends JacksonFeature> {
    protected int _enabled;

    public JacksonFeatureSet(int i3) {
        this._enabled = i3;
    }

    public static <F extends JacksonFeature> JacksonFeatureSet<F> fromBitmask(int i3) {
        return new JacksonFeatureSet<>(i3);
    }

    public static <F extends JacksonFeature> JacksonFeatureSet<F> fromDefaults(F[] fArr) {
        if (fArr.length <= 31) {
            int i3 = 0;
            for (F f2 : fArr) {
                if (f2.enabledByDefault()) {
                    i3 |= f2.getMask();
                }
            }
            return new JacksonFeatureSet<>(i3);
        }
        throw new IllegalArgumentException(String.format("Can not use type `%s` with JacksonFeatureSet: too many entries (%d > 31)", new Object[]{fArr[0].getClass().getName(), Integer.valueOf(fArr.length)}));
    }

    public int asBitmask() {
        return this._enabled;
    }

    public boolean isEnabled(F f2) {
        return (this._enabled & f2.getMask()) != 0;
    }

    public JacksonFeatureSet<F> with(F f2) {
        int mask = f2.getMask() | this._enabled;
        return mask == this._enabled ? this : new JacksonFeatureSet<>(mask);
    }

    public JacksonFeatureSet<F> without(F f2) {
        int i3 = (~f2.getMask()) & this._enabled;
        return i3 == this._enabled ? this : new JacksonFeatureSet<>(i3);
    }
}
