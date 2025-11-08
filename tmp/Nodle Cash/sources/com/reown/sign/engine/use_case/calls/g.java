package com.reown.sign.engine.use_case.calls;

import app.cash.sqldelight.db.SqlCursor;
import com.reown.android.Core;
import com.reown.sign.storage.data.dao.linkmode.LinkModeDaoQueries;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.proposal.ProposalDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import kotlin.Result;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class g implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7512a;

    public /* synthetic */ g(int i3) {
        this.f7512a = i3;
    }

    public final Object invoke(Object obj) {
        switch (this.f7512a) {
            case 0:
                return ApproveSessionUseCaseInterface.approve$lambda$1((Throwable) obj);
            case 1:
                return GetPairingForSessionAuthenticateUseCase.invoke$lambda$1((Core.Model.Error) obj);
            case 2:
                return PingUseCase.collectResponse$lambda$0((Result) obj);
            case 3:
                return RejectSessionUseCaseInterface.reject$lambda$0((Throwable) obj);
            case 4:
                return SessionRequestUseCase.collectResponse$lambda$5((Result) obj);
            case 5:
                return LinkModeDaoQueries.insertOrIgnore$lambda$2((Function1) obj);
            case 6:
                return LinkModeDaoQueries.isEnabled$lambda$0((SqlCursor) obj);
            case 7:
                return NamespaceDaoQueries.insertOrAbortNamespace$lambda$6((Function1) obj);
            case 8:
                return Boolean.valueOf(NamespaceDaoQueries.isUpdateNamespaceRequestValid$lambda$3((SqlCursor) obj));
            case 9:
                return NamespaceDaoQueries.deleteNamespacesByTopic$lambda$8((Function1) obj);
            case 10:
                return OptionalNamespaceDaoQueries.deleteOptionalNamespacesByTopic$lambda$7((Function1) obj);
            case 11:
                return OptionalNamespaceDaoQueries.insertOrAbortOptionalNamespace$lambda$5((Function1) obj);
            case 12:
                return OptionalNamespaceDaoQueries.deleteProposalNamespacesProposerKey$lambda$9((Function1) obj);
            case 13:
                return ProposalDaoQueries.deleteProposal$lambda$17((Function1) obj);
            case 14:
                return ProposalDaoQueries.insertOrAbortSession$lambda$15((Function1) obj);
            case 15:
                return ProposalNamespaceDaoQueries.deleteProposalNamespacesProposerKey$lambda$9((Function1) obj);
            case 16:
                return ProposalNamespaceDaoQueries.deleteProposalNamespacesByTopic$lambda$7((Function1) obj);
            case 17:
                return ProposalNamespaceDaoQueries.insertOrAbortProposalNamespace$lambda$5((Function1) obj);
            case 18:
                return Long.valueOf(SessionDaoQueries.getSessionIdByTopic$lambda$11((SqlCursor) obj));
            case 19:
                return SessionDaoQueries.acknowledgeSession$lambda$21((Function1) obj);
            case 20:
                return SessionDaoQueries.insertOrAbortSession$lambda$19((Function1) obj);
            case 21:
                return SessionDaoQueries.getAllSessionTopicsByPairingTopic$lambda$12((SqlCursor) obj);
            case 22:
                return Long.valueOf(SessionDaoQueries.lastInsertedRow$lambda$0((SqlCursor) obj));
            case 23:
                return SessionDaoQueries.hasTopic$lambda$13((SqlCursor) obj);
            case 24:
                return SessionDaoQueries.updateSessionExpiry$lambda$23((Function1) obj);
            case 25:
                return SessionDaoQueries.deleteSession$lambda$25((Function1) obj);
            case 26:
                return Long.valueOf(SessionDaoQueries.getExpiry$lambda$14((SqlCursor) obj));
            case 27:
                return Boolean.valueOf(TempNamespaceDaoQueries.isUpdateNamespaceRequestValid$lambda$3((SqlCursor) obj));
            case 28:
                return TempNamespaceDaoQueries.markNamespaceAcknowledged$lambda$8((Function1) obj);
            default:
                return TempNamespaceDaoQueries.insertOrAbortNamespace$lambda$6((Function1) obj);
        }
    }
}
