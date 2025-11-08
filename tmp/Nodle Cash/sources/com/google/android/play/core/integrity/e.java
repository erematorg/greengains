package com.google.android.play.core.integrity;

import android.net.Network;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

final class e extends IntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final String f6780a;

    /* renamed from: b  reason: collision with root package name */
    private final Long f6781b;

    public /* synthetic */ e(String str, Long l2, Network network, d dVar) {
        this.f6780a = str;
        this.f6781b = l2;
    }

    @RequiresApi(api = 23)
    @Nullable
    public final Network a() {
        return null;
    }

    @Nullable
    public final Long cloudProjectNumber() {
        return this.f6781b;
    }

    public final boolean equals(Object obj) {
        Long l2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntegrityTokenRequest) {
            IntegrityTokenRequest integrityTokenRequest = (IntegrityTokenRequest) obj;
            if (this.f6780a.equals(integrityTokenRequest.nonce()) && ((l2 = this.f6781b) != null ? l2.equals(integrityTokenRequest.cloudProjectNumber()) : integrityTokenRequest.cloudProjectNumber() == null)) {
                integrityTokenRequest.a();
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f6780a.hashCode() ^ 1000003;
        Long l2 = this.f6781b;
        return ((l2 == null ? 0 : l2.hashCode()) ^ (hashCode * 1000003)) * 1000003;
    }

    public final String nonce() {
        return this.f6780a;
    }

    public final String toString() {
        String str = this.f6780a;
        Long l2 = this.f6781b;
        return "IntegrityTokenRequest{nonce=" + str + ", cloudProjectNumber=" + l2 + ", network=null}";
    }
}
