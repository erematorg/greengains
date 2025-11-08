package com.google.android.datatransport.runtime;

import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import com.google.android.datatransport.runtime.EventInternal;
import java.util.Arrays;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_EventInternal extends EventInternal {
    private final Map<String, String> autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final byte[] experimentIdsClear;
    private final byte[] experimentIdsEncrypted;
    private final Integer productId;
    private final String pseudonymousId;
    private final String transportName;
    private final long uptimeMillis;

    public static final class Builder extends EventInternal.Builder {
        private Map<String, String> autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private byte[] experimentIdsClear;
        private byte[] experimentIdsEncrypted;
        private Integer productId;
        private String pseudonymousId;
        private String transportName;
        private Long uptimeMillis;

        public EventInternal build() {
            String str = this.transportName == null ? " transportName" : "";
            if (this.encodedPayload == null) {
                str = a.m(str, " encodedPayload");
            }
            if (this.eventMillis == null) {
                str = a.m(str, " eventMillis");
            }
            if (this.uptimeMillis == null) {
                str = a.m(str, " uptimeMillis");
            }
            if (this.autoMetadata == null) {
                str = a.m(str, " autoMetadata");
            }
            if (str.isEmpty()) {
                return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata, this.productId, this.pseudonymousId, this.experimentIdsClear, this.experimentIdsEncrypted);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public Map<String, String> getAutoMetadata() {
            Map<String, String> map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        public EventInternal.Builder setAutoMetadata(Map<String, String> map) {
            if (map != null) {
                this.autoMetadata = map;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        public EventInternal.Builder setCode(Integer num) {
            this.code = num;
            return this;
        }

        public EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload2) {
            if (encodedPayload2 != null) {
                this.encodedPayload = encodedPayload2;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        public EventInternal.Builder setEventMillis(long j2) {
            this.eventMillis = Long.valueOf(j2);
            return this;
        }

        public EventInternal.Builder setExperimentIdsClear(byte[] bArr) {
            this.experimentIdsClear = bArr;
            return this;
        }

        public EventInternal.Builder setExperimentIdsEncrypted(byte[] bArr) {
            this.experimentIdsEncrypted = bArr;
            return this;
        }

        public EventInternal.Builder setProductId(Integer num) {
            this.productId = num;
            return this;
        }

        public EventInternal.Builder setPseudonymousId(String str) {
            this.pseudonymousId = str;
            return this;
        }

        public EventInternal.Builder setTransportName(String str) {
            if (str != null) {
                this.transportName = str;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        public EventInternal.Builder setUptimeMillis(long j2) {
            this.uptimeMillis = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        Integer num;
        Integer num2;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventInternal)) {
            return false;
        }
        EventInternal eventInternal = (EventInternal) obj;
        if (this.transportName.equals(eventInternal.getTransportName()) && ((num = this.code) != null ? num.equals(eventInternal.getCode()) : eventInternal.getCode() == null) && this.encodedPayload.equals(eventInternal.getEncodedPayload()) && this.eventMillis == eventInternal.getEventMillis() && this.uptimeMillis == eventInternal.getUptimeMillis() && this.autoMetadata.equals(eventInternal.getAutoMetadata()) && ((num2 = this.productId) != null ? num2.equals(eventInternal.getProductId()) : eventInternal.getProductId() == null) && ((str = this.pseudonymousId) != null ? str.equals(eventInternal.getPseudonymousId()) : eventInternal.getPseudonymousId() == null)) {
            boolean z2 = eventInternal instanceof AutoValue_EventInternal;
            if (Arrays.equals(this.experimentIdsClear, z2 ? ((AutoValue_EventInternal) eventInternal).experimentIdsClear : eventInternal.getExperimentIdsClear())) {
                if (Arrays.equals(this.experimentIdsEncrypted, z2 ? ((AutoValue_EventInternal) eventInternal).experimentIdsEncrypted : eventInternal.getExperimentIdsEncrypted())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    @Nullable
    public Integer getCode() {
        return this.code;
    }

    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    public long getEventMillis() {
        return this.eventMillis;
    }

    @Nullable
    public byte[] getExperimentIdsClear() {
        return this.experimentIdsClear;
    }

    @Nullable
    public byte[] getExperimentIdsEncrypted() {
        return this.experimentIdsEncrypted;
    }

    @Nullable
    public Integer getProductId() {
        return this.productId;
    }

    @Nullable
    public String getPseudonymousId() {
        return this.pseudonymousId;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        int hashCode = (this.transportName.hashCode() ^ 1000003) * 1000003;
        Integer num = this.code;
        int i3 = 0;
        int hashCode2 = num == null ? 0 : num.hashCode();
        long j2 = this.eventMillis;
        long j3 = this.uptimeMillis;
        int hashCode3 = (((((((((hashCode ^ hashCode2) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode()) * 1000003;
        Integer num2 = this.productId;
        int hashCode4 = (hashCode3 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str = this.pseudonymousId;
        if (str != null) {
            i3 = str.hashCode();
        }
        return Arrays.hashCode(this.experimentIdsEncrypted) ^ ((((hashCode4 ^ i3) * 1000003) ^ Arrays.hashCode(this.experimentIdsClear)) * 1000003);
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + ", productId=" + this.productId + ", pseudonymousId=" + this.pseudonymousId + ", experimentIdsClear=" + Arrays.toString(this.experimentIdsClear) + ", experimentIdsEncrypted=" + Arrays.toString(this.experimentIdsEncrypted) + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_EventInternal(String str, @Nullable Integer num, EncodedPayload encodedPayload2, long j2, long j3, Map<String, String> map, @Nullable Integer num2, @Nullable String str2, @Nullable byte[] bArr, @Nullable byte[] bArr2) {
        this.transportName = str;
        this.code = num;
        this.encodedPayload = encodedPayload2;
        this.eventMillis = j2;
        this.uptimeMillis = j3;
        this.autoMetadata = map;
        this.productId = num2;
        this.pseudonymousId = str2;
        this.experimentIdsClear = bArr;
        this.experimentIdsEncrypted = bArr2;
    }
}
