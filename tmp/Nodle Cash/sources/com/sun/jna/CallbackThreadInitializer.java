package com.sun.jna;

public class CallbackThreadInitializer {
    private boolean daemon;
    private boolean detach;
    private ThreadGroup group;
    private String name;

    public CallbackThreadInitializer() {
        this(true);
    }

    public boolean detach(Callback callback) {
        return this.detach;
    }

    public String getName(Callback callback) {
        return this.name;
    }

    public ThreadGroup getThreadGroup(Callback callback) {
        return this.group;
    }

    public boolean isDaemon(Callback callback) {
        return this.daemon;
    }

    public CallbackThreadInitializer(boolean z2) {
        this(z2, false);
    }

    public CallbackThreadInitializer(boolean z2, boolean z3) {
        this(z2, z3, (String) null);
    }

    public CallbackThreadInitializer(boolean z2, boolean z3, String str) {
        this(z2, z3, str, (ThreadGroup) null);
    }

    public CallbackThreadInitializer(boolean z2, boolean z3, String str, ThreadGroup threadGroup) {
        this.daemon = z2;
        this.detach = z3;
        this.name = str;
        this.group = threadGroup;
    }
}
