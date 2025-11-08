package com.appsamurai.storyly.util.animation.models;

import androidx.camera.core.impl.i;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6292a;

    /* renamed from: b  reason: collision with root package name */
    public long f6293b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f6294c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f6295d;

    public a() {
        this(false, 0, false, false, 15);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f6292a == aVar.f6292a && this.f6293b == aVar.f6293b && this.f6294c == aVar.f6294c && this.f6295d == aVar.f6295d;
    }

    public int hashCode() {
        boolean z2 = this.f6292a;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int D2 = androidx.compose.animation.core.a.D(this.f6293b, (z2 ? 1 : 0) * true, 31);
        boolean z4 = this.f6294c;
        if (z4) {
            z4 = true;
        }
        int i3 = (D2 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f6295d;
        if (!z5) {
            z3 = z5;
        }
        return i3 + (z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("ConfettiConfig(fadeOut=");
        sb.append(this.f6292a);
        sb.append(", timeToLive=");
        sb.append(this.f6293b);
        sb.append(", rotate=");
        sb.append(this.f6294c);
        sb.append(", accelerate=");
        return i.c(sb, this.f6295d, ')');
    }

    public a(boolean z2, long j2, boolean z3, boolean z4) {
        this.f6292a = z2;
        this.f6293b = j2;
        this.f6294c = z3;
        this.f6295d = z4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(boolean z2, long j2, boolean z3, boolean z4, int i3) {
        this((i3 & 1) != 0 ? false : z2, (i3 & 2) != 0 ? ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS : j2, (i3 & 4) != 0 ? true : z3, (i3 & 8) != 0 ? true : z4);
    }
}
