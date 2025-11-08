package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Looper;
import androidx.annotation.Nullable;

public interface HandlerWrapper {

    public interface Message {
        HandlerWrapper getTarget();

        void sendToTarget();
    }

    Looper getLooper();

    boolean hasMessages(int i3);

    Message obtainMessage(int i3);

    Message obtainMessage(int i3, int i4, int i5);

    Message obtainMessage(int i3, int i4, int i5, @Nullable Object obj);

    Message obtainMessage(int i3, @Nullable Object obj);

    boolean post(Runnable runnable);

    boolean postAtFrontOfQueue(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j2);

    void removeCallbacksAndMessages(@Nullable Object obj);

    void removeMessages(int i3);

    boolean sendEmptyMessage(int i3);

    boolean sendEmptyMessageAtTime(int i3, long j2);

    boolean sendEmptyMessageDelayed(int i3, int i4);

    boolean sendMessageAtFrontOfQueue(Message message);
}
