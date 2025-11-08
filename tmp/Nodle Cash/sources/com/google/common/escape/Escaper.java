package com.google.common.escape;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.errorprone.annotations.DoNotMock;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use Escapers.nullEscaper() or another methods from the *Escapers classes")
public abstract class Escaper {
    private final Function<String, String> asFunction = new a(this, 1);

    public final Function<String, String> asFunction() {
        return this.asFunction;
    }

    public abstract String escape(String str);
}
