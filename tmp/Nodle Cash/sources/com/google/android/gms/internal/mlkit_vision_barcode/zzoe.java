package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzoe implements ObjectEncoder {
    static final zzoe zza = new zzoe();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("appName"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("sessionId"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("startZoomLevel"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("endZoomLevel"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("durationMs"));
    private static final FieldDescriptor zzg = a.o(6, FieldDescriptor.builder("predictedArea"));

    private zzoe() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzut zzut = (zzut) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzut.zze());
        objectEncoderContext.add(zzc, (Object) zzut.zzf());
        objectEncoderContext.add(zzd, (Object) zzut.zzc());
        objectEncoderContext.add(zze, (Object) zzut.zzb());
        objectEncoderContext.add(zzf, (Object) zzut.zzd());
        objectEncoderContext.add(zzg, (Object) zzut.zza());
    }
}
