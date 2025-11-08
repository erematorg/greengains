package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.util.JacksonFeature;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Serializable;

public class DatatypeFeatures implements Serializable {
    protected static final int FEATURE_INDEX_ENUM = 0;
    protected static final int FEATURE_INDEX_JSON_NODE = 1;
    private static final long serialVersionUID = 1;
    private final int _enabledFor1;
    private final int _enabledFor2;
    private final int _explicitFor1;
    private final int _explicitFor2;

    public static class DefaultHolder {
        private static final DatatypeFeatures DEFAULT_FEATURES = new DatatypeFeatures(collectDefaults(EnumFeature.values()), 0, collectDefaults(JsonNodeFeature.values()), 0);

        private DefaultHolder() {
        }

        private static <F extends Enum<F> & JacksonFeature> int collectDefaults(F[] fArr) {
            int i3 = 0;
            for (F f2 : fArr) {
                if (f2.enabledByDefault()) {
                    i3 |= f2.getMask();
                }
            }
            return i3;
        }

        public static DatatypeFeatures getDefault() {
            return DEFAULT_FEATURES;
        }
    }

    public DatatypeFeatures(int i3, int i4, int i5, int i6) {
        this._enabledFor1 = i3;
        this._explicitFor1 = i4;
        this._enabledFor2 = i5;
        this._explicitFor2 = i6;
    }

    private static final int _calcMask(DatatypeFeature... datatypeFeatureArr) {
        int i3 = 0;
        for (DatatypeFeature mask : datatypeFeatureArr) {
            i3 |= mask.getMask();
        }
        return i3;
    }

    private DatatypeFeatures _with(int i3, int i4, int i5, int i6) {
        return (this._enabledFor1 == i3 && this._explicitFor1 == i4 && this._enabledFor2 == i5 && this._explicitFor2 == i6) ? this : new DatatypeFeatures(i3, i4, i5, i6);
    }

    public static DatatypeFeatures defaultFeatures() {
        return DefaultHolder.getDefault();
    }

    public Boolean getExplicitState(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex != 1) {
                VersionUtil.throwInternal();
                return null;
            } else if (datatypeFeature.enabledIn(this._explicitFor2)) {
                return Boolean.valueOf(datatypeFeature.enabledIn(this._enabledFor2));
            } else {
                return null;
            }
        } else if (datatypeFeature.enabledIn(this._explicitFor1)) {
            return Boolean.valueOf(datatypeFeature.enabledIn(this._enabledFor1));
        } else {
            return null;
        }
    }

    public boolean isEnabled(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex == 0) {
            return datatypeFeature.enabledIn(this._enabledFor1);
        }
        if (featureIndex == 1) {
            return datatypeFeature.enabledIn(this._enabledFor2);
        }
        VersionUtil.throwInternal();
        return false;
    }

    public boolean isExplicitlySet(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex == 0) {
            return datatypeFeature.enabledIn(this._explicitFor1);
        }
        if (featureIndex == 1) {
            return datatypeFeature.enabledIn(this._explicitFor2);
        }
        VersionUtil.throwInternal();
        return false;
    }

    public DatatypeFeatures with(DatatypeFeature datatypeFeature) {
        int mask = datatypeFeature.getMask();
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex == 0) {
            return _with(this._enabledFor1 | mask, mask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
        }
        if (featureIndex == 1) {
            return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 | mask, mask | this._explicitFor2);
        }
        VersionUtil.throwInternal();
        return this;
    }

    public DatatypeFeatures withFeatures(DatatypeFeature... datatypeFeatureArr) {
        int _calcMask = _calcMask(datatypeFeatureArr);
        if (_calcMask == 0) {
            return this;
        }
        int featureIndex = datatypeFeatureArr[0].featureIndex();
        if (featureIndex == 0) {
            return _with(this._enabledFor1 | _calcMask, _calcMask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
        }
        if (featureIndex == 1) {
            return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 | _calcMask, _calcMask | this._explicitFor2);
        }
        VersionUtil.throwInternal();
        return this;
    }

    public DatatypeFeatures without(DatatypeFeature datatypeFeature) {
        int mask = datatypeFeature.getMask();
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex == 0) {
            return _with(this._enabledFor1 & (~mask), mask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
        }
        if (featureIndex == 1) {
            return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 & (~mask), mask | this._explicitFor2);
        }
        VersionUtil.throwInternal();
        return this;
    }

    public DatatypeFeatures withoutFeatures(DatatypeFeature... datatypeFeatureArr) {
        int _calcMask = _calcMask(datatypeFeatureArr);
        if (_calcMask == 0) {
            return this;
        }
        int featureIndex = datatypeFeatureArr[0].featureIndex();
        if (featureIndex == 0) {
            return _with(this._enabledFor1 & (~_calcMask), _calcMask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
        }
        if (featureIndex == 1) {
            return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 & (~_calcMask), _calcMask | this._explicitFor2);
        }
        VersionUtil.throwInternal();
        return this;
    }
}
