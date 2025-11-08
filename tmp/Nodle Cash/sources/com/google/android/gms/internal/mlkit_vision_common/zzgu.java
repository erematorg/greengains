package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

final class zzgu implements ObjectEncoder {
    static final zzgu zza = new zzgu();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("firebaseProjectId"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("mlSdkVersion"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("tfliteSchemaVersion"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("gcmSenderId"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("apiKey"));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("languages"));
    private static final FieldDescriptor zzj = a.q(9, FieldDescriptor.builder("mlSdkInstanceId"));
    private static final FieldDescriptor zzk = a.q(10, FieldDescriptor.builder("isClearcutClient"));
    private static final FieldDescriptor zzl = a.q(11, FieldDescriptor.builder("isStandaloneMlkit"));
    private static final FieldDescriptor zzm = a.q(12, FieldDescriptor.builder("isJsonLogging"));
    private static final FieldDescriptor zzn = a.q(13, FieldDescriptor.builder("buildLevel"));
    private static final FieldDescriptor zzo = a.q(14, FieldDescriptor.builder("optionalModuleVersion"));

    private zzgu() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzla zzla = (zzla) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzla.zzg());
        objectEncoderContext.add(zzc, (Object) zzla.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzla.zzj());
        objectEncoderContext.add(zzf, (Object) zzla.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzla.zza());
        objectEncoderContext.add(zzj, (Object) zzla.zzi());
        objectEncoderContext.add(zzk, (Object) zzla.zzb());
        objectEncoderContext.add(zzl, (Object) zzla.zzd());
        objectEncoderContext.add(zzm, (Object) zzla.zzc());
        objectEncoderContext.add(zzn, (Object) zzla.zze());
        objectEncoderContext.add(zzo, (Object) zzla.zzf());
    }
}
