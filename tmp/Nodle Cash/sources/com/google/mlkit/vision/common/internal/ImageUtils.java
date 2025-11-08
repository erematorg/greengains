package com.google.mlkit.vision.common.internal;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

@KeepForSdk
public class ImageUtils {
    private static final GmsLogger zza = new GmsLogger("MLKitImageUtils", "");
    private static final ImageUtils zzb = new ImageUtils();

    private ImageUtils() {
    }

    @NonNull
    @KeepForSdk
    public static ImageUtils getInstance() {
        return zzb;
    }

    @NonNull
    @KeepForSdk
    public IObjectWrapper getImageDataWrapper(@NonNull InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format == -1) {
            return ObjectWrapper.wrap((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()));
        }
        if (format != 17) {
            if (format == 35) {
                return ObjectWrapper.wrap(inputImage.getMediaImage());
            }
            if (format != 842094169) {
                throw new MlKitException(a.k("Unsupported image format: ", inputImage.getFormat()), 3);
            }
        }
        return ObjectWrapper.wrap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()));
    }

    @KeepForSdk
    public int getMobileVisionImageFormat(@NonNull InputImage inputImage) {
        return inputImage.getFormat();
    }

    @KeepForSdk
    public int getMobileVisionImageSize(@NonNull InputImage inputImage) {
        if (inputImage.getFormat() == -1) {
            return ((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal())).getAllocationByteCount();
        }
        if (inputImage.getFormat() == 17 || inputImage.getFormat() == 842094169) {
            return ((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())).limit();
        }
        if (inputImage.getFormat() != 35) {
            return 0;
        }
        return (((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getBuffer().limit() * 3) / 2;
    }

    @KeepForSdk
    @Nullable
    public Matrix getUprightRotationMatrix(int i3, int i4, int i5) {
        if (i5 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate(((float) (-i3)) / 2.0f, ((float) (-i4)) / 2.0f);
        matrix.postRotate((float) (i5 * 90));
        int i6 = i5 % 2;
        int i7 = i6 != 0 ? i4 : i3;
        if (i6 == 0) {
            i3 = i4;
        }
        matrix.postTranslate(((float) i7) / 2.0f, ((float) i3) / 2.0f);
        return matrix;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004b */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0069 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006a A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0096 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009a A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a5 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ac A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c1 A[Catch:{ FileNotFoundException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[Catch:{ FileNotFoundException -> 0x0025 }, RETURN, SYNTHETIC] */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap zza(@androidx.annotation.NonNull android.content.ContentResolver r10, @androidx.annotation.NonNull android.net.Uri r11) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r9 = "MLKitImageUtils"
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            android.graphics.Bitmap r8 = android.provider.MediaStore.Images.Media.getBitmap(r10, r11)     // Catch:{ FileNotFoundException -> 0x0025 }
            if (r8 == 0) goto L_0x00c6
            java.lang.String r1 = "content"
            java.lang.String r2 = r11.getScheme()     // Catch:{ FileNotFoundException -> 0x0025 }
            boolean r1 = r1.equals(r2)     // Catch:{ FileNotFoundException -> 0x0025 }
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L_0x0028
            java.lang.String r1 = "file"
            java.lang.String r4 = r11.getScheme()     // Catch:{ FileNotFoundException -> 0x0025 }
            boolean r1 = r1.equals(r4)     // Catch:{ FileNotFoundException -> 0x0025 }
            if (r1 != 0) goto L_0x0028
            goto L_0x0071
        L_0x0025:
            r10 = move-exception
            goto L_0x00ce
        L_0x0028:
            java.io.InputStream r10 = r10.openInputStream(r11)     // Catch:{ IOException -> 0x004c }
            if (r10 == 0) goto L_0x004e
            androidx.exifinterface.media.ExifInterface r1 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x0034 }
            r1.<init>((java.io.InputStream) r10)     // Catch:{ all -> 0x0034 }
            goto L_0x004f
        L_0x0034:
            r1 = move-exception
            r10.close()     // Catch:{ all -> 0x0039 }
            goto L_0x004b
        L_0x0039:
            r10 = move-exception
            java.lang.String r4 = "addSuppressed"
            java.lang.Class[] r5 = new java.lang.Class[]{r0}     // Catch:{ Exception -> 0x004b }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x004b }
            java.lang.Object[] r10 = new java.lang.Object[]{r10}     // Catch:{ Exception -> 0x004b }
            r0.invoke(r1, r10)     // Catch:{ Exception -> 0x004b }
        L_0x004b:
            throw r1     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            r10 = move-exception
            goto L_0x0057
        L_0x004e:
            r1 = r3
        L_0x004f:
            if (r10 == 0) goto L_0x0067
            r10.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0067
        L_0x0055:
            r10 = move-exception
            goto L_0x0058
        L_0x0057:
            r1 = r3
        L_0x0058:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ FileNotFoundException -> 0x0025 }
            java.lang.String r4 = java.lang.String.valueOf(r11)     // Catch:{ FileNotFoundException -> 0x0025 }
            java.lang.String r5 = "failed to open file to read rotation meta data: "
            java.lang.String r4 = r5.concat(r4)     // Catch:{ FileNotFoundException -> 0x0025 }
            r0.e(r9, r4, r10)     // Catch:{ FileNotFoundException -> 0x0025 }
        L_0x0067:
            if (r1 != 0) goto L_0x006a
            goto L_0x0071
        L_0x006a:
            java.lang.String r10 = "Orientation"
            r0 = 1
            int r2 = r1.getAttributeInt(r10, r0)     // Catch:{ FileNotFoundException -> 0x0025 }
        L_0x0071:
            android.graphics.Matrix r10 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x0025 }
            r10.<init>()     // Catch:{ FileNotFoundException -> 0x0025 }
            int r4 = r8.getWidth()     // Catch:{ FileNotFoundException -> 0x0025 }
            int r5 = r8.getHeight()     // Catch:{ FileNotFoundException -> 0x0025 }
            r0 = 1119092736(0x42b40000, float:90.0)
            r1 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r6 = 1065353216(0x3f800000, float:1.0)
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            switch(r2) {
                case 2: goto L_0x00ac;
                case 3: goto L_0x00a5;
                case 4: goto L_0x00a1;
                case 5: goto L_0x009a;
                case 6: goto L_0x0096;
                case 7: goto L_0x008f;
                case 8: goto L_0x008b;
                default: goto L_0x0089;
            }     // Catch:{ FileNotFoundException -> 0x0025 }
        L_0x0089:
            r6 = r3
            goto L_0x00b5
        L_0x008b:
            r10.postRotate(r1)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x008f:
            r10.postRotate(r1)     // Catch:{ FileNotFoundException -> 0x0025 }
            r10.postScale(r7, r6)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x0096:
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x009a:
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x0025 }
            r10.postScale(r7, r6)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x00a1:
            r10.postScale(r6, r7)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x00a5:
            r0 = 1127481344(0x43340000, float:180.0)
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x0025 }
        L_0x00aa:
            r6 = r10
            goto L_0x00b5
        L_0x00ac:
            android.graphics.Matrix r10 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x0025 }
            r10.<init>()     // Catch:{ FileNotFoundException -> 0x0025 }
            r10.postScale(r7, r6)     // Catch:{ FileNotFoundException -> 0x0025 }
            goto L_0x00aa
        L_0x00b5:
            if (r6 == 0) goto L_0x00c5
            r3 = 0
            r7 = 1
            r2 = 0
            r1 = r8
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ FileNotFoundException -> 0x0025 }
            if (r8 == r10) goto L_0x00c5
            r8.recycle()     // Catch:{ FileNotFoundException -> 0x0025 }
            r8 = r10
        L_0x00c5:
            return r8
        L_0x00c6:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x0025 }
            java.lang.String r0 = "The image Uri could not be resolved."
            r10.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0025 }
            throw r10     // Catch:{ FileNotFoundException -> 0x0025 }
        L_0x00ce:
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r1 = "Could not open file: "
            java.lang.String r11 = r1.concat(r11)
            r0.e(r9, r11, r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageUtils.zza(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
