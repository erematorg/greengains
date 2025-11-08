package com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv;

import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface EbmlProcessor {
    public static final int ELEMENT_TYPE_BINARY = 4;
    public static final int ELEMENT_TYPE_FLOAT = 5;
    public static final int ELEMENT_TYPE_MASTER = 1;
    public static final int ELEMENT_TYPE_STRING = 3;
    public static final int ELEMENT_TYPE_UNKNOWN = 0;
    public static final int ELEMENT_TYPE_UNSIGNED_INT = 2;

    @Documented
    @Target({java.lang.annotation.ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ElementType {
    }

    void binaryElement(int i3, int i4, ExtractorInput extractorInput) throws IOException;

    void endMasterElement(int i3) throws ParserException;

    void floatElement(int i3, double d2) throws ParserException;

    int getElementType(int i3);

    void integerElement(int i3, long j2) throws ParserException;

    boolean isLevel1Element(int i3);

    void startMasterElement(int i3, long j2, long j3) throws ParserException;

    void stringElement(int i3, String str) throws ParserException;
}
