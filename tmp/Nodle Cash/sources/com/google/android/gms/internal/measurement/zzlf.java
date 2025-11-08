package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzlf extends zzjm<Integer> implements zzlj, zzmx, RandomAccess {
    private static final zzlf zza = new zzlf(new int[0], 0, false);
    private int[] zzb;
    private int zzc;

    public zzlf() {
        this(new int[10], 0, true);
    }

    public static zzlf zzd() {
        return zza;
    }

    private final String zze(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    private final void zzf(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zze(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        int intValue = ((Integer) obj).intValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zze(i3));
        }
        int[] iArr = this.zzb;
        if (i4 < iArr.length) {
            System.arraycopy(iArr, i3, iArr, i3 + 1, i4 - i3);
        } else {
            int[] iArr2 = new int[b.b(i4, 3, 2, 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.zzb, i3, iArr2, i3 + 1, this.zzc - i3);
            this.zzb = iArr2;
        }
        this.zzb[i3] = intValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zza();
        zzle.zza(collection);
        if (!(collection instanceof zzlf)) {
            return super.addAll(collection);
        }
        zzlf zzlf = (zzlf) collection;
        int i3 = zzlf.zzc;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzc;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            int[] iArr = this.zzb;
            if (i5 > iArr.length) {
                this.zzb = Arrays.copyOf(iArr, i5);
            }
            System.arraycopy(zzlf.zzb, 0, this.zzb, this.zzc, zzlf.zzc);
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
        if (!(obj instanceof zzlf)) {
            return super.equals(obj);
        }
        zzlf zzlf = (zzlf) obj;
        if (this.zzc != zzlf.zzc) {
            return false;
        }
        int[] iArr = zzlf.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (this.zzb[i3] != iArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        return Integer.valueOf(zzb(i3));
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            i3 = (i3 * 31) + this.zzb[i4];
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.zzb[i3] == intValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zzf(i3);
        int[] iArr = this.zzb;
        int i4 = iArr[i3];
        int i5 = this.zzc;
        if (i3 < i5 - 1) {
            System.arraycopy(iArr, i3 + 1, iArr, i3, (i5 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Integer.valueOf(i4);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            int[] iArr = this.zzb;
            System.arraycopy(iArr, i4, iArr, i3, this.zzc - i4);
            this.zzc -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i3, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zza();
        zzf(i3);
        int[] iArr = this.zzb;
        int i4 = iArr[i3];
        iArr[i3] = intValue;
        return Integer.valueOf(i4);
    }

    public final int size() {
        return this.zzc;
    }

    public final int zzb(int i3) {
        zzf(i3);
        return this.zzb[i3];
    }

    /* renamed from: zzc */
    public final zzlj zza(int i3) {
        if (i3 >= this.zzc) {
            return new zzlf(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    private zzlf(int[] iArr, int i3, boolean z2) {
        super(z2);
        this.zzb = iArr;
        this.zzc = i3;
    }

    public final void zzd(int i3) {
        zza();
        int i4 = this.zzc;
        int[] iArr = this.zzb;
        if (i4 == iArr.length) {
            int[] iArr2 = new int[b.b(i4, 3, 2, 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i4);
            this.zzb = iArr2;
        }
        int[] iArr3 = this.zzb;
        int i5 = this.zzc;
        this.zzc = i5 + 1;
        iArr3[i5] = i3;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzd(((Integer) obj).intValue());
        return true;
    }
}
