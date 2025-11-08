package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

public final class CharsToNameCanonicalizer {
    private static final int DEFAULT_T_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_LENGTH = 150;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    private static final int MAX_T_SIZE = 65536;
    protected Bucket[] _buckets;
    protected boolean _canonicalize;
    protected final int _flags;
    protected boolean _hashShared;
    protected int _indexMask;
    protected int _longestCollisionList;
    protected BitSet _overflows;
    protected final CharsToNameCanonicalizer _parent;
    protected final int _seed;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;
    protected final AtomicReference<TableInfo> _tableInfo;

    public static final class Bucket {
        public final int length;
        public final Bucket next;
        public final String symbol;

        public Bucket(String str, Bucket bucket) {
            this.symbol = str;
            this.next = bucket;
            this.length = bucket != null ? 1 + bucket.length : 1;
        }

        public String has(char[] cArr, int i3, int i4) {
            if (this.symbol.length() != i4) {
                return null;
            }
            int i5 = 0;
            while (this.symbol.charAt(i5) == cArr[i3 + i5]) {
                i5++;
                if (i5 >= i4) {
                    return this.symbol;
                }
            }
            return null;
        }
    }

    private CharsToNameCanonicalizer(int i3) {
        this._parent = null;
        this._seed = i3;
        this._canonicalize = true;
        this._flags = -1;
        this._hashShared = false;
        this._longestCollisionList = 0;
        this._tableInfo = new AtomicReference<>(TableInfo.createInitial(64));
    }

    private String _addSymbol(char[] cArr, int i3, int i4, int i5, int i6) {
        if (this._hashShared) {
            copyArrays();
            this._hashShared = false;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            i6 = _hashToIndex(calcHash(cArr, i3, i4));
        }
        String str = new String(cArr, i3, i4);
        if (JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
            str = InternCache.instance.intern(str);
        }
        this._size++;
        String[] strArr = this._symbols;
        if (strArr[i6] == null) {
            strArr[i6] = str;
        } else {
            int i7 = i6 >> 1;
            Bucket bucket = new Bucket(str, this._buckets[i7]);
            int i8 = bucket.length;
            if (i8 > 150) {
                _handleSpillOverflow(i7, bucket, i6);
            } else {
                this._buckets[i7] = bucket;
                this._longestCollisionList = Math.max(i8, this._longestCollisionList);
            }
        }
        return str;
    }

    private String _findSymbol2(char[] cArr, int i3, int i4, Bucket bucket) {
        while (bucket != null) {
            String has = bucket.has(cArr, i3, i4);
            if (has != null) {
                return has;
            }
            bucket = bucket.next;
        }
        return null;
    }

    private void _handleSpillOverflow(int i3, Bucket bucket, int i4) {
        BitSet bitSet = this._overflows;
        if (bitSet == null) {
            BitSet bitSet2 = new BitSet();
            this._overflows = bitSet2;
            bitSet2.set(i3);
        } else if (bitSet.get(i3)) {
            if (JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
                _reportTooManyCollisions(150);
            }
            this._canonicalize = false;
        } else {
            this._overflows.set(i3);
        }
        this._symbols[i4] = bucket.symbol;
        this._buckets[i3] = null;
        this._size -= bucket.length;
        this._longestCollisionList = -1;
    }

    private static int _thresholdSize(int i3) {
        return i3 - (i3 >> 2);
    }

    private void copyArrays() {
        String[] strArr = this._symbols;
        this._symbols = (String[]) Arrays.copyOf(strArr, strArr.length);
        Bucket[] bucketArr = this._buckets;
        this._buckets = (Bucket[]) Arrays.copyOf(bucketArr, bucketArr.length);
    }

