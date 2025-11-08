package com.reown.android.di;

import G1.C0235a;
import I1.C0238b;
import R1.a;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import app.cash.sqldelight.driver.android.AndroidSqliteDriver;
import com.getkeepsafe.relinker.ReLinker;
import com.google.android.gms.stats.CodePackage;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.di.BaseStorageModuleKt;
import com.reown.android.internal.common.di.DatabaseConfig;
import com.reown.android.internal.common.di.DatabaseConfigKt;
import com.reown.android.sdk.core.AndroidCoreDatabase;
import com.reown.foundation.util.Logger;
import com.reown.util.UtilFunctionsKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\f\u001a\u00020\r*\u00020\u000eH\u0002\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u000eH\u0002\u001a\b\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\b\u0010\u0013\u001a\u00020\u0014H\u0002\u001a(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\u0018\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001\u001a$\u0010#\u001a\u00020\u00142\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100&0%2\u0006\u0010'\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"ANDROID_KEYSTORE", "", "KEYSTORE_ALIAS", "SHARED_PREFS_FILENAME", "KEY_SIZE", "", "keyStore", "Ljava/security/KeyStore;", "cipher", "Ljavax/crypto/Cipher;", "keyGenParameterSpec", "Landroid/security/keystore/KeyGenParameterSpec;", "createSharedPreferences", "Landroid/content/SharedPreferences;", "Lorg/koin/core/scope/Scope;", "deleteSharedPreferences", "", "getSecretKey", "Ljavax/crypto/SecretKey;", "signingModule", "Lorg/koin/core/module/Module;", "getSupportFactory", "Lnet/zetetic/database/sqlcipher/SupportOpenHelperFactory;", "context", "Landroid/content/Context;", "passphrase", "", "hook", "Lnet/zetetic/database/sqlcipher/SQLiteDatabaseHook;", "clearPassphrase", "", "loadSqlCipherLibrary", "coreStorageModule", "storagePrefix", "packageName", "sdkBaseStorageModule", "databaseSchema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "databaseName", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoreStorageModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreStorageModule.kt\ncom/reown/android/di/CoreStorageModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,206:1\n138#2,5:207\n138#2,5:245\n138#2,5:250\n138#2,5:288\n105#3,6:212\n111#3,5:240\n105#3,6:255\n111#3,5:283\n105#3,6:293\n111#3,5:321\n196#4,7:218\n203#4:239\n196#4,7:261\n203#4:282\n196#4,7:299\n203#4:320\n115#5,14:225\n115#5,14:268\n115#5,14:306\n1#6:326\n*S KotlinDebug\n*F\n+ 1 CoreStorageModule.kt\ncom/reown/android/di/CoreStorageModuleKt\n*L\n87#1:207,5\n189#1:245,5\n190#1:250,5\n203#1:288,5\n104#1:212,6\n104#1:240,5\n185#1:255,6\n185#1:283,5\n198#1:293,6\n198#1:321,5\n104#1:218,7\n104#1:239\n185#1:261,7\n185#1:282\n198#1:299,7\n198#1:320\n104#1:225,14\n185#1:268,14\n198#1:306,14\n*E\n"})
public final class CoreStorageModuleKt {
    @NotNull
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    @NotNull
    private static final String KEYSTORE_ALIAS = "_wc_db_key_";
    private static final int KEY_SIZE = 256;
    @NotNull
    private static final String SHARED_PREFS_FILENAME = "db_key_store";
    @NotNull
    private static final Cipher cipher;
    @NotNull
    private static final KeyGenParameterSpec keyGenParameterSpec;
    @NotNull
    private static final KeyStore keyStore;

    static {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEYSTORE);
        instance.load((KeyStore.LoadStoreParameter) null);
        Intrinsics.checkNotNullExpressionValue(instance, "apply(...)");
        keyStore = instance;
        Cipher instance2 = Cipher.getInstance("AES/GCM/NoPadding");
        Intrinsics.checkNotNullExpressionValue(instance2, "let(...)");
        cipher = instance2;
        KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(KEYSTORE_ALIAS, 3).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).setKeySize(256).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        keyGenParameterSpec = build;
    }

    @NotNull
    public static final Module coreStorageModule(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "storagePrefix");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        return ModuleDSLKt.module$default(false, new a(str, str2, 0), 1, (Object) null);
    }

    public static /* synthetic */ Module coreStorageModule$default(String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        return coreStorageModule(str, str2);
    }

    /* access modifiers changed from: private */
    public static final Unit coreStorageModule$lambda$12(String str, String str2, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        module.includes(BaseStorageModuleKt.baseStorageModule(str, str2), signingModule());
        Qualifier named = QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE_DRIVER);
        a aVar = new a(3);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SqlDriver.class), named, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final SqlDriver coreStorageModule$lambda$12$lambda$11(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new AndroidSqliteDriver(AndroidCoreDatabase.Companion.getSchema(), ModuleExtKt.androidContext(scope), ((DatabaseConfig) scope.get(Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getANDROID_CORE_DB_NAME(), getSupportFactory(ModuleExtKt.androidContext(scope), (byte[]) scope.get(Reflection.getOrCreateKotlinClass(byte[].class), QualifierKt.named(AndroidBuildVariantDITags.DB_PASSPHRASE), (Function0<? extends ParametersHolder>) null), (SQLiteDatabaseHook) null, false), (SupportSQLiteOpenHelper.Callback) null, 0, false, (Long) null, 240, (DefaultConstructorMarker) null);
    }

    private static final synchronized SharedPreferences createSharedPreferences(Scope scope) {
        SharedPreferences create;
        synchronized (CoreStorageModuleKt.class) {
            MasterKey build = new MasterKey.Builder(ModuleExtKt.androidContext(scope), KEYSTORE_ALIAS).setKeyGenParameterSpec(keyGenParameterSpec).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            create = EncryptedSharedPreferences.create(ModuleExtKt.androidContext(scope), SHARED_PREFS_FILENAME, build, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        }
        return create;
    }

    private static final synchronized void deleteSharedPreferences(Scope scope) {
        synchronized (CoreStorageModuleKt.class) {
            try {
                Context androidContext = ModuleExtKt.androidContext(scope);
                if (androidContext.getSharedPreferences(SHARED_PREFS_FILENAME, 0) != null) {
                    androidContext.deleteSharedPreferences(SHARED_PREFS_FILENAME);
                }
                keyStore.deleteEntry(KEYSTORE_ALIAS);
            } catch (Exception e3) {
                Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
                ((Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null)).error("Occurred when trying to reset encrypted shared prefs: " + e3);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.security.KeyStore$Entry] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final synchronized javax.crypto.SecretKey getSecretKey() {
        /*
            java.lang.Class<com.reown.android.di.CoreStorageModuleKt> r0 = com.reown.android.di.CoreStorageModuleKt.class
            monitor-enter(r0)
            java.security.KeyStore r1 = keyStore     // Catch:{ all -> 0x0018 }
            android.security.keystore.KeyGenParameterSpec r2 = keyGenParameterSpec     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = r2.getKeystoreAlias()     // Catch:{ all -> 0x0018 }
            r4 = 0
            java.security.KeyStore$Entry r1 = r1.getEntry(r3, r4)     // Catch:{ all -> 0x0018 }
            boolean r3 = r1 instanceof java.security.KeyStore.SecretKeyEntry     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x001a
            r4 = r1
            java.security.KeyStore$SecretKeyEntry r4 = (java.security.KeyStore.SecretKeyEntry) r4     // Catch:{ all -> 0x0018 }
            goto L_0x001a
        L_0x0018:
            r1 = move-exception
            goto L_0x0038
        L_0x001a:
            if (r4 == 0) goto L_0x0022
            javax.crypto.SecretKey r1 = r4.getSecretKey()     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x0036
        L_0x0022:
            java.lang.String r1 = "AES"
            java.lang.String r3 = "AndroidKeyStore"
            javax.crypto.KeyGenerator r1 = javax.crypto.KeyGenerator.getInstance(r1, r3)     // Catch:{ all -> 0x0018 }
            r1.init(r2)     // Catch:{ all -> 0x0018 }
            javax.crypto.SecretKey r1 = r1.generateKey()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "run(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ all -> 0x0018 }
        L_0x0036:
            monitor-exit(r0)
            return r1
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.di.CoreStorageModuleKt.getSecretKey():javax.crypto.SecretKey");
    }

    @NotNull
    public static final SupportOpenHelperFactory getSupportFactory(@NotNull Context context, @NotNull byte[] bArr, @Nullable SQLiteDatabaseHook sQLiteDatabaseHook, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bArr, "passphrase");
        loadSqlCipherLibrary(context);
        return new SupportOpenHelperFactory(bArr, sQLiteDatabaseHook, z2);
    }

    private static final void loadSqlCipherLibrary(Context context) {
        try {
            System.loadLibrary("sqlcipher");
        } catch (UnsatisfiedLinkError e3) {
            ReLinker.loadLibrary(context, "sqlcipher", (ReLinker.LoadListener) new CoreStorageModuleKt$loadSqlCipherLibrary$1(e3));
        }
    }

    @NotNull
    @SuppressLint({"HardwareIds"})
    public static final Module sdkBaseStorageModule(@NotNull SqlSchema<QueryResult.Value<Unit>> sqlSchema, @NotNull String str) {
        Intrinsics.checkNotNullParameter(sqlSchema, "databaseSchema");
        Intrinsics.checkNotNullParameter(str, "databaseName");
        return ModuleDSLKt.module$default(false, new C0235a(sqlSchema, str), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit sdkBaseStorageModule$lambda$14(String str, SqlSchema sqlSchema, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        StringQualifier named = QualifierKt.named(str);
        I.a aVar = new I.a(sqlSchema, str, 3);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SqlDriver.class), named, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final SqlDriver sdkBaseStorageModule$lambda$14$lambda$13(SqlSchema sqlSchema, String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new AndroidSqliteDriver(sqlSchema, ModuleExtKt.androidContext(scope), str, getSupportFactory(ModuleExtKt.androidContext(scope), (byte[]) scope.get(Reflection.getOrCreateKotlinClass(byte[].class), QualifierKt.named(AndroidBuildVariantDITags.DB_PASSPHRASE), (Function0<? extends ParametersHolder>) null), (SQLiteDatabaseHook) null, false), (SupportSQLiteOpenHelper.Callback) null, 0, false, (Long) null, 240, (DefaultConstructorMarker) null);
    }

    private static final synchronized Module signingModule() {
        Module module$default;
        synchronized (CoreStorageModuleKt.class) {
            module$default = ModuleDSLKt.module$default(false, new C0238b(29), 1, (Object) null);
        }
        return module$default;
    }

    /* access modifiers changed from: private */
    public static final Unit signingModule$lambda$10(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier named = QualifierKt.named(AndroidBuildVariantDITags.DB_PASSPHRASE);
        a aVar = new a(4);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(byte[].class), named, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final byte[] signingModule$lambda$10$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        SharedPreferences sharedPreferences;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        try {
            sharedPreferences = createSharedPreferences(scope);
        } catch (Exception unused) {
            deleteSharedPreferences(scope);
            DatabaseConfigKt.deleteDatabases(scope);
            sharedPreferences = createSharedPreferences(scope);
        }
        byte[] bArr2 = null;
        String string = sharedPreferences.getString("encryptedDBKey", (String) null);
        if (string != null) {
            bArr2 = Base64.decode(string, 0);
        }
        if (bArr2 == null) {
            bArr = UtilFunctionsKt.randomBytes(32);
            SecretKey secretKey = getSecretKey();
            Cipher cipher2 = cipher;
            cipher2.init(1, secretKey);
            byte[] doFinal = cipher2.doFinal(bArr);
            Intrinsics.checkNotNullExpressionValue(doFinal, "doFinal(...)");
            byte[] iv = cipher2.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            byte[] bArr3 = new byte[(iv.length + 4 + doFinal.length)];
            ByteBuffer wrap = ByteBuffer.wrap(bArr3);
            wrap.order(ByteOrder.BIG_ENDIAN);
            wrap.putInt(iv.length);
            wrap.put(iv);
            wrap.put(doFinal);
            sharedPreferences.edit().putString("encryptedDBKey", Base64.encodeToString(bArr3, 2)).apply();
        } else {
            ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
            wrap2.order(ByteOrder.BIG_ENDIAN);
            int i3 = wrap2.getInt();
            byte[] bArr4 = new byte[i3];
            wrap2.get(bArr4);
            byte[] bArr5 = new byte[((bArr2.length - 4) - i3)];
            wrap2.get(bArr5);
            SecretKey secretKey2 = getSecretKey();
            GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr4);
            Cipher cipher3 = cipher;
            cipher3.init(2, secretKey2, gCMParameterSpec);
            bArr = cipher3.doFinal(bArr5);
        }
        Intrinsics.checkNotNull(bArr);
        return bArr;
    }
}
