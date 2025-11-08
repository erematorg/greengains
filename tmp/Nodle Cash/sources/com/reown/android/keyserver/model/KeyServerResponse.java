package com.reown.android.keyserver.model;

import A.a;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerResponse;", "", "<init>", "()V", "ResolveInvite", "ResolveIdentity", "Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveIdentity;", "Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveInvite;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class KeyServerResponse {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveIdentity;", "Lcom/reown/android/keyserver/model/KeyServerResponse;", "cacao", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "<init>", "(Lcom/reown/android/internal/common/signing/cacao/Cacao;)V", "getCacao", "()Lcom/reown/android/internal/common/signing/cacao/Cacao;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResolveIdentity extends KeyServerResponse {
        @NotNull
        private final Cacao cacao;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResolveIdentity(@NotNull Cacao cacao2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(cacao2, "cacao");
            this.cacao = cacao2;
        }

        public static /* synthetic */ ResolveIdentity copy$default(ResolveIdentity resolveIdentity, Cacao cacao2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                cacao2 = resolveIdentity.cacao;
            }
            return resolveIdentity.copy(cacao2);
        }

        @NotNull
        public final Cacao component1() {
            return this.cacao;
        }

        @NotNull
        public final ResolveIdentity copy(@NotNull Cacao cacao2) {
            Intrinsics.checkNotNullParameter(cacao2, "cacao");
            return new ResolveIdentity(cacao2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ResolveIdentity) && Intrinsics.areEqual((Object) this.cacao, (Object) ((ResolveIdentity) obj).cacao);
        }

        @NotNull
        public final Cacao getCacao() {
            return this.cacao;
        }

        public int hashCode() {
            return this.cacao.hashCode();
        }

        @NotNull
        public String toString() {
            Cacao cacao2 = this.cacao;
            return "ResolveIdentity(cacao=" + cacao2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveInvite;", "Lcom/reown/android/keyserver/model/KeyServerResponse;", "inviteKey", "", "<init>", "(Ljava/lang/String;)V", "getInviteKey", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResolveInvite extends KeyServerResponse {
        @NotNull
        private final String inviteKey;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResolveInvite(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "inviteKey");
            this.inviteKey = str;
        }

        public static /* synthetic */ ResolveInvite copy$default(ResolveInvite resolveInvite, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = resolveInvite.inviteKey;
            }
            return resolveInvite.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.inviteKey;
        }

        @NotNull
        public final ResolveInvite copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "inviteKey");
            return new ResolveInvite(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ResolveInvite) && Intrinsics.areEqual((Object) this.inviteKey, (Object) ((ResolveInvite) obj).inviteKey);
        }

        @NotNull
        public final String getInviteKey() {
            return this.inviteKey;
        }

        public int hashCode() {
            return this.inviteKey.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("ResolveInvite(inviteKey=", this.inviteKey, ")");
        }
    }

    public /* synthetic */ KeyServerResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KeyServerResponse() {
    }
}
