package com.google.android.gms.internal.mlkit_common;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhd implements ObjectEncoder {
    static final zzhd zza = new zzhd();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS), "filename"), "lineNumber"));
    }

    private zzhd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzns zzns = (zzns) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
