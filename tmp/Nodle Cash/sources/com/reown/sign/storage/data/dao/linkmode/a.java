package com.reown.sign.storage.data.dao.linkmode;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.linkmode.LinkModeDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7567a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7568b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7567a = i3;
        this.f7568b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7567a;
        Object obj2 = this.f7568b;
        switch (i3) {
            case 0:
                return LinkModeDaoQueries.IsEnabledQuery.execute$lambda$0((LinkModeDaoQueries.IsEnabledQuery) obj2, (SqlPreparedStatement) obj);
            default:
                return LinkModeDaoQueries.insertOrIgnore$lambda$1((String) obj2, (SqlPreparedStatement) obj);
        }
    }
}
