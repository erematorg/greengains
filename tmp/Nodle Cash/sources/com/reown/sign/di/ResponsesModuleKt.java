package com.reown.sign.di;

import A.a;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.CacaoVerifier;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionProposalResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionSettleResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionUpdateResponseUseCase;
import com.reown.sign.json_rpc.domain.GetSessionAuthenticateRequest;
import com.reown.sign.json_rpc.domain.GetSessionRequestByIdUseCase;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.link_mode.LinkModeStorageRepository;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"responsesModule", "Lorg/koin/core/module/Module;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nResponsesModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponsesModule.kt\ncom/reown/sign/di/ResponsesModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,64:1\n138#2,5:65\n138#2,5:70\n138#2,5:75\n138#2,5:80\n138#2,5:85\n138#2,5:90\n138#2,5:95\n138#2,5:100\n138#2,5:105\n138#2,5:110\n138#2,5:115\n138#2,5:120\n138#2,5:125\n138#2,5:130\n138#2,5:135\n138#2,5:140\n138#2,5:145\n138#2,5:150\n138#2,5:155\n138#2,5:160\n138#2,5:165\n138#2,5:170\n138#2,5:175\n138#2,5:180\n138#2,5:185\n138#2,5:190\n138#2,5:195\n138#2,5:200\n105#3,6:205\n111#3,5:233\n105#3,6:238\n111#3,5:266\n105#3,6:271\n111#3,5:299\n105#3,6:304\n111#3,5:332\n105#3,6:337\n111#3,5:365\n196#4,7:211\n203#4:232\n196#4,7:244\n203#4:265\n196#4,7:277\n203#4:298\n196#4,7:310\n203#4:331\n196#4,7:343\n203#4:364\n115#5,14:218\n115#5,14:251\n115#5,14:284\n115#5,14:317\n115#5,14:350\n*S KotlinDebug\n*F\n+ 1 ResponsesModule.kt\ncom/reown/sign/di/ResponsesModuleKt\n*L\n18#1:65,5\n19#1:70,5\n20#1:75,5\n21#1:80,5\n22#1:85,5\n28#1:90,5\n29#1:95,5\n30#1:100,5\n31#1:105,5\n32#1:110,5\n38#1:115,5\n39#1:120,5\n40#1:125,5\n41#1:130,5\n42#1:135,5\n43#1:140,5\n44#1:145,5\n45#1:150,5\n46#1:155,5\n47#1:160,5\n48#1:165,5\n49#1:170,5\n50#1:175,5\n54#1:180,5\n58#1:185,5\n59#1:190,5\n60#1:195,5\n61#1:200,5\n16#1:205,6\n16#1:233,5\n26#1:238,6\n26#1:266,5\n36#1:271,6\n36#1:299,5\n54#1:304,6\n54#1:332,5\n56#1:337,6\n56#1:365,5\n16#1:211,7\n16#1:232\n26#1:244,7\n26#1:265\n36#1:277,7\n36#1:298\n54#1:310,7\n54#1:331\n56#1:343,7\n56#1:364\n16#1:218,14\n26#1:251,14\n36#1:284,14\n54#1:317,14\n56#1:350,14\n*E\n"})
public final class ResponsesModuleKt {
    /* access modifiers changed from: private */
    public static final Unit responsesModule$lambda$5(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        b bVar = new b(14);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(OnSessionProposalResponseUseCase.class), (Qualifier) null, bVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        b bVar2 = new b(15);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionSettleResponseUseCase.class), (Qualifier) null, bVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        b bVar3 = new b(16);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionAuthenticateResponseUseCase.class), (Qualifier) null, bVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        b bVar4 = new b(17);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionUpdateResponseUseCase.class), (Qualifier) null, bVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        b bVar5 = new b(18);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionRequestResponseUseCase.class), (Qualifier) null, bVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final OnSessionProposalResponseUseCase responsesModule$lambda$5$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        return new OnSessionProposalResponseUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PairingControllerInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionSettleResponseUseCase responsesModule$lambda$5$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        return new OnSessionSettleResponseUseCase((SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), relayJsonRpcInteractorInterface, (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionAuthenticateResponseUseCase responsesModule$lambda$5$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Scope scope2 = scope;
        ParametersHolder parametersHolder2 = parametersHolder;
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        return new OnSessionAuthenticateResponseUseCase((PairingControllerInterface) scope2.get(a.t(scope2, "$this$single", parametersHolder2, "it", PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PairingInterface) scope2.get(Reflection.getOrCreateKotlinClass(PairingInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (CacaoVerifier) scope2.get(Reflection.getOrCreateKotlinClass(CacaoVerifier.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope2.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (RelayJsonRpcInteractorInterface) scope2.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope2.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AuthenticateResponseTopicRepository) scope2.get(Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope2.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope2.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope2.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (GetSessionAuthenticateRequest) scope2.get(Reflection.getOrCreateKotlinClass(GetSessionAuthenticateRequest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(LinkModeStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionUpdateResponseUseCase responsesModule$lambda$5$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OnSessionUpdateResponseUseCase((SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionRequestResponseUseCase responsesModule$lambda$5$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.CLIENT_ID);
        return new OnSessionRequestResponseUseCase((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (GetSessionRequestByIdUseCase) scope.get(Reflection.getOrCreateKotlinClass(GetSessionRequestByIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), named2, (Function0<? extends ParametersHolder>) null));
    }
}
