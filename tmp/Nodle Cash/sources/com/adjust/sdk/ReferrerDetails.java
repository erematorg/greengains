package com.adjust.sdk;

public class ReferrerDetails {
    public Boolean googlePlayInstant;
    public long installBeginTimestampSeconds;
    public long installBeginTimestampServerSeconds;
    public String installReferrer;
    public String installVersion;
    public Boolean isClick;
    public long referrerClickTimestampSeconds;
    public long referrerClickTimestampServerSeconds;

    public ReferrerDetails(String str, long j2, long j3) {
        this(str, j2, j3, -1, -1, (String) null, (Boolean) null, (Boolean) null);
    }

    public ReferrerDetails(String str, long j2, long j3, long j4, long j5, String str2, Boolean bool, Boolean bool2) {
        this.installReferrer = str;
        this.referrerClickTimestampSeconds = j2;
        this.installBeginTimestampSeconds = j3;
        this.referrerClickTimestampServerSeconds = j4;
        this.installBeginTimestampServerSeconds = j5;
        this.installVersion = str2;
        this.googlePlayInstant = bool;
        this.isClick = bool2;
    }

    public ReferrerDetails(String str, long j2, Boolean bool) {
        this(str, j2, -1, -1, -1, (String) null, (Boolean) null, bool);
    }
}
