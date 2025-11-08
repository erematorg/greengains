package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;

public class SystemClock implements Clock {
    public HandlerWrapper createHandler(Looper looper, @Nullable Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public void onThreadBlocked() {
    }

    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
