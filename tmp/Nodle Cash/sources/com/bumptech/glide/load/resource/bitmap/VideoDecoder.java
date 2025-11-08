package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaDataSource;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.compose.animation.core.a;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    public static final long DEFAULT_FRAME = -1;
    @VisibleForTesting
    static final int DEFAULT_FRAME_OPTION = 2;
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        public void update(@NonNull byte[] bArr, @NonNull Integer num, @NonNull MessageDigest messageDigest) {
            if (num != null) {
                messageDigest.update(bArr);
                synchronized (this.buffer) {
                    this.buffer.position(0);
                    messageDigest.update(this.buffer.putInt(num.intValue()).array());
                }
            }
        }
    });
    private static final List<String> PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX = Collections.unmodifiableList(Arrays.asList(new String[]{"TP1A", "TD1A.220804.031"}));
    private static final String TAG = "VideoDecoder";
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        public void update(@NonNull byte[] bArr, @NonNull Long l2, @NonNull MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(l2.longValue()).array());
            }
        }
    });
    private static final String WEBM_MIME_TYPE = "video/webm";
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaInitializer<T> initializer;

    @RequiresApi(16)
    public static final class AssetFileDescriptorInitializer implements MediaInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        public void initializeExtractor(MediaExtractor mediaExtractor, AssetFileDescriptor assetFileDescriptor) throws IOException {
            mediaExtractor.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    @RequiresApi(23)
    public static final class ByteBufferInitializer implements MediaInitializer<ByteBuffer> {
        private MediaDataSource getMediaDataSource(final ByteBuffer byteBuffer) {
            return new MediaDataSource() {
                public void close() {
                }

                public long getSize() {
                    return (long) byteBuffer.limit();
                }

                public int readAt(long j2, byte[] bArr, int i3, int i4) {
                    if (j2 >= ((long) byteBuffer.limit())) {
                        return -1;
                    }
                    byteBuffer.position((int) j2);
                    int min = Math.min(i4, byteBuffer.remaining());
                    byteBuffer.get(bArr, i3, min);
                    return min;
                }
            };
        }

        public void initializeExtractor(MediaExtractor mediaExtractor, ByteBuffer byteBuffer) throws IOException {
            mediaExtractor.setDataSource(getMediaDataSource(byteBuffer));
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
            mediaMetadataRetriever.setDataSource(getMediaDataSource(byteBuffer));
        }
    }

    @VisibleForTesting
    public interface MediaInitializer<T> {
        @RequiresApi(16)
        void initializeExtractor(MediaExtractor mediaExtractor, T t2) throws IOException;

        void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, T t2);
    }

    @VisibleForTesting
    public static class MediaMetadataRetrieverFactory {
        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    public static final class ParcelFileDescriptorInitializer implements MediaInitializer<ParcelFileDescriptor> {
        @RequiresApi(16)
        public void initializeExtractor(MediaExtractor mediaExtractor, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            mediaExtractor.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    public static final class VideoDecoderException extends RuntimeException {
        private static final long serialVersionUID = -2556382523004027815L;

        public VideoDecoderException() {
            super("MediaMetadataRetriever failed to retrieve a frame without throwing, check the adb logs for .*MetadataRetriever.* prior to this exception for details");
        }
    }

    public VideoDecoder(BitmapPool bitmapPool2, MediaInitializer<T> mediaInitializer) {
        this(bitmapPool2, mediaInitializer, DEFAULT_FACTORY);
    }

    @RequiresApi(16)
    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new AssetFileDescriptorInitializer());
    }

    @RequiresApi(api = 23)
    public static ResourceDecoder<ByteBuffer, Bitmap> byteBuffer(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ByteBufferInitializer());
    }

    @TargetApi(30)
    private static Bitmap correctHdr180DegVideoFrameOrientation(MediaMetadataRetriever mediaMetadataRetriever, Bitmap bitmap) {
        if (!isHdr180RotationFixRequired()) {
            return bitmap;
        }
        try {
            if (isHDR(mediaMetadataRetriever) && Math.abs(Integer.parseInt(mediaMetadataRetriever.extractMetadata(24))) == 180) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Applying HDR 180 deg thumbnail correction");
                }
                Matrix matrix = new Matrix();
                matrix.postRotate(180.0f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        } catch (NumberFormatException unused) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception trying to extract HDR transfer function or rotation");
            }
        }
        return bitmap;
    }

    @Nullable
    private Bitmap decodeFrame(@NonNull T t2, MediaMetadataRetriever mediaMetadataRetriever, long j2, int i3, int i4, int i5, DownsampleStrategy downsampleStrategy) {
        if (!isUnsupportedFormat(t2, mediaMetadataRetriever)) {
            Bitmap decodeScaledFrame = (i4 == Integer.MIN_VALUE || i5 == Integer.MIN_VALUE || downsampleStrategy == DownsampleStrategy.NONE) ? null : decodeScaledFrame(mediaMetadataRetriever, j2, i3, i4, i5, downsampleStrategy);
            if (decodeScaledFrame == null) {
                decodeScaledFrame = decodeOriginalFrame(mediaMetadataRetriever, j2, i3);
            }
            Bitmap correctHdr180DegVideoFrameOrientation = correctHdr180DegVideoFrameOrientation(mediaMetadataRetriever, decodeScaledFrame);
            if (correctHdr180DegVideoFrameOrientation != null) {
                return correctHdr180DegVideoFrameOrientation;
            }
            throw new VideoDecoderException();
        }
        throw new IllegalStateException("Cannot decode VP8 video on CrOS.");
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i3) {
        return mediaMetadataRetriever.getFrameAtTime(j2, i3);
    }

    @TargetApi(27)
    @Nullable
    private static Bitmap decodeScaledFrame(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i3, int i4, int i5, DownsampleStrategy downsampleStrategy) {
        try {
            int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (parseInt3 == 90 || parseInt3 == 270) {
                int i6 = parseInt2;
                parseInt2 = parseInt;
                parseInt = i6;
            }
            float scaleFactor = downsampleStrategy.getScaleFactor(parseInt, parseInt2, i4, i5);
            return mediaMetadataRetriever.getScaledFrameAtTime(j2, i3, Math.round(((float) parseInt) * scaleFactor), Math.round(scaleFactor * ((float) parseInt2)));
        } catch (Throwable th) {
            if (!Log.isLoggable(TAG, 3)) {
                return null;
            }
            Log.d(TAG, "Exception trying to decode a scaled frame on oreo+, falling back to a fullsize frame", th);
            return null;
        }
    }

    @RequiresApi(30)
    private static boolean isHDR(MediaMetadataRetriever mediaMetadataRetriever) throws NumberFormatException {
        String extractMetadata = mediaMetadataRetriever.extractMetadata(36);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(35);
        int parseInt = Integer.parseInt(extractMetadata);
        return (parseInt == 7 || parseInt == 6) && Integer.parseInt(extractMetadata2) == 6;
    }

    @VisibleForTesting
    public static boolean isHdr180RotationFixRequired() {
        if (Build.MODEL.startsWith("Pixel") && Build.VERSION.SDK_INT == 33) {
            return isTBuildRequiringRotationFix();
        }
        int i3 = Build.VERSION.SDK_INT;
        return i3 >= 30 && i3 < 33;
    }

    private static boolean isTBuildRequiringRotationFix() {
        for (String startsWith : PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX) {
            if (Build.ID.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b A[Catch:{ all -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0065 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isUnsupportedFormat(@androidx.annotation.NonNull T r5, android.media.MediaMetadataRetriever r6) {
        /*
            r4 = this;
            java.lang.String r0 = "VideoDecoder"
            java.lang.String r1 = android.os.Build.DEVICE
            r2 = 0
            if (r1 == 0) goto L_0x006f
            java.lang.String r3 = ".+_cheets|cheets_.+"
            boolean r1 = r1.matches(r3)
            if (r1 == 0) goto L_0x006f
            r1 = 12
            r3 = 0
            java.lang.String r6 = r6.extractMetadata(r1)     // Catch:{ all -> 0x0053 }
            java.lang.String r1 = "video/webm"
            boolean r6 = r1.equals(r6)     // Catch:{ all -> 0x0053 }
            if (r6 != 0) goto L_0x0020
            return r2
        L_0x0020:
            android.media.MediaExtractor r6 = new android.media.MediaExtractor     // Catch:{ all -> 0x0053 }
            r6.<init>()     // Catch:{ all -> 0x0053 }
            com.bumptech.glide.load.resource.bitmap.VideoDecoder$MediaInitializer<T> r4 = r4.initializer     // Catch:{ all -> 0x004c }
            r4.initializeExtractor(r6, r5)     // Catch:{ all -> 0x004c }
            int r4 = r6.getTrackCount()     // Catch:{ all -> 0x004c }
            r5 = r2
        L_0x002f:
            if (r5 >= r4) goto L_0x004f
            android.media.MediaFormat r1 = r6.getTrackFormat(r5)     // Catch:{ all -> 0x004c }
            java.lang.String r3 = "mime"
            java.lang.String r1 = r1.getString(r3)     // Catch:{ all -> 0x004c }
            java.lang.String r3 = "video/x-vnd.on2.vp8"
            boolean r1 = r3.equals(r1)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x0049
            r6.release()
            r4 = 1
            return r4
        L_0x0049:
            int r5 = r5 + 1
            goto L_0x002f
        L_0x004c:
            r4 = move-exception
            r3 = r6
            goto L_0x0054
        L_0x004f:
            r6.release()
            goto L_0x0068
        L_0x0053:
            r4 = move-exception
        L_0x0054:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x0061 }
            if (r5 == 0) goto L_0x0063
            java.lang.String r5 = "Exception trying to extract track info for a webm video on CrOS."
            android.util.Log.d(r0, r5, r4)     // Catch:{ all -> 0x0061 }
            goto L_0x0063
        L_0x0061:
            r4 = move-exception
            goto L_0x0069
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.release()
        L_0x0068:
            return r2
        L_0x0069:
            if (r3 == 0) goto L_0x006e
            r3.release()
        L_0x006e:
            throw r4
        L_0x006f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.VideoDecoder.isUnsupportedFormat(java.lang.Object, android.media.MediaMetadataRetriever):boolean");
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ParcelFileDescriptorInitializer());
    }

    /* JADX INFO: finally extract failed */
    public Resource<Bitmap> decode(@NonNull T t2, int i3, int i4, @NonNull Options options) throws IOException {
        long longValue = ((Long) options.get(TARGET_FRAME)).longValue();
        if (longValue >= 0 || longValue == -1) {
            Integer num = (Integer) options.get(FRAME_OPTION);
            if (num == null) {
                num = 2;
            }
            DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
            if (downsampleStrategy == null) {
                downsampleStrategy = DownsampleStrategy.DEFAULT;
            }
            DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
            MediaMetadataRetriever build = this.factory.build();
            try {
                this.initializer.initializeRetriever(build, t2);
                Bitmap decodeFrame = decodeFrame(t2, build, longValue, num.intValue(), i3, i4, downsampleStrategy2);
                build.close();
                return BitmapResource.obtain(decodeFrame, this.bitmapPool);
            } catch (Throwable th) {
                build.close();
                throw th;
            }
        } else {
            throw new IllegalArgumentException(a.s("Requested frame must be non-negative, or DEFAULT_FRAME, given: ", longValue));
        }
    }

    public boolean handles(@NonNull T t2, @NonNull Options options) {
        return true;
    }

    @VisibleForTesting
    public VideoDecoder(BitmapPool bitmapPool2, MediaInitializer<T> mediaInitializer, MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory) {
        this.bitmapPool = bitmapPool2;
        this.initializer = mediaInitializer;
        this.factory = mediaMetadataRetrieverFactory;
    }
}
