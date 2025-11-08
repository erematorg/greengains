package com.google.firebase.crashlytics.internal.model;

import android.support.v4.media.session.a;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_StaticSessionData_OsData extends StaticSessionData.OsData {
    private final boolean isRooted;
    private final String osCodeName;
    private final String osRelease;

    public AutoValue_StaticSessionData_OsData(String str, String str2, boolean z2) {
        if (str != null) {
            this.osRelease = str;
            if (str2 != null) {
                this.osCodeName = str2;
                this.isRooted = z2;
                return;
            }
            throw new NullPointerException("Null osCodeName");
        }
        throw new NullPointerException("Null osRelease");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StaticSessionData.OsData)) {
            return false;
        }
        StaticSessionData.OsData osData = (StaticSessionData.OsData) obj;
        return this.osRelease.equals(osData.osRelease()) && this.osCodeName.equals(osData.osCodeName()) && this.isRooted == osData.isRooted();
    }

    public int hashCode() {
        return (this.isRooted ? 1231 : 1237) ^ ((((this.osRelease.hashCode() ^ 1000003) * 1000003) ^ this.osCodeName.hashCode()) * 1000003);
    }

    public boolean isRooted() {
        return this.isRooted;
    }

    public String osCodeName() {
        return this.osCodeName;
    }

    public String osRelease() {
        return this.osRelease;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OsData{osRelease=");
        sb.append(this.osRelease);
        sb.append(", osCodeName=");
        sb.append(this.osCodeName);
        sb.append(", isRooted=");
        return a.s(sb, this.isRooted, StringSubstitutor.DEFAULT_VAR_END);
    }
}
