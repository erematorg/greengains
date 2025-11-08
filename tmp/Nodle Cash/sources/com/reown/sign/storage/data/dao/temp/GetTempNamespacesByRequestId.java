package com.reown.sign.storage.data.dao.temp;

import androidx.compose.animation.core.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J_\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006#"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/GetTempNamespacesByRequestId;", "", "session_id", "", "key", "", "chains", "", "accounts", "methods", "events", "<init>", "(JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getSession_id", "()J", "getKey", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetTempNamespacesByRequestId {
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
    private final long session_id;

    public GetTempNamespacesByRequestId(long j2, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        this.session_id = j2;
        this.key = str;
        this.chains = list;
        this.accounts = list2;
        this.methods = list3;
        this.events = list4;
    }

    public static /* synthetic */ GetTempNamespacesByRequestId copy$default(GetTempNamespacesByRequestId getTempNamespacesByRequestId, long j2, String str, List list, List list2, List list3, List list4, int i3, Object obj) {
        GetTempNamespacesByRequestId getTempNamespacesByRequestId2 = getTempNamespacesByRequestId;
        return getTempNamespacesByRequestId.copy((i3 & 1) != 0 ? getTempNamespacesByRequestId2.session_id : j2, (i3 & 2) != 0 ? getTempNamespacesByRequestId2.key : str, (i3 & 4) != 0 ? getTempNamespacesByRequestId2.chains : list, (i3 & 8) != 0 ? getTempNamespacesByRequestId2.accounts : list2, (i3 & 16) != 0 ? getTempNamespacesByRequestId2.methods : list3, (i3 & 32) != 0 ? getTempNamespacesByRequestId2.events : list4);
    }

    public final long component1() {
        return this.session_id;
    }

    @NotNull
    public final String component2() {
        return this.key;
    }

    @Nullable
    public final List<String> component3() {
        return this.chains;
    }

    @NotNull
    public final List<String> component4() {
        return this.accounts;
    }

    @NotNull
    public final List<String> component5() {
        return this.methods;
    }

    @NotNull
    public final List<String> component6() {
        return this.events;
    }

    @NotNull
    public final GetTempNamespacesByRequestId copy(long j2, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        return new GetTempNamespacesByRequestId(j2, str, list, list2, list3, list4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetTempNamespacesByRequestId)) {
            return false;
        }
        GetTempNamespacesByRequestId getTempNamespacesByRequestId = (GetTempNamespacesByRequestId) obj;
        return this.session_id == getTempNamespacesByRequestId.session_id && Intrinsics.areEqual((Object) this.key, (Object) getTempNamespacesByRequestId.key) && Intrinsics.areEqual((Object) this.chains, (Object) getTempNamespacesByRequestId.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) getTempNamespacesByRequestId.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) getTempNamespacesByRequestId.methods) && Intrinsics.areEqual((Object) this.events, (Object) getTempNamespacesByRequestId.events);
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

    public final long getSession_id() {
        return this.session_id;
    }

    public int hashCode() {
        int i3 = a.i(this.key, Long.hashCode(this.session_id) * 31, 31);
        List<String> list = this.chains;
        return this.events.hashCode() + a.j(this.methods, a.j(this.accounts, (i3 + (list == null ? 0 : list.hashCode())) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.session_id;
        String str = this.key;
        List<String> list = this.chains;
        List<String> list2 = this.accounts;
        List<String> list3 = this.methods;
        List<String> list4 = this.events;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "GetTempNamespacesByRequestId(session_id=", ", key=", str);
        v2.append(", chains=");
        v2.append(list);
        v2.append(", accounts=");
        v2.append(list2);
        v2.append(", methods=");
        v2.append(list3);
        v2.append(", events=");
        v2.append(list4);
        v2.append(")");
        return v2.toString();
    }
}
