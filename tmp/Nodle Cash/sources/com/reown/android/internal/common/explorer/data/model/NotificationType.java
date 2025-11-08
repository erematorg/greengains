package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/NotificationType;", "", "name", "", "id", "description", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;)V", "getName", "()Ljava/lang/String;", "getId", "getDescription", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationType {
    @NotNull
    private final String description;
    @NotNull
    private final String id;
    @Nullable
    private final ImageUrl imageUrl;
    @NotNull
    private final String name;

    public NotificationType(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable ImageUrl imageUrl2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, "description");
        this.name = str;
        this.id = str2;
        this.description = str3;
        this.imageUrl = imageUrl2;
    }

    public static /* synthetic */ NotificationType copy$default(NotificationType notificationType, String str, String str2, String str3, ImageUrl imageUrl2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = notificationType.name;
        }
        if ((i3 & 2) != 0) {
            str2 = notificationType.id;
        }
        if ((i3 & 4) != 0) {
            str3 = notificationType.description;
        }
        if ((i3 & 8) != 0) {
            imageUrl2 = notificationType.imageUrl;
        }
        return notificationType.copy(str, str2, str3, imageUrl2);
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
    public final ImageUrl component4() {
        return this.imageUrl;
    }

    @NotNull
    public final NotificationType copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable ImageUrl imageUrl2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, "description");
        return new NotificationType(str, str2, str3, imageUrl2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationType)) {
            return false;
        }
        NotificationType notificationType = (NotificationType) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) notificationType.name) && Intrinsics.areEqual((Object) this.id, (Object) notificationType.id) && Intrinsics.areEqual((Object) this.description, (Object) notificationType.description) && Intrinsics.areEqual((Object) this.imageUrl, (Object) notificationType.imageUrl);
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
    public final ImageUrl getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int i3 = a.i(this.description, a.i(this.id, this.name.hashCode() * 31, 31), 31);
        ImageUrl imageUrl2 = this.imageUrl;
        return i3 + (imageUrl2 == null ? 0 : imageUrl2.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.id;
        String str3 = this.description;
        ImageUrl imageUrl2 = this.imageUrl;
        StringBuilder l2 = C0118y.l("NotificationType(name=", str, ", id=", str2, ", description=");
        l2.append(str3);
        l2.append(", imageUrl=");
        l2.append(imageUrl2);
        l2.append(")");
        return l2.toString();
    }
}
