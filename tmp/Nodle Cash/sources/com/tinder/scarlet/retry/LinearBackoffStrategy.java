package com.tinder.scarlet.retry;

import androidx.compose.ui.autofill.a;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/retry/LinearBackoffStrategy;", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "durationMillis", "", "(J)V", "getDurationMillis", "()J", "shouldBackoff", "", "getShouldBackoff", "()Z", "setShouldBackoff", "(Z)V", "backoffDurationMillisAt", "retryCount", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLinearBackoffStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LinearBackoffStrategy.kt\ncom/tinder/scarlet/retry/LinearBackoffStrategy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,19:1\n1#2:20\n*E\n"})
public final class LinearBackoffStrategy implements BackoffStrategy {
    private final long durationMillis;
    private boolean shouldBackoff;

    public LinearBackoffStrategy(long j2) {
        this.durationMillis = j2;
        if (j2 > 0) {
            this.shouldBackoff = true;
            return;
        }
        throw new IllegalArgumentException(a.j("durationMillis, ", j2, ", must be positive").toString());
    }

    public long backoffDurationMillisAt(int i3) {
        return this.durationMillis;
    }

    public final long getDurationMillis() {
        return this.durationMillis;
    }

    public boolean getShouldBackoff() {
        return this.shouldBackoff;
    }

    public void setShouldBackoff(boolean z2) {
        this.shouldBackoff = z2;
    }
}
