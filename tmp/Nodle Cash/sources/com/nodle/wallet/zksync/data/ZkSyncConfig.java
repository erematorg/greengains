package com.nodle.wallet.zksync.data;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/nodle/wallet/zksync/data/ZkSyncConfig;", "", "nftContract", "", "zyfiPaymasterContract", "bridgeContract", "rewardsContract", "eraUrl", "chainId", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getNftContract", "()Ljava/lang/String;", "getZyfiPaymasterContract", "getBridgeContract", "getRewardsContract", "getEraUrl", "getChainId", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkSyncConfig {
    @NotNull
    private final String bridgeContract;
    private final long chainId;
    @NotNull
    private final String eraUrl;
    @NotNull
    private final String nftContract;
    @NotNull
    private final String rewardsContract;
    @NotNull
    private final String zyfiPaymasterContract;

    public ZkSyncConfig(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, long j2) {
        Intrinsics.checkNotNullParameter(str, "nftContract");
        Intrinsics.checkNotNullParameter(str2, "zyfiPaymasterContract");
        Intrinsics.checkNotNullParameter(str3, "bridgeContract");
        Intrinsics.checkNotNullParameter(str4, "rewardsContract");
        Intrinsics.checkNotNullParameter(str5, "eraUrl");
        this.nftContract = str;
        this.zyfiPaymasterContract = str2;
        this.bridgeContract = str3;
        this.rewardsContract = str4;
        this.eraUrl = str5;
        this.chainId = j2;
    }

    public static /* synthetic */ ZkSyncConfig copy$default(ZkSyncConfig zkSyncConfig, String str, String str2, String str3, String str4, String str5, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = zkSyncConfig.nftContract;
        }
        if ((i3 & 2) != 0) {
            str2 = zkSyncConfig.zyfiPaymasterContract;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = zkSyncConfig.bridgeContract;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = zkSyncConfig.rewardsContract;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            str5 = zkSyncConfig.eraUrl;
        }
        String str9 = str5;
        if ((i3 & 32) != 0) {
            j2 = zkSyncConfig.chainId;
        }
        return zkSyncConfig.copy(str, str6, str7, str8, str9, j2);
    }

    @NotNull
    public final String component1() {
        return this.nftContract;
    }

    @NotNull
    public final String component2() {
        return this.zyfiPaymasterContract;
    }

    @NotNull
    public final String component3() {
        return this.bridgeContract;
    }

    @NotNull
    public final String component4() {
        return this.rewardsContract;
    }

    @NotNull
    public final String component5() {
        return this.eraUrl;
    }

    public final long component6() {
        return this.chainId;
    }

    @NotNull
    public final ZkSyncConfig copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, long j2) {
        Intrinsics.checkNotNullParameter(str, "nftContract");
        Intrinsics.checkNotNullParameter(str2, "zyfiPaymasterContract");
        Intrinsics.checkNotNullParameter(str3, "bridgeContract");
        Intrinsics.checkNotNullParameter(str4, "rewardsContract");
        Intrinsics.checkNotNullParameter(str5, "eraUrl");
        return new ZkSyncConfig(str, str2, str3, str4, str5, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZkSyncConfig)) {
            return false;
        }
        ZkSyncConfig zkSyncConfig = (ZkSyncConfig) obj;
        return Intrinsics.areEqual((Object) this.nftContract, (Object) zkSyncConfig.nftContract) && Intrinsics.areEqual((Object) this.zyfiPaymasterContract, (Object) zkSyncConfig.zyfiPaymasterContract) && Intrinsics.areEqual((Object) this.bridgeContract, (Object) zkSyncConfig.bridgeContract) && Intrinsics.areEqual((Object) this.rewardsContract, (Object) zkSyncConfig.rewardsContract) && Intrinsics.areEqual((Object) this.eraUrl, (Object) zkSyncConfig.eraUrl) && this.chainId == zkSyncConfig.chainId;
    }

    @NotNull
    public final String getBridgeContract() {
        return this.bridgeContract;
    }

    public final long getChainId() {
        return this.chainId;
    }

    @NotNull
    public final String getEraUrl() {
        return this.eraUrl;
    }

    @NotNull
    public final String getNftContract() {
        return this.nftContract;
    }

    @NotNull
    public final String getRewardsContract() {
        return this.rewardsContract;
    }

    @NotNull
    public final String getZyfiPaymasterContract() {
        return this.zyfiPaymasterContract;
    }

    public int hashCode() {
        return Long.hashCode(this.chainId) + a.i(this.eraUrl, a.i(this.rewardsContract, a.i(this.bridgeContract, a.i(this.zyfiPaymasterContract, this.nftContract.hashCode() * 31, 31), 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.nftContract;
        String str2 = this.zyfiPaymasterContract;
        String str3 = this.bridgeContract;
        String str4 = this.rewardsContract;
        String str5 = this.eraUrl;
        long j2 = this.chainId;
        StringBuilder l2 = C0118y.l("ZkSyncConfig(nftContract=", str, ", zyfiPaymasterContract=", str2, ", bridgeContract=");
        b.w(l2, str3, ", rewardsContract=", str4, ", eraUrl=");
        l2.append(str5);
        l2.append(", chainId=");
        l2.append(j2);
        l2.append(")");
        return l2.toString();
    }
}
