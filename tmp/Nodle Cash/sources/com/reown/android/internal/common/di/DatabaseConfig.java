package com.reown.android.internal.common.di;

import android.support.v4.media.session.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/di/DatabaseConfig;", "", "storagePrefix", "", "<init>", "(Ljava/lang/String;)V", "ANDROID_CORE_DB_NAME", "getANDROID_CORE_DB_NAME", "()Ljava/lang/String;", "SIGN_SDK_DB_NAME", "getSIGN_SDK_DB_NAME", "NOTIFY_SDK_DB_NAME", "getNOTIFY_SDK_DB_NAME", "dbNames", "", "getDbNames", "()Ljava/util/List;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DatabaseConfig {
    @NotNull
    private final List<String> dbNames;
    @NotNull
    private final String storagePrefix;

    public DatabaseConfig() {
        this((String) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final String getANDROID_CORE_DB_NAME() {
        return a.m(this.storagePrefix, "WalletConnectAndroidCore.db");
    }

    @NotNull
    public final List<String> getDbNames() {
        return this.dbNames;
    }

    @NotNull
    public final String getNOTIFY_SDK_DB_NAME() {
        return a.m(this.storagePrefix, "WalletConnectV2_notify.db");
    }

    @NotNull
    public final String getSIGN_SDK_DB_NAME() {
        return a.m(this.storagePrefix, "WalletConnectV2.db");
    }

    public DatabaseConfig(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "storagePrefix");
        this.storagePrefix = str;
        this.dbNames = CollectionsKt.listOf(getANDROID_CORE_DB_NAME(), getSIGN_SDK_DB_NAME(), getNOTIFY_SDK_DB_NAME());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatabaseConfig(String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>") : str);
    }
}
