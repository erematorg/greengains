package com.reown.sign.storage.data.dao.optionalnamespaces;

import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
import kotlin.jvm.functions.Function4;

public final /* synthetic */ class b implements Function4 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7581a;

    public /* synthetic */ b(int i3) {
        this.f7581a = i3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        String str = (String) obj;
        List list = (List) obj2;
        List list2 = (List) obj3;
        List list3 = (List) obj4;
        switch (this.f7581a) {
            case 0:
                return OptionalNamespaceDaoQueries.getOptionalNamespaces$lambda$2(str, list, list2, list3);
            case 1:
                return ProposalNamespaceDaoQueries.getProposalNamespaces$lambda$2(str, list, list2, list3);
            case 2:
                return ProposalStorageRepository.getRequiredNamespaces$lambda$3(str, list, list2, list3);
            case 3:
                return ProposalStorageRepository.getOptionalNamespaces$lambda$4(str, list, list2, list3);
            case 4:
                return SessionStorageRepository.getOptionalNamespaces$lambda$14(str, list, list2, list3);
            default:
                return SessionStorageRepository.getRequiredNamespaces$lambda$13(str, list, list2, list3);
        }
    }
}
