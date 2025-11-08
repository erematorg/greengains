package com.google.android.recaptcha.internal;

public final class zznf extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zznf zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    /* access modifiers changed from: private */
    public int zzg;
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    /* access modifiers changed from: private */
    public long zzl;
    private zzib zzm;
    /* access modifiers changed from: private */
    public int zzn;
    private zzmr zzo;
    private zznr zzp;
    /* access modifiers changed from: private */
    public String zzq = "";
    private zzlj zzr;
    private zzib zzs;

    static {
        zznf zznf = new zznf();
        zzb = zznf;
        zzit.zzD(zznf.class, zznf);
    }

    private zznf() {
    }

    public static zznf zzH() {
        return zzb;
    }

    public static zznf zzI(byte[] bArr) throws zzje {
        return (zznf) zzit.zzu(zzb, bArr);
    }

    public static /* synthetic */ void zzL(zznf zznf, String str) {
        str.getClass();
        zznf.zzj = str;
    }

    public static /* synthetic */ void zzN(zznf zznf, zzmr zzmr) {
        zznf.zzo = zzmr;
        zznf.zzd |= 2;
    }

    public static /* synthetic */ void zzO(zznf zznf, zznr zznr) {
        zznr.getClass();
        zznf.zzp = zznr;
        zznf.zzd |= 4;
    }

    public static /* synthetic */ void zzR(zznf zznf, String str) {
        str.getClass();
        zznf.zzh = str;
    }

    public static /* synthetic */ void zzS(zznf zznf, String str) {
        str.getClass();
        zznf.zzi = str;
    }

    public static zznc zzi() {
        return (zznc) zzb.zzp();
    }

    public final String zzJ() {
        return this.zzi;
    }

    public final String zzK() {
        return this.zzj;
    }

    public final boolean zzT() {
        return (this.zzd & 2) != 0;
    }

    public final int zzU() {
        int i3 = this.zzn;
        int i4 = 2;
        if (i3 != 0) {
            i4 = i3 != 1 ? i3 != 2 ? 0 : 4 : 3;
        }
        if (i4 == 0) {
            return 1;
        }
        return i4;
    }

    @Deprecated
    public final long zzf() {
        return this.zzl;
    }

    public final zzmr zzg() {
        zzmr zzmr = this.zzo;
        return zzmr == null ? zzmr.zzj() : zzmr;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u000e\u0001\u0001\u0001\u000f\u000e\u0000\u0000\u0000\u0001\f\u0002Ȉ\u0003\u0003\u0004\f\u0005ဉ\u0001\u0006ဉ\u0002\u0007Ȉ\bȈ\tȈ\nဉ\u0000\u000bဉ\u0003\rဉ\u0004\u000eȈ\u000f<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", "zzi", "zzl", "zzn", "zzo", "zzp", "zzq", "zzj", "zzk", "zzm", "zzr", "zzs", "zzh", zzml.class});
        } else if (i4 == 3) {
            return new zznf();
        } else {
            if (i4 == 4) {
                return new zznc((zznb) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzne zzj() {
        zzne zzne;
        int i3 = this.zzg;
        zzne zzne2 = zzne.UNKNOWN;
        switch (i3) {
            case 0:
                zzne = zzne.UNKNOWN;
                break;
            case 1:
                zzne = zzne.INIT_NATIVE;
                break;
            case 2:
                zzne = zzne.INIT_NETWORK;
                break;
            case 3:
                zzne = zzne.INIT_JS;
                break;
            case 4:
                zzne = zzne.INIT_TOTAL;
                break;
            case 5:
                zzne = zzne.EXECUTE_NATIVE;
                break;
            case 6:
                zzne = zzne.EXECUTE_JS;
                break;
            case 7:
                zzne = zzne.EXECUTE_TOTAL;
                break;
            case 8:
                zzne = zzne.CHALLENGE_ACCOUNT_NATIVE;
                break;
            case 9:
                zzne = zzne.CHALLENGE_ACCOUNT_JS;
                break;
            case 10:
                zzne = zzne.CHALLENGE_ACCOUNT_TOTAL;
                break;
            case 11:
                zzne = zzne.VERIFY_PIN_NATIVE;
                break;
            case 12:
                zzne = zzne.VERIFY_PIN_JS;
                break;
            case 13:
                zzne = zzne.VERIFY_PIN_TOTAL;
                break;
            case 14:
                zzne = zzne.RUN_PROGRAM;
                break;
            case 15:
                zzne = zzne.FETCH_ALLOWLIST;
                break;
            case 16:
                zzne = zzne.JS_LOAD;
                break;
            case 17:
                zzne = zzne.WEB_VIEW_RELOAD_JS;
                break;
            case 18:
                zzne = zzne.INIT_NETWORK_MRI_ACTION;
                break;
            case 19:
                zzne = zzne.INIT_DOWNLOAD_JS;
                break;
            case 20:
                zzne = zzne.VALIDATE_INPUT;
                break;
            case 21:
                zzne = zzne.DOWNLOAD_JS;
                break;
            case 22:
                zzne = zzne.SAVE_CACHE_JS;
                break;
            case 23:
                zzne = zzne.LOAD_CACHE_JS;
                break;
            case 24:
                zzne = zzne.LOAD_WEBVIEW;
                break;
            case 25:
                zzne = zzne.COLLECT_SIGNALS;
                break;
            case 26:
                zzne = zzne.FETCH_TOKEN;
                break;
            case 27:
                zzne = zzne.POST_EXECUTE;
                break;
            default:
                zzne = null;
                break;
        }
        return zzne == null ? zzne.UNRECOGNIZED : zzne;
    }
}
