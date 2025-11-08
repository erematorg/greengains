package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import java.io.EOFException;
import java.io.IOException;
import org.checkerframework.dataflow.qual.Pure;

public final class ExtractorUtil {
    private ExtractorUtil() {
    }

    @Pure
    public static void checkContainerInput(boolean z2, @Nullable String str) throws ParserException {
        if (!z2) {
            throw ParserException.createForMalformedContainer(str, (Throwable) null);
        }
    }

    public static boolean peekFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i3, int i4, boolean z2) throws IOException {
        try {
            return extractorInput.peekFully(bArr, i3, i4, z2);
        } catch (EOFException e3) {
            if (z2) {
                return false;
            }
            throw e3;
        }
    }

    public static int peekToLength(ExtractorInput extractorInput, byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        while (i5 < i4) {
            int peek = extractorInput.peek(bArr, i3 + i5, i4 - i5);
            if (peek == -1) {
                break;
            }
            i5 += peek;
        }
        return i5;
    }

    public static boolean readFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i3, int i4) throws IOException {
        try {
            extractorInput.readFully(bArr, i3, i4);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean skipFullyQuietly(ExtractorInput extractorInput, int i3) throws IOException {
        try {
            extractorInput.skipFully(i3);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
