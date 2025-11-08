package com.reown.sign.di;

import A.a;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.verify.domain.ResolveAttestationIdUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.engine.use_case.requests.OnPingUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionDeleteUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionEventUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"requestsModule", "Lorg/koin/core/module/Module;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRequestsModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestsModule.kt\ncom/reown/sign/di/RequestsModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,77:1\n138#2,5:78\n138#2,5:83\n138#2,5:88\n138#2,5:93\n138#2,5:98\n138#2,5:103\n138#2,5:108\n138#2,5:113\n138#2,5:118\n138#2,5:123\n138#2,5:128\n138#2,5:133\n138#2,5:138\n138#2,5:143\n138#2,5:148\n138#2,5:153\n138#2,5:158\n138#2,5:163\n138#2,5:168\n138#2,5:173\n138#2,5:178\n138#2,5:183\n138#2,5:188\n138#2,5:193\n138#2,5:198\n138#2,5:203\n138#2,5:208\n138#2,5:213\n138#2,5:218\n138#2,5:223\n138#2,5:228\n138#2,5:233\n138#2,5:238\n105#3,6:243\n111#3,5:271\n105#3,6:276\n111#3,5:304\n105#3,6:309\n111#3,5:337\n105#3,6:342\n111#3,5:370\n105#3,6:375\n111#3,5:403\n105#3,6:408\n111#3,5:436\n105#3,6:441\n111#3,5:469\n105#3,6:474\n111#3,5:502\n105#3,6:507\n111#3,5:535\n196#4,7:249\n203#4:270\n196#4,7:282\n203#4:303\n196#4,7:315\n203#4:336\n196#4,7:348\n203#4:369\n196#4,7:381\n203#4:402\n196#4,7:414\n203#4:435\n196#4,7:447\n203#4:468\n196#4,7:480\n203#4:501\n196#4,7:513\n203#4:534\n115#5,14:256\n115#5,14:289\n115#5,14:322\n115#5,14:355\n115#5,14:388\n115#5,14:421\n115#5,14:454\n115#5,14:487\n115#5,14:520\n*S KotlinDebug\n*F\n+ 1 RequestsModule.kt\ncom/reown/sign/di/RequestsModuleKt\n*L\n22#1:78,5\n23#1:83,5\n24#1:88,5\n25#1:93,5\n26#1:98,5\n27#1:103,5\n33#1:108,5\n34#1:113,5\n35#1:118,5\n36#1:123,5\n37#1:128,5\n38#1:133,5\n39#1:138,5\n45#1:143,5\n46#1:148,5\n47#1:153,5\n48#1:158,5\n49#1:163,5\n50#1:168,5\n51#1:173,5\n52#1:178,5\n58#1:183,5\n59#1:188,5\n60#1:193,5\n61#1:198,5\n62#1:203,5\n63#1:208,5\n64#1:213,5\n68#1:218,5\n70#1:223,5\n72#1:228,5\n74#1:233,5\n76#1:238,5\n20#1:243,6\n20#1:271,5\n31#1:276,6\n31#1:304,5\n43#1:309,6\n43#1:337,5\n56#1:342,6\n56#1:370,5\n68#1:375,6\n68#1:403,5\n70#1:408,6\n70#1:436,5\n72#1:441,6\n72#1:469,5\n74#1:474,6\n74#1:502,5\n76#1:507,6\n76#1:535,5\n20#1:249,7\n20#1:270\n31#1:282,7\n31#1:303\n43#1:315,7\n43#1:336\n56#1:348,7\n56#1:369\n68#1:381,7\n68#1:402\n70#1:414,7\n70#1:435\n72#1:447,7\n72#1:468\n74#1:480,7\n74#1:501\n76#1:513,7\n76#1:534\n20#1:256,14\n31#1:289,14\n43#1:322,14\n56#1:355,14\n68#1:388,14\n70#1:421,14\n72#1:454,14\n74#1:487,14\n76#1:520,14\n*E\n"})
public final class RequestsModuleKt {
    /* access modifiers changed from: private */
    public static final Unit requestsModule$lambda$9(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        b bVar = new b(5);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(OnSessionProposalUseCase.class), (Qualifier) null, bVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        b bVar2 = new b(6);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionAuthenticateUseCase.class), (Qualifier) null, bVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        b bVar3 = new b(7);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionSettleUseCase.class), (Qualifier) null, bVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        b bVar4 = new b(8);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionRequestUseCase.class), (Qualifier) null, bVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        b bVar5 = new b(9);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionDeleteUseCase.class), (Qualifier) null, bVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        b bVar6 = new b(10);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionEventUseCase.class), (Qualifier) null, bVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        b bVar7 = new b(11);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionUpdateUseCase.class), (Qualifier) null, bVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        b bVar8 = new b(12);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnSessionExtendUseCase.class), (Qualifier) null, bVar8, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        b bVar9 = new b(13);
        SingleInstanceFactory u11 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OnPingUseCase.class), (Qualifier) null, bVar9, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final OnSessionProposalUseCase requestsModule$lambda$9$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        return new OnSessionProposalUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ResolveAttestationIdUseCase) scope.get(Reflection.getOrCreateKotlinClass(ResolveAttestationIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PairingControllerInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionAuthenticateUseCase requestsModule$lambda$9$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        return new OnSessionAuthenticateUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ResolveAttestationIdUseCase) scope.get(Reflection.getOrCreateKotlinClass(ResolveAttestationIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PairingControllerInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionSettleUseCase requestsModule$lambda$9$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        SessionStorageRepository sessionStorageRepository = (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        return new OnSessionSettleUseCase((KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), relayJsonRpcInteractorInterface, (ProposalStorageRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), sessionStorageRepository, (PairingControllerInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionRequestUseCase requestsModule$lambda$9$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        SessionStorageRepository sessionStorageRepository = (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        return new OnSessionRequestUseCase(relayJsonRpcInteractorInterface, sessionStorageRepository, (MetadataStorageRepositoryInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ResolveAttestationIdUseCase) scope.get(Reflection.getOrCreateKotlinClass(ResolveAttestationIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionDeleteUseCase requestsModule$lambda$9$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        return new OnSessionDeleteUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionEventUseCase requestsModule$lambda$9$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OnSessionEventUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionUpdateUseCase requestsModule$lambda$9$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OnSessionUpdateUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnSessionExtendUseCase requestsModule$lambda$9$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OnSessionExtendUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final OnPingUseCase requestsModule$lambda$9$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OnPingUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }
}
