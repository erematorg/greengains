package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\b\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\b\u0001\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\b\u0003\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0003\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019¨\u0006'"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTO;", "", "name", "", "homepage", "description", "dappUrl", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "notificationTypes", "", "Lcom/reown/android/internal/common/explorer/data/network/model/NotificationTypeDTO;", "isVerified", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;Ljava/util/List;Z)V", "getName", "()Ljava/lang/String;", "getHomepage", "getDescription", "getDappUrl", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "getNotificationTypes", "()Ljava/util/List;", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotifyConfigDataDTO {
    @NotNull
    private final String dappUrl;
    @NotNull
    private final String description;
    @Nullable
    private final String homepage;
    @Nullable
    private final ImageUrlDTO imageUrl;
    private final boolean isVerified;
    @NotNull
    private final String name;
    @NotNull
    private final List<NotificationTypeDTO> notificationTypes;

    public NotifyConfigDataDTO(@NotNull @Json(name = "name") String str, @Nullable @Json(name = "homepage") String str2, @NotNull @Json(name = "description") String str3, @NotNull @Json(name = "dapp_url") String str4, @Nullable @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @NotNull @Json(name = "notificationTypes") List<NotificationTypeDTO> list, @Json(name = "isVerified") boolean z2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(str4, "dappUrl");
        Intrinsics.checkNotNullParameter(list, "notificationTypes");
        this.name = str;
        this.homepage = str2;
        this.description = str3;
        this.dappUrl = str4;
        this.imageUrl = imageUrlDTO;
        this.notificationTypes = list;
        this.isVerified = z2;
    }

    public static /* synthetic */ NotifyConfigDataDTO copy$default(NotifyConfigDataDTO notifyConfigDataDTO, String str, String str2, String str3, String str4, ImageUrlDTO imageUrlDTO, List<NotificationTypeDTO> list, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = notifyConfigDataDTO.name;
        }
        if ((i3 & 2) != 0) {
            str2 = notifyConfigDataDTO.homepage;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            str3 = notifyConfigDataDTO.description;
        }
        String str6 = str3;
        if ((i3 & 8) != 0) {
            str4 = notifyConfigDataDTO.dappUrl;
        }
        String str7 = str4;
        if ((i3 & 16) != 0) {
            imageUrlDTO = notifyConfigDataDTO.imageUrl;
        }
        ImageUrlDTO imageUrlDTO2 = imageUrlDTO;
        if ((i3 & 32) != 0) {
            list = notifyConfigDataDTO.notificationTypes;
        }
        List<NotificationTypeDTO> list2 = list;
        if ((i3 & 64) != 0) {
            z2 = notifyConfigDataDTO.isVerified;
        }
        return notifyConfigDataDTO.copy(str, str5, str6, str7, imageUrlDTO2, list2, z2);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @Nullable
    public final String component2() {
        return this.homepage;
    }

    @NotNull
    public final String component3() {
        return this.description;
    }

    @NotNull
    public final String component4() {
        return this.dappUrl;
    }

    @Nullable
    public final ImageUrlDTO component5() {
        return this.imageUrl;
    }

    @NotNull
    public final List<NotificationTypeDTO> component6() {
        return this.notificationTypes;
    }

    public final boolean component7() {
        return this.isVerified;
    }

    @NotNull
    public final NotifyConfigDataDTO copy(@NotNull @Json(name = "name") String str, @Nullable @Json(name = "homepage") String str2, @NotNull @Json(name = "description") String str3, @NotNull @Json(name = "dapp_url") String str4, @Nullable @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @NotNull @Json(name = "notificationTypes") List<NotificationTypeDTO> list, @Json(name = "isVerified") boolean z2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(str4, "dappUrl");
        Intrinsics.checkNotNullParameter(list, "notificationTypes");
        return new NotifyConfigDataDTO(str, str2, str3, str4, imageUrlDTO, list, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotifyConfigDataDTO)) {
            return false;
        }
        NotifyConfigDataDTO notifyConfigDataDTO = (NotifyConfigDataDTO) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) notifyConfigDataDTO.name) && Intrinsics.areEqual((Object) this.homepage, (Object) notifyConfigDataDTO.homepage) && Intrinsics.areEqual((Object) this.description, (Object) notifyConfigDataDTO.description) && Intrinsics.areEqual((Object) this.dappUrl, (Object) notifyConfigDataDTO.dappUrl) && Intrinsics.areEqual((Object) this.imageUrl, (Object) notifyConfigDataDTO.imageUrl) && Intrinsics.areEqual((Object) this.notificationTypes, (Object) notifyConfigDataDTO.notificationTypes) && this.isVerified == notifyConfigDataDTO.isVerified;
    }

    @NotNull
    public final String getDappUrl() {
        return this.dappUrl;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getHomepage() {
        return this.homepage;
    }

    @Nullable
    public final ImageUrlDTO getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final List<NotificationTypeDTO> getNotificationTypes() {
        return this.notificationTypes;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        String str = this.homepage;
        int i3 = 0;
        int i4 = a.i(this.dappUrl, a.i(this.description, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31), 31);
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        if (imageUrlDTO != null) {
            i3 = imageUrlDTO.hashCode();
        }
        return Boolean.hashCode(this.isVerified) + a.j(this.notificationTypes, (i4 + i3) * 31, 31);
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.homepage;
        String str3 = this.description;
        String str4 = this.dappUrl;
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        List<NotificationTypeDTO> list = this.notificationTypes;
        boolean z2 = this.isVerified;
        StringBuilder l2 = C0118y.l("NotifyConfigDataDTO(name=", str, ", homepage=", str2, ", description=");
        b.w(l2, str3, ", dappUrl=", str4, ", imageUrl=");
        l2.append(imageUrlDTO);
        l2.append(", notificationTypes=");
        l2.append(list);
        l2.append(", isVerified=");
        return android.support.v4.media.session.a.s(l2, z2, ")");
    }
}
