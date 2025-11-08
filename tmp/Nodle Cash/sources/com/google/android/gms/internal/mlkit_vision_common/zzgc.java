package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgc implements ObjectEncoder {
    static final zzgc zza = new zzgc();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("inferenceCommonLogEvent"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("detectorOptions"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("loadDurationMs"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("sessionDurationMs"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("sessionTotalInferenceDurationMs"));

    private zzgc() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkk zzkk = (zzkk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
