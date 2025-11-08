package com.reown.android.internal.common.di;

import com.google.firebase.crashlytics.internal.a;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.sdk.storage.data.dao.VerifyPublicKeyQueries;
import com.reown.android.verify.client.VerifyInterface;
import com.reown.android.verify.data.VerifyService;
import com.reown.android.verify.domain.JWTRepository;
import com.reown.android.verify.domain.ResolveAttestationIdUseCase;
import com.reown.android.verify.domain.VerifyPublicKeyStorageRepository;
import com.reown.android.verify.domain.VerifyRepository;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"verifyModule", "Lorg/koin/core/module/Module;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVerifyModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VerifyModule.kt\ncom/reown/android/internal/common/di/VerifyModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,41:1\n138#2,5:42\n138#2,5:47\n138#2,5:52\n138#2,5:57\n138#2,5:62\n138#2,5:67\n138#2,5:72\n138#2,5:77\n138#2,5:82\n138#2,5:87\n153#3,10:92\n163#3,2:118\n105#3,6:120\n111#3,5:148\n105#3,6:153\n111#3,5:181\n105#3,6:186\n111#3,5:214\n105#3,6:219\n111#3,5:247\n105#3,6:252\n111#3,5:280\n105#3,6:285\n111#3,5:313\n212#4:102\n213#4:117\n196#4,7:126\n203#4:147\n196#4,7:159\n203#4:180\n196#4,7:192\n203#4:213\n196#4,7:225\n203#4:246\n196#4,7:258\n203#4:279\n196#4,7:291\n203#4:312\n115#5,14:103\n115#5,14:133\n115#5,14:166\n115#5,14:199\n115#5,14:232\n115#5,14:265\n115#5,14:298\n*S KotlinDebug\n*F\n+ 1 VerifyModule.kt\ncom/reown/android/internal/common/di/VerifyModuleKt\n*L\n19#1:42,5\n20#1:47,5\n21#1:52,5\n25#1:57,5\n27#1:62,5\n29#1:67,5\n35#1:72,5\n36#1:77,5\n37#1:82,5\n38#1:87,5\n15#1:92,10\n15#1:118,2\n17#1:120,6\n17#1:148,5\n25#1:153,6\n25#1:181,5\n27#1:186,6\n27#1:214,5\n29#1:219,6\n29#1:247,5\n31#1:252,6\n31#1:280,5\n33#1:285,6\n33#1:313,5\n15#1:102\n15#1:117\n17#1:126,7\n17#1:147\n25#1:159,7\n25#1:180\n27#1:192,7\n27#1:213\n29#1:225,7\n29#1:246\n31#1:258,7\n31#1:279\n33#1:291,7\n33#1:312\n15#1:103,14\n17#1:133,14\n25#1:166,14\n27#1:199,14\n29#1:232,14\n31#1:265,14\n33#1:298,14\n*E\n"})
public final class VerifyModuleKt {
    /* access modifiers changed from: private */
    public static final Unit verifyModule$lambda$7(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.VERIFY_URL);
        i iVar = new i(20);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        new KoinDefinition(module, a.h(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(String.class), named, iVar, Kind.Factory, CollectionsKt.emptyList()), module));
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.VERIFY_RETROFIT);
        i iVar2 = new i(21);
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(Retrofit.class), named2, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        i iVar3 = new i(22);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyService.class), (Qualifier) null, iVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        i iVar4 = new i(23);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ResolveAttestationIdUseCase.class), (Qualifier) null, iVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        i iVar5 = new i(24);
        SingleInstanceFactory u6 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyPublicKeyStorageRepository.class), (Qualifier) null, iVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        i iVar6 = new i(25);
        SingleInstanceFactory u7 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(JWTRepository.class), (Qualifier) null, iVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        i iVar7 = new i(26);
        SingleInstanceFactory u8 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyRepository.class), (Qualifier) null, iVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String verifyModule$lambda$7$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$factory");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return "https://verify.walletconnect.org/";
    }

    /* access modifiers changed from: private */
    public static final Retrofit verifyModule$lambda$7$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.VERIFY_URL), (Function0<? extends ParametersHolder>) null)).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null))).build();
    }

    /* access modifiers changed from: private */
    public static final VerifyService verifyModule$lambda$7$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (VerifyService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.VERIFY_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(VerifyService.class);
    }

    /* access modifiers changed from: private */
    public static final ResolveAttestationIdUseCase verifyModule$lambda$7$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ResolveAttestationIdUseCase((VerifyInterface) scope.get(Reflection.getOrCreateKotlinClass(VerifyInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.VERIFY_URL), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final VerifyPublicKeyStorageRepository verifyModule$lambda$7$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new VerifyPublicKeyStorageRepository((VerifyPublicKeyQueries) scope.get(Reflection.getOrCreateKotlinClass(VerifyPublicKeyQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final JWTRepository verifyModule$lambda$7$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new JWTRepository();
    }

    /* access modifiers changed from: private */
    public static final VerifyRepository verifyModule$lambda$7$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new VerifyRepository((VerifyService) scope.get(Reflection.getOrCreateKotlinClass(VerifyService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JWTRepository) scope.get(Reflection.getOrCreateKotlinClass(JWTRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null), (VerifyPublicKeyStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyPublicKeyStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (CoroutineScope) null, 16, (DefaultConstructorMarker) null);
    }
}
