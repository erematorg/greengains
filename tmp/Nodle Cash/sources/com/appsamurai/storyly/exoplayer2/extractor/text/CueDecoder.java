package com.appsamurai.storyly.exoplayer2.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

public final class CueDecoder {
    static final String BUNDLED_CUES = "c";

    public ImmutableList<Cue> decode(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        Bundle readBundle = obtain.readBundle(Bundle.class.getClassLoader());
        obtain.recycle();
        return BundleableUtil.fromBundleList(Cue.CREATOR, (ArrayList) Assertions.checkNotNull(readBundle.getParcelableArrayList("c")));
    }
}
