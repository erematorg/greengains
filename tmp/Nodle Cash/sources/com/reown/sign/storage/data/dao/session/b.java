package com.reown.sign.storage.data.dao.session;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function13;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7617a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function13 f7618b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ SessionDaoQueries f7619c;

    public /* synthetic */ b(Function13 function13, SessionDaoQueries sessionDaoQueries, int i3) {
        this.f7617a = i3;
        this.f7618b = function13;
        this.f7619c = sessionDaoQueries;
    }

    public final Object invoke(Object obj) {
        SqlCursor sqlCursor = (SqlCursor) obj;
        switch (this.f7617a) {
            case 0:
                return SessionDaoQueries.getListOfSessionDaos$lambda$4(this.f7618b, this.f7619c, sqlCursor);
            default:
                return SessionDaoQueries.getSessionByTopic$lambda$9(this.f7618b, this.f7619c, sqlCursor);
        }
    }
}
