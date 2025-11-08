package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdp extends zzcs implements RandomAccess, zzeo {
    private double[] zza;
    private int zzb;

    static {
        new zzdp(new double[0], 0, false);
    }

    public zzdp() {
        this(new double[10], 0, true);
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
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i3));
        }
        int i5 = i3 + 1;
        double[] dArr = this.zza;
        if (i4 < dArr.length) {
            System.arraycopy(dArr, i3, dArr, i5, i4 - i3);
        } else {
            double[] dArr2 = new double[b.b(i4, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            System.arraycopy(this.zza, i3, dArr2, i5, this.zzb - i3);
            this.zza = dArr2;
        }
        this.zza[i3] = doubleValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzep.zzb;
        collection.getClass();
        if (!(collection instanceof zzdp)) {
            return super.addAll(collection);
        }
        zzdp zzdp = (zzdp) collection;
        int i3 = zzdp.zzb;
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
            System.arraycopy(zzdp.zza, 0, this.zza, this.zzb, zzdp.zzb);
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
        if (!(obj instanceof zzdp)) {
            return super.equals(obj);
        }
        zzdp zzdp = (zzdp) obj;
        if (this.zzb != zzdp.zzb) {
            return false;
        }
        double[] dArr = zzdp.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (Double.doubleToLongBits(this.zza[i3]) != Double.doubleToLongBits(dArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzh(i3);
        return Double.valueOf(this.zza[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            long doubleToLongBits = Double.doubleToLongBits(this.zza[i4]);
            byte[] bArr = zzep.zzb;
            i3 = (i3 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int i3 = this.zzb;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zza[i4] == doubleValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzh(i3);
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

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        zzh(i3);
        double[] dArr = this.zza;
        double d2 = dArr[i3];
        dArr[i3] = doubleValue;
        return Double.valueOf(d2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ zzeo zzd(int i3) {
        if (i3 >= this.zzb) {
            return new zzdp(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final double zze(int i3) {
        zzh(i3);
        return this.zza[i3];
    }

    public final void zzf(double d2) {
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

    private zzdp(double[] dArr, int i3, boolean z2) {
        super(z2);
        this.zza = dArr;
        this.zzb = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Double) obj).doubleValue());
        return true;
    }
}
