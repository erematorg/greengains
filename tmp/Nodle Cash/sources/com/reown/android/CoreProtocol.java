package com.reown.android;

import G1.C0235a;
import I1.C0238b;
import S0.a;
import S0.d;
import S0.e;
import S0.f;
import S0.g;
import S0.h;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.CoreInterface;
import com.reown.android.di.CoreStorageModuleKt;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.di.CoreCommonModuleKt;
import com.reown.android.internal.common.di.CoreCryptoModuleKt;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.di.CorePairingModuleKt;
import com.reown.android.internal.common.di.b;
import com.reown.android.internal.common.explorer.ExplorerInterface;
import com.reown.android.internal.common.explorer.ExplorerProtocol;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.TelemetryEnabled;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.client.PairingProtocol;
import com.reown.android.pairing.handler.PairingController;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.push.PushInterface;
import com.reown.android.push.client.PushClient;
import com.reown.android.relay.ConnectionType;
import com.reown.android.relay.NetworkClientTimeout;
import com.reown.android.relay.RelayClient;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.android.utils.ExtensionsKt;
import com.reown.android.utils.TimberKt;
import com.reown.android.verify.client.VerifyClient;
import com.reown.android.verify.client.VerifyInterface;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 >2\u00020\u0001:\u0001>B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016Jb\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010,2\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020%09H\u0016Jb\u0010(\u001a\u00020%2\u0006\u0010/\u001a\u0002002\u0006\u0010;\u001a\u00020,2\u0006\u0010)\u001a\u00020*2\u0006\u0010-\u001a\u00020.2\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010,2\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020%09H\u0016Jr\u0010<\u001a\u00020%*\u00020\u00002\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010,2\u0006\u0010;\u001a\u00020,2\u0006\u00106\u001a\u0002072\u0006\u0010-\u001a\u00020.2\b\u00104\u001a\u0004\u0018\u0001052\b\u00101\u001a\u0004\u0018\u0001022\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020%092\u0006\u0010)\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010,H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00020\u00158\u0016X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006?"}, d2 = {"Lcom/reown/android/CoreProtocol;", "Lcom/reown/android/CoreInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "Pairing", "Lcom/reown/android/pairing/client/PairingInterface;", "getPairing", "()Lcom/reown/android/pairing/client/PairingInterface;", "PairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "getPairingController", "()Lcom/reown/android/pairing/handler/PairingControllerInterface;", "Relay", "Lcom/reown/android/relay/RelayClient;", "getRelay", "()Lcom/reown/android/relay/RelayClient;", "setRelay", "(Lcom/reown/android/relay/RelayClient;)V", "Echo", "Lcom/reown/android/push/PushInterface;", "getEcho$annotations", "()V", "getEcho", "()Lcom/reown/android/push/PushInterface;", "Push", "getPush", "Verify", "Lcom/reown/android/verify/client/VerifyInterface;", "getVerify", "()Lcom/reown/android/verify/client/VerifyInterface;", "Explorer", "Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "getExplorer", "()Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "setDelegate", "", "delegate", "Lcom/reown/android/CoreInterface$Delegate;", "initialize", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "relayServerUrl", "", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "application", "Landroid/app/Application;", "relay", "Lcom/reown/android/relay/RelayConnectionInterface;", "keyServerUrl", "networkClientTimeout", "Lcom/reown/android/relay/NetworkClientTimeout;", "telemetryEnabled", "", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "projectId", "setup", "serverUrl", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreProtocol.kt\ncom/reown/android/CoreProtocol\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n+ 6 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,193:1\n1#2:194\n105#3,6:195\n111#3,5:223\n105#3,6:228\n111#3,5:256\n105#3,6:261\n111#3,5:289\n105#3,6:299\n111#3,5:327\n105#3,6:332\n111#3,5:360\n105#3,6:365\n111#3,5:393\n105#3,6:398\n111#3,5:426\n105#3,6:431\n111#3,5:459\n105#3,6:464\n111#3,5:492\n196#4,7:201\n203#4:222\n196#4,7:234\n203#4:255\n196#4,7:267\n203#4:288\n196#4,7:305\n203#4:326\n196#4,7:338\n203#4:359\n196#4,7:371\n203#4:392\n196#4,7:404\n203#4:425\n196#4,7:437\n203#4:458\n196#4,7:470\n203#4:491\n115#5,14:208\n115#5,14:241\n115#5,14:274\n115#5,14:312\n115#5,14:345\n115#5,14:378\n115#5,14:411\n115#5,14:444\n115#5,14:477\n138#6,5:294\n*S KotlinDebug\n*F\n+ 1 CoreProtocol.kt\ncom/reown/android/CoreProtocol\n*L\n147#1:195,6\n147#1:223,5\n148#1:228,6\n148#1:256,5\n149#1:261,6\n149#1:289,5\n161#1:299,6\n161#1:327,5\n163#1:332,6\n163#1:360,5\n165#1:365,6\n165#1:393,5\n177#1:398,6\n177#1:426,5\n178#1:431,6\n178#1:459,5\n179#1:464,6\n179#1:492,5\n147#1:201,7\n147#1:222\n148#1:234,7\n148#1:255\n149#1:267,7\n149#1:288\n161#1:305,7\n161#1:326\n163#1:338,7\n163#1:359\n165#1:371,7\n165#1:392\n177#1:404,7\n177#1:425\n178#1:437,7\n178#1:458\n179#1:470,7\n179#1:491\n147#1:208,14\n148#1:241,14\n149#1:274,14\n161#1:312,14\n163#1:345,14\n165#1:378,14\n177#1:411,14\n178#1:444,14\n179#1:477,14\n161#1:294,5\n*E\n"})
public final class CoreProtocol implements CoreInterface {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final CoreProtocol instance = new CoreProtocol((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    @NotNull
    private final PushInterface Echo;
    @NotNull
    private final ExplorerInterface Explorer;
    @NotNull
    private final PairingInterface Pairing;
    @NotNull
    private final PairingControllerInterface PairingController;
    @NotNull
    private final PushInterface Push;
    @NotNull
    private RelayClient Relay;
    @NotNull
    private final VerifyInterface Verify;
    @NotNull
    private final KoinApplication koinApp;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/CoreProtocol$Companion;", "", "<init>", "()V", "instance", "Lcom/reown/android/CoreProtocol;", "getInstance", "()Lcom/reown/android/CoreProtocol;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CoreProtocol getInstance() {
            return CoreProtocol.instance;
        }

        private Companion() {
        }
    }

    public CoreProtocol() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    @Deprecated(message = "Replaced with Push")
    public static /* synthetic */ void getEcho$annotations() {
    }

    private final void setup(CoreProtocol coreProtocol, Application application, String str, String str2, boolean z2, ConnectionType connectionType, NetworkClientTimeout networkClientTimeout, RelayConnectionInterface relayConnectionInterface, Function1<? super Core.Model.Error, Unit> function1, Core.Model.AppMetaData appMetaData, String str3) {
        CoreProtocol coreProtocol2 = coreProtocol;
        String str4 = str2;
        ConnectionType connectionType2 = connectionType;
        RelayConnectionInterface relayConnectionInterface2 = relayConnectionInterface;
        String packageName = application.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        String a2 = (str == null || str.length() == 0) ? c.a("wss://relay.walletconnect.org?projectId=", str4) : str;
        KoinApplication koinApplication = coreProtocol2.koinApp;
        KoinExtKt.androidContext(koinApplication, application);
        koinApplication.modules(ModuleDSLKt.module$default(false, new f(packageName, 0), 1, (Object) null), ModuleDSLKt.module$default(false, new f(str4, 1), 1, (Object) null), ModuleDSLKt.module$default(false, new g(z2, 0), 1, (Object) null), CoreNetworkModuleKt.coreAndroidNetworkModule(a2, connectionType2, "1.4.11", networkClientTimeout, packageName), CoreCommonModuleKt.coreCommonModule(), CoreCryptoModuleKt.coreCryptoModule$default((String) null, (String) null, 3, (Object) null));
        if (relayConnectionInterface2 == null) {
            coreProtocol.getRelay().initialize(connectionType2, new h(function1, 0));
        }
        koinApplication.modules(CoreStorageModuleKt.coreStorageModule$default((String) null, packageName, 1, (Object) null), ModuleDSLKt.module$default(false, new C0238b(6), 1, (Object) null), ModuleDSLKt.module$default(false, new b(5), 1, (Object) null), ModuleDSLKt.module$default(false, new C0235a(relayConnectionInterface2, coreProtocol2, 3), 1, (Object) null), ModuleDSLKt.module$default(false, new D1.b(appMetaData, 5), 1, (Object) null), ModuleDSLKt.module$default(false, new a(coreProtocol2, 2), 1, (Object) null), ModuleDSLKt.module$default(false, new a(coreProtocol2, 0), 1, (Object) null), ModuleDSLKt.module$default(false, new a(coreProtocol2, 1), 1, (Object) null), ModuleDSLKt.module$default(false, new b(3), 1, (Object) null), CorePairingModuleKt.corePairingModule(coreProtocol.getPairing(), coreProtocol.getPairingController()), ModuleDSLKt.module$default(false, new f(str3, 3), 1, (Object) null), ModuleDSLKt.module$default(false, new b(4), 1, (Object) null), ModuleDSLKt.module$default(false, new b(0), 1, (Object) null), Intrinsics.checkNotNullParameter(packageName, "bundleId"));
        coreProtocol.getPairing().initialize();
        coreProtocol.getPairingController().initialize();
        coreProtocol.getVerify().initialize();
    }

    public static /* synthetic */ void setup$default(CoreProtocol coreProtocol, CoreProtocol coreProtocol2, Application application, String str, String str2, boolean z2, ConnectionType connectionType, NetworkClientTimeout networkClientTimeout, RelayConnectionInterface relayConnectionInterface, Function1 function1, Core.Model.AppMetaData appMetaData, String str3, int i3, Object obj) {
        coreProtocol.setup(coreProtocol2, application, (i3 & 2) != 0 ? null : str, str2, z2, connectionType, networkClientTimeout, relayConnectionInterface, function1, appMetaData, str3);
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$10(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.CLIENT_ID);
        R1.a aVar = new R1.a(2);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(String.class), named, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String setup$lambda$22$lambda$10$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        String string = ((SharedPreferences) scope.get(A.a.t(scope, "$this$single", parametersHolder, "it", SharedPreferences.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getString(CoreNetworkModuleKt.KEY_CLIENT_ID, (String) null);
        if (string != null) {
            return string;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$12(RelayConnectionInterface relayConnectionInterface, CoreProtocol coreProtocol, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        I.a aVar = new I.a(relayConnectionInterface, coreProtocol, 1);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RelayConnectionInterface.class), (Qualifier) null, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final RelayConnectionInterface setup$lambda$22$lambda$12$lambda$11(RelayConnectionInterface relayConnectionInterface, CoreProtocol coreProtocol, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return relayConnectionInterface == null ? coreProtocol.getRelay() : relayConnectionInterface;
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$15(Core.Model.AppMetaData appMetaData, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        e eVar = new e(appMetaData, 0);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, eVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final AppMetaData setup$lambda$22$lambda$15$lambda$14(Core.Model.AppMetaData appMetaData, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new AppMetaData(appMetaData.getDescription(), appMetaData.getUrl(), appMetaData.getIcons(), appMetaData.getName(), new Redirect(appMetaData.getRedirect(), appMetaData.getAppLink(), appMetaData.getLinkMode()), (String) null, 32, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$17(CoreProtocol coreProtocol, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        S0.c cVar = new S0.c(coreProtocol, 1);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PushInterface.class), (Qualifier) null, cVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final PushInterface setup$lambda$22$lambda$17$lambda$16(CoreProtocol coreProtocol, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return coreProtocol.getEcho();
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$19(CoreProtocol coreProtocol, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        S0.c cVar = new S0.c(coreProtocol, 0);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PushInterface.class), (Qualifier) null, cVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final PushInterface setup$lambda$22$lambda$19$lambda$18(CoreProtocol coreProtocol, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return coreProtocol.getPush();
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$21(CoreProtocol coreProtocol, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        S0.c cVar = new S0.c(coreProtocol, 2);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyInterface.class), (Qualifier) null, cVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final VerifyInterface setup$lambda$22$lambda$21$lambda$20(CoreProtocol coreProtocol, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return coreProtocol.getVerify();
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$3(String str, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.PACKAGE_NAME);
        d dVar = new d(str, 1);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(String.class), named, dVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String setup$lambda$22$lambda$3$lambda$2(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return str;
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$5(String str, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        d dVar = new d(str, 0);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, dVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final ProjectId setup$lambda$22$lambda$5$lambda$4(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ProjectId(str);
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$7(boolean z2, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.TELEMETRY_ENABLED);
        S0.b bVar = new S0.b(z2);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(TelemetryEnabled.class), named, bVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final TelemetryEnabled setup$lambda$22$lambda$7$lambda$6(boolean z2, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return TelemetryEnabled.m8785boximpl(TelemetryEnabled.m8786constructorimpl(z2));
    }

    /* access modifiers changed from: private */
    public static final Unit setup$lambda$22$lambda$8(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    @NotNull
    public PushInterface getEcho() {
        return this.Echo;
    }

    @NotNull
    public ExplorerInterface getExplorer() {
        return this.Explorer;
    }

    @NotNull
    public PairingInterface getPairing() {
        return this.Pairing;
    }

    @NotNull
    public PairingControllerInterface getPairingController() {
        return this.PairingController;
    }

    @NotNull
    public PushInterface getPush() {
        return this.Push;
    }

    @NotNull
    public VerifyInterface getVerify() {
        return this.Verify;
    }

    public void initialize(@NotNull Core.Model.AppMetaData appMetaData, @NotNull String str, @NotNull ConnectionType connectionType, @NotNull Application application, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Function1<? super Core.Model.Error, Unit> function12 = function1;
        Core.Model.AppMetaData appMetaData2 = appMetaData;
        Intrinsics.checkNotNullParameter(appMetaData, "metaData");
        Intrinsics.checkNotNullParameter(str, "relayServerUrl");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(function12, "onError");
        try {
            if (ExtensionsKt.isValidRelayServerUrl(str)) {
                setup(this, application, str, ExtensionsKt.projectId(str), z2, connectionType, networkClientTimeout, relayConnectionInterface, function1, appMetaData, str2);
                return;
            }
            throw new IllegalArgumentException("Check the schema and projectId parameter of the Server Url");
        } catch (Exception e3) {
            function12.invoke(new Core.Model.Error(e3));
        }
    }

    public void setDelegate(@NotNull CoreInterface.Delegate delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        getPairing().setDelegate(delegate);
    }

    public void setRelay(@NotNull RelayClient relayClient) {
        Intrinsics.checkNotNullParameter(relayClient, "<set-?>");
        this.Relay = relayClient;
    }

    public CoreProtocol(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
        this.Pairing = new PairingProtocol(koinApplication);
        this.PairingController = new PairingController(koinApplication);
        this.Relay = new RelayClient(koinApplication);
        PushClient pushClient = PushClient.INSTANCE;
        this.Echo = pushClient;
        this.Push = pushClient;
        this.Verify = new VerifyClient(koinApplication, (CoroutineScope) null, 2, (DefaultConstructorMarker) null);
        this.Explorer = new ExplorerProtocol(koinApplication);
        TimberKt.plantTimber();
    }

    @NotNull
    public RelayClient getRelay() {
        return this.Relay;
    }

    public void initialize(@NotNull Application application, @NotNull String str, @NotNull Core.Model.AppMetaData appMetaData, @NotNull ConnectionType connectionType, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Function1<? super Core.Model.Error, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(str, "projectId");
        Intrinsics.checkNotNullParameter(appMetaData, "metaData");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        Intrinsics.checkNotNullParameter(function12, "onError");
        try {
            if (str.length() > 0) {
                setup$default(this, this, application, (String) null, str, z2, connectionType, networkClientTimeout, relayConnectionInterface, function1, appMetaData, str2, 2, (Object) null);
                return;
            }
            throw new IllegalArgumentException("Project Id cannot be empty");
        } catch (Exception e3) {
            function12.invoke(new Core.Model.Error(e3));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CoreProtocol(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }
}
