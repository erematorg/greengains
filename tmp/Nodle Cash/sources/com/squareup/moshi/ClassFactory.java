package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class ClassFactory<T> {
    public static <T> ClassFactory<T> get(final Class<?> cls) {
        Class<ObjectStreamClass> cls2 = ObjectStreamClass.class;
        Class<Class> cls3 = Class.class;
        try {
            final Constructor<?> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
            declaredConstructor.setAccessible(true);
            return new ClassFactory<T>() {
                public T newInstance() throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return declaredConstructor.newInstance((Object[]) null);
                }

                public String toString() {
                    return cls.getName();
                }
            };
        } catch (NoSuchMethodException unused) {
            try {
                Class<?> cls4 = Class.forName("sun.misc.Unsafe");
                Field declaredField = cls4.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                final Object obj = declaredField.get((Object) null);
                final Method method = cls4.getMethod("allocateInstance", new Class[]{cls3});
                return new ClassFactory<T>() {
                    public T newInstance() throws InvocationTargetException, IllegalAccessException {
                        return method.invoke(obj, new Object[]{cls});
                    }

                    public String toString() {
                        return cls.getName();
                    }
                };
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException unused3) {
                try {
                    Method declaredMethod = cls2.getDeclaredMethod("getConstructorId", new Class[]{cls3});
                    declaredMethod.setAccessible(true);
                    final int intValue = ((Integer) declaredMethod.invoke((Object) null, new Object[]{Object.class})).intValue();
                    final Method declaredMethod2 = cls2.getDeclaredMethod("newInstance", new Class[]{cls3, Integer.TYPE});
                    declaredMethod2.setAccessible(true);
                    return new ClassFactory<T>() {
                        public T newInstance() throws InvocationTargetException, IllegalAccessException {
                            return declaredMethod2.invoke((Object) null, new Object[]{cls, Integer.valueOf(intValue)});
                        }

                        public String toString() {
                            return cls.getName();
                        }
                    };
                } catch (IllegalAccessException unused4) {
                    throw new AssertionError();
                } catch (InvocationTargetException e3) {
                    throw Util.rethrowCause(e3);
                } catch (NoSuchMethodException unused5) {
                    try {
                        final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{cls3, cls3});
                        declaredMethod3.setAccessible(true);
                        return new ClassFactory<T>() {
                            public T newInstance() throws InvocationTargetException, IllegalAccessException {
                                return declaredMethod3.invoke((Object) null, new Object[]{cls, Object.class});
                            }

                            public String toString() {
                                return cls.getName();
                            }
                        };
                    } catch (Exception unused6) {
                        throw new IllegalArgumentException("cannot construct instances of ".concat(cls.getName()));
                    }
                }
            }
        }
    }

    public abstract T newInstance() throws InvocationTargetException, IllegalAccessException, InstantiationException;
}
