package com.reown.sign.storage.data.dao.temp;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7632a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7633b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f7632a = i3;
        this.f7633b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7632a;
        Object obj2 = this.f7633b;
        switch (i3) {
            case 0:
                return TempNamespaceDaoQueries.GetTempNamespacesByRequestIdQuery.execute$lambda$0((TempNamespaceDaoQueries.GetTempNamespacesByRequestIdQuery) obj2, (SqlPreparedStatement) obj);
            case 1:
                return TempNamespaceDaoQueries.IsUpdateNamespaceRequestValidQuery.execute$lambda$0((TempNamespaceDaoQueries.IsUpdateNamespaceRequestValidQuery) obj2, (SqlPreparedStatement) obj);
            default:
                return TempNamespaceDaoQueries.deleteTempNamespacesByTopic$lambda$11((String) obj2, (SqlPreparedStatement) obj);
        }
    }
}
