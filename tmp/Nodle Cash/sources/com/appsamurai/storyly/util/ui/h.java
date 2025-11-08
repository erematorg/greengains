package com.appsamurai.storyly.util.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.ScrollView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class h extends ScrollView {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6389a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public boolean onInterceptTouchEvent(@Nullable MotionEvent motionEvent) {
        return this.f6389a && super.onInterceptTouchEvent(motionEvent);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(@NotNull MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        return motionEvent.getAction() == 0 ? this.f6389a && super.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public final void setScrollable(boolean z2) {
        this.f6389a = z2;
    }
}
