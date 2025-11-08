package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0012JD\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\b\u0010\u0012¨\u0006 "}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/Identities;", "", "id", "", "identity", "", "accountId", "cacao_payload", "is_owner", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getIdentity", "()Ljava/lang/String;", "getAccountId", "getCacao_payload", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/sdk/storage/data/dao/Identities;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Identities {
    @NotNull
    private final String accountId;
    @Nullable
    private final String cacao_payload;
    private final long id;
    @NotNull
    private final String identity;
    @Nullable
    private final Boolean is_owner;

    public Identities(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "identity");
        Intrinsics.checkNotNullParameter(str2, "accountId");
        this.id = j2;
        this.identity = str;
        this.accountId = str2;
        this.cacao_payload = str3;
        this.is_owner = bool;
    }

    public static /* synthetic */ Identities copy$default(Identities identities, long j2, String str, String str2, String str3, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = identities.id;
        }
        long j3 = j2;
        if ((i3 & 2) != 0) {
            str = identities.identity;
        }
        String str4 = str;
        if ((i3 & 4) != 0) {
            str2 = identities.accountId;
        }
        String str5 = str2;
        if ((i3 & 8) != 0) {
            str3 = identities.cacao_payload;
        }
        String str6 = str3;
        if ((i3 & 16) != 0) {
            bool = identities.is_owner;
        }
        return identities.copy(j3, str4, str5, str6, bool);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.identity;
    }

    @NotNull
    public final String component3() {
        return this.accountId;
    }

    @Nullable
    public final String component4() {
        return this.cacao_payload;
    }

    @Nullable
    public final Boolean component5() {
        return this.is_owner;
    }

    @NotNull
    public final Identities copy(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "identity");
        Intrinsics.checkNotNullParameter(str2, "accountId");
        return new Identities(j2, str, str2, str3, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identities)) {
            return false;
        }
        Identities identities = (Identities) obj;
        return this.id == identities.id && Intrinsics.areEqual((Object) this.identity, (Object) identities.identity) && Intrinsics.areEqual((Object) this.accountId, (Object) identities.accountId) && Intrinsics.areEqual((Object) this.cacao_payload, (Object) identities.cacao_payload) && Intrinsics.areEqual((Object) this.is_owner, (Object) identities.is_owner);
    }

    @NotNull
    public final String getAccountId() {
        return this.accountId;
    }

    @Nullable
    public final String getCacao_payload() {
        return this.cacao_payload;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getIdentity() {
        return this.identity;
    }

    public int hashCode() {
        int i3 = a.i(this.accountId, a.i(this.identity, Long.hashCode(this.id) * 31, 31), 31);
        String str = this.cacao_payload;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.is_owner;
        if (bool != null) {
            i4 = bool.hashCode();
        }
        return hashCode + i4;
    }

    @Nullable
    public final Boolean is_owner() {
        return this.is_owner;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.identity;
        String str2 = this.accountId;
        String str3 = this.cacao_payload;
        Boolean bool = this.is_owner;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "Identities(id=", ", identity=", str);
        b.w(v2, ", accountId=", str2, ", cacao_payload=", str3);
        v2.append(", is_owner=");
        v2.append(bool);
        v2.append(")");
        return v2.toString();
    }
}
