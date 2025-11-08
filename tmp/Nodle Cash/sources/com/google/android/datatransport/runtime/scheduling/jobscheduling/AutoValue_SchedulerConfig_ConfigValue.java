package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.support.v4.media.session.a;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import java.util.Set;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_SchedulerConfig_ConfigValue extends SchedulerConfig.ConfigValue {
    private final long delta;
    private final Set<SchedulerConfig.Flag> flags;
    private final long maxAllowedDelay;

    public static final class Builder extends SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set<SchedulerConfig.Flag> flags;
        private Long maxAllowedDelay;

        public SchedulerConfig.ConfigValue build() {
            String str = this.delta == null ? " delta" : "";
            if (this.maxAllowedDelay == null) {
                str = a.m(str, " maxAllowedDelay");
            }
            if (this.flags == null) {
                str = a.m(str, " flags");
            }
            if (str.isEmpty()) {
                return new AutoValue_SchedulerConfig_ConfigValue(this.delta.longValue(), this.maxAllowedDelay.longValue(), this.flags);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public SchedulerConfig.ConfigValue.Builder setDelta(long j2) {
            this.delta = Long.valueOf(j2);
            return this;
        }

        public SchedulerConfig.ConfigValue.Builder setFlags(Set<SchedulerConfig.Flag> set) {
            if (set != null) {
                this.flags = set;
                return this;
            }
            throw new NullPointerException("Null flags");
        }

        public SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long j2) {
            this.maxAllowedDelay = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SchedulerConfig.ConfigValue)) {
            return false;
        }
        SchedulerConfig.ConfigValue configValue = (SchedulerConfig.ConfigValue) obj;
        return this.delta == configValue.getDelta() && this.maxAllowedDelay == configValue.getMaxAllowedDelay() && this.flags.equals(configValue.getFlags());
    }

    public long getDelta() {
        return this.delta;
    }

    public Set<SchedulerConfig.Flag> getFlags() {
        return this.flags;
    }

    public long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    public int hashCode() {
        long j2 = this.delta;
        long j3 = this.maxAllowedDelay;
        return this.flags.hashCode() ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003);
    }

    public String toString() {
        return "ConfigValue{delta=" + this.delta + ", maxAllowedDelay=" + this.maxAllowedDelay + ", flags=" + this.flags + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_SchedulerConfig_ConfigValue(long j2, long j3, Set<SchedulerConfig.Flag> set) {
        this.delta = j2;
        this.maxAllowedDelay = j3;
        this.flags = set;
    }
}
