package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ComplianceData;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ComplianceData extends ComplianceData {
    private final ExternalPrivacyContext privacyContext;
    private final ComplianceData.ProductIdOrigin productIdOrigin;

    public static final class Builder extends ComplianceData.Builder {
        private ExternalPrivacyContext privacyContext;
        private ComplianceData.ProductIdOrigin productIdOrigin;

        public ComplianceData build() {
            return new AutoValue_ComplianceData(this.privacyContext, this.productIdOrigin);
        }

        public ComplianceData.Builder setPrivacyContext(@Nullable ExternalPrivacyContext externalPrivacyContext) {
            this.privacyContext = externalPrivacyContext;
            return this;
        }

        public ComplianceData.Builder setProductIdOrigin(@Nullable ComplianceData.ProductIdOrigin productIdOrigin2) {
            this.productIdOrigin = productIdOrigin2;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComplianceData)) {
            return false;
        }
        ComplianceData complianceData = (ComplianceData) obj;
        ExternalPrivacyContext externalPrivacyContext = this.privacyContext;
        if (externalPrivacyContext != null ? externalPrivacyContext.equals(complianceData.getPrivacyContext()) : complianceData.getPrivacyContext() == null) {
            ComplianceData.ProductIdOrigin productIdOrigin2 = this.productIdOrigin;
            if (productIdOrigin2 == null) {
                if (complianceData.getProductIdOrigin() == null) {
                    return true;
                }
            } else if (productIdOrigin2.equals(complianceData.getProductIdOrigin())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public ExternalPrivacyContext getPrivacyContext() {
        return this.privacyContext;
    }

    @Nullable
    public ComplianceData.ProductIdOrigin getProductIdOrigin() {
        return this.productIdOrigin;
    }

    public int hashCode() {
        ExternalPrivacyContext externalPrivacyContext = this.privacyContext;
        int i3 = 0;
        int hashCode = ((externalPrivacyContext == null ? 0 : externalPrivacyContext.hashCode()) ^ 1000003) * 1000003;
        ComplianceData.ProductIdOrigin productIdOrigin2 = this.productIdOrigin;
        if (productIdOrigin2 != null) {
            i3 = productIdOrigin2.hashCode();
        }
        return hashCode ^ i3;
    }

    public String toString() {
        return "ComplianceData{privacyContext=" + this.privacyContext + ", productIdOrigin=" + this.productIdOrigin + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_ComplianceData(@Nullable ExternalPrivacyContext externalPrivacyContext, @Nullable ComplianceData.ProductIdOrigin productIdOrigin2) {
        this.privacyContext = externalPrivacyContext;
        this.productIdOrigin = productIdOrigin2;
    }
}
