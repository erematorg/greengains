package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ExternalPRequestContext;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ExternalPRequestContext extends ExternalPRequestContext {
    private final Integer originAssociatedProductId;

    public static final class Builder extends ExternalPRequestContext.Builder {
        private Integer originAssociatedProductId;

        public ExternalPRequestContext build() {
            return new AutoValue_ExternalPRequestContext(this.originAssociatedProductId);
        }

        public ExternalPRequestContext.Builder setOriginAssociatedProductId(@Nullable Integer num) {
            this.originAssociatedProductId = num;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalPRequestContext)) {
            return false;
        }
        ExternalPRequestContext externalPRequestContext = (ExternalPRequestContext) obj;
        Integer num = this.originAssociatedProductId;
        return num == null ? externalPRequestContext.getOriginAssociatedProductId() == null : num.equals(externalPRequestContext.getOriginAssociatedProductId());
    }

    @Nullable
    public Integer getOriginAssociatedProductId() {
        return this.originAssociatedProductId;
    }

    public int hashCode() {
        Integer num = this.originAssociatedProductId;
        return (num == null ? 0 : num.hashCode()) ^ 1000003;
    }

    public String toString() {
        return "ExternalPRequestContext{originAssociatedProductId=" + this.originAssociatedProductId + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_ExternalPRequestContext(@Nullable Integer num) {
        this.originAssociatedProductId = num;
    }
}
