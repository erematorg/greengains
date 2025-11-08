package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.camera.view.f;
import androidx.constraintlayout.core.state.b;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class ManifestParser {
    private static final String GLIDE_MODULE_VALUE = "GlideModule";
    private static final String TAG = "ManifestParser";
    private final Context context;

    public ManifestParser(Context context2) {
        this.context = context2;
    }

    @Nullable
    private ApplicationInfo getOurApplicationInfo() throws PackageManager.NameNotFoundException {
        return this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
    }

    private static GlideModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            Object obj = null;
            try {
                obj = cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (InstantiationException e3) {
                throwInstantiateGlideModuleException(cls, e3);
            } catch (IllegalAccessException e4) {
                throwInstantiateGlideModuleException(cls, e4);
            } catch (NoSuchMethodException e5) {
                throwInstantiateGlideModuleException(cls, e5);
            } catch (InvocationTargetException e6) {
                throwInstantiateGlideModuleException(cls, e6);
            }
            if (obj instanceof GlideModule) {
                return (GlideModule) obj;
            }
            throw new RuntimeException(f.a(obj, "Expected instanceof GlideModule, but found: "));
        } catch (ClassNotFoundException e7) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e7);
        }
    }

    private static void throwInstantiateGlideModuleException(Class<?> cls, Exception exc) {
        throw new RuntimeException(b.k("Unable to instantiate GlideModule implementation for ", cls), exc);
    }

    public List<GlideModule> parse() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo ourApplicationInfo = getOurApplicationInfo();
            if (ourApplicationInfo != null) {
                if (ourApplicationInfo.metaData != null) {
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Got app info metadata: " + ourApplicationInfo.metaData);
                    }
                    for (String next : ourApplicationInfo.metaData.keySet()) {
                        if (GLIDE_MODULE_VALUE.equals(ourApplicationInfo.metaData.get(next))) {
                            arrayList.add(parseModule(next));
                            if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "Loaded Glide module: " + next);
                            }
                        }
                    }
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Finished loading Glide modules");
                    }
                    return arrayList;
                }
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Got null app info metadata");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e3) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Failed to parse glide modules", e3);
            }
        }
    }
}
