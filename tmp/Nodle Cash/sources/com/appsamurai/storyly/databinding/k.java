package com.appsamurai.storyly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;

public final class k implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final FrameLayout f4383a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final FrameLayout f4384b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final FrameLayout f4385c;

    public k(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull FrameLayout frameLayout3) {
        this.f4383a = frameLayout;
        this.f4384b = frameLayout2;
        this.f4385c = frameLayout3;
    }

    @NonNull
    public FrameLayout a() {
        return this.f4383a;
    }

    @NonNull
    public View getRoot() {
        return this.f4383a;
    }

    @NonNull
    public static k a(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.st_story_presenter_dialog, (ViewGroup) null, false);
        int i3 = R.id.st_holder;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
        if (frameLayout != null) {
            i3 = R.id.st_storyly_dialog_layout;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
            if (frameLayout2 != null) {
                return new k((FrameLayout) inflate, frameLayout, frameLayout2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
    }
}
