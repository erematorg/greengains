package com.tinder.scarlet.retry;

import androidx.compose.ui.autofill.a;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/retry/ExponentialBackoffStrategy;", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "initialDurationMillis", "", "maxDurationMillis", "(JJ)V", "getInitialDurationMillis", "()J", "getMaxDurationMillis", "shouldBackoff", "", "getShouldBackoff", "()Z", "setShouldBackoff", "(Z)V", "backoffDurationMillisAt", "retryCount", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExponentialBackoffStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExponentialBackoffStrategy.kt\ncom/tinder/scarlet/retry/ExponentialBackoffStrategy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,27:1\n1#2:28\n*E\n"})
public final class ExponentialBackoffStrategy implements BackoffStrategy {
    private final long initialDurationMillis;
    private final long maxDurationMillis;
    private boolean shouldBackoff;

    public ExponentialBackoffStrategy(long j2, long j3) {
        this.initialDurationMillis = j2;
        this.maxDurationMillis = j3;
        if (j2 <= 0) {
            throw new IllegalArgumentException(a.j("initialDurationMillis, ", j2, ", must be positive").toString());
        } else if (j3 > 0) {
            this.shouldBackoff = true;
        } else {
            throw new IllegalArgumentException(a.j("maxDurationMillis, ", j3, ", must be positive").toString());
        }
    }

    public long backoffDurationMillisAt(int i3) {
        return (long) Math.min((double) this.maxDurationMillis, Math.pow(2.0d, (double) i3) * ((double) this.initialDurationMillis));
    }

    public final long getInitialDurationMillis() {
        return this.initialDurationMillis;
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
}
