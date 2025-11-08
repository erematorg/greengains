package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Device extends CrashlyticsReport.Session.Device {
    private final int arch;
    private final int cores;
    private final long diskSpace;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final long ram;
    private final boolean simulator;
    private final int state;

    public static final class Builder extends CrashlyticsReport.Session.Device.Builder {
        private int arch;
        private int cores;
        private long diskSpace;
        private String manufacturer;
        private String model;
        private String modelClass;
        private long ram;
        private byte set$0;
        private boolean simulator;
        private int state;

        public CrashlyticsReport.Session.Device build() {
            String str;
            String str2;
            String str3;
            if (this.set$0 == 63 && (str = this.model) != null && (str2 = this.manufacturer) != null && (str3 = this.modelClass) != null) {
                return new AutoValue_CrashlyticsReport_Session_Device(this.arch, str, this.cores, this.ram, this.diskSpace, this.simulator, this.state, str2, str3);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" arch");
            }
            if (this.model == null) {
                sb.append(" model");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" cores");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" ram");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" diskSpace");
            }
            if ((this.set$0 & 16) == 0) {
                sb.append(" simulator");
            }
            if ((this.set$0 & 32) == 0) {
                sb.append(" state");
            }
            if (this.manufacturer == null) {
                sb.append(" manufacturer");
            }
            if (this.modelClass == null) {
                sb.append(" modelClass");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Device.Builder setArch(int i3) {
            this.arch = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setCores(int i3) {
            this.cores = i3;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setDiskSpace(long j2) {
            this.diskSpace = j2;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setManufacturer(String str) {
            if (str != null) {
                this.manufacturer = str;
                return this;
            }
            throw new NullPointerException("Null manufacturer");
        }

        public CrashlyticsReport.Session.Device.Builder setModel(String str) {
            if (str != null) {
                this.model = str;
                return this;
            }
            throw new NullPointerException("Null model");
        }

        public CrashlyticsReport.Session.Device.Builder setModelClass(String str) {
            if (str != null) {
                this.modelClass = str;
                return this;
            }
            throw new NullPointerException("Null modelClass");
        }

        public CrashlyticsReport.Session.Device.Builder setRam(long j2) {
            this.ram = j2;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setSimulator(boolean z2) {
            this.simulator = z2;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setState(int i3) {
            this.state = i3;
            this.set$0 = (byte) (this.set$0 | 32);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Device)) {
            return false;
        }
        CrashlyticsReport.Session.Device device = (CrashlyticsReport.Session.Device) obj;
        return this.arch == device.getArch() && this.model.equals(device.getModel()) && this.cores == device.getCores() && this.ram == device.getRam() && this.diskSpace == device.getDiskSpace() && this.simulator == device.isSimulator() && this.state == device.getState() && this.manufacturer.equals(device.getManufacturer()) && this.modelClass.equals(device.getModelClass());
    }

    @NonNull
    public int getArch() {
        return this.arch;
    }

    public int getCores() {
        return this.cores;
    }

    public long getDiskSpace() {
        return this.diskSpace;
    }

    @NonNull
    public String getManufacturer() {
        return this.manufacturer;
    }

    @NonNull
    public String getModel() {
        return this.model;
    }

    @NonNull
    public String getModelClass() {
        return this.modelClass;
    }

    public long getRam() {
        return this.ram;
    }

    public int getState() {
        return this.state;
    }

    public int hashCode() {
        long j2 = this.ram;
        long j3 = this.diskSpace;
        return this.modelClass.hashCode() ^ ((((((((((((((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.cores) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ (this.simulator ? 1231 : 1237)) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003);
    }

    public boolean isSimulator() {
        return this.simulator;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Device{arch=");
        sb.append(this.arch);
        sb.append(", model=");
        sb.append(this.model);
        sb.append(", cores=");
        sb.append(this.cores);
        sb.append(", ram=");
        sb.append(this.ram);
        sb.append(", diskSpace=");
        sb.append(this.diskSpace);
        sb.append(", simulator=");
        sb.append(this.simulator);
        sb.append(", state=");
        sb.append(this.state);
        sb.append(", manufacturer=");
        sb.append(this.manufacturer);
        sb.append(", modelClass=");
        return A.a.n(sb, this.modelClass, StringSubstitutor.DEFAULT_VAR_END);
    }

    private AutoValue_CrashlyticsReport_Session_Device(int i3, String str, int i4, long j2, long j3, boolean z2, int i5, String str2, String str3) {
        this.arch = i3;
        this.model = str;
        this.cores = i4;
        this.ram = j2;
        this.diskSpace = j3;
        this.simulator = z2;
        this.state = i5;
        this.manufacturer = str2;
        this.modelClass = str3;
    }
}
