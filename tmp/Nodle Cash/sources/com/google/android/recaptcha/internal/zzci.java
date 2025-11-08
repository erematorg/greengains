package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzci {
    @NotNull
    public static final zzci zza = new zzci();

    private zzci() {
    }

    @NotNull
    public static final Class zza(@Nullable Object obj) {
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (obj instanceof Integer) {
            int intValue = ((Number) obj).intValue();
            Class cls = intValue == 1 ? Integer.TYPE : intValue == 2 ? Short.TYPE : intValue == 3 ? Byte.TYPE : intValue == 4 ? Long.TYPE : intValue == 5 ? Character.TYPE : intValue == 6 ? Float.TYPE : intValue == 7 ? Double.TYPE : intValue == 8 ? Boolean.TYPE : null;
            if (cls != null) {
                return cls;
            }
            throw new zzae(4, 6, (Throwable) null);
        } else if (obj instanceof String) {
            try {
                String str = (String) obj;
                Class<?> cls2 = Class.forName(str);
                if (zzcb.zzb(str)) {
                    return cls2;
                }
                throw new zzae(6, 47, (Throwable) null);
            } catch (Exception e3) {
                throw new zzae(6, 8, e3);
            }
        } else {
            throw new zzae(4, 5, (Throwable) null);
        }
    }
}
