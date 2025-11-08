package com.apollographql.apollo3.mpp;

import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/text/SimpleDateFormat;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class UtilsKt$simpleDateFormat$2 extends Lambda implements Function0<SimpleDateFormat> {
    public static final UtilsKt$simpleDateFormat$2 INSTANCE = new UtilsKt$simpleDateFormat$2();

    public UtilsKt$simpleDateFormat$2() {
        super(0);
    }

    @NotNull
    public final SimpleDateFormat invoke() {
        return new SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT);
    }
}
