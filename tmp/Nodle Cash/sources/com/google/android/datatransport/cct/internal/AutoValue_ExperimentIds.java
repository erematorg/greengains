package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ExperimentIds;
import java.util.Arrays;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ExperimentIds extends ExperimentIds {
    private final byte[] clearBlob;
    private final byte[] encryptedBlob;

    public static final class Builder extends ExperimentIds.Builder {
        private byte[] clearBlob;
        private byte[] encryptedBlob;

        public ExperimentIds build() {
            return new AutoValue_ExperimentIds(this.clearBlob, this.encryptedBlob);
        }

        public ExperimentIds.Builder setClearBlob(@Nullable byte[] bArr) {
            this.clearBlob = bArr;
            return this;
        }

        public ExperimentIds.Builder setEncryptedBlob(@Nullable byte[] bArr) {
            this.encryptedBlob = bArr;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExperimentIds)) {
            return false;
        }
        ExperimentIds experimentIds = (ExperimentIds) obj;
        boolean z2 = experimentIds instanceof AutoValue_ExperimentIds;
        if (Arrays.equals(this.clearBlob, z2 ? ((AutoValue_ExperimentIds) experimentIds).clearBlob : experimentIds.getClearBlob())) {
            if (Arrays.equals(this.encryptedBlob, z2 ? ((AutoValue_ExperimentIds) experimentIds).encryptedBlob : experimentIds.getEncryptedBlob())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public byte[] getClearBlob() {
        return this.clearBlob;
    }

    @Nullable
    public byte[] getEncryptedBlob() {
        return this.encryptedBlob;
    }

    public int hashCode() {
        return Arrays.hashCode(this.encryptedBlob) ^ ((Arrays.hashCode(this.clearBlob) ^ 1000003) * 1000003);
    }

    public String toString() {
        return "ExperimentIds{clearBlob=" + Arrays.toString(this.clearBlob) + ", encryptedBlob=" + Arrays.toString(this.encryptedBlob) + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_ExperimentIds(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        this.clearBlob = bArr;
        this.encryptedBlob = bArr2;
    }
}
