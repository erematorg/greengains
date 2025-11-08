package com.reown.android.internal.common.di;

import A.a;
import S0.d;
import com.reown.android.internal.common.model.TelemetryEnabled;
import com.reown.android.internal.common.storage.events.EventsRepository;
import com.reown.android.pulse.data.PulseService;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.pulse.domain.SendBatchEventUseCase;
import com.reown.android.pulse.domain.SendEventInterface;
import com.reown.android.pulse.domain.SendEventUseCase;
import com.reown.foundation.util.Logger;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
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

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"pulseModule", "Lorg/koin/core/module/Module;", "bundleId", "", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPulseModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PulseModule.kt\ncom/reown/android/internal/common/di/PulseModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,62:1\n138#2,5:63\n138#2,5:68\n138#2,5:73\n138#2,5:78\n138#2,5:83\n138#2,5:88\n138#2,5:93\n138#2,5:98\n138#2,5:103\n138#2,5:108\n138#2,5:113\n138#2,5:118\n138#2,5:123\n138#2,5:128\n105#3,6:133\n111#3,5:161\n105#3,6:166\n111#3,5:194\n105#3,6:199\n111#3,5:227\n105#3,6:232\n111#3,5:260\n105#3,6:265\n111#3,5:293\n105#3,6:298\n111#3,5:326\n105#3,6:331\n111#3,5:359\n196#4,7:139\n203#4:160\n196#4,7:172\n203#4:193\n196#4,7:205\n203#4:226\n196#4,7:238\n203#4:259\n196#4,7:271\n203#4:292\n196#4,7:304\n203#4:325\n196#4,7:337\n203#4:358\n115#5,14:146\n115#5,14:179\n115#5,14:212\n115#5,14:245\n115#5,14:278\n115#5,14:311\n115#5,14:344\n*S KotlinDebug\n*F\n+ 1 PulseModule.kt\ncom/reown/android/internal/common/di/PulseModuleKt\n*L\n22#1:63,5\n23#1:68,5\n24#1:73,5\n29#1:78,5\n34#1:83,5\n35#1:88,5\n42#1:93,5\n43#1:98,5\n44#1:103,5\n45#1:108,5\n51#1:113,5\n52#1:118,5\n58#1:123,5\n59#1:128,5\n18#1:133,6\n18#1:161,5\n20#1:166,6\n20#1:194,5\n28#1:199,6\n28#1:227,5\n32#1:232,6\n32#1:260,5\n40#1:265,6\n40#1:293,5\n49#1:298,6\n49#1:326,5\n56#1:331,6\n56#1:359,5\n18#1:139,7\n18#1:160\n20#1:172,7\n20#1:193\n28#1:205,7\n28#1:226\n32#1:238,7\n32#1:259\n40#1:271,7\n40#1:292\n49#1:304,7\n49#1:325\n56#1:337,7\n56#1:358\n18#1:146,14\n20#1:179,14\n28#1:212,14\n32#1:245,14\n40#1:278,14\n49#1:311,14\n56#1:344,14\n*E\n"})
public final class PulseModuleKt {
    /* access modifiers changed from: private */
    public static final Unit pulseModule$lambda$7(String str, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.PULSE_URL);
        i iVar = new i(10);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(String.class), named, iVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.PULSE_RETROFIT);
        i iVar2 = new i(11);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Retrofit.class), named2, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        i iVar3 = new i(12);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PulseService.class), (Qualifier) null, iVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        d dVar = new d(str, 8);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SendEventInterface.class), (Qualifier) null, dVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        i iVar4 = new i(13);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SendBatchEventUseCase.class), (Qualifier) null, iVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        i iVar5 = new i(14);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, iVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        i iVar6 = new i(15);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, iVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String pulseModule$lambda$7$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return "https://pulse.walletconnect.org";
    }

    /* access modifiers changed from: private */
    public static final Retrofit pulseModule$lambda$7$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Retrofit.Builder().baseUrl((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.PULSE_URL), (Function0<? extends ParametersHolder>) null)).client((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.APPKIT_OKHTTP), (Function0<? extends ParametersHolder>) null)).addConverterFactory(MoshiConverterFactory.create(((Moshi.Builder) scope.get(Reflection.getOrCreateKotlinClass(Moshi.Builder.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null)).build())).build();
    }

    /* access modifiers changed from: private */
    public static final PulseService pulseModule$lambda$7$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (PulseService) ((Retrofit) scope.get(Reflection.getOrCreateKotlinClass(Retrofit.class), QualifierKt.named(AndroidCommonDITags.PULSE_RETROFIT), (Function0<? extends ParametersHolder>) null)).create(PulseService.class);
    }

    /* access modifiers changed from: private */
    public static final SendEventInterface pulseModule$lambda$7$lambda$3(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new SendEventUseCase((PulseService) scope.get(Reflection.getOrCreateKotlinClass(PulseService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null), str);
    }

    /* access modifiers changed from: private */
    public static final SendBatchEventUseCase pulseModule$lambda$7$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        boolean r4 = ((TelemetryEnabled) scope.get(Reflection.getOrCreateKotlinClass(TelemetryEnabled.class), QualifierKt.named(AndroidCommonDITags.TELEMETRY_ENABLED), (Function0<? extends ParametersHolder>) null)).m8791unboximpl();
        return new SendBatchEventUseCase((PulseService) scope.get(a.t(scope, "$this$single", parametersHolder, "it", PulseService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (EventsRepository) scope.get(Reflection.getOrCreateKotlinClass(EventsRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), r4, (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null), (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final InsertTelemetryEventUseCase pulseModule$lambda$7$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        return new InsertTelemetryEventUseCase((EventsRepository) scope.get(Reflection.getOrCreateKotlinClass(EventsRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final InsertEventUseCase pulseModule$lambda$7$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        return new InsertEventUseCase((EventsRepository) scope.get(Reflection.getOrCreateKotlinClass(EventsRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null));
    }
}
