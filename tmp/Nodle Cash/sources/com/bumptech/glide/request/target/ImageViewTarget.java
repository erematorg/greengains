package com.bumptech.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.Transition;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements Transition.ViewAdapter {
    @Nullable
    private Animatable animatable;

    public ImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    private void maybeUpdateAnimatable(@Nullable Z z2) {
        if (z2 instanceof Animatable) {
            Animatable animatable2 = (Animatable) z2;
            this.animatable = animatable2;
            animatable2.start();
            return;
        }
        this.animatable = null;
    }

    private void setResourceInternal(@Nullable Z z2) {
        setResource(z2);
        maybeUpdateAnimatable(z2);
    }

    @Nullable
    public Drawable getCurrentDrawable() {
        return ((ImageView) this.view).getDrawable();
    }

    public void onLoadCleared(@Nullable Drawable drawable) {
        super.onLoadCleared(drawable);
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onLoadFailed(@Nullable Drawable drawable) {
        super.onLoadFailed(drawable);
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onLoadStarted(@Nullable Drawable drawable) {
        super.onLoadStarted(drawable);
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onResourceReady(@NonNull Z z2, @Nullable Transition<? super Z> transition) {
        if (transition == null || !transition.transition(z2, this)) {
            setResourceInternal(z2);
        } else {
            maybeUpdateAnimatable(z2);
        }
    }

    public void onStart() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.start();
        }
    }

    public void onStop() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
    }

    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public abstract void setResource(@Nullable Z z2);

    @Deprecated
    public ImageViewTarget(ImageView imageView, boolean z2) {
        super(imageView, z2);
    }
}
