package com.airbnb.lottie.model;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.i;
import androidx.core.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class MutablePair<T> {
    @Nullable
    T first;
    @Nullable
    T second;

    private static boolean objectsEqual(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return objectsEqual(pair.first, this.first) && objectsEqual(pair.second, this.second);
    }

    public int hashCode() {
        T t2 = this.first;
        int i3 = 0;
        int hashCode = t2 == null ? 0 : t2.hashCode();
        T t3 = this.second;
        if (t3 != null) {
            i3 = t3.hashCode();
        }
        return hashCode ^ i3;
    }

    public void set(T t2, T t3) {
        this.first = t2;
        this.second = t3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Pair{");
        sb.append(this.first);
        sb.append(StringUtils.SPACE);
        return i.b(sb, this.second, StringSubstitutor.DEFAULT_VAR_END);
    }
}
