package com.appsamurai.storyly.exoplayer2.extractor.metadata.id3;

import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;

public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public Id3Frame(String str) {
        this.id = str;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.id;
    }
}
