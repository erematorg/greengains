package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_CustomAttribute extends CrashlyticsReport.CustomAttribute {
    private final String key;
    private final String value;

    public static final class Builder extends CrashlyticsReport.CustomAttribute.Builder {
        private String key;
        private String value;

        public CrashlyticsReport.CustomAttribute build() {
            String str;
            String str2 = this.key;
            if (str2 != null && (str = this.value) != null) {
                return new AutoValue_CrashlyticsReport_CustomAttribute(str2, str);
            }
            StringBuilder sb = new StringBuilder();
            if (this.key == null) {
                sb.append(" key");
            }
            if (this.value == null) {
                sb.append(" value");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.CustomAttribute.Builder setKey(String str) {
            if (str != null) {
                this.key = str;
                return this;
            }
            throw new NullPointerException("Null key");
        }

        public CrashlyticsReport.CustomAttribute.Builder setValue(String str) {
            if (str != null) {
                this.value = str;
                return this;
            }
            throw new NullPointerException("Null value");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.CustomAttribute)) {
            return false;
        }
        CrashlyticsReport.CustomAttribute customAttribute = (CrashlyticsReport.CustomAttribute) obj;
        return this.key.equals(customAttribute.getKey()) && this.value.equals(customAttribute.getValue());
    }

    @NonNull
    public String getKey() {
        return this.key;
    }

    @NonNull
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ ((this.key.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CustomAttribute{key=");
        sb.append(this.key);
        sb.append(", value=");
        return A.a.n(sb, this.value, StringSubstitutor.DEFAULT_VAR_END);
    }

    private AutoValue_CrashlyticsReport_CustomAttribute(String str, String str2) {
        this.key = str;
        this.value = str2;
    }
}
