package com.reown.sign.client;

import com.reown.android.push.client.a;
import com.reown.android.sdk.storage.data.dao.k;
import com.reown.sign.client.Sign;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0002MNJ4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000fH&J8\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH'J8\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&JD\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J$\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00122\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J8\u0010 \u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020!2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J8\u0010\"\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020#2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H&J:\u0010'\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u0010-\u001a\u00020\u00032\u0006\u0010-\u001a\u00020.2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u0010/\u001a\u00020\u00032\u0006\u0010/\u001a\u0002002\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J:\u00101\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J\u001c\u00103\u001a\u00020\u00032\u0006\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u000106H&J:\u00107\u001a\u00020\u00032\u0006\u00107\u001a\u0002082\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J8\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020;2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>H&J\u0012\u0010@\u001a\u0004\u0018\u00010?2\u0006\u0010A\u001a\u00020\u0012H&J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020C0>2\u0006\u0010A\u001a\u00020\u0012H&J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020E0>H&J\u0012\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020IH&J\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020K0>H&J\u000e\u0010L\u001a\b\u0012\u0004\u0012\u00020G0>H&¨\u0006OÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/SignInterface;", "", "initialize", "", "init", "Lcom/reown/sign/client/Sign$Params$Init;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "Lcom/reown/sign/client/Sign$Model$Error;", "setWalletDelegate", "delegate", "Lcom/reown/sign/client/SignInterface$WalletDelegate;", "setDappDelegate", "Lcom/reown/sign/client/SignInterface$DappDelegate;", "connect", "Lcom/reown/sign/client/Sign$Params$Connect;", "", "connectParams", "Lcom/reown/sign/client/Sign$Params$ConnectParams;", "authenticate", "Lcom/reown/sign/client/Sign$Params$Authenticate;", "walletAppLink", "dispatchEnvelope", "urlWithEnvelope", "approveSession", "approve", "Lcom/reown/sign/client/Sign$Params$Approve;", "rejectSession", "reject", "Lcom/reown/sign/client/Sign$Params$Reject;", "approveAuthenticate", "Lcom/reown/sign/client/Sign$Params$ApproveAuthenticate;", "rejectAuthenticate", "Lcom/reown/sign/client/Sign$Params$RejectAuthenticate;", "formatAuthMessage", "formatMessage", "Lcom/reown/sign/client/Sign$Params$FormatMessage;", "request", "Lcom/reown/sign/client/Sign$Params$Request;", "Lcom/reown/sign/client/Sign$Model$SentRequest;", "respond", "response", "Lcom/reown/sign/client/Sign$Params$Response;", "update", "Lcom/reown/sign/client/Sign$Params$Update;", "extend", "Lcom/reown/sign/client/Sign$Params$Extend;", "emit", "Lcom/reown/sign/client/Sign$Params$Emit;", "ping", "Lcom/reown/sign/client/Sign$Params$Ping;", "sessionPing", "Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "disconnect", "Lcom/reown/sign/client/Sign$Params$Disconnect;", "decryptMessage", "params", "Lcom/reown/sign/client/Sign$Params$DecryptMessage;", "Lcom/reown/sign/client/Sign$Model$Message;", "getListOfActiveSessions", "", "Lcom/reown/sign/client/Sign$Model$Session;", "getActiveSessionByTopic", "topic", "getPendingSessionRequests", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "getSessionProposals", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "getVerifyContext", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "id", "", "getPendingAuthenticateRequests", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "getListOfVerifyContexts", "WalletDelegate", "DappDelegate", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SignInterface {

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H&J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H&¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/SignInterface$DappDelegate;", "", "onSessionApproved", "", "approvedSession", "Lcom/reown/sign/client/Sign$Model$ApprovedSession;", "onSessionRejected", "rejectedSession", "Lcom/reown/sign/client/Sign$Model$RejectedSession;", "onSessionUpdate", "updatedSession", "Lcom/reown/sign/client/Sign$Model$UpdatedSession;", "onSessionEvent", "sessionEvent", "Lcom/reown/sign/client/Sign$Model$SessionEvent;", "Lcom/reown/sign/client/Sign$Model$Event;", "onSessionExtend", "session", "Lcom/reown/sign/client/Sign$Model$Session;", "onSessionDelete", "deletedSession", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "onSessionRequestResponse", "response", "Lcom/reown/sign/client/Sign$Model$SessionRequestResponse;", "onSessionAuthenticateResponse", "sessionAuthenticateResponse", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "onProposalExpired", "proposal", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "onRequestExpired", "request", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "onConnectionStateChange", "state", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "onError", "error", "Lcom/reown/sign/client/Sign$Model$Error;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DappDelegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onSessionAuthenticateResponse(@NotNull DappDelegate dappDelegate, @NotNull Sign.Model.SessionAuthenticateResponse sessionAuthenticateResponse) {
                Intrinsics.checkNotNullParameter(sessionAuthenticateResponse, "sessionAuthenticateResponse");
                DappDelegate.super.onSessionAuthenticateResponse(sessionAuthenticateResponse);
            }

            @Deprecated
            public static void onSessionEvent(@NotNull DappDelegate dappDelegate, @NotNull Sign.Model.Event event) {
                Intrinsics.checkNotNullParameter(event, "sessionEvent");
                DappDelegate.super.onSessionEvent(event);
            }
        }

        void onConnectionStateChange(@NotNull Sign.Model.ConnectionState connectionState);

        void onError(@NotNull Sign.Model.Error error);

        void onProposalExpired(@NotNull Sign.Model.ExpiredProposal expiredProposal);

        void onRequestExpired(@NotNull Sign.Model.ExpiredRequest expiredRequest);

        void onSessionApproved(@NotNull Sign.Model.ApprovedSession approvedSession);

        void onSessionAuthenticateResponse(@NotNull Sign.Model.SessionAuthenticateResponse sessionAuthenticateResponse) {
            Intrinsics.checkNotNullParameter(sessionAuthenticateResponse, "sessionAuthenticateResponse");
        }

        void onSessionDelete(@NotNull Sign.Model.DeletedSession deletedSession);

        void onSessionEvent(@NotNull Sign.Model.Event event) {
            Intrinsics.checkNotNullParameter(event, "sessionEvent");
        }

        @Deprecated(message = "onSessionEvent is deprecated. Use onEvent instead. Using both will result in duplicate events.", replaceWith = @ReplaceWith(expression = "onEvent(event)", imports = {}))
        void onSessionEvent(@NotNull Sign.Model.SessionEvent sessionEvent);

        void onSessionExtend(@NotNull Sign.Model.Session session);

        void onSessionRejected(@NotNull Sign.Model.RejectedSession rejectedSession);

        void onSessionRequestResponse(@NotNull Sign.Model.SessionRequestResponse sessionRequestResponse);

        void onSessionUpdate(@NotNull Sign.Model.UpdatedSession updatedSession);
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H&J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H&R(\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/SignInterface$WalletDelegate;", "", "onSessionProposal", "", "sessionProposal", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "verifyContext", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "onSessionAuthenticate", "Lkotlin/Function2;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "getOnSessionAuthenticate", "()Lkotlin/jvm/functions/Function2;", "onSessionRequest", "sessionRequest", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "onSessionDelete", "deletedSession", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "onSessionExtend", "session", "Lcom/reown/sign/client/Sign$Model$Session;", "onSessionSettleResponse", "settleSessionResponse", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "onSessionUpdateResponse", "sessionUpdateResponse", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "onProposalExpired", "proposal", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "onRequestExpired", "request", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "onConnectionStateChange", "state", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "onError", "error", "Lcom/reown/sign/client/Sign$Model$Error;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface WalletDelegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Nullable
            @Deprecated
            public static Function2<Sign.Model.SessionAuthenticate, Sign.Model.VerifyContext, Unit> getOnSessionAuthenticate(@NotNull WalletDelegate walletDelegate) {
                return WalletDelegate.super.getOnSessionAuthenticate();
            }

            @Deprecated
            public static void onProposalExpired(@NotNull WalletDelegate walletDelegate, @NotNull Sign.Model.ExpiredProposal expiredProposal) {
                Intrinsics.checkNotNullParameter(expiredProposal, "proposal");
                WalletDelegate.super.onProposalExpired(expiredProposal);
            }

            @Deprecated
            public static void onRequestExpired(@NotNull WalletDelegate walletDelegate, @NotNull Sign.Model.ExpiredRequest expiredRequest) {
                Intrinsics.checkNotNullParameter(expiredRequest, "request");
                WalletDelegate.super.onRequestExpired(expiredRequest);
            }
        }

        @Nullable
        Function2<Sign.Model.SessionAuthenticate, Sign.Model.VerifyContext, Unit> getOnSessionAuthenticate() {
            return null;
        }

        void onConnectionStateChange(@NotNull Sign.Model.ConnectionState connectionState);

        void onError(@NotNull Sign.Model.Error error);

        void onProposalExpired(@NotNull Sign.Model.ExpiredProposal expiredProposal) {
            Intrinsics.checkNotNullParameter(expiredProposal, "proposal");
        }

        void onRequestExpired(@NotNull Sign.Model.ExpiredRequest expiredRequest) {
            Intrinsics.checkNotNullParameter(expiredRequest, "request");
        }

        void onSessionDelete(@NotNull Sign.Model.DeletedSession deletedSession);

        void onSessionExtend(@NotNull Sign.Model.Session session);

        void onSessionProposal(@NotNull Sign.Model.SessionProposal sessionProposal, @NotNull Sign.Model.VerifyContext verifyContext);

        void onSessionRequest(@NotNull Sign.Model.SessionRequest sessionRequest, @NotNull Sign.Model.VerifyContext verifyContext);

        void onSessionSettleResponse(@NotNull Sign.Model.SettledSessionResponse settledSessionResponse);

        void onSessionUpdateResponse(@NotNull Sign.Model.SessionUpdateResponse sessionUpdateResponse);
    }

    static /* synthetic */ void approveSession$default(SignInterface signInterface, Sign.Params.Approve approve, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(19);
            }
            signInterface.approveSession(approve, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: approveSession");
    }

    /* access modifiers changed from: private */
    static Unit approveSession$lambda$1(Sign.Params.Approve approve) {
        Intrinsics.checkNotNullParameter(approve, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void authenticate$default(SignInterface signInterface, Sign.Params.Authenticate authenticate, String str, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                str = null;
            }
            signInterface.authenticate(authenticate, str, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authenticate");
    }

    static /* synthetic */ void disconnect$default(SignInterface signInterface, Sign.Params.Disconnect disconnect, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(23);
            }
            signInterface.disconnect(disconnect, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: disconnect");
    }

    /* access modifiers changed from: private */
    static Unit disconnect$lambda$8(Sign.Params.Disconnect disconnect) {
        Intrinsics.checkNotNullParameter(disconnect, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void emit$default(SignInterface signInterface, Sign.Params.Emit emit, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(17);
            }
            signInterface.emit(emit, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: emit");
    }

    /* access modifiers changed from: private */
    static Unit emit$lambda$7(Sign.Params.Emit emit) {
        Intrinsics.checkNotNullParameter(emit, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void extend$default(SignInterface signInterface, Sign.Params.Extend extend, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(24);
            }
            signInterface.extend(extend, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: extend");
    }

    /* access modifiers changed from: private */
    static Unit extend$lambda$6(Sign.Params.Extend extend) {
        Intrinsics.checkNotNullParameter(extend, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void initialize$default(SignInterface signInterface, Sign.Params.Init init, Function0 function0, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function0 = new a(5);
            }
            signInterface.initialize(init, function0, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initialize");
    }

    /* access modifiers changed from: private */
    static Unit initialize$lambda$0() {
        return Unit.INSTANCE;
    }

    static /* synthetic */ void ping$default(SignInterface signInterface, Sign.Params.Ping ping, Sign.Listeners.SessionPing sessionPing, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                sessionPing = null;
            }
            signInterface.ping(ping, sessionPing);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ping");
    }

    static /* synthetic */ void rejectSession$default(SignInterface signInterface, Sign.Params.Reject reject, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(22);
            }
            signInterface.rejectSession(reject, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rejectSession");
    }

    /* access modifiers changed from: private */
    static Unit rejectSession$lambda$2(Sign.Params.Reject reject) {
        Intrinsics.checkNotNullParameter(reject, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void request$default(SignInterface signInterface, Sign.Params.Request request, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(21);
            }
            signInterface.request(request, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
    }

    /* access modifiers changed from: private */
    static Unit request$lambda$3(Sign.Model.SentRequest sentRequest) {
        Intrinsics.checkNotNullParameter(sentRequest, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void respond$default(SignInterface signInterface, Sign.Params.Response response, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(20);
            }
            signInterface.respond(response, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respond");
    }

    /* access modifiers changed from: private */
    static Unit respond$lambda$4(Sign.Params.Response response) {
        Intrinsics.checkNotNullParameter(response, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void update$default(SignInterface signInterface, Sign.Params.Update update, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(18);
            }
            signInterface.update(update, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
    }

    /* access modifiers changed from: private */
    static Unit update$lambda$5(Sign.Params.Update update) {
        Intrinsics.checkNotNullParameter(update, "it");
        return Unit.INSTANCE;
    }

    void approveAuthenticate(@NotNull Sign.Params.ApproveAuthenticate approveAuthenticate, @NotNull Function1<? super Sign.Params.ApproveAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void approveSession(@NotNull Sign.Params.Approve approve, @NotNull Function1<? super Sign.Params.Approve, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void authenticate(@NotNull Sign.Params.Authenticate authenticate, @Nullable String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    @Deprecated(message = "This method is deprecated. The requiredNamespaces parameter is no longer supported as all namespaces are now treated as optional to improve connection compatibility. Use connect(connectParams: Sign.Params.ConnectParams, onSuccess: (String) -> Unit, onError: (Sign.Model.Error) -> Unit) instead.", replaceWith = @ReplaceWith(expression = "connect(connect, onSuccess, onError)", imports = {}))
    void connect(@NotNull Sign.Params.Connect connect, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void connect(@NotNull Sign.Params.ConnectParams connectParams, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void decryptMessage(@NotNull Sign.Params.DecryptMessage decryptMessage, @NotNull Function1<? super Sign.Model.Message, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void disconnect(@NotNull Sign.Params.Disconnect disconnect, @NotNull Function1<? super Sign.Params.Disconnect, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void dispatchEnvelope(@NotNull String str, @NotNull Function1<? super Sign.Model.Error, Unit> function1);

    void emit(@NotNull Sign.Params.Emit emit, @NotNull Function1<? super Sign.Params.Emit, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void extend(@NotNull Sign.Params.Extend extend, @NotNull Function1<? super Sign.Params.Extend, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    @NotNull
    String formatAuthMessage(@NotNull Sign.Params.FormatMessage formatMessage);

    @Nullable
    Sign.Model.Session getActiveSessionByTopic(@NotNull String str);

    @NotNull
    List<Sign.Model.Session> getListOfActiveSessions();

    @NotNull
    List<Sign.Model.VerifyContext> getListOfVerifyContexts();

    @NotNull
    List<Sign.Model.SessionAuthenticate> getPendingAuthenticateRequests();

    @NotNull
    List<Sign.Model.SessionRequest> getPendingSessionRequests(@NotNull String str);

    @NotNull
    List<Sign.Model.SessionProposal> getSessionProposals();

    @Nullable
    Sign.Model.VerifyContext getVerifyContext(long j2);

    void initialize(@NotNull Sign.Params.Init init, @NotNull Function0<Unit> function0, @NotNull Function1<? super Sign.Model.Error, Unit> function1);

    void ping(@NotNull Sign.Params.Ping ping, @Nullable Sign.Listeners.SessionPing sessionPing);

    void rejectAuthenticate(@NotNull Sign.Params.RejectAuthenticate rejectAuthenticate, @NotNull Function1<? super Sign.Params.RejectAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void rejectSession(@NotNull Sign.Params.Reject reject, @NotNull Function1<? super Sign.Params.Reject, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void request(@NotNull Sign.Params.Request request, @NotNull Function1<? super Sign.Model.SentRequest, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void respond(@NotNull Sign.Params.Response response, @NotNull Function1<? super Sign.Params.Response, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);

    void setDappDelegate(@NotNull DappDelegate dappDelegate);

    void setWalletDelegate(@NotNull WalletDelegate walletDelegate);

    void update(@NotNull Sign.Params.Update update, @NotNull Function1<? super Sign.Params.Update, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12);
}
