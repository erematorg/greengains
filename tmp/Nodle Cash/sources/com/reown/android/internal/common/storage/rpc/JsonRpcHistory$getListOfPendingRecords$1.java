package com.reown.android.internal.common.storage.rpc;

import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.android.internal.common.model.TransportType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class JsonRpcHistory$getListOfPendingRecords$1 extends FunctionReferenceImpl implements Function6<Long, String, String, String, String, TransportType, JsonRpcHistoryRecord> {
    public JsonRpcHistory$getListOfPendingRecords$1(Object obj) {
        super(6, obj, JsonRpcHistory.class, "toRecord", "toRecord(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)Lcom/reown/android/internal/common/json_rpc/model/JsonRpcHistoryRecord;", 0);
    }

    public final JsonRpcHistoryRecord invoke(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, "p1");
        Intrinsics.checkNotNullParameter(str2, "p2");
        Intrinsics.checkNotNullParameter(str3, "p3");
        return ((JsonRpcHistory) this.receiver).toRecord(j2, str, str2, str3, str4, transportType);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return invoke(((Number) obj).longValue(), (String) obj2, (String) obj3, (String) obj4, (String) obj5, (TransportType) obj6);
    }
}
