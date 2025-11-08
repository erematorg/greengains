package com.google.thirdparty.publicsuffix;

import androidx.compose.animation.core.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@Beta
public enum PublicSuffixType {
    PRIVATE(AbstractJsonLexerKt.COLON, AbstractJsonLexerKt.COMMA),
    REGISTRY('!', '?');
    
    private final char innerNodeCode;
    private final char leafNodeCode;

    private PublicSuffixType(char c3, char c4) {
        this.innerNodeCode = c3;
        this.leafNodeCode = c4;
    }

    public static PublicSuffixType fromCode(char c3) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c3 || publicSuffixType.getLeafNodeCode() == c3) {
                return publicSuffixType;
            }
        }
        throw new IllegalArgumentException(a.p("No enum corresponding to given code: ", c3));
    }

    public char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    public char getLeafNodeCode() {
        return this.leafNodeCode;
    }
}
