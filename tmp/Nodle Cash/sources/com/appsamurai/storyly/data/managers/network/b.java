package com.appsamurai.storyly.data.managers.network;

import android.support.v4.media.session.a;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public final class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final JSONObject f3908a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final Map<String, String> f3909b;

    /* renamed from: c  reason: collision with root package name */
    public final int f3910c;

    public b(@Nullable JSONObject jSONObject, @Nullable Map<String, String> map, int i3) {
        this.f3908a = jSONObject;
        this.f3909b = map;
        this.f3910c = i3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.f3908a, (Object) bVar.f3908a) && Intrinsics.areEqual((Object) this.f3909b, (Object) bVar.f3909b) && this.f3910c == bVar.f3910c;
    }

    public int hashCode() {
        JSONObject jSONObject = this.f3908a;
        int i3 = 0;
        int hashCode = (jSONObject == null ? 0 : jSONObject.hashCode()) * 31;
        Map<String, String> map = this.f3909b;
        if (map != null) {
            i3 = map.hashCode();
        }
        return Integer.hashCode(this.f3910c) + ((hashCode + i3) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("HTTPResponse(response=");
        sb.append(this.f3908a);
        sb.append(", header=");
        sb.append(this.f3909b);
        sb.append(", status=");
        return a.p(sb, this.f3910c, ')');
    }
}
