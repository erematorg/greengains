package com.appsamurai.storyly.exoplayer2.common.util;

import android.util.SparseBooleanArray;
import androidx.annotation.Nullable;

public final class FlagSet {
    private final SparseBooleanArray flags;

    public boolean contains(int i3) {
        return this.flags.get(i3);
    }

    public boolean containsAny(int... iArr) {
        for (int contains : iArr) {
            if (contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagSet)) {
            return false;
        }
        FlagSet flagSet = (FlagSet) obj;
        if (Util.SDK_INT >= 24) {
            return this.flags.equals(flagSet.flags);
        }
        if (size() != flagSet.size()) {
            return false;
        }
        for (int i3 = 0; i3 < size(); i3++) {
            if (get(i3) != flagSet.get(i3)) {
                return false;
            }
        }
        return true;
    }

    public int get(int i3) {
        Assertions.checkIndex(i3, 0, size());
        return this.flags.keyAt(i3);
    }

    public int hashCode() {
        if (Util.SDK_INT >= 24) {
            return this.flags.hashCode();
        }
        int size = size();
        for (int i3 = 0; i3 < size(); i3++) {
            size = (size * 31) + get(i3);
        }
        return size;
    }

    public int size() {
        return this.flags.size();
    }

    public static final class Builder {
        private boolean buildCalled;
        private final SparseBooleanArray flags = new SparseBooleanArray();

        public Builder add(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.flags.append(i3, true);
            return this;
        }

        public Builder addAll(int... iArr) {
            for (int add : iArr) {
                add(add);
            }
            return this;
        }

        public Builder addIf(int i3, boolean z2) {
            return z2 ? add(i3) : this;
        }

        public FlagSet build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new FlagSet(this.flags);
        }

        public Builder remove(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.flags.delete(i3);
            return this;
        }

        public Builder removeAll(int... iArr) {
            for (int remove : iArr) {
                remove(remove);
            }
            return this;
        }

        public Builder removeIf(int i3, boolean z2) {
            return z2 ? remove(i3) : this;
        }

        public Builder addAll(FlagSet flagSet) {
            for (int i3 = 0; i3 < flagSet.size(); i3++) {
                add(flagSet.get(i3));
            }
            return this;
        }
    }

    private FlagSet(SparseBooleanArray sparseBooleanArray) {
        this.flags = sparseBooleanArray;
    }
}
