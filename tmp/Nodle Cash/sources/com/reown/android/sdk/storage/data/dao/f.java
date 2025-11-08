package com.reown.android.sdk.storage.data.dao;

import androidx.sqlite.SQLiteConnection;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import io.nodle.cash.data.local.dao.TokensDao_Impl;
import io.nodle.cash.data.local.dao.TokensMetadataDao_Impl;
import io.nodle.cash.data.local.dao.TokensPricesDao_Impl;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class f implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7381a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f7382b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7383c;

    public /* synthetic */ f(long j2, String str, int i3) {
        this.f7381a = i3;
        this.f7382b = j2;
        this.f7383c = str;
    }

    public final Object invoke(Object obj) {
        switch (this.f7381a) {
            case 0:
                String str = this.f7383c;
                return JsonRpcHistoryQueries.updateJsonRpcHistory$lambda$17(str, this.f7382b, (SqlPreparedStatement) obj);
            case 1:
                return PairingQueries.updateOrAbortExpiry$lambda$16(this.f7382b, this.f7383c, (SqlPreparedStatement) obj);
            case 2:
                String str2 = this.f7383c;
                return VerifyPublicKeyQueries.upsertKey$lambda$2(str2, this.f7382b, (SqlPreparedStatement) obj);
            case 3:
                return SessionDaoQueries.updateSessionExpiry$lambda$22(this.f7382b, this.f7383c, (SqlPreparedStatement) obj);
            case 4:
                return TokensDao_Impl.lambda$getToken$4(this.f7382b, this.f7383c, (SQLiteConnection) obj);
            case 5:
                return TokensMetadataDao_Impl.lambda$getTokenMetadata$1(this.f7382b, this.f7383c, (SQLiteConnection) obj);
            default:
                return TokensPricesDao_Impl.lambda$getToken$3(this.f7382b, this.f7383c, (SQLiteConnection) obj);
        }
    }

    public /* synthetic */ f(String str, long j2, int i3) {
        this.f7381a = i3;
        this.f7383c = str;
        this.f7382b = j2;
    }
}
