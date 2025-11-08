package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzfp implements ObjectEncoder {
    static final zzfp zza = new zzfp();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("maxLabels"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("confidenceThreshold"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("customLocalModelOptions"));

    private zzfp() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzjx zzjx = (zzjx) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
