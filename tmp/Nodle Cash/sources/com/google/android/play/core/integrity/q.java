package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ak;

final class q implements ai {

    /* renamed from: a  reason: collision with root package name */
    private Context f6797a;

    private q() {
    }

    public final q a(Context context) {
        context.getClass();
        this.f6797a = context;
        return this;
    }

    public final s b() {
        ak.a(this.f6797a, Context.class);
        return new s(this.f6797a, (r) null);
    }

    public /* synthetic */ q(p pVar) {
    }
}
