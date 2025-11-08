package com.appsamurai.storyly.storylypresenter.share;

import androidx.annotation.DrawableRes;
import androidx.compose.animation.core.a;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final int f5304a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f5305b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final c f5306c;

    public b(@DrawableRes int i3, @NotNull String str, @NotNull c cVar) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(cVar, "storyShareType");
        this.f5304a = i3;
        this.f5305b = str;
        this.f5306c = cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f5304a == bVar.f5304a && Intrinsics.areEqual((Object) this.f5305b, (Object) bVar.f5305b) && this.f5306c == bVar.f5306c;
    }

    public int hashCode() {
        return this.f5306c.hashCode() + a.i(this.f5305b, Integer.hashCode(this.f5304a) * 31, 31);
    }

    @NotNull
    public String toString() {
        return "ShareItemEntity(icon=" + this.f5304a + ", title=" + this.f5305b + ", storyShareType=" + this.f5306c + ')';
    }
}
