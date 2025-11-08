package com.reown.walletkit.client;

import G1.C0235a;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.CoreInterface;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.push.client.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.sign.client.Sign;
import com.reown.sign.client.SignClient;
import com.reown.sign.client.utils.ApprovedNamespacesUtils;
import com.reown.sign.common.exceptions.SignClientAlreadyInitializedException;
import com.reown.sign.storage.data.dao.temp.b;
import com.reown.walletkit.client.Wallet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001SB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ2\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J:\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J6\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00182\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J\"\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00142\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J:\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u001d2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00070\u00102\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u001f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J.\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\"0!J8\u0010&\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020'2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010(\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020)2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010*\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020+2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J\u001e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00142\u0006\u00101\u001a\u000202J*\u00103\u001a\u00020/2\u0006\u0010.\u001a\u00020/2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u0014052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001405J8\u00107\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u0002082\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u00109\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020:2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010;\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020<2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010=\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020>2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J8\u0010?\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020@2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00070\u00102\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010J\u0018\u0010A\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010DJ\u000e\u0010E\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020FJ\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020H05H\u0007J\u0010\u0010I\u001a\u0004\u0018\u00010H2\u0006\u0010J\u001a\u00020\u0014J\u0014\u0010K\u001a\b\u0012\u0004\u0012\u00020L052\u0006\u0010J\u001a\u00020\u0014J\f\u0010M\u001a\b\u0012\u0004\u0012\u00020$05J\u0010\u0010N\u001a\u0004\u0018\u00010O2\u0006\u0010P\u001a\u00020QJ\f\u0010R\u001a\b\u0012\u0004\u0012\u00020O05R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/reown/walletkit/client/WalletKit;", "", "<init>", "()V", "coreClient", "Lcom/reown/android/CoreInterface;", "setWalletDelegate", "", "delegate", "Lcom/reown/walletkit/client/WalletKit$WalletDelegate;", "initialize", "params", "Lcom/reown/walletkit/client/Wallet$Params$Init;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "Lcom/reown/walletkit/client/Wallet$Model$Error;", "registerDeviceToken", "firebaseAccessToken", "", "enableEncrypted", "", "decryptMessage", "Lcom/reown/walletkit/client/Wallet$Params$DecryptMessage;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "dispatchEnvelope", "urlWithEnvelope", "pair", "Lcom/reown/walletkit/client/Wallet$Params$Pair;", "approveSession", "Lcom/reown/walletkit/client/Wallet$Params$SessionApprove;", "generateApprovedNamespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "sessionProposal", "Lcom/reown/walletkit/client/Wallet$Model$SessionProposal;", "supportedNamespaces", "rejectSession", "Lcom/reown/walletkit/client/Wallet$Params$SessionReject;", "approveSessionAuthenticate", "Lcom/reown/walletkit/client/Wallet$Params$ApproveSessionAuthenticate;", "rejectSessionAuthenticate", "Lcom/reown/walletkit/client/Wallet$Params$RejectSessionAuthenticate;", "generateAuthObject", "Lcom/reown/walletkit/client/Wallet$Model$Cacao;", "payloadParams", "Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "issuer", "signature", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "generateAuthPayloadParams", "supportedChains", "", "supportedMethods", "updateSession", "Lcom/reown/walletkit/client/Wallet$Params$SessionUpdate;", "extendSession", "Lcom/reown/walletkit/client/Wallet$Params$SessionExtend;", "respondSessionRequest", "Lcom/reown/walletkit/client/Wallet$Params$SessionRequestResponse;", "emitSessionEvent", "Lcom/reown/walletkit/client/Wallet$Params$SessionEmit;", "disconnectSession", "Lcom/reown/walletkit/client/Wallet$Params$SessionDisconnect;", "pingSession", "Lcom/reown/walletkit/client/Wallet$Params$Ping;", "sessionPing", "Lcom/reown/walletkit/client/Wallet$Listeners$SessionPing;", "formatAuthMessage", "Lcom/reown/walletkit/client/Wallet$Params$FormatAuthMessage;", "getListOfActiveSessions", "Lcom/reown/walletkit/client/Wallet$Model$Session;", "getActiveSessionByTopic", "topic", "getPendingListOfSessionRequests", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest;", "getSessionProposals", "getVerifyContext", "Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "id", "", "getListOfVerifyContexts", "WalletDelegate", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWalletKit.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WalletKit.kt\ncom/reown/walletkit/client/WalletKit\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,392:1\n1563#2:393\n1634#2,3:394\n1563#2:397\n1634#2,3:398\n1563#2:401\n1634#2,3:402\n*S KotlinDebug\n*F\n+ 1 WalletKit.kt\ncom/reown/walletkit/client/WalletKit\n*L\n296#1:393\n296#1:394,3\n326#1:397\n326#1:398,3\n344#1:401\n344#1:402,3\n*E\n"})
public final class WalletKit {
    @NotNull
    public static final WalletKit INSTANCE = new WalletKit();
    private static CoreInterface coreClient;

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H&J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H&R(\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/reown/walletkit/client/WalletKit$WalletDelegate;", "", "onSessionProposal", "", "sessionProposal", "Lcom/reown/walletkit/client/Wallet$Model$SessionProposal;", "verifyContext", "Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "onSessionAuthenticate", "Lkotlin/Function2;", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate;", "getOnSessionAuthenticate", "()Lkotlin/jvm/functions/Function2;", "onSessionRequest", "sessionRequest", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest;", "onSessionDelete", "sessionDelete", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "onSessionExtend", "session", "Lcom/reown/walletkit/client/Wallet$Model$Session;", "onSessionSettleResponse", "settleSessionResponse", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "onSessionUpdateResponse", "sessionUpdateResponse", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "onProposalExpired", "proposal", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredProposal;", "onRequestExpired", "request", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredRequest;", "onConnectionStateChange", "state", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState;", "onError", "error", "Lcom/reown/walletkit/client/Wallet$Model$Error;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface WalletDelegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Nullable
            @Deprecated
            public static Function2<Wallet.Model.SessionAuthenticate, Wallet.Model.VerifyContext, Unit> getOnSessionAuthenticate(@NotNull WalletDelegate walletDelegate) {
                return WalletDelegate.super.getOnSessionAuthenticate();
            }

            @Deprecated
            public static void onProposalExpired(@NotNull WalletDelegate walletDelegate, @NotNull Wallet.Model.ExpiredProposal expiredProposal) {
                Intrinsics.checkNotNullParameter(expiredProposal, "proposal");
                WalletDelegate.super.onProposalExpired(expiredProposal);
            }

            @Deprecated
            public static void onRequestExpired(@NotNull WalletDelegate walletDelegate, @NotNull Wallet.Model.ExpiredRequest expiredRequest) {
                Intrinsics.checkNotNullParameter(expiredRequest, "request");
                WalletDelegate.super.onRequestExpired(expiredRequest);
            }
        }

