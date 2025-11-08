package com.kenai.jffi;

import com.kenai.jffi.Closure;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public final class ClosureManager {
    private final Map<CallContext, Reference<ClosurePool>> poolMap;

    public static final class SingletonHolder {
        static final ClosureManager INSTANCE = new ClosureManager();

        private SingletonHolder() {
        }
    }

    public static ClosureManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public final synchronized ClosurePool getClosurePool(CallContext callContext) {
        ClosurePool closurePool;
        Reference reference = this.poolMap.get(callContext);
        if (reference != null && (closurePool = (ClosurePool) reference.get()) != null) {
            return closurePool;
        }
        Map<CallContext, Reference<ClosurePool>> map = this.poolMap;
        ClosurePool closurePool2 = new ClosurePool(callContext);
        map.put(callContext, new SoftReference(closurePool2));
        return closurePool2;
    }

    public final Closure.Handle newClosure(Closure closure, Type type, Type[] typeArr, CallingConvention callingConvention) {
        return newClosure(closure, CallContextCache.getInstance().getCallContext(type, typeArr, callingConvention));
    }

    public ClosureMagazine newClosureMagazine(CallContext callContext, Method method) {
        Foreign instance = Foreign.getInstance();
        boolean z2 = true;
        if (method.getParameterTypes().length >= 1) {
            if (Closure.Buffer.class.isAssignableFrom(method.getParameterTypes()[0])) {
                z2 = false;
            }
        }
        long newClosureMagazine = instance.newClosureMagazine(callContext.getAddress(), method, z2);
        if (newClosureMagazine != 0) {
            return new ClosureMagazine(instance, callContext, newClosureMagazine);
        }
        throw new RuntimeException("could not allocate new closure magazine");
    }

    private ClosureManager() {
        this.poolMap = new WeakHashMap();
    }

    public final Closure.Handle newClosure(Closure closure, CallContext callContext) {
        return getClosurePool(callContext).newClosureHandle(closure);
    }
}
