package com.google.zxing.oned.rss.expanded;

import androidx.camera.core.impl.i;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class ExpandedPair {
    private final FinderPattern finderPattern;
    private final DataCharacter leftChar;
    private final DataCharacter rightChar;

    public ExpandedPair(DataCharacter dataCharacter, DataCharacter dataCharacter2, FinderPattern finderPattern2) {
        this.leftChar = dataCharacter;
        this.rightChar = dataCharacter2;
        this.finderPattern = finderPattern2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedPair)) {
            return false;
        }
        ExpandedPair expandedPair = (ExpandedPair) obj;
        return Objects.equals(this.leftChar, expandedPair.leftChar) && Objects.equals(this.rightChar, expandedPair.rightChar) && Objects.equals(this.finderPattern, expandedPair.finderPattern);
    }

    public FinderPattern getFinderPattern() {
        return this.finderPattern;
    }

    public DataCharacter getLeftChar() {
        return this.leftChar;
    }

    public DataCharacter getRightChar() {
        return this.rightChar;
    }

    public int hashCode() {
        return Objects.hashCode(this.finderPattern) ^ (Objects.hashCode(this.leftChar) ^ Objects.hashCode(this.rightChar));
    }

    public boolean mustBeLast() {
        return this.rightChar == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.leftChar);
        sb.append(" , ");
        sb.append(this.rightChar);
        sb.append(" : ");
        FinderPattern finderPattern2 = this.finderPattern;
        return i.b(sb, finderPattern2 == null ? AbstractJsonLexerKt.NULL : Integer.valueOf(finderPattern2.getValue()), " ]");
    }
}
