package com.appsamurai.storyly.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public final class g implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final RelativeLayout f4356a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final ImageView f4357b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final SeekBar f4358c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final TextView f4359d;

    public g(@NonNull RelativeLayout relativeLayout, @NonNull ImageView imageView, @NonNull SeekBar seekBar, @NonNull TextView textView) {
        this.f4356a = relativeLayout;
        this.f4357b = imageView;
        this.f4358c = seekBar;
        this.f4359d = textView;
    }

    @NonNull
    public RelativeLayout a() {
        return this.f4356a;
    }

    @NonNull
    public View getRoot() {
        return this.f4356a;
    }
}
