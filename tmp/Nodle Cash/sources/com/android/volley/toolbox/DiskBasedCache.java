package com.android.volley.toolbox;

import A.a;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private static final int CACHE_MAGIC = 538247942;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    @VisibleForTesting
    static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final FileSupplier mRootDirectorySupplier;
    private long mTotalSize;

    public interface FileSupplier {
        File get();
    }

    public DiskBasedCache(final File file, int i3) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectorySupplier = new FileSupplier() {
            public File get() {
                return file;
            }
        };
        this.mMaxCacheSizeInBytes = i3;
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        StringBuilder p2 = a.p(String.valueOf(str.substring(0, length).hashCode()));
        p2.append(String.valueOf(str.substring(length).hashCode()));
        return p2.toString();
    }

    private void initializeIfRootDirectoryDeleted() {
        if (!this.mRootDirectorySupplier.get().exists()) {
            VolleyLog.d("Re-initializing cache after external clearing.", new Object[0]);
            this.mEntries.clear();
            this.mTotalSize = 0;
            initialize();
        }
    }

    private void pruneIfNeeded() {
        if (this.mTotalSize >= ((long) this.mMaxCacheSizeInBytes)) {
            int i3 = 0;
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            long j2 = this.mTotalSize;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> it = this.mEntries.entrySet().iterator();
            while (it.hasNext()) {
                CacheHeader cacheHeader = (CacheHeader) it.next().getValue();
                if (getFileForKey(cacheHeader.key).delete()) {
                    this.mTotalSize -= cacheHeader.size;
                } else {
                    String str = cacheHeader.key;
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
                }
                it.remove();
                i3++;
                if (((float) this.mTotalSize) < ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                    break;
                }
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i3), Long.valueOf(this.mTotalSize - j2), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.size;
        } else {
            this.mTotalSize = (cacheHeader.size - this.mEntries.get(str).size) + this.mTotalSize;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    public static List<Header> readHeaderList(CountingInputStream countingInputStream) throws IOException {
        int readInt = readInt(countingInputStream);
        if (readInt >= 0) {
            List<Header> emptyList = readInt == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i3 = 0; i3 < readInt; i3++) {
                emptyList.add(new Header(readString(countingInputStream).intern(), readString(countingInputStream).intern()));
            }
            return emptyList;
        }
        throw new IOException(a.k("readHeaderList size=", readInt));
    }

    public static int readInt(InputStream inputStream) throws IOException {
        return (read(inputStream) << 24) | read(inputStream) | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    public static long readLong(InputStream inputStream) throws IOException {
        return (((long) read(inputStream)) & 255) | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((255 & ((long) read(inputStream))) << 56);
    }

    public static String readString(CountingInputStream countingInputStream) throws IOException {
        return new String(streamToBytes(countingInputStream, readLong(countingInputStream)), "UTF-8");
    }

    private void removeEntry(String str) {
        CacheHeader remove = this.mEntries.remove(str);
        if (remove != null) {
            this.mTotalSize -= remove.size;
        }
    }

    @VisibleForTesting
    public static byte[] streamToBytes(CountingInputStream countingInputStream, long j2) throws IOException {
        long bytesRemaining = countingInputStream.bytesRemaining();
        if (j2 >= 0 && j2 <= bytesRemaining) {
            int i3 = (int) j2;
            if (((long) i3) == j2) {
                byte[] bArr = new byte[i3];
                new DataInputStream(countingInputStream).readFully(bArr);
                return bArr;
            }
        }
        StringBuilder u3 = androidx.compose.animation.core.a.u("streamToBytes length=", j2, ", maxLength=");
        u3.append(bytesRemaining);
        throw new IOException(u3.toString());
    }

    public static void writeHeaderList(List<Header> list, OutputStream outputStream) throws IOException {
        if (list != null) {
            writeInt(outputStream, list.size());
            for (Header next : list) {
                writeString(outputStream, next.getName());
                writeString(outputStream, next.getValue());
            }
            return;
        }
        writeInt(outputStream, 0);
    }

    public static void writeInt(OutputStream outputStream, int i3) throws IOException {
        outputStream.write(i3 & 255);
        outputStream.write((i3 >> 8) & 255);
        outputStream.write((i3 >> 16) & 255);
        outputStream.write((i3 >> 24) & 255);
    }

    public static void writeLong(OutputStream outputStream, long j2) throws IOException {
        outputStream.write((byte) ((int) j2));
        outputStream.write((byte) ((int) (j2 >>> 8)));
        outputStream.write((byte) ((int) (j2 >>> 16)));
        outputStream.write((byte) ((int) (j2 >>> 24)));
        outputStream.write((byte) ((int) (j2 >>> 32)));
        outputStream.write((byte) ((int) (j2 >>> 40)));
        outputStream.write((byte) ((int) (j2 >>> 48)));
        outputStream.write((byte) ((int) (j2 >>> 56)));
    }

    public static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    public synchronized void clear() {
        try {
            File[] listFiles = this.mRootDirectorySupplier.get().listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0;
            VolleyLog.d("Cache cleared.", new Object[0]);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public InputStream createInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @VisibleForTesting
    public OutputStream createOutputStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    public synchronized Cache.Entry get(String str) {
        CountingInputStream countingInputStream;
        CacheHeader cacheHeader = this.mEntries.get(str);
        if (cacheHeader == null) {
            return null;
        }
        File fileForKey = getFileForKey(str);
        try {
            countingInputStream = new CountingInputStream(new BufferedInputStream(createInputStream(fileForKey)), fileForKey.length());
            CacheHeader readHeader = CacheHeader.readHeader(countingInputStream);
            if (!TextUtils.equals(str, readHeader.key)) {
                VolleyLog.d("%s: key=%s, found=%s", fileForKey.getAbsolutePath(), str, readHeader.key);
                removeEntry(str);
                countingInputStream.close();
                return null;
            }
            Cache.Entry cacheEntry = cacheHeader.toCacheEntry(streamToBytes(countingInputStream, countingInputStream.bytesRemaining()));
            countingInputStream.close();
            return cacheEntry;
        } catch (IOException e3) {
            VolleyLog.d("%s: %s", fileForKey.getAbsolutePath(), e3.toString());
            remove(str);
            return null;
        } catch (Throwable th) {
            countingInputStream.close();
            throw th;
        }
    }

    public File getFileForKey(String str) {
        return new File(this.mRootDirectorySupplier.get(), getFilenameForKey(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0059 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r9 = this;
            monitor-enter(r9)
            com.android.volley.toolbox.DiskBasedCache$FileSupplier r0 = r9.mRootDirectorySupplier     // Catch:{ all -> 0x0021 }
            java.io.File r0 = r0.get()     // Catch:{ all -> 0x0021 }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x0021 }
            if (r1 != 0) goto L_0x0025
            boolean r1 = r0.mkdirs()     // Catch:{ all -> 0x0021 }
            if (r1 != 0) goto L_0x0023
            java.lang.String r1 = "Unable to create cache dir %s"
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x0021 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x0021 }
            com.android.volley.VolleyLog.e(r1, r0)     // Catch:{ all -> 0x0021 }
            goto L_0x0023
        L_0x0021:
            r0 = move-exception
            goto L_0x0061
        L_0x0023:
            monitor-exit(r9)
            return
        L_0x0025:
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x002d
            monitor-exit(r9)
            return
        L_0x002d:
            int r1 = r0.length     // Catch:{ all -> 0x0021 }
            r2 = 0
        L_0x002f:
            if (r2 >= r1) goto L_0x005f
            r3 = r0[r2]     // Catch:{ all -> 0x0021 }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0059 }
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r6 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x0059 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0059 }
            java.io.InputStream r8 = r9.createInputStream(r3)     // Catch:{ IOException -> 0x0059 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0059 }
            r6.<init>(r7, r4)     // Catch:{ IOException -> 0x0059 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r7 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r6)     // Catch:{ all -> 0x0054 }
            r7.size = r4     // Catch:{ all -> 0x0054 }
            java.lang.String r4 = r7.key     // Catch:{ all -> 0x0054 }
            r9.putEntry(r4, r7)     // Catch:{ all -> 0x0054 }
            r6.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005c
        L_0x0054:
            r4 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0059 }
            throw r4     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            r3.delete()     // Catch:{ all -> 0x0021 }
        L_0x005c:
            int r2 = r2 + 1
            goto L_0x002f
        L_0x005f:
            monitor-exit(r9)
            return
        L_0x0061:
            monitor-exit(r9)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    public synchronized void invalidate(String str, boolean z2) {
        try {
            Cache.Entry entry = get(str);
            if (entry != null) {
                entry.softTtl = 0;
                if (z2) {
                    entry.ttl = 0;
                }
                put(str, entry);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|19|(1:21)|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0065, code lost:
        if (r0.delete() == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        com.android.volley.VolleyLog.d("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0074, code lost:
        initializeIfRootDirectoryDeleted();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0061 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r7, com.android.volley.Cache.Entry r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mTotalSize     // Catch:{ all -> 0x001c }
            byte[] r2 = r8.data     // Catch:{ all -> 0x001c }
            int r3 = r2.length     // Catch:{ all -> 0x001c }
            long r3 = (long) r3     // Catch:{ all -> 0x001c }
            long r0 = r0 + r3
            int r3 = r6.mMaxCacheSizeInBytes     // Catch:{ all -> 0x001c }
            long r4 = (long) r3     // Catch:{ all -> 0x001c }
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x001e
            int r0 = r2.length     // Catch:{ all -> 0x001c }
            float r0 = (float) r0
            float r1 = (float) r3
            r2 = 1063675494(0x3f666666, float:0.9)
            float r1 = r1 * r2
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x001e
            monitor-exit(r6)
            return
        L_0x001c:
            r7 = move-exception
            goto L_0x0079
        L_0x001e:
            java.io.File r0 = r6.getFileForKey(r7)     // Catch:{ all -> 0x001c }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0061 }
            java.io.OutputStream r2 = r6.createOutputStream(r0)     // Catch:{ IOException -> 0x0061 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0061 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r2 = new com.android.volley.toolbox.DiskBasedCache$CacheHeader     // Catch:{ IOException -> 0x0061 }
            r2.<init>(r7, r8)     // Catch:{ IOException -> 0x0061 }
            boolean r3 = r2.writeHeader(r1)     // Catch:{ IOException -> 0x0061 }
            if (r3 == 0) goto L_0x004b
            byte[] r8 = r8.data     // Catch:{ IOException -> 0x0061 }
            r1.write(r8)     // Catch:{ IOException -> 0x0061 }
            r1.close()     // Catch:{ IOException -> 0x0061 }
            long r3 = r0.length()     // Catch:{ IOException -> 0x0061 }
            r2.size = r3     // Catch:{ IOException -> 0x0061 }
            r6.putEntry(r7, r2)     // Catch:{ IOException -> 0x0061 }
            r6.pruneIfNeeded()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0077
        L_0x004b:
            r1.close()     // Catch:{ IOException -> 0x0061 }
            java.lang.String r7 = "Failed to write header for %s"
            java.lang.String r8 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0061 }
            java.lang.Object[] r8 = new java.lang.Object[]{r8}     // Catch:{ IOException -> 0x0061 }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ IOException -> 0x0061 }
            java.io.IOException r7 = new java.io.IOException     // Catch:{ IOException -> 0x0061 }
            r7.<init>()     // Catch:{ IOException -> 0x0061 }
            throw r7     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            boolean r7 = r0.delete()     // Catch:{ all -> 0x001c }
            if (r7 != 0) goto L_0x0074
            java.lang.String r7 = "Could not clean up file %s"
            java.lang.String r8 = r0.getAbsolutePath()     // Catch:{ all -> 0x001c }
            java.lang.Object[] r8 = new java.lang.Object[]{r8}     // Catch:{ all -> 0x001c }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ all -> 0x001c }
        L_0x0074:
            r6.initializeIfRootDirectoryDeleted()     // Catch:{ all -> 0x001c }
        L_0x0077:
            monitor-exit(r6)
            return
        L_0x0079:
            monitor-exit(r6)     // Catch:{ all -> 0x001c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.put(java.lang.String, com.android.volley.Cache$Entry):void");
    }

    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        removeEntry(str);
        if (!delete) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
        }
    }

    @VisibleForTesting
    public static class CountingInputStream extends FilterInputStream {
        private long bytesRead;
        private final long length;

        public CountingInputStream(InputStream inputStream, long j2) {
            super(inputStream);
            this.length = j2;
        }

        @VisibleForTesting
        public long bytesRead() {
            return this.bytesRead;
        }

        public long bytesRemaining() {
            return this.length - this.bytesRead;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        public int read(byte[] bArr, int i3, int i4) throws IOException {
            int read = super.read(bArr, i3, i4);
            if (read != -1) {
                this.bytesRead += (long) read;
            }
            return read;
        }
    }

    public DiskBasedCache(FileSupplier fileSupplier, int i3) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectorySupplier = fileSupplier;
        this.mMaxCacheSizeInBytes = i3;
    }

    @VisibleForTesting
    public static class CacheHeader {
        final List<Header> allResponseHeaders;
        final String etag;
        final String key;
        final long lastModified;
        final long serverDate;
        long size;
        final long softTtl;
        final long ttl;

        private CacheHeader(String str, String str2, long j2, long j3, long j4, long j5, List<Header> list) {
            this.key = str;
            this.etag = "".equals(str2) ? null : str2;
            this.serverDate = j2;
            this.lastModified = j3;
            this.ttl = j4;
            this.softTtl = j5;
            this.allResponseHeaders = list;
        }

        private static List<Header> getAllResponseHeaders(Cache.Entry entry) {
            List<Header> list = entry.allResponseHeaders;
            return list != null ? list : HttpHeaderParser.toAllHeaderList(entry.responseHeaders);
        }

        public static CacheHeader readHeader(CountingInputStream countingInputStream) throws IOException {
            if (DiskBasedCache.readInt(countingInputStream) == DiskBasedCache.CACHE_MAGIC) {
                return new CacheHeader(DiskBasedCache.readString(countingInputStream), DiskBasedCache.readString(countingInputStream), DiskBasedCache.readLong(countingInputStream), DiskBasedCache.readLong(countingInputStream), DiskBasedCache.readLong(countingInputStream), DiskBasedCache.readLong(countingInputStream), DiskBasedCache.readHeaderList(countingInputStream));
            }
            throw new IOException();
        }

        public Cache.Entry toCacheEntry(byte[] bArr) {
            Cache.Entry entry = new Cache.Entry();
            entry.data = bArr;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.lastModified = this.lastModified;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            entry.responseHeaders = HttpHeaderParser.toHeaderMap(this.allResponseHeaders);
            entry.allResponseHeaders = Collections.unmodifiableList(this.allResponseHeaders);
            return entry;
        }

        public boolean writeHeader(OutputStream outputStream) {
            try {
                DiskBasedCache.writeInt(outputStream, DiskBasedCache.CACHE_MAGIC);
                DiskBasedCache.writeString(outputStream, this.key);
                String str = this.etag;
                if (str == null) {
                    str = "";
                }
                DiskBasedCache.writeString(outputStream, str);
                DiskBasedCache.writeLong(outputStream, this.serverDate);
                DiskBasedCache.writeLong(outputStream, this.lastModified);
                DiskBasedCache.writeLong(outputStream, this.ttl);
                DiskBasedCache.writeLong(outputStream, this.softTtl);
                DiskBasedCache.writeHeaderList(this.allResponseHeaders, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e3) {
                VolleyLog.d("%s", e3.toString());
                return false;
            }
        }

        public CacheHeader(String str, Cache.Entry entry) {
            this(str, entry.etag, entry.serverDate, entry.lastModified, entry.ttl, entry.softTtl, getAllResponseHeaders(entry));
        }
    }

    public DiskBasedCache(File file) {
        this(file, (int) DEFAULT_DISK_USAGE_BYTES);
    }

    public DiskBasedCache(FileSupplier fileSupplier) {
        this(fileSupplier, (int) DEFAULT_DISK_USAGE_BYTES);
    }
}
