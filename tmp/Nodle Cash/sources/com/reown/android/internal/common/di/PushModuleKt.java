package com.reown.android.internal.common.di;

import com.google.firebase.crashlytics.internal.a;
import com.reown.android.push.network.PushService;
import java.util.LinkedHashMap;
import java.util.Map;
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
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"pushModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushModule.kt\ncom/reown/android/internal/common/di/PushModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,28:1\n138#2,5:29\n138#2,5:34\n138#2,5:39\n153#3,10:44\n163#3,2:70\n105#3,6:72\n111#3,5:100\n105#3,6:105\n111#3,5:133\n105#3,6:138\n111#3,5:166\n212#4:54\n213#4:69\n196#4,7:78\n203#4:99\n196#4,7:111\n203#4:132\n196#4,7:144\n203#4:165\n115#5,14:55\n115#5,14:85\n115#5,14:118\n115#5,14:151\n*S KotlinDebug\n*F\n+ 1 PushModule.kt\ncom/reown/android/internal/common/di/PushModuleKt\n*L\n17#1:29,5\n19#1:34,5\n24#1:39,5\n13#1:44,10\n13#1:70,2\n15#1:72,6\n15#1:100,5\n23#1:105,6\n23#1:133,5\n27#1:138,6\n27#1:166,5\n13#1:54\n13#1:69\n15#1:78,7\n15#1:99\n23#1:111,7\n23#1:132\n27#1:144,7\n27#1:165\n13#1:55,14\n15#1:85,14\n23#1:118,14\n27#1:151,14\n*E\n"})
public final class PushModuleKt {
    /* access modifiers changed from: private */
    public static final Unit pushModule$lambda$4(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.PUSH_URL);
        i iVar = new i(16);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        new KoinDefinition(module, a.h(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(String.class), named, iVar, Kind.Factory, CollectionsKt.emptyList()), module));
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.PUSH_RETROFIT);
        i iVar2 = new i(17);
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(Retrofit.class), named2, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        i iVar3 = new i(18);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PushService.class), (Qualifier) null, iVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        Qualifier named3 = QualifierKt.named(AndroidCommonDITags.DECRYPT_USE_CASES);
        i iVar4 = new i(19);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Map.class), named3, iVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String pushModule$lambda$4$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$factory");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return "https://echo.walletconnect.org/";
    }

    /* access modifiers changed from: private */
    public static final Retrofit pushModule$lambda$4$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.PUSH_URL), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create()).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null)).build();
    }

    /* access modifiers changed from: private */
    public static final PushService pushModule$lambda$4$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (PushService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.PUSH_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(PushService.class);
    }

    /* access modifiers changed from: private */
    public static final Map pushModule$lambda$4$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new LinkedHashMap();
    }
}
