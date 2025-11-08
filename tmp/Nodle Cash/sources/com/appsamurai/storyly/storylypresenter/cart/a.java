package com.appsamurai.storyly.storylypresenter.cart;

import android.view.animation.Animation;
import com.appsamurai.storyly.util.b;
import org.jetbrains.annotations.Nullable;

public final class a extends b {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f4772a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4773b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4774c;

    public a(b bVar, int i3, int i4) {
        this.f4772a = bVar;
        this.f4773b = i3;
        this.f4774c = i4;
    }

    public void onAnimationEnd(@Nullable Animation animation) {
        this.f4772a.c().setText(String.valueOf(this.f4773b));
        int i3 = this.f4773b;
        int i4 = this.f4774c;
        if (i3 != i4) {
            b.a(this.f4772a, i3, i4);
        }
    }
}
