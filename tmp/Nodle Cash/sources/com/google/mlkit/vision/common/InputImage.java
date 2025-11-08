package com.google.mlkit.vision.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;

@Immutable
public class InputImage implements MLTaskInput {
    @KeepForSdk
    public static final int IMAGE_FORMAT_BITMAP = -1;
    public static final int IMAGE_FORMAT_NV21 = 17;
    public static final int IMAGE_FORMAT_YUV_420_888 = 35;
    public static final int IMAGE_FORMAT_YV12 = 842094169;
    @Nullable
    private volatile Bitmap zza;
    @Nullable
    private volatile ByteBuffer zzb;
    @Nullable
    private volatile zzb zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;
    @ImageFormat
    private final int zzg;
    @Nullable
    private final Matrix zzh;

    @Retention(RetentionPolicy.CLASS)
    public @interface ImageFormat {
    }

    private InputImage(@NonNull Bitmap bitmap, int i3) {
        this.zza = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.zzd = bitmap.getWidth();
        this.zze = bitmap.getHeight();
        zza(i3);
        this.zzf = i3;
        this.zzg = -1;
        this.zzh = null;
    }

    @NonNull
    public static InputImage fromBitmap(@NonNull Bitmap bitmap, int i3) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(bitmap, i3);
        zzc(-1, 1, elapsedRealtime, bitmap.getHeight(), bitmap.getWidth(), bitmap.getAllocationByteCount(), i3);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteArray(@NonNull byte[] bArr, int i3, int i4, int i5, @ImageFormat int i6) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(ByteBuffer.wrap((byte[]) Preconditions.checkNotNull(bArr)), i3, i4, i5, i6);
        zzc(i6, 2, elapsedRealtime, i4, i3, bArr.length, i5);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteBuffer(@NonNull ByteBuffer byteBuffer, int i3, int i4, int i5, @ImageFormat int i6) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(byteBuffer, i3, i4, i5, i6);
        zzc(i6, 3, elapsedRealtime, i4, i3, byteBuffer.limit(), i5);
        return inputImage;
    }

    @NonNull
    public static InputImage fromFilePath(@NonNull Context context, @NonNull Uri uri) throws IOException {
        Preconditions.checkNotNull(context, "Please provide a valid Context");
        Preconditions.checkNotNull(uri, "Please provide a valid imageUri");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap zza2 = ImageUtils.getInstance().zza(context.getContentResolver(), uri);
        InputImage inputImage = new InputImage(zza2, 0);
        zzc(-1, 4, elapsedRealtime, zza2.getHeight(), zza2.getWidth(), zza2.getAllocationByteCount(), 0);
        return inputImage;
    }

    @NonNull
    public static InputImage fromMediaImage(@NonNull Image image, int i3) {
        return zzb(image, i3, (Matrix) null);
    }

    private static int zza(int i3) {
        boolean z2 = true;
        if (!(i3 == 0 || i3 == 90 || i3 == 180)) {
            if (i3 == 270) {
                i3 = 270;
            } else {
                z2 = false;
            }
        }
        Preconditions.checkArgument(z2, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        return i3;
    }

    private static InputImage zzb(@NonNull Image image, int i3, @Nullable Matrix matrix) {
        InputImage inputImage;
        int limit;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Preconditions.checkNotNull(image, "Please provide a valid image");
        zza(i3);
        boolean z2 = true;
        if (!(image.getFormat() == 256 || image.getFormat() == 35)) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes = image.getPlanes();
        if (image.getFormat() == 256) {
            limit = image.getPlanes()[0].getBuffer().limit();
            inputImage = new InputImage(ImageConvertUtils.getInstance().convertJpegToUpRightBitmap(image, i3), 0);
        } else {
            for (Image.Plane plane : planes) {
                if (plane.getBuffer() != null) {
                    plane.getBuffer().rewind();
                }
            }
            inputImage = new InputImage(image, image.getWidth(), image.getHeight(), i3, matrix);
            limit = (image.getPlanes()[0].getBuffer().limit() * 3) / 2;
        }
        int i4 = limit;
        InputImage inputImage2 = inputImage;
        zzc(image.getFormat(), 5, elapsedRealtime, image.getHeight(), image.getWidth(), i4, i3);
        return inputImage2;
    }

    private static void zzc(int i3, int i4, long j2, int i5, int i6, int i7, int i8) {
        zzmu.zza(zzms.zzb("vision-common"), i3, i4, j2, i5, i6, i7, i8);
    }

    @KeepForSdk
    @Nullable
    public Bitmap getBitmapInternal() {
        return this.zza;
    }

    @KeepForSdk
    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.zzb;
    }

    @KeepForSdk
    @Nullable
    public Matrix getCoordinatesMatrix() {
        return this.zzh;
    }

    @KeepForSdk
    @ImageFormat
    public int getFormat() {
        return this.zzg;
    }

    @KeepForSdk
    public int getHeight() {
        return this.zze;
    }

    @KeepForSdk
    @Nullable
    public Image getMediaImage() {
        if (this.zzc == null) {
            return null;
        }
        return this.zzc.zza();
    }

    @KeepForSdk
    @Nullable
    public Image.Plane[] getPlanes() {
        if (this.zzc == null) {
            return null;
        }
        return this.zzc.zzb();
    }

    @KeepForSdk
    public int getRotationDegrees() {
        return this.zzf;
    }

    @KeepForSdk
    public int getWidth() {
        return this.zzd;
    }

    @NonNull
    @KeepForSdk
    public static InputImage fromMediaImage(@NonNull Image image, int i3, @NonNull Matrix matrix) {
        Preconditions.checkArgument(image.getFormat() == 35, "Only YUV_420_888 is supported now");
        return zzb(image, i3, matrix);
    }

    private InputImage(@NonNull Image image, int i3, int i4, int i5, @Nullable Matrix matrix) {
        Preconditions.checkNotNull(image);
        this.zzc = new zzb(image);
        this.zzd = i3;
        this.zze = i4;
        zza(i5);
        this.zzf = i5;
        this.zzg = 35;
        this.zzh = matrix;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private InputImage(@androidx.annotation.NonNull java.nio.ByteBuffer r5, int r6, int r7, int r8, @com.google.mlkit.vision.common.InputImage.ImageFormat int r9) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 842094169(0x32315659, float:1.0322389E-8)
            r1 = 0
            r2 = 1
            if (r9 == r0) goto L_0x000f
            r0 = 17
            if (r9 != r0) goto L_0x0011
            r9 = r0
        L_0x000f:
            r0 = r2
            goto L_0x0012
        L_0x0011:
            r0 = r1
        L_0x0012:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r4.zzb = r0
            int r0 = r5.limit()
            int r3 = r6 * r7
            if (r0 <= r3) goto L_0x0026
            r1 = r2
        L_0x0026:
            java.lang.String r0 = "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format."
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1, r0)
            r5.rewind()
            r4.zzd = r6
            r4.zze = r7
            zza(r8)
            r4.zzf = r8
            r4.zzg = r9
            r5 = 0
            r4.zzh = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.InputImage.<init>(java.nio.ByteBuffer, int, int, int, int):void");
    }
}
