package com.reown.sign.storage.data.dao.optionalnamespaces;

import androidx.compose.animation.core.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/storage/data/dao/optionalnamespaces/GetOptionalNamespaces;", "", "key", "", "chains", "", "methods", "events", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getKey", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetOptionalNamespaces {
    @Nullable
    private final List<String> chains;
    @NotNull
    private final List<String> events;
    @NotNull
    private final String key;
    @NotNull
    private final List<String> methods;

    public GetOptionalNamespaces(@NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        this.key = str;
        this.chains = list;
        this.methods = list2;
        this.events = list3;
    }

    public static /* synthetic */ GetOptionalNamespaces copy$default(GetOptionalNamespaces getOptionalNamespaces, String str, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = getOptionalNamespaces.key;
        }
        if ((i3 & 2) != 0) {
            list = getOptionalNamespaces.chains;
        }
        if ((i3 & 4) != 0) {
            list2 = getOptionalNamespaces.methods;
        }
        if ((i3 & 8) != 0) {
            list3 = getOptionalNamespaces.events;
        }
        return getOptionalNamespaces.copy(str, list, list2, list3);
    }

    @NotNull
    public final String component1() {
        return this.key;
    }

    @Nullable
    public final List<String> component2() {
        return this.chains;
    }

    @NotNull
    public final List<String> component3() {
        return this.methods;
    }

    @NotNull
    public final List<String> component4() {
        return this.events;
    }

    @NotNull
    public final GetOptionalNamespaces copy(@NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return new GetOptionalNamespaces(str, list, list2, list3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetOptionalNamespaces)) {
            return false;
        }
        GetOptionalNamespaces getOptionalNamespaces = (GetOptionalNamespaces) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) getOptionalNamespaces.key) && Intrinsics.areEqual((Object) this.chains, (Object) getOptionalNamespaces.chains) && Intrinsics.areEqual((Object) this.methods, (Object) getOptionalNamespaces.methods) && Intrinsics.areEqual((Object) this.events, (Object) getOptionalNamespaces.events);
    }

    @Nullable
    public final List<String> getChains() {
        return this.chains;
    }

    @NotNull
    public final List<String> getEvents() {
        return this.events;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final List<String> getMethods() {
        return this.methods;
    }

    public int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        List<String> list = this.chains;
        return this.events.hashCode() + a.j(this.methods, (hashCode + (list == null ? 0 : list.hashCode())) * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.key;
        List<String> list = this.chains;
        List<String> list2 = this.methods;
        List<String> list3 = this.events;
        return "GetOptionalNamespaces(key=" + str + ", chains=" + list + ", methods=" + list2 + ", events=" + list3 + ")";
    }
}
