package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WindowUtils {
    /* access modifiers changed from: private */
    public static final String TAG = "WindowUtils";

    public static class Api14Impl {
        private Api14Impl() {
        }

        @NonNull
        public static Rect getCurrentWindowBounds(@NonNull WindowManager windowManager) {
            int i3;
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point realSizeForDisplay = getRealSizeForDisplay(defaultDisplay);
            Rect rect = new Rect();
            int i4 = realSizeForDisplay.x;
            if (i4 == 0 || (i3 = realSizeForDisplay.y) == 0) {
                defaultDisplay.getRectSize(rect);
            } else {
                rect.right = i4;
                rect.bottom = i3;
            }
            return rect;
        }

        private static Point getRealSizeForDisplay(Display display) {
            Point point = new Point();
            try {
                Method declaredMethod = Display.class.getDeclaredMethod("getRealSize", new Class[]{Point.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(display, new Object[]{point});
            } catch (NoSuchMethodException e3) {
                Log.w(WindowUtils.TAG, e3);
            } catch (IllegalAccessException e4) {
                Log.w(WindowUtils.TAG, e4);
            } catch (InvocationTargetException e5) {
                Log.w(WindowUtils.TAG, e5);
            }
            return point;
        }
    }

    @RequiresApi(api = 17)
    public static class Api17Impl {
        private Api17Impl() {
        }

        @NonNull
        public static Rect getCurrentWindowBounds(@NonNull WindowManager windowManager) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            Rect rect = new Rect();
            rect.right = point.x;
            rect.bottom = point.y;
            return rect;
        }
    }

    @RequiresApi(api = 30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        @NonNull
        public static Rect getCurrentWindowBounds(@NonNull WindowManager windowManager) {
            return windowManager.getCurrentWindowMetrics().getBounds();
        }
    }

    private WindowUtils() {
    }

    @NonNull
    public static Rect getCurrentWindowBounds(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        return Build.VERSION.SDK_INT >= 30 ? Api30Impl.getCurrentWindowBounds(windowManager) : Api17Impl.getCurrentWindowBounds(windowManager);
    }
}
