package com.google.android.gms.internal.auth;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdv extends zzdr implements RandomAccess, zzez, zzge {
    private static final zzdv zza = new zzdv(new boolean[0], 0, false);
    private boolean[] zzb;
    private int zzc;

    public zzdv() {
        this(new boolean[10], 0, true);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
        boolean[] zArr = this.zzb;
        if (i4 < zArr.length) {
            System.arraycopy(zArr, i3, zArr, i3 + 1, i4 - i3);
        } else {
            boolean[] zArr2 = new boolean[b.b(i4, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            System.arraycopy(this.zzb, i3, zArr2, i3 + 1, this.zzc - i3);
            this.zzb = zArr2;
        }
        this.zzb[i3] = booleanValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzfa.zzd;
        collection.getClass();
        if (!(collection instanceof zzdv)) {
            return super.addAll(collection);
        }
        zzdv zzdv = (zzdv) collection;
        int i3 = zzdv.zzc;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.zzc;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            boolean[] zArr = this.zzb;
            if (i5 > zArr.length) {
                this.zzb = Arrays.copyOf(zArr, i5);
            }
            System.arraycopy(zzdv.zzb, 0, this.zzb, this.zzc, zzdv.zzc);
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
        if (!(obj instanceof zzdv)) {
            return super.equals(obj);
        }
        zzdv zzdv = (zzdv) obj;
        if (this.zzc != zzdv.zzc) {
            return false;
        }
        boolean[] zArr = zzdv.zzb;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            if (this.zzb[i3] != zArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i3) {
        zzg(i3);
        return Boolean.valueOf(this.zzb[i3]);
    }

    public final int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.zzc; i4++) {
            i3 = (i3 * 31) + zzfa.zza(this.zzb[i4]);
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int i3 = this.zzc;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.zzb[i4] == booleanValue) {
                return i4;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i3) {
        zza();
        zzg(i3);
        boolean[] zArr = this.zzb;
        boolean z2 = zArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(zArr, i3 + 1, zArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Boolean.valueOf(z2);
    }

    public final void removeRange(int i3, int i4) {
        zza();
        if (i4 >= i3) {
            boolean[] zArr = this.zzb;
            System.arraycopy(zArr, i4, zArr, i3, this.zzc - i4);
            this.zzc -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        zzg(i3);
        boolean[] zArr = this.zzb;
        boolean z2 = zArr[i3];
        zArr[i3] = booleanValue;
        return Boolean.valueOf(z2);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzez zzd(int i3) {
        if (i3 >= this.zzc) {
            return new zzdv(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(boolean z2) {
        zza();
        int i3 = this.zzc;
        boolean[] zArr = this.zzb;
        if (i3 == zArr.length) {
            boolean[] zArr2 = new boolean[b.b(i3, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            this.zzb = zArr2;
        }
        boolean[] zArr3 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        zArr3[i4] = z2;
    }

    private zzdv(boolean[] zArr, int i3, boolean z2) {
        super(z2);
        this.zzb = zArr;
        this.zzc = i3;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Boolean) obj).booleanValue());
        return true;
    }
}
