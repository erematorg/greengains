package com.reown.android.internal;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001bBI\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0003JK\u0010\u0014\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/reown/android/internal/Proposal;", "", "chains", "", "", "methods", "events", "extensions", "Lcom/reown/android/internal/Proposal$Extension;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "getExtensions", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Extension", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Proposal {
    @NotNull
    private final List<String> chains;
    @NotNull
    private final List<String> events;
    @Nullable
    private final List<Extension> extensions;
    @NotNull
    private final List<String> methods;

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J9\u0010\u0010\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/reown/android/internal/Proposal$Extension;", "", "chains", "", "", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Extension {
        @NotNull
        private final List<String> chains;
        @NotNull
        private final List<String> events;
        @NotNull
        private final List<String> methods;

        public Extension(@NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "methods") List<String> list2, @NotNull @Json(name = "events") List<String> list3) {
            Intrinsics.checkNotNullParameter(list, "chains");
            Intrinsics.checkNotNullParameter(list2, "methods");
            Intrinsics.checkNotNullParameter(list3, "events");
            this.chains = list;
            this.methods = list2;
            this.events = list3;
        }

        public static /* synthetic */ Extension copy$default(Extension extension, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                list = extension.chains;
            }
            if ((i3 & 2) != 0) {
                list2 = extension.methods;
            }
            if ((i3 & 4) != 0) {
                list3 = extension.events;
            }
            return extension.copy(list, list2, list3);
        }

        @NotNull
        public final List<String> component1() {
            return this.chains;
        }

        @NotNull
        public final List<String> component2() {
            return this.methods;
        }

        @NotNull
        public final List<String> component3() {
            return this.events;
        }

        @NotNull
        public final Extension copy(@NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "methods") List<String> list2, @NotNull @Json(name = "events") List<String> list3) {
            Intrinsics.checkNotNullParameter(list, "chains");
            Intrinsics.checkNotNullParameter(list2, "methods");
            Intrinsics.checkNotNullParameter(list3, "events");
            return new Extension(list, list2, list3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Extension)) {
                return false;
            }
            Extension extension = (Extension) obj;
            return Intrinsics.areEqual((Object) this.chains, (Object) extension.chains) && Intrinsics.areEqual((Object) this.methods, (Object) extension.methods) && Intrinsics.areEqual((Object) this.events, (Object) extension.events);
        }

        @NotNull
        public final List<String> getChains() {
            return this.chains;
        }

        @NotNull
        public final List<String> getEvents() {
            return this.events;
        }

        @NotNull
        public final List<String> getMethods() {
            return this.methods;
        }

        public int hashCode() {
            return this.events.hashCode() + a.j(this.methods, this.chains.hashCode() * 31, 31);
        }

        @NotNull
        public String toString() {
            List<String> list = this.chains;
            List<String> list2 = this.methods;
            List<String> list3 = this.events;
            StringBuilder sb = new StringBuilder("Extension(chains=");
            sb.append(list);
            sb.append(", methods=");
            sb.append(list2);
            sb.append(", events=");
            return C0118y.h(")", list3, sb);
        }
    }

    public Proposal(@NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "methods") List<String> list2, @NotNull @Json(name = "events") List<String> list3, @Nullable @Json(name = "extension") List<Extension> list4) {
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        this.chains = list;
        this.methods = list2;
        this.events = list3;
        this.extensions = list4;
    }

    public static /* synthetic */ Proposal copy$default(Proposal proposal, List<String> list, List<String> list2, List<String> list3, List<Extension> list4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = proposal.chains;
        }
        if ((i3 & 2) != 0) {
            list2 = proposal.methods;
        }
        if ((i3 & 4) != 0) {
            list3 = proposal.events;
        }
        if ((i3 & 8) != 0) {
            list4 = proposal.extensions;
        }
        return proposal.copy(list, list2, list3, list4);
    }

    @NotNull
    public final List<String> component1() {
        return this.chains;
    }

    @NotNull
    public final List<String> component2() {
        return this.methods;
    }

    @NotNull
    public final List<String> component3() {
        return this.events;
    }

    @Nullable
    public final List<Extension> component4() {
        return this.extensions;
    }

    @NotNull
    public final Proposal copy(@NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "methods") List<String> list2, @NotNull @Json(name = "events") List<String> list3, @Nullable @Json(name = "extension") List<Extension> list4) {
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return new Proposal(list, list2, list3, list4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Proposal)) {
            return false;
        }
        Proposal proposal = (Proposal) obj;
        return Intrinsics.areEqual((Object) this.chains, (Object) proposal.chains) && Intrinsics.areEqual((Object) this.methods, (Object) proposal.methods) && Intrinsics.areEqual((Object) this.events, (Object) proposal.events) && Intrinsics.areEqual((Object) this.extensions, (Object) proposal.extensions);
    }

    @NotNull
    public final List<String> getChains() {
        return this.chains;
    }

    @NotNull
    public final List<String> getEvents() {
        return this.events;
    }

    @Nullable
    public final List<Extension> getExtensions() {
        return this.extensions;
    }

    @NotNull
    public final List<String> getMethods() {
        return this.methods;
    }

    public int hashCode() {
        int j2 = a.j(this.events, a.j(this.methods, this.chains.hashCode() * 31, 31), 31);
        List<Extension> list = this.extensions;
        return j2 + (list == null ? 0 : list.hashCode());
    }

    @NotNull
    public String toString() {
        List<String> list = this.chains;
        List<String> list2 = this.methods;
        List<String> list3 = this.events;
        List<Extension> list4 = this.extensions;
        return "Proposal(chains=" + list + ", methods=" + list2 + ", events=" + list3 + ", extensions=" + list4 + ")";
    }
}
