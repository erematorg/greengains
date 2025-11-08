package com.google.zxing.oned.rss;

import android.support.v4.media.session.a;

public class DataCharacter {
    private final int checksumPortion;
    private final int value;

    public DataCharacter(int i3, int i4) {
        this.value = i3;
        this.checksumPortion = i4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DataCharacter)) {
            return false;
        }
        DataCharacter dataCharacter = (DataCharacter) obj;
        return this.value == dataCharacter.value && this.checksumPortion == dataCharacter.checksumPortion;
    }

    public final int getChecksumPortion() {
        return this.checksumPortion;
    }

    public final int getValue() {
        return this.value;
    }

    public final int hashCode() {
        return this.checksumPortion ^ this.value;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        sb.append("(");
        return a.p(sb, this.checksumPortion, ')');
    }
}
