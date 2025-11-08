package com.sun.jna.win32;

import androidx.constraintlayout.core.state.b;
import com.sun.jna.FunctionMapper;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;

public class StdCallFunctionMapper implements FunctionMapper {
    public int getArgumentNativeStackSize(Class<?> cls) {
        if (NativeMapped.class.isAssignableFrom(cls)) {
            cls = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls.isArray()) {
            return Native.POINTER_SIZE;
        }
        try {
            return Native.getNativeSize(cls);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(b.k("Unknown native stack allocation size for ", cls));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return r6.getFunction("_" + r5, 63).getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0028 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFunctionName(com.sun.jna.NativeLibrary r6, java.lang.reflect.Method r7) {
        /*
            r5 = this;
            java.lang.String r0 = r7.getName()
            java.lang.Class[] r7 = r7.getParameterTypes()
            int r1 = r7.length
            r2 = 0
            r3 = r2
        L_0x000b:
            if (r2 >= r1) goto L_0x0017
            r4 = r7[r2]
            int r4 = r5.getArgumentNativeStackSize(r4)
            int r3 = r3 + r4
            int r2 = r2 + 1
            goto L_0x000b
        L_0x0017:
            java.lang.String r5 = "@"
            java.lang.String r5 = com.appsamurai.storyly.exoplayer2.common.a.b(r3, r0, r5)
            r7 = 63
            com.sun.jna.Function r1 = r6.getFunction((java.lang.String) r5, (int) r7)     // Catch:{ UnsatisfiedLinkError -> 0x0028 }
            java.lang.String r0 = r1.getName()     // Catch:{ UnsatisfiedLinkError -> 0x0028 }
            goto L_0x0041
        L_0x0028:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            r1.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            r1.append(r5)     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            java.lang.String r5 = r1.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            com.sun.jna.Function r5 = r6.getFunction((java.lang.String) r5, (int) r7)     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
            java.lang.String r0 = r5.getName()     // Catch:{ UnsatisfiedLinkError -> 0x0041 }
        L_0x0041:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.win32.StdCallFunctionMapper.getFunctionName(com.sun.jna.NativeLibrary, java.lang.reflect.Method):java.lang.String");
    }
}
