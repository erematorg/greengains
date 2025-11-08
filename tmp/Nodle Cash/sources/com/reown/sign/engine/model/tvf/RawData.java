package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003J\\\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\bHÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\u000fR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014¨\u0006&"}, d2 = {"Lcom/reown/sign/engine/model/tvf/RawData;", "", "expiration", "", "contract", "", "Lcom/reown/sign/engine/model/tvf/Contract;", "ref_block_hash", "", "fee_limit", "timestamp", "ref_block_bytes", "<init>", "(Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)V", "getExpiration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getContract", "()Ljava/util/List;", "getRef_block_hash", "()Ljava/lang/String;", "getFee_limit", "getTimestamp", "getRef_block_bytes", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lcom/reown/sign/engine/model/tvf/RawData;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RawData {
    @Nullable
    private final List<Contract> contract;
    @Nullable
    private final Long expiration;
    @Nullable
    private final Long fee_limit;
    @Nullable
    private final String ref_block_bytes;
    @Nullable
    private final String ref_block_hash;
    @Nullable
    private final Long timestamp;

    public RawData(@Nullable Long l2, @Nullable List<Contract> list, @Nullable String str, @Nullable Long l3, @Nullable Long l4, @Nullable String str2) {
        this.expiration = l2;
        this.contract = list;
        this.ref_block_hash = str;
        this.fee_limit = l3;
        this.timestamp = l4;
        this.ref_block_bytes = str2;
    }

    public static /* synthetic */ RawData copy$default(RawData rawData, Long l2, List<Contract> list, String str, Long l3, Long l4, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = rawData.expiration;
        }
        if ((i3 & 2) != 0) {
            list = rawData.contract;
        }
        List<Contract> list2 = list;
        if ((i3 & 4) != 0) {
            str = rawData.ref_block_hash;
        }
        String str3 = str;
        if ((i3 & 8) != 0) {
            l3 = rawData.fee_limit;
        }
        Long l5 = l3;
        if ((i3 & 16) != 0) {
            l4 = rawData.timestamp;
        }
        Long l6 = l4;
        if ((i3 & 32) != 0) {
            str2 = rawData.ref_block_bytes;
        }
        return rawData.copy(l2, list2, str3, l5, l6, str2);
    }

    @Nullable
    public final Long component1() {
        return this.expiration;
    }

    @Nullable
    public final List<Contract> component2() {
        return this.contract;
    }

    @Nullable
    public final String component3() {
        return this.ref_block_hash;
    }

    @Nullable
    public final Long component4() {
        return this.fee_limit;
    }

    @Nullable
    public final Long component5() {
        return this.timestamp;
    }

    @Nullable
    public final String component6() {
        return this.ref_block_bytes;
    }

    @NotNull
    public final RawData copy(@Nullable Long l2, @Nullable List<Contract> list, @Nullable String str, @Nullable Long l3, @Nullable Long l4, @Nullable String str2) {
        return new RawData(l2, list, str, l3, l4, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RawData)) {
            return false;
        }
        RawData rawData = (RawData) obj;
        return Intrinsics.areEqual((Object) this.expiration, (Object) rawData.expiration) && Intrinsics.areEqual((Object) this.contract, (Object) rawData.contract) && Intrinsics.areEqual((Object) this.ref_block_hash, (Object) rawData.ref_block_hash) && Intrinsics.areEqual((Object) this.fee_limit, (Object) rawData.fee_limit) && Intrinsics.areEqual((Object) this.timestamp, (Object) rawData.timestamp) && Intrinsics.areEqual((Object) this.ref_block_bytes, (Object) rawData.ref_block_bytes);
    }

    @Nullable
    public final List<Contract> getContract() {
        return this.contract;
    }

    @Nullable
    public final Long getExpiration() {
        return this.expiration;
    }

    @Nullable
    public final Long getFee_limit() {
        return this.fee_limit;
    }

    @Nullable
    public final String getRef_block_bytes() {
        return this.ref_block_bytes;
    }

    @Nullable
    public final String getRef_block_hash() {
        return this.ref_block_hash;
    }

    @Nullable
    public final Long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        Long l2 = this.expiration;
        int i3 = 0;
        int hashCode = (l2 == null ? 0 : l2.hashCode()) * 31;
        List<Contract> list = this.contract;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.ref_block_hash;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Long l3 = this.fee_limit;
        int hashCode4 = (hashCode3 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.timestamp;
        int hashCode5 = (hashCode4 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str2 = this.ref_block_bytes;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode5 + i3;
    }

    @NotNull
    public String toString() {
        Long l2 = this.expiration;
        List<Contract> list = this.contract;
        String str = this.ref_block_hash;
        Long l3 = this.fee_limit;
        Long l4 = this.timestamp;
        String str2 = this.ref_block_bytes;
        return "RawData(expiration=" + l2 + ", contract=" + list + ", ref_block_hash=" + str + ", fee_limit=" + l3 + ", timestamp=" + l4 + ", ref_block_bytes=" + str2 + ")";
    }
}
