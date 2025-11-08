package com.appsamurai.storyly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;

public final class b implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final RelativeLayout f4326a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final ImageView f4327b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final LinearLayout f4328c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final FrameLayout f4329d;
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    public final RelativeLayout f4330e;
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    public final ImageView f4331f;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    public final TextView f4332g;
    @NonNull

    /* renamed from: h  reason: collision with root package name */
    public final TextView f4333h;

    public b(@NonNull RelativeLayout relativeLayout, @NonNull ImageView imageView, @NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout, @NonNull RelativeLayout relativeLayout2, @NonNull ImageView imageView2, @NonNull TextView textView, @NonNull TextView textView2) {
        this.f4326a = relativeLayout;
        this.f4327b = imageView;
        this.f4328c = linearLayout;
        this.f4329d = frameLayout;
        this.f4330e = relativeLayout2;
        this.f4331f = imageView2;
        this.f4332g = textView;
        this.f4333h = textView2;
    }

    @NonNull
    public static b a(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.st_default_header_view, (ViewGroup) null, false);
        int i3 = R.id.st_close_button;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(inflate, i3);
        if (imageView != null) {
            i3 = R.id.st_header_pager_view;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(inflate, i3);
            if (linearLayout != null) {
                i3 = R.id.st_icon_image_view;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                if (frameLayout != null) {
                    i3 = R.id.st_icon_title_container;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(inflate, i3);
                    if (relativeLayout != null) {
                        i3 = R.id.st_share_button;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(inflate, i3);
                        if (imageView2 != null) {
                            i3 = R.id.st_sponsored_text;
                            TextView textView = (TextView) ViewBindings.findChildViewById(inflate, i3);
                            if (textView != null) {
                                i3 = R.id.st_title_view;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(inflate, i3);
                                if (textView2 != null) {
                                    return new b((RelativeLayout) inflate, imageView, linearLayout, frameLayout, relativeLayout, imageView2, textView, textView2);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
    }

    @NonNull
    public View getRoot() {
        return this.f4326a;
    }
}
