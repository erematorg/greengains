package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;

public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, @Nullable Handler.Callback callback);

    long currentTimeMillis();

    long elapsedRealtime();

    void onThreadBlocked();

    long uptimeMillis();
}
