package com.reown.android.relay;

import C1.C0233a;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\u00038BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\t¨\u0006\u001b"}, d2 = {"Lcom/reown/android/relay/NetworkClientTimeout;", "", "timeout", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "<init>", "(JLjava/util/concurrent/TimeUnit;)V", "getTimeout", "()J", "getTimeUnit", "()Ljava/util/concurrent/TimeUnit;", "timeoutInMillis", "getTimeoutInMillis", "timeoutInMillis$delegate", "Lkotlin/Lazy;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NetworkClientTimeout {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long MAX_TIMEOUT_LIMIT = 60000;
    private static final long MIN_TIMEOUT_LIMIT = 15000;
    @NotNull
    private final TimeUnit timeUnit;
    private final long timeout;
    @NotNull
    private final Lazy timeoutInMillis$delegate = LazyKt.lazy(new C0233a(this, 12));

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/reown/android/relay/NetworkClientTimeout$Companion;", "", "<init>", "()V", "MIN_TIMEOUT_LIMIT", "", "MAX_TIMEOUT_LIMIT", "getDefaultNetworkTimeout", "Lcom/reown/android/relay/NetworkClientTimeout;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NetworkClientTimeout getDefaultNetworkTimeout() {
            return new NetworkClientTimeout(15000, TimeUnit.MILLISECONDS);
        }

        private Companion() {
        }
    }

    public NetworkClientTimeout(long j2, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit2, "timeUnit");
        this.timeout = j2;
        this.timeUnit = timeUnit2;
        long timeoutInMillis = getTimeoutInMillis();
        if (15000 > timeoutInMillis || timeoutInMillis >= 60001) {
            throw new IllegalArgumentException("Timeout must be in range of 15000..60000 milliseconds");
        }
    }

    public static /* synthetic */ NetworkClientTimeout copy$default(NetworkClientTimeout networkClientTimeout, long j2, TimeUnit timeUnit2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = networkClientTimeout.timeout;
        }
        if ((i3 & 2) != 0) {
            timeUnit2 = networkClientTimeout.timeUnit;
        }
        return networkClientTimeout.copy(j2, timeUnit2);
    }

    private final long getTimeoutInMillis() {
        return ((Number) this.timeoutInMillis$delegate.getValue()).longValue();
    }

    /* access modifiers changed from: private */
    public static final long timeoutInMillis_delegate$lambda$0(NetworkClientTimeout networkClientTimeout) {
        return TimeUnit.MILLISECONDS.convert(networkClientTimeout.timeout, networkClientTimeout.timeUnit);
    }

    public final long component1() {
        return this.timeout;
    }

    @NotNull
    public final TimeUnit component2() {
        return this.timeUnit;
    }

    @NotNull
    public final NetworkClientTimeout copy(long j2, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit2, "timeUnit");
        return new NetworkClientTimeout(j2, timeUnit2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkClientTimeout)) {
            return false;
        }
        NetworkClientTimeout networkClientTimeout = (NetworkClientTimeout) obj;
        return this.timeout == networkClientTimeout.timeout && this.timeUnit == networkClientTimeout.timeUnit;
    }

    @NotNull
    public final TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public int hashCode() {
        return this.timeUnit.hashCode() + (Long.hashCode(this.timeout) * 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.timeout;
        TimeUnit timeUnit2 = this.timeUnit;
        return "NetworkClientTimeout(timeout=" + j2 + ", timeUnit=" + timeUnit2 + ")";
    }
}
