package com.google.android.gms.common.images;

import A.a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Size {
    private final int zaa;
    private final int zab;

    public Size(int i3, int i4) {
        this.zaa = i3;
        this.zab = i4;
    }

    @NonNull
    public static Size parseSize(@NonNull String str) throws NumberFormatException {
        if (str != null) {
            int indexOf = str.indexOf(42);
            if (indexOf < 0) {
                indexOf = str.indexOf(120);
            }
            if (indexOf >= 0) {
                try {
                    return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                    throw zaa(str);
                }
            } else {
                throw zaa(str);
            }
        } else {
            throw new IllegalArgumentException("string must not be null");
        }
    }

    private static NumberFormatException zaa(String str) {
        throw new NumberFormatException(a.l("Invalid Size: \"", str, "\""));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.zaa == size.zaa && this.zab == size.zab;
        }
    }

    public int getHeight() {
        return this.zab;
    }

    public int getWidth() {
        return this.zaa;
    }

    public int hashCode() {
        int i3 = this.zaa;
        return this.zab ^ ((i3 >>> 16) | (i3 << 16));
    }

    @NonNull
    public String toString() {
        return this.zaa + "x" + this.zab;
    }
}
