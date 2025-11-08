package com.reown.android.di;

import androidx.sqlite.SQLiteConnection;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.di.BaseStorageModuleKt;
import com.reown.android.internal.common.di.CoreCryptoModuleKt;
import com.reown.sign.storage.data.dao.authenticatereponse.AuthenticateResponseTopicDaoQueries;
import io.nodle.cash.data.local.dao.PolkadotNftDao_Impl;
import kotlin.jvm.functions.Function1;
import org.koin.core.module.Module;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7298a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7299b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7300c;

    public /* synthetic */ a(String str, String str2, int i3) {
        this.f7298a = i3;
        this.f7299b = str;
        this.f7300c = str2;
    }

    public final Object invoke(Object obj) {
        switch (this.f7298a) {
            case 0:
                return CoreStorageModuleKt.coreStorageModule$lambda$12(this.f7299b, this.f7300c, (Module) obj);
            case 1:
                return BaseStorageModuleKt.baseStorageModule$lambda$23(this.f7299b, this.f7300c, (Module) obj);
            case 2:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7(this.f7299b, this.f7300c, (Module) obj);
            case 3:
                return PolkadotNftDao_Impl.lambda$delete$5(this.f7299b, this.f7300c, (SQLiteConnection) obj);
            case 4:
                return PolkadotNftDao_Impl.lambda$isPresent$4(this.f7299b, this.f7300c, (SQLiteConnection) obj);
            default:
                return AuthenticateResponseTopicDaoQueries.insertOrAbort$lambda$2(this.f7299b, this.f7300c, (SqlPreparedStatement) obj);
        }
    }
}
