package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhk implements ObjectEncoder {
    static final zzhk zza = new zzhk();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("logEventKey"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("eventCount"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("inferenceDurationStats"));

    private zzhk() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzfv zzfv = (zzfv) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzfv.zza());
        objectEncoderContext.add(zzc, (Object) zzfv.zzc());
        objectEncoderContext.add(zzd, (Object) zzfv.zzb());
    }
}
