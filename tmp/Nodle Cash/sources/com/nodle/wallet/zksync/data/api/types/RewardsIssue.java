package com.nodle.wallet.zksync.data.api.types;

import io.zksync.crypto.eip712.Structurable;
import java.math.BigInteger;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f0\u000bH\u0016J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/types/RewardsIssue;", "Lio/zksync/crypto/eip712/Structurable;", "sequence", "Ljava/math/BigInteger;", "<init>", "(Ljava/math/BigInteger;)V", "getSequence", "()Ljava/math/BigInteger;", "getTypeName", "", "eip712types", "", "Lorg/apache/commons/lang3/tuple/Pair;", "Lorg/web3j/abi/datatypes/Type;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RewardsIssue implements Structurable {
    @NotNull
    private final BigInteger sequence;

    public RewardsIssue(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "sequence");
        this.sequence = bigInteger;
    }

    public static /* synthetic */ RewardsIssue copy$default(RewardsIssue rewardsIssue, BigInteger bigInteger, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bigInteger = rewardsIssue.sequence;
        }
        return rewardsIssue.copy(bigInteger);
    }

    @NotNull
    public final BigInteger component1() {
        return this.sequence;
    }

    @NotNull
    public final RewardsIssue copy(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "sequence");
        return new RewardsIssue(bigInteger);
    }

    @NotNull
    public List<Pair<String, Type<?>>> eip712types() {
        return CollectionsKt.listOf(Pair.of("sequence", new Uint256(this.sequence)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RewardsIssue) && Intrinsics.areEqual((Object) this.sequence, (Object) ((RewardsIssue) obj).sequence);
    }

    @NotNull
    public final BigInteger getSequence() {
        return this.sequence;
    }

    @NotNull
    public String getTypeName() {
        return "Issue";
    }

    public int hashCode() {
        return this.sequence.hashCode();
    }

    @NotNull
    public String toString() {
        BigInteger bigInteger = this.sequence;
        return "RewardsIssue(sequence=" + bigInteger + ")";
    }
}
