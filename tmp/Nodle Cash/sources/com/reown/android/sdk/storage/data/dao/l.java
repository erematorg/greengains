package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.sdk.storage.data.dao.MetaDataQueries;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function7;

public final /* synthetic */ class l implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7404a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7405b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7406c;

    public /* synthetic */ l(Object obj, Object obj2, int i3) {
        this.f7404a = i3;
        this.f7406c = obj;
        this.f7405b = obj2;
    }

    public final Object invoke(Object obj) {
        switch (this.f7404a) {
            case 0:
                return MetaDataQueries.GetIdByTopicAndTypeQuery.execute$lambda$0((MetaDataQueries.GetIdByTopicAndTypeQuery) this.f7406c, (MetaDataQueries) this.f7405b, (SqlPreparedStatement) obj);
            case 1:
                return MetaDataQueries.GetMetadataByTopicAndTypeQuery.execute$lambda$0((MetaDataQueries.GetMetadataByTopicAndTypeQuery) this.f7406c, (MetaDataQueries) this.f7405b, (SqlPreparedStatement) obj);
            case 2:
                return EventQueries.getAllEventsWithLimitAndOffset$lambda$1((Function11) this.f7406c, (EventQueries) this.f7405b, (SqlCursor) obj);
            case 3:
                return MetaDataQueries.getMetadataByTopicAndType$lambda$0((Function7) this.f7406c, (MetaDataQueries) this.f7405b, (SqlCursor) obj);
            case 4:
                return MetaDataQueries.updateOrAbortMetaDataTopic$lambda$7((String) this.f7406c, (String) this.f7405b, (SqlPreparedStatement) obj);
            default:
                return PairingQueries.setRequestReceived$lambda$14((Boolean) this.f7406c, (String) this.f7405b, (SqlPreparedStatement) obj);
        }
    }
}
