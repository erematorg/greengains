package com.kenai.jffi;

import A.a;
import androidx.core.os.EnvironmentCompat;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public abstract class Platform {
    /* access modifiers changed from: private */
    public static final Locale LOCALE = Locale.ENGLISH;
    private final int javaVersionMajor;
    private final OS os;

    /* renamed from: com.kenai.jffi.Platform$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$kenai$jffi$Platform$OS;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.kenai.jffi.Platform$OS[] r0 = com.kenai.jffi.Platform.OS.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$kenai$jffi$Platform$OS = r0
                com.kenai.jffi.Platform$OS r1 = com.kenai.jffi.Platform.OS.DARWIN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$kenai$jffi$Platform$OS     // Catch:{ NoSuchFieldError -> 0x001d }
                com.kenai.jffi.Platform$OS r1 = com.kenai.jffi.Platform.OS.WINDOWS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Platform.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class ArchHolder {
        public static final CPU cpu = determineCPU();

        private ArchHolder() {
        }

        private static CPU determineCPU() {
            String str;
            try {
                str = Foreign.getInstance().getArch();
            } catch (UnsatisfiedLinkError unused) {
                str = null;
            }
            if (str == null || EnvironmentCompat.MEDIA_UNKNOWN.equals(str)) {
                str = System.getProperty("os.arch", EnvironmentCompat.MEDIA_UNKNOWN);
            }
            if (Util.equalsIgnoreCase("x86", str, Platform.LOCALE) || Util.equalsIgnoreCase("i386", str, Platform.LOCALE) || Util.equalsIgnoreCase("i86pc", str, Platform.LOCALE)) {
                return CPU.I386;
            }
            if (Util.equalsIgnoreCase("x86_64", str, Platform.LOCALE) || Util.equalsIgnoreCase("amd64", str, Platform.LOCALE)) {
                return CPU.X86_64;
            }
            if (Util.equalsIgnoreCase("ppc", str, Platform.LOCALE) || Util.equalsIgnoreCase("powerpc", str, Platform.LOCALE)) {
                return CPU.PPC;
            }
            if (Util.equalsIgnoreCase("ppc64", str, Platform.LOCALE) || Util.equalsIgnoreCase("powerpc64", str, Platform.LOCALE)) {
                return CPU.PPC64;
            }
            if (Util.equalsIgnoreCase("ppc64le", str, Platform.LOCALE) || Util.equalsIgnoreCase("powerpc64le", str, Platform.LOCALE)) {
                return CPU.PPC64LE;
            }
            if (Util.equalsIgnoreCase("s390", str, Platform.LOCALE) || Util.equalsIgnoreCase("s390x", str, Platform.LOCALE)) {
                return CPU.S390X;
            }
            if (Util.equalsIgnoreCase("arm", str, Platform.LOCALE) || Util.equalsIgnoreCase("armv7l", str, Platform.LOCALE)) {
                return CPU.ARM;
            }
            if (Util.equalsIgnoreCase("aarch64", str, Platform.LOCALE)) {
                return CPU.AARCH64;
            }
            if (Util.equalsIgnoreCase("mips64", str, Platform.LOCALE) || Util.equalsIgnoreCase("mips64el", str, Platform.LOCALE)) {
                return CPU.MIPS64EL;
            }
            for (CPU cpu2 : CPU.values()) {
                if (cpu2.name().equalsIgnoreCase(str)) {
                    return cpu2;
                }
            }
            return CPU.UNKNOWN;
        }
    }

    public enum CPU {
        I386(32),
        X86_64(64),
        PPC(32),
        PPC64(64),
        PPC64LE(64),
        SPARC(32),
        SPARCV9(64),
        S390X(64),
        ARM(32),
        AARCH64(64),
        MIPS64EL(64),
        UNKNOWN(64);
        
        public final long addressMask;
        public final int dataModel;

        private CPU(int i3) {
            this.dataModel = i3;
            this.addressMask = i3 == 32 ? 4294967295L : -1;
        }

        public String toString() {
            return name().toLowerCase(Platform.LOCALE);
        }
    }

    public static final class Darwin extends Platform {
        public Darwin() {
            super(OS.DARWIN, (AnonymousClass1) null);
        }

        public String getLibraryNamePattern() {
            return "lib.*\\.(dylib|jnilib)$";
        }

        public String getName() {
            return "Darwin";
        }

        public final int longSize() {
            return getCPU().dataModel;
        }

        public String mapLibraryName(String str) {
            return str.matches(getLibraryNamePattern()) ? str : a.l("lib", str, ".dylib");
        }
    }

    public static final class Default extends Platform {
        public Default(OS os) {
            super(os, (AnonymousClass1) null);
        }

        public final int longSize() {
            return getCPU().dataModel;
        }
    }

    public enum OS {
        DARWIN,
        FREEBSD,
        NETBSD,
        OPENBSD,
        DRAGONFLY,
        LINUX,
        SOLARIS,
        WINDOWS,
        AIX,
        IBMI,
        ZLINUX,
        UNKNOWN;

        public String toString() {
            return name().toLowerCase(Platform.LOCALE);
        }
    }

    public static final class SingletonHolder {
        static final Platform PLATFORM = Platform.determinePlatform(Platform.determineOS());

        private SingletonHolder() {
        }
    }

    public static final class Windows extends Platform {
        public Windows() {
            super(OS.WINDOWS, (AnonymousClass1) null);
        }

        public String getLibraryNamePattern() {
            return ".*\\.dll$";
        }

        public final int longSize() {
            return 32;
        }
    }

    public /* synthetic */ Platform(OS os2, AnonymousClass1 r2) {
        this(os2);
    }

    /* access modifiers changed from: private */
    public static final OS determineOS() {
        String str = System.getProperty("os.name").split(StringUtils.SPACE)[0];
        return (startsWithIgnoreCase(str, "mac") || startsWithIgnoreCase(str, "darwin")) ? OS.DARWIN : startsWithIgnoreCase(str, "linux") ? OS.LINUX : (startsWithIgnoreCase(str, "sunos") || startsWithIgnoreCase(str, "solaris")) ? OS.SOLARIS : startsWithIgnoreCase(str, "aix") ? OS.AIX : (startsWithIgnoreCase(str, "os/400") || startsWithIgnoreCase(str, "os400")) ? OS.IBMI : startsWithIgnoreCase(str, "openbsd") ? OS.OPENBSD : startsWithIgnoreCase(str, "freebsd") ? OS.FREEBSD : startsWithIgnoreCase(str, "dragonfly") ? OS.DRAGONFLY : startsWithIgnoreCase(str, "windows") ? OS.WINDOWS : OS.UNKNOWN;
    }

    /* access modifiers changed from: private */
    public static final Platform determinePlatform(OS os2) {
        int i3 = AnonymousClass1.$SwitchMap$com$kenai$jffi$Platform$OS[os2.ordinal()];
        return i3 != 1 ? i3 != 2 ? newDefaultPlatform(os2) : newWindowsPlatform() : newDarwinPlatform();
    }

    public static final Platform getPlatform() {
        return SingletonHolder.PLATFORM;
    }

    private static Platform newDarwinPlatform() {
        return new Darwin();
    }

    private static Platform newDefaultPlatform(OS os2) {
        return new Default(os2);
    }

    private static Platform newWindowsPlatform() {
        return new Windows();
    }

    private static boolean startsWithIgnoreCase(String str, String str2) {
        if (!str.startsWith(str2)) {
            Locale locale = LOCALE;
            return str.toUpperCase(locale).startsWith(str2.toUpperCase(locale)) || str.toLowerCase(locale).startsWith(str2.toLowerCase(locale));
        }
    }

    public final long addressMask() {
        return getCPU().addressMask;
    }

    public final int addressSize() {
        return getCPU().dataModel;
    }

    public final CPU getCPU() {
        return ArchHolder.cpu;
    }

    public final int getJavaMajorVersion() {
        return this.javaVersionMajor;
    }

    public String getLibraryNamePattern() {
        return "lib.*\\.so.*$";
    }

    public String getName() {
        String str = System.getProperty("os.name").split(StringUtils.SPACE)[0];
        return getCPU().name().toLowerCase(LOCALE) + "-" + str;
    }

    public final OS getOS() {
        return this.os;
    }

    public boolean isSupported() {
        if ((Foreign.getInstance().getVersion() & 16776960) == ((Foreign.VERSION_MAJOR << 16) | (Foreign.VERSION_MINOR << 8))) {
            return true;
        }
        throw new UnsatisfiedLinkError("Incorrect native library version");
    }

    public abstract int longSize();

    public String mapLibraryName(String str) {
        return str.matches(getLibraryNamePattern()) ? str : OS.IBMI.equals(getOS()) ? a.l("lib", str, ".so") : System.mapLibraryName(str);
    }

    private Platform(OS os2) {
        this.os = os2;
        int i3 = 8;
        try {
            String property = System.getProperty("java.version");
            if (property != null) {
                String str = property.split("[^0-9.]")[0];
                int indexOf = str.indexOf(46);
                i3 = Integer.valueOf(indexOf != -1 ? str.substring(indexOf + 1) : str).intValue();
            }
        } catch (Exception unused) {
        }
        this.javaVersionMajor = i3;
    }
}