    public static CharsToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b A[LOOP:0: B:7:0x001b->B:10:0x0026, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeChild(com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer.TableInfo r4) {
        /*
            r3 = this;
            int r0 = r4.size
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$TableInfo> r1 = r3._tableInfo
            java.lang.Object r1 = r1.get()
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$TableInfo r1 = (com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer.TableInfo) r1
            int r2 = r1.size
            if (r0 != r2) goto L_0x000f
            return
        L_0x000f:
            r2 = 12000(0x2ee0, float:1.6816E-41)
            if (r0 <= r2) goto L_0x0019
            r4 = 64
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$TableInfo r4 = com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer.TableInfo.createInitial(r4)
        L_0x0019:
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$TableInfo> r3 = r3._tableInfo
        L_0x001b:
            boolean r0 = r3.compareAndSet(r1, r4)
            if (r0 == 0) goto L_0x0022
            goto L_0x0028
        L_0x0022:
            java.lang.Object r0 = r3.get()
            if (r0 == r1) goto L_0x001b
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer.mergeChild(com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$TableInfo):void");
    }

    private void rehash() {
        int i3 = r1 + r1;
        if (i3 > 65536) {
            this._size = 0;
            this._canonicalize = false;
            this._symbols = new String[64];
            this._buckets = new Bucket[32];
            this._indexMask = 63;
            this._hashShared = false;
            return;
        }
        Bucket[] bucketArr = this._buckets;
        this._symbols = new String[i3];
        this._buckets = new Bucket[(i3 >> 1)];
        this._indexMask = i3 - 1;
        this._sizeThreshold = _thresholdSize(i3);
        int i4 = 0;
        int i5 = 0;
        for (String str : this._symbols) {
            if (str != null) {
                i4++;
                int _hashToIndex = _hashToIndex(calcHash(str));
                String[] strArr = this._symbols;
                if (strArr[_hashToIndex] == null) {
                    strArr[_hashToIndex] = str;
                } else {
                    int i6 = _hashToIndex >> 1;
                    Bucket bucket = new Bucket(str, this._buckets[i6]);
                    this._buckets[i6] = bucket;
                    i5 = Math.max(i5, bucket.length);
                }
            }
        }
        int i7 = r1 >> 1;
        for (int i8 = 0; i8 < i7; i8++) {
            for (Bucket bucket2 = bucketArr[i8]; bucket2 != null; bucket2 = bucket2.next) {
                i4++;
                String str2 = bucket2.symbol;
                int _hashToIndex2 = _hashToIndex(calcHash(str2));
                String[] strArr2 = this._symbols;
                if (strArr2[_hashToIndex2] == null) {
                    strArr2[_hashToIndex2] = str2;
                } else {
                    int i9 = _hashToIndex2 >> 1;
                    Bucket bucket3 = new Bucket(str2, this._buckets[i9]);
                    this._buckets[i9] = bucket3;
                    i5 = Math.max(i5, bucket3.length);
                }
            }
        }
        this._longestCollisionList = i5;
        this._overflows = null;
        if (i4 != this._size) {
            throw new IllegalStateException(String.format("Internal error on SymbolTable.rehash(): had %d entries; now have %d", new Object[]{Integer.valueOf(this._size), Integer.valueOf(i4)}));
        }
    }

    public int _hashToIndex(int i3) {
        int i4 = i3 + (i3 >>> 15);
        int i5 = i4 ^ (i4 << 7);
        return this._indexMask & (i5 + (i5 >>> 3));
    }

    public void _reportTooManyCollisions(int i3) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + i3 + " -- suspect a DoS attack based on hash collisions");
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public int calcHash(char[] cArr, int i3, int i4) {
        int i5 = this._seed;
        int i6 = i4 + i3;
        while (i3 < i6) {
            i5 = (i5 * 33) + cArr[i3];
            i3++;
        }
        if (i5 == 0) {
            return 1;
        }
        return i5;
    }

    public int collisionCount() {
        int i3 = 0;
        for (Bucket bucket : this._buckets) {
            if (bucket != null) {
                i3 += bucket.length;
            }
        }
        return i3;
    }

    public String findSymbol(char[] cArr, int i3, int i4, int i5) {
        if (i4 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(cArr, i3, i4);
        }
        int _hashToIndex = _hashToIndex(i5);
        String str = this._symbols[_hashToIndex];
        if (str != null) {
            if (str.length() == i4) {
                int i6 = 0;
                while (str.charAt(i6) == cArr[i3 + i6]) {
                    i6++;
                    if (i6 == i4) {
                        return str;
                    }
                }
            }
            Bucket bucket = this._buckets[_hashToIndex >> 1];
            if (bucket != null) {
                String has = bucket.has(cArr, i3, i4);
                if (has != null) {
                    return has;
                }
                String _findSymbol2 = _findSymbol2(cArr, i3, i4, bucket.next);
                if (_findSymbol2 != null) {
                    return _findSymbol2;
                }
            }
        }
        return _addSymbol(cArr, i3, i4, i5, _hashToIndex);
    }

    public int hashSeed() {
        return this._seed;
    }

    public CharsToNameCanonicalizer makeChild(int i3) {
        return new CharsToNameCanonicalizer(this, i3, this._seed, this._tableInfo.get());
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public boolean maybeDirty() {
        return !this._hashShared;
    }

    public void release() {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        if (maybeDirty() && (charsToNameCanonicalizer = this._parent) != null && this._canonicalize) {
            charsToNameCanonicalizer.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }

    public int size() {
        AtomicReference<TableInfo> atomicReference = this._tableInfo;
        return atomicReference != null ? atomicReference.get().size : this._size;
    }

    public void verifyInternalConsistency() {
        int i3 = 0;
        for (String str : this._symbols) {
            if (str != null) {
                i3++;
            }
        }
        int i4 = r0 >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            for (Bucket bucket = this._buckets[i5]; bucket != null; bucket = bucket.next) {
                i3++;
            }
        }
        if (i3 != this._size) {
            throw new IllegalStateException(String.format("Internal error: expected internal size %d vs calculated count %d", new Object[]{Integer.valueOf(this._size), Integer.valueOf(i3)}));
        }
    }

    public static CharsToNameCanonicalizer createRoot(int i3) {
        return new CharsToNameCanonicalizer(i3);
    }

    public int calcHash(String str) {
        int length = str.length();
        int i3 = this._seed;
        for (int i4 = 0; i4 < length; i4++) {
            i3 = (i3 * 33) + str.charAt(i4);
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    public static final class TableInfo {
        final Bucket[] buckets;
        final int longestCollisionList;
        final int size;
        final String[] symbols;

        public TableInfo(int i3, int i4, String[] strArr, Bucket[] bucketArr) {
            this.size = i3;
            this.longestCollisionList = i4;
            this.symbols = strArr;
            this.buckets = bucketArr;
        }

        public static TableInfo createInitial(int i3) {
            return new TableInfo(0, 0, new String[i3], new Bucket[(i3 >> 1)]);
        }

        public TableInfo(CharsToNameCanonicalizer charsToNameCanonicalizer) {
            this.size = charsToNameCanonicalizer._size;
            this.longestCollisionList = charsToNameCanonicalizer._longestCollisionList;
            this.symbols = charsToNameCanonicalizer._symbols;
            this.buckets = charsToNameCanonicalizer._buckets;
        }
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, int i3, int i4, TableInfo tableInfo) {
        this._parent = charsToNameCanonicalizer;
        this._seed = i4;
        this._tableInfo = null;
        this._flags = i3;
        this._canonicalize = JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i3);
        String[] strArr = tableInfo.symbols;
        this._symbols = strArr;
        this._buckets = tableInfo.buckets;
        this._size = tableInfo.size;
        this._longestCollisionList = tableInfo.longestCollisionList;
        int length = strArr.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._hashShared = true;
    }
}
