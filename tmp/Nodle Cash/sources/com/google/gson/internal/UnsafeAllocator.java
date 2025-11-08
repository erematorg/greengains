package com.google.gson.internal;

import androidx.constraintlayout.core.state.b;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator {
    public static final UnsafeAllocator INSTANCE = create();

    /* access modifiers changed from: private */
    public static void assertInstantiable(Class<?> cls) {
        String checkInstantiable = ConstructorConstructor.checkInstantiable(cls);
        if (checkInstantiable != null) {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(checkInstantiable));
        }
    }

    private static UnsafeAllocator create() {
        Class<ObjectStreamClass> cls = ObjectStreamClass.class;
        Class<Class> cls2 = Class.class;
        try {
            Class<?> cls3 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls3.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get((Object) null);
            final Method method = cls3.getMethod("allocateInstance", new Class[]{cls2});
            return new UnsafeAllocator() {
                public <T> T newInstance(Class<T> cls) throws Exception {
                    UnsafeAllocator.assertInstantiable(cls);
                    return method.invoke(obj, new Object[]{cls});
                }
            };
        } catch (Exception unused) {
            try {
                Method declaredMethod = cls.getDeclaredMethod("getConstructorId", new Class[]{cls2});
                declaredMethod.setAccessible(true);
                final int intValue = ((Integer) declaredMethod.invoke((Object) null, new Object[]{Object.class})).intValue();
                final Method declaredMethod2 = cls.getDeclaredMethod("newInstance", new Class[]{cls2, Integer.TYPE});
                declaredMethod2.setAccessible(true);
                return new UnsafeAllocator() {
                    public <T> T newInstance(Class<T> cls) throws Exception {
                        UnsafeAllocator.assertInstantiable(cls);
                        return declaredMethod2.invoke((Object) null, new Object[]{cls, Integer.valueOf(intValue)});
                    }
                };
            } catch (Exception unused2) {
                try {
                    final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{cls2, cls2});
                    declaredMethod3.setAccessible(true);
                    return new UnsafeAllocator() {
                        public <T> T newInstance(Class<T> cls) throws Exception {
                            UnsafeAllocator.assertInstantiable(cls);
                            return declaredMethod3.invoke((Object) null, new Object[]{cls, Object.class});
                        }
                    };
                } catch (Exception unused3) {
                    return new UnsafeAllocator() {
                        public <T> T newInstance(Class<T> cls) {
                            throw new UnsupportedOperationException(b.l("Cannot allocate ", cls, ". Usage of JDK sun.misc.Unsafe is enabled, but it could not be used. Make sure your runtime is configured correctly."));
                        }
                    };
                }
            }
        }
    }

    public abstract <T> T newInstance(Class<T> cls) throws Exception;
}
