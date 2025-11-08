package com.reown.sign.client;

import A.a;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.di.DatabaseConfig;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.k;
import com.reown.sign.client.Sign;
import com.reown.sign.client.SignInterface;
import com.reown.sign.common.exceptions.SignClientAlreadyInitializedException;
import com.reown.sign.engine.domain.SignEngine;
import io.zksync.wrappers.ERC20;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \\2\u00020\u0001:\u0001\\B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0017H\u0016J8\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0017J8\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016JB\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J$\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u001a2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0016J8\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020*2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u0010+\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020,2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u0010-\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020.2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u0010/\u001a\u00020\u000b2\u0006\u0010/\u001a\u0002002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u0002042\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u00105\u001a\u00020\u000b2\u0006\u00105\u001a\u0002062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u00107\u001a\u00020\u000b2\u0006\u00107\u001a\u0002082\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u00109\u001a\u00020\u000b2\u0006\u00109\u001a\u00020:2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u001a\u0010;\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J8\u0010?\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020@2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J8\u0010A\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020C2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u000b0\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u000e\u0010E\u001a\b\u0012\u0004\u0012\u00020G0FH\u0016J\u0012\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010I\u001a\u00020\u001aH\u0016J\u0016\u0010J\u001a\b\u0012\u0004\u0012\u00020K0F2\u0006\u0010I\u001a\u00020\u001aH\u0016J\u000e\u0010L\u001a\b\u0012\u0004\u0012\u00020M0FH\u0016J\u000e\u0010N\u001a\b\u0012\u0004\u0012\u00020O0FH\u0016J\u0012\u0010P\u001a\u0004\u0018\u00010Q2\u0006\u0010R\u001a\u00020SH\u0016J\u000e\u0010T\u001a\b\u0012\u0004\u0012\u00020Q0FH\u0016J+\u0010U\u001a\u00020\u000b2!\u0010V\u001a\u001d\u0012\u0013\u0012\u00110W¢\u0006\f\bX\u0012\b\bY\u0012\u0004\b\b(Z\u0012\u0004\u0012\u00020\u000b0\u0011H\u0002J\b\u0010[\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lcom/reown/sign/client/SignProtocol;", "Lcom/reown/sign/client/SignInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "signEngine", "Lcom/reown/sign/engine/domain/SignEngine;", "atomicBoolean", "Ljava/util/concurrent/atomic/AtomicBoolean;", "initialize", "", "init", "Lcom/reown/sign/client/Sign$Params$Init;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "Lcom/reown/sign/client/Sign$Model$Error;", "setWalletDelegate", "delegate", "Lcom/reown/sign/client/SignInterface$WalletDelegate;", "setDappDelegate", "Lcom/reown/sign/client/SignInterface$DappDelegate;", "connect", "Lcom/reown/sign/client/Sign$Params$Connect;", "", "connectParams", "Lcom/reown/sign/client/Sign$Params$ConnectParams;", "authenticate", "Lcom/reown/sign/client/Sign$Params$Authenticate;", "walletAppLink", "dispatchEnvelope", "urlWithEnvelope", "formatAuthMessage", "formatMessage", "Lcom/reown/sign/client/Sign$Params$FormatMessage;", "approveSession", "approve", "Lcom/reown/sign/client/Sign$Params$Approve;", "rejectSession", "reject", "Lcom/reown/sign/client/Sign$Params$Reject;", "approveAuthenticate", "Lcom/reown/sign/client/Sign$Params$ApproveAuthenticate;", "rejectAuthenticate", "Lcom/reown/sign/client/Sign$Params$RejectAuthenticate;", "request", "Lcom/reown/sign/client/Sign$Params$Request;", "Lcom/reown/sign/client/Sign$Model$SentRequest;", "respond", "response", "Lcom/reown/sign/client/Sign$Params$Response;", "update", "Lcom/reown/sign/client/Sign$Params$Update;", "extend", "Lcom/reown/sign/client/Sign$Params$Extend;", "emit", "Lcom/reown/sign/client/Sign$Params$Emit;", "ping", "Lcom/reown/sign/client/Sign$Params$Ping;", "sessionPing", "Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "disconnect", "Lcom/reown/sign/client/Sign$Params$Disconnect;", "decryptMessage", "params", "Lcom/reown/sign/client/Sign$Params$DecryptMessage;", "Lcom/reown/sign/client/Sign$Model$Message;", "getListOfActiveSessions", "", "Lcom/reown/sign/client/Sign$Model$Session;", "getActiveSessionByTopic", "topic", "getPendingSessionRequests", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "getSessionProposals", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "getPendingAuthenticateRequests", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "getVerifyContext", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "id", "", "getListOfVerifyContexts", "handleConnectionState", "onDelegate", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "Lkotlin/ParameterName;", "name", "state", "checkEngineInitialization", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSignProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n+ 4 Module.kt\norg/koin/core/module/Module\n+ 5 Module.kt\norg/koin/core/module/ModuleKt\n+ 6 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,553:1\n124#2,4:554\n124#2,4:559\n142#3:558\n142#3:563\n105#4,6:564\n111#4,5:592\n196#5,7:570\n203#5:591\n115#6,14:577\n*S KotlinDebug\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol\n*L\n45#1:554,4\n49#1:559,4\n45#1:558\n49#1:563\n64#1:564,6\n64#1:592,5\n64#1:570,7\n64#1:591\n64#1:577,14\n*E\n"})
public final class SignProtocol implements SignInterface {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final SignProtocol instance = new SignProtocol((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @Nullable
    public AtomicBoolean atomicBoolean;
    @NotNull
    private final KoinApplication koinApp;
    /* access modifiers changed from: private */
    public SignEngine signEngine;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/SignProtocol$Companion;", "", "<init>", "()V", "instance", "Lcom/reown/sign/client/SignProtocol;", "getInstance", "()Lcom/reown/sign/client/SignProtocol;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SignProtocol getInstance() {
            return SignProtocol.instance;
        }

        private Companion() {
        }
    }

    public SignProtocol() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    private final void checkEngineInitialization() throws IllegalStateException {
        if (this.signEngine == null) {
            throw new IllegalStateException("SignClient needs to be initialized first using the initialize function");
        }
    }

    private final void handleConnectionState(Function1<? super Sign.Model.ConnectionState, Unit> function1) {
        SignEngine signEngine2 = this.signEngine;
        if (signEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signEngine");
            signEngine2 = null;
        }
        FlowKt.launchIn(FlowKt.onEach(signEngine2.getWssConnection(), new SignProtocol$handleConnectionState$1(this, function1, (Continuation<? super SignProtocol$handleConnectionState$1>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public static final Unit setDappDelegate$lambda$3(SignInterface.DappDelegate dappDelegate, Sign.Model.ConnectionState connectionState) {
        Intrinsics.checkNotNullParameter(connectionState, "connectionState");
        dappDelegate.onConnectionStateChange(connectionState);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit setWalletDelegate$lambda$1(SignInterface.WalletDelegate walletDelegate, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.ENABLE_AUTHENTICATE);
        b bVar = new b(walletDelegate);
        SingleInstanceFactory u3 = a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Boolean.class), named, bVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final boolean setWalletDelegate$lambda$1$lambda$0(SignInterface.WalletDelegate walletDelegate, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return walletDelegate.getOnSessionAuthenticate() != null;
    }

    /* access modifiers changed from: private */
    public static final Unit setWalletDelegate$lambda$2(SignInterface.WalletDelegate walletDelegate, Sign.Model.ConnectionState connectionState) {
        Intrinsics.checkNotNullParameter(connectionState, "connectionState");
        walletDelegate.onConnectionStateChange(connectionState);
        return Unit.INSTANCE;
    }

    public void approveAuthenticate(@NotNull Sign.Params.ApproveAuthenticate approveAuthenticate, @NotNull Function1<? super Sign.Params.ApproveAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(approveAuthenticate, ERC20.FUNC_APPROVE);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$approveAuthenticate$1(this, approveAuthenticate, function12, function1, (Continuation<? super SignProtocol$approveAuthenticate$1>) null), 3, (Object) null);
    }

    public void approveSession(@NotNull Sign.Params.Approve approve, @NotNull Function1<? super Sign.Params.Approve, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(approve, ERC20.FUNC_APPROVE);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$approveSession$1(this, approve, function12, function1, (Continuation<? super SignProtocol$approveSession$1>) null), 3, (Object) null);
    }

    public void authenticate(@NotNull Sign.Params.Authenticate authenticate, @Nullable String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(authenticate, "authenticate");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$authenticate$1(this, authenticate, str, function12, function1, (Continuation<? super SignProtocol$authenticate$1>) null), 3, (Object) null);
    }

    @Deprecated(message = "This method is deprecated. The requiredNamespaces parameter is no longer supported as all namespaces are now treated as optional to improve connection compatibility. Use connect(connectParams: Sign.Params.ConnectParams, onSuccess: (String) -> Unit, onError: (Sign.Model.Error) -> Unit) instead.", replaceWith = @ReplaceWith(expression = "connect(connect, onSuccess, onError)", imports = {}))
    public void connect(@NotNull Sign.Params.Connect connect, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(connect, "connect");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$connect$1(connect, function12, this, function1, (Continuation<? super SignProtocol$connect$1>) null), 3, (Object) null);
    }

    public void decryptMessage(@NotNull Sign.Params.DecryptMessage decryptMessage, @NotNull Function1<? super Sign.Model.Message, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(decryptMessage, "params");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$decryptMessage$1(this, decryptMessage, function12, function1, (Continuation<? super SignProtocol$decryptMessage$1>) null), 3, (Object) null);
    }

    public void disconnect(@NotNull Sign.Params.Disconnect disconnect, @NotNull Function1<? super Sign.Params.Disconnect, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(disconnect, "disconnect");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$disconnect$1(this, disconnect, function12, function1, (Continuation<? super SignProtocol$disconnect$1>) null), 3, (Object) null);
    }

    public void dispatchEnvelope(@NotNull String str, @NotNull Function1<? super Sign.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, "urlWithEnvelope");
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$dispatchEnvelope$1(this, str, function1, (Continuation<? super SignProtocol$dispatchEnvelope$1>) null), 3, (Object) null);
    }

    public void emit(@NotNull Sign.Params.Emit emit, @NotNull Function1<? super Sign.Params.Emit, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(emit, "emit");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$emit$1(this, emit, function12, function1, (Continuation<? super SignProtocol$emit$1>) null), 3, (Object) null);
    }

    public void extend(@NotNull Sign.Params.Extend extend, @NotNull Function1<? super Sign.Params.Extend, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(extend, "extend");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$extend$1(this, extend, function12, function1, (Continuation<? super SignProtocol$extend$1>) null), 3, (Object) null);
    }

    @NotNull
    public String formatAuthMessage(@NotNull Sign.Params.FormatMessage formatMessage) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(formatMessage, "formatMessage");
        checkEngineInitialization();
        return (String) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$formatAuthMessage$1(this, formatMessage, (Continuation<? super SignProtocol$formatAuthMessage$1>) null), 1, (Object) null);
    }

    @Nullable
    public Sign.Model.Session getActiveSessionByTopic(@NotNull String str) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        checkEngineInitialization();
        return (Sign.Model.Session) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getActiveSessionByTopic$1(this, str, (Continuation<? super SignProtocol$getActiveSessionByTopic$1>) null), 1, (Object) null);
    }

    @NotNull
    public List<Sign.Model.Session> getListOfActiveSessions() throws IllegalStateException {
        checkEngineInitialization();
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getListOfActiveSessions$1(this, (Continuation<? super SignProtocol$getListOfActiveSessions$1>) null), 1, (Object) null);
    }

    @NotNull
    public List<Sign.Model.VerifyContext> getListOfVerifyContexts() throws IllegalStateException {
        checkEngineInitialization();
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getListOfVerifyContexts$1(this, (Continuation<? super SignProtocol$getListOfVerifyContexts$1>) null), 1, (Object) null);
    }

    @NotNull
    public List<Sign.Model.SessionAuthenticate> getPendingAuthenticateRequests() throws IllegalStateException {
        checkEngineInitialization();
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getPendingAuthenticateRequests$1(this, (Continuation<? super SignProtocol$getPendingAuthenticateRequests$1>) null), 1, (Object) null);
    }

    @NotNull
    public List<Sign.Model.SessionRequest> getPendingSessionRequests(@NotNull String str) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        checkEngineInitialization();
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getPendingSessionRequests$1(this, str, (Continuation<? super SignProtocol$getPendingSessionRequests$1>) null), 1, (Object) null);
    }

    @NotNull
    public List<Sign.Model.SessionProposal> getSessionProposals() throws IllegalStateException {
        checkEngineInitialization();
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getSessionProposals$1(this, (Continuation<? super SignProtocol$getSessionProposals$1>) null), 1, (Object) null);
    }

    @Nullable
    public Sign.Model.VerifyContext getVerifyContext(long j2) throws IllegalStateException {
        checkEngineInitialization();
        return (Sign.Model.VerifyContext) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SignProtocol$getVerifyContext$1(this, j2, (Continuation<? super SignProtocol$getVerifyContext$1>) null), 1, (Object) null);
    }

    public void initialize(@NotNull Sign.Params.Init init, @NotNull Function0<Unit> function0, @NotNull Function1<? super Sign.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (this.signEngine == null) {
            try {
                SignEngine signEngine2 = null;
                this.koinApp.modules(ModuleDSLKt.module$default(false, new k(29), 1, (Object) null), Intrinsics.checkNotNullParameter(((DatabaseConfig) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getSIGN_SDK_DB_NAME(), "dbName"), ModuleDSLKt.module$default(false, new k(26), 1, (Object) null));
                SignEngine signEngine3 = (SignEngine) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(SignEngine.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
                this.signEngine = signEngine3;
                if (signEngine3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                } else {
                    signEngine2 = signEngine3;
                }
                signEngine2.setup();
                function0.invoke();
            } catch (Exception e3) {
                function1.invoke(new Sign.Model.Error(e3));
            }
        } else {
            function1.invoke(new Sign.Model.Error(new SignClientAlreadyInitializedException()));
        }
    }

    public void ping(@NotNull Sign.Params.Ping ping, @Nullable Sign.Listeners.SessionPing sessionPing) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(ping, "ping");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$ping$1(this, ping, sessionPing, (Continuation<? super SignProtocol$ping$1>) null), 3, (Object) null);
    }

    public void rejectAuthenticate(@NotNull Sign.Params.RejectAuthenticate rejectAuthenticate, @NotNull Function1<? super Sign.Params.RejectAuthenticate, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(rejectAuthenticate, "reject");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$rejectAuthenticate$1(this, rejectAuthenticate, function12, function1, (Continuation<? super SignProtocol$rejectAuthenticate$1>) null), 3, (Object) null);
    }

    public void rejectSession(@NotNull Sign.Params.Reject reject, @NotNull Function1<? super Sign.Params.Reject, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(reject, "reject");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$rejectSession$1(this, reject, function12, function1, (Continuation<? super SignProtocol$rejectSession$1>) null), 3, (Object) null);
    }

    public void request(@NotNull Sign.Params.Request request, @NotNull Function1<? super Sign.Model.SentRequest, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$request$1(this, request, function12, function1, (Continuation<? super SignProtocol$request$1>) null), 3, (Object) null);
    }

    public void respond(@NotNull Sign.Params.Response response, @NotNull Function1<? super Sign.Params.Response, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$respond$1(this, response, function12, function1, (Continuation<? super SignProtocol$respond$1>) null), 3, (Object) null);
    }

    public void setDappDelegate(@NotNull SignInterface.DappDelegate dappDelegate) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(dappDelegate, "delegate");
        checkEngineInitialization();
        handleConnectionState(new e(dappDelegate, 2));
        SignEngine signEngine2 = this.signEngine;
        if (signEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signEngine");
            signEngine2 = null;
        }
        FlowKt.launchIn(FlowKt.onEach(signEngine2.getEngineEvent(), new SignProtocol$setDappDelegate$2(dappDelegate, (Continuation<? super SignProtocol$setDappDelegate$2>) null)), WalletConnectScopeKt.getScope());
    }

    public void setWalletDelegate(@NotNull SignInterface.WalletDelegate walletDelegate) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(walletDelegate, "delegate");
        checkEngineInitialization();
        KoinApplicationKt.getWcKoinApp().modules(ModuleDSLKt.module$default(false, new a(walletDelegate, 0), 1, (Object) null));
        handleConnectionState(new a(walletDelegate, 1));
        SignEngine signEngine2 = this.signEngine;
        if (signEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signEngine");
            signEngine2 = null;
        }
        FlowKt.launchIn(FlowKt.onEach(signEngine2.getEngineEvent(), new SignProtocol$setWalletDelegate$3(walletDelegate, (Continuation<? super SignProtocol$setWalletDelegate$3>) null)), WalletConnectScopeKt.getScope());
    }

    public void update(@NotNull Sign.Params.Update update, @NotNull Function1<? super Sign.Params.Update, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$update$1(this, update, function12, function1, (Continuation<? super SignProtocol$update$1>) null), 3, (Object) null);
    }

    public SignProtocol(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SignProtocol(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }

    public void connect(@NotNull Sign.Params.ConnectParams connectParams, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Sign.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(connectParams, "connectParams");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignProtocol$connect$2(connectParams, function12, this, function1, (Continuation<? super SignProtocol$connect$2>) null), 3, (Object) null);
    }
}
