package com.appsamurai.storyly.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public final class e implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final FrameLayout f4343a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final ImageView f4344b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final ImageView f4345c;

    public e(@NonNull FrameLayout frameLayout, @NonNull ImageView imageView, @NonNull ImageView imageView2) {
        this.f4343a = frameLayout;
        this.f4344b = imageView;
        this.f4345c = imageView2;
    }

    @NonNull
    public FrameLayout a() {
        return this.f4343a;
    }

    @NonNull
    public View getRoot() {
        return this.f4343a;
    }
}
