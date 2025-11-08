package com.reown.android.internal.common.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/Namespace;", "", "<init>", "()V", "chains", "", "", "getChains", "()Ljava/util/List;", "methods", "getMethods", "events", "getEvents", "Proposal", "Session", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "Lcom/reown/android/internal/common/model/Namespace$Session;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Namespace {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/Namespace$Proposal;", "Lcom/reown/android/internal/common/model/Namespace;", "methods", "", "", "chains", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getMethods", "()Ljava/util/List;", "getChains", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Proposal extends Namespace {
        @Nullable
        private final List<String> chains;
        @NotNull
        private final List<String> events;
        @NotNull
        private final List<String> methods;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Proposal(List list, List list2, List list3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i3 & 2) != 0 ? null : list2, list3);
        }

        public static /* synthetic */ Proposal copy$default(Proposal proposal, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                list = proposal.methods;
            }
            if ((i3 & 2) != 0) {
                list2 = proposal.chains;
            }
            if ((i3 & 4) != 0) {
                list3 = proposal.events;
            }
            return proposal.copy(list, list2, list3);
        }

        @NotNull
        public final List<String> component1() {
            return this.methods;
        }

        @Nullable
        public final List<String> component2() {
            return this.chains;
        }

        @NotNull
        public final List<String> component3() {
            return this.events;
        }

        @NotNull
        public final Proposal copy(@NotNull @Json(name = "methods") List<String> list, @Nullable @Json(name = "chains") List<String> list2, @NotNull @Json(name = "events") List<String> list3) {
            Intrinsics.checkNotNullParameter(list, "methods");
            Intrinsics.checkNotNullParameter(list3, "events");
            return new Proposal(list, list2, list3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Proposal)) {
                return false;
            }
            Proposal proposal = (Proposal) obj;
            return Intrinsics.areEqual((Object) this.methods, (Object) proposal.methods) && Intrinsics.areEqual((Object) this.chains, (Object) proposal.chains) && Intrinsics.areEqual((Object) this.events, (Object) proposal.events);
        }

        @Nullable
        public List<String> getChains() {
            return this.chains;
        }

        @NotNull
        public List<String> getEvents() {
            return this.events;
        }

        @NotNull
        public List<String> getMethods() {
            return this.methods;
        }

        public int hashCode() {
            int hashCode = this.methods.hashCode() * 31;
            List<String> list = this.chains;
            return this.events.hashCode() + ((hashCode + (list == null ? 0 : list.hashCode())) * 31);
        }

        @NotNull
        public String toString() {
            List<String> list = this.methods;
            List<String> list2 = this.chains;
            List<String> list3 = this.events;
            StringBuilder sb = new StringBuilder("Proposal(methods=");
            sb.append(list);
            sb.append(", chains=");
            sb.append(list2);
            sb.append(", events=");
            return C0118y.h(")", list3, sb);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Proposal(@NotNull @Json(name = "methods") List<String> list, @Nullable @Json(name = "chains") List<String> list2, @NotNull @Json(name = "events") List<String> list3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "methods");
            Intrinsics.checkNotNullParameter(list3, "events");
            this.methods = list;
            this.chains = list2;
            this.events = list3;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/android/internal/common/model/Namespace$Session;", "Lcom/reown/android/internal/common/model/Namespace;", "chains", "", "", "accounts", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Session extends Namespace {
        @NotNull
        private final List<String> accounts;
        @Nullable
        private final List<String> chains;
        @NotNull
        private final List<String> events;
        @NotNull
        private final List<String> methods;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Session(List list, List list2, List list3, List list4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : list, list2, list3, list4);
        }

        public static /* synthetic */ Session copy$default(Session session, List<String> list, List<String> list2, List<String> list3, List<String> list4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                list = session.chains;
            }
            if ((i3 & 2) != 0) {
                list2 = session.accounts;
            }
            if ((i3 & 4) != 0) {
                list3 = session.methods;
            }
            if ((i3 & 8) != 0) {
                list4 = session.events;
            }
            return session.copy(list, list2, list3, list4);
        }

        @Nullable
        public final List<String> component1() {
            return this.chains;
        }

        @NotNull
        public final List<String> component2() {
            return this.accounts;
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
        public final Session copy(@Nullable @Json(name = "chains") List<String> list, @NotNull @Json(name = "accounts") List<String> list2, @NotNull @Json(name = "methods") List<String> list3, @NotNull @Json(name = "events") List<String> list4) {
            Intrinsics.checkNotNullParameter(list2, "accounts");
            Intrinsics.checkNotNullParameter(list3, "methods");
            Intrinsics.checkNotNullParameter(list4, "events");
            return new Session(list, list2, list3, list4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            return Intrinsics.areEqual((Object) this.chains, (Object) session.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) session.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) session.methods) && Intrinsics.areEqual((Object) this.events, (Object) session.events);
        }

        @NotNull
        public final List<String> getAccounts() {
            return this.accounts;
        }

        @Nullable
        public List<String> getChains() {
            return this.chains;
        }

        @NotNull
        public List<String> getEvents() {
            return this.events;
        }

        @NotNull
        public List<String> getMethods() {
            return this.methods;
        }

        public int hashCode() {
            List<String> list = this.chains;
            return this.events.hashCode() + a.j(this.methods, a.j(this.accounts, (list == null ? 0 : list.hashCode()) * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            List<String> list = this.chains;
            List<String> list2 = this.accounts;
            List<String> list3 = this.methods;
            List<String> list4 = this.events;
            return "Session(chains=" + list + ", accounts=" + list2 + ", methods=" + list3 + ", events=" + list4 + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Session(@Nullable @Json(name = "chains") List<String> list, @NotNull @Json(name = "accounts") List<String> list2, @NotNull @Json(name = "methods") List<String> list3, @NotNull @Json(name = "events") List<String> list4) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list2, "accounts");
            Intrinsics.checkNotNullParameter(list3, "methods");
            Intrinsics.checkNotNullParameter(list4, "events");
            this.chains = list;
            this.accounts = list2;
            this.methods = list3;
            this.events = list4;
        }
    }

    public /* synthetic */ Namespace(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Nullable
    public abstract List<String> getChains();

    @NotNull
    public abstract List<String> getEvents();

    @NotNull
    public abstract List<String> getMethods();

    private Namespace() {
    }
}
