package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

public final /* synthetic */ class a implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6936a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Service f6937b;

    public /* synthetic */ a(Service service, int i3) {
        this.f6936a = i3;
        this.f6937b = service;
    }

    public final void execute(Runnable runnable) {
        int i3 = this.f6936a;
        Service service = this.f6937b;
        switch (i3) {
            case 0:
                ((AbstractExecutionThreadService) service).lambda$executor$0(runnable);
                return;
            default:
                ((AbstractIdleService) service).lambda$executor$0(runnable);
                return;
        }
    }
}
