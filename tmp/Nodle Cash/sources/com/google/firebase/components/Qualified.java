package com.google.firebase.components;

import androidx.annotation.NonNull;
import androidx.constraintlayout.core.state.b;
import java.lang.annotation.Annotation;
import org.apache.commons.lang3.StringUtils;

public final class Qualified<T> {
    private final Class<? extends Annotation> qualifier;
    private final Class<T> type;

    public @interface Unqualified {
    }

    public Qualified(Class<? extends Annotation> cls, Class<T> cls2) {
        this.qualifier = cls;
        this.type = cls2;
    }

    @NonNull
    public static <T> Qualified<T> qualified(Class<? extends Annotation> cls, Class<T> cls2) {
        return new Qualified<>(cls, cls2);
    }

    @NonNull
    public static <T> Qualified<T> unqualified(Class<T> cls) {
        return new Qualified<>(Unqualified.class, cls);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Qualified.class != obj.getClass()) {
            return false;
        }
        Qualified qualified = (Qualified) obj;
        if (!this.type.equals(qualified.type)) {
            return false;
        }
        return this.qualifier.equals(qualified.qualifier);
    }

    public int hashCode() {
        return this.qualifier.hashCode() + (this.type.hashCode() * 31);
    }

    public String toString() {
        if (this.qualifier == Unqualified.class) {
            return this.type.getName();
        }
        StringBuilder sb = new StringBuilder("@");
        b.t(this.qualifier, sb, StringUtils.SPACE);
        sb.append(this.type.getName());
        return sb.toString();
    }
}
