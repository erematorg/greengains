package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ak;

final class m implements t {

    /* renamed from: a  reason: collision with root package name */
    private Context f6787a;

    private m() {
    }

    public final m a(Context context) {
        context.getClass();
        this.f6787a = context;
        return this;
    }

    public final o b() {
        ak.a(this.f6787a, Context.class);
        return new o(this.f6787a, (n) null);
    }

    public /* synthetic */ m(l lVar) {
    }
}
