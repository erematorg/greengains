package com.apollographql.apollo3.api;

import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J%\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/DeferredFragmentIdentifier;", "", "path", "", "label", "", "(Ljava/util/List;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getPath", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DeferredFragmentIdentifier {
    @Nullable
    private final String label;
    @NotNull
    private final List<Object> path;

    public DeferredFragmentIdentifier(@NotNull List<? extends Object> list, @Nullable String str) {
        Intrinsics.checkNotNullParameter(list, "path");
        this.path = list;
        this.label = str;
    }

    public static /* synthetic */ DeferredFragmentIdentifier copy$default(DeferredFragmentIdentifier deferredFragmentIdentifier, List<Object> list, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = deferredFragmentIdentifier.path;
        }
        if ((i3 & 2) != 0) {
            str = deferredFragmentIdentifier.label;
        }
        return deferredFragmentIdentifier.copy(list, str);
    }

    @NotNull
    public final List<Object> component1() {
        return this.path;
    }

    @Nullable
    public final String component2() {
        return this.label;
    }

    @NotNull
    public final DeferredFragmentIdentifier copy(@NotNull List<? extends Object> list, @Nullable String str) {
        Intrinsics.checkNotNullParameter(list, "path");
        return new DeferredFragmentIdentifier(list, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeferredFragmentIdentifier)) {
            return false;
        }
        DeferredFragmentIdentifier deferredFragmentIdentifier = (DeferredFragmentIdentifier) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) deferredFragmentIdentifier.path) && Intrinsics.areEqual((Object) this.label, (Object) deferredFragmentIdentifier.label);
    }

    @Nullable
    public final String getLabel() {
        return this.label;
    }

    @NotNull
    public final List<Object> getPath() {
        return this.path;
    }

    public int hashCode() {
        int hashCode = this.path.hashCode() * 31;
        String str = this.label;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("DeferredFragmentIdentifier(path=");
        sb.append(this.path);
        sb.append(", label=");
        return a.o(')', this.label, sb);
    }
}
