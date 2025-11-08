package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import com.google.android.play.integrity.internal.q;

final class bb extends StandardIntegrityManager.StandardIntegrityToken {

    /* renamed from: a  reason: collision with root package name */
    private final String f6771a;

    /* renamed from: b  reason: collision with root package name */
    private final u f6772b;

    public bb(String str, q qVar, @Nullable PendingIntent pendingIntent) {
        this.f6771a = str;
        this.f6772b = new u(qVar, pendingIntent);
    }

    public final String token() {
        return this.f6771a;
    }
}
