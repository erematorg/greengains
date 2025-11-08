package com.google.android.gms.internal.auth;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfm extends zzdr implements RandomAccess, zzez, zzge {
    private static final zzfm zza = new zzfm(new long[0], 0, false);
    private long[] zzb;
    private int zzc;

    public zzfm() {
        this(new long[10], 0, true);
    }

    private final String zzf(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    private final void zzg(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
        long[] jArr = this.zzb;
        if (i4 < jArr.length) {
            System.arraycopy(jArr, i3, jArr, i3 + 1, i4 - i3);
        } else {
            long[] jArr2 = new long[b.b(i4, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            System.arraycopy(this.zzb, i3, jArr2, i3 + 1, this.zzc - i3);
            this.zzb = jArr2;
        }
        this.zzb[i3] = longValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzfa.zzd;
        collection.getClass();
        if (!(collection instanceof zzfm)) {
            return super.addAll(collection);
        }
        zzfm zzfm = (zzfm) collection;
        int i3 = zzfm.zzc;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzc;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            long[] jArr = this.zzb;
            if (i5 > jArr.length) {
                this.zzb = Arrays.copyOf(jArr, i5);
            }
            System.arraycopy(zzfm.zzb, 0, this.zzb, this.zzc, zzfm.zzc);
            this.zzc = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfm)) {
            return super.equals(obj);
        }
        zzfm zzfm = (zzfm) obj;
        if (this.zzc != zzfm.zzc) {
            return false;
        }
        long[] jArr = zzfm.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (this.zzb[i3] != jArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzg(i3);
        return Long.valueOf(this.zzb[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            long j2 = this.zzb[i4];
            byte[] bArr = zzfa.zzd;
            i3 = (i3 * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i3 = this.zzc;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zzb[i4] == longValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzg(i3);
        long[] jArr = this.zzb;
        long j2 = jArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(jArr, i3 + 1, jArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            long[] jArr = this.zzb;
            System.arraycopy(jArr, i4, jArr, i3, this.zzc - i4);
            this.zzc -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzg(i3);
        long[] jArr = this.zzb;
        long j2 = jArr[i3];
        jArr[i3] = longValue;
        return Long.valueOf(j2);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzez zzd(int i3) {
        if (i3 >= this.zzc) {
            return new zzfm(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(long j2) {
        zza();
        int i3 = this.zzc;
        long[] jArr = this.zzb;
        if (i3 == jArr.length) {
            long[] jArr2 = new long[b.b(i3, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            this.zzb = jArr2;
        }
        long[] jArr3 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        jArr3[i4] = j2;
    }

    private zzfm(long[] jArr, int i3, boolean z2) {
        super(z2);
        this.zzb = jArr;
        this.zzc = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Long) obj).longValue());
        return true;
    }
}
