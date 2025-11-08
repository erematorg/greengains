package com.google.android.recaptcha.internal;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzil extends zzgh implements RandomAccess, zzjb, zzkm {
    private static final zzil zza = new zzil(new float[0], 0, false);
    private float[] zzb;
    private int zzc;

    public zzil() {
        this(new float[10], 0, true);
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
        float floatValue = ((Float) obj).floatValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
        int i5 = i3 + 1;
        float[] fArr = this.zzb;
        if (i4 < fArr.length) {
            System.arraycopy(fArr, i3, fArr, i5, i4 - i3);
        } else {
            float[] fArr2 = new float[b.b(i4, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            System.arraycopy(this.zzb, i3, fArr2, i5, this.zzc - i3);
            this.zzb = fArr2;
        }
        this.zzb[i3] = floatValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzjc.zzd;
        collection.getClass();
        if (!(collection instanceof zzil)) {
            return super.addAll(collection);
        }
        zzil zzil = (zzil) collection;
        int i3 = zzil.zzc;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzc;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            float[] fArr = this.zzb;
            if (i5 > fArr.length) {
                this.zzb = Arrays.copyOf(fArr, i5);
            }
            System.arraycopy(zzil.zzb, 0, this.zzb, this.zzc, zzil.zzc);
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
        if (!(obj instanceof zzil)) {
            return super.equals(obj);
        }
        zzil zzil = (zzil) obj;
        if (this.zzc != zzil.zzc) {
            return false;
        }
        float[] fArr = zzil.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (Float.floatToIntBits(this.zzb[i3]) != Float.floatToIntBits(fArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzg(i3);
        return Float.valueOf(this.zzb[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            i3 = (i3 * 31) + Float.floatToIntBits(this.zzb[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int i3 = this.zzc;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zzb[i4] == floatValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzg(i3);
        float[] fArr = this.zzb;
        float f2 = fArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(fArr, i3 + 1, fArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            float[] fArr = this.zzb;
            System.arraycopy(fArr, i4, fArr, i3, this.zzc - i4);
            this.zzc -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zza();
        zzg(i3);
        float[] fArr = this.zzb;
        float f2 = fArr[i3];
        fArr[i3] = floatValue;
        return Float.valueOf(f2);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzjb zzd(int i3) {
        if (i3 >= this.zzc) {
            return new zzil(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(float f2) {
        zza();
        int i3 = this.zzc;
        float[] fArr = this.zzb;
        if (i3 == fArr.length) {
            float[] fArr2 = new float[b.b(i3, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            this.zzb = fArr2;
        }
        float[] fArr3 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        fArr3[i4] = f2;
    }

    private zzil(float[] fArr, int i3, boolean z2) {
        super(z2);
        this.zzb = fArr;
        this.zzc = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Float) obj).floatValue());
        return true;
    }
}
