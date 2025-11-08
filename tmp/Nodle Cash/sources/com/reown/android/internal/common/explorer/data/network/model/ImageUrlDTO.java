package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "", "sm", "", "md", "lg", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSm", "()Ljava/lang/String;", "getMd", "getLg", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ImageUrlDTO {
    @NotNull
    private final String lg;
    @NotNull
    private final String md;
    @NotNull
    private final String sm;

    public ImageUrlDTO(@NotNull @Json(name = "sm") String str, @NotNull @Json(name = "md") String str2, @NotNull @Json(name = "lg") String str3) {
        Intrinsics.checkNotNullParameter(str, "sm");
        Intrinsics.checkNotNullParameter(str2, "md");
        Intrinsics.checkNotNullParameter(str3, "lg");
        this.sm = str;
        this.md = str2;
        this.lg = str3;
    }

    public static /* synthetic */ ImageUrlDTO copy$default(ImageUrlDTO imageUrlDTO, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imageUrlDTO.sm;
        }
        if ((i3 & 2) != 0) {
            str2 = imageUrlDTO.md;
        }
        if ((i3 & 4) != 0) {
            str3 = imageUrlDTO.lg;
        }
        return imageUrlDTO.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.sm;
    }

    @NotNull
    public final String component2() {
        return this.md;
    }

    @NotNull
    public final String component3() {
        return this.lg;
    }

    @NotNull
    public final ImageUrlDTO copy(@NotNull @Json(name = "sm") String str, @NotNull @Json(name = "md") String str2, @NotNull @Json(name = "lg") String str3) {
        Intrinsics.checkNotNullParameter(str, "sm");
        Intrinsics.checkNotNullParameter(str2, "md");
        Intrinsics.checkNotNullParameter(str3, "lg");
        return new ImageUrlDTO(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageUrlDTO)) {
            return false;
        }
        ImageUrlDTO imageUrlDTO = (ImageUrlDTO) obj;
        return Intrinsics.areEqual((Object) this.sm, (Object) imageUrlDTO.sm) && Intrinsics.areEqual((Object) this.md, (Object) imageUrlDTO.md) && Intrinsics.areEqual((Object) this.lg, (Object) imageUrlDTO.lg);
    }

    @NotNull
    public final String getLg() {
        return this.lg;
    }

    @NotNull
    public final String getMd() {
        return this.md;
    }

    @NotNull
    public final String getSm() {
        return this.sm;
    }

    public int hashCode() {
        return this.lg.hashCode() + a.i(this.md, this.sm.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.sm;
        String str2 = this.md;
        return A.a.n(C0118y.l("ImageUrlDTO(sm=", str, ", md=", str2, ", lg="), this.lg, ")");
    }
}
