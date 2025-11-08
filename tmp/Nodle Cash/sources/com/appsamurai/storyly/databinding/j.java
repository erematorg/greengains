package com.appsamurai.storyly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;

public final class j implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final RelativeLayout f4371a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final FrameLayout f4372b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final FrameLayout f4373c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final FrameLayout f4374d;
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    public final RelativeLayout f4375e;
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    public final FrameLayout f4376f;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    public final FrameLayout f4377g;
    @NonNull

    /* renamed from: h  reason: collision with root package name */
    public final FrameLayout f4378h;
    @NonNull

    /* renamed from: i  reason: collision with root package name */
    public final ImageButton f4379i;
    @NonNull

    /* renamed from: j  reason: collision with root package name */
    public final ImageButton f4380j;
    @NonNull

    /* renamed from: k  reason: collision with root package name */
    public final RelativeLayout f4381k;
    @NonNull

    /* renamed from: l  reason: collision with root package name */
    public final FrameLayout f4382l;

    public j(@NonNull RelativeLayout relativeLayout, @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull ProgressBar progressBar, @NonNull FrameLayout frameLayout3, @NonNull RelativeLayout relativeLayout2, @NonNull FrameLayout frameLayout4, @NonNull FrameLayout frameLayout5, @NonNull RelativeLayout relativeLayout3, @NonNull FrameLayout frameLayout6, @NonNull ImageButton imageButton, @NonNull ImageButton imageButton2, @NonNull RelativeLayout relativeLayout4, @NonNull FrameLayout frameLayout7) {
        this.f4371a = relativeLayout;
        this.f4372b = frameLayout;
        this.f4373c = frameLayout2;
        this.f4374d = frameLayout3;
        this.f4375e = relativeLayout2;
        this.f4376f = frameLayout4;
        this.f4377g = frameLayout5;
        this.f4378h = frameLayout6;
        this.f4379i = imageButton;
        this.f4380j = imageButton2;
        this.f4381k = relativeLayout4;
        this.f4382l = frameLayout7;
    }

    @NonNull
    public RelativeLayout a() {
        return this.f4371a;
    }

    @NonNull
    public View getRoot() {
        return this.f4371a;
    }

    @NonNull
    public static j a(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.st_story_group_view_item, (ViewGroup) null, false);
        int i3 = R.id.st_cart_view_holder;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
        if (frameLayout != null) {
            i3 = R.id.st_center_view_holder;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
            if (frameLayout2 != null) {
                i3 = R.id.st_default_loading_view;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(inflate, i3);
                if (progressBar != null) {
                    i3 = R.id.st_footer_view_holder;
                    FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                    if (frameLayout3 != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) inflate;
                        i3 = R.id.st_header_view_holder;
                        FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                        if (frameLayout4 != null) {
                            i3 = R.id.st_loading_layout;
                            FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                            if (frameLayout5 != null) {
                                i3 = R.id.st_loading_layout_wrapper;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(inflate, i3);
                                if (relativeLayout2 != null) {
                                    i3 = R.id.st_moments_report_view;
                                    FrameLayout frameLayout6 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                                    if (frameLayout6 != null) {
                                        i3 = R.id.st_navigation_left_button;
                                        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(inflate, i3);
                                        if (imageButton != null) {
                                            i3 = R.id.st_navigation_right_button;
                                            ImageButton imageButton2 = (ImageButton) ViewBindings.findChildViewById(inflate, i3);
                                            if (imageButton2 != null) {
                                                i3 = R.id.st_navigation_view_holder;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(inflate, i3);
                                                if (relativeLayout3 != null) {
                                                    i3 = R.id.st_storyly_layer_view;
                                                    FrameLayout frameLayout7 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                                                    if (frameLayout7 != null) {
                                                        return new j(relativeLayout, frameLayout, frameLayout2, progressBar, frameLayout3, relativeLayout, frameLayout4, frameLayout5, relativeLayout2, frameLayout6, imageButton, imageButton2, relativeLayout3, frameLayout7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
    }
}
