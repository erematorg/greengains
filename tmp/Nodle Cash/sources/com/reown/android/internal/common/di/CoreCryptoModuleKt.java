package com.reown.android.internal.common.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.google.android.gms.stats.CodePackage;
import com.reown.android.di.a;
import com.reown.android.internal.common.crypto.codec.ChaChaPolyCodec;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.jwt.clientid.ClientIdJwtRepositoryAndroid;
import com.reown.android.internal.common.storage.key_chain.KeyChain;
import com.reown.android.internal.common.storage.key_chain.KeyStore;
import com.reown.foundation.crypto.data.repository.ClientIdJwtRepository;
import com.reown.foundation.util.Logger;
import java.security.KeyStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
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

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"ANDROID_KEY_STORE", "", "SHARED_PREFS_FILE", "KEY_STORE_ALIAS", "KEY_SIZE", "", "coreCryptoModule", "Lorg/koin/core/module/Module;", "sharedPrefsFile", "keyStoreAlias", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreCryptoModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreCryptoModule.kt\ncom/reown/android/internal/common/di/CoreCryptoModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,102:1\n138#2,5:103\n138#2,5:108\n138#2,5:113\n138#2,5:118\n138#2,5:123\n138#2,5:128\n105#3,6:133\n111#3,5:161\n105#3,6:166\n111#3,5:194\n105#3,6:199\n111#3,5:227\n105#3,6:232\n111#3,5:260\n105#3,6:265\n111#3,5:293\n196#4,7:139\n203#4:160\n196#4,7:172\n203#4:193\n196#4,7:205\n203#4:226\n196#4,7:238\n203#4:259\n196#4,7:271\n203#4:292\n115#5,14:146\n115#5,14:179\n115#5,14:212\n115#5,14:245\n115#5,14:278\n*S KotlinDebug\n*F\n+ 1 CoreCryptoModule.kt\ncom/reown/android/internal/common/di/CoreCryptoModuleKt\n*L\n79#1:103,5\n87#1:108,5\n95#1:113,5\n97#1:118,5\n99#1:123,5\n101#1:128,5\n83#1:133,6\n83#1:161,5\n95#1:166,6\n95#1:194,5\n97#1:199,6\n97#1:227,5\n99#1:232,6\n99#1:260,5\n101#1:265,6\n101#1:293,5\n83#1:139,7\n83#1:160\n95#1:172,7\n95#1:193\n97#1:205,7\n97#1:226\n99#1:238,7\n99#1:259\n101#1:271,7\n101#1:292\n83#1:146,14\n95#1:179,14\n97#1:212,14\n99#1:245,14\n101#1:278,14\n*E\n"})
public final class CoreCryptoModuleKt {
    @NotNull
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final int KEY_SIZE = 256;
    @NotNull
    private static final String KEY_STORE_ALIAS = "wc_keystore_key";
    @NotNull
    private static final String SHARED_PREFS_FILE = "wc_key_store";

    public static final /* synthetic */ Module coreCryptoModule(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "sharedPrefsFile");
        Intrinsics.checkNotNullParameter(str2, "keyStoreAlias");
        return ModuleDSLKt.module$default(false, new a(str2, str, 2), 1, (Object) null);
    }

    public static /* synthetic */ Module coreCryptoModule$default(String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = SHARED_PREFS_FILE;
        }
        if ((i3 & 2) != 0) {
            str2 = KEY_STORE_ALIAS;
        }
        return coreCryptoModule(str, str2);
    }

    /* access modifiers changed from: private */
    public static final Unit coreCryptoModule$lambda$7(String str, String str2, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        e eVar = new e(str, str2, 0);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(SharedPreferences.class), (Qualifier) null, eVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        c cVar = new c(9);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(KeyStore.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        c cVar2 = new c(10);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ClientIdJwtRepository.class), (Qualifier) null, cVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        c cVar3 = new c(11);
        SingleInstanceFactory u6 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        c cVar4 = new c(12);
        SingleInstanceFactory u7 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Codec.class), (Qualifier) null, cVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        return Unit.INSTANCE;
    }

    private static final synchronized SharedPreferences coreCryptoModule$lambda$7$createSharedPreferences(Scope scope, String str, String str2) {
        SharedPreferences create;
        synchronized (CoreCryptoModuleKt.class) {
            KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(str, 3).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).setKeySize(256).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            MasterKey build2 = new MasterKey.Builder(ModuleExtKt.androidContext(scope), str).setKeyGenParameterSpec(build).build();
            Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
            create = EncryptedSharedPreferences.create(ModuleExtKt.androidContext(scope), str2, build2, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        }
        return create;
    }

    private static final synchronized void coreCryptoModule$lambda$7$deleteMasterKey(String str) {
        synchronized (CoreCryptoModuleKt.class) {
            java.security.KeyStore instance = java.security.KeyStore.getInstance(ANDROID_KEY_STORE);
            instance.load((KeyStore.LoadStoreParameter) null);
            instance.deleteEntry(str);
        }
    }

    private static final synchronized void coreCryptoModule$lambda$7$deleteSharedPreferences(Scope scope, String str) {
        synchronized (CoreCryptoModuleKt.class) {
            try {
                Context androidContext = ModuleExtKt.androidContext(scope);
                if (androidContext.getSharedPreferences(str, 0) != null) {
                    androidContext.deleteSharedPreferences(str);
                }
            } catch (Exception e3) {
                Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
                ((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null)).error("Occurred when trying to reset encrypted shared prefs: " + e3);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final SharedPreferences coreCryptoModule$lambda$7$lambda$2(String str, String str2, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        try {
            return coreCryptoModule$lambda$7$createSharedPreferences(scope, str, str2);
        } catch (Exception e3) {
            ((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null)).error((Throwable) e3);
            coreCryptoModule$lambda$7$deleteMasterKey(str);
            coreCryptoModule$lambda$7$deleteSharedPreferences(scope, str2);
            DatabaseConfigKt.deleteDatabases(scope);
            return coreCryptoModule$lambda$7$createSharedPreferences(scope, str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static final com.reown.android.internal.common.storage.key_chain.KeyStore coreCryptoModule$lambda$7$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new KeyChain((SharedPreferences) scope.get(Reflection.getOrCreateKotlinClass(SharedPreferences.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ClientIdJwtRepository coreCryptoModule$lambda$7$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ClientIdJwtRepositoryAndroid((com.reown.android.internal.common.storage.key_chain.KeyStore) scope.get(Reflection.getOrCreateKotlinClass(com.reown.android.internal.common.storage.key_chain.KeyStore.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final KeyManagementRepository coreCryptoModule$lambda$7$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new BouncyCastleKeyManagementRepository((com.reown.android.internal.common.storage.key_chain.KeyStore) scope.get(Reflection.getOrCreateKotlinClass(com.reown.android.internal.common.storage.key_chain.KeyStore.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final Codec coreCryptoModule$lambda$7$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ChaChaPolyCodec((KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }
}
