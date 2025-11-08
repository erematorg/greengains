package com.reown.foundation.di;

import A.a;
import S0.d;
import androidx.compose.material.C0140g;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.foundation.network.BaseRelayClient;
import com.reown.foundation.network.data.adapter.FlowStreamAdapter;
import com.reown.foundation.network.data.service.RelayService;
import com.reown.sign.di.c;
import com.squareup.moshi.Moshi;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.Scarlet;
import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter;
import com.tinder.scarlet.retry.BackoffStrategy;
import com.tinder.scarlet.retry.LinearBackoffStrategy;
import com.tinder.scarlet.websocket.okhttp.OkHttpClientUtils;
import io.nodle.cash.ui.feature.chat.messages.n;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003¨\u0006\u0007"}, d2 = {"networkModule", "Lorg/koin/core/module/Module;", "serverUrl", "", "sdkVersion", "jwt", "packageName", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFoundationNetworkModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoundationNetworkModule.kt\ncom/reown/foundation/di/FoundationNetworkModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,62:1\n138#2,5:63\n138#2,5:68\n138#2,5:73\n138#2,5:78\n138#2,5:83\n138#2,5:88\n138#2,5:93\n105#3,6:98\n111#3,5:126\n105#3,6:131\n111#3,5:159\n105#3,6:164\n111#3,5:192\n105#3,6:197\n111#3,5:225\n105#3,6:230\n111#3,5:258\n105#3,6:263\n111#3,5:291\n105#3,6:296\n111#3,5:324\n105#3,6:329\n111#3,5:357\n196#4,7:104\n203#4:125\n196#4,7:137\n203#4:158\n196#4,7:170\n203#4:191\n196#4,7:203\n203#4:224\n196#4,7:236\n203#4:257\n196#4,7:269\n203#4:290\n196#4,7:302\n203#4:323\n196#4,7:335\n203#4:356\n115#5,14:111\n115#5,14:144\n115#5,14:177\n115#5,14:210\n115#5,14:243\n115#5,14:276\n115#5,14:309\n115#5,14:342\n*S KotlinDebug\n*F\n+ 1 FoundationNetworkModule.kt\ncom/reown/foundation/di/FoundationNetworkModuleKt\n*L\n35#1:63,5\n44#1:68,5\n52#1:73,5\n53#1:78,5\n54#1:83,5\n55#1:88,5\n59#1:93,5\n23#1:98,6\n23#1:126,5\n33#1:131,6\n33#1:159,5\n44#1:164,6\n44#1:192,5\n46#1:197,6\n46#1:225,5\n48#1:230,6\n48#1:258,5\n50#1:263,6\n50#1:291,5\n59#1:296,6\n59#1:324,5\n61#1:329,6\n61#1:357,5\n23#1:104,7\n23#1:125\n33#1:137,7\n33#1:158\n44#1:170,7\n44#1:191\n46#1:203,7\n46#1:224\n48#1:236,7\n48#1:257\n50#1:269,7\n50#1:290\n59#1:302,7\n59#1:323\n61#1:335,7\n61#1:356\n23#1:111,14\n33#1:144,14\n44#1:177,14\n46#1:210,14\n48#1:243,14\n50#1:276,14\n59#1:309,14\n61#1:342,14\n*E\n"})
public final class FoundationNetworkModuleKt {
    @NotNull
    public static final Module networkModule(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkNotNullParameter(str, "serverUrl");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        Intrinsics.checkNotNullParameter(str3, "jwt");
        Intrinsics.checkNotNullParameter(str4, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        return ModuleDSLKt.module$default(false, new C0140g(14, (Object) str2, (Object) str, (Object) str3, (Object) str4), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit networkModule$lambda$9(String str, String str2, String str3, String str4, Module module) {
        Module module2 = module;
        Intrinsics.checkNotNullParameter(module2, "$this$module");
        Qualifier named = QualifierKt.named(FoundationDITags.INTERCEPTOR);
        String str5 = str;
        d dVar = new d(str, 10);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(Interceptor.class), named, dVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module2, u3);
        Qualifier named2 = QualifierKt.named(FoundationDITags.OK_HTTP);
        c cVar = new c(17);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OkHttpClient.class), named2, cVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module2, u4);
        Qualifier named3 = QualifierKt.named(FoundationDITags.MSG_ADAPTER);
        c cVar2 = new c(18);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(MoshiMessageAdapter.Factory.class), named3, cVar2, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module2, u5);
        c cVar3 = new c(19);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(FlowStreamAdapter.Factory.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module2, u6);
        c cVar4 = new c(20);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(LinearBackoffStrategy.class), (Qualifier) null, cVar4, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module2, u7);
        Qualifier named4 = QualifierKt.named(FoundationDITags.SCARLET);
        n nVar = new n(str2, str3, 1, str4);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Scarlet.class), named4, nVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module2, u8);
        Qualifier named5 = QualifierKt.named(FoundationDITags.RELAY_SERVICE);
        c cVar5 = new c(21);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RelayService.class), named5, cVar5, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module2, u9);
        c cVar6 = new c(22);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(BaseRelayClient.class), (Qualifier) null, cVar6, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module2, u10);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Interceptor networkModule$lambda$9$lambda$1(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new com.reown.android.internal.common.di.a(str, 2);
    }

    /* access modifiers changed from: private */
    public static final Response networkModule$lambda$9$lambda$1$lambda$0(String str, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "it");
        Request.Builder newBuilder = chain.request().newBuilder();
        return chain.proceed(newBuilder.addHeader(HttpHeaders.USER_AGENT, "wc-2/kotlin-" + str).build());
    }

    /* access modifiers changed from: private */
    public static final OkHttpClient networkModule$lambda$9$lambda$2(long j2, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor((Interceptor) scope.get(Reflection.getOrCreateKotlinClass(Interceptor.class), QualifierKt.named(FoundationDITags.INTERCEPTOR), (Function0<? extends ParametersHolder>) null)).addInterceptor(new HttpLoggingInterceptor((HttpLoggingInterceptor.Logger) null, 1, (DefaultConstructorMarker) null).setLevel(HttpLoggingInterceptor.Level.BODY));
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return addInterceptor.writeTimeout(j2, timeUnit).readTimeout(j2, timeUnit).callTimeout(j2, timeUnit).connectTimeout(j2, timeUnit).build();
    }

    /* access modifiers changed from: private */
    public static final MoshiMessageAdapter.Factory networkModule$lambda$9$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new MoshiMessageAdapter.Factory((Moshi) scope.get(Reflection.getOrCreateKotlinClass(Moshi.class), QualifierKt.named(FoundationDITags.MOSHI), (Function0<? extends ParametersHolder>) null), (MoshiMessageAdapter.Factory.Config) null, 2, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final FlowStreamAdapter.Factory networkModule$lambda$9$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FlowStreamAdapter.Factory();
    }

    /* access modifiers changed from: private */
    public static final LinearBackoffStrategy networkModule$lambda$9$lambda$5(long j2, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new LinearBackoffStrategy(TimeUnit.SECONDS.toMillis(j2));
    }

    /* access modifiers changed from: private */
    public static final Scarlet networkModule$lambda$9$lambda$6(String str, String str2, String str3, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Scarlet.Builder backoffStrategy = new Scarlet.Builder().backoffStrategy((BackoffStrategy) scope.get(Reflection.getOrCreateKotlinClass(LinearBackoffStrategy.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
        Qualifier named = QualifierKt.named(FoundationDITags.OK_HTTP);
        return backoffStrategy.webSocketFactory(OkHttpClientUtils.newWebSocketFactory((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), named, (Function0<? extends ParametersHolder>) null), str + "&auth=" + str2 + "&packageName=" + str3)).addMessageAdapterFactory((MessageAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(MoshiMessageAdapter.Factory.class), QualifierKt.named(FoundationDITags.MSG_ADAPTER), (Function0<? extends ParametersHolder>) null)).addStreamAdapterFactory((StreamAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(FlowStreamAdapter.Factory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).build();
    }

    /* access modifiers changed from: private */
    public static final RelayService networkModule$lambda$9$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (RelayService) ((Scarlet) scope.get(Reflection.getOrCreateKotlinClass(Scarlet.class), QualifierKt.named(FoundationDITags.SCARLET), (Function0<? extends ParametersHolder>) null)).create(RelayService.class);
    }

    /* access modifiers changed from: private */
    public static final BaseRelayClient networkModule$lambda$9$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FoundationNetworkModuleKt$networkModule$1$8$1();
    }
}
