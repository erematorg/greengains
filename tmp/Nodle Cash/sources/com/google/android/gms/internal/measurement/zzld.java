package com.google.android.gms.internal.measurement;

final class zzld implements zzmi {
    private static final zzld zza = new zzld();

    private zzld() {
    }

    public static zzld zza() {
        return zza;
    }

    public final boolean zzb(Class<?> cls) {
        return zzlc.class.isAssignableFrom(cls);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzmj zza(java.lang.Class<?> r3) {
        /*
            r2 = this;
            java.lang.Class<com.google.android.gms.internal.measurement.zzlc> r2 = com.google.android.gms.internal.measurement.zzlc.class
            boolean r0 = r2.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x002b
            java.lang.Class r2 = r3.asSubclass(r2)     // Catch:{ Exception -> 0x001a }
            com.google.android.gms.internal.measurement.zzlc r2 = com.google.android.gms.internal.measurement.zzlc.zza(r2)     // Catch:{ Exception -> 0x001a }
            int r0 = com.google.android.gms.internal.measurement.zzlc.zze.zzc     // Catch:{ Exception -> 0x001a }
            r1 = 0
            java.lang.Object r2 = r2.zza((int) r0, (java.lang.Object) r1, (java.lang.Object) r1)     // Catch:{ Exception -> 0x001a }
            com.google.android.gms.internal.measurement.zzmj r2 = (com.google.android.gms.internal.measurement.zzmj) r2     // Catch:{ Exception -> 0x001a }
            return r2
        L_0x001a:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r3 = r3.getName()
            java.lang.String r1 = "Unable to get message info for "
            java.lang.String r3 = r1.concat(r3)
            r0.<init>(r3, r2)
            throw r0
        L_0x002b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = r3.getName()
            java.lang.String r0 = "Unsupported message type: "
            java.lang.String r3 = r0.concat(r3)
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzld.zza(java.lang.Class):com.google.android.gms.internal.measurement.zzmj");
    }
}
