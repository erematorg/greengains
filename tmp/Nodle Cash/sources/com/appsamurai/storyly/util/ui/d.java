package com.appsamurai.storyly.util.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.appsamurai.storyly.external.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@SuppressLint({"ViewConstructor"})
public final class d extends a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final View f6383a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Handler f6384b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull View view, @NotNull Context context) {
        super(context, (AttributeSet) null, 0, 6);
        Intrinsics.checkNotNullParameter(view, "loadingView");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6383a = view;
        this.f6384b = new Handler(context.getMainLooper());
    }

    public static final void a(d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        dVar.f6383a.setVisibility(0);
    }

    public void b() {
        this.f6384b.postDelayed(new C0.d(this, 1), 500);
    }

    public void a() {
        this.f6384b.removeCallbacksAndMessages((Object) null);
        this.f6383a.setVisibility(4);
    }
}
