package com.google.firebase.crashlytics.internal.model;

import A.a;
import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Log extends CrashlyticsReport.Session.Event.Log {
    private final String content;

    public static final class Builder extends CrashlyticsReport.Session.Event.Log.Builder {
        private String content;

        public CrashlyticsReport.Session.Event.Log build() {
            String str = this.content;
            if (str != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Log(str);
            }
            throw new IllegalStateException("Missing required properties: content");
        }

        public CrashlyticsReport.Session.Event.Log.Builder setContent(String str) {
            if (str != null) {
                this.content = str;
                return this;
            }
            throw new NullPointerException("Null content");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Event.Log) {
            return this.content.equals(((CrashlyticsReport.Session.Event.Log) obj).getContent());
        }
        return false;
    }

    @NonNull
    public String getContent() {
        return this.content;
    }

    public int hashCode() {
        return this.content.hashCode() ^ 1000003;
    }

    public String toString() {
        return a.n(new StringBuilder("Log{content="), this.content, StringSubstitutor.DEFAULT_VAR_END);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Log(String str) {
        this.content = str;
    }
}
