package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;

public final class ConstructorDetector implements Serializable {
    public static final ConstructorDetector DEFAULT = new ConstructorDetector(SingleArgConstructor.HEURISTIC);
    public static final ConstructorDetector EXPLICIT_ONLY = new ConstructorDetector(SingleArgConstructor.REQUIRE_MODE);
    public static final ConstructorDetector USE_DELEGATING = new ConstructorDetector(SingleArgConstructor.DELEGATING);
    public static final ConstructorDetector USE_PROPERTIES_BASED = new ConstructorDetector(SingleArgConstructor.PROPERTIES);
    private static final long serialVersionUID = 1;
    protected final boolean _allowJDKTypeCtors;
    protected final boolean _requireCtorAnnotation;
    protected final SingleArgConstructor _singleArgMode;

    public enum SingleArgConstructor {
        DELEGATING,
        PROPERTIES,
        HEURISTIC,
        REQUIRE_MODE
    }

    public ConstructorDetector(SingleArgConstructor singleArgConstructor, boolean z2, boolean z3) {
        this._singleArgMode = singleArgConstructor;
        this._requireCtorAnnotation = z2;
        this._allowJDKTypeCtors = z3;
    }

    public boolean allowJDKTypeConstructors() {
        return this._allowJDKTypeCtors;
    }

    public boolean requireCtorAnnotation() {
        return this._requireCtorAnnotation;
    }

    public boolean shouldIntrospectorImplicitConstructors(Class<?> cls) {
        if (this._requireCtorAnnotation) {
            return false;
        }
        return this._allowJDKTypeCtors || !ClassUtil.isJDKClass(cls) || Throwable.class.isAssignableFrom(cls);
    }

    public boolean singleArgCreatorDefaultsToDelegating() {
        return this._singleArgMode == SingleArgConstructor.DELEGATING;
    }

    public boolean singleArgCreatorDefaultsToProperties() {
        return this._singleArgMode == SingleArgConstructor.PROPERTIES;
    }

    public SingleArgConstructor singleArgMode() {
        return this._singleArgMode;
    }

    public ConstructorDetector withAllowJDKTypeConstructors(boolean z2) {
        return new ConstructorDetector(this._singleArgMode, this._requireCtorAnnotation, z2);
    }

    public ConstructorDetector withRequireAnnotation(boolean z2) {
        return new ConstructorDetector(this._singleArgMode, z2, this._allowJDKTypeCtors);
    }

    public ConstructorDetector withSingleArgMode(SingleArgConstructor singleArgConstructor) {
        return new ConstructorDetector(singleArgConstructor, this._requireCtorAnnotation, this._allowJDKTypeCtors);
    }

    public ConstructorDetector(SingleArgConstructor singleArgConstructor) {
        this(singleArgConstructor, false, false);
    }
}
