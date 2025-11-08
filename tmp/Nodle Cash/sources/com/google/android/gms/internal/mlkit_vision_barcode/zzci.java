package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

final class zzci extends AbstractMap implements Serializable {
    /* access modifiers changed from: private */
    public static final Object zzd = new Object();
    @CheckForNull
    transient int[] zza;
    @CheckForNull
    transient Object[] zzb;
    @CheckForNull
    transient Object[] zzc;
    @CheckForNull
    private transient Object zze;
    /* access modifiers changed from: private */
    public transient int zzf;
    /* access modifiers changed from: private */
    public transient int zzg;
    @CheckForNull
    private transient Set zzh;
    @CheckForNull
    private transient Set zzi;
    @CheckForNull
    private transient Collection zzj;

    public zzci() {
        zzp(3);
    }

    /* access modifiers changed from: private */
    public final int[] zzA() {
        int[] iArr = this.zza;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    /* access modifiers changed from: private */
    public final Object[] zzB() {
        Object[] objArr = this.zzb;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    /* access modifiers changed from: private */
    public final Object[] zzC() {
        Object[] objArr = this.zzc;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public static /* synthetic */ Object zzg(zzci zzci, int i3) {
        return zzci.zzB()[i3];
    }

    public static /* synthetic */ Object zzi(zzci zzci) {
        Object obj = zzci.zze;
        Objects.requireNonNull(obj);
        return obj;
    }

    public static /* synthetic */ Object zzj(zzci zzci, int i3) {
        return zzci.zzC()[i3];
    }

    public static /* synthetic */ void zzn(zzci zzci, int i3, Object obj) {
        zzci.zzC()[i3] = obj;
    }

    /* access modifiers changed from: private */
    public final int zzv() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* access modifiers changed from: private */
    public final int zzw(@CheckForNull Object obj) {
        if (zzr()) {
            return -1;
        }
        int zza2 = zzck.zza(obj);
        int zzv = zzv();
        Object obj2 = this.zze;
        Objects.requireNonNull(obj2);
        int zzc2 = zzcj.zzc(obj2, zza2 & zzv);
        if (zzc2 != 0) {
            int i3 = ~zzv;
            int i4 = zza2 & i3;
            do {
                int i5 = zzc2 - 1;
                int i6 = zzA()[i5];
                if ((i6 & i3) == i4 && zzax.zza(obj, zzB()[i5])) {
                    return i5;
                }
                zzc2 = i6 & zzv;
            } while (zzc2 != 0);
        }
        return -1;
    }

    private final int zzx(int i3, int i4, int i5, int i6) {
        int i7 = i4 - 1;
        Object zzd2 = zzcj.zzd(i4);
        if (i6 != 0) {
            zzcj.zze(zzd2, i5 & i7, i6 + 1);
        }
        Object obj = this.zze;
        Objects.requireNonNull(obj);
        int[] zzA = zzA();
        for (int i8 = 0; i8 <= i3; i8++) {
            int zzc2 = zzcj.zzc(obj, i8);
            while (zzc2 != 0) {
                int i9 = zzc2 - 1;
                int i10 = zzA[i9];
                int i11 = ((~i3) & i10) | i8;
                int i12 = i11 & i7;
                int zzc3 = zzcj.zzc(zzd2, i12);
                zzcj.zze(zzd2, i12, zzc2);
                zzA[i9] = ((~i7) & i11) | (zzc3 & i7);
                zzc2 = i10 & i3;
            }
        }
        this.zze = zzd2;
        zzz(i7);
        return i7;
    }

    /* access modifiers changed from: private */
    public final Object zzy(@CheckForNull Object obj) {
        if (!zzr()) {
            int zzv = zzv();
            Object obj2 = this.zze;
            Objects.requireNonNull(obj2);
            int zzb2 = zzcj.zzb(obj, (Object) null, zzv, obj2, zzA(), zzB(), (Object[]) null);
            if (zzb2 != -1) {
                Object obj3 = zzC()[zzb2];
                zzq(zzb2, zzv);
                this.zzg--;
                zzo();
                return obj3;
            }
        }
        return zzd;
    }

    private final void zzz(int i3) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i3)) & 31) | (this.zzf & -32);
    }

    public final void clear() {
        if (!zzr()) {
            zzo();
            Map zzl = zzl();
            if (zzl != null) {
                this.zzf = zzdw.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                zzl.clear();
                this.zze = null;
                this.zzg = 0;
                return;
            }
            Arrays.fill(zzB(), 0, this.zzg, (Object) null);
            Arrays.fill(zzC(), 0, this.zzg, (Object) null);
            Object obj = this.zze;
            Objects.requireNonNull(obj);
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(zzA(), 0, this.zzg, 0);
            this.zzg = 0;
        }
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        Map zzl = zzl();
        return zzl != null ? zzl.containsKey(obj) : zzw(obj) != -1;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.containsValue(obj);
        }
        for (int i3 = 0; i3 < this.zzg; i3++) {
            if (zzax.zza(obj, zzC()[i3])) {
                return true;
            }
        }
        return false;
    }

    public final Set entrySet() {
        Set set = this.zzi;
        if (set != null) {
            return set;
        }
        zzcc zzcc = new zzcc(this);
        this.zzi = zzcc;
        return zzcc;
    }

    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.get(obj);
        }
        int zzw = zzw(obj);
        if (zzw == -1) {
            return null;
        }
        return zzC()[zzw];
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final Set keySet() {
        Set set = this.zzh;
        if (set != null) {
            return set;
        }
        zzcf zzcf = new zzcf(this);
        this.zzh = zzcf;
        return zzcf;
    }

    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int min;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (zzr()) {
            zzaz.zzf(zzr(), "Arrays already allocated");
            int i3 = this.zzf;
            int max = Math.max(i3 + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > ((int) ((double) highestOneBit)) && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = zzcj.zzd(max2);
            zzz(max2 - 1);
            this.zza = new int[i3];
            this.zzb = new Object[i3];
            this.zzc = new Object[i3];
        }
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.put(obj3, obj4);
        }
        int[] zzA = zzA();
        Object[] zzB = zzB();
        Object[] zzC = zzC();
        int i4 = this.zzg;
        int i5 = i4 + 1;
        int zza2 = zzck.zza(obj);
        int zzv = zzv();
        int i6 = zza2 & zzv;
        Object obj5 = this.zze;
        Objects.requireNonNull(obj5);
        int zzc2 = zzcj.zzc(obj5, i6);
        if (zzc2 != 0) {
            int i7 = ~zzv;
            int i8 = zza2 & i7;
            int i9 = 0;
            while (true) {
                int i10 = zzc2 - 1;
                int i11 = zzA[i10];
                int i12 = i11 & i7;
                if (i12 != i8 || !zzax.zza(obj3, zzB[i10])) {
                    int i13 = i11 & zzv;
                    i9++;
                    if (i13 != 0) {
                        zzc2 = i13;
                    } else if (i9 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(zzv() + 1, 1.0f);
                        for (int zze2 = zze(); zze2 >= 0; zze2 = zzf(zze2)) {
                            linkedHashMap.put(zzB()[zze2], zzC()[zze2]);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        zzo();
                        return linkedHashMap.put(obj3, obj4);
                    } else if (i5 > zzv) {
                        zzv = zzx(zzv, zzcj.zza(zzv), zza2, i4);
                    } else {
                        zzA[i10] = (i5 & zzv) | i12;
                    }
                } else {
                    Object obj6 = zzC[i10];
                    zzC[i10] = obj4;
                    return obj6;
                }
            }
        } else if (i5 > zzv) {
            zzv = zzx(zzv, zzcj.zza(zzv), zza2, i4);
        } else {
            Object obj7 = this.zze;
            Objects.requireNonNull(obj7);
            zzcj.zze(obj7, i6, i5);
        }
        int length = zzA().length;
        if (i5 > length && (min = Math.min(LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.zza = Arrays.copyOf(zzA(), min);
            this.zzb = Arrays.copyOf(zzB(), min);
            this.zzc = Arrays.copyOf(zzC(), min);
        }
        zzA()[i4] = (~zzv) & zza2;
        zzB()[i4] = obj3;
        zzC()[i4] = obj4;
        this.zzg = i5;
        zzo();
        return null;
    }

    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.remove(obj);
        }
        Object zzy = zzy(obj);
        if (zzy == zzd) {
            return null;
        }
        return zzy;
    }

    public final int size() {
        Map zzl = zzl();
        return zzl != null ? zzl.size() : this.zzg;
    }

    public final Collection values() {
        Collection collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzch zzch = new zzch(this);
        this.zzj = zzch;
        return zzch;
    }

    public final int zze() {
        return isEmpty() ? -1 : 0;
    }

    public final int zzf(int i3) {
        int i4 = i3 + 1;
        if (i4 < this.zzg) {
            return i4;
        }
        return -1;
    }

    @CheckForNull
    public final Map zzl() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final void zzo() {
        this.zzf += 32;
    }

    public final void zzp(int i3) {
        this.zzf = zzdw.zza(i3, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public final void zzq(int i3, int i4) {
        Object obj = this.zze;
        Objects.requireNonNull(obj);
        int[] zzA = zzA();
        Object[] zzB = zzB();
        Object[] zzC = zzC();
        int size = size();
        int i5 = size - 1;
        if (i3 < i5) {
            int i6 = i3 + 1;
            Object obj2 = zzB[i5];
            zzB[i3] = obj2;
            zzC[i3] = zzC[i5];
            zzB[i5] = null;
            zzC[i5] = null;
            zzA[i3] = zzA[i5];
            zzA[i5] = 0;
            int zza2 = zzck.zza(obj2) & i4;
            int zzc2 = zzcj.zzc(obj, zza2);
            if (zzc2 != size) {
                while (true) {
                    int i7 = zzc2 - 1;
                    int i8 = zzA[i7];
                    int i9 = i8 & i4;
                    if (i9 != size) {
                        zzc2 = i9;
                    } else {
                        zzA[i7] = ((~i4) & i8) | (i6 & i4);
                        return;
                    }
                }
            } else {
                zzcj.zze(obj, zza2, i6);
            }
        } else {
            zzB[i3] = null;
            zzC[i3] = null;
            zzA[i3] = 0;
        }
    }

    public final boolean zzr() {
        return this.zze == null;
    }

    public zzci(int i3) {
        zzp(12);
    }
}
