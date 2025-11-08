package com.reown.sign.engine.model.mapper;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SessionProposer;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.cacao.CacaoKt;
import com.reown.android.internal.common.signing.cacao.CacaoType;
import com.reown.android.internal.common.signing.cacao.Issuer;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.exceptions.PeerError;
import com.reown.sign.common.model.vo.clientsync.common.PayloadParams;
import com.reown.sign.common.model.vo.clientsync.common.SessionParticipant;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000¢\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\nH\u0000\u001a\u001e\u0010\u0004\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\f\u0010\u0004\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0000\u001a\f\u0010\u001e\u001a\u00020\u001f*\u00020\u0019H\u0000\u001a\\\u0010 \u001a\u00020!*\u00020\n2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\f2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020'0&2\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010&2\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010&H\u0000\u001a\u0001\u0010*\u001a\u00020\u00062\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&2\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010&2\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010&2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u001dH\u0000¢\u0006\u0004\b6\u00107\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\nH\u0000\u001a\f\u00108\u001a\u000209*\u00020\nH\u0000\u001a\u0012\u0010:\u001a\u00020;*\b\u0012\u0004\u0012\u00020\u00010<H\u0000\u001a\u0012\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020\u0001H\u0002\u001a$\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&H\u0000\u001a$\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&H\u0000\u001a$\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0&H\u0000\u001a$\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020/0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0&H\u0000\u001a$\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020'0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020F0&H\u0000\u001a$\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020F0&*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020'0&H\u0000\u001a\f\u0010\u0004\u001a\u00020H*\u00020IH\u0000\u001a\f\u0010\u0004\u001a\u00020J*\u00020KH\u0000\u001a\u001b\u0010L\u001a\u00020M*\u00020\n2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0004\bN\u0010O\u001a\u0014\u0010\u0004\u001a\u00020P*\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\f\u0010Q\u001a\u00020R*\u00020\u0017H\u0000\u001a\u001c\u0010S\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u00010<2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0000\u001a\f\u0010T\u001a\u00020U*\u00020VH\u0000\u001a\f\u0010\u0004\u001a\u00020W*\u00020XH\u0000\u001a\f\u0010\u0004\u001a\u00020Y*\u00020ZH\u0000\u001a\f\u0010[\u001a\u00020\\*\u00020]H\u0000\u001a\f\u0010\u0004\u001a\u00020^*\u00020\\H\u0000\u001a\u0014\u0010_\u001a\u00020`*\u00020^2\u0006\u0010a\u001a\u00020bH\u0000\u001a\u001c\u0010c\u001a\u00020\u0001*\u00020^2\u0006\u0010a\u001a\u00020b2\u0006\u0010d\u001a\u00020\u0001H\u0000¨\u0006e"}, d2 = {"toAbsoluteString", "", "Lcom/reown/sign/engine/model/EngineDO$WalletConnectUri;", "getQuery", "toEngineDO", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "topic", "Lcom/reown/foundation/common/model/Topic;", "toVO", "Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", "requestId", "", "toSessionProposeRequest", "Lcom/reown/android/internal/common/model/WCRequest;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "request", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "Lcom/reown/sign/engine/model/EngineDO$SessionDelete;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "Lcom/reown/sign/engine/model/EngineDO$SessionEvent;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;", "Lcom/reown/sign/engine/model/EngineDO$Session;", "Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "toEngineDOSessionExtend", "Lcom/reown/sign/engine/model/EngineDO$SessionExtend;", "expiryVO", "Lcom/reown/android/internal/common/model/Expiry;", "toSessionApproved", "Lcom/reown/sign/engine/model/EngineDO$SessionApproved;", "toSessionSettleParams", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "selfParticipant", "Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;", "sessionExpiry", "namespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "sessionProperties", "scopedProperties", "toSessionProposeParams", "relays", "", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "requiredNamespaces", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "properties", "selfPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "appMetaData", "expiry", "toSessionProposeParams-n2MeESo", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/android/internal/common/model/Expiry;)Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "toExpiredProposal", "Lcom/reown/sign/engine/model/EngineDO$ExpiredProposal;", "toExpiredSessionRequest", "Lcom/reown/sign/engine/model/EngineDO$ExpiredRequest;", "Lcom/reown/sign/common/model/Request;", "convertToURI", "Ljava/net/URI;", "it", "toNamespacesVORequired", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "toNamespacesVOOptional", "toMapOfEngineNamespacesRequired", "toMapOfEngineNamespacesOptional", "toMapOfEngineNamespacesSession", "Lcom/reown/android/internal/common/model/Namespace$Session;", "toMapOfNamespacesVOSession", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcError;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "toSessionApproveParams", "Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "toSessionApproveParams-zo5FKG8", "(Lcom/reown/sign/common/model/vo/proposal/ProposalVO;Ljava/lang/String;)Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "Lcom/reown/sign/engine/model/EngineDO$Request;", "toEngineDOEvent", "Lcom/reown/sign/engine/model/EngineDO$Event;", "toSessionRequest", "toPeerError", "Lcom/reown/sign/common/exceptions/PeerError;", "Lcom/reown/sign/engine/model/ValidationError;", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "Lcom/reown/android/verify/model/VerifyContext;", "Lcom/reown/sign/engine/model/EngineDO$Participant;", "Lcom/reown/sign/common/model/vo/clientsync/common/Requester;", "toCommon", "Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "toCacaoPayload", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "iss", "Lcom/reown/android/internal/common/signing/cacao/Issuer;", "toCAIP222Message", "chainName", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEngineMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EngineMapper.kt\ncom/reown/sign/engine/model/mapper/EngineMapperKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,367:1\n1617#2,9:368\n1869#2:377\n1870#2:379\n1626#2:380\n1617#2,9:387\n1869#2:396\n1870#2:398\n1626#2:399\n1252#2,4:402\n1252#2,4:408\n1252#2,4:414\n1252#2,4:420\n1252#2,4:426\n1252#2,4:432\n1#3:378\n1#3:397\n77#4:381\n97#4,5:382\n463#5:400\n413#5:401\n463#5:406\n413#5:407\n463#5:412\n413#5:413\n463#5:418\n413#5:419\n463#5:424\n413#5:425\n463#5:430\n413#5:431\n*S KotlinDebug\n*F\n+ 1 EngineMapper.kt\ncom/reown/sign/engine/model/mapper/EngineMapperKt\n*L\n56#1:368,9\n56#1:377\n56#1:379\n56#1:380\n203#1:387,9\n203#1:396\n203#1:398\n203#1:399\n228#1:402,4\n234#1:408,4\n240#1:414,4\n246#1:420,4\n252#1:426,4\n258#1:432,4\n56#1:378\n203#1:397\n155#1:381\n155#1:382,5\n228#1:400\n228#1:401\n234#1:406\n234#1:407\n240#1:412\n240#1:413\n246#1:418\n246#1:419\n252#1:424\n252#1:425\n258#1:430\n258#1:431\n*E\n"})
public final /* synthetic */ class EngineMapperKt {
    private static final URI convertToURI(String str) {
        try {
            return new URI(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private static final String getQuery(EngineDO.WalletConnectUri walletConnectUri) {
        String a2 = c.a("relay-protocol=", walletConnectUri.getRelay().getProtocol());
        return walletConnectUri.getRelay().getData() != null ? a.n(a2, "&relay-data=", walletConnectUri.getRelay().getData()) : a2;
    }

    public static final /* synthetic */ String toAbsoluteString(EngineDO.WalletConnectUri walletConnectUri) {
        Intrinsics.checkNotNullParameter(walletConnectUri, "<this>");
        String value = walletConnectUri.getTopic().getValue();
        String version = walletConnectUri.getVersion();
        return C0118y.j(C0118y.l("wc:", value, "@", version, "?"), getQuery(walletConnectUri), "&symKey=", walletConnectUri.m8882getSymKeyC2wS6ak());
    }

    public static final /* synthetic */ String toCAIP222Message(EngineDO.PayloadParams payloadParams, Issuer issuer, String str) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        Intrinsics.checkNotNullParameter(issuer, "iss");
        Intrinsics.checkNotNullParameter(str, "chainName");
        return CacaoKt.toCAIP222Message(toCacaoPayload(payloadParams, issuer), str);
    }

    public static final /* synthetic */ Cacao.Payload toCacaoPayload(EngineDO.PayloadParams payloadParams, Issuer issuer) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        Intrinsics.checkNotNullParameter(issuer, "iss");
        return new Cacao.Payload(issuer.getValue(), payloadParams.getDomain(), payloadParams.getAud(), payloadParams.getVersion(), payloadParams.getNonce(), payloadParams.getIat(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources());
    }

    public static final /* synthetic */ PayloadParams toCommon(EngineDO.Authenticate authenticate) {
        Intrinsics.checkNotNullParameter(authenticate, "<this>");
        String domain = authenticate.getDomain();
        String aud = authenticate.getAud();
        String nonce = authenticate.getNonce();
        String nbf = authenticate.getNbf();
        String exp = authenticate.getExp();
        String statement = authenticate.getStatement();
        String requestId = authenticate.getRequestId();
        List<String> resources = authenticate.getResources();
        List<String> chains = authenticate.getChains();
        String type = authenticate.getType();
        if (type == null) {
            type = CacaoType.EIP4361.getHeader();
        }
        String str = type;
        String format = new SimpleDateFormat(Cacao.Payload.ISO_8601_PATTERN).format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new PayloadParams(str, chains, domain, aud, nonce, "1", format, nbf, exp, statement, requestId, resources);
    }

    public static final /* synthetic */ EngineDO.SessionProposal toEngineDO(SignParams.SessionProposeParams sessionProposeParams, Topic topic) {
        String empty;
        Map emptyMap;
        SignParams.SessionProposeParams sessionProposeParams2 = sessionProposeParams;
        Intrinsics.checkNotNullParameter(sessionProposeParams, "<this>");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        String value = topic.getValue();
        String name = sessionProposeParams.getProposer().getMetadata().getName();
        String description = sessionProposeParams.getProposer().getMetadata().getDescription();
        String url = sessionProposeParams.getProposer().getMetadata().getUrl();
        ArrayList arrayList = new ArrayList();
        for (String convertToURI : sessionProposeParams.getProposer().getMetadata().getIcons()) {
            URI convertToURI2 = convertToURI(convertToURI);
            if (convertToURI2 != null) {
                arrayList.add(convertToURI2);
            }
        }
        Redirect redirect = sessionProposeParams.getProposer().getMetadata().getRedirect();
        if (redirect == null || (empty = redirect.getNative()) == null) {
            empty = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        String str = empty;
        Map mapOfEngineNamespacesRequired = toMapOfEngineNamespacesRequired(sessionProposeParams.getRequiredNamespaces());
        Map<String, Namespace.Proposal> optionalNamespaces = sessionProposeParams.getOptionalNamespaces();
        if (optionalNamespaces == null || (emptyMap = toMapOfEngineNamespacesOptional(optionalNamespaces)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        return new EngineDO.SessionProposal(value, name, description, url, arrayList, str, mapOfEngineNamespacesRequired, emptyMap, sessionProposeParams.getProperties(), sessionProposeParams.getProposer().getPublicKey(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getProtocol(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getData(), sessionProposeParams.getScopedProperties());
    }

    public static final /* synthetic */ EngineDO.SessionExtend toEngineDOSessionExtend(SessionVO sessionVO, Expiry expiry) {
        Intrinsics.checkNotNullParameter(sessionVO, "<this>");
        Intrinsics.checkNotNullParameter(expiry, "expiryVO");
        Topic topic = sessionVO.getTopic();
        String pairingTopic = sessionVO.getPairingTopic();
        Map mapOfEngineNamespacesRequired = toMapOfEngineNamespacesRequired(sessionVO.getRequiredNamespaces());
        Map<String, Namespace.Proposal> optionalNamespaces = sessionVO.getOptionalNamespaces();
        return new EngineDO.SessionExtend(topic, expiry, pairingTopic, mapOfEngineNamespacesRequired, optionalNamespaces != null ? toMapOfEngineNamespacesOptional(optionalNamespaces) : null, toMapOfEngineNamespacesSession(sessionVO.getSessionNamespaces()), sessionVO.getSelfAppMetaData());
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesOptional(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Namespace.Proposal proposal = (Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesRequired(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Namespace.Proposal proposal = (Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesSession(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Namespace.Session session = (Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfNamespacesVOSession(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            EngineDO.Namespace.Session session = (EngineDO.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toNamespacesVOOptional(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            EngineDO.Namespace.Proposal proposal = (EngineDO.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Namespace.Proposal(proposal.getMethods(), proposal.getChains(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toNamespacesVORequired(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            EngineDO.Namespace.Proposal proposal = (EngineDO.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Namespace.Proposal(proposal.getMethods(), proposal.getChains(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ PeerError toPeerError(ValidationError validationError) {
        Intrinsics.checkNotNullParameter(validationError, "<this>");
        if (validationError instanceof ValidationError.UnsupportedNamespaceKey) {
            return new PeerError.CAIP25.UnsupportedNamespaceKey(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UnsupportedChains) {
            return new PeerError.CAIP25.UnsupportedChains(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.InvalidEvent) {
            return new PeerError.Invalid.Event(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.InvalidExtendRequest) {
            return new PeerError.Invalid.ExtendRequest(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.InvalidSessionRequest) {
            return new PeerError.Invalid.Method(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UnauthorizedEvent) {
            return new PeerError.Unauthorized.Event(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UnauthorizedMethod) {
            return new PeerError.Unauthorized.Method(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UserRejected) {
            return new PeerError.CAIP25.UserRejected(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UserRejectedEvents) {
            return new PeerError.CAIP25.UserRejectedEvents(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UserRejectedMethods) {
            return new PeerError.CAIP25.UserRejectedMethods(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.UserRejectedChains) {
            return new PeerError.CAIP25.UserRejectedChains(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.InvalidSessionProperties) {
            return new PeerError.CAIP25.InvalidSessionPropertiesObject(validationError.getMessage());
        }
        if (validationError instanceof ValidationError.EmptyNamespaces) {
            return new PeerError.CAIP25.EmptySessionNamespaces(validationError.getMessage());
        }
        throw new NoWhenBranchMatchedException();
    }

    /* renamed from: toSessionApproveParams-zo5FKG8  reason: not valid java name */
    public static final /* synthetic */ CoreSignParams.ApprovalParams m8883toSessionApproveParamszo5FKG8(ProposalVO proposalVO, String str) {
        Intrinsics.checkNotNullParameter(proposalVO, "$this$toSessionApproveParams");
        Intrinsics.checkNotNullParameter(str, "selfPublicKey");
        return new CoreSignParams.ApprovalParams(new RelayProtocolOptions(proposalVO.getRelayProtocol(), proposalVO.getRelayData()), str);
    }

    public static final /* synthetic */ EngineDO.SessionApproved toSessionApproved(SessionVO sessionVO) {
        Intrinsics.checkNotNullParameter(sessionVO, "<this>");
        String value = sessionVO.getTopic().getValue();
        AppMetaData peerAppMetaData = sessionVO.getPeerAppMetaData();
        Map<String, Namespace.Session> sessionNamespaces = sessionVO.getSessionNamespaces();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Namespace.Session> value2 : sessionNamespaces.entrySet()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((Namespace.Session) value2.getValue()).getAccounts());
        }
        return new EngineDO.SessionApproved(value, peerAppMetaData, arrayList, toMapOfEngineNamespacesSession(sessionVO.getSessionNamespaces()));
    }

    /* renamed from: toSessionProposeParams-n2MeESo  reason: not valid java name */
    public static final /* synthetic */ SignParams.SessionProposeParams m8884toSessionProposeParamsn2MeESo(List list, Map map, Map map2, Map map3, Map map4, String str, AppMetaData appMetaData, Expiry expiry) {
        Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
        Intrinsics.checkNotNullParameter(str, "selfPublicKey");
        Intrinsics.checkNotNullParameter(appMetaData, "appMetaData");
        Intrinsics.checkNotNullParameter(expiry, "expiry");
        if (list == null) {
            list = CollectionsKt.listOf(new RelayProtocolOptions((String) null, (String) null, 3, (DefaultConstructorMarker) null));
        }
        SessionProposer sessionProposer = new SessionProposer(str, appMetaData);
        return new SignParams.SessionProposeParams(toNamespacesVORequired(map), toNamespacesVOOptional(map2), list, sessionProposer, map3, map4, Long.valueOf(expiry.getSeconds()));
    }

    public static final /* synthetic */ WCRequest toSessionProposeRequest(ProposalVO proposalVO) {
        Intrinsics.checkNotNullParameter(proposalVO, "<this>");
        Topic pairingTopic = proposalVO.getPairingTopic();
        long requestId = proposalVO.getRequestId();
        List listOf = CollectionsKt.listOf(new RelayProtocolOptions(proposalVO.getRelayProtocol(), proposalVO.getRelayData()));
        SessionProposer sessionProposer = new SessionProposer(proposalVO.getProposerPublicKey(), new AppMetaData(proposalVO.getDescription(), proposalVO.getUrl(), proposalVO.getIcons(), proposalVO.getName(), (Redirect) null, (String) null, 48, (DefaultConstructorMarker) null));
        Map<String, Namespace.Proposal> requiredNamespaces = proposalVO.getRequiredNamespaces();
        Map<String, Namespace.Proposal> optionalNamespaces = proposalVO.getOptionalNamespaces();
        Map<String, String> properties = proposalVO.getProperties();
        Expiry expiry = proposalVO.getExpiry();
        return new WCRequest(pairingTopic, requestId, JsonRpcMethod.WC_SESSION_PROPOSE, new SignParams.SessionProposeParams(requiredNamespaces, optionalNamespaces, listOf, sessionProposer, properties, proposalVO.getScopedProperties(), expiry != null ? Long.valueOf(expiry.getSeconds()) : null), (String) null, 0, (String) null, (String) null, TransportType.RELAY, 240, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ SignParams.SessionSettleParams toSessionSettleParams(ProposalVO proposalVO, SessionParticipant sessionParticipant, long j2, Map map, Map map2, Map map3) {
        Intrinsics.checkNotNullParameter(proposalVO, "<this>");
        Intrinsics.checkNotNullParameter(sessionParticipant, "selfParticipant");
        Intrinsics.checkNotNullParameter(map, "namespaces");
        return new SignParams.SessionSettleParams(new RelayProtocolOptions(proposalVO.getRelayProtocol(), proposalVO.getRelayData()), sessionParticipant, toMapOfNamespacesVOSession(map), j2, map2, map3);
    }

    public static final /* synthetic */ ProposalVO toVO(SignParams.SessionProposeParams sessionProposeParams, Topic topic, long j2) {
        String empty;
        Intrinsics.checkNotNullParameter(sessionProposeParams, "<this>");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        String name = sessionProposeParams.getProposer().getMetadata().getName();
        String description = sessionProposeParams.getProposer().getMetadata().getDescription();
        String url = sessionProposeParams.getProposer().getMetadata().getUrl();
        List<String> icons = sessionProposeParams.getProposer().getMetadata().getIcons();
        Redirect redirect = sessionProposeParams.getProposer().getMetadata().getRedirect();
        if (redirect == null || (empty = redirect.getNative()) == null) {
            empty = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        String str = empty;
        Map<String, Namespace.Proposal> requiredNamespaces = sessionProposeParams.getRequiredNamespaces();
        Map<String, Namespace.Proposal> optionalNamespaces = sessionProposeParams.getOptionalNamespaces();
        if (optionalNamespaces == null) {
            optionalNamespaces = MapsKt.emptyMap();
        }
        return new ProposalVO(j2, topic, name, description, url, icons, str, requiredNamespaces, optionalNamespaces, sessionProposeParams.getProperties(), sessionProposeParams.getScopedProperties(), sessionProposeParams.getProposer().getPublicKey(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getProtocol(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getData(), sessionProposeParams.getExpiryTimestamp() != null ? new Expiry(sessionProposeParams.getExpiryTimestamp().longValue()) : null);
    }

    public static final /* synthetic */ EngineDO.SessionRequest toEngineDO(SignParams.SessionRequestParams sessionRequestParams, WCRequest wCRequest, AppMetaData appMetaData) {
        Intrinsics.checkNotNullParameter(sessionRequestParams, "<this>");
        Intrinsics.checkNotNullParameter(wCRequest, "request");
        return new EngineDO.SessionRequest(wCRequest.getTopic().getValue(), sessionRequestParams.getChainId(), appMetaData, new EngineDO.SessionRequest.JSONRPCRequest(wCRequest.getId(), sessionRequestParams.getRequest().getMethod(), sessionRequestParams.getRequest().getParams()), sessionRequestParams.getRequest().getExpiryTimestamp() != null ? new Expiry(sessionRequestParams.getRequest().getExpiryTimestamp().longValue()) : null);
    }

    public static final /* synthetic */ EngineDO.SessionDelete toEngineDO(SignParams.DeleteParams deleteParams, Topic topic) {
        Intrinsics.checkNotNullParameter(deleteParams, "<this>");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return new EngineDO.SessionDelete(topic.getValue(), deleteParams.getMessage());
    }

    public static final /* synthetic */ EngineDO.SessionEvent toEngineDO(SignParams.EventParams eventParams, Topic topic) {
        Intrinsics.checkNotNullParameter(eventParams, "<this>");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return new EngineDO.SessionEvent(topic.getValue(), eventParams.getEvent().getName(), eventParams.getEvent().getData().toString(), eventParams.getChainId());
    }

    public static final /* synthetic */ EngineDO.Session toEngineDO(SessionVO sessionVO) {
        Intrinsics.checkNotNullParameter(sessionVO, "<this>");
        Topic topic = sessionVO.getTopic();
        Expiry expiry = sessionVO.getExpiry();
        String pairingTopic = sessionVO.getPairingTopic();
        Map mapOfEngineNamespacesRequired = toMapOfEngineNamespacesRequired(sessionVO.getRequiredNamespaces());
        Map<String, Namespace.Proposal> optionalNamespaces = sessionVO.getOptionalNamespaces();
        return new EngineDO.Session(topic, expiry, pairingTopic, mapOfEngineNamespacesRequired, optionalNamespaces != null ? toMapOfEngineNamespacesOptional(optionalNamespaces) : null, toMapOfEngineNamespacesSession(sessionVO.getSessionNamespaces()), sessionVO.getPeerAppMetaData());
    }

    public static final /* synthetic */ EngineDO.SessionProposal toEngineDO(ProposalVO proposalVO) {
        Intrinsics.checkNotNullParameter(proposalVO, "<this>");
        String value = proposalVO.getPairingTopic().getValue();
        String name = proposalVO.getName();
        String description = proposalVO.getDescription();
        String url = proposalVO.getUrl();
        ArrayList arrayList = new ArrayList();
        for (String convertToURI : proposalVO.getIcons()) {
            URI convertToURI2 = convertToURI(convertToURI);
            if (convertToURI2 != null) {
                arrayList.add(convertToURI2);
            }
        }
        String redirect = proposalVO.getRedirect();
        String relayData = proposalVO.getRelayData();
        String relayProtocol = proposalVO.getRelayProtocol();
        return new EngineDO.SessionProposal(value, name, description, url, arrayList, redirect, toMapOfEngineNamespacesRequired(proposalVO.getRequiredNamespaces()), toMapOfEngineNamespacesOptional(proposalVO.getOptionalNamespaces()), proposalVO.getProperties(), proposalVO.getProposerPublicKey(), relayProtocol, relayData, proposalVO.getScopedProperties());
    }

    public static final /* synthetic */ EngineDO.Request toEngineDO(SignParams.SessionRequestParams sessionRequestParams, Topic topic) {
        Intrinsics.checkNotNullParameter(sessionRequestParams, "<this>");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return new EngineDO.Request(topic.getValue(), sessionRequestParams.getRequest().getMethod(), sessionRequestParams.getRequest().getParams(), sessionRequestParams.getChainId(), (Expiry) null, 16, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ EngineDO.PayloadParams toEngineDO(PayloadParams payloadParams) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        String domain = payloadParams.getDomain();
        String aud = payloadParams.getAud();
        return new EngineDO.PayloadParams(payloadParams.getChains(), domain, payloadParams.getNonce(), aud, payloadParams.getType(), payloadParams.getIat(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources(), "1");
    }
}
