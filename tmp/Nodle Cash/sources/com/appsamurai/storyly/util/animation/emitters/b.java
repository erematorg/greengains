package com.appsamurai.storyly.util.animation.emitters;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.Nullable;

public abstract class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Function0<Unit> f6280a;

    public abstract void a(float f2);

    public final void a(@Nullable Function0<Unit> function0) {
        this.f6280a = function0;
    }

    public abstract boolean a();
}
