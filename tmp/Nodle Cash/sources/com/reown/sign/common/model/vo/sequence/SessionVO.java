package com.reown.sign.common.model.vo.sequence;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.type.Sequence;
import com.reown.android.internal.utils.Expiration;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.clientsync.common.SessionParticipant;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b;\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \\2\u00020\u0001:\u0001\\Bé\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0011\u0012\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011\u0012\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011\u0012\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0007\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0007HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0012\u0010C\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\bD\u0010$J\u0010\u0010E\u001a\u00020\nHÆ\u0003¢\u0006\u0004\bF\u0010$J\u000b\u0010G\u001a\u0004\u0018\u00010\rHÆ\u0003J\u0012\u0010H\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\bI\u0010$J\u000b\u0010J\u001a\u0004\u0018\u00010\rHÆ\u0003J\u0015\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\u0015\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0011HÆ\u0003J\u0017\u0010M\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011HÆ\u0003J\u0017\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011HÆ\u0003J\u0017\u0010O\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011HÆ\u0003J\t\u0010P\u001a\u00020\u0019HÆ\u0003J\t\u0010Q\u001a\u00020\u0007HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u0002\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u00112\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u00112\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00112\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00112\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÆ\u0001¢\u0006\u0004\bT\u0010UJ\u0013\u0010V\u001a\u00020\u00192\b\u0010W\u001a\u0004\u0018\u00010XHÖ\u0003J\t\u0010Y\u001a\u00020ZHÖ\u0001J\t\u0010[\u001a\u00020\u0007HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010'\u001a\u0004\b&\u0010$R\u0013\u0010\u000b\u001a\u00020\n¢\u0006\n\n\u0002\u0010'\u001a\u0004\b(\u0010$R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010'\u001a\u0004\b+\u0010$R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0011¢\u0006\b\n\u0000\u001a\u0004\b/\u0010.R\u001f\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u001f\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b1\u0010.R\u001f\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b2\u0010.R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u00103R\u0011\u0010\u001a\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b4\u0010$R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u00107\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b7\u00103R\u0011\u00108\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b8\u00103R\u0015\u00109\u001a\u0004\u0018\u00010\u0019¢\u0006\n\n\u0002\u0010<\u001a\u0004\b:\u0010;R\u0013\u0010=\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b>\u0010$¨\u0006]"}, d2 = {"Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "Lcom/reown/android/internal/common/model/type/Sequence;", "topic", "Lcom/reown/foundation/common/model/Topic;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "relayProtocol", "", "relayData", "controllerKey", "Lcom/reown/foundation/common/model/PublicKey;", "selfPublicKey", "selfAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "peerPublicKey", "peerAppMetaData", "sessionNamespaces", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "requiredNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "optionalNamespaces", "properties", "scopedProperties", "isAcknowledged", "", "pairingTopic", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZLjava/lang/String;Lcom/reown/android/internal/common/model/TransportType;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getRelayProtocol", "()Ljava/lang/String;", "getRelayData", "getControllerKey-eO6xuyU", "Ljava/lang/String;", "getSelfPublicKey-uN_RPug", "getSelfAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "getPeerPublicKey-eO6xuyU", "getPeerAppMetaData", "getSessionNamespaces", "()Ljava/util/Map;", "getRequiredNamespaces", "getOptionalNamespaces", "getProperties", "getScopedProperties", "()Z", "getPairingTopic", "getTransportType", "()Lcom/reown/android/internal/common/model/TransportType;", "isPeerController", "isSelfController", "peerLinkMode", "getPeerLinkMode", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "peerAppLink", "getPeerAppLink", "component1", "component2", "component3", "component4", "component5", "component5-eO6xuyU", "component6", "component6-uN_RPug", "component7", "component8", "component8-eO6xuyU", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "copy-pMwxKLQ", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZLjava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "equals", "other", "", "hashCode", "", "toString", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionVO implements Sequence {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Nullable
    private final String controllerKey;
    @NotNull
    private final Expiry expiry;
    private final boolean isAcknowledged;
    private final boolean isPeerController;
    private final boolean isSelfController;
    @Nullable
    private final Map<String, Namespace.Proposal> optionalNamespaces;
    @NotNull
    private final String pairingTopic;
    @Nullable
    private final String peerAppLink;
    @Nullable
    private final AppMetaData peerAppMetaData;
    @Nullable
    private final Boolean peerLinkMode;
    @Nullable
    private final String peerPublicKey;
    @Nullable
    private final Map<String, String> properties;
    @Nullable
    private final String relayData;
    @NotNull
    private final String relayProtocol;
    @NotNull
    private final Map<String, Namespace.Proposal> requiredNamespaces;
    @Nullable
    private final Map<String, String> scopedProperties;
    @Nullable
    private final AppMetaData selfAppMetaData;
    @NotNull
    private final String selfPublicKey;
    @NotNull
    private final Map<String, Namespace.Session> sessionNamespaces;
    @NotNull
    private final Topic topic;
    @Nullable
    private final TransportType transportType;

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jw\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0015Ja\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001e0\u000f2\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0010H\u0000¢\u0006\u0004\b \u0010!J{\u0010\"\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010%\u001a\u0004\u0018\u00010\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001e0\u000f2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020'0\u000f2\u0006\u0010\u0014\u001a\u00020\u00102\b\u0010(\u001a\u0004\u0018\u00010)H\u0000¢\u0006\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/reown/sign/common/model/vo/sequence/SessionVO$Companion;", "", "<init>", "()V", "createUnacknowledgedSession", "Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "sessionTopic", "Lcom/reown/foundation/common/model/Topic;", "proposal", "Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", "selfParticipant", "Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;", "sessionExpiry", "", "namespaces", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "scopedProperties", "sessionProperties", "pairingTopic", "createUnacknowledgedSession$sign_release", "createAcknowledgedSession", "settleParams", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "selfPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "selfMetadata", "Lcom/reown/android/internal/common/model/AppMetaData;", "requiredNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "optionalNamespaces", "createAcknowledgedSession-Ol7oN5g$sign_release", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;)Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "createAuthenticatedSession", "peerPublicKey", "peerMetadata", "controllerKey", "sessionNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Session;", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "createAuthenticatedSession-tF0nsiE$sign_release", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ SessionVO createUnacknowledgedSession$sign_release$default(Companion companion, Topic topic, ProposalVO proposalVO, SessionParticipant sessionParticipant, long j2, Map map, Map map2, Map map3, String str, int i3, Object obj) {
            return companion.createUnacknowledgedSession$sign_release(topic, proposalVO, sessionParticipant, j2, map, (i3 & 32) != 0 ? null : map2, map3, str);
        }

        /* renamed from: createAcknowledgedSession-Ol7oN5g$sign_release  reason: not valid java name */
        public final /* synthetic */ SessionVO m8879createAcknowledgedSessionOl7oN5g$sign_release(Topic topic, SignParams.SessionSettleParams sessionSettleParams, String str, AppMetaData appMetaData, Map map, Map map2, String str2) {
            Intrinsics.checkNotNullParameter(topic, "sessionTopic");
            Intrinsics.checkNotNullParameter(sessionSettleParams, "settleParams");
            Intrinsics.checkNotNullParameter(str, "selfPublicKey");
            Intrinsics.checkNotNullParameter(appMetaData, "selfMetadata");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(str2, "pairingTopic");
            Expiry expiry = r0;
            Expiry expiry2 = new Expiry(sessionSettleParams.getExpiry());
            String protocol = sessionSettleParams.getRelay().getProtocol();
            String data = sessionSettleParams.getRelay().getData();
            String r8 = PublicKey.m8856constructorimpl(sessionSettleParams.getController().getPublicKey());
            AppMetaData metadata = sessionSettleParams.getController().getMetadata();
            return new SessionVO(topic, expiry, protocol, data, PublicKey.m8856constructorimpl(sessionSettleParams.getController().getPublicKey()), str, appMetaData, r8, metadata, sessionSettleParams.getNamespaces(), map, map2, sessionSettleParams.getProperties(), sessionSettleParams.getScopedProperties(), true, str2, TransportType.RELAY, (DefaultConstructorMarker) null);
        }

        /* renamed from: createAuthenticatedSession-tF0nsiE$sign_release  reason: not valid java name */
        public final /* synthetic */ SessionVO m8880createAuthenticatedSessiontF0nsiE$sign_release(Topic topic, String str, AppMetaData appMetaData, String str2, AppMetaData appMetaData2, String str3, Map map, Map map2, String str4, TransportType transportType) {
            String str5 = str2;
            AppMetaData appMetaData3 = appMetaData2;
            String str6 = str3;
            Map map3 = map2;
            Intrinsics.checkNotNullParameter(topic, "sessionTopic");
            Intrinsics.checkNotNullParameter(str, "peerPublicKey");
            Intrinsics.checkNotNullParameter(appMetaData, "peerMetadata");
            Intrinsics.checkNotNullParameter(str2, "selfPublicKey");
            Intrinsics.checkNotNullParameter(appMetaData2, "selfMetadata");
            Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
            Intrinsics.checkNotNullParameter(map2, "sessionNamespaces");
            Intrinsics.checkNotNullParameter(str4, "pairingTopic");
            Expiry expiry = r0;
            Expiry expiry2 = new Expiry(Expiration.getACTIVE_SESSION());
            return new SessionVO(topic, expiry, "irn", (String) null, str6, str5, appMetaData3, str, appMetaData, map3, map, (Map) null, (Map) null, (Map) null, true, str4, transportType, 12288, (DefaultConstructorMarker) null);
        }

        public final /* synthetic */ SessionVO createUnacknowledgedSession$sign_release(Topic topic, ProposalVO proposalVO, SessionParticipant sessionParticipant, long j2, Map map, Map map2, Map map3, String str) {
            Map map4 = map3;
            Intrinsics.checkNotNullParameter(topic, "sessionTopic");
            Intrinsics.checkNotNullParameter(proposalVO, "proposal");
            Intrinsics.checkNotNullParameter(sessionParticipant, "selfParticipant");
            Intrinsics.checkNotNullParameter(map, "namespaces");
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Expiry expiry = r0;
            Expiry expiry2 = new Expiry(j2);
            String relayProtocol = proposalVO.getRelayProtocol();
            String relayData = proposalVO.getRelayData();
            String r8 = PublicKey.m8856constructorimpl(proposalVO.getProposerPublicKey());
            AppMetaData appMetaData = proposalVO.getAppMetaData();
            String r6 = PublicKey.m8856constructorimpl(sessionParticipant.getPublicKey());
            AppMetaData metadata = sessionParticipant.getMetadata();
            return new SessionVO(topic, expiry, relayProtocol, relayData, PublicKey.m8856constructorimpl(sessionParticipant.getPublicKey()), r6, metadata, r8, appMetaData, EngineMapperKt.toMapOfNamespacesVOSession(map), proposalVO.getRequiredNamespaces(), proposalVO.getOptionalNamespaces(), map4, map2, false, str, TransportType.RELAY, (DefaultConstructorMarker) null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SessionVO(Topic topic2, Expiry expiry2, String str, String str2, String str3, String str4, AppMetaData appMetaData, String str5, AppMetaData appMetaData2, Map map, Map map2, Map map3, Map map4, Map map5, boolean z2, String str6, TransportType transportType2, DefaultConstructorMarker defaultConstructorMarker) {
        this(topic2, expiry2, str, str2, str3, str4, appMetaData, str5, appMetaData2, map, map2, map3, map4, map5, z2, str6, transportType2);
    }

    /* renamed from: copy-pMwxKLQ$default  reason: not valid java name */
    public static /* synthetic */ SessionVO m8871copypMwxKLQ$default(SessionVO sessionVO, Topic topic2, Expiry expiry2, String str, String str2, String str3, String str4, AppMetaData appMetaData, String str5, AppMetaData appMetaData2, Map map, Map map2, Map map3, Map map4, Map map5, boolean z2, String str6, TransportType transportType2, int i3, Object obj) {
        SessionVO sessionVO2 = sessionVO;
        int i4 = i3;
        return sessionVO.m8875copypMwxKLQ((i4 & 1) != 0 ? sessionVO2.topic : topic2, (i4 & 2) != 0 ? sessionVO2.expiry : expiry2, (i4 & 4) != 0 ? sessionVO2.relayProtocol : str, (i4 & 8) != 0 ? sessionVO2.relayData : str2, (i4 & 16) != 0 ? sessionVO2.controllerKey : str3, (i4 & 32) != 0 ? sessionVO2.selfPublicKey : str4, (i4 & 64) != 0 ? sessionVO2.selfAppMetaData : appMetaData, (i4 & 128) != 0 ? sessionVO2.peerPublicKey : str5, (i4 & 256) != 0 ? sessionVO2.peerAppMetaData : appMetaData2, (i4 & 512) != 0 ? sessionVO2.sessionNamespaces : map, (i4 & 1024) != 0 ? sessionVO2.requiredNamespaces : map2, (i4 & 2048) != 0 ? sessionVO2.optionalNamespaces : map3, (i4 & 4096) != 0 ? sessionVO2.properties : map4, (i4 & 8192) != 0 ? sessionVO2.scopedProperties : map5, (i4 & 16384) != 0 ? sessionVO2.isAcknowledged : z2, (i4 & 32768) != 0 ? sessionVO2.pairingTopic : str6, (i4 & 65536) != 0 ? sessionVO2.transportType : transportType2);
    }

    @NotNull
    public final Topic component1() {
        return this.topic;
    }

    @NotNull
    public final Map<String, Namespace.Session> component10() {
        return this.sessionNamespaces;
    }

    @NotNull
    public final Map<String, Namespace.Proposal> component11() {
        return this.requiredNamespaces;
    }

    @Nullable
    public final Map<String, Namespace.Proposal> component12() {
        return this.optionalNamespaces;
    }

    @Nullable
    public final Map<String, String> component13() {
        return this.properties;
    }

    @Nullable
    public final Map<String, String> component14() {
        return this.scopedProperties;
    }

    public final boolean component15() {
        return this.isAcknowledged;
    }

    @NotNull
    public final String component16() {
        return this.pairingTopic;
    }

    @Nullable
    public final TransportType component17() {
        return this.transportType;
    }

    @NotNull
    public final Expiry component2() {
        return this.expiry;
    }

    @NotNull
    public final String component3() {
        return this.relayProtocol;
    }

    @Nullable
    public final String component4() {
        return this.relayData;
    }

    @Nullable
    /* renamed from: component5-eO6xuyU  reason: not valid java name */
    public final String m8872component5eO6xuyU() {
        return this.controllerKey;
    }

    @NotNull
    /* renamed from: component6-uN_RPug  reason: not valid java name */
    public final String m8873component6uN_RPug() {
        return this.selfPublicKey;
    }

    @Nullable
    public final AppMetaData component7() {
        return this.selfAppMetaData;
    }

    @Nullable
    /* renamed from: component8-eO6xuyU  reason: not valid java name */
    public final String m8874component8eO6xuyU() {
        return this.peerPublicKey;
    }

    @Nullable
    public final AppMetaData component9() {
        return this.peerAppMetaData;
    }

    @NotNull
    /* renamed from: copy-pMwxKLQ  reason: not valid java name */
    public final SessionVO m8875copypMwxKLQ(@NotNull Topic topic2, @NotNull Expiry expiry2, @NotNull String str, @Nullable String str2, @Nullable String str3, @NotNull String str4, @Nullable AppMetaData appMetaData, @Nullable String str5, @Nullable AppMetaData appMetaData2, @NotNull Map<String, Namespace.Session> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, Namespace.Proposal> map3, @Nullable Map<String, String> map4, @Nullable Map<String, String> map5, boolean z2, @NotNull String str6, @Nullable TransportType transportType2) {
        Topic topic3 = topic2;
        Intrinsics.checkNotNullParameter(topic3, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(expiry2, "expiry");
        Intrinsics.checkNotNullParameter(str, "relayProtocol");
        Intrinsics.checkNotNullParameter(str4, "selfPublicKey");
        Intrinsics.checkNotNullParameter(map, "sessionNamespaces");
        Intrinsics.checkNotNullParameter(map2, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(str6, "pairingTopic");
        return new SessionVO(topic3, expiry2, str, str2, str3, str4, appMetaData, str5, appMetaData2, map, map2, map3, map4, map5, z2, str6, transportType2, (DefaultConstructorMarker) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d5 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.reown.sign.common.model.vo.sequence.SessionVO
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.reown.sign.common.model.vo.sequence.SessionVO r5 = (com.reown.sign.common.model.vo.sequence.SessionVO) r5
            com.reown.foundation.common.model.Topic r1 = r4.topic
            com.reown.foundation.common.model.Topic r3 = r5.topic
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0017
            return r2
        L_0x0017:
            com.reown.android.internal.common.model.Expiry r1 = r4.expiry
            com.reown.android.internal.common.model.Expiry r3 = r5.expiry
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0022
            return r2
        L_0x0022:
            java.lang.String r1 = r4.relayProtocol
            java.lang.String r3 = r5.relayProtocol
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x002d
            return r2
        L_0x002d:
            java.lang.String r1 = r4.relayData
            java.lang.String r3 = r5.relayData
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0038
            return r2
        L_0x0038:
            java.lang.String r1 = r4.controllerKey
            java.lang.String r3 = r5.controllerKey
            if (r1 != 0) goto L_0x0044
            if (r3 != 0) goto L_0x0042
            r1 = r0
            goto L_0x004b
        L_0x0042:
            r1 = r2
            goto L_0x004b
        L_0x0044:
            if (r3 != 0) goto L_0x0047
            goto L_0x0042
        L_0x0047:
            boolean r1 = com.reown.foundation.common.model.PublicKey.m8858equalsimpl0(r1, r3)
        L_0x004b:
            if (r1 != 0) goto L_0x004e
            return r2
        L_0x004e:
            java.lang.String r1 = r4.selfPublicKey
            java.lang.String r3 = r5.selfPublicKey
            boolean r1 = com.reown.foundation.common.model.PublicKey.m8858equalsimpl0(r1, r3)
            if (r1 != 0) goto L_0x0059
            return r2
        L_0x0059:
            com.reown.android.internal.common.model.AppMetaData r1 = r4.selfAppMetaData
            com.reown.android.internal.common.model.AppMetaData r3 = r5.selfAppMetaData
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0064
            return r2
        L_0x0064:
            java.lang.String r1 = r4.peerPublicKey
            java.lang.String r3 = r5.peerPublicKey
            if (r1 != 0) goto L_0x0070
            if (r3 != 0) goto L_0x006e
            r1 = r0
            goto L_0x0077
        L_0x006e:
            r1 = r2
            goto L_0x0077
        L_0x0070:
            if (r3 != 0) goto L_0x0073
            goto L_0x006e
        L_0x0073:
            boolean r1 = com.reown.foundation.common.model.PublicKey.m8858equalsimpl0(r1, r3)
        L_0x0077:
            if (r1 != 0) goto L_0x007a
            return r2
        L_0x007a:
            com.reown.android.internal.common.model.AppMetaData r1 = r4.peerAppMetaData
            com.reown.android.internal.common.model.AppMetaData r3 = r5.peerAppMetaData
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0085
            return r2
        L_0x0085:
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Session> r1 = r4.sessionNamespaces
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Session> r3 = r5.sessionNamespaces
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x0090
            return r2
        L_0x0090:
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Proposal> r1 = r4.requiredNamespaces
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Proposal> r3 = r5.requiredNamespaces
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x009b
            return r2
        L_0x009b:
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Proposal> r1 = r4.optionalNamespaces
            java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Proposal> r3 = r5.optionalNamespaces
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x00a6
            return r2
        L_0x00a6:
            java.util.Map<java.lang.String, java.lang.String> r1 = r4.properties
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.properties
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x00b1
            return r2
        L_0x00b1:
            java.util.Map<java.lang.String, java.lang.String> r1 = r4.scopedProperties
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.scopedProperties
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x00bc
            return r2
        L_0x00bc:
            boolean r1 = r4.isAcknowledged
            boolean r3 = r5.isAcknowledged
            if (r1 == r3) goto L_0x00c3
            return r2
        L_0x00c3:
            java.lang.String r1 = r4.pairingTopic
            java.lang.String r3 = r5.pairingTopic
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 != 0) goto L_0x00ce
            return r2
        L_0x00ce:
            com.reown.android.internal.common.model.TransportType r4 = r4.transportType
            com.reown.android.internal.common.model.TransportType r5 = r5.transportType
            if (r4 == r5) goto L_0x00d5
            return r2
        L_0x00d5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.model.vo.sequence.SessionVO.equals(java.lang.Object):boolean");
    }

    @Nullable
    /* renamed from: getControllerKey-eO6xuyU  reason: not valid java name */
    public final String m8876getControllerKeyeO6xuyU() {
        return this.controllerKey;
    }

    @NotNull
    public Expiry getExpiry() {
        return this.expiry;
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
    public final String getPeerAppLink() {
        return this.peerAppLink;
    }

    @Nullable
    public final AppMetaData getPeerAppMetaData() {
        return this.peerAppMetaData;
    }

    @Nullable
    public final Boolean getPeerLinkMode() {
        return this.peerLinkMode;
    }

    @Nullable
    /* renamed from: getPeerPublicKey-eO6xuyU  reason: not valid java name */
    public final String m8877getPeerPublicKeyeO6xuyU() {
        return this.peerPublicKey;
    }

    @Nullable
    public final Map<String, String> getProperties() {
        return this.properties;
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

    @Nullable
    public final AppMetaData getSelfAppMetaData() {
        return this.selfAppMetaData;
    }

    @NotNull
    /* renamed from: getSelfPublicKey-uN_RPug  reason: not valid java name */
    public final String m8878getSelfPublicKeyuN_RPug() {
        return this.selfPublicKey;
    }

    @NotNull
    public final Map<String, Namespace.Session> getSessionNamespaces() {
        return this.sessionNamespaces;
    }

    @NotNull
    public Topic getTopic() {
        return this.topic;
    }

    @Nullable
    public final TransportType getTransportType() {
        return this.transportType;
    }

    public int hashCode() {
        int i3 = a.i(this.relayProtocol, (this.expiry.hashCode() + (this.topic.hashCode() * 31)) * 31, 31);
        String str = this.relayData;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.controllerKey;
        int r2 = (PublicKey.m8860hashCodeimpl(this.selfPublicKey) + ((hashCode + (str2 == null ? 0 : PublicKey.m8860hashCodeimpl(str2))) * 31)) * 31;
        AppMetaData appMetaData = this.selfAppMetaData;
        int hashCode2 = (r2 + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31;
        String str3 = this.peerPublicKey;
        int r22 = (hashCode2 + (str3 == null ? 0 : PublicKey.m8860hashCodeimpl(str3))) * 31;
        AppMetaData appMetaData2 = this.peerAppMetaData;
        int d2 = b.d(this.requiredNamespaces, b.d(this.sessionNamespaces, (r22 + (appMetaData2 == null ? 0 : appMetaData2.hashCode())) * 31, 31), 31);
        Map<String, Namespace.Proposal> map = this.optionalNamespaces;
        int hashCode3 = (d2 + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, String> map2 = this.properties;
        int hashCode4 = (hashCode3 + (map2 == null ? 0 : map2.hashCode())) * 31;
        Map<String, String> map3 = this.scopedProperties;
        int i5 = a.i(this.pairingTopic, android.support.v4.media.session.a.f(this.isAcknowledged, (hashCode4 + (map3 == null ? 0 : map3.hashCode())) * 31, 31), 31);
        TransportType transportType2 = this.transportType;
        if (transportType2 != null) {
            i4 = transportType2.hashCode();
        }
        return i5 + i4;
    }

    public final boolean isAcknowledged() {
        return this.isAcknowledged;
    }

    public final boolean isPeerController() {
        return this.isPeerController;
    }

    public final boolean isSelfController() {
        return this.isSelfController;
    }

    @NotNull
    public String toString() {
        Topic topic2 = this.topic;
        Expiry expiry2 = this.expiry;
        String str = this.relayProtocol;
        String str2 = this.relayData;
        String str3 = this.controllerKey;
        String str4 = AbstractJsonLexerKt.NULL;
        String r5 = str3 == null ? str4 : PublicKey.m8861toStringimpl(str3);
        String r7 = PublicKey.m8861toStringimpl(this.selfPublicKey);
        AppMetaData appMetaData = this.selfAppMetaData;
        String str5 = this.peerPublicKey;
        if (str5 != null) {
            str4 = PublicKey.m8861toStringimpl(str5);
        }
        AppMetaData appMetaData2 = this.peerAppMetaData;
        Map<String, Namespace.Session> map = this.sessionNamespaces;
        Map<String, Namespace.Proposal> map2 = this.requiredNamespaces;
        Map<String, Namespace.Proposal> map3 = this.optionalNamespaces;
        Map<String, String> map4 = this.properties;
        Map<String, String> map5 = this.scopedProperties;
        boolean z2 = this.isAcknowledged;
        String str6 = this.pairingTopic;
        TransportType transportType2 = this.transportType;
        StringBuilder sb = new StringBuilder("SessionVO(topic=");
        sb.append(topic2);
        sb.append(", expiry=");
        sb.append(expiry2);
        sb.append(", relayProtocol=");
        b.w(sb, str, ", relayData=", str2, ", controllerKey=");
        b.w(sb, r5, ", selfPublicKey=", r7, ", selfAppMetaData=");
        sb.append(appMetaData);
        sb.append(", peerPublicKey=");
        sb.append(str4);
        sb.append(", peerAppMetaData=");
        sb.append(appMetaData2);
        sb.append(", sessionNamespaces=");
        sb.append(map);
        sb.append(", requiredNamespaces=");
        sb.append(map2);
        sb.append(", optionalNamespaces=");
        sb.append(map3);
        sb.append(", properties=");
        sb.append(map4);
        sb.append(", scopedProperties=");
        sb.append(map5);
        sb.append(", isAcknowledged=");
        sb.append(z2);
        sb.append(", pairingTopic=");
        sb.append(str6);
        sb.append(", transportType=");
        sb.append(transportType2);
        sb.append(")");
        return sb.toString();
    }

    private SessionVO(Topic topic2, Expiry expiry2, String str, String str2, String str3, String str4, AppMetaData appMetaData, String str5, AppMetaData appMetaData2, Map<String, Namespace.Session> map, Map<String, Namespace.Proposal> map2, Map<String, Namespace.Proposal> map3, Map<String, String> map4, Map<String, String> map5, boolean z2, String str6, TransportType transportType2) {
        Redirect redirect;
        Redirect redirect2;
        String str7 = str3;
        String str8 = str4;
        String str9 = str5;
        AppMetaData appMetaData3 = appMetaData2;
        Map<String, Namespace.Session> map6 = map;
        Map<String, Namespace.Proposal> map7 = map2;
        String str10 = str6;
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(expiry2, "expiry");
        Intrinsics.checkNotNullParameter(str, "relayProtocol");
        Intrinsics.checkNotNullParameter(str8, "selfPublicKey");
        Intrinsics.checkNotNullParameter(map6, "sessionNamespaces");
        Intrinsics.checkNotNullParameter(map7, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(str10, "pairingTopic");
        this.topic = topic2;
        this.expiry = expiry2;
        this.relayProtocol = str;
        this.relayData = str2;
        this.controllerKey = str7;
        this.selfPublicKey = str8;
        this.selfAppMetaData = appMetaData;
        this.peerPublicKey = str9;
        this.peerAppMetaData = appMetaData3;
        this.sessionNamespaces = map6;
        this.requiredNamespaces = map7;
        this.optionalNamespaces = map3;
        this.properties = map4;
        this.scopedProperties = map5;
        this.isAcknowledged = z2;
        this.pairingTopic = str10;
        this.transportType = transportType2;
        String str11 = null;
        this.isPeerController = Intrinsics.areEqual((Object) str9 == null ? null : str9, (Object) str7 == null ? null : str7);
        this.isSelfController = Intrinsics.areEqual((Object) str8, (Object) str7 == null ? null : str7);
        this.peerLinkMode = (appMetaData3 == null || (redirect2 = appMetaData2.getRedirect()) == null) ? null : Boolean.valueOf(redirect2.getLinkMode());
        if (!(appMetaData3 == null || (redirect = appMetaData2.getRedirect()) == null)) {
            str11 = redirect.getUniversal();
        }
        this.peerAppLink = str11;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SessionVO(com.reown.foundation.common.model.Topic r23, com.reown.android.internal.common.model.Expiry r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, com.reown.android.internal.common.model.AppMetaData r29, java.lang.String r30, com.reown.android.internal.common.model.AppMetaData r31, java.util.Map r32, java.util.Map r33, java.util.Map r34, java.util.Map r35, java.util.Map r36, boolean r37, java.lang.String r38, com.reown.android.internal.common.model.TransportType r39, int r40, kotlin.jvm.internal.DefaultConstructorMarker r41) {
        /*
            r22 = this;
            r0 = r40
            r1 = r0 & 16
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r8 = r2
            goto L_0x000b
        L_0x0009:
            r8 = r27
        L_0x000b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0011
            r10 = r2
            goto L_0x0013
        L_0x0011:
            r10 = r29
        L_0x0013:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0019
            r11 = r2
            goto L_0x001b
        L_0x0019:
            r11 = r30
        L_0x001b:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0021
            r12 = r2
            goto L_0x0023
        L_0x0021:
            r12 = r31
        L_0x0023:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x002a
            r16 = r2
            goto L_0x002c
        L_0x002a:
            r16 = r35
        L_0x002c:
            r0 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L_0x0033
            r17 = r2
            goto L_0x0035
        L_0x0033:
            r17 = r36
        L_0x0035:
            r21 = 0
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r9 = r28
            r13 = r32
            r14 = r33
            r15 = r34
            r18 = r37
            r19 = r38
            r20 = r39
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.model.vo.sequence.SessionVO.<init>(com.reown.foundation.common.model.Topic, com.reown.android.internal.common.model.Expiry, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.reown.android.internal.common.model.AppMetaData, java.lang.String, com.reown.android.internal.common.model.AppMetaData, java.util.Map, java.util.Map, java.util.Map, java.util.Map, java.util.Map, boolean, java.lang.String, com.reown.android.internal.common.model.TransportType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
