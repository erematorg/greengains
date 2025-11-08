package com.reown.android.sdk.storage.data.dao;

import com.reown.android.internal.common.model.TransportType;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import java.util.List;
import kotlin.jvm.functions.Function6;

public final /* synthetic */ class g implements Function6 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7384a;

    public /* synthetic */ g(int i3) {
        this.f7384a = i3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        int i3 = this.f7384a;
        long longValue = ((Long) obj).longValue();
        String str = (String) obj2;
        switch (i3) {
            case 0:
                return JsonRpcHistoryQueries.getPendingSessionRequests$lambda$11(longValue, str, (String) obj3, (String) obj4, (String) obj5, (TransportType) obj6);
            case 1:
                return JsonRpcHistoryQueries.getJsonRpcRecordsByTopic$lambda$5(longValue, str, (String) obj3, (String) obj4, (String) obj5, (TransportType) obj6);
            case 2:
                return JsonRpcHistoryQueries.getJsonRpcHistoryRecord$lambda$2(longValue, str, (String) obj3, (String) obj4, (String) obj5, (TransportType) obj6);
            case 3:
                return JsonRpcHistoryQueries.getJsonRpcRecords$lambda$8(longValue, str, (String) obj3, (String) obj4, (String) obj5, (TransportType) obj6);
            default:
                return TempNamespaceDaoQueries.getTempNamespacesByRequestId$lambda$2(longValue, str, (List) obj3, (List) obj4, (List) obj5, (List) obj6);
        }
    }
}
