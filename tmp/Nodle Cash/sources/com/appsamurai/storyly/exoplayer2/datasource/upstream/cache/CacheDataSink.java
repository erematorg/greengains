package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSink;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class CacheDataSink implements DataSink {
    public static final int DEFAULT_BUFFER_SIZE = 20480;
    public static final long DEFAULT_FRAGMENT_SIZE = 5242880;
    private static final long MIN_RECOMMENDED_FRAGMENT_SIZE = 2097152;
    private static final String TAG = "CacheDataSink";
    private final int bufferSize;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private final Cache cache;
    @Nullable
    private DataSpec dataSpec;
    private long dataSpecBytesWritten;
    private long dataSpecFragmentSize;
    @Nullable
    private File file;
    private final long fragmentSize;
    @Nullable
    private OutputStream outputStream;
    private long outputStreamBytesWritten;

    public static final class CacheDataSinkException extends Cache.CacheException {
        public CacheDataSinkException(IOException iOException) {
            super((Throwable) iOException);
        }
    }

    public static final class Factory implements DataSink.Factory {
        private int bufferSize = CacheDataSink.DEFAULT_BUFFER_SIZE;
        private Cache cache;
        private long fragmentSize = CacheDataSink.DEFAULT_FRAGMENT_SIZE;

        public DataSink createDataSink() {
            return new CacheDataSink((Cache) Assertions.checkNotNull(this.cache), this.fragmentSize, this.bufferSize);
        }

        public Factory setBufferSize(int i3) {
            this.bufferSize = i3;
            return this;
        }

        public Factory setCache(Cache cache2) {
            this.cache = cache2;
            return this;
        }

        public Factory setFragmentSize(long j2) {
            this.fragmentSize = j2;
            return this;
        }
    }

    public CacheDataSink(Cache cache2, long j2) {
        this(cache2, j2, DEFAULT_BUFFER_SIZE);
    }

    private void closeCurrentOutputStream() throws IOException {
        OutputStream outputStream2 = this.outputStream;
        if (outputStream2 != null) {
            try {
                outputStream2.flush();
                Util.closeQuietly(this.outputStream);
                this.outputStream = null;
                this.file = null;
                this.cache.commitFile((File) Util.castNonNull(this.file), this.outputStreamBytesWritten);
            } catch (Throwable th) {
                Util.closeQuietly(this.outputStream);
                this.outputStream = null;
                this.file = null;
                ((File) Util.castNonNull(this.file)).delete();
                throw th;
            }
        }
    }

    private void openNextOutputStream(DataSpec dataSpec2) throws IOException {
        long j2 = dataSpec2.length;
        long j3 = -1;
        if (j2 != -1) {
            j3 = Math.min(j2 - this.dataSpecBytesWritten, this.dataSpecFragmentSize);
        }
        this.file = this.cache.startFile((String) Util.castNonNull(dataSpec2.key), dataSpec2.position + this.dataSpecBytesWritten, j3);
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        if (this.bufferSize > 0) {
            ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
            if (reusableBufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(fileOutputStream, this.bufferSize);
            } else {
                reusableBufferedOutputStream.reset(fileOutputStream);
            }
            this.outputStream = this.bufferedOutputStream;
        } else {
            this.outputStream = fileOutputStream;
        }
        this.outputStreamBytesWritten = 0;
    }

    public void close() throws CacheDataSinkException {
        if (this.dataSpec != null) {
            try {
                closeCurrentOutputStream();
            } catch (IOException e3) {
                throw new CacheDataSinkException(e3);
            }
        }
    }

    public void open(DataSpec dataSpec2) throws CacheDataSinkException {
        Assertions.checkNotNull(dataSpec2.key);
        if (dataSpec2.length != -1 || !dataSpec2.isFlagSet(2)) {
            this.dataSpec = dataSpec2;
            this.dataSpecFragmentSize = dataSpec2.isFlagSet(4) ? this.fragmentSize : Long.MAX_VALUE;
            this.dataSpecBytesWritten = 0;
            try {
                openNextOutputStream(dataSpec2);
            } catch (IOException e3) {
                throw new CacheDataSinkException(e3);
            }
        } else {
            this.dataSpec = null;
        }
    }

    public void write(byte[] bArr, int i3, int i4) throws CacheDataSinkException {
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            int i5 = 0;
            while (i5 < i4) {
                try {
                    if (this.outputStreamBytesWritten == this.dataSpecFragmentSize) {
                        closeCurrentOutputStream();
                        openNextOutputStream(dataSpec2);
                    }
                    int min = (int) Math.min((long) (i4 - i5), this.dataSpecFragmentSize - this.outputStreamBytesWritten);
                    ((OutputStream) Util.castNonNull(this.outputStream)).write(bArr, i3 + i5, min);
                    i5 += min;
                    long j2 = (long) min;
                    this.outputStreamBytesWritten += j2;
                    this.dataSpecBytesWritten += j2;
                } catch (IOException e3) {
                    throw new CacheDataSinkException(e3);
                }
            }
        }
    }

    public CacheDataSink(Cache cache2, long j2, int i3) {
        Assertions.checkState(j2 > 0 || j2 == -1, "fragmentSize must be positive or C.LENGTH_UNSET.");
        int i4 = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
        if (i4 != 0 && j2 < 2097152) {
            Log.w(TAG, "fragmentSize is below the minimum recommended value of 2097152. This may cause poor cache performance.");
        }
        this.cache = (Cache) Assertions.checkNotNull(cache2);
        this.fragmentSize = i4 == 0 ? Long.MAX_VALUE : j2;
        this.bufferSize = i3;
    }
}
