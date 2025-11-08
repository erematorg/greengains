package com.appsamurai.storyly.data.managers.conditional;

import androidx.compose.animation.core.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3906a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3907b;

    public c(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "groupId");
        Intrinsics.checkNotNullParameter(str2, "storyId");
        this.f3906a = str;
        this.f3907b = str2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Intrinsics.areEqual((Object) this.f3906a, (Object) cVar.f3906a) && Intrinsics.areEqual((Object) this.f3907b, (Object) cVar.f3907b);
    }

    public int hashCode() {
        return this.f3907b.hashCode() + (this.f3906a.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StoryIndex(groupId=");
        sb.append(this.f3906a);
        sb.append(", storyId=");
        return a.o(')', this.f3907b, sb);
    }
}
