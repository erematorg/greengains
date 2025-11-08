package com.sun.jna.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cleaner {
    private static final Cleaner INSTANCE = new Cleaner();
    /* access modifiers changed from: private */
    public Thread cleanerThread;
    /* access modifiers changed from: private */
    public CleanerRef firstCleanable;
    /* access modifiers changed from: private */
    public final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    public interface Cleanable {
        void clean();
    }

    public static class CleanerRef extends PhantomReference<Object> implements Cleanable {
        private final Cleaner cleaner;
        /* access modifiers changed from: private */
        public final Runnable cleanupTask;
        /* access modifiers changed from: private */
        public CleanerRef next;
        private CleanerRef previous;

        public CleanerRef(Cleaner cleaner2, Object obj, ReferenceQueue<? super Object> referenceQueue, Runnable runnable) {
            super(obj, referenceQueue);
            this.cleaner = cleaner2;
            this.cleanupTask = runnable;
        }

        public void clean() {
            if (this.cleaner.remove(this)) {
                this.cleanupTask.run();
            }
        }

        public CleanerRef getNext() {
            return this.next;
        }

        public CleanerRef getPrevious() {
            return this.previous;
        }

        public void setNext(CleanerRef cleanerRef) {
            this.next = cleanerRef;
        }

        public void setPrevious(CleanerRef cleanerRef) {
            this.previous = cleanerRef;
        }
    }

    public class CleanerThread extends Thread {
        private static final long CLEANER_LINGER_TIME = 30000;

        public CleanerThread() {
            super("JNA Cleaner");
            setDaemon(true);
        }

        public void run() {
            while (true) {
                try {
                    Reference remove = Cleaner.this.referenceQueue.remove(30000);
                    if (remove instanceof CleanerRef) {
                        ((CleanerRef) remove).clean();
                    } else if (remove == null) {
                        synchronized (Cleaner.this.referenceQueue) {
                            Logger logger = Logger.getLogger(Cleaner.class.getName());
                            if (Cleaner.this.firstCleanable == null) {
                                Thread unused = Cleaner.this.cleanerThread = null;
                                logger.log(Level.FINE, "Shutting down CleanerThread");
                                return;
                            } else if (logger.isLoggable(Level.FINER)) {
                                StringBuilder sb = new StringBuilder();
                                for (CleanerRef access$200 = Cleaner.this.firstCleanable; access$200 != null; access$200 = access$200.next) {
                                    if (sb.length() != 0) {
                                        sb.append(", ");
                                    }
                                    sb.append(access$200.cleanupTask.toString());
                                }
                                logger.log(Level.FINER, "Registered Cleaners: {0}", sb.toString());
                            }
                        }
                    } else {
                        continue;
                    }
                } catch (InterruptedException unused2) {
                    return;
                } catch (Exception e3) {
                    Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, (String) null, e3);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private Cleaner() {
    }

    private synchronized CleanerRef add(CleanerRef cleanerRef) {
        synchronized (this.referenceQueue) {
            try {
                CleanerRef cleanerRef2 = this.firstCleanable;
                if (cleanerRef2 == null) {
                    this.firstCleanable = cleanerRef;
                } else {
                    cleanerRef.setNext(cleanerRef2);
                    this.firstCleanable.setPrevious(cleanerRef);
                    this.firstCleanable = cleanerRef;
                }
                if (this.cleanerThread == null) {
                    Logger.getLogger(Cleaner.class.getName()).log(Level.FINE, "Starting CleanerThread");
                    CleanerThread cleanerThread2 = new CleanerThread();
                    this.cleanerThread = cleanerThread2;
                    cleanerThread2.start();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return cleanerRef;
    }

    public static Cleaner getCleaner() {
        return INSTANCE;
    }

    /* access modifiers changed from: private */
    public synchronized boolean remove(CleanerRef cleanerRef) {
        boolean z2;
        boolean z3;
        synchronized (this.referenceQueue) {
            try {
                z2 = true;
                if (cleanerRef == this.firstCleanable) {
                    this.firstCleanable = cleanerRef.getNext();
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (cleanerRef.getPrevious() != null) {
                    cleanerRef.getPrevious().setNext(cleanerRef.getNext());
                }
                if (cleanerRef.getNext() != null) {
                    cleanerRef.getNext().setPrevious(cleanerRef.getPrevious());
                }
                if (cleanerRef.getPrevious() == null) {
                    if (cleanerRef.getNext() == null) {
                        z2 = z3;
                    }
                }
                cleanerRef.setNext((CleanerRef) null);
                cleanerRef.setPrevious((CleanerRef) null);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return z2;
    }

    public synchronized Cleanable register(Object obj, Runnable runnable) {
        return add(new CleanerRef(this, obj, this.referenceQueue, runnable));
    }
}
