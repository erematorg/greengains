package com.kenai.jffi;

public final class LastError {
    private final Foreign foreign;

    public static final class SingletonHolder {
        static final LastError INSTANCE = new LastError();

        private SingletonHolder() {
        }
    }

    public static final LastError getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public final int get() {
        return Foreign.getLastError();
    }

    @Deprecated
    public final int getError() {
        return Foreign.getLastError();
    }

    public final void set(int i3) {
        Foreign.setLastError(i3);
    }

    private LastError() {
        this.foreign = Foreign.getInstance();
    }
}
