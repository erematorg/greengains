package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Arrays;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_FilesPayload_File extends CrashlyticsReport.FilesPayload.File {
    private final byte[] contents;
    private final String filename;

    public static final class Builder extends CrashlyticsReport.FilesPayload.File.Builder {
        private byte[] contents;
        private String filename;

        public CrashlyticsReport.FilesPayload.File build() {
            byte[] bArr;
            String str = this.filename;
            if (str != null && (bArr = this.contents) != null) {
                return new AutoValue_CrashlyticsReport_FilesPayload_File(str, bArr);
            }
            StringBuilder sb = new StringBuilder();
            if (this.filename == null) {
                sb.append(" filename");
            }
            if (this.contents == null) {
                sb.append(" contents");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.FilesPayload.File.Builder setContents(byte[] bArr) {
            if (bArr != null) {
                this.contents = bArr;
                return this;
            }
            throw new NullPointerException("Null contents");
        }

        public CrashlyticsReport.FilesPayload.File.Builder setFilename(String str) {
            if (str != null) {
                this.filename = str;
                return this;
            }
            throw new NullPointerException("Null filename");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.FilesPayload.File)) {
            return false;
        }
        CrashlyticsReport.FilesPayload.File file = (CrashlyticsReport.FilesPayload.File) obj;
        if (this.filename.equals(file.getFilename())) {
            if (Arrays.equals(this.contents, file instanceof AutoValue_CrashlyticsReport_FilesPayload_File ? ((AutoValue_CrashlyticsReport_FilesPayload_File) file).contents : file.getContents())) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public byte[] getContents() {
        return this.contents;
    }

    @NonNull
    public String getFilename() {
        return this.filename;
    }

    public int hashCode() {
        return Arrays.hashCode(this.contents) ^ ((this.filename.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        return "File{filename=" + this.filename + ", contents=" + Arrays.toString(this.contents) + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_CrashlyticsReport_FilesPayload_File(String str, byte[] bArr) {
        this.filename = str;
        this.contents = bArr;
    }
}
