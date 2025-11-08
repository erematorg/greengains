package com.reown.sign.di;

import A.a;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.CacaoVerifier;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.push.notifications.DecryptMessageUseCaseInterface;
import com.reown.android.sdk.storage.data.dao.k;
import com.reown.foundation.util.Logger;
import com.reown.sign.engine.domain.SignEngine;
import com.reown.sign.engine.domain.wallet_service.WalletServiceFinder;
import com.reown.sign.engine.domain.wallet_service.WalletServiceRequester;
import com.reown.sign.engine.model.tvf.TVF;
import com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ApproveSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.DisconnectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.EmitEventUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ExtendSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.FormatAuthenticateMessageUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetPairingsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetPendingAuthenticateRequestUseCase;
import com.reown.sign.engine.use_case.calls.GetPendingAuthenticateRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionProposalsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PairUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PingUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ProposeSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionUpdateUseCaseInterface;
import com.reown.sign.engine.use_case.requests.OnPingUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionDeleteUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionEventUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionProposalResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionSettleResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionUpdateResponseUseCase;
import com.reown.sign.json_rpc.domain.DeleteRequestByIdUseCase;
import com.reown.sign.json_rpc.domain.GetPendingJsonRpcHistoryEntryByIdUseCase;
import com.reown.sign.json_rpc.domain.GetPendingRequestsUseCaseByTopicInterface;
import com.reown.sign.json_rpc.domain.GetPendingSessionAuthenticateRequest;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequestByTopicUseCaseInterface;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequests;
import com.reown.sign.json_rpc.domain.GetSessionAuthenticateRequest;
import com.reown.sign.json_rpc.domain.GetSessionRequestByIdUseCase;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import com.squareup.moshi.Moshi;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"engineModule", "Lorg/koin/core/module/Module;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEngineModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EngineModule.kt\ncom/reown/sign/di/EngineModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,114:1\n138#2,5:115\n138#2,5:120\n138#2,5:125\n138#2,5:130\n138#2,5:135\n138#2,5:140\n138#2,5:145\n138#2,5:150\n138#2,5:155\n138#2,5:160\n138#2,5:165\n138#2,5:170\n138#2,5:175\n138#2,5:180\n138#2,5:185\n138#2,5:190\n138#2,5:195\n138#2,5:200\n138#2,5:205\n138#2,5:210\n138#2,5:215\n138#2,5:220\n138#2,5:225\n138#2,5:230\n138#2,5:235\n138#2,5:240\n138#2,5:245\n138#2,5:250\n138#2,5:255\n138#2,5:260\n138#2,5:265\n138#2,5:270\n138#2,5:275\n138#2,5:280\n138#2,5:285\n138#2,5:290\n138#2,5:295\n138#2,5:300\n138#2,5:305\n138#2,5:310\n138#2,5:315\n138#2,5:320\n138#2,5:325\n138#2,5:330\n138#2,5:335\n138#2,5:340\n138#2,5:345\n138#2,5:350\n138#2,5:355\n138#2,5:360\n138#2,5:365\n138#2,5:370\n138#2,5:375\n138#2,5:380\n138#2,5:385\n138#2,5:390\n138#2,5:395\n138#2,5:400\n138#2,5:405\n138#2,5:410\n138#2,5:415\n105#3,6:420\n111#3,5:448\n105#3,6:453\n111#3,5:481\n105#3,6:486\n111#3,5:514\n105#3,6:519\n111#3,5:547\n105#3,6:552\n111#3,5:580\n105#3,6:585\n111#3,5:613\n105#3,6:618\n111#3,5:646\n105#3,6:651\n111#3,5:679\n105#3,6:684\n111#3,5:712\n105#3,6:717\n111#3,5:745\n105#3,6:750\n111#3,5:778\n105#3,6:783\n111#3,5:811\n196#4,7:426\n203#4:447\n196#4,7:459\n203#4:480\n196#4,7:492\n203#4:513\n196#4,7:525\n203#4:546\n196#4,7:558\n203#4:579\n196#4,7:591\n203#4:612\n196#4,7:624\n203#4:645\n196#4,7:657\n203#4:678\n196#4,7:690\n203#4:711\n196#4,7:723\n203#4:744\n196#4,7:756\n203#4:777\n196#4,7:789\n203#4:810\n115#5,14:433\n115#5,14:466\n115#5,14:499\n115#5,14:532\n115#5,14:565\n115#5,14:598\n115#5,14:631\n115#5,14:664\n115#5,14:697\n115#5,14:730\n115#5,14:763\n115#5,14:796\n*S KotlinDebug\n*F\n+ 1 EngineModule.kt\ncom/reown/sign/di/EngineModuleKt\n*L\n29#1:115,5\n31#1:120,5\n33#1:125,5\n35#1:130,5\n37#1:135,5\n39#1:140,5\n41#1:145,5\n43#1:150,5\n45#1:155,5\n47#1:160,5\n61#1:165,5\n62#1:170,5\n63#1:175,5\n64#1:180,5\n65#1:185,5\n66#1:190,5\n67#1:195,5\n68#1:200,5\n69#1:205,5\n70#1:210,5\n71#1:215,5\n72#1:220,5\n73#1:225,5\n74#1:230,5\n75#1:235,5\n76#1:240,5\n77#1:245,5\n78#1:250,5\n79#1:255,5\n80#1:260,5\n81#1:265,5\n82#1:270,5\n83#1:275,5\n84#1:280,5\n85#1:285,5\n86#1:290,5\n87#1:295,5\n88#1:300,5\n89#1:305,5\n90#1:310,5\n91#1:315,5\n92#1:320,5\n93#1:325,5\n94#1:330,5\n95#1:335,5\n96#1:340,5\n97#1:345,5\n98#1:350,5\n99#1:355,5\n100#1:360,5\n101#1:365,5\n102#1:370,5\n103#1:375,5\n104#1:380,5\n105#1:385,5\n106#1:390,5\n107#1:395,5\n108#1:400,5\n109#1:405,5\n110#1:410,5\n111#1:415,5\n29#1:420,6\n29#1:448,5\n31#1:453,6\n31#1:481,5\n33#1:486,6\n33#1:514,5\n35#1:519,6\n35#1:547,5\n37#1:552,6\n37#1:580,5\n39#1:585,6\n39#1:613,5\n41#1:618,6\n41#1:646,5\n43#1:651,6\n43#1:679,5\n45#1:684,6\n45#1:712,5\n47#1:717,6\n47#1:745,5\n49#1:750,6\n49#1:778,5\n59#1:783,6\n59#1:811,5\n29#1:426,7\n29#1:447\n31#1:459,7\n31#1:480\n33#1:492,7\n33#1:513\n35#1:525,7\n35#1:546\n37#1:558,7\n37#1:579\n39#1:591,7\n39#1:612\n41#1:624,7\n41#1:645\n43#1:657,7\n43#1:678\n45#1:690,7\n45#1:711\n47#1:723,7\n47#1:744\n49#1:756,7\n49#1:777\n59#1:789,7\n59#1:810\n29#1:433,14\n31#1:466,14\n33#1:499,14\n35#1:532,14\n37#1:565,14\n39#1:598,14\n41#1:631,14\n43#1:664,14\n45#1:697,14\n47#1:730,14\n49#1:763,14\n59#1:796,14\n*E\n"})
public final /* synthetic */ class EngineModuleKt {
    /* access modifiers changed from: private */
    public static final Unit engineModule$lambda$12(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        module.includes(ModuleDSLKt.module$default(false, new k(25), 1, (Object) null), ModuleDSLKt.module$default(false, new k(27), 1, (Object) null), ModuleDSLKt.module$default(false, new k(28), 1, (Object) null));
        a aVar = new a(23);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(TVF.class), (Qualifier) null, aVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        a aVar2 = new a(28);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingSessionRequests.class), (Qualifier) null, aVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        a aVar3 = new a(29);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingAuthenticateRequestUseCaseInterface.class), (Qualifier) null, aVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        b bVar = new b(0);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(DeleteRequestByIdUseCase.class), (Qualifier) null, bVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        b bVar2 = new b(1);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingJsonRpcHistoryEntryByIdUseCase.class), (Qualifier) null, bVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        b bVar3 = new b(2);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetSessionRequestByIdUseCase.class), (Qualifier) null, bVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        b bVar4 = new b(3);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingSessionAuthenticateRequest.class), (Qualifier) null, bVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        b bVar5 = new b(4);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetSessionAuthenticateRequest.class), (Qualifier) null, bVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        a aVar4 = new a(24);
        SingleInstanceFactory u11 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(CacaoVerifier.class), (Qualifier) null, aVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        a aVar5 = new a(25);
        SingleInstanceFactory u12 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(WalletServiceFinder.class), (Qualifier) null, aVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module, u12);
        a aVar6 = new a(26);
        SingleInstanceFactory u13 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(WalletServiceRequester.class), (Qualifier) null, aVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u13);
        }
        new KoinDefinition(module, u13);
        a aVar7 = new a(27);
        SingleInstanceFactory u14 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SignEngine.class), (Qualifier) null, aVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u14);
        }
        new KoinDefinition(module, u14);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final TVF engineModule$lambda$12$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new TVF((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPendingSessionRequests engineModule$lambda$12$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPendingSessionRequests((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final WalletServiceRequester engineModule$lambda$12$lambda$10(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return new WalletServiceRequester(builder.connectTimeout(30, timeUnit).readTimeout(30, timeUnit).writeTimeout(30, timeUnit).build());
    }

    /* access modifiers changed from: private */
    public static final SignEngine engineModule$lambda$12$lambda$11(Scope scope, ParametersHolder parametersHolder) {
        Scope scope2 = scope;
        ParametersHolder parametersHolder2 = parametersHolder;
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope2.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        KeyManagementRepository keyManagementRepository = (KeyManagementRepository) scope2.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        AuthenticateResponseTopicRepository authenticateResponseTopicRepository = (AuthenticateResponseTopicRepository) scope2.get(Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        ProposalStorageRepository proposalStorageRepository = (ProposalStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        SessionStorageRepository sessionStorageRepository = (SessionStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        MetadataStorageRepositoryInterface metadataStorageRepositoryInterface = (MetadataStorageRepositoryInterface) scope2.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        EmitEventUseCaseInterface emitEventUseCaseInterface = (EmitEventUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(EmitEventUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        ExtendSessionUseCaseInterface extendSessionUseCaseInterface = (ExtendSessionUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(ExtendSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Qualifier named = QualifierKt.named(AndroidCommonDITags.DECRYPT_SIGN_MESSAGE);
        GetPairingsUseCaseInterface getPairingsUseCaseInterface = (GetPairingsUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetPairingsUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetPendingRequestsUseCaseByTopicInterface getPendingRequestsUseCaseByTopicInterface = (GetPendingRequestsUseCaseByTopicInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetPendingRequestsUseCaseByTopicInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetPendingSessionRequests getPendingSessionRequests = (GetPendingSessionRequests) scope2.get(Reflection.getOrCreateKotlinClass(GetPendingSessionRequests.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetSessionProposalsUseCaseInterface getSessionProposalsUseCaseInterface = (GetSessionProposalsUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetSessionProposalsUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetSessionsUseCaseInterface getSessionsUseCaseInterface = (GetSessionsUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetSessionsUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetVerifyContextByIdUseCaseInterface getVerifyContextByIdUseCaseInterface = (GetVerifyContextByIdUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetVerifyContextByIdUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionDeleteUseCase onSessionDeleteUseCase = (OnSessionDeleteUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionDeleteUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionEventUseCase onSessionEventUseCase = (OnSessionEventUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionExtendUseCase onSessionExtendUseCase = (OnSessionExtendUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionExtendUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetPendingSessionRequestByTopicUseCaseInterface getPendingSessionRequestByTopicUseCaseInterface = (GetPendingSessionRequestByTopicUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetPendingSessionRequestByTopicUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionProposalUseCase onSessionProposalUseCase = (OnSessionProposalUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionProposalUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionRequestUseCase onSessionRequestUseCase = (OnSessionRequestUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionRequestUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionSettleResponseUseCase onSessionSettleResponseUseCase = (OnSessionSettleResponseUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionSettleResponseUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionSettleUseCase onSessionSettleUseCase = (OnSessionSettleUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionSettleUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionUpdateResponseUseCase onSessionUpdateResponseUseCase = (OnSessionUpdateResponseUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionUpdateResponseUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionUpdateUseCase onSessionUpdateUseCase = (OnSessionUpdateUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionUpdateUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        PairingControllerInterface pairingControllerInterface = (PairingControllerInterface) scope2.get(Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        PairUseCaseInterface pairUseCaseInterface = (PairUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(PairUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        PingUseCaseInterface pingUseCaseInterface = (PingUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(PingUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        ProposeSessionUseCaseInterface proposeSessionUseCaseInterface = (ProposeSessionUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(ProposeSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RejectSessionUseCaseInterface rejectSessionUseCaseInterface = (RejectSessionUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(RejectSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RespondSessionRequestUseCaseInterface respondSessionRequestUseCaseInterface = (RespondSessionRequestUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(RespondSessionRequestUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        SessionRequestUseCaseInterface sessionRequestUseCaseInterface = (SessionRequestUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(SessionRequestUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        SessionUpdateUseCaseInterface sessionUpdateUseCaseInterface = (SessionUpdateUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(SessionUpdateUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionAuthenticateUseCase onSessionAuthenticateUseCase = (OnSessionAuthenticateUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionAuthenticateUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase = (OnSessionAuthenticateResponseUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionAuthenticateResponseUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        ApproveSessionAuthenticateUseCaseInterface approveSessionAuthenticateUseCaseInterface = (ApproveSessionAuthenticateUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(ApproveSessionAuthenticateUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RejectSessionAuthenticateUseCaseInterface rejectSessionAuthenticateUseCaseInterface = (RejectSessionAuthenticateUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(RejectSessionAuthenticateUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        FormatAuthenticateMessageUseCaseInterface formatAuthenticateMessageUseCaseInterface = (FormatAuthenticateMessageUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(FormatAuthenticateMessageUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        DeleteRequestByIdUseCase deleteRequestByIdUseCase = (DeleteRequestByIdUseCase) scope2.get(Reflection.getOrCreateKotlinClass(DeleteRequestByIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        return new SignEngine(relayJsonRpcInteractorInterface, getPendingRequestsUseCaseByTopicInterface, getPendingSessionRequestByTopicUseCaseInterface, getPendingSessionRequests, (GetPendingAuthenticateRequestUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetPendingAuthenticateRequestUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), deleteRequestByIdUseCase, keyManagementRepository, authenticateResponseTopicRepository, proposalStorageRepository, sessionStorageRepository, metadataStorageRepositoryInterface, pairingControllerInterface, (VerifyContextStorageRepository) scope2.get(a.t(scope2, "$this$single", parametersHolder2, "it", VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), proposeSessionUseCaseInterface, (SessionAuthenticateUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(SessionAuthenticateUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), pairUseCaseInterface, rejectSessionUseCaseInterface, (ApproveSessionUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(ApproveSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), approveSessionAuthenticateUseCaseInterface, rejectSessionAuthenticateUseCaseInterface, sessionUpdateUseCaseInterface, sessionRequestUseCaseInterface, respondSessionRequestUseCaseInterface, pingUseCaseInterface, formatAuthenticateMessageUseCaseInterface, emitEventUseCaseInterface, extendSessionUseCaseInterface, (DisconnectSessionUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(DisconnectSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (DecryptMessageUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(DecryptMessageUseCaseInterface.class), named, (Function0<? extends ParametersHolder>) null), getSessionsUseCaseInterface, getPairingsUseCaseInterface, getSessionProposalsUseCaseInterface, getVerifyContextByIdUseCaseInterface, (GetListOfVerifyContextsUseCaseInterface) scope2.get(Reflection.getOrCreateKotlinClass(GetListOfVerifyContextsUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), onSessionProposalUseCase, onSessionAuthenticateUseCase, onSessionSettleUseCase, onSessionRequestUseCase, onSessionDeleteUseCase, onSessionEventUseCase, onSessionUpdateUseCase, onSessionExtendUseCase, (OnPingUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnPingUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (OnSessionProposalResponseUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionProposalResponseUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), onSessionAuthenticateResponseUseCase, onSessionSettleResponseUseCase, onSessionUpdateResponseUseCase, (OnSessionRequestResponseUseCase) scope2.get(Reflection.getOrCreateKotlinClass(OnSessionRequestResponseUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope2.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeJsonRpcInteractorInterface) scope2.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope2.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPendingAuthenticateRequestUseCaseInterface engineModule$lambda$12$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPendingAuthenticateRequestUseCase((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final DeleteRequestByIdUseCase engineModule$lambda$12$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new DeleteRequestByIdUseCase((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPendingJsonRpcHistoryEntryByIdUseCase engineModule$lambda$12$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPendingJsonRpcHistoryEntryByIdUseCase((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetSessionRequestByIdUseCase engineModule$lambda$12$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetSessionRequestByIdUseCase((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPendingSessionAuthenticateRequest engineModule$lambda$12$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPendingSessionAuthenticateRequest((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetSessionAuthenticateRequest engineModule$lambda$12$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetSessionAuthenticateRequest((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final CacaoVerifier engineModule$lambda$12$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new CacaoVerifier((ProjectId) scope.get(Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final WalletServiceFinder engineModule$lambda$12$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new WalletServiceFinder((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }
}
