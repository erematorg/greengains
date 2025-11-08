package com.google.android.gms.internal.fido;

import java.util.Arrays;
import java.util.Set;
import javax.annotation.CheckForNull;

public abstract class zzbc extends zzav implements Set {
    @CheckForNull
    private transient zzaz zza;

    private static zzbc zzf(int i3, Object... objArr) {
        if (i3 == 0) {
            return zzbt.zza;
        }
        if (i3 != 1) {
            int zzh = zzh(i3);
            Object[] objArr2 = new Object[zzh];
            int i4 = zzh - 1;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                Object obj = objArr[i7];
                zzbq.zza(obj, i7);
                int hashCode = obj.hashCode();
                int zza2 = zzau.zza(hashCode);
                while (true) {
                    int i8 = zza2 & i4;
                    Object obj2 = objArr2[i8];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zza2++;
                    } else {
                        objArr[i6] = obj;
                        objArr2[i8] = obj;
                        i5 += hashCode;
                        i6++;
                        break;
                    }
                }
            }
            Arrays.fill(objArr, i6, i3, (Object) null);
            if (i6 == 1) {
                Object obj3 = objArr[0];
                obj3.getClass();
                return new zzby(obj3);
            }
            if (zzh(i6) < zzh / 2) {
                return zzf(i6, objArr);
            }
            if (i6 <= 0) {
                objArr = Arrays.copyOf(objArr, i6);
            }
            return new zzbt(objArr, i5, objArr2, i4, i6);
        }
        Object obj4 = objArr[0];
        obj4.getClass();
        return new zzby(obj4);
    }

    public static int zzh(int i3) {
        int max = Math.max(i3, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        } else if (max < 1073741824) {
            return 1073741824;
        } else {
            throw new IllegalArgumentException("collection too large");
        }
    }

    public static zzbc zzk(Object obj, Object obj2) {
        return zzf(2, obj, obj2);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbc) && zzg() && ((zzbc) obj).zzg() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return zzbx.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzcb iterator();

    public boolean zzg() {
        return false;
    }

    public zzaz zzi() {
        zzaz zzaz = this.zza;
        if (zzaz != null) {
            return zzaz;
        }
        zzaz zzj = zzj();
        this.zza = zzj;
        return zzj;
    }

    public zzaz zzj() {
        Object[] array = toArray();
        int i3 = zzaz.zzd;
        return zzaz.zzh(array, array.length);
    }
}
