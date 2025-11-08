package com.reown.android.internal.common.di;

import S0.d;
import S0.e;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import androidx.camera.camera2.internal.C0118y;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.android.internal.common.ConditionalExponentialBackoffStrategy;
import com.reown.android.internal.common.connection.ConnectivityState;
import com.reown.android.internal.common.connection.DefaultConnectionLifecycle;
import com.reown.android.internal.common.connection.ManualConnectionLifecycle;
import com.reown.android.internal.common.jwt.clientid.GenerateJwtStoreClientIdUseCase;
import com.reown.android.relay.ConnectionType;
import com.reown.android.relay.NetworkClientTimeout;
import com.reown.foundation.crypto.data.repository.ClientIdJwtRepository;
import com.reown.foundation.network.data.adapter.FlowStreamAdapter;
import com.reown.foundation.network.data.service.RelayService;
import com.squareup.moshi.Moshi;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.Scarlet;
import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter;
import com.tinder.scarlet.retry.BackoffStrategy;
import com.tinder.scarlet.websocket.okhttp.OkHttpClientUtils;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
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
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0004\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\b\u001a\u00020\tH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"INIT_BACKOFF_MILLIS", "", "MAX_BACKOFF_SEC", "KEY_CLIENT_ID", "", "coreAndroidNetworkModule", "Lorg/koin/core/module/Module;", "serverUrl", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "sdkVersion", "timeout", "Lcom/reown/android/relay/NetworkClientTimeout;", "packageName", "getLifecycle", "Lcom/tinder/scarlet/Lifecycle;", "Lorg/koin/core/scope/Scope;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreNetworkModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreNetworkModule.kt\ncom/reown/android/internal/common/di/CoreNetworkModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Module.kt\norg/koin/core/module/Module\n+ 5 Module.kt\norg/koin/core/module/ModuleKt\n+ 6 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,145:1\n138#2,5:146\n138#2,5:151\n138#2,5:156\n138#2,5:161\n138#2,5:166\n138#2,5:171\n138#2,5:177\n138#2,5:182\n138#2,5:187\n138#2,5:192\n138#2,5:197\n138#2,5:202\n138#2,5:207\n138#2,5:212\n138#2,5:217\n1#3:176\n153#4,10:222\n163#4,2:248\n153#4,10:250\n163#4,2:276\n105#4,6:278\n111#4,5:306\n105#4,6:311\n111#4,5:339\n105#4,6:344\n111#4,5:372\n105#4,6:377\n111#4,5:405\n105#4,6:410\n111#4,5:438\n105#4,6:443\n111#4,5:471\n105#4,6:476\n111#4,5:504\n105#4,6:509\n111#4,5:537\n105#4,6:542\n111#4,5:570\n105#4,6:575\n111#4,5:603\n105#4,6:608\n111#4,5:636\n105#4,6:641\n111#4,5:669\n105#4,6:674\n111#4,5:702\n212#5:232\n213#5:247\n212#5:260\n213#5:275\n196#5,7:284\n203#5:305\n196#5,7:317\n203#5:338\n196#5,7:350\n203#5:371\n196#5,7:383\n203#5:404\n196#5,7:416\n203#5:437\n196#5,7:449\n203#5:470\n196#5,7:482\n203#5:503\n196#5,7:515\n203#5:536\n196#5,7:548\n203#5:569\n196#5,7:581\n203#5:602\n196#5,7:614\n203#5:635\n196#5,7:647\n203#5:668\n196#5,7:680\n203#5:701\n115#6,14:233\n115#6,14:261\n115#6,14:291\n115#6,14:324\n115#6,14:357\n115#6,14:390\n115#6,14:423\n115#6,14:456\n115#6,14:489\n115#6,14:522\n115#6,14:555\n115#6,14:588\n115#6,14:621\n115#6,14:654\n115#6,14:687\n*S KotlinDebug\n*F\n+ 1 CoreNetworkModule.kt\ncom/reown/android/internal/common/di/CoreNetworkModuleKt\n*L\n142#1:146,5\n144#1:151,5\n45#1:156,5\n49#1:161,5\n60#1:166,5\n66#1:171,5\n81#1:177,5\n91#1:182,5\n92#1:187,5\n107#1:192,5\n123#1:197,5\n124#1:202,5\n126#1:207,5\n127#1:212,5\n132#1:217,5\n44#1:222,10\n44#1:248,2\n55#1:250,10\n55#1:276,2\n59#1:278,6\n59#1:306,5\n63#1:311,6\n63#1:339,5\n73#1:344,6\n73#1:372,5\n77#1:377,6\n77#1:405,5\n89#1:410,6\n89#1:438,5\n107#1:443,6\n107#1:471,5\n109#1:476,6\n109#1:504,5\n113#1:509,6\n113#1:537,5\n117#1:542,6\n117#1:570,5\n119#1:575,6\n119#1:603,5\n121#1:608,6\n121#1:636,5\n131#1:641,6\n131#1:669,5\n135#1:674,6\n135#1:702,5\n44#1:232\n44#1:247\n55#1:260\n55#1:275\n59#1:284,7\n59#1:305\n63#1:317,7\n63#1:338\n73#1:350,7\n73#1:371\n77#1:383,7\n77#1:404\n89#1:416,7\n89#1:437\n107#1:449,7\n107#1:470\n109#1:482,7\n109#1:503\n113#1:515,7\n113#1:536\n117#1:548,7\n117#1:569\n119#1:581,7\n119#1:602\n121#1:614,7\n121#1:635\n131#1:647,7\n131#1:668\n135#1:680,7\n135#1:701\n44#1:233,14\n55#1:261,14\n59#1:291,14\n63#1:324,14\n73#1:357,14\n77#1:390,14\n89#1:423,14\n107#1:456,14\n109#1:489,14\n113#1:522,14\n117#1:555,14\n119#1:588,14\n121#1:621,14\n131#1:654,14\n135#1:687,14\n*E\n"})
public final class CoreNetworkModuleKt {
    private static final long INIT_BACKOFF_MILLIS = 1;
    @NotNull
    public static final String KEY_CLIENT_ID = "clientId";
    private static final long MAX_BACKOFF_SEC = 20;

