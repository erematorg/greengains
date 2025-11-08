package com.google.android.gms.internal.fido;

import androidx.compose.animation.core.a;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

final class zzdv {
    private final Deque zza = new ArrayDeque(16);

    private zzdv(boolean z2) {
    }

    public static zzdv zza() {
        return new zzdv(false);
    }

    private final long zzh() {
        if (this.zza.isEmpty()) {
            return 0;
        }
        return ((Long) this.zza.peek()).longValue();
    }

    private final void zzi(long j2) {
        this.zza.pop();
        this.zza.push(Long.valueOf(j2));
    }

    public final void zzb() throws IOException {
        if (!this.zza.isEmpty()) {
            int size = this.zza.size();
            long zzh = zzh();
            throw new IOException("data item not completed, stackSize: " + size + " scope: " + zzh);
        }
    }

    public final void zzc() throws IOException {
        long zzh = zzh();
        if (zzh >= 0) {
            throw new IOException(a.s("expected indefinite length scope but found ", zzh));
        } else if (zzh != -5) {
            this.zza.pop();
        } else {
            throw new IOException("expected a value for dangling key in indefinite-length map");
        }
    }

    public final void zzd() throws IOException {
        long zzh = zzh();
        if (zzh != -1) {
            if (zzh == -2) {
                zzh = -2;
            } else {
                return;
            }
        }
        throw new IOException(a.s("expected non-string scope but found ", zzh));
    }

    public final void zze(long j2) throws IOException {
        long zzh = zzh();
        if (zzh != j2) {
            if (zzh != -1) {
                if (zzh == -2) {
                    zzh = -2;
                } else {
                    return;
                }
            }
            StringBuilder u3 = a.u("expected non-string scope or scope ", j2, " but found ");
            u3.append(zzh);
            throw new IOException(u3.toString());
        }
    }

    public final void zzf() {
        long zzh = zzh();
        int i3 = (zzh > 1 ? 1 : (zzh == 1 ? 0 : -1));
        if (i3 == 0) {
            this.zza.pop();
        } else if (i3 > 0) {
            zzi(zzh - 1);
        } else if (zzh == -4) {
            zzi(-5);
        } else if (zzh == -5) {
            zzi(-4);
        }
    }

    public final void zzg(long j2) {
        this.zza.push(Long.valueOf(j2));
    }
}
