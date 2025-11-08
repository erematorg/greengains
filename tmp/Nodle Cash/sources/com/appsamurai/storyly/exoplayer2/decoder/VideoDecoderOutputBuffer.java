package com.appsamurai.storyly.exoplayer2.decoder;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderOutputBuffer;
import java.nio.ByteBuffer;

public class VideoDecoderOutputBuffer extends DecoderOutputBuffer {
    public static final int COLORSPACE_BT2020 = 3;
    public static final int COLORSPACE_BT601 = 1;
    public static final int COLORSPACE_BT709 = 2;
    public static final int COLORSPACE_UNKNOWN = 0;
    public int colorspace;
    @Nullable
    public ByteBuffer data;
    public int decoderPrivate;
    @Nullable
    public Format format;
    public int height;
    public int mode;
    private final DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> owner;
    @Nullable
    public ByteBuffer supplementalData;
    public int width;
    @Nullable
    public ByteBuffer[] yuvPlanes;
    @Nullable
    public int[] yuvStrides;

    public VideoDecoderOutputBuffer(DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> owner2) {
        this.owner = owner2;
    }

    private static boolean isSafeToMultiply(int i3, int i4) {
        return i3 >= 0 && i4 >= 0 && (i4 <= 0 || i3 < Integer.MAX_VALUE / i4);
    }

    public void init(long j2, int i3, @Nullable ByteBuffer byteBuffer) {
        this.timeUs = j2;
        this.mode = i3;
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            this.supplementalData = null;
            return;
        }
        addFlag(268435456);
        int limit = byteBuffer.limit();
        ByteBuffer byteBuffer2 = this.supplementalData;
        if (byteBuffer2 == null || byteBuffer2.capacity() < limit) {
            this.supplementalData = ByteBuffer.allocate(limit);
        } else {
            this.supplementalData.clear();
        }
        this.supplementalData.put(byteBuffer);
        this.supplementalData.flip();
        byteBuffer.position(0);
    }

    public void initForPrivateFrame(int i3, int i4) {
        this.width = i3;
        this.height = i4;
    }

    public boolean initForYuvFrame(int i3, int i4, int i5, int i6, int i7) {
        this.width = i3;
        this.height = i4;
        this.colorspace = i7;
        int i8 = (int) ((((long) i4) + 1) / 2);
        if (isSafeToMultiply(i5, i4) && isSafeToMultiply(i6, i8)) {
            int i9 = i4 * i5;
            int i10 = i8 * i6;
            int i11 = (i10 * 2) + i9;
            if (isSafeToMultiply(i10, 2) && i11 >= i9) {
                ByteBuffer byteBuffer = this.data;
                if (byteBuffer == null || byteBuffer.capacity() < i11) {
                    this.data = ByteBuffer.allocateDirect(i11);
                } else {
                    this.data.position(0);
                    this.data.limit(i11);
                }
                if (this.yuvPlanes == null) {
                    this.yuvPlanes = new ByteBuffer[3];
                }
                ByteBuffer byteBuffer2 = this.data;
                ByteBuffer[] byteBufferArr = this.yuvPlanes;
                ByteBuffer slice = byteBuffer2.slice();
                byteBufferArr[0] = slice;
                slice.limit(i9);
                byteBuffer2.position(i9);
                ByteBuffer slice2 = byteBuffer2.slice();
                byteBufferArr[1] = slice2;
                slice2.limit(i10);
                byteBuffer2.position(i9 + i10);
                ByteBuffer slice3 = byteBuffer2.slice();
                byteBufferArr[2] = slice3;
                slice3.limit(i10);
                if (this.yuvStrides == null) {
                    this.yuvStrides = new int[3];
                }
                int[] iArr = this.yuvStrides;
                iArr[0] = i5;
                iArr[1] = i6;
                iArr[2] = i6;
                return true;
            }
        }
        return false;
    }

    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
