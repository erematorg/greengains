package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Throwables {
    @GwtIncompatible
    @J2ktIncompatible
    private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
    @GwtIncompatible
    @J2ktIncompatible
    @VisibleForTesting
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    /* access modifiers changed from: private */
    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    public static final Method getStackTraceDepthMethod;
    /* access modifiers changed from: private */
    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    public static final Method getStackTraceElementMethod;
    /* access modifiers changed from: private */
    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    public static final Object jla;

    static {
        Object jla2 = getJLA();
        jla = jla2;
        Method method = null;
        getStackTraceElementMethod = jla2 == null ? null : getGetMethod();
        if (jla2 != null) {
            method = getSizeMethod(jla2);
        }
        getStackTraceDepthMethod = method;
    }

    private Throwables() {
    }

    public static List<Throwable> getCausalChain(Throwable th) {
        Preconditions.checkNotNull(th);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(th);
        boolean z2 = false;
        Throwable th2 = th;
        while (true) {
            th = th.getCause();
            if (th == null) {
                return Collections.unmodifiableList(arrayList);
            }
            arrayList.add(th);
            if (th != th2) {
                if (z2) {
                    th2 = th2.getCause();
                }
                z2 = !z2;
            } else {
                throw new IllegalArgumentException("Loop in causal chain detected.", th);
            }
        }
    }

    @GwtIncompatible
    @CheckForNull
    @J2ktIncompatible
    public static <X extends Throwable> X getCauseAs(Throwable th, Class<X> cls) {
        try {
            return (Throwable) cls.cast(th.getCause());
        } catch (ClassCastException e3) {
            e3.initCause(th);
            throw e3;
        }
    }

    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    private static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }

    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    private static Object getJLA() {
        try {
            return Class.forName(SHARED_SECRETS_CLASSNAME, false, (ClassLoader) null).getMethod("getJavaLangAccess", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable unused) {
            return null;
        }
    }

    @GwtIncompatible
    @CheckForNull
    @J2ktIncompatible
    private static Method getJlaMethod(String str, Class<?>... clsArr) throws ThreadDeath {
        try {
            return Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, (ClassLoader) null).getMethod(str, clsArr);
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Throwable getRootCause(Throwable th) {
        boolean z2 = false;
        Throwable th2 = th;
        while (true) {
            Throwable cause = th.getCause();
            if (cause == null) {
                return th;
            }
            if (cause != th2) {
                if (z2) {
                    th2 = th2.getCause();
                }
                z2 = !z2;
                th = cause;
            } else {
                throw new IllegalArgumentException("Loop in causal chain detected.", cause);
            }
        }
    }

    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    private static Method getSizeMethod(Object obj) {
        try {
            Method jlaMethod = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (jlaMethod == null) {
                return null;
            }
            jlaMethod.invoke(obj, new Object[]{new Throwable()});
            return jlaMethod;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    @GwtIncompatible
    public static String getStackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    @J2ktIncompatible
    public static Object invokeAccessibleNonThrowingMethod(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw propagate(e4.getCause());
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    private static List<StackTraceElement> jlaStackTrace(final Throwable th) {
        Preconditions.checkNotNull(th);
        return new AbstractList<StackTraceElement>() {
            public int size() {
                Method access$300 = Throwables.getStackTraceDepthMethod;
                Objects.requireNonNull(access$300);
                Object access$100 = Throwables.jla;
                Objects.requireNonNull(access$100);
                return ((Integer) Throwables.invokeAccessibleNonThrowingMethod(access$300, access$100, th)).intValue();
            }

            public StackTraceElement get(int i3) {
                Method access$000 = Throwables.getStackTraceElementMethod;
                Objects.requireNonNull(access$000);
                Object access$100 = Throwables.jla;
                Objects.requireNonNull(access$100);
                return (StackTraceElement) Throwables.invokeAccessibleNonThrowingMethod(access$000, access$100, th, Integer.valueOf(i3));
            }
        };
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static List<StackTraceElement> lazyStackTrace(Throwable th) {
        return lazyStackTraceIsLazy() ? jlaStackTrace(th) : Collections.unmodifiableList(Arrays.asList(th.getStackTrace()));
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static boolean lazyStackTraceIsLazy() {
        return (getStackTraceElementMethod == null || getStackTraceDepthMethod == null) ? false : true;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Deprecated
    @J2ktIncompatible
    public static RuntimeException propagate(Throwable th) {
        throwIfUnchecked(th);
        throw new RuntimeException(th);
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static <X extends Throwable> void propagateIfInstanceOf(@CheckForNull Throwable th, Class<X> cls) throws Throwable {
        if (th != null) {
            throwIfInstanceOf(th, cls);
        }
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static void propagateIfPossible(@CheckForNull Throwable th) {
        if (th != null) {
            throwIfUnchecked(th);
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static <X extends Throwable> void throwIfInstanceOf(Throwable th, Class<X> cls) throws Throwable {
        Preconditions.checkNotNull(th);
        if (cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }

    public static void throwIfUnchecked(Throwable th) {
        Preconditions.checkNotNull(th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static <X extends Throwable> void propagateIfPossible(@CheckForNull Throwable th, Class<X> cls) throws Throwable {
        propagateIfInstanceOf(th, cls);
        propagateIfPossible(th);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(@CheckForNull Throwable th, Class<X1> cls, Class<X2> cls2) throws Throwable, Throwable {
        Preconditions.checkNotNull(cls2);
        propagateIfInstanceOf(th, cls);
        propagateIfPossible(th, cls2);
    }
}
