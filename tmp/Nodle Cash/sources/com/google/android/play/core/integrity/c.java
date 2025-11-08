package com.google.android.play.core.integrity;

import android.net.Network;
import com.google.android.play.core.integrity.IntegrityTokenRequest;

final class c extends IntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private String f6778a;

    /* renamed from: b  reason: collision with root package name */
    private Long f6779b;

    public final IntegrityTokenRequest build() {
        String str = this.f6778a;
        if (str != null) {
            return new e(str, this.f6779b, (Network) null, (d) null);
        }
        throw new IllegalStateException("Missing required properties: nonce");
    }

    public final IntegrityTokenRequest.Builder setCloudProjectNumber(long j2) {
        this.f6779b = Long.valueOf(j2);
        return this;
    }

    public final IntegrityTokenRequest.Builder setNonce(String str) {
        if (str != null) {
            this.f6778a = str;
            return this;
        }
        throw new NullPointerException("Null nonce");
    }
}
