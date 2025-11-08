package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;

public interface Bundleable {

    public interface Creator<T extends Bundleable> {
        T fromBundle(Bundle bundle);
    }

    Bundle toBundle();
}
