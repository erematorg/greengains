package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzev implements ObjectEncoder {
    static final zzev zza = new zzev();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("name"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("type"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("version"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("featureLevel"));

    private zzev() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlk zzlk = (zzlk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
