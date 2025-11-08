package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import org.jetbrains.annotations.Nullable;

public final class s implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ r f6025a;

    public s(r rVar) {
        this.f6025a = rVar;
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
        this.f6025a.getOnLayerLoad$storyly_release().invoke();
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
        Drawable drawable = (Drawable) obj;
        this.f6025a.getOnLayerLoad$storyly_release().invoke();
        return false;
    }
}
