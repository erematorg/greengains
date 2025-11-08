package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;

final class zzca extends ContentObserver {
    public zzca(Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z2) {
        zzcb.zzl.set(true);
    }
}
