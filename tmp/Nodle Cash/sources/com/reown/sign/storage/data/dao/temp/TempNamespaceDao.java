package com.reown.sign.storage.data.dao.temp;

import androidx.compose.animation.core.a;
import app.cash.sqldelight.ColumnAdapter;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001.Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\tHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\tHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\tHÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0001\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\r\u0010\u001b¨\u0006/"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao;", "", "request_id", "", "session_id", "topic", "", "key", "chains", "", "accounts", "methods", "events", "isAcknowledged", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getRequest_id", "()J", "getSession_id", "getTopic", "()Ljava/lang/String;", "getKey", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao;", "equals", "", "other", "hashCode", "", "toString", "Adapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TempNamespaceDao {
    @NotNull
    private final List<String> accounts;
    @Nullable
    private final List<String> chains;
    @NotNull
    private final List<String> events;
    @Nullable
    private final Long isAcknowledged;
    @NotNull
    private final String key;
    @NotNull
    private final List<String> methods;
    private final long request_id;
    private final long session_id;
    @NotNull
    private final String topic;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001Bo\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nR#\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR#\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR#\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR#\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;", "", "chainsAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "accountsAdapter", "methodsAdapter", "eventsAdapter", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;)V", "getChainsAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "getAccountsAdapter", "getMethodsAdapter", "getEventsAdapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<List<String>, String> accountsAdapter;
        @NotNull
        private final ColumnAdapter<List<String>, String> chainsAdapter;
        @NotNull
        private final ColumnAdapter<List<String>, String> eventsAdapter;
        @NotNull
        private final ColumnAdapter<List<String>, String> methodsAdapter;

        public Adapter(@NotNull ColumnAdapter<List<String>, String> columnAdapter, @NotNull ColumnAdapter<List<String>, String> columnAdapter2, @NotNull ColumnAdapter<List<String>, String> columnAdapter3, @NotNull ColumnAdapter<List<String>, String> columnAdapter4) {
            Intrinsics.checkNotNullParameter(columnAdapter, "chainsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter2, "accountsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter3, "methodsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter4, "eventsAdapter");
            this.chainsAdapter = columnAdapter;
            this.accountsAdapter = columnAdapter2;
            this.methodsAdapter = columnAdapter3;
            this.eventsAdapter = columnAdapter4;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getAccountsAdapter() {
            return this.accountsAdapter;
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

    public TempNamespaceDao(long j2, long j3, @NotNull String str, @NotNull String str2, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        this.request_id = j2;
        this.session_id = j3;
        this.topic = str;
        this.key = str2;
        this.chains = list;
        this.accounts = list2;
        this.methods = list3;
        this.events = list4;
        this.isAcknowledged = l2;
    }

    public static /* synthetic */ TempNamespaceDao copy$default(TempNamespaceDao tempNamespaceDao, long j2, long j3, String str, String str2, List list, List list2, List list3, List list4, Long l2, int i3, Object obj) {
        TempNamespaceDao tempNamespaceDao2 = tempNamespaceDao;
        int i4 = i3;
        return tempNamespaceDao.copy((i4 & 1) != 0 ? tempNamespaceDao2.request_id : j2, (i4 & 2) != 0 ? tempNamespaceDao2.session_id : j3, (i4 & 4) != 0 ? tempNamespaceDao2.topic : str, (i4 & 8) != 0 ? tempNamespaceDao2.key : str2, (i4 & 16) != 0 ? tempNamespaceDao2.chains : list, (i4 & 32) != 0 ? tempNamespaceDao2.accounts : list2, (i4 & 64) != 0 ? tempNamespaceDao2.methods : list3, (i4 & 128) != 0 ? tempNamespaceDao2.events : list4, (i4 & 256) != 0 ? tempNamespaceDao2.isAcknowledged : l2);
    }

    public final long component1() {
        return this.request_id;
    }

    public final long component2() {
        return this.session_id;
    }

    @NotNull
    public final String component3() {
        return this.topic;
    }

    @NotNull
    public final String component4() {
        return this.key;
    }

    @Nullable
    public final List<String> component5() {
        return this.chains;
    }

    @NotNull
    public final List<String> component6() {
        return this.accounts;
    }

    @NotNull
    public final List<String> component7() {
        return this.methods;
    }

    @NotNull
    public final List<String> component8() {
        return this.events;
    }

    @Nullable
    public final Long component9() {
        return this.isAcknowledged;
    }

    @NotNull
    public final TempNamespaceDao copy(long j2, long j3, @NotNull String str, @NotNull String str2, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4, @Nullable Long l2) {
        String str3 = str;
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_TOPIC);
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str4, JwtUtilsKt.DID_METHOD_KEY);
        List<String> list5 = list2;
        Intrinsics.checkNotNullParameter(list5, "accounts");
        List<String> list6 = list3;
        Intrinsics.checkNotNullParameter(list6, "methods");
        List<String> list7 = list4;
        Intrinsics.checkNotNullParameter(list7, "events");
        return new TempNamespaceDao(j2, j3, str3, str4, list, list5, list6, list7, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TempNamespaceDao)) {
            return false;
        }
        TempNamespaceDao tempNamespaceDao = (TempNamespaceDao) obj;
        return this.request_id == tempNamespaceDao.request_id && this.session_id == tempNamespaceDao.session_id && Intrinsics.areEqual((Object) this.topic, (Object) tempNamespaceDao.topic) && Intrinsics.areEqual((Object) this.key, (Object) tempNamespaceDao.key) && Intrinsics.areEqual((Object) this.chains, (Object) tempNamespaceDao.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) tempNamespaceDao.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) tempNamespaceDao.methods) && Intrinsics.areEqual((Object) this.events, (Object) tempNamespaceDao.events) && Intrinsics.areEqual((Object) this.isAcknowledged, (Object) tempNamespaceDao.isAcknowledged);
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

    public final long getRequest_id() {
        return this.request_id;
    }

    public final long getSession_id() {
        return this.session_id;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i3 = a.i(this.key, a.i(this.topic, a.D(this.session_id, Long.hashCode(this.request_id) * 31, 31), 31), 31);
        List<String> list = this.chains;
        int i4 = 0;
        int j2 = a.j(this.events, a.j(this.methods, a.j(this.accounts, (i3 + (list == null ? 0 : list.hashCode())) * 31, 31), 31), 31);
        Long l2 = this.isAcknowledged;
        if (l2 != null) {
            i4 = l2.hashCode();
        }
        return j2 + i4;
    }

    @Nullable
    public final Long isAcknowledged() {
        return this.isAcknowledged;
    }

    @NotNull
    public String toString() {
        long j2 = this.request_id;
        long j3 = this.session_id;
        String str = this.topic;
        String str2 = this.key;
        List<String> list = this.chains;
        List<String> list2 = this.accounts;
        List<String> list3 = this.methods;
        List<String> list4 = this.events;
        Long l2 = this.isAcknowledged;
        StringBuilder u3 = a.u("TempNamespaceDao(request_id=", j2, ", session_id=");
        u3.append(j3);
        u3.append(", topic=");
        u3.append(str);
        u3.append(", key=");
        u3.append(str2);
        u3.append(", chains=");
        u3.append(list);
        u3.append(", accounts=");
        u3.append(list2);
        u3.append(", methods=");
        u3.append(list3);
        u3.append(", events=");
        u3.append(list4);
        u3.append(", isAcknowledged=");
        u3.append(l2);
        u3.append(")");
        return u3.toString();
    }
}
