package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzkz implements ObjectEncoder {
    static final zzkz zza = new zzkz();

    static {
        a.p(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("isChargingRequired"), "isWifiRequired"), "isDeviceIdleRequired"), "canDownloadInBackground"));
    }

    private zzkz() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzri zzri = (zzri) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
