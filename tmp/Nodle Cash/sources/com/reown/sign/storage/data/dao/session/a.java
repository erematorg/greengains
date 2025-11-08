package com.reown.sign.storage.data.dao.session;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.model.TransportType;
import java.util.Map;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f7604a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7605b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f7606c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7607d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ String f7608e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ String f7609f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ String f7610g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f7611h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f7612i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Map f7613j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ TransportType f7614k;

    /* renamed from: l  reason: collision with root package name */
    public final /* synthetic */ Map f7615l;

    /* renamed from: m  reason: collision with root package name */
    public final /* synthetic */ SessionDaoQueries f7616m;

    public /* synthetic */ a(String str, String str2, long j2, String str3, String str4, String str5, String str6, String str7, boolean z2, Map map, TransportType transportType, Map map2, SessionDaoQueries sessionDaoQueries) {
        this.f7604a = str;
        this.f7605b = str2;
        this.f7606c = j2;
        this.f7607d = str3;
        this.f7608e = str4;
        this.f7609f = str5;
        this.f7610g = str6;
        this.f7611h = str7;
        this.f7612i = z2;
        this.f7613j = map;
        this.f7614k = transportType;
        this.f7615l = map2;
        this.f7616m = sessionDaoQueries;
    }

    public final Object invoke(Object obj) {
        TransportType transportType = this.f7614k;
        Map map = this.f7615l;
        String str = this.f7604a;
        String str2 = this.f7605b;
        long j2 = this.f7606c;
        String str3 = this.f7607d;
        String str4 = this.f7608e;
        String str5 = this.f7609f;
        String str6 = this.f7610g;
        String str7 = this.f7611h;
        boolean z2 = this.f7612i;
        Map map2 = this.f7613j;
        SessionDaoQueries sessionDaoQueries = this.f7616m;
        return SessionDaoQueries.insertOrAbortSession$lambda$18(str, str2, j2, str3, str4, str5, str6, str7, z2, map2, transportType, map, sessionDaoQueries, (SqlPreparedStatement) obj);
    }
}
