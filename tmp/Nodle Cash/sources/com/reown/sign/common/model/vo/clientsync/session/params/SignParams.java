package com.reown.sign.common.model.vo.clientsync.session.params;

import androidx.compose.ui.autofill.a;
import androidx.constraintlayout.core.state.b;
import androidx.core.app.NotificationCompat;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SessionProposer;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.sign.common.model.vo.clientsync.common.PayloadParams;
import com.reown.sign.common.model.vo.clientsync.common.Requester;
import com.reown.sign.common.model.vo.clientsync.common.SessionParticipant;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\t\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\t\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "Lcom/reown/android/internal/common/model/params/CoreSignParams;", "<init>", "()V", "SessionProposeParams", "SessionAuthenticateParams", "SessionSettleParams", "SessionRequestParams", "EventParams", "UpdateNamespacesParams", "ExtendParams", "DeleteParams", "PingParams", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$ExtendParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$PingParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SignParams extends CoreSignParams {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "event", "Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionEventVO;", "chainId", "", "<init>", "(Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionEventVO;Ljava/lang/String;)V", "getEvent", "()Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionEventVO;", "getChainId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class EventParams extends SignParams {
        @NotNull
        private final String chainId;
        @NotNull
        private final SessionEventVO event;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public EventParams(@NotNull @Json(name = "event") SessionEventVO sessionEventVO, @NotNull @Json(name = "chainId") String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(sessionEventVO, NotificationCompat.CATEGORY_EVENT);
            Intrinsics.checkNotNullParameter(str, "chainId");
            this.event = sessionEventVO;
            this.chainId = str;
        }

        public static /* synthetic */ EventParams copy$default(EventParams eventParams, SessionEventVO sessionEventVO, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                sessionEventVO = eventParams.event;
            }
            if ((i3 & 2) != 0) {
                str = eventParams.chainId;
            }
            return eventParams.copy(sessionEventVO, str);
        }

        @NotNull
        public final SessionEventVO component1() {
            return this.event;
        }

        @NotNull
        public final String component2() {
            return this.chainId;
        }

        @NotNull
        public final EventParams copy(@NotNull @Json(name = "event") SessionEventVO sessionEventVO, @NotNull @Json(name = "chainId") String str) {
            Intrinsics.checkNotNullParameter(sessionEventVO, NotificationCompat.CATEGORY_EVENT);
            Intrinsics.checkNotNullParameter(str, "chainId");
            return new EventParams(sessionEventVO, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventParams)) {
                return false;
            }
            EventParams eventParams = (EventParams) obj;
            return Intrinsics.areEqual((Object) this.event, (Object) eventParams.event) && Intrinsics.areEqual((Object) this.chainId, (Object) eventParams.chainId);
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        @NotNull
        public final SessionEventVO getEvent() {
            return this.event;
        }

        public int hashCode() {
            return this.chainId.hashCode() + (this.event.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            SessionEventVO sessionEventVO = this.event;
            String str = this.chainId;
            return "EventParams(event=" + sessionEventVO + ", chainId=" + str + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$ExtendParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "expiry", "", "<init>", "(J)V", "getExpiry", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ExtendParams extends SignParams {
        private final long expiry;

        public ExtendParams(@Json(name = "expiry") long j2) {
            super((DefaultConstructorMarker) null);
            this.expiry = j2;
        }

        public static /* synthetic */ ExtendParams copy$default(ExtendParams extendParams, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = extendParams.expiry;
            }
            return extendParams.copy(j2);
        }

        public final long component1() {
            return this.expiry;
        }

        @NotNull
        public final ExtendParams copy(@Json(name = "expiry") long j2) {
            return new ExtendParams(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ExtendParams) && this.expiry == ((ExtendParams) obj).expiry;
        }

        public final long getExpiry() {
            return this.expiry;
        }

        public int hashCode() {
            return Long.hashCode(this.expiry);
        }

        @NotNull
        public String toString() {
            return a.j("ExtendParams(expiry=", this.expiry, ")");
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$PingParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PingParams extends SignParams {
        public PingParams() {
            super((DefaultConstructorMarker) null);
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J'\u0010\u001e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "requester", "Lcom/reown/sign/common/model/vo/clientsync/common/Requester;", "authPayload", "Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "expiryTimestamp", "", "<init>", "(Lcom/reown/sign/common/model/vo/clientsync/common/Requester;Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;J)V", "getRequester", "()Lcom/reown/sign/common/model/vo/clientsync/common/Requester;", "getAuthPayload", "()Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "getExpiryTimestamp", "()J", "metadataUrl", "", "getMetadataUrl", "()Ljava/lang/String;", "appLink", "getAppLink", "linkMode", "", "getLinkMode", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionAuthenticateParams extends SignParams {
        @Nullable
        private final String appLink;
        @NotNull
        private final PayloadParams authPayload;
        private final long expiryTimestamp;
        @Nullable
        private final Boolean linkMode;
        @NotNull
        private final String metadataUrl;
        @NotNull
        private final Requester requester;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionAuthenticateParams(@NotNull @Json(name = "requester") Requester requester2, @NotNull @Json(name = "authPayload") PayloadParams payloadParams, @Json(name = "expiryTimestamp") long j2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(requester2, "requester");
            Intrinsics.checkNotNullParameter(payloadParams, "authPayload");
            Boolean bool = null;
            this.requester = requester2;
            this.authPayload = payloadParams;
            this.expiryTimestamp = j2;
            this.metadataUrl = requester2.getMetadata().getUrl();
            Redirect redirect = requester2.getMetadata().getRedirect();
            this.appLink = redirect != null ? redirect.getUniversal() : null;
            Redirect redirect2 = requester2.getMetadata().getRedirect();
            this.linkMode = redirect2 != null ? Boolean.valueOf(redirect2.getLinkMode()) : bool;
        }

        public static /* synthetic */ SessionAuthenticateParams copy$default(SessionAuthenticateParams sessionAuthenticateParams, Requester requester2, PayloadParams payloadParams, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                requester2 = sessionAuthenticateParams.requester;
            }
            if ((i3 & 2) != 0) {
                payloadParams = sessionAuthenticateParams.authPayload;
            }
            if ((i3 & 4) != 0) {
                j2 = sessionAuthenticateParams.expiryTimestamp;
            }
            return sessionAuthenticateParams.copy(requester2, payloadParams, j2);
        }

        @NotNull
        public final Requester component1() {
            return this.requester;
        }

        @NotNull
        public final PayloadParams component2() {
            return this.authPayload;
        }

        public final long component3() {
            return this.expiryTimestamp;
        }

        @NotNull
        public final SessionAuthenticateParams copy(@NotNull @Json(name = "requester") Requester requester2, @NotNull @Json(name = "authPayload") PayloadParams payloadParams, @Json(name = "expiryTimestamp") long j2) {
            Intrinsics.checkNotNullParameter(requester2, "requester");
            Intrinsics.checkNotNullParameter(payloadParams, "authPayload");
            return new SessionAuthenticateParams(requester2, payloadParams, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionAuthenticateParams)) {
                return false;
            }
            SessionAuthenticateParams sessionAuthenticateParams = (SessionAuthenticateParams) obj;
            return Intrinsics.areEqual((Object) this.requester, (Object) sessionAuthenticateParams.requester) && Intrinsics.areEqual((Object) this.authPayload, (Object) sessionAuthenticateParams.authPayload) && this.expiryTimestamp == sessionAuthenticateParams.expiryTimestamp;
        }

        @Nullable
        public final String getAppLink() {
            return this.appLink;
        }

        @NotNull
        public final PayloadParams getAuthPayload() {
            return this.authPayload;
        }

        public final long getExpiryTimestamp() {
            return this.expiryTimestamp;
        }

        @Nullable
        public final Boolean getLinkMode() {
            return this.linkMode;
        }

        @NotNull
        public final String getMetadataUrl() {
            return this.metadataUrl;
        }

        @NotNull
        public final Requester getRequester() {
            return this.requester;
        }

        public int hashCode() {
            int hashCode = this.authPayload.hashCode();
            return Long.hashCode(this.expiryTimestamp) + ((hashCode + (this.requester.hashCode() * 31)) * 31);
        }

        @NotNull
        public String toString() {
            Requester requester2 = this.requester;
            PayloadParams payloadParams = this.authPayload;
            long j2 = this.expiryTimestamp;
            StringBuilder sb = new StringBuilder("SessionAuthenticateParams(requester=");
            sb.append(requester2);
            sb.append(", authPayload=");
            sb.append(payloadParams);
            sb.append(", expiryTimestamp=");
            return android.support.v4.media.session.a.k(j2, ")", sb);
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0014\b\u0001\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0016\b\u0001\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0016\b\u0001\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0017\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\u0017\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0001\u0010%\u001a\u00020\u00002\u0014\b\u0003\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0016\b\u0003\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0003\u0010\n\u001a\u00020\u000b2\u0016\b\u0003\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0003\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001c¨\u0006."}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "requiredNamespaces", "", "", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "optionalNamespaces", "relays", "", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "proposer", "Lcom/reown/android/internal/common/model/SessionProposer;", "properties", "scopedProperties", "expiryTimestamp", "", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Lcom/reown/android/internal/common/model/SessionProposer;Ljava/util/Map;Ljava/util/Map;Ljava/lang/Long;)V", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getRelays", "()Ljava/util/List;", "getProposer", "()Lcom/reown/android/internal/common/model/SessionProposer;", "getProperties", "getScopedProperties", "getExpiryTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Lcom/reown/android/internal/common/model/SessionProposer;Ljava/util/Map;Ljava/util/Map;Ljava/lang/Long;)Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionProposeParams extends SignParams {
        @Nullable
        private final Long expiryTimestamp;
        @Nullable
        private final Map<String, Namespace.Proposal> optionalNamespaces;
        @Nullable
        private final Map<String, String> properties;
        @NotNull
        private final SessionProposer proposer;
        @NotNull
        private final List<RelayProtocolOptions> relays;
        @NotNull
        private final Map<String, Namespace.Proposal> requiredNamespaces;
        @Nullable
        private final Map<String, String> scopedProperties;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionProposeParams(@NotNull @Json(name = "requiredNamespaces") Map<String, Namespace.Proposal> map, @Nullable @Json(name = "optionalNamespaces") Map<String, Namespace.Proposal> map2, @NotNull @Json(name = "relays") List<RelayProtocolOptions> list, @NotNull @Json(name = "proposer") SessionProposer sessionProposer, @Nullable @Json(name = "sessionProperties") Map<String, String> map3, @Nullable @Json(name = "scopedProperties") Map<String, String> map4, @Nullable @Json(name = "expiryTimestamp") Long l2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(list, "relays");
            Intrinsics.checkNotNullParameter(sessionProposer, "proposer");
            this.requiredNamespaces = map;
            this.optionalNamespaces = map2;
            this.relays = list;
            this.proposer = sessionProposer;
            this.properties = map3;
            this.scopedProperties = map4;
            this.expiryTimestamp = l2;
        }

        public static /* synthetic */ SessionProposeParams copy$default(SessionProposeParams sessionProposeParams, Map<String, Namespace.Proposal> map, Map<String, Namespace.Proposal> map2, List<RelayProtocolOptions> list, SessionProposer sessionProposer, Map<String, String> map3, Map<String, String> map4, Long l2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                map = sessionProposeParams.requiredNamespaces;
            }
            if ((i3 & 2) != 0) {
                map2 = sessionProposeParams.optionalNamespaces;
            }
            Map<String, Namespace.Proposal> map5 = map2;
            if ((i3 & 4) != 0) {
                list = sessionProposeParams.relays;
            }
            List<RelayProtocolOptions> list2 = list;
            if ((i3 & 8) != 0) {
                sessionProposer = sessionProposeParams.proposer;
            }
            SessionProposer sessionProposer2 = sessionProposer;
            if ((i3 & 16) != 0) {
                map3 = sessionProposeParams.properties;
            }
            Map<String, String> map6 = map3;
            if ((i3 & 32) != 0) {
                map4 = sessionProposeParams.scopedProperties;
            }
            Map<String, String> map7 = map4;
            if ((i3 & 64) != 0) {
                l2 = sessionProposeParams.expiryTimestamp;
            }
            return sessionProposeParams.copy(map, map5, list2, sessionProposer2, map6, map7, l2);
        }

        @NotNull
        public final Map<String, Namespace.Proposal> component1() {
            return this.requiredNamespaces;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> component2() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final List<RelayProtocolOptions> component3() {
            return this.relays;
        }

        @NotNull
        public final SessionProposer component4() {
            return this.proposer;
        }

        @Nullable
        public final Map<String, String> component5() {
            return this.properties;
        }

        @Nullable
        public final Map<String, String> component6() {
            return this.scopedProperties;
        }

        @Nullable
        public final Long component7() {
            return this.expiryTimestamp;
        }

        @NotNull
        public final SessionProposeParams copy(@NotNull @Json(name = "requiredNamespaces") Map<String, Namespace.Proposal> map, @Nullable @Json(name = "optionalNamespaces") Map<String, Namespace.Proposal> map2, @NotNull @Json(name = "relays") List<RelayProtocolOptions> list, @NotNull @Json(name = "proposer") SessionProposer sessionProposer, @Nullable @Json(name = "sessionProperties") Map<String, String> map3, @Nullable @Json(name = "scopedProperties") Map<String, String> map4, @Nullable @Json(name = "expiryTimestamp") Long l2) {
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(list, "relays");
            Intrinsics.checkNotNullParameter(sessionProposer, "proposer");
            return new SessionProposeParams(map, map2, list, sessionProposer, map3, map4, l2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionProposeParams)) {
                return false;
            }
            SessionProposeParams sessionProposeParams = (SessionProposeParams) obj;
            return Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposeParams.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposeParams.optionalNamespaces) && Intrinsics.areEqual((Object) this.relays, (Object) sessionProposeParams.relays) && Intrinsics.areEqual((Object) this.proposer, (Object) sessionProposeParams.proposer) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposeParams.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionProposeParams.scopedProperties) && Intrinsics.areEqual((Object) this.expiryTimestamp, (Object) sessionProposeParams.expiryTimestamp);
        }

        @Nullable
        public final Long getExpiryTimestamp() {
            return this.expiryTimestamp;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
            return this.optionalNamespaces;
        }

        @Nullable
        public final Map<String, String> getProperties() {
            return this.properties;
        }

        @NotNull
        public final SessionProposer getProposer() {
            return this.proposer;
        }

        @NotNull
        public final List<RelayProtocolOptions> getRelays() {
            return this.relays;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
            return this.requiredNamespaces;
        }

        @Nullable
        public final Map<String, String> getScopedProperties() {
            return this.scopedProperties;
        }

        public int hashCode() {
            int hashCode = this.requiredNamespaces.hashCode() * 31;
            Map<String, Namespace.Proposal> map = this.optionalNamespaces;
            int i3 = 0;
            int hashCode2 = (this.proposer.hashCode() + androidx.compose.animation.core.a.j(this.relays, (hashCode + (map == null ? 0 : map.hashCode())) * 31, 31)) * 31;
            Map<String, String> map2 = this.properties;
            int hashCode3 = (hashCode2 + (map2 == null ? 0 : map2.hashCode())) * 31;
            Map<String, String> map3 = this.scopedProperties;
            int hashCode4 = (hashCode3 + (map3 == null ? 0 : map3.hashCode())) * 31;
            Long l2 = this.expiryTimestamp;
            if (l2 != null) {
                i3 = l2.hashCode();
            }
            return hashCode4 + i3;
        }

        @NotNull
        public String toString() {
            Map<String, Namespace.Proposal> map = this.requiredNamespaces;
            Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
            List<RelayProtocolOptions> list = this.relays;
            SessionProposer sessionProposer = this.proposer;
            Map<String, String> map3 = this.properties;
            Map<String, String> map4 = this.scopedProperties;
            Long l2 = this.expiryTimestamp;
            return "SessionProposeParams(requiredNamespaces=" + map + ", optionalNamespaces=" + map2 + ", relays=" + list + ", proposer=" + sessionProposer + ", properties=" + map3 + ", scopedProperties=" + map4 + ", expiryTimestamp=" + l2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "request", "Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;", "chainId", "", "<init>", "(Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;Ljava/lang/String;)V", "getRequest", "()Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;", "getChainId", "()Ljava/lang/String;", "expiry", "", "getExpiry", "()Ljava/lang/Long;", "Ljava/lang/Long;", "rpcMethod", "getRpcMethod", "rpcParams", "getRpcParams", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionRequestParams extends SignParams {
        @NotNull
        private final String chainId;
        @Nullable
        private final Long expiry;
        @NotNull
        private final SessionRequestVO request;
        @NotNull
        private final String rpcMethod;
        @NotNull
        private final String rpcParams;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionRequestParams(@NotNull @Json(name = "request") SessionRequestVO sessionRequestVO, @NotNull @Json(name = "chainId") String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(sessionRequestVO, "request");
            Intrinsics.checkNotNullParameter(str, "chainId");
            this.request = sessionRequestVO;
            this.chainId = str;
            this.expiry = sessionRequestVO.getExpiryTimestamp();
            this.rpcMethod = sessionRequestVO.getMethod();
            this.rpcParams = sessionRequestVO.getParams();
        }

        public static /* synthetic */ SessionRequestParams copy$default(SessionRequestParams sessionRequestParams, SessionRequestVO sessionRequestVO, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                sessionRequestVO = sessionRequestParams.request;
            }
            if ((i3 & 2) != 0) {
                str = sessionRequestParams.chainId;
            }
            return sessionRequestParams.copy(sessionRequestVO, str);
        }

        @NotNull
        public final SessionRequestVO component1() {
            return this.request;
        }

        @NotNull
        public final String component2() {
            return this.chainId;
        }

        @NotNull
        public final SessionRequestParams copy(@NotNull @Json(name = "request") SessionRequestVO sessionRequestVO, @NotNull @Json(name = "chainId") String str) {
            Intrinsics.checkNotNullParameter(sessionRequestVO, "request");
            Intrinsics.checkNotNullParameter(str, "chainId");
            return new SessionRequestParams(sessionRequestVO, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionRequestParams)) {
                return false;
            }
            SessionRequestParams sessionRequestParams = (SessionRequestParams) obj;
            return Intrinsics.areEqual((Object) this.request, (Object) sessionRequestParams.request) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequestParams.chainId);
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        @Nullable
        public final Long getExpiry() {
            return this.expiry;
        }

        @NotNull
        public final SessionRequestVO getRequest() {
            return this.request;
        }

        @NotNull
        public final String getRpcMethod() {
            return this.rpcMethod;
        }

        @NotNull
        public final String getRpcParams() {
            return this.rpcParams;
        }

        public int hashCode() {
            return this.chainId.hashCode() + (this.request.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            SessionRequestVO sessionRequestVO = this.request;
            String str = this.chainId;
            return "SessionRequestParams(request=" + sessionRequestVO + ", chainId=" + str + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0001\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0016\b\u0001\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0016\b\u0001\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003J\u0017\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0017\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003Jm\u0010 \u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u0014\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0003\u0010\n\u001a\u00020\u000b2\u0016\b\u0003\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0016\b\u0003\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006("}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "relay", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "controller", "Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;", "namespaces", "", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "expiry", "", "properties", "scopedProperties", "<init>", "(Lcom/reown/android/internal/common/model/RelayProtocolOptions;Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;Ljava/util/Map;JLjava/util/Map;Ljava/util/Map;)V", "getRelay", "()Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "getController", "()Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;", "getNamespaces", "()Ljava/util/Map;", "getExpiry", "()J", "getProperties", "getScopedProperties", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionSettleParams extends SignParams {
        @NotNull
        private final SessionParticipant controller;
        private final long expiry;
        @NotNull
        private final Map<String, Namespace.Session> namespaces;
        @Nullable
        private final Map<String, String> properties;
        @NotNull
        private final RelayProtocolOptions relay;
        @Nullable
        private final Map<String, String> scopedProperties;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionSettleParams(@NotNull @Json(name = "relay") RelayProtocolOptions relayProtocolOptions, @NotNull @Json(name = "controller") SessionParticipant sessionParticipant, @NotNull @Json(name = "namespaces") Map<String, Namespace.Session> map, @Json(name = "expiry") long j2, @Nullable @Json(name = "sessionProperties") Map<String, String> map2, @Nullable @Json(name = "scopedProperties") Map<String, String> map3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
            Intrinsics.checkNotNullParameter(sessionParticipant, "controller");
            Intrinsics.checkNotNullParameter(map, "namespaces");
            this.relay = relayProtocolOptions;
            this.controller = sessionParticipant;
            this.namespaces = map;
            this.expiry = j2;
            this.properties = map2;
            this.scopedProperties = map3;
        }

        public static /* synthetic */ SessionSettleParams copy$default(SessionSettleParams sessionSettleParams, RelayProtocolOptions relayProtocolOptions, SessionParticipant sessionParticipant, Map<String, Namespace.Session> map, long j2, Map<String, String> map2, Map<String, String> map3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                relayProtocolOptions = sessionSettleParams.relay;
            }
            if ((i3 & 2) != 0) {
                sessionParticipant = sessionSettleParams.controller;
            }
            SessionParticipant sessionParticipant2 = sessionParticipant;
            if ((i3 & 4) != 0) {
                map = sessionSettleParams.namespaces;
            }
            Map<String, Namespace.Session> map4 = map;
            if ((i3 & 8) != 0) {
                j2 = sessionSettleParams.expiry;
            }
            long j3 = j2;
            if ((i3 & 16) != 0) {
                map2 = sessionSettleParams.properties;
            }
            Map<String, String> map5 = map2;
            if ((i3 & 32) != 0) {
                map3 = sessionSettleParams.scopedProperties;
            }
            return sessionSettleParams.copy(relayProtocolOptions, sessionParticipant2, map4, j3, map5, map3);
        }

        @NotNull
        public final RelayProtocolOptions component1() {
            return this.relay;
        }

        @NotNull
        public final SessionParticipant component2() {
            return this.controller;
        }

        @NotNull
        public final Map<String, Namespace.Session> component3() {
            return this.namespaces;
        }

        public final long component4() {
            return this.expiry;
        }

        @Nullable
        public final Map<String, String> component5() {
            return this.properties;
        }

        @Nullable
        public final Map<String, String> component6() {
            return this.scopedProperties;
        }

        @NotNull
        public final SessionSettleParams copy(@NotNull @Json(name = "relay") RelayProtocolOptions relayProtocolOptions, @NotNull @Json(name = "controller") SessionParticipant sessionParticipant, @NotNull @Json(name = "namespaces") Map<String, Namespace.Session> map, @Json(name = "expiry") long j2, @Nullable @Json(name = "sessionProperties") Map<String, String> map2, @Nullable @Json(name = "scopedProperties") Map<String, String> map3) {
            Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
            Intrinsics.checkNotNullParameter(sessionParticipant, "controller");
            Intrinsics.checkNotNullParameter(map, "namespaces");
            return new SessionSettleParams(relayProtocolOptions, sessionParticipant, map, j2, map2, map3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionSettleParams)) {
                return false;
            }
            SessionSettleParams sessionSettleParams = (SessionSettleParams) obj;
            return Intrinsics.areEqual((Object) this.relay, (Object) sessionSettleParams.relay) && Intrinsics.areEqual((Object) this.controller, (Object) sessionSettleParams.controller) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionSettleParams.namespaces) && this.expiry == sessionSettleParams.expiry && Intrinsics.areEqual((Object) this.properties, (Object) sessionSettleParams.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionSettleParams.scopedProperties);
        }

        @NotNull
        public final SessionParticipant getController() {
            return this.controller;
        }

        public final long getExpiry() {
            return this.expiry;
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }

        @Nullable
        public final Map<String, String> getProperties() {
            return this.properties;
        }

        @NotNull
        public final RelayProtocolOptions getRelay() {
            return this.relay;
        }

        @Nullable
        public final Map<String, String> getScopedProperties() {
            return this.scopedProperties;
        }

        public int hashCode() {
            int D2 = androidx.compose.animation.core.a.D(this.expiry, b.d(this.namespaces, (this.controller.hashCode() + (this.relay.hashCode() * 31)) * 31, 31), 31);
            Map<String, String> map = this.properties;
            int i3 = 0;
            int hashCode = (D2 + (map == null ? 0 : map.hashCode())) * 31;
            Map<String, String> map2 = this.scopedProperties;
            if (map2 != null) {
                i3 = map2.hashCode();
            }
            return hashCode + i3;
        }

        @NotNull
        public String toString() {
            RelayProtocolOptions relayProtocolOptions = this.relay;
            SessionParticipant sessionParticipant = this.controller;
            Map<String, Namespace.Session> map = this.namespaces;
            long j2 = this.expiry;
            Map<String, String> map2 = this.properties;
            Map<String, String> map3 = this.scopedProperties;
            return "SessionSettleParams(relay=" + relayProtocolOptions + ", controller=" + sessionParticipant + ", namespaces=" + map + ", expiry=" + j2 + ", properties=" + map2 + ", scopedProperties=" + map3 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\b\u0001\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "namespaces", "", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "<init>", "(Ljava/util/Map;)V", "getNamespaces", "()Ljava/util/Map;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateNamespacesParams extends SignParams {
        @NotNull
        private final Map<String, Namespace.Session> namespaces;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UpdateNamespacesParams(@NotNull @Json(name = "namespaces") Map<String, Namespace.Session> map) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "namespaces");
            this.namespaces = map;
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }
    }

    public /* synthetic */ SignParams(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DeleteParams extends SignParams {
        private final int code;
        @NotNull
        private final String message;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ DeleteParams(int i3, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? Intrinsics.checkNotNullParameter(IntCompanionObject.INSTANCE, "<this>") : i3, str);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DeleteParams(@Json(name = "code") int i3, @NotNull @Json(name = "message") String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            this.code = i3;
            this.message = str;
        }
    }

    private SignParams() {
    }
}
