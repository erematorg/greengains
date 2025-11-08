package com.appsamurai.storyly.storylylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class d extends FrameLayout {

    /* renamed from: b  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4703b = {androidx.compose.ui.autofill.a.m(d.class, "momentsView", "getMomentsView$storyly_release()Landroid/view/View;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteProperty f4704a = new a((Object) null, (Object) null, this);

    public static final class a extends ObservableProperty<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4705a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Object obj, Object obj2, d dVar) {
            super(null);
            this.f4705a = dVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, View view, View view2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            View view3 = view2;
            View view4 = view;
            this.f4705a.removeAllViews();
            View momentsView$storyly_release = this.f4705a.getMomentsView$storyly_release();
            if (momentsView$storyly_release != null) {
                ViewParent parent = momentsView$storyly_release.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(momentsView$storyly_release);
                }
                this.f4705a.addView(momentsView$storyly_release);
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public d(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Delegates delegates = Delegates.INSTANCE;
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    }

    @Nullable
    public final View getMomentsView$storyly_release() {
        return (View) this.f4704a.getValue(this, f4703b[0]);
    }

    public final void setMomentsView$storyly_release(@Nullable View view) {
        this.f4704a.setValue(this, f4703b[0], view);
    }
}
