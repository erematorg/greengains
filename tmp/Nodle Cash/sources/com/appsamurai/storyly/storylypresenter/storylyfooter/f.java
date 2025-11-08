package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import org.jetbrains.annotations.Nullable;

public final class f implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5457a;

    public f(d dVar) {
        this.f5457a = dVar;
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
        Drawable drawable = (Drawable) obj;
        this.f5457a.d().setAlpha(0.6f);
        return false;
    }
}
