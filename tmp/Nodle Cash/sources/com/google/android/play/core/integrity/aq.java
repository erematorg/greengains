package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ag;
import com.google.android.play.integrity.internal.r;

final class aq extends r {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f6746a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ax f6747b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public aq(ax axVar, TaskCompletionSource taskCompletionSource, Context context) {
        super(taskCompletionSource);
        this.f6747b = axVar;
        this.f6746a = context;
    }

    public final void b() {
        this.f6747b.f6764d.trySetResult(Boolean.valueOf(ag.a(this.f6746a)));
    }
}
