package com.google.firebase;

import android.support.v4.media.session.a;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_StartupTime extends StartupTime {
    private final long elapsedRealtime;
    private final long epochMillis;
    private final long uptimeMillis;

    public AutoValue_StartupTime(long j2, long j3, long j4) {
        this.epochMillis = j2;
        this.elapsedRealtime = j3;
        this.uptimeMillis = j4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StartupTime)) {
            return false;
        }
        StartupTime startupTime = (StartupTime) obj;
        return this.epochMillis == startupTime.getEpochMillis() && this.elapsedRealtime == startupTime.getElapsedRealtime() && this.uptimeMillis == startupTime.getUptimeMillis();
    }

    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    public long getEpochMillis() {
        return this.epochMillis;
    }

    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        long j2 = this.epochMillis;
        long j3 = this.elapsedRealtime;
        long j4 = this.uptimeMillis;
        return ((int) ((j4 >>> 32) ^ j4)) ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StartupTime{epochMillis=");
        sb.append(this.epochMillis);
        sb.append(", elapsedRealtime=");
        sb.append(this.elapsedRealtime);
        sb.append(", uptimeMillis=");
        return a.k(this.uptimeMillis, StringSubstitutor.DEFAULT_VAR_END, sb);
    }
}
