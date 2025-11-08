package com.bumptech.glide.manager;

import android.support.v4.media.session.a;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import org.apache.commons.text.StringSubstitutor;

public class RequestTracker {
    private static final String TAG = "RequestTracker";
    private boolean isPaused;
    private final Set<Request> pendingRequests = new HashSet();
    private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());

    @VisibleForTesting
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public boolean clearAndRemove(@Nullable Request request) {
        boolean z2 = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.requests.remove(request);
        if (!this.pendingRequests.remove(request) && !remove) {
            z2 = false;
        }
        if (z2) {
            request.clear();
        }
        return z2;
    }

    public void clearRequests() {
        for (T clearAndRemove : Util.getSnapshot(this.requests)) {
            clearAndRemove(clearAndRemove);
        }
        this.pendingRequests.clear();
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void pauseAllRequests() {
        this.isPaused = true;
        for (T t2 : Util.getSnapshot(this.requests)) {
            if (t2.isRunning() || t2.isComplete()) {
                t2.clear();
                this.pendingRequests.add(t2);
            }
        }
    }

    public void pauseRequests() {
        this.isPaused = true;
        for (T t2 : Util.getSnapshot(this.requests)) {
            if (t2.isRunning()) {
                t2.pause();
                this.pendingRequests.add(t2);
            }
        }
    }

    public void restartRequests() {
        for (T t2 : Util.getSnapshot(this.requests)) {
            if (!t2.isComplete() && !t2.isCleared()) {
                t2.clear();
                if (!this.isPaused) {
                    t2.begin();
                } else {
                    this.pendingRequests.add(t2);
                }
            }
        }
    }

    public void resumeRequests() {
        this.isPaused = false;
        for (T t2 : Util.getSnapshot(this.requests)) {
            if (!t2.isComplete() && !t2.isRunning()) {
                t2.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void runRequest(@NonNull Request request) {
        this.requests.add(request);
        if (!this.isPaused) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Paused, delaying request");
        }
        this.pendingRequests.add(request);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{numRequests=");
        sb.append(this.requests.size());
        sb.append(", isPaused=");
        return a.s(sb, this.isPaused, StringSubstitutor.DEFAULT_VAR_END);
    }
}
