package com.reown.android.internal.common.di;

import A.a;
import com.reown.android.internal.common.explorer.ExplorerRepository;
import com.reown.android.internal.common.explorer.data.network.ExplorerService;
import com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase;
import com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase;
import com.reown.android.internal.common.model.ProjectId;
import com.squareup.moshi.Moshi;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"explorerModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExplorerModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExplorerModule.kt\ncom/reown/android/internal/common/di/ExplorerModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,38:1\n138#2,5:39\n138#2,5:44\n138#2,5:49\n138#2,5:54\n138#2,5:59\n138#2,5:64\n138#2,5:69\n138#2,5:74\n105#3,6:79\n111#3,5:107\n105#3,6:112\n111#3,5:140\n105#3,6:145\n111#3,5:173\n105#3,6:178\n111#3,5:206\n105#3,6:211\n111#3,5:239\n105#3,6:244\n111#3,5:272\n196#4,7:85\n203#4:106\n196#4,7:118\n203#4:139\n196#4,7:151\n203#4:172\n196#4,7:184\n203#4:205\n196#4,7:217\n203#4:238\n196#4,7:250\n203#4:271\n115#5,14:92\n115#5,14:125\n115#5,14:158\n115#5,14:191\n115#5,14:224\n115#5,14:257\n*S KotlinDebug\n*F\n+ 1 ExplorerModule.kt\ncom/reown/android/internal/common/di/ExplorerModuleKt\n*L\n21#1:39,5\n22#1:44,5\n23#1:49,5\n27#1:54,5\n31#1:59,5\n32#1:64,5\n36#1:69,5\n37#1:74,5\n17#1:79,6\n17#1:107,5\n19#1:112,6\n19#1:140,5\n27#1:145,6\n27#1:173,5\n29#1:178,6\n29#1:206,5\n36#1:211,6\n36#1:239,5\n37#1:244,6\n37#1:272,5\n17#1:85,7\n17#1:106\n19#1:118,7\n19#1:139\n27#1:151,7\n27#1:172\n29#1:184,7\n29#1:205\n36#1:217,7\n36#1:238\n37#1:250,7\n37#1:271\n17#1:92,14\n19#1:125,14\n27#1:158,14\n29#1:191,14\n36#1:224,14\n37#1:257,14\n*E\n"})
public final /* synthetic */ class ExplorerModuleKt {
    /* access modifiers changed from: private */
    public static final Unit explorerModule$lambda$6(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.EXPLORER_URL);
        c cVar = new c(26);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(String.class), named, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.EXPLORER_RETROFIT);
        c cVar2 = new c(27);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Retrofit.class), named2, cVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        c cVar3 = new c(28);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ExplorerService.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        c cVar4 = new c(29);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ExplorerRepository.class), (Qualifier) null, cVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        i iVar = new i(0);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetProjectsWithPaginationUseCase.class), (Qualifier) null, iVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        i iVar2 = new i(1);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetNotifyConfigUseCase.class), (Qualifier) null, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String explorerModule$lambda$6$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return "https://registry.walletconnect.org/";
    }

    /* access modifiers changed from: private */
    public static final Retrofit explorerModule$lambda$6$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.EXPLORER_URL), (Function0<? extends ParametersHolder>) null)).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null))).build();
    }

    /* access modifiers changed from: private */
    public static final ExplorerService explorerModule$lambda$6$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (ExplorerService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.EXPLORER_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(ExplorerService.class);
    }

    /* access modifiers changed from: private */
    public static final ExplorerRepository explorerModule$lambda$6$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ExplorerRepository((ExplorerService) scope.get(Reflection.getOrCreateKotlinClass(ExplorerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProjectId) scope.get(Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetProjectsWithPaginationUseCase explorerModule$lambda$6$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetProjectsWithPaginationUseCase((ExplorerRepository) scope.get(Reflection.getOrCreateKotlinClass(ExplorerRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetNotifyConfigUseCase explorerModule$lambda$6$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetNotifyConfigUseCase((ExplorerRepository) scope.get(Reflection.getOrCreateKotlinClass(ExplorerRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }
}
