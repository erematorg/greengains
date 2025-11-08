package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaio  reason: invalid package */
final class zzaio extends zzahi<Double> implements zzajl<Double>, zzalb, RandomAccess {
    private static final zzaio zza = new zzaio(new double[0], 0, false);
    private double[] zzb;
    private int zzc;

    public zzaio() {
        this(new double[10], 0, true);
    }

    private final String zzc(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    private final void zzd(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i3));
        }
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        int i4;
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzc(i3));
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

    public final boolean addAll(Collection<? extends Double> collection) {
        zza();
        zzajh.zza(collection);
        if (!(collection instanceof zzaio)) {
            return super.addAll(collection);
        }
        zzaio zzaio = (zzaio) collection;
        int i3 = zzaio.zzc;
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
            System.arraycopy(zzaio.zzb, 0, this.zzb, this.zzc, zzaio.zzc);
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
        if (!(obj instanceof zzaio)) {
            return super.equals(obj);
        }
        zzaio zzaio = (zzaio) obj;
        if (this.zzc != zzaio.zzc) {
            return false;
        }
        double[] dArr = zzaio.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (Double.doubleToLongBits(this.zzb[i3]) != Double.doubleToLongBits(dArr[i3])) {
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
        for (int i4 = 0; i4 < this.zzc; i4++) {
            i3 = (i3 * 31) + zzajh.zza(Double.doubleToLongBits(this.zzb[i4]));
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
            if (this.zzb[i3] == doubleValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zzd(i3);
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

    public final /* synthetic */ Object set(int i3, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        zzd(i3);
        double[] dArr = this.zzb;
        double d2 = dArr[i3];
        dArr[i3] = doubleValue;
        return Double.valueOf(d2);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* synthetic */ zzajl zza(int i3) {
        if (i3 >= this.zzc) {
            return new zzaio(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final double zzb(int i3) {
        zzd(i3);
        return this.zzb[i3];
    }

    private zzaio(double[] dArr, int i3, boolean z2) {
        super(z2);
        this.zzb = dArr;
        this.zzc = i3;
    }

    public final void zza(double d2) {
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

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Double) obj).doubleValue());
        return true;
    }
}
