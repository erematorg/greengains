package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import A.a;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.AtomicFile;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.database.DatabaseIOException;
import com.appsamurai.storyly.exoplayer2.database.DatabaseProvider;
import com.appsamurai.storyly.exoplayer2.database.VersionTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

class CachedContentIndex {
    static final String FILE_NAME_ATOMIC = "cached_content_index.exi";
    private static final int INCREMENTAL_METADATA_READ_LENGTH = 10485760;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SparseBooleanArray newIds;
    @Nullable
    private Storage previousStorage;
    private final SparseBooleanArray removedIds;
    private Storage storage;

    public static final class DatabaseStorage implements Storage {
        private static final String[] COLUMNS = {"id", "key", "metadata"};
        private static final String COLUMN_ID = "id";
        private static final int COLUMN_INDEX_ID = 0;
        private static final int COLUMN_INDEX_KEY = 1;
        private static final int COLUMN_INDEX_METADATA = 2;
        private static final String COLUMN_KEY = "key";
        private static final String COLUMN_METADATA = "metadata";
        private static final String TABLE_PREFIX = "ExoPlayerCacheIndex";
        private static final String TABLE_SCHEMA = "(id INTEGER PRIMARY KEY NOT NULL,key TEXT NOT NULL,metadata BLOB NOT NULL)";
        private static final int TABLE_VERSION = 1;
        private static final String WHERE_ID_EQUALS = "id = ?";
        private final DatabaseProvider databaseProvider;
        private String hexUid;
        private final SparseArray<CachedContent> pendingUpdates = new SparseArray<>();
        private String tableName;

        public DatabaseStorage(DatabaseProvider databaseProvider2) {
            this.databaseProvider = databaseProvider2;
        }

