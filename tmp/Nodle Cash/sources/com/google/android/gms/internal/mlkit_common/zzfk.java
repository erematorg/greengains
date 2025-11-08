package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzfk implements ObjectEncoder {
    static final zzfk zza = new zzfk();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder("modelType"));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("isSuccessful"));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("modelName"));

    private zzfk() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlo zzlo = (zzlo) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzlo.zza());
        objectEncoderContext.add(zzc, (Object) zzlo.zzb());
        objectEncoderContext.add(zzd, (Object) null);
    }
}
