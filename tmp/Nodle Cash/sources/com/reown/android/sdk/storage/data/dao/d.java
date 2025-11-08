package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7376a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7377b;

    public /* synthetic */ d(String str, int i3) {
        this.f7376a = i3;
        this.f7377b = str;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7376a;
        String str = this.f7377b;
        SqlPreparedStatement sqlPreparedStatement = (SqlPreparedStatement) obj;
        switch (i3) {
            case 0:
                return IdentitiesQueries.removeIdentity$lambda$5(str, sqlPreparedStatement);
            case 1:
                return JsonRpcHistoryQueries.deleteJsonRpcHistory$lambda$19(str, sqlPreparedStatement);
            case 2:
                return MetaDataQueries.deleteMetaDataFromTopic$lambda$9(str, sqlPreparedStatement);
            case 3:
                return PairingQueries.deletePairing$lambda$12(str, sqlPreparedStatement);
            default:
                return PushMessageQueries.deleteMessageByTopic$lambda$5(str, sqlPreparedStatement);
        }
    }
}
