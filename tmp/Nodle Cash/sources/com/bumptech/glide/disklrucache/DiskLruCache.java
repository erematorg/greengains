package com.bumptech.glide.disklrucache;

import android.annotation.TargetApi;
import android.os.StrictMode;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Callable<Void> cleanupCallable = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r3 = this;
                com.bumptech.glide.disklrucache.DiskLruCache r0 = com.bumptech.glide.disklrucache.DiskLruCache.this
                monitor-enter(r0)
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x000e }
                java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x000e }
                r2 = 0
                if (r1 != 0) goto L_0x0010
                monitor-exit(r0)     // Catch:{ all -> 0x000e }
                return r2
            L_0x000e:
                r3 = move-exception
                goto L_0x002a
            L_0x0010:
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x000e }
                r1.trimToSize()     // Catch:{ all -> 0x000e }
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x000e }
                boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x000e }
                if (r1 == 0) goto L_0x0028
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x000e }
                r1.rebuildJournal()     // Catch:{ all -> 0x000e }
                com.bumptech.glide.disklrucache.DiskLruCache r3 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x000e }
                r1 = 0
                int unused = r3.redundantOpCount = r1     // Catch:{ all -> 0x000e }
            L_0x0028:
                monitor-exit(r0)     // Catch:{ all -> 0x000e }
                return r2
            L_0x002a:
                monitor-exit(r0)     // Catch:{ all -> 0x000e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.AnonymousClass1.call():java.lang.Void");
        }
    };
    /* access modifiers changed from: private */
    public final File directory;
    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DiskLruCacheThreadFactory());
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    public static final class DiskLruCacheThreadFactory implements ThreadFactory {
        private DiskLruCacheThreadFactory() {
        }

        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    public final class Editor {
        private boolean committed;
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public final boolean[] written;

        private InputStream newInputStream(int i3) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                } else if (!this.entry.readable) {
                    return null;
                } else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.entry.getCleanFile(i3));
                        return fileInputStream;
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                }
            }
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (IOException unused) {
                }
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.completeEdit(this, true);
            this.committed = true;
        }

        public File getFile(int i3) throws IOException {
            File dirtyFile;
            synchronized (DiskLruCache.this) {
                try {
                    if (this.entry.currentEditor == this) {
                        if (!this.entry.readable) {
                            this.written[i3] = true;
                        }
                        dirtyFile = this.entry.getDirtyFile(i3);
                        DiskLruCache.this.directory.mkdirs();
                    } else {
                        throw new IllegalStateException();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return dirtyFile;
        }

        public String getString(int i3) throws IOException {
            InputStream newInputStream = newInputStream(i3);
            if (newInputStream != null) {
                return DiskLruCache.inputStreamToString(newInputStream);
            }
            return null;
        }

        public void set(int i3, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(getFile(i3)), Util.UTF_8);
                try {
                    outputStreamWriter2.write(str);
                    Util.closeQuietly(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    Util.closeQuietly(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Util.closeQuietly(outputStreamWriter);
                throw th;
            }
        }

        private Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }
    }

    public final class Entry {
        File[] cleanFiles;
        /* access modifiers changed from: private */
        public Editor currentEditor;
        File[] dirtyFiles;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.valueCount) {
                int i3 = 0;
                while (i3 < strArr.length) {
                    try {
                        this.lengths[i3] = Long.parseLong(strArr[i3]);
                        i3++;
                    } catch (NumberFormatException unused) {
                        throw invalidLengths(strArr);
                    }
                }
                return;
            }
            throw invalidLengths(strArr);
        }

        public File getCleanFile(int i3) {
            return this.cleanFiles[i3];
        }

        public File getDirtyFile(int i3) {
            return this.dirtyFiles[i3];
        }

        public String getLengths() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.lengths) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sb = new StringBuilder(str);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            int length = sb.length();
            for (int i3 = 0; i3 < DiskLruCache.this.valueCount; i3++) {
                sb.append(i3);
                this.cleanFiles[i3] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i3] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }
    }

    public final class Value {
        private final File[] files;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public File getFile(int i3) {
            return this.files[i3];
        }

        public long getLength(int i3) {
            return this.lengths[i3];
        }

        public String getString(int i3) throws IOException {
            return DiskLruCache.inputStreamToString(new FileInputStream(this.files[i3]));
        }

        private Value(String str, long j2, File[] fileArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j2;
            this.files = fileArr;
            this.lengths = jArr;
        }
    }

    private DiskLruCache(File file, int i3, int i4, long j2) {
        File file2 = file;
        this.directory = file2;
        this.appVersion = i3;
        this.journalFile = new File(file2, "journal");
        this.journalFileTmp = new File(file2, "journal.tmp");
        this.journalFileBackup = new File(file2, "journal.bkp");
        this.valueCount = i4;
        this.maxSize = j2;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @TargetApi(26)
    private static void closeWriter(Writer writer) throws IOException {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x010a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(com.bumptech.glide.disklrucache.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = r10.entry     // Catch:{ all -> 0x0030 }
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x0030 }
            if (r1 != r10) goto L_0x010b
            r1 = 0
            if (r11 == 0) goto L_0x0050
            boolean r2 = r0.readable     // Catch:{ all -> 0x0030 }
            if (r2 != 0) goto L_0x0050
            r2 = r1
        L_0x0015:
            int r3 = r9.valueCount     // Catch:{ all -> 0x0030 }
            if (r2 >= r3) goto L_0x0050
            boolean[] r3 = r10.written     // Catch:{ all -> 0x0030 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0030 }
            if (r3 == 0) goto L_0x0036
            java.io.File r3 = r0.getDirtyFile(r2)     // Catch:{ all -> 0x0030 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0030 }
            if (r3 != 0) goto L_0x0033
            r10.abort()     // Catch:{ all -> 0x0030 }
            monitor-exit(r9)
            return
        L_0x0030:
            r10 = move-exception
            goto L_0x0111
        L_0x0033:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0036:
            r10.abort()     // Catch:{ all -> 0x0030 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0030 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0030 }
            r11.<init>()     // Catch:{ all -> 0x0030 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x0030 }
            r11.append(r2)     // Catch:{ all -> 0x0030 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0030 }
            r10.<init>(r11)     // Catch:{ all -> 0x0030 }
            throw r10     // Catch:{ all -> 0x0030 }
        L_0x0050:
            int r10 = r9.valueCount     // Catch:{ all -> 0x0030 }
            if (r1 >= r10) goto L_0x0084
            java.io.File r10 = r0.getDirtyFile(r1)     // Catch:{ all -> 0x0030 }
            if (r11 == 0) goto L_0x007e
            boolean r2 = r10.exists()     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0081
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0030 }
            r10.renameTo(r2)     // Catch:{ all -> 0x0030 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0030 }
            r3 = r10[r1]     // Catch:{ all -> 0x0030 }
            long r5 = r2.length()     // Catch:{ all -> 0x0030 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0030 }
            r10[r1] = r5     // Catch:{ all -> 0x0030 }
            long r7 = r9.size     // Catch:{ all -> 0x0030 }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.size = r7     // Catch:{ all -> 0x0030 }
            goto L_0x0081
        L_0x007e:
            deleteIfExists(r10)     // Catch:{ all -> 0x0030 }
        L_0x0081:
            int r1 = r1 + 1
            goto L_0x0050
        L_0x0084:
            int r10 = r9.redundantOpCount     // Catch:{ all -> 0x0030 }
            r1 = 1
            int r10 = r10 + r1
            r9.redundantOpCount = r10     // Catch:{ all -> 0x0030 }
            r10 = 0
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.currentEditor = r10     // Catch:{ all -> 0x0030 }
            boolean r10 = r0.readable     // Catch:{ all -> 0x0030 }
            r10 = r10 | r11
            r2 = 10
            r3 = 32
            if (r10 == 0) goto L_0x00cc
            boolean unused = r0.readable = r1     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = "CLEAN"
            r10.append(r1)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            r10.append(r3)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = r0.key     // Catch:{ all -> 0x0030 }
            r10.append(r1)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = r0.getLengths()     // Catch:{ all -> 0x0030 }
            r10.append(r1)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            r10.append(r2)     // Catch:{ all -> 0x0030 }
            if (r11 == 0) goto L_0x00ef
            long r10 = r9.nextSequenceNumber     // Catch:{ all -> 0x0030 }
            r1 = 1
            long r1 = r1 + r10
            r9.nextSequenceNumber = r1     // Catch:{ all -> 0x0030 }
            long unused = r0.sequenceNumber = r10     // Catch:{ all -> 0x0030 }
            goto L_0x00ef
        L_0x00cc:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r10 = r9.lruEntries     // Catch:{ all -> 0x0030 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0030 }
            r10.remove(r11)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            java.lang.String r11 = "REMOVE"
            r10.append(r11)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            r10.append(r3)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0030 }
            r10.append(r11)     // Catch:{ all -> 0x0030 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            r10.append(r2)     // Catch:{ all -> 0x0030 }
        L_0x00ef:
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0030 }
            flushWriter(r10)     // Catch:{ all -> 0x0030 }
            long r10 = r9.size     // Catch:{ all -> 0x0030 }
            long r0 = r9.maxSize     // Catch:{ all -> 0x0030 }
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 > 0) goto L_0x0102
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x0030 }
            if (r10 == 0) goto L_0x0109
        L_0x0102:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.executorService     // Catch:{ all -> 0x0030 }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.cleanupCallable     // Catch:{ all -> 0x0030 }
            r10.submit(r11)     // Catch:{ all -> 0x0030 }
        L_0x0109:
            monitor-exit(r9)
            return
        L_0x010b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0030 }
            r10.<init>()     // Catch:{ all -> 0x0030 }
            throw r10     // Catch:{ all -> 0x0030 }
        L_0x0111:
            monitor-exit(r9)     // Catch:{ all -> 0x0030 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.completeEdit(com.bumptech.glide.disklrucache.DiskLruCache$Editor, boolean):void");
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    @TargetApi(26)
    private static void flushWriter(Writer writer) throws IOException {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* access modifiers changed from: private */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return Util.readFully(new InputStreamReader(inputStream, Util.UTF_8));
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i3 = this.redundantOpCount;
        return i3 >= 2000 && i3 >= this.lruEntries.size();
    }

    public static DiskLruCache open(File file, int i3, int i4, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i4 > 0) {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    renameTo(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i3, i4, j2);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    return diskLruCache;
                } catch (IOException e3) {
                    PrintStream printStream = System.out;
                    printStream.println("DiskLruCache " + file + " is corrupt: " + e3.getMessage() + ", removing");
                    diskLruCache.delete();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i3, i4, j2);
            diskLruCache2.rebuildJournal();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i3 = 0;
            if (next.currentEditor == null) {
                while (i3 < this.valueCount) {
                    this.size += next.lengths[i3];
                    i3++;
                }
            } else {
                Editor unused = next.currentEditor = null;
                while (i3 < this.valueCount) {
                    deleteIfExists(next.getCleanFile(i3));
                    deleteIfExists(next.getDirtyFile(i3));
                    i3++;
                }
                it.remove();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|(1:20)(1:21)|22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r9.redundantOpCount = r0 - r9.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        if (r2.hasUnterminatedLine() != false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        rebuildJournal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0077, code lost:
        r9.journalWriter = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r9.journalFile, true), com.bumptech.glide.disklrucache.Util.US_ASCII));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0090, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0064 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0091=Splitter:B:24:0x0091, B:17:0x0064=Splitter:B:17:0x0064} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournal() throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = ", "
            java.lang.String r1 = "unexpected journal header: ["
            com.bumptech.glide.disklrucache.StrictLineReader r2 = new com.bumptech.glide.disklrucache.StrictLineReader
            java.io.FileInputStream r3 = new java.io.FileInputStream
            java.io.File r4 = r9.journalFile
            r3.<init>(r4)
            java.nio.charset.Charset r4 = com.bumptech.glide.disklrucache.Util.US_ASCII
            r2.<init>(r3, r4)
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x0062 }
            java.lang.String r4 = r2.readLine()     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x0062 }
            java.lang.String r6 = r2.readLine()     // Catch:{ all -> 0x0062 }
            java.lang.String r7 = r2.readLine()     // Catch:{ all -> 0x0062 }
            java.lang.String r8 = "libcore.io.DiskLruCache"
            boolean r8 = r8.equals(r3)     // Catch:{ all -> 0x0062 }
            if (r8 == 0) goto L_0x0091
            java.lang.String r8 = "1"
            boolean r8 = r8.equals(r4)     // Catch:{ all -> 0x0062 }
            if (r8 == 0) goto L_0x0091
            int r8 = r9.appVersion     // Catch:{ all -> 0x0062 }
            java.lang.String r8 = java.lang.Integer.toString(r8)     // Catch:{ all -> 0x0062 }
            boolean r5 = r8.equals(r5)     // Catch:{ all -> 0x0062 }
            if (r5 == 0) goto L_0x0091
            int r5 = r9.valueCount     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ all -> 0x0062 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0062 }
            if (r5 == 0) goto L_0x0091
            java.lang.String r5 = ""
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0062 }
            if (r5 == 0) goto L_0x0091
            r0 = 0
        L_0x0058:
            java.lang.String r1 = r2.readLine()     // Catch:{ EOFException -> 0x0064 }
            r9.readJournalLine(r1)     // Catch:{ EOFException -> 0x0064 }
            int r0 = r0 + 1
            goto L_0x0058
        L_0x0062:
            r9 = move-exception
            goto L_0x00ba
        L_0x0064:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r1 = r9.lruEntries     // Catch:{ all -> 0x0062 }
            int r1 = r1.size()     // Catch:{ all -> 0x0062 }
            int r0 = r0 - r1
            r9.redundantOpCount = r0     // Catch:{ all -> 0x0062 }
            boolean r0 = r2.hasUnterminatedLine()     // Catch:{ all -> 0x0062 }
            if (r0 == 0) goto L_0x0077
            r9.rebuildJournal()     // Catch:{ all -> 0x0062 }
            goto L_0x008d
        L_0x0077:
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x0062 }
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x0062 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0062 }
            java.io.File r4 = r9.journalFile     // Catch:{ all -> 0x0062 }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0062 }
            java.nio.charset.Charset r4 = com.bumptech.glide.disklrucache.Util.US_ASCII     // Catch:{ all -> 0x0062 }
            r1.<init>(r3, r4)     // Catch:{ all -> 0x0062 }
            r0.<init>(r1)     // Catch:{ all -> 0x0062 }
            r9.journalWriter = r0     // Catch:{ all -> 0x0062 }
        L_0x008d:
            com.bumptech.glide.disklrucache.Util.closeQuietly(r2)
            return
        L_0x0091:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r5.<init>(r1)     // Catch:{ all -> 0x0062 }
            r5.append(r3)     // Catch:{ all -> 0x0062 }
            r5.append(r0)     // Catch:{ all -> 0x0062 }
            r5.append(r4)     // Catch:{ all -> 0x0062 }
            r5.append(r0)     // Catch:{ all -> 0x0062 }
            r5.append(r6)     // Catch:{ all -> 0x0062 }
            r5.append(r0)     // Catch:{ all -> 0x0062 }
            r5.append(r7)     // Catch:{ all -> 0x0062 }
            java.lang.String r0 = "]"
            r5.append(r0)     // Catch:{ all -> 0x0062 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0062 }
            r9.<init>(r0)     // Catch:{ all -> 0x0062 }
            throw r9     // Catch:{ all -> 0x0062 }
        L_0x00ba:
            com.bumptech.glide.disklrucache.Util.closeQuietly(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.readJournal():void");
    }

    private void readJournalLine(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i3 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i3);
            if (indexOf2 == -1) {
                str2 = str.substring(i3);
                if (indexOf == 6 && str.startsWith(REMOVE)) {
                    this.lruEntries.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i3, indexOf2);
            }
            Entry entry = this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith(CLEAN)) {
                String[] split = str.substring(indexOf2 + 1).split(StringUtils.SPACE);
                boolean unused = entry.readable = true;
                Editor unused2 = entry.currentEditor = null;
                entry.setLengths(split);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith(DIRTY)) {
                Editor unused3 = entry.currentEditor = new Editor(entry);
            } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith(READ)) {
                throw new IOException("unexpected journal line: ".concat(str));
            }
        } else {
            throw new IOException("unexpected journal line: ".concat(str));
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        BufferedWriter bufferedWriter;
        try {
            Writer writer = this.journalWriter;
            if (writer != null) {
                closeWriter(writer);
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.appVersion));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.valueCount));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry next : this.lruEntries.values()) {
                if (next.currentEditor != null) {
                    bufferedWriter.write("DIRTY " + next.key + 10);
                } else {
                    bufferedWriter.write("CLEAN " + next.key + next.getLengths() + 10);
                }
            }
            closeWriter(bufferedWriter);
            if (this.journalFile.exists()) {
                renameTo(this.journalFile, this.journalFileBackup, true);
            }
            renameTo(this.journalFileTmp, this.journalFile, false);
            this.journalFileBackup.delete();
            this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
        } catch (Throwable th) {
            closeWriter(bufferedWriter);
            throw th;
        } finally {
        }
    }

    private static void renameTo(File file, File file2, boolean z2) throws IOException {
        if (z2) {
            deleteIfExists(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public synchronized void close() throws IOException {
        try {
            if (this.journalWriter != null) {
                Iterator it = new ArrayList(this.lruEntries.values()).iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (entry.currentEditor != null) {
                        entry.currentEditor.abort();
                    }
                }
                trimToSize();
                closeWriter(this.journalWriter);
                this.journalWriter = null;
            }
        } finally {
            while (true) {
            }
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        flushWriter(this.journalWriter);
    }

    public synchronized Value get(String str) throws IOException {
        checkNotClosed();
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return null;
        }
        if (!entry.readable) {
            return null;
        }
        for (File exists : entry.cleanFiles) {
            if (!exists.exists()) {
                return null;
            }
        }
        this.redundantOpCount++;
        this.journalWriter.append(READ);
        this.journalWriter.append(' ');
        this.journalWriter.append(str);
        this.journalWriter.append(10);
        if (journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
        return new Value(str, entry.sequenceNumber, entry.cleanFiles, entry.lengths);
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized boolean isClosed() {
        return this.journalWriter == null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0090, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.checkNotClosed()     // Catch:{ all -> 0x0043 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ all -> 0x0043 }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0043 }
            r1 = 0
            if (r0 == 0) goto L_0x008f
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x0043 }
            if (r2 == 0) goto L_0x0017
            goto L_0x008f
        L_0x0017:
            int r2 = r6.valueCount     // Catch:{ all -> 0x0043 }
            if (r1 >= r2) goto L_0x005b
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0043 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x0045
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x002c
            goto L_0x0045
        L_0x002c:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0043 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0043 }
            r0.<init>()     // Catch:{ all -> 0x0043 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0043 }
            r0.append(r2)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0043 }
            r7.<init>(r0)     // Catch:{ all -> 0x0043 }
            throw r7     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r7 = move-exception
            goto L_0x0091
        L_0x0045:
            long r2 = r6.size     // Catch:{ all -> 0x0043 }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x0043 }
            r4 = r4[r1]     // Catch:{ all -> 0x0043 }
            long r2 = r2 - r4
            r6.size = r2     // Catch:{ all -> 0x0043 }
            long[] r2 = r0.lengths     // Catch:{ all -> 0x0043 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0043 }
            int r1 = r1 + 1
            goto L_0x0017
        L_0x005b:
            int r0 = r6.redundantOpCount     // Catch:{ all -> 0x0043 }
            r1 = 1
            int r0 = r0 + r1
            r6.redundantOpCount = r0     // Catch:{ all -> 0x0043 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = "REMOVE"
            r0.append(r2)     // Catch:{ all -> 0x0043 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0043 }
            r2 = 32
            r0.append(r2)     // Catch:{ all -> 0x0043 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0043 }
            r0.append(r7)     // Catch:{ all -> 0x0043 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0043 }
            r2 = 10
            r0.append(r2)     // Catch:{ all -> 0x0043 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0043 }
            r0.remove(r7)     // Catch:{ all -> 0x0043 }
            boolean r7 = r6.journalRebuildRequired()     // Catch:{ all -> 0x0043 }
            if (r7 == 0) goto L_0x008d
            java.util.concurrent.ThreadPoolExecutor r7 = r6.executorService     // Catch:{ all -> 0x0043 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r6.cleanupCallable     // Catch:{ all -> 0x0043 }
            r7.submit(r0)     // Catch:{ all -> 0x0043 }
        L_0x008d:
            monitor-exit(r6)
            return r1
        L_0x008f:
            monitor-exit(r6)
            return r1
        L_0x0091:
            monitor-exit(r6)     // Catch:{ all -> 0x0043 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.remove(java.lang.String):boolean");
    }

    public synchronized void setMaxSize(long j2) {
        this.maxSize = j2;
        this.executorService.submit(this.cleanupCallable);
    }

    public synchronized long size() {
        return this.size;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.bumptech.glide.disklrucache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x001e }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x001e }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x001e }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x001e }
            r1 = -1
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L_0x0022
            if (r0 == 0) goto L_0x0020
            long r3 = r0.sequenceNumber     // Catch:{ all -> 0x001e }
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0022
            goto L_0x0020
        L_0x001e:
            r6 = move-exception
            goto L_0x0060
        L_0x0020:
            monitor-exit(r5)
            return r2
        L_0x0022:
            if (r0 != 0) goto L_0x002f
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry     // Catch:{ all -> 0x001e }
            r0.<init>(r6)     // Catch:{ all -> 0x001e }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x001e }
            r7.put(r6, r0)     // Catch:{ all -> 0x001e }
            goto L_0x0037
        L_0x002f:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x001e }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r2
        L_0x0037:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor     // Catch:{ all -> 0x001e }
            r7.<init>(r0)     // Catch:{ all -> 0x001e }
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.currentEditor = r7     // Catch:{ all -> 0x001e }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x001e }
            java.lang.String r0 = "DIRTY"
            r8.append(r0)     // Catch:{ all -> 0x001e }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x001e }
            r0 = 32
            r8.append(r0)     // Catch:{ all -> 0x001e }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x001e }
            r8.append(r6)     // Catch:{ all -> 0x001e }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x001e }
            r8 = 10
            r6.append(r8)     // Catch:{ all -> 0x001e }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x001e }
            flushWriter(r6)     // Catch:{ all -> 0x001e }
            monitor-exit(r5)
            return r7
        L_0x0060:
            monitor-exit(r5)     // Catch:{ all -> 0x001e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.edit(java.lang.String, long):com.bumptech.glide.disklrucache.DiskLruCache$Editor");
    }
}
