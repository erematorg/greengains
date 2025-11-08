package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractIdleService;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6942a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AbstractIdleService.DelegateService f6943b;

    public /* synthetic */ e(AbstractIdleService.DelegateService delegateService, int i3) {
        this.f6942a = i3;
        this.f6943b = delegateService;
    }

    public final void run() {
        int i3 = this.f6942a;
        AbstractIdleService.DelegateService delegateService = this.f6943b;
        switch (i3) {
            case 0:
                delegateService.lambda$doStop$1();
                return;
            default:
                delegateService.lambda$doStart$0();
                return;
        }
    }
}
