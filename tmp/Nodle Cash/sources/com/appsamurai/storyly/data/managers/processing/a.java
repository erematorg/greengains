package com.appsamurai.storyly.data.managers.processing;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3947a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f3948b;

    public a(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "data");
        this.f3947a = str;
        this.f3948b = str2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual((Object) this.f3947a, (Object) aVar.f3947a) && Intrinsics.areEqual((Object) this.f3948b, (Object) aVar.f3948b);
    }

    public int hashCode() {
        int hashCode = this.f3947a.hashCode() * 31;
        String str = this.f3948b;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return "InternalData(data=" + this.f3947a + ", eTag=" + this.f3948b + ')';
    }
}
