package com.reown.android.sdk.storage.data.dao;

import java.util.List;
import kotlin.jvm.functions.Function12;

public final /* synthetic */ class n implements Function12 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7415a;

    public /* synthetic */ n(int i3) {
        this.f7415a = i3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        switch (this.f7415a) {
            case 0:
                return PairingQueries.getListOfPairing$lambda$2((String) obj, ((Long) obj2).longValue(), (String) obj3, (String) obj4, (String) obj5, (String) obj6, (Boolean) obj7, (String) obj8, (String) obj9, (String) obj10, (List) obj11, (String) obj12);
            case 1:
                return PairingQueries.getPairingByTopic$lambda$8((String) obj, ((Long) obj2).longValue(), (String) obj3, (String) obj4, (String) obj5, (String) obj6, (Boolean) obj7, (String) obj8, (String) obj9, (String) obj10, (List) obj11, (String) obj12);
            default:
                return PairingQueries.getListOfPairingsWithoutRequestReceived$lambda$5((String) obj, ((Long) obj2).longValue(), (String) obj3, (String) obj4, (String) obj5, (String) obj6, (Boolean) obj7, (String) obj8, (String) obj9, (String) obj10, (List) obj11, (String) obj12);
        }
    }
}
