package com.appsamurai.storyly.storylypresenter.cart.list;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

public final class e implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function1<Drawable, Unit> f4846a;

    public e(Function1<? super Drawable, Unit> function1) {
        this.f4846a = function1;
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
        this.f4846a.invoke(null);
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
        this.f4846a.invoke((Drawable) obj);
        return false;
    }
}
