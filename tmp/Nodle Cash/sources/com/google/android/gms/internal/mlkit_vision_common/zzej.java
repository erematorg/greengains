package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzej implements ObjectEncoder {
    static final zzej zza = new zzej();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("durationMs"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("imageSource"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("imageFormat"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("imageByteSize"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("imageWidth"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("imageHeight"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("rotationDegrees"));

    private zzej() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zziq zziq = (zziq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zziq.zzg());
        objectEncoderContext.add(zzc, (Object) zziq.zzb());
        objectEncoderContext.add(zzd, (Object) zziq.zza());
        objectEncoderContext.add(zze, (Object) zziq.zzc());
        objectEncoderContext.add(zzf, (Object) zziq.zze());
        objectEncoderContext.add(zzg, (Object) zziq.zzd());
        objectEncoderContext.add(zzh, (Object) zziq.zzf());
    }
}
