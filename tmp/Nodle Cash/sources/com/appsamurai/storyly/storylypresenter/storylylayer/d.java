package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Pair<Float, Float> f5693a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Pair<Float, Float> f5694b;

    public d(@NotNull Pair<Float, Float> pair, @NotNull Pair<Float, Float> pair2) {
        Intrinsics.checkNotNullParameter(pair, "size");
        Intrinsics.checkNotNullParameter(pair2, "position");
        this.f5693a = pair;
        this.f5694b = pair2;
    }

    public final float a() {
        return this.f5693a.getSecond().floatValue();
    }

    public final float b() {
        return this.f5693a.getFirst().floatValue();
    }

    public final float c() {
        return this.f5694b.getFirst().floatValue();
    }

    public final float d() {
        return this.f5694b.getSecond().floatValue();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Intrinsics.areEqual((Object) this.f5693a, (Object) dVar.f5693a) && Intrinsics.areEqual((Object) this.f5694b, (Object) dVar.f5694b);
    }

    public int hashCode() {
        return this.f5694b.hashCode() + (this.f5693a.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "SafeFrame(size=" + this.f5693a + ", position=" + this.f5694b + ')';
    }
}
