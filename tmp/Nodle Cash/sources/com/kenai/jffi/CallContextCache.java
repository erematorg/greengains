package com.kenai.jffi;

import androidx.constraintlayout.core.state.b;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallContextCache {
    private final Map<Signature, CallContextRef> contextCache;
    private final ReferenceQueue<CallContext> contextReferenceQueue;

    public static final class CallContextRef extends SoftReference<CallContext> {
        final Signature signature;

        public CallContextRef(Signature signature2, CallContext callContext, ReferenceQueue<CallContext> referenceQueue) {
            super(callContext, referenceQueue);
            this.signature = signature2;
        }
    }

    public static final class Signature {
        private final CallingConvention convention;
        private final boolean faultProtect;
        private int hashCode = 0;
        private final Type[] parameterTypes;
        private final Type returnType;
        private final boolean saveErrno;

        public Signature(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2, boolean z3) {
            if (type == null || typeArr == null) {
                throw new NullPointerException("null return type or parameter types array");
            }
            this.returnType = type;
            this.parameterTypes = typeArr;
            this.convention = callingConvention;
            this.saveErrno = z2;
            this.faultProtect = z3;
        }

        private final int calculateHashCode() {
            Type type = this.returnType;
            int i3 = 0;
            int hashCode2 = 371 + (type != null ? type.hashCode() : 0);
            int i4 = 1;
            while (true) {
                Type[] typeArr = this.parameterTypes;
                if (i3 < typeArr.length) {
                    i4 = (i4 * 31) + typeArr[i3].hashCode();
                    i3++;
                } else {
                    return ((((this.convention.hashCode() + b.A(hashCode2, 53, i4, 53)) * 53) + (this.saveErrno ? 1 : 0)) * 53) + (this.faultProtect ? 1 : 0);
                }
            }
        }

        public boolean equals(Object obj) {
            Type type;
            Type type2;
            if (obj != null && Signature.class == obj.getClass()) {
                Signature signature = (Signature) obj;
                if (this.convention == signature.convention && this.saveErrno == signature.saveErrno && this.faultProtect == signature.faultProtect && (((type = this.returnType) == (type2 = signature.returnType) || type.equals(type2)) && this.parameterTypes.length == signature.parameterTypes.length)) {
                    int i3 = 0;
                    while (true) {
                        Type[] typeArr = this.parameterTypes;
                        if (i3 >= typeArr.length) {
                            return true;
                        }
                        Type type3 = typeArr[i3];
                        Type type4 = signature.parameterTypes[i3];
                        if (type3 == type4 || (type3 != null && type3.equals(type4))) {
                            i3++;
                        }
                    }
                    return false;
                }
            }
            return false;
        }

        public int hashCode() {
            int i3 = this.hashCode;
            if (i3 != 0) {
                return i3;
            }
            int calculateHashCode = calculateHashCode();
            this.hashCode = calculateHashCode;
            return calculateHashCode;
        }
    }

    public static final class SingletonHolder {
        static final CallContextCache INSTANCE = new CallContextCache();

        private SingletonHolder() {
        }
    }

    public static CallContextCache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public final CallContext getCallContext(Type type, Type[] typeArr, CallingConvention callingConvention) {
        return getCallContext(type, typeArr, callingConvention, true, false);
    }

    private CallContextCache() {
        this.contextCache = new ConcurrentHashMap();
        this.contextReferenceQueue = new ReferenceQueue<>();
    }

    public final CallContext getCallContext(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        return getCallContext(type, typeArr, callingConvention, z2, false);
    }

    public final CallContext getCallContext(Type type, int i3, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        return getCallContext(type, i3, typeArr, callingConvention, z2, false);
    }

    public final CallContext getCallContext(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2, boolean z3) {
        return getCallContext(type, typeArr.length, typeArr, callingConvention, z2, z3);
    }

    public final CallContext getCallContext(Type type, int i3, Type[] typeArr, CallingConvention callingConvention, boolean z2, boolean z3) {
        CallContext callContext;
        Signature signature = new Signature(type, typeArr, callingConvention, z2, z3);
        CallContextRef callContextRef = this.contextCache.get(signature);
        if (callContextRef != null && (callContext = (CallContext) callContextRef.get()) != null) {
            return callContext;
        }
        while (true) {
            CallContextRef callContextRef2 = (CallContextRef) this.contextReferenceQueue.poll();
            if (callContextRef2 != null) {
                this.contextCache.remove(callContextRef2.signature);
            } else {
                CallContext callContext2 = new CallContext(type, i3, (Type[]) typeArr.clone(), callingConvention, z2, z3);
                this.contextCache.put(signature, new CallContextRef(signature, callContext2, this.contextReferenceQueue));
                return callContext2;
            }
        }
    }
}
