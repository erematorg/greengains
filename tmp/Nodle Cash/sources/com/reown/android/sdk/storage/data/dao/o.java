package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function12;

public final /* synthetic */ class o implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7416a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function12 f7417b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ PairingQueries f7418c;

    public /* synthetic */ o(Function12 function12, PairingQueries pairingQueries, int i3) {
        this.f7416a = i3;
        this.f7417b = function12;
        this.f7418c = pairingQueries;
    }

    public final Object invoke(Object obj) {
        SqlCursor sqlCursor = (SqlCursor) obj;
        switch (this.f7416a) {
            case 0:
                return PairingQueries.getPairingByTopic$lambda$7(this.f7417b, this.f7418c, sqlCursor);
            case 1:
                return PairingQueries.getListOfPairingsWithoutRequestReceived$lambda$4(this.f7417b, this.f7418c, sqlCursor);
            default:
                return PairingQueries.getListOfPairing$lambda$1(this.f7417b, this.f7418c, sqlCursor);
        }
    }
}
