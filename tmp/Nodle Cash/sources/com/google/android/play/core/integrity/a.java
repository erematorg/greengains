package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import com.google.android.play.integrity.internal.q;

final class a extends ag {

    /* renamed from: a  reason: collision with root package name */
    private String f6717a;

    /* renamed from: b  reason: collision with root package name */
    private q f6718b;

    /* renamed from: c  reason: collision with root package name */
    private PendingIntent f6719c;

    public final ag a(PendingIntent pendingIntent) {
        this.f6719c = pendingIntent;
        return this;
    }

    public final ag b(q qVar) {
        if (qVar != null) {
            this.f6718b = qVar;
            return this;
        }
        throw new NullPointerException("Null logger");
    }

    public final ag c(String str) {
        this.f6717a = str;
        return this;
    }

    public final ah d() {
        q qVar;
        String str = this.f6717a;
        if (str != null && (qVar = this.f6718b) != null) {
            return new ah(str, qVar, this.f6719c);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f6717a == null) {
            sb.append(" token");
        }
        if (this.f6718b == null) {
            sb.append(" logger");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
