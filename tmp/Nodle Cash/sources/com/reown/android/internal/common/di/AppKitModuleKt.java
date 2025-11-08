package com.reown.android.internal.common.di;

import R1.a;
import android.content.Context;
import com.reown.android.internal.common.modal.AppKitApiRepository;
import com.reown.android.internal.common.modal.data.network.AppKitService;
import com.reown.android.internal.common.modal.domain.usecase.EnableAnalyticsUseCase;
import com.reown.android.internal.common.modal.domain.usecase.EnableAnalyticsUseCaseInterface;
import com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase;
import com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCaseInterface;
import com.reown.android.internal.common.modal.domain.usecase.GetSampleWalletsUseCase;
import com.reown.android.internal.common.modal.domain.usecase.GetSampleWalletsUseCaseInterface;
import com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase;
import com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCaseInterface;
import com.reown.android.internal.common.model.ProjectId;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"appKitModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAppKitModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppKitModule.kt\ncom/reown/android/internal/common/di/AppKitModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,67:1\n138#2,5:68\n138#2,5:73\n138#2,5:78\n138#2,5:83\n138#2,5:88\n138#2,5:93\n138#2,5:98\n138#2,5:103\n138#2,5:108\n138#2,5:113\n138#2,5:118\n138#2,5:123\n138#2,5:128\n105#3,6:133\n111#3,5:161\n105#3,6:166\n111#3,5:194\n105#3,6:199\n111#3,5:227\n105#3,6:232\n111#3,5:260\n105#3,6:265\n111#3,5:293\n105#3,6:298\n111#3,5:326\n105#3,6:331\n111#3,5:359\n105#3,6:364\n111#3,5:392\n105#3,6:397\n111#3,5:425\n105#3,6:430\n111#3,5:458\n196#4,7:139\n203#4:160\n196#4,7:172\n203#4:193\n196#4,7:205\n203#4:226\n196#4,7:238\n203#4:259\n196#4,7:271\n203#4:292\n196#4,7:304\n203#4:325\n196#4,7:337\n203#4:358\n196#4,7:370\n203#4:391\n196#4,7:403\n203#4:424\n196#4,7:436\n203#4:457\n115#5,14:146\n115#5,14:179\n115#5,14:212\n115#5,14:245\n115#5,14:278\n115#5,14:311\n115#5,14:344\n115#5,14:377\n115#5,14:410\n115#5,14:443\n*S KotlinDebug\n*F\n+ 1 AppKitModule.kt\ncom/reown/android/internal/common/di/AppKitModuleKt\n*L\n30#1:68,5\n38#1:73,5\n40#1:78,5\n46#1:83,5\n47#1:88,5\n48#1:93,5\n52#1:98,5\n56#1:103,5\n57#1:108,5\n62#1:113,5\n63#1:118,5\n64#1:123,5\n65#1:128,5\n25#1:133,6\n25#1:161,5\n27#1:166,6\n27#1:194,5\n37#1:199,6\n37#1:227,5\n44#1:232,6\n44#1:260,5\n52#1:265,6\n52#1:293,5\n54#1:298,6\n54#1:326,5\n62#1:331,6\n62#1:359,5\n63#1:364,6\n63#1:392,5\n64#1:397,6\n64#1:425,5\n65#1:430,6\n65#1:458,5\n25#1:139,7\n25#1:160\n27#1:172,7\n27#1:193\n37#1:205,7\n37#1:226\n44#1:238,7\n44#1:259\n52#1:271,7\n52#1:292\n54#1:304,7\n54#1:325\n62#1:337,7\n62#1:358\n63#1:370,7\n63#1:391\n64#1:403,7\n64#1:424\n65#1:436,7\n65#1:457\n25#1:146,14\n27#1:179,14\n37#1:212,14\n44#1:245,14\n52#1:278,14\n54#1:311,14\n62#1:344,14\n63#1:377,14\n64#1:410,14\n65#1:443,14\n*E\n"})
public final class AppKitModuleKt {
    /* access modifiers changed from: private */
    public static final Unit appKitModule$lambda$11(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.WEB3MODAL_URL);
        a aVar = new a(7);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(String.class), named, aVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.APPKIT_INTERCEPTOR);
        a aVar2 = new a(8);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Interceptor.class), named2, aVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        Qualifier named3 = QualifierKt.named(AndroidCommonDITags.APPKIT_OKHTTP);
        a aVar3 = new a(9);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OkHttpClient.class), named3, aVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        Qualifier named4 = QualifierKt.named(AndroidCommonDITags.APPKIT_RETROFIT);
        a aVar4 = new a(10);
        SingleInstanceFactory u6 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Retrofit.class), named4, aVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        a aVar5 = new a(11);
        SingleInstanceFactory u7 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AppKitService.class), (Qualifier) null, aVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        a aVar6 = new a(12);
        SingleInstanceFactory u8 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AppKitApiRepository.class), (Qualifier) null, aVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        a aVar7 = new a(13);
        SingleInstanceFactory u9 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetInstalledWalletsIdsUseCaseInterface.class), (Qualifier) null, aVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        a aVar8 = new a(14);
        SingleInstanceFactory u10 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetWalletsUseCaseInterface.class), (Qualifier) null, aVar8, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        a aVar9 = new a(5);
        SingleInstanceFactory u11 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetSampleWalletsUseCaseInterface.class), (Qualifier) null, aVar9, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        a aVar10 = new a(6);
        SingleInstanceFactory u12 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(EnableAnalyticsUseCaseInterface.class), (Qualifier) null, aVar10, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module, u12);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String appKitModule$lambda$11$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return "https://api.web3modal.com/";
    }

    /* access modifiers changed from: private */
    public static final EnableAnalyticsUseCaseInterface appKitModule$lambda$11$lambda$10(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EnableAnalyticsUseCase((AppKitApiRepository) scope.get(Reflection.getOrCreateKotlinClass(AppKitApiRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final Interceptor appKitModule$lambda$11$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new a(scope, 0);
    }

    /* access modifiers changed from: private */
    public static final Response appKitModule$lambda$11$lambda$2$lambda$1(Scope scope, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().addHeader("x-project-id", ((ProjectId) scope.get(Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getValue()).addHeader("x-sdk-version", "kotlin-1.4.11").build());
    }

    /* access modifiers changed from: private */
    public static final OkHttpClient appKitModule$lambda$11$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null)).newBuilder().addInterceptor((Interceptor) scope.get(Reflection.getOrCreateKotlinClass(Interceptor.class), QualifierKt.named(AndroidCommonDITags.APPKIT_INTERCEPTOR), (Function0<? extends ParametersHolder>) null)).build();
    }

    /* access modifiers changed from: private */
    public static final Retrofit appKitModule$lambda$11$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.WEB3MODAL_URL), (Function0<? extends ParametersHolder>) null)).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.APPKIT_OKHTTP), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null))).build();
    }

    /* access modifiers changed from: private */
    public static final AppKitService appKitModule$lambda$11$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (AppKitService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.APPKIT_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(AppKitService.class);
    }

    /* access modifiers changed from: private */
    public static final AppKitApiRepository appKitModule$lambda$11$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.WEB3MODAL_URL);
        return new AppKitApiRepository(ModuleExtKt.androidContext(scope), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), named, (Function0<? extends ParametersHolder>) null), (AppKitService) scope.get(Reflection.getOrCreateKotlinClass(AppKitService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetInstalledWalletsIdsUseCaseInterface appKitModule$lambda$11$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetInstalledWalletsIdsUseCase((AppKitApiRepository) scope.get(Reflection.getOrCreateKotlinClass(AppKitApiRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetWalletsUseCaseInterface appKitModule$lambda$11$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetWalletsUseCase((AppKitApiRepository) scope.get(Reflection.getOrCreateKotlinClass(AppKitApiRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetSampleWalletsUseCaseInterface appKitModule$lambda$11$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetSampleWalletsUseCase((Context) scope.get(Reflection.getOrCreateKotlinClass(Context.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }
}
