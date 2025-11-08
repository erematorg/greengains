package com.sun.jna;

import java.lang.reflect.Method;

abstract class VarArgsChecker {

    public static final class NoVarArgsChecker extends VarArgsChecker {
        private NoVarArgsChecker() {
            super();
        }

        public int fixedArgs(Method method) {
            return 0;
        }

        public boolean isVarArgs(Method method) {
            return false;
        }
    }

    public static final class RealVarArgsChecker extends VarArgsChecker {
        private RealVarArgsChecker() {
            super();
        }

        public int fixedArgs(Method method) {
            if (method.isVarArgs()) {
                return method.getParameterTypes().length - 1;
            }
            return 0;
        }

        public boolean isVarArgs(Method method) {
            return method.isVarArgs();
        }
    }

    public static VarArgsChecker create() {
        try {
            return Method.class.getMethod("isVarArgs", (Class[]) null) != null ? new RealVarArgsChecker() : new NoVarArgsChecker();
        } catch (NoSuchMethodException | SecurityException unused) {
            return new NoVarArgsChecker();
        }
    }

    public abstract int fixedArgs(Method method);

    public abstract boolean isVarArgs(Method method);

    private VarArgsChecker() {
    }
}
