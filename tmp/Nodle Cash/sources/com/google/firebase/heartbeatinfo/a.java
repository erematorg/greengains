package com.google.firebase.heartbeatinfo;

import java.util.concurrent.Callable;

public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7114a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ DefaultHeartBeatController f7115b;

    public /* synthetic */ a(DefaultHeartBeatController defaultHeartBeatController, int i3) {
        this.f7114a = i3;
        this.f7115b = defaultHeartBeatController;
    }

    public final Object call() {
        int i3 = this.f7114a;
        DefaultHeartBeatController defaultHeartBeatController = this.f7115b;
        switch (i3) {
            case 0:
                return defaultHeartBeatController.lambda$getHeartBeatsHeader$1();
            default:
                return defaultHeartBeatController.lambda$registerHeartBeat$0();
        }
    }
}
