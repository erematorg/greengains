package com.reown.sign.storage.data.dao.proposalnamespace;

import androidx.compose.animation.core.a;
import app.cash.sqldelight.ColumnAdapter;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001#BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bHÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0003JY\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006$"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao;", "", "id", "", "session_id", "key", "", "chains", "", "methods", "events", "<init>", "(JJLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getId", "()J", "getSession_id", "getKey", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Adapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProposalNamespaceDao {
    @Nullable
    private final List<String> chains;
    @NotNull
    private final List<String> events;
    private final long id;
    @NotNull
    private final String key;
    @NotNull
    private final List<String> methods;
    private final long session_id;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001BU\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\b\u0010\tR#\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR#\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao$Adapter;", "", "chainsAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "methodsAdapter", "eventsAdapter", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;)V", "getChainsAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "getMethodsAdapter", "getEventsAdapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<List<String>, String> chainsAdapter;
        @NotNull
        private final ColumnAdapter<List<String>, String> eventsAdapter;
        @NotNull
        private final ColumnAdapter<List<String>, String> methodsAdapter;

        public Adapter(@NotNull ColumnAdapter<List<String>, String> columnAdapter, @NotNull ColumnAdapter<List<String>, String> columnAdapter2, @NotNull ColumnAdapter<List<String>, String> columnAdapter3) {
            Intrinsics.checkNotNullParameter(columnAdapter, "chainsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter2, "methodsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter3, "eventsAdapter");
            this.chainsAdapter = columnAdapter;
            this.methodsAdapter = columnAdapter2;
            this.eventsAdapter = columnAdapter3;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getChainsAdapter() {
            return this.chainsAdapter;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getEventsAdapter() {
            return this.eventsAdapter;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getMethodsAdapter() {
            return this.methodsAdapter;
        }
    }

    public ProposalNamespaceDao(long j2, long j3, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        this.id = j2;
        this.session_id = j3;
        this.key = str;
        this.chains = list;
        this.methods = list2;
        this.events = list3;
    }

    public static /* synthetic */ ProposalNamespaceDao copy$default(ProposalNamespaceDao proposalNamespaceDao, long j2, long j3, String str, List list, List list2, List list3, int i3, Object obj) {
        ProposalNamespaceDao proposalNamespaceDao2 = proposalNamespaceDao;
        return proposalNamespaceDao.copy((i3 & 1) != 0 ? proposalNamespaceDao2.id : j2, (i3 & 2) != 0 ? proposalNamespaceDao2.session_id : j3, (i3 & 4) != 0 ? proposalNamespaceDao2.key : str, (i3 & 8) != 0 ? proposalNamespaceDao2.chains : list, (i3 & 16) != 0 ? proposalNamespaceDao2.methods : list2, (i3 & 32) != 0 ? proposalNamespaceDao2.events : list3);
    }

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.session_id;
    }

    @NotNull
    public final String component3() {
        return this.key;
    }

    @Nullable
    public final List<String> component4() {
        return this.chains;
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
    public final ProposalNamespaceDao copy(long j2, long j3, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        List<String> list4 = list2;
        Intrinsics.checkNotNullParameter(list4, "methods");
        List<String> list5 = list3;
        Intrinsics.checkNotNullParameter(list5, "events");
        return new ProposalNamespaceDao(j2, j3, str, list, list4, list5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProposalNamespaceDao)) {
            return false;
        }
        ProposalNamespaceDao proposalNamespaceDao = (ProposalNamespaceDao) obj;
        return this.id == proposalNamespaceDao.id && this.session_id == proposalNamespaceDao.session_id && Intrinsics.areEqual((Object) this.key, (Object) proposalNamespaceDao.key) && Intrinsics.areEqual((Object) this.chains, (Object) proposalNamespaceDao.chains) && Intrinsics.areEqual((Object) this.methods, (Object) proposalNamespaceDao.methods) && Intrinsics.areEqual((Object) this.events, (Object) proposalNamespaceDao.events);
    }

    @Nullable
    public final List<String> getChains() {
        return this.chains;
    }

    @NotNull
    public final List<String> getEvents() {
        return this.events;
    }

    public final long getId() {
        return this.id;
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
        int i3 = a.i(this.key, a.D(this.session_id, Long.hashCode(this.id) * 31, 31), 31);
        List<String> list = this.chains;
        return this.events.hashCode() + a.j(this.methods, (i3 + (list == null ? 0 : list.hashCode())) * 31, 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        long j3 = this.session_id;
        String str = this.key;
        List<String> list = this.chains;
        List<String> list2 = this.methods;
        List<String> list3 = this.events;
        StringBuilder u3 = a.u("ProposalNamespaceDao(id=", j2, ", session_id=");
        u3.append(j3);
        u3.append(", key=");
        u3.append(str);
        u3.append(", chains=");
        u3.append(list);
        u3.append(", methods=");
        u3.append(list2);
        u3.append(", events=");
        u3.append(list3);
        u3.append(")");
        return u3.toString();
    }
}
