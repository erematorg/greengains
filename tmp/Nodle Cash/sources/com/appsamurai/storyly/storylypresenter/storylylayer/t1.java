package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.p;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class t1 implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ s1 f6107a;

    public t1(s1 s1Var) {
        this.f6107a = s1Var;
    }

    public static final void a(s1 s1Var) {
        Intrinsics.checkNotNullParameter(s1Var, "this$0");
        s1Var.getOnLayerLoadFail$storyly_release().invoke();
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
        new Handler(Looper.getMainLooper()).post(new p(this.f6107a, 1));
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
        Drawable drawable = (Drawable) obj;
        this.f6107a.getOnImageReady$storyly_release().invoke();
        return false;
    }
}
