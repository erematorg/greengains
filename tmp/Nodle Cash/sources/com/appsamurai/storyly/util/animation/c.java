package com.appsamurai.storyly.util.animation;

import com.appsamurai.storyly.util.animation.models.b;
import com.appsamurai.storyly.util.animation.modules.a;
import com.appsamurai.storyly.util.animation.modules.b;
import java.util.Random;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class c {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final b f6270a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public a f6271b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public b f6272c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public int[] f6273d = {-65536};
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.models.c[] f6274e = {new com.appsamurai.storyly.util.animation.models.c(16, 0.0f, 2)};
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.models.b[] f6275f = {b.C0056b.f6298a};
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.models.a f6276g = new com.appsamurai.storyly.util.animation.models.a(false, 0, false, false, 15);

    /* renamed from: h  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.emitters.c f6277h;

    public c(@NotNull b bVar) {
        Intrinsics.checkNotNullParameter(bVar, "konfettiView");
        this.f6270a = bVar;
        Random random = new Random();
        this.f6271b = new a(random);
        this.f6272c = new com.appsamurai.storyly.util.animation.modules.b(random);
    }

    public final boolean a() {
        com.appsamurai.storyly.util.animation.emitters.c cVar = this.f6277h;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("renderSystem");
            cVar = null;
        }
        return (cVar.f6287g.a() && cVar.f6291k.size() == 0) || (!cVar.f6288h && cVar.f6291k.size() == 0);
    }
}
