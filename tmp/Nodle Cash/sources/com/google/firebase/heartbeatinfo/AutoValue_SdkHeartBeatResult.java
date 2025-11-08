package com.google.firebase.heartbeatinfo;

import android.support.v4.media.session.a;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_SdkHeartBeatResult extends SdkHeartBeatResult {
    private final long millis;
    private final String sdkName;

    public AutoValue_SdkHeartBeatResult(String str, long j2) {
        if (str != null) {
            this.sdkName = str;
            this.millis = j2;
            return;
        }
        throw new NullPointerException("Null sdkName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkHeartBeatResult)) {
            return false;
        }
        SdkHeartBeatResult sdkHeartBeatResult = (SdkHeartBeatResult) obj;
        return this.sdkName.equals(sdkHeartBeatResult.getSdkName()) && this.millis == sdkHeartBeatResult.getMillis();
    }

    public long getMillis() {
        return this.millis;
    }

    public String getSdkName() {
        return this.sdkName;
    }

    public int hashCode() {
        long j2 = this.millis;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((this.sdkName.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SdkHeartBeatResult{sdkName=");
        sb.append(this.sdkName);
        sb.append(", millis=");
        return a.k(this.millis, StringSubstitutor.DEFAULT_VAR_END, sb);
    }
}
