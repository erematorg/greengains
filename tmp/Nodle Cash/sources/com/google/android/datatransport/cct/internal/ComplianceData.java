package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.AutoValue_ComplianceData;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ComplianceData {

    @AutoValue.Builder
    public static abstract class Builder {
        @NonNull
        public abstract ComplianceData build();

        @NonNull
        public abstract Builder setPrivacyContext(@Nullable ExternalPrivacyContext externalPrivacyContext);

        @NonNull
        public abstract Builder setProductIdOrigin(@Nullable ProductIdOrigin productIdOrigin);
    }

    public enum ProductIdOrigin {
        NOT_SET(0),
        EVENT_OVERRIDE(5);
        
        private static final SparseArray<ProductIdOrigin> valueMap = null;
        private final int value;

        static {
            ProductIdOrigin productIdOrigin;
            ProductIdOrigin productIdOrigin2;
            SparseArray<ProductIdOrigin> sparseArray = new SparseArray<>();
            valueMap = sparseArray;
            sparseArray.put(0, productIdOrigin);
            sparseArray.put(5, productIdOrigin2);
        }

        private ProductIdOrigin(int i3) {
            this.value = i3;
        }

        @Nullable
        public static ProductIdOrigin forNumber(int i3) {
            return valueMap.get(i3);
        }

        public int getValue() {
            return this.value;
        }
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_ComplianceData.Builder();
    }

    @Nullable
    public abstract ExternalPrivacyContext getPrivacyContext();

    @Nullable
    public abstract ProductIdOrigin getProductIdOrigin();
}
