package com.google.android.gms.internal.measurement;

import A.a;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzlc.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzlc<MessageType extends zzlc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzji<MessageType, BuilderType> {
    private static Map<Class<?>, zzlc<?, ?>> zzc = new ConcurrentHashMap();
    protected zznw zzb = zznw.zzc();
    private int zzd = -1;

    public static class zza<T extends zzlc<T, ?>> extends zzjj<T> {
        public zza(T t2) {
        }
    }

    public static abstract class zzb<MessageType extends zzlc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzjh<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        public zzb(MessageType messagetype) {
            this.zzb = messagetype;
            if (!messagetype.zzco()) {
                this.zza = messagetype.zzce();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        private final BuilderType zzb(byte[] bArr, int i3, int i4, zzkp zzkp) throws zzlk {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzmz.zza().zza(this.zza).zza(this.zza, bArr, 0, i4, new zzjn(zzkp));
                return this;
            } catch (zzlk e3) {
                throw e3;
            } catch (IndexOutOfBoundsException unused) {
                throw zzlk.zzi();
            } catch (IOException e4) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e4);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzc */
        public final BuilderType zzb(zzkg zzkg, zzkp zzkp) throws IOException {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzmz.zza().zza(this.zza).zza(this.zza, zzkk.zza(zzkg), zzkp);
                return this;
            } catch (RuntimeException e3) {
                if (e3.getCause() instanceof IOException) {
                    throw ((IOException) e3.getCause());
                }
                throw e3;
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzb2 = (zzb) this.zzb.zza(zze.zze, (Object) null, (Object) null);
            zzb2.zza = (zzlc) zzaj();
            return zzb2;
        }

        public final /* synthetic */ zzjh zza(zzkg zzkg, zzkp zzkp) throws IOException {
            return (zzb) zzb(zzkg, zzkp);
        }

        public final /* synthetic */ zzjh zzaf() {
            return (zzb) clone();
        }

        /* renamed from: zzag */
        public final MessageType zzai() {
            MessageType messagetype = (zzlc) zzaj();
            if (zzlc.zza(messagetype, true)) {
                return messagetype;
            }
            throw new zznu(messagetype);
        }

        /* renamed from: zzah */
        public MessageType zzaj() {
            if (!this.zza.zzco()) {
                return this.zza;
            }
            this.zza.zzcl();
            return this.zza;
        }

        public final void zzak() {
            if (!this.zza.zzco()) {
                zzal();
            }
        }

        public void zzal() {
            MessageType zzce = this.zzb.zzce();
            zza(zzce, this.zza);
            this.zza = zzce;
        }

        public final /* synthetic */ zzml zzck() {
            return this.zzb;
        }

        public final boolean zzcn() {
            return zzlc.zza(this.zza, false);
        }

        public final /* synthetic */ zzjh zza(byte[] bArr, int i3, int i4) throws zzlk {
            return zzb(bArr, 0, i4, zzkp.zza);
        }

        public final /* synthetic */ zzjh zza(byte[] bArr, int i3, int i4, zzkp zzkp) throws zzlk {
            return zzb(bArr, 0, i4, zzkp);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzco()) {
                zzal();
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzmz.zza().zza(messagetype).zza(messagetype, messagetype2);
        }
    }

    public static final class zzc implements zzkx<zzc> {
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        public final int zza() {
            throw new NoSuchMethodError();
        }

        public final zzof zzb() {
            throw new NoSuchMethodError();
        }

        public final zzop zzc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        public final zzmk zza(zzmk zzmk, zzml zzml) {
            throw new NoSuchMethodError();
        }

        public final zzmq zza(zzmq zzmq, zzmq zzmq2) {
            throw new NoSuchMethodError();
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzlc<MessageType, BuilderType> implements zzmn {
        protected zzkv<zzc> zzc = zzkv.zzb();

        public final zzkv<zzc> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzkv) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* 'enum' modifier removed */
    public static final class zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    public static class zzf<ContainingType extends zzml, Type> extends zzkq<ContainingType, Type> {
    }

    private final int zza() {
        return zzmz.zza().zza(this).zzb(this);
    }

    private final int zzb(zznd<?> zznd) {
        return zznd == null ? zzmz.zza().zza(this).zza(this) : zznd.zza(this);
    }

    public static zzlj zzcf() {
        return zzlf.zzd();
    }

    public static zzli zzcg() {
        return zzlw.zzd();
    }

    public static <E> zzll<E> zzch() {
        return zzmy.zzd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzmz.zza().zza(this).zzb(this, (zzlc) obj);
        }
        return false;
    }

    public int hashCode() {
        if (zzco()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    public String toString() {
        return zzmm.zza((zzml) this, super.toString());
    }

    public abstract Object zza(int i3, Object obj, Object obj2);

    public final int zzby() {
        return this.zzd & Integer.MAX_VALUE;
    }

    public final void zzc(int i3) {
        if (i3 >= 0) {
            this.zzd = (i3 & Integer.MAX_VALUE) | (this.zzd & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException(a.k("serialized size must be non-negative, was ", i3));
    }

    public final int zzcb() {
        return zza((zznd) null);
    }

    public final <MessageType extends zzlc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzcc() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    public final BuilderType zzcd() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    public final MessageType zzce() {
        return (zzlc) zza(zze.zzd, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzmk zzci() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzmk zzcj() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    public final /* synthetic */ zzml zzck() {
        return (zzlc) zza(zze.zzf, (Object) null, (Object) null);
    }

    public final void zzcl() {
        zzmz.zza().zza(this).zzd(this);
        zzcm();
    }

    public final void zzcm() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final boolean zzcn() {
        return zza(this, true);
    }

    public final boolean zzco() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final int zza(zznd zznd) {
        if (zzco()) {
            int zzb2 = zzb(zznd);
            if (zzb2 >= 0) {
                return zzb2;
            }
            throw new IllegalStateException(a.k("serialized size must be non-negative, was ", zzb2));
        } else if (zzby() != Integer.MAX_VALUE) {
            return zzby();
        } else {
            int zzb3 = zzb(zznd);
            zzc(zzb3);
            return zzb3;
        }
    }

    public final <MessageType extends zzlc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zza(MessageType messagetype) {
        return zzcc().zza(messagetype);
    }

    public static <T extends zzlc<?, ?>> T zza(Class<T> cls) {
        T t2 = (zzlc) zzc.get(cls);
        if (t2 == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t2 = (zzlc) zzc.get(cls);
            } catch (ClassNotFoundException e3) {
                throw new IllegalStateException("Class initialization cannot fail.", e3);
            }
        }
        if (t2 == null) {
            t2 = (zzlc) ((zzlc) zzny.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (t2 != null) {
                zzc.put(cls, t2);
            } else {
                throw new IllegalStateException();
            }
        }
        return t2;
    }

    public static zzli zza(zzli zzli) {
        int size = zzli.size();
        return zzli.zzc(size == 0 ? 10 : size << 1);
    }

    public static <E> zzll<E> zza(zzll<E> zzll) {
        int size = zzll.size();
        return zzll.zza(size == 0 ? 10 : size << 1);
    }

    public static Object zza(Method method, Object obj, Object... objArr) {
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

    public static Object zza(zzml zzml, String str, Object[] objArr) {
        return new zznb(zzml, str, objArr);
    }

    public static <T extends zzlc<?, ?>> void zza(Class<T> cls, T t2) {
        t2.zzcm();
        zzc.put(cls, t2);
    }

    public final void zza(zzkl zzkl) throws IOException {
        zzmz.zza().zza(this).zza(this, (zzos) zzko.zza(zzkl));
    }

    public static final <T extends zzlc<T, ?>> boolean zza(T t2, boolean z2) {
        byte byteValue = ((Byte) t2.zza(zze.zza, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zze2 = zzmz.zza().zza(t2).zze(t2);
        if (z2) {
            t2.zza(zze.zzb, (Object) zze2 ? t2 : null, (Object) null);
        }
        return zze2;
    }
}
