package com.kenai.jffi;

import java.lang.reflect.Method;

public final class InvokeDynamicSupport {

    public static final class Invoker {
        private final Method method;
        private final Object methodHandle;

        public Invoker(Method method2, Object obj) {
            this.method = method2;
            this.methodHandle = obj;
        }

        public Method getMethod() {
            return this.method;
        }

        public Object getMethodHandle() {
            return this.methodHandle;
        }
    }

    public static final class JSR292 {
        static final JSR292 INSTANCE = getInstance();
        private final Method insertArguments;
        private final Object lookup;
        private final Class methodHandles;
        private final Method unreflect;

        public JSR292(Object obj, Method method, Class cls, Method method2) {
            this.lookup = obj;
            this.unreflect = method;
            this.methodHandles = cls;
            this.insertArguments = method2;
        }

        private static JSR292 getInstance() {
            try {
                Class<?> cls = Class.forName("java.lang.invoke.MethodHandles$Lookup");
                Class<?> cls2 = Class.forName("java.lang.invoke.MethodHandles");
                Class<?> cls3 = Class.forName("java.lang.invoke.MethodHandle");
                Method declaredMethod = cls2.getDeclaredMethod("lookup", (Class[]) null);
                return new JSR292(declaredMethod.invoke(cls2, (Object[]) null), cls.getDeclaredMethod("unreflect", new Class[]{Method.class}), cls2, cls2.getDeclaredMethod("insertArguments", new Class[]{cls3, Integer.TYPE, Object[].class}));
            } catch (Throwable unused) {
                return null;
            }
        }

        public static boolean isAvailable() {
            return INSTANCE != null;
        }

        public Object insertArguments(Object obj, int i3, Object... objArr) throws Exception {
            return this.insertArguments.invoke(this.methodHandles, new Object[]{obj, Integer.valueOf(i3), objArr});
        }

        public Object unreflect(Method method) throws Exception {
            return this.unreflect.invoke(this.lookup, new Object[]{method});
        }
    }

