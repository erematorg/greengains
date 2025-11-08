package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalc  reason: invalid package */
final class zzalc<E> extends zzahi<E> implements RandomAccess {
    private static final zzalc<Object> zza = new zzalc<>(new Object[0], 0, false);
    private E[] zzb;
    private int zzc;

    public zzalc() {
        this(new Object[10], 0, true);
    }

    private final String zzb(int i3) {
        return a.r("Index:", i3, ", Size:", this.zzc);
    }

    private final void zzc(int i3) {
        if (i3 < 0 || i3 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzb(i3));
        }
    }

    public static <E> zzalc<E> zzd() {
        return zza;
    }

    public final void add(int i3, E e3) {
        int i4;
        zza();
        if (i3 < 0 || i3 > (i4 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzb(i3));
        }
        E[] eArr = this.zzb;
        if (i4 < eArr.length) {
            System.arraycopy(eArr, i3, eArr, i3 + 1, i4 - i3);
        } else {
            E[] eArr2 = new Object[b.b(i4, 3, 2, 1)];
            System.arraycopy(eArr, 0, eArr2, 0, i3);
            System.arraycopy(this.zzb, i3, eArr2, i3 + 1, this.zzc - i3);
            this.zzb = eArr2;
        }
        this.zzb[i3] = e3;
        this.zzc++;
        this.modCount++;
    }

    public final E get(int i3) {
        zzc(i3);
        return this.zzb[i3];
    }

    public final E remove(int i3) {
        zza();
        zzc(i3);
        E[] eArr = this.zzb;
        E e3 = eArr[i3];
        int i4 = this.zzc;
        if (i3 < i4 - 1) {
            System.arraycopy(eArr, i3 + 1, eArr, i3, (i4 - i3) - 1);
        }
        this.zzc--;
        this.modCount++;
        return e3;
    }

    public final E set(int i3, E e3) {
        zza();
        zzc(i3);
        E[] eArr = this.zzb;
        E e4 = eArr[i3];
        eArr[i3] = e3;
        this.modCount++;
        return e4;
    }

    public final int size() {
        return this.zzc;
    }

    public final /* synthetic */ zzajl zza(int i3) {
        if (i3 >= this.zzc) {
            return new zzalc(Arrays.copyOf(this.zzb, i3), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    private zzalc(E[] eArr, int i3, boolean z2) {
        super(z2);
        this.zzb = eArr;
        this.zzc = i3;
    }

    public final boolean add(E e3) {
        zza();
        int i3 = this.zzc;
        E[] eArr = this.zzb;
        if (i3 == eArr.length) {
            this.zzb = Arrays.copyOf(eArr, ((i3 * 3) / 2) + 1);
        }
        E[] eArr2 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        eArr2[i4] = e3;
        this.modCount++;
        return true;
    }
}
