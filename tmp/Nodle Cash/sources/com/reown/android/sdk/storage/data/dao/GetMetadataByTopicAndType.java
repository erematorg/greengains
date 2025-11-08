package com.reown.android.sdk.storage.data.dao;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017J`\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/GetMetadataByTopicAndType;", "", "name", "", "description", "url", "icons", "", "native", "app_link", "link_mode", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getName", "()Ljava/lang/String;", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getNative", "getApp_link", "getLink_mode", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/sdk/storage/data/dao/GetMetadataByTopicAndType;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetMetadataByTopicAndType {
    @Nullable
    private final String app_link;
    @NotNull
    private final String description;
    @NotNull
    private final List<String> icons;
    @Nullable
    private final Boolean link_mode;
    @NotNull
    private final String name;
    @Nullable

    /* renamed from: native  reason: not valid java name */
    private final String f75native;
    @NotNull
    private final String url;

    public GetMetadataByTopicAndType(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "description");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        this.name = str;
        this.description = str2;
        this.url = str3;
        this.icons = list;
        this.f75native = str4;
        this.app_link = str5;
        this.link_mode = bool;
    }

    public static /* synthetic */ GetMetadataByTopicAndType copy$default(GetMetadataByTopicAndType getMetadataByTopicAndType, String str, String str2, String str3, List<String> list, String str4, String str5, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = getMetadataByTopicAndType.name;
        }
        if ((i3 & 2) != 0) {
            str2 = getMetadataByTopicAndType.description;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = getMetadataByTopicAndType.url;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            list = getMetadataByTopicAndType.icons;
        }
        List<String> list2 = list;
        if ((i3 & 16) != 0) {
            str4 = getMetadataByTopicAndType.f75native;
        }
        String str8 = str4;
        if ((i3 & 32) != 0) {
            str5 = getMetadataByTopicAndType.app_link;
        }
        String str9 = str5;
        if ((i3 & 64) != 0) {
            bool = getMetadataByTopicAndType.link_mode;
        }
        return getMetadataByTopicAndType.copy(str, str6, str7, list2, str8, str9, bool);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    @NotNull
    public final String component3() {
        return this.url;
    }

    @NotNull
    public final List<String> component4() {
        return this.icons;
    }

    @Nullable
    public final String component5() {
        return this.f75native;
    }

    @Nullable
    public final String component6() {
        return this.app_link;
    }

    @Nullable
    public final Boolean component7() {
        return this.link_mode;
    }

    @NotNull
    public final GetMetadataByTopicAndType copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "description");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        return new GetMetadataByTopicAndType(str, str2, str3, list, str4, str5, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetMetadataByTopicAndType)) {
            return false;
        }
        GetMetadataByTopicAndType getMetadataByTopicAndType = (GetMetadataByTopicAndType) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) getMetadataByTopicAndType.name) && Intrinsics.areEqual((Object) this.description, (Object) getMetadataByTopicAndType.description) && Intrinsics.areEqual((Object) this.url, (Object) getMetadataByTopicAndType.url) && Intrinsics.areEqual((Object) this.icons, (Object) getMetadataByTopicAndType.icons) && Intrinsics.areEqual((Object) this.f75native, (Object) getMetadataByTopicAndType.f75native) && Intrinsics.areEqual((Object) this.app_link, (Object) getMetadataByTopicAndType.app_link) && Intrinsics.areEqual((Object) this.link_mode, (Object) getMetadataByTopicAndType.link_mode);
    }

    @Nullable
    public final String getApp_link() {
        return this.app_link;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final List<String> getIcons() {
        return this.icons;
    }

    @Nullable
    public final Boolean getLink_mode() {
        return this.link_mode;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getNative() {
        return this.f75native;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int j2 = a.j(this.icons, a.i(this.url, a.i(this.description, this.name.hashCode() * 31, 31), 31), 31);
        String str = this.f75native;
        int i3 = 0;
        int hashCode = (j2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.app_link;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.link_mode;
        if (bool != null) {
            i3 = bool.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.description;
        String str3 = this.url;
        List<String> list = this.icons;
        String str4 = this.f75native;
        String str5 = this.app_link;
        Boolean bool = this.link_mode;
        StringBuilder l2 = C0118y.l("GetMetadataByTopicAndType(name=", str, ", description=", str2, ", url=");
        l2.append(str3);
        l2.append(", icons=");
        l2.append(list);
        l2.append(", native=");
        b.w(l2, str4, ", app_link=", str5, ", link_mode=");
        l2.append(bool);
        l2.append(")");
        return l2.toString();
    }
}
