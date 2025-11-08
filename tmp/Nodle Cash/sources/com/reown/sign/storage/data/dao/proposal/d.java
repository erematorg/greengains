package com.reown.sign.storage.data.dao.proposal;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.sign.storage.data.dao.proposal.ProposalDaoQueries;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7600a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7601b;

    public /* synthetic */ d(Object obj, int i3) {
        this.f7600a = i3;
        this.f7601b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7600a;
        Object obj2 = this.f7601b;
        switch (i3) {
            case 0:
                return ProposalDaoQueries.GetProposalByKeyQuery.execute$lambda$0((ProposalDaoQueries.GetProposalByKeyQuery) obj2, (SqlPreparedStatement) obj);
            case 1:
                return ProposalDaoQueries.GetProposalByPairingTopicQuery.execute$lambda$0((ProposalDaoQueries.GetProposalByPairingTopicQuery) obj2, (SqlPreparedStatement) obj);
            default:
                return ProposalDaoQueries.deleteProposal$lambda$16((String) obj2, (SqlPreparedStatement) obj);
        }
    }
}
