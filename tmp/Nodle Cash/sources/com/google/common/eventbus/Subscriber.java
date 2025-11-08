package com.google.common.eventbus;

import androidx.camera.view.f;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
class Subscriber {
    @Weak
    private EventBus bus;
    private final Executor executor;
    private final Method method;
    @VisibleForTesting
    final Object target;

    @VisibleForTesting
    public static final class SynchronizedSubscriber extends Subscriber {
        public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
            synchronized (this) {
                Subscriber.super.invokeSubscriberMethod(obj);
            }
        }

        private SynchronizedSubscriber(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method);
        }
    }

    private SubscriberExceptionContext context(Object obj) {
        return new SubscriberExceptionContext(this.bus, obj, this.target, this.method);
    }

    public static Subscriber create(EventBus eventBus, Object obj, Method method2) {
        return isDeclaredThreadSafe(method2) ? new Subscriber(eventBus, obj, method2) : new SynchronizedSubscriber(eventBus, obj, method2);
    }

    private static boolean isDeclaredThreadSafe(Method method2) {
        return method2.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchEvent$0(Object obj) {
        try {
            invokeSubscriberMethod(obj);
        } catch (InvocationTargetException e3) {
            this.bus.handleSubscriberException(e3.getCause(), context(obj));
        }
    }

    public final void dispatchEvent(Object obj) {
        this.executor.execute(new a(obj, this));
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        return this.target == subscriber.target && this.method.equals(subscriber.method);
    }

    public final int hashCode() {
        return System.identityHashCode(this.target) + ((this.method.hashCode() + 31) * 31);
    }

    @VisibleForTesting
    public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
        try {
            this.method.invoke(this.target, new Object[]{Preconditions.checkNotNull(obj)});
        } catch (IllegalArgumentException e3) {
            throw new Error(f.a(obj, "Method rejected target/argument: "), e3);
        } catch (IllegalAccessException e4) {
            throw new Error(f.a(obj, "Method became inaccessible: "), e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof Error) {
                throw ((Error) e5.getCause());
            }
            throw e5;
        }
    }

    private Subscriber(EventBus eventBus, Object obj, Method method2) {
        this.bus = eventBus;
        this.target = Preconditions.checkNotNull(obj);
        this.method = method2;
        method2.setAccessible(true);
        this.executor = eventBus.executor();
    }
}
