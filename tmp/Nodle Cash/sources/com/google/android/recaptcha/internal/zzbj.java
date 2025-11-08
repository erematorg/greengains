package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

public final class zzbj extends TimerTask {
    final /* synthetic */ zzbm zza;

    public zzbj(zzbm zzbm) {
        this.zza = zzbm;
    }

    public final void run() {
        zzbm zzbm = this.zza;
        Job unused = BuildersKt__Builders_commonKt.launch$default(zzbm.zzd, (CoroutineContext) null, (CoroutineStart) null, new zzbk(zzbm, (Continuation) null), 3, (Object) null);
    }
}
