package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.io.IOException;

public final class ByteArrayDataSource extends BaseDataSource {
    private int bytesRemaining;
    private final byte[] data;
    private boolean opened;
    private int readPosition;
    @Nullable
    private Uri uri;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ByteArrayDataSource(byte[] bArr) {
        super(false);
        boolean z2 = false;
        Assertions.checkNotNull(bArr);
        Assertions.checkArgument(bArr.length > 0 ? true : z2);
        this.data = bArr;
    }

    public void close() {
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
        this.uri = null;
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        transferInitializing(dataSpec);
        long j2 = dataSpec.position;
        byte[] bArr = this.data;
        if (j2 <= ((long) bArr.length)) {
            this.readPosition = (int) j2;
            int length = bArr.length - ((int) j2);
            this.bytesRemaining = length;
            long j3 = dataSpec.length;
            if (j3 != -1) {
                this.bytesRemaining = (int) Math.min((long) length, j3);
            }
            this.opened = true;
            transferStarted(dataSpec);
            long j4 = dataSpec.length;
            return j4 != -1 ? j4 : (long) this.bytesRemaining;
        }
        throw new DataSourceException(2008);
    }

    public int read(byte[] bArr, int i3, int i4) {
        if (i4 == 0) {
            return 0;
        }
        int i5 = this.bytesRemaining;
        if (i5 == 0) {
            return -1;
        }
        int min = Math.min(i4, i5);
        System.arraycopy(this.data, this.readPosition, bArr, i3, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }
}
