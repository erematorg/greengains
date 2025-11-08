package com.reown.foundation.di;

import com.reown.android.internal.common.di.d;
import com.reown.foundation.common.adapters.SubscriptionIdAdapter;
import com.reown.foundation.common.adapters.TopicAdapter;
import com.reown.foundation.common.adapters.TtlAdapter;
import com.reown.foundation.common.model.SubscriptionId;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.di.c;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
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
import u1.a;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"foundationCommonModule", "Lorg/koin/core/module/Module;", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFoundationCommonModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoundationCommonModule.kt\ncom/reown/foundation/di/FoundationCommonModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,56:1\n138#2,5:57\n105#3,6:62\n111#3,5:90\n105#3,6:95\n111#3,5:123\n105#3,6:128\n111#3,5:156\n196#4,7:68\n203#4:89\n196#4,7:101\n203#4:122\n196#4,7:134\n203#4:155\n115#5,14:75\n115#5,14:108\n115#5,14:141\n*S KotlinDebug\n*F\n+ 1 FoundationCommonModule.kt\ncom/reown/foundation/di/FoundationCommonModuleKt\n*L\n33#1:57,5\n19#1:62,6\n19#1:90,5\n23#1:95,6\n23#1:123,5\n37#1:128,6\n37#1:156,5\n19#1:68,7\n19#1:89\n23#1:101,7\n23#1:122\n37#1:134,7\n37#1:155\n19#1:75,14\n23#1:108,14\n37#1:141,14\n*E\n"})
public final class FoundationCommonModuleKt {
    @NotNull
    public static final Module foundationCommonModule() {
        return ModuleDSLKt.module$default(false, new a(0), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit foundationCommonModule$lambda$4(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        c cVar = new c(13);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(KotlinJsonAdapterFactory.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named = QualifierKt.named(FoundationDITags.MOSHI);
        c cVar2 = new c(14);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Moshi.class), named, cVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        c cVar3 = new c(15);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Logger.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final KotlinJsonAdapterFactory foundationCommonModule$lambda$4$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new KotlinJsonAdapterFactory();
    }

    /* access modifiers changed from: private */
    public static final Moshi foundationCommonModule$lambda$4$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Moshi build = new Moshi.Builder().add((JsonAdapter.Factory) new d(1)).addLast((JsonAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(KotlinJsonAdapterFactory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    /* access modifiers changed from: private */
    public static final JsonAdapter foundationCommonModule$lambda$4$lambda$2$lambda$1(Type type, Set set, Moshi moshi) {
        Intrinsics.checkNotNull(type);
        String name = TypeUtils.getRawType(type).getName();
        if (Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(SubscriptionId.class)))) {
            return SubscriptionIdAdapter.INSTANCE;
        }
        if (Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(Topic.class)))) {
            return TopicAdapter.INSTANCE;
        }
        if (Intrinsics.areEqual((Object) name, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(Ttl.class)))) {
            return TtlAdapter.INSTANCE;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final Logger foundationCommonModule$lambda$4$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FoundationCommonModuleKt$foundationCommonModule$1$3$1();
    }
}
