package com.appsamurai.storyly.storylypresenter.share;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.appsamurai.storyly.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class f extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TextView f5325a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f5326b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f5327c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function0<Unit> f5328d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(TextView textView, d dVar, long j2, Function0<Unit> function0) {
        super(0);
        this.f5325a = textView;
        this.f5326b = dVar;
        this.f5327c = j2;
        this.f5328d = function0;
    }

    public Object invoke() {
        TextView textView = this.f5325a;
        textView.setText(textView.getContext().getString(R.string.st_share_sheet_copied_text));
        this.f5325a.setTextColor(Color.parseColor("#01EA85"));
        d dVar = this.f5326b;
        TextView textView2 = this.f5325a;
        Intrinsics.checkNotNullExpressionValue(textView2, "this");
        dVar.a((View) textView2, this.f5327c, (Function0<Unit>) new e(this.f5328d));
        return Unit.INSTANCE;
    }
}
