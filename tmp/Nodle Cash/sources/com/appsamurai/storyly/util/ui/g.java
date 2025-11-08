package com.appsamurai.storyly.util.ui;

import C0.d;
import I0.a;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.NotificationCompat;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class g extends AppCompatEditText {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Function0<Boolean> f6386a;

    /* renamed from: b  reason: collision with root package name */
    public boolean f6387b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f6388c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setOnFocusChangeListener(new a(this, 0));
    }

    public static final void a(g gVar, View view, boolean z2) {
        Intrinsics.checkNotNullParameter(gVar, "this$0");
        gVar.f6387b = z2;
        gVar.post(new d(gVar, 2));
    }

    private final InputMethodManager getInputMethodManager() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        Activity a2 = com.appsamurai.storyly.util.g.a(context);
        Object systemService = a2 == null ? null : a2.getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            return (InputMethodManager) systemService;
        }
        return null;
    }

    public final boolean getHasFocused() {
        return this.f6387b;
    }

    @Nullable
    public final Function0<Boolean> getOnBackPressed() {
        return this.f6386a;
    }

    public boolean onKeyPreIme(int i3, @NotNull KeyEvent keyEvent) {
        Boolean invoke;
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i3 != 4 || keyEvent.getAction() != 1) {
            return dispatchKeyEvent(keyEvent);
        }
        Function0<Boolean> function0 = this.f6386a;
        if (function0 == null || (invoke = function0.invoke()) == null) {
            return false;
        }
        return invoke.booleanValue();
    }

    public final void setOnBackPressed(@Nullable Function0<Boolean> function0) {
        this.f6386a = function0;
    }

    public static final void a(g gVar) {
        Intrinsics.checkNotNullParameter(gVar, "this$0");
        if (gVar.getHasFocused()) {
            if (!gVar.f6388c) {
                gVar.f6388c = true;
                InputMethodManager inputMethodManager = gVar.getInputMethodManager();
                if (inputMethodManager != null) {
                    inputMethodManager.toggleSoftInput(1, 0);
                }
            }
        } else if (gVar.f6388c) {
            gVar.f6388c = false;
            InputMethodManager inputMethodManager2 = gVar.getInputMethodManager();
            if (inputMethodManager2 != null) {
                inputMethodManager2.toggleSoftInput(0, 1);
            }
        }
    }
}
