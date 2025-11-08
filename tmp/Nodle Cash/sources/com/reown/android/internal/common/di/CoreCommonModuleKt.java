package com.reown.android.internal.common.di;

import A.a;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.adapter.ExpiryAdapter;
import com.reown.android.internal.common.adapter.JsonRpcResultAdapter;
import com.reown.android.internal.common.adapter.TagsAdapter;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Tags;
import com.reown.foundation.di.FoundationCommonModuleKt;
import com.reown.foundation.di.FoundationDITags;
import com.reown.foundation.util.Logger;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import com.tinder.scarlet.utils.TypeUtils;
import java.lang.reflect.Type;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.jvm.KClassesJvm;
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
import timber.log.Timber;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"coreCommonModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreCommonModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreCommonModule.kt\ncom/reown/android/internal/common/di/CoreCommonModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,69:1\n138#2,5:70\n138#2,5:75\n138#2,5:80\n105#3,6:85\n111#3,5:113\n105#3,6:118\n111#3,5:146\n105#3,6:151\n111#3,5:179\n105#3,6:184\n111#3,5:212\n196#4,7:91\n203#4:112\n196#4,7:124\n203#4:145\n196#4,7:157\n203#4:178\n196#4,7:190\n203#4:211\n115#5,14:98\n115#5,14:131\n115#5,14:164\n115#5,14:197\n*S KotlinDebug\n*F\n+ 1 CoreCommonModule.kt\ncom/reown/android/internal/common/di/CoreCommonModuleKt\n*L\n32#1:70,5\n42#1:75,5\n43#1:80,5\n25#1:85,6\n25#1:113,5\n31#1:118,6\n31#1:146,5\n46#1:151,6\n46#1:179,5\n50#1:184,6\n50#1:212,5\n25#1:91,7\n25#1:112\n31#1:124,7\n31#1:145\n46#1:157,7\n46#1:178\n50#1:190,7\n50#1:211\n25#1:98,14\n31#1:131,14\n46#1:164,14\n50#1:197,14\n*E\n"})
public final class CoreCommonModuleKt {
    @NotNull
    public static final Module coreCommonModule() {
        return ModuleDSLKt.module$default(false, new b(2), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit coreCommonModule$lambda$5(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        module.includes(FoundationCommonModuleKt.foundationCommonModule());
        c cVar = new c(5);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(PolymorphicJsonAdapterFactory.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named = QualifierKt.named(AndroidCommonDITags.MOSHI);
        c cVar2 = new c(6);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Moshi.Builder.class), named, cVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        c cVar3 = new c(7);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Timber.Forest.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.LOGGER);
        c cVar4 = new c(8);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Logger.class), named2, cVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final PolymorphicJsonAdapterFactory coreCommonModule$lambda$5$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        PolymorphicJsonAdapterFactory<JsonRpcResponse> withSubtype = PolymorphicJsonAdapterFactory.of(JsonRpcResponse.class, "type").withSubtype(JsonRpcResponse.JsonRpcResult.class, "result").withSubtype(JsonRpcResponse.JsonRpcError.class, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Intrinsics.checkNotNullExpressionValue(withSubtype, "withSubtype(...)");
        return withSubtype;
    }

    /* access modifiers changed from: private */
    public static final Moshi.Builder coreCommonModule$lambda$5$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Class<PolymorphicJsonAdapterFactory> cls = PolymorphicJsonAdapterFactory.class;
        Moshi.Builder add = ((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(FoundationDITags.MOSHI), (Function0<? extends ParametersHolder>) null)).newBuilder().add((JsonAdapter.Factory) new d(0)).add((JsonAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(cls), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).add((JsonAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(cls), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
        Intrinsics.checkNotNullExpressionValue(add, "add(...)");
        return add;
    }

    /* access modifiers changed from: private */
    public static final JsonAdapter coreCommonModule$lambda$5$lambda$2$lambda$1(Type type, Set set, Moshi moshi) {
        Intrinsics.checkNotNull(type);
        String name = TypeUtils.getRawType(type).getName();
        if (Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(Expiry.class)))) {
            return ExpiryAdapter.INSTANCE;
        }
        if (Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(Tags.class)))) {
            return TagsAdapter.INSTANCE;
        }
        if (!Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(JsonRpcResponse.JsonRpcResult.class)))) {
            return null;
        }
        Intrinsics.checkNotNull(moshi);
        return new JsonRpcResultAdapter(moshi);
    }

    /* access modifiers changed from: private */
    public static final Timber.Forest coreCommonModule$lambda$5$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return Timber.Forest;
    }

    /* access modifiers changed from: private */
    public static final Logger coreCommonModule$lambda$5$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new CoreCommonModuleKt$coreCommonModule$1$4$1(scope);
    }
}
