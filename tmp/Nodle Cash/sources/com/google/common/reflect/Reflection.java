package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@ElementTypesAreNonnullByDefault
public final class Reflection {
    private Reflection() {
    }

    public static String getPackageName(Class<?> cls) {
        return getPackageName(cls.getName());
    }

    public static void initialize(Class<?>... clsArr) {
        int length = clsArr.length;
        int i3 = 0;
        while (i3 < length) {
            Class<?> cls = clsArr[i3];
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                i3++;
            } catch (ClassNotFoundException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    public static <T> T newProxy(Class<T> cls, InvocationHandler invocationHandler) {
        Preconditions.checkNotNull(invocationHandler);
        Preconditions.checkArgument(cls.isInterface(), "%s is not an interface", (Object) cls);
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    public static String getPackageName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? "" : str.substring(0, lastIndexOf);
    }
}
