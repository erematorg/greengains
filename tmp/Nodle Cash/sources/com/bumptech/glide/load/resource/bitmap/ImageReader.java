package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import com.bumptech.glide.util.Preconditions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

interface ImageReader {

    public static final class ByteArrayReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final byte[] bytes;
        private final List<ImageHeaderParser> parsers;

        public ByteArrayReader(byte[] bArr, List<ImageHeaderParser> list, ArrayPool arrayPool) {
            this.bytes = bArr;
            this.parsers = list;
            this.byteArrayPool = arrayPool;
        }

        @Nullable
        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            byte[] bArr = this.bytes;
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, ByteBuffer.wrap(this.bytes), this.byteArrayPool);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, ByteBuffer.wrap(this.bytes));
        }

        public void stopGrowingBuffers() {
        }
    }

    public static final class ByteBufferReader implements ImageReader {
        private final ByteBuffer buffer;
        private final ArrayPool byteArrayPool;
        private final List<ImageHeaderParser> parsers;

        public ByteBufferReader(ByteBuffer byteBuffer, List<ImageHeaderParser> list, ArrayPool arrayPool) {
            this.buffer = byteBuffer;
            this.parsers = list;
            this.byteArrayPool = arrayPool;
        }

        private InputStream stream() {
            return ByteBufferUtil.toStream(ByteBufferUtil.rewind(this.buffer));
        }

        @Nullable
        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(stream(), (Rect) null, options);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, ByteBufferUtil.rewind(this.buffer), this.byteArrayPool);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, ByteBufferUtil.rewind(this.buffer));
        }

        public void stopGrowingBuffers() {
        }
    }

    public static final class FileReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final File file;
        private final List<ImageHeaderParser> parsers;

        public FileReader(File file2, List<ImageHeaderParser> list, ArrayPool arrayPool) {
            this.file = file2;
            this.parsers = list;
            this.byteArrayPool = arrayPool;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A[SYNTHETIC, Splitter:B:13:0x001d] */
        @androidx.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.graphics.Bitmap decodeBitmap(android.graphics.BitmapFactory.Options r5) throws java.io.FileNotFoundException {
            /*
                r4 = this;
                r0 = 0
                com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x001a }
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x001a }
                java.io.File r3 = r4.file     // Catch:{ all -> 0x001a }
                r2.<init>(r3)     // Catch:{ all -> 0x001a }
                com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r4.byteArrayPool     // Catch:{ all -> 0x001a }
                r1.<init>(r2, r4)     // Catch:{ all -> 0x001a }
                android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r1, r0, r5)     // Catch:{ all -> 0x0017 }
                r1.close()     // Catch:{ IOException -> 0x0016 }
            L_0x0016:
                return r4
            L_0x0017:
                r4 = move-exception
                r0 = r1
                goto L_0x001b
            L_0x001a:
                r4 = move-exception
            L_0x001b:
                if (r0 == 0) goto L_0x0020
                r0.close()     // Catch:{ IOException -> 0x0020 }
            L_0x0020:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageReader.FileReader.decodeBitmap(android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0021 A[SYNTHETIC, Splitter:B:13:0x0021] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getImageOrientation() throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x001e }
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x001e }
                java.io.File r3 = r4.file     // Catch:{ all -> 0x001e }
                r2.<init>(r3)     // Catch:{ all -> 0x001e }
                com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r4.byteArrayPool     // Catch:{ all -> 0x001e }
                r1.<init>(r2, r3)     // Catch:{ all -> 0x001e }
                java.util.List<com.bumptech.glide.load.ImageHeaderParser> r0 = r4.parsers     // Catch:{ all -> 0x001b }
                com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r4.byteArrayPool     // Catch:{ all -> 0x001b }
                int r4 = com.bumptech.glide.load.ImageHeaderParserUtils.getOrientation((java.util.List<com.bumptech.glide.load.ImageHeaderParser>) r0, (java.io.InputStream) r1, (com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool) r4)     // Catch:{ all -> 0x001b }
                r1.close()     // Catch:{ IOException -> 0x001a }
            L_0x001a:
                return r4
            L_0x001b:
                r4 = move-exception
                r0 = r1
                goto L_0x001f
            L_0x001e:
                r4 = move-exception
            L_0x001f:
                if (r0 == 0) goto L_0x0024
                r0.close()     // Catch:{ IOException -> 0x0024 }
            L_0x0024:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageReader.FileReader.getImageOrientation():int");
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0021 A[SYNTHETIC, Splitter:B:13:0x0021] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.load.ImageHeaderParser.ImageType getImageType() throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x001e }
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x001e }
                java.io.File r3 = r4.file     // Catch:{ all -> 0x001e }
                r2.<init>(r3)     // Catch:{ all -> 0x001e }
                com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r4.byteArrayPool     // Catch:{ all -> 0x001e }
                r1.<init>(r2, r3)     // Catch:{ all -> 0x001e }
                java.util.List<com.bumptech.glide.load.ImageHeaderParser> r0 = r4.parsers     // Catch:{ all -> 0x001b }
                com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r4.byteArrayPool     // Catch:{ all -> 0x001b }
                com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParserUtils.getType((java.util.List<com.bumptech.glide.load.ImageHeaderParser>) r0, (java.io.InputStream) r1, (com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool) r4)     // Catch:{ all -> 0x001b }
                r1.close()     // Catch:{ IOException -> 0x001a }
            L_0x001a:
                return r4
            L_0x001b:
                r4 = move-exception
                r0 = r1
                goto L_0x001f
            L_0x001e:
                r4 = move-exception
            L_0x001f:
                if (r0 == 0) goto L_0x0024
                r0.close()     // Catch:{ IOException -> 0x0024 }
            L_0x0024:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageReader.FileReader.getImageType():com.bumptech.glide.load.ImageHeaderParser$ImageType");
        }

        public void stopGrowingBuffers() {
        }
    }

    public static final class InputStreamImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final InputStreamRewinder dataRewinder;
        private final List<ImageHeaderParser> parsers;

        public InputStreamImageReader(InputStream inputStream, List<ImageHeaderParser> list, ArrayPool arrayPool) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
            this.parsers = (List) Preconditions.checkNotNull(list);
            this.dataRewinder = new InputStreamRewinder(inputStream, arrayPool);
        }

        @Nullable
        public Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeStream(this.dataRewinder.rewindAndGet(), (Rect) null, options);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
            this.dataRewinder.fixMarkLimits();
        }
    }

    @RequiresApi(21)
    public static final class ParcelFileDescriptorImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final ParcelFileDescriptorRewinder dataRewinder;
        private final List<ImageHeaderParser> parsers;

        public ParcelFileDescriptorImageReader(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> list, ArrayPool arrayPool) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
            this.parsers = (List) Preconditions.checkNotNull(list);
            this.dataRewinder = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        @Nullable
        public Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeFileDescriptor(this.dataRewinder.rewindAndGet().getFileDescriptor(), (Rect) null, options);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder, this.byteArrayPool);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder, this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
        }
    }

    @Nullable
    Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException;

    int getImageOrientation() throws IOException;

    ImageHeaderParser.ImageType getImageType() throws IOException;

    void stopGrowingBuffers();
}
