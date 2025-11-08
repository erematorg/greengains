package com.google.android.play.core.integrity;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ad;
import com.google.android.play.integrity.internal.r;

abstract class aw extends r {

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ ax f6760f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public aw(@Nullable ax axVar, TaskCompletionSource taskCompletionSource) {
        super(taskCompletionSource);
        this.f6760f = axVar;
    }

    public final void a(Exception exc) {
        if (!(exc instanceof ad)) {
            super.a(exc);
        } else if (ax.g(this.f6760f)) {
            super.a(new StandardIntegrityException(-2, exc));
        } else {
            super.a(new StandardIntegrityException(-9, exc));
        }
    }
}
