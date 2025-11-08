package com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv;

import androidx.collection.SieveCacheKt;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;
import java.util.ArrayDeque;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class DefaultEbmlReader implements EbmlReader {
    private static final int ELEMENT_STATE_READ_CONTENT = 2;
    private static final int ELEMENT_STATE_READ_CONTENT_SIZE = 1;
    private static final int ELEMENT_STATE_READ_ID = 0;
    private static final int MAX_ID_BYTES = 4;
    private static final int MAX_INTEGER_ELEMENT_SIZE_BYTES = 8;
    private static final int MAX_LENGTH_BYTES = 8;
    private static final int VALID_FLOAT32_ELEMENT_SIZE_BYTES = 4;
    private static final int VALID_FLOAT64_ELEMENT_SIZE_BYTES = 8;
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final ArrayDeque<MasterElement> masterElementsStack = new ArrayDeque<>();
    private EbmlProcessor processor;
    private final byte[] scratch = new byte[8];
    private final VarintReader varintReader = new VarintReader();

    public static final class MasterElement {
        /* access modifiers changed from: private */
        public final long elementEndPosition;
        /* access modifiers changed from: private */
        public final int elementId;

        private MasterElement(int i3, long j2) {
            this.elementId = i3;
            this.elementEndPosition = j2;
        }
    }

    @RequiresNonNull({"processor"})
    private long maybeResyncToNextLevel1Element(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        while (true) {
            extractorInput.peekFully(this.scratch, 0, 4);
            int parseUnsignedVarintLength = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if (parseUnsignedVarintLength != -1 && parseUnsignedVarintLength <= 4) {
                int assembleVarint = (int) VarintReader.assembleVarint(this.scratch, parseUnsignedVarintLength, false);
                if (this.processor.isLevel1Element(assembleVarint)) {
                    extractorInput.skipFully(parseUnsignedVarintLength);
                    return (long) assembleVarint;
                }
            }
            extractorInput.skipFully(1);
        }
    }

    private double readFloat(ExtractorInput extractorInput, int i3) throws IOException {
        long readInteger = readInteger(extractorInput, i3);
        return i3 == 4 ? (double) Float.intBitsToFloat((int) readInteger) : Double.longBitsToDouble(readInteger);
    }

    private long readInteger(ExtractorInput extractorInput, int i3) throws IOException {
        extractorInput.readFully(this.scratch, 0, i3);
        long j2 = 0;
        for (int i4 = 0; i4 < i3; i4++) {
            j2 = (j2 << 8) | ((long) (this.scratch[i4] & 255));
        }
        return j2;
    }

    private static String readString(ExtractorInput extractorInput, int i3) throws IOException {
        if (i3 == 0) {
            return "";
        }
        byte[] bArr = new byte[i3];
        extractorInput.readFully(bArr, 0, i3);
        while (i3 > 0 && bArr[i3 - 1] == 0) {
            i3--;
        }
        return new String(bArr, 0, i3);
    }

    public void init(EbmlProcessor ebmlProcessor) {
        this.processor = ebmlProcessor;
    }

    public boolean read(ExtractorInput extractorInput) throws IOException {
        Assertions.checkStateNotNull(this.processor);
        while (true) {
            MasterElement peek = this.masterElementsStack.peek();
            if (peek == null || extractorInput.getPosition() < peek.elementEndPosition) {
                if (this.elementState == 0) {
                    long readUnsignedVarint = this.varintReader.readUnsignedVarint(extractorInput, true, false, 4);
                    if (readUnsignedVarint == -2) {
                        readUnsignedVarint = maybeResyncToNextLevel1Element(extractorInput);
                    }
                    if (readUnsignedVarint == -1) {
                        return false;
                    }
                    this.elementId = (int) readUnsignedVarint;
                    this.elementState = 1;
                }
                if (this.elementState == 1) {
                    this.elementContentSize = this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                    this.elementState = 2;
                }
                int elementType = this.processor.getElementType(this.elementId);
                if (elementType == 0) {
                    extractorInput.skipFully((int) this.elementContentSize);
                    this.elementState = 0;
                } else if (elementType == 1) {
                    long position = extractorInput.getPosition();
                    this.masterElementsStack.push(new MasterElement(this.elementId, this.elementContentSize + position));
                    this.processor.startMasterElement(this.elementId, position, this.elementContentSize);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 2) {
                    long j2 = this.elementContentSize;
                    if (j2 <= 8) {
                        this.processor.integerElement(this.elementId, readInteger(extractorInput, (int) j2));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("Invalid integer size: " + this.elementContentSize, (Throwable) null);
                } else if (elementType == 3) {
                    long j3 = this.elementContentSize;
                    if (j3 <= SieveCacheKt.NodeLinkMask) {
                        this.processor.stringElement(this.elementId, readString(extractorInput, (int) j3));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("String element size: " + this.elementContentSize, (Throwable) null);
                } else if (elementType == 4) {
                    this.processor.binaryElement(this.elementId, (int) this.elementContentSize, extractorInput);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 5) {
                    long j4 = this.elementContentSize;
                    if (j4 == 4 || j4 == 8) {
                        this.processor.floatElement(this.elementId, readFloat(extractorInput, (int) j4));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("Invalid float size: " + this.elementContentSize, (Throwable) null);
                } else {
                    throw ParserException.createForMalformedContainer("Invalid element type " + elementType, (Throwable) null);
                }
            } else {
                this.processor.endMasterElement(this.masterElementsStack.pop().elementId);
                return true;
            }
        }
    }

    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}
