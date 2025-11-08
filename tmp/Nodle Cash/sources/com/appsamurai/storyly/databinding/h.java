package com.appsamurai.storyly.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public final class h implements ViewBinding {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final ConstraintLayout f4360a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    public final TextView f4361b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    public final ImageView f4362c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    public final LinearLayout f4363d;
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    public final TextView f4364e;
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    public final RecyclerView f4365f;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    public final LinearLayout f4366g;
    @NonNull

    /* renamed from: h  reason: collision with root package name */
    public final LinearLayout f4367h;

    public h(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull TextView textView, @NonNull ImageView imageView2, @NonNull LinearLayout linearLayout, @NonNull TextView textView2, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull LinearLayout linearLayout2, @NonNull LinearLayout linearLayout3, @NonNull View view2, @NonNull TextView textView3) {
        this.f4360a = constraintLayout;
        this.f4361b = textView;
        this.f4362c = imageView2;
        this.f4363d = linearLayout;
        this.f4364e = textView2;
        this.f4365f = recyclerView;
        this.f4366g = linearLayout2;
        this.f4367h = linearLayout3;
    }

    @NonNull
    public ConstraintLayout a() {
        return this.f4360a;
    }

    @NonNull
    public View getRoot() {
        return this.f4360a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
        r0 = com.appsamurai.storyly.R.id.st_divider;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r0 = com.appsamurai.storyly.R.id.st_space_view;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.appsamurai.storyly.databinding.h a(@androidx.annotation.NonNull android.view.LayoutInflater r15) {
        /*
            int r0 = com.appsamurai.storyly.R.layout.st_share_bottom_sheet
            r1 = 0
            r2 = 0
            android.view.View r15 = r15.inflate(r0, r2, r1)
            int r0 = com.appsamurai.storyly.R.id.st_bottom_sheet_indicator
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r4 = r1
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            if (r4 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_cancel
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r5 = r1
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_copy_link_image
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r6 = r1
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            if (r6 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_copy_link_layout
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r7 = r1
            android.widget.LinearLayout r7 = (android.widget.LinearLayout) r7
            if (r7 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_copy_link_text
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            if (r8 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_divider
            android.view.View r9 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            if (r9 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_recycler_view
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r10 = r1
            androidx.recyclerview.widget.RecyclerView r10 = (androidx.recyclerview.widget.RecyclerView) r10
            if (r10 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_share_link_via_layout
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r11 = r1
            android.widget.LinearLayout r11 = (android.widget.LinearLayout) r11
            if (r11 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_share_screenshot_via_layout
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r12 = r1
            android.widget.LinearLayout r12 = (android.widget.LinearLayout) r12
            if (r12 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_space_view
            android.view.View r13 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            if (r13 == 0) goto L_0x0085
            int r0 = com.appsamurai.storyly.R.id.st_title
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r15, r0)
            r14 = r1
            android.widget.TextView r14 = (android.widget.TextView) r14
            if (r14 == 0) goto L_0x0085
            com.appsamurai.storyly.databinding.h r0 = new com.appsamurai.storyly.databinding.h
            r3 = r15
            androidx.constraintlayout.widget.ConstraintLayout r3 = (androidx.constraintlayout.widget.ConstraintLayout) r3
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x0085:
            android.content.res.Resources r15 = r15.getResources()
            java.lang.String r15 = r15.getResourceName(r0)
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Missing required view with ID: "
            java.lang.String r15 = r1.concat(r15)
            r0.<init>(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.databinding.h.a(android.view.LayoutInflater):com.appsamurai.storyly.databinding.h");
    }
}
