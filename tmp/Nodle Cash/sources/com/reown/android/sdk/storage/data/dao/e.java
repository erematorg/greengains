package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;

public final /* synthetic */ class e implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7378a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function6 f7379b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ JsonRpcHistoryQueries f7380c;

    public /* synthetic */ e(Function6 function6, JsonRpcHistoryQueries jsonRpcHistoryQueries, int i3) {
        this.f7378a = i3;
        this.f7379b = function6;
        this.f7380c = jsonRpcHistoryQueries;
    }

    public final Object invoke(Object obj) {
        SqlCursor sqlCursor = (SqlCursor) obj;
        switch (this.f7378a) {
            case 0:
                return JsonRpcHistoryQueries.getJsonRpcRecords$lambda$7(this.f7379b, this.f7380c, sqlCursor);
            case 1:
                return JsonRpcHistoryQueries.getPendingSessionRequests$lambda$10(this.f7379b, this.f7380c, sqlCursor);
            case 2:
                return JsonRpcHistoryQueries.getJsonRpcHistoryRecord$lambda$1(this.f7379b, this.f7380c, sqlCursor);
            default:
                return JsonRpcHistoryQueries.getJsonRpcRecordsByTopic$lambda$4(this.f7379b, this.f7380c, sqlCursor);
        }
    }
}
