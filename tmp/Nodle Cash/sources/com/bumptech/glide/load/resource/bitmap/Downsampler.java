package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.ImageReader;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class Downsampler {
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG;
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() {
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }

        public void onObtainBounds() {
        }
    };
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS;
    private static final String ICO_MIME_TYPE = "image/x-ico";
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{WBMP_MIME_TYPE, ICO_MIME_TYPE})));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    public static final Option<PreferredColorSpace> PREFERRED_COLOR_SPACE = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace");
    static final String TAG = "Downsampler";
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    static {
        Boolean bool = Boolean.FALSE;
        FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        ALLOW_HARDWARE_CONFIG = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics2, BitmapPool bitmapPool2, ArrayPool arrayPool) {
        this.parsers = list;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics2);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool2);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
    }

    private static int adjustTargetDensityForError(double d2) {
        int densityMultiplier = getDensityMultiplier(d2);
        int round = round(((double) densityMultiplier) * d2);
        return round((d2 / ((double) (((float) round) / ((float) densityMultiplier)))) * ((double) round));
    }

    private void calculateConfig(ImageReader imageReader, DecodeFormat decodeFormat, boolean z2, boolean z3, BitmapFactory.Options options, int i3, int i4) {
        boolean z4;
        if (!this.hardwareConfigState.setHardwareConfigIfAllowed(i3, i4, options, z2, z3)) {
            if (decodeFormat != DecodeFormat.PREFER_ARGB_8888) {
                try {
                    z4 = imageReader.getImageType().hasAlpha();
                } catch (IOException e3) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e3);
                    }
                    z4 = false;
                }
                Bitmap.Config config = z4 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
                options.inPreferredConfig = config;
                if (config == Bitmap.Config.RGB_565) {
                    options.inDither = true;
                    return;
                }
                return;
            }
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }
    }

    private static void calculateScaling(ImageHeaderParser.ImageType imageType, ImageReader imageReader, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool2, DownsampleStrategy downsampleStrategy, int i3, int i4, int i5, int i6, int i7, BitmapFactory.Options options) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        ImageHeaderParser.ImageType imageType2 = imageType;
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        int i12 = i4;
        int i13 = i5;
        int i14 = i6;
        int i15 = i7;
        BitmapFactory.Options options2 = options;
        if (i12 > 0 && i13 > 0) {
            if (isRotationRequired(i3)) {
                i8 = i12;
                i9 = i13;
            } else {
                i9 = i12;
                i8 = i13;
            }
            float scaleFactor = downsampleStrategy2.getScaleFactor(i9, i8, i14, i15);
            if (scaleFactor > 0.0f) {
                DownsampleStrategy.SampleSizeRounding sampleSizeRounding = downsampleStrategy2.getSampleSizeRounding(i9, i8, i14, i15);
                if (sampleSizeRounding != null) {
                    float f2 = (float) i9;
                    float f3 = (float) i8;
                    int round = i9 / round((double) (scaleFactor * f2));
                    int round2 = i8 / round((double) (scaleFactor * f3));
                    DownsampleStrategy.SampleSizeRounding sampleSizeRounding2 = DownsampleStrategy.SampleSizeRounding.MEMORY;
                    int max = Math.max(1, Integer.highestOneBit(sampleSizeRounding == sampleSizeRounding2 ? Math.max(round, round2) : Math.min(round, round2)));
                    if (sampleSizeRounding == sampleSizeRounding2 && ((float) max) < 1.0f / scaleFactor) {
                        max <<= 1;
                    }
                    options2.inSampleSize = max;
                    if (imageType2 == ImageHeaderParser.ImageType.JPEG) {
                        float min = (float) Math.min(max, 8);
                        i10 = (int) Math.ceil((double) (f2 / min));
                        i11 = (int) Math.ceil((double) (f3 / min));
                        int i16 = max / 8;
                        if (i16 > 0) {
                            i10 /= i16;
                            i11 /= i16;
                        }
                    } else if (imageType2 == ImageHeaderParser.ImageType.PNG || imageType2 == ImageHeaderParser.ImageType.PNG_A) {
                        float f4 = (float) max;
                        i10 = (int) Math.floor((double) (f2 / f4));
                        i11 = (int) Math.floor((double) (f3 / f4));
                    } else if (imageType.isWebp()) {
                        float f5 = (float) max;
                        i10 = Math.round(f2 / f5);
                        i11 = Math.round(f3 / f5);
                    } else if (i9 % max == 0 && i8 % max == 0) {
                        i10 = i9 / max;
                        i11 = i8 / max;
                    } else {
                        int[] dimensions = getDimensions(imageReader, options2, decodeCallbacks, bitmapPool2);
                        i10 = dimensions[0];
                        i11 = dimensions[1];
                    }
                    double scaleFactor2 = (double) downsampleStrategy2.getScaleFactor(i10, i11, i14, i15);
                    options2.inTargetDensity = adjustTargetDensityForError(scaleFactor2);
                    options2.inDensity = getDensityMultiplier(scaleFactor2);
                    if (isScaling(options)) {
                        options2.inScaled = true;
                    } else {
                        options2.inTargetDensity = 0;
                        options2.inDensity = 0;
                    }
                    if (Log.isLoggable(TAG, 2)) {
                        StringBuilder k2 = C0118y.k(i4, i5, "Calculate scaling, source: [", "x", "], degreesToRotate: ");
                        k2.append(i3);
                        k2.append(", target: [");
                        k2.append(i14);
                        k2.append("x");
                        k2.append(i15);
                        k2.append("], power of two scaled: [");
                        k2.append(i10);
                        k2.append("x");
                        k2.append(i11);
                        k2.append("], exact scale factor: ");
                        k2.append(scaleFactor);
                        k2.append(", power of 2 sample size: ");
                        k2.append(max);
                        k2.append(", adjusted scale factor: ");
                        k2.append(scaleFactor2);
                        k2.append(", target density: ");
                        k2.append(options2.inTargetDensity);
                        k2.append(", density: ");
                        k2.append(options2.inDensity);
                        Log.v(TAG, k2.toString());
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("Cannot round with null rounding");
            }
            int i17 = i12;
            throw new IllegalArgumentException("Cannot scale with factor: " + scaleFactor + " from: " + downsampleStrategy2 + ", source: [" + i17 + "x" + i13 + "], target: [" + i14 + "x" + i15 + "]");
        } else if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to determine dimensions for: " + imageType2 + " with target [" + i14 + "x" + i15 + "]");
        }
    }

    private Bitmap decodeFromWrappedStreams(ImageReader imageReader, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, PreferredColorSpace preferredColorSpace, boolean z2, int i3, int i4, boolean z3, DecodeCallbacks decodeCallbacks) throws IOException {
        int i5;
        int i6;
        int i7;
        ColorSpace colorSpace;
        int i8;
        int i9;
        ImageReader imageReader2 = imageReader;
        BitmapFactory.Options options2 = options;
        PreferredColorSpace preferredColorSpace2 = preferredColorSpace;
        DecodeCallbacks decodeCallbacks2 = decodeCallbacks;
        long logTime = LogTime.getLogTime();
        int[] dimensions = getDimensions(imageReader2, options2, decodeCallbacks2, this.bitmapPool);
        int i10 = dimensions[0];
        int i11 = dimensions[1];
        String str = options2.outMimeType;
        boolean z4 = (i10 == -1 || i11 == -1) ? false : z2;
        int imageOrientation = imageReader.getImageOrientation();
        int exifOrientationDegrees = TransformationUtils.getExifOrientationDegrees(imageOrientation);
        boolean isExifOrientationRequired = TransformationUtils.isExifOrientationRequired(imageOrientation);
        int i12 = i3;
        if (i12 == Integer.MIN_VALUE) {
            i6 = i4;
            i5 = isRotationRequired(exifOrientationDegrees) ? i11 : i10;
        } else {
            i6 = i4;
            i5 = i12;
        }
        int i13 = i6 == Integer.MIN_VALUE ? isRotationRequired(exifOrientationDegrees) ? i10 : i11 : i6;
        ImageHeaderParser.ImageType imageType = imageReader.getImageType();
        ImageHeaderParser.ImageType imageType2 = imageType;
        calculateScaling(imageType, imageReader, decodeCallbacks, this.bitmapPool, downsampleStrategy, exifOrientationDegrees, i10, i11, i5, i13, options);
        String str2 = str;
        int i14 = i11;
        int i15 = i10;
        DecodeCallbacks decodeCallbacks3 = decodeCallbacks2;
        PreferredColorSpace preferredColorSpace3 = preferredColorSpace2;
        BitmapFactory.Options options3 = options2;
        int i16 = imageOrientation;
        ImageReader imageReader3 = imageReader2;
        calculateConfig(imageReader, decodeFormat, z4, isExifOrientationRequired, options, i5, i13);
        if (shouldUsePool(imageType2)) {
            if (i15 < 0 || i14 < 0 || !z3) {
                float f2 = isScaling(options) ? ((float) options3.inTargetDensity) / ((float) options3.inDensity) : 1.0f;
                int i17 = options3.inSampleSize;
                float f3 = (float) i17;
                i9 = Math.round(((float) ((int) Math.ceil((double) (((float) i15) / f3)))) * f2);
                i8 = Math.round(((float) ((int) Math.ceil((double) (((float) i14) / f3)))) * f2);
                if (Log.isLoggable(TAG, 2)) {
                    StringBuilder k2 = C0118y.k(i9, i8, "Calculated target [", "x", "] for source [");
                    k2.append(i15);
                    k2.append("x");
                    k2.append(i14);
                    k2.append("], sampleSize: ");
                    k2.append(i17);
                    k2.append(", targetDensity: ");
                    k2.append(options3.inTargetDensity);
                    k2.append(", density: ");
                    k2.append(options3.inDensity);
                    k2.append(", density multiplier: ");
                    k2.append(f2);
                    Log.v(TAG, k2.toString());
                }
            } else {
                i9 = i5;
                i8 = i13;
            }
            if (i9 > 0 && i8 > 0) {
                setInBitmap(options3, this.bitmapPool, i9, i8);
            }
        }
        if (preferredColorSpace3 != null) {
            options3.inPreferredColorSpace = ColorSpace.get((preferredColorSpace3 != PreferredColorSpace.DISPLAY_P3 || (colorSpace = options3.outColorSpace) == null || !colorSpace.isWideGamut()) ? ColorSpace.Named.SRGB : ColorSpace.Named.DISPLAY_P3);
        }
        Bitmap decodeStream = decodeStream(imageReader3, options3, decodeCallbacks3, this.bitmapPool);
        decodeCallbacks3.onDecodeComplete(this.bitmapPool, decodeStream);
        if (Log.isLoggable(TAG, 2)) {
            i7 = i16;
            logDecode(i15, i14, str2, options, decodeStream, i3, i4, logTime);
        } else {
            i7 = i16;
        }
        if (decodeStream == null) {
            return null;
        }
        decodeStream.setDensity(this.displayMetrics.densityDpi);
        Bitmap rotateImageExif = TransformationUtils.rotateImageExif(this.bitmapPool, decodeStream, i7);
        if (decodeStream.equals(rotateImageExif)) {
            return rotateImageExif;
        }
        this.bitmapPool.put(decodeStream);
        return rotateImageExif;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeStream(com.bumptech.glide.load.resource.bitmap.ImageReader r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r7, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r8) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r6.inJustDecodeBounds
            if (r1 != 0) goto L_0x000c
            r7.onObtainBounds()
            r5.stopGrowingBuffers()
        L_0x000c:
            int r1 = r6.outWidth
            int r2 = r6.outHeight
            java.lang.String r3 = r6.outMimeType
            java.util.concurrent.locks.Lock r4 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r4.lock()
            android.graphics.Bitmap r5 = r5.decodeBitmap(r6)     // Catch:{ IllegalArgumentException -> 0x0027 }
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r6.unlock()
            return r5
        L_0x0025:
            r5 = move-exception
            goto L_0x0050
        L_0x0027:
            r4 = move-exception
            java.io.IOException r1 = newIoExceptionForInBitmapAssertion(r4, r1, r2, r3, r6)     // Catch:{ all -> 0x0025 }
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch:{ all -> 0x0025 }
            if (r2 == 0) goto L_0x0038
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch:{ all -> 0x0025 }
        L_0x0038:
            android.graphics.Bitmap r0 = r6.inBitmap     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x004f
            r8.put(r0)     // Catch:{ IOException -> 0x004e }
            r0 = 0
            r6.inBitmap = r0     // Catch:{ IOException -> 0x004e }
            android.graphics.Bitmap r5 = decodeStream(r5, r6, r7, r8)     // Catch:{ IOException -> 0x004e }
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r6.unlock()
            return r5
        L_0x004e:
            throw r1     // Catch:{ all -> 0x0025 }
        L_0x004f:
            throw r1     // Catch:{ all -> 0x0025 }
        L_0x0050:
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeStream(com.bumptech.glide.load.resource.bitmap.ImageReader, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    @TargetApi(19)
    @Nullable
    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options poll;
        synchronized (Downsampler.class) {
            Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
            synchronized (queue) {
                poll = queue.poll();
            }
            if (poll == null) {
                poll = new BitmapFactory.Options();
                resetOptions(poll);
            }
        }
        return poll;
    }

    private static int getDensityMultiplier(double d2) {
        if (d2 > 1.0d) {
            d2 = 1.0d / d2;
        }
        return (int) Math.round(d2 * 2.147483647E9d);
    }

    private static int[] getDimensions(ImageReader imageReader, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool2) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(imageReader, options, decodeCallbacks, bitmapPool2);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static String getInBitmapString(BitmapFactory.Options options) {
        return getBitmapString(options.inBitmap);
    }

    private static boolean isRotationRequired(int i3) {
        return i3 == 90 || i3 == 270;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1.inDensity;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isScaling(android.graphics.BitmapFactory.Options r1) {
        /*
            int r0 = r1.inTargetDensity
            if (r0 <= 0) goto L_0x000c
            int r1 = r1.inDensity
            if (r1 <= 0) goto L_0x000c
            if (r0 == r1) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.isScaling(android.graphics.BitmapFactory$Options):boolean");
    }

    private static void logDecode(int i3, int i4, String str, BitmapFactory.Options options, Bitmap bitmap, int i5, int i6, long j2) {
        StringBuilder sb = new StringBuilder("Decoded ");
        b.v(sb, getBitmapString(bitmap), " from [", i3, "x");
        sb.append(i4);
        sb.append("] ");
        sb.append(str);
        sb.append(" with inBitmap ");
        b.v(sb, getInBitmapString(options), " for [", i5, "x");
        sb.append(i6);
        sb.append("], sample size: ");
        sb.append(options.inSampleSize);
        sb.append(", density: ");
        sb.append(options.inDensity);
        sb.append(", target density: ");
        sb.append(options.inTargetDensity);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        sb.append(", duration: ");
        sb.append(LogTime.getElapsedMillis(j2));
        Log.v(TAG, sb.toString());
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException illegalArgumentException, int i3, int i4, String str, BitmapFactory.Options options) {
        StringBuilder k2 = C0118y.k(i3, i4, "Exception decoding bitmap, outWidth: ", ", outHeight: ", ", outMimeType: ");
        k2.append(str);
        k2.append(", inBitmap: ");
        k2.append(getInBitmapString(options));
        return new IOException(k2.toString(), illegalArgumentException);
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    private static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.inPreferredColorSpace = null;
        options.outColorSpace = null;
        options.outConfig = null;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    private static int round(double d2) {
        return (int) (d2 + 0.5d);
    }

    @TargetApi(26)
    private static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool2, int i3, int i4) {
        Bitmap.Config config = options.inPreferredConfig;
        if (config != Bitmap.Config.HARDWARE) {
            Bitmap.Config config2 = options.outConfig;
            if (config2 != null) {
                config = config2;
            }
            options.inBitmap = bitmapPool2.getDirty(i3, i4, config);
        }
    }

    private boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i3, int i4, Options options) throws IOException {
        return decode(inputStream, i3, i4, options, EMPTY_CALLBACKS);
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    public Resource<Bitmap> decode(ByteBuffer byteBuffer, int i3, int i4, Options options) throws IOException {
        return decode((ImageReader) new ImageReader.ByteBufferReader(byteBuffer, this.parsers, this.byteArrayPool), i3, i4, options, EMPTY_CALLBACKS);
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i3, int i4, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        return decode((ImageReader) new ImageReader.InputStreamImageReader(inputStream, this.parsers, this.byteArrayPool), i3, i4, options, decodeCallbacks);
    }

    public boolean handles(ParcelFileDescriptor parcelFileDescriptor) {
        return ParcelFileDescriptorRewinder.isSupported();
    }

    @VisibleForTesting
    public void decode(byte[] bArr, int i3, int i4, Options options) throws IOException {
        decode((ImageReader) new ImageReader.ByteArrayReader(bArr, this.parsers, this.byteArrayPool), i3, i4, options, EMPTY_CALLBACKS);
    }

    @VisibleForTesting
    public void decode(File file, int i3, int i4, Options options) throws IOException {
        decode((ImageReader) new ImageReader.FileReader(file, this.parsers, this.byteArrayPool), i3, i4, options, EMPTY_CALLBACKS);
    }

    @RequiresApi(21)
    public Resource<Bitmap> decode(ParcelFileDescriptor parcelFileDescriptor, int i3, int i4, Options options) throws IOException {
        return decode((ImageReader) new ImageReader.ParcelFileDescriptorImageReader(parcelFileDescriptor, this.parsers, this.byteArrayPool), i3, i4, options, EMPTY_CALLBACKS);
    }

    private Resource<Bitmap> decode(ImageReader imageReader, int i3, int i4, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Options options2 = options;
        byte[] bArr = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        defaultOptions.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options2.get(DECODE_FORMAT);
        PreferredColorSpace preferredColorSpace = (PreferredColorSpace) options2.get(PREFERRED_COLOR_SPACE);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options2.get(DownsampleStrategy.OPTION);
        boolean booleanValue = ((Boolean) options2.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
        Option option = ALLOW_HARDWARE_CONFIG;
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(imageReader, defaultOptions, downsampleStrategy, decodeFormat, preferredColorSpace, options2.get(option) != null && ((Boolean) options2.get(option)).booleanValue(), i3, i4, booleanValue, decodeCallbacks), this.bitmapPool);
        } finally {
            releaseOptions(defaultOptions);
            this.byteArrayPool.put(bArr);
        }
    }
}
