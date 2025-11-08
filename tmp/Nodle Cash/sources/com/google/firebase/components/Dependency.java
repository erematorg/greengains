package com.google.firebase.components;

import A.a;
import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import org.apache.commons.text.StringSubstitutor;
import org.apache.xerces.impl.xs.SchemaSymbols;

public final class Dependency {
    private final Qualified<?> anInterface;
    private final int injection;
    private final int type;

    private Dependency(Class<?> cls, int i3, int i4) {
        this(Qualified.unqualified(cls), i3, i4);
    }

    public static Dependency deferred(Class<?> cls) {
        return new Dependency(cls, 0, 2);
    }

    private static String describeInjection(int i3) {
        if (i3 == 0) {
            return DevicePublicKeyStringDef.DIRECT;
        }
        if (i3 == 1) {
            return "provider";
        }
        if (i3 == 2) {
            return "deferred";
        }
        throw new AssertionError(a.k("Unsupported injection: ", i3));
    }

    @Deprecated
    public static Dependency optional(Class<?> cls) {
        return new Dependency(cls, 0, 0);
    }

    public static Dependency optionalProvider(Class<?> cls) {
        return new Dependency(cls, 0, 1);
    }

    public static Dependency required(Class<?> cls) {
        return new Dependency(cls, 1, 0);
    }

    public static Dependency requiredProvider(Class<?> cls) {
        return new Dependency(cls, 1, 1);
    }

    public static Dependency setOf(Class<?> cls) {
        return new Dependency(cls, 2, 0);
    }

    public static Dependency setOfProvider(Class<?> cls) {
        return new Dependency(cls, 2, 1);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dependency)) {
            return false;
        }
        Dependency dependency = (Dependency) obj;
        return this.anInterface.equals(dependency.anInterface) && this.type == dependency.type && this.injection == dependency.injection;
    }

    public Qualified<?> getInterface() {
        return this.anInterface;
    }

    public int hashCode() {
        return this.injection ^ ((((this.anInterface.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003);
    }

    public boolean isDeferred() {
        return this.injection == 2;
    }

    public boolean isDirectInjection() {
        return this.injection == 0;
    }

    public boolean isRequired() {
        return this.type == 1;
    }

    public boolean isSet() {
        return this.type == 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.anInterface);
        sb.append(", type=");
        int i3 = this.type;
        sb.append(i3 == 1 ? SchemaSymbols.ATTVAL_REQUIRED : i3 == 0 ? SchemaSymbols.ATTVAL_OPTIONAL : "set");
        sb.append(", injection=");
        return a.n(sb, describeInjection(this.injection), StringSubstitutor.DEFAULT_VAR_END);
    }

    private Dependency(Qualified<?> qualified, int i3, int i4) {
        this.anInterface = (Qualified) Preconditions.checkNotNull(qualified, "Null dependency anInterface.");
        this.type = i3;
        this.injection = i4;
    }

    public static Dependency deferred(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 2);
    }

    public static Dependency optionalProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 1);
    }

    public static Dependency required(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 0);
    }

    public static Dependency requiredProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 1);
    }

    public static Dependency setOf(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 0);
    }

    public static Dependency setOfProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 1);
    }
}
