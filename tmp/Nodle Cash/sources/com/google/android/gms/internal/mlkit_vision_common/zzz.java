package com.google.android.gms.internal.mlkit_vision_common;

import javax.annotation.CheckForNull;

final class zzz extends zzr {
    static final zzr zza = new zzz((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    private zzz(@CheckForNull Object obj, Object[] objArr, int i3) {
        this.zzb = objArr;
        this.zzc = i3;
    }

    public static zzz zzg(int i3, Object[] objArr, zzq zzq) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzi.zza(obj, obj2);
        return new zzz((Object) null, objArr, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.Object[] r0 = r3.zzb
            int r3 = r3.zzc
            r1 = 0
            if (r4 != 0) goto L_0x0009
        L_0x0007:
            r3 = r1
            goto L_0x001d
        L_0x0009:
            r2 = 1
            if (r3 != r2) goto L_0x0007
            r3 = 0
            r3 = r0[r3]
            r3.getClass()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0007
            r3 = r0[r2]
            r3.getClass()
        L_0x001d:
            if (r3 != 0) goto L_0x0020
            return r1
        L_0x0020:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_common.zzz.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzc;
    }

    public final zzl zza() {
        return new zzy(this.zzb, 1, this.zzc);
    }

    public final zzs zzd() {
        return new zzw(this, this.zzb, 0, this.zzc);
    }

    public final zzs zze() {
        return new zzx(this, new zzy(this.zzb, 0, this.zzc));
    }
}
