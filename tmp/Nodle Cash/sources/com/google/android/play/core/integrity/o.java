package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ah;
import com.google.android.play.integrity.internal.ai;
import com.google.android.play.integrity.internal.aj;
import com.google.android.play.integrity.internal.al;

final class o {

    /* renamed from: a  reason: collision with root package name */
    private final o f6792a = this;

    /* renamed from: b  reason: collision with root package name */
    private final al f6793b;

    /* renamed from: c  reason: collision with root package name */
    private final al f6794c;

    /* renamed from: d  reason: collision with root package name */
    private final al f6795d;

    /* renamed from: e  reason: collision with root package name */
    private final al f6796e;

    public /* synthetic */ o(Context context, n nVar) {
        ai b3 = aj.b(context);
        this.f6793b = b3;
        al b4 = ah.b(y.f6809a);
        this.f6794c = b4;
        al b5 = ah.b(new af(b3, b4));
        this.f6795d = b5;
        this.f6796e = ah.b(new x(b5));
    }

    public final IntegrityManager a() {
        return (IntegrityManager) this.f6796e.a();
    }
}
