package com.kenai.jffi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

final class Init {
    private static volatile boolean loaded = false;
    static final String stubLoaderClassName = (Init.class.getPackage().getName() + ".internal.StubLoader");

    private Init() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|(4:9|(2:13|20)|16|7)|17|14|15) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000c */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.ClassLoader> getClassLoaders() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ SecurityException -> 0x000c }
            r0.add(r1)     // Catch:{ SecurityException -> 0x000c }
        L_0x000c:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ SecurityException -> 0x0017 }
            java.lang.ClassLoader r1 = r1.getContextClassLoader()     // Catch:{ SecurityException -> 0x0017 }
            r0.add(r1)     // Catch:{ SecurityException -> 0x0017 }
        L_0x0017:
            java.lang.Class<com.kenai.jffi.Init> r1 = com.kenai.jffi.Init.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.add(r1)
            java.util.Iterator r1 = r0.iterator()
            r2 = 0
        L_0x0025:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x003a
            java.lang.Object r3 = r1.next()
            if (r3 != 0) goto L_0x0025
            int r2 = r2 + 1
            r3 = 1
            if (r2 <= r3) goto L_0x0025
            r1.remove()
            goto L_0x0025
        L_0x003a:
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Init.getClassLoaders():java.util.List");
    }

    public static void load() {
        if (!loaded) {
            ArrayList arrayList = new ArrayList();
            for (ClassLoader cls : getClassLoaders()) {
                try {
                    Class<?> cls2 = Class.forName(stubLoaderClassName, true, cls);
                    Method declaredMethod = cls2.getDeclaredMethod("isLoaded", (Class[]) null);
                    loaded = Boolean.class.cast(declaredMethod.invoke(cls2, (Object[]) null)).booleanValue() | loaded;
                    if (!loaded) {
                        throw Throwable.class.cast(cls2.getDeclaredMethod("getFailureCause", (Class[]) null).invoke(cls2, (Object[]) null));
                    }
                } catch (IllegalAccessException e3) {
                    arrayList.add(e3);
                } catch (InvocationTargetException e4) {
                    arrayList.add(e4);
                } catch (ClassNotFoundException e5) {
                    arrayList.add(e5);
                } catch (Throwable th) {
                    if (th instanceof UnsatisfiedLinkError) {
                        throw th;
                    }
                    throw newLoadError(th);
                }
            }
            if (!loaded && !arrayList.isEmpty()) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Throwable) it.next()).printStackTrace(printWriter);
                }
                throw new UnsatisfiedLinkError(stringWriter.toString());
            }
        }
    }

    private static UnsatisfiedLinkError newLoadError(Throwable th) {
        UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(th.getLocalizedMessage());
        unsatisfiedLinkError.initCause(th);
        return unsatisfiedLinkError;
    }
}
