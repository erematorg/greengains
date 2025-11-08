package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzht extends ContentObserver {
    private final /* synthetic */ zzhr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzht(zzhr zzhr, Handler handler) {
        super((Handler) null);
        this.zza = zzhr;
    }

    public final void onChange(boolean z2) {
        this.zza.zza.set(true);
    }
}
