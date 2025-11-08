package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import com.appsamurai.storyly.exoplayer2.common.ParserException;

public class UnrecognizedInputFormatException extends ParserException {
    public final Uri uri;

    public UnrecognizedInputFormatException(String str, Uri uri2) {
        super(str, (Throwable) null, false, 1);
        this.uri = uri2;
    }
}
