package com.reown.sign.storage.data.dao.proposalnamespace;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7602a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7603b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7602a = i3;
        this.f7603b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7602a;
        Object obj2 = this.f7603b;
        switch (i3) {
            case 0:
                return ProposalNamespaceDaoQueries.deleteProposalNamespacesProposerKey$lambda$8((String) obj2, (SqlPreparedStatement) obj);
            case 1:
                return ProposalNamespaceDaoQueries.deleteProposalNamespacesByTopic$lambda$6((String) obj2, (SqlPreparedStatement) obj);
            default:
                return ProposalNamespaceDaoQueries.GetProposalNamespacesQuery.execute$lambda$0((ProposalNamespaceDaoQueries.GetProposalNamespacesQuery) obj2, (SqlPreparedStatement) obj);
        }
    }
}
