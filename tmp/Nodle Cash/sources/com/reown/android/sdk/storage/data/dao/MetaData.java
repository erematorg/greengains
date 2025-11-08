package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import app.cash.sqldelight.ColumnAdapter;
import com.reown.android.internal.common.model.AppMetaDataType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u00014Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010!J~\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!¨\u00065"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/MetaData;", "", "id", "", "sequence_topic", "", "name", "description", "url", "icons", "", "native", "type", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "app_link", "link_mode", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getSequence_topic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getNative", "getType", "()Lcom/reown/android/internal/common/model/AppMetaDataType;", "getApp_link", "getLink_mode", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/sdk/storage/data/dao/MetaData;", "equals", "other", "hashCode", "", "toString", "Adapter", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MetaData {
    @Nullable
    private final String app_link;
    @NotNull
    private final String description;
    @NotNull
    private final List<String> icons;
    private final long id;
    @Nullable
    private final Boolean link_mode;
    @NotNull
    private final String name;
    @Nullable

    /* renamed from: native  reason: not valid java name */
    private final String f77native;
    @NotNull
    private final String sequence_topic;
    @NotNull
    private final AppMetaDataType type;
    @NotNull
    private final String url;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B5\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\b\u0010\tR#\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "", "iconsAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "typeAdapter", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;)V", "getIconsAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "getTypeAdapter", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<List<String>, String> iconsAdapter;
        @NotNull
        private final ColumnAdapter<AppMetaDataType, String> typeAdapter;

        public Adapter(@NotNull ColumnAdapter<List<String>, String> columnAdapter, @NotNull ColumnAdapter<AppMetaDataType, String> columnAdapter2) {
            Intrinsics.checkNotNullParameter(columnAdapter, "iconsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter2, "typeAdapter");
            this.iconsAdapter = columnAdapter;
            this.typeAdapter = columnAdapter2;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getIconsAdapter() {
            return this.iconsAdapter;
        }

        @NotNull
        public final ColumnAdapter<AppMetaDataType, String> getTypeAdapter() {
            return this.typeAdapter;
        }
    }

    public MetaData(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @Nullable String str5, @NotNull AppMetaDataType appMetaDataType, @Nullable String str6, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        this.id = j2;
        this.sequence_topic = str;
        this.name = str2;
        this.description = str3;
        this.url = str4;
        this.icons = list;
        this.f77native = str5;
        this.type = appMetaDataType;
        this.app_link = str6;
        this.link_mode = bool;
    }

    public static /* synthetic */ MetaData copy$default(MetaData metaData, long j2, String str, String str2, String str3, String str4, List list, String str5, AppMetaDataType appMetaDataType, String str6, Boolean bool, int i3, Object obj) {
        MetaData metaData2 = metaData;
        int i4 = i3;
        return metaData.copy((i4 & 1) != 0 ? metaData2.id : j2, (i4 & 2) != 0 ? metaData2.sequence_topic : str, (i4 & 4) != 0 ? metaData2.name : str2, (i4 & 8) != 0 ? metaData2.description : str3, (i4 & 16) != 0 ? metaData2.url : str4, (i4 & 32) != 0 ? metaData2.icons : list, (i4 & 64) != 0 ? metaData2.f77native : str5, (i4 & 128) != 0 ? metaData2.type : appMetaDataType, (i4 & 256) != 0 ? metaData2.app_link : str6, (i4 & 512) != 0 ? metaData2.link_mode : bool);
    }

    public final long component1() {
        return this.id;
    }

    @Nullable
    public final Boolean component10() {
        return this.link_mode;
    }

    @NotNull
    public final String component2() {
        return this.sequence_topic;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final String component4() {
        return this.description;
    }

    @NotNull
    public final String component5() {
        return this.url;
    }

    @NotNull
    public final List<String> component6() {
        return this.icons;
    }

    @Nullable
    public final String component7() {
        return this.f77native;
    }

    @NotNull
    public final AppMetaDataType component8() {
        return this.type;
    }

    @Nullable
    public final String component9() {
        return this.app_link;
    }

    @NotNull
    public final MetaData copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @Nullable String str5, @NotNull AppMetaDataType appMetaDataType, @Nullable String str6, @Nullable Boolean bool) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, "sequence_topic");
        String str8 = str2;
        Intrinsics.checkNotNullParameter(str8, "name");
        String str9 = str3;
        Intrinsics.checkNotNullParameter(str9, "description");
        String str10 = str4;
        Intrinsics.checkNotNullParameter(str10, "url");
        List<String> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        AppMetaDataType appMetaDataType2 = appMetaDataType;
        Intrinsics.checkNotNullParameter(appMetaDataType2, "type");
        return new MetaData(j2, str7, str8, str9, str10, list2, str5, appMetaDataType2, str6, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetaData)) {
            return false;
        }
        MetaData metaData = (MetaData) obj;
        return this.id == metaData.id && Intrinsics.areEqual((Object) this.sequence_topic, (Object) metaData.sequence_topic) && Intrinsics.areEqual((Object) this.name, (Object) metaData.name) && Intrinsics.areEqual((Object) this.description, (Object) metaData.description) && Intrinsics.areEqual((Object) this.url, (Object) metaData.url) && Intrinsics.areEqual((Object) this.icons, (Object) metaData.icons) && Intrinsics.areEqual((Object) this.f77native, (Object) metaData.f77native) && this.type == metaData.type && Intrinsics.areEqual((Object) this.app_link, (Object) metaData.app_link) && Intrinsics.areEqual((Object) this.link_mode, (Object) metaData.link_mode);
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

    public final long getId() {
        return this.id;
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
        return this.f77native;
    }

    @NotNull
    public final String getSequence_topic() {
        return this.sequence_topic;
    }

    @NotNull
    public final AppMetaDataType getType() {
        return this.type;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int j2 = a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, a.i(this.sequence_topic, Long.hashCode(this.id) * 31, 31), 31), 31), 31), 31);
        String str = this.f77native;
        int i3 = 0;
        int hashCode = (this.type.hashCode() + ((j2 + (str == null ? 0 : str.hashCode())) * 31)) * 31;
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
        long j2 = this.id;
        String str = this.sequence_topic;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.url;
        List<String> list = this.icons;
        String str5 = this.f77native;
        AppMetaDataType appMetaDataType = this.type;
        String str6 = this.app_link;
        Boolean bool = this.link_mode;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "MetaData(id=", ", sequence_topic=", str);
        b.w(v2, ", name=", str2, ", description=", str3);
        v2.append(", url=");
        v2.append(str4);
        v2.append(", icons=");
        v2.append(list);
        v2.append(", native=");
        v2.append(str5);
        v2.append(", type=");
        v2.append(appMetaDataType);
        v2.append(", app_link=");
        v2.append(str6);
        v2.append(", link_mode=");
        v2.append(bool);
        v2.append(")");
        return v2.toString();
    }
}
