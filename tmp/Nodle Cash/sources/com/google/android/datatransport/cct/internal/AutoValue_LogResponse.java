package com.google.android.datatransport.cct.internal;

import android.support.v4.media.session.a;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_LogResponse extends LogResponse {
    private final long nextRequestWaitMillis;

    public AutoValue_LogResponse(long j2) {
        this.nextRequestWaitMillis = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LogResponse) {
            return this.nextRequestWaitMillis == ((LogResponse) obj).getNextRequestWaitMillis();
        }
        return false;
    }

    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public int hashCode() {
        long j2 = this.nextRequestWaitMillis;
        return ((int) (j2 ^ (j2 >>> 32))) ^ 1000003;
    }

    public String toString() {
        return a.k(this.nextRequestWaitMillis, StringSubstitutor.DEFAULT_VAR_END, new StringBuilder("LogResponse{nextRequestWaitMillis="));
    }
}
