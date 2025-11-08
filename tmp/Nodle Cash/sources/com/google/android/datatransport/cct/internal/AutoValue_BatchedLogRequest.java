package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_BatchedLogRequest extends BatchedLogRequest {
    private final List<LogRequest> logRequests;

    public AutoValue_BatchedLogRequest(List<LogRequest> list) {
        if (list != null) {
            this.logRequests = list;
            return;
        }
        throw new NullPointerException("Null logRequests");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BatchedLogRequest) {
            return this.logRequests.equals(((BatchedLogRequest) obj).getLogRequests());
        }
        return false;
    }

    @NonNull
    @Encodable.Field(name = "logRequest")
    public List<LogRequest> getLogRequests() {
        return this.logRequests;
    }

    public int hashCode() {
        return this.logRequests.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0118y.h(StringSubstitutor.DEFAULT_VAR_END, this.logRequests, new StringBuilder("BatchedLogRequest{logRequests="));
    }
}
