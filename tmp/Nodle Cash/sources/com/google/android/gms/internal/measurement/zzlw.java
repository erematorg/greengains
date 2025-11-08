package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzlw extends zzjm<Long> implements zzli, zzmx, RandomAccess {
    private static final zzlw zza = new zzlw(new long[0], 0, false);
    private long[] zzb;
    private int zzc;

    public zzlw() {
        this(new long[10], 0, true);
    }

    public static zzlw zzd() {
        return zza;
    }

    private final void zze(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzd(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzd(i3));
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

    public final boolean addAll(Collection<? extends Long> collection) {
        zza();
        zzle.zza(collection);
        if (!(collection instanceof zzlw)) {
            return super.addAll(collection);
        }
        zzlw zzlw = (zzlw) collection;
        int i3 = zzlw.zzc;
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
            System.arraycopy(zzlw.zzb, 0, this.zzb, this.zzc, zzlw.zzc);
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
        if (!(obj instanceof zzlw)) {
            return super.equals(obj);
        }
        zzlw zzlw = (zzlw) obj;
        if (this.zzc != zzlw.zzc) {
            return false;
        }
        long[] jArr = zzlw.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (this.zzb[i3] != jArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        return Long.valueOf(zzb(i3));
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            i3 = (i3 * 31) + zzle.zza(this.zzb[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.zzb[i3] == longValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zze(i3);
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

    public final /* synthetic */ Object set(int i3, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zze(i3);
        long[] jArr = this.zzb;
        long j2 = jArr[i3];
        jArr[i3] = longValue;
        return Long.valueOf(j2);
    }

    public final int size() {
        return this.zzc;
    }

    public final long zzb(int i3) {
        zze(i3);
        return this.zzb[i3];
    }

    /* renamed from: zzc */
    public final zzli zza(int i3) {
        if (i3 >= this.zzc) {
            return new zzlw(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    private zzlw(long[] jArr, int i3, boolean z2) {
        super(z2);
        this.zzb = jArr;
        this.zzc = i3;
    }

    private final String zzd(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    public final void zza(long j2) {
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

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Long) obj).longValue());
        return true;
    }
}
