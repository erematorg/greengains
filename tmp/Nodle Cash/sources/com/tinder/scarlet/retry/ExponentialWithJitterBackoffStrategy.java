package com.tinder.scarlet.retry;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.LongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0005\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0002J\f\u0010\u0017\u001a\u00020\u0003*\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/tinder/scarlet/retry/ExponentialWithJitterBackoffStrategy;", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "baseDurationMillis", "", "maxDurationMillis", "random", "Ljava/util/Random;", "(JJLjava/util/Random;)V", "getBaseDurationMillis", "()J", "exponentialBackoffRetryStrategy", "Lcom/tinder/scarlet/retry/ExponentialBackoffStrategy;", "getMaxDurationMillis", "shouldBackoff", "", "getShouldBackoff", "()Z", "setShouldBackoff", "(Z)V", "backoffDurationMillisAt", "retryCount", "", "Lkotlin/ranges/ClosedRange;", "withJitter", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExponentialWithJitterBackoffStrategy implements BackoffStrategy {
    private final long baseDurationMillis;
    @NotNull
    private final ExponentialBackoffStrategy exponentialBackoffRetryStrategy;
    private final long maxDurationMillis;
    @NotNull
    private final Random random;
    private boolean shouldBackoff;

    public ExponentialWithJitterBackoffStrategy(long j2, long j3, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random2, "random");
        this.baseDurationMillis = j2;
        this.maxDurationMillis = j3;
        this.random = random2;
        this.exponentialBackoffRetryStrategy = new ExponentialBackoffStrategy(j2, j3);
        this.shouldBackoff = true;
    }

    private final long random(ClosedRange<Long> closedRange) {
        return closedRange.getStart().longValue() + ((long) this.random.nextInt((int) (closedRange.getEndInclusive().longValue() - closedRange.getStart().longValue())));
    }

    private final long withJitter(long j2) {
        return random(new LongRange(0, j2));
    }

    public long backoffDurationMillisAt(int i3) {
        long backoffDurationMillisAt = this.exponentialBackoffRetryStrategy.backoffDurationMillisAt(i3);
        return backoffDurationMillisAt == this.maxDurationMillis ? backoffDurationMillisAt : withJitter(backoffDurationMillisAt);
    }

    public final long getBaseDurationMillis() {
        return this.baseDurationMillis;
    }

    public final long getMaxDurationMillis() {
        return this.maxDurationMillis;
    }

    public boolean getShouldBackoff() {
        return this.shouldBackoff;
    }

    public void setShouldBackoff(boolean z2) {
        this.shouldBackoff = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExponentialWithJitterBackoffStrategy(long j2, long j3, Random random2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3, (i3 & 4) != 0 ? new Random() : random2);
    }
}
