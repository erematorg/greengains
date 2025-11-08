package com.reown.android.internal.common.di;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.koin.android.ext.koin.ModuleExtKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0006"}, d2 = {"deleteDatabase", "", "Lorg/koin/core/scope/Scope;", "dbName", "", "deleteDatabases", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDatabaseConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DatabaseConfig.kt\ncom/reown/android/internal/common/di/DatabaseConfigKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,31:1\n13472#2:32\n13473#2:38\n138#3,5:33\n*S KotlinDebug\n*F\n+ 1 DatabaseConfig.kt\ncom/reown/android/internal/common/di/DatabaseConfigKt\n*L\n26#1:32\n26#1:38\n27#1:33,5\n*E\n"})
public final class DatabaseConfigKt {
    public static final void deleteDatabase(@NotNull Scope scope, @NotNull String str) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(str, "dbName");
        ModuleExtKt.androidContext(scope).deleteDatabase(str);
    }

    public static final void deleteDatabases(@NotNull Scope scope) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        String[] databaseList = ModuleExtKt.androidContext(scope).databaseList();
        Intrinsics.checkNotNullExpressionValue(databaseList, "databaseList(...)");
        for (String str : databaseList) {
            if (((DatabaseConfig) scope.get(Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getDbNames().contains(str)) {
                Intrinsics.checkNotNull(str);
                deleteDatabase(scope, str);
            }
        }
    }
}
