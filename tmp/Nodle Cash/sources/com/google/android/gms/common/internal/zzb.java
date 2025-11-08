package com.google.android.gms.common.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.common.zzi;

final class zzb extends zzi {
    final /* synthetic */ BaseGmsClient zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzb(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.zza = baseGmsClient;
    }

    private static final void zza(Message message) {
        zzc zzc = (zzc) message.obj;
        zzc.zzc();
        zzc.zzg();
    }

    private static final boolean zzb(Message message) {
        int i3 = message.what;
        return i3 == 2 || i3 == 1 || i3 == 7;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.app.PendingIntent} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r8) {
        /*
            r7 = this;
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            java.util.concurrent.atomic.AtomicInteger r0 = r0.zzd
            int r0 = r0.get()
            int r1 = r8.arg1
            if (r0 == r1) goto L_0x0016
            boolean r7 = zzb(r8)
            if (r7 == 0) goto L_0x0015
            zza(r8)
        L_0x0015:
            return
        L_0x0016:
            int r0 = r8.what
            r1 = 4
            r2 = 1
            r3 = 5
            if (r0 == r2) goto L_0x002e
            r4 = 7
            if (r0 == r4) goto L_0x002e
            if (r0 != r1) goto L_0x002a
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            boolean r0 = r0.enableLocalFallback()
            if (r0 == 0) goto L_0x002e
        L_0x002a:
            int r0 = r8.what
            if (r0 != r3) goto L_0x0036
        L_0x002e:
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            boolean r0 = r0.isConnecting()
            if (r0 == 0) goto L_0x0115
        L_0x0036:
            int r0 = r8.what
            r4 = 8
            r5 = 3
            r6 = 0
            if (r0 != r1) goto L_0x007e
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            com.google.android.gms.common.ConnectionResult r1 = new com.google.android.gms.common.ConnectionResult
            int r8 = r8.arg2
            r1.<init>(r8)
            r0.zzB = r1
            com.google.android.gms.common.internal.BaseGmsClient r8 = r7.zza
            boolean r8 = com.google.android.gms.common.internal.BaseGmsClient.zzo(r8)
            if (r8 == 0) goto L_0x005f
            com.google.android.gms.common.internal.BaseGmsClient r8 = r7.zza
            boolean r0 = r8.zzC
            if (r0 == 0) goto L_0x005b
            goto L_0x005f
        L_0x005b:
            r8.zzp(r5, (android.os.IInterface) null)
            return
        L_0x005f:
            com.google.android.gms.common.internal.BaseGmsClient r8 = r7.zza
            com.google.android.gms.common.ConnectionResult r0 = r8.zzB
            if (r0 == 0) goto L_0x006c
            com.google.android.gms.common.ConnectionResult r8 = r8.zzB
            goto L_0x0071
        L_0x006c:
            com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
            r8.<init>(r4)
        L_0x0071:
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzc
            r0.onReportServiceBinding(r8)
            com.google.android.gms.common.internal.BaseGmsClient r7 = r7.zza
            r7.onConnectionFailed(r8)
            return
        L_0x007e:
            if (r0 != r3) goto L_0x009f
            com.google.android.gms.common.internal.BaseGmsClient r8 = r7.zza
            com.google.android.gms.common.ConnectionResult r0 = r8.zzB
            if (r0 == 0) goto L_0x008d
            com.google.android.gms.common.ConnectionResult r8 = r8.zzB
            goto L_0x0092
        L_0x008d:
            com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
            r8.<init>(r4)
        L_0x0092:
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzc
            r0.onReportServiceBinding(r8)
            com.google.android.gms.common.internal.BaseGmsClient r7 = r7.zza
            r7.onConnectionFailed(r8)
            return
        L_0x009f:
            if (r0 != r5) goto L_0x00be
            java.lang.Object r0 = r8.obj
            boolean r1 = r0 instanceof android.app.PendingIntent
            if (r1 == 0) goto L_0x00aa
            r6 = r0
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
        L_0x00aa:
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult
            int r8 = r8.arg2
            r0.<init>(r8, r6)
            com.google.android.gms.common.internal.BaseGmsClient r8 = r7.zza
            com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r8 = r8.zzc
            r8.onReportServiceBinding(r0)
            com.google.android.gms.common.internal.BaseGmsClient r7 = r7.zza
            r7.onConnectionFailed(r0)
            return
        L_0x00be:
            r1 = 6
            if (r0 != r1) goto L_0x00e4
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            r0.zzp(r3, (android.os.IInterface) null)
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r1 = r0.zzw
            if (r1 == 0) goto L_0x00d7
            com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r0 = r0.zzw
            int r1 = r8.arg2
            r0.onConnectionSuspended(r1)
        L_0x00d7:
            com.google.android.gms.common.internal.BaseGmsClient r0 = r7.zza
            int r8 = r8.arg2
            r0.onConnectionSuspended(r8)
            com.google.android.gms.common.internal.BaseGmsClient r7 = r7.zza
            com.google.android.gms.common.internal.BaseGmsClient.zzn(r7, r3, r2, r6)
            return
        L_0x00e4:
            r1 = 2
            if (r0 != r1) goto L_0x00f4
            com.google.android.gms.common.internal.BaseGmsClient r7 = r7.zza
            boolean r7 = r7.isConnected()
            if (r7 == 0) goto L_0x00f0
            goto L_0x00f4
        L_0x00f0:
            zza(r8)
            return
        L_0x00f4:
            boolean r7 = zzb(r8)
            if (r7 == 0) goto L_0x0102
            java.lang.Object r7 = r8.obj
            com.google.android.gms.common.internal.zzc r7 = (com.google.android.gms.common.internal.zzc) r7
            r7.zze()
            return
        L_0x0102:
            int r7 = r8.what
            java.lang.String r8 = "Don't know how to handle message: "
            java.lang.String r7 = A.a.k(r8, r7)
            java.lang.Exception r8 = new java.lang.Exception
            r8.<init>()
            java.lang.String r0 = "GmsClient"
            android.util.Log.wtf(r0, r7, r8)
            return
        L_0x0115:
            zza(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzb.handleMessage(android.os.Message):void");
    }
}
