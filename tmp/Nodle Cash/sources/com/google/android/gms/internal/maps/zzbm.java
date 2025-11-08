package com.google.android.gms.internal.maps;

import A.a;
import com.google.android.gms.maps.model.FeatureType;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

public abstract class zzbm extends zzbf implements Set {
    @CheckForNull
    private transient zzbi zza;

    public static int zzf(int i3) {
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

    @SafeVarargs
    public static zzbm zzi(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Object[] objArr2 = new Object[7];
        objArr2[0] = FeatureType.ADMINISTRATIVE_AREA_LEVEL_1;
        objArr2[1] = FeatureType.ADMINISTRATIVE_AREA_LEVEL_2;
        objArr2[2] = FeatureType.COUNTRY;
        objArr2[3] = FeatureType.LOCALITY;
        objArr2[4] = FeatureType.POSTAL_CODE;
        objArr2[5] = FeatureType.SCHOOL_DISTRICT;
        System.arraycopy(objArr, 0, objArr2, 6, 1);
        return zzk(7, objArr2);
    }

    private static zzbm zzk(int i3, Object... objArr) {
        if (i3 == 0) {
            return zzbu.zza;
        }
        if (i3 != 1) {
            int zzf = zzf(i3);
            Object[] objArr2 = new Object[zzf];
            int i4 = zzf - 1;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i5 < i3) {
                Object obj = objArr[i5];
                if (obj != null) {
                    int hashCode = obj.hashCode();
                    int zza2 = zzbe.zza(hashCode);
                    while (true) {
                        int i8 = zza2 & i4;
                        Object obj2 = objArr2[i8];
                        if (obj2 != null) {
                            if (obj2.equals(obj)) {
                                break;
                            }
                            zza2++;
                        } else {
                            objArr[i7] = obj;
                            objArr2[i8] = obj;
                            i6 += hashCode;
                            i7++;
                            break;
                        }
                    }
                    i5++;
                } else {
                    throw new NullPointerException(a.k("at index ", i5));
                }
            }
            Arrays.fill(objArr, i7, i3, (Object) null);
            if (i7 == 1) {
                Object obj3 = objArr[0];
                Objects.requireNonNull(obj3);
                return new zzbw(obj3);
            }
            if (zzf(i7) < zzf / 2) {
                return zzk(i7, objArr);
            }
            if (i7 < 4) {
                objArr = Arrays.copyOf(objArr, i7);
            }
            return new zzbu(objArr, i6, objArr2, i4, i7);
        }
        Object obj4 = objArr[0];
        Objects.requireNonNull(obj4);
        return new zzbw(obj4);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbm) && zzj() && ((zzbm) obj).zzj() && hashCode() != obj.hashCode()) {
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
        return zzbv.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzbx iterator();

    public final zzbi zzg() {
        zzbi zzbi = this.zza;
        if (zzbi != null) {
            return zzbi;
        }
        zzbi zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    public zzbi zzh() {
        Object[] array = toArray();
        int i3 = zzbi.zzd;
        return zzbi.zzg(array, array.length);
    }

    public boolean zzj() {
        return false;
    }
}
