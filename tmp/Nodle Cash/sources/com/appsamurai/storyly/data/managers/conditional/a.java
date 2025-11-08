package com.appsamurai.storyly.data.managers.conditional;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f3896a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Set<c> f3897b;

    public a() {
        this((Integer) null, (Set) null, 3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual((Object) this.f3896a, (Object) aVar.f3896a) && Intrinsics.areEqual((Object) this.f3897b, (Object) aVar.f3897b);
    }

    public int hashCode() {
        Integer num = this.f3896a;
        return this.f3897b.hashCode() + ((num == null ? 0 : num.hashCode()) * 31);
    }

    @NotNull
    public String toString() {
        return "ConditionalInteractive(reaction=" + this.f3896a + ", affectedStories=" + this.f3897b + ')';
    }

    public a(@Nullable Integer num, @NotNull Set<c> set) {
        Intrinsics.checkNotNullParameter(set, "affectedStories");
        this.f3896a = num;
        this.f3897b = set;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Integer num, Set set, int i3) {
        this((Integer) null, (i3 & 2) != 0 ? new LinkedHashSet() : null);
    }
}
