package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgr implements ObjectEncoder {
    static final zzgr zza = new zzgr();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder("name"));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("version"));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("source"));
    private static final FieldDescriptor zze = a.g(4, FieldDescriptor.builder("uri"));
    private static final FieldDescriptor zzf = a.g(5, FieldDescriptor.builder("hash"));
    private static final FieldDescriptor zzg = a.g(6, FieldDescriptor.builder("modelType"));
    private static final FieldDescriptor zzh = a.g(7, FieldDescriptor.builder("size"));
    private static final FieldDescriptor zzi = a.g(8, FieldDescriptor.builder("hasLabelMap"));
    private static final FieldDescriptor zzj = a.g(9, FieldDescriptor.builder("isManifestModel"));

    private zzgr() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zznh zznh = (zznh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zznh.zzd());
        objectEncoderContext.add(zzc, (Object) null);
        objectEncoderContext.add(zzd, (Object) zznh.zzb());
        objectEncoderContext.add(zze, (Object) null);
        objectEncoderContext.add(zzf, (Object) zznh.zzc());
        objectEncoderContext.add(zzg, (Object) zznh.zza());
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) null);
        objectEncoderContext.add(zzj, (Object) null);
    }
}
