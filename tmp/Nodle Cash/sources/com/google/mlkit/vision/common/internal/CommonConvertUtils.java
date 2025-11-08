package com.google.mlkit.vision.common.internal;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.android.gms.internal.mlkit_vision_common.zzmw;
import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.ImageProperties;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.List;

@KeepForSdk
public class CommonConvertUtils {
    @KeepForSdk
    @Nullable
    public static InputImage convertMlImagetoInputImage(@NonNull MlImage mlImage) {
        InputImage inputImage;
        ImageProperties imageProperties = mlImage.getContainedImageProperties().get(0);
        int storageType = imageProperties.getStorageType();
        if (storageType != 1) {
            inputImage = null;
            if (storageType == 2) {
                ByteBuffer extract = ByteBufferExtractor.extract(mlImage);
                int imageFormat = imageProperties.getImageFormat();
                Integer valueOf = imageFormat != 4 ? imageFormat != 5 ? null : Integer.valueOf(InputImage.IMAGE_FORMAT_YV12) : 17;
                if (valueOf != null) {
                    zza(valueOf.intValue(), 3, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract.limit(), mlImage.getRotation());
                    inputImage = InputImage.fromByteBuffer(extract, mlImage.getWidth(), mlImage.getHeight(), mlImage.getRotation(), valueOf.intValue());
                }
            } else if (storageType == 3) {
                Image extract2 = MediaImageExtractor.extract(mlImage);
                zza(extract2.getFormat(), 5, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract2.getFormat() == 256 ? extract2.getPlanes()[0].getBuffer().limit() : (extract2.getPlanes()[0].getBuffer().limit() * 3) / 2, mlImage.getRotation());
                inputImage = InputImage.fromMediaImage(extract2, mlImage.getRotation());
            }
        } else {
            Bitmap extract3 = BitmapExtractor.extract(mlImage);
            zza(-1, 1, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract3.getAllocationByteCount(), mlImage.getRotation());
            inputImage = InputImage.fromBitmap(extract3, mlImage.getRotation());
        }
        if (inputImage != null) {
            zzmw.zza();
        }
        return inputImage;
    }

    @KeepForSdk
    public static int convertToAndroidImageFormat(@InputImage.ImageFormat int i3) {
        int i4 = 17;
        if (i3 != 17) {
            i4 = 35;
            if (i3 != 35) {
                i4 = InputImage.IMAGE_FORMAT_YV12;
                if (i3 != 842094169) {
                    return 0;
                }
            }
        }
        return i4;
    }

    @KeepForSdk
    public static int convertToMVRotation(int i3) {
        if (i3 == 0) {
            return 0;
        }
        if (i3 == 90) {
            return 1;
        }
        if (i3 == 180) {
            return 2;
        }
        if (i3 == 270) {
            return 3;
        }
        throw new IllegalArgumentException(a.k("Invalid rotation: ", i3));
    }

    @KeepForSdk
    public static void transformPointArray(@NonNull Point[] pointArr, @NonNull Matrix matrix) {
        int length = pointArr.length;
        float[] fArr = new float[(length + length)];
        for (int i3 = 0; i3 < pointArr.length; i3++) {
            Point point = pointArr[i3];
            int i4 = i3 + i3;
            fArr[i4] = (float) point.x;
            fArr[i4 + 1] = (float) point.y;
        }
        matrix.mapPoints(fArr);
        for (int i5 = 0; i5 < pointArr.length; i5++) {
            int i6 = i5 + i5;
            pointArr[i5].set((int) fArr[i6], (int) fArr[i6 + 1]);
        }
    }

    @KeepForSdk
    public static void transformPointF(@NonNull PointF pointF, @NonNull Matrix matrix) {
        float[] fArr = {pointF.x, pointF.y};
        matrix.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    @KeepForSdk
    public static void transformPointList(@NonNull List<PointF> list, @NonNull Matrix matrix) {
        int size = list.size();
        float[] fArr = new float[(size + size)];
        for (int i3 = 0; i3 < list.size(); i3++) {
            int i4 = i3 + i3;
            fArr[i4] = list.get(i3).x;
            fArr[i4 + 1] = list.get(i3).y;
        }
        matrix.mapPoints(fArr);
        for (int i5 = 0; i5 < list.size(); i5++) {
            int i6 = i5 + i5;
            list.get(i5).set(fArr[i6], fArr[i6 + 1]);
        }
    }

    @KeepForSdk
    public static void transformRect(@NonNull Rect rect, @NonNull Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    private static void zza(int i3, int i4, long j2, int i5, int i6, int i7, int i8) {
        zzmu.zzb(zzms.zzb("vision-common"), i3, i4, j2, i5, i6, i7, i8);
    }
}
