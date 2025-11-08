package com.reown.sign.storage.data.dao.proposal;

import com.reown.android.internal.common.model.TransportType;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function13;

public final /* synthetic */ class b implements Function13 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7585a;

    public /* synthetic */ b(int i3) {
        this.f7585a = i3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        switch (this.f7585a) {
            case 0:
                return ProposalDaoQueries.getProposalByPairingTopic$lambda$11(((Long) obj).longValue(), (String) obj2, (String) obj3, (String) obj4, (String) obj5, (List) obj6, (String) obj7, (String) obj8, (String) obj9, (Map) obj10, (String) obj11, (Long) obj12, (Map) obj13);
            case 1:
                return ProposalDaoQueries.getProposalByKey$lambda$3(((Long) obj).longValue(), (String) obj2, (String) obj3, (String) obj4, (String) obj5, (List) obj6, (String) obj7, (String) obj8, (String) obj9, (Map) obj10, (String) obj11, (Long) obj12, (Map) obj13);
            case 2:
                return ProposalDaoQueries.getListOfProposalDaos$lambda$7(((Long) obj).longValue(), (String) obj2, (String) obj3, (String) obj4, (String) obj5, (List) obj6, (String) obj7, (String) obj8, (String) obj9, (Map) obj10, (String) obj11, (Long) obj12, (Map) obj13);
            case 3:
                return SessionDaoQueries.getSessionByTopic$lambda$10(((Long) obj).longValue(), (String) obj2, ((Long) obj3).longValue(), (String) obj4, (String) obj5, (String) obj6, (String) obj7, (String) obj8, ((Boolean) obj9).booleanValue(), (String) obj10, (Map) obj11, (TransportType) obj12, (Map) obj13);
            default:
                return SessionDaoQueries.getListOfSessionDaos$lambda$5(((Long) obj).longValue(), (String) obj2, ((Long) obj3).longValue(), (String) obj4, (String) obj5, (String) obj6, (String) obj7, (String) obj8, ((Boolean) obj9).booleanValue(), (String) obj10, (Map) obj11, (TransportType) obj12, (Map) obj13);
        }
    }
}
