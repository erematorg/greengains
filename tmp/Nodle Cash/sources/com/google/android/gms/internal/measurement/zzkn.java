package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzkn extends zzjm<Double> implements zzll<Double>, zzmx, RandomAccess {
    private double[] zza;
    private int zzb;

    static {
        new zzkn(new double[0], 0, false);
    }

    public zzkn() {
        this(new double[10], 0, true);
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
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i3));
        }
        double[] dArr = this.zza;
        if (i4 < dArr.length) {
            System.arraycopy(dArr, i3, dArr, i3 + 1, i4 - i3);
        } else {
            double[] dArr2 = new double[b.b(i4, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            System.arraycopy(this.zza, i3, dArr2, i3 + 1, this.zzb - i3);
            this.zza = dArr2;
        }
        this.zza[i3] = doubleValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zza();
        zzle.zza(collection);
        if (!(collection instanceof zzkn)) {
            return super.addAll(collection);
        }
        zzkn zzkn = (zzkn) collection;
        int i3 = zzkn.zzb;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzb;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            double[] dArr = this.zza;
            if (i5 > dArr.length) {
                this.zza = Arrays.copyOf(dArr, i5);
            }
            System.arraycopy(zzkn.zza, 0, this.zza, this.zzb, zzkn.zzb);
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
        if (!(obj instanceof zzkn)) {
            return super.equals(obj);
        }
        zzkn zzkn = (zzkn) obj;
        if (this.zzb != zzkn.zzb) {
            return false;
        }
        double[] dArr = zzkn.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (Double.doubleToLongBits(this.zza[i3]) != Double.doubleToLongBits(dArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        return Double.valueOf(zzb(i3));
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            i3 = (i3 * 31) + zzle.zza(Double.doubleToLongBits(this.zza[i4]));
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.zza[i3] == doubleValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zzd(i3);
        double[] dArr = this.zza;
        double d2 = dArr[i3];
        int i4 = this.zzb;
        if (i3 < i4 - 1) {
            System.arraycopy(dArr, i3 + 1, dArr, i3, (i4 - i3) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Double.valueOf(d2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            double[] dArr = this.zza;
            System.arraycopy(dArr, i4, dArr, i3, this.zzb - i4);
            this.zzb -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i3, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        zzd(i3);
        double[] dArr = this.zza;
        double d2 = dArr[i3];
        dArr[i3] = doubleValue;
        return Double.valueOf(d2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* synthetic */ zzll zza(int i3) {
        if (i3 >= this.zzb) {
            return new zzkn(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final double zzb(int i3) {
        zzd(i3);
        return this.zza[i3];
    }

    private zzkn(double[] dArr, int i3, boolean z2) {
        super(z2);
        this.zza = dArr;
        this.zzb = i3;
    }

    public final void zza(double d2) {
        zza();
        int i3 = this.zzb;
        double[] dArr = this.zza;
        if (i3 == dArr.length) {
            double[] dArr2 = new double[b.b(i3, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            this.zza = dArr2;
        }
        double[] dArr3 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        dArr3[i4] = d2;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Double) obj).doubleValue());
        return true;
    }
}
