package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

final class zzon implements ObjectEncoder {
    static final zzon zza = new zzon();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("firebaseProjectId"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("mlSdkVersion"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("tfliteSchemaVersion"));
    private static final FieldDescriptor zzg = a.o(6, FieldDescriptor.builder("gcmSenderId"));
    private static final FieldDescriptor zzh = a.o(7, FieldDescriptor.builder("apiKey"));
    private static final FieldDescriptor zzi = a.o(8, FieldDescriptor.builder("languages"));
    private static final FieldDescriptor zzj = a.o(9, FieldDescriptor.builder("mlSdkInstanceId"));
    private static final FieldDescriptor zzk = a.o(10, FieldDescriptor.builder("isClearcutClient"));
    private static final FieldDescriptor zzl = a.o(11, FieldDescriptor.builder("isStandaloneMlkit"));
    private static final FieldDescriptor zzm = a.o(12, FieldDescriptor.builder("isJsonLogging"));
    private static final FieldDescriptor zzn = a.o(13, FieldDescriptor.builder("buildLevel"));
    private static final FieldDescriptor zzo = a.o(14, FieldDescriptor.builder("optionalModuleVersion"));

    private zzon() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzvd zzvd = (zzvd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzvd.zzg());
        objectEncoderContext.add(zzc, (Object) zzvd.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzvd.zzj());
        objectEncoderContext.add(zzf, (Object) zzvd.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzvd.zza());
        objectEncoderContext.add(zzj, (Object) zzvd.zzi());
        objectEncoderContext.add(zzk, (Object) zzvd.zzb());
        objectEncoderContext.add(zzl, (Object) zzvd.zzd());
        objectEncoderContext.add(zzm, (Object) zzvd.zzc());
        objectEncoderContext.add(zzn, (Object) zzvd.zze());
        objectEncoderContext.add(zzo, (Object) zzvd.zzf());
    }
}