    public static final /* synthetic */ Module coreAndroidNetworkModule(String str, ConnectionType connectionType, String str2, NetworkClientTimeout networkClientTimeout, String str3) {
        Intrinsics.checkNotNullParameter(str, "serverUrl");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        Intrinsics.checkNotNullParameter(str3, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        return ModuleDSLKt.module$default(false, new g(str, connectionType, str3, networkClientTimeout, str2), 1, (Object) null);
    }

    public static /* synthetic */ Module coreAndroidNetworkModule$default(String str, ConnectionType connectionType, String str2, NetworkClientTimeout networkClientTimeout, String str3, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            networkClientTimeout = null;
        }
        return coreAndroidNetworkModule(str, connectionType, str2, networkClientTimeout, str3);
    }

    /* access modifiers changed from: private */
    public static final Unit coreAndroidNetworkModule$lambda$20(NetworkClientTimeout networkClientTimeout, String str, String str2, String str3, ConnectionType connectionType, Module module) {
        String str4 = str;
        ConnectionType connectionType2 = connectionType;
        Module module2 = module;
        Intrinsics.checkNotNullParameter(module2, "$this$module");
        NetworkClientTimeout defaultNetworkTimeout = networkClientTimeout == null ? NetworkClientTimeout.Companion.getDefaultNetworkTimeout() : networkClientTimeout;
        Qualifier named = QualifierKt.named(AndroidCommonDITags.RELAY_URL);
        e eVar = new e(str4, str2, 1);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Factory;
        Class<String> cls = String.class;
        new KoinDefinition(module2, a.h(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(cls), named, eVar, kind, CollectionsKt.emptyList()), module2));
        new KoinDefinition(module2, a.h(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.USER_AGENT), new d(str3, 5), kind, CollectionsKt.emptyList()), module2));
        c cVar = new c(23);
        StringQualifier rootScopeQualifier2 = companion.getRootScopeQualifier();
        Kind kind2 = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier2, Reflection.getOrCreateKotlinClass(GenerateJwtStoreClientIdUseCase.class), (Qualifier) null, cVar, kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module2, u3);
        Class<Interceptor> cls2 = Interceptor.class;
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls2), QualifierKt.named(AndroidCommonDITags.SHARED_INTERCEPTOR), new c(24), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module2, u4);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls2), QualifierKt.named(AndroidCommonDITags.LOGGING_INTERCEPTOR), new c(16), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module2, u5);
        SingleInstanceFactory u6 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Authenticator.class), QualifierKt.named(AndroidCommonDITags.AUTHENTICATOR), new d(str4, 4), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module2, u6);
        SingleInstanceFactory u7 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), new e(defaultNetworkTimeout, 2), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module2, u7);
        SingleInstanceFactory u8 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(MoshiMessageAdapter.Factory.class), QualifierKt.named(AndroidCommonDITags.MSG_ADAPTER), new c(17), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module2, u8);
        SingleInstanceFactory u9 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ManualConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.MANUAL_CONNECTION_LIFECYCLE), new c(18), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module2, u9);
        SingleInstanceFactory u10 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(DefaultConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.DEFAULT_CONNECTION_LIFECYCLE), new c(19), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module2, u10);
        SingleInstanceFactory u11 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ConditionalExponentialBackoffStrategy.class), (Qualifier) null, new h(connectionType2, 0), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module2, u11);
        SingleInstanceFactory u12 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(FlowStreamAdapter.Factory.class), (Qualifier) null, new c(20), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module2, u12);
        SingleInstanceFactory u13 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Scarlet.class), QualifierKt.named(AndroidCommonDITags.SCARLET), new h(connectionType2, 1), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u13);
        }
        new KoinDefinition(module2, u13);
        SingleInstanceFactory u14 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RelayService.class), QualifierKt.named(AndroidCommonDITags.RELAY_SERVICE), new c(21), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u14);
        }
        new KoinDefinition(module2, u14);
        SingleInstanceFactory u15 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ConnectivityState.class), QualifierKt.named(AndroidCommonDITags.CONNECTIVITY_STATE), new c(22), kind2, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u15);
        }
        new KoinDefinition(module2, u15);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String coreAndroidNetworkModule$lambda$20$lambda$0(String str, String str2, Scope scope, ParametersHolder parametersHolder) {
        return Uri.parse(str).buildUpon().appendQueryParameter("auth", ((GenerateJwtStoreClientIdUseCase) scope.get(A.a.t(scope, "$this$factory", parametersHolder, "it", GenerateJwtStoreClientIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).invoke(str)).appendQueryParameter("ua", (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.USER_AGENT), (Function0<? extends ParametersHolder>) null)).appendQueryParameter(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, str2).build().toString();
    }

    /* access modifiers changed from: private */
    public static final String coreAndroidNetworkModule$lambda$20$lambda$1(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$factory");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return C0118y.f("wc-2/reown-kotlin-", str, "/android-", Build.VERSION.RELEASE);
    }

    /* access modifiers changed from: private */
    public static final OkHttpClient coreAndroidNetworkModule$lambda$20$lambda$11(NetworkClientTimeout networkClientTimeout, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new OkHttpClient.Builder().addInterceptor((Interceptor) scope.get(Reflection.getOrCreateKotlinClass(Interceptor.class), QualifierKt.named(AndroidCommonDITags.SHARED_INTERCEPTOR), (Function0<? extends ParametersHolder>) null)).authenticator((Authenticator) scope.get(Reflection.getOrCreateKotlinClass(Authenticator.class), QualifierKt.named(AndroidCommonDITags.AUTHENTICATOR), (Function0<? extends ParametersHolder>) null)).writeTimeout(networkClientTimeout.getTimeout(), networkClientTimeout.getTimeUnit()).readTimeout(networkClientTimeout.getTimeout(), networkClientTimeout.getTimeUnit()).callTimeout(networkClientTimeout.getTimeout(), networkClientTimeout.getTimeUnit()).connectTimeout(networkClientTimeout.getTimeout(), networkClientTimeout.getTimeUnit()).retryOnConnectionFailure(true).build();
    }

    /* access modifiers changed from: private */
    public static final MoshiMessageAdapter.Factory coreAndroidNetworkModule$lambda$20$lambda$12(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        Moshi build = ((Moshi.Builder) scope.get(Reflection.getOrCreateKotlinClass(Moshi.Builder.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return new MoshiMessageAdapter.Factory(build, (MoshiMessageAdapter.Factory.Config) null, 2, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final ManualConnectionLifecycle coreAndroidNetworkModule$lambda$20$lambda$13(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ManualConnectionLifecycle((LifecycleRegistry) null, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final DefaultConnectionLifecycle coreAndroidNetworkModule$lambda$20$lambda$14(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new DefaultConnectionLifecycle(ModuleExtKt.androidApplication(scope), (LifecycleRegistry) null, 2, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final ConditionalExponentialBackoffStrategy coreAndroidNetworkModule$lambda$20$lambda$15(ConnectionType connectionType, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ConditionalExponentialBackoffStrategy(1, TimeUnit.SECONDS.toMillis(20), connectionType);
    }

    /* access modifiers changed from: private */
    public static final FlowStreamAdapter.Factory coreAndroidNetworkModule$lambda$20$lambda$16(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FlowStreamAdapter.Factory();
    }

    /* access modifiers changed from: private */
    public static final Scarlet coreAndroidNetworkModule$lambda$20$lambda$17(ConnectionType connectionType, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new Scarlet.Builder().backoffStrategy((BackoffStrategy) scope.get(Reflection.getOrCreateKotlinClass(ConditionalExponentialBackoffStrategy.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).webSocketFactory(OkHttpClientUtils.newWebSocketFactory((OkHttpClient) scope.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), QualifierKt.named(AndroidCommonDITags.OK_HTTP), (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.RELAY_URL), (Function0<? extends ParametersHolder>) null))).lifecycle(getLifecycle(scope, connectionType)).addMessageAdapterFactory((MessageAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(MoshiMessageAdapter.Factory.class), QualifierKt.named(AndroidCommonDITags.MSG_ADAPTER), (Function0<? extends ParametersHolder>) null)).addStreamAdapterFactory((StreamAdapter.Factory) scope.get(Reflection.getOrCreateKotlinClass(FlowStreamAdapter.Factory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).build();
    }

    /* access modifiers changed from: private */
    public static final RelayService coreAndroidNetworkModule$lambda$20$lambda$18(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return (RelayService) ((Scarlet) scope.get(Reflection.getOrCreateKotlinClass(Scarlet.class), QualifierKt.named(AndroidCommonDITags.SCARLET), (Function0<? extends ParametersHolder>) null)).create(RelayService.class);
    }

    /* access modifiers changed from: private */
    public static final ConnectivityState coreAndroidNetworkModule$lambda$20$lambda$19(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ConnectivityState(ModuleExtKt.androidApplication(scope));
    }

    /* access modifiers changed from: private */
    public static final GenerateJwtStoreClientIdUseCase coreAndroidNetworkModule$lambda$20$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GenerateJwtStoreClientIdUseCase((ClientIdJwtRepository) scope.get(Reflection.getOrCreateKotlinClass(ClientIdJwtRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SharedPreferences) scope.get(Reflection.getOrCreateKotlinClass(SharedPreferences.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final Interceptor coreAndroidNetworkModule$lambda$20$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new a(scope, 1);
    }

    /* access modifiers changed from: private */
    public static final Response coreAndroidNetworkModule$lambda$20$lambda$4$lambda$3(Scope scope, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().addHeader(HttpHeaders.USER_AGENT, (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.USER_AGENT), (Function0<? extends ParametersHolder>) null)).build());
    }

    /* access modifiers changed from: private */
    public static final Interceptor coreAndroidNetworkModule$lambda$20$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((HttpLoggingInterceptor.Logger) null, 1, (DefaultConstructorMarker) null);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /* access modifiers changed from: private */
    public static final Authenticator coreAndroidNetworkModule$lambda$20$lambda$9(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new f(scope, str);
    }

    /* access modifiers changed from: private */
    public static final Request coreAndroidNetworkModule$lambda$20$lambda$9$lambda$8(String str, Scope scope, Route route, Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Request request = response.request();
        if (!Intrinsics.areEqual((Object) Uri.parse(str).getHost(), (Object) request.url().host())) {
            return null;
        }
        return request.newBuilder().url((String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.RELAY_URL), (Function0<? extends ParametersHolder>) null)).build();
    }

    private static final Lifecycle getLifecycle(Scope scope, ConnectionType connectionType) {
        if (connectionType == ConnectionType.MANUAL) {
            return (Lifecycle) scope.get(Reflection.getOrCreateKotlinClass(ManualConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.MANUAL_CONNECTION_LIFECYCLE), (Function0<? extends ParametersHolder>) null);
        }
        return (Lifecycle) scope.get(Reflection.getOrCreateKotlinClass(DefaultConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.DEFAULT_CONNECTION_LIFECYCLE), (Function0<? extends ParametersHolder>) null);
    }
}
