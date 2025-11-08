package com.reown.android.sdk.storage.data.dao;

import com.reown.android.internal.common.model.Validation;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
import kotlin.jvm.functions.Function5;

public final /* synthetic */ class p implements Function5 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7419a;

    public /* synthetic */ p(int i3) {
        this.f7419a = i3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        switch (this.f7419a) {
            case 0:
                return VerifyContextQueries.geListOfVerifyContexts$lambda$3(((Long) obj).longValue(), (String) obj2, (Validation) obj3, (String) obj4, (Boolean) obj5);
            case 1:
                return VerifyContextQueries.getVerifyContextById$lambda$1(((Long) obj).longValue(), (String) obj2, (Validation) obj3, (String) obj4, (Boolean) obj5);
            case 2:
                return NamespaceDaoQueries.getNamespaces$lambda$2((String) obj, (List) obj2, (List) obj3, (List) obj4, (List) obj5);
            default:
                return SessionStorageRepository.getSessionNamespaces$lambda$15((String) obj, (List) obj2, (List) obj3, (List) obj4, (List) obj5);
        }
    }
}
