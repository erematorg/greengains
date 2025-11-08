package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgq implements ObjectEncoder {
    static final zzgq zza = new zzgq();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("roughDownloadDurationMs"));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zze = a.g(4, FieldDescriptor.builder("exactDownloadDurationMs"));
    private static final FieldDescriptor zzf = a.g(5, FieldDescriptor.builder("downloadStatus"));
    private static final FieldDescriptor zzg = a.g(6, FieldDescriptor.builder("downloadFailureStatus"));
    private static final FieldDescriptor zzh = a.g(7, FieldDescriptor.builder("mddDownloadErrorCodes"));

    private zzgq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zznc zznc = (zznc) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zznc.zzc());
        objectEncoderContext.add(zzc, (Object) zznc.zzf());
        objectEncoderContext.add(zzd, (Object) zznc.zza());
        objectEncoderContext.add(zze, (Object) zznc.zze());
        objectEncoderContext.add(zzf, (Object) zznc.zzb());
        objectEncoderContext.add(zzg, (Object) zznc.zzd());
        objectEncoderContext.add(zzh, (Object) null);
    }
}
