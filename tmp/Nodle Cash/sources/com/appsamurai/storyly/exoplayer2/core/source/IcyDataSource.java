package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

final class IcyDataSource implements DataSource {
    private int bytesUntilMetadata;
    private final Listener listener;
    private final int metadataIntervalBytes;
    private final byte[] metadataLengthByteHolder;
    private final DataSource upstream;

    public interface Listener {
        void onIcyMetadata(ParsableByteArray parsableByteArray);
    }

    public IcyDataSource(DataSource dataSource, int i3, Listener listener2) {
        Assertions.checkArgument(i3 > 0);
        this.upstream = dataSource;
        this.metadataIntervalBytes = i3;
        this.listener = listener2;
        this.metadataLengthByteHolder = new byte[1];
        this.bytesUntilMetadata = i3;
    }

    private boolean readMetadata() throws IOException {
        if (this.upstream.read(this.metadataLengthByteHolder, 0, 1) == -1) {
            return false;
        }
        int i3 = (this.metadataLengthByteHolder[0] & 255) << 4;
        if (i3 == 0) {
            return true;
        }
        byte[] bArr = new byte[i3];
        int i4 = i3;
        int i5 = 0;
        while (i4 > 0) {
            int read = this.upstream.read(bArr, i5, i4);
            if (read == -1) {
                return false;
            }
            i5 += read;
            i4 -= read;
        }
        while (i3 > 0 && bArr[i3 - 1] == 0) {
            i3--;
        }
        if (i3 > 0) {
            this.listener.onIcyMetadata(new ParsableByteArray(bArr, i3));
        }
        return true;
    }

    public void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        this.upstream.addTransferListener(transferListener);
    }

    public void close() {
        throw new UnsupportedOperationException();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    @Nullable
    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec dataSpec) {
        throw new UnsupportedOperationException();
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        if (this.bytesUntilMetadata == 0) {
            if (!readMetadata()) {
                return -1;
            }
            this.bytesUntilMetadata = this.metadataIntervalBytes;
        }
        int read = this.upstream.read(bArr, i3, Math.min(this.bytesUntilMetadata, i4));
        if (read != -1) {
            this.bytesUntilMetadata -= read;
        }
        return read;
    }
}
