package com.appsamurai.storyly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;

public final class a implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final RelativeLayout f4322a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final FrameLayout f4323b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final RelativeLayout f4324c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final FrameLayout f4325d;

    public a(@NonNull RelativeLayout relativeLayout, @NonNull FrameLayout frameLayout, @NonNull RelativeLayout relativeLayout2, @NonNull CoordinatorLayout coordinatorLayout, @NonNull FrameLayout frameLayout2) {
        this.f4322a = relativeLayout;
        this.f4323b = frameLayout;
        this.f4324c = relativeLayout2;
        this.f4325d = frameLayout2;
    }

    @NonNull
    public static a a(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.st_bottom_sheet, (ViewGroup) null, false);
        int i3 = R.id.bottomSheet;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
        if (frameLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) inflate;
            i3 = R.id.sheetContainer;
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(inflate, i3);
            if (coordinatorLayout != null) {
                i3 = R.id.stickyContainer;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(inflate, i3);
                if (frameLayout2 != null) {
                    return new a(relativeLayout, frameLayout, relativeLayout, coordinatorLayout, frameLayout2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
    }

    @NonNull
    public View getRoot() {
        return this.f4322a;
    }
}
