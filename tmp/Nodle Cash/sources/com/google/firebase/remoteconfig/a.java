package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class a implements Continuation, SuccessContinuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FirebaseRemoteConfig f7160a;

    public /* synthetic */ a(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.f7160a = firebaseRemoteConfig;
    }

    public Task then(Object obj) {
        return this.f7160a.lambda$fetchAndActivate$1((Void) obj);
    }

    public Object then(Task task) {
        return Boolean.valueOf(this.f7160a.processActivatePutTask(task));
    }
}
