package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzlb extends zzjm<Float> implements zzll<Float>, zzmx, RandomAccess {
    private float[] zza;
    private int zzb;

    static {
        new zzlb(new float[0], 0, false);
    }

    public zzlb() {
        this(new float[10], 0, true);
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
        float floatValue = ((Float) obj).floatValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i3));
        }
        float[] fArr = this.zza;
        if (i4 < fArr.length) {
            System.arraycopy(fArr, i3, fArr, i3 + 1, i4 - i3);
        } else {
            float[] fArr2 = new float[b.b(i4, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            System.arraycopy(this.zza, i3, fArr2, i3 + 1, this.zzb - i3);
            this.zza = fArr2;
        }
        this.zza[i3] = floatValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zza();
        zzle.zza(collection);
        if (!(collection instanceof zzlb)) {
            return super.addAll(collection);
        }
        zzlb zzlb = (zzlb) collection;
        int i3 = zzlb.zzb;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzb;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            float[] fArr = this.zza;
            if (i5 > fArr.length) {
                this.zza = Arrays.copyOf(fArr, i5);
            }
            System.arraycopy(zzlb.zza, 0, this.zza, this.zzb, zzlb.zzb);
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
        if (!(obj instanceof zzlb)) {
            return super.equals(obj);
        }
        zzlb zzlb = (zzlb) obj;
        if (this.zzb != zzlb.zzb) {
            return false;
        }
        float[] fArr = zzlb.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (Float.floatToIntBits(this.zza[i3]) != Float.floatToIntBits(fArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        return Float.valueOf(zzb(i3));
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            i3 = (i3 * 31) + Float.floatToIntBits(this.zza[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.zza[i3] == floatValue) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        zzd(i3);
        float[] fArr = this.zza;
        float f2 = fArr[i3];
        int i4 = this.zzb;
        if (i3 < i4 - 1) {
            System.arraycopy(fArr, i3 + 1, fArr, i3, (i4 - i3) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Float.valueOf(f2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            float[] fArr = this.zza;
            System.arraycopy(fArr, i4, fArr, i3, this.zzb - i4);
            this.zzb -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i3, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zza();
        zzd(i3);
        float[] fArr = this.zza;
        float f2 = fArr[i3];
        fArr[i3] = floatValue;
        return Float.valueOf(f2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* synthetic */ zzll zza(int i3) {
        if (i3 >= this.zzb) {
            return new zzlb(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final float zzb(int i3) {
        zzd(i3);
        return this.zza[i3];
    }

    private zzlb(float[] fArr, int i3, boolean z2) {
        super(z2);
        this.zza = fArr;
        this.zzb = i3;
    }

    public final void zza(float f2) {
        zza();
        int i3 = this.zzb;
        float[] fArr = this.zza;
        if (i3 == fArr.length) {
            float[] fArr2 = new float[b.b(i3, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            this.zza = fArr2;
        }
        float[] fArr3 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        fArr3[i4] = f2;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Float) obj).floatValue());
        return true;
    }
}
