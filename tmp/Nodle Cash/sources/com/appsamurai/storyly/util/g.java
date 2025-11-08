package com.appsamurai.storyly.util;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class g {
    @Nullable
    public static final Activity a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (!(context instanceof ContextWrapper)) {
            return null;
        }
        Context baseContext = ((ContextWrapper) context).getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "this.baseContext");
        return a(baseContext);
    }

    public static final boolean b(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("accessibility");
        T t2 = null;
        AccessibilityManager accessibilityManager = systemService instanceof AccessibilityManager ? (AccessibilityManager) systemService : null;
        if (accessibilityManager == null) {
            return false;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        if (enabledAccessibilityServiceList != null) {
            Iterator<T> it = enabledAccessibilityServiceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                T next = it.next();
                AccessibilityServiceInfo accessibilityServiceInfo = (AccessibilityServiceInfo) next;
                String settingsActivityName = accessibilityServiceInfo.getSettingsActivityName();
                if (!(settingsActivityName == null || settingsActivityName.length() == 0)) {
                    String settingsActivityName2 = accessibilityServiceInfo.getSettingsActivityName();
                    Intrinsics.checkNotNullExpressionValue(settingsActivityName2, "it.settingsActivityName");
                    if (StringsKt__StringsKt.contains$default((CharSequence) settingsActivityName2, (CharSequence) "TalkBackPreferencesActivity", false, 2, (Object) null)) {
                        t2 = next;
                        break;
                    }
                }
            }
            t2 = (AccessibilityServiceInfo) t2;
        }
        return t2 != null;
    }

    public static final void a(@NotNull Context context, @NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "label");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Object systemService = context.getSystemService("clipboard");
        if (systemService != null) {
            ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(str, charSequence));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.content.ClipboardManager");
    }
}
