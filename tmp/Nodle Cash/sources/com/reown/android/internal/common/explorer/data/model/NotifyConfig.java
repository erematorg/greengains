package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JW\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019¨\u0006'"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/NotifyConfig;", "", "dappUrl", "", "name", "homepage", "description", "types", "", "Lcom/reown/android/internal/common/explorer/data/model/NotificationType;", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "isVerified", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;Z)V", "getDappUrl", "()Ljava/lang/String;", "getName", "getHomepage", "getDescription", "getTypes", "()Ljava/util/List;", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotifyConfig {
    @NotNull
    private final String dappUrl;
    @NotNull
    private final String description;
    @NotNull
    private final String homepage;
    @Nullable
    private final ImageUrl imageUrl;
    private final boolean isVerified;
    @NotNull
    private final String name;
    @NotNull
    private final List<NotificationType> types;

    public NotifyConfig(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<NotificationType> list, @Nullable ImageUrl imageUrl2, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "dappUrl");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homepage");
        Intrinsics.checkNotNullParameter(str4, "description");
        Intrinsics.checkNotNullParameter(list, "types");
        this.dappUrl = str;
        this.name = str2;
        this.homepage = str3;
        this.description = str4;
        this.types = list;
        this.imageUrl = imageUrl2;
        this.isVerified = z2;
    }

    public static /* synthetic */ NotifyConfig copy$default(NotifyConfig notifyConfig, String str, String str2, String str3, String str4, List<NotificationType> list, ImageUrl imageUrl2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = notifyConfig.dappUrl;
        }
        if ((i3 & 2) != 0) {
            str2 = notifyConfig.name;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            str3 = notifyConfig.homepage;
        }
        String str6 = str3;
        if ((i3 & 8) != 0) {
            str4 = notifyConfig.description;
        }
        String str7 = str4;
        if ((i3 & 16) != 0) {
            list = notifyConfig.types;
        }
        List<NotificationType> list2 = list;
        if ((i3 & 32) != 0) {
            imageUrl2 = notifyConfig.imageUrl;
        }
        ImageUrl imageUrl3 = imageUrl2;
        if ((i3 & 64) != 0) {
            z2 = notifyConfig.isVerified;
        }
        return notifyConfig.copy(str, str5, str6, str7, list2, imageUrl3, z2);
    }

    @NotNull
    public final String component1() {
        return this.dappUrl;
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
        return this.description;
    }

    @NotNull
    public final List<NotificationType> component5() {
        return this.types;
    }

    @Nullable
    public final ImageUrl component6() {
        return this.imageUrl;
    }

    public final boolean component7() {
        return this.isVerified;
    }

    @NotNull
    public final NotifyConfig copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<NotificationType> list, @Nullable ImageUrl imageUrl2, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "dappUrl");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homepage");
        Intrinsics.checkNotNullParameter(str4, "description");
        Intrinsics.checkNotNullParameter(list, "types");
        return new NotifyConfig(str, str2, str3, str4, list, imageUrl2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotifyConfig)) {
            return false;
        }
        NotifyConfig notifyConfig = (NotifyConfig) obj;
        return Intrinsics.areEqual((Object) this.dappUrl, (Object) notifyConfig.dappUrl) && Intrinsics.areEqual((Object) this.name, (Object) notifyConfig.name) && Intrinsics.areEqual((Object) this.homepage, (Object) notifyConfig.homepage) && Intrinsics.areEqual((Object) this.description, (Object) notifyConfig.description) && Intrinsics.areEqual((Object) this.types, (Object) notifyConfig.types) && Intrinsics.areEqual((Object) this.imageUrl, (Object) notifyConfig.imageUrl) && this.isVerified == notifyConfig.isVerified;
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

    @Nullable
    public final ImageUrl getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final List<NotificationType> getTypes() {
        return this.types;
    }

    public int hashCode() {
        int j2 = a.j(this.types, a.i(this.description, a.i(this.homepage, a.i(this.name, this.dappUrl.hashCode() * 31, 31), 31), 31), 31);
        ImageUrl imageUrl2 = this.imageUrl;
        return Boolean.hashCode(this.isVerified) + ((j2 + (imageUrl2 == null ? 0 : imageUrl2.hashCode())) * 31);
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    @NotNull
    public String toString() {
        String str = this.dappUrl;
        String str2 = this.name;
        String str3 = this.homepage;
        String str4 = this.description;
        List<NotificationType> list = this.types;
        ImageUrl imageUrl2 = this.imageUrl;
        boolean z2 = this.isVerified;
        StringBuilder l2 = C0118y.l("NotifyConfig(dappUrl=", str, ", name=", str2, ", homepage=");
        b.w(l2, str3, ", description=", str4, ", types=");
        l2.append(list);
        l2.append(", imageUrl=");
        l2.append(imageUrl2);
        l2.append(", isVerified=");
        return android.support.v4.media.session.a.s(l2, z2, ")");
    }
}
