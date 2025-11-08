package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcw extends zzcs implements RandomAccess, zzeo {
    private boolean[] zza;
    private int zzb;

    static {
        new zzcw(new boolean[0], 0, false);
    }

    public zzcw() {
        this(new boolean[10], 0, true);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i3));
        }
        int i5 = i3 + 1;
        boolean[] zArr = this.zza;
        if (i4 < zArr.length) {
            System.arraycopy(zArr, i3, zArr, i5, i4 - i3);
        } else {
            boolean[] zArr2 = new boolean[b.b(i4, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            System.arraycopy(this.zza, i3, zArr2, i5, this.zzb - i3);
            this.zza = zArr2;
        }
        this.zza[i3] = booleanValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzep.zzb;
        collection.getClass();
        if (!(collection instanceof zzcw)) {
            return super.addAll(collection);
        }
        zzcw zzcw = (zzcw) collection;
        int i3 = zzcw.zzb;
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
            System.arraycopy(zzcw.zza, 0, this.zza, this.zzb, zzcw.zzb);
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
        if (!(obj instanceof zzcw)) {
            return super.equals(obj);
        }
        zzcw zzcw = (zzcw) obj;
        if (this.zzb != zzcw.zzb) {
            return false;
        }
        boolean[] zArr = zzcw.zza;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            if (this.zza[i3] != zArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzh(i3);
        return Boolean.valueOf(this.zza[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            i3 = (i3 * 31) + zzep.zza(this.zza[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int i3 = this.zzb;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zza[i4] == booleanValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzh(i3);
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

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        zzh(i3);
        boolean[] zArr = this.zza;
        boolean z2 = zArr[i3];
        zArr[i3] = booleanValue;
        return Boolean.valueOf(z2);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ zzeo zzd(int i3) {
        if (i3 >= this.zzb) {
            return new zzcw(Arrays.copyOf(this.zza, i3), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(boolean z2) {
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

    public final boolean zzf(int i3) {
        zzh(i3);
        return this.zza[i3];
    }

    private zzcw(boolean[] zArr, int i3, boolean z2) {
        super(z2);
        this.zza = zArr;
        this.zzb = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Boolean) obj).booleanValue());
        return true;
    }
}
