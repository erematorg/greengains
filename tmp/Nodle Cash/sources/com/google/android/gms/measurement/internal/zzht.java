package com.google.android.gms.measurement.internal;

import android.os.Process;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzht extends Thread {
    private final Object zza;
    private final BlockingQueue<zzhu<?>> zzb;
    @GuardedBy("threadLifeCycleLock")
    private boolean zzc = false;
    private final /* synthetic */ zzhp zzd;

    public zzht(zzhp zzhp, String str, BlockingQueue<zzhu<?>> blockingQueue) {
        this.zzd = zzhp;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zza = new Object();
        this.zzb = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        zzgk zzu = this.zzd.zzj().zzu();
        String name = getName();
        zzu.zza(name + " was interrupted", interruptedException);
    }

    private final void zzb() {
        synchronized (this.zzd.zzh) {
            try {
                if (!this.zzc) {
                    this.zzd.zzi.release();
                    this.zzd.zzh.notifyAll();
                    if (this == this.zzd.zzb) {
                        this.zzd.zzb = null;
                    } else if (this == this.zzd.zzc) {
                        this.zzd.zzc = null;
                    } else {
                        this.zzd.zzj().zzg().zza("Current scheduler thread is neither worker nor network");
                    }
                    this.zzc = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void run() {
        boolean z2 = false;
        while (!z2) {
            try {
                this.zzd.zzi.acquire();
                z2 = true;
            } catch (InterruptedException e3) {
                zza(e3);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzhu poll = this.zzb.poll();
                if (poll != null) {
                    Process.setThreadPriority(poll.zza ? threadPriority : 10);
                    poll.run();
                } else {
                    synchronized (this.zza) {
                        if (this.zzb.peek() == null && !this.zzd.zzj) {
                            try {
                                this.zza.wait(30000);
                            } catch (InterruptedException e4) {
                                zza(e4);
                            }
                        }
                    }
                    synchronized (this.zzd.zzh) {
                        if (this.zzb.peek() == null) {
                            zzb();
                            zzb();
                            return;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            zzb();
            throw th;
        }
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.notifyAll();
        }
    }
}
