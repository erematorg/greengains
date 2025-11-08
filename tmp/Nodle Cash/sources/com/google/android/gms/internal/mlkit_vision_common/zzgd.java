package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgd implements ObjectEncoder {
    static final zzgd zza = new zzgd();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("detectorMode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("personDetectionMode"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("landmarkDetectionMode"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("preferredHardwareConfigs"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("runConfig"));

    private zzgd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkl zzkl = (zzkl) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
