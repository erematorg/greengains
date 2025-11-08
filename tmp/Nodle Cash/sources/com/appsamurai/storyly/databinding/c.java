package com.appsamurai.storyly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;

public final class c implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final LinearLayout f4334a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final FrameLayout f4335b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final FrameLayout f4336c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final FrameLayout f4337d;
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    public final TextView f4338e;
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    public final TextView f4339f;

    public c(@NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull FrameLayout frameLayout3, @NonNull TextView textView, @NonNull TextView textView2) {
        this.f4334a = linearLayout;
        this.f4335b = frameLayout;
        this.f4336c = frameLayout2;
        this.f4337d = frameLayout3;
        this.f4338e = textView;
        this.f4339f = textView2;
    }

    @NonNull
    public LinearLayout a() {
        return this.f4334a;
    }

    @NonNull
    public View getRoot() {
        return this.f4334a;
    }

    @NonNull
    public static c a(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.st_list_view_item, (ViewGroup) null, false);
        int i3 = R.id.st_badge_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
        if (frameLayout != null) {
            i3 = R.id.st_icon_holder;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
            if (frameLayout2 != null) {
                i3 = R.id.st_pin_icon_holder;
                FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                if (frameLayout3 != null) {
                    i3 = R.id.st_story_group_badge;
                    TextView textView = (TextView) ViewBindings.findChildViewById(inflate, i3);
                    if (textView != null) {
                        i3 = R.id.st_storyly_title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(inflate, i3);
                        if (textView2 != null) {
                            return new c((LinearLayout) inflate, frameLayout, frameLayout2, frameLayout3, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
    }
}
