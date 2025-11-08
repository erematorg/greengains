package com.airbnb.lottie.model;

import android.support.v4.media.session.a;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Marker;

public class KeyPath {
    public static final KeyPath COMPOSITION = new KeyPath("COMPOSITION");
    private final List<String> keys;
    @Nullable
    private KeyPathElement resolvedElement;

    public KeyPath(String... strArr) {
        this.keys = Arrays.asList(strArr);
    }

    private boolean endsWithGlobstar() {
        return ((String) a.h(this.keys, 1)).equals("**");
    }

    private boolean isContainer(String str) {
        return "__container".equals(str);
    }

    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPath addKey(String str) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.keys.add(str);
        return keyPath;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyPath keyPath = (KeyPath) obj;
        if (!this.keys.equals(keyPath.keys)) {
            return false;
        }
        KeyPathElement keyPathElement = this.resolvedElement;
        return keyPathElement != null ? keyPathElement.equals(keyPath.resolvedElement) : keyPath.resolvedElement == null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean fullyResolvesTo(String str, int i3) {
        if (i3 >= this.keys.size()) {
            return false;
        }
        boolean z2 = i3 == this.keys.size() - 1;
        String str2 = this.keys.get(i3);
        if (!str2.equals("**")) {
            return (z2 || (i3 == this.keys.size() + -2 && endsWithGlobstar())) && (str2.equals(str) || str2.equals(Marker.ANY_MARKER));
        } else if (!z2 && this.keys.get(i3 + 1).equals(str)) {
            return i3 == this.keys.size() + -2 || (i3 == this.keys.size() + -3 && endsWithGlobstar());
        } else {
            if (z2) {
                return true;
            }
            int i4 = i3 + 1;
            if (i4 < this.keys.size() - 1) {
                return false;
            }
            return this.keys.get(i4).equals(str);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPathElement getResolvedElement() {
        return this.resolvedElement;
    }

    public int hashCode() {
        int hashCode = this.keys.hashCode() * 31;
        KeyPathElement keyPathElement = this.resolvedElement;
        return hashCode + (keyPathElement != null ? keyPathElement.hashCode() : 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int incrementDepthBy(String str, int i3) {
        if (isContainer(str)) {
            return 0;
        }
        if (!this.keys.get(i3).equals("**")) {
            return 1;
        }
        return (i3 != this.keys.size() - 1 && this.keys.get(i3 + 1).equals(str)) ? 2 : 0;
    }

    public String keysToString() {
        return this.keys.toString();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean matches(String str, int i3) {
        if (isContainer(str)) {
            return true;
        }
        if (i3 >= this.keys.size()) {
            return false;
        }
        return this.keys.get(i3).equals(str) || this.keys.get(i3).equals("**") || this.keys.get(i3).equals(Marker.ANY_MARKER);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean propagateToChildren(String str, int i3) {
        return "__container".equals(str) || i3 < this.keys.size() - 1 || this.keys.get(i3).equals("**");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPath resolve(KeyPathElement keyPathElement) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.resolvedElement = keyPathElement;
        return keyPath;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("KeyPath{keys=");
        sb.append(this.keys);
        sb.append(",resolved=");
        return i.c(sb, this.resolvedElement != null, AbstractJsonLexerKt.END_OBJ);
    }

    private KeyPath(KeyPath keyPath) {
        this.keys = new ArrayList(keyPath.keys);
        this.resolvedElement = keyPath.resolvedElement;
    }
}
