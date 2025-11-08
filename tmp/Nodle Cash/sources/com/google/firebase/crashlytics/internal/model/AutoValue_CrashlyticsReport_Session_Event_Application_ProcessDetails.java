package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails extends CrashlyticsReport.Session.Event.Application.ProcessDetails {
    private final boolean defaultProcess;
    private final int importance;
    private final int pid;
    private final String processName;

    public static final class Builder extends CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder {
        private boolean defaultProcess;
        private int importance;
        private int pid;
        private String processName;
        private byte set$0;

        public CrashlyticsReport.Session.Event.Application.ProcessDetails build() {
            String str;
            if (this.set$0 == 7 && (str = this.processName) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails(str, this.pid, this.importance, this.defaultProcess);
            }
            StringBuilder sb = new StringBuilder();
            if (this.processName == null) {
                sb.append(" processName");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" pid");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" importance");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" defaultProcess");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setDefaultProcess(boolean z2) {
            this.defaultProcess = z2;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setImportance(int i3) {
            this.importance = i3;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setPid(int i3) {
            this.pid = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setProcessName(String str) {
            if (str != null) {
                this.processName = str;
                return this;
            }
            throw new NullPointerException("Null processName");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.ProcessDetails)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails = (CrashlyticsReport.Session.Event.Application.ProcessDetails) obj;
        return this.processName.equals(processDetails.getProcessName()) && this.pid == processDetails.getPid() && this.importance == processDetails.getImportance() && this.defaultProcess == processDetails.isDefaultProcess();
    }

    public int getImportance() {
        return this.importance;
    }

    public int getPid() {
        return this.pid;
    }

    @NonNull
    public String getProcessName() {
        return this.processName;
    }

    public int hashCode() {
        return (this.defaultProcess ? 1231 : 1237) ^ ((((((this.processName.hashCode() ^ 1000003) * 1000003) ^ this.pid) * 1000003) ^ this.importance) * 1000003);
    }

    public boolean isDefaultProcess() {
        return this.defaultProcess;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ProcessDetails{processName=");
        sb.append(this.processName);
        sb.append(", pid=");
        sb.append(this.pid);
        sb.append(", importance=");
        sb.append(this.importance);
        sb.append(", defaultProcess=");
        return android.support.v4.media.session.a.s(sb, this.defaultProcess, StringSubstitutor.DEFAULT_VAR_END);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails(String str, int i3, int i4, boolean z2) {
        this.processName = str;
        this.pid = i3;
        this.importance = i4;
        this.defaultProcess = z2;
    }
}
