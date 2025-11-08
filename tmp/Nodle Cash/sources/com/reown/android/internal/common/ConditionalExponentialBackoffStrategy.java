package com.reown.android.internal.common;

import androidx.compose.ui.autofill.a;
import com.reown.android.relay.ConnectionType;
import com.tinder.scarlet.retry.BackoffStrategy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\t\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/ConditionalExponentialBackoffStrategy;", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "initialDurationMillis", "", "maxDurationMillis", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "<init>", "(JJLcom/reown/android/relay/ConnectionType;)V", "shouldBackoff", "", "getShouldBackoff", "()Z", "setShouldBackoff", "(Z)V", "", "backoffDurationMillisAt", "retryCount", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConditionalExponentialBackoffStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConditionalExponentialBackoffStrategy.kt\ncom/reown/android/internal/common/ConditionalExponentialBackoffStrategy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,27:1\n1#2:28\n*E\n"})
public final class ConditionalExponentialBackoffStrategy implements BackoffStrategy {
    @NotNull
    private final ConnectionType connectionType;
    private final long initialDurationMillis;
    private final long maxDurationMillis;
    private boolean shouldBackoff;

    public ConditionalExponentialBackoffStrategy(long j2, long j3, @NotNull ConnectionType connectionType2) {
        Intrinsics.checkNotNullParameter(connectionType2, "connectionType");
        this.initialDurationMillis = j2;
        this.maxDurationMillis = j3;
        this.connectionType = connectionType2;
        if (j2 <= 0) {
            throw new IllegalArgumentException(a.j("initialDurationMillis, ", j2, ", must be positive").toString());
        } else if (j3 <= 0) {
            throw new IllegalArgumentException(a.j("maxDurationMillis, ", j3, ", must be positive").toString());
        }
    }

    public long backoffDurationMillisAt(int i3) {
        return (long) RangesKt.coerceAtMost((double) this.maxDurationMillis, Math.pow(2.0d, (double) i3) * ((double) this.initialDurationMillis));
    }

    public boolean getShouldBackoff() {
        return this.shouldBackoff;
    }

    public void setShouldBackoff(boolean z2) {
        this.shouldBackoff = z2;
    }

    public final void shouldBackoff(boolean z2) {
        if (this.connectionType != ConnectionType.MANUAL) {
            setShouldBackoff(z2);
        }
    }
}
