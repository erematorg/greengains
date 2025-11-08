package com.google.android.play.core.integrity;

import A.a;
import androidx.annotation.Nullable;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import org.apache.commons.text.StringSubstitutor;

final class k extends StandardIntegrityManager.StandardIntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final String f6786a;

    public /* synthetic */ k(String str, j jVar) {
        this.f6786a = str;
    }

    @Nullable
    public final String a() {
        return this.f6786a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardIntegrityManager.StandardIntegrityTokenRequest)) {
            return false;
        }
        StandardIntegrityManager.StandardIntegrityTokenRequest standardIntegrityTokenRequest = (StandardIntegrityManager.StandardIntegrityTokenRequest) obj;
        String str = this.f6786a;
        return str == null ? standardIntegrityTokenRequest.a() == null : str.equals(standardIntegrityTokenRequest.a());
    }

    public final int hashCode() {
        String str = this.f6786a;
        return (str == null ? 0 : str.hashCode()) ^ 1000003;
    }

    public final String toString() {
        return a.l("StandardIntegrityTokenRequest{requestHash=", this.f6786a, StringSubstitutor.DEFAULT_VAR_END);
    }
}
