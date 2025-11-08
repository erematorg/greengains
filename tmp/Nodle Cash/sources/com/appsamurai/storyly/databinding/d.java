package com.appsamurai.storyly.databinding;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public final class d implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final RelativeLayout f4340a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final TextView f4341b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final TextView f4342c;

    public d(@NonNull RelativeLayout relativeLayout, @NonNull TextView textView, @NonNull TextView textView2) {
        this.f4340a = relativeLayout;
        this.f4341b = textView;
        this.f4342c = textView2;
    }

    @NonNull
    public RelativeLayout a() {
        return this.f4340a;
    }

    @NonNull
    public View getRoot() {
        return this.f4340a;
    }
}
