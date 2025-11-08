package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import android.os.ConditionVariable;
import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.h;
import com.appsamurai.storyly.exoplayer2.database.DatabaseIOException;
import com.appsamurai.storyly.exoplayer2.database.DatabaseProvider;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public final class SimpleCache implements Cache {
    private static final int SUBDIRECTORY_COUNT = 10;
    private static final String TAG = "SimpleCache";
    private static final String UID_FILE_SUFFIX = ".uid";
    private static final HashSet<File> lockedCacheDirs = new HashSet<>();
    private final File cacheDir;
    private final CachedContentIndex contentIndex;
    /* access modifiers changed from: private */
    public final CacheEvictor evictor;
    @Nullable
    private final CacheFileMetadataIndex fileIndex;
    private Cache.CacheException initializationException;
    private final HashMap<String, ArrayList<Cache.Listener>> listeners;
    private final Random random;
    private boolean released;
    private long totalSpace;
    private final boolean touchCacheSpans;
    private long uid;

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, (byte[]) null, false);
    }

    private void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.contentIndex.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    private static void createCacheDirectories(File file) throws Cache.CacheException {
        if (!file.mkdirs() && !file.isDirectory()) {
            String str = "Failed to create cache directory: " + file;
            Log.e(TAG, str);
            throw new Cache.CacheException(str);
        }
    }

    private static long createUid(File file) throws IOException {
        long nextLong = new SecureRandom().nextLong();
        long abs = nextLong == Long.MIN_VALUE ? 0 : Math.abs(nextLong);
        File file2 = new File(file, a.m(Long.toString(abs, 16), UID_FILE_SUFFIX));
        if (file2.createNewFile()) {
            return abs;
        }
        throw new IOException("Failed to create UID file: " + file2);
    }

    @WorkerThread
    public static void delete(File file, @Nullable DatabaseProvider databaseProvider) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                file.delete();
                return;
            }
            if (databaseProvider != null) {
                long loadUid = loadUid(listFiles);
                if (loadUid != -1) {
                    try {
                        CacheFileMetadataIndex.delete(databaseProvider, loadUid);
                    } catch (DatabaseIOException unused) {
                        Log.w(TAG, "Failed to delete file metadata: " + loadUid);
                    }
                    try {
                        CachedContentIndex.delete(databaseProvider, loadUid);
                    } catch (DatabaseIOException unused2) {
                        Log.w(TAG, "Failed to delete file metadata: " + loadUid);
                    }
                }
            }
            Util.recursiveDelete(file);
        }
    }

    private SimpleCacheSpan getSpan(String str, long j2, long j3) {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createHole(str, j2, j3);
        }
        while (true) {
            span = cachedContent.getSpan(j2, j3);
            if (!span.isCached || span.file.length() == span.length) {
                return span;
            }
            removeStaleSpans();
        }
        return span;
    }

    /* access modifiers changed from: private */
    public void initialize() {
        if (!this.cacheDir.exists()) {
            try {
                createCacheDirectories(this.cacheDir);
            } catch (Cache.CacheException e3) {
                this.initializationException = e3;
                return;
            }
        }
        File[] listFiles = this.cacheDir.listFiles();
        if (listFiles == null) {
            String str = "Failed to list cache directory files: " + this.cacheDir;
            Log.e(TAG, str);
            this.initializationException = new Cache.CacheException(str);
            return;
        }
        long loadUid = loadUid(listFiles);
        this.uid = loadUid;
        if (loadUid == -1) {
            try {
                this.uid = createUid(this.cacheDir);
            } catch (IOException e4) {
                String str2 = "Failed to create cache UID: " + this.cacheDir;
                Log.e(TAG, str2, e4);
                this.initializationException = new Cache.CacheException(str2, e4);
                return;
            }
        }
        try {
            this.contentIndex.initialize(this.uid);
            CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
            if (cacheFileMetadataIndex != null) {
                cacheFileMetadataIndex.initialize(this.uid);
                Map<String, CacheFileMetadata> all = this.fileIndex.getAll();
                loadDirectory(this.cacheDir, true, listFiles, all);
                this.fileIndex.removeAll(all.keySet());
            } else {
                loadDirectory(this.cacheDir, true, listFiles, (Map<String, CacheFileMetadata>) null);
            }
            this.contentIndex.removeEmpty();
            try {
                this.contentIndex.store();
            } catch (IOException e5) {
                Log.e(TAG, "Storing index file failed", e5);
            }
        } catch (IOException e6) {
            String str3 = "Failed to initialize cache indices: " + this.cacheDir;
            Log.e(TAG, str3, e6);
            this.initializationException = new Cache.CacheException(str3, e6);
        }
    }

    public static synchronized boolean isCacheFolderLocked(File file) {
        boolean contains;
        synchronized (SimpleCache.class) {
            contains = lockedCacheDirs.contains(file.getAbsoluteFile());
        }
        return contains;
    }

    private void loadDirectory(File file, boolean z2, @Nullable File[] fileArr, @Nullable Map<String, CacheFileMetadata> map) {
        long j2;
        long j3;
        if (fileArr != null && fileArr.length != 0) {
            for (File file2 : fileArr) {
                String name = file2.getName();
                if (z2 && name.indexOf(46) == -1) {
                    loadDirectory(file2, false, file2.listFiles(), map);
                } else if (!z2 || (!CachedContentIndex.isIndexFile(name) && !name.endsWith(UID_FILE_SUFFIX))) {
                    CacheFileMetadata remove = map != null ? map.remove(name) : null;
                    if (remove != null) {
                        j3 = remove.length;
                        j2 = remove.lastTouchTimestamp;
                    } else {
                        j2 = -9223372036854775807L;
                        j3 = -1;
                    }
                    SimpleCacheSpan createCacheEntry = SimpleCacheSpan.createCacheEntry(file2, j3, j2, this.contentIndex);
                    if (createCacheEntry != null) {
                        addSpan(createCacheEntry);
                    } else {
                        file2.delete();
                    }
                }
            }
        } else if (!z2) {
            file.delete();
        }
    }

    private static long loadUid(File[] fileArr) {
        int length = fileArr.length;
        int i3 = 0;
        while (i3 < length) {
            File file = fileArr[i3];
            String name = file.getName();
            if (name.endsWith(UID_FILE_SUFFIX)) {
                try {
                    return parseUid(name);
                } catch (NumberFormatException unused) {
                    Log.e(TAG, "Malformed UID file: " + file);
                    file.delete();
                }
            } else {
                i3++;
            }
        }
        return -1;
    }

    private static synchronized boolean lockFolder(File file) {
        boolean add;
        synchronized (SimpleCache.class) {
            add = lockedCacheDirs.add(file.getAbsoluteFile());
        }
        return add;
    }

    private void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    private static long parseUid(String str) {
        return Long.parseLong(str.substring(0, str.indexOf(46)), 16);
    }

    private void removeSpanInternal(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        if (cachedContent != null && cachedContent.removeSpan(cacheSpan)) {
            this.totalSpace -= cacheSpan.length;
            if (this.fileIndex != null) {
                String name = cacheSpan.file.getName();
                try {
                    this.fileIndex.remove(name);
                } catch (IOException unused) {
                    h.a("Failed to remove file index entry for: ", name, TAG);
                }
            }
            this.contentIndex.maybeRemove(cachedContent.key);
            notifySpanRemoved(cacheSpan);
        }
    }

    private void removeStaleSpans() {
        ArrayList arrayList = new ArrayList();
        for (CachedContent spans : this.contentIndex.getAll()) {
            Iterator<SimpleCacheSpan> it = spans.getSpans().iterator();
            while (it.hasNext()) {
                CacheSpan next = it.next();
                if (next.file.length() != next.length) {
                    arrayList.add(next);
                }
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            removeSpanInternal((CacheSpan) arrayList.get(i3));
        }
    }

    private SimpleCacheSpan touchSpan(String str, SimpleCacheSpan simpleCacheSpan) {
        boolean z2;
        if (!this.touchCacheSpans) {
            return simpleCacheSpan;
        }
        String name = ((File) Assertions.checkNotNull(simpleCacheSpan.file)).getName();
        long j2 = simpleCacheSpan.length;
        long currentTimeMillis = System.currentTimeMillis();
        CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
        if (cacheFileMetadataIndex != null) {
            try {
                cacheFileMetadataIndex.set(name, j2, currentTimeMillis);
            } catch (IOException unused) {
                Log.w(TAG, "Failed to update index with new touch timestamp.");
            }
            z2 = false;
        } else {
            z2 = true;
        }
        SimpleCacheSpan lastTouchTimestamp = this.contentIndex.get(str).setLastTouchTimestamp(simpleCacheSpan, currentTimeMillis, z2);
        notifySpanTouched(simpleCacheSpan, lastTouchTimestamp);
        return lastTouchTimestamp;
    }

    private static synchronized void unlockFolder(File file) {
        synchronized (SimpleCache.class) {
            lockedCacheDirs.remove(file.getAbsoluteFile());
        }
    }

    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        try {
            Assertions.checkState(!this.released);
            Assertions.checkNotNull(str);
            Assertions.checkNotNull(listener);
            ArrayList arrayList = this.listeners.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.listeners.put(str, arrayList);
            }
            arrayList.add(listener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return getCachedSpans(str);
    }

    public synchronized void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws Cache.CacheException {
        Assertions.checkState(!this.released);
        checkInitialization();
        this.contentIndex.applyContentMetadataMutations(str, contentMetadataMutations);
        try {
            this.contentIndex.store();
        } catch (IOException e3) {
            throw new Cache.CacheException((Throwable) e3);
        }
    }

    public synchronized void checkInitialization() throws Cache.CacheException {
        Cache.CacheException cacheException = this.initializationException;
        if (cacheException != null) {
            throw cacheException;
        }
    }

    public synchronized void commitFile(File file, long j2) throws Cache.CacheException {
        boolean z2 = false;
        Assertions.checkState(!this.released);
        if (file.exists()) {
            if (j2 == 0) {
                file.delete();
                return;
            }
            SimpleCacheSpan simpleCacheSpan = (SimpleCacheSpan) Assertions.checkNotNull(SimpleCacheSpan.createCacheEntry(file, j2, this.contentIndex));
            CachedContent cachedContent = (CachedContent) Assertions.checkNotNull(this.contentIndex.get(simpleCacheSpan.key));
            Assertions.checkState(cachedContent.isFullyLocked(simpleCacheSpan.position, simpleCacheSpan.length));
            long contentLength = ContentMetadata.getContentLength(cachedContent.getMetadata());
            if (contentLength != -1) {
                if (simpleCacheSpan.position + simpleCacheSpan.length <= contentLength) {
                    z2 = true;
                }
                Assertions.checkState(z2);
            }
            if (this.fileIndex != null) {
                try {
                    this.fileIndex.set(file.getName(), simpleCacheSpan.length, simpleCacheSpan.lastTouchTimestamp);
                } catch (IOException e3) {
                    throw new Cache.CacheException((Throwable) e3);
                } catch (IOException e4) {
                    throw new Cache.CacheException((Throwable) e4);
                }
            }
            addSpan(simpleCacheSpan);
            this.contentIndex.store();
            notifyAll();
        }
    }

    public synchronized long getCacheSpace() {
        Assertions.checkState(!this.released);
        return this.totalSpace;
    }

    public synchronized long getCachedBytes(String str, long j2, long j3) {
        long j4;
        long j5 = j3 == -1 ? Long.MAX_VALUE : j2 + j3;
        long j6 = j5 < 0 ? Long.MAX_VALUE : j5;
        long j7 = j2;
        j4 = 0;
        while (j7 < j6) {
            long cachedLength = getCachedLength(str, j7, j6 - j7);
            if (cachedLength > 0) {
                j4 += cachedLength;
            } else {
                cachedLength = -cachedLength;
            }
            j7 += cachedLength;
        }
        return j4;
    }

    public synchronized long getCachedLength(String str, long j2, long j3) {
        CachedContent cachedContent;
        Assertions.checkState(!this.released);
        if (j3 == -1) {
            j3 = Long.MAX_VALUE;
        }
        cachedContent = this.contentIndex.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j2, j3) : -j3;
    }

    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        TreeSet treeSet;
        try {
            Assertions.checkState(!this.released);
            CachedContent cachedContent = this.contentIndex.get(str);
            if (cachedContent != null) {
                if (!cachedContent.isEmpty()) {
                    treeSet = new TreeSet(cachedContent.getSpans());
                }
            }
            treeSet = new TreeSet();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return treeSet;
    }

    public synchronized ContentMetadata getContentMetadata(String str) {
        Assertions.checkState(!this.released);
        return this.contentIndex.getContentMetadata(str);
    }

    public synchronized Set<String> getKeys() {
        Assertions.checkState(!this.released);
        return new HashSet(this.contentIndex.getKeys());
    }

    public synchronized long getUid() {
        return this.uid;
    }

    public synchronized boolean isCached(String str, long j2, long j3) {
        boolean z2;
        z2 = false;
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent != null && cachedContent.getCachedBytesLength(j2, j3) >= j3) {
            z2 = true;
        }
        return z2;
    }

    public synchronized void release() {
        if (!this.released) {
            this.listeners.clear();
            removeStaleSpans();
            try {
                this.contentIndex.store();
                unlockFolder(this.cacheDir);
            } catch (IOException e3) {
                try {
                    Log.e(TAG, "Storing index file failed", e3);
                    unlockFolder(this.cacheDir);
                } catch (Throwable th) {
                    unlockFolder(this.cacheDir);
                    this.released = true;
                    throw th;
                }
            }
            this.released = true;
            return;
        }
        return;
    }

    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        CachedContent cachedContent = (CachedContent) Assertions.checkNotNull(this.contentIndex.get(cacheSpan.key));
        cachedContent.unlockRange(cacheSpan.position);
        this.contentIndex.maybeRemove(cachedContent.key);
        notifyAll();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeListener(java.lang.String r2, com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache.Listener r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.released     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            java.util.HashMap<java.lang.String, java.util.ArrayList<com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache$Listener>> r0 = r1.listeners     // Catch:{ all -> 0x0020 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0020 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0022
            r0.remove(r3)     // Catch:{ all -> 0x0020 }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x0022
            java.util.HashMap<java.lang.String, java.util.ArrayList<com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache$Listener>> r3 = r1.listeners     // Catch:{ all -> 0x0020 }
            r3.remove(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x0022
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            monitor-exit(r1)
            return
        L_0x0024:
            monitor-exit(r1)     // Catch:{ all -> 0x0020 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.SimpleCache.removeListener(java.lang.String, com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache$Listener):void");
    }

    public synchronized void removeResource(String str) {
        Assertions.checkState(!this.released);
        for (CacheSpan removeSpanInternal : getCachedSpans(str)) {
            removeSpanInternal(removeSpanInternal);
        }
    }

    public synchronized void removeSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        removeSpanInternal(cacheSpan);
    }

    public synchronized File startFile(String str, long j2, long j3) throws Cache.CacheException {
        CachedContent cachedContent;
        File file;
        try {
            Assertions.checkState(!this.released);
            checkInitialization();
            cachedContent = this.contentIndex.get(str);
            Assertions.checkNotNull(cachedContent);
            Assertions.checkState(cachedContent.isFullyLocked(j2, j3));
            if (!this.cacheDir.exists()) {
                createCacheDirectories(this.cacheDir);
                removeStaleSpans();
            }
            this.evictor.onStartFile(this, str, j2, j3);
            file = new File(this.cacheDir, Integer.toString(this.random.nextInt(10)));
            if (!file.exists()) {
                createCacheDirectories(file);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return SimpleCacheSpan.getCacheFile(file, cachedContent.id, j2, System.currentTimeMillis());
    }

    public synchronized CacheSpan startReadWrite(String str, long j2, long j3) throws InterruptedException, Cache.CacheException {
        CacheSpan startReadWriteNonBlocking;
        Assertions.checkState(!this.released);
        checkInitialization();
        while (true) {
            startReadWriteNonBlocking = startReadWriteNonBlocking(str, j2, j3);
            if (startReadWriteNonBlocking == null) {
                wait();
            }
        }
        return startReadWriteNonBlocking;
    }

    @Nullable
    public synchronized CacheSpan startReadWriteNonBlocking(String str, long j2, long j3) throws Cache.CacheException {
        Assertions.checkState(!this.released);
        checkInitialization();
        SimpleCacheSpan span = getSpan(str, j2, j3);
        if (span.isCached) {
            return touchSpan(str, span);
        } else if (this.contentIndex.getOrAdd(str).lockRange(j2, span.length)) {
            return span;
        } else {
            return null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable byte[] bArr) {
        this(file, cacheEvictor, bArr, bArr != null);
    }

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable byte[] bArr, boolean z2) {
        this(file, cacheEvictor, (DatabaseProvider) null, bArr, z2, true);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider) {
        this(file, cacheEvictor, databaseProvider, (byte[]) null, false, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable DatabaseProvider databaseProvider, @Nullable byte[] bArr, boolean z2, boolean z3) {
        this(file, cacheEvictor, new CachedContentIndex(databaseProvider, file, bArr, z2, z3), (databaseProvider == null || z3) ? null : new CacheFileMetadataIndex(databaseProvider));
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex, @Nullable CacheFileMetadataIndex cacheFileMetadataIndex) {
        if (lockFolder(file)) {
            this.cacheDir = file;
            this.evictor = cacheEvictor;
            this.contentIndex = cachedContentIndex;
            this.fileIndex = cacheFileMetadataIndex;
            this.listeners = new HashMap<>();
            this.random = new Random();
            this.touchCacheSpans = cacheEvictor.requiresCacheSpanTouches();
            this.uid = -1;
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread("ExoPlayer:SimpleCacheInit") {
                public void run() {
                    synchronized (SimpleCache.this) {
                        conditionVariable.open();
                        SimpleCache.this.initialize();
                        SimpleCache.this.evictor.onCacheInitialized();
                    }
                }
            }.start();
            conditionVariable.block();
            return;
        }
        throw new IllegalStateException("Another SimpleCache instance uses the folder: " + file);
    }
}
