package com.google.common.cache;

import android.support.v4.media.session.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LocalCache;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class CacheBuilder<K, V> {
    static final Supplier<AbstractCache.StatsCounter> CACHE_STATS_COUNTER = new Supplier<AbstractCache.StatsCounter>() {
        public AbstractCache.StatsCounter get() {
            return new AbstractCache.SimpleStatsCounter();
        }
    };
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_REFRESH_NANOS = 0;
    static final CacheStats EMPTY_STATS = new CacheStats(0, 0, 0, 0, 0, 0);
    static final Supplier<? extends AbstractCache.StatsCounter> NULL_STATS_COUNTER = Suppliers.ofInstance(new AbstractCache.StatsCounter() {
        public void recordEviction() {
        }

        public void recordHits(int i3) {
        }

        public void recordLoadException(long j2) {
        }

        public void recordLoadSuccess(long j2) {
        }

        public void recordMisses(int i3) {
        }

        public CacheStats snapshot() {
            return CacheBuilder.EMPTY_STATS;
        }
    });
    static final Ticker NULL_TICKER = new Ticker() {
        public long read() {
            return 0;
        }
    };
    static final int UNSET_INT = -1;
    int concurrencyLevel = -1;
    long expireAfterAccessNanos = -1;
    long expireAfterWriteNanos = -1;
    int initialCapacity = -1;
    @CheckForNull
    Equivalence<Object> keyEquivalence;
    @CheckForNull
    LocalCache.Strength keyStrength;
    long maximumSize = -1;
    long maximumWeight = -1;
    long refreshNanos = -1;
    @CheckForNull
    RemovalListener<? super K, ? super V> removalListener;
    Supplier<? extends AbstractCache.StatsCounter> statsCounterSupplier = NULL_STATS_COUNTER;
    boolean strictParsing = true;
    @CheckForNull
    Ticker ticker;
    @CheckForNull
    Equivalence<Object> valueEquivalence;
    @CheckForNull
    LocalCache.Strength valueStrength;
    @CheckForNull
    Weigher<? super K, ? super V> weigher;

    public static final class LoggerHolder {
        static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());

        private LoggerHolder() {
        }
    }

    public enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    public enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    private CacheBuilder() {
    }

    private void checkNonLoadingCache() {
        Preconditions.checkState(this.refreshNanos == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void checkWeightWithWeigher() {
        boolean z2 = false;
        if (this.weigher == null) {
            if (this.maximumWeight == -1) {
                z2 = true;
            }
            Preconditions.checkState(z2, "maximumWeight requires weigher");
        } else if (this.strictParsing) {
            if (this.maximumWeight != -1) {
                z2 = true;
            }
            Preconditions.checkState(z2, "weigher requires maximumWeight");
        } else if (this.maximumWeight == -1) {
            LoggerHolder.logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec cacheBuilderSpec) {
        return cacheBuilderSpec.toCacheBuilder().lenientParsing();
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> cacheLoader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache(this, cacheLoader);
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> concurrencyLevel(int i3) {
        int i4 = this.concurrencyLevel;
        boolean z2 = false;
        Preconditions.checkState(i4 == -1, "concurrency level was already set to %s", i4);
        if (i3 > 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        this.concurrencyLevel = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> expireAfterAccess(long j2, TimeUnit timeUnit) {
        long j3 = this.expireAfterAccessNanos;
        boolean z2 = false;
        Preconditions.checkState(j3 == -1, "expireAfterAccess was already set to %s ns", j3);
        if (j2 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "duration cannot be negative: %s %s", j2, (Object) timeUnit);
        this.expireAfterAccessNanos = timeUnit.toNanos(j2);
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> expireAfterWrite(long j2, TimeUnit timeUnit) {
        long j3 = this.expireAfterWriteNanos;
        boolean z2 = false;
        Preconditions.checkState(j3 == -1, "expireAfterWrite was already set to %s ns", j3);
        if (j2 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "duration cannot be negative: %s %s", j2, (Object) timeUnit);
        this.expireAfterWriteNanos = timeUnit.toNanos(j2);
        return this;
    }

    public int getConcurrencyLevel() {
        int i3 = this.concurrencyLevel;
        if (i3 == -1) {
            return 4;
        }
        return i3;
    }

    public long getExpireAfterAccessNanos() {
        long j2 = this.expireAfterAccessNanos;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    public long getExpireAfterWriteNanos() {
        long j2 = this.expireAfterWriteNanos;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    public int getInitialCapacity() {
        int i3 = this.initialCapacity;
        if (i3 == -1) {
            return 16;
        }
        return i3;
    }

    public Equivalence<Object> getKeyEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    public LocalCache.Strength getKeyStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.keyStrength, LocalCache.Strength.STRONG);
    }

    public long getMaximumWeight() {
        if (this.expireAfterWriteNanos == 0 || this.expireAfterAccessNanos == 0) {
            return 0;
        }
        return this.weigher == null ? this.maximumSize : this.maximumWeight;
    }

    public long getRefreshNanos() {
        long j2 = this.refreshNanos;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    public <K1 extends K, V1 extends V> RemovalListener<K1, V1> getRemovalListener() {
        return (RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }

    public Supplier<? extends AbstractCache.StatsCounter> getStatsCounterSupplier() {
        return this.statsCounterSupplier;
    }

    public Ticker getTicker(boolean z2) {
        Ticker ticker2 = this.ticker;
        return ticker2 != null ? ticker2 : z2 ? Ticker.systemTicker() : NULL_TICKER;
    }

    public Equivalence<Object> getValueEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.valueEquivalence, getValueStrength().defaultEquivalence());
    }

    public LocalCache.Strength getValueStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.valueStrength, LocalCache.Strength.STRONG);
    }

    public <K1 extends K, V1 extends V> Weigher<K1, V1> getWeigher() {
        return (Weigher) MoreObjects.firstNonNull(this.weigher, OneWeigher.INSTANCE);
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> initialCapacity(int i3) {
        int i4 = this.initialCapacity;
        boolean z2 = false;
        Preconditions.checkState(i4 == -1, "initial capacity was already set to %s", i4);
        if (i3 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        this.initialCapacity = i3;
        return this;
    }

    public boolean isRecordingStats() {
        return this.statsCounterSupplier == CACHE_STATS_COUNTER;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> keyEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.keyEquivalence;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", (Object) equivalence2);
        this.keyEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> lenientParsing() {
        this.strictParsing = false;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> maximumSize(long j2) {
        long j3 = this.maximumSize;
        boolean z2 = false;
        Preconditions.checkState(j3 == -1, "maximum size was already set to %s", j3);
        long j4 = this.maximumWeight;
        Preconditions.checkState(j4 == -1, "maximum weight was already set to %s", j4);
        Preconditions.checkState(this.weigher == null, "maximum size can not be combined with weigher");
        if (j2 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "maximum size must not be negative");
        this.maximumSize = j2;
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> maximumWeight(long j2) {
        long j3 = this.maximumWeight;
        boolean z2 = false;
        Preconditions.checkState(j3 == -1, "maximum weight was already set to %s", j3);
        long j4 = this.maximumSize;
        Preconditions.checkState(j4 == -1, "maximum size was already set to %s", j4);
        if (j2 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "maximum weight must not be negative");
        this.maximumWeight = j2;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> recordStats() {
        this.statsCounterSupplier = CACHE_STATS_COUNTER;
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> refreshAfterWrite(long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        long j3 = this.refreshNanos;
        boolean z2 = false;
        Preconditions.checkState(j3 == -1, "refresh was already set to %s ns", j3);
        if (j2 > 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "duration must be positive: %s %s", j2, (Object) timeUnit);
        this.refreshNanos = timeUnit.toNanos(j2);
        return this;
    }

    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> removalListener2) {
        Preconditions.checkState(this.removalListener == null);
        this.removalListener = (RemovalListener) Preconditions.checkNotNull(removalListener2);
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> setKeyStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.keyStrength;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", (Object) strength2);
        this.keyStrength = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> setValueStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.valueStrength;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", (Object) strength2);
        this.valueStrength = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> softValues() {
        return setValueStrength(LocalCache.Strength.SOFT);
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> ticker(Ticker ticker2) {
        Preconditions.checkState(this.ticker == null);
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker2);
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        int i3 = this.initialCapacity;
        if (i3 != -1) {
            stringHelper.add("initialCapacity", i3);
        }
        int i4 = this.concurrencyLevel;
        if (i4 != -1) {
            stringHelper.add("concurrencyLevel", i4);
        }
        long j2 = this.maximumSize;
        if (j2 != -1) {
            stringHelper.add("maximumSize", j2);
        }
        long j3 = this.maximumWeight;
        if (j3 != -1) {
            stringHelper.add("maximumWeight", j3);
        }
        if (this.expireAfterWriteNanos != -1) {
            stringHelper.add("expireAfterWrite", (Object) a.k(this.expireAfterWriteNanos, "ns", new StringBuilder()));
        }
        if (this.expireAfterAccessNanos != -1) {
            stringHelper.add("expireAfterAccess", (Object) a.k(this.expireAfterAccessNanos, "ns", new StringBuilder()));
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.add("keyStrength", (Object) Ascii.toLowerCase(strength.toString()));
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.add("valueStrength", (Object) Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue((Object) "keyEquivalence");
        }
        if (this.valueEquivalence != null) {
            stringHelper.addValue((Object) "valueEquivalence");
        }
        if (this.removalListener != null) {
            stringHelper.addValue((Object) "removalListener");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> valueEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.valueEquivalence;
        Preconditions.checkState(equivalence2 == null, "value equivalence was already set to %s", (Object) equivalence2);
        this.valueEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> weakKeys() {
        return setKeyStrength(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public CacheBuilder<K, V> weakValues() {
        return setValueStrength(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher2) {
        boolean z2 = false;
        Preconditions.checkState(this.weigher == null);
        if (this.strictParsing) {
            long j2 = this.maximumSize;
            if (j2 == -1) {
                z2 = true;
            }
            Preconditions.checkState(z2, "weigher can not be combined with maximum size (%s provided)", j2);
        }
        this.weigher = (Weigher) Preconditions.checkNotNull(weigher2);
        return this;
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(String str) {
        return from(CacheBuilderSpec.parse(str));
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache(this);
    }
}
