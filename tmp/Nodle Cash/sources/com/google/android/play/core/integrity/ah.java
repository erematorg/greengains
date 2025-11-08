package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.google.android.play.integrity.internal.q;

final class ah extends IntegrityTokenResponse {

    /* renamed from: a  reason: collision with root package name */
    private final String f6735a;

    /* renamed from: b  reason: collision with root package name */
    private final u f6736b;

    public ah(String str, q qVar, @Nullable PendingIntent pendingIntent) {
        this.f6735a = str;
        this.f6736b = new u(qVar, pendingIntent);
    }

    public final String token() {
        return this.f6735a;
    }
}
