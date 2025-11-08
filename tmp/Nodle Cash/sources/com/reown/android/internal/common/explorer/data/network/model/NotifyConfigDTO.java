package com.reown.android.internal.common.explorer.data.network.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDTO;", "", "data", "Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTO;", "<init>", "(Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTO;)V", "getData", "()Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTO;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotifyConfigDTO {
    @NotNull
    private final NotifyConfigDataDTO data;

    public NotifyConfigDTO(@NotNull @Json(name = "data") NotifyConfigDataDTO notifyConfigDataDTO) {
        Intrinsics.checkNotNullParameter(notifyConfigDataDTO, "data");
        this.data = notifyConfigDataDTO;
    }

    public static /* synthetic */ NotifyConfigDTO copy$default(NotifyConfigDTO notifyConfigDTO, NotifyConfigDataDTO notifyConfigDataDTO, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            notifyConfigDataDTO = notifyConfigDTO.data;
        }
        return notifyConfigDTO.copy(notifyConfigDataDTO);
    }

    @NotNull
    public final NotifyConfigDataDTO component1() {
        return this.data;
    }

    @NotNull
    public final NotifyConfigDTO copy(@NotNull @Json(name = "data") NotifyConfigDataDTO notifyConfigDataDTO) {
        Intrinsics.checkNotNullParameter(notifyConfigDataDTO, "data");
        return new NotifyConfigDTO(notifyConfigDataDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof NotifyConfigDTO) && Intrinsics.areEqual((Object) this.data, (Object) ((NotifyConfigDTO) obj).data);
    }

    @NotNull
    public final NotifyConfigDataDTO getData() {
        return this.data;
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    @NotNull
    public String toString() {
        NotifyConfigDataDTO notifyConfigDataDTO = this.data;
        return "NotifyConfigDTO(data=" + notifyConfigDataDTO + ")";
    }
}
