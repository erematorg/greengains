package com.reown.android.internal.common.di;

import A.a;
import S0.d;
import S0.f;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository;
import com.reown.android.keyserver.data.service.KeyServerService;
import com.reown.android.keyserver.domain.IdentitiesInteractor;
import com.reown.android.keyserver.domain.use_case.RegisterIdentityUseCase;
import com.reown.android.keyserver.domain.use_case.RegisterInviteUseCase;
import com.reown.android.keyserver.domain.use_case.ResolveIdentityUseCase;
import com.reown.android.keyserver.domain.use_case.ResolveInviteUseCase;
import com.reown.android.keyserver.domain.use_case.UnregisterIdentityUseCase;
import com.reown.android.keyserver.domain.use_case.UnregisterInviteUseCase;
import com.reown.foundation.util.Logger;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
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
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"keyServerModule", "Lorg/koin/core/module/Module;", "optionalKeyServerUrl", "", "DEFAULT_KEYSERVER_URL", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nKeyServerModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KeyServerModule.kt\ncom/reown/android/internal/common/di/KeyServerModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,49:1\n138#2,5:50\n138#2,5:55\n138#2,5:60\n138#2,5:65\n138#2,5:70\n138#2,5:75\n138#2,5:80\n138#2,5:85\n138#2,5:90\n138#2,5:95\n138#2,5:100\n138#2,5:105\n138#2,5:110\n138#2,5:115\n138#2,5:120\n138#2,5:125\n105#3,6:130\n111#3,5:158\n105#3,6:163\n111#3,5:191\n105#3,6:196\n111#3,5:224\n105#3,6:229\n111#3,5:257\n105#3,6:262\n111#3,5:290\n105#3,6:295\n111#3,5:323\n105#3,6:328\n111#3,5:356\n105#3,6:361\n111#3,5:389\n105#3,6:394\n111#3,5:422\n105#3,6:427\n111#3,5:455\n196#4,7:136\n203#4:157\n196#4,7:169\n203#4:190\n196#4,7:202\n203#4:223\n196#4,7:235\n203#4:256\n196#4,7:268\n203#4:289\n196#4,7:301\n203#4:322\n196#4,7:334\n203#4:355\n196#4,7:367\n203#4:388\n196#4,7:400\n203#4:421\n196#4,7:433\n203#4:454\n115#5,14:143\n115#5,14:176\n115#5,14:209\n115#5,14:242\n115#5,14:275\n115#5,14:308\n115#5,14:341\n115#5,14:374\n115#5,14:407\n115#5,14:440\n*S KotlinDebug\n*F\n+ 1 KeyServerModule.kt\ncom/reown/android/internal/common/di/KeyServerModuleKt\n*L\n22#1:50,5\n23#1:55,5\n27#1:60,5\n29#1:65,5\n30#1:70,5\n31#1:75,5\n32#1:80,5\n33#1:85,5\n34#1:90,5\n38#1:95,5\n39#1:100,5\n40#1:105,5\n41#1:110,5\n42#1:115,5\n43#1:120,5\n44#1:125,5\n17#1:130,6\n17#1:158,5\n19#1:163,6\n19#1:191,5\n27#1:196,6\n27#1:224,5\n29#1:229,6\n29#1:257,5\n30#1:262,6\n30#1:290,5\n31#1:295,6\n31#1:323,5\n32#1:328,6\n32#1:356,5\n33#1:361,6\n33#1:389,5\n34#1:394,6\n34#1:422,5\n36#1:427,6\n36#1:455,5\n17#1:136,7\n17#1:157\n19#1:169,7\n19#1:190\n27#1:202,7\n27#1:223\n29#1:235,7\n29#1:256\n30#1:268,7\n30#1:289\n31#1:301,7\n31#1:322\n32#1:334,7\n32#1:355\n33#1:367,7\n33#1:388\n34#1:400,7\n34#1:421\n36#1:433,7\n36#1:454\n17#1:143,14\n19#1:176,14\n27#1:209,14\n29#1:242,14\n30#1:275,14\n31#1:308,14\n32#1:341,14\n33#1:374,14\n34#1:407,14\n36#1:440,14\n*E\n"})
public final /* synthetic */ class KeyServerModuleKt {
    @NotNull
    private static final String DEFAULT_KEYSERVER_URL = "https://keys.walletconnect.org";

    public static /* synthetic */ Module keyServerModule$default(String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = null;
        }
        return ModuleDSLKt.module$default(false, new f(str, 3), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit keyServerModule$lambda$10(String str, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        if (str == null) {
            str = DEFAULT_KEYSERVER_URL;
        }
        Qualifier named = QualifierKt.named(AndroidCommonDITags.KEYSERVER_URL);
        d dVar = new d(str, 6);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(String.class), named, dVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.KEYSERVER_RETROFIT);
        d dVar2 = new d(str, 7);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Retrofit.class), named2, dVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        i iVar = new i(2);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, iVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        i iVar2 = new i(3);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RegisterIdentityUseCase.class), (Qualifier) null, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        i iVar3 = new i(4);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(UnregisterIdentityUseCase.class), (Qualifier) null, iVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        i iVar4 = new i(5);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ResolveIdentityUseCase.class), (Qualifier) null, iVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        i iVar5 = new i(6);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RegisterInviteUseCase.class), (Qualifier) null, iVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        i iVar6 = new i(7);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(UnregisterInviteUseCase.class), (Qualifier) null, iVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        i iVar7 = new i(8);
        SingleInstanceFactory u11 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ResolveInviteUseCase.class), (Qualifier) null, iVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        i iVar8 = new i(9);
        SingleInstanceFactory u12 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(IdentitiesInteractor.class), (Qualifier) null, iVar8, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module, u12);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String keyServerModule$lambda$10$lambda$0(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return str;
    }

    /* access modifiers changed from: private */
    public static final Retrofit keyServerModule$lambda$10$lambda$1(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl(str).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null))).build();
    }

    /* access modifiers changed from: private */
    public static final KeyServerService keyServerModule$lambda$10$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (KeyServerService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.KEYSERVER_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(KeyServerService.class);
    }

    /* access modifiers changed from: private */
    public static final RegisterIdentityUseCase keyServerModule$lambda$10$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new RegisterIdentityUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final UnregisterIdentityUseCase keyServerModule$lambda$10$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new UnregisterIdentityUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ResolveIdentityUseCase keyServerModule$lambda$10$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ResolveIdentityUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final RegisterInviteUseCase keyServerModule$lambda$10$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new RegisterInviteUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final UnregisterInviteUseCase keyServerModule$lambda$10$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new UnregisterInviteUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ResolveInviteUseCase keyServerModule$lambda$10$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ResolveInviteUseCase((KeyServerService) scope.get(Reflection.getOrCreateKotlinClass(KeyServerService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final IdentitiesInteractor keyServerModule$lambda$10$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new IdentitiesInteractor((IdentitiesStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(IdentitiesStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ResolveIdentityUseCase) scope.get(Reflection.getOrCreateKotlinClass(ResolveIdentityUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (RegisterIdentityUseCase) scope.get(Reflection.getOrCreateKotlinClass(RegisterIdentityUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (UnregisterIdentityUseCase) scope.get(Reflection.getOrCreateKotlinClass(UnregisterIdentityUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProjectId) scope.get(Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }
}
