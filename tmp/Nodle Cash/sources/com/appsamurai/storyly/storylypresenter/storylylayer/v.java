package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.s;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import com.appsamurai.storyly.data.y;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class v implements RequestListener<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u f6182a;

    public v(u uVar) {
        this.f6182a = uVar;
    }

    public static final void a(u uVar) {
        Intrinsics.checkNotNullParameter(uVar, "this$0");
        uVar.getOnLayerLoadFail$storyly_release().invoke();
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
        if (this.f6182a.f6114m.incrementAndGet() != 1) {
            return false;
        }
        new Handler(Looper.getMainLooper()).post(new s(this.f6182a, 1));
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
        Drawable drawable = (Drawable) obj;
        int incrementAndGet = this.f6182a.f6113l.incrementAndGet();
        y yVar = this.f6182a.f6111j;
        if (yVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar = null;
        }
        if (incrementAndGet == yVar.f4279a.size()) {
            this.f6182a.f6113l.set(0);
            this.f6182a.getOnImageReady$storyly_release().invoke();
        }
        return false;
    }
}
