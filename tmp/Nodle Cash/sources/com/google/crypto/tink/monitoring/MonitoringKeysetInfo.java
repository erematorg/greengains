package com.google.crypto.tink.monitoring;

import android.support.v4.media.session.a;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
@Alpha
public final class MonitoringKeysetInfo {
    private final MonitoringAnnotations annotations;
    private final List<Entry> entries;
    @Nullable
    private final Integer primaryKeyId;

    public static final class Builder {
        private MonitoringAnnotations builderAnnotations = MonitoringAnnotations.EMPTY;
        @Nullable
        private ArrayList<Entry> builderEntries = new ArrayList<>();
        @Nullable
        private Integer builderPrimaryKeyId = null;

        private boolean isKeyIdInEntries(int i3) {
            Iterator<Entry> it = this.builderEntries.iterator();
            while (it.hasNext()) {
                if (it.next().getKeyId() == i3) {
                    return true;
                }
            }
            return false;
        }

        @CanIgnoreReturnValue
        public Builder addEntry(KeyStatus keyStatus, int i3, String str, String str2) {
            ArrayList<Entry> arrayList = this.builderEntries;
            if (arrayList != null) {
                arrayList.add(new Entry(keyStatus, i3, str, str2));
                return this;
            }
            throw new IllegalStateException("addEntry cannot be called after build()");
        }

        public MonitoringKeysetInfo build() throws GeneralSecurityException {
            if (this.builderEntries != null) {
                Integer num = this.builderPrimaryKeyId;
                if (num == null || isKeyIdInEntries(num.intValue())) {
                    MonitoringKeysetInfo monitoringKeysetInfo = new MonitoringKeysetInfo(this.builderAnnotations, Collections.unmodifiableList(this.builderEntries), this.builderPrimaryKeyId);
                    this.builderEntries = null;
                    return monitoringKeysetInfo;
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            throw new IllegalStateException("cannot call build() twice");
        }

        @CanIgnoreReturnValue
        public Builder setAnnotations(MonitoringAnnotations monitoringAnnotations) {
            if (this.builderEntries != null) {
                this.builderAnnotations = monitoringAnnotations;
                return this;
            }
            throw new IllegalStateException("setAnnotations cannot be called after build()");
        }

        @CanIgnoreReturnValue
        public Builder setPrimaryKeyId(int i3) {
            if (this.builderEntries != null) {
                this.builderPrimaryKeyId = Integer.valueOf(i3);
                return this;
            }
            throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
        }
    }

    @Immutable
    public static final class Entry {
        private final int keyId;
        private final String keyPrefix;
        private final String keyType;
        private final KeyStatus status;

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.status == entry.status && this.keyId == entry.keyId && this.keyType.equals(entry.keyType) && this.keyPrefix.equals(entry.keyPrefix);
        }

        public int getKeyId() {
            return this.keyId;
        }

        public String getKeyPrefix() {
            return this.keyPrefix;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public KeyStatus getStatus() {
            return this.status;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.status, Integer.valueOf(this.keyId), this.keyType, this.keyPrefix});
        }

        public String toString() {
            KeyStatus keyStatus = this.status;
            int i3 = this.keyId;
            String str = this.keyType;
            String str2 = this.keyPrefix;
            StringBuilder sb = new StringBuilder("(status=");
            sb.append(keyStatus);
            sb.append(", keyId=");
            sb.append(i3);
            sb.append(", keyType='");
            return a.r(sb, str, "', keyPrefix='", str2, "')");
        }

        private Entry(KeyStatus keyStatus, int i3, String str, String str2) {
            this.status = keyStatus;
            this.keyId = i3;
            this.keyType = str;
            this.keyPrefix = str2;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MonitoringKeysetInfo)) {
            return false;
        }
        MonitoringKeysetInfo monitoringKeysetInfo = (MonitoringKeysetInfo) obj;
        return this.annotations.equals(monitoringKeysetInfo.annotations) && this.entries.equals(monitoringKeysetInfo.entries) && Objects.equals(this.primaryKeyId, monitoringKeysetInfo.primaryKeyId);
    }

    public MonitoringAnnotations getAnnotations() {
        return this.annotations;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    @Nullable
    public Integer getPrimaryKeyId() {
        return this.primaryKeyId;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.annotations, this.entries});
    }

    public String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", new Object[]{this.annotations, this.entries, this.primaryKeyId});
    }

    private MonitoringKeysetInfo(MonitoringAnnotations monitoringAnnotations, List<Entry> list, Integer num) {
        this.annotations = monitoringAnnotations;
        this.entries = list;
        this.primaryKeyId = num;
    }
}
