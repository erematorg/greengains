package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzof implements ObjectEncoder {
    static final zzof zza = new zzof();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("xMin"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("yMin"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("xMax"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("yMax"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("confidenceScore"));

    private zzof() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzur zzur = (zzur) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzur.zzc());
        objectEncoderContext.add(zzc, (Object) zzur.zze());
        objectEncoderContext.add(zzd, (Object) zzur.zzb());
        objectEncoderContext.add(zze, (Object) zzur.zzd());
        objectEncoderContext.add(zzf, (Object) zzur.zza());
    }
}
