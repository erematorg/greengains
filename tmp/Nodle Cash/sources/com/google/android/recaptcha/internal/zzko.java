package com.google.android.recaptcha.internal;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.RandomAccess;

final class zzko extends zzgh implements RandomAccess {
    private static final zzko zza = new zzko(new Object[0], 0, false);
    private Object[] zzb;
    private int zzc;

    public zzko() {
        this(new Object[10], 0, true);
    }

    public static zzko zze() {
        return zza;
    }

    private final String zzf(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    private final void zzg(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
    }

    public final void add(int i3, Object obj) {
        int i4;
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i3));
        }
        int i5 = i3 + 1;
        Object[] objArr = this.zzb;
        if (i4 < objArr.length) {
            System.arraycopy(objArr, i3, objArr, i5, i4 - i3);
        } else {
            Object[] objArr2 = new Object[b.b(i4, 3, 2, 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i3);
            System.arraycopy(this.zzb, i3, objArr2, i5, this.zzc - i3);
            this.zzb = objArr2;
        }
        this.zzb[i3] = obj;
        this.zzc++;
        this.modCount++;
    }

    public final Object get(int i3) {
        zzg(i3);
        return this.zzb[i3];
    }

    public final Object remove(int i3) {
        zza();
        zzg(i3);
        Object[] objArr = this.zzb;
        Object obj = objArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(objArr, i3 + 1, objArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i3, Object obj) {
        zza();
        zzg(i3);
        Object[] objArr = this.zzb;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        this.modCount++;
        return obj2;
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzjb zzd(int i3) {
        if (i3 >= this.zzc) {
            return new zzko(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    private zzko(Object[] objArr, int i3, boolean z2) {
        super(z2);
        this.zzb = objArr;
        this.zzc = i3;
    }

    public final boolean add(Object obj) {
        zza();
        int i3 = this.zzc;
        Object[] objArr = this.zzb;
        if (i3 == objArr.length) {
            this.zzb = Arrays.copyOf(objArr, ((i3 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        objArr2[i4] = obj;
        this.modCount++;
        return true;
    }
}
