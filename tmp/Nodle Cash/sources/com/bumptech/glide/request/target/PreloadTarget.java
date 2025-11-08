package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends CustomTarget<Z> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).clear();
            return true;
        }
    });
    private static final int MESSAGE_CLEAR = 1;
    private final RequestManager requestManager;

    private PreloadTarget(RequestManager requestManager2, int i3, int i4) {
        super(i3, i4);
        this.requestManager = requestManager2;
    }

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager2, int i3, int i4) {
        return new PreloadTarget<>(requestManager2, i3, i4);
    }

    public void clear() {
        this.requestManager.clear((Target<?>) this);
    }

    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    public void onResourceReady(@NonNull Z z2, @Nullable Transition<? super Z> transition) {
        Request request = getRequest();
        if (request != null && request.isComplete()) {
            HANDLER.obtainMessage(1, this).sendToTarget();
        }
    }
}
