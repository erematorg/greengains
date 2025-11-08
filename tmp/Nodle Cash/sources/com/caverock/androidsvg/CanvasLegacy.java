package com.caverock.androidsvg;

import android.graphics.Canvas;
import java.lang.reflect.Method;

class CanvasLegacy {
    static final int MATRIX_SAVE_FLAG;
    private static final Method SAVE;

    static {
        Class<Canvas> cls = Canvas.class;
        try {
            MATRIX_SAVE_FLAG = ((Integer) cls.getField("MATRIX_SAVE_FLAG").get((Object) null)).intValue();
            SAVE = cls.getMethod("save", new Class[]{Integer.TYPE});
        } catch (Throwable th) {
            throw sneakyThrow(th);
        }
    }

    public static void save(Canvas canvas, int i3) {
        try {
            SAVE.invoke(canvas, new Object[]{Integer.valueOf(i3)});
        } catch (Throwable th) {
            throw sneakyThrow(th);
        }
    }

    private static RuntimeException sneakyThrow(Throwable th) {
        if (th != null) {
            return (RuntimeException) sneakyThrow0(th);
        }
        throw new NullPointerException("t");
    }

    private static <T extends Throwable> T sneakyThrow0(Throwable th) throws Throwable {
        throw th;
    }
}
