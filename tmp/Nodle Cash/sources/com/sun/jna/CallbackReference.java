package com.sun.jna;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.internal.Cleaner;
import com.sun.jna.win32.DLLCallback;
import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CallbackReference extends WeakReference<Callback> implements Closeable {
    private static final Class<?> DLL_CALLBACK_CLASS;
    private static final Method PROXY_CALLBACK_METHOD;
    /* access modifiers changed from: private */
    public static final Map<Long, Reference<CallbackReference>> allocatedMemory = new ConcurrentHashMap();
    static final Map<Object, Object> allocations = Collections.synchronizedMap(new WeakHashMap());
    static final Map<Callback, CallbackReference> callbackMap = new WeakHashMap();
    static final Map<Callback, CallbackReference> directCallbackMap = new WeakHashMap();
    private static final Map<Callback, CallbackThreadInitializer> initializers = new WeakHashMap();
    static final Map<Pointer, Reference<Callback>[]> pointerCallbackMap = new WeakHashMap();
    int callingConvention;
    Pointer cbstruct;
    Cleaner.Cleanable cleanable;
    Method method;
    CallbackProxy proxy;
    Pointer trampoline;

    public static class AttachOptions extends Structure {
        public static final List<String> FIELDS = Structure.createFieldsOrder("daemon", "detach", "name");
        public boolean daemon;
        public boolean detach;
        public String name;

        public AttachOptions() {
            setStringEncoding("utf8");
        }

        public List<String> getFieldOrder() {
            return FIELDS;
        }
    }

    public static final class CallbackReferenceDisposer implements Runnable {
        private Pointer cbstruct;

        public CallbackReferenceDisposer(Pointer pointer) {
            this.cbstruct = pointer;
        }

        public synchronized void run() {
            try {
                Pointer pointer = this.cbstruct;
                if (pointer != null) {
                    Native.freeNativeCallback(pointer.peer);
                    CallbackReference.allocatedMemory.remove(Long.valueOf(this.cbstruct.peer));
                    this.cbstruct.peer = 0;
                    this.cbstruct = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public class DefaultCallbackProxy implements CallbackProxy {
        private final Method callbackMethod;
        private final String encoding;
        private final FromNativeConverter[] fromNative;
        private ToNativeConverter toNative;

        public DefaultCallbackProxy(Method method, TypeMapper typeMapper, String str) {
            this.callbackMethod = method;
            this.encoding = str;
            Class[] parameterTypes = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            this.fromNative = new FromNativeConverter[parameterTypes.length];
            Class<NativeMapped> cls = NativeMapped.class;
            if (cls.isAssignableFrom(returnType)) {
                this.toNative = NativeMappedConverter.getInstance(returnType);
            } else if (typeMapper != null) {
                this.toNative = typeMapper.getToNativeConverter(returnType);
            }
            for (int i3 = 0; i3 < this.fromNative.length; i3++) {
                if (cls.isAssignableFrom(parameterTypes[i3])) {
                    this.fromNative[i3] = new NativeMappedConverter(parameterTypes[i3]);
                } else if (typeMapper != null) {
                    this.fromNative[i3] = typeMapper.getFromNativeConverter(parameterTypes[i3]);
                }
            }
            if (!method.isAccessible()) {
                try {
                    method.setAccessible(true);
                } catch (SecurityException unused) {
                    throw new IllegalArgumentException("Callback method is inaccessible, make sure the interface is public: " + method);
                }
            }
        }

        /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Class<?>, java.lang.Class] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object convertArgument(java.lang.Object r8, java.lang.Class<?> r9) {
            /*
                r7 = this;
                boolean r0 = r8 instanceof com.sun.jna.Pointer
                if (r0 == 0) goto L_0x0088
                java.lang.Class<java.lang.String> r0 = java.lang.String.class
                r1 = 0
                if (r9 != r0) goto L_0x0014
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                java.lang.String r7 = r7.encoding
                java.lang.String r8 = r8.getString(r1, r7)
                goto L_0x00a3
            L_0x0014:
                java.lang.Class<com.sun.jna.WString> r0 = com.sun.jna.WString.class
                if (r9 != r0) goto L_0x0026
                com.sun.jna.WString r7 = new com.sun.jna.WString
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                java.lang.String r8 = r8.getWideString(r1)
                r7.<init>(r8)
            L_0x0023:
                r8 = r7
                goto L_0x00a3
            L_0x0026:
                java.lang.Class<java.lang.String[]> r0 = java.lang.String[].class
                if (r9 != r0) goto L_0x0034
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                java.lang.String r7 = r7.encoding
                java.lang.String[] r8 = r8.getStringArray((long) r1, (java.lang.String) r7)
                goto L_0x00a3
            L_0x0034:
                java.lang.Class<com.sun.jna.WString[]> r7 = com.sun.jna.WString[].class
                if (r9 != r7) goto L_0x003f
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                java.lang.String[] r8 = r8.getWideStringArray(r1)
                goto L_0x00a3
            L_0x003f:
                java.lang.Class<com.sun.jna.Callback> r7 = com.sun.jna.Callback.class
                boolean r7 = r7.isAssignableFrom(r9)
                if (r7 == 0) goto L_0x004e
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                com.sun.jna.Callback r8 = com.sun.jna.CallbackReference.getCallback(r9, r8)
                goto L_0x00a3
            L_0x004e:
                java.lang.Class<com.sun.jna.Structure> r7 = com.sun.jna.Structure.class
                boolean r7 = r7.isAssignableFrom(r9)
                if (r7 == 0) goto L_0x00a3
                java.lang.Class<com.sun.jna.Structure$ByValue> r7 = com.sun.jna.Structure.ByValue.class
                boolean r7 = r7.isAssignableFrom(r9)
                if (r7 == 0) goto L_0x007e
                com.sun.jna.Structure r7 = com.sun.jna.Structure.newInstance(r9)
                int r9 = r7.size()
                byte[] r6 = new byte[r9]
                r0 = r8
                com.sun.jna.Pointer r0 = (com.sun.jna.Pointer) r0
                r1 = 0
                r4 = 0
                r3 = r6
                r5 = r9
                r0.read((long) r1, (byte[]) r3, (int) r4, (int) r5)
                com.sun.jna.Pointer r0 = r7.getPointer()
                r0.write((long) r1, (byte[]) r3, (int) r4, (int) r5)
                r7.read()
                goto L_0x0023
            L_0x007e:
                com.sun.jna.Pointer r8 = (com.sun.jna.Pointer) r8
                com.sun.jna.Structure r8 = com.sun.jna.Structure.newInstance(r9, (com.sun.jna.Pointer) r8)
                r8.conditionalAutoRead()
                goto L_0x00a3
            L_0x0088:
                java.lang.Class r7 = java.lang.Boolean.TYPE
                if (r7 == r9) goto L_0x0090
                java.lang.Class<java.lang.Boolean> r7 = java.lang.Boolean.class
                if (r7 != r9) goto L_0x00a3
            L_0x0090:
                boolean r7 = r8 instanceof java.lang.Number
                if (r7 == 0) goto L_0x00a3
                java.lang.Number r8 = (java.lang.Number) r8
                int r7 = r8.intValue()
                if (r7 == 0) goto L_0x009e
                r7 = 1
                goto L_0x009f
            L_0x009e:
                r7 = 0
            L_0x009f:
                java.lang.Boolean r8 = com.sun.jna.Function.valueOf(r7)
            L_0x00a3:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.CallbackReference.DefaultCallbackProxy.convertArgument(java.lang.Object, java.lang.Class):java.lang.Object");
        }

        private Object convertResult(Object obj) {
            ToNativeConverter toNativeConverter = this.toNative;
            if (toNativeConverter != null) {
                obj = toNativeConverter.toNative(obj, new CallbackResultContext(this.callbackMethod));
            }
            if (obj == null) {
                return null;
            }
            Class<?> cls = obj.getClass();
            if (Structure.class.isAssignableFrom(cls)) {
                return Structure.ByValue.class.isAssignableFrom(cls) ? obj : ((Structure) obj).getPointer();
            }
            if (cls == Boolean.TYPE || cls == Boolean.class) {
                return Boolean.TRUE.equals(obj) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            Class<WString> cls2 = WString.class;
            if (cls == String.class || cls == cls2) {
                return CallbackReference.getNativeString(obj, cls == cls2);
            }
            Class<String[]> cls3 = String[].class;
            if (cls != cls3 && cls != WString[].class) {
                return Callback.class.isAssignableFrom(cls) ? CallbackReference.getFunctionPointer((Callback) obj) : obj;
            }
            StringArray stringArray = cls == cls3 ? new StringArray((String[]) obj, this.encoding) : new StringArray((WString[]) obj);
            CallbackReference.allocations.put(obj, stringArray);
            return stringArray;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object invokeCallback(java.lang.Object[] r10) {
            /*
                r9 = this;
                java.lang.reflect.Method r0 = r9.callbackMethod
                java.lang.Class[] r0 = r0.getParameterTypes()
                int r1 = r10.length
                java.lang.Object[] r2 = new java.lang.Object[r1]
                r3 = 0
                r4 = r3
            L_0x000b:
                int r5 = r10.length
                if (r4 >= r5) goto L_0x0033
                r5 = r0[r4]
                r6 = r10[r4]
                com.sun.jna.FromNativeConverter[] r7 = r9.fromNative
                r7 = r7[r4]
                if (r7 == 0) goto L_0x002a
                com.sun.jna.CallbackParameterContext r7 = new com.sun.jna.CallbackParameterContext
                java.lang.reflect.Method r8 = r9.callbackMethod
                r7.<init>(r5, r8, r10, r4)
                com.sun.jna.FromNativeConverter[] r5 = r9.fromNative
                r5 = r5[r4]
                java.lang.Object r5 = r5.fromNative(r6, r7)
                r2[r4] = r5
                goto L_0x0030
            L_0x002a:
                java.lang.Object r5 = r9.convertArgument(r6, r5)
                r2[r4] = r5
            L_0x0030:
                int r4 = r4 + 1
                goto L_0x000b
            L_0x0033:
                com.sun.jna.Callback r10 = r9.getCallback()
                if (r10 == 0) goto L_0x005b
                java.lang.reflect.Method r0 = r9.callbackMethod     // Catch:{ IllegalAccessException | IllegalArgumentException -> 0x0046, InvocationTargetException -> 0x0044 }
                java.lang.Object r0 = r0.invoke(r10, r2)     // Catch:{ IllegalAccessException | IllegalArgumentException -> 0x0046, InvocationTargetException -> 0x0044 }
                java.lang.Object r9 = r9.convertResult(r0)     // Catch:{ IllegalAccessException | IllegalArgumentException -> 0x0046, InvocationTargetException -> 0x0044 }
                goto L_0x005c
            L_0x0044:
                r9 = move-exception
                goto L_0x0048
            L_0x0046:
                r9 = move-exception
                goto L_0x0054
            L_0x0048:
                com.sun.jna.Callback$UncaughtExceptionHandler r0 = com.sun.jna.Native.getCallbackExceptionHandler()
                java.lang.Throwable r9 = r9.getTargetException()
                r0.uncaughtException(r10, r9)
                goto L_0x005b
            L_0x0054:
                com.sun.jna.Callback$UncaughtExceptionHandler r0 = com.sun.jna.Native.getCallbackExceptionHandler()
                r0.uncaughtException(r10, r9)
            L_0x005b:
                r9 = 0
            L_0x005c:
                if (r3 >= r1) goto L_0x0070
                r10 = r2[r3]
                boolean r0 = r10 instanceof com.sun.jna.Structure
                if (r0 == 0) goto L_0x006d
                boolean r0 = r10 instanceof com.sun.jna.Structure.ByValue
                if (r0 != 0) goto L_0x006d
                com.sun.jna.Structure r10 = (com.sun.jna.Structure) r10
                r10.autoWrite()
            L_0x006d:
                int r3 = r3 + 1
                goto L_0x005c
            L_0x0070:
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.CallbackReference.DefaultCallbackProxy.invokeCallback(java.lang.Object[]):java.lang.Object");
        }

        public Object callback(Object[] objArr) {
            try {
                return invokeCallback(objArr);
            } catch (Throwable th) {
                Native.getCallbackExceptionHandler().uncaughtException(getCallback(), th);
                return null;
            }
        }

        public Callback getCallback() {
            return CallbackReference.this.getCallback();
        }

        public Class<?>[] getParameterTypes() {
            return this.callbackMethod.getParameterTypes();
        }

        public Class<?> getReturnType() {
            return this.callbackMethod.getReturnType();
        }
    }

    public static class NativeFunctionHandler implements InvocationHandler {
        private final Function function;
        private final Map<String, ?> options;

        public NativeFunctionHandler(Pointer pointer, int i3, Map<String, ?> map) {
            this.options = map;
            this.function = new Function(pointer, i3, (String) map.get(Library.OPTION_STRING_ENCODING));
        }

        public Pointer getPointer() {
            return this.function;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
                return b.g(CallbackReference.findCallbackClass(((Method) this.options.get("invoking-method")).getDeclaringClass()), a.q("Proxy interface to " + this.function, " ("), ")");
            } else if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
                return Integer.valueOf(hashCode());
            } else {
                if (Library.Handler.OBJECT_EQUALS.equals(method)) {
                    boolean z2 = false;
                    Object obj2 = objArr[0];
                    if (obj2 == null || !Proxy.isProxyClass(obj2.getClass())) {
                        return Boolean.FALSE;
                    }
                    if (Proxy.getInvocationHandler(obj2) == this) {
                        z2 = true;
                    }
                    return Function.valueOf(z2);
                }
                if (Function.isVarArgs(method)) {
                    objArr = Function.concatenateVarArgs(objArr);
                }
                return this.function.invoke(method.getReturnType(), objArr, this.options);
            }
        }
    }

    static {
        try {
            PROXY_CALLBACK_METHOD = CallbackProxy.class.getMethod(Callback.METHOD_NAME, new Class[]{Object[].class});
            if (Platform.isWindows()) {
                try {
                    DLL_CALLBACK_CLASS = DLLCallback.class;
                } catch (ClassNotFoundException e3) {
                    throw new Error("Error loading DLLCallback class", e3);
                }
            } else {
                DLL_CALLBACK_CLASS = null;
            }
        } catch (Exception unused) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
        r6 = r4[r5];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private CallbackReference(com.sun.jna.Callback r11, int r12, boolean r13) {
        /*
            r10 = this;
            r10.<init>(r11)
            java.lang.Class r0 = r11.getClass()
            com.sun.jna.TypeMapper r0 = com.sun.jna.Native.getTypeMapper(r0)
            r10.callingConvention = r12
            boolean r1 = com.sun.jna.Platform.isPPC()
            r2 = 0
            if (r13 == 0) goto L_0x0049
            java.lang.reflect.Method r3 = getCallbackMethod((com.sun.jna.Callback) r11)
            java.lang.Class[] r4 = r3.getParameterTypes()
            r5 = r2
        L_0x001d:
            int r6 = r4.length
            if (r5 >= r6) goto L_0x003c
            if (r1 == 0) goto L_0x002e
            r6 = r4[r5]
            java.lang.Class r7 = java.lang.Float.TYPE
            if (r6 == r7) goto L_0x002c
            java.lang.Class r7 = java.lang.Double.TYPE
            if (r6 != r7) goto L_0x002e
        L_0x002c:
            r13 = r2
            goto L_0x003c
        L_0x002e:
            if (r0 == 0) goto L_0x0039
            r6 = r4[r5]
            com.sun.jna.FromNativeConverter r6 = r0.getFromNativeConverter(r6)
            if (r6 == 0) goto L_0x0039
            goto L_0x002c
        L_0x0039:
            int r5 = r5 + 1
            goto L_0x001d
        L_0x003c:
            if (r0 == 0) goto L_0x0049
            java.lang.Class r1 = r3.getReturnType()
            com.sun.jna.ToNativeConverter r1 = r0.getToNativeConverter(r1)
            if (r1 == 0) goto L_0x0049
            r13 = r2
        L_0x0049:
            java.lang.Class r1 = r11.getClass()
            java.lang.String r9 = com.sun.jna.Native.getStringEncoding(r1)
            if (r13 == 0) goto L_0x007c
            java.lang.reflect.Method r13 = getCallbackMethod((com.sun.jna.Callback) r11)
            r10.method = r13
            java.lang.Class[] r5 = r13.getParameterTypes()
            java.lang.reflect.Method r13 = r10.method
            java.lang.Class r6 = r13.getReturnType()
            java.lang.Class<?> r13 = DLL_CALLBACK_CLASS
            if (r13 == 0) goto L_0x0070
            boolean r13 = r13.isInstance(r11)
            if (r13 == 0) goto L_0x0070
            r13 = 3
        L_0x006e:
            r8 = r13
            goto L_0x0072
        L_0x0070:
            r13 = 1
            goto L_0x006e
        L_0x0072:
            java.lang.reflect.Method r4 = r10.method
            r3 = r11
            r7 = r12
            long r11 = com.sun.jna.Native.createNativeCallback(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x010d
        L_0x007c:
            boolean r13 = r11 instanceof com.sun.jna.CallbackProxy
            if (r13 == 0) goto L_0x0086
            r13 = r11
            com.sun.jna.CallbackProxy r13 = (com.sun.jna.CallbackProxy) r13
            r10.proxy = r13
            goto L_0x0091
        L_0x0086:
            com.sun.jna.CallbackReference$DefaultCallbackProxy r13 = new com.sun.jna.CallbackReference$DefaultCallbackProxy
            java.lang.reflect.Method r1 = getCallbackMethod((com.sun.jna.Callback) r11)
            r13.<init>(r1, r0, r9)
            r10.proxy = r13
        L_0x0091:
            com.sun.jna.CallbackProxy r13 = r10.proxy
            java.lang.Class[] r5 = r13.getParameterTypes()
            com.sun.jna.CallbackProxy r13 = r10.proxy
            java.lang.Class r13 = r13.getReturnType()
            if (r0 == 0) goto L_0x00be
            r1 = r2
        L_0x00a0:
            int r3 = r5.length
            if (r1 >= r3) goto L_0x00b4
            r3 = r5[r1]
            com.sun.jna.FromNativeConverter r3 = r0.getFromNativeConverter(r3)
            if (r3 == 0) goto L_0x00b1
            java.lang.Class r3 = r3.nativeType()
            r5[r1] = r3
        L_0x00b1:
            int r1 = r1 + 1
            goto L_0x00a0
        L_0x00b4:
            com.sun.jna.ToNativeConverter r0 = r0.getToNativeConverter(r13)
            if (r0 == 0) goto L_0x00be
            java.lang.Class r13 = r0.nativeType()
        L_0x00be:
            r0 = r2
        L_0x00bf:
            int r1 = r5.length
            java.lang.String r3 = " requires custom type conversion"
            if (r0 >= r1) goto L_0x00ee
            r1 = r5[r0]
            java.lang.Class r1 = r10.getNativeType(r1)
            r5[r0] = r1
            boolean r1 = isAllowableNativeType(r1)
            if (r1 == 0) goto L_0x00d5
            int r0 = r0 + 1
            goto L_0x00bf
        L_0x00d5:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Callback argument "
            r10.<init>(r11)
            r11 = r5[r0]
            r10.append(r11)
            r10.append(r3)
            java.lang.String r10 = r10.toString()
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            r11.<init>(r10)
            throw r11
        L_0x00ee:
            java.lang.Class r6 = r10.getNativeType(r13)
            boolean r13 = isAllowableNativeType(r6)
            if (r13 == 0) goto L_0x013e
            java.lang.Class<?> r13 = DLL_CALLBACK_CLASS
            if (r13 == 0) goto L_0x0103
            boolean r11 = r13.isInstance(r11)
            if (r11 == 0) goto L_0x0103
            r2 = 2
        L_0x0103:
            r8 = r2
            com.sun.jna.CallbackProxy r3 = r10.proxy
            java.lang.reflect.Method r4 = PROXY_CALLBACK_METHOD
            r7 = r12
            long r11 = com.sun.jna.Native.createNativeCallback(r3, r4, r5, r6, r7, r8, r9)
        L_0x010d:
            r0 = 0
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 == 0) goto L_0x0119
            com.sun.jna.Pointer r0 = new com.sun.jna.Pointer
            r0.<init>(r11)
            goto L_0x011a
        L_0x0119:
            r0 = 0
        L_0x011a:
            r10.cbstruct = r0
            if (r13 == 0) goto L_0x013d
            java.util.Map<java.lang.Long, java.lang.ref.Reference<com.sun.jna.CallbackReference>> r13 = allocatedMemory
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            java.lang.ref.WeakReference r12 = new java.lang.ref.WeakReference
            r12.<init>(r10)
            r13.put(r11, r12)
            com.sun.jna.internal.Cleaner r11 = com.sun.jna.internal.Cleaner.getCleaner()
            com.sun.jna.CallbackReference$CallbackReferenceDisposer r12 = new com.sun.jna.CallbackReference$CallbackReferenceDisposer
            com.sun.jna.Pointer r13 = r10.cbstruct
            r12.<init>(r13)
            com.sun.jna.internal.Cleaner$Cleanable r11 = r11.register(r10, r12)
            r10.cleanable = r11
        L_0x013d:
            return
        L_0x013e:
            java.lang.String r10 = "Callback return type "
            java.lang.String r10 = androidx.constraintlayout.core.state.b.l(r10, r6, r3)
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            r11.<init>(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.CallbackReference.<init>(com.sun.jna.Callback, int, boolean):void");
    }

    private static Reference<Callback>[] addCallbackToArray(Callback callback, Reference<Callback>[] referenceArr) {
        int i3 = 0;
        int i4 = 1;
        if (referenceArr != null) {
            for (int i5 = 0; i5 < referenceArr.length; i5++) {
                if (referenceArr[i5].get() == null) {
                    referenceArr[i5] = null;
                } else {
                    i4++;
                }
            }
        }
        Reference<Callback>[] referenceArr2 = new Reference[i4];
        if (referenceArr != null) {
            int i6 = 0;
            while (i3 < referenceArr.length) {
                Reference<Callback> reference = referenceArr[i3];
                if (reference != null) {
                    referenceArr2[i6] = reference;
                    i6++;
                }
                i3++;
            }
            i3 = i6;
        }
        referenceArr2[i3] = new WeakReference(callback);
        return referenceArr2;
    }

    private static Method checkMethod(Method method2) {
        if (method2.getParameterTypes().length <= 256) {
            return method2;
        }
        throw new UnsupportedOperationException("Method signature exceeds the maximum parameter count: " + method2);
    }

    private static Callback createCallback(Class<?> cls, Pointer pointer) {
        int i3 = AltCallingConvention.class.isAssignableFrom(cls) ? 63 : 0;
        HashMap hashMap = new HashMap(Native.getLibraryOptions(cls));
        hashMap.put("invoking-method", getCallbackMethod(cls));
        return (Callback) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new NativeFunctionHandler(pointer, i3, hashMap));
    }

    public static void disposeAll() {
        for (Reference reference : new LinkedList(allocatedMemory.values())) {
            CallbackReference callbackReference = (CallbackReference) reference.get();
            if (callbackReference != null) {
                callbackReference.close();
            }
        }
    }

    public static Class<?> findCallbackClass(Class<?> cls) {
        Class<Callback> cls2 = Callback.class;
        if (!cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(cls.getName().concat(" is not derived from com.sun.jna.Callback"));
        } else if (cls.isInterface()) {
            return cls;
        } else {
            Class<?>[] interfaces = cls.getInterfaces();
            int i3 = 0;
            while (i3 < interfaces.length) {
                if (cls2.isAssignableFrom(interfaces[i3])) {
                    try {
                        getCallbackMethod(interfaces[i3]);
                        return interfaces[i3];
                    } catch (IllegalArgumentException unused) {
                    }
                } else {
                    i3++;
                }
            }
            return cls2.isAssignableFrom(cls.getSuperclass()) ? findCallbackClass(cls.getSuperclass()) : cls;
        }
    }

    public static Callback getCallback(Class<?> cls, Pointer pointer) {
        return getCallback(cls, pointer, false);
    }

    private static Method getCallbackMethod(Callback callback) {
        return getCallbackMethod(findCallbackClass(callback.getClass()));
    }

    public static Pointer getFunctionPointer(Callback callback) {
        return getFunctionPointer(callback, false);
    }

    private static Pointer getNativeFunctionPointer(Callback callback) {
        if (!Proxy.isProxyClass(callback.getClass())) {
            return null;
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(callback);
        if (invocationHandler instanceof NativeFunctionHandler) {
            return ((NativeFunctionHandler) invocationHandler).getPointer();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Pointer getNativeString(Object obj, boolean z2) {
        if (obj == null) {
            return null;
        }
        NativeString nativeString = new NativeString(obj.toString(), z2);
        allocations.put(obj, nativeString);
        return nativeString.getPointer();
    }

    private Class<?> getNativeType(Class<?> cls) {
        Class<Pointer> cls2 = Pointer.class;
        if (Structure.class.isAssignableFrom(cls)) {
            Structure.validate(cls);
            if (!Structure.ByValue.class.isAssignableFrom(cls)) {
                return cls2;
            }
        } else if (NativeMapped.class.isAssignableFrom(cls)) {
            return NativeMappedConverter.getInstance(cls).nativeType();
        } else {
            if (cls == String.class || cls == WString.class || cls == String[].class || cls == WString[].class || Callback.class.isAssignableFrom(cls)) {
                return cls2;
            }
        }
        return cls;
    }

    private static Callback getTypeAssignableCallback(Class<?> cls, Reference<Callback>[] referenceArr) {
        if (referenceArr == null) {
            return null;
        }
        for (Reference<Callback> reference : referenceArr) {
            Callback callback = reference.get();
            if (callback != null && cls.isAssignableFrom(callback.getClass())) {
                return callback;
            }
        }
        return null;
    }

    private static ThreadGroup initializeThread(Callback callback, AttachOptions attachOptions) {
        CallbackThreadInitializer callbackThreadInitializer;
        if (callback instanceof DefaultCallbackProxy) {
            callback = ((DefaultCallbackProxy) callback).getCallback();
        }
        Map<Callback, CallbackThreadInitializer> map = initializers;
        synchronized (map) {
            callbackThreadInitializer = map.get(callback);
        }
        if (callbackThreadInitializer == null) {
            return null;
        }
        ThreadGroup threadGroup = callbackThreadInitializer.getThreadGroup(callback);
        attachOptions.name = callbackThreadInitializer.getName(callback);
        attachOptions.daemon = callbackThreadInitializer.isDaemon(callback);
        attachOptions.detach = callbackThreadInitializer.detach(callback);
        attachOptions.write();
        return threadGroup;
    }

    private static boolean isAllowableNativeType(Class<?> cls) {
        return cls == Void.TYPE || cls == Void.class || cls == Boolean.TYPE || cls == Boolean.class || cls == Byte.TYPE || cls == Byte.class || cls == Short.TYPE || cls == Short.class || cls == Character.TYPE || cls == Character.class || cls == Integer.TYPE || cls == Integer.class || cls == Long.TYPE || cls == Long.class || cls == Float.TYPE || cls == Float.class || cls == Double.TYPE || cls == Double.class || (Structure.ByValue.class.isAssignableFrom(cls) && Structure.class.isAssignableFrom(cls)) || Pointer.class.isAssignableFrom(cls);
    }

    private void setCallbackOptions(int i3) {
        this.cbstruct.setInt((long) Native.POINTER_SIZE, i3);
    }

    public static CallbackThreadInitializer setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        Map<Callback, CallbackThreadInitializer> map = initializers;
        synchronized (map) {
            if (callbackThreadInitializer != null) {
                try {
                    CallbackThreadInitializer put = map.put(callback, callbackThreadInitializer);
                    return put;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                CallbackThreadInitializer remove = map.remove(callback);
                return remove;
            }
        }
    }

    public void close() {
        Cleaner.Cleanable cleanable2 = this.cleanable;
        if (cleanable2 != null) {
            cleanable2.clean();
        }
        this.cbstruct = null;
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public Pointer getTrampoline() {
        if (this.trampoline == null) {
            this.trampoline = this.cbstruct.getPointer(0);
        }
        return this.trampoline;
    }

    private static Callback getCallback(Class<?> cls, Pointer pointer, boolean z2) {
        if (pointer == null) {
            return null;
        }
        if (cls.isInterface()) {
            Map<Callback, CallbackReference> map = z2 ? directCallbackMap : callbackMap;
            Map<Pointer, Reference<Callback>[]> map2 = pointerCallbackMap;
            synchronized (map2) {
                try {
                    Reference[] referenceArr = (Reference[]) map2.get(pointer);
                    Callback typeAssignableCallback = getTypeAssignableCallback(cls, referenceArr);
                    if (typeAssignableCallback != null) {
                        return typeAssignableCallback;
                    }
                    Callback createCallback = createCallback(cls, pointer);
                    map2.put(pointer, addCallbackToArray(createCallback, referenceArr));
                    map.remove(createCallback);
                    return createCallback;
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
    }

    private static Method getCallbackMethod(Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        Method[] methods = cls.getMethods();
        HashSet hashSet = new HashSet(Arrays.asList(declaredMethods));
        hashSet.retainAll(Arrays.asList(methods));
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (Callback.FORBIDDEN_NAMES.contains(((Method) it.next()).getName())) {
                it.remove();
            }
        }
        Method[] methodArr = (Method[]) hashSet.toArray(new Method[0]);
        if (methodArr.length == 1) {
            return checkMethod(methodArr[0]);
        }
        for (Method method2 : methodArr) {
            if (Callback.METHOD_NAME.equals(method2.getName())) {
                return checkMethod(method2);
            }
        }
        throw new IllegalArgumentException("Callback must implement a single public method, or one public method named 'callback'");
    }

    private static Pointer getFunctionPointer(Callback callback, boolean z2) {
        int i3;
        Pointer trampoline2;
        if (callback == null) {
            return null;
        }
        Pointer nativeFunctionPointer = getNativeFunctionPointer(callback);
        if (nativeFunctionPointer != null) {
            return nativeFunctionPointer;
        }
        Map<String, Object> libraryOptions = Native.getLibraryOptions(callback.getClass());
        if (callback instanceof AltCallingConvention) {
            i3 = 63;
        } else {
            i3 = (libraryOptions == null || !libraryOptions.containsKey(Library.OPTION_CALLING_CONVENTION)) ? 0 : ((Integer) libraryOptions.get(Library.OPTION_CALLING_CONVENTION)).intValue();
        }
        Map<Callback, CallbackReference> map = z2 ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>[]> map2 = pointerCallbackMap;
        synchronized (map2) {
            try {
                CallbackReference callbackReference = map.get(callback);
                if (callbackReference != null) {
                    if (callbackReference.cbstruct == null) {
                    }
                    trampoline2 = callbackReference.getTrampoline();
                }
                callbackReference = new CallbackReference(callback, i3, z2);
                map.put(callback, callbackReference);
                map2.put(callbackReference.getTrampoline(), addCallbackToArray(callback, (Reference<Callback>[]) null));
                if (initializers.containsKey(callback)) {
                    callbackReference.setCallbackOptions(1);
                }
                trampoline2 = callbackReference.getTrampoline();
            } catch (Throwable th) {
                throw th;
            }
        }
        return trampoline2;
    }

    /* access modifiers changed from: private */
    public Callback getCallback() {
        return (Callback) get();
    }
}
