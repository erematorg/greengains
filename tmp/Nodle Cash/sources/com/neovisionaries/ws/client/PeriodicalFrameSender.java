package com.neovisionaries.ws.client;

import java.util.Timer;
import java.util.TimerTask;

abstract class PeriodicalFrameSender {
    private PayloadGenerator mGenerator;
    private long mInterval;
    private boolean mScheduled;
    private Timer mTimer;
    private String mTimerName;
    private final WebSocket mWebSocket;

    public final class Task extends TimerTask {
        private Task() {
        }

        public void run() {
            PeriodicalFrameSender.this.doTask();
        }
    }

    public PeriodicalFrameSender(WebSocket webSocket, String str, PayloadGenerator payloadGenerator) {
        this.mWebSocket = webSocket;
        this.mTimerName = str;
        this.mGenerator = payloadGenerator;
    }

    private WebSocketFrame createFrame() {
        return createFrame(generatePayload());
    }

    /* access modifiers changed from: private */
    public void doTask() {
        synchronized (this) {
            try {
                if (this.mInterval != 0) {
                    if (this.mWebSocket.isOpen()) {
                        this.mWebSocket.sendFrame(createFrame());
                        this.mScheduled = schedule(this.mTimer, new Task(), this.mInterval);
                        return;
                    }
                }
                this.mScheduled = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private byte[] generatePayload() {
        PayloadGenerator payloadGenerator = this.mGenerator;
        if (payloadGenerator == null) {
            return null;
        }
        try {
            return payloadGenerator.generate();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean schedule(Timer timer, Task task, long j2) {
        try {
            timer.schedule(task, j2);
            return true;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public abstract WebSocketFrame createFrame(byte[] bArr);

    public long getInterval() {
        long j2;
        synchronized (this) {
            j2 = this.mInterval;
        }
        return j2;
    }

    public PayloadGenerator getPayloadGenerator() {
        PayloadGenerator payloadGenerator;
        synchronized (this) {
            payloadGenerator = this.mGenerator;
        }
        return payloadGenerator;
    }

    public String getTimerName() {
        return this.mTimerName;
    }

    public void setInterval(long j2) {
        if (j2 < 0) {
            j2 = 0;
        }
        synchronized (this) {
            this.mInterval = j2;
        }
        if (j2 != 0 && this.mWebSocket.isOpen()) {
            synchronized (this) {
                try {
                    if (this.mTimer == null) {
                        if (this.mTimerName == null) {
                            this.mTimer = new Timer();
                        } else {
                            this.mTimer = new Timer(this.mTimerName);
                        }
                    }
                    if (!this.mScheduled) {
                        this.mScheduled = schedule(this.mTimer, new Task(), j2);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void setPayloadGenerator(PayloadGenerator payloadGenerator) {
        synchronized (this) {
            this.mGenerator = payloadGenerator;
        }
    }

    public void setTimerName(String str) {
        synchronized (this) {
            this.mTimerName = str;
        }
    }

    public void start() {
        setInterval(getInterval());
    }

    public void stop() {
        synchronized (this) {
            try {
                Timer timer = this.mTimer;
                if (timer != null) {
                    this.mScheduled = false;
                    timer.cancel();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
