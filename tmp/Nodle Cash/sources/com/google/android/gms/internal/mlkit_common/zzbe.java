package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

final class zzbe implements ObjectEncoderContext {
    private static final Charset zza = Charset.forName("UTF-8");
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder(JwtUtilsKt.DID_METHOD_KEY));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("value"));
    private static final ObjectEncoder zzd = new zzbd();
    private OutputStream zze;
    private final Map zzf;
    private final Map zzg;
    private final ObjectEncoder zzh;
    private final zzbi zzi = new zzbi(this);

    public zzbe(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.zze = outputStream;
        this.zzf = map;
        this.zzg = map2;
        this.zzh = objectEncoder;
    }

    public static /* synthetic */ void zzg(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(zzb, entry.getKey());
        objectEncoderContext.add(zzc, entry.getValue());
    }

    private static int zzh(FieldDescriptor fieldDescriptor) {
        zzbc zzbc = (zzbc) fieldDescriptor.getProperty(zzbc.class);
        if (zzbc != null) {
            return zzbc.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long zzi(ObjectEncoder objectEncoder, Object obj) throws IOException {
        OutputStream outputStream;
        zzaz zzaz = new zzaz();
        try {
            outputStream = this.zze;
            this.zze = zzaz;
            objectEncoder.encode(obj, this);
            this.zze = outputStream;
            long zza2 = zzaz.zza();
            zzaz.close();
            return zza2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static zzbc zzj(FieldDescriptor fieldDescriptor) {
        zzbc zzbc = (zzbc) fieldDescriptor.getProperty(zzbc.class);
        if (zzbc != null) {
            return zzbc;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzbe zzk(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z2) throws IOException {
        long zzi2 = zzi(objectEncoder, obj);
        if (z2 && zzi2 == 0) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 2);
        zzo(zzi2);
        objectEncoder.encode(obj, this);
        return this;
    }

    private final zzbe zzl(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z2) throws IOException {
        this.zzi.zza(fieldDescriptor, z2);
        valueEncoder.encode(obj, this.zzi);
        return this;
    }

    private static ByteBuffer zzm(int i3) {
        return ByteBuffer.allocate(i3).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void zzn(int i3) throws IOException {
        while (true) {
            int i4 = i3 & 127;
            if (((long) (i3 & -128)) != 0) {
                this.zze.write(i4 | 128);
                i3 >>>= 7;
            } else {
                this.zze.write(i4);
                return;
            }
        }
    }

    private final void zzo(long j2) throws IOException {
        while (true) {
            int i3 = ((int) j2) & 127;
            if ((-128 & j2) != 0) {
                this.zze.write(i3 | 128);
                j2 >>>= 7;
            } else {
                this.zze.write(i3);
                return;
            }
        }
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d2) throws IOException {
        zza(fieldDescriptor, d2, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        zzf(obj);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    public final ObjectEncoderContext zza(@NonNull FieldDescriptor fieldDescriptor, double d2, boolean z2) throws IOException {
        if (z2 && d2 == 0.0d) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 1);
        this.zze.write(zzm(8).putDouble(d2).array());
        return this;
    }

    public final ObjectEncoderContext zzb(@NonNull FieldDescriptor fieldDescriptor, float f2, boolean z2) throws IOException {
        if (z2 && f2 == 0.0f) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 5);
        this.zze.write(zzm(4).putFloat(f2).array());
        return this;
    }

    public final ObjectEncoderContext zzc(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj, boolean z2) throws IOException {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z2 || charSequence.length() != 0) {
                    zzn((zzh(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(zza);
                    zzn(bytes.length);
                    this.zze.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                for (Object zzc2 : (Collection) obj) {
                    zzc(fieldDescriptor, zzc2, false);
                }
            } else if (obj instanceof Map) {
                for (Map.Entry zzk : ((Map) obj).entrySet()) {
                    zzk(zzd, fieldDescriptor, zzk, false);
                }
            } else if (obj instanceof Double) {
                zza(fieldDescriptor, ((Double) obj).doubleValue(), z2);
                return this;
            } else if (obj instanceof Float) {
                zzb(fieldDescriptor, ((Float) obj).floatValue(), z2);
                return this;
            } else if (obj instanceof Number) {
                zze(fieldDescriptor, ((Number) obj).longValue(), z2);
                return this;
            } else if (obj instanceof Boolean) {
                zzd(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z2);
                return this;
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (!z2 || bArr.length != 0) {
                    zzn((zzh(fieldDescriptor) << 3) | 2);
                    zzn(bArr.length);
                    this.zze.write(bArr);
                    return this;
                }
            } else {
                ObjectEncoder objectEncoder = (ObjectEncoder) this.zzf.get(obj.getClass());
                if (objectEncoder != null) {
                    zzk(objectEncoder, fieldDescriptor, obj, z2);
                    return this;
                }
                ValueEncoder valueEncoder = (ValueEncoder) this.zzg.get(obj.getClass());
                if (valueEncoder != null) {
                    zzl(valueEncoder, fieldDescriptor, obj, z2);
                    return this;
                } else if (obj instanceof zzba) {
                    zzd(fieldDescriptor, ((zzba) obj).zza(), true);
                    return this;
                } else if (obj instanceof Enum) {
                    zzd(fieldDescriptor, ((Enum) obj).ordinal(), true);
                    return this;
                } else {
                    zzk(this.zzh, fieldDescriptor, obj, z2);
                    return this;
                }
            }
        }
        return this;
    }

    public final zzbe zzd(@NonNull FieldDescriptor fieldDescriptor, int i3, boolean z2) throws IOException {
        if (!z2 || i3 != 0) {
            zzbc zzj = zzj(fieldDescriptor);
            int ordinal = zzj.zzb().ordinal();
            if (ordinal == 0) {
                zzn(zzj.zza() << 3);
                zzn(i3);
            } else if (ordinal == 1) {
                zzn(zzj.zza() << 3);
                zzn((i3 + i3) ^ (i3 >> 31));
            } else if (ordinal == 2) {
                zzn((zzj.zza() << 3) | 5);
                this.zze.write(zzm(4).putInt(i3).array());
            }
        }
        return this;
    }

    public final zzbe zze(@NonNull FieldDescriptor fieldDescriptor, long j2, boolean z2) throws IOException {
        if (!z2 || j2 != 0) {
            zzbc zzj = zzj(fieldDescriptor);
            int ordinal = zzj.zzb().ordinal();
            if (ordinal == 0) {
                zzn(zzj.zza() << 3);
                zzo(j2);
            } else if (ordinal == 1) {
                zzn(zzj.zza() << 3);
                zzo((j2 >> 63) ^ (j2 + j2));
            } else if (ordinal == 2) {
                zzn((zzj.zza() << 3) | 1);
                this.zze.write(zzm(8).putLong(j2).array());
            }
        }
        return this;
    }

    public final zzbe zzf(@Nullable Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.zzf.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for ".concat(String.valueOf(obj.getClass())));
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2) throws IOException {
        zzb(fieldDescriptor, f2, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext nested(@NonNull String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i3) throws IOException {
        zzd(fieldDescriptor, i3, true);
        return this;
    }

    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j2) throws IOException {
        zze(fieldDescriptor, j2, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        zzc(fieldDescriptor, obj, true);
        return this;
    }

    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z2) throws IOException {
        zzd(fieldDescriptor, z2 ? 1 : 0, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, double d2) throws IOException {
        zza(FieldDescriptor.of(str), d2, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, int i3) throws IOException {
        zzd(FieldDescriptor.of(str), i3, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, long j2) throws IOException {
        zze(FieldDescriptor.of(str), j2, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws IOException {
        zzc(FieldDescriptor.of(str), obj, true);
        return this;
    }

    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, boolean z2) throws IOException {
        zzd(FieldDescriptor.of(str), z2 ? 1 : 0, true);
        return this;
    }
}
