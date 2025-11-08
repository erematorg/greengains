package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

final class zab implements PendingResult.StatusListener {
    final /* synthetic */ Batch zaa;

    public zab(Batch batch) {
        this.zaa = batch;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0067, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(com.google.android.gms.common.api.Status r4) {
        /*
            r3 = this;
            com.google.android.gms.common.api.Batch r0 = r3.zaa
            java.lang.Object r0 = r0.zai
            monitor-enter(r0)
            com.google.android.gms.common.api.Batch r1 = r3.zaa     // Catch:{ all -> 0x0011 }
            boolean r1 = r1.isCanceled()     // Catch:{ all -> 0x0011 }
            if (r1 == 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return
        L_0x0011:
            r3 = move-exception
            goto L_0x0068
        L_0x0013:
            boolean r1 = r4.isCanceled()     // Catch:{ all -> 0x0011 }
            r2 = 1
            if (r1 == 0) goto L_0x0020
            com.google.android.gms.common.api.Batch r4 = r3.zaa     // Catch:{ all -> 0x0011 }
            r4.zag = true     // Catch:{ all -> 0x0011 }
            goto L_0x002b
        L_0x0020:
            boolean r4 = r4.isSuccess()     // Catch:{ all -> 0x0011 }
            if (r4 != 0) goto L_0x002b
            com.google.android.gms.common.api.Batch r4 = r3.zaa     // Catch:{ all -> 0x0011 }
            r4.zaf = true     // Catch:{ all -> 0x0011 }
        L_0x002b:
            com.google.android.gms.common.api.Batch r4 = r3.zaa     // Catch:{ all -> 0x0011 }
            int r1 = r4.zae     // Catch:{ all -> 0x0011 }
            int r1 = r1 + -1
            r4.zae = r1     // Catch:{ all -> 0x0011 }
            com.google.android.gms.common.api.Batch r4 = r3.zaa     // Catch:{ all -> 0x0011 }
            int r1 = r4.zae     // Catch:{ all -> 0x0011 }
            if (r1 != 0) goto L_0x0066
            boolean r1 = r4.zag     // Catch:{ all -> 0x0011 }
            if (r1 == 0) goto L_0x0048
            com.google.android.gms.common.api.zab.super.cancel()     // Catch:{ all -> 0x0011 }
            goto L_0x0066
        L_0x0048:
            boolean r4 = r4.zaf     // Catch:{ all -> 0x0011 }
            if (r4 == 0) goto L_0x0056
            com.google.android.gms.common.api.Status r4 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0011 }
            r1 = 13
            r4.<init>(r1)     // Catch:{ all -> 0x0011 }
            goto L_0x0058
        L_0x0056:
            com.google.android.gms.common.api.Status r4 = com.google.android.gms.common.api.Status.RESULT_SUCCESS     // Catch:{ all -> 0x0011 }
        L_0x0058:
            com.google.android.gms.common.api.Batch r3 = r3.zaa     // Catch:{ all -> 0x0011 }
            com.google.android.gms.common.api.BatchResult r1 = new com.google.android.gms.common.api.BatchResult     // Catch:{ all -> 0x0011 }
            com.google.android.gms.common.api.PendingResult[] r2 = r3.zah     // Catch:{ all -> 0x0011 }
            r1.<init>(r4, r2)     // Catch:{ all -> 0x0011 }
            r3.setResult(r1)     // Catch:{ all -> 0x0011 }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return
        L_0x0068:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zab.onComplete(com.google.android.gms.common.api.Status):void");
    }
}
