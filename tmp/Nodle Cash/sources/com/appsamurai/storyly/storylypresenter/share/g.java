package com.appsamurai.storyly.storylypresenter.share;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class g extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ImageView f5329a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f5330b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f5331c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(ImageView imageView, d dVar, long j2) {
        super(0);
        this.f5329a = imageView;
        this.f5330b = dVar;
        this.f5331c = j2;
    }

    public Object invoke() {
        this.f5329a.setColorFilter(Color.parseColor("#01EA85"));
        ImageView imageView = this.f5329a;
        imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.st_copied));
        d dVar = this.f5330b;
        ImageView imageView2 = this.f5329a;
        Intrinsics.checkNotNullExpressionValue(imageView2, "this");
        dVar.a((View) imageView2, this.f5331c, (Function0<Unit>) null);
        return Unit.INSTANCE;
    }
}
