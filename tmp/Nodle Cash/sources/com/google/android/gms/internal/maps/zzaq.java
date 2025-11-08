package com.google.android.gms.internal.maps;

public abstract class zzaq extends zzb implements zzar {
    public zzaq() {
        super("com.google.android.gms.maps.model.internal.IStyleFactory");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            r6 = 1
            if (r3 != r6) goto L_0x002d
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x000b
            r3 = 0
            goto L_0x001f
        L_0x000b:
            java.lang.String r0 = "com.google.android.gms.maps.model.internal.IFeatureDelegate"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.maps.zzp
            if (r1 == 0) goto L_0x0019
            r3 = r0
            com.google.android.gms.internal.maps.zzp r3 = (com.google.android.gms.internal.maps.zzp) r3
            goto L_0x001f
        L_0x0019:
            com.google.android.gms.internal.maps.zzn r0 = new com.google.android.gms.internal.maps.zzn
            r0.<init>(r3)
            r3 = r0
        L_0x001f:
            com.google.android.gms.internal.maps.zzc.zzd(r4)
            com.google.android.gms.maps.model.FeatureStyle r2 = r2.zzb(r3)
            r5.writeNoException()
            com.google.android.gms.internal.maps.zzc.zzf(r5, r2)
            return r6
        L_0x002d:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.maps.zzaq.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
