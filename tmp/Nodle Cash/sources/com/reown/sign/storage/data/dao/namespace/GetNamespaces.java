package com.reown.sign.storage.data.dao.namespace;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003JU\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/storage/data/dao/namespace/GetNamespaces;", "", "key", "", "chains", "", "accounts", "methods", "events", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getKey", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetNamespaces {
    @NotNull
    private final List<String> accounts;
    @Nullable
    private final List<String> chains;
    @NotNull
    private final List<String> events;
    @NotNull
    private final String key;
    @NotNull
    private final List<String> methods;

    public GetNamespaces(@NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        this.key = str;
        this.chains = list;
        this.accounts = list2;
        this.methods = list3;
        this.events = list4;
    }

    public static /* synthetic */ GetNamespaces copy$default(GetNamespaces getNamespaces, String str, List<String> list, List<String> list2, List<String> list3, List<String> list4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = getNamespaces.key;
        }
        if ((i3 & 2) != 0) {
            list = getNamespaces.chains;
        }
        List<String> list5 = list;
        if ((i3 & 4) != 0) {
            list2 = getNamespaces.accounts;
        }
        List<String> list6 = list2;
        if ((i3 & 8) != 0) {
            list3 = getNamespaces.methods;
        }
        List<String> list7 = list3;
        if ((i3 & 16) != 0) {
            list4 = getNamespaces.events;
        }
        return getNamespaces.copy(str, list5, list6, list7, list4);
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
        return this.accounts;
    }

    @NotNull
    public final List<String> component4() {
        return this.methods;
    }

    @NotNull
    public final List<String> component5() {
        return this.events;
    }

    @NotNull
    public final GetNamespaces copy(@NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        return new GetNamespaces(str, list, list2, list3, list4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetNamespaces)) {
            return false;
        }
        GetNamespaces getNamespaces = (GetNamespaces) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) getNamespaces.key) && Intrinsics.areEqual((Object) this.chains, (Object) getNamespaces.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) getNamespaces.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) getNamespaces.methods) && Intrinsics.areEqual((Object) this.events, (Object) getNamespaces.events);
    }

    @NotNull
    public final List<String> getAccounts() {
        return this.accounts;
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
        return this.events.hashCode() + a.j(this.methods, a.j(this.accounts, (hashCode + (list == null ? 0 : list.hashCode())) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.key;
        List<String> list = this.chains;
        List<String> list2 = this.accounts;
        List<String> list3 = this.methods;
        List<String> list4 = this.events;
        StringBuilder sb = new StringBuilder("GetNamespaces(key=");
        sb.append(str);
        sb.append(", chains=");
        sb.append(list);
        sb.append(", accounts=");
        sb.append(list2);
        sb.append(", methods=");
        sb.append(list3);
        sb.append(", events=");
        return C0118y.h(")", list4, sb);
    }
}
