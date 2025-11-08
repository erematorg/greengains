package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;

final class f extends StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private long f6782a;

    /* renamed from: b  reason: collision with root package name */
    private byte f6783b;

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest build() {
        if (this.f6783b == 1) {
            return new h(this.f6782a, (g) null);
        }
        throw new IllegalStateException("Missing required properties: cloudProjectNumber");
    }

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder setCloudProjectNumber(long j2) {
        this.f6782a = j2;
        this.f6783b = 1;
        return this;
    }
}
