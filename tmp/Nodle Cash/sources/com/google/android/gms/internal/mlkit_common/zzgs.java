package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgs implements ObjectEncoder {
    static final zzgs zza = new zzgs();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder("modelInfo"));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("initialDownloadConditions"));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("updateDownloadConditions"));
    private static final FieldDescriptor zze = a.g(4, FieldDescriptor.builder("isModelUpdateEnabled"));

    private zzgs() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) ((zznl) obj).zza());
        objectEncoderContext.add(zzc, (Object) null);
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) null);
    }
}
