package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

public class Finalizer implements Runnable {
    private static final String FINALIZABLE_REFERENCE = "com.google.common.base.FinalizableReference";

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f6854a = 0;
    @CheckForNull
    private static final Constructor<Thread> bigThreadConstructor;
    @CheckForNull
    private static final Field inheritableThreadLocals;
    private static final Logger logger = Logger.getLogger(Finalizer.class.getName());
    private final WeakReference<Class<?>> finalizableReferenceClassReference;
    private final PhantomReference<Object> frqReference;
    private final ReferenceQueue<Object> queue;

    static {
        Constructor<Thread> bigThreadConstructor2 = getBigThreadConstructor();
        bigThreadConstructor = bigThreadConstructor2;
        inheritableThreadLocals = bigThreadConstructor2 == null ? getInheritableThreadLocalsField() : null;
    }

    private Finalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
        this.queue = referenceQueue;
        this.finalizableReferenceClassReference = new WeakReference<>(cls);
        this.frqReference = phantomReference;
    }

    private boolean cleanUp(Reference<?> reference) {
        Reference<? extends Object> poll;
        Method finalizeReferentMethod = getFinalizeReferentMethod();
        if (finalizeReferentMethod == null || !finalizeReference(reference, finalizeReferentMethod)) {
            return false;
        }
        do {
            poll = this.queue.poll();
            if (poll == null) {
                return true;
            }
        } while (finalizeReference(poll, finalizeReferentMethod));
        return false;
    }

    private boolean finalizeReference(Reference<?> reference, Method method) {
        reference.clear();
        if (reference == this.frqReference) {
            return false;
        }
        try {
            method.invoke(reference, (Object[]) null);
            return true;
        } catch (Throwable th) {
            logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
            return true;
        }
    }

    @CheckForNull
    private static Constructor<Thread> getBigThreadConstructor() {
        try {
            return Thread.class.getConstructor(new Class[]{ThreadGroup.class, Runnable.class, String.class, Long.TYPE, Boolean.TYPE});
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    private Method getFinalizeReferentMethod() {
        Class cls = this.finalizableReferenceClassReference.get();
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod("finalizeReferent", (Class[]) null);
        } catch (NoSuchMethodException e3) {
            throw new AssertionError(e3);
        }
    }

    @CheckForNull
    private static Field getInheritableThreadLocalsField() {
        try {
            Field declaredField = Thread.class.getDeclaredField("inheritableThreadLocals");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            logger.log(Level.INFO, "Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049 A[Catch:{ all -> 0x004d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void startFinalizer(java.lang.Class<?> r4, java.lang.ref.ReferenceQueue<java.lang.Object> r5, java.lang.ref.PhantomReference<java.lang.Object> r6) {
        /*
            java.lang.String r0 = r4.getName()
            java.lang.String r1 = "com.google.common.base.FinalizableReference"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005b
            com.google.common.base.internal.Finalizer r0 = new com.google.common.base.internal.Finalizer
            r0.<init>(r4, r5, r6)
            java.lang.Class<com.google.common.base.internal.Finalizer> r4 = com.google.common.base.internal.Finalizer.class
            java.lang.String r4 = r4.getName()
            java.lang.reflect.Constructor<java.lang.Thread> r5 = bigThreadConstructor
            r6 = 0
            if (r5 == 0) goto L_0x0039
            r1 = 0
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x002f }
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x002f }
            java.lang.Object[] r1 = new java.lang.Object[]{r6, r0, r4, r1, r2}     // Catch:{ all -> 0x002f }
            java.lang.Object r5 = r5.newInstance(r1)     // Catch:{ all -> 0x002f }
            java.lang.Thread r5 = (java.lang.Thread) r5     // Catch:{ all -> 0x002f }
            goto L_0x003a
        L_0x002f:
            r5 = move-exception
            java.util.logging.Logger r1 = logger
            java.util.logging.Level r2 = java.util.logging.Level.INFO
            java.lang.String r3 = "Failed to create a thread without inherited thread-local values"
            r1.log(r2, r3, r5)
        L_0x0039:
            r5 = r6
        L_0x003a:
            if (r5 != 0) goto L_0x0041
            java.lang.Thread r5 = new java.lang.Thread
            r5.<init>(r6, r0, r4)
        L_0x0041:
            r4 = 1
            r5.setDaemon(r4)
            java.lang.reflect.Field r4 = inheritableThreadLocals     // Catch:{ all -> 0x004d }
            if (r4 == 0) goto L_0x0057
            r4.set(r5, r6)     // Catch:{ all -> 0x004d }
            goto L_0x0057
        L_0x004d:
            r4 = move-exception
            java.util.logging.Logger r6 = logger
            java.util.logging.Level r0 = java.util.logging.Level.INFO
            java.lang.String r1 = "Failed to clear thread local values inherited by reference finalizer thread."
            r6.log(r0, r1, r4)
        L_0x0057:
            r5.start()
            return
        L_0x005b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Expected com.google.common.base.FinalizableReference."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.internal.Finalizer.startFinalizer(java.lang.Class, java.lang.ref.ReferenceQueue, java.lang.ref.PhantomReference):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:0|1) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:2:0x000a, LOOP_START, MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r1 = this;
        L_0x0000:
            java.lang.ref.ReferenceQueue<java.lang.Object> r0 = r1.queue     // Catch:{ InterruptedException -> 0x0000 }
            java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
            boolean r0 = r1.cleanUp(r0)     // Catch:{ InterruptedException -> 0x0000 }
            if (r0 != 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.internal.Finalizer.run():void");
    }
}
