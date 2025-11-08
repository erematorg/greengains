package com.reown.sign.storage.data.dao.namespace;

import app.cash.sqldelight.db.SqlPreparedStatement;
import java.util.List;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f7569a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7570b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ List f7571c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ NamespaceDaoQueries f7572d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ List f7573e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ List f7574f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ List f7575g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f7576h;

    public /* synthetic */ a(long j2, String str, List list, NamespaceDaoQueries namespaceDaoQueries, List list2, List list3, List list4, long j3) {
        this.f7569a = j2;
        this.f7570b = str;
        this.f7571c = list;
        this.f7572d = namespaceDaoQueries;
        this.f7573e = list2;
        this.f7574f = list3;
        this.f7575g = list4;
        this.f7576h = j3;
    }

    public final Object invoke(Object obj) {
        List list = this.f7574f;
        List list2 = this.f7575g;
        return NamespaceDaoQueries.insertOrAbortNamespace$lambda$5(this.f7569a, this.f7570b, this.f7571c, this.f7572d, this.f7573e, list, list2, this.f7576h, (SqlPreparedStatement) obj);
    }
}
