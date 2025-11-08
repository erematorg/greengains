package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
