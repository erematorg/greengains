package com.reown.android.internal.common.modal.data.network.model;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/EnableAnalyticsDTO;", "", "isAnalyticsEnabled", "", "<init>", "(Z)V", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EnableAnalyticsDTO {
    private final boolean isAnalyticsEnabled;

    public EnableAnalyticsDTO(@Json(name = "isAnalyticsEnabled") boolean z2) {
        this.isAnalyticsEnabled = z2;
    }

    public static /* synthetic */ EnableAnalyticsDTO copy$default(EnableAnalyticsDTO enableAnalyticsDTO, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z2 = enableAnalyticsDTO.isAnalyticsEnabled;
        }
        return enableAnalyticsDTO.copy(z2);
    }

    public final boolean component1() {
        return this.isAnalyticsEnabled;
    }

    @NotNull
    public final EnableAnalyticsDTO copy(@Json(name = "isAnalyticsEnabled") boolean z2) {
        return new EnableAnalyticsDTO(z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EnableAnalyticsDTO) && this.isAnalyticsEnabled == ((EnableAnalyticsDTO) obj).isAnalyticsEnabled;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isAnalyticsEnabled);
    }

    public final boolean isAnalyticsEnabled() {
        return this.isAnalyticsEnabled;
    }

    @NotNull
    public String toString() {
        boolean z2 = this.isAnalyticsEnabled;
        return "EnableAnalyticsDTO(isAnalyticsEnabled=" + z2 + ")";
    }
}
