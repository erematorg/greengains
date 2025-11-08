package com.reown.android.internal.common.di;

import A.a;
import com.reown.android.internal.common.ConditionalExponentialBackoffStrategy;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.pairing.model.PairingJsonRpcMethod;
import com.reown.android.pairing.model.PairingRpc;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.foundation.util.Logger;
import com.reown.utils.JsonAdapterEntry;
import com.reown.utils.UtilFunctionsKt;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import org.koin.android.ext.koin.ModuleExtKt;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"coreJsonRpcModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreJsonRpcModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreJsonRpcModule.kt\ncom/reown/android/internal/common/di/CoreJsonRpcModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,55:1\n138#2,5:56\n138#2,5:61\n138#2,5:66\n138#2,5:71\n138#2,5:76\n138#2,5:81\n387#2:86\n387#2:87\n387#2:88\n138#2,5:89\n138#2,5:94\n138#2,5:99\n105#3,6:104\n111#3,5:132\n105#3,6:137\n111#3,5:165\n105#3,6:170\n111#3,5:198\n196#4,7:110\n203#4:131\n196#4,7:143\n203#4:164\n196#4,7:176\n203#4:197\n115#5,14:117\n115#5,14:150\n115#5,14:183\n*S KotlinDebug\n*F\n+ 1 CoreJsonRpcModule.kt\ncom/reown/android/internal/common/di/CoreJsonRpcModuleKt\n*L\n24#1:56,5\n25#1:61,5\n26#1:66,5\n27#1:71,5\n28#1:76,5\n29#1:81,5\n41#1:86\n42#1:87\n43#1:88\n44#1:89,5\n50#1:94,5\n51#1:99,5\n22#1:104,6\n22#1:132,5\n39#1:137,6\n39#1:165,5\n48#1:170,6\n48#1:198,5\n22#1:110,7\n22#1:131\n39#1:143,7\n39#1:164\n48#1:176,7\n48#1:197\n22#1:117,14\n39#1:150,14\n48#1:183,14\n*E\n"})
public final class CoreJsonRpcModuleKt {
    /* access modifiers changed from: private */
    public static final Unit coreJsonRpcModule$lambda$3(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        c cVar = new c(13);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Class<PairingRpc.PairingPing> cls = PairingRpc.PairingPing.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls));
        Class<PairingRpc.PairingDelete> cls2 = PairingRpc.PairingDelete.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls2));
        UtilFunctionsKt.addDeserializerEntry(module, PairingJsonRpcMethod.WC_PAIRING_PING, Reflection.getOrCreateKotlinClass(cls));
        UtilFunctionsKt.addDeserializerEntry(module, PairingJsonRpcMethod.WC_PAIRING_DELETE, Reflection.getOrCreateKotlinClass(cls2));
        c cVar2 = new c(14);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, cVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        c cVar3 = new c(15);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final RelayJsonRpcInteractorInterface coreJsonRpcModule$lambda$3$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new RelayJsonRpcInteractor((RelayConnectionInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayConnectionInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Codec) scope.get(Reflection.getOrCreateKotlinClass(Codec.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PushMessagesRepository) scope.get(Reflection.getOrCreateKotlinClass(PushMessagesRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null), (ConditionalExponentialBackoffStrategy) scope.get(Reflection.getOrCreateKotlinClass(ConditionalExponentialBackoffStrategy.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final JsonRpcSerializer coreJsonRpcModule$lambda$3$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new JsonRpcSerializer(CollectionsKt.toSet(scope.getAll(Reflection.getOrCreateKotlinClass(KClass.class))), MapsKt.toMap(scope.getAll(Reflection.getOrCreateKotlinClass(Pair.class))), CollectionsKt.toSet(scope.getAll(Reflection.getOrCreateKotlinClass(JsonAdapterEntry.class))), (Moshi.Builder) scope.get(Reflection.getOrCreateKotlinClass(Moshi.Builder.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final LinkModeJsonRpcInteractorInterface coreJsonRpcModule$lambda$3$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new LinkModeJsonRpcInteractor((Codec) scope.get(Reflection.getOrCreateKotlinClass(Codec.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), ModuleExtKt.androidContext(scope));
    }
}
