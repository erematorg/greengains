package com.reown.sign.storage.data.dao.proposal;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function13;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7582a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function13 f7583b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ProposalDaoQueries f7584c;

    public /* synthetic */ a(Function13 function13, ProposalDaoQueries proposalDaoQueries, int i3) {
        this.f7582a = i3;
        this.f7583b = function13;
        this.f7584c = proposalDaoQueries;
    }

    public final Object invoke(Object obj) {
        SqlCursor sqlCursor = (SqlCursor) obj;
        switch (this.f7582a) {
            case 0:
                return ProposalDaoQueries.getProposalByPairingTopic$lambda$10(this.f7583b, this.f7584c, sqlCursor);
            case 1:
                return ProposalDaoQueries.getProposalByKey$lambda$2(this.f7583b, this.f7584c, sqlCursor);
            default:
                return ProposalDaoQueries.getListOfProposalDaos$lambda$6(this.f7583b, this.f7584c, sqlCursor);
        }
    }
}
