package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.common.C;
import org.apache.xerces.xs.XSSimpleTypeDefinition;

final class zzxd extends zzxl {
    private int zza;
    private int zzb;
    private float zzc;
    private float zzd;
    private boolean zze;
    private float zzf;
    private float zzg;
    private long zzh;
    private long zzi;
    private boolean zzj;
    private float zzk;
    private float zzl;
    private short zzm;

    public final zzxl zza(boolean z2) {
        this.zzj = true;
        this.zzm = (short) (this.zzm | XSSimpleTypeDefinition.FACET_TOTALDIGITS);
        return this;
    }

    public final zzxl zzb(float f2) {
        this.zzg = 0.8f;
        this.zzm = (short) (this.zzm | 64);
        return this;
    }

    public final zzxl zzc(float f2) {
        this.zzf = 0.5f;
        this.zzm = (short) (this.zzm | 32);
        return this;
    }

    public final zzxl zzd(float f2) {
        this.zzd = 0.8f;
        this.zzm = (short) (this.zzm | 8);
        return this;
    }

    public final zzxl zze(int i3) {
        this.zzb = 5;
        this.zzm = (short) (this.zzm | 2);
        return this;
    }

    public final zzxl zzf(float f2) {
        this.zzc = 0.25f;
        this.zzm = (short) (this.zzm | 4);
        return this;
    }

    public final zzxl zzg(int i3) {
        this.zza = 10;
        this.zzm = (short) (this.zzm | 1);
        return this;
    }

    public final zzxl zzh(long j2) {
        this.zzi = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        this.zzm = (short) (this.zzm | XSSimpleTypeDefinition.FACET_MININCLUSIVE);
        return this;
    }

    public final zzxl zzi(boolean z2) {
        this.zze = z2;
        this.zzm = (short) (this.zzm | 16);
        return this;
    }

    public final zzxl zzj(float f2) {
        this.zzk = 0.1f;
        this.zzm = (short) (this.zzm | 1024);
        return this;
    }

    public final zzxl zzk(long j2) {
        this.zzh = 1500;
        this.zzm = (short) (this.zzm | XSSimpleTypeDefinition.FACET_MINEXCLUSIVE);
        return this;
    }

    public final zzxl zzl(float f2) {
        this.zzl = 0.05f;
        this.zzm = (short) (this.zzm | XSSimpleTypeDefinition.FACET_ENUMERATION);
        return this;
    }

    public final zzxm zzm() {
        if (this.zzm == 4095) {
            return new zzxf(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, (zzxe) null);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzm & 1) == 0) {
            sb.append(" recentFramesToCheck");
        }
        if ((this.zzm & 2) == 0) {
            sb.append(" recentFramesContainingPredictedArea");
        }
        if ((this.zzm & 4) == 0) {
            sb.append(" recentFramesIou");
        }
        if ((this.zzm & 8) == 0) {
            sb.append(" maxCoverage");
        }
        if ((this.zzm & 16) == 0) {
            sb.append(" useConfidenceScore");
        }
        if ((this.zzm & 32) == 0) {
            sb.append(" lowerConfidenceScore");
        }
        if ((this.zzm & 64) == 0) {
            sb.append(" higherConfidenceScore");
        }
        if ((this.zzm & XSSimpleTypeDefinition.FACET_MINEXCLUSIVE) == 0) {
            sb.append(" zoomIntervalInMillis");
        }
        if ((this.zzm & XSSimpleTypeDefinition.FACET_MININCLUSIVE) == 0) {
            sb.append(" resetIntervalInMillis");
        }
        if ((this.zzm & XSSimpleTypeDefinition.FACET_TOTALDIGITS) == 0) {
            sb.append(" enableZoomThreshold");
        }
        if ((this.zzm & 1024) == 0) {
            sb.append(" zoomInThreshold");
        }
        if ((this.zzm & XSSimpleTypeDefinition.FACET_ENUMERATION) == 0) {
            sb.append(" zoomOutThreshold");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
