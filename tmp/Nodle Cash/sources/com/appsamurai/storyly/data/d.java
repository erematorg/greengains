package com.appsamurai.storyly.data;

import androidx.compose.animation.core.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3631a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3632b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f3633c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final String f3634d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final String f3635e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final String f3636f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final String f3637g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final String f3638h;

    public d(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8) {
        Intrinsics.checkNotNullParameter(str, "storylyListEndpoint");
        Intrinsics.checkNotNullParameter(str2, "storylyAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter(str3, "storylyProductEndpoint");
        Intrinsics.checkNotNullParameter(str4, "shareUrl");
        Intrinsics.checkNotNullParameter(str5, "momentsReportEndpoint");
        Intrinsics.checkNotNullParameter(str6, "momentsAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter(str7, "momentsStoryGroupIdsEndpoint");
        Intrinsics.checkNotNullParameter(str8, "momentsStoryGroupPagedListEndpoint");
        this.f3631a = str;
        this.f3632b = str2;
        this.f3633c = str3;
        this.f3634d = str4;
        this.f3635e = str5;
        this.f3636f = str6;
        this.f3637g = str7;
        this.f3638h = str8;
    }

    @NotNull
    public final String a() {
        return this.f3637g;
    }

    @NotNull
    public final String b() {
        return this.f3638h;
    }

    @NotNull
    public final String c() {
        return this.f3634d;
    }

    @NotNull
    public final String d() {
        return this.f3631a;
    }

    @NotNull
    public final String e() {
        return this.f3633c;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Intrinsics.areEqual((Object) this.f3631a, (Object) dVar.f3631a) && Intrinsics.areEqual((Object) this.f3632b, (Object) dVar.f3632b) && Intrinsics.areEqual((Object) this.f3633c, (Object) dVar.f3633c) && Intrinsics.areEqual((Object) this.f3634d, (Object) dVar.f3634d) && Intrinsics.areEqual((Object) this.f3635e, (Object) dVar.f3635e) && Intrinsics.areEqual((Object) this.f3636f, (Object) dVar.f3636f) && Intrinsics.areEqual((Object) this.f3637g, (Object) dVar.f3637g) && Intrinsics.areEqual((Object) this.f3638h, (Object) dVar.f3638h);
    }

    public int hashCode() {
        return this.f3638h.hashCode() + a.i(this.f3637g, a.i(this.f3636f, a.i(this.f3635e, a.i(this.f3634d, a.i(this.f3633c, a.i(this.f3632b, this.f3631a.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("EndpointConstants(storylyListEndpoint=");
        sb.append(this.f3631a);
        sb.append(", storylyAnalyticsEndpoint=");
        sb.append(this.f3632b);
        sb.append(", storylyProductEndpoint=");
        sb.append(this.f3633c);
        sb.append(", shareUrl=");
        sb.append(this.f3634d);
        sb.append(", momentsReportEndpoint=");
        sb.append(this.f3635e);
        sb.append(", momentsAnalyticsEndpoint=");
        sb.append(this.f3636f);
        sb.append(", momentsStoryGroupIdsEndpoint=");
        sb.append(this.f3637g);
        sb.append(", momentsStoryGroupPagedListEndpoint=");
        return a.o(')', this.f3638h, sb);
    }
}
