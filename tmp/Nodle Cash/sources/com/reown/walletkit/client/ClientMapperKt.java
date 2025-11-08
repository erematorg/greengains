package com.reown.walletkit.client;

import com.reown.android.internal.common.signing.cacao.CacaoType;
import com.reown.sign.client.Sign;
import com.reown.walletkit.client.Wallet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000ê\u0001\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u0001H\u0000\u001a$\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0000\u001a$\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0001H\u0000\u001a$\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0001H\u0000\u001a\f\u0010\u0000\u001a\u00020\n*\u00020\u000bH\u0000\u001a\f\u0010\u0000\u001a\u00020\f*\u00020\rH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000e*\u00020\u000fH\u0000\u001a\f\u0010\u0000\u001a\u00020\u0010*\u00020\u0011H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0014*\u00020\u0015H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0016*\u00020\u0017H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b*\b\u0012\u0004\u0012\u00020\u001d0\u001bH\u0000\u001a\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b*\b\u0012\u0004\u0012\u00020 0\u001bH\u0000\u001a\f\u0010\u0005\u001a\u00020!*\u00020\"H\u0000\u001a\f\u0010\u0005\u001a\u00020#*\u00020$H\u0000\u001a\f\u0010\u0005\u001a\u00020%*\u00020&H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0017*\u00020\u0016H\u0000\u001a\f\u0010\u0005\u001a\u00020'*\u00020(H\u0000\u001a\f\u0010\u0005\u001a\u00020)*\u00020*H\u0000\u001a\f\u0010\u0005\u001a\u00020\u001f*\u00020 H\u0000\u001a\f\u0010\u0005\u001a\u00020+*\u00020,H\u0000\u001a\f\u0010\u0005\u001a\u00020-*\u00020.H\u0000\u001a\f\u0010\u0005\u001a\u00020/*\u000200H\u0000\u001a\f\u0010\u0005\u001a\u000201*\u000202H\u0000\u001a\f\u0010\u0005\u001a\u000203*\u000204H\u0000\u001a\f\u0010\u0000\u001a\u00020\"*\u00020!H\u0000\u001a\f\u0010\u0005\u001a\u000205*\u000206H\u0000\u001a\f\u0010\u0005\u001a\u000207*\u000208H\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002090\u001b*\b\u0012\u0004\u0012\u00020:0\u001bH\u0000\u001a\f\u0010\u0005\u001a\u00020:*\u000209H\u0000\u001a\f\u0010\u0005\u001a\u00020;*\u00020<H\u0000¨\u0006="}, d2 = {"toSign", "", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "toWallet", "toWalletProposalNamespaces", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "toSignProposalNamespaces", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "Lcom/reown/sign/client/Sign$Model$SessionEvent;", "Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;", "Lcom/reown/sign/client/Sign$Model$Event;", "Lcom/reown/walletkit/client/Wallet$Model$Event;", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "Lcom/reown/walletkit/client/Wallet$Model$Session;", "Lcom/reown/sign/client/Sign$Model$Session;", "mapToPendingRequests", "", "Lcom/reown/walletkit/client/Wallet$Model$PendingSessionRequest;", "Lcom/reown/sign/client/Sign$Model$PendingRequest;", "mapToPendingSessionRequests", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "Lcom/reown/walletkit/client/Wallet$Model$Validation;", "Lcom/reown/sign/client/Sign$Model$Validation;", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredProposal;", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredRequest;", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$Message$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$Message$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$Cacao;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "walletkit_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nClientMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientMapper.kt\ncom/reown/walletkit/client/ClientMapperKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,570:1\n463#2:571\n413#2:572\n463#2:577\n413#2:578\n463#2:583\n413#2:584\n463#2:589\n413#2:590\n1252#3,4:573\n1252#3,4:579\n1252#3,4:585\n1252#3,4:591\n1563#3:595\n1634#3,3:596\n1563#3:599\n1634#3,3:600\n1869#3,2:603\n*S KotlinDebug\n*F\n+ 1 ClientMapper.kt\ncom/reown/walletkit/client/ClientMapperKt\n*L\n30#1:571\n30#1:572\n36#1:577\n36#1:578\n42#1:583\n42#1:584\n48#1:589\n48#1:590\n30#1:573,4\n36#1:579,4\n42#1:585,4\n48#1:591,4\n104#1:595\n104#1:596,3\n115#1:599\n115#1:600,3\n263#1:603,2\n*E\n"})
public final class ClientMapperKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.reown.sign.client.Sign$Model$Validation[] r0 = com.reown.sign.client.Sign.Model.Validation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.reown.sign.client.Sign$Model$Validation r1 = com.reown.sign.client.Sign.Model.Validation.VALID     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.reown.sign.client.Sign$Model$Validation r1 = com.reown.sign.client.Sign.Model.Validation.INVALID     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.reown.sign.client.Sign$Model$Validation r1 = com.reown.sign.client.Sign.Model.Validation.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.walletkit.client.ClientMapperKt.WhenMappings.<clinit>():void");
        }
    }

    public static final /* synthetic */ List mapToPendingRequests(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterable<Sign.Model.PendingRequest> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Sign.Model.PendingRequest pendingRequest : iterable) {
            arrayList.add(new Wallet.Model.PendingSessionRequest(pendingRequest.getRequestId(), pendingRequest.getTopic(), pendingRequest.getMethod(), pendingRequest.getChainId(), pendingRequest.getParams()));
        }
        return arrayList;
    }

    public static final /* synthetic */ List mapToPendingSessionRequests(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterable<Sign.Model.SessionRequest> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Sign.Model.SessionRequest sessionRequest : iterable) {
            arrayList.add(new Wallet.Model.SessionRequest(sessionRequest.getTopic(), sessionRequest.getChainId(), sessionRequest.getPeerMetaData(), new Wallet.Model.SessionRequest.JSONRPCRequest(sessionRequest.getRequest().getId(), sessionRequest.getRequest().getMethod(), sessionRequest.getRequest().getParams())));
        }
        return arrayList;
    }

    public static final /* synthetic */ Sign.Model.JsonRpcResponse toSign(Wallet.Model.JsonRpcResponse jsonRpcResponse) {
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "<this>");
        if (jsonRpcResponse instanceof Wallet.Model.JsonRpcResponse.JsonRpcResult) {
            return Intrinsics.checkNotNullParameter((Wallet.Model.JsonRpcResponse.JsonRpcResult) jsonRpcResponse, "<this>");
        }
        if (jsonRpcResponse instanceof Wallet.Model.JsonRpcResponse.JsonRpcError) {
            return Intrinsics.checkNotNullParameter((Wallet.Model.JsonRpcResponse.JsonRpcError) jsonRpcResponse, "<this>");
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Map toSignProposalNamespaces(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Wallet.Model.Namespace.Proposal proposal = (Wallet.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Wallet.Model.Session toWallet(Sign.Model.Session session) {
        Intrinsics.checkNotNullParameter(session, "<this>");
        String pairingTopic = session.getPairingTopic();
        String topic = session.getTopic();
        long expiry = session.getExpiry();
        Map walletProposalNamespaces = toWalletProposalNamespaces(session.getRequiredNamespaces());
        Map<String, Sign.Model.Namespace.Proposal> optionalNamespaces = session.getOptionalNamespaces();
        return new Wallet.Model.Session(pairingTopic, topic, expiry, walletProposalNamespaces, optionalNamespaces != null ? toWalletProposalNamespaces(optionalNamespaces) : null, toWallet((Map) session.getNamespaces()), session.getMetaData());
    }

    public static final /* synthetic */ Map toWalletProposalNamespaces(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Proposal proposal = (Sign.Model.Namespace.Proposal) entry.getValue();
            linkedHashMap.put(key, new Wallet.Model.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Sign.Model.PayloadParams toSign(Wallet.Model.PayloadAuthRequestParams payloadAuthRequestParams) {
        Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "<this>");
        String type = payloadAuthRequestParams.getType();
        if (type == null) {
            type = CacaoType.CAIP222.getHeader();
        }
        List<String> chains = payloadAuthRequestParams.getChains();
        String domain = payloadAuthRequestParams.getDomain();
        String aud = payloadAuthRequestParams.getAud();
        return new Sign.Model.PayloadParams(chains, domain, payloadAuthRequestParams.getNonce(), aud, type, payloadAuthRequestParams.getNbf(), payloadAuthRequestParams.getExp(), payloadAuthRequestParams.getIat(), payloadAuthRequestParams.getStatement(), payloadAuthRequestParams.getRequestId(), payloadAuthRequestParams.getResources());
    }

    public static final /* synthetic */ Wallet.Model.PayloadAuthRequestParams toWallet(Sign.Model.PayloadParams payloadParams) {
        Intrinsics.checkNotNullParameter(payloadParams, "<this>");
        List<String> chains = payloadParams.getChains();
        String type = payloadParams.getType();
        if (type == null) {
            type = CacaoType.CAIP222.getHeader();
        }
        String domain = payloadParams.getDomain();
        String aud = payloadParams.getAud();
        return new Wallet.Model.PayloadAuthRequestParams(chains, domain, payloadParams.getNonce(), aud, type, payloadParams.getIat(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources());
    }

    public static final /* synthetic */ List toSign(List list) {
        List<Wallet.Model.Cacao> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "<this>");
        ArrayList arrayList = new ArrayList();
        for (Wallet.Model.Cacao cacao : list2) {
            arrayList.add(new Sign.Model.Cacao(new Sign.Model.Cacao.Header(cacao.getHeader().getT()), new Sign.Model.Cacao.Payload(cacao.getPayload().getIss(), cacao.getPayload().getDomain(), cacao.getPayload().getAud(), cacao.getPayload().getVersion(), cacao.getPayload().getNonce(), cacao.getPayload().getIat(), cacao.getPayload().getNbf(), cacao.getPayload().getExp(), cacao.getPayload().getStatement(), cacao.getPayload().getRequestId(), cacao.getPayload().getResources()), new Sign.Model.Cacao.Signature(cacao.getSignature().getT(), cacao.getSignature().getS(), cacao.getSignature().getM())));
        }
        return arrayList;
    }

    @NotNull
    public static final Wallet.Model.VerifyContext toWallet(@NotNull Sign.Model.VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "<this>");
        return new Wallet.Model.VerifyContext(verifyContext.getId(), verifyContext.getOrigin(), toWallet(verifyContext.getValidation()), verifyContext.getVerifyUrl(), verifyContext.isScam());
    }

    @NotNull
    public static final Wallet.Model.Validation toWallet(@NotNull Sign.Model.Validation validation) {
        Intrinsics.checkNotNullParameter(validation, "<this>");
        int i3 = WhenMappings.$EnumSwitchMapping$0[validation.ordinal()];
        if (i3 == 1) {
            return Wallet.Model.Validation.VALID;
        }
        if (i3 == 2) {
            return Wallet.Model.Validation.INVALID;
        }
        if (i3 == 3) {
            return Wallet.Model.Validation.UNKNOWN;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Wallet.Model.SessionDelete toWallet(Sign.Model.DeletedSession deletedSession) {
        Intrinsics.checkNotNullParameter(deletedSession, "<this>");
        if (deletedSession instanceof Sign.Model.DeletedSession.Success) {
            Sign.Model.DeletedSession.Success success = (Sign.Model.DeletedSession.Success) deletedSession;
            return new Wallet.Model.SessionDelete.Success(success.getTopic(), success.getReason());
        } else if (deletedSession instanceof Sign.Model.DeletedSession.Error) {
            return new Wallet.Model.SessionDelete.Error(((Sign.Model.DeletedSession.Error) deletedSession).getError());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final /* synthetic */ Map toSign(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Wallet.Model.Namespace.Session session = (Wallet.Model.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Sign.Model.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ Wallet.Model.SettledSessionResponse toWallet(Sign.Model.SettledSessionResponse settledSessionResponse) {
        Intrinsics.checkNotNullParameter(settledSessionResponse, "<this>");
        if (settledSessionResponse instanceof Sign.Model.SettledSessionResponse.Result) {
            return new Wallet.Model.SettledSessionResponse.Result(toWallet(((Sign.Model.SettledSessionResponse.Result) settledSessionResponse).getSession()));
        }
        if (settledSessionResponse instanceof Sign.Model.SettledSessionResponse.Error) {
            return new Wallet.Model.SettledSessionResponse.Error(((Sign.Model.SettledSessionResponse.Error) settledSessionResponse).getErrorMessage());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Wallet.Model.SessionUpdateResponse toWallet(Sign.Model.SessionUpdateResponse sessionUpdateResponse) {
        Intrinsics.checkNotNullParameter(sessionUpdateResponse, "<this>");
        if (sessionUpdateResponse instanceof Sign.Model.SessionUpdateResponse.Result) {
            Sign.Model.SessionUpdateResponse.Result result = (Sign.Model.SessionUpdateResponse.Result) sessionUpdateResponse;
            return new Wallet.Model.SessionUpdateResponse.Result(result.getTopic(), toWallet((Map) result.getNamespaces()));
        } else if (sessionUpdateResponse instanceof Sign.Model.SessionUpdateResponse.Error) {
            return new Wallet.Model.SessionUpdateResponse.Error(((Sign.Model.SessionUpdateResponse.Error) sessionUpdateResponse).getErrorMessage());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final /* synthetic */ Wallet.Model.ConnectionState.Reason toWallet(Sign.Model.ConnectionState.Reason reason) {
        Intrinsics.checkNotNullParameter(reason, "<this>");
        if (reason instanceof Sign.Model.ConnectionState.Reason.ConnectionClosed) {
            return new Wallet.Model.ConnectionState.Reason.ConnectionClosed(((Sign.Model.ConnectionState.Reason.ConnectionClosed) reason).getMessage());
        }
        if (reason instanceof Sign.Model.ConnectionState.Reason.ConnectionFailed) {
            return new Wallet.Model.ConnectionState.Reason.ConnectionFailed(((Sign.Model.ConnectionState.Reason.ConnectionFailed) reason).getThrowable());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Map toWallet(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Sign.Model.Namespace.Session session = (Sign.Model.Namespace.Session) entry.getValue();
            linkedHashMap.put(key, new Wallet.Model.Namespace.Session(session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents()));
        }
        return linkedHashMap;
    }
}
