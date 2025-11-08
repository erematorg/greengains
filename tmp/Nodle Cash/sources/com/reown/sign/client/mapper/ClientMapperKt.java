package com.reown.sign.client.mapper;

import com.reown.android.Core;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.cacao.CacaoKt;
import com.reown.android.internal.common.signing.cacao.CacaoType;
import com.reown.android.utils.ExtensionsKt;
import com.reown.sign.client.Sign;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.common.PayloadParams;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000°\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\f\u0010\f\u001a\u00020\u0002*\u00020\rH\u0000\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001cH\u0000\u001a\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0000\u001a\u0014\u0010 \u001a\u00020!*\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0000\u001a\u000e\u0010$\u001a\u0004\u0018\u00010#*\u00020\u001cH\u0002\u001a\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&*\b\u0012\u0004\u0012\u00020(0&H\u0000\u001a\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020(0&*\b\u0012\u0004\u0012\u00020'0&H\u0000\u001a\f\u0010*\u001a\u00020+*\u00020,H\u0000\u001a\f\u0010-\u001a\u00020.*\u00020/H\u0000\u001a\u0014\u00100\u001a\u000201*\u0002022\u0006\u00103\u001a\u00020#H\u0000\u001a\f\u00104\u001a\u000205*\u000206H\u0000\u001a\f\u00107\u001a\u000202*\u000208H\u0000\u001a\f\u00109\u001a\u00020:*\u00020;H\u0000\u001a\f\u0010)\u001a\u00020<*\u00020=H\u0000\u001a\f\u0010)\u001a\u00020\u001c*\u00020\u001bH\u0000\u001a\f\u0010>\u001a\u00020?*\u000208H\u0000\u001a\f\u0010@\u001a\u00020A*\u00020BH\u0000\u001a\f\u0010@\u001a\u00020A*\u00020CH\u0000\u001a\f\u0010D\u001a\u00020E*\u00020FH\u0000\u001a\f\u0010G\u001a\u00020H*\u00020IH\u0000\u001a$\u0010J\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020L0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020M0KH\u0000\u001a\f\u0010N\u001a\u00020O*\u00020PH\u0000\u001a\u0014\u0010Q\u001a\u00020R*\u00020P2\u0006\u0010S\u001a\u00020TH\u0000\u001a\f\u0010U\u001a\u00020,*\u00020VH\u0000\u001a\f\u0010W\u001a\u00020X*\u00020YH\u0000\u001a\f\u0010Z\u001a\u00020/*\u00020[H\u0000\u001a\f\u0010\\\u001a\u00020]*\u00020^H\u0000\u001a\u001e\u0010_\u001a\b\u0012\u0004\u0012\u00020`0&*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0a0&H\u0000\u001a\u0018\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00180&*\b\u0012\u0004\u0012\u00020\u00190&H\u0000\u001a\u0012\u0010)\u001a\u00020:*\b\u0012\u0004\u0012\u00020c0aH\u0000\u001a\f\u0010)\u001a\u00020\u001c*\u00020cH\u0000\u001a\f\u0010d\u001a\u00020e*\u00020fH\u0000\u001a$\u0010g\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020h0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020i0KH\u0000\u001a$\u0010j\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020h0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020i0KH\u0000\u001a$\u0010k\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020i0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020h0KH\u0000\u001a$\u0010l\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020M0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020L0KH\u0000\u001a$\u0010m\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020n0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020i0KH\u0000\u001a$\u0010o\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020p0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020L0KH\u0000\u001a$\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020L0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020p0KH\u0000\u001a$\u0010q\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020i0K*\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020r0KH\u0000\u001a\f\u0010s\u001a\u00020t*\u00020uH\u0000\u001a\f\u0010)\u001a\u00020v*\u00020wH\u0000\u001a\f\u0010)\u001a\u00020x*\u00020yH\u0000\u001a\f\u0010z\u001a\u00020{*\u00020|H\u0000\u001a\f\u0010q\u001a\u00020}*\u00020~H\u0000\u001a\r\u0010q\u001a\u00020*\u00030\u0001H\u0000\u001a\r\u0010)\u001a\u00020\u001c*\u00030\u0001H\u0002\u001a\u000e\u0010q\u001a\u00030\u0001*\u00030\u0001H\u0000¨\u0006\u0001"}, d2 = {"toJsonRpcResponse", "Lcom/reown/android/internal/common/JsonRpcResponse;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "toClientSettledSessionResponse", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "Lcom/reown/sign/engine/model/EngineDO$SettledSessionResponse;", "toClientSessionAuthenticateResponse", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateResponse;", "toClientUpdateSessionNamespacesResponse", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespacesResponse;", "toClientJsonRpcResponse", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse;", "toClientSessionProposal", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "toCore", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "toClientValidation", "Lcom/reown/sign/client/Sign$Model$Validation;", "Lcom/reown/android/internal/common/model/Validation;", "toClientSessionRequest", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "toEngine", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "toAuthenticate", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "Lcom/reown/sign/client/Sign$Params$Authenticate;", "toCacaoPayload", "Lcom/reown/sign/client/Sign$Model$Cacao$Payload;", "issuer", "", "getStatement", "toCommon", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "Lcom/reown/sign/client/Sign$Model$Cacao;", "toClient", "toRpcResult", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcResult;", "toRpcError", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcError;", "toEngineEvent", "Lcom/reown/sign/engine/model/EngineDO$Event;", "Lcom/reown/sign/client/Sign$Model$SessionEvent;", "chainId", "toClientDeletedSession", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "Lcom/reown/sign/engine/model/EngineDO$SessionDelete;", "toClientSessionEvent", "Lcom/reown/sign/engine/model/EngineDO$SessionEvent;", "toClientSessionAuthenticate", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "Lcom/reown/sign/engine/model/EngineDO$SessionAuthenticateEvent;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "Lcom/reown/sign/engine/model/EngineDO$Participant;", "toClientEvent", "Lcom/reown/sign/client/Sign$Model$Event;", "toClientActiveSession", "Lcom/reown/sign/client/Sign$Model$Session;", "Lcom/reown/sign/engine/model/EngineDO$Session;", "Lcom/reown/sign/engine/model/EngineDO$SessionExtend;", "toClientSessionRejected", "Lcom/reown/sign/client/Sign$Model$RejectedSession;", "Lcom/reown/sign/engine/model/EngineDO$SessionRejected;", "toClientSessionApproved", "Lcom/reown/sign/client/Sign$Model$ApprovedSession;", "Lcom/reown/sign/engine/model/EngineDO$SessionApproved;", "toMapOfClientNamespacesSession", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "toEngineDORequest", "Lcom/reown/sign/engine/model/EngineDO$Request;", "Lcom/reown/sign/client/Sign$Params$Request;", "toSentRequest", "Lcom/reown/sign/client/Sign$Model$SentRequest;", "requestId", "", "toClientJsonRpcResult", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcResult;", "toClientSessionsNamespaces", "Lcom/reown/sign/client/Sign$Model$UpdatedSession;", "Lcom/reown/sign/engine/model/EngineDO$SessionUpdateNamespaces;", "toClientJsonRpcError", "Lcom/reown/sign/engine/model/EngineDO$JsonRpcResponse$JsonRpcError;", "toClientSettledPairing", "Lcom/reown/sign/client/Sign$Model$Pairing;", "Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "mapToPendingRequests", "Lcom/reown/sign/client/Sign$Model$PendingRequest;", "Lcom/reown/sign/common/model/Request;", "mapToPendingSessionRequests", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "toClientSessionPayloadResponse", "Lcom/reown/sign/client/Sign$Model$SessionRequestResponse;", "Lcom/reown/sign/engine/model/EngineDO$SessionPayloadResponse;", "toMapOfEngineNamespacesRequired", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "toMapOfEngineNamespacesOptional", "toMapOfClientNamespacesProposal", "toMapOfEngineNamespacesSession", "toProposalNamespacesVO", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "toSessionNamespacesVO", "Lcom/reown/android/internal/common/model/Namespace$Session;", "toSign", "Lcom/reown/android/Core$Model$Namespace$Proposal;", "toClientConnectionState", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "Lcom/reown/android/internal/common/model/ConnectionState;", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "Lcom/reown/sign/engine/model/EngineDO$ExpiredProposal;", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "Lcom/reown/sign/engine/model/EngineDO$ExpiredRequest;", "toClientError", "Lcom/reown/sign/client/Sign$Model$Error;", "Lcom/reown/android/internal/common/model/SDKError;", "Lcom/reown/sign/client/Sign$Model$Message$SessionProposal;", "Lcom/reown/android/Core$Model$Message$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$Message$SessionAuthenticate;", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate;", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "Lcom/reown/sign/client/Sign$Model$Message$SessionRequest;", "Lcom/reown/android/Core$Model$Message$SessionRequest;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nClientMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientMapper.kt\ncom/reown/sign/client/mapper/ClientMapperKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,464:1\n1563#2:465\n1634#2,3:466\n1563#2:469\n1634#2,3:470\n1252#2,4:475\n1563#2:480\n1634#2,3:481\n1563#2:484\n1634#2,3:485\n1252#2,4:490\n1252#2,4:496\n1252#2,4:502\n1252#2,4:508\n1252#2,4:514\n1252#2,4:520\n1252#2,4:526\n1252#2,4:532\n463#3:473\n413#3:474\n463#3:488\n413#3:489\n463#3:494\n413#3:495\n463#3:500\n413#3:501\n463#3:506\n413#3:507\n463#3:512\n413#3:513\n463#3:518\n413#3:519\n463#3:524\n413#3:525\n463#3:530\n413#3:531\n1#4:479\n*S KotlinDebug\n*F\n+ 1 ClientMapper.kt\ncom/reown/sign/client/mapper/ClientMapperKt\n*L\n148#1:465\n148#1:466,3\n171#1:469\n171#1:470,3\n277#1:475,4\n306#1:480\n306#1:481,3\n318#1:484\n318#1:485,3\n353#1:490,4\n359#1:496,4\n365#1:502,4\n371#1:508,4\n377#1:514,4\n383#1:520,4\n389#1:526,4\n395#1:532,4\n277#1:473\n277#1:474\n353#1:488\n353#1:489\n359#1:494\n359#1:495\n365#1:500\n365#1:501\n371#1:506\n371#1:507\n377#1:512\n377#1:513\n383#1:518\n383#1:519\n389#1:524\n389#1:525\n395#1:530\n395#1:531\n*E\n"})
public final /* synthetic */ class ClientMapperKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.reown.android.internal.common.model.Validation[] r0 = com.reown.android.internal.common.model.Validation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.reown.android.internal.common.model.Validation r1 = com.reown.android.internal.common.model.Validation.VALID     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.reown.android.internal.common.model.Validation r1 = com.reown.android.internal.common.model.Validation.INVALID     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.reown.android.internal.common.model.Validation r1 = com.reown.android.internal.common.model.Validation.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.mapper.ClientMapperKt.WhenMappings.<clinit>():void");
        }
    }

    private static final String getStatement(Sign.Model.PayloadParams payloadParams) {
        String statement = payloadParams.getStatement();
        return (statement == null || !StringsKt__StringsKt.contains$default((CharSequence) statement, (CharSequence) CacaoKt.RECAPS_STATEMENT, false, 2, (Object) null)) ? CacaoKt.getStatement(new Pair(payloadParams.getStatement(), payloadParams.getResources())) : payloadParams.getStatement();
    }

    public static final /* synthetic */ List mapToPendingRequests(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterable<Request> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Request request : iterable) {
            arrayList.add(new Sign.Model.PendingRequest(request.getId(), request.getTopic().getValue(), request.getMethod(), request.getChainId(), (String) request.getParams()));
        }
        return arrayList;
    }

    public static final /* synthetic */ List mapToPendingSessionRequests(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterable<EngineDO.SessionRequest> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (EngineDO.SessionRequest clientSessionRequest : iterable) {
            arrayList.add(toClientSessionRequest(clientSessionRequest));
        }
        return arrayList;
    }

    public static final /* synthetic */ EngineDO.Authenticate toAuthenticate(Sign.Params.Authenticate authenticate) {
        Intrinsics.checkNotNullParameter(authenticate, "<this>");
        String header = CacaoType.EIP4361.getHeader();
        return new EngineDO.Authenticate((String) null, authenticate.getChains(), authenticate.getDomain(), authenticate.getNonce(), authenticate.getUri(), header, authenticate.getNbf(), authenticate.getExp(), authenticate.getStatement(), authenticate.getRequestId(), authenticate.getResources(), authenticate.getMethods(), authenticate.getExpiry(), 1, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ Sign.Model.Cacao.Payload toCacaoPayload(Sign.Model.PayloadParams payloadParams, String str) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        Intrinsics.checkNotNullParameter(str, "issuer");
        return new Sign.Model.Cacao.Payload(str, payloadParams.getDomain(), payloadParams.getAud(), "1", payloadParams.getNonce(), payloadParams.getIat(), payloadParams.getNbf(), payloadParams.getExp(), getStatement(payloadParams), payloadParams.getRequestId(), payloadParams.getResources());
    }

    public static final /* synthetic */ List toClient(List list) {
        List list2 = list;
        Intrinsics.checkNotNullParameter(list2, "<this>");
        Iterable<Cacao> iterable = list2;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Cacao cacao : iterable) {
            arrayList.add(new Sign.Model.Cacao(new Sign.Model.Cacao.Header(cacao.getHeader().getT()), new Sign.Model.Cacao.Payload(cacao.getPayload().getIss(), cacao.getPayload().getDomain(), cacao.getPayload().getAud(), cacao.getPayload().getVersion(), cacao.getPayload().getNonce(), cacao.getPayload().getIat(), cacao.getPayload().getNbf(), cacao.getPayload().getExp(), cacao.getPayload().getStatement(), cacao.getPayload().getRequestId(), cacao.getPayload().getResources()), new Sign.Model.Cacao.Signature(cacao.getSignature().getT(), cacao.getSignature().getS(), cacao.getSignature().getM())));
        }
        return arrayList;
    }

    public static final /* synthetic */ Sign.Model.Session toClientActiveSession(EngineDO.Session session) {
        Intrinsics.checkNotNullParameter(session, "<this>");
        String pairingTopic = session.getPairingTopic();
        String value = session.getTopic().getValue();
        long seconds = session.getExpiry().getSeconds();
        Map mapOfClientNamespacesProposal = toMapOfClientNamespacesProposal(session.getRequiredNamespaces());
        Map<String, EngineDO.Namespace.Proposal> optionalNamespaces = session.getOptionalNamespaces();
        Map mapOfClientNamespacesProposal2 = optionalNamespaces != null ? toMapOfClientNamespacesProposal(optionalNamespaces) : null;
        Map mapOfClientNamespacesSession = toMapOfClientNamespacesSession(session.getNamespaces());
        AppMetaData peerAppMetaData = session.getPeerAppMetaData();
        return new Sign.Model.Session(pairingTopic, value, seconds, mapOfClientNamespacesProposal, mapOfClientNamespacesProposal2, mapOfClientNamespacesSession, peerAppMetaData != null ? ExtensionsKt.toClient(peerAppMetaData) : null);
    }

    public static final /* synthetic */ Sign.Model.JsonRpcResponse toClientJsonRpcResponse(EngineDO.JsonRpcResponse jsonRpcResponse) {
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "<this>");
        if (jsonRpcResponse instanceof EngineDO.JsonRpcResponse.JsonRpcResult) {
            return Intrinsics.checkNotNullParameter((EngineDO.JsonRpcResponse.JsonRpcResult) jsonRpcResponse, "<this>");
        }
        if (jsonRpcResponse instanceof EngineDO.JsonRpcResponse.JsonRpcError) {
            return Intrinsics.checkNotNullParameter((EngineDO.JsonRpcResponse.JsonRpcError) jsonRpcResponse, "<this>");
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Sign.Model.ApprovedSession toClientSessionApproved(EngineDO.SessionApproved sessionApproved) {
        Intrinsics.checkNotNullParameter(sessionApproved, "<this>");
        String topic = sessionApproved.getTopic();
        AppMetaData peerAppMetaData = sessionApproved.getPeerAppMetaData();
        return new Sign.Model.ApprovedSession(topic, peerAppMetaData != null ? ExtensionsKt.toClient(peerAppMetaData) : null, toMapOfClientNamespacesSession(sessionApproved.getNamespaces()), sessionApproved.getAccounts());
    }

    public static final /* synthetic */ Sign.Model.SessionAuthenticate toClientSessionAuthenticate(EngineDO.SessionAuthenticateEvent sessionAuthenticateEvent) {
        Intrinsics.checkNotNullParameter(sessionAuthenticateEvent, "<this>");
        sessionAuthenticateEvent.getPayloadParams();
        return new Sign.Model.SessionAuthenticate(sessionAuthenticateEvent.getId(), sessionAuthenticateEvent.getPairingTopic(), Intrinsics.checkNotNullParameter(sessionAuthenticateEvent.getParticipant(), "<this>"), toClient(sessionAuthenticateEvent.getPayloadParams()), sessionAuthenticateEvent.getExpiryTimestamp());
    }

    public static final /* synthetic */ Sign.Model.SessionAuthenticateResponse toClientSessionAuthenticateResponse(EngineDO.SessionAuthenticateResponse sessionAuthenticateResponse) {
        Intrinsics.checkNotNullParameter(sessionAuthenticateResponse, "<this>");
        if (sessionAuthenticateResponse instanceof EngineDO.SessionAuthenticateResponse.Result) {
            EngineDO.SessionAuthenticateResponse.Result result = (EngineDO.SessionAuthenticateResponse.Result) sessionAuthenticateResponse;
            long id = result.getId();
            List client = toClient((List) result.getCacaos());
            EngineDO.Session session = result.getSession();
            return new Sign.Model.SessionAuthenticateResponse.Result(id, client, session != null ? toClientActiveSession(session) : null);
        } else if (sessionAuthenticateResponse instanceof EngineDO.SessionAuthenticateResponse.Error) {
            EngineDO.SessionAuthenticateResponse.Error error = (EngineDO.SessionAuthenticateResponse.Error) sessionAuthenticateResponse;
            return new Sign.Model.SessionAuthenticateResponse.Error(error.getId(), error.getCode(), error.getMessage());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final /* synthetic */ Sign.Model.SessionRequest toClientSessionRequest(EngineDO.SessionRequest sessionRequest) {
        Intrinsics.checkNotNullParameter(sessionRequest, "<this>");
        String topic = sessionRequest.getTopic();
        String chainId = sessionRequest.getChainId();
        AppMetaData peerAppMetaData = sessionRequest.getPeerAppMetaData();
        return new Sign.Model.SessionRequest(topic, chainId, peerAppMetaData != null ? ExtensionsKt.toClient(peerAppMetaData) : null, new Sign.Model.SessionRequest.JSONRPCRequest(sessionRequest.getRequest().getId(), sessionRequest.getRequest().getMethod(), sessionRequest.getRequest().getParams()));
    }

    public static final /* synthetic */ Sign.Model.Pairing toClientSettledPairing(EngineDO.PairingSettle pairingSettle) {
        Intrinsics.checkNotNullParameter(pairingSettle, "<this>");
        String value = pairingSettle.getTopic().getValue();
        AppMetaData appMetaData = pairingSettle.getAppMetaData();
        return new Sign.Model.Pairing(value, appMetaData != null ? ExtensionsKt.toClient(appMetaData) : null);
    }

    public static final /* synthetic */ Sign.Model.SettledSessionResponse toClientSettledSessionResponse(EngineDO.SettledSessionResponse settledSessionResponse) {
        Intrinsics.checkNotNullParameter(settledSessionResponse, "<this>");
        if (settledSessionResponse instanceof EngineDO.SettledSessionResponse.Result) {
            return new Sign.Model.SettledSessionResponse.Result(toClientActiveSession(((EngineDO.SettledSessionResponse.Result) settledSessionResponse).getSettledSession()));
        }
        if (settledSessionResponse instanceof EngineDO.SettledSessionResponse.Error) {
            return new Sign.Model.SettledSessionResponse.Error(((EngineDO.SettledSessionResponse.Error) settledSessionResponse).getErrorMessage());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Sign.Model.SessionUpdateResponse toClientUpdateSessionNamespacesResponse(EngineDO.SessionUpdateNamespacesResponse sessionUpdateNamespacesResponse) {
        Intrinsics.checkNotNullParameter(sessionUpdateNamespacesResponse, "<this>");
        if (sessionUpdateNamespacesResponse instanceof EngineDO.SessionUpdateNamespacesResponse.Result) {
            EngineDO.SessionUpdateNamespacesResponse.Result result = (EngineDO.SessionUpdateNamespacesResponse.Result) sessionUpdateNamespacesResponse;
            return new Sign.Model.SessionUpdateResponse.Result(result.getTopic().getValue(), toMapOfClientNamespacesSession(result.getNamespaces()));
        } else if (sessionUpdateNamespacesResponse instanceof EngineDO.SessionUpdateNamespacesResponse.Error) {
            return new Sign.Model.SessionUpdateResponse.Error(((EngineDO.SessionUpdateNamespacesResponse.Error) sessionUpdateNamespacesResponse).getErrorMessage());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public static final Sign.Model.Validation toClientValidation(@NotNull Validation validation) {
        Intrinsics.checkNotNullParameter(validation, "<this>");
        int i3 = WhenMappings.$EnumSwitchMapping$0[validation.ordinal()];
        if (i3 == 1) {
            return Sign.Model.Validation.VALID;
        }
        if (i3 == 2) {
            return Sign.Model.Validation.INVALID;
        }
        if (i3 == 3) {
            return Sign.Model.Validation.UNKNOWN;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ List toCommon(List list) {
        List list2 = list;
        Intrinsics.checkNotNullParameter(list2, "<this>");
        Iterable<Sign.Model.Cacao> iterable = list2;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Sign.Model.Cacao cacao : iterable) {
            arrayList.add(new Cacao(new Cacao.Header(cacao.getHeader().getT()), new Cacao.Payload(cacao.getPayload().getIss(), cacao.getPayload().getDomain(), cacao.getPayload().getAud(), cacao.getPayload().getVersion(), cacao.getPayload().getNonce(), cacao.getPayload().getIat(), cacao.getPayload().getNbf(), cacao.getPayload().getExp(), cacao.getPayload().getStatement(), cacao.getPayload().getRequestId(), cacao.getPayload().getResources()), new Cacao.Signature(cacao.getSignature().getT(), cacao.getSignature().getS(), cacao.getSignature().getM())));
        }
        return arrayList;
    }

    public static final /* synthetic */ EngineDO.PayloadParams toEngine(Sign.Model.PayloadParams payloadParams) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        String header = CacaoType.CAIP222.getHeader();
        List<String> chains = payloadParams.getChains();
        String domain = payloadParams.getDomain();
        String aud = payloadParams.getAud();
        return new EngineDO.PayloadParams(chains, domain, payloadParams.getNonce(), aud, header, payloadParams.getIat(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources(), "1");
    }

    public static final /* synthetic */ EngineDO.Request toEngineDORequest(Sign.Params.Request request) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        String sessionTopic = request.getSessionTopic();
        String method = request.getMethod();
        String params = request.getParams();
        String chainId = request.getChainId();
        Long expiry = request.getExpiry();
        return new EngineDO.Request(sessionTopic, method, params, chainId, expiry != null ? new Expiry(expiry.longValue()) : null);
    }

    public static final /* synthetic */ EngineDO.Event toEngineEvent(Sign.Model.SessionEvent sessionEvent, String str) {
        Intrinsics.checkNotNullParameter(sessionEvent, "<this>");
        Intrinsics.checkNotNullParameter(str, "chainId");
        return new EngineDO.Event(sessionEvent.getName(), sessionEvent.getData(), str);
    }

    public static final /* synthetic */ JsonRpcResponse toJsonRpcResponse(Sign.Model.JsonRpcResponse jsonRpcResponse) {
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "<this>");
        if (jsonRpcResponse instanceof Sign.Model.JsonRpcResponse.JsonRpcResult) {
            return Intrinsics.checkNotNullParameter((Sign.Model.JsonRpcResponse.JsonRpcResult) jsonRpcResponse, "<this>");
        }
        if (jsonRpcResponse instanceof Sign.Model.JsonRpcResponse.JsonRpcError) {
            return Intrinsics.checkNotNullParameter((Sign.Model.JsonRpcResponse.JsonRpcError) jsonRpcResponse, "<this>");
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Map toMapOfClientNamespacesProposal(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            EngineDO.Namespace.Proposal proposal = (EngineDO.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfClientNamespacesSession(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            EngineDO.Namespace.Session session = (EngineDO.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesOptional(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Proposal proposal = (Sign.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesRequired(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Proposal proposal = (Sign.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toMapOfEngineNamespacesSession(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Session session = (Sign.Model.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new EngineDO.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toProposalNamespacesVO(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Proposal proposal = (Sign.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Namespace.Proposal(proposal.getMethods(), proposal.getChains(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toSessionNamespacesVO(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Session session = (Sign.Model.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Map toCore(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Namespace.Session session = (Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Sign.Model.Session toClientActiveSession(EngineDO.SessionExtend sessionExtend) {
        Intrinsics.checkNotNullParameter(sessionExtend, "<this>");
        String pairingTopic = sessionExtend.getPairingTopic();
        String value = sessionExtend.getTopic().getValue();
        long seconds = sessionExtend.getExpiry().getSeconds();
        Map mapOfClientNamespacesProposal = toMapOfClientNamespacesProposal(sessionExtend.getRequiredNamespaces());
        Map<String, EngineDO.Namespace.Proposal> optionalNamespaces = sessionExtend.getOptionalNamespaces();
        Map mapOfClientNamespacesProposal2 = optionalNamespaces != null ? toMapOfClientNamespacesProposal(optionalNamespaces) : null;
        Map mapOfClientNamespacesSession = toMapOfClientNamespacesSession(sessionExtend.getNamespaces());
        AppMetaData peerAppMetaData = sessionExtend.getPeerAppMetaData();
        return new Sign.Model.Session(pairingTopic, value, seconds, mapOfClientNamespacesProposal, mapOfClientNamespacesProposal2, mapOfClientNamespacesSession, peerAppMetaData != null ? ExtensionsKt.toClient(peerAppMetaData) : null);
    }

    public static final /* synthetic */ Sign.Model.PayloadParams toClient(EngineDO.PayloadParams payloadParams) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        String type = payloadParams.getType();
        List<String> chains = payloadParams.getChains();
        String domain = payloadParams.getDomain();
        String aud = payloadParams.getAud();
        return new Sign.Model.PayloadParams(chains, domain, payloadParams.getNonce(), aud, type, payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getIat(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources());
    }

    public static final /* synthetic */ Map toSign(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Core.Model.Namespace.Proposal proposal = (Core.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Sign.Model.PayloadParams toClient(SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
        Intrinsics.checkNotNullParameter(sessionAuthenticateParams, "<this>");
        PayloadParams authPayload = sessionAuthenticateParams.getAuthPayload();
        String type = authPayload.getType();
        List<String> chains = authPayload.getChains();
        String domain = authPayload.getDomain();
        String aud = authPayload.getAud();
        return new Sign.Model.PayloadParams(chains, domain, authPayload.getNonce(), aud, type, authPayload.getNbf(), authPayload.getExp(), authPayload.getIat(), authPayload.getStatement(), authPayload.getRequestId(), authPayload.getResources());
    }

    private static final Sign.Model.PayloadParams toClient(Core.Model.Message.SessionAuthenticate.PayloadParams payloadParams) {
        return new Sign.Model.PayloadParams(payloadParams.getChains(), payloadParams.getDomain(), payloadParams.getNonce(), payloadParams.getAud(), payloadParams.getType(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getIat(), payloadParams.getRequestId(), payloadParams.getRequestId(), payloadParams.getResources());
    }
}
