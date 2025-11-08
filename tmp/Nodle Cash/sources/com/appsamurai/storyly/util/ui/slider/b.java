package com.appsamurai.storyly.util.ui.slider;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.emoji.text.EmojiCompat;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {
    @NotNull
    public static final d a(@NotNull Context context, @NotNull String str, float f2, @Nullable Float f3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "text");
        d dVar = new d(context, context.getResources().getDisplayMetrics().widthPixels);
        SpannableString spannableString = new SpannableString(EmojiCompat.get().process(str));
        Intrinsics.checkNotNullParameter(spannableString, "spannable");
        Spannable spannable = dVar.f6498c;
        if (spannable == null || !Intrinsics.areEqual((Object) spannable, (Object) spannableString)) {
            dVar.f6498c = spannableString;
            dVar.a();
            dVar.invalidateSelf();
        }
        dVar.f6497b.setTextSize(f2);
        dVar.a();
        dVar.invalidateSelf();
        if (f3 != null) {
            float floatValue = f3.floatValue();
            if (floatValue > -30.0f && floatValue < 30.0f) {
                dVar.f6505j = floatValue;
                dVar.a();
                dVar.invalidateSelf();
            }
        }
        return dVar;
    }
}
