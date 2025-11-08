package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.model.AppMetaDataType;
import java.util.List;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class j implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7392a = 1;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7393b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7394c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7395d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ MetaDataQueries f7396e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ List f7397f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ String f7398g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ AppMetaDataType f7399h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f7400i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Boolean f7401j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ String f7402k;

    public /* synthetic */ j(String str, String str2, String str3, MetaDataQueries metaDataQueries, List list, String str4, AppMetaDataType appMetaDataType, String str5, Boolean bool, String str6) {
        this.f7393b = str;
        this.f7394c = str2;
        this.f7395d = str3;
        this.f7396e = metaDataQueries;
        this.f7397f = list;
        this.f7398g = str4;
        this.f7399h = appMetaDataType;
        this.f7400i = str5;
        this.f7401j = bool;
        this.f7402k = str6;
    }

    public final Object invoke(Object obj) {
        switch (this.f7392a) {
            case 0:
                AppMetaDataType appMetaDataType = this.f7399h;
                String str = this.f7402k;
                String str2 = this.f7393b;
                String str3 = this.f7394c;
                String str4 = this.f7395d;
                String str5 = this.f7398g;
                return MetaDataQueries.insertOrAbortMetaData$lambda$3(str2, str3, str4, str5, this.f7396e, this.f7397f, this.f7400i, appMetaDataType, str, this.f7401j, (SqlPreparedStatement) obj);
            default:
                String str6 = this.f7400i;
                Boolean bool = this.f7401j;
                return MetaDataQueries.updateMetaData$lambda$5(this.f7393b, this.f7394c, this.f7395d, this.f7396e, this.f7397f, this.f7398g, this.f7399h, str6, bool, this.f7402k, (SqlPreparedStatement) obj);
        }
    }

    public /* synthetic */ j(String str, String str2, String str3, String str4, MetaDataQueries metaDataQueries, List list, String str5, AppMetaDataType appMetaDataType, String str6, Boolean bool) {
        this.f7393b = str;
        this.f7394c = str2;
        this.f7395d = str3;
        this.f7398g = str4;
        this.f7396e = metaDataQueries;
        this.f7397f = list;
        this.f7400i = str5;
        this.f7399h = appMetaDataType;
        this.f7402k = str6;
        this.f7401j = bool;
    }
}
