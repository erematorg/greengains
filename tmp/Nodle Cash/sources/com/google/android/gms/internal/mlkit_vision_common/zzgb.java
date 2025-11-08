package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgb implements ObjectEncoder {
    static final zzgb zza = new zzgb();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("detectorOptions"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("totalInitializationMs"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("loggingInitializationMs"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("otherErrors"));

    private zzgb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkj zzkj = (zzkj) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
