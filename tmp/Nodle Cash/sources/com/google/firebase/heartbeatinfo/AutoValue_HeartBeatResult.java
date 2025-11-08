package com.google.firebase.heartbeatinfo;

import androidx.camera.camera2.internal.C0118y;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final List<String> usedDates;
    private final String userAgent;

    public AutoValue_HeartBeatResult(String str, List<String> list) {
        if (str != null) {
            this.userAgent = str;
            if (list != null) {
                this.usedDates = list;
                return;
            }
            throw new NullPointerException("Null usedDates");
        }
        throw new NullPointerException("Null userAgent");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult heartBeatResult = (HeartBeatResult) obj;
        return this.userAgent.equals(heartBeatResult.getUserAgent()) && this.usedDates.equals(heartBeatResult.getUsedDates());
    }

    public List<String> getUsedDates() {
        return this.usedDates;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public int hashCode() {
        return this.usedDates.hashCode() ^ ((this.userAgent.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HeartBeatResult{userAgent=");
        sb.append(this.userAgent);
        sb.append(", usedDates=");
        return C0118y.h(StringSubstitutor.DEFAULT_VAR_END, this.usedDates, sb);
    }
}
