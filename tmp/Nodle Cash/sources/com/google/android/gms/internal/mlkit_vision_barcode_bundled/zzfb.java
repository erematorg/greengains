package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfb extends zzcs implements RandomAccess, zzeo {
    private long[] zza;
    private int zzb;

    static {
        new zzfb(new long[0], 0, false);
    }

    public zzfb() {
        this(new long[10], 0, true);
    }

    private final String zzg(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzb);
    }

    private final void zzh(int i3) {
        if (i3 < 0 || i3 >= this.zzb) {
            throw new IndexOutOfBoundsException(zzg(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i3));
        }
        int i5 = i3 + 1;
        long[] jArr = this.zza;
        if (i4 < jArr.length) {
            System.arraycopy(jArr, i3, jArr, i5, i4 - i3);
        } else {
            long[] jArr2 = new long[b.b(i4, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            System.arraycopy(this.zza, i3, jArr2, i5, this.zzb - i3);
            this.zza = jArr2;
        }
        this.zza[i3] = longValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzep.zzb;
        collection.getClass();
        if (!(collection instanceof zzfb)) {
            return super.addAll(collection);
        }
        zzfb zzfb = (zzfb) collection;
        int i3 = zzfb.zzb;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzb;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            long[] jArr = this.zza;
            if (i5 > jArr.length) {
                this.zza = Arrays.copyOf(jArr, i5);
            }
            System.arraycopy(zzfb.zza, 0, this.zza, this.zzb, zzfb.zzb);
            this.zzb = i5;
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
        if (!(obj instanceof zzfb)) {
            return super.equals(obj);
        }
        zzfb zzfb = (zzfb) obj;
        if (this.zzb != zzfb.zzb) {
            return false;
        }
        long[] jArr = zzfb.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (this.zza[i3] != jArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzh(i3);
        return Long.valueOf(this.zza[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            long j2 = this.zza[i4];
            byte[] bArr = zzep.zzb;
            i3 = (i3 * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i3 = this.zzb;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zza[i4] == longValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzh(i3);
        long[] jArr = this.zza;
        long j2 = jArr[i3];
        int i4 = this.zzb;
        if (i3 < i4 - 1) {
            System.arraycopy(jArr, i3 + 1, jArr, i3, (i4 - i3) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            long[] jArr = this.zza;
            System.arraycopy(jArr, i4, jArr, i3, this.zzb - i4);
            this.zzb -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzh(i3);
        long[] jArr = this.zza;
        long j2 = jArr[i3];
        jArr[i3] = longValue;
        return Long.valueOf(j2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ zzeo zzd(int i3) {
        if (i3 >= this.zzb) {
            return new zzfb(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final long zze(int i3) {
        zzh(i3);
        return this.zza[i3];
    }

    public final void zzf(long j2) {
        zza();
        int i3 = this.zzb;
        long[] jArr = this.zza;
        if (i3 == jArr.length) {
            long[] jArr2 = new long[b.b(i3, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            this.zza = jArr2;
        }
        long[] jArr3 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        jArr3[i4] = j2;
    }

    private zzfb(long[] jArr, int i3, boolean z2) {
        super(z2);
        this.zza = jArr;
        this.zzb = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Long) obj).longValue());
        return true;
    }
}
