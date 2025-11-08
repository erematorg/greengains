package com.google.android.play.core.integrity;

import androidx.annotation.Nullable;
import com.google.android.play.core.integrity.StandardIntegrityManager;

final class i extends StandardIntegrityManager.StandardIntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private String f6785a;

    public final StandardIntegrityManager.StandardIntegrityTokenRequest build() {
        return new k(this.f6785a, (j) null);
    }

    public final StandardIntegrityManager.StandardIntegrityTokenRequest.Builder setRequestHash(@Nullable String str) {
        this.f6785a = str;
        return this;
    }
}
