package com.reown.sign.storage.data.dao.session;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7620a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7621b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f7620a = i3;
        this.f7621b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7620a;
        Object obj2 = this.f7621b;
        switch (i3) {
            case 0:
                return SessionDaoQueries.GetAllSessionTopicsByPairingTopicQuery.execute$lambda$0((SessionDaoQueries.GetAllSessionTopicsByPairingTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 1:
                return SessionDaoQueries.GetExpiryQuery.execute$lambda$0((SessionDaoQueries.GetExpiryQuery) obj2, (SqlPreparedStatement) obj);
            case 2:
                return SessionDaoQueries.GetSessionByTopicQuery.execute$lambda$0((SessionDaoQueries.GetSessionByTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 3:
                return SessionDaoQueries.GetSessionIdByTopicQuery.execute$lambda$0((SessionDaoQueries.GetSessionIdByTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 4:
                return SessionDaoQueries.HasTopicQuery.execute$lambda$0((SessionDaoQueries.HasTopicQuery) obj2, (SqlPreparedStatement) obj);
            default:
                return SessionDaoQueries.deleteSession$lambda$24((String) obj2, (SqlPreparedStatement) obj);
        }
    }
}
