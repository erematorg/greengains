package com.reown.sign.storage.data.dao.proposal;

import app.cash.sqldelight.db.SqlPreparedStatement;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Long f7586a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7587b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7588c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7589d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ String f7590e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ ProposalDaoQueries f7591f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ List f7592g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f7593h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f7594i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ String f7595j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ Map f7596k;

    /* renamed from: l  reason: collision with root package name */
    public final /* synthetic */ String f7597l;

    /* renamed from: m  reason: collision with root package name */
    public final /* synthetic */ Long f7598m;

    /* renamed from: n  reason: collision with root package name */
    public final /* synthetic */ Map f7599n;

    public /* synthetic */ c(Long l2, String str, String str2, String str3, String str4, ProposalDaoQueries proposalDaoQueries, List list, String str5, String str6, String str7, Map map, String str8, Long l3, Map map2) {
        this.f7586a = l2;
        this.f7587b = str;
        this.f7588c = str2;
        this.f7589d = str3;
        this.f7590e = str4;
        this.f7591f = proposalDaoQueries;
        this.f7592g = list;
        this.f7593h = str5;
        this.f7594i = str6;
        this.f7595j = str7;
        this.f7596k = map;
        this.f7597l = str8;
        this.f7598m = l3;
        this.f7599n = map2;
    }

    public final Object invoke(Object obj) {
        String str = this.f7597l;
        Long l2 = this.f7598m;
        Long l3 = this.f7586a;
        String str2 = this.f7587b;
        String str3 = this.f7588c;
        String str4 = this.f7589d;
        String str5 = this.f7590e;
        ProposalDaoQueries proposalDaoQueries = this.f7591f;
        List list = this.f7592g;
        String str6 = this.f7593h;
        String str7 = this.f7594i;
        String str8 = this.f7595j;
        Map map = this.f7596k;
        Map map2 = this.f7599n;
        return ProposalDaoQueries.insertOrAbortSession$lambda$14(l3, str2, str3, str4, str5, proposalDaoQueries, list, str6, str7, str8, map, str, l2, map2, (SqlPreparedStatement) obj);
    }
}
