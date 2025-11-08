package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class SimpleTimeLimiter implements TimeLimiter {
    private final ExecutorService executor;

    private SimpleTimeLimiter(ExecutorService executorService) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    /* access modifiers changed from: private */
    @ParametricNullness
    public <T> T callWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit, boolean z2) throws Exception {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        if (!z2) {
            return Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        }
        try {
            return submit.get(j2, timeUnit);
        } catch (InterruptedException e3) {
            submit.cancel(true);
            throw e3;
        } catch (ExecutionException e4) {
            throw throwCause(e4, true);
        } catch (TimeoutException e5) {
            submit.cancel(true);
            throw new UncheckedTimeoutException((Throwable) e5);
        }
    }

    private static void checkPositiveTimeout(long j2) {
        Preconditions.checkArgument(j2 > 0, "timeout must be positive: %s", j2);
    }

    public static SimpleTimeLimiter create(ExecutorService executorService) {
        return new SimpleTimeLimiter(executorService);
    }

    private static boolean declaresInterruptedEx(Method method) {
        for (Class<InterruptedException> cls : method.getExceptionTypes()) {
            if (cls == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    private static Set<Method> findInterruptibleMethods(Class<?> cls) {
        HashSet newHashSet = Sets.newHashSet();
        for (Method method : cls.getMethods()) {
            if (declaresInterruptedEx(method)) {
                newHashSet.add(method);
            }
        }
        return newHashSet;
    }

    /* access modifiers changed from: private */
    public static Exception throwCause(Exception exc, boolean z2) throws Exception {
        Throwable cause = exc.getCause();
        if (cause != null) {
            if (z2) {
                cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), exc.getStackTrace(), StackTraceElement.class));
            }
            if (cause instanceof Exception) {
                throw ((Exception) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw exc;
            }
        } else {
            throw exc;
        }
    }

    private void wrapAndThrowExecutionExceptionOrError(Throwable th) throws ExecutionException {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        } else if (th instanceof RuntimeException) {
            throw new UncheckedExecutionException(th);
        } else {
            throw new ExecutionException(th);
        }
    }

    private void wrapAndThrowRuntimeExecutionExceptionOrError(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws TimeoutException, ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        try {
            return Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        } catch (TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        } catch (ExecutionException e4) {
            wrapAndThrowExecutionExceptionOrError(e4.getCause());
            throw new AssertionError();
        }
    }

    public <T> T newProxy(T t2, Class<T> cls, long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(t2);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Preconditions.checkArgument(cls.isInterface(), "interfaceType must be an interface type");
        final Set<Method> findInterruptibleMethods = findInterruptibleMethods(cls);
        final T t3 = t2;
        final long j3 = j2;
        final TimeUnit timeUnit2 = timeUnit;
        return newProxy(cls, new InvocationHandler() {
            /* access modifiers changed from: private */
            public static /* synthetic */ Object lambda$invoke$0(Method method, Object obj, Object[] objArr) throws Exception {
                try {
                    return method.invoke(obj, objArr);
                } catch (InvocationTargetException e3) {
                    throw SimpleTimeLimiter.throwCause(e3, false);
                }
            }

            @CheckForNull
            public Object invoke(Object obj, Method method, @CheckForNull Object[] objArr) throws Throwable {
                return SimpleTimeLimiter.this.callWithTimeout(new n(method, t3, objArr), j3, timeUnit2, findInterruptibleMethods.contains(method));
            }
        });
    }

    public void runUninterruptiblyWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) throws TimeoutException {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<?> submit = this.executor.submit(runnable);
        try {
            Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        } catch (TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        } catch (ExecutionException e4) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e4.getCause());
            throw new AssertionError();
        }
    }

    public void runWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<?> submit = this.executor.submit(runnable);
        try {
            submit.get(j2, timeUnit);
        } catch (InterruptedException | TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        } catch (ExecutionException e4) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e4.getCause());
            throw new AssertionError();
        }
    }

    private static <T> T newProxy(Class<T> cls, InvocationHandler invocationHandler) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws TimeoutException, InterruptedException, ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        try {
            return submit.get(j2, timeUnit);
        } catch (InterruptedException | TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        } catch (ExecutionException e4) {
            wrapAndThrowExecutionExceptionOrError(e4.getCause());
            throw new AssertionError();
        }
    }
}
