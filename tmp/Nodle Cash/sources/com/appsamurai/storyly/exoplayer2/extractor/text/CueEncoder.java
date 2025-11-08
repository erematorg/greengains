package com.appsamurai.storyly.exoplayer2.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.appsamurai.storyly.util.ui.blur.c;
import java.util.ArrayList;
import java.util.List;

public final class CueEncoder {
    public byte[] encode(List<Cue> list) {
        ArrayList<Bundle> bundleArrayList = BundleableUtil.toBundleArrayList(list);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(c.f6372c, bundleArrayList);
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
