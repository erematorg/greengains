package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import com.google.android.play.integrity.internal.q;

final class b extends ba {

    /* renamed from: a  reason: collision with root package name */
    private String f6768a;

    /* renamed from: b  reason: collision with root package name */
    private q f6769b;

    /* renamed from: c  reason: collision with root package name */
    private PendingIntent f6770c;

    public final ba a(PendingIntent pendingIntent) {
        this.f6770c = pendingIntent;
        return this;
    }

    public final ba b(q qVar) {
        if (qVar != null) {
            this.f6769b = qVar;
            return this;
        }
        throw new NullPointerException("Null logger");
    }

    public final ba c(String str) {
        if (str != null) {
            this.f6768a = str;
            return this;
        }
        throw new NullPointerException("Null token");
    }

    public final bb d() {
        q qVar;
        String str = this.f6768a;
        if (str != null && (qVar = this.f6769b) != null) {
            return new bb(str, qVar, this.f6770c);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f6768a == null) {
            sb.append(" token");
        }
        if (this.f6769b == null) {
            sb.append(" logger");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
