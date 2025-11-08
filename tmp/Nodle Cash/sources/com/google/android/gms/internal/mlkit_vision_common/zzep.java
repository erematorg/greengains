package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzep implements ObjectEncoder {
    static final zzep zza = new zzep();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("roughDownloadDurationMs"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("exactDownloadDurationMs"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("downloadStatus"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("downloadFailureStatus"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("mddDownloadErrorCodes"));

    private zzep() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zziz zziz = (zziz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
