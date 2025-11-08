package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper;
import java.util.ArrayList;
import java.util.List;

final class SystemHandlerWrapper implements HandlerWrapper {
    private static final int MAX_POOL_SIZE = 50;
    @GuardedBy("messagePool")
    private static final List<SystemMessage> messagePool = new ArrayList(50);
    private final Handler handler;

    public static final class SystemMessage implements HandlerWrapper.Message {
        @Nullable
        private SystemHandlerWrapper handler;
        @Nullable
        private Message message;

        private SystemMessage() {
        }

        private void recycle() {
            this.message = null;
            this.handler = null;
            SystemHandlerWrapper.recycleMessage(this);
        }

        public HandlerWrapper getTarget() {
            return (HandlerWrapper) Assertions.checkNotNull(this.handler);
        }

        public boolean sendAtFrontOfQueue(Handler handler2) {
            boolean sendMessageAtFrontOfQueue = handler2.sendMessageAtFrontOfQueue((Message) Assertions.checkNotNull(this.message));
            recycle();
            return sendMessageAtFrontOfQueue;
        }

        public void sendToTarget() {
            ((Message) Assertions.checkNotNull(this.message)).sendToTarget();
            recycle();
        }

        public SystemMessage setMessage(Message message2, SystemHandlerWrapper systemHandlerWrapper) {
            this.message = message2;
            this.handler = systemHandlerWrapper;
            return this;
        }
    }

    public SystemHandlerWrapper(Handler handler2) {
        this.handler = handler2;
    }

    private static SystemMessage obtainSystemMessage() {
        SystemMessage systemMessage;
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                systemMessage = list.isEmpty() ? new SystemMessage() : list.remove(list.size() - 1);
            } catch (Throwable th) {
                throw th;
            }
        }
        return systemMessage;
    }

    /* access modifiers changed from: private */
    public static void recycleMessage(SystemMessage systemMessage) {
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                if (list.size() < 50) {
                    list.add(systemMessage);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Looper getLooper() {
        return this.handler.getLooper();
    }

    public boolean hasMessages(int i3) {
        return this.handler.hasMessages(i3);
    }

    public HandlerWrapper.Message obtainMessage(int i3) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i3), this);
    }

    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    public boolean postAtFrontOfQueue(Runnable runnable) {
        return this.handler.postAtFrontOfQueue(runnable);
    }

    public boolean postDelayed(Runnable runnable, long j2) {
        return this.handler.postDelayed(runnable, j2);
    }

    public void removeCallbacksAndMessages(@Nullable Object obj) {
        this.handler.removeCallbacksAndMessages(obj);
    }

    public void removeMessages(int i3) {
        this.handler.removeMessages(i3);
    }

    public boolean sendEmptyMessage(int i3) {
        return this.handler.sendEmptyMessage(i3);
    }

    public boolean sendEmptyMessageAtTime(int i3, long j2) {
        return this.handler.sendEmptyMessageAtTime(i3, j2);
    }

    public boolean sendEmptyMessageDelayed(int i3, int i4) {
        return this.handler.sendEmptyMessageDelayed(i3, (long) i4);
    }

    public boolean sendMessageAtFrontOfQueue(HandlerWrapper.Message message) {
        return ((SystemMessage) message).sendAtFrontOfQueue(this.handler);
    }

    public HandlerWrapper.Message obtainMessage(int i3, @Nullable Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i3, obj), this);
    }

    public HandlerWrapper.Message obtainMessage(int i3, int i4, int i5) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i3, i4, i5), this);
    }

    public HandlerWrapper.Message obtainMessage(int i3, int i4, int i5, @Nullable Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i3, i4, i5, obj), this);
    }
}
