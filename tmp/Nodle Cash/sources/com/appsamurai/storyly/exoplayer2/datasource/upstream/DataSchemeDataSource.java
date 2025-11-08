package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.google.common.base.Charsets;
import java.io.IOException;
import java.net.URLDecoder;

public final class DataSchemeDataSource extends BaseDataSource {
    public static final String SCHEME_DATA = "data";
    private int bytesRemaining;
    @Nullable
    private byte[] data;
    @Nullable
    private DataSpec dataSpec;
    private int readPosition;

    public DataSchemeDataSource() {
        super(false);
    }

    public void close() {
        if (this.data != null) {
            this.data = null;
            transferEnded();
        }
        this.dataSpec = null;
    }

    @Nullable
    public Uri getUri() {
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public long open(DataSpec dataSpec2) throws IOException {
        transferInitializing(dataSpec2);
        this.dataSpec = dataSpec2;
        Uri uri = dataSpec2.uri;
        String scheme = uri.getScheme();
        boolean equals = "data".equals(scheme);
        Assertions.checkArgument(equals, "Unsupported scheme: " + scheme);
        String[] split = Util.split(uri.getSchemeSpecificPart(), ",");
        if (split.length == 2) {
            String str = split[1];
            if (split[0].contains(";base64")) {
                try {
                    this.data = Base64.decode(str, 0);
                } catch (IllegalArgumentException e3) {
                    throw ParserException.createForMalformedDataOfUnknownType("Error while parsing Base64 encoded string: " + str, e3);
                }
            } else {
                this.data = Util.getUtf8Bytes(URLDecoder.decode(str, Charsets.US_ASCII.name()));
            }
            long j2 = dataSpec2.position;
            byte[] bArr = this.data;
            if (j2 <= ((long) bArr.length)) {
                int i3 = (int) j2;
                this.readPosition = i3;
                int length = bArr.length - i3;
                this.bytesRemaining = length;
                long j3 = dataSpec2.length;
                if (j3 != -1) {
                    this.bytesRemaining = (int) Math.min((long) length, j3);
                }
                transferStarted(dataSpec2);
                long j4 = dataSpec2.length;
                return j4 != -1 ? j4 : (long) this.bytesRemaining;
            }
            this.data = null;
            throw new DataSourceException(2008);
        }
        throw ParserException.createForMalformedDataOfUnknownType("Unexpected URI format: " + uri, (Throwable) null);
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
        System.arraycopy(Util.castNonNull(this.data), this.readPosition, bArr, i3, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }
}
