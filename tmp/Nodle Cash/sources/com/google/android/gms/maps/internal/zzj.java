package com.google.android.gms.maps.internal;

import com.google.android.gms.internal.maps.zzb;

public abstract class zzj extends zzb implements ILocationSourceDelegate {
    public zzj() {
        super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            r6 = 1
            if (r3 == r6) goto L_0x000c
            r4 = 2
            if (r3 == r4) goto L_0x0008
            r2 = 0
            return r2
        L_0x0008:
            r2.deactivate()
            goto L_0x002e
        L_0x000c:
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x0014
            r3 = 0
            goto L_0x0028
        L_0x0014:
            java.lang.String r0 = "com.google.android.gms.maps.internal.IOnLocationChangeListener"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.maps.internal.zzaj
            if (r1 == 0) goto L_0x0022
            r3 = r0
            com.google.android.gms.maps.internal.zzaj r3 = (com.google.android.gms.maps.internal.zzaj) r3
            goto L_0x0028
        L_0x0022:
            com.google.android.gms.maps.internal.zzai r0 = new com.google.android.gms.maps.internal.zzai
            r0.<init>(r3)
            r3 = r0
        L_0x0028:
            com.google.android.gms.internal.maps.zzc.zzd(r4)
            r2.activate(r3)
        L_0x002e:
            r5.writeNoException()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzj.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
