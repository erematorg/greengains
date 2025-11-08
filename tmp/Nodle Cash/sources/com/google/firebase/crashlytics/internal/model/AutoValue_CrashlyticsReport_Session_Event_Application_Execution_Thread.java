package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread extends CrashlyticsReport.Session.Event.Application.Execution.Thread {
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
    private final int importance;
    private final String name;

    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder {
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
        private int importance;
        private String name;
        private byte set$0;

        public CrashlyticsReport.Session.Event.Application.Execution.Thread build() {
            String str;
            List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list;
            if (this.set$0 == 1 && (str = this.name) != null && (list = this.frames) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(str, this.importance, list);
            }
            StringBuilder sb = new StringBuilder();
            if (this.name == null) {
                sb.append(" name");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" importance");
            }
            if (this.frames == null) {
                sb.append(" frames");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setFrames(List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list) {
            if (list != null) {
                this.frames = list;
                return this;
            }
            throw new NullPointerException("Null frames");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setImportance(int i3) {
            this.importance = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setName(String str) {
            if (str != null) {
                this.name = str;
                return this;
            }
            throw new NullPointerException("Null name");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.Thread)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.Thread thread = (CrashlyticsReport.Session.Event.Application.Execution.Thread) obj;
        return this.name.equals(thread.getName()) && this.importance == thread.getImportance() && this.frames.equals(thread.getFrames());
    }

    @NonNull
    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> getFrames() {
        return this.frames;
    }

    public int getImportance() {
        return this.importance;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.frames.hashCode() ^ ((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.importance) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Thread{name=");
        sb.append(this.name);
        sb.append(", importance=");
        sb.append(this.importance);
        sb.append(", frames=");
        return C0118y.h(StringSubstitutor.DEFAULT_VAR_END, this.frames, sb);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(String str, int i3, List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list) {
        this.name = str;
        this.importance = i3;
        this.frames = list;
    }
}
