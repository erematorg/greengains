package com.appsamurai.storyly.storylypresenter;

import android.view.animation.Animation;
import com.appsamurai.storyly.data.managers.product.a;
import com.appsamurai.storyly.util.b;
import org.jetbrains.annotations.Nullable;

public final class c0 extends b {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4770a;

    public c0(o oVar) {
        this.f4770a = oVar;
    }

    public void onAnimationEnd(@Nullable Animation animation) {
        o oVar = this.f4770a;
        a aVar = oVar.f5056k;
        if (aVar != null) {
            aVar.f4040c = null;
        }
        oVar.getOnDismissed$storyly_release().invoke();
    }
}
