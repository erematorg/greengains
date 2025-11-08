package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ah;
import com.google.android.play.integrity.internal.ai;
import com.google.android.play.integrity.internal.aj;
import com.google.android.play.integrity.internal.al;

final class s {

    /* renamed from: a  reason: collision with root package name */
    private final s f6798a = this;

    /* renamed from: b  reason: collision with root package name */
    private final al f6799b;

    /* renamed from: c  reason: collision with root package name */
    private final al f6800c;

    /* renamed from: d  reason: collision with root package name */
    private final al f6801d;

    /* renamed from: e  reason: collision with root package name */
    private final al f6802e;

    /* renamed from: f  reason: collision with root package name */
    private final al f6803f;

    public /* synthetic */ s(Context context, r rVar) {
        ai b3 = aj.b(context);
        this.f6799b = b3;
        al b4 = ah.b(an.f6744a);
        this.f6800c = b4;
        al b5 = ah.b(new az(b3, b4));
        this.f6801d = b5;
        al b6 = ah.b(new be(b5));
        this.f6802e = b6;
        this.f6803f = ah.b(new am(b5, b6));
    }

    public final StandardIntegrityManager a() {
        return (StandardIntegrityManager) this.f6803f.a();
    }
}
