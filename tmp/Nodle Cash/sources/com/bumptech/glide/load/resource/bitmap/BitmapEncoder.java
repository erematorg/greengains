package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    private static final String TAG = "BitmapEncoder";
    @Nullable
    private final ArrayPool arrayPool;

    public BitmapEncoder(@NonNull ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        return compressFormat != null ? compressFormat : bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
    }

    @NonNull
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        return EncodeStrategy.TRANSFORMED;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:23|(2:43|44)|45|46) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00c2 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066 A[Catch:{ all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[SYNTHETIC, Splitter:B:31:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0078 A[Catch:{ all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bf A[SYNTHETIC, Splitter:B:43:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(@androidx.annotation.NonNull com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r9, @androidx.annotation.NonNull java.io.File r10, @androidx.annotation.NonNull com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.String r1 = "Compressed with type: "
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r2 = r8.getFormat(r9, r11)
            int r3 = r9.getWidth()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r4 = r9.getHeight()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r5, r3, r4, r2)
            long r3 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x0058 }
            com.bumptech.glide.load.Option<java.lang.Integer> r5 = COMPRESSION_QUALITY     // Catch:{ all -> 0x0058 }
            java.lang.Object r5 = r11.get(r5)     // Catch:{ all -> 0x0058 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x0058 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x0058 }
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x005e }
            r7.<init>(r10)     // Catch:{ IOException -> 0x005e }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch:{ IOException -> 0x004a, all -> 0x0046 }
            if (r10 == 0) goto L_0x004d
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch:{ IOException -> 0x004a, all -> 0x0046 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r8 = r8.arrayPool     // Catch:{ IOException -> 0x004a, all -> 0x0046 }
            r10.<init>(r7, r8)     // Catch:{ IOException -> 0x004a, all -> 0x0046 }
            r6 = r10
            goto L_0x004e
        L_0x0046:
            r8 = move-exception
            r6 = r7
            goto L_0x00bd
        L_0x004a:
            r8 = move-exception
            r6 = r7
            goto L_0x005f
        L_0x004d:
            r6 = r7
        L_0x004e:
            r9.compress(r2, r5, r6)     // Catch:{ IOException -> 0x005e }
            r6.close()     // Catch:{ IOException -> 0x005e }
            r6.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005a
        L_0x0058:
            r8 = move-exception
            goto L_0x00c3
        L_0x005a:
            r8 = 1
            goto L_0x0071
        L_0x005c:
            r8 = move-exception
            goto L_0x00bd
        L_0x005e:
            r8 = move-exception
        L_0x005f:
            r10 = 3
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch:{ all -> 0x005c }
            if (r10 == 0) goto L_0x006b
            java.lang.String r10 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r10, r8)     // Catch:{ all -> 0x005c }
        L_0x006b:
            if (r6 == 0) goto L_0x0070
            r6.close()     // Catch:{ IOException -> 0x0070 }
        L_0x0070:
            r8 = 0
        L_0x0071:
            r10 = 2
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch:{ all -> 0x0058 }
            if (r10 == 0) goto L_0x00b9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
            r10.<init>(r1)     // Catch:{ all -> 0x0058 }
            r10.append(r2)     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = " of size "
            r10.append(r1)     // Catch:{ all -> 0x0058 }
            int r1 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch:{ all -> 0x0058 }
            r10.append(r1)     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = " in "
            r10.append(r1)     // Catch:{ all -> 0x0058 }
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x0058 }
            r10.append(r1)     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = ", options format: "
            r10.append(r1)     // Catch:{ all -> 0x0058 }
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = COMPRESSION_FORMAT     // Catch:{ all -> 0x0058 }
            java.lang.Object r11 = r11.get(r1)     // Catch:{ all -> 0x0058 }
            r10.append(r11)     // Catch:{ all -> 0x0058 }
            java.lang.String r11 = ", hasAlpha: "
            r10.append(r11)     // Catch:{ all -> 0x0058 }
            boolean r9 = r9.hasAlpha()     // Catch:{ all -> 0x0058 }
            r10.append(r9)     // Catch:{ all -> 0x0058 }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x0058 }
            android.util.Log.v(r0, r9)     // Catch:{ all -> 0x0058 }
        L_0x00b9:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r8
        L_0x00bd:
            if (r6 == 0) goto L_0x00c2
            r6.close()     // Catch:{ IOException -> 0x00c2 }
        L_0x00c2:
            throw r8     // Catch:{ all -> 0x0058 }
        L_0x00c3:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }
}
