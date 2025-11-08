package com.nodle.wallet.zksync.data.api.types;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\bHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/nodle/wallet/zksync/data/api/types/Proposal;", "", "target", "", "amount", "Ljava/math/BigInteger;", "lastVote", "totalVotes", "", "executed", "", "<init>", "(Ljava/lang/String;Ljava/math/BigInteger;Ljava/math/BigInteger;IZ)V", "getTarget", "()Ljava/lang/String;", "getAmount", "()Ljava/math/BigInteger;", "getLastVote", "getTotalVotes", "()I", "getExecuted", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Proposal {
    @NotNull
    private final BigInteger amount;
    private final boolean executed;
    @NotNull
    private final BigInteger lastVote;
    @NotNull
    private final String target;
    private final int totalVotes;

    public Proposal(@NotNull String str, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2, int i3, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TypedValues.AttributesType.S_TARGET);
        Intrinsics.checkNotNullParameter(bigInteger, "amount");
        Intrinsics.checkNotNullParameter(bigInteger2, "lastVote");
        this.target = str;
        this.amount = bigInteger;
        this.lastVote = bigInteger2;
        this.totalVotes = i3;
        this.executed = z2;
    }

    public static /* synthetic */ Proposal copy$default(Proposal proposal, String str, BigInteger bigInteger, BigInteger bigInteger2, int i3, boolean z2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = proposal.target;
        }
        if ((i4 & 2) != 0) {
            bigInteger = proposal.amount;
        }
        BigInteger bigInteger3 = bigInteger;
        if ((i4 & 4) != 0) {
            bigInteger2 = proposal.lastVote;
        }
        BigInteger bigInteger4 = bigInteger2;
        if ((i4 & 8) != 0) {
            i3 = proposal.totalVotes;
        }
        int i5 = i3;
        if ((i4 & 16) != 0) {
            z2 = proposal.executed;
        }
        return proposal.copy(str, bigInteger3, bigInteger4, i5, z2);
    }

    @NotNull
    public final String component1() {
        return this.target;
    }

    @NotNull
    public final BigInteger component2() {
        return this.amount;
    }

    @NotNull
    public final BigInteger component3() {
        return this.lastVote;
    }

    public final int component4() {
        return this.totalVotes;
    }

    public final boolean component5() {
        return this.executed;
    }

    @NotNull
    public final Proposal copy(@NotNull String str, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2, int i3, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TypedValues.AttributesType.S_TARGET);
        Intrinsics.checkNotNullParameter(bigInteger, "amount");
        Intrinsics.checkNotNullParameter(bigInteger2, "lastVote");
        return new Proposal(str, bigInteger, bigInteger2, i3, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Proposal)) {
            return false;
        }
        Proposal proposal = (Proposal) obj;
        return Intrinsics.areEqual((Object) this.target, (Object) proposal.target) && Intrinsics.areEqual((Object) this.amount, (Object) proposal.amount) && Intrinsics.areEqual((Object) this.lastVote, (Object) proposal.lastVote) && this.totalVotes == proposal.totalVotes && this.executed == proposal.executed;
    }

    @NotNull
    public final BigInteger getAmount() {
        return this.amount;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    @NotNull
    public final BigInteger getLastVote() {
        return this.lastVote;
    }

    @NotNull
    public final String getTarget() {
        return this.target;
    }

    public final int getTotalVotes() {
        return this.totalVotes;
    }

    public int hashCode() {
        return Boolean.hashCode(this.executed) + a.c(this.totalVotes, A.a.c(this.lastVote, A.a.c(this.amount, this.target.hashCode() * 31, 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.target;
        BigInteger bigInteger = this.amount;
        BigInteger bigInteger2 = this.lastVote;
        int i3 = this.totalVotes;
        boolean z2 = this.executed;
        StringBuilder sb = new StringBuilder("Proposal(target=");
        sb.append(str);
        sb.append(", amount=");
        sb.append(bigInteger);
        sb.append(", lastVote=");
        sb.append(bigInteger2);
        sb.append(", totalVotes=");
        sb.append(i3);
        sb.append(", executed=");
        return android.support.v4.media.session.a.s(sb, z2, ")");
    }
}
