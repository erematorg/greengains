package com.reown.sign.storage.data.dao.temp;

import app.cash.sqldelight.db.SqlPreparedStatement;
import java.util.List;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f7622a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7623b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7624c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ List f7625d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ TempNamespaceDaoQueries f7626e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ List f7627f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ List f7628g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ List f7629h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f7630i;

    public /* synthetic */ a(long j2, String str, String str2, List list, TempNamespaceDaoQueries tempNamespaceDaoQueries, List list2, List list3, List list4, long j3) {
        this.f7622a = j2;
        this.f7623b = str;
        this.f7624c = str2;
        this.f7625d = list;
        this.f7626e = tempNamespaceDaoQueries;
        this.f7627f = list2;
        this.f7628g = list3;
        this.f7629h = list4;
        this.f7630i = j3;
    }

    public final Object invoke(Object obj) {
        List list = this.f7628g;
        List list2 = this.f7629h;
        return TempNamespaceDaoQueries.insertOrAbortNamespace$lambda$5(this.f7622a, this.f7623b, this.f7624c, this.f7625d, this.f7626e, this.f7627f, list, list2, this.f7630i, (SqlPreparedStatement) obj);
    }
}
