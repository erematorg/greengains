package com.fasterxml.jackson.core.sym;

import androidx.compose.animation.core.a;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer {
    private static final int DEFAULT_T_SIZE = 64;
    protected static final int MAX_ENTRIES_FOR_REUSE = 6000;
    private static final int MAX_T_SIZE = 65536;
    private static final int MIN_HASH_SIZE = 16;
    private static final int MULT = 33;
    private static final int MULT2 = 65599;
    private static final int MULT3 = 31;
    protected int _count;
    protected final boolean _failOnDoS;
    protected int[] _hashArea;
    protected boolean _hashShared;
    protected int _hashSize;
    protected final boolean _intern;
    protected int _longNameOffset;
    protected String[] _names;
    protected final ByteQuadsCanonicalizer _parent;
    protected int _secondaryStart;
    protected final int _seed;
    protected int _spilloverEnd;
    protected final AtomicReference<TableInfo> _tableInfo;
    protected int _tertiaryShift;
    protected int _tertiaryStart;

    private ByteQuadsCanonicalizer(int i3, int i4) {
        this._parent = null;
        this._count = 0;
        this._hashShared = true;
        this._seed = i4;
        this._intern = false;
        this._failOnDoS = true;
        int i5 = 16;
        if (i3 >= 16) {
            if (((i3 - 1) & i3) != 0) {
                while (i5 < i3) {
                    i5 += i5;
                }
            }
            this._tableInfo = new AtomicReference<>(TableInfo.createInitial(i3));
        }
        i3 = i5;
        this._tableInfo = new AtomicReference<>(TableInfo.createInitial(i3));
    }

    private int _appendLongName(int[] iArr, int i3) {
        int i4 = this._longNameOffset;
        int i5 = i4 + i3;
        int[] iArr2 = this._hashArea;
        if (i5 > iArr2.length) {
            int length = i5 - iArr2.length;
            int min = Math.min(4096, this._hashSize);
            this._hashArea = Arrays.copyOf(this._hashArea, Math.max(length, min) + this._hashArea.length);
        }
        System.arraycopy(iArr, 0, this._hashArea, i4, i3);
        this._longNameOffset += i3;
        return i4;
    }

    private final int _calcOffset(int i3) {
        return ((this._hashSize - 1) & i3) << 2;
    }

    public static int _calcTertiaryShift(int i3) {
        int i4 = i3 >> 2;
        if (i4 < 64) {
            return 4;
        }
        if (i4 <= 256) {
            return 5;
        }
        return i4 <= 1024 ? 6 : 7;
    }

    private boolean _checkNeedForRehash() {
        if (this._count <= (this._hashSize >> 1)) {
            return false;
        }
        int _spilloverStart = (this._spilloverEnd - _spilloverStart()) >> 2;
        int i3 = this._count;
        return _spilloverStart > ((i3 + 1) >> 7) || ((double) i3) > ((double) this._hashSize) * 0.8d;
    }

    private int _findOffsetForAdd(int i3) {
        int _calcOffset = _calcOffset(i3);
        int[] iArr = this._hashArea;
        if (iArr[_calcOffset + 3] == 0) {
            return _calcOffset;
        }
        if (_checkNeedForRehash()) {
            return _resizeAndFindOffsetForAdd(i3);
        }
        int i4 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        if (iArr[i4 + 3] == 0) {
            return i4;
        }
        int i5 = this._tertiaryStart;
        int i6 = this._tertiaryShift;
        int i7 = i5 + ((_calcOffset >> (i6 + 2)) << i6);
        int i8 = (1 << i6) + i7;
        while (i7 < i8) {
            if (iArr[i7 + 3] == 0) {
                return i7;
            }
            i7 += 4;
        }
        int i9 = this._spilloverEnd;
        int i10 = i9 + 4;
        this._spilloverEnd = i10;
        if (i10 < (this._hashSize << 3)) {
            return i9;
        }
        if (this._failOnDoS) {
            _reportTooManyCollisions();
        }
        return _resizeAndFindOffsetForAdd(i3);
    }

    private String _findSecondary(int i3, int i4) {
        int i5 = this._tertiaryStart;
        int i6 = this._tertiaryShift;
        int i7 = i5 + ((i3 >> (i6 + 2)) << i6);
        int[] iArr = this._hashArea;
        int i8 = (1 << i6) + i7;
        while (i7 < i8) {
            int i9 = iArr[i7 + 3];
            if (i4 == iArr[i7] && 1 == i9) {
                return this._names[i7 >> 2];
            }
            if (i9 == 0) {
                return null;
            }
            i7 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (i4 == iArr[_spilloverStart] && 1 == iArr[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    private int _resizeAndFindOffsetForAdd(int i3) {
        rehash();
        int _calcOffset = _calcOffset(i3);
        int[] iArr = this._hashArea;
        if (iArr[_calcOffset + 3] == 0) {
            return _calcOffset;
        }
        int i4 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        if (iArr[i4 + 3] == 0) {
            return i4;
        }
        int i5 = this._tertiaryStart;
        int i6 = this._tertiaryShift;
        int i7 = i5 + ((_calcOffset >> (i6 + 2)) << i6);
        int i8 = (1 << i6) + i7;
        while (i7 < i8) {
            if (iArr[i7 + 3] == 0) {
                return i7;
            }
            i7 += 4;
        }
        int i9 = this._spilloverEnd;
        this._spilloverEnd = i9 + 4;
        return i9;
    }

    private final int _spilloverStart() {
        int i3 = this._hashSize;
        return (i3 << 3) - i3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        if (r7[r6] == r0[r9]) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r6 = r8 + 1;
        r3 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        if (r7[r8] == r0[r9]) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        r8 = r6 + 1;
        r3 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r7[r6] == r0[r9]) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        r6 = r8 + 1;
        r4 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        if (r7[r8] == r0[r9]) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        r3 = r8 + 2;
        r5 = r9 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
        if (r7[r6] == r0[r4]) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0059, code lost:
        r8 = r8 + 3;
        r9 = r9 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
        if (r7[r3] == r0[r5]) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        if (r7[r8] == r0[r9]) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r8 = r6 + 1;
        r3 = r9 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean _verifyLongName(int[] r7, int r8, int r9) {
        /*
            r6 = this;
            int[] r0 = r6._hashArea
            r1 = 1
            r2 = 0
            switch(r8) {
                case 4: goto L_0x0042;
                case 5: goto L_0x0034;
                case 6: goto L_0x0026;
                case 7: goto L_0x0018;
                case 8: goto L_0x000c;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r6 = r6._verifyLongName2(r7, r8, r9)
            return r6
        L_0x000c:
            r6 = r7[r2]
            int r8 = r9 + 1
            r9 = r0[r9]
            if (r6 == r9) goto L_0x0015
            return r2
        L_0x0015:
            r9 = r8
            r6 = r1
            goto L_0x0019
        L_0x0018:
            r6 = r2
        L_0x0019:
            int r8 = r6 + 1
            r6 = r7[r6]
            int r3 = r9 + 1
            r9 = r0[r9]
            if (r6 == r9) goto L_0x0024
            return r2
        L_0x0024:
            r9 = r3
            goto L_0x0027
        L_0x0026:
            r8 = r2
        L_0x0027:
            int r6 = r8 + 1
            r8 = r7[r8]
            int r3 = r9 + 1
            r9 = r0[r9]
            if (r8 == r9) goto L_0x0032
            return r2
        L_0x0032:
            r9 = r3
            goto L_0x0035
        L_0x0034:
            r6 = r2
        L_0x0035:
            int r8 = r6 + 1
            r6 = r7[r6]
            int r3 = r9 + 1
            r9 = r0[r9]
            if (r6 == r9) goto L_0x0040
            return r2
        L_0x0040:
            r9 = r3
            goto L_0x0043
        L_0x0042:
            r8 = r2
        L_0x0043:
            int r6 = r8 + 1
            r3 = r7[r8]
            int r4 = r9 + 1
            r5 = r0[r9]
            if (r3 == r5) goto L_0x004e
            return r2
        L_0x004e:
            int r3 = r8 + 2
            r6 = r7[r6]
            int r5 = r9 + 2
            r4 = r0[r4]
            if (r6 == r4) goto L_0x0059
            return r2
        L_0x0059:
            int r8 = r8 + 3
            r6 = r7[r3]
            int r9 = r9 + 3
            r3 = r0[r5]
            if (r6 == r3) goto L_0x0064
            return r2
        L_0x0064:
            r6 = r7[r8]
            r7 = r0[r9]
            if (r6 == r7) goto L_0x006b
            return r2
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer._verifyLongName(int[], int, int):boolean");
    }

    private boolean _verifyLongName2(int[] iArr, int i3, int i4) {
        int i5 = 0;
        while (true) {
            int i6 = i5 + 1;
            int i7 = i4 + 1;
            if (iArr[i5] != this._hashArea[i4]) {
                return false;
            }
            if (i6 >= i3) {
                return true;
            }
            i5 = i6;
            i4 = i7;
        }
    }

    private void _verifySharing() {
        if (!this._hashShared) {
            return;
        }
        if (this._parent != null) {
            int[] iArr = this._hashArea;
            this._hashArea = Arrays.copyOf(iArr, iArr.length);
            String[] strArr = this._names;
            this._names = (String[]) Arrays.copyOf(strArr, strArr.length);
            this._hashShared = false;
        } else if (this._count == 0) {
            throw new IllegalStateException("Cannot add names to Root symbol table");
        } else {
            throw new IllegalStateException("Cannot add names to Placeholder symbol table");
        }
    }

    public static ByteQuadsCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b A[LOOP:0: B:7:0x001b->B:10:0x0026, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeChild(com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer.TableInfo r4) {
        /*
            r3 = this;
            int r0 = r4.count
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo> r1 = r3._tableInfo
            java.lang.Object r1 = r1.get()
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo r1 = (com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer.TableInfo) r1
            int r2 = r1.count
            if (r0 != r2) goto L_0x000f
            return
        L_0x000f:
            r2 = 6000(0x1770, float:8.408E-42)
            if (r0 <= r2) goto L_0x0019
            r4 = 64
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo r4 = com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer.TableInfo.createInitial(r4)
        L_0x0019:
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo> r3 = r3._tableInfo
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer.mergeChild(com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo):void");
    }

    private void nukeSymbols(boolean z2) {
        this._count = 0;
        this._spilloverEnd = _spilloverStart();
        this._longNameOffset = this._hashSize << 3;
        if (z2) {
            Arrays.fill(this._hashArea, 0);
            Arrays.fill(this._names, (Object) null);
        }
    }

    private void rehash() {
        this._hashShared = false;
        int[] iArr = this._hashArea;
        String[] strArr = this._names;
        int i3 = this._hashSize;
        int i4 = this._count;
        int i5 = i3 + i3;
        int i6 = this._spilloverEnd;
        if (i5 > 65536) {
            nukeSymbols(true);
            return;
        }
        this._hashArea = new int[(iArr.length + (i3 << 3))];
        this._hashSize = i5;
        int i7 = i5 << 2;
        this._secondaryStart = i7;
        this._tertiaryStart = i7 + (i7 >> 1);
        this._tertiaryShift = _calcTertiaryShift(i5);
        this._names = new String[(strArr.length << 1)];
        nukeSymbols(false);
        int[] iArr2 = new int[16];
        int i8 = 0;
        for (int i9 = 0; i9 < i6; i9 += 4) {
            int i10 = iArr[i9 + 3];
            if (i10 != 0) {
                i8++;
                String str = strArr[i9 >> 2];
                if (i10 == 1) {
                    iArr2[0] = iArr[i9];
                    addName(str, iArr2, 1);
                } else if (i10 == 2) {
                    iArr2[0] = iArr[i9];
                    iArr2[1] = iArr[i9 + 1];
                    addName(str, iArr2, 2);
                } else if (i10 != 3) {
                    if (i10 > iArr2.length) {
                        iArr2 = new int[i10];
                    }
                    System.arraycopy(iArr, iArr[i9 + 1], iArr2, 0, i10);
                    addName(str, iArr2, i10);
                } else {
                    iArr2[0] = iArr[i9];
                    iArr2[1] = iArr[i9 + 1];
                    iArr2[2] = iArr[i9 + 2];
                    addName(str, iArr2, 3);
                }
            }
        }
        if (i8 != i4) {
            throw new IllegalStateException(a.r("Failed rehash(): old count=", i4, ", copyCount=", i8));
        }
    }

    public void _reportTooManyCollisions() {
        if (this._hashSize > 1024) {
            StringBuilder sb = new StringBuilder("Spill-over slots in symbol table with ");
            sb.append(this._count);
            sb.append(" entries, hash area of ");
            sb.append(this._hashSize);
            sb.append(" slots is now full (all ");
            throw new IllegalStateException(A.a.m(sb, " slots -- suspect a DoS attack based on hash collisions. You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`", this._hashSize >> 3));
        }
    }

    public String addName(String str, int i3) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(calcHash(i3));
        int[] iArr = this._hashArea;
        iArr[_findOffsetForAdd] = i3;
        iArr[_findOffsetForAdd + 3] = 1;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        return str;
    }

    public int bucketCount() {
        return this._hashSize;
    }

    public int calcHash(int i3) {
        int i4 = this._seed ^ i3;
        int i5 = i4 + (i4 >>> 16);
        int i6 = i5 ^ (i5 << 3);
        return i6 + (i6 >>> 12);
    }

    public String findName(int i3) {
        int _calcOffset = _calcOffset(calcHash(i3));
        int[] iArr = this._hashArea;
        int i4 = iArr[_calcOffset + 3];
        if (i4 == 1) {
            if (iArr[_calcOffset] == i3) {
                return this._names[_calcOffset >> 2];
            }
        } else if (i4 == 0) {
            return null;
        }
        int i5 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int i6 = iArr[i5 + 3];
        if (i6 == 1) {
            if (iArr[i5] == i3) {
                return this._names[i5 >> 2];
            }
        } else if (i6 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, i3);
    }

    public int hashSeed() {
        return this._seed;
    }

    public boolean isCanonicalizing() {
        return this._parent != null;
    }

    public ByteQuadsCanonicalizer makeChild(int i3) {
        return new ByteQuadsCanonicalizer(this, this._seed, this._tableInfo.get(), JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(i3), JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(i3));
    }

    public ByteQuadsCanonicalizer makeChildOrPlaceholder(int i3) {
        if (!JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i3)) {
            return new ByteQuadsCanonicalizer(this._tableInfo.get());
        }
        return new ByteQuadsCanonicalizer(this, this._seed, this._tableInfo.get(), JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(i3), JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(i3));
    }

    public boolean maybeDirty() {
        return !this._hashShared;
    }

    public int primaryCount() {
        int i3 = this._secondaryStart;
        int i4 = 0;
        for (int i5 = 3; i5 < i3; i5 += 4) {
            if (this._hashArea[i5] != 0) {
                i4++;
            }
        }
        return i4;
    }

    public void release() {
        if (this._parent != null && maybeDirty()) {
            this._parent.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }

    public int secondaryCount() {
        int i3 = this._tertiaryStart;
        int i4 = 0;
        for (int i5 = this._secondaryStart + 3; i5 < i3; i5 += 4) {
            if (this._hashArea[i5] != 0) {
                i4++;
            }
        }
        return i4;
    }

    public int size() {
        AtomicReference<TableInfo> atomicReference = this._tableInfo;
        return atomicReference != null ? atomicReference.get().count : this._count;
    }

    public int spilloverCount() {
        return (this._spilloverEnd - _spilloverStart()) >> 2;
    }

    public int tertiaryCount() {
        int i3 = this._tertiaryStart + 3;
        int i4 = this._hashSize + i3;
        int i5 = 0;
        while (i3 < i4) {
            if (this._hashArea[i3] != 0) {
                i5++;
            }
            i3 += 4;
        }
        return i5;
    }

    public String toString() {
        int primaryCount = primaryCount();
        int secondaryCount = secondaryCount();
        int tertiaryCount = tertiaryCount();
        int spilloverCount = spilloverCount();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", new Object[]{ByteQuadsCanonicalizer.class.getName(), Integer.valueOf(this._count), Integer.valueOf(this._hashSize), Integer.valueOf(primaryCount), Integer.valueOf(secondaryCount), Integer.valueOf(tertiaryCount), Integer.valueOf(spilloverCount), Integer.valueOf(primaryCount + secondaryCount + tertiaryCount + spilloverCount), Integer.valueOf(totalCount())});
    }

    public int totalCount() {
        int i3 = this._hashSize << 3;
        int i4 = 0;
        for (int i5 = 3; i5 < i3; i5 += 4) {
            if (this._hashArea[i5] != 0) {
                i4++;
            }
        }
        return i4;
    }

    public int calcHash(int i3, int i4) {
        int i5 = i3 + (i3 >>> 15);
        int i6 = this._seed;
        int i7 = i6 ^ ((i4 * 33) + (i5 ^ (i5 >>> 9)));
        int i8 = i7 + (i7 >>> 16);
        int i9 = i8 ^ (i8 >>> 4);
        return i9 + (i9 << 3);
    }

    public static ByteQuadsCanonicalizer createRoot(int i3) {
        return new ByteQuadsCanonicalizer(64, i3);
    }

    public int calcHash(int i3, int i4, int i5) {
        int i6 = this._seed ^ i3;
        int i7 = (((i6 + (i6 >>> 9)) * 31) + i4) * 33;
        int i8 = (i7 + (i7 >>> 15)) ^ i5;
        int i9 = i8 + (i8 >>> 4);
        int i10 = i9 + (i9 >>> 15);
        return i10 ^ (i10 << 9);
    }

    public int calcHash(int[] iArr, int i3) {
        if (i3 >= 4) {
            int i4 = this._seed ^ iArr[0];
            int i5 = i4 + (i4 >>> 9) + iArr[1];
            int i6 = ((i5 + (i5 >>> 15)) * 33) ^ iArr[2];
            int i7 = i6 + (i6 >>> 4);
            for (int i8 = 3; i8 < i3; i8++) {
                int i9 = iArr[i8];
                i7 += i9 ^ (i9 >> 21);
            }
            int i10 = i7 * MULT2;
            int i11 = i10 + (i10 >>> 19);
            return i11 ^ (i11 << 5);
        }
        throw new IllegalArgumentException();
    }

    public static final class TableInfo {
        public final int count;
        public final int longNameOffset;
        public final int[] mainHash;
        public final String[] names;
        public final int size;
        public final int spilloverEnd;
        public final int tertiaryShift;

        public TableInfo(int i3, int i4, int i5, int[] iArr, String[] strArr, int i6, int i7) {
            this.size = i3;
            this.count = i4;
            this.tertiaryShift = i5;
            this.mainHash = iArr;
            this.names = strArr;
            this.spilloverEnd = i6;
            this.longNameOffset = i7;
        }

        public static TableInfo createInitial(int i3) {
            int i4 = i3 << 3;
            return new TableInfo(i3, 0, ByteQuadsCanonicalizer._calcTertiaryShift(i3), new int[i4], new String[(i3 << 1)], i4 - i3, i4);
        }

        public TableInfo(ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
            this.size = byteQuadsCanonicalizer._hashSize;
            this.count = byteQuadsCanonicalizer._count;
            this.tertiaryShift = byteQuadsCanonicalizer._tertiaryShift;
            this.mainHash = byteQuadsCanonicalizer._hashArea;
            this.names = byteQuadsCanonicalizer._names;
            this.spilloverEnd = byteQuadsCanonicalizer._spilloverEnd;
            this.longNameOffset = byteQuadsCanonicalizer._longNameOffset;
        }
    }

    private ByteQuadsCanonicalizer(ByteQuadsCanonicalizer byteQuadsCanonicalizer, int i3, TableInfo tableInfo, boolean z2, boolean z3) {
        this._parent = byteQuadsCanonicalizer;
        this._seed = i3;
        this._intern = z2;
        this._failOnDoS = z3;
        this._tableInfo = null;
        this._count = tableInfo.count;
        int i4 = tableInfo.size;
        this._hashSize = i4;
        int i5 = i4 << 2;
        this._secondaryStart = i5;
        this._tertiaryStart = i5 + (i5 >> 1);
        this._tertiaryShift = tableInfo.tertiaryShift;
        this._hashArea = tableInfo.mainHash;
        this._names = tableInfo.names;
        this._spilloverEnd = tableInfo.spilloverEnd;
        this._longNameOffset = tableInfo.longNameOffset;
        this._hashShared = true;
    }

    private String _findSecondary(int i3, int i4, int i5) {
        int i6 = this._tertiaryStart;
        int i7 = this._tertiaryShift;
        int i8 = i6 + ((i3 >> (i7 + 2)) << i7);
        int[] iArr = this._hashArea;
        int i9 = (1 << i7) + i8;
        while (i8 < i9) {
            int i10 = iArr[i8 + 3];
            if (i4 == iArr[i8] && i5 == iArr[i8 + 1] && 2 == i10) {
                return this._names[i8 >> 2];
            }
            if (i10 == 0) {
                return null;
            }
            i8 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (i4 == iArr[_spilloverStart] && i5 == iArr[_spilloverStart + 1] && 2 == iArr[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    public String addName(String str, int i3, int i4) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(calcHash(i3, i4));
        int[] iArr = this._hashArea;
        iArr[_findOffsetForAdd] = i3;
        iArr[_findOffsetForAdd + 1] = i4;
        iArr[_findOffsetForAdd + 3] = 2;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        return str;
    }

    public String findName(int i3, int i4) {
        int _calcOffset = _calcOffset(calcHash(i3, i4));
        int[] iArr = this._hashArea;
        int i5 = iArr[_calcOffset + 3];
        if (i5 == 2) {
            if (i3 == iArr[_calcOffset] && i4 == iArr[_calcOffset + 1]) {
                return this._names[_calcOffset >> 2];
            }
        } else if (i5 == 0) {
            return null;
        }
        int i6 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int i7 = iArr[i6 + 3];
        if (i7 == 2) {
            if (i3 == iArr[i6] && i4 == iArr[i6 + 1]) {
                return this._names[i6 >> 2];
            }
        } else if (i7 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, i3, i4);
    }

    private String _findSecondary(int i3, int i4, int i5, int i6) {
        int i7 = this._tertiaryStart;
        int i8 = this._tertiaryShift;
        int i9 = i7 + ((i3 >> (i8 + 2)) << i8);
        int[] iArr = this._hashArea;
        int i10 = (1 << i8) + i9;
        while (i9 < i10) {
            int i11 = iArr[i9 + 3];
            if (i4 == iArr[i9] && i5 == iArr[i9 + 1] && i6 == iArr[i9 + 2] && 3 == i11) {
                return this._names[i9 >> 2];
            }
            if (i11 == 0) {
                return null;
            }
            i9 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (i4 == iArr[_spilloverStart] && i5 == iArr[_spilloverStart + 1] && i6 == iArr[_spilloverStart + 2] && 3 == iArr[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    public String addName(String str, int i3, int i4, int i5) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(calcHash(i3, i4, i5));
        int[] iArr = this._hashArea;
        iArr[_findOffsetForAdd] = i3;
        iArr[_findOffsetForAdd + 1] = i4;
        iArr[_findOffsetForAdd + 2] = i5;
        iArr[_findOffsetForAdd + 3] = 3;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        return str;
    }

    public String findName(int i3, int i4, int i5) {
        int _calcOffset = _calcOffset(calcHash(i3, i4, i5));
        int[] iArr = this._hashArea;
        int i6 = iArr[_calcOffset + 3];
        if (i6 == 3) {
            if (i3 == iArr[_calcOffset] && iArr[_calcOffset + 1] == i4 && iArr[_calcOffset + 2] == i5) {
                return this._names[_calcOffset >> 2];
            }
        } else if (i6 == 0) {
            return null;
        }
        int i7 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int i8 = iArr[i7 + 3];
        if (i8 == 3) {
            if (i3 == iArr[i7] && iArr[i7 + 1] == i4 && iArr[i7 + 2] == i5) {
                return this._names[i7 >> 2];
            }
        } else if (i8 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, i3, i4, i5);
    }

    private ByteQuadsCanonicalizer(TableInfo tableInfo) {
        this._parent = null;
        this._seed = 0;
        this._intern = false;
        this._failOnDoS = true;
        this._tableInfo = null;
        this._count = -1;
        int[] iArr = tableInfo.mainHash;
        this._hashArea = iArr;
        this._names = tableInfo.names;
        this._hashSize = tableInfo.size;
        int length = iArr.length;
        this._secondaryStart = length;
        this._tertiaryStart = length;
        this._tertiaryShift = 1;
        this._spilloverEnd = length;
        this._longNameOffset = length;
        this._hashShared = true;
    }

    private String _findSecondary(int i3, int i4, int[] iArr, int i5) {
        int i6 = this._tertiaryStart;
        int i7 = this._tertiaryShift;
        int i8 = i6 + ((i3 >> (i7 + 2)) << i7);
        int[] iArr2 = this._hashArea;
        int i9 = (1 << i7) + i8;
        while (i8 < i9) {
            int i10 = iArr2[i8 + 3];
            if (i4 == iArr2[i8] && i5 == i10 && _verifyLongName(iArr, i5, iArr2[i8 + 1])) {
                return this._names[i8 >> 2];
            }
            if (i10 == 0) {
                return null;
            }
            i8 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (i4 == iArr2[_spilloverStart] && i5 == iArr2[_spilloverStart + 3] && _verifyLongName(iArr, i5, iArr2[_spilloverStart + 1])) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    public String addName(String str, int[] iArr, int i3) {
        int i4;
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        if (i3 == 1) {
            i4 = _findOffsetForAdd(calcHash(iArr[0]));
            int[] iArr2 = this._hashArea;
            iArr2[i4] = iArr[0];
            iArr2[i4 + 3] = 1;
        } else if (i3 == 2) {
            i4 = _findOffsetForAdd(calcHash(iArr[0], iArr[1]));
            int[] iArr3 = this._hashArea;
            iArr3[i4] = iArr[0];
            iArr3[i4 + 1] = iArr[1];
            iArr3[i4 + 3] = 2;
        } else if (i3 != 3) {
            int calcHash = calcHash(iArr, i3);
            i4 = _findOffsetForAdd(calcHash);
            this._hashArea[i4] = calcHash;
            int _appendLongName = _appendLongName(iArr, i3);
            int[] iArr4 = this._hashArea;
            iArr4[i4 + 1] = _appendLongName;
            iArr4[i4 + 3] = i3;
        } else {
            int _findOffsetForAdd = _findOffsetForAdd(calcHash(iArr[0], iArr[1], iArr[2]));
            int[] iArr5 = this._hashArea;
            iArr5[_findOffsetForAdd] = iArr[0];
            iArr5[_findOffsetForAdd + 1] = iArr[1];
            iArr5[_findOffsetForAdd + 2] = iArr[2];
            iArr5[_findOffsetForAdd + 3] = 3;
            i4 = _findOffsetForAdd;
        }
        this._names[i4 >> 2] = str;
        this._count++;
        return str;
    }

    public String findName(int[] iArr, int i3) {
        if (i3 >= 4) {
            int calcHash = calcHash(iArr, i3);
            int _calcOffset = _calcOffset(calcHash);
            int[] iArr2 = this._hashArea;
            int i4 = iArr2[_calcOffset + 3];
            if (calcHash == iArr2[_calcOffset] && i4 == i3 && _verifyLongName(iArr, i3, iArr2[_calcOffset + 1])) {
                return this._names[_calcOffset >> 2];
            }
            if (i4 == 0) {
                return null;
            }
            int i5 = this._secondaryStart + ((_calcOffset >> 3) << 2);
            int i6 = iArr2[i5 + 3];
            if (calcHash == iArr2[i5] && i6 == i3 && _verifyLongName(iArr, i3, iArr2[i5 + 1])) {
                return this._names[i5 >> 2];
            }
            return _findSecondary(_calcOffset, calcHash, iArr, i3);
        } else if (i3 == 1) {
            return findName(iArr[0]);
        } else {
            if (i3 == 2) {
                return findName(iArr[0], iArr[1]);
            }
            if (i3 != 3) {
                return "";
            }
            return findName(iArr[0], iArr[1], iArr[2]);
        }
    }
}
