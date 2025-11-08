package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

final class zzcx extends Lambda implements Function2 {
    final /* synthetic */ zzcj zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcx(zzcj zzcj, String str, int i3) {
        super(2);
        this.zza = zzcj;
        this.zzb = str;
        this.zzc = i3;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        this.zza.zzi().zzb(this.zzb, (String) obj2);
        int i3 = this.zzc;
        if (i3 != -1) {
            this.zza.zzc().zzf(i3, objArr);
        }
        return Unit.INSTANCE;
    }
}
