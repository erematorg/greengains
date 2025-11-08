package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

final class zzkh implements ObjectEncoder {
    static final zzkh zza = new zzkh();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("firebaseProjectId"));
    private static final FieldDescriptor zze = a.g(4, FieldDescriptor.builder("mlSdkVersion"));
    private static final FieldDescriptor zzf = a.g(5, FieldDescriptor.builder("tfliteSchemaVersion"));
    private static final FieldDescriptor zzg = a.g(6, FieldDescriptor.builder("gcmSenderId"));
    private static final FieldDescriptor zzh = a.g(7, FieldDescriptor.builder("apiKey"));
    private static final FieldDescriptor zzi = a.g(8, FieldDescriptor.builder("languages"));
    private static final FieldDescriptor zzj = a.g(9, FieldDescriptor.builder("mlSdkInstanceId"));
    private static final FieldDescriptor zzk = a.g(10, FieldDescriptor.builder("isClearcutClient"));
    private static final FieldDescriptor zzl = a.g(11, FieldDescriptor.builder("isStandaloneMlkit"));
    private static final FieldDescriptor zzm = a.g(12, FieldDescriptor.builder("isJsonLogging"));
    private static final FieldDescriptor zzn = a.g(13, FieldDescriptor.builder("buildLevel"));
    private static final FieldDescriptor zzo = a.g(14, FieldDescriptor.builder("optionalModuleVersion"));

    private zzkh() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqv zzqv = (zzqv) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzqv.zzg());
        objectEncoderContext.add(zzc, (Object) zzqv.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzqv.zzj());
        objectEncoderContext.add(zzf, (Object) zzqv.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzqv.zza());
        objectEncoderContext.add(zzj, (Object) zzqv.zzi());
        objectEncoderContext.add(zzk, (Object) zzqv.zzb());
        objectEncoderContext.add(zzl, (Object) zzqv.zzd());
        objectEncoderContext.add(zzm, (Object) zzqv.zzc());
        objectEncoderContext.add(zzn, (Object) zzqv.zze());
        objectEncoderContext.add(zzo, (Object) zzqv.zzf());
    }
}
