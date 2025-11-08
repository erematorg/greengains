package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzds implements ObjectEncoder {
    static final zzds zza = new zzds();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("modelType"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("isSuccessful"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("modelName"));

    private zzds() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzhz zzhz = (zzhz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
