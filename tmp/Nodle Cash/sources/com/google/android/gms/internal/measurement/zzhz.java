package com.google.android.gms.internal.measurement;

import android.content.Context;
import androidx.camera.camera2.internal.C0118y;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import javax.annotation.Nullable;
import org.apache.commons.text.StringSubstitutor;

final class zzhz extends zziy {
    private final Context zza;
    @Nullable
    private final Supplier<Optional<zzil>> zzb;

    public zzhz(Context context, @Nullable Supplier<Optional<zzil>> supplier) {
        if (context != null) {
            this.zza = context;
            this.zzb = supplier;
            return;
        }
        throw new NullPointerException("Null context");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r4 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.measurement.zziy
            r2 = 0
            if (r1 == 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zziy r5 = (com.google.android.gms.internal.measurement.zziy) r5
            android.content.Context r1 = r4.zza
            android.content.Context r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x002d
            com.google.common.base.Supplier<com.google.common.base.Optional<com.google.android.gms.internal.measurement.zzil>> r4 = r4.zzb
            if (r4 != 0) goto L_0x0022
            com.google.common.base.Supplier r4 = r5.zzb()
            if (r4 != 0) goto L_0x002d
            goto L_0x002c
        L_0x0022:
            com.google.common.base.Supplier r5 = r5.zzb()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x002d
        L_0x002c:
            return r0
        L_0x002d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhz.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Supplier<Optional<zzil>> supplier = this.zzb;
        return (supplier == null ? 0 : supplier.hashCode()) ^ hashCode;
    }

    public final String toString() {
        return C0118y.g("FlagsContext{context=", String.valueOf(this.zza), ", hermeticFileOverrides=", String.valueOf(this.zzb), StringSubstitutor.DEFAULT_VAR_END);
    }

    public final Context zza() {
        return this.zza;
    }

    @Nullable
    public final Supplier<Optional<zzil>> zzb() {
        return this.zzb;
    }
}
