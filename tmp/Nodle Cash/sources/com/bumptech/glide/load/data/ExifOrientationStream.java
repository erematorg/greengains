package com.bumptech.glide.load.data;

import A.a;
import androidx.annotation.NonNull;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ExifOrientationStream extends FilterInputStream {
    private static final byte[] EXIF_SEGMENT;
    private static final int ORIENTATION_POSITION;
    private static final int SEGMENT_LENGTH;
    private static final int SEGMENT_START_POSITION = 2;
    private final byte orientation;
    private int position;

    static {
        byte[] bArr = {-1, -31, 0, Ascii.FS, 69, CBORConstants.BYTE_STRING_1BYTE_LEN, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, Ascii.DC2, 0, 2, 0, 0, 0, 1, 0};
        EXIF_SEGMENT = bArr;
        int length = bArr.length;
        SEGMENT_LENGTH = length;
        ORIENTATION_POSITION = length + 2;
    }

    public ExifOrientationStream(InputStream inputStream, int i3) {
        super(inputStream);
        if (i3 < -1 || i3 > 8) {
            throw new IllegalArgumentException(a.k("Cannot add invalid orientation: ", i3));
        }
        this.orientation = (byte) i3;
    }

    public void mark(int i3) {
        throw new UnsupportedOperationException();
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        int i3;
        int i4;
        int i5 = this.position;
        if (i5 < 2 || i5 > (i4 = ORIENTATION_POSITION)) {
            i3 = super.read();
        } else if (i5 == i4) {
            i3 = this.orientation;
        } else {
            i3 = EXIF_SEGMENT[i5 - 2] & 255;
        }
        if (i3 != -1) {
            this.position++;
        }
        return i3;
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    public long skip(long j2) throws IOException {
        long skip = super.skip(j2);
        if (skip > 0) {
            this.position = (int) (((long) this.position) + skip);
        }
        return skip;
    }

    public int read(@NonNull byte[] bArr, int i3, int i4) throws IOException {
        int i5;
        int i6 = this.position;
        int i7 = ORIENTATION_POSITION;
        if (i6 > i7) {
            i5 = super.read(bArr, i3, i4);
        } else if (i6 == i7) {
            bArr[i3] = this.orientation;
            i5 = 1;
        } else if (i6 < 2) {
            i5 = super.read(bArr, i3, 2 - i6);
        } else {
            int min = Math.min(i7 - i6, i4);
            System.arraycopy(EXIF_SEGMENT, this.position - 2, bArr, i3, min);
            i5 = min;
        }
        if (i5 > 0) {
            this.position += i5;
        }
        return i5;
    }
}
