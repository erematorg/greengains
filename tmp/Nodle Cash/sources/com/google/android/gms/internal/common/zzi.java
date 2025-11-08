package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

public class zzi extends Handler {
    private final Looper zza = Looper.getMainLooper();

    public zzi() {
    }

    public zzi(Looper looper) {
        super(looper);
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
