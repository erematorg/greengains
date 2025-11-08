package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0019J`\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019¨\u0006+"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Project;", "", "id", "", "name", "homepage", "imageId", "description", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "dappUrl", "order", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;Ljava/lang/String;Ljava/lang/Long;)V", "getId", "()Ljava/lang/String;", "getName", "getHomepage", "getImageId", "getDescription", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "getDappUrl", "getOrder", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/android/internal/common/explorer/data/model/Project;", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Project {
    @NotNull
    private final String dappUrl;
    @NotNull
    private final String description;
    @NotNull
    private final String homepage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageId;
    @NotNull
    private final ImageUrl imageUrl;
    @NotNull
    private final String name;
    @Nullable
    private final Long order;

    public Project(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull ImageUrl imageUrl2, @NotNull String str6, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homepage");
        Intrinsics.checkNotNullParameter(str4, "imageId");
        Intrinsics.checkNotNullParameter(str5, "description");
        Intrinsics.checkNotNullParameter(imageUrl2, "imageUrl");
        Intrinsics.checkNotNullParameter(str6, "dappUrl");
        this.id = str;
        this.name = str2;
        this.homepage = str3;
        this.imageId = str4;
        this.description = str5;
        this.imageUrl = imageUrl2;
        this.dappUrl = str6;
        this.order = l2;
    }

    public static /* synthetic */ Project copy$default(Project project, String str, String str2, String str3, String str4, String str5, ImageUrl imageUrl2, String str6, Long l2, int i3, Object obj) {
        Project project2 = project;
        int i4 = i3;
        return project.copy((i4 & 1) != 0 ? project2.id : str, (i4 & 2) != 0 ? project2.name : str2, (i4 & 4) != 0 ? project2.homepage : str3, (i4 & 8) != 0 ? project2.imageId : str4, (i4 & 16) != 0 ? project2.description : str5, (i4 & 32) != 0 ? project2.imageUrl : imageUrl2, (i4 & 64) != 0 ? project2.dappUrl : str6, (i4 & 128) != 0 ? project2.order : l2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.homepage;
    }

    @NotNull
    public final String component4() {
        return this.imageId;
    }

    @NotNull
    public final String component5() {
        return this.description;
    }

    @NotNull
    public final ImageUrl component6() {
        return this.imageUrl;
    }

    @NotNull
    public final String component7() {
        return this.dappUrl;
    }

    @Nullable
    public final Long component8() {
        return this.order;
    }

    @NotNull
    public final Project copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull ImageUrl imageUrl2, @NotNull String str6, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homepage");
        Intrinsics.checkNotNullParameter(str4, "imageId");
        Intrinsics.checkNotNullParameter(str5, "description");
        ImageUrl imageUrl3 = imageUrl2;
        Intrinsics.checkNotNullParameter(imageUrl3, "imageUrl");
        String str7 = str6;
        Intrinsics.checkNotNullParameter(str7, "dappUrl");
        return new Project(str, str2, str3, str4, str5, imageUrl3, str7, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Project)) {
            return false;
        }
        Project project = (Project) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) project.id) && Intrinsics.areEqual((Object) this.name, (Object) project.name) && Intrinsics.areEqual((Object) this.homepage, (Object) project.homepage) && Intrinsics.areEqual((Object) this.imageId, (Object) project.imageId) && Intrinsics.areEqual((Object) this.description, (Object) project.description) && Intrinsics.areEqual((Object) this.imageUrl, (Object) project.imageUrl) && Intrinsics.areEqual((Object) this.dappUrl, (Object) project.dappUrl) && Intrinsics.areEqual((Object) this.order, (Object) project.order);
    }

    @NotNull
    public final String getDappUrl() {
        return this.dappUrl;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getHomepage() {
        return this.homepage;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getImageId() {
        return this.imageId;
    }

    @NotNull
    public final ImageUrl getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Long getOrder() {
        return this.order;
    }

    public int hashCode() {
        int i3 = a.i(this.dappUrl, (this.imageUrl.hashCode() + a.i(this.description, a.i(this.imageId, a.i(this.homepage, a.i(this.name, this.id.hashCode() * 31, 31), 31), 31), 31)) * 31, 31);
        Long l2 = this.order;
        return i3 + (l2 == null ? 0 : l2.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.homepage;
        String str4 = this.imageId;
        String str5 = this.description;
        ImageUrl imageUrl2 = this.imageUrl;
        String str6 = this.dappUrl;
        Long l2 = this.order;
        StringBuilder l3 = C0118y.l("Project(id=", str, ", name=", str2, ", homepage=");
        b.w(l3, str3, ", imageId=", str4, ", description=");
        l3.append(str5);
        l3.append(", imageUrl=");
        l3.append(imageUrl2);
        l3.append(", dappUrl=");
        l3.append(str6);
        l3.append(", order=");
        l3.append(l2);
        l3.append(")");
        return l3.toString();
    }
}
