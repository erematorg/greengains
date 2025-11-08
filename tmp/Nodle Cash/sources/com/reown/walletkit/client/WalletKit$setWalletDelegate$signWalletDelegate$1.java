package com.reown.walletkit.client;

import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.sign.client.Sign;
import com.reown.sign.client.SignClient;
import com.reown.walletkit.client.Wallet;
import com.reown.walletkit.client.WalletKit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000o\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H\u0016R(\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006("}, d2 = {"com/reown/walletkit/client/WalletKit$setWalletDelegate$signWalletDelegate$1", "Lcom/reown/sign/client/SignClient$WalletDelegate;", "onSessionProposal", "", "sessionProposal", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "verifyContext", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "onSessionAuthenticate", "Lkotlin/Function2;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "getOnSessionAuthenticate", "()Lkotlin/jvm/functions/Function2;", "onSessionRequest", "sessionRequest", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "onSessionDelete", "deletedSession", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "onSessionExtend", "session", "Lcom/reown/sign/client/Sign$Model$Session;", "onSessionSettleResponse", "settleSessionResponse", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "onSessionUpdateResponse", "sessionUpdateResponse", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "onProposalExpired", "proposal", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "onRequestExpired", "request", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "onConnectionStateChange", "state", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "onError", "error", "Lcom/reown/sign/client/Sign$Model$Error;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletKit$setWalletDelegate$signWalletDelegate$1 implements SignClient.WalletDelegate {
    final /* synthetic */ WalletKit.WalletDelegate $delegate;
    final /* synthetic */ boolean $isSessionAuthenticateImplemented;

    public WalletKit$setWalletDelegate$signWalletDelegate$1(WalletKit.WalletDelegate walletDelegate, boolean z2) {
        this.$delegate = walletDelegate;
        this.$isSessionAuthenticateImplemented = z2;
    }

    /* access modifiers changed from: private */
    public static final Unit _get_onSessionAuthenticate_$lambda$0(WalletKit.WalletDelegate walletDelegate, Sign.Model.SessionAuthenticate sessionAuthenticate, Sign.Model.VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(sessionAuthenticate, "sessionAuthenticate");
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        Function2<Wallet.Model.SessionAuthenticate, Wallet.Model.VerifyContext, Unit> onSessionAuthenticate = walletDelegate.getOnSessionAuthenticate();
        if (onSessionAuthenticate != null) {
            onSessionAuthenticate.invoke(Intrinsics.checkNotNullParameter(sessionAuthenticate, "<this>"), ClientMapperKt.toWallet(verifyContext));
        }
        return Unit.INSTANCE;
    }

    public Function2<Sign.Model.SessionAuthenticate, Sign.Model.VerifyContext, Unit> getOnSessionAuthenticate() {
        if (this.$isSessionAuthenticateImplemented) {
            return new b(this.$delegate);
        }
        return null;
    }

    public void onConnectionStateChange(Sign.Model.ConnectionState connectionState) {
        Intrinsics.checkNotNullParameter(connectionState, RemoteConfigConstants.ResponseFieldKey.STATE);
        WalletKit.WalletDelegate walletDelegate = this.$delegate;
        boolean isAvailable = connectionState.isAvailable();
        Sign.Model.ConnectionState.Reason reason = connectionState.getReason();
        walletDelegate.onConnectionStateChange(new Wallet.Model.ConnectionState(isAvailable, reason != null ? ClientMapperKt.toWallet(reason) : null));
    }

    public void onError(Sign.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        this.$delegate.onError(new Wallet.Model.Error(error.getThrowable()));
    }

    public void onProposalExpired(Sign.Model.ExpiredProposal expiredProposal) {
        Intrinsics.checkNotNullParameter(expiredProposal, "proposal");
        this.$delegate.onProposalExpired(Intrinsics.checkNotNullParameter(expiredProposal, "<this>"));
    }

    public void onRequestExpired(Sign.Model.ExpiredRequest expiredRequest) {
        Intrinsics.checkNotNullParameter(expiredRequest, "request");
        this.$delegate.onRequestExpired(Intrinsics.checkNotNullParameter(expiredRequest, "<this>"));
    }

    public void onSessionDelete(Sign.Model.DeletedSession deletedSession) {
        Intrinsics.checkNotNullParameter(deletedSession, "deletedSession");
        this.$delegate.onSessionDelete(ClientMapperKt.toWallet(deletedSession));
    }

    public void onSessionExtend(Sign.Model.Session session) {
        Intrinsics.checkNotNullParameter(session, "session");
        this.$delegate.onSessionExtend(ClientMapperKt.toWallet(session));
    }

    public void onSessionProposal(Sign.Model.SessionProposal sessionProposal, Sign.Model.VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(sessionProposal, "sessionProposal");
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        this.$delegate.onSessionProposal(Intrinsics.checkNotNullParameter(sessionProposal, "<this>"), ClientMapperKt.toWallet(verifyContext));
    }

    public void onSessionRequest(Sign.Model.SessionRequest sessionRequest, Sign.Model.VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(sessionRequest, "sessionRequest");
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        this.$delegate.onSessionRequest(Intrinsics.checkNotNullParameter(sessionRequest, "<this>"), ClientMapperKt.toWallet(verifyContext));
    }

    public void onSessionSettleResponse(Sign.Model.SettledSessionResponse settledSessionResponse) {
        Intrinsics.checkNotNullParameter(settledSessionResponse, "settleSessionResponse");
        this.$delegate.onSessionSettleResponse(ClientMapperKt.toWallet(settledSessionResponse));
    }

    public void onSessionUpdateResponse(Sign.Model.SessionUpdateResponse sessionUpdateResponse) {
        Intrinsics.checkNotNullParameter(sessionUpdateResponse, "sessionUpdateResponse");
        this.$delegate.onSessionUpdateResponse(ClientMapperKt.toWallet(sessionUpdateResponse));
    }
}
