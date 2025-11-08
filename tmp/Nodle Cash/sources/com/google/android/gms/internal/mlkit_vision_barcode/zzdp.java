package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Objects;
import javax.annotation.CheckForNull;

final class zzdp extends zzcu {
    final transient Object[] zza;

    private zzdp(@CheckForNull Object obj, Object[] objArr, int i3) {
        this.zza = objArr;
    }

    public static zzdp zzg(int i3, Object[] objArr, zzct zzct) {
        Object obj = objArr[0];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[1];
        Objects.requireNonNull(obj2);
        zzby.zzb(obj, obj2);
        return new zzdp((Object) null, objArr, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L_0x0005
        L_0x0003:
            r2 = r0
            goto L_0x0019
        L_0x0005:
            java.lang.Object[] r2 = r2.zza
            r1 = 0
            r1 = r2[r1]
            java.util.Objects.requireNonNull(r1)
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0003
            r3 = 1
            r2 = r2[r3]
            java.util.Objects.requireNonNull(r2)
        L_0x0019:
            if (r2 != 0) goto L_0x001c
            return r0
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdp.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return 1;
    }

    public final zzcn zza() {
        return new zzdo(this.zza, 1, 1);
    }

    public final zzcv zzd() {
        return new zzdm(this, this.zza, 0, 1);
    }

    public final zzcv zze() {
        return new zzdn(this, new zzdo(this.zza, 0, 1));
    }
}
