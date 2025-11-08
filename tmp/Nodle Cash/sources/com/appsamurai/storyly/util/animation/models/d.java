package com.appsamurai.storyly.util.animation.models;

import android.support.v4.media.session.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    public float f6301a;

    /* renamed from: b  reason: collision with root package name */
    public float f6302b;

    public d() {
        this(0.0f, 0.0f, 3);
    }

    public static d a(d dVar, float f2, float f3, int i3) {
        if ((i3 & 1) != 0) {
            f2 = dVar.f6301a;
        }
        if ((i3 & 2) != 0) {
            f3 = dVar.f6302b;
        }
        dVar.getClass();
        return new d(f2, f3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Intrinsics.areEqual((Object) Float.valueOf(this.f6301a), (Object) Float.valueOf(dVar.f6301a)) && Intrinsics.areEqual((Object) Float.valueOf(this.f6302b), (Object) Float.valueOf(dVar.f6302b));
    }

    public int hashCode() {
        return Float.hashCode(this.f6302b) + (Float.hashCode(this.f6301a) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Vector(x=");
        sb.append(this.f6301a);
        sb.append(", y=");
        return a.o(sb, this.f6302b, ')');
    }

    public d(float f2, float f3) {
        this.f6301a = f2;
        this.f6302b = f3;
    }

    public final void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "v");
        this.f6301a += dVar.f6301a;
        this.f6302b += dVar.f6302b;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(float f2, float f3, int i3) {
        this((i3 & 1) != 0 ? 0.0f : f2, (i3 & 2) != 0 ? 0.0f : f3);
    }
}
