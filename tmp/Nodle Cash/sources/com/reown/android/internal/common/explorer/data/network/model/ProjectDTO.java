package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0019Jl\u0010#\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019¨\u0006+"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTO;", "", "id", "", "name", "homepage", "imageId", "description", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "dappUrl", "order", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;Ljava/lang/String;Ljava/lang/Long;)V", "getId", "()Ljava/lang/String;", "getName", "getHomepage", "getImageId", "getDescription", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "getDappUrl", "getOrder", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTO;", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProjectDTO {
    @Nullable
    private final String dappUrl;
    @Nullable
    private final String description;
    @Nullable
    private final String homepage;
    @NotNull
    private final String id;
    @Nullable
    private final String imageId;
    @Nullable
    private final ImageUrlDTO imageUrl;
    @Nullable
    private final String name;
    @Nullable
    private final Long order;

    public ProjectDTO(@NotNull @Json(name = "id") String str, @Nullable @Json(name = "name") String str2, @Nullable @Json(name = "homepage") String str3, @Nullable @Json(name = "image_id") String str4, @Nullable @Json(name = "description") String str5, @Nullable @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @Nullable @Json(name = "dapp_url") String str6, @Nullable @Json(name = "order") Long l2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
        this.name = str2;
        this.homepage = str3;
        this.imageId = str4;
        this.description = str5;
        this.imageUrl = imageUrlDTO;
        this.dappUrl = str6;
        this.order = l2;
    }

    public static /* synthetic */ ProjectDTO copy$default(ProjectDTO projectDTO, String str, String str2, String str3, String str4, String str5, ImageUrlDTO imageUrlDTO, String str6, Long l2, int i3, Object obj) {
        ProjectDTO projectDTO2 = projectDTO;
        int i4 = i3;
        return projectDTO.copy((i4 & 1) != 0 ? projectDTO2.id : str, (i4 & 2) != 0 ? projectDTO2.name : str2, (i4 & 4) != 0 ? projectDTO2.homepage : str3, (i4 & 8) != 0 ? projectDTO2.imageId : str4, (i4 & 16) != 0 ? projectDTO2.description : str5, (i4 & 32) != 0 ? projectDTO2.imageUrl : imageUrlDTO, (i4 & 64) != 0 ? projectDTO2.dappUrl : str6, (i4 & 128) != 0 ? projectDTO2.order : l2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.name;
    }

    @Nullable
    public final String component3() {
        return this.homepage;
    }

    @Nullable
    public final String component4() {
        return this.imageId;
    }

    @Nullable
    public final String component5() {
        return this.description;
    }

    @Nullable
    public final ImageUrlDTO component6() {
        return this.imageUrl;
    }

    @Nullable
    public final String component7() {
        return this.dappUrl;
    }

    @Nullable
    public final Long component8() {
        return this.order;
    }

    @NotNull
    public final ProjectDTO copy(@NotNull @Json(name = "id") String str, @Nullable @Json(name = "name") String str2, @Nullable @Json(name = "homepage") String str3, @Nullable @Json(name = "image_id") String str4, @Nullable @Json(name = "description") String str5, @Nullable @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @Nullable @Json(name = "dapp_url") String str6, @Nullable @Json(name = "order") Long l2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new ProjectDTO(str, str2, str3, str4, str5, imageUrlDTO, str6, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProjectDTO)) {
            return false;
        }
        ProjectDTO projectDTO = (ProjectDTO) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) projectDTO.id) && Intrinsics.areEqual((Object) this.name, (Object) projectDTO.name) && Intrinsics.areEqual((Object) this.homepage, (Object) projectDTO.homepage) && Intrinsics.areEqual((Object) this.imageId, (Object) projectDTO.imageId) && Intrinsics.areEqual((Object) this.description, (Object) projectDTO.description) && Intrinsics.areEqual((Object) this.imageUrl, (Object) projectDTO.imageUrl) && Intrinsics.areEqual((Object) this.dappUrl, (Object) projectDTO.dappUrl) && Intrinsics.areEqual((Object) this.order, (Object) projectDTO.order);
    }

    @Nullable
    public final String getDappUrl() {
        return this.dappUrl;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getHomepage() {
        return this.homepage;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getImageId() {
        return this.imageId;
    }

    @Nullable
    public final ImageUrlDTO getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Long getOrder() {
        return this.order;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.name;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.homepage;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.imageId;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.description;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        int hashCode6 = (hashCode5 + (imageUrlDTO == null ? 0 : imageUrlDTO.hashCode())) * 31;
        String str5 = this.dappUrl;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Long l2 = this.order;
        if (l2 != null) {
            i3 = l2.hashCode();
        }
        return hashCode7 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.homepage;
        String str4 = this.imageId;
        String str5 = this.description;
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        String str6 = this.dappUrl;
        Long l2 = this.order;
        StringBuilder l3 = C0118y.l("ProjectDTO(id=", str, ", name=", str2, ", homepage=");
        b.w(l3, str3, ", imageId=", str4, ", description=");
        l3.append(str5);
        l3.append(", imageUrl=");
        l3.append(imageUrlDTO);
        l3.append(", dappUrl=");
        l3.append(str6);
        l3.append(", order=");
        l3.append(l2);
        l3.append(")");
        return l3.toString();
    }
}
