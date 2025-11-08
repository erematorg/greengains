package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ExternalPrivacyContext;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ExternalPrivacyContext extends ExternalPrivacyContext {
    private final ExternalPRequestContext prequest;

    public static final class Builder extends ExternalPrivacyContext.Builder {
        private ExternalPRequestContext prequest;

        public ExternalPrivacyContext build() {
            return new AutoValue_ExternalPrivacyContext(this.prequest);
        }

        public ExternalPrivacyContext.Builder setPrequest(@Nullable ExternalPRequestContext externalPRequestContext) {
            this.prequest = externalPRequestContext;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalPrivacyContext)) {
            return false;
        }
        ExternalPrivacyContext externalPrivacyContext = (ExternalPrivacyContext) obj;
        ExternalPRequestContext externalPRequestContext = this.prequest;
        return externalPRequestContext == null ? externalPrivacyContext.getPrequest() == null : externalPRequestContext.equals(externalPrivacyContext.getPrequest());
    }

    @Nullable
    public ExternalPRequestContext getPrequest() {
        return this.prequest;
    }

    public int hashCode() {
        ExternalPRequestContext externalPRequestContext = this.prequest;
        return (externalPRequestContext == null ? 0 : externalPRequestContext.hashCode()) ^ 1000003;
    }

    public String toString() {
        return "ExternalPrivacyContext{prequest=" + this.prequest + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_ExternalPrivacyContext(@Nullable ExternalPRequestContext externalPRequestContext) {
        this.prequest = externalPRequestContext;
    }
}
