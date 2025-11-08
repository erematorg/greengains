package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public enum RemovalCause {
    EXPLICIT {
        public boolean wasEvicted() {
            return false;
        }
    },
    REPLACED {
        public boolean wasEvicted() {
            return false;
        }
    },
    COLLECTED {
        public boolean wasEvicted() {
            return true;
        }
    },
    EXPIRED {
        public boolean wasEvicted() {
            return true;
        }
    },
    SIZE {
        public boolean wasEvicted() {
            return true;
        }
    };

    public abstract boolean wasEvicted();
}
