package com.appsamurai.storyly.util.animation.emitters;

import com.appsamurai.storyly.util.animation.models.d;
import com.appsamurai.storyly.util.animation.modules.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class c {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final com.appsamurai.storyly.util.animation.modules.a f6281a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final b f6282b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final com.appsamurai.storyly.util.animation.models.c[] f6283c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final com.appsamurai.storyly.util.animation.models.b[] f6284d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final int[] f6285e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final com.appsamurai.storyly.util.animation.models.a f6286f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final b f6287g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f6288h = true;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Random f6289i = new Random();
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public d f6290j = new d(0.0f, 0.01f);
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final List<com.appsamurai.storyly.util.animation.a> f6291k = new ArrayList();

    public /* synthetic */ class a extends FunctionReferenceImpl implements Function0<Unit> {
        public a(Object obj) {
            super(0, obj, c.class, "addConfetti", "addConfetti()V", 0);
        }

        public Object invoke() {
            float f2;
            double d2;
            c cVar = (c) this.receiver;
            List<com.appsamurai.storyly.util.animation.a> list = cVar.f6291k;
            com.appsamurai.storyly.util.animation.modules.a aVar = cVar.f6281a;
            d dVar = new d(aVar.f6303a, aVar.f6304b);
            com.appsamurai.storyly.util.animation.models.c[] cVarArr = cVar.f6283c;
            com.appsamurai.storyly.util.animation.models.c cVar2 = cVarArr[cVar.f6289i.nextInt(cVarArr.length)];
            com.appsamurai.storyly.util.animation.models.b[] bVarArr = cVar.f6284d;
            com.appsamurai.storyly.util.animation.models.b bVar = bVarArr[cVar.f6289i.nextInt(bVarArr.length)];
            int[] iArr = cVar.f6285e;
            int i3 = iArr[cVar.f6289i.nextInt(iArr.length)];
            com.appsamurai.storyly.util.animation.models.a aVar2 = cVar.f6286f;
            long j2 = aVar2.f6293b;
            boolean z2 = aVar2.f6292a;
            b bVar2 = cVar.f6282b;
            Float f3 = bVar2.f6309e;
            if (f3 == null) {
                f2 = bVar2.f6308d;
            } else {
                Intrinsics.checkNotNull(f3);
                f2 = bVar2.f6308d + (bVar2.f6305a.nextFloat() * (f3.floatValue() - bVar2.f6308d));
            }
            Double d3 = bVar2.f6307c;
            if (d3 == null) {
                d2 = bVar2.f6306b;
            } else {
                Intrinsics.checkNotNull(d3);
                d2 = bVar2.f6306b + (bVar2.f6305a.nextDouble() * (d3.doubleValue() - bVar2.f6306b));
            }
            d dVar2 = new d(((float) Math.cos(d2)) * f2, f2 * ((float) Math.sin(d2)));
            com.appsamurai.storyly.util.animation.models.a aVar3 = cVar.f6286f;
            boolean z3 = aVar3.f6294c;
            float f4 = cVar.f6282b.f6310f;
            list.add(new com.appsamurai.storyly.util.animation.a(dVar, i3, cVar2, bVar, j2, z2, new d(0.0f, 0.0f), dVar2, z3, aVar3.f6295d, f4));
            return Unit.INSTANCE;
        }
    }

    public c(@NotNull com.appsamurai.storyly.util.animation.modules.a aVar, @NotNull b bVar, @NotNull com.appsamurai.storyly.util.animation.models.c[] cVarArr, @NotNull com.appsamurai.storyly.util.animation.models.b[] bVarArr, @NotNull int[] iArr, @NotNull com.appsamurai.storyly.util.animation.models.a aVar2, @NotNull b bVar2) {
        Intrinsics.checkNotNullParameter(aVar, FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNullParameter(bVar, "velocity");
        Intrinsics.checkNotNullParameter(cVarArr, "sizes");
        Intrinsics.checkNotNullParameter(bVarArr, "shapes");
        Intrinsics.checkNotNullParameter(iArr, "colors");
        Intrinsics.checkNotNullParameter(aVar2, "config");
        Intrinsics.checkNotNullParameter(bVar2, "emitter");
        this.f6281a = aVar;
        this.f6282b = bVar;
        this.f6283c = cVarArr;
        this.f6284d = bVarArr;
        this.f6285e = iArr;
        this.f6286f = aVar2;
        this.f6287g = bVar2;
        bVar2.a((Function0<Unit>) new a(this));
    }
}
