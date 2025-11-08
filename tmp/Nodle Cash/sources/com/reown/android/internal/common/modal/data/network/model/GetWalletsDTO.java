package com.reown.android.internal.common.modal.data.network.model;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/GetWalletsDTO;", "", "count", "", "data", "", "Lcom/reown/android/internal/common/modal/data/network/model/WalletDTO;", "<init>", "(ILjava/util/List;)V", "getCount", "()I", "getData", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetWalletsDTO {
    private final int count;
    @NotNull
    private final List<WalletDTO> data;

    public GetWalletsDTO(@Json(name = "count") int i3, @NotNull @Json(name = "data") List<WalletDTO> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.count = i3;
        this.data = list;
    }

    public static /* synthetic */ GetWalletsDTO copy$default(GetWalletsDTO getWalletsDTO, int i3, List<WalletDTO> list, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = getWalletsDTO.count;
        }
        if ((i4 & 2) != 0) {
            list = getWalletsDTO.data;
        }
        return getWalletsDTO.copy(i3, list);
    }

    public final int component1() {
        return this.count;
    }

    @NotNull
    public final List<WalletDTO> component2() {
        return this.data;
    }

    @NotNull
    public final GetWalletsDTO copy(@Json(name = "count") int i3, @NotNull @Json(name = "data") List<WalletDTO> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        return new GetWalletsDTO(i3, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetWalletsDTO)) {
            return false;
        }
        GetWalletsDTO getWalletsDTO = (GetWalletsDTO) obj;
        return this.count == getWalletsDTO.count && Intrinsics.areEqual((Object) this.data, (Object) getWalletsDTO.data);
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<WalletDTO> getData() {
        return this.data;
    }

    public int hashCode() {
        return this.data.hashCode() + (Integer.hashCode(this.count) * 31);
    }

    @NotNull
    public String toString() {
        int i3 = this.count;
        List<WalletDTO> list = this.data;
        return "GetWalletsDTO(count=" + i3 + ", data=" + list + ")";
    }
}
