package com.google.android.gms.cloudmessaging;

import android.os.Handler;

public final /* synthetic */ class zzm implements Handler.Callback {
    public final /* synthetic */ zzp zza;

    public /* synthetic */ zzm(zzp zzp) {
        this.zza = zzp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r4 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        if (r4.getBoolean("unsupported", false) == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        r1.zzc(new com.google.android.gms.cloudmessaging.zzt(4, "Not supported by GmsCore", (java.lang.Throwable) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0066, code lost:
        r1.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            java.lang.String r0 = "Received response for unknown request: "
            java.lang.String r1 = "MessengerIpcClient"
            int r2 = r5.arg1
            r3 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r3)
            if (r1 == 0) goto L_0x0020
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Received response to request: "
            r1.<init>(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "MessengerIpcClient"
            android.util.Log.d(r3, r1)
        L_0x0020:
            com.google.android.gms.cloudmessaging.zzp r4 = r4.zza
            monitor-enter(r4)
            android.util.SparseArray r1 = r4.zze     // Catch:{ all -> 0x0040 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0040 }
            com.google.android.gms.cloudmessaging.zzs r1 = (com.google.android.gms.cloudmessaging.zzs) r1     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x0042
            java.lang.String r5 = "MessengerIpcClient"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0040 }
            r1.<init>(r0)     // Catch:{ all -> 0x0040 }
            r1.append(r2)     // Catch:{ all -> 0x0040 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0040 }
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x0040 }
            monitor-exit(r4)     // Catch:{ all -> 0x0040 }
            goto L_0x0069
        L_0x0040:
            r5 = move-exception
            goto L_0x006b
        L_0x0042:
            android.util.SparseArray r0 = r4.zze     // Catch:{ all -> 0x0040 }
            r0.remove(r2)     // Catch:{ all -> 0x0040 }
            r4.zzf()     // Catch:{ all -> 0x0040 }
            monitor-exit(r4)     // Catch:{ all -> 0x0040 }
            android.os.Bundle r4 = r5.getData()
            java.lang.String r5 = "unsupported"
            r0 = 0
            boolean r5 = r4.getBoolean(r5, r0)
            if (r5 == 0) goto L_0x0066
            java.lang.String r4 = "Not supported by GmsCore"
            com.google.android.gms.cloudmessaging.zzt r5 = new com.google.android.gms.cloudmessaging.zzt
            r0 = 4
            r2 = 0
            r5.<init>(r0, r4, r2)
            r1.zzc(r5)
            goto L_0x0069
        L_0x0066:
            r1.zza(r4)
        L_0x0069:
            r4 = 1
            return r4
        L_0x006b:
            monitor-exit(r4)     // Catch:{ all -> 0x0040 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzm.handleMessage(android.os.Message):boolean");
    }
}
