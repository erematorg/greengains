package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

final class zzit implements ObjectEncoder {
    static final zzit zza = new zzit();

    static {
        a.j(2, a.f(1, FieldDescriptor.builder("confidence"), RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE));
    }

    private zzit() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpi zzpi = (zzpi) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
