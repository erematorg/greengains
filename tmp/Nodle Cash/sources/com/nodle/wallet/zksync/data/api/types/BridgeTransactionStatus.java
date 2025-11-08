package com.nodle.wallet.zksync.data.api.types;

import A.a;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/types/BridgeTransactionStatus;", "", "proposal", "Lcom/nodle/wallet/zksync/data/api/types/Proposal;", "delay", "Ljava/math/BigInteger;", "currentBlock", "<init>", "(Lcom/nodle/wallet/zksync/data/api/types/Proposal;Ljava/math/BigInteger;Ljava/math/BigInteger;)V", "getProposal", "()Lcom/nodle/wallet/zksync/data/api/types/Proposal;", "getDelay", "()Ljava/math/BigInteger;", "getCurrentBlock", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BridgeTransactionStatus {
    @NotNull
    private final BigInteger currentBlock;
    @NotNull
    private final BigInteger delay;
    @NotNull
    private final Proposal proposal;

    public BridgeTransactionStatus(@NotNull Proposal proposal2, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(proposal2, "proposal");
        Intrinsics.checkNotNullParameter(bigInteger, "delay");
        Intrinsics.checkNotNullParameter(bigInteger2, "currentBlock");
        this.proposal = proposal2;
        this.delay = bigInteger;
        this.currentBlock = bigInteger2;
    }

    public static /* synthetic */ BridgeTransactionStatus copy$default(BridgeTransactionStatus bridgeTransactionStatus, Proposal proposal2, BigInteger bigInteger, BigInteger bigInteger2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            proposal2 = bridgeTransactionStatus.proposal;
        }
        if ((i3 & 2) != 0) {
            bigInteger = bridgeTransactionStatus.delay;
        }
        if ((i3 & 4) != 0) {
            bigInteger2 = bridgeTransactionStatus.currentBlock;
        }
        return bridgeTransactionStatus.copy(proposal2, bigInteger, bigInteger2);
    }

    @NotNull
    public final Proposal component1() {
        return this.proposal;
    }

    @NotNull
    public final BigInteger component2() {
        return this.delay;
    }

    @NotNull
    public final BigInteger component3() {
        return this.currentBlock;
    }

    @NotNull
    public final BridgeTransactionStatus copy(@NotNull Proposal proposal2, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(proposal2, "proposal");
        Intrinsics.checkNotNullParameter(bigInteger, "delay");
        Intrinsics.checkNotNullParameter(bigInteger2, "currentBlock");
        return new BridgeTransactionStatus(proposal2, bigInteger, bigInteger2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BridgeTransactionStatus)) {
            return false;
        }
        BridgeTransactionStatus bridgeTransactionStatus = (BridgeTransactionStatus) obj;
        return Intrinsics.areEqual((Object) this.proposal, (Object) bridgeTransactionStatus.proposal) && Intrinsics.areEqual((Object) this.delay, (Object) bridgeTransactionStatus.delay) && Intrinsics.areEqual((Object) this.currentBlock, (Object) bridgeTransactionStatus.currentBlock);
    }

    @NotNull
    public final BigInteger getCurrentBlock() {
        return this.currentBlock;
    }

    @NotNull
    public final BigInteger getDelay() {
        return this.delay;
    }

    @NotNull
    public final Proposal getProposal() {
        return this.proposal;
    }

    public int hashCode() {
        return this.currentBlock.hashCode() + a.c(this.delay, this.proposal.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        Proposal proposal2 = this.proposal;
        BigInteger bigInteger = this.delay;
        BigInteger bigInteger2 = this.currentBlock;
        return "BridgeTransactionStatus(proposal=" + proposal2 + ", delay=" + bigInteger + ", currentBlock=" + bigInteger2 + ")";
    }
}
