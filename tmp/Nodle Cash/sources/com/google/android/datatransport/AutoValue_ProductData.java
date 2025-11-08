package com.google.android.datatransport;

import androidx.annotation.Nullable;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ProductData extends ProductData {
    private final Integer productId;

    public AutoValue_ProductData(@Nullable Integer num) {
        this.productId = num;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProductData)) {
            return false;
        }
        ProductData productData = (ProductData) obj;
        Integer num = this.productId;
        return num == null ? productData.getProductId() == null : num.equals(productData.getProductId());
    }

    @Nullable
    public Integer getProductId() {
        return this.productId;
    }

    public int hashCode() {
        Integer num = this.productId;
        return (num == null ? 0 : num.hashCode()) ^ 1000003;
    }

    public String toString() {
        return "ProductData{productId=" + this.productId + StringSubstitutor.DEFAULT_VAR_END;
    }
}
