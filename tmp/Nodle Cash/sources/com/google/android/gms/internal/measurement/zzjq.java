package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzjq extends zzjm<Boolean> implements zzll<Boolean>, zzmx, RandomAccess {
    private boolean[] zza;
    private int zzb;

    static {
        new zzjq(new boolean[0], 0, false);
    }

    public zzjq() {
        this(new boolean[10], 0, true);
    }

    private final String zzc(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzb);
    }

    private final void zzd(int i3) {
        if (i3 < 0 || i3 >= this.zzb) {
            throw new IndexOutOfBoundsException(zzc(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i3));
        }
        boolean[] zArr = this.zza;
        if (i4 < zArr.length) {
            System.arraycopy(zArr, i3, zArr, i3 + 1, i4 - i3);
        } else {
            boolean[] zArr2 = new boolean[b.b(i4, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            System.arraycopy(this.zza, i3, zArr2, i3 + 1, this.zzb - i3);
            this.zza = zArr2;
        }
        this.zza[i3] = booleanValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zza();
        zzle.zza(collection);
        if (!(collection instanceof zzjq)) {
            return super.addAll(collection);
        }
        zzjq zzjq = (zzjq) collection;
        int i3 = zzjq.zzb;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzb;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            boolean[] zArr = this.zza;
            if (i5 > zArr.length) {
                this.zza = Arrays.copyOf(zArr, i5);
            }
            System.arraycopy(zzjq.zza, 0, this.zza, this.zzb, zzjq.zzb);
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
        if (!(obj instanceof zzjq)) {
            return super.equals(obj);
        }
        zzjq zzjq = (zzjq) obj;
        if (this.zzb != zzjq.zzb) {
            return false;
        }
        boolean[] zArr = zzjq.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (this.zza[i3] != zArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        return Boolean.valueOf(zzb(i3));
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            i3 = (i3 * 31) + zzle.zza(this.zza[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.zza[i3] == booleanValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zzd(i3);
        boolean[] zArr = this.zza;
        boolean z2 = zArr[i3];
        int i4 = this.zzb;
        if (i3 < i4 - 1) {
            System.arraycopy(zArr, i3 + 1, zArr, i3, (i4 - i3) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Boolean.valueOf(z2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            boolean[] zArr = this.zza;
            System.arraycopy(zArr, i4, zArr, i3, this.zzb - i4);
            this.zzb -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        zzd(i3);
        boolean[] zArr = this.zza;
        boolean z2 = zArr[i3];
        zArr[i3] = booleanValue;
        return Boolean.valueOf(z2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* synthetic */ zzll zza(int i3) {
        if (i3 >= this.zzb) {
            return new zzjq(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean zzb(int i3) {
        zzd(i3);
        return this.zza[i3];
    }

    private zzjq(boolean[] zArr, int i3, boolean z2) {
        super(z2);
        this.zza = zArr;
        this.zzb = i3;
    }

    public final void zza(boolean z2) {
        zza();
        int i3 = this.zzb;
        boolean[] zArr = this.zza;
        if (i3 == zArr.length) {
            boolean[] zArr2 = new boolean[b.b(i3, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            this.zza = zArr2;
        }
        boolean[] zArr3 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        zArr3[i4] = z2;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Boolean) obj).booleanValue());
        return true;
    }
}
