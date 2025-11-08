package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution extends CrashlyticsReport.Session.Event.Application.Execution {
    private final CrashlyticsReport.ApplicationExitInfo appExitInfo;
    private final List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> binaries;
    private final CrashlyticsReport.Session.Event.Application.Execution.Exception exception;
    private final CrashlyticsReport.Session.Event.Application.Execution.Signal signal;
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread> threads;

    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Builder {
        private CrashlyticsReport.ApplicationExitInfo appExitInfo;
        private List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> binaries;
        private CrashlyticsReport.Session.Event.Application.Execution.Exception exception;
        private CrashlyticsReport.Session.Event.Application.Execution.Signal signal;
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread> threads;

        public CrashlyticsReport.Session.Event.Application.Execution build() {
            List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> list;
            CrashlyticsReport.Session.Event.Application.Execution.Signal signal2 = this.signal;
            if (signal2 != null && (list = this.binaries) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution(this.threads, this.exception, this.appExitInfo, signal2, list);
            }
            StringBuilder sb = new StringBuilder();
            if (this.signal == null) {
                sb.append(" signal");
            }
            if (this.binaries == null) {
                sb.append(" binaries");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Builder setAppExitInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
            this.appExitInfo = applicationExitInfo;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Builder setBinaries(List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> list) {
            if (list != null) {
                this.binaries = list;
                return this;
            }
            throw new NullPointerException("Null binaries");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Builder setException(CrashlyticsReport.Session.Event.Application.Execution.Exception exception2) {
            this.exception = exception2;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Builder setSignal(CrashlyticsReport.Session.Event.Application.Execution.Signal signal2) {
            if (signal2 != null) {
                this.signal = signal2;
                return this;
            }
            throw new NullPointerException("Null signal");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Builder setThreads(List<CrashlyticsReport.Session.Event.Application.Execution.Thread> list) {
            this.threads = list;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution execution = (CrashlyticsReport.Session.Event.Application.Execution) obj;
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread> list = this.threads;
        if (list != null ? list.equals(execution.getThreads()) : execution.getThreads() == null) {
            CrashlyticsReport.Session.Event.Application.Execution.Exception exception2 = this.exception;
            if (exception2 != null ? exception2.equals(execution.getException()) : execution.getException() == null) {
                CrashlyticsReport.ApplicationExitInfo applicationExitInfo = this.appExitInfo;
                if (applicationExitInfo != null ? applicationExitInfo.equals(execution.getAppExitInfo()) : execution.getAppExitInfo() == null) {
                    if (this.signal.equals(execution.getSignal()) && this.binaries.equals(execution.getBinaries())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public CrashlyticsReport.ApplicationExitInfo getAppExitInfo() {
        return this.appExitInfo;
    }

    @NonNull
    public List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> getBinaries() {
        return this.binaries;
    }

    @Nullable
    public CrashlyticsReport.Session.Event.Application.Execution.Exception getException() {
        return this.exception;
    }

    @NonNull
    public CrashlyticsReport.Session.Event.Application.Execution.Signal getSignal() {
        return this.signal;
    }

    @Nullable
    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread> getThreads() {
        return this.threads;
    }

    public int hashCode() {
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread> list = this.threads;
        int i3 = 0;
        int hashCode = ((list == null ? 0 : list.hashCode()) ^ 1000003) * 1000003;
        CrashlyticsReport.Session.Event.Application.Execution.Exception exception2 = this.exception;
        int hashCode2 = (hashCode ^ (exception2 == null ? 0 : exception2.hashCode())) * 1000003;
        CrashlyticsReport.ApplicationExitInfo applicationExitInfo = this.appExitInfo;
        if (applicationExitInfo != null) {
            i3 = applicationExitInfo.hashCode();
        }
        return this.binaries.hashCode() ^ ((((hashCode2 ^ i3) * 1000003) ^ this.signal.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Execution{threads=");
        sb.append(this.threads);
        sb.append(", exception=");
        sb.append(this.exception);
        sb.append(", appExitInfo=");
        sb.append(this.appExitInfo);
        sb.append(", signal=");
        sb.append(this.signal);
        sb.append(", binaries=");
        return C0118y.h(StringSubstitutor.DEFAULT_VAR_END, this.binaries, sb);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution(@Nullable List<CrashlyticsReport.Session.Event.Application.Execution.Thread> list, @Nullable CrashlyticsReport.Session.Event.Application.Execution.Exception exception2, @Nullable CrashlyticsReport.ApplicationExitInfo applicationExitInfo, CrashlyticsReport.Session.Event.Application.Execution.Signal signal2, List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> list2) {
        this.threads = list;
        this.exception = exception2;
        this.appExitInfo = applicationExitInfo;
        this.signal = signal2;
        this.binaries = list2;
    }
}
