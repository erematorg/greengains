package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzka implements ObjectEncoder {
    static final zzka zza = new zzka();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("maxMs"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("minMs"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("avgMs"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("firstQuartileMs"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("medianMs"));
    private static final FieldDescriptor zzg = a.o(6, FieldDescriptor.builder("thirdQuartileMs"));

    private zzka() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqd zzqd = (zzqd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzqd.zzc());
        objectEncoderContext.add(zzc, (Object) zzqd.zze());
        objectEncoderContext.add(zzd, (Object) zzqd.zza());
        objectEncoderContext.add(zze, (Object) zzqd.zzb());
        objectEncoderContext.add(zzf, (Object) zzqd.zzd());
        objectEncoderContext.add(zzg, (Object) zzqd.zzf());
    }
}
