package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import com.reown.android.sdk.storage.data.dao.IdentitiesQueries;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryQueries;
import com.reown.android.sdk.storage.data.dao.PairingQueries;
import com.reown.android.sdk.storage.data.dao.PushMessageQueries;
import com.reown.android.sdk.storage.data.dao.VerifyContextQueries;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7374a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7375b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f7374a = i3;
        this.f7375b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7374a;
        Object obj2 = this.f7375b;
        switch (i3) {
            case 0:
                return EventQueries.GetAllEventsWithLimitAndOffsetQuery.execute$lambda$0((EventQueries.GetAllEventsWithLimitAndOffsetQuery) obj2, (SqlPreparedStatement) obj);
            case 1:
                return IdentitiesQueries.GetAccountIdByIdentityQuery.execute$lambda$0((IdentitiesQueries.GetAccountIdByIdentityQuery) obj2, (SqlPreparedStatement) obj);
            case 2:
                return IdentitiesQueries.GetCacaoPayloadByIdentityQuery.execute$lambda$0((IdentitiesQueries.GetCacaoPayloadByIdentityQuery) obj2, (SqlPreparedStatement) obj);
            case 3:
                return JsonRpcHistoryQueries.DoesJsonRpcNotExistQuery.execute$lambda$0((JsonRpcHistoryQueries.DoesJsonRpcNotExistQuery) obj2, (SqlPreparedStatement) obj);
            case 4:
                return JsonRpcHistoryQueries.GetJsonRpcHistoryRecordQuery.execute$lambda$0((JsonRpcHistoryQueries.GetJsonRpcHistoryRecordQuery) obj2, (SqlPreparedStatement) obj);
            case 5:
                return JsonRpcHistoryQueries.GetJsonRpcRecordsByTopicQuery.execute$lambda$0((JsonRpcHistoryQueries.GetJsonRpcRecordsByTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 6:
                return PairingQueries.GetPairingByTopicQuery.execute$lambda$0((PairingQueries.GetPairingByTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 7:
                return PairingQueries.HasTopicQuery.execute$lambda$0((PairingQueries.HasTopicQuery) obj2, (SqlPreparedStatement) obj);
            case 8:
                return PushMessageQueries.DoesMessagesExistsByRequestIdQuery.execute$lambda$0((PushMessageQueries.DoesMessagesExistsByRequestIdQuery) obj2, (SqlPreparedStatement) obj);
            case 9:
                return PushMessageQueries.GetPushMessageByIdQuery.execute$lambda$0((PushMessageQueries.GetPushMessageByIdQuery) obj2, (SqlPreparedStatement) obj);
            case 10:
                return VerifyContextQueries.GetVerifyContextByIdQuery.execute$lambda$0((VerifyContextQueries.GetVerifyContextByIdQuery) obj2, (SqlPreparedStatement) obj);
            case 11:
                return EventQueries.deleteByIds$lambda$7((Collection) obj2, (SqlPreparedStatement) obj);
            case 12:
                return IdentitiesQueries.getCacaoPayloadByIdentity$lambda$1((Function1) obj2, (SqlCursor) obj);
            case 13:
                return PushMessageQueries.getPushMessageById$lambda$0((Function3) obj2, (SqlCursor) obj);
            default:
                return VerifyPublicKeyQueries.getKey$lambda$0((Function2) obj2, (SqlCursor) obj);
        }
    }
}
