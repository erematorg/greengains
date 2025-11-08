package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhj implements ObjectEncoder {
    static final zzhj zza = new zzhj();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder("totalGroups"), "addedGroups"), "removedGroups"));
    }

    private zzhj() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzny zzny = (zzny) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
