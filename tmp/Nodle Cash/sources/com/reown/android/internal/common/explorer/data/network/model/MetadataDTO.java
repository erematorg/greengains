package com.reown.android.internal.common.explorer.data.network.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;", "", "shortName", "", "colors", "Lcom/reown/android/internal/common/explorer/data/network/model/ColorsDTO;", "<init>", "(Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ColorsDTO;)V", "getShortName", "()Ljava/lang/String;", "getColors", "()Lcom/reown/android/internal/common/explorer/data/network/model/ColorsDTO;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MetadataDTO {
    @NotNull
    private final ColorsDTO colors;
    @Nullable
    private final String shortName;

    public MetadataDTO(@Nullable @Json(name = "shortName") String str, @NotNull @Json(name = "colors") ColorsDTO colorsDTO) {
        Intrinsics.checkNotNullParameter(colorsDTO, "colors");
        this.shortName = str;
        this.colors = colorsDTO;
    }

    public static /* synthetic */ MetadataDTO copy$default(MetadataDTO metadataDTO, String str, ColorsDTO colorsDTO, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = metadataDTO.shortName;
        }
        if ((i3 & 2) != 0) {
            colorsDTO = metadataDTO.colors;
        }
        return metadataDTO.copy(str, colorsDTO);
    }

    @Nullable
    public final String component1() {
        return this.shortName;
    }

    @NotNull
    public final ColorsDTO component2() {
        return this.colors;
    }

    @NotNull
    public final MetadataDTO copy(@Nullable @Json(name = "shortName") String str, @NotNull @Json(name = "colors") ColorsDTO colorsDTO) {
        Intrinsics.checkNotNullParameter(colorsDTO, "colors");
        return new MetadataDTO(str, colorsDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetadataDTO)) {
            return false;
        }
        MetadataDTO metadataDTO = (MetadataDTO) obj;
        return Intrinsics.areEqual((Object) this.shortName, (Object) metadataDTO.shortName) && Intrinsics.areEqual((Object) this.colors, (Object) metadataDTO.colors);
    }

    @NotNull
    public final ColorsDTO getColors() {
        return this.colors;
    }

    @Nullable
    public final String getShortName() {
        return this.shortName;
    }

    public int hashCode() {
        String str = this.shortName;
        return this.colors.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.shortName;
        ColorsDTO colorsDTO = this.colors;
        return "MetadataDTO(shortName=" + str + ", colors=" + colorsDTO + ")";
    }
}
