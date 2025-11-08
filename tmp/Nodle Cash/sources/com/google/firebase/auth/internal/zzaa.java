package com.google.firebase.auth.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzaa extends FirebaseAuth {
    public zzaa(FirebaseApp firebaseApp, Provider<InteropAppCheckTokenProvider> provider, Provider<HeartBeatController> provider2, @Background Executor executor, @Blocking Executor executor2, @Lightweight Executor executor3, @Lightweight ScheduledExecutorService scheduledExecutorService, @UiThread Executor executor4) {
        super(firebaseApp, provider, provider2, executor, executor2, executor3, scheduledExecutorService, executor4);
    }
}
