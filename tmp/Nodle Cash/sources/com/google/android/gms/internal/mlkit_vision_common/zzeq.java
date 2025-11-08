package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzeq implements ObjectEncoder {
    static final zzeq zza = new zzeq();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("name"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("version"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("source"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("uri"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("hash"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("modelType"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("size"));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("hasLabelMap"));
    private static final FieldDescriptor zzj = a.q(9, FieldDescriptor.builder("isManifestModel"));

    private zzeq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzja zzja = (zzja) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
