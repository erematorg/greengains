package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import A.a;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzeh<MessageType extends zzeh<MessageType, BuilderType>, BuilderType extends zzeb<MessageType, BuilderType>> extends zzcq<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzgt zzc = zzgt.zzc();
    private int zzd = -1;

    public static zzef zzI(zzfm zzfm, Object obj, zzfm zzfm2, zzek zzek, int i3, zzhf zzhf, Class cls) {
        return new zzef(zzfm, obj, zzfm2, new zzee((zzek) null, i3, zzhf, false, false), cls);
    }

    public static zzeh zzJ(Class cls) {
        Map map = zzb;
        zzeh zzeh = (zzeh) map.get(cls);
        if (zzeh == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzeh = (zzeh) map.get(cls);
            } catch (ClassNotFoundException e3) {
                throw new IllegalStateException("Class initialization cannot fail.", e3);
            }
        }
        if (zzeh == null) {
            zzeh = (zzeh) ((zzeh) zzgz.zze(cls)).zzg(6, (Object) null, (Object) null);
            if (zzeh != null) {
                map.put(cls, zzeh);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzeh;
    }

    public static zzeh zzL(zzeh zzeh, byte[] bArr, zzds zzds) throws zzer {
        zzeh zze = zze(zzeh, bArr, 0, bArr.length, zzds);
        if (zze == null || zzX(zze, true)) {
            return zze;
        }
        throw new zzgr(zze).zza();
    }

    public static zzem zzM() {
        return zzdz.zzf();
    }

    public static zzem zzN(zzem zzem) {
        int size = zzem.size();
        return zzem.zzg(size == 0 ? 10 : size + size);
    }

    public static zzen zzO() {
        return zzei.zzf();
    }

    public static zzeo zzP() {
        return zzfv.zze();
    }

    public static zzeo zzQ(zzeo zzeo) {
        int size = zzeo.size();
        return zzeo.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzR(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static Object zzS(zzfm zzfm, String str, Object[] objArr) {
        return new zzfw(zzfm, str, objArr);
    }

    public static void zzV(Class cls, zzeh zzeh) {
        zzeh.zzU();
        zzb.put(cls, zzeh);
    }

    public static final boolean zzX(zzeh zzeh, boolean z2) {
        byte byteValue = ((Byte) zzeh.zzg(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzfu.zza().zzb(zzeh.getClass()).zzk(zzeh);
        if (z2) {
            zzeh.zzg(2, true != zzk ? null : zzeh, (Object) null);
        }
        return zzk;
    }

    private final int zzc(zzge zzge) {
        return zzfu.zza().zzb(getClass()).zza(this);
    }

    private static zzeh zze(zzeh zzeh, byte[] bArr, int i3, int i4, zzds zzds) throws zzer {
        if (i4 == 0) {
            return zzeh;
        }
        zzeh zzK = zzeh.zzK();
        try {
            zzge zzb2 = zzfu.zza().zzb(zzK.getClass());
            zzb2.zzh(zzK, bArr, 0, i4, new zzcu(zzds));
            zzb2.zzf(zzK);
            return zzK;
        } catch (zzer e3) {
            throw e3;
        } catch (zzgr e4) {
            throw e4.zza();
        } catch (IOException e5) {
            if (e5.getCause() instanceof zzer) {
                throw ((zzer) e5.getCause());
            }
            throw new zzer(e5);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzfu.zza().zzb(getClass()).zzj(this, (zzeh) obj);
    }

    public final int hashCode() {
        if (zzY()) {
            return zzE();
        }
        int i3 = this.zza;
        if (i3 != 0) {
            return i3;
        }
        int zzE = zzE();
        this.zza = zzE;
        return zzE;
    }

    public final String toString() {
        return zzfo.zza(this, super.toString());
    }

    public final int zzB(zzge zzge) {
        if (zzY()) {
            int zza = zzge.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException(a.k("serialized size must be non-negative, was ", zza));
        }
        int i3 = this.zzd & Integer.MAX_VALUE;
        if (i3 != Integer.MAX_VALUE) {
            return i3;
        }
        int zza2 = zzge.zza(this);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException(a.k("serialized size must be non-negative, was ", zza2));
    }

    public final int zzE() {
        return zzfu.zza().zzb(getClass()).zzb(this);
    }

    public final int zzF() {
        if (zzY()) {
            int zzc2 = zzc((zzge) null);
            if (zzc2 >= 0) {
                return zzc2;
            }
            throw new IllegalStateException(a.k("serialized size must be non-negative, was ", zzc2));
        }
        int i3 = this.zzd & Integer.MAX_VALUE;
        if (i3 == Integer.MAX_VALUE) {
            i3 = zzc((zzge) null);
            if (i3 >= 0) {
                this.zzd = (this.zzd & Integer.MIN_VALUE) | i3;
            } else {
                throw new IllegalStateException(a.k("serialized size must be non-negative, was ", i3));
            }
        }
        return i3;
    }

    public final zzeb zzG() {
        return (zzeb) zzg(5, (Object) null, (Object) null);
    }

    public final zzeb zzH() {
        zzeb zzeb = (zzeb) zzg(5, (Object) null, (Object) null);
        zzeb.zzg(this);
        return zzeb;
    }

    public final zzeh zzK() {
        return (zzeh) zzg(4, (Object) null, (Object) null);
    }

    public final void zzT() {
        zzfu.zza().zzb(getClass()).zzf(this);
        zzU();
    }

    public final void zzU() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void zzW(int i3) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean zzY() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final /* synthetic */ zzfl zzZ() {
        return (zzeb) zzg(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzfl zzaa() {
        zzeb zzeb = (zzeb) zzg(5, (Object) null, (Object) null);
        zzeb.zzg(this);
        return zzeb;
    }

    public final void zzab(zzdn zzdn) throws IOException {
        zzfu.zza().zzb(getClass()).zzi(this, zzdo.zza(zzdn));
    }

    public final /* synthetic */ zzfm zzac() {
        return (zzeh) zzg(6, (Object) null, (Object) null);
    }

    public final boolean zzad() {
        return zzX(this, true);
    }

    public abstract Object zzg(int i3, Object obj, Object obj2);
}
