package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public final class DrawableDecoderCompat {
    private static volatile boolean shouldCallAppCompatResources = true;

    private DrawableDecoderCompat() {
    }

    public static Drawable getDrawable(Context context, Context context2, @DrawableRes int i3) {
        return getDrawable(context, context2, i3, (Resources.Theme) null);
    }

    private static Drawable loadDrawableV4(Context context, @DrawableRes int i3, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), i3, theme);
    }

    private static Drawable loadDrawableV7(Context context, @DrawableRes int i3, @Nullable Resources.Theme theme) {
        if (theme != null) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, theme);
            contextThemeWrapper.applyOverrideConfiguration(theme.getResources().getConfiguration());
            context = contextThemeWrapper;
        }
        return AppCompatResources.getDrawable(context, i3);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int i3, @Nullable Resources.Theme theme) {
        return getDrawable(context, context, i3, theme);
    }

    private static Drawable getDrawable(Context context, Context context2, @DrawableRes int i3, @Nullable Resources.Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return loadDrawableV7(context2, i3, theme);
            }
        } catch (NoClassDefFoundError unused) {
            shouldCallAppCompatResources = false;
        } catch (IllegalStateException e3) {
            if (!context.getPackageName().equals(context2.getPackageName())) {
                return ContextCompat.getDrawable(context2, i3);
            }
            throw e3;
        } catch (Resources.NotFoundException unused2) {
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return loadDrawableV4(context2, i3, theme);
    }
}