        @Nullable
        Function2<Wallet.Model.SessionAuthenticate, Wallet.Model.VerifyContext, Unit> getOnSessionAuthenticate() {
            return null;
        }

        void onConnectionStateChange(@NotNull Wallet.Model.ConnectionState connectionState);

        void onError(@NotNull Wallet.Model.Error error);

        void onProposalExpired(@NotNull Wallet.Model.ExpiredProposal expiredProposal) {
            Intrinsics.checkNotNullParameter(expiredProposal, "proposal");
        }

        void onRequestExpired(@NotNull Wallet.Model.ExpiredRequest expiredRequest) {
            Intrinsics.checkNotNullParameter(expiredRequest, "request");
        }

        void onSessionDelete(@NotNull Wallet.Model.SessionDelete sessionDelete);

        void onSessionExtend(@NotNull Wallet.Model.Session session);

        void onSessionProposal(@NotNull Wallet.Model.SessionProposal sessionProposal, @NotNull Wallet.Model.VerifyContext verifyContext);

        void onSessionRequest(@NotNull Wallet.Model.SessionRequest sessionRequest, @NotNull Wallet.Model.VerifyContext verifyContext);

        void onSessionSettleResponse(@NotNull Wallet.Model.SettledSessionResponse settledSessionResponse);

