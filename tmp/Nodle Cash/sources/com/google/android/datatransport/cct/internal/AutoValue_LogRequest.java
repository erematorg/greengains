package com.google.android.datatransport.cct.internal;

import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_LogRequest extends LogRequest {
    private final ClientInfo clientInfo;
    private final List<LogEvent> logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    public static final class Builder extends LogRequest.Builder {
        private ClientInfo clientInfo;
        private List<LogEvent> logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        public LogRequest build() {
            String str = this.requestTimeMs == null ? " requestTimeMs" : "";
            if (this.requestUptimeMs == null) {
                str = a.m(str, " requestUptimeMs");
            }
            if (str.isEmpty()) {
                return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public LogRequest.Builder setClientInfo(@Nullable ClientInfo clientInfo2) {
            this.clientInfo = clientInfo2;
            return this;
        }

        public LogRequest.Builder setLogEvents(@Nullable List<LogEvent> list) {
            this.logEvents = list;
            return this;
        }

        public LogRequest.Builder setLogSource(@Nullable Integer num) {
            this.logSource = num;
            return this;
        }

        public LogRequest.Builder setLogSourceName(@Nullable String str) {
            this.logSourceName = str;
            return this;
        }

        public LogRequest.Builder setQosTier(@Nullable QosTier qosTier2) {
            this.qosTier = qosTier2;
            return this;
        }

        public LogRequest.Builder setRequestTimeMs(long j2) {
            this.requestTimeMs = Long.valueOf(j2);
            return this;
        }

        public LogRequest.Builder setRequestUptimeMs(long j2) {
            this.requestUptimeMs = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        ClientInfo clientInfo2;
        Integer num;
        String str;
        List<LogEvent> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogRequest)) {
            return false;
        }
        LogRequest logRequest = (LogRequest) obj;
        if (this.requestTimeMs == logRequest.getRequestTimeMs() && this.requestUptimeMs == logRequest.getRequestUptimeMs() && ((clientInfo2 = this.clientInfo) != null ? clientInfo2.equals(logRequest.getClientInfo()) : logRequest.getClientInfo() == null) && ((num = this.logSource) != null ? num.equals(logRequest.getLogSource()) : logRequest.getLogSource() == null) && ((str = this.logSourceName) != null ? str.equals(logRequest.getLogSourceName()) : logRequest.getLogSourceName() == null) && ((list = this.logEvents) != null ? list.equals(logRequest.getLogEvents()) : logRequest.getLogEvents() == null)) {
            QosTier qosTier2 = this.qosTier;
            if (qosTier2 == null) {
                if (logRequest.getQosTier() == null) {
                    return true;
                }
            } else if (qosTier2.equals(logRequest.getQosTier())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    @Nullable
    @Encodable.Field(name = "logEvent")
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    @Nullable
    public Integer getLogSource() {
        return this.logSource;
    }

    @Nullable
    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Nullable
    public QosTier getQosTier() {
        return this.qosTier;
    }

    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    public int hashCode() {
        long j2 = this.requestTimeMs;
        long j3 = this.requestUptimeMs;
        int i3 = (((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        ClientInfo clientInfo2 = this.clientInfo;
        int i4 = 0;
        int hashCode = (i3 ^ (clientInfo2 == null ? 0 : clientInfo2.hashCode())) * 1000003;
        Integer num = this.logSource;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.logSourceName;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<LogEvent> list = this.logEvents;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier2 = this.qosTier;
        if (qosTier2 != null) {
            i4 = qosTier2.hashCode();
        }
        return hashCode4 ^ i4;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_LogRequest(long j2, long j3, @Nullable ClientInfo clientInfo2, @Nullable Integer num, @Nullable String str, @Nullable List<LogEvent> list, @Nullable QosTier qosTier2) {
        this.requestTimeMs = j2;
        this.requestUptimeMs = j3;
        this.clientInfo = clientInfo2;
        this.logSource = num;
        this.logSourceName = str;
        this.logEvents = list;
        this.qosTier = qosTier2;
    }
}
