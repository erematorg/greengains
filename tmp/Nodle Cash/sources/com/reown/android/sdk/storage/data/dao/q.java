package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;

public final /* synthetic */ class q implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7420a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function5 f7421b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ VerifyContextQueries f7422c;

    public /* synthetic */ q(Function5 function5, VerifyContextQueries verifyContextQueries, int i3) {
        this.f7420a = i3;
        this.f7421b = function5;
        this.f7422c = verifyContextQueries;
    }

    public final Object invoke(Object obj) {
        SqlCursor sqlCursor = (SqlCursor) obj;
        switch (this.f7420a) {
            case 0:
                return VerifyContextQueries.geListOfVerifyContexts$lambda$2(this.f7421b, this.f7422c, sqlCursor);
            default:
                return VerifyContextQueries.getVerifyContextById$lambda$0(this.f7421b, this.f7422c, sqlCursor);
        }
    }
}
