package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;
import java.util.Map;

public final /* synthetic */ class b implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ConfigFetchHandler f7172a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Task f7173b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Task f7174c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Date f7175d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Map f7176e;

    public /* synthetic */ b(ConfigFetchHandler configFetchHandler, Task task, Task task2, Date date, Map map) {
        this.f7172a = configFetchHandler;
        this.f7173b = task;
        this.f7174c = task2;
        this.f7175d = date;
        this.f7176e = map;
    }

    public final Object then(Task task) {
        Date date = this.f7175d;
        return this.f7172a.lambda$fetchIfCacheExpiredAndNotThrottled$2(this.f7173b, this.f7174c, date, this.f7176e, task);
    }
}
