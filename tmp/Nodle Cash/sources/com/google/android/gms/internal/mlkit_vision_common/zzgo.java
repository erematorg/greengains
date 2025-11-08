package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgo implements ObjectEncoder {
    static final zzgo zza = new zzgo();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("pipelineNamespace"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("name"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("clientLibraryName"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("clientLibraryVersion"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("minClientLibraryVersion"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("maxClientLibraryVersion"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("sourceProduct"));

    private zzgo() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzln zzln = (zzln) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