        void onSessionUpdateResponse(@NotNull Wallet.Model.SessionUpdateResponse sessionUpdateResponse);
    }

    private WalletKit() {
    }

    public static /* synthetic */ void approveSession$default(WalletKit walletKit, Wallet.Params.SessionApprove sessionApprove, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(3);
        }
        walletKit.approveSession(sessionApprove, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$7(Wallet.Params.SessionApprove sessionApprove) {
        Intrinsics.checkNotNullParameter(sessionApprove, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$8(Function1 function1, Wallet.Params.SessionApprove sessionApprove, Sign.Params.Approve approve) {
        Intrinsics.checkNotNullParameter(approve, "it");
        function1.invoke(sessionApprove);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$9(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void approveSessionAuthenticate$default(WalletKit walletKit, Wallet.Params.ApproveSessionAuthenticate approveSessionAuthenticate, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(8);
        }
        walletKit.approveSessionAuthenticate(approveSessionAuthenticate, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit approveSessionAuthenticate$lambda$13(Wallet.Params.ApproveSessionAuthenticate approveSessionAuthenticate) {
        Intrinsics.checkNotNullParameter(approveSessionAuthenticate, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit approveSessionAuthenticate$lambda$14(Function1 function1, Wallet.Params.ApproveSessionAuthenticate approveSessionAuthenticate, Sign.Params.ApproveAuthenticate approveAuthenticate) {
        Intrinsics.checkNotNullParameter(approveAuthenticate, "it");
        function1.invoke(approveSessionAuthenticate);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit approveSessionAuthenticate$lambda$15(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void disconnectSession$default(WalletKit walletKit, Wallet.Params.SessionDisconnect sessionDisconnect, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(9);
        }
        walletKit.disconnectSession(sessionDisconnect, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit disconnectSession$lambda$31(Wallet.Params.SessionDisconnect sessionDisconnect) {
        Intrinsics.checkNotNullParameter(sessionDisconnect, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit disconnectSession$lambda$32(Function1 function1, Wallet.Params.SessionDisconnect sessionDisconnect, Sign.Params.Disconnect disconnect) {
        Intrinsics.checkNotNullParameter(disconnect, "it");
        function1.invoke(sessionDisconnect);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit disconnectSession$lambda$33(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void emitSessionEvent$default(WalletKit walletKit, Wallet.Params.SessionEmit sessionEmit, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(12);
        }
        walletKit.emitSessionEvent(sessionEmit, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit emitSessionEvent$lambda$28(Wallet.Params.SessionEmit sessionEmit) {
        Intrinsics.checkNotNullParameter(sessionEmit, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit emitSessionEvent$lambda$29(Function1 function1, Wallet.Params.SessionEmit sessionEmit, Sign.Params.Emit emit) {
        Intrinsics.checkNotNullParameter(emit, "it");
        function1.invoke(sessionEmit);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit emitSessionEvent$lambda$30(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void extendSession$default(WalletKit walletKit, Wallet.Params.SessionExtend sessionExtend, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(4);
        }
        walletKit.extendSession(sessionExtend, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit extendSession$lambda$22(Wallet.Params.SessionExtend sessionExtend) {
        Intrinsics.checkNotNullParameter(sessionExtend, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit extendSession$lambda$23(Function1 function1, Wallet.Params.SessionExtend sessionExtend, Sign.Params.Extend extend) {
        Intrinsics.checkNotNullParameter(extend, "it");
        function1.invoke(sessionExtend);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit extendSession$lambda$24(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    @JvmStatic
    @NotNull
    public static final List<Wallet.Model.Session> getListOfActiveSessions() throws IllegalStateException {
        Iterable<Sign.Model.Session> listOfActiveSessions = SignClient.INSTANCE.getListOfActiveSessions();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOfActiveSessions, 10));
        for (Sign.Model.Session wallet : listOfActiveSessions) {
            arrayList.add(ClientMapperKt.toWallet(wallet));
        }
        return arrayList;
    }

    public static /* synthetic */ void initialize$default(WalletKit walletKit, Wallet.Params.Init init, Function0 function0, Function1 function1, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function0 = new a(8);
        }
        walletKit.initialize(init, function0, function1);
    }

    /* access modifiers changed from: private */
    public static final Unit initialize$lambda$0() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit initialize$lambda$1(Function0 function0, Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (error.getThrowable() instanceof SignClientAlreadyInitializedException) {
            function0.invoke();
        } else {
            function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void pair$default(WalletKit walletKit, Wallet.Params.Pair pair, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(6);
        }
        if ((i3 & 4) != 0) {
            function12 = new b(10);
        }
        walletKit.pair(pair, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$3(Wallet.Params.Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$4(Wallet.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$5(Function1 function1, Wallet.Params.Pair pair, Core.Params.Pair pair2) {
        Intrinsics.checkNotNullParameter(pair2, "it");
        function1.invoke(pair);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$6(Function1 function1, Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void registerDeviceToken$default(WalletKit walletKit, String str, boolean z2, Function0 function0, Function1 function1, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        walletKit.registerDeviceToken(str, z2, function0, function1);
    }

    /* access modifiers changed from: private */
    public static final Unit registerDeviceToken$lambda$2(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(th));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void rejectSession$default(WalletKit walletKit, Wallet.Params.SessionReject sessionReject, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(11);
        }
        walletKit.rejectSession(sessionReject, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSession$lambda$10(Wallet.Params.SessionReject sessionReject) {
        Intrinsics.checkNotNullParameter(sessionReject, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSession$lambda$11(Function1 function1, Wallet.Params.SessionReject sessionReject, Sign.Params.Reject reject) {
        Intrinsics.checkNotNullParameter(reject, "it");
        function1.invoke(sessionReject);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSession$lambda$12(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void rejectSessionAuthenticate$default(WalletKit walletKit, Wallet.Params.RejectSessionAuthenticate rejectSessionAuthenticate, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(7);
        }
        walletKit.rejectSessionAuthenticate(rejectSessionAuthenticate, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSessionAuthenticate$lambda$16(Wallet.Params.RejectSessionAuthenticate rejectSessionAuthenticate) {
        Intrinsics.checkNotNullParameter(rejectSessionAuthenticate, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSessionAuthenticate$lambda$17(Function1 function1, Wallet.Params.RejectSessionAuthenticate rejectSessionAuthenticate, Sign.Params.RejectAuthenticate rejectAuthenticate) {
        Intrinsics.checkNotNullParameter(rejectAuthenticate, "it");
        function1.invoke(rejectSessionAuthenticate);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit rejectSessionAuthenticate$lambda$18(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void respondSessionRequest$default(WalletKit walletKit, Wallet.Params.SessionRequestResponse sessionRequestResponse, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(5);
        }
        walletKit.respondSessionRequest(sessionRequestResponse, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit respondSessionRequest$lambda$25(Wallet.Params.SessionRequestResponse sessionRequestResponse) {
        Intrinsics.checkNotNullParameter(sessionRequestResponse, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondSessionRequest$lambda$26(Function1 function1, Wallet.Params.SessionRequestResponse sessionRequestResponse, Sign.Params.Response response) {
        Intrinsics.checkNotNullParameter(response, "it");
        function1.invoke(sessionRequestResponse);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondSessionRequest$lambda$27(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void updateSession$default(WalletKit walletKit, Wallet.Params.SessionUpdate sessionUpdate, Function1 function1, Function1 function12, int i3, Object obj) throws IllegalStateException {
        if ((i3 & 2) != 0) {
            function1 = new b(13);
        }
        walletKit.updateSession(sessionUpdate, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final Unit updateSession$lambda$19(Wallet.Params.SessionUpdate sessionUpdate) {
        Intrinsics.checkNotNullParameter(sessionUpdate, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateSession$lambda$20(Function1 function1, Wallet.Params.SessionUpdate sessionUpdate, Sign.Params.Update update) {
        Intrinsics.checkNotNullParameter(update, "it");
        function1.invoke(sessionUpdate);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateSession$lambda$21(Function1 function1, Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public final void approveSession(@NotNull Wallet.Params.SessionApprove sessionApprove, @NotNull Function1<? super Wallet.Params.SessionApprove, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionApprove, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.approveSession(new Sign.Params.Approve(sessionApprove.getProposerPublicKey(), ClientMapperKt.toSign((Map) sessionApprove.getNamespaces()), sessionApprove.getProperties(), sessionApprove.getScopedProperties(), sessionApprove.getRelayProtocol()), new C0235a(function1, sessionApprove, 26), new a(function12, 4));
    }

    public final void approveSessionAuthenticate(@NotNull Wallet.Params.ApproveSessionAuthenticate approveSessionAuthenticate, @NotNull Function1<? super Wallet.Params.ApproveSessionAuthenticate, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(approveSessionAuthenticate, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.approveAuthenticate(new Sign.Params.ApproveAuthenticate(approveSessionAuthenticate.getId(), ClientMapperKt.toSign((List) approveSessionAuthenticate.getAuths())), new C0235a(function1, approveSessionAuthenticate, 16), new a(function12, 5));
    }

    public final void decryptMessage(@NotNull Wallet.Params.DecryptMessage decryptMessage, @NotNull Function1<? super Wallet.Model.Message, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(decryptMessage, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new WalletKit$decryptMessage$1(decryptMessage, function1, function12, (Continuation<? super WalletKit$decryptMessage$1>) null), 3, (Object) null);
    }

    public final void disconnectSession(@NotNull Wallet.Params.SessionDisconnect sessionDisconnect, @NotNull Function1<? super Wallet.Params.SessionDisconnect, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionDisconnect, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.disconnect(new Sign.Params.Disconnect(sessionDisconnect.getSessionTopic()), new C0235a(function1, sessionDisconnect, 17), new a(function12, 6));
    }

    public final void dispatchEnvelope(@NotNull String str, @NotNull Function1<? super Wallet.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, "urlWithEnvelope");
        Intrinsics.checkNotNullParameter(function1, "onError");
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new WalletKit$dispatchEnvelope$1(str, function1, (Continuation<? super WalletKit$dispatchEnvelope$1>) null), 3, (Object) null);
    }

    public final void emitSessionEvent(@NotNull Wallet.Params.SessionEmit sessionEmit, @NotNull Function1<? super Wallet.Params.SessionEmit, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionEmit, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.emit(new Sign.Params.Emit(sessionEmit.getTopic(), Intrinsics.checkNotNullParameter(sessionEmit.getEvent(), "<this>"), sessionEmit.getChainId()), new C0235a(function1, sessionEmit, 24), new a(function12, 12));
    }

    public final void extendSession(@NotNull Wallet.Params.SessionExtend sessionExtend, @NotNull Function1<? super Wallet.Params.SessionExtend, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionExtend, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.extend(new Sign.Params.Extend(sessionExtend.getTopic()), new C0235a(function1, sessionExtend, 19), new a(function12, 8));
    }

    @NotNull
    public final String formatAuthMessage(@NotNull Wallet.Params.FormatAuthMessage formatAuthMessage) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(formatAuthMessage, "params");
        return SignClient.INSTANCE.formatAuthMessage(new Sign.Params.FormatMessage(ClientMapperKt.toSign(formatAuthMessage.getPayloadParams()), formatAuthMessage.getIssuer()));
    }

    @NotNull
    public final Map<String, Wallet.Model.Namespace.Session> generateApprovedNamespaces(@NotNull Wallet.Model.SessionProposal sessionProposal, @NotNull Map<String, Wallet.Model.Namespace.Session> map) throws Exception {
        Intrinsics.checkNotNullParameter(sessionProposal, "sessionProposal");
        Intrinsics.checkNotNullParameter(map, "supportedNamespaces");
        return ClientMapperKt.toWallet((Map) ApprovedNamespacesUtils.generateApprovedNamespaces(Intrinsics.checkNotNullParameter(sessionProposal, "<this>"), ClientMapperKt.toSign((Map) map)));
    }

    @NotNull
    public final Wallet.Model.Cacao generateAuthObject(@NotNull Wallet.Model.PayloadAuthRequestParams payloadAuthRequestParams, @NotNull String str, @NotNull Wallet.Model.Cacao.Signature signature) throws Exception {
        Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
        Intrinsics.checkNotNullParameter(str, "issuer");
        Intrinsics.checkNotNullParameter(signature, "signature");
        return Intrinsics.checkNotNullParameter(ApprovedNamespacesUtils.generateAuthObject(ClientMapperKt.toSign(payloadAuthRequestParams), str, Intrinsics.checkNotNullParameter(signature, "<this>")), "<this>");
    }

    @NotNull
    public final Wallet.Model.PayloadAuthRequestParams generateAuthPayloadParams(@NotNull Wallet.Model.PayloadAuthRequestParams payloadAuthRequestParams, @NotNull List<String> list, @NotNull List<String> list2) throws Exception {
        Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
        Intrinsics.checkNotNullParameter(list, "supportedChains");
        Intrinsics.checkNotNullParameter(list2, "supportedMethods");
        return ClientMapperKt.toWallet(ApprovedNamespacesUtils.generateAuthPayloadParams(ClientMapperKt.toSign(payloadAuthRequestParams), list, list2));
    }

    @Nullable
    public final Wallet.Model.Session getActiveSessionByTopic(@NotNull String str) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Sign.Model.Session activeSessionByTopic = SignClient.INSTANCE.getActiveSessionByTopic(str);
        if (activeSessionByTopic != null) {
            return ClientMapperKt.toWallet(activeSessionByTopic);
        }
        return null;
    }

    @NotNull
    public final List<Wallet.Model.VerifyContext> getListOfVerifyContexts() throws IllegalStateException {
        Iterable<Sign.Model.VerifyContext> listOfVerifyContexts = SignClient.INSTANCE.getListOfVerifyContexts();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOfVerifyContexts, 10));
        for (Sign.Model.VerifyContext wallet : listOfVerifyContexts) {
            arrayList.add(ClientMapperKt.toWallet(wallet));
        }
        return arrayList;
    }

    @NotNull
    public final List<Wallet.Model.SessionRequest> getPendingListOfSessionRequests(@NotNull String str) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return ClientMapperKt.mapToPendingSessionRequests(SignClient.INSTANCE.getPendingSessionRequests(str));
    }

    @NotNull
    public final List<Wallet.Model.SessionProposal> getSessionProposals() throws IllegalStateException {
        Iterable<Sign.Model.SessionProposal> sessionProposals = SignClient.INSTANCE.getSessionProposals();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sessionProposals, 10));
        for (Sign.Model.SessionProposal wallet : sessionProposals) {
            arrayList.add(Intrinsics.checkNotNullParameter(wallet, "<this>"));
        }
        return arrayList;
    }

    @Nullable
    public final Wallet.Model.VerifyContext getVerifyContext(long j2) throws IllegalStateException {
        Sign.Model.VerifyContext verifyContext = SignClient.INSTANCE.getVerifyContext(j2);
        if (verifyContext != null) {
            return ClientMapperKt.toWallet(verifyContext);
        }
        return null;
    }

    public final void initialize(@NotNull Wallet.Params.Init init, @NotNull Function0<Unit> function0, @NotNull Function1<? super Wallet.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(init, "params");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        coreClient = init.getCore();
        SignClient.INSTANCE.initialize(new Sign.Params.Init(init.getCore()), function0, new C0235a(function0, function1, 20));
    }

    public final void pair(@NotNull Wallet.Params.Pair pair, @NotNull Function1<? super Wallet.Params.Pair, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(pair, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        CoreInterface coreInterface = coreClient;
        if (coreInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coreClient");
            coreInterface = null;
        }
        coreInterface.getPairing().pair(new Core.Params.Pair(pair.getUri()), new C0235a(function1, pair, 21), new a(function12, 9));
    }

    public final void pingSession(@NotNull Wallet.Params.Ping ping, @Nullable Wallet.Listeners.SessionPing sessionPing) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(ping, "params");
        SignClient.INSTANCE.ping(new Sign.Params.Ping(ping.getSessionTopic(), 0, 2, (DefaultConstructorMarker) null), new WalletKit$pingSession$signPingLister$1(sessionPing));
    }

    public final void registerDeviceToken(@NotNull String str, boolean z2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Wallet.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, "firebaseAccessToken");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        CoreInterface coreInterface = coreClient;
        if (coreInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coreClient");
            coreInterface = null;
        }
        coreInterface.getEcho().register(str, z2, function0, new a(function1, 3));
    }

    public final void rejectSession(@NotNull Wallet.Params.SessionReject sessionReject, @NotNull Function1<? super Wallet.Params.SessionReject, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionReject, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.rejectSession(new Sign.Params.Reject(sessionReject.getProposerPublicKey(), sessionReject.getReason()), new C0235a(function1, sessionReject, 25), new a(function12, 13));
    }

    public final void rejectSessionAuthenticate(@NotNull Wallet.Params.RejectSessionAuthenticate rejectSessionAuthenticate, @NotNull Function1<? super Wallet.Params.RejectSessionAuthenticate, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(rejectSessionAuthenticate, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.rejectAuthenticate(new Sign.Params.RejectAuthenticate(rejectSessionAuthenticate.getId(), rejectSessionAuthenticate.getReason()), new C0235a(function1, rejectSessionAuthenticate, 23), new a(function12, 11));
    }

    public final void respondSessionRequest(@NotNull Wallet.Params.SessionRequestResponse sessionRequestResponse, @NotNull Function1<? super Wallet.Params.SessionRequestResponse, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionRequestResponse, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.respond(new Sign.Params.Response(sessionRequestResponse.getSessionTopic(), ClientMapperKt.toSign(sessionRequestResponse.getJsonRpcResponse())), new C0235a(function1, sessionRequestResponse, 18), new a(function12, 7));
    }

    public final void setWalletDelegate(@NotNull WalletDelegate walletDelegate) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(walletDelegate, "delegate");
        SignClient.INSTANCE.setWalletDelegate(new WalletKit$setWalletDelegate$signWalletDelegate$1(walletDelegate, walletDelegate.getOnSessionAuthenticate() != null));
    }

    public final void updateSession(@NotNull Wallet.Params.SessionUpdate sessionUpdate, @NotNull Function1<? super Wallet.Params.SessionUpdate, Unit> function1, @NotNull Function1<? super Wallet.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(sessionUpdate, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        SignClient.INSTANCE.update(new Sign.Params.Update(sessionUpdate.getSessionTopic(), ClientMapperKt.toSign((Map) sessionUpdate.getNamespaces())), new C0235a(function1, sessionUpdate, 22), new a(function12, 10));
    }
}
