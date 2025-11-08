package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.state.b;
import com.google.android.gms.internal.measurement.zzkp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzla<T extends zzkp> {
    private static String zza = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    public static <T extends zzkp> T zza(Class<T> cls) {
        String str;
        Class<zzla> cls2 = zzla.class;
        ClassLoader classLoader = cls2.getClassLoader();
        if (cls.equals(zzkp.class)) {
            str = zza;
        } else if (cls.getPackage().equals(cls2.getPackage())) {
            str = b.m(cls.getPackage().getName(), ".BlazeGenerated", cls.getSimpleName(), "Loader");
        } else {
            throw new IllegalArgumentException(cls.getName());
        }
        try {
            return (zzkp) cls.cast(((zzla) Class.forName(str, true, classLoader).getConstructor((Class[]) null).newInstance((Object[]) null)).zza());
        } catch (NoSuchMethodException e3) {
            throw new IllegalStateException(e3);
        } catch (InstantiationException e4) {
            throw new IllegalStateException(e4);
        } catch (IllegalAccessException e5) {
            throw new IllegalStateException(e5);
        } catch (InvocationTargetException e6) {
            throw new IllegalStateException(e6);
        } catch (ClassNotFoundException unused) {
            Iterator<S> it = ServiceLoader.load(cls2, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zzkp) cls.cast(((zzla) it.next()).zza()));
                } catch (ServiceConfigurationError e7) {
                    Logger.getLogger(zzkl.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(cls.getSimpleName()), e7);
                }
            }
            if (arrayList.size() == 1) {
                return (zzkp) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zzkp) cls.getMethod("combine", new Class[]{Collection.class}).invoke((Object) null, new Object[]{arrayList});
            } catch (NoSuchMethodException e8) {
                throw new IllegalStateException(e8);
            } catch (IllegalAccessException e9) {
                throw new IllegalStateException(e9);
            } catch (InvocationTargetException e10) {
                throw new IllegalStateException(e10);
            }
        }
    }

    public abstract T zza();
}
