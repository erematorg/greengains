package com.reown.sign.engine.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.Sequence;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.squareup.moshi.JsonClass;
import java.net.URI;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u001c\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u001d !\"#$%&'()*+,-./0123456789:;<¨\u0006="}, d2 = {"Lcom/reown/sign/engine/model/EngineDO;", "", "<init>", "()V", "WalletConnectUri", "SessionProposalEvent", "SessionRequestEvent", "SessionAuthenticateEvent", "PayloadParams", "Authenticate", "Participant", "SessionProposal", "ExpiredProposal", "ExpiredRequest", "VerifyContext", "Namespace", "SessionRequest", "SessionPayloadResponse", "SessionDelete", "SessionEvent", "SessionAuthenticateResponse", "SettledSessionResponse", "SessionUpdateNamespacesResponse", "SessionRejected", "SessionApproved", "PairingSettle", "SessionUpdateNamespaces", "SessionExtend", "Session", "Event", "JsonRpcResponse", "Request", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "Lcom/reown/sign/engine/model/EngineDO$Event;", "Lcom/reown/sign/engine/model/EngineDO$ExpiredProposal;", "Lcom/reown/sign/engine/model/EngineDO$ExpiredRequest;", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "Lcom/reown/sign/engine/model/EngineDO$Namespace;", "Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "Lcom/reown/sign/engine/model/EngineDO$Participant;", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "Lcom/reown/sign/engine/model/EngineDO$Request;", "Lcom/reown/sign/engine/model/EngineDO$Session;", "Lcom/reown/sign/engine/model/EngineDO$SessionApproved;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateEvent;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse;", "Lcom/reown/sign/engine/model/EngineDO$SessionDelete;", "Lcom/reown/sign/engine/model/EngineDO$SessionEvent;", "Lcom/reown/sign/engine/model/EngineDO$SessionExtend;", "Lcom/reown/sign/engine/model/EngineDO$SessionPayloadResponse;", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "Lcom/reown/sign/engine/model/EngineDO$SessionProposalEvent;", "Lcom/reown/sign/engine/model/EngineDO$SessionRejected;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest$JSONRPCRequest;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequestEvent;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespaces;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse;", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse;", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "Lcom/reown/sign/engine/model/EngineDO$WalletConnectUri;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class EngineDO {

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b%\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u00101\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u0010%J´\u0001\u00104\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u00105J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u000109HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\t\u0010<\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\"R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%¨\u0006="}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "Lcom/reown/sign/engine/model/EngineDO;", "pairingTopic", "", "chains", "", "domain", "nonce", "aud", "type", "nbf", "exp", "statement", "requestId", "resources", "methods", "expiry", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getPairingTopic", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getDomain", "getNonce", "getAud", "getType", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "setResources", "(Ljava/util/List;)V", "getMethods", "getExpiry", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Authenticate extends EngineDO {
        @NotNull
        private final String aud;
        @NotNull
        private final List<String> chains;
        @NotNull
        private final String domain;
        @Nullable
        private final String exp;
        @Nullable
        private final Long expiry;
        @Nullable
        private final List<String> methods;
        @Nullable
        private final String nbf;
        @NotNull
        private final String nonce;
        @Nullable
        private final String pairingTopic;
        @Nullable
        private final String requestId;
        @Nullable
        private List<String> resources;
        @Nullable
        private final String statement;
        @Nullable
        private final String type;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Authenticate(String str, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list2, List list3, Long l2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : str, list, str2, str3, str4, str5, str6, str7, str8, str9, list2, list3, l2);
        }

        public static /* synthetic */ Authenticate copy$default(Authenticate authenticate, String str, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list2, List list3, Long l2, int i3, Object obj) {
            Authenticate authenticate2 = authenticate;
            int i4 = i3;
            return authenticate.copy((i4 & 1) != 0 ? authenticate2.pairingTopic : str, (i4 & 2) != 0 ? authenticate2.chains : list, (i4 & 4) != 0 ? authenticate2.domain : str2, (i4 & 8) != 0 ? authenticate2.nonce : str3, (i4 & 16) != 0 ? authenticate2.aud : str4, (i4 & 32) != 0 ? authenticate2.type : str5, (i4 & 64) != 0 ? authenticate2.nbf : str6, (i4 & 128) != 0 ? authenticate2.exp : str7, (i4 & 256) != 0 ? authenticate2.statement : str8, (i4 & 512) != 0 ? authenticate2.requestId : str9, (i4 & 1024) != 0 ? authenticate2.resources : list2, (i4 & 2048) != 0 ? authenticate2.methods : list3, (i4 & 4096) != 0 ? authenticate2.expiry : l2);
        }

        @Nullable
        public final String component1() {
            return this.pairingTopic;
        }

        @Nullable
        public final String component10() {
            return this.requestId;
        }

        @Nullable
        public final List<String> component11() {
            return this.resources;
        }

        @Nullable
        public final List<String> component12() {
            return this.methods;
        }

        @Nullable
        public final Long component13() {
            return this.expiry;
        }

        @NotNull
        public final List<String> component2() {
            return this.chains;
        }

        @NotNull
        public final String component3() {
            return this.domain;
        }

        @NotNull
        public final String component4() {
            return this.nonce;
        }

        @NotNull
        public final String component5() {
            return this.aud;
        }

        @Nullable
        public final String component6() {
            return this.type;
        }

        @Nullable
        public final String component7() {
            return this.nbf;
        }

        @Nullable
        public final String component8() {
            return this.exp;
        }

        @Nullable
        public final String component9() {
            return this.statement;
        }

        @NotNull
        public final Authenticate copy(@Nullable String str, @NotNull List<String> list, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Long l2) {
            List<String> list4 = list;
            Intrinsics.checkNotNullParameter(list4, "chains");
            String str10 = str2;
            Intrinsics.checkNotNullParameter(str10, "domain");
            String str11 = str3;
            Intrinsics.checkNotNullParameter(str11, "nonce");
            String str12 = str4;
            Intrinsics.checkNotNullParameter(str12, "aud");
            return new Authenticate(str, list4, str10, str11, str12, str5, str6, str7, str8, str9, list2, list3, l2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Authenticate)) {
                return false;
            }
            Authenticate authenticate = (Authenticate) obj;
            return Intrinsics.areEqual((Object) this.pairingTopic, (Object) authenticate.pairingTopic) && Intrinsics.areEqual((Object) this.chains, (Object) authenticate.chains) && Intrinsics.areEqual((Object) this.domain, (Object) authenticate.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) authenticate.nonce) && Intrinsics.areEqual((Object) this.aud, (Object) authenticate.aud) && Intrinsics.areEqual((Object) this.type, (Object) authenticate.type) && Intrinsics.areEqual((Object) this.nbf, (Object) authenticate.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) authenticate.exp) && Intrinsics.areEqual((Object) this.statement, (Object) authenticate.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) authenticate.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) authenticate.resources) && Intrinsics.areEqual((Object) this.methods, (Object) authenticate.methods) && Intrinsics.areEqual((Object) this.expiry, (Object) authenticate.expiry);
        }

        @NotNull
        public final String getAud() {
            return this.aud;
        }

        @NotNull
        public final List<String> getChains() {
            return this.chains;
        }

        @NotNull
        public final String getDomain() {
            return this.domain;
        }

        @Nullable
        public final String getExp() {
            return this.exp;
        }

        @Nullable
        public final Long getExpiry() {
            return this.expiry;
        }

        @Nullable
        public final List<String> getMethods() {
            return this.methods;
        }

        @Nullable
        public final String getNbf() {
            return this.nbf;
        }

        @NotNull
        public final String getNonce() {
            return this.nonce;
        }

        @Nullable
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        @Nullable
        public final List<String> getResources() {
            return this.resources;
        }

        @Nullable
        public final String getStatement() {
            return this.statement;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            String str = this.pairingTopic;
            int i3 = 0;
            int i4 = a.i(this.aud, a.i(this.nonce, a.i(this.domain, a.j(this.chains, (str == null ? 0 : str.hashCode()) * 31, 31), 31), 31), 31);
            String str2 = this.type;
            int hashCode = (i4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.nbf;
            int hashCode2 = (hashCode + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.exp;
            int hashCode3 = (hashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.statement;
            int hashCode4 = (hashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.requestId;
            int hashCode5 = (hashCode4 + (str6 == null ? 0 : str6.hashCode())) * 31;
            List<String> list = this.resources;
            int hashCode6 = (hashCode5 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.methods;
            int hashCode7 = (hashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
            Long l2 = this.expiry;
            if (l2 != null) {
                i3 = l2.hashCode();
            }
            return hashCode7 + i3;
        }

        public final void setResources(@Nullable List<String> list) {
            this.resources = list;
        }

        @NotNull
        public String toString() {
            String str = this.pairingTopic;
            List<String> list = this.chains;
            String str2 = this.domain;
            String str3 = this.nonce;
            String str4 = this.aud;
            String str5 = this.type;
            String str6 = this.nbf;
            String str7 = this.exp;
            String str8 = this.statement;
            String str9 = this.requestId;
            List<String> list2 = this.resources;
            List<String> list3 = this.methods;
            Long l2 = this.expiry;
            StringBuilder sb = new StringBuilder("Authenticate(pairingTopic=");
            sb.append(str);
            sb.append(", chains=");
            sb.append(list);
            sb.append(", domain=");
            b.w(sb, str2, ", nonce=", str3, ", aud=");
            b.w(sb, str4, ", type=", str5, ", nbf=");
            b.w(sb, str6, ", exp=", str7, ", statement=");
            b.w(sb, str8, ", requestId=", str9, ", resources=");
            sb.append(list2);
            sb.append(", methods=");
            sb.append(list3);
            sb.append(", expiry=");
            sb.append(l2);
            sb.append(")");
            return sb.toString();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Authenticate(@Nullable String str, @NotNull List<String> list, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Long l2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "chains");
            Intrinsics.checkNotNullParameter(str2, "domain");
            Intrinsics.checkNotNullParameter(str3, "nonce");
            Intrinsics.checkNotNullParameter(str4, "aud");
            this.pairingTopic = str;
            this.chains = list;
            this.domain = str2;
            this.nonce = str3;
            this.aud = str4;
            this.type = str5;
            this.nbf = str6;
            this.exp = str7;
            this.statement = str8;
            this.requestId = str9;
            this.resources = list2;
            this.methods = list3;
            this.expiry = l2;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Event;", "Lcom/reown/sign/engine/model/EngineDO;", "name", "", "data", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getData", "getChainId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Event extends EngineDO {
        @NotNull
        private final String chainId;
        @NotNull
        private final String data;
        @NotNull
        private final String name;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Event(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "data");
            Intrinsics.checkNotNullParameter(str3, "chainId");
            this.name = str;
            this.data = str2;
            this.chainId = str3;
        }

        public static /* synthetic */ Event copy$default(Event event, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = event.name;
            }
            if ((i3 & 2) != 0) {
                str2 = event.data;
            }
            if ((i3 & 4) != 0) {
                str3 = event.chainId;
            }
            return event.copy(str, str2, str3);
        }

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final String component2() {
            return this.data;
        }

        @NotNull
        public final String component3() {
            return this.chainId;
        }

        @NotNull
        public final Event copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "data");
            Intrinsics.checkNotNullParameter(str3, "chainId");
            return new Event(str, str2, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Event)) {
                return false;
            }
            Event event = (Event) obj;
            return Intrinsics.areEqual((Object) this.name, (Object) event.name) && Intrinsics.areEqual((Object) this.data, (Object) event.data) && Intrinsics.areEqual((Object) this.chainId, (Object) event.chainId);
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        @NotNull
        public final String getData() {
            return this.data;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return this.chainId.hashCode() + a.i(this.data, this.name.hashCode() * 31, 31);
        }

        @NotNull
        public String toString() {
            String str = this.name;
            String str2 = this.data;
            return A.a.n(C0118y.l("Event(name=", str, ", data=", str2, ", chainId="), this.chainId, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$ExpiredProposal;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "pairingTopic", "", "proposerPublicKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPairingTopic", "()Ljava/lang/String;", "getProposerPublicKey", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ExpiredProposal extends EngineDO implements EngineEvent {
        @NotNull
        private final String pairingTopic;
        @NotNull
        private final String proposerPublicKey;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ExpiredProposal(@NotNull String str, @NotNull String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
            this.pairingTopic = str;
            this.proposerPublicKey = str2;
        }

        public static /* synthetic */ ExpiredProposal copy$default(ExpiredProposal expiredProposal, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = expiredProposal.pairingTopic;
            }
            if ((i3 & 2) != 0) {
                str2 = expiredProposal.proposerPublicKey;
            }
            return expiredProposal.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.pairingTopic;
        }

        @NotNull
        public final String component2() {
            return this.proposerPublicKey;
        }

        @NotNull
        public final ExpiredProposal copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
            return new ExpiredProposal(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ExpiredProposal)) {
                return false;
            }
            ExpiredProposal expiredProposal = (ExpiredProposal) obj;
            return Intrinsics.areEqual((Object) this.pairingTopic, (Object) expiredProposal.pairingTopic) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) expiredProposal.proposerPublicKey);
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @NotNull
        public final String getProposerPublicKey() {
            return this.proposerPublicKey;
        }

        public int hashCode() {
            return this.proposerPublicKey.hashCode() + (this.pairingTopic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("ExpiredProposal(pairingTopic=", this.pairingTopic, ", proposerPublicKey=", this.proposerPublicKey, ")");
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$ExpiredRequest;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "id", "", "<init>", "(Ljava/lang/String;J)V", "getTopic", "()Ljava/lang/String;", "getId", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ExpiredRequest extends EngineDO implements EngineEvent {
        private final long id;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ExpiredRequest(@NotNull String str, long j2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            this.topic = str;
            this.id = j2;
        }

        public static /* synthetic */ ExpiredRequest copy$default(ExpiredRequest expiredRequest, String str, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = expiredRequest.topic;
            }
            if ((i3 & 2) != 0) {
                j2 = expiredRequest.id;
            }
            return expiredRequest.copy(str, j2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        public final long component2() {
            return this.id;
        }

        @NotNull
        public final ExpiredRequest copy(@NotNull String str, long j2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            return new ExpiredRequest(str, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ExpiredRequest)) {
                return false;
            }
            ExpiredRequest expiredRequest = (ExpiredRequest) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) expiredRequest.topic) && this.id == expiredRequest.id;
        }

        public final long getId() {
            return this.id;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return Long.hashCode(this.id) + (this.topic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(this.id, "ExpiredRequest(topic=", this.topic, ", id=");
            i3.append(")");
            return i3.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "Lcom/reown/sign/engine/model/EngineDO;", "<init>", "()V", "id", "", "getId", "()J", "JsonRpcResult", "JsonRpcError", "Error", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcResult;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class JsonRpcResponse extends EngineDO {

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$Error;", "", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error {
            private final int code;
            @NotNull
            private final String message;

            public Error(int i3, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.code = i3;
                this.message = str;
            }

            public static /* synthetic */ Error copy$default(Error error, int i3, String str, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    i3 = error.code;
                }
                if ((i4 & 2) != 0) {
                    str = error.message;
                }
                return error.copy(i3, str);
            }

            public final int component1() {
                return this.code;
            }

            @NotNull
            public final String component2() {
                return this.message;
            }

            @NotNull
            public final Error copy(int i3, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new Error(i3, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Error)) {
                    return false;
                }
                Error error = (Error) obj;
                return this.code == error.code && Intrinsics.areEqual((Object) this.message, (Object) error.message);
            }

            public final int getCode() {
                return this.code;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode() + (Integer.hashCode(this.code) * 31);
            }

            @NotNull
            public String toString() {
                int i3 = this.code;
                String str = this.message;
                return "Error(code=" + i3 + ", message=" + str + ")";
            }
        }

        public /* synthetic */ JsonRpcResponse(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract long getId();

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "id", "", "jsonrpc", "", "error", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$Error;", "<init>", "(JLjava/lang/String;Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$Error;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$Error;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class JsonRpcError extends JsonRpcResponse {
            @NotNull
            private final Error error;
            private final long id;
            @NotNull
            private final String jsonrpc;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ JsonRpcError(long j2, String str, Error error2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(j2, (i3 & 2) != 0 ? "2.0" : str, error2);
            }

            public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, long j2, String str, Error error2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = jsonRpcError.id;
                }
                if ((i3 & 2) != 0) {
                    str = jsonRpcError.jsonrpc;
                }
                if ((i3 & 4) != 0) {
                    error2 = jsonRpcError.error;
                }
                return jsonRpcError.copy(j2, str, error2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final Error component3() {
                return this.error;
            }

            @NotNull
            public final JsonRpcError copy(long j2, @NotNull String str, @NotNull Error error2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                return new JsonRpcError(j2, str, error2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof JsonRpcError)) {
                    return false;
                }
                JsonRpcError jsonRpcError = (JsonRpcError) obj;
                return this.id == jsonRpcError.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error);
            }

            @NotNull
            public final Error getError() {
                return this.error;
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public final String getJsonrpc() {
                return this.jsonrpc;
            }

            public int hashCode() {
                return this.error.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                Error error2 = this.error;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "JsonRpcError(id=", ", jsonrpc=", str);
                v2.append(", error=");
                v2.append(error2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public JsonRpcError(long j2, @NotNull String str, @NotNull Error error2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                this.id = j2;
                this.jsonrpc = str;
                this.error = error2;
            }
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "id", "", "jsonrpc", "", "result", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class JsonRpcResult extends JsonRpcResponse {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String result;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ JsonRpcResult(long j2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(j2, (i3 & 2) != 0 ? "2.0" : str, str2);
            }

            public static /* synthetic */ JsonRpcResult copy$default(JsonRpcResult jsonRpcResult, long j2, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = jsonRpcResult.id;
                }
                if ((i3 & 2) != 0) {
                    str = jsonRpcResult.jsonrpc;
                }
                if ((i3 & 4) != 0) {
                    str2 = jsonRpcResult.result;
                }
                return jsonRpcResult.copy(j2, str, str2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.result;
            }

            @NotNull
            public final JsonRpcResult copy(long j2, @NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, "result");
                return new JsonRpcResult(j2, str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof JsonRpcResult)) {
                    return false;
                }
                JsonRpcResult jsonRpcResult = (JsonRpcResult) obj;
                return this.id == jsonRpcResult.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcResult.jsonrpc) && Intrinsics.areEqual((Object) this.result, (Object) jsonRpcResult.result);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public final String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                return C0118y.j(androidx.work.impl.a.v(j2, "JsonRpcResult(id=", ", jsonrpc=", str), ", result=", this.result, ")");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public JsonRpcResult(long j2, @NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, "result");
                this.id = j2;
                this.jsonrpc = str;
                this.result = str2;
            }
        }

        private JsonRpcResponse() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Namespace;", "Lcom/reown/sign/engine/model/EngineDO;", "<init>", "()V", "Proposal", "Session", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Namespace extends EngineDO {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "Lcom/reown/sign/engine/model/EngineDO$Namespace;", "chains", "", "", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Proposal extends Namespace {
            @Nullable
            private final List<String> chains;
            @NotNull
            private final List<String> events;
            @NotNull
            private final List<String> methods;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Proposal(List list, List list2, List list3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? null : list, list2, list3);
            }

            public static /* synthetic */ Proposal copy$default(Proposal proposal, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    list = proposal.chains;
                }
                if ((i3 & 2) != 0) {
                    list2 = proposal.methods;
                }
                if ((i3 & 4) != 0) {
                    list3 = proposal.events;
                }
                return proposal.copy(list, list2, list3);
            }

            @Nullable
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
            public final Proposal copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                Intrinsics.checkNotNullParameter(list2, "methods");
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
                return Intrinsics.areEqual((Object) this.chains, (Object) proposal.chains) && Intrinsics.areEqual((Object) this.methods, (Object) proposal.methods) && Intrinsics.areEqual((Object) this.events, (Object) proposal.events);
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
            public final List<String> getMethods() {
                return this.methods;
            }

            public int hashCode() {
                List<String> list = this.chains;
                return this.events.hashCode() + a.j(this.methods, (list == null ? 0 : list.hashCode()) * 31, 31);
            }

            @NotNull
            public String toString() {
                List<String> list = this.chains;
                List<String> list2 = this.methods;
                List<String> list3 = this.events;
                StringBuilder sb = new StringBuilder("Proposal(chains=");
                sb.append(list);
                sb.append(", methods=");
                sb.append(list2);
                sb.append(", events=");
                return C0118y.h(")", list3, sb);
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Proposal(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list2, "methods");
                Intrinsics.checkNotNullParameter(list3, "events");
                this.chains = list;
                this.methods = list2;
                this.events = list3;
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "Lcom/reown/sign/engine/model/EngineDO$Namespace;", "chains", "", "", "accounts", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
            public final Session copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
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
            public Session(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
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

        private Namespace() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "Lcom/reown/foundation/common/model/Topic;", "appMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PairingSettle extends EngineDO implements EngineEvent {
        @Nullable
        private final AppMetaData appMetaData;
        @NotNull
        private final Topic topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PairingSettle(@NotNull Topic topic2, @Nullable AppMetaData appMetaData2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            this.topic = topic2;
            this.appMetaData = appMetaData2;
        }

        public static /* synthetic */ PairingSettle copy$default(PairingSettle pairingSettle, Topic topic2, AppMetaData appMetaData2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                topic2 = pairingSettle.topic;
            }
            if ((i3 & 2) != 0) {
                appMetaData2 = pairingSettle.appMetaData;
            }
            return pairingSettle.copy(topic2, appMetaData2);
        }

        @NotNull
        public final Topic component1() {
            return this.topic;
        }

        @Nullable
        public final AppMetaData component2() {
            return this.appMetaData;
        }

        @NotNull
        public final PairingSettle copy(@NotNull Topic topic2, @Nullable AppMetaData appMetaData2) {
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            return new PairingSettle(topic2, appMetaData2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PairingSettle)) {
                return false;
            }
            PairingSettle pairingSettle = (PairingSettle) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) pairingSettle.topic) && Intrinsics.areEqual((Object) this.appMetaData, (Object) pairingSettle.appMetaData);
        }

        @Nullable
        public final AppMetaData getAppMetaData() {
            return this.appMetaData;
        }

        @NotNull
        public final Topic getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int hashCode = this.topic.hashCode() * 31;
            AppMetaData appMetaData2 = this.appMetaData;
            return hashCode + (appMetaData2 == null ? 0 : appMetaData2.hashCode());
        }

        @NotNull
        public String toString() {
            Topic topic2 = this.topic;
            AppMetaData appMetaData2 = this.appMetaData;
            return "PairingSettle(topic=" + topic2 + ", appMetaData=" + appMetaData2 + ")";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Participant;", "Lcom/reown/sign/engine/model/EngineDO;", "publicKey", "", "metadata", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getPublicKey", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Participant extends EngineDO {
        @NotNull
        private final AppMetaData metadata;
        @NotNull
        private final String publicKey;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Participant(@NotNull String str, @NotNull AppMetaData appMetaData) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "publicKey");
            Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
            this.publicKey = str;
            this.metadata = appMetaData;
        }

        public static /* synthetic */ Participant copy$default(Participant participant, String str, AppMetaData appMetaData, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = participant.publicKey;
            }
            if ((i3 & 2) != 0) {
                appMetaData = participant.metadata;
            }
            return participant.copy(str, appMetaData);
        }

        @NotNull
        public final String component1() {
            return this.publicKey;
        }

        @NotNull
        public final AppMetaData component2() {
            return this.metadata;
        }

        @NotNull
        public final Participant copy(@NotNull String str, @NotNull AppMetaData appMetaData) {
            Intrinsics.checkNotNullParameter(str, "publicKey");
            Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
            return new Participant(str, appMetaData);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Participant)) {
                return false;
            }
            Participant participant = (Participant) obj;
            return Intrinsics.areEqual((Object) this.publicKey, (Object) participant.publicKey) && Intrinsics.areEqual((Object) this.metadata, (Object) participant.metadata);
        }

        @NotNull
        public final AppMetaData getMetadata() {
            return this.metadata;
        }

        @NotNull
        public final String getPublicKey() {
            return this.publicKey;
        }

        public int hashCode() {
            return this.metadata.hashCode() + (this.publicKey.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            String str = this.publicKey;
            AppMetaData appMetaData = this.metadata;
            return "Participant(publicKey=" + str + ", metadata=" + appMetaData + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0004HÆ\u0003J\u0001\u0010.\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0004HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u00066"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "Lcom/reown/sign/engine/model/EngineDO;", "chains", "", "", "domain", "nonce", "aud", "type", "iat", "nbf", "exp", "statement", "requestId", "resources", "version", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getChains", "()Ljava/util/List;", "getDomain", "()Ljava/lang/String;", "getNonce", "getAud", "getType", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "setResources", "(Ljava/util/List;)V", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PayloadParams extends EngineDO {
        @NotNull
        private final String aud;
        @NotNull
        private final List<String> chains;
        @NotNull
        private final String domain;
        @Nullable
        private final String exp;
        @NotNull
        private final String iat;
        @Nullable
        private final String nbf;
        @NotNull
        private final String nonce;
        @Nullable
        private final String requestId;
        @Nullable
        private List<String> resources;
        @Nullable
        private final String statement;
        @Nullable
        private final String type;
        @NotNull
        private final String version;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PayloadParams(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2, @NotNull String str10) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "chains");
            Intrinsics.checkNotNullParameter(str, "domain");
            Intrinsics.checkNotNullParameter(str2, "nonce");
            Intrinsics.checkNotNullParameter(str3, "aud");
            Intrinsics.checkNotNullParameter(str5, "iat");
            Intrinsics.checkNotNullParameter(str10, "version");
            this.chains = list;
            this.domain = str;
            this.nonce = str2;
            this.aud = str3;
            this.type = str4;
            this.iat = str5;
            this.nbf = str6;
            this.exp = str7;
            this.statement = str8;
            this.requestId = str9;
            this.resources = list2;
            this.version = str10;
        }

        public static /* synthetic */ PayloadParams copy$default(PayloadParams payloadParams, List list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list2, String str10, int i3, Object obj) {
            PayloadParams payloadParams2 = payloadParams;
            int i4 = i3;
            return payloadParams.copy((i4 & 1) != 0 ? payloadParams2.chains : list, (i4 & 2) != 0 ? payloadParams2.domain : str, (i4 & 4) != 0 ? payloadParams2.nonce : str2, (i4 & 8) != 0 ? payloadParams2.aud : str3, (i4 & 16) != 0 ? payloadParams2.type : str4, (i4 & 32) != 0 ? payloadParams2.iat : str5, (i4 & 64) != 0 ? payloadParams2.nbf : str6, (i4 & 128) != 0 ? payloadParams2.exp : str7, (i4 & 256) != 0 ? payloadParams2.statement : str8, (i4 & 512) != 0 ? payloadParams2.requestId : str9, (i4 & 1024) != 0 ? payloadParams2.resources : list2, (i4 & 2048) != 0 ? payloadParams2.version : str10);
        }

        @NotNull
        public final List<String> component1() {
            return this.chains;
        }

        @Nullable
        public final String component10() {
            return this.requestId;
        }

        @Nullable
        public final List<String> component11() {
            return this.resources;
        }

        @NotNull
        public final String component12() {
            return this.version;
        }

        @NotNull
        public final String component2() {
            return this.domain;
        }

        @NotNull
        public final String component3() {
            return this.nonce;
        }

        @NotNull
        public final String component4() {
            return this.aud;
        }

        @Nullable
        public final String component5() {
            return this.type;
        }

        @NotNull
        public final String component6() {
            return this.iat;
        }

        @Nullable
        public final String component7() {
            return this.nbf;
        }

        @Nullable
        public final String component8() {
            return this.exp;
        }

        @Nullable
        public final String component9() {
            return this.statement;
        }

        @NotNull
        public final PayloadParams copy(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2, @NotNull String str10) {
            Intrinsics.checkNotNullParameter(list, "chains");
            String str11 = str;
            Intrinsics.checkNotNullParameter(str11, "domain");
            String str12 = str2;
            Intrinsics.checkNotNullParameter(str12, "nonce");
            String str13 = str3;
            Intrinsics.checkNotNullParameter(str13, "aud");
            String str14 = str5;
            Intrinsics.checkNotNullParameter(str14, "iat");
            String str15 = str10;
            Intrinsics.checkNotNullParameter(str15, "version");
            return new PayloadParams(list, str11, str12, str13, str4, str14, str6, str7, str8, str9, list2, str15);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PayloadParams)) {
                return false;
            }
            PayloadParams payloadParams = (PayloadParams) obj;
            return Intrinsics.areEqual((Object) this.chains, (Object) payloadParams.chains) && Intrinsics.areEqual((Object) this.domain, (Object) payloadParams.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadParams.nonce) && Intrinsics.areEqual((Object) this.aud, (Object) payloadParams.aud) && Intrinsics.areEqual((Object) this.type, (Object) payloadParams.type) && Intrinsics.areEqual((Object) this.iat, (Object) payloadParams.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadParams.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payloadParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadParams.resources) && Intrinsics.areEqual((Object) this.version, (Object) payloadParams.version);
        }

        @NotNull
        public final String getAud() {
            return this.aud;
        }

        @NotNull
        public final List<String> getChains() {
            return this.chains;
        }

        @NotNull
        public final String getDomain() {
            return this.domain;
        }

        @Nullable
        public final String getExp() {
            return this.exp;
        }

        @NotNull
        public final String getIat() {
            return this.iat;
        }

        @Nullable
        public final String getNbf() {
            return this.nbf;
        }

        @NotNull
        public final String getNonce() {
            return this.nonce;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        @Nullable
        public final List<String> getResources() {
            return this.resources;
        }

        @Nullable
        public final String getStatement() {
            return this.statement;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @NotNull
        public final String getVersion() {
            return this.version;
        }

        public int hashCode() {
            int i3 = a.i(this.aud, a.i(this.nonce, a.i(this.domain, this.chains.hashCode() * 31, 31), 31), 31);
            String str = this.type;
            int i4 = 0;
            int i5 = a.i(this.iat, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
            String str2 = this.nbf;
            int hashCode = (i5 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.exp;
            int hashCode2 = (hashCode + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.statement;
            int hashCode3 = (hashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.requestId;
            int hashCode4 = (hashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
            List<String> list = this.resources;
            if (list != null) {
                i4 = list.hashCode();
            }
            return this.version.hashCode() + ((hashCode4 + i4) * 31);
        }

        public final void setResources(@Nullable List<String> list) {
            this.resources = list;
        }

        @NotNull
        public String toString() {
            List<String> list = this.chains;
            String str = this.domain;
            String str2 = this.nonce;
            String str3 = this.aud;
            String str4 = this.type;
            String str5 = this.iat;
            String str6 = this.nbf;
            String str7 = this.exp;
            String str8 = this.statement;
            String str9 = this.requestId;
            List<String> list2 = this.resources;
            String str10 = this.version;
            StringBuilder sb = new StringBuilder("PayloadParams(chains=");
            sb.append(list);
            sb.append(", domain=");
            sb.append(str);
            sb.append(", nonce=");
            b.w(sb, str2, ", aud=", str3, ", type=");
            b.w(sb, str4, ", iat=", str5, ", nbf=");
            b.w(sb, str6, ", exp=", str7, ", statement=");
            b.w(sb, str8, ", requestId=", str9, ", resources=");
            sb.append(list2);
            sb.append(", version=");
            sb.append(str10);
            sb.append(")");
            return sb.toString();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Request;", "Lcom/reown/sign/engine/model/EngineDO;", "topic", "", "method", "params", "chainId", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;)V", "getTopic", "()Ljava/lang/String;", "getMethod", "getParams", "getChainId", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Request extends EngineDO {
        @NotNull
        private final String chainId;
        @Nullable
        private final Expiry expiry;
        @NotNull
        private final String method;
        @NotNull
        private final String params;
        @NotNull
        private final String topic;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Request(String str, String str2, String str3, String str4, Expiry expiry2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i3 & 16) != 0 ? null : expiry2);
        }

        public static /* synthetic */ Request copy$default(Request request, String str, String str2, String str3, String str4, Expiry expiry2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = request.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = request.method;
            }
            String str5 = str2;
            if ((i3 & 4) != 0) {
                str3 = request.params;
            }
            String str6 = str3;
            if ((i3 & 8) != 0) {
                str4 = request.chainId;
            }
            String str7 = str4;
            if ((i3 & 16) != 0) {
                expiry2 = request.expiry;
            }
            return request.copy(str, str5, str6, str7, expiry2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @NotNull
        public final String component2() {
            return this.method;
        }

        @NotNull
        public final String component3() {
            return this.params;
        }

        @NotNull
        public final String component4() {
            return this.chainId;
        }

        @Nullable
        public final Expiry component5() {
            return this.expiry;
        }

        @NotNull
        public final Request copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Expiry expiry2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(str3, "params");
            Intrinsics.checkNotNullParameter(str4, "chainId");
            return new Request(str, str2, str3, str4, expiry2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Request)) {
                return false;
            }
            Request request = (Request) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) request.topic) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params) && Intrinsics.areEqual((Object) this.chainId, (Object) request.chainId) && Intrinsics.areEqual((Object) this.expiry, (Object) request.expiry);
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        @Nullable
        public final Expiry getExpiry() {
            return this.expiry;
        }

        @NotNull
        public final String getMethod() {
            return this.method;
        }

        @NotNull
        public final String getParams() {
            return this.params;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int i3 = a.i(this.chainId, a.i(this.params, a.i(this.method, this.topic.hashCode() * 31, 31), 31), 31);
            Expiry expiry2 = this.expiry;
            return i3 + (expiry2 == null ? 0 : expiry2.hashCode());
        }

        @NotNull
        public String toString() {
            String str = this.topic;
            String str2 = this.method;
            String str3 = this.params;
            String str4 = this.chainId;
            Expiry expiry2 = this.expiry;
            StringBuilder l2 = C0118y.l("Request(topic=", str, ", method=", str2, ", params=");
            b.w(l2, str3, ", chainId=", str4, ", expiry=");
            l2.append(expiry2);
            l2.append(")");
            return l2.toString();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Request(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Expiry expiry2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(str3, "params");
            Intrinsics.checkNotNullParameter(str4, "chainId");
            this.topic = str;
            this.method = str2;
            this.params = str3;
            this.chainId = str4;
            this.expiry = expiry2;
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003Bg\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jw\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\tHÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$Session;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/Sequence;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "Lcom/reown/foundation/common/model/Topic;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "pairingTopic", "", "requiredNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "namespaces", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getPairingTopic", "()Ljava/lang/String;", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getNamespaces", "getPeerAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Session extends EngineDO implements Sequence, EngineEvent {
        @NotNull
        private final Expiry expiry;
        @NotNull
        private final Map<String, Namespace.Session> namespaces;
        @Nullable
        private final Map<String, Namespace.Proposal> optionalNamespaces;
        @NotNull
        private final String pairingTopic;
        @Nullable
        private final AppMetaData peerAppMetaData;
        @NotNull
        private final Map<String, Namespace.Proposal> requiredNamespaces;
        @NotNull
        private final Topic topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Session(@NotNull Topic topic2, @NotNull Expiry expiry2, @NotNull String str, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable AppMetaData appMetaData) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(expiry2, "expiry");
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map3, "namespaces");
            this.topic = topic2;
            this.expiry = expiry2;
            this.pairingTopic = str;
            this.requiredNamespaces = map;
            this.optionalNamespaces = map2;
            this.namespaces = map3;
            this.peerAppMetaData = appMetaData;
        }

        public static /* synthetic */ Session copy$default(Session session, Topic topic2, Expiry expiry2, String str, Map<String, Namespace.Proposal> map, Map<String, Namespace.Proposal> map2, Map<String, Namespace.Session> map3, AppMetaData appMetaData, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                topic2 = session.topic;
            }
            if ((i3 & 2) != 0) {
                expiry2 = session.expiry;
            }
            Expiry expiry3 = expiry2;
            if ((i3 & 4) != 0) {
                str = session.pairingTopic;
            }
            String str2 = str;
            if ((i3 & 8) != 0) {
                map = session.requiredNamespaces;
            }
            Map<String, Namespace.Proposal> map4 = map;
            if ((i3 & 16) != 0) {
                map2 = session.optionalNamespaces;
            }
            Map<String, Namespace.Proposal> map5 = map2;
            if ((i3 & 32) != 0) {
                map3 = session.namespaces;
            }
            Map<String, Namespace.Session> map6 = map3;
            if ((i3 & 64) != 0) {
                appMetaData = session.peerAppMetaData;
            }
            return session.copy(topic2, expiry3, str2, map4, map5, map6, appMetaData);
        }

        @NotNull
        public final Topic component1() {
            return this.topic;
        }

        @NotNull
        public final Expiry component2() {
            return this.expiry;
        }

        @NotNull
        public final String component3() {
            return this.pairingTopic;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> component4() {
            return this.requiredNamespaces;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> component5() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final Map<String, Namespace.Session> component6() {
            return this.namespaces;
        }

        @Nullable
        public final AppMetaData component7() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final Session copy(@NotNull Topic topic2, @NotNull Expiry expiry2, @NotNull String str, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable AppMetaData appMetaData) {
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(expiry2, "expiry");
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map3, "namespaces");
            return new Session(topic2, expiry2, str, map, map2, map3, appMetaData);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) session.topic) && Intrinsics.areEqual((Object) this.expiry, (Object) session.expiry) && Intrinsics.areEqual((Object) this.pairingTopic, (Object) session.pairingTopic) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) session.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) session.optionalNamespaces) && Intrinsics.areEqual((Object) this.namespaces, (Object) session.namespaces) && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) session.peerAppMetaData);
        }

        @NotNull
        public Expiry getExpiry() {
            return this.expiry;
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @Nullable
        public final AppMetaData getPeerAppMetaData() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
            return this.requiredNamespaces;
        }

        @NotNull
        public Topic getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int d2 = b.d(this.requiredNamespaces, a.i(this.pairingTopic, (this.expiry.hashCode() + (this.topic.hashCode() * 31)) * 31, 31), 31);
            Map<String, Namespace.Proposal> map = this.optionalNamespaces;
            int i3 = 0;
            int d3 = b.d(this.namespaces, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31);
            AppMetaData appMetaData = this.peerAppMetaData;
            if (appMetaData != null) {
                i3 = appMetaData.hashCode();
            }
            return d3 + i3;
        }

        @NotNull
        public String toString() {
            Topic topic2 = this.topic;
            Expiry expiry2 = this.expiry;
            String str = this.pairingTopic;
            Map<String, Namespace.Proposal> map = this.requiredNamespaces;
            Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
            Map<String, Namespace.Session> map3 = this.namespaces;
            AppMetaData appMetaData = this.peerAppMetaData;
            return "Session(topic=" + topic2 + ", expiry=" + expiry2 + ", pairingTopic=" + str + ", requiredNamespaces=" + map + ", optionalNamespaces=" + map2 + ", namespaces=" + map3 + ", peerAppMetaData=" + appMetaData + ")";
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionApproved;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "accounts", "", "namespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "<init>", "(Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/util/List;Ljava/util/Map;)V", "getTopic", "()Ljava/lang/String;", "getPeerAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "getAccounts", "()Ljava/util/List;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionApproved extends EngineDO implements EngineEvent {
        @NotNull
        private final List<String> accounts;
        @NotNull
        private final Map<String, Namespace.Session> namespaces;
        @Nullable
        private final AppMetaData peerAppMetaData;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionApproved(@NotNull String str, @Nullable AppMetaData appMetaData, @NotNull List<String> list, @NotNull Map<String, Namespace.Session> map) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(list, "accounts");
            Intrinsics.checkNotNullParameter(map, "namespaces");
            this.topic = str;
            this.peerAppMetaData = appMetaData;
            this.accounts = list;
            this.namespaces = map;
        }

        public static /* synthetic */ SessionApproved copy$default(SessionApproved sessionApproved, String str, AppMetaData appMetaData, List<String> list, Map<String, Namespace.Session> map, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionApproved.topic;
            }
            if ((i3 & 2) != 0) {
                appMetaData = sessionApproved.peerAppMetaData;
            }
            if ((i3 & 4) != 0) {
                list = sessionApproved.accounts;
            }
            if ((i3 & 8) != 0) {
                map = sessionApproved.namespaces;
            }
            return sessionApproved.copy(str, appMetaData, list, map);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @Nullable
        public final AppMetaData component2() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final List<String> component3() {
            return this.accounts;
        }

        @NotNull
        public final Map<String, Namespace.Session> component4() {
            return this.namespaces;
        }

        @NotNull
        public final SessionApproved copy(@NotNull String str, @Nullable AppMetaData appMetaData, @NotNull List<String> list, @NotNull Map<String, Namespace.Session> map) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(list, "accounts");
            Intrinsics.checkNotNullParameter(map, "namespaces");
            return new SessionApproved(str, appMetaData, list, map);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionApproved)) {
                return false;
            }
            SessionApproved sessionApproved = (SessionApproved) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionApproved.topic) && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) sessionApproved.peerAppMetaData) && Intrinsics.areEqual((Object) this.accounts, (Object) sessionApproved.accounts) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionApproved.namespaces);
        }

        @NotNull
        public final List<String> getAccounts() {
            return this.accounts;
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }

        @Nullable
        public final AppMetaData getPeerAppMetaData() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int hashCode = this.topic.hashCode() * 31;
            AppMetaData appMetaData = this.peerAppMetaData;
            return this.namespaces.hashCode() + a.j(this.accounts, (hashCode + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31, 31);
        }

        @NotNull
        public String toString() {
            String str = this.topic;
            AppMetaData appMetaData = this.peerAppMetaData;
            List<String> list = this.accounts;
            Map<String, Namespace.Session> map = this.namespaces;
            return "SessionApproved(topic=" + str + ", peerAppMetaData=" + appMetaData + ", accounts=" + list + ", namespaces=" + map + ")";
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006)"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateEvent;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "id", "", "pairingTopic", "", "payloadParams", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "participant", "Lcom/reown/sign/engine/model/EngineDO$Participant;", "expiryTimestamp", "verifyContext", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "<init>", "(JLjava/lang/String;Lcom/reown/sign/engine/model/EngineDO$PayloadParams;Lcom/reown/sign/engine/model/EngineDO$Participant;JLcom/reown/sign/engine/model/EngineDO$VerifyContext;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getPayloadParams", "()Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "getParticipant", "()Lcom/reown/sign/engine/model/EngineDO$Participant;", "getExpiryTimestamp", "getVerifyContext", "()Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionAuthenticateEvent extends EngineDO implements EngineEvent {
        private final long expiryTimestamp;
        private final long id;
        @NotNull
        private final String pairingTopic;
        @NotNull
        private final Participant participant;
        @NotNull
        private final PayloadParams payloadParams;
        @NotNull
        private final VerifyContext verifyContext;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionAuthenticateEvent(long j2, @NotNull String str, @NotNull PayloadParams payloadParams2, @NotNull Participant participant2, long j3, @NotNull VerifyContext verifyContext2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
            Intrinsics.checkNotNullParameter(participant2, "participant");
            Intrinsics.checkNotNullParameter(verifyContext2, "verifyContext");
            this.id = j2;
            this.pairingTopic = str;
            this.payloadParams = payloadParams2;
            this.participant = participant2;
            this.expiryTimestamp = j3;
            this.verifyContext = verifyContext2;
        }

        public static /* synthetic */ SessionAuthenticateEvent copy$default(SessionAuthenticateEvent sessionAuthenticateEvent, long j2, String str, PayloadParams payloadParams2, Participant participant2, long j3, VerifyContext verifyContext2, int i3, Object obj) {
            SessionAuthenticateEvent sessionAuthenticateEvent2 = sessionAuthenticateEvent;
            return sessionAuthenticateEvent.copy((i3 & 1) != 0 ? sessionAuthenticateEvent2.id : j2, (i3 & 2) != 0 ? sessionAuthenticateEvent2.pairingTopic : str, (i3 & 4) != 0 ? sessionAuthenticateEvent2.payloadParams : payloadParams2, (i3 & 8) != 0 ? sessionAuthenticateEvent2.participant : participant2, (i3 & 16) != 0 ? sessionAuthenticateEvent2.expiryTimestamp : j3, (i3 & 32) != 0 ? sessionAuthenticateEvent2.verifyContext : verifyContext2);
        }

        public final long component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.pairingTopic;
        }

        @NotNull
        public final PayloadParams component3() {
            return this.payloadParams;
        }

        @NotNull
        public final Participant component4() {
            return this.participant;
        }

        public final long component5() {
            return this.expiryTimestamp;
        }

        @NotNull
        public final VerifyContext component6() {
            return this.verifyContext;
        }

        @NotNull
        public final SessionAuthenticateEvent copy(long j2, @NotNull String str, @NotNull PayloadParams payloadParams2, @NotNull Participant participant2, long j3, @NotNull VerifyContext verifyContext2) {
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
            Intrinsics.checkNotNullParameter(participant2, "participant");
            VerifyContext verifyContext3 = verifyContext2;
            Intrinsics.checkNotNullParameter(verifyContext3, "verifyContext");
            return new SessionAuthenticateEvent(j2, str, payloadParams2, participant2, j3, verifyContext3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionAuthenticateEvent)) {
                return false;
            }
            SessionAuthenticateEvent sessionAuthenticateEvent = (SessionAuthenticateEvent) obj;
            return this.id == sessionAuthenticateEvent.id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionAuthenticateEvent.pairingTopic) && Intrinsics.areEqual((Object) this.payloadParams, (Object) sessionAuthenticateEvent.payloadParams) && Intrinsics.areEqual((Object) this.participant, (Object) sessionAuthenticateEvent.participant) && this.expiryTimestamp == sessionAuthenticateEvent.expiryTimestamp && Intrinsics.areEqual((Object) this.verifyContext, (Object) sessionAuthenticateEvent.verifyContext);
        }

        public final long getExpiryTimestamp() {
            return this.expiryTimestamp;
        }

        public final long getId() {
            return this.id;
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @NotNull
        public final Participant getParticipant() {
            return this.participant;
        }

        @NotNull
        public final PayloadParams getPayloadParams() {
            return this.payloadParams;
        }

        @NotNull
        public final VerifyContext getVerifyContext() {
            return this.verifyContext;
        }

        public int hashCode() {
            int i3 = a.i(this.pairingTopic, Long.hashCode(this.id) * 31, 31);
            int hashCode = this.participant.hashCode();
            return this.verifyContext.hashCode() + a.D(this.expiryTimestamp, (hashCode + ((this.payloadParams.hashCode() + i3) * 31)) * 31, 31);
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.pairingTopic;
            PayloadParams payloadParams2 = this.payloadParams;
            Participant participant2 = this.participant;
            long j3 = this.expiryTimestamp;
            VerifyContext verifyContext2 = this.verifyContext;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticateEvent(id=", ", pairingTopic=", str);
            v2.append(", payloadParams=");
            v2.append(payloadParams2);
            v2.append(", participant=");
            v2.append(participant2);
            v2.append(", expiryTimestamp=");
            v2.append(j3);
            v2.append(", verifyContext=");
            v2.append(verifyContext2);
            v2.append(")");
            return v2.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class SessionAuthenticateResponse extends EngineDO implements EngineEvent {

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse;", "id", "", "code", "", "message", "", "<init>", "(JILjava/lang/String;)V", "getId", "()J", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends SessionAuthenticateResponse {
            private final int code;
            private final long id;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(long j2, int i3, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.id = j2;
                this.code = i3;
                this.message = str;
            }

            public static /* synthetic */ Error copy$default(Error error, long j2, int i3, String str, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    j2 = error.id;
                }
                if ((i4 & 2) != 0) {
                    i3 = error.code;
                }
                if ((i4 & 4) != 0) {
                    str = error.message;
                }
                return error.copy(j2, i3, str);
            }

            public final long component1() {
                return this.id;
            }

            public final int component2() {
                return this.code;
            }

            @NotNull
            public final String component3() {
                return this.message;
            }

            @NotNull
            public final Error copy(long j2, int i3, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new Error(j2, i3, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Error)) {
                    return false;
                }
                Error error = (Error) obj;
                return this.id == error.id && this.code == error.code && Intrinsics.areEqual((Object) this.message, (Object) error.message);
            }

            public final int getCode() {
                return this.code;
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode() + a.c(this.code, Long.hashCode(this.id) * 31, 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                int i3 = this.code;
                String str = this.message;
                StringBuilder sb = new StringBuilder("Error(id=");
                sb.append(j2);
                sb.append(", code=");
                sb.append(i3);
                return C0118y.j(sb, ", message=", str, ")");
            }
        }

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse$Result;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse;", "id", "", "cacaos", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "session", "Lcom/reown/sign/engine/model/EngineDO$Session;", "<init>", "(JLjava/util/List;Lcom/reown/sign/engine/model/EngineDO$Session;)V", "getId", "()J", "getCacaos", "()Ljava/util/List;", "getSession", "()Lcom/reown/sign/engine/model/EngineDO$Session;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Result extends SessionAuthenticateResponse {
            @NotNull
            private final List<Cacao> cacaos;
            private final long id;
            @Nullable
            private final Session session;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Result(long j2, @NotNull List<Cacao> list, @Nullable Session session2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "cacaos");
                this.id = j2;
                this.cacaos = list;
                this.session = session2;
            }

            public static /* synthetic */ Result copy$default(Result result, long j2, List<Cacao> list, Session session2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = result.id;
                }
                if ((i3 & 2) != 0) {
                    list = result.cacaos;
                }
                if ((i3 & 4) != 0) {
                    session2 = result.session;
                }
                return result.copy(j2, list, session2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final List<Cacao> component2() {
                return this.cacaos;
            }

            @Nullable
            public final Session component3() {
                return this.session;
            }

            @NotNull
            public final Result copy(long j2, @NotNull List<Cacao> list, @Nullable Session session2) {
                Intrinsics.checkNotNullParameter(list, "cacaos");
                return new Result(j2, list, session2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Result)) {
                    return false;
                }
                Result result = (Result) obj;
                return this.id == result.id && Intrinsics.areEqual((Object) this.cacaos, (Object) result.cacaos) && Intrinsics.areEqual((Object) this.session, (Object) result.session);
            }

            @NotNull
            public final List<Cacao> getCacaos() {
                return this.cacaos;
            }

            public final long getId() {
                return this.id;
            }

            @Nullable
            public final Session getSession() {
                return this.session;
            }

            public int hashCode() {
                int j2 = a.j(this.cacaos, Long.hashCode(this.id) * 31, 31);
                Session session2 = this.session;
                return j2 + (session2 == null ? 0 : session2.hashCode());
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                List<Cacao> list = this.cacaos;
                Session session2 = this.session;
                return "Result(id=" + j2 + ", cacaos=" + list + ", session=" + session2 + ")";
            }
        }

        public /* synthetic */ SessionAuthenticateResponse(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private SessionAuthenticateResponse() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionDelete;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionDelete extends EngineDO implements EngineEvent {
        @NotNull
        private final String reason;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionDelete(@NotNull String str, @NotNull String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            this.topic = str;
            this.reason = str2;
        }

        public static /* synthetic */ SessionDelete copy$default(SessionDelete sessionDelete, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionDelete.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = sessionDelete.reason;
            }
            return sessionDelete.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @NotNull
        public final String component2() {
            return this.reason;
        }

        @NotNull
        public final SessionDelete copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            return new SessionDelete(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionDelete)) {
                return false;
            }
            SessionDelete sessionDelete = (SessionDelete) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionDelete.topic) && Intrinsics.areEqual((Object) this.reason, (Object) sessionDelete.reason);
        }

        @NotNull
        public final String getReason() {
            return this.reason;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.reason.hashCode() + (this.topic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("SessionDelete(topic=", this.topic, ", reason=", this.reason, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionEvent;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "name", "data", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getName", "getData", "getChainId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionEvent extends EngineDO implements EngineEvent {
        @NotNull
        private final String chainId;
        @NotNull
        private final String data;
        @NotNull
        private final String name;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionEvent(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "name");
            Intrinsics.checkNotNullParameter(str3, "data");
            Intrinsics.checkNotNullParameter(str4, "chainId");
            this.topic = str;
            this.name = str2;
            this.data = str3;
            this.chainId = str4;
        }

        public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, String str, String str2, String str3, String str4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionEvent.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = sessionEvent.name;
            }
            if ((i3 & 4) != 0) {
                str3 = sessionEvent.data;
            }
            if ((i3 & 8) != 0) {
                str4 = sessionEvent.chainId;
            }
            return sessionEvent.copy(str, str2, str3, str4);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final String component3() {
            return this.data;
        }

        @NotNull
        public final String component4() {
            return this.chainId;
        }

        @NotNull
        public final SessionEvent copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "name");
            Intrinsics.checkNotNullParameter(str3, "data");
            Intrinsics.checkNotNullParameter(str4, "chainId");
            return new SessionEvent(str, str2, str3, str4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionEvent)) {
                return false;
            }
            SessionEvent sessionEvent = (SessionEvent) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionEvent.topic) && Intrinsics.areEqual((Object) this.name, (Object) sessionEvent.name) && Intrinsics.areEqual((Object) this.data, (Object) sessionEvent.data) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionEvent.chainId);
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        @NotNull
        public final String getData() {
            return this.data;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.chainId.hashCode() + a.i(this.data, a.i(this.name, this.topic.hashCode() * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            String str = this.topic;
            String str2 = this.name;
            return android.support.v4.media.session.a.r(C0118y.l("SessionEvent(topic=", str, ", name=", str2, ", data="), this.data, ", chainId=", this.chainId, ")");
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003Bg\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jw\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\tHÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionExtend;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/Sequence;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "Lcom/reown/foundation/common/model/Topic;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "pairingTopic", "", "requiredNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "namespaces", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getPairingTopic", "()Ljava/lang/String;", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getNamespaces", "getPeerAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionExtend extends EngineDO implements Sequence, EngineEvent {
        @NotNull
        private final Expiry expiry;
        @NotNull
        private final Map<String, Namespace.Session> namespaces;
        @Nullable
        private final Map<String, Namespace.Proposal> optionalNamespaces;
        @NotNull
        private final String pairingTopic;
        @Nullable
        private final AppMetaData peerAppMetaData;
        @NotNull
        private final Map<String, Namespace.Proposal> requiredNamespaces;
        @NotNull
        private final Topic topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionExtend(@NotNull Topic topic2, @NotNull Expiry expiry2, @NotNull String str, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable AppMetaData appMetaData) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(expiry2, "expiry");
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map3, "namespaces");
            this.topic = topic2;
            this.expiry = expiry2;
            this.pairingTopic = str;
            this.requiredNamespaces = map;
            this.optionalNamespaces = map2;
            this.namespaces = map3;
            this.peerAppMetaData = appMetaData;
        }

        public static /* synthetic */ SessionExtend copy$default(SessionExtend sessionExtend, Topic topic2, Expiry expiry2, String str, Map<String, Namespace.Proposal> map, Map<String, Namespace.Proposal> map2, Map<String, Namespace.Session> map3, AppMetaData appMetaData, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                topic2 = sessionExtend.topic;
            }
            if ((i3 & 2) != 0) {
                expiry2 = sessionExtend.expiry;
            }
            Expiry expiry3 = expiry2;
            if ((i3 & 4) != 0) {
                str = sessionExtend.pairingTopic;
            }
            String str2 = str;
            if ((i3 & 8) != 0) {
                map = sessionExtend.requiredNamespaces;
            }
            Map<String, Namespace.Proposal> map4 = map;
            if ((i3 & 16) != 0) {
                map2 = sessionExtend.optionalNamespaces;
            }
            Map<String, Namespace.Proposal> map5 = map2;
            if ((i3 & 32) != 0) {
                map3 = sessionExtend.namespaces;
            }
            Map<String, Namespace.Session> map6 = map3;
            if ((i3 & 64) != 0) {
                appMetaData = sessionExtend.peerAppMetaData;
            }
            return sessionExtend.copy(topic2, expiry3, str2, map4, map5, map6, appMetaData);
        }

        @NotNull
        public final Topic component1() {
            return this.topic;
        }

        @NotNull
        public final Expiry component2() {
            return this.expiry;
        }

        @NotNull
        public final String component3() {
            return this.pairingTopic;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> component4() {
            return this.requiredNamespaces;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> component5() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final Map<String, Namespace.Session> component6() {
            return this.namespaces;
        }

        @Nullable
        public final AppMetaData component7() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final SessionExtend copy(@NotNull Topic topic2, @NotNull Expiry expiry2, @NotNull String str, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable AppMetaData appMetaData) {
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(expiry2, "expiry");
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map3, "namespaces");
            return new SessionExtend(topic2, expiry2, str, map, map2, map3, appMetaData);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionExtend)) {
                return false;
            }
            SessionExtend sessionExtend = (SessionExtend) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionExtend.topic) && Intrinsics.areEqual((Object) this.expiry, (Object) sessionExtend.expiry) && Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionExtend.pairingTopic) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionExtend.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionExtend.optionalNamespaces) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionExtend.namespaces) && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) sessionExtend.peerAppMetaData);
        }

        @NotNull
        public Expiry getExpiry() {
            return this.expiry;
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }

        @Nullable
        public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @Nullable
        public final AppMetaData getPeerAppMetaData() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
            return this.requiredNamespaces;
        }

        @NotNull
        public Topic getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int d2 = b.d(this.requiredNamespaces, a.i(this.pairingTopic, (this.expiry.hashCode() + (this.topic.hashCode() * 31)) * 31, 31), 31);
            Map<String, Namespace.Proposal> map = this.optionalNamespaces;
            int i3 = 0;
            int d3 = b.d(this.namespaces, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31);
            AppMetaData appMetaData = this.peerAppMetaData;
            if (appMetaData != null) {
                i3 = appMetaData.hashCode();
            }
            return d3 + i3;
        }

        @NotNull
        public String toString() {
            Topic topic2 = this.topic;
            Expiry expiry2 = this.expiry;
            String str = this.pairingTopic;
            Map<String, Namespace.Proposal> map = this.requiredNamespaces;
            Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
            Map<String, Namespace.Session> map3 = this.namespaces;
            AppMetaData appMetaData = this.peerAppMetaData;
            return "SessionExtend(topic=" + topic2 + ", expiry=" + expiry2 + ", pairingTopic=" + str + ", requiredNamespaces=" + map + ", optionalNamespaces=" + map2 + ", namespaces=" + map3 + ", peerAppMetaData=" + appMetaData + ")";
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionPayloadResponse;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "chainId", "method", "result", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getMethod", "getResult", "()Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionPayloadResponse extends EngineDO implements EngineEvent {
        @Nullable
        private final String chainId;
        @NotNull
        private final String method;
        @NotNull
        private final JsonRpcResponse result;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionPayloadResponse(@NotNull String str, @Nullable String str2, @NotNull String str3, @NotNull JsonRpcResponse jsonRpcResponse) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(jsonRpcResponse, "result");
            this.topic = str;
            this.chainId = str2;
            this.method = str3;
            this.result = jsonRpcResponse;
        }

        public static /* synthetic */ SessionPayloadResponse copy$default(SessionPayloadResponse sessionPayloadResponse, String str, String str2, String str3, JsonRpcResponse jsonRpcResponse, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionPayloadResponse.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = sessionPayloadResponse.chainId;
            }
            if ((i3 & 4) != 0) {
                str3 = sessionPayloadResponse.method;
            }
            if ((i3 & 8) != 0) {
                jsonRpcResponse = sessionPayloadResponse.result;
            }
            return sessionPayloadResponse.copy(str, str2, str3, jsonRpcResponse);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @Nullable
        public final String component2() {
            return this.chainId;
        }

        @NotNull
        public final String component3() {
            return this.method;
        }

        @NotNull
        public final JsonRpcResponse component4() {
            return this.result;
        }

        @NotNull
        public final SessionPayloadResponse copy(@NotNull String str, @Nullable String str2, @NotNull String str3, @NotNull JsonRpcResponse jsonRpcResponse) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(jsonRpcResponse, "result");
            return new SessionPayloadResponse(str, str2, str3, jsonRpcResponse);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionPayloadResponse)) {
                return false;
            }
            SessionPayloadResponse sessionPayloadResponse = (SessionPayloadResponse) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionPayloadResponse.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionPayloadResponse.chainId) && Intrinsics.areEqual((Object) this.method, (Object) sessionPayloadResponse.method) && Intrinsics.areEqual((Object) this.result, (Object) sessionPayloadResponse.result);
        }

        @Nullable
        public final String getChainId() {
            return this.chainId;
        }

        @NotNull
        public final String getMethod() {
            return this.method;
        }

        @NotNull
        public final JsonRpcResponse getResult() {
            return this.result;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int hashCode = this.topic.hashCode() * 31;
            String str = this.chainId;
            return this.result.hashCode() + a.i(this.method, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        }

        @NotNull
        public String toString() {
            String str = this.topic;
            String str2 = this.chainId;
            String str3 = this.method;
            JsonRpcResponse jsonRpcResponse = this.result;
            StringBuilder l2 = C0118y.l("SessionPayloadResponse(topic=", str, ", chainId=", str2, ", method=");
            l2.append(str3);
            l2.append(", result=");
            l2.append(jsonRpcResponse);
            l2.append(")");
            return l2.toString();
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B«\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\u0004HÆ\u0003J\t\u0010*\u001a\u00020\u0004HÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010,\u001a\u00020\u0004HÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0017\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rHÆ\u0003J\t\u00100\u001a\u00020\u0004HÆ\u0003J\t\u00101\u001a\u00020\u0004HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0017\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rHÆ\u0003JÇ\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rHÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 ¨\u0006<"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "pairingTopic", "", "name", "description", "url", "icons", "", "Ljava/net/URI;", "redirect", "requiredNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "scopedProperties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "getScopedProperties", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionProposal extends EngineDO implements EngineEvent {
        @NotNull
        private final String description;
        @NotNull
        private final List<URI> icons;
        @NotNull
        private final String name;
        @NotNull
        private final Map<String, Namespace.Proposal> optionalNamespaces;
        @NotNull
        private final String pairingTopic;
        @Nullable
        private final Map<String, String> properties;
        @NotNull
        private final String proposerPublicKey;
        @NotNull
        private final String redirect;
        @Nullable
        private final String relayData;
        @NotNull
        private final String relayProtocol;
        @NotNull
        private final Map<String, Namespace.Proposal> requiredNamespaces;
        @Nullable
        private final Map<String, String> scopedProperties;
        @NotNull
        private final String url;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionProposal(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(str2, "name");
            Intrinsics.checkNotNullParameter(str3, "description");
            Intrinsics.checkNotNullParameter(str4, "url");
            Intrinsics.checkNotNullParameter(list, "icons");
            Intrinsics.checkNotNullParameter(str5, "redirect");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
            Intrinsics.checkNotNullParameter(str6, "proposerPublicKey");
            Intrinsics.checkNotNullParameter(str7, "relayProtocol");
            this.pairingTopic = str;
            this.name = str2;
            this.description = str3;
            this.url = str4;
            this.icons = list;
            this.redirect = str5;
            this.requiredNamespaces = map;
            this.optionalNamespaces = map2;
            this.properties = map3;
            this.proposerPublicKey = str6;
            this.relayProtocol = str7;
            this.relayData = str8;
            this.scopedProperties = map4;
        }

        public static /* synthetic */ SessionProposal copy$default(SessionProposal sessionProposal, String str, String str2, String str3, String str4, List list, String str5, Map map, Map map2, Map map3, String str6, String str7, String str8, Map map4, int i3, Object obj) {
            SessionProposal sessionProposal2 = sessionProposal;
            int i4 = i3;
            return sessionProposal.copy((i4 & 1) != 0 ? sessionProposal2.pairingTopic : str, (i4 & 2) != 0 ? sessionProposal2.name : str2, (i4 & 4) != 0 ? sessionProposal2.description : str3, (i4 & 8) != 0 ? sessionProposal2.url : str4, (i4 & 16) != 0 ? sessionProposal2.icons : list, (i4 & 32) != 0 ? sessionProposal2.redirect : str5, (i4 & 64) != 0 ? sessionProposal2.requiredNamespaces : map, (i4 & 128) != 0 ? sessionProposal2.optionalNamespaces : map2, (i4 & 256) != 0 ? sessionProposal2.properties : map3, (i4 & 512) != 0 ? sessionProposal2.proposerPublicKey : str6, (i4 & 1024) != 0 ? sessionProposal2.relayProtocol : str7, (i4 & 2048) != 0 ? sessionProposal2.relayData : str8, (i4 & 4096) != 0 ? sessionProposal2.scopedProperties : map4);
        }

        @NotNull
        public final String component1() {
            return this.pairingTopic;
        }

        @NotNull
        public final String component10() {
            return this.proposerPublicKey;
        }

        @NotNull
        public final String component11() {
            return this.relayProtocol;
        }

        @Nullable
        public final String component12() {
            return this.relayData;
        }

        @Nullable
        public final Map<String, String> component13() {
            return this.scopedProperties;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final String component3() {
            return this.description;
        }

        @NotNull
        public final String component4() {
            return this.url;
        }

        @NotNull
        public final List<URI> component5() {
            return this.icons;
        }

        @NotNull
        public final String component6() {
            return this.redirect;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> component7() {
            return this.requiredNamespaces;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> component8() {
            return this.optionalNamespaces;
        }

        @Nullable
        public final Map<String, String> component9() {
            return this.properties;
        }

        @NotNull
        public final SessionProposal copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
            String str9 = str;
            Intrinsics.checkNotNullParameter(str9, "pairingTopic");
            String str10 = str2;
            Intrinsics.checkNotNullParameter(str10, "name");
            String str11 = str3;
            Intrinsics.checkNotNullParameter(str11, "description");
            String str12 = str4;
            Intrinsics.checkNotNullParameter(str12, "url");
            List<URI> list2 = list;
            Intrinsics.checkNotNullParameter(list2, "icons");
            String str13 = str5;
            Intrinsics.checkNotNullParameter(str13, "redirect");
            Map<String, Namespace.Proposal> map5 = map;
            Intrinsics.checkNotNullParameter(map5, "requiredNamespaces");
            Map<String, Namespace.Proposal> map6 = map2;
            Intrinsics.checkNotNullParameter(map6, "optionalNamespaces");
            String str14 = str6;
            Intrinsics.checkNotNullParameter(str14, "proposerPublicKey");
            String str15 = str7;
            Intrinsics.checkNotNullParameter(str15, "relayProtocol");
            return new SessionProposal(str9, str10, str11, str12, list2, str13, map5, map6, map3, str14, str15, str8, map4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionProposal)) {
                return false;
            }
            SessionProposal sessionProposal = (SessionProposal) obj;
            return Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionProposal.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) sessionProposal.name) && Intrinsics.areEqual((Object) this.description, (Object) sessionProposal.description) && Intrinsics.areEqual((Object) this.url, (Object) sessionProposal.url) && Intrinsics.areEqual((Object) this.icons, (Object) sessionProposal.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) sessionProposal.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposal.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposal.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposal.properties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionProposal.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionProposal.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) sessionProposal.relayData) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionProposal.scopedProperties);
        }

        @NotNull
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final List<URI> getIcons() {
            return this.icons;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
            return this.optionalNamespaces;
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        @Nullable
        public final Map<String, String> getProperties() {
            return this.properties;
        }

        @NotNull
        public final String getProposerPublicKey() {
            return this.proposerPublicKey;
        }

        @NotNull
        public final String getRedirect() {
            return this.redirect;
        }

        @Nullable
        public final String getRelayData() {
            return this.relayData;
        }

        @NotNull
        public final String getRelayProtocol() {
            return this.relayProtocol;
        }

        @NotNull
        public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
            return this.requiredNamespaces;
        }

        @Nullable
        public final Map<String, String> getScopedProperties() {
            return this.scopedProperties;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, a.i(this.redirect, a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, this.pairingTopic.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31);
            Map<String, String> map = this.properties;
            int i3 = 0;
            int i4 = a.i(this.relayProtocol, a.i(this.proposerPublicKey, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31), 31);
            String str = this.relayData;
            int hashCode = (i4 + (str == null ? 0 : str.hashCode())) * 31;
            Map<String, String> map2 = this.scopedProperties;
            if (map2 != null) {
                i3 = map2.hashCode();
            }
            return hashCode + i3;
        }

        @NotNull
        public String toString() {
            String str = this.pairingTopic;
            String str2 = this.name;
            String str3 = this.description;
            String str4 = this.url;
            List<URI> list = this.icons;
            String str5 = this.redirect;
            Map<String, Namespace.Proposal> map = this.requiredNamespaces;
            Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
            Map<String, String> map3 = this.properties;
            String str6 = this.proposerPublicKey;
            String str7 = this.relayProtocol;
            String str8 = this.relayData;
            Map<String, String> map4 = this.scopedProperties;
            StringBuilder l2 = C0118y.l("SessionProposal(pairingTopic=", str, ", name=", str2, ", description=");
            b.w(l2, str3, ", url=", str4, ", icons=");
            l2.append(list);
            l2.append(", redirect=");
            l2.append(str5);
            l2.append(", requiredNamespaces=");
            l2.append(map);
            l2.append(", optionalNamespaces=");
            l2.append(map2);
            l2.append(", properties=");
            l2.append(map3);
            l2.append(", proposerPublicKey=");
            l2.append(str6);
            l2.append(", relayProtocol=");
            b.w(l2, str7, ", relayData=", str8, ", scopedProperties=");
            l2.append(map4);
            l2.append(")");
            return l2.toString();
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionProposalEvent;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "proposal", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "context", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "<init>", "(Lcom/reown/sign/engine/model/EngineDO$SessionProposal;Lcom/reown/sign/engine/model/EngineDO$VerifyContext;)V", "getProposal", "()Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "getContext", "()Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionProposalEvent extends EngineDO implements EngineEvent {
        @NotNull
        private final VerifyContext context;
        @NotNull
        private final SessionProposal proposal;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionProposalEvent(@NotNull SessionProposal sessionProposal, @NotNull VerifyContext verifyContext) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(sessionProposal, "proposal");
            Intrinsics.checkNotNullParameter(verifyContext, "context");
            this.proposal = sessionProposal;
            this.context = verifyContext;
        }

        public static /* synthetic */ SessionProposalEvent copy$default(SessionProposalEvent sessionProposalEvent, SessionProposal sessionProposal, VerifyContext verifyContext, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                sessionProposal = sessionProposalEvent.proposal;
            }
            if ((i3 & 2) != 0) {
                verifyContext = sessionProposalEvent.context;
            }
            return sessionProposalEvent.copy(sessionProposal, verifyContext);
        }

        @NotNull
        public final SessionProposal component1() {
            return this.proposal;
        }

        @NotNull
        public final VerifyContext component2() {
            return this.context;
        }

        @NotNull
        public final SessionProposalEvent copy(@NotNull SessionProposal sessionProposal, @NotNull VerifyContext verifyContext) {
            Intrinsics.checkNotNullParameter(sessionProposal, "proposal");
            Intrinsics.checkNotNullParameter(verifyContext, "context");
            return new SessionProposalEvent(sessionProposal, verifyContext);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionProposalEvent)) {
                return false;
            }
            SessionProposalEvent sessionProposalEvent = (SessionProposalEvent) obj;
            return Intrinsics.areEqual((Object) this.proposal, (Object) sessionProposalEvent.proposal) && Intrinsics.areEqual((Object) this.context, (Object) sessionProposalEvent.context);
        }

        @NotNull
        public final VerifyContext getContext() {
            return this.context;
        }

        @NotNull
        public final SessionProposal getProposal() {
            return this.proposal;
        }

        public int hashCode() {
            return this.context.hashCode() + (this.proposal.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            SessionProposal sessionProposal = this.proposal;
            VerifyContext verifyContext = this.context;
            return "SessionProposalEvent(proposal=" + sessionProposal + ", context=" + verifyContext + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionRejected;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionRejected extends EngineDO implements EngineEvent {
        @NotNull
        private final String reason;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionRejected(@NotNull String str, @NotNull String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            this.topic = str;
            this.reason = str2;
        }

        public static /* synthetic */ SessionRejected copy$default(SessionRejected sessionRejected, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionRejected.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = sessionRejected.reason;
            }
            return sessionRejected.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @NotNull
        public final String component2() {
            return this.reason;
        }

        @NotNull
        public final SessionRejected copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            return new SessionRejected(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionRejected)) {
                return false;
            }
            SessionRejected sessionRejected = (SessionRejected) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionRejected.topic) && Intrinsics.areEqual((Object) this.reason, (Object) sessionRejected.reason);
        }

        @NotNull
        public final String getReason() {
            return this.reason;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.reason.hashCode() + (this.topic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("SessionRejected(topic=", this.topic, ", reason=", this.reason, ")");
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u00012\u00020\u0002:\u0001$B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bHÆ\u0003JA\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "", "chainId", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "request", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest$JSONRPCRequest;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/sign/engine/model/EngineDO$SessionRequest$JSONRPCRequest;Lcom/reown/android/internal/common/model/Expiry;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "getRequest", "()Lcom/reown/sign/engine/model/EngineDO$SessionRequest$JSONRPCRequest;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionRequest extends EngineDO implements EngineEvent {
        @Nullable
        private final String chainId;
        @Nullable
        private final Expiry expiry;
        @Nullable
        private final AppMetaData peerAppMetaData;
        @NotNull
        private final JSONRPCRequest request;
        @NotNull
        private final String topic;

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionRequest$JSONRPCRequest;", "Lcom/reown/sign/engine/model/EngineDO;", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class JSONRPCRequest extends EngineDO {
            private final long id;
            @NotNull
            private final String method;
            @NotNull
            private final String params;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str2, "params");
                this.id = j2;
                this.method = str;
                this.params = str2;
            }

            public static /* synthetic */ JSONRPCRequest copy$default(JSONRPCRequest jSONRPCRequest, long j2, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = jSONRPCRequest.id;
                }
                if ((i3 & 2) != 0) {
                    str = jSONRPCRequest.method;
                }
                if ((i3 & 4) != 0) {
                    str2 = jSONRPCRequest.params;
                }
                return jSONRPCRequest.copy(j2, str, str2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.method;
            }

            @NotNull
            public final String component3() {
                return this.params;
            }

            @NotNull
            public final JSONRPCRequest copy(long j2, @NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str2, "params");
                return new JSONRPCRequest(j2, str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof JSONRPCRequest)) {
                    return false;
                }
                JSONRPCRequest jSONRPCRequest = (JSONRPCRequest) obj;
                return this.id == jSONRPCRequest.id && Intrinsics.areEqual((Object) this.method, (Object) jSONRPCRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) jSONRPCRequest.params);
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final String getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, Long.hashCode(this.id) * 31, 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.method;
                return C0118y.j(androidx.work.impl.a.v(j2, "JSONRPCRequest(id=", ", method=", str), ", params=", this.params, ")");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest, @Nullable Expiry expiry2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
            this.topic = str;
            this.chainId = str2;
            this.peerAppMetaData = appMetaData;
            this.request = jSONRPCRequest;
            this.expiry = expiry2;
        }

        public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, Expiry expiry2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = sessionRequest.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = sessionRequest.chainId;
            }
            String str3 = str2;
            if ((i3 & 4) != 0) {
                appMetaData = sessionRequest.peerAppMetaData;
            }
            AppMetaData appMetaData2 = appMetaData;
            if ((i3 & 8) != 0) {
                jSONRPCRequest = sessionRequest.request;
            }
            JSONRPCRequest jSONRPCRequest2 = jSONRPCRequest;
            if ((i3 & 16) != 0) {
                expiry2 = sessionRequest.expiry;
            }
            return sessionRequest.copy(str, str3, appMetaData2, jSONRPCRequest2, expiry2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @Nullable
        public final String component2() {
            return this.chainId;
        }

        @Nullable
        public final AppMetaData component3() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final JSONRPCRequest component4() {
            return this.request;
        }

        @Nullable
        public final Expiry component5() {
            return this.expiry;
        }

        @NotNull
        public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest, @Nullable Expiry expiry2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
            return new SessionRequest(str, str2, appMetaData, jSONRPCRequest, expiry2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionRequest)) {
                return false;
            }
            SessionRequest sessionRequest = (SessionRequest) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionRequest.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequest.chainId) && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) sessionRequest.peerAppMetaData) && Intrinsics.areEqual((Object) this.request, (Object) sessionRequest.request) && Intrinsics.areEqual((Object) this.expiry, (Object) sessionRequest.expiry);
        }

        @Nullable
        public final String getChainId() {
            return this.chainId;
        }

        @Nullable
        public final Expiry getExpiry() {
            return this.expiry;
        }

        @Nullable
        public final AppMetaData getPeerAppMetaData() {
            return this.peerAppMetaData;
        }

        @NotNull
        public final JSONRPCRequest getRequest() {
            return this.request;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            int hashCode = this.topic.hashCode() * 31;
            String str = this.chainId;
            int i3 = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            AppMetaData appMetaData = this.peerAppMetaData;
            int hashCode3 = (this.request.hashCode() + ((hashCode2 + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31)) * 31;
            Expiry expiry2 = this.expiry;
            if (expiry2 != null) {
                i3 = expiry2.hashCode();
            }
            return hashCode3 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.topic;
            String str2 = this.chainId;
            AppMetaData appMetaData = this.peerAppMetaData;
            JSONRPCRequest jSONRPCRequest = this.request;
            Expiry expiry2 = this.expiry;
            StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerAppMetaData=");
            l2.append(appMetaData);
            l2.append(", request=");
            l2.append(jSONRPCRequest);
            l2.append(", expiry=");
            l2.append(expiry2);
            l2.append(")");
            return l2.toString();
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionRequestEvent;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "request", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "context", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "<init>", "(Lcom/reown/sign/engine/model/EngineDO$SessionRequest;Lcom/reown/sign/engine/model/EngineDO$VerifyContext;)V", "getRequest", "()Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "getContext", "()Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionRequestEvent extends EngineDO implements EngineEvent {
        @NotNull
        private final VerifyContext context;
        @NotNull
        private final SessionRequest request;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionRequestEvent(@NotNull SessionRequest sessionRequest, @NotNull VerifyContext verifyContext) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(sessionRequest, "request");
            Intrinsics.checkNotNullParameter(verifyContext, "context");
            this.request = sessionRequest;
            this.context = verifyContext;
        }

        public static /* synthetic */ SessionRequestEvent copy$default(SessionRequestEvent sessionRequestEvent, SessionRequest sessionRequest, VerifyContext verifyContext, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                sessionRequest = sessionRequestEvent.request;
            }
            if ((i3 & 2) != 0) {
                verifyContext = sessionRequestEvent.context;
            }
            return sessionRequestEvent.copy(sessionRequest, verifyContext);
        }

        @NotNull
        public final SessionRequest component1() {
            return this.request;
        }

        @NotNull
        public final VerifyContext component2() {
            return this.context;
        }

        @NotNull
        public final SessionRequestEvent copy(@NotNull SessionRequest sessionRequest, @NotNull VerifyContext verifyContext) {
            Intrinsics.checkNotNullParameter(sessionRequest, "request");
            Intrinsics.checkNotNullParameter(verifyContext, "context");
            return new SessionRequestEvent(sessionRequest, verifyContext);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionRequestEvent)) {
                return false;
            }
            SessionRequestEvent sessionRequestEvent = (SessionRequestEvent) obj;
            return Intrinsics.areEqual((Object) this.request, (Object) sessionRequestEvent.request) && Intrinsics.areEqual((Object) this.context, (Object) sessionRequestEvent.context);
        }

        @NotNull
        public final VerifyContext getContext() {
            return this.context;
        }

        @NotNull
        public final SessionRequest getRequest() {
            return this.request;
        }

        public int hashCode() {
            return this.context.hashCode() + (this.request.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            SessionRequest sessionRequest = this.request;
            VerifyContext verifyContext = this.context;
            return "SessionRequestEvent(request=" + sessionRequest + ", context=" + verifyContext + ")";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespaces;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "topic", "Lcom/reown/foundation/common/model/Topic;", "namespaces", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/util/Map;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionUpdateNamespaces extends EngineDO implements EngineEvent {
        @NotNull
        private final Map<String, Namespace.Session> namespaces;
        @NotNull
        private final Topic topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionUpdateNamespaces(@NotNull Topic topic2, @NotNull Map<String, Namespace.Session> map) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(map, "namespaces");
            this.topic = topic2;
            this.namespaces = map;
        }

        public static /* synthetic */ SessionUpdateNamespaces copy$default(SessionUpdateNamespaces sessionUpdateNamespaces, Topic topic2, Map<String, Namespace.Session> map, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                topic2 = sessionUpdateNamespaces.topic;
            }
            if ((i3 & 2) != 0) {
                map = sessionUpdateNamespaces.namespaces;
            }
            return sessionUpdateNamespaces.copy(topic2, map);
        }

        @NotNull
        public final Topic component1() {
            return this.topic;
        }

        @NotNull
        public final Map<String, Namespace.Session> component2() {
            return this.namespaces;
        }

        @NotNull
        public final SessionUpdateNamespaces copy(@NotNull Topic topic2, @NotNull Map<String, Namespace.Session> map) {
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(map, "namespaces");
            return new SessionUpdateNamespaces(topic2, map);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionUpdateNamespaces)) {
                return false;
            }
            SessionUpdateNamespaces sessionUpdateNamespaces = (SessionUpdateNamespaces) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) sessionUpdateNamespaces.topic) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionUpdateNamespaces.namespaces);
        }

        @NotNull
        public final Map<String, Namespace.Session> getNamespaces() {
            return this.namespaces;
        }

        @NotNull
        public final Topic getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.namespaces.hashCode() + (this.topic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            Topic topic2 = this.topic;
            Map<String, Namespace.Session> map = this.namespaces;
            return "SessionUpdateNamespaces(topic=" + topic2 + ", namespaces=" + map + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class SessionUpdateNamespacesResponse extends EngineDO implements EngineEvent {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends SessionUpdateNamespacesResponse {
            @NotNull
            private final String errorMessage;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                this.errorMessage = str;
            }

            public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = error.errorMessage;
                }
                return error.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.errorMessage;
            }

            @NotNull
            public final Error copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                return new Error(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
            }

            @NotNull
            public final String getErrorMessage() {
                return this.errorMessage;
            }

            public int hashCode() {
                return this.errorMessage.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Error(errorMessage=", this.errorMessage, ")");
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse$Result;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse;", "topic", "Lcom/reown/foundation/common/model/Topic;", "namespaces", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/util/Map;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Result extends SessionUpdateNamespacesResponse {
            @NotNull
            private final Map<String, Namespace.Session> namespaces;
            @NotNull
            private final Topic topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Result(@NotNull Topic topic2, @NotNull Map<String, Namespace.Session> map) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.topic = topic2;
                this.namespaces = map;
            }

            public static /* synthetic */ Result copy$default(Result result, Topic topic2, Map<String, Namespace.Session> map, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    topic2 = result.topic;
                }
                if ((i3 & 2) != 0) {
                    map = result.namespaces;
                }
                return result.copy(topic2, map);
            }

            @NotNull
            public final Topic component1() {
                return this.topic;
            }

            @NotNull
            public final Map<String, Namespace.Session> component2() {
                return this.namespaces;
            }

            @NotNull
            public final Result copy(@NotNull Topic topic2, @NotNull Map<String, Namespace.Session> map) {
                Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new Result(topic2, map);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Result)) {
                    return false;
                }
                Result result = (Result) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) result.topic) && Intrinsics.areEqual((Object) this.namespaces, (Object) result.namespaces);
            }

            @NotNull
            public final Map<String, Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @NotNull
            public final Topic getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.namespaces.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                Topic topic2 = this.topic;
                Map<String, Namespace.Session> map = this.namespaces;
                return "Result(topic=" + topic2 + ", namespaces=" + map + ")";
            }
        }

        public /* synthetic */ SessionUpdateNamespacesResponse(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private SessionUpdateNamespacesResponse() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse;", "Lcom/reown/sign/engine/model/EngineDO;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class SettledSessionResponse extends EngineDO implements EngineEvent {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse$Error;", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends SettledSessionResponse {
            @NotNull
            private final String errorMessage;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                this.errorMessage = str;
            }

            public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = error.errorMessage;
                }
                return error.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.errorMessage;
            }

            @NotNull
            public final Error copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                return new Error(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
            }

            @NotNull
            public final String getErrorMessage() {
                return this.errorMessage;
            }

            public int hashCode() {
                return this.errorMessage.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Error(errorMessage=", this.errorMessage, ")");
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse$Result;", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse;", "settledSession", "Lcom/reown/sign/engine/model/EngineDO$Session;", "<init>", "(Lcom/reown/sign/engine/model/EngineDO$Session;)V", "getSettledSession", "()Lcom/reown/sign/engine/model/EngineDO$Session;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Result extends SettledSessionResponse {
            @NotNull
            private final Session settledSession;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Result(@NotNull Session session) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(session, "settledSession");
                this.settledSession = session;
            }

            public static /* synthetic */ Result copy$default(Result result, Session session, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    session = result.settledSession;
                }
                return result.copy(session);
            }

            @NotNull
            public final Session component1() {
                return this.settledSession;
            }

            @NotNull
            public final Result copy(@NotNull Session session) {
                Intrinsics.checkNotNullParameter(session, "settledSession");
                return new Result(session);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Result) && Intrinsics.areEqual((Object) this.settledSession, (Object) ((Result) obj).settledSession);
            }

            @NotNull
            public final Session getSettledSession() {
                return this.settledSession;
            }

            public int hashCode() {
                return this.settledSession.hashCode();
            }

            @NotNull
            public String toString() {
                Session session = this.settledSession;
                return "Result(settledSession=" + session + ")";
            }
        }

        public /* synthetic */ SettledSessionResponse(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private SettledSessionResponse() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\t\u0010\u0014¨\u0006#"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "Lcom/reown/sign/engine/model/EngineDO;", "id", "", "origin", "", "validation", "Lcom/reown/android/internal/common/model/Validation;", "verifyUrl", "isScam", "", "<init>", "(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getOrigin", "()Ljava/lang/String;", "getValidation", "()Lcom/reown/android/internal/common/model/Validation;", "getVerifyUrl", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "equals", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class VerifyContext extends EngineDO {
        private final long id;
        @Nullable
        private final Boolean isScam;
        @NotNull
        private final String origin;
        @NotNull
        private final Validation validation;
        @NotNull
        private final String verifyUrl;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VerifyContext(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "origin");
            Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
            Intrinsics.checkNotNullParameter(str2, "verifyUrl");
            this.id = j2;
            this.origin = str;
            this.validation = validation2;
            this.verifyUrl = str2;
            this.isScam = bool;
        }

        public static /* synthetic */ VerifyContext copy$default(VerifyContext verifyContext, long j2, String str, Validation validation2, String str2, Boolean bool, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = verifyContext.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = verifyContext.origin;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                validation2 = verifyContext.validation;
            }
            Validation validation3 = validation2;
            if ((i3 & 8) != 0) {
                str2 = verifyContext.verifyUrl;
            }
            String str4 = str2;
            if ((i3 & 16) != 0) {
                bool = verifyContext.isScam;
            }
            return verifyContext.copy(j3, str3, validation3, str4, bool);
        }

        public final long component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.origin;
        }

        @NotNull
        public final Validation component3() {
            return this.validation;
        }

        @NotNull
        public final String component4() {
            return this.verifyUrl;
        }

        @Nullable
        public final Boolean component5() {
            return this.isScam;
        }

        @NotNull
        public final VerifyContext copy(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "origin");
            Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
            Intrinsics.checkNotNullParameter(str2, "verifyUrl");
            return new VerifyContext(j2, str, validation2, str2, bool);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VerifyContext)) {
                return false;
            }
            VerifyContext verifyContext = (VerifyContext) obj;
            return this.id == verifyContext.id && Intrinsics.areEqual((Object) this.origin, (Object) verifyContext.origin) && this.validation == verifyContext.validation && Intrinsics.areEqual((Object) this.verifyUrl, (Object) verifyContext.verifyUrl) && Intrinsics.areEqual((Object) this.isScam, (Object) verifyContext.isScam);
        }

        public final long getId() {
            return this.id;
        }

        @NotNull
        public final String getOrigin() {
            return this.origin;
        }

        @NotNull
        public final Validation getValidation() {
            return this.validation;
        }

        @NotNull
        public final String getVerifyUrl() {
            return this.verifyUrl;
        }

        public int hashCode() {
            int i3 = a.i(this.verifyUrl, (this.validation.hashCode() + a.i(this.origin, Long.hashCode(this.id) * 31, 31)) * 31, 31);
            Boolean bool = this.isScam;
            return i3 + (bool == null ? 0 : bool.hashCode());
        }

        @Nullable
        public final Boolean isScam() {
            return this.isScam;
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.origin;
            Validation validation2 = this.validation;
            String str2 = this.verifyUrl;
            Boolean bool = this.isScam;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "VerifyContext(id=", ", origin=", str);
            v2.append(", validation=");
            v2.append(validation2);
            v2.append(", verifyUrl=");
            v2.append(str2);
            v2.append(", isScam=");
            v2.append(bool);
            v2.append(")");
            return v2.toString();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/engine/model/EngineDO$WalletConnectUri;", "Lcom/reown/sign/engine/model/EngineDO;", "topic", "Lcom/reown/foundation/common/model/Topic;", "symKey", "Lcom/reown/android/internal/common/model/SymmetricKey;", "relay", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "version", "", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/android/internal/common/model/RelayProtocolOptions;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getSymKey-C2wS6ak", "()Ljava/lang/String;", "Ljava/lang/String;", "getRelay", "()Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "getVersion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WalletConnectUri extends EngineDO {
        @NotNull
        private final RelayProtocolOptions relay;
        @NotNull
        private final String symKey;
        @NotNull
        private final Topic topic;
        @NotNull
        private final String version;

        public /* synthetic */ WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, DefaultConstructorMarker defaultConstructorMarker) {
            this(topic2, str, relayProtocolOptions, str2);
        }

        @NotNull
        public final RelayProtocolOptions getRelay() {
            return this.relay;
        }

        @NotNull
        /* renamed from: getSymKey-C2wS6ak  reason: not valid java name */
        public final String m8882getSymKeyC2wS6ak() {
            return this.symKey;
        }

        @NotNull
        public final Topic getTopic() {
            return this.topic;
        }

        @NotNull
        public final String getVersion() {
            return this.version;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(topic2, str, relayProtocolOptions, (i3 & 8) != 0 ? "2" : str2, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str, "symKey");
            Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
            Intrinsics.checkNotNullParameter(str2, "version");
            this.topic = topic2;
            this.symKey = str;
            this.relay = relayProtocolOptions;
            this.version = str2;
        }
    }

    public /* synthetic */ EngineDO(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private EngineDO() {
    }
}
