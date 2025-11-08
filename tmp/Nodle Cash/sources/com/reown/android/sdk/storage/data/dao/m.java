package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class m implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f7407a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f7408b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7409c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7410d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ String f7411e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ String f7412f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ boolean f7413g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Boolean f7414h;

    public /* synthetic */ m(String str, long j2, String str2, String str3, String str4, String str5, boolean z2, Boolean bool) {
        this.f7407a = str;
        this.f7408b = j2;
        this.f7409c = str2;
        this.f7410d = str3;
        this.f7411e = str4;
        this.f7412f = str5;
        this.f7413g = z2;
        this.f7414h = bool;
    }

    public final Object invoke(Object obj) {
        String str = this.f7412f;
        boolean z2 = this.f7413g;
        return PairingQueries.insertOrAbortPairing$lambda$10(this.f7407a, this.f7408b, this.f7409c, this.f7410d, this.f7411e, str, z2, this.f7414h, (SqlPreparedStatement) obj);
    }
}