        private void addOrUpdateRow(SQLiteDatabase sQLiteDatabase, CachedContent cachedContent) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CachedContentIndex.writeContentMetadata(cachedContent.getMetadata(), new DataOutputStream(byteArrayOutputStream));
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(cachedContent.id));
            contentValues.put("key", cachedContent.key);
            contentValues.put("metadata", byteArray);
            sQLiteDatabase.replaceOrThrow((String) Assertions.checkNotNull(this.tableName), (String) null, contentValues);
        }

        public static void delete(DatabaseProvider databaseProvider2, long j2) throws DatabaseIOException {
            delete(databaseProvider2, Long.toHexString(j2));
        }

        private void deleteRow(SQLiteDatabase sQLiteDatabase, int i3) {
            sQLiteDatabase.delete((String) Assertions.checkNotNull(this.tableName), WHERE_ID_EQUALS, new String[]{Integer.toString(i3)});
        }

        private static void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        }

        private Cursor getCursor() {
            return this.databaseProvider.getReadableDatabase().query((String) Assertions.checkNotNull(this.tableName), COLUMNS, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        }

        private static String getTableName(String str) {
            return c.a(TABLE_PREFIX, str);
        }

        private void initializeTable(SQLiteDatabase sQLiteDatabase) throws DatabaseIOException {
            VersionTable.setVersion(sQLiteDatabase, 1, (String) Assertions.checkNotNull(this.hexUid), 1);
            dropTable(sQLiteDatabase, (String) Assertions.checkNotNull(this.tableName));
            sQLiteDatabase.execSQL("CREATE TABLE " + this.tableName + " (id INTEGER PRIMARY KEY NOT NULL,key TEXT NOT NULL,metadata BLOB NOT NULL)");
        }

        public boolean exists() throws DatabaseIOException {
            return VersionTable.getVersion(this.databaseProvider.getReadableDatabase(), 1, (String) Assertions.checkNotNull(this.hexUid)) != -1;
        }

        public void initialize(long j2) {
            String hexString = Long.toHexString(j2);
            this.hexUid = hexString;
            this.tableName = getTableName(hexString);
        }

        public void load(HashMap<String, CachedContent> hashMap, SparseArray<String> sparseArray) throws IOException {
            Cursor cursor;
            SQLiteDatabase writableDatabase;
            Assertions.checkState(this.pendingUpdates.size() == 0);
            try {
                if (VersionTable.getVersion(this.databaseProvider.getReadableDatabase(), 1, (String) Assertions.checkNotNull(this.hexUid)) != 1) {
                    writableDatabase = this.databaseProvider.getWritableDatabase();
                    writableDatabase.beginTransactionNonExclusive();
                    initializeTable(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                }
                cursor = getCursor();
                while (cursor.moveToNext()) {
                    CachedContent cachedContent = new CachedContent(cursor.getInt(0), (String) Assertions.checkNotNull(cursor.getString(1)), CachedContentIndex.readContentMetadata(new DataInputStream(new ByteArrayInputStream(cursor.getBlob(2)))));
                    hashMap.put(cachedContent.key, cachedContent);
                    sparseArray.put(cachedContent.id, cachedContent.key);
                }
                cursor.close();
                return;
            } catch (SQLiteException e3) {
                hashMap.clear();
                sparseArray.clear();
                throw new DatabaseIOException(e3);
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
            throw th;
        }

        public void onRemove(CachedContent cachedContent, boolean z2) {
            if (z2) {
                this.pendingUpdates.delete(cachedContent.id);
            } else {
                this.pendingUpdates.put(cachedContent.id, (Object) null);
            }
        }

        public void onUpdate(CachedContent cachedContent) {
            this.pendingUpdates.put(cachedContent.id, cachedContent);
        }

        public void storeFully(HashMap<String, CachedContent> hashMap) throws IOException {
            SQLiteDatabase writableDatabase;
            try {
                writableDatabase = this.databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                initializeTable(writableDatabase);
                for (CachedContent addOrUpdateRow : hashMap.values()) {
                    addOrUpdateRow(writableDatabase, addOrUpdateRow);
                }
                writableDatabase.setTransactionSuccessful();
                this.pendingUpdates.clear();
                writableDatabase.endTransaction();
            } catch (SQLException e3) {
                throw new DatabaseIOException(e3);
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        }

        public void storeIncremental(HashMap<String, CachedContent> hashMap) throws IOException {
            SQLiteDatabase writableDatabase;
            if (this.pendingUpdates.size() != 0) {
                try {
                    writableDatabase = this.databaseProvider.getWritableDatabase();
                    writableDatabase.beginTransactionNonExclusive();
                    for (int i3 = 0; i3 < this.pendingUpdates.size(); i3++) {
                        CachedContent valueAt = this.pendingUpdates.valueAt(i3);
                        if (valueAt == null) {
                            deleteRow(writableDatabase, this.pendingUpdates.keyAt(i3));
                        } else {
                            addOrUpdateRow(writableDatabase, valueAt);
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                    this.pendingUpdates.clear();
                    writableDatabase.endTransaction();
                } catch (SQLException e3) {
                    throw new DatabaseIOException(e3);
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
        }

        public void delete() throws DatabaseIOException {
            delete(this.databaseProvider, (String) Assertions.checkNotNull(this.hexUid));
        }

        private static void delete(DatabaseProvider databaseProvider2, String str) throws DatabaseIOException {
            SQLiteDatabase writableDatabase;
            try {
                String tableName2 = getTableName(str);
                writableDatabase = databaseProvider2.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                VersionTable.removeVersion(writableDatabase, 1, str);
                dropTable(writableDatabase, tableName2);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (SQLException e3) {
                throw new DatabaseIOException(e3);
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        }
    }

    public static class LegacyStorage implements Storage {
        private static final int FLAG_ENCRYPTED_INDEX = 1;
        private static final int VERSION = 2;
        private static final int VERSION_METADATA_INTRODUCED = 2;
        private final AtomicFile atomicFile;
        @Nullable
        private ReusableBufferedOutputStream bufferedOutputStream;
        private boolean changed;
        @Nullable
        private final Cipher cipher;
        private final boolean encrypt;
        @Nullable
        private final SecureRandom random;
        @Nullable
        private final SecretKeySpec secretKeySpec;

        public LegacyStorage(File file, @Nullable byte[] bArr, boolean z2) {
            SecretKeySpec secretKeySpec2;
            Cipher cipher2;
            boolean z3 = false;
            Assertions.checkState(bArr != null || !z2);
            SecureRandom secureRandom = null;
            if (bArr != null) {
                Assertions.checkArgument(bArr.length == 16 ? true : z3);
                try {
                    cipher2 = CachedContentIndex.getCipher();
                    secretKeySpec2 = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
                } catch (NoSuchAlgorithmException | NoSuchPaddingException e3) {
                    throw new IllegalStateException(e3);
                }
            } else {
                Assertions.checkArgument(!z2);
                cipher2 = null;
                secretKeySpec2 = null;
            }
            this.encrypt = z2;
            this.cipher = cipher2;
            this.secretKeySpec = secretKeySpec2;
            this.random = z2 ? new SecureRandom() : secureRandom;
            this.atomicFile = new AtomicFile(file);
        }

        private int hashCachedContent(CachedContent cachedContent, int i3) {
            int hashCode = cachedContent.key.hashCode() + (cachedContent.id * 31);
            if (i3 >= 2) {
                return (hashCode * 31) + cachedContent.getMetadata().hashCode();
            }
            long contentLength = ContentMetadata.getContentLength(cachedContent.getMetadata());
            return (hashCode * 31) + ((int) (contentLength ^ (contentLength >>> 32)));
        }

        private CachedContent readCachedContent(int i3, DataInputStream dataInputStream) throws IOException {
            DefaultContentMetadata defaultContentMetadata;
            int readInt = dataInputStream.readInt();
            String readUTF = dataInputStream.readUTF();
            if (i3 < 2) {
                long readLong = dataInputStream.readLong();
                ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
                ContentMetadataMutations.setContentLength(contentMetadataMutations, readLong);
                defaultContentMetadata = DefaultContentMetadata.EMPTY.copyWithMutationsApplied(contentMetadataMutations);
            } else {
                defaultContentMetadata = CachedContentIndex.readContentMetadata(dataInputStream);
            }
            return new CachedContent(readInt, readUTF, defaultContentMetadata);
        }

        /* JADX WARNING: Removed duplicated region for block: B:53:0x00b1  */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00b7  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean readFile(java.util.HashMap<java.lang.String, com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CachedContent> r11, android.util.SparseArray<java.lang.String> r12) {
            /*
                r10 = this;
                com.appsamurai.storyly.exoplayer2.common.util.AtomicFile r0 = r10.atomicFile
                boolean r0 = r0.exists()
                r1 = 1
                if (r0 != 0) goto L_0x000a
                return r1
            L_0x000a:
                r0 = 0
                r2 = 0
                java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                com.appsamurai.storyly.exoplayer2.common.util.AtomicFile r4 = r10.atomicFile     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                java.io.InputStream r4 = r4.openRead()     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                r3.<init>(r4)     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                r4.<init>(r3)     // Catch:{ IOException -> 0x00b5, all -> 0x00ae }
                int r2 = r4.readInt()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                if (r2 < 0) goto L_0x00aa
                r5 = 2
                if (r2 <= r5) goto L_0x0027
                goto L_0x00aa
            L_0x0027:
                int r6 = r4.readInt()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r6 = r6 & r1
                if (r6 == 0) goto L_0x0069
                javax.crypto.Cipher r6 = r10.cipher     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                if (r6 != 0) goto L_0x0036
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r4)
                return r0
            L_0x0036:
                r6 = 16
                byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r4.readFully(r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                javax.crypto.spec.IvParameterSpec r7 = new javax.crypto.spec.IvParameterSpec     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r7.<init>(r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                javax.crypto.Cipher r6 = r10.cipher     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0062 }
                javax.crypto.spec.SecretKeySpec r8 = r10.secretKeySpec     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0062 }
                java.lang.Object r8 = com.appsamurai.storyly.exoplayer2.common.util.Util.castNonNull(r8)     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0062 }
                java.security.Key r8 = (java.security.Key) r8     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0062 }
                r6.init(r5, r8, r7)     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0062 }
                java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                javax.crypto.CipherInputStream r6 = new javax.crypto.CipherInputStream     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                javax.crypto.Cipher r7 = r10.cipher     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r6.<init>(r3, r7)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r5.<init>(r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r4 = r5
                goto L_0x006f
            L_0x005d:
                r10 = move-exception
                r2 = r4
                goto L_0x00af
            L_0x0060:
                r2 = r4
                goto L_0x00b5
            L_0x0062:
                r10 = move-exception
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r11.<init>(r10)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                throw r11     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            L_0x0069:
                boolean r3 = r10.encrypt     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                if (r3 == 0) goto L_0x006f
                r10.changed = r1     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            L_0x006f:
                int r3 = r4.readInt()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r5 = r0
                r6 = r5
            L_0x0075:
                if (r5 >= r3) goto L_0x008f
                com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CachedContent r7 = r10.readCachedContent(r2, r4)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                java.lang.String r8 = r7.key     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r11.put(r8, r7)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                int r8 = r7.id     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                java.lang.String r9 = r7.key     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r12.put(r8, r9)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                int r7 = r10.hashCachedContent(r7, r2)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                int r6 = r6 + r7
                int r5 = r5 + 1
                goto L_0x0075
            L_0x008f:
                int r10 = r4.readInt()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                int r11 = r4.read()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
                r12 = -1
                if (r11 != r12) goto L_0x009c
                r11 = r1
                goto L_0x009d
            L_0x009c:
                r11 = r0
            L_0x009d:
                if (r10 != r6) goto L_0x00a6
                if (r11 != 0) goto L_0x00a2
                goto L_0x00a6
            L_0x00a2:
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r4)
                return r1
            L_0x00a6:
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r4)
                return r0
            L_0x00aa:
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r4)
                return r0
            L_0x00ae:
                r10 = move-exception
            L_0x00af:
                if (r2 == 0) goto L_0x00b4
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r2)
            L_0x00b4:
                throw r10
            L_0x00b5:
                if (r2 == 0) goto L_0x00ba
                com.appsamurai.storyly.exoplayer2.common.util.Util.closeQuietly(r2)
            L_0x00ba:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CachedContentIndex.LegacyStorage.readFile(java.util.HashMap, android.util.SparseArray):boolean");
        }

        private void writeCachedContent(CachedContent cachedContent, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeInt(cachedContent.id);
            dataOutputStream.writeUTF(cachedContent.key);
            CachedContentIndex.writeContentMetadata(cachedContent.getMetadata(), dataOutputStream);
        }

        private void writeFile(HashMap<String, CachedContent> hashMap) throws IOException {
            DataOutputStream dataOutputStream = null;
            try {
                OutputStream startWrite = this.atomicFile.startWrite();
                ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
                if (reusableBufferedOutputStream == null) {
                    this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
                } else {
                    reusableBufferedOutputStream.reset(startWrite);
                }
                ReusableBufferedOutputStream reusableBufferedOutputStream2 = this.bufferedOutputStream;
                DataOutputStream dataOutputStream2 = new DataOutputStream(reusableBufferedOutputStream2);
                try {
                    dataOutputStream2.writeInt(2);
                    int i3 = 0;
                    dataOutputStream2.writeInt(this.encrypt ? 1 : 0);
                    if (this.encrypt) {
                        byte[] bArr = new byte[16];
                        ((SecureRandom) Util.castNonNull(this.random)).nextBytes(bArr);
                        dataOutputStream2.write(bArr);
                        ((Cipher) Util.castNonNull(this.cipher)).init(1, (Key) Util.castNonNull(this.secretKeySpec), new IvParameterSpec(bArr));
                        dataOutputStream2.flush();
                        dataOutputStream2 = new DataOutputStream(new CipherOutputStream(reusableBufferedOutputStream2, this.cipher));
                    }
                    dataOutputStream2.writeInt(hashMap.size());
                    for (CachedContent next : hashMap.values()) {
                        writeCachedContent(next, dataOutputStream2);
                        i3 += hashCachedContent(next, 2);
                    }
                    dataOutputStream2.writeInt(i3);
                    this.atomicFile.endWrite(dataOutputStream2);
                    Util.closeQuietly((Closeable) null);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException e3) {
                    throw new IllegalStateException(e3);
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    Util.closeQuietly(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Util.closeQuietly(dataOutputStream);
                throw th;
            }
        }

        public void delete() {
            this.atomicFile.delete();
        }

        public boolean exists() {
            return this.atomicFile.exists();
        }

        public void initialize(long j2) {
        }

        public void load(HashMap<String, CachedContent> hashMap, SparseArray<String> sparseArray) {
            Assertions.checkState(!this.changed);
            if (!readFile(hashMap, sparseArray)) {
                hashMap.clear();
                sparseArray.clear();
                this.atomicFile.delete();
            }
        }

        public void onRemove(CachedContent cachedContent, boolean z2) {
            this.changed = true;
        }

        public void onUpdate(CachedContent cachedContent) {
            this.changed = true;
        }

        public void storeFully(HashMap<String, CachedContent> hashMap) throws IOException {
            writeFile(hashMap);
            this.changed = false;
        }

        public void storeIncremental(HashMap<String, CachedContent> hashMap) throws IOException {
            if (this.changed) {
                storeFully(hashMap);
            }
        }
    }

    public interface Storage {
        void delete() throws IOException;

        boolean exists() throws IOException;

        void initialize(long j2);

        void load(HashMap<String, CachedContent> hashMap, SparseArray<String> sparseArray) throws IOException;

        void onRemove(CachedContent cachedContent, boolean z2);

        void onUpdate(CachedContent cachedContent);

        void storeFully(HashMap<String, CachedContent> hashMap) throws IOException;

        void storeIncremental(HashMap<String, CachedContent> hashMap) throws IOException;
    }

    public CachedContentIndex(DatabaseProvider databaseProvider) {
        this(databaseProvider, (File) null, (byte[]) null, false, false);
    }

    private CachedContent addNew(String str) {
        int newId = getNewId(this.idToKey);
        CachedContent cachedContent = new CachedContent(newId, str);
        this.keyToContent.put(str, cachedContent);
        this.idToKey.put(newId, str);
        this.newIds.put(newId, true);
        this.storage.onUpdate(cachedContent);
        return cachedContent;
    }

    @WorkerThread
    public static void delete(DatabaseProvider databaseProvider, long j2) throws DatabaseIOException {
        DatabaseStorage.delete(databaseProvider, j2);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"GetInstance"})
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", BouncyCastleProvider.PROVIDER_NAME);
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    @VisibleForTesting
    public static int getNewId(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        int i3 = 0;
        int keyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (keyAt >= 0) {
            return keyAt;
        }
        while (i3 < size && i3 == sparseArray.keyAt(i3)) {
            i3++;
        }
        return i3;
    }

    public static boolean isIndexFile(String str) {
        return str.startsWith(FILE_NAME_ATOMIC);
    }

    /* access modifiers changed from: private */
    public static DefaultContentMetadata readContentMetadata(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        HashMap hashMap = new HashMap();
        int i3 = 0;
        while (i3 < readInt) {
            String readUTF = dataInputStream.readUTF();
            int readInt2 = dataInputStream.readInt();
            if (readInt2 >= 0) {
                int min = Math.min(readInt2, INCREMENTAL_METADATA_READ_LENGTH);
                byte[] bArr = Util.EMPTY_BYTE_ARRAY;
                int i4 = 0;
                while (i4 != readInt2) {
                    int i5 = i4 + min;
                    bArr = Arrays.copyOf(bArr, i5);
                    dataInputStream.readFully(bArr, i4, min);
                    min = Math.min(readInt2 - i5, INCREMENTAL_METADATA_READ_LENGTH);
                    i4 = i5;
                }
                hashMap.put(readUTF, bArr);
                i3++;
            } else {
                throw new IOException(a.k("Invalid value size: ", readInt2));
            }
        }
        return new DefaultContentMetadata(hashMap);
    }

    /* access modifiers changed from: private */
    public static void writeContentMetadata(DefaultContentMetadata defaultContentMetadata, DataOutputStream dataOutputStream) throws IOException {
        Set<Map.Entry<String, byte[]>> entrySet = defaultContentMetadata.entrySet();
        dataOutputStream.writeInt(entrySet.size());
        for (Map.Entry next : entrySet) {
            dataOutputStream.writeUTF((String) next.getKey());
            byte[] bArr = (byte[]) next.getValue();
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
    }

    public void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) {
        CachedContent orAdd = getOrAdd(str);
        if (orAdd.applyMetadataMutations(contentMetadataMutations)) {
            this.storage.onUpdate(orAdd);
        }
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    @Nullable
    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return Collections.unmodifiableCollection(this.keyToContent.values());
    }

    public ContentMetadata getContentMetadata(String str) {
        CachedContent cachedContent = get(str);
        return cachedContent != null ? cachedContent.getMetadata() : DefaultContentMetadata.EMPTY;
    }

    @Nullable
    public String getKeyForId(int i3) {
        return this.idToKey.get(i3);
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str) : cachedContent;
    }

    @WorkerThread
    public void initialize(long j2) throws IOException {
        Storage storage2;
        this.storage.initialize(j2);
        Storage storage3 = this.previousStorage;
        if (storage3 != null) {
            storage3.initialize(j2);
        }
        if (this.storage.exists() || (storage2 = this.previousStorage) == null || !storage2.exists()) {
            this.storage.load(this.keyToContent, this.idToKey);
        } else {
            this.previousStorage.load(this.keyToContent, this.idToKey);
            this.storage.storeFully(this.keyToContent);
        }
        Storage storage4 = this.previousStorage;
        if (storage4 != null) {
            storage4.delete();
            this.previousStorage = null;
        }
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.isEmpty() && cachedContent.isFullyUnlocked()) {
            this.keyToContent.remove(str);
            int i3 = cachedContent.id;
            boolean z2 = this.newIds.get(i3);
            this.storage.onRemove(cachedContent, z2);
            if (z2) {
                this.idToKey.remove(i3);
                this.newIds.delete(i3);
                return;
            }
            this.idToKey.put(i3, (Object) null);
            this.removedIds.put(i3, true);
        }
    }

    public void removeEmpty() {
        UnmodifiableIterator<String> it = ImmutableSet.copyOf(this.keyToContent.keySet()).iterator();
        while (it.hasNext()) {
            maybeRemove(it.next());
        }
    }

    @WorkerThread
    public void store() throws IOException {
        this.storage.storeIncremental(this.keyToContent);
        int size = this.removedIds.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.idToKey.remove(this.removedIds.keyAt(i3));
        }
        this.removedIds.clear();
        this.newIds.clear();
    }

    public CachedContentIndex(@Nullable DatabaseProvider databaseProvider, @Nullable File file, @Nullable byte[] bArr, boolean z2, boolean z3) {
        Assertions.checkState((databaseProvider == null && file == null) ? false : true);
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.removedIds = new SparseBooleanArray();
        this.newIds = new SparseBooleanArray();
        LegacyStorage legacyStorage = null;
        DatabaseStorage databaseStorage = databaseProvider != null ? new DatabaseStorage(databaseProvider) : null;
        legacyStorage = file != null ? new LegacyStorage(new File(file, FILE_NAME_ATOMIC), bArr, z2) : legacyStorage;
        if (databaseStorage == null || (legacyStorage != null && z3)) {
            this.storage = (Storage) Util.castNonNull(legacyStorage);
            this.previousStorage = databaseStorage;
            return;
        }
        this.storage = databaseStorage;
        this.previousStorage = legacyStorage;
    }
}
