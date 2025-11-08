package com.reown.sign.client;

import com.reown.android.push.notifications.PushMessagingService;
import com.reown.sign.client.Sign;
import com.reown.sign.client.SignInterface;
import io.zksync.wrappers.ERC20;
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

@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002OPB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\r2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001JC\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J%\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00112\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010 \u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J\u0011\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$H\u0001J\u0013\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\u0011H\u0001J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020&0)H\u0001J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0)H\u0001J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0)H\u0001J\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020/0)2\u0006\u0010'\u001a\u00020\u0011H\u0001J\u000f\u00100\u001a\b\u0012\u0004\u0012\u0002010)H\u0001J\u0013\u00102\u001a\u0004\u0018\u00010+2\u0006\u00103\u001a\u000204H\u0001J3\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u0002072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u0005082\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J\u001b\u00109\u001a\u00020\u00052\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0001J9\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020?2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010@\u001a\u00020\u00052\u0006\u0010>\u001a\u00020A2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010B\u001a\u00020\u00052\u0006\u0010B\u001a\u00020C2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J9\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020G2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001J\u0011\u0010H\u001a\u00020\u00052\u0006\u0010I\u001a\u00020JH\u0001J\u0011\u0010K\u001a\u00020\u00052\u0006\u0010I\u001a\u00020LH\u0001J9\u0010M\u001a\u00020\u00052\u0006\u0010M\u001a\u00020N2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020\u00050\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\tH\u0001¨\u0006Q"}, d2 = {"Lcom/reown/sign/client/SignClient;", "Lcom/reown/sign/client/SignInterface;", "<init>", "()V", "approveAuthenticate", "", "approve", "Lcom/reown/sign/client/Sign$Params$ApproveAuthenticate;", "onSuccess", "Lkotlin/Function1;", "onError", "Lcom/reown/sign/client/Sign$Model$Error;", "approveSession", "Lcom/reown/sign/client/Sign$Params$Approve;", "authenticate", "Lcom/reown/sign/client/Sign$Params$Authenticate;", "walletAppLink", "", "connect", "Lcom/reown/sign/client/Sign$Params$Connect;", "connectParams", "Lcom/reown/sign/client/Sign$Params$ConnectParams;", "decryptMessage", "params", "Lcom/reown/sign/client/Sign$Params$DecryptMessage;", "Lcom/reown/sign/client/Sign$Model$Message;", "disconnect", "Lcom/reown/sign/client/Sign$Params$Disconnect;", "dispatchEnvelope", "urlWithEnvelope", "emit", "Lcom/reown/sign/client/Sign$Params$Emit;", "extend", "Lcom/reown/sign/client/Sign$Params$Extend;", "formatAuthMessage", "formatMessage", "Lcom/reown/sign/client/Sign$Params$FormatMessage;", "getActiveSessionByTopic", "Lcom/reown/sign/client/Sign$Model$Session;", "topic", "getListOfActiveSessions", "", "getListOfVerifyContexts", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "getPendingAuthenticateRequests", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "getPendingSessionRequests", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "getSessionProposals", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "getVerifyContext", "id", "", "initialize", "init", "Lcom/reown/sign/client/Sign$Params$Init;", "Lkotlin/Function0;", "ping", "Lcom/reown/sign/client/Sign$Params$Ping;", "sessionPing", "Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "rejectAuthenticate", "reject", "Lcom/reown/sign/client/Sign$Params$RejectAuthenticate;", "rejectSession", "Lcom/reown/sign/client/Sign$Params$Reject;", "request", "Lcom/reown/sign/client/Sign$Params$Request;", "Lcom/reown/sign/client/Sign$Model$SentRequest;", "respond", "response", "Lcom/reown/sign/client/Sign$Params$Response;", "setDappDelegate", "delegate", "Lcom/reown/sign/client/SignInterface$DappDelegate;", "setWalletDelegate", "Lcom/reown/sign/client/SignInterface$WalletDelegate;", "update", "Lcom/reown/sign/client/Sign$Params$Update;", "WalletDelegate", "DappDelegate", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignClient implements SignInterface {
    @NotNull
    public static final SignClient INSTANCE = new SignClient();
    private final /* synthetic */ SignProtocol $$delegate_0 = SignProtocol.Companion.getInstance();

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/SignClient$DappDelegate;", "Lcom/reown/sign/client/SignInterface$DappDelegate;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DappDelegate extends SignInterface.DappDelegate {

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
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/SignClient$WalletDelegate;", "Lcom/reown/sign/client/SignInterface$WalletDelegate;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface WalletDelegate extends SignInterface.WalletDelegate {

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
    }

    private SignClient() {
    }

    public void approveAuthenticate(@NotNull Sign.Params.ApproveAuthenticate approveAuthenticate, @NotNull Function1<? super Sign.Params.ApproveAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(approveAuthenticate, ERC20.FUNC_APPROVE);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.approveAuthenticate(approveAuthenticate, function1, function12);
    }

    public void approveSession(@NotNull Sign.Params.Approve approve, @NotNull Function1<? super Sign.Params.Approve, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(approve, ERC20.FUNC_APPROVE);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.approveSession(approve, function1, function12);
    }

    public void authenticate(@NotNull Sign.Params.Authenticate authenticate, @Nullable String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(authenticate, "authenticate");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.authenticate(authenticate, str, function1, function12);
    }

    @Deprecated(message = "This method is deprecated. The requiredNamespaces parameter is no longer supported as all namespaces are now treated as optional to improve connection compatibility. Use connect(connectParams: Sign.Params.ConnectParams, onSuccess: (String) -> Unit, onError: (Sign.Model.Error) -> Unit) instead.", replaceWith = @ReplaceWith(expression = "connect(connect, onSuccess, onError)", imports = {}))
    public void connect(@NotNull Sign.Params.Connect connect, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(connect, "connect");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.connect(connect, function1, function12);
    }

    public void decryptMessage(@NotNull Sign.Params.DecryptMessage decryptMessage, @NotNull Function1<? super Sign.Model.Message, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(decryptMessage, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.decryptMessage(decryptMessage, function1, function12);
    }

    public void disconnect(@NotNull Sign.Params.Disconnect disconnect, @NotNull Function1<? super Sign.Params.Disconnect, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(disconnect, "disconnect");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.disconnect(disconnect, function1, function12);
    }

    public void dispatchEnvelope(@NotNull String str, @NotNull Function1<? super Sign.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "urlWithEnvelope");
        Intrinsics.checkNotNullParameter(function1, "onError");
        this.$$delegate_0.dispatchEnvelope(str, function1);
    }

    public void emit(@NotNull Sign.Params.Emit emit, @NotNull Function1<? super Sign.Params.Emit, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.emit(emit, function1, function12);
    }

    public void extend(@NotNull Sign.Params.Extend extend, @NotNull Function1<? super Sign.Params.Extend, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(extend, "extend");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.extend(extend, function1, function12);
    }

    @NotNull
    public String formatAuthMessage(@NotNull Sign.Params.FormatMessage formatMessage) {
        Intrinsics.checkNotNullParameter(formatMessage, "formatMessage");
        return this.$$delegate_0.formatAuthMessage(formatMessage);
    }

    @Nullable
    public Sign.Model.Session getActiveSessionByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return this.$$delegate_0.getActiveSessionByTopic(str);
    }

    @NotNull
    public List<Sign.Model.Session> getListOfActiveSessions() {
        return this.$$delegate_0.getListOfActiveSessions();
    }

    @NotNull
    public List<Sign.Model.VerifyContext> getListOfVerifyContexts() {
        return this.$$delegate_0.getListOfVerifyContexts();
    }

    @NotNull
    public List<Sign.Model.SessionAuthenticate> getPendingAuthenticateRequests() {
        return this.$$delegate_0.getPendingAuthenticateRequests();
    }

    @NotNull
    public List<Sign.Model.SessionRequest> getPendingSessionRequests(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return this.$$delegate_0.getPendingSessionRequests(str);
    }

    @NotNull
    public List<Sign.Model.SessionProposal> getSessionProposals() {
        return this.$$delegate_0.getSessionProposals();
    }

    @Nullable
    public Sign.Model.VerifyContext getVerifyContext(long j2) {
        return this.$$delegate_0.getVerifyContext(j2);
    }

    public void initialize(@NotNull Sign.Params.Init init, @NotNull Function0<Unit> function0, @NotNull Function1<? super Sign.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        this.$$delegate_0.initialize(init, function0, function1);
    }

    public void ping(@NotNull Sign.Params.Ping ping, @Nullable Sign.Listeners.SessionPing sessionPing) {
        Intrinsics.checkNotNullParameter(ping, "ping");
        this.$$delegate_0.ping(ping, sessionPing);
    }

    public void rejectAuthenticate(@NotNull Sign.Params.RejectAuthenticate rejectAuthenticate, @NotNull Function1<? super Sign.Params.RejectAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(rejectAuthenticate, "reject");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.rejectAuthenticate(rejectAuthenticate, function1, function12);
    }

    public void rejectSession(@NotNull Sign.Params.Reject reject, @NotNull Function1<? super Sign.Params.Reject, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(reject, "reject");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.rejectSession(reject, function1, function12);
    }

    public void request(@NotNull Sign.Params.Request request, @NotNull Function1<? super Sign.Model.SentRequest, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.request(request, function1, function12);
    }

    public void respond(@NotNull Sign.Params.Response response, @NotNull Function1<? super Sign.Params.Response, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.respond(response, function1, function12);
    }

    public void setDappDelegate(@NotNull SignInterface.DappDelegate dappDelegate) {
        Intrinsics.checkNotNullParameter(dappDelegate, "delegate");
        this.$$delegate_0.setDappDelegate(dappDelegate);
    }

    public void setWalletDelegate(@NotNull SignInterface.WalletDelegate walletDelegate) {
        Intrinsics.checkNotNullParameter(walletDelegate, "delegate");
        this.$$delegate_0.setWalletDelegate(walletDelegate);
    }

    public void update(@NotNull Sign.Params.Update update, @NotNull Function1<? super Sign.Params.Update, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.update(update, function1, function12);
    }

    public void connect(@NotNull Sign.Params.ConnectParams connectParams, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(connectParams, "connectParams");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.connect(connectParams, function1, function12);
    }
}
