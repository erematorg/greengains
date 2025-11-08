package com.google.android.datatransport.runtime.backends;

import android.support.v4.media.session.a;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final BackendResponse.Status status;

    public AutoValue_BackendResponse(BackendResponse.Status status2, long j2) {
        if (status2 != null) {
            this.status = status2;
            this.nextRequestWaitMillis = j2;
            return;
        }
        throw new NullPointerException("Null status");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendResponse)) {
            return false;
        }
        BackendResponse backendResponse = (BackendResponse) obj;
        return this.status.equals(backendResponse.getStatus()) && this.nextRequestWaitMillis == backendResponse.getNextRequestWaitMillis();
    }

    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public BackendResponse.Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        long j2 = this.nextRequestWaitMillis;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((this.status.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BackendResponse{status=");
        sb.append(this.status);
        sb.append(", nextRequestWaitMillis=");
        return a.k(this.nextRequestWaitMillis, StringSubstitutor.DEFAULT_VAR_END, sb);
    }
}
