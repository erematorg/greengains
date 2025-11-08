package com.appsamurai.storyly.util.animation;

import android.content.res.Resources;
import android.graphics.Paint;
import com.appsamurai.storyly.util.animation.models.b;
import com.appsamurai.storyly.util.animation.models.c;
import com.appsamurai.storyly.util.animation.models.d;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public d f6250a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final b f6251b;

    /* renamed from: c  reason: collision with root package name */
    public long f6252c;

    /* renamed from: d  reason: collision with root package name */
    public final boolean f6253d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public d f6254e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public d f6255f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f6256g;

    /* renamed from: h  reason: collision with root package name */
    public final float f6257h;

    /* renamed from: i  reason: collision with root package name */
    public final float f6258i;

    /* renamed from: j  reason: collision with root package name */
    public float f6259j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Paint f6260k;

    /* renamed from: l  reason: collision with root package name */
    public float f6261l;

    /* renamed from: m  reason: collision with root package name */
    public float f6262m;

    /* renamed from: n  reason: collision with root package name */
    public float f6263n = this.f6259j;

    /* renamed from: o  reason: collision with root package name */
    public float f6264o = 60.0f;

    /* renamed from: p  reason: collision with root package name */
    public int f6265p = 255;

    public a(@NotNull d dVar, int i3, @NotNull c cVar, @NotNull b bVar, long j2, boolean z2, @NotNull d dVar2, @NotNull d dVar3, boolean z3, boolean z4, float f2) {
        Intrinsics.checkNotNullParameter(dVar, FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNullParameter(cVar, "size");
        Intrinsics.checkNotNullParameter(bVar, "shape");
        Intrinsics.checkNotNullParameter(dVar2, "acceleration");
        Intrinsics.checkNotNullParameter(dVar3, "velocity");
        this.f6250a = dVar;
        this.f6251b = bVar;
        this.f6252c = j2;
        this.f6253d = z2;
        this.f6254e = dVar2;
        this.f6255f = dVar3;
        this.f6256g = z4;
        this.f6257h = f2;
        this.f6258i = cVar.a();
        this.f6259j = cVar.b();
        Paint paint = new Paint();
        this.f6260k = paint;
        float f3 = Resources.getSystem().getDisplayMetrics().density * 0.29f;
        float f4 = ((float) 3) * f3;
        if (z3) {
            this.f6261l = (Random.Default.nextFloat() * f4) + f3;
        }
        paint.setColor(i3);
    }
}
