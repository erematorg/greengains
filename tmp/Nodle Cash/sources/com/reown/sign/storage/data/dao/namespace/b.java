package com.reown.sign.storage.data.dao.namespace;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7577a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7578b;

    public /* synthetic */ b(Object obj, int i3) {
        this.f7577a = i3;
        this.f7578b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7577a;
        Object obj2 = this.f7578b;
        switch (i3) {
            case 0:
                return NamespaceDaoQueries.GetNamespacesQuery.execute$lambda$0((NamespaceDaoQueries.GetNamespacesQuery) obj2, (SqlPreparedStatement) obj);
            case 1:
                return NamespaceDaoQueries.IsUpdateNamespaceRequestValidQuery.execute$lambda$0((NamespaceDaoQueries.IsUpdateNamespaceRequestValidQuery) obj2, (SqlPreparedStatement) obj);
            default:
                return NamespaceDaoQueries.deleteNamespacesByTopic$lambda$7((String) obj2, (SqlPreparedStatement) obj);
        }
    }
}
