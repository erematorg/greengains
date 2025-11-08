package com.reown.sign.storage.data.dao.optionalnamespaces;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7579a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7580b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7579a = i3;
        this.f7580b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7579a;
        Object obj2 = this.f7580b;
        switch (i3) {
            case 0:
                return OptionalNamespaceDaoQueries.deleteOptionalNamespacesByTopic$lambda$6((String) obj2, (SqlPreparedStatement) obj);
            case 1:
                return OptionalNamespaceDaoQueries.deleteProposalNamespacesProposerKey$lambda$8((String) obj2, (SqlPreparedStatement) obj);
            default:
                return OptionalNamespaceDaoQueries.GetOptionalNamespacesQuery.execute$lambda$0((OptionalNamespaceDaoQueries.GetOptionalNamespacesQuery) obj2, (SqlPreparedStatement) obj);
        }
    }
}
