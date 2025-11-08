package com.appsamurai.storyly.external;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class a extends FrameLayout {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Context context, AttributeSet attributeSet, int i3, int i4) {
        this(context, (AttributeSet) null, (i4 & 4) != 0 ? 0 : i3);
    }

    public abstract void a();

    public abstract void b();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public a(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
