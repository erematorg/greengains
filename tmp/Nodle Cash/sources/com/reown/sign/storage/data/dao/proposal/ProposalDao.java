package com.reown.sign.storage.data.dao.proposal;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import app.cash.sqldelight.ColumnAdapter;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001=B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\u0017\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010%J\u0017\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J¶\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\t\u0010<\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u001f\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"¨\u0006>"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposal/ProposalDao;", "", "request_id", "", "pairingTopic", "", "name", "description", "url", "icons", "", "relay_protocol", "relay_data", "proposer_key", "properties", "", "redirect", "expiry", "scoped_properties", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)V", "getRequest_id", "()J", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRelay_protocol", "getRelay_data", "getProposer_key", "getProperties", "()Ljava/util/Map;", "getRedirect", "getExpiry", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getScoped_properties", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)Lcom/reown/sign/storage/data/dao/proposal/ProposalDao;", "equals", "", "other", "hashCode", "", "toString", "Adapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProposalDao {
    @NotNull
    private final String description;
    @Nullable
    private final Long expiry;
    @NotNull
    private final List<String> icons;
    @NotNull
    private final String name;
    @NotNull
    private final String pairingTopic;
    @Nullable
    private final Map<String, String> properties;
    @NotNull
    private final String proposer_key;
    @NotNull
    private final String redirect;
    @Nullable
    private final String relay_data;
    @NotNull
    private final String relay_protocol;
    private final long request_id;
    @Nullable
    private final Map<String, String> scoped_properties;
    @NotNull
    private final String url;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\b\u0018\u00002\u00020\u0001Ba\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u001e\u0010\b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nR#\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR)\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR)\u0010\b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;", "", "iconsAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "propertiesAdapter", "", "scoped_propertiesAdapter", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;Lapp/cash/sqldelight/ColumnAdapter;)V", "getIconsAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "getPropertiesAdapter", "getScoped_propertiesAdapter", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<List<String>, String> iconsAdapter;
        @NotNull
        private final ColumnAdapter<Map<String, String>, String> propertiesAdapter;
        @NotNull
        private final ColumnAdapter<Map<String, String>, String> scoped_propertiesAdapter;

        public Adapter(@NotNull ColumnAdapter<List<String>, String> columnAdapter, @NotNull ColumnAdapter<Map<String, String>, String> columnAdapter2, @NotNull ColumnAdapter<Map<String, String>, String> columnAdapter3) {
            Intrinsics.checkNotNullParameter(columnAdapter, "iconsAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter2, "propertiesAdapter");
            Intrinsics.checkNotNullParameter(columnAdapter3, "scoped_propertiesAdapter");
            this.iconsAdapter = columnAdapter;
            this.propertiesAdapter = columnAdapter2;
            this.scoped_propertiesAdapter = columnAdapter3;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getIconsAdapter() {
            return this.iconsAdapter;
        }

        @NotNull
        public final ColumnAdapter<Map<String, String>, String> getPropertiesAdapter() {
            return this.propertiesAdapter;
        }

        @NotNull
        public final ColumnAdapter<Map<String, String>, String> getScoped_propertiesAdapter() {
            return this.scoped_propertiesAdapter;
        }
    }

    public ProposalDao(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @Nullable String str6, @NotNull String str7, @Nullable Map<String, String> map, @NotNull String str8, @Nullable Long l2, @Nullable Map<String, String> map2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        Intrinsics.checkNotNullParameter(str5, "relay_protocol");
        Intrinsics.checkNotNullParameter(str7, "proposer_key");
        Intrinsics.checkNotNullParameter(str8, "redirect");
        this.request_id = j2;
        this.pairingTopic = str;
        this.name = str2;
        this.description = str3;
        this.url = str4;
        this.icons = list;
        this.relay_protocol = str5;
        this.relay_data = str6;
        this.proposer_key = str7;
        this.properties = map;
        this.redirect = str8;
        this.expiry = l2;
        this.scoped_properties = map2;
    }

    public static /* synthetic */ ProposalDao copy$default(ProposalDao proposalDao, long j2, String str, String str2, String str3, String str4, List list, String str5, String str6, String str7, Map map, String str8, Long l2, Map map2, int i3, Object obj) {
        ProposalDao proposalDao2 = proposalDao;
        int i4 = i3;
        return proposalDao.copy((i4 & 1) != 0 ? proposalDao2.request_id : j2, (i4 & 2) != 0 ? proposalDao2.pairingTopic : str, (i4 & 4) != 0 ? proposalDao2.name : str2, (i4 & 8) != 0 ? proposalDao2.description : str3, (i4 & 16) != 0 ? proposalDao2.url : str4, (i4 & 32) != 0 ? proposalDao2.icons : list, (i4 & 64) != 0 ? proposalDao2.relay_protocol : str5, (i4 & 128) != 0 ? proposalDao2.relay_data : str6, (i4 & 256) != 0 ? proposalDao2.proposer_key : str7, (i4 & 512) != 0 ? proposalDao2.properties : map, (i4 & 1024) != 0 ? proposalDao2.redirect : str8, (i4 & 2048) != 0 ? proposalDao2.expiry : l2, (i4 & 4096) != 0 ? proposalDao2.scoped_properties : map2);
    }

    public final long component1() {
        return this.request_id;
    }

    @Nullable
    public final Map<String, String> component10() {
        return this.properties;
    }

    @NotNull
    public final String component11() {
        return this.redirect;
    }

    @Nullable
    public final Long component12() {
        return this.expiry;
    }

    @Nullable
    public final Map<String, String> component13() {
        return this.scoped_properties;
    }

    @NotNull
    public final String component2() {
        return this.pairingTopic;
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

    @NotNull
    public final String component7() {
        return this.relay_protocol;
    }

    @Nullable
    public final String component8() {
        return this.relay_data;
    }

    @NotNull
    public final String component9() {
        return this.proposer_key;
    }

    @NotNull
    public final ProposalDao copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @Nullable String str6, @NotNull String str7, @Nullable Map<String, String> map, @NotNull String str8, @Nullable Long l2, @Nullable Map<String, String> map2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "pairingTopic");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "name");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "description");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "url");
        List<String> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "relay_protocol");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "proposer_key");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "redirect");
        return new ProposalDao(j2, str9, str10, str11, str12, list2, str13, str6, str14, map, str15, l2, map2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProposalDao)) {
            return false;
        }
        ProposalDao proposalDao = (ProposalDao) obj;
        return this.request_id == proposalDao.request_id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) proposalDao.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) proposalDao.name) && Intrinsics.areEqual((Object) this.description, (Object) proposalDao.description) && Intrinsics.areEqual((Object) this.url, (Object) proposalDao.url) && Intrinsics.areEqual((Object) this.icons, (Object) proposalDao.icons) && Intrinsics.areEqual((Object) this.relay_protocol, (Object) proposalDao.relay_protocol) && Intrinsics.areEqual((Object) this.relay_data, (Object) proposalDao.relay_data) && Intrinsics.areEqual((Object) this.proposer_key, (Object) proposalDao.proposer_key) && Intrinsics.areEqual((Object) this.properties, (Object) proposalDao.properties) && Intrinsics.areEqual((Object) this.redirect, (Object) proposalDao.redirect) && Intrinsics.areEqual((Object) this.expiry, (Object) proposalDao.expiry) && Intrinsics.areEqual((Object) this.scoped_properties, (Object) proposalDao.scoped_properties);
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final Long getExpiry() {
        return this.expiry;
    }

    @NotNull
    public final List<String> getIcons() {
        return this.icons;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPairingTopic() {
        return this.pairingTopic;
    }

    @Nullable
    public final Map<String, String> getProperties() {
        return this.properties;
    }

    @NotNull
    public final String getProposer_key() {
        return this.proposer_key;
    }

    @NotNull
    public final String getRedirect() {
        return this.redirect;
    }

    @Nullable
    public final String getRelay_data() {
        return this.relay_data;
    }

    @NotNull
    public final String getRelay_protocol() {
        return this.relay_protocol;
    }

    public final long getRequest_id() {
        return this.request_id;
    }

    @Nullable
    public final Map<String, String> getScoped_properties() {
        return this.scoped_properties;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i3 = a.i(this.relay_protocol, a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, a.i(this.pairingTopic, Long.hashCode(this.request_id) * 31, 31), 31), 31), 31), 31), 31);
        String str = this.relay_data;
        int i4 = 0;
        int i5 = a.i(this.proposer_key, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
        Map<String, String> map = this.properties;
        int i6 = a.i(this.redirect, (i5 + (map == null ? 0 : map.hashCode())) * 31, 31);
        Long l2 = this.expiry;
        int hashCode = (i6 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Map<String, String> map2 = this.scoped_properties;
        if (map2 != null) {
            i4 = map2.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        long j2 = this.request_id;
        String str = this.pairingTopic;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.url;
        List<String> list = this.icons;
        String str5 = this.relay_protocol;
        String str6 = this.relay_data;
        String str7 = this.proposer_key;
        Map<String, String> map = this.properties;
        String str8 = this.redirect;
        Long l2 = this.expiry;
        Map<String, String> map2 = this.scoped_properties;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "ProposalDao(request_id=", ", pairingTopic=", str);
        b.w(v2, ", name=", str2, ", description=", str3);
        v2.append(", url=");
        v2.append(str4);
        v2.append(", icons=");
        v2.append(list);
        b.w(v2, ", relay_protocol=", str5, ", relay_data=", str6);
        v2.append(", proposer_key=");
        v2.append(str7);
        v2.append(", properties=");
        v2.append(map);
        v2.append(", redirect=");
        v2.append(str8);
        v2.append(", expiry=");
        v2.append(l2);
        v2.append(", scoped_properties=");
        v2.append(map2);
        v2.append(")");
        return v2.toString();
    }
}
