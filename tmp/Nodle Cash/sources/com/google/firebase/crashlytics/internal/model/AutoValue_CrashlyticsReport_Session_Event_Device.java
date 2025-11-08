package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Device extends CrashlyticsReport.Session.Event.Device {
    private final Double batteryLevel;
    private final int batteryVelocity;
    private final long diskUsed;
    private final int orientation;
    private final boolean proximityOn;
    private final long ramUsed;

    public static final class Builder extends CrashlyticsReport.Session.Event.Device.Builder {
        private Double batteryLevel;
        private int batteryVelocity;
        private long diskUsed;
        private int orientation;
        private boolean proximityOn;
        private long ramUsed;
        private byte set$0;

        public CrashlyticsReport.Session.Event.Device build() {
            if (this.set$0 == 31) {
                return new AutoValue_CrashlyticsReport_Session_Event_Device(this.batteryLevel, this.batteryVelocity, this.proximityOn, this.orientation, this.ramUsed, this.diskUsed);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" batteryVelocity");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" proximityOn");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" orientation");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" ramUsed");
            }
            if ((this.set$0 & 16) == 0) {
                sb.append(" diskUsed");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.Device.Builder setBatteryLevel(Double d2) {
            this.batteryLevel = d2;
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setBatteryVelocity(int i3) {
            this.batteryVelocity = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setDiskUsed(long j2) {
            this.diskUsed = j2;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setOrientation(int i3) {
            this.orientation = i3;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setProximityOn(boolean z2) {
            this.proximityOn = z2;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setRamUsed(long j2) {
            this.ramUsed = j2;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Device)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Device device = (CrashlyticsReport.Session.Event.Device) obj;
        Double d2 = this.batteryLevel;
        if (d2 != null ? d2.equals(device.getBatteryLevel()) : device.getBatteryLevel() == null) {
            if (this.batteryVelocity == device.getBatteryVelocity() && this.proximityOn == device.isProximityOn() && this.orientation == device.getOrientation() && this.ramUsed == device.getRamUsed() && this.diskUsed == device.getDiskUsed()) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Double getBatteryLevel() {
        return this.batteryLevel;
    }

    public int getBatteryVelocity() {
        return this.batteryVelocity;
    }

    public long getDiskUsed() {
        return this.diskUsed;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public long getRamUsed() {
        return this.ramUsed;
    }

    public int hashCode() {
        Double d2 = this.batteryLevel;
        int hashCode = ((((d2 == null ? 0 : d2.hashCode()) ^ 1000003) * 1000003) ^ this.batteryVelocity) * 1000003;
        int i3 = this.proximityOn ? 1231 : 1237;
        long j2 = this.ramUsed;
        long j3 = this.diskUsed;
        return ((int) (j3 ^ (j3 >>> 32))) ^ ((((((hashCode ^ i3) * 1000003) ^ this.orientation) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003);
    }

    public boolean isProximityOn() {
        return this.proximityOn;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Device{batteryLevel=");
        sb.append(this.batteryLevel);
        sb.append(", batteryVelocity=");
        sb.append(this.batteryVelocity);
        sb.append(", proximityOn=");
        sb.append(this.proximityOn);
        sb.append(", orientation=");
        sb.append(this.orientation);
        sb.append(", ramUsed=");
        sb.append(this.ramUsed);
        sb.append(", diskUsed=");
        return android.support.v4.media.session.a.k(this.diskUsed, StringSubstitutor.DEFAULT_VAR_END, sb);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Device(@Nullable Double d2, int i3, boolean z2, int i4, long j2, long j3) {
        this.batteryLevel = d2;
        this.batteryVelocity = i3;
        this.proximityOn = z2;
        this.orientation = i4;
        this.ramUsed = j2;
        this.diskUsed = j3;
    }
}
