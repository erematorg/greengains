package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractScheduledService;

public final /* synthetic */ class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6944a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AbstractScheduledService.ServiceDelegate f6945b;

    public /* synthetic */ f(AbstractScheduledService.ServiceDelegate serviceDelegate, int i3) {
        this.f6944a = i3;
        this.f6945b = serviceDelegate;
    }

    public final void run() {
        int i3 = this.f6944a;
        AbstractScheduledService.ServiceDelegate serviceDelegate = this.f6945b;
        switch (i3) {
            case 0:
                serviceDelegate.lambda$doStart$1();
                return;
            default:
                serviceDelegate.lambda$doStop$2();
                return;
        }
    }
}
