package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class ResourceRecycler {
    private final Handler handler = new Handler(Looper.getMainLooper(), new ResourceRecyclerCallback());
    private boolean isRecycling;

    public static final class ResourceRecyclerCallback implements Handler.Callback {
        static final int RECYCLE_RESOURCE = 1;

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((Resource) message.obj).recycle();
            return true;
        }
    }

    public synchronized void recycle(Resource<?> resource, boolean z2) {
        try {
            if (!this.isRecycling) {
                if (!z2) {
                    this.isRecycling = true;
                    resource.recycle();
                    this.isRecycling = false;
                }
            }
            this.handler.obtainMessage(1, resource).sendToTarget();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
