package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import androidx.annotation.NonNull;
import androidx.compose.runtime.b;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

@KeepForSdk
public class ImageConvertUtils {
    private static final ImageConvertUtils zza = new ImageConvertUtils();

    private ImageConvertUtils() {
    }

    @NonNull
    @KeepForSdk
    public static ByteBuffer bufferWithBackingArray(@NonNull ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return byteBuffer;
        }
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        return ByteBuffer.wrap(bArr);
    }

    @NonNull
    @KeepForSdk
    public static ImageConvertUtils getInstance() {
        return zza;
    }

    @NonNull
    @KeepForSdk
    public static Bitmap yv12ToBitmap(@NonNull ByteBuffer byteBuffer, int i3, int i4, int i5) throws MlKitException {
        byte[] zzb = zzb(yv12ToNv21Buffer(byteBuffer, true).array(), i3, i4);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzb, 0, zzb.length);
        return zza(decodeByteArray, i5, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    @NonNull
    @KeepForSdk
    public static ByteBuffer yv12ToNv21Buffer(@NonNull ByteBuffer byteBuffer, boolean z2) {
        int i3;
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        int i4 = limit / 6;
        ByteBuffer allocate = z2 ? ByteBuffer.allocate(limit) : ByteBuffer.allocateDirect(limit);
        int i5 = 0;
        while (true) {
            i3 = i4 * 4;
            if (i5 >= i3) {
                break;
            }
            allocate.put(i5, byteBuffer.get(i5));
            i5++;
        }
        for (int i6 = 0; i6 < i4 + i4; i6++) {
            allocate.put(i3 + i6, byteBuffer.get((i6 / 2) + ((i6 % 2) * i4) + i3));
        }
        return allocate;
    }

    @NonNull
    public static Bitmap zza(@NonNull Bitmap bitmap, int i3, int i4, int i5) {
        if (i3 == 0) {
            return Bitmap.createBitmap(bitmap, 0, 0, i4, i5);
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i3);
        return Bitmap.createBitmap(bitmap, 0, 0, i4, i5, matrix, true);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        throw r9;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x003f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] zzb(@androidx.annotation.NonNull byte[] r8, int r9, int r10) throws com.google.mlkit.common.MlKitException {
        /*
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            android.graphics.YuvImage r7 = new android.graphics.YuvImage
            r3 = 17
            r6 = 0
            r1 = r7
            r2 = r8
            r4 = r9
            r5 = r10
            r1.<init>(r2, r3, r4, r5, r6)
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0026 }
            r8.<init>()     // Catch:{ IOException -> 0x0026 }
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ all -> 0x0028 }
            r2 = 0
            r1.<init>(r2, r2, r9, r10)     // Catch:{ all -> 0x0028 }
            r9 = 100
            r7.compressToJpeg(r1, r9, r8)     // Catch:{ all -> 0x0028 }
            byte[] r9 = r8.toByteArray()     // Catch:{ all -> 0x0028 }
            r8.close()     // Catch:{ IOException -> 0x0026 }
            return r9
        L_0x0026:
            r8 = move-exception
            goto L_0x0040
        L_0x0028:
            r9 = move-exception
            r8.close()     // Catch:{ all -> 0x002d }
            goto L_0x003f
        L_0x002d:
            r8 = move-exception
            java.lang.String r10 = "addSuppressed"
            java.lang.Class[] r1 = new java.lang.Class[]{r0}     // Catch:{ Exception -> 0x003f }
            java.lang.reflect.Method r10 = r0.getDeclaredMethod(r10, r1)     // Catch:{ Exception -> 0x003f }
            java.lang.Object[] r8 = new java.lang.Object[]{r8}     // Catch:{ Exception -> 0x003f }
            r10.invoke(r9, r8)     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            throw r9     // Catch:{ IOException -> 0x0026 }
        L_0x0040:
            java.lang.String r9 = "ImageConvertUtils"
            java.lang.String r10 = "Error closing ByteArrayOutputStream"
            android.util.Log.w(r9, r10)
            com.google.mlkit.common.MlKitException r9 = new com.google.mlkit.common.MlKitException
            java.lang.String r10 = "Image conversion error from NV21 format"
            r0 = 13
            r9.<init>(r10, r0, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageConvertUtils.zzb(byte[], int, int):byte[]");
    }

    private static final void zzc(Image.Plane plane, int i3, int i4, byte[] bArr, int i5, int i6) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int rowStride = ((plane.getRowStride() + buffer.limit()) - 1) / plane.getRowStride();
        if (rowStride != 0) {
            int i7 = i3 / (i4 / rowStride);
            int i8 = 0;
            for (int i9 = 0; i9 < rowStride; i9++) {
                int i10 = i8;
                for (int i11 = 0; i11 < i7; i11++) {
                    bArr[i5] = buffer.get(i10);
                    i5 += i6;
                    i10 += plane.getPixelStride();
                }
                i8 += plane.getRowStride();
            }
        }
    }

    @NonNull
    @KeepForSdk
    public byte[] byteBufferToByteArray(@NonNull ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return byteBuffer.array();
        }
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        return bArr;
    }

    @NonNull
    @KeepForSdk
    public ByteBuffer cloneByteBuffer(@NonNull ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        ByteBuffer allocateDirect = byteBuffer.isDirect() ? ByteBuffer.allocateDirect(capacity) : ByteBuffer.allocate(capacity);
        allocateDirect.limit(byteBuffer.limit());
        allocateDirect.put((ByteBuffer) byteBuffer.rewind());
        allocateDirect.position(position);
        byteBuffer.position(position);
        return allocateDirect;
    }

    @NonNull
    @KeepForSdk
    public Bitmap convertJpegToUpRightBitmap(@NonNull Image image, int i3) {
        Preconditions.checkArgument(image.getFormat() == 256, "Only JPEG is supported now");
        Image.Plane[] planes = image.getPlanes();
        if (planes == null || planes.length != 1) {
            throw new IllegalArgumentException("Unexpected image format, JPEG should have exactly 1 image plane");
        }
        ByteBuffer buffer = planes[0].getBuffer();
        buffer.rewind();
        int remaining = buffer.remaining();
        byte[] bArr = new byte[remaining];
        buffer.get(bArr);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, remaining);
        return zza(decodeByteArray, i3, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    @NonNull
    @KeepForSdk
    public ByteBuffer convertToNv21Buffer(@NonNull InputImage inputImage, boolean z2) throws MlKitException {
        boolean z3 = z2;
        int format = inputImage.getFormat();
        if (format == -1) {
            Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal());
            if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, bitmap.isMutable());
            }
            Bitmap bitmap2 = bitmap;
            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();
            int i3 = width * height;
            int[] iArr = new int[i3];
            bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
            int ceil = (int) Math.ceil(((double) height) / 2.0d);
            int ceil2 = ((ceil + ceil) * ((int) Math.ceil(((double) width) / 2.0d))) + i3;
            ByteBuffer allocate = z3 ? ByteBuffer.allocate(ceil2) : ByteBuffer.allocateDirect(ceil2);
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < height; i6++) {
                int i7 = 0;
                while (i7 < width) {
                    int i8 = iArr[i5];
                    int i9 = i8 >> 16;
                    int i10 = i8 >> 8;
                    int i11 = i8 & 255;
                    int i12 = i4 + 1;
                    int i13 = i9 & 255;
                    int i14 = i10 & 255;
                    allocate.put(i4, (byte) Math.min(255, (b.a(i11, 25, (i14 * 129) + (i13 * 66), 128) >> 8) + 16));
                    if (i6 % 2 == 0 && i5 % 2 == 0) {
                        int i15 = i3 + 1;
                        allocate.put(i3, (byte) Math.min(255, (((((i13 * 112) - (i14 * 94)) - (i11 * 18)) + 128) >> 8) + 128));
                        i3 += 2;
                        allocate.put(i15, (byte) Math.min(255, (((((i13 * -38) - (i14 * 74)) + (i11 * 112)) + 128) >> 8) + 128));
                    }
                    i5++;
                    i7++;
                    i4 = i12;
                }
            }
            return allocate;
        } else if (format == 17) {
            return z3 ? bufferWithBackingArray((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())) : (ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer());
        } else {
            if (format == 35) {
                return yuv420ThreePlanesToNV21((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()), inputImage.getWidth(), inputImage.getHeight());
            } else if (format == 842094169) {
                return yv12ToNv21Buffer((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), z3);
            } else {
                throw new MlKitException("Unsupported image format", 13);
            }
        }
    }

    @NonNull
    @KeepForSdk
    public Bitmap convertToUpRightBitmap(@NonNull InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format == -1) {
            return zza((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()), inputImage.getRotationDegrees(), inputImage.getWidth(), inputImage.getHeight());
        }
        if (format == 17) {
            return nv21ToBitmap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        if (format == 35) {
            return nv21ToBitmap(yuv420ThreePlanesToNV21((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()), inputImage.getWidth(), inputImage.getHeight()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        if (format == 842094169) {
            return yv12ToBitmap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        throw new MlKitException("Unsupported image format", 13);
    }

    @NonNull
    @KeepForSdk
    public Bitmap getUpRightBitmap(@NonNull InputImage inputImage) throws MlKitException {
        Bitmap bitmapInternal = inputImage.getBitmapInternal();
        return bitmapInternal != null ? zza(bitmapInternal, inputImage.getRotationDegrees(), inputImage.getWidth(), inputImage.getHeight()) : convertToUpRightBitmap(inputImage);
    }

    @NonNull
    @KeepForSdk
    public Bitmap nv21ToBitmap(@NonNull ByteBuffer byteBuffer, int i3, int i4, int i5) throws MlKitException {
        byte[] zzb = zzb(byteBufferToByteArray(byteBuffer), i3, i4);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzb, 0, zzb.length);
        return zza(decodeByteArray, i5, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    @NonNull
    @KeepForSdk
    public ByteBuffer yuv420ThreePlanesToNV21(@NonNull Image.Plane[] planeArr, int i3, int i4) {
        int i5 = i3 * i4;
        int i6 = i5 / 4;
        byte[] bArr = new byte[(i6 + i6 + i5)];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i7 = (i5 + i5) / 4;
        boolean z2 = buffer2.remaining() == i7 + -2 && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z2) {
            planeArr[0].getBuffer().get(bArr, 0, i5);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i5, 1);
            buffer3.get(bArr, i5 + 1, i7 - 1);
        } else {
            int i8 = i3;
            int i9 = i4;
            byte[] bArr2 = bArr;
            zzc(planeArr[0], i8, i9, bArr2, 0, 1);
            zzc(planeArr[1], i8, i9, bArr2, i5 + 1, 2);
            zzc(planeArr[2], i3, i4, bArr, i5, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
