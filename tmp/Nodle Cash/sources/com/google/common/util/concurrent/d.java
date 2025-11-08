package com.google.common.util.concurrent;

import sun.misc.Unsafe;

public final /* synthetic */ class d {
    public static /* synthetic */ boolean a(Unsafe unsafe, AbstractFuture abstractFuture, long j2, Object obj, Object obj2) {
        while (!unsafe.compareAndSwapObject(abstractFuture, j2, obj, obj2)) {
            if (unsafe.getObject(abstractFuture, j2) != obj) {
                return false;
            }
        }
        return true;
    }
}
