package com.reown.android.keyserver.model;

import A.a;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerBody;", "", "<init>", "()V", "UnregisterIdentity", "RegisterIdentity", "RegisterInvite", "UnregisterInvite", "Lcom/reown/android/keyserver/model/KeyServerBody$RegisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody$RegisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterInvite;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class KeyServerBody {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerBody$RegisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody;", "cacao", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "<init>", "(Lcom/reown/android/internal/common/signing/cacao/Cacao;)V", "getCacao", "()Lcom/reown/android/internal/common/signing/cacao/Cacao;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisterIdentity extends KeyServerBody {
        @NotNull
        private final Cacao cacao;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RegisterIdentity(@NotNull Cacao cacao2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(cacao2, "cacao");
            this.cacao = cacao2;
        }

        public static /* synthetic */ RegisterIdentity copy$default(RegisterIdentity registerIdentity, Cacao cacao2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                cacao2 = registerIdentity.cacao;
            }
            return registerIdentity.copy(cacao2);
        }

        @NotNull
        public final Cacao component1() {
            return this.cacao;
        }

        @NotNull
        public final RegisterIdentity copy(@NotNull Cacao cacao2) {
            Intrinsics.checkNotNullParameter(cacao2, "cacao");
            return new RegisterIdentity(cacao2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RegisterIdentity) && Intrinsics.areEqual((Object) this.cacao, (Object) ((RegisterIdentity) obj).cacao);
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
            return "RegisterIdentity(cacao=" + cacao2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerBody$RegisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerBody;", "idAuth", "", "<init>", "(Ljava/lang/String;)V", "getIdAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisterInvite extends KeyServerBody {
        @NotNull
        private final String idAuth;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RegisterInvite(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "idAuth");
            this.idAuth = str;
        }

        public static /* synthetic */ RegisterInvite copy$default(RegisterInvite registerInvite, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = registerInvite.idAuth;
            }
            return registerInvite.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.idAuth;
        }

        @NotNull
        public final RegisterInvite copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "idAuth");
            return new RegisterInvite(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RegisterInvite) && Intrinsics.areEqual((Object) this.idAuth, (Object) ((RegisterInvite) obj).idAuth);
        }

        @NotNull
        public final String getIdAuth() {
            return this.idAuth;
        }

        public int hashCode() {
            return this.idAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("RegisterInvite(idAuth=", this.idAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody;", "idAuth", "", "<init>", "(Ljava/lang/String;)V", "getIdAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregisterIdentity extends KeyServerBody {
        @NotNull
        private final String idAuth;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnregisterIdentity(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "idAuth");
            this.idAuth = str;
        }

        public static /* synthetic */ UnregisterIdentity copy$default(UnregisterIdentity unregisterIdentity, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = unregisterIdentity.idAuth;
            }
            return unregisterIdentity.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.idAuth;
        }

        @NotNull
        public final UnregisterIdentity copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "idAuth");
            return new UnregisterIdentity(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof UnregisterIdentity) && Intrinsics.areEqual((Object) this.idAuth, (Object) ((UnregisterIdentity) obj).idAuth);
        }

        @NotNull
        public final String getIdAuth() {
            return this.idAuth;
        }

        public int hashCode() {
            return this.idAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("UnregisterIdentity(idAuth=", this.idAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerBody;", "idAuth", "", "<init>", "(Ljava/lang/String;)V", "getIdAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregisterInvite extends KeyServerBody {
        @NotNull
        private final String idAuth;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnregisterInvite(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "idAuth");
            this.idAuth = str;
        }

        public static /* synthetic */ UnregisterInvite copy$default(UnregisterInvite unregisterInvite, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = unregisterInvite.idAuth;
            }
            return unregisterInvite.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.idAuth;
        }

        @NotNull
        public final UnregisterInvite copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "idAuth");
            return new UnregisterInvite(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof UnregisterInvite) && Intrinsics.areEqual((Object) this.idAuth, (Object) ((UnregisterInvite) obj).idAuth);
        }

        @NotNull
        public final String getIdAuth() {
            return this.idAuth;
        }

        public int hashCode() {
            return this.idAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("UnregisterInvite(idAuth=", this.idAuth, ")");
        }
    }

    public /* synthetic */ KeyServerBody(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KeyServerBody() {
    }
}
