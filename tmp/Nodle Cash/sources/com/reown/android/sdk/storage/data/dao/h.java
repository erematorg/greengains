package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.model.TransportType;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import java.util.List;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class h implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7385a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f7386b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7387c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7388d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7389e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Object f7390f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ Object f7391g;

    public /* synthetic */ h(long j2, String str, Object obj, Object obj2, Object obj3, Object obj4, int i3) {
        this.f7385a = i3;
        this.f7386b = j2;
        this.f7387c = str;
        this.f7388d = obj;
        this.f7389e = obj2;
        this.f7390f = obj3;
        this.f7391g = obj4;
    }

    public final Object invoke(Object obj) {
        switch (this.f7385a) {
            case 0:
                String str = (String) this.f7389e;
                TransportType transportType = (TransportType) this.f7390f;
                return JsonRpcHistoryQueries.insertOrAbortJsonRpcHistory$lambda$15(this.f7386b, this.f7387c, (String) this.f7388d, str, transportType, (JsonRpcHistoryQueries) this.f7391g, (SqlPreparedStatement) obj);
            case 1:
                OptionalNamespaceDaoQueries optionalNamespaceDaoQueries = (OptionalNamespaceDaoQueries) this.f7389e;
                List list = (List) this.f7390f;
                return OptionalNamespaceDaoQueries.insertOrAbortOptionalNamespace$lambda$4(this.f7386b, this.f7387c, (List) this.f7388d, optionalNamespaceDaoQueries, list, (List) this.f7391g, (SqlPreparedStatement) obj);
            default:
                ProposalNamespaceDaoQueries proposalNamespaceDaoQueries = (ProposalNamespaceDaoQueries) this.f7389e;
                List list2 = (List) this.f7390f;
                return ProposalNamespaceDaoQueries.insertOrAbortProposalNamespace$lambda$4(this.f7386b, this.f7387c, (List) this.f7388d, proposalNamespaceDaoQueries, list2, (List) this.f7391g, (SqlPreparedStatement) obj);
        }
    }
}
