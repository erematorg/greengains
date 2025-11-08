package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/SupportedStandardDTO;", "", "id", "", "url", "title", "standardId", "", "standardPrefix", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUrl", "getTitle", "getStandardId", "()I", "getStandardPrefix", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SupportedStandardDTO {
    @NotNull
    private final String id;
    private final int standardId;
    @NotNull
    private final String standardPrefix;
    @NotNull
    private final String title;
    @NotNull
    private final String url;

    public SupportedStandardDTO(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "url") String str2, @NotNull @Json(name = "title") String str3, @Json(name = "standard_id") int i3, @NotNull @Json(name = "standard_prefix") String str4) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(str3, "title");
        Intrinsics.checkNotNullParameter(str4, "standardPrefix");
        this.id = str;
        this.url = str2;
        this.title = str3;
        this.standardId = i3;
        this.standardPrefix = str4;
    }

    public static /* synthetic */ SupportedStandardDTO copy$default(SupportedStandardDTO supportedStandardDTO, String str, String str2, String str3, int i3, String str4, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = supportedStandardDTO.id;
        }
        if ((i4 & 2) != 0) {
            str2 = supportedStandardDTO.url;
        }
        String str5 = str2;
        if ((i4 & 4) != 0) {
            str3 = supportedStandardDTO.title;
        }
        String str6 = str3;
        if ((i4 & 8) != 0) {
            i3 = supportedStandardDTO.standardId;
        }
        int i5 = i3;
        if ((i4 & 16) != 0) {
            str4 = supportedStandardDTO.standardPrefix;
        }
        return supportedStandardDTO.copy(str, str5, str6, i5, str4);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.url;
    }

    @NotNull
    public final String component3() {
        return this.title;
    }

    public final int component4() {
        return this.standardId;
    }

    @NotNull
    public final String component5() {
        return this.standardPrefix;
    }

    @NotNull
    public final SupportedStandardDTO copy(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "url") String str2, @NotNull @Json(name = "title") String str3, @Json(name = "standard_id") int i3, @NotNull @Json(name = "standard_prefix") String str4) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(str3, "title");
        Intrinsics.checkNotNullParameter(str4, "standardPrefix");
        return new SupportedStandardDTO(str, str2, str3, i3, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SupportedStandardDTO)) {
            return false;
        }
        SupportedStandardDTO supportedStandardDTO = (SupportedStandardDTO) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) supportedStandardDTO.id) && Intrinsics.areEqual((Object) this.url, (Object) supportedStandardDTO.url) && Intrinsics.areEqual((Object) this.title, (Object) supportedStandardDTO.title) && this.standardId == supportedStandardDTO.standardId && Intrinsics.areEqual((Object) this.standardPrefix, (Object) supportedStandardDTO.standardPrefix);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final int getStandardId() {
        return this.standardId;
    }

    @NotNull
    public final String getStandardPrefix() {
        return this.standardPrefix;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return this.standardPrefix.hashCode() + a.c(this.standardId, a.i(this.title, a.i(this.url, this.id.hashCode() * 31, 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.url;
        String str3 = this.title;
        int i3 = this.standardId;
        String str4 = this.standardPrefix;
        StringBuilder l2 = C0118y.l("SupportedStandardDTO(id=", str, ", url=", str2, ", title=");
        b.v(l2, str3, ", standardId=", i3, ", standardPrefix=");
        return A.a.n(l2, str4, ")");
    }
}
