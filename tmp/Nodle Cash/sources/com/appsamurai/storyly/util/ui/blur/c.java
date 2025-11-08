package com.appsamurai.storyly.util.ui.blur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class c extends FrameLayout {

    /* renamed from: c  reason: collision with root package name */
    public static final String f6372c = "c";

    /* renamed from: a  reason: collision with root package name */
    public int f6373a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public b f6374b;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(Context context, AttributeSet attributeSet, int i3, int i4, int i5) {
        this(context, (AttributeSet) null, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    @NotNull
    public final b a(@NotNull ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "rootView");
        a aVar = new a(this, viewGroup, this.f6373a);
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.a();
        }
        this.f6374b = aVar;
        return aVar;
    }

    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        b bVar = this.f6374b;
        if (bVar == null ? false : bVar.a(canvas)) {
            super.draw(canvas);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isHardwareAccelerated()) {
            Log.e(f6372c, "BlurView can't be used in not hardware-accelerated window!");
            return;
        }
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.b(true);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.b(false);
        }
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.b();
        }
    }

    public final void setBlurAutoUpdate(boolean z2) {
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.b(z2);
        }
    }

    public final void setBlurEnabled(boolean z2) {
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.a(z2);
        }
    }

    public final void setBlurRadius(float f2) {
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.a(f2);
        }
    }

    public final void setOverlayColor(@ColorInt int i3) {
        this.f6373a = i3;
        b bVar = this.f6374b;
        if (bVar != null) {
            bVar.a(i3);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @ColorInt int i4) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6373a = i4;
    }
}
