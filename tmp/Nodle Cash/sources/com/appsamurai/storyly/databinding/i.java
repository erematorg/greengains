package com.appsamurai.storyly.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public final class i implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final LinearLayout f4368a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final ImageView f4369b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final TextView f4370c;

    public i(@NonNull LinearLayout linearLayout, @NonNull ImageView imageView, @NonNull TextView textView) {
        this.f4368a = linearLayout;
        this.f4369b = imageView;
        this.f4370c = textView;
    }

    @NonNull
    public LinearLayout a() {
        return this.f4368a;
    }

    @NonNull
    public View getRoot() {
        return this.f4368a;
    }
}
