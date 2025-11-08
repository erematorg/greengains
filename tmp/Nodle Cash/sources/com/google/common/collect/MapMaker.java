package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class MapMaker {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final int UNSET_INT = -1;
    int concurrencyLevel = -1;
    int initialCapacity = -1;
    @CheckForNull
    Equivalence<Object> keyEquivalence;
    @CheckForNull
    MapMakerInternalMap.Strength keyStrength;
    boolean useCustomMap;
    @CheckForNull
    MapMakerInternalMap.Strength valueStrength;

    public enum Dummy {
        VALUE
    }

    @CanIgnoreReturnValue
    public MapMaker concurrencyLevel(int i3) {
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

    public int getConcurrencyLevel() {
        int i3 = this.concurrencyLevel;
        if (i3 == -1) {
            return 4;
        }
        return i3;
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

    public MapMakerInternalMap.Strength getKeyStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
    }

    public MapMakerInternalMap.Strength getValueStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
    }

    @CanIgnoreReturnValue
    public MapMaker initialCapacity(int i3) {
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

    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker keyEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.keyEquivalence;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", (Object) equivalence2);
        this.keyEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.useCustomMap = true;
        return this;
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        return !this.useCustomMap ? new ConcurrentHashMap(getInitialCapacity(), 0.75f, getConcurrencyLevel()) : MapMakerInternalMap.create(this);
    }

    public MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.keyStrength;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", (Object) strength2);
        this.keyStrength = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    public MapMaker setValueStrength(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", (Object) strength2);
        this.valueStrength = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
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
        MapMakerInternalMap.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.add("keyStrength", (Object) Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.add("valueStrength", (Object) Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue((Object) "keyEquivalence");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker weakKeys() {
        return setKeyStrength(MapMakerInternalMap.Strength.WEAK);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker weakValues() {
        return setValueStrength(MapMakerInternalMap.Strength.WEAK);
    }
}
