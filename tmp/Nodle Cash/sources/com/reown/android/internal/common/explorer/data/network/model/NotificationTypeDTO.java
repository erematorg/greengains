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
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/NotificationTypeDTO;", "", "name", "", "id", "description", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;)V", "getName", "()Ljava/lang/String;", "getId", "getDescription", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationTypeDTO {
    @NotNull
    private final String description;
    @NotNull
    private final String id;
    @Nullable
    private final ImageUrlDTO imageUrl;
    @NotNull
    private final String name;

    public NotificationTypeDTO(@NotNull @Json(name = "name") String str, @NotNull @Json(name = "id") String str2, @NotNull @Json(name = "description") String str3, @Nullable @Json(name = "imageUrls") ImageUrlDTO imageUrlDTO) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, "description");
        this.name = str;
        this.id = str2;
        this.description = str3;
        this.imageUrl = imageUrlDTO;
    }

    public static /* synthetic */ NotificationTypeDTO copy$default(NotificationTypeDTO notificationTypeDTO, String str, String str2, String str3, ImageUrlDTO imageUrlDTO, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = notificationTypeDTO.name;
        }
        if ((i3 & 2) != 0) {
            str2 = notificationTypeDTO.id;
        }
        if ((i3 & 4) != 0) {
            str3 = notificationTypeDTO.description;
        }
        if ((i3 & 8) != 0) {
            imageUrlDTO = notificationTypeDTO.imageUrl;
        }
        return notificationTypeDTO.copy(str, str2, str3, imageUrlDTO);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.id;
    }

    @NotNull
    public final String component3() {
        return this.description;
    }

    @Nullable
    public final ImageUrlDTO component4() {
        return this.imageUrl;
    }

    @NotNull
    public final NotificationTypeDTO copy(@NotNull @Json(name = "name") String str, @NotNull @Json(name = "id") String str2, @NotNull @Json(name = "description") String str3, @Nullable @Json(name = "imageUrls") ImageUrlDTO imageUrlDTO) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, "description");
        return new NotificationTypeDTO(str, str2, str3, imageUrlDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationTypeDTO)) {
            return false;
        }
        NotificationTypeDTO notificationTypeDTO = (NotificationTypeDTO) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) notificationTypeDTO.name) && Intrinsics.areEqual((Object) this.id, (Object) notificationTypeDTO.id) && Intrinsics.areEqual((Object) this.description, (Object) notificationTypeDTO.description) && Intrinsics.areEqual((Object) this.imageUrl, (Object) notificationTypeDTO.imageUrl);
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final ImageUrlDTO getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int i3 = a.i(this.description, a.i(this.id, this.name.hashCode() * 31, 31), 31);
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        return i3 + (imageUrlDTO == null ? 0 : imageUrlDTO.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.id;
        String str3 = this.description;
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        StringBuilder l2 = C0118y.l("NotificationTypeDTO(name=", str, ", id=", str2, ", description=");
        l2.append(str3);
        l2.append(", imageUrl=");
        l2.append(imageUrlDTO);
        l2.append(")");
        return l2.toString();
    }
}
