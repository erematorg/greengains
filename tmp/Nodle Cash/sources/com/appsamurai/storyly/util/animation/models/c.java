package com.appsamurai.storyly.util.animation.models;

import android.content.res.Resources;
import android.support.v4.media.session.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final int f6299a;

    /* renamed from: b  reason: collision with root package name */
    public final float f6300b;

    public c(int i3, float f2) {
        this.f6299a = i3;
        this.f6300b = f2;
        if (f2 == 0.0f) {
            throw new IllegalArgumentException(("mass=" + a() + " must be != 0").toString());
        }
    }

    public final float a() {
        return this.f6300b;
    }

    public final float b() {
        return ((float) this.f6299a) * Resources.getSystem().getDisplayMetrics().density;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f6299a == cVar.f6299a && Intrinsics.areEqual((Object) Float.valueOf(this.f6300b), (Object) Float.valueOf(cVar.f6300b));
    }

    public int hashCode() {
        return Float.hashCode(this.f6300b) + (Integer.hashCode(this.f6299a) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Size(sizeInDp=");
        sb.append(this.f6299a);
        sb.append(", mass=");
        return a.o(sb, this.f6300b, ')');
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(int i3, float f2, int i4) {
        this(i3, (i4 & 2) != 0 ? 5.0f : f2);
    }
}
