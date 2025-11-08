package com.appsamurai.storyly.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import org.jetbrains.annotations.NotNull;

public final class h {
    @NotNull
    public static final SimpleDateFormat a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }
}
