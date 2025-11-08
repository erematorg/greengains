package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.AbstractScheduledService;

public final /* synthetic */ class b implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6938a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AbstractService f6939b;

    public /* synthetic */ b(AbstractService abstractService, int i3) {
        this.f6938a = i3;
        this.f6939b = abstractService;
    }

    public final Object get() {
        int i3 = this.f6938a;
        AbstractService abstractService = this.f6939b;
        switch (i3) {
            case 0:
                return ((AbstractExecutionThreadService.AnonymousClass1) abstractService).lambda$doStart$0();
            default:
                return ((AbstractScheduledService.ServiceDelegate) abstractService).lambda$doStart$0();
        }
    }
}
