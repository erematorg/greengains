package com.reown.android.internal.common.di;

import A.a;
import G1.C0235a;
import S0.e;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.engine.domain.PairingEngine;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.pulse.domain.SendBatchEventUseCase;
import com.reown.foundation.util.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
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

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"corePairingModule", "Lorg/koin/core/module/Module;", "pairing", "Lcom/reown/android/pairing/client/PairingInterface;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCorePairingModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CorePairingModule.kt\ncom/reown/android/internal/common/di/CorePairingModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,27:1\n138#2,5:28\n138#2,5:33\n138#2,5:38\n138#2,5:43\n138#2,5:48\n138#2,5:53\n138#2,5:58\n138#2,5:63\n138#2,5:68\n138#2,5:73\n138#2,5:78\n105#3,6:83\n111#3,5:111\n105#3,6:116\n111#3,5:144\n105#3,6:149\n111#3,5:177\n196#4,7:89\n203#4:110\n196#4,7:122\n203#4:143\n196#4,7:155\n203#4:176\n115#5,14:96\n115#5,14:129\n115#5,14:162\n*S KotlinDebug\n*F\n+ 1 CorePairingModule.kt\ncom/reown/android/internal/common/di/CorePairingModuleKt\n*L\n12#1:28,5\n13#1:33,5\n14#1:38,5\n15#1:43,5\n16#1:48,5\n17#1:53,5\n18#1:58,5\n19#1:63,5\n20#1:68,5\n21#1:73,5\n22#1:78,5\n10#1:83,6\n10#1:111,5\n25#1:116,6\n25#1:144,5\n26#1:149,6\n26#1:177,5\n10#1:89,7\n10#1:110\n25#1:122,7\n25#1:143\n26#1:155,7\n26#1:176\n10#1:96,14\n25#1:129,14\n26#1:162,14\n*E\n"})
public final class CorePairingModuleKt {
    @NotNull
    public static final Module corePairingModule(@NotNull PairingInterface pairingInterface, @NotNull PairingControllerInterface pairingControllerInterface) {
        Intrinsics.checkNotNullParameter(pairingInterface, "pairing");
        Intrinsics.checkNotNullParameter(pairingControllerInterface, "pairingController");
        return ModuleDSLKt.module$default(false, new C0235a(pairingInterface, pairingControllerInterface, 9), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit corePairingModule$lambda$3(PairingInterface pairingInterface, PairingControllerInterface pairingControllerInterface, Module module) {
        Module module2 = module;
        Intrinsics.checkNotNullParameter(module2, "$this$module");
        c cVar = new c(25);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(PairingEngine.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module2, u3);
        PairingInterface pairingInterface2 = pairingInterface;
        e eVar = new e(pairingInterface, 3);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PairingInterface.class), (Qualifier) null, eVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module2, u4);
        e eVar2 = new e(pairingControllerInterface, 4);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, eVar2, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module2, u5);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final PairingEngine corePairingModule$lambda$3$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        MetadataStorageRepositoryInterface metadataStorageRepositoryInterface = (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Class<String> cls = String.class;
        return new PairingEngine((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(a.t(scope, "$this$single", parametersHolder, "it", AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), metadataStorageRepositoryInterface, (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), relayJsonRpcInteractorInterface, (PairingStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SendBatchEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(SendBatchEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.USER_AGENT), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final PairingInterface corePairingModule$lambda$3$lambda$1(PairingInterface pairingInterface, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return pairingInterface;
    }

    /* access modifiers changed from: private */
    public static final PairingControllerInterface corePairingModule$lambda$3$lambda$2(PairingControllerInterface pairingControllerInterface, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return pairingControllerInterface;
    }
}
