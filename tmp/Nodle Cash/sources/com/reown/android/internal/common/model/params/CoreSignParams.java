package com.reown.android.internal.common.model.params;

import com.reown.android.internal.common.model.Participant;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreSignParams;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "<init>", "()V", "ApprovalParams", "SessionAuthenticateApproveParams", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class CoreSignParams implements ClientParams {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "Lcom/reown/android/internal/common/model/params/CoreSignParams;", "relay", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "responderPublicKey", "", "<init>", "(Lcom/reown/android/internal/common/model/RelayProtocolOptions;Ljava/lang/String;)V", "getRelay", "()Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "getResponderPublicKey", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ApprovalParams extends CoreSignParams {
        @NotNull
        private final RelayProtocolOptions relay;
        @NotNull
        private final String responderPublicKey;

        public ApprovalParams(@NotNull @Json(name = "relay") RelayProtocolOptions relayProtocolOptions, @NotNull @Json(name = "responderPublicKey") String str) {
            Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
            Intrinsics.checkNotNullParameter(str, "responderPublicKey");
            this.relay = relayProtocolOptions;
            this.responderPublicKey = str;
        }

        public static /* synthetic */ ApprovalParams copy$default(ApprovalParams approvalParams, RelayProtocolOptions relayProtocolOptions, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                relayProtocolOptions = approvalParams.relay;
            }
            if ((i3 & 2) != 0) {
                str = approvalParams.responderPublicKey;
            }
            return approvalParams.copy(relayProtocolOptions, str);
        }

        @NotNull
        public final RelayProtocolOptions component1() {
            return this.relay;
        }

        @NotNull
        public final String component2() {
            return this.responderPublicKey;
        }

        @NotNull
        public final ApprovalParams copy(@NotNull @Json(name = "relay") RelayProtocolOptions relayProtocolOptions, @NotNull @Json(name = "responderPublicKey") String str) {
            Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
            Intrinsics.checkNotNullParameter(str, "responderPublicKey");
            return new ApprovalParams(relayProtocolOptions, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ApprovalParams)) {
                return false;
            }
            ApprovalParams approvalParams = (ApprovalParams) obj;
            return Intrinsics.areEqual((Object) this.relay, (Object) approvalParams.relay) && Intrinsics.areEqual((Object) this.responderPublicKey, (Object) approvalParams.responderPublicKey);
        }

        @NotNull
        public final RelayProtocolOptions getRelay() {
            return this.relay;
        }

        @NotNull
        public final String getResponderPublicKey() {
            return this.responderPublicKey;
        }

        public int hashCode() {
            return this.responderPublicKey.hashCode() + (this.relay.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            RelayProtocolOptions relayProtocolOptions = this.relay;
            String str = this.responderPublicKey;
            return "ApprovalParams(relay=" + relayProtocolOptions + ", responderPublicKey=" + str + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreSignParams$SessionAuthenticateApproveParams;", "Lcom/reown/android/internal/common/model/params/CoreSignParams;", "responder", "Lcom/reown/android/internal/common/model/Participant;", "cacaos", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "<init>", "(Lcom/reown/android/internal/common/model/Participant;Ljava/util/List;)V", "getResponder", "()Lcom/reown/android/internal/common/model/Participant;", "getCacaos", "()Ljava/util/List;", "linkMode", "", "getLinkMode", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "appLink", "", "getAppLink", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionAuthenticateApproveParams extends CoreSignParams {
        @Nullable
        private final String appLink;
        @NotNull
        private final List<Cacao> cacaos;
        @Nullable
        private final Boolean linkMode;
        @NotNull
        private final Participant responder;

        public SessionAuthenticateApproveParams(@NotNull @Json(name = "responder") Participant participant, @NotNull @Json(name = "cacaos") List<Cacao> list) {
            Intrinsics.checkNotNullParameter(participant, "responder");
            Intrinsics.checkNotNullParameter(list, "cacaos");
            this.responder = participant;
            this.cacaos = list;
            Redirect redirect = participant.getMetadata().getRedirect();
            String str = null;
            this.linkMode = redirect != null ? Boolean.valueOf(redirect.getLinkMode()) : null;
            Redirect redirect2 = participant.getMetadata().getRedirect();
            this.appLink = redirect2 != null ? redirect2.getUniversal() : str;
        }

        public static /* synthetic */ SessionAuthenticateApproveParams copy$default(SessionAuthenticateApproveParams sessionAuthenticateApproveParams, Participant participant, List<Cacao> list, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                participant = sessionAuthenticateApproveParams.responder;
            }
            if ((i3 & 2) != 0) {
                list = sessionAuthenticateApproveParams.cacaos;
            }
            return sessionAuthenticateApproveParams.copy(participant, list);
        }

        @NotNull
        public final Participant component1() {
            return this.responder;
        }

        @NotNull
        public final List<Cacao> component2() {
            return this.cacaos;
        }

        @NotNull
        public final SessionAuthenticateApproveParams copy(@NotNull @Json(name = "responder") Participant participant, @NotNull @Json(name = "cacaos") List<Cacao> list) {
            Intrinsics.checkNotNullParameter(participant, "responder");
            Intrinsics.checkNotNullParameter(list, "cacaos");
            return new SessionAuthenticateApproveParams(participant, list);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionAuthenticateApproveParams)) {
                return false;
            }
            SessionAuthenticateApproveParams sessionAuthenticateApproveParams = (SessionAuthenticateApproveParams) obj;
            return Intrinsics.areEqual((Object) this.responder, (Object) sessionAuthenticateApproveParams.responder) && Intrinsics.areEqual((Object) this.cacaos, (Object) sessionAuthenticateApproveParams.cacaos);
        }

        @Nullable
        public final String getAppLink() {
            return this.appLink;
        }

        @NotNull
        public final List<Cacao> getCacaos() {
            return this.cacaos;
        }

        @Nullable
        public final Boolean getLinkMode() {
            return this.linkMode;
        }

        @NotNull
        public final Participant getResponder() {
            return this.responder;
        }

        public int hashCode() {
            return this.cacaos.hashCode() + (this.responder.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            Participant participant = this.responder;
            List<Cacao> list = this.cacaos;
            return "SessionAuthenticateApproveParams(responder=" + participant + ", cacaos=" + list + ")";
        }
    }
}