    private InvokeDynamicSupport() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0094, code lost:
        if (r9 == 64) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0096, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0098, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0099, code lost:
        r1 = r1 & r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a0, code lost:
        if (r0.dataModel == 64) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00a3, code lost:
        r8 = r8 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.kenai.jffi.InvokeDynamicSupport.Invoker getFastNumericInvoker(com.kenai.jffi.CallContext r11, long r12) {
        /*
            com.kenai.jffi.Platform r0 = com.kenai.jffi.Platform.getPlatform()
            com.kenai.jffi.Platform$CPU r0 = r0.getCPU()
            com.kenai.jffi.Type r1 = r11.getReturnType()
            boolean r1 = r1 instanceof com.kenai.jffi.Type.Builtin
            r2 = 0
            if (r1 != 0) goto L_0x0012
            return r2
        L_0x0012:
            int r1 = r11.flags
            r3 = 1
            r1 = r1 & r3
            if (r1 == 0) goto L_0x0019
            return r2
        L_0x0019:
            int r1 = r11.getParameterCount()
            r4 = 6
            if (r1 <= r4) goto L_0x0021
            return r2
        L_0x0021:
            com.kenai.jffi.Type r1 = r11.getReturnType()
            int r1 = r1.type()
            r4 = 32
            r5 = 64
            r6 = 0
            if (r1 == 0) goto L_0x0050
            switch(r1) {
                case 5: goto L_0x0047;
                case 6: goto L_0x0047;
                case 7: goto L_0x0047;
                case 8: goto L_0x0047;
                case 9: goto L_0x0047;
                case 10: goto L_0x0047;
                case 11: goto L_0x0044;
                case 12: goto L_0x0044;
                case 13: goto L_0x0043;
                case 14: goto L_0x0036;
                default: goto L_0x0033;
            }
        L_0x0033:
            r1 = r6
        L_0x0034:
            r7 = r1
            goto L_0x0052
        L_0x0036:
            int r1 = r0.dataModel
            if (r1 != r4) goto L_0x003c
            r7 = r3
            goto L_0x003d
        L_0x003c:
            r7 = r6
        L_0x003d:
            if (r1 != r5) goto L_0x0041
            r1 = r3
            goto L_0x0052
        L_0x0041:
            r1 = r6
            goto L_0x0052
        L_0x0043:
            return r2
        L_0x0044:
            r1 = r3
            r7 = r6
            goto L_0x0052
        L_0x0047:
            int r1 = r0.dataModel
            if (r1 != r5) goto L_0x004d
            r1 = r3
            goto L_0x004e
        L_0x004d:
            r1 = r6
        L_0x004e:
            r7 = r3
            goto L_0x0052
        L_0x0050:
            r1 = r3
            goto L_0x0034
        L_0x0052:
            com.kenai.jffi.Platform$CPU r8 = com.kenai.jffi.Platform.CPU.I386
            if (r0 == r8) goto L_0x005d
            com.kenai.jffi.Platform$CPU r9 = com.kenai.jffi.Platform.CPU.X86_64
            if (r0 != r9) goto L_0x005b
            goto L_0x005d
        L_0x005b:
            r9 = r6
            goto L_0x005e
        L_0x005d:
            r9 = r3
        L_0x005e:
            r7 = r7 & r9
            if (r0 == r8) goto L_0x0068
            com.kenai.jffi.Platform$CPU r8 = com.kenai.jffi.Platform.CPU.X86_64
            if (r0 != r8) goto L_0x0066
            goto L_0x0068
        L_0x0066:
            r8 = r6
            goto L_0x0069
        L_0x0068:
            r8 = r3
        L_0x0069:
            r1 = r1 & r8
            r8 = r6
        L_0x006b:
            int r9 = r11.getParameterCount()
            if (r8 >= r9) goto L_0x00a6
            if (r7 != 0) goto L_0x0075
            if (r1 == 0) goto L_0x00a6
        L_0x0075:
            com.kenai.jffi.Type r9 = r11.getParameterType(r8)
            boolean r9 = r9 instanceof com.kenai.jffi.Type.Builtin
            if (r9 != 0) goto L_0x007e
            return r2
        L_0x007e:
            com.kenai.jffi.Type r9 = r11.getParameterType(r8)
            int r9 = r9.type()
            switch(r9) {
                case 5: goto L_0x009e;
                case 6: goto L_0x009e;
                case 7: goto L_0x009e;
                case 8: goto L_0x009e;
                case 9: goto L_0x009e;
                case 10: goto L_0x009e;
                case 11: goto L_0x009c;
                case 12: goto L_0x009c;
                case 13: goto L_0x009b;
                case 14: goto L_0x008c;
                default: goto L_0x0089;
            }
        L_0x0089:
            r1 = r6
            r7 = r1
            goto L_0x00a3
        L_0x008c:
            int r9 = r0.dataModel
            if (r9 != r4) goto L_0x0092
            r10 = r3
            goto L_0x0093
        L_0x0092:
            r10 = r6
        L_0x0093:
            r7 = r7 & r10
            if (r9 != r5) goto L_0x0098
        L_0x0096:
            r9 = r3
            goto L_0x0099
        L_0x0098:
            r9 = r6
        L_0x0099:
            r1 = r1 & r9
            goto L_0x00a3
        L_0x009b:
            return r2
        L_0x009c:
            r7 = r6
            goto L_0x00a3
        L_0x009e:
            int r9 = r0.dataModel
            if (r9 != r5) goto L_0x0098
            goto L_0x0096
        L_0x00a3:
            int r8 = r8 + 1
            goto L_0x006b
        L_0x00a6:
            if (r7 == 0) goto L_0x00ab
            java.lang.Class r0 = java.lang.Integer.TYPE
            goto L_0x00ad
        L_0x00ab:
            java.lang.Class r0 = java.lang.Long.TYPE
        L_0x00ad:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            if (r7 == 0) goto L_0x00b7
            java.lang.String r5 = "invokeI"
            goto L_0x00be
        L_0x00b7:
            if (r1 == 0) goto L_0x00bc
            java.lang.String r5 = "invokeL"
            goto L_0x00be
        L_0x00bc:
            java.lang.String r5 = "invokeN"
        L_0x00be:
            r4.append(r5)
            int r5 = r11.getParameterCount()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            int r5 = r11.flags
            r8 = 2
            r5 = r5 & r8
            if (r5 == 0) goto L_0x00dc
            if (r7 != 0) goto L_0x00d6
            if (r1 == 0) goto L_0x00dc
        L_0x00d6:
            java.lang.String r1 = "NoErrno"
            java.lang.String r4 = android.support.v4.media.session.a.m(r4, r1)
        L_0x00dc:
            int r1 = r11.getParameterCount()
            int r1 = r1 + r8
            java.lang.Class[] r5 = new java.lang.Class[r1]
            java.lang.Class r7 = java.lang.Long.TYPE
            r5[r6] = r7
            r5[r3] = r7
            java.util.Arrays.fill(r5, r8, r1, r0)
            java.lang.Class<com.kenai.jffi.Foreign> r0 = com.kenai.jffi.Foreign.class
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r4, r5)     // Catch:{ all -> 0x0112 }
            com.kenai.jffi.InvokeDynamicSupport$JSR292 r1 = com.kenai.jffi.InvokeDynamicSupport.JSR292.INSTANCE     // Catch:{ all -> 0x0112 }
            java.lang.Object r3 = r1.unreflect(r0)     // Catch:{ all -> 0x0112 }
            long r4 = r11.getAddress()     // Catch:{ all -> 0x0112 }
            java.lang.Long r11 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0112 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0112 }
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r12}     // Catch:{ all -> 0x0112 }
            java.lang.Object r11 = r1.insertArguments(r3, r6, r11)     // Catch:{ all -> 0x0112 }
            com.kenai.jffi.InvokeDynamicSupport$Invoker r12 = new com.kenai.jffi.InvokeDynamicSupport$Invoker     // Catch:{ all -> 0x0112 }
            r12.<init>(r0, r11)     // Catch:{ all -> 0x0112 }
            return r12
        L_0x0112:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.InvokeDynamicSupport.getFastNumericInvoker(com.kenai.jffi.CallContext, long):com.kenai.jffi.InvokeDynamicSupport$Invoker");
    }
}
