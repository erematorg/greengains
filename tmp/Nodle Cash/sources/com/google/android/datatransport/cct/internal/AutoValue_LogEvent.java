package com.google.android.datatransport.cct.internal;

import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_LogEvent extends LogEvent {
    private final ComplianceData complianceData;
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final ExperimentIds experimentIds;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    public static final class Builder extends LogEvent.Builder {
        private ComplianceData complianceData;
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private ExperimentIds experimentIds;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        public LogEvent build() {
            String str = this.eventTimeMs == null ? " eventTimeMs" : "";
            if (this.eventUptimeMs == null) {
                str = a.m(str, " eventUptimeMs");
            }
            if (this.timezoneOffsetSeconds == null) {
                str = a.m(str, " timezoneOffsetSeconds");
            }
            if (str.isEmpty()) {
                return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.complianceData, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo, this.experimentIds);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public LogEvent.Builder setComplianceData(@Nullable ComplianceData complianceData2) {
            this.complianceData = complianceData2;
            return this;
        }

        public LogEvent.Builder setEventCode(@Nullable Integer num) {
            this.eventCode = num;
            return this;
        }

        public LogEvent.Builder setEventTimeMs(long j2) {
            this.eventTimeMs = Long.valueOf(j2);
            return this;
        }

        public LogEvent.Builder setEventUptimeMs(long j2) {
            this.eventUptimeMs = Long.valueOf(j2);
            return this;
        }

        public LogEvent.Builder setExperimentIds(@Nullable ExperimentIds experimentIds2) {
            this.experimentIds = experimentIds2;
            return this;
        }

        public LogEvent.Builder setNetworkConnectionInfo(@Nullable NetworkConnectionInfo networkConnectionInfo2) {
            this.networkConnectionInfo = networkConnectionInfo2;
            return this;
        }

        public LogEvent.Builder setSourceExtension(@Nullable byte[] bArr) {
            this.sourceExtension = bArr;
            return this;
        }

        public LogEvent.Builder setSourceExtensionJsonProto3(@Nullable String str) {
            this.sourceExtensionJsonProto3 = str;
            return this;
        }

        public LogEvent.Builder setTimezoneOffsetSeconds(long j2) {
            this.timezoneOffsetSeconds = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        Integer num;
        ComplianceData complianceData2;
        String str;
        NetworkConnectionInfo networkConnectionInfo2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEvent)) {
            return false;
        }
        LogEvent logEvent = (LogEvent) obj;
        if (this.eventTimeMs == logEvent.getEventTimeMs() && ((num = this.eventCode) != null ? num.equals(logEvent.getEventCode()) : logEvent.getEventCode() == null) && ((complianceData2 = this.complianceData) != null ? complianceData2.equals(logEvent.getComplianceData()) : logEvent.getComplianceData() == null) && this.eventUptimeMs == logEvent.getEventUptimeMs()) {
            if (Arrays.equals(this.sourceExtension, logEvent instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) logEvent).sourceExtension : logEvent.getSourceExtension()) && ((str = this.sourceExtensionJsonProto3) != null ? str.equals(logEvent.getSourceExtensionJsonProto3()) : logEvent.getSourceExtensionJsonProto3() == null) && this.timezoneOffsetSeconds == logEvent.getTimezoneOffsetSeconds() && ((networkConnectionInfo2 = this.networkConnectionInfo) != null ? networkConnectionInfo2.equals(logEvent.getNetworkConnectionInfo()) : logEvent.getNetworkConnectionInfo() == null)) {
                ExperimentIds experimentIds2 = this.experimentIds;
                if (experimentIds2 == null) {
                    if (logEvent.getExperimentIds() == null) {
                        return true;
                    }
                } else if (experimentIds2.equals(logEvent.getExperimentIds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public ComplianceData getComplianceData() {
        return this.complianceData;
    }

    @Nullable
    public Integer getEventCode() {
        return this.eventCode;
    }

    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Nullable
    public ExperimentIds getExperimentIds() {
        return this.experimentIds;
    }

    @Nullable
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    @Nullable
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Nullable
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public int hashCode() {
        long j2 = this.eventTimeMs;
        int i3 = (((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.eventCode;
        int i4 = 0;
        int hashCode = (i3 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        ComplianceData complianceData2 = this.complianceData;
        int hashCode2 = complianceData2 == null ? 0 : complianceData2.hashCode();
        long j3 = this.eventUptimeMs;
        int hashCode3 = (((((hashCode ^ hashCode2) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String str = this.sourceExtensionJsonProto3;
        int hashCode4 = str == null ? 0 : str.hashCode();
        long j4 = this.timezoneOffsetSeconds;
        int i5 = (((hashCode3 ^ hashCode4) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo2 = this.networkConnectionInfo;
        int hashCode5 = (i5 ^ (networkConnectionInfo2 == null ? 0 : networkConnectionInfo2.hashCode())) * 1000003;
        ExperimentIds experimentIds2 = this.experimentIds;
        if (experimentIds2 != null) {
            i4 = experimentIds2.hashCode();
        }
        return hashCode5 ^ i4;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", complianceData=" + this.complianceData + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + ", experimentIds=" + this.experimentIds + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_LogEvent(long j2, @Nullable Integer num, @Nullable ComplianceData complianceData2, long j3, @Nullable byte[] bArr, @Nullable String str, long j4, @Nullable NetworkConnectionInfo networkConnectionInfo2, @Nullable ExperimentIds experimentIds2) {
        this.eventTimeMs = j2;
        this.eventCode = num;
        this.complianceData = complianceData2;
        this.eventUptimeMs = j3;
        this.sourceExtension = bArr;
        this.sourceExtensionJsonProto3 = str;
        this.timezoneOffsetSeconds = j4;
        this.networkConnectionInfo = networkConnectionInfo2;
        this.experimentIds = experimentIds2;
    }
}
