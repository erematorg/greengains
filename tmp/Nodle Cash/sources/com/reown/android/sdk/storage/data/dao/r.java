package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.model.Validation;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class r implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Long f7423a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7424b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ VerifyContextQueries f7425c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Validation f7426d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ String f7427e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Boolean f7428f;

    public /* synthetic */ r(Long l2, String str, VerifyContextQueries verifyContextQueries, Validation validation, String str2, Boolean bool) {
        this.f7423a = l2;
        this.f7424b = str;
        this.f7425c = verifyContextQueries;
        this.f7426d = validation;
        this.f7427e = str2;
        this.f7428f = bool;
    }

    public final Object invoke(Object obj) {
        Validation validation = this.f7426d;
        String str = this.f7427e;
        return VerifyContextQueries.insertOrAbortVerifyContext$lambda$4(this.f7423a, this.f7424b, this.f7425c, validation, str, this.f7428f, (SqlPreparedStatement) obj);
    }
}
