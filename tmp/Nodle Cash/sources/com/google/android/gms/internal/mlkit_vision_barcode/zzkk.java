package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzkk implements ObjectEncoder {
    static final zzkk zza = new zzkk();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("durationMs"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("autoManageModelOnBackground"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("autoManageModelOnLowMemory"));
    private static final FieldDescriptor zzg = a.o(6, FieldDescriptor.builder("isNnApiEnabled"));
    private static final FieldDescriptor zzh = a.o(7, FieldDescriptor.builder("eventsCount"));
    private static final FieldDescriptor zzi = a.o(8, FieldDescriptor.builder("otherErrors"));
    private static final FieldDescriptor zzj = a.o(9, FieldDescriptor.builder("remoteConfigValueForAcceleration"));
    private static final FieldDescriptor zzk = a.o(10, FieldDescriptor.builder("isAccelerated"));

    private zzkk() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqq zzqq = (zzqq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzqq.zze());
        objectEncoderContext.add(zzc, (Object) zzqq.zza());
        objectEncoderContext.add(zzd, (Object) zzqq.zzd());
        objectEncoderContext.add(zze, (Object) zzqq.zzb());
        objectEncoderContext.add(zzf, (Object) zzqq.zzc());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) null);
        objectEncoderContext.add(zzj, (Object) null);
        objectEncoderContext.add(zzk, (Object) null);
    }
}
