package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import java.util.List;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Long f7362a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7363b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f7364c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7365d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ String f7366e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ String f7367f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ List f7368g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Long f7369h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f7370i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ String f7371j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ String f7372k;

    /* renamed from: l  reason: collision with root package name */
    public final /* synthetic */ EventQueries f7373l;

    public /* synthetic */ a(Long l2, String str, long j2, String str2, String str3, String str4, List list, Long l3, String str5, String str6, String str7, EventQueries eventQueries) {
        this.f7362a = l2;
        this.f7363b = str;
        this.f7364c = j2;
        this.f7365d = str2;
        this.f7366e = str3;
        this.f7367f = str4;
        this.f7368g = list;
        this.f7369h = l3;
        this.f7370i = str5;
        this.f7371j = str6;
        this.f7372k = str7;
        this.f7373l = eventQueries;
    }

    public final Object invoke(Object obj) {
        String str = this.f7371j;
        String str2 = this.f7372k;
        return EventQueries.insertOrAbort$lambda$4(this.f7362a, this.f7363b, this.f7364c, this.f7365d, this.f7366e, this.f7367f, this.f7368g, this.f7369h, this.f7370i, str, str2, this.f7373l, (SqlPreparedStatement) obj);
    }
}
