package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_ApplicationExitInfo extends CrashlyticsReport.ApplicationExitInfo {
    private final List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> buildIdMappingForArch;
    private final int importance;
    private final int pid;
    private final String processName;
    private final long pss;
    private final int reasonCode;
    private final long rss;
    private final long timestamp;
    private final String traceFile;

    public static final class Builder extends CrashlyticsReport.ApplicationExitInfo.Builder {
        private List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> buildIdMappingForArch;
        private int importance;
        private int pid;
        private String processName;
        private long pss;
        private int reasonCode;
        private long rss;
        private byte set$0;
        private long timestamp;
        private String traceFile;

        public CrashlyticsReport.ApplicationExitInfo build() {
            String str;
            if (this.set$0 == 63 && (str = this.processName) != null) {
                return new AutoValue_CrashlyticsReport_ApplicationExitInfo(this.pid, str, this.reasonCode, this.importance, this.pss, this.rss, this.timestamp, this.traceFile, this.buildIdMappingForArch);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" pid");
            }
            if (this.processName == null) {
                sb.append(" processName");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" reasonCode");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" importance");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" pss");
            }
            if ((this.set$0 & 16) == 0) {
                sb.append(" rss");
            }
            if ((this.set$0 & 32) == 0) {
                sb.append(" timestamp");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setBuildIdMappingForArch(@Nullable List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> list) {
            this.buildIdMappingForArch = list;
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setImportance(int i3) {
            this.importance = i3;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setPid(int i3) {
            this.pid = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setProcessName(String str) {
            if (str != null) {
                this.processName = str;
                return this;
            }
            throw new NullPointerException("Null processName");
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setPss(long j2) {
            this.pss = j2;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setReasonCode(int i3) {
            this.reasonCode = i3;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setRss(long j2) {
            this.rss = j2;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setTimestamp(long j2) {
            this.timestamp = j2;
            this.set$0 = (byte) (this.set$0 | 32);
            return this;
        }

        public CrashlyticsReport.ApplicationExitInfo.Builder setTraceFile(@Nullable String str) {
            this.traceFile = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.ApplicationExitInfo)) {
            return false;
        }
        CrashlyticsReport.ApplicationExitInfo applicationExitInfo = (CrashlyticsReport.ApplicationExitInfo) obj;
        if (this.pid == applicationExitInfo.getPid() && this.processName.equals(applicationExitInfo.getProcessName()) && this.reasonCode == applicationExitInfo.getReasonCode() && this.importance == applicationExitInfo.getImportance() && this.pss == applicationExitInfo.getPss() && this.rss == applicationExitInfo.getRss() && this.timestamp == applicationExitInfo.getTimestamp() && ((str = this.traceFile) != null ? str.equals(applicationExitInfo.getTraceFile()) : applicationExitInfo.getTraceFile() == null)) {
            List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> list = this.buildIdMappingForArch;
            if (list == null) {
                if (applicationExitInfo.getBuildIdMappingForArch() == null) {
                    return true;
                }
            } else if (list.equals(applicationExitInfo.getBuildIdMappingForArch())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> getBuildIdMappingForArch() {
        return this.buildIdMappingForArch;
    }

    @NonNull
    public int getImportance() {
        return this.importance;
    }

    @NonNull
    public int getPid() {
        return this.pid;
    }

    @NonNull
    public String getProcessName() {
        return this.processName;
    }

    @NonNull
    public long getPss() {
        return this.pss;
    }

    @NonNull
    public int getReasonCode() {
        return this.reasonCode;
    }

    @NonNull
    public long getRss() {
        return this.rss;
    }

    @NonNull
    public long getTimestamp() {
        return this.timestamp;
    }

    @Nullable
    public String getTraceFile() {
        return this.traceFile;
    }

    public int hashCode() {
        long j2 = this.pss;
        long j3 = this.rss;
        long j4 = this.timestamp;
        int hashCode = (((((((((((((this.pid ^ 1000003) * 1000003) ^ this.processName.hashCode()) * 1000003) ^ this.reasonCode) * 1000003) ^ this.importance) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ ((int) (j4 ^ (j4 >>> 32)))) * 1000003;
        String str = this.traceFile;
        int i3 = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> list = this.buildIdMappingForArch;
        if (list != null) {
            i3 = list.hashCode();
        }
        return hashCode2 ^ i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ApplicationExitInfo{pid=");
        sb.append(this.pid);
        sb.append(", processName=");
        sb.append(this.processName);
        sb.append(", reasonCode=");
        sb.append(this.reasonCode);
        sb.append(", importance=");
        sb.append(this.importance);
        sb.append(", pss=");
        sb.append(this.pss);
        sb.append(", rss=");
        sb.append(this.rss);
        sb.append(", timestamp=");
        sb.append(this.timestamp);
        sb.append(", traceFile=");
        sb.append(this.traceFile);
        sb.append(", buildIdMappingForArch=");
        return C0118y.h(StringSubstitutor.DEFAULT_VAR_END, this.buildIdMappingForArch, sb);
    }

    private AutoValue_CrashlyticsReport_ApplicationExitInfo(int i3, String str, int i4, int i5, long j2, long j3, long j4, @Nullable String str2, @Nullable List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> list) {
        this.pid = i3;
        this.processName = str;
        this.reasonCode = i4;
        this.importance = i5;
        this.pss = j2;
        this.rss = j3;
        this.timestamp = j4;
        this.traceFile = str2;
        this.buildIdMappingForArch = list;
    }
}
