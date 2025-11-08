package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzeb implements ObjectEncoder {
    static final zzeb zza = new zzeb();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("mode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("landmark"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("classification"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("prominentFaceOnly"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("tracking"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("minFaceSize"));

    private zzeb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlu zzlu = (zzlu) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
