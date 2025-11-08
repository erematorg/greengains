package com.google.android.gms.internal.auth;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzek extends zzdr implements RandomAccess, zzez, zzge {
    private static final zzek zza = new zzek(new double[0], 0, false);
    private double[] zzb;
    private int zzc;

    public zzek() {
        this(new double[10], 0, true);
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
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
        double[] dArr = this.zzb;
        if (i4 < dArr.length) {
            System.arraycopy(dArr, i3, dArr, i3 + 1, i4 - i3);
        } else {
            double[] dArr2 = new double[b.b(i4, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            System.arraycopy(this.zzb, i3, dArr2, i3 + 1, this.zzc - i3);
            this.zzb = dArr2;
        }
        this.zzb[i3] = doubleValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzfa.zzd;
        collection.getClass();
        if (!(collection instanceof zzek)) {
            return super.addAll(collection);
        }
        zzek zzek = (zzek) collection;
        int i3 = zzek.zzc;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzc;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            double[] dArr = this.zzb;
            if (i5 > dArr.length) {
                this.zzb = Arrays.copyOf(dArr, i5);
            }
            System.arraycopy(zzek.zzb, 0, this.zzb, this.zzc, zzek.zzc);
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
        if (!(obj instanceof zzek)) {
            return super.equals(obj);
        }
        zzek zzek = (zzek) obj;
        if (this.zzc != zzek.zzc) {
            return false;
        }
        double[] dArr = zzek.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (Double.doubleToLongBits(this.zzb[i3]) != Double.doubleToLongBits(dArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzg(i3);
        return Double.valueOf(this.zzb[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            long doubleToLongBits = Double.doubleToLongBits(this.zzb[i4]);
            byte[] bArr = zzfa.zzd;
            i3 = (i3 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int i3 = this.zzc;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zzb[i4] == doubleValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzg(i3);
        double[] dArr = this.zzb;
        double d2 = dArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(dArr, i3 + 1, dArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Double.valueOf(d2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            double[] dArr = this.zzb;
            System.arraycopy(dArr, i4, dArr, i3, this.zzc - i4);
            this.zzc -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        zzg(i3);
        double[] dArr = this.zzb;
        double d2 = dArr[i3];
        dArr[i3] = doubleValue;
        return Double.valueOf(d2);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzez zzd(int i3) {
        if (i3 >= this.zzc) {
            return new zzek(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(double d2) {
        zza();
        int i3 = this.zzc;
        double[] dArr = this.zzb;
        if (i3 == dArr.length) {
            double[] dArr2 = new double[b.b(i3, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            this.zzb = dArr2;
        }
        double[] dArr3 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        dArr3[i4] = d2;
    }

    private zzek(double[] dArr, int i3, boolean z2) {
        super(z2);
        this.zzb = dArr;
        this.zzc = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Double) obj).doubleValue());
        return true;
    }
}
