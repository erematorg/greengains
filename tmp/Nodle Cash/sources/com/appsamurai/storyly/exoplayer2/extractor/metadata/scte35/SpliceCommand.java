package com.appsamurai.storyly.exoplayer2.extractor.metadata.scte35;

import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;

public abstract class SpliceCommand implements Metadata.Entry {
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "SCTE-35 splice command: type=".concat(getClass().getSimpleName());
    }
}
