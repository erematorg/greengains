package com.google.android.play.core.integrity;

import androidx.compose.ui.autofill.a;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import org.apache.commons.text.StringSubstitutor;

final class h extends StandardIntegrityManager.PrepareIntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final long f6784a;

    public /* synthetic */ h(long j2, g gVar) {
        this.f6784a = j2;
    }

    public final long a() {
        return this.f6784a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof StandardIntegrityManager.PrepareIntegrityTokenRequest) && this.f6784a == ((StandardIntegrityManager.PrepareIntegrityTokenRequest) obj).a();
    }

    public final int hashCode() {
        long j2 = this.f6784a;
        return ((int) (j2 ^ (j2 >>> 32))) ^ 1000003;
    }

    public final String toString() {
        return a.j("PrepareIntegrityTokenRequest{cloudProjectNumber=", this.f6784a, StringSubstitutor.DEFAULT_VAR_END);
    }
}
