package com.google.android.datatransport.runtime.scheduling.persistence;

import android.support.v4.media.session.a;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_EventStoreConfig extends EventStoreConfig {
    private final int criticalSectionEnterTimeoutMs;
    private final long eventCleanUpAge;
    private final int loadBatchSize;
    private final int maxBlobByteSizePerRow;
    private final long maxStorageSizeInBytes;

    public static final class Builder extends EventStoreConfig.Builder {
        private Integer criticalSectionEnterTimeoutMs;
        private Long eventCleanUpAge;
        private Integer loadBatchSize;
        private Integer maxBlobByteSizePerRow;
        private Long maxStorageSizeInBytes;

        public EventStoreConfig build() {
            String str = this.maxStorageSizeInBytes == null ? " maxStorageSizeInBytes" : "";
            if (this.loadBatchSize == null) {
                str = a.m(str, " loadBatchSize");
            }
            if (this.criticalSectionEnterTimeoutMs == null) {
                str = a.m(str, " criticalSectionEnterTimeoutMs");
            }
            if (this.eventCleanUpAge == null) {
                str = a.m(str, " eventCleanUpAge");
            }
            if (this.maxBlobByteSizePerRow == null) {
                str = a.m(str, " maxBlobByteSizePerRow");
            }
            if (str.isEmpty()) {
                return new AutoValue_EventStoreConfig(this.maxStorageSizeInBytes.longValue(), this.loadBatchSize.intValue(), this.criticalSectionEnterTimeoutMs.intValue(), this.eventCleanUpAge.longValue(), this.maxBlobByteSizePerRow.intValue());
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public EventStoreConfig.Builder setCriticalSectionEnterTimeoutMs(int i3) {
            this.criticalSectionEnterTimeoutMs = Integer.valueOf(i3);
            return this;
        }

        public EventStoreConfig.Builder setEventCleanUpAge(long j2) {
            this.eventCleanUpAge = Long.valueOf(j2);
            return this;
        }

        public EventStoreConfig.Builder setLoadBatchSize(int i3) {
            this.loadBatchSize = Integer.valueOf(i3);
            return this;
        }

        public EventStoreConfig.Builder setMaxBlobByteSizePerRow(int i3) {
            this.maxBlobByteSizePerRow = Integer.valueOf(i3);
            return this;
        }

        public EventStoreConfig.Builder setMaxStorageSizeInBytes(long j2) {
            this.maxStorageSizeInBytes = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventStoreConfig)) {
            return false;
        }
        EventStoreConfig eventStoreConfig = (EventStoreConfig) obj;
        return this.maxStorageSizeInBytes == eventStoreConfig.getMaxStorageSizeInBytes() && this.loadBatchSize == eventStoreConfig.getLoadBatchSize() && this.criticalSectionEnterTimeoutMs == eventStoreConfig.getCriticalSectionEnterTimeoutMs() && this.eventCleanUpAge == eventStoreConfig.getEventCleanUpAge() && this.maxBlobByteSizePerRow == eventStoreConfig.getMaxBlobByteSizePerRow();
    }

    public int getCriticalSectionEnterTimeoutMs() {
        return this.criticalSectionEnterTimeoutMs;
    }

    public long getEventCleanUpAge() {
        return this.eventCleanUpAge;
    }

    public int getLoadBatchSize() {
        return this.loadBatchSize;
    }

    public int getMaxBlobByteSizePerRow() {
        return this.maxBlobByteSizePerRow;
    }

    public long getMaxStorageSizeInBytes() {
        return this.maxStorageSizeInBytes;
    }

    public int hashCode() {
        long j2 = this.maxStorageSizeInBytes;
        long j3 = this.eventCleanUpAge;
        return this.maxBlobByteSizePerRow ^ ((((((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.loadBatchSize) * 1000003) ^ this.criticalSectionEnterTimeoutMs) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EventStoreConfig{maxStorageSizeInBytes=");
        sb.append(this.maxStorageSizeInBytes);
        sb.append(", loadBatchSize=");
        sb.append(this.loadBatchSize);
        sb.append(", criticalSectionEnterTimeoutMs=");
        sb.append(this.criticalSectionEnterTimeoutMs);
        sb.append(", eventCleanUpAge=");
        sb.append(this.eventCleanUpAge);
        sb.append(", maxBlobByteSizePerRow=");
        return A.a.m(sb, StringSubstitutor.DEFAULT_VAR_END, this.maxBlobByteSizePerRow);
    }

    private AutoValue_EventStoreConfig(long j2, int i3, int i4, long j3, int i5) {
        this.maxStorageSizeInBytes = j2;
        this.loadBatchSize = i3;
        this.criticalSectionEnterTimeoutMs = i4;
        this.eventCleanUpAge = j3;
        this.maxBlobByteSizePerRow = i5;
    }
}
