package com.google.firebase.remoteconfig.interop.rollouts;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    public static final class Builder extends RolloutAssignment.Builder {
        private String parameterKey;
        private String parameterValue;
        private String rolloutId;
        private byte set$0;
        private long templateVersion;
        private String variantId;

        public RolloutAssignment build() {
            if (this.set$0 == 1 && this.rolloutId != null && this.variantId != null && this.parameterKey != null && this.parameterValue != null) {
                return new AutoValue_RolloutAssignment(this.rolloutId, this.variantId, this.parameterKey, this.parameterValue, this.templateVersion);
            }
            StringBuilder sb = new StringBuilder();
            if (this.rolloutId == null) {
                sb.append(" rolloutId");
            }
            if (this.variantId == null) {
                sb.append(" variantId");
            }
            if (this.parameterKey == null) {
                sb.append(" parameterKey");
            }
            if (this.parameterValue == null) {
                sb.append(" parameterValue");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" templateVersion");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public RolloutAssignment.Builder setParameterKey(String str) {
            if (str != null) {
                this.parameterKey = str;
                return this;
            }
            throw new NullPointerException("Null parameterKey");
        }

        public RolloutAssignment.Builder setParameterValue(String str) {
            if (str != null) {
                this.parameterValue = str;
                return this;
            }
            throw new NullPointerException("Null parameterValue");
        }

        public RolloutAssignment.Builder setRolloutId(String str) {
            if (str != null) {
                this.rolloutId = str;
                return this;
            }
            throw new NullPointerException("Null rolloutId");
        }

        public RolloutAssignment.Builder setTemplateVersion(long j2) {
            this.templateVersion = j2;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public RolloutAssignment.Builder setVariantId(String str) {
            if (str != null) {
                this.variantId = str;
                return this;
            }
            throw new NullPointerException("Null variantId");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RolloutAssignment)) {
            return false;
        }
        RolloutAssignment rolloutAssignment = (RolloutAssignment) obj;
        return this.rolloutId.equals(rolloutAssignment.getRolloutId()) && this.variantId.equals(rolloutAssignment.getVariantId()) && this.parameterKey.equals(rolloutAssignment.getParameterKey()) && this.parameterValue.equals(rolloutAssignment.getParameterValue()) && this.templateVersion == rolloutAssignment.getTemplateVersion();
    }

    @NonNull
    public String getParameterKey() {
        return this.parameterKey;
    }

    @NonNull
    public String getParameterValue() {
        return this.parameterValue;
    }

    @NonNull
    public String getRolloutId() {
        return this.rolloutId;
    }

    public long getTemplateVersion() {
        return this.templateVersion;
    }

    @NonNull
    public String getVariantId() {
        return this.variantId;
    }

    public int hashCode() {
        long j2 = this.templateVersion;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.variantId.hashCode()) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RolloutAssignment{rolloutId=");
        sb.append(this.rolloutId);
        sb.append(", variantId=");
        sb.append(this.variantId);
        sb.append(", parameterKey=");
        sb.append(this.parameterKey);
        sb.append(", parameterValue=");
        sb.append(this.parameterValue);
        sb.append(", templateVersion=");
        return android.support.v4.media.session.a.k(this.templateVersion, StringSubstitutor.DEFAULT_VAR_END, sb);
    }

    private AutoValue_RolloutAssignment(String str, String str2, String str3, String str4, long j2) {
        this.rolloutId = str;
        this.variantId = str2;
        this.parameterKey = str3;
        this.parameterValue = str4;
        this.templateVersion = j2;
    }
}
