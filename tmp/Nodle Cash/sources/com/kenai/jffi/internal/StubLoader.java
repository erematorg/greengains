package com.kenai.jffi.internal;

import A.a;
import androidx.browser.trusted.c;
import androidx.core.os.EnvironmentCompat;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Util;
import com.kenai.jffi.Version;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class StubLoader {
    private static final String JFFI_EXTRACT_DIR = "jffi.extract.dir";
    private static final String JFFI_EXTRACT_NAME = "jffi.extract.name";
    /* access modifiers changed from: private */
    public static final Locale LOCALE = Locale.ENGLISH;
    private static final String TMPDIR;
    private static final String TMPDIR_ENV;
    public static final String TMPDIR_EXEC_ERROR;
    private static final String TMPDIR_RECOMMENDATION;
    public static final String TMPDIR_WRITE_ERROR;
    public static final int VERSION_MAJOR;
    public static final int VERSION_MINOR;
    private static final String bootLibraryPropertyName = "jffi.boot.library.path";
    private static final String bootPropertyFilename = "boot.properties";
    private static volatile CPU cpu = null;
    private static volatile Throwable failureCause = null;
    private static final File jffiExtractDir;
    private static final String jffiExtractName;
    private static volatile boolean loaded = false;
    private static volatile OS os = null;
    private static final String stubLibraryName;
    private static final String versionClassName = "com.kenai.jffi.Version";

    /* renamed from: com.kenai.jffi.internal.StubLoader$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$kenai$jffi$internal$StubLoader$OS;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.kenai.jffi.internal.StubLoader$OS[] r0 = com.kenai.jffi.internal.StubLoader.OS.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$kenai$jffi$internal$StubLoader$OS = r0
                com.kenai.jffi.internal.StubLoader$OS r1 = com.kenai.jffi.internal.StubLoader.OS.WINDOWS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$kenai$jffi$internal$StubLoader$OS     // Catch:{ NoSuchFieldError -> 0x001d }
                com.kenai.jffi.internal.StubLoader$OS r1 = com.kenai.jffi.internal.StubLoader.OS.DARWIN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.internal.StubLoader.AnonymousClass1.<clinit>():void");
        }
    }

    public enum CPU {
        I386,
        X86_64,
        PPC,
        PPC64,
        PPC64LE,
        SPARC,
        SPARCV9,
        S390X,
        ARM,
        AARCH64,
        MIPS64EL,
        UNKNOWN;

        public String toString() {
            return name().toLowerCase(StubLoader.LOCALE);
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
            return name().toLowerCase(StubLoader.LOCALE);
        }
    }

    static {
        int versionField = getVersionField("MAJOR");
        VERSION_MAJOR = versionField;
        int versionField2 = getVersionField("MINOR");
        VERSION_MINOR = versionField2;
        stubLibraryName = String.format("jffi-%d.%d", new Object[]{Integer.valueOf(versionField), Integer.valueOf(versionField2)});
        String str = Platform.getPlatform().getOS() == Platform.OS.WINDOWS ? "TEMP" : "TMPDIR";
        TMPDIR_ENV = str;
        String property = System.getProperty("java.io.tmpdir");
        TMPDIR = property;
        TMPDIR_RECOMMENDATION = a.l("Set `", str, "` or Java property `java.io.tmpdir` to a read/write path that is not mounted \"noexec\".");
        TMPDIR_WRITE_ERROR = a.l("Unable to write jffi binary stub to `", property, "`.");
        TMPDIR_EXEC_ERROR = a.l("Unable to execute or load jffi binary stub from `", property, "`.");
        failureCause = null;
        loaded = false;
        String property2 = System.getProperty(JFFI_EXTRACT_DIR);
        if (property2 != null) {
            jffiExtractDir = new File(property2);
        } else {
            jffiExtractDir = null;
        }
        String property3 = System.getProperty(JFFI_EXTRACT_NAME);
        if (property3 != null) {
            jffiExtractName = property3;
        } else {
            jffiExtractName = null;
        }
        try {
            load();
            loaded = true;
        } catch (Throwable th) {
            failureCause = th;
        }
    }

    public static File calculateExtractPath(File file, String str) throws IOException {
        if (str == null) {
            return calculateExtractPath(file);
        }
        if (str.isEmpty()) {
            str = "jffi-" + VERSION_MAJOR + JwtUtilsKt.JWT_DELIMITER + VERSION_MINOR;
        }
        if (!str.endsWith(dlExtension())) {
            StringBuilder q2 = a.q(str, JwtUtilsKt.JWT_DELIMITER);
            q2.append(dlExtension());
            str = q2.toString();
        }
        if (file == null) {
            return new File(TMPDIR, str);
        }
        return new File(file, str);
    }

    private static CPU determineCPU() {
        String property = System.getProperty("os.arch", EnvironmentCompat.MEDIA_UNKNOWN);
        Locale locale = LOCALE;
        if (Util.equalsIgnoreCase("x86", property, locale) || Util.equalsIgnoreCase("i386", property, locale) || Util.equalsIgnoreCase("i86pc", property, locale)) {
            return CPU.I386;
        }
        if (Util.equalsIgnoreCase("x86_64", property, locale) || Util.equalsIgnoreCase("amd64", property, locale)) {
            return CPU.X86_64;
        }
        if (Util.equalsIgnoreCase("ppc", property, locale) || Util.equalsIgnoreCase("powerpc", property, locale)) {
            return CPU.PPC;
        }
        if (Util.equalsIgnoreCase("ppc64", property, locale) || Util.equalsIgnoreCase("powerpc64", property, locale)) {
            return "little".equals(System.getProperty("sun.cpu.endian")) ? CPU.PPC64LE : CPU.PPC64;
        }
        if (Util.equalsIgnoreCase("ppc64le", property, locale) || Util.equalsIgnoreCase("powerpc64le", property, locale)) {
            return CPU.PPC64LE;
        }
        if (Util.equalsIgnoreCase("s390", property, locale) || Util.equalsIgnoreCase("s390x", property, locale)) {
            return CPU.S390X;
        }
        if (Util.equalsIgnoreCase("arm", property, locale) || Util.equalsIgnoreCase("armv7l", property, locale)) {
            return CPU.ARM;
        }
        if (Util.equalsIgnoreCase("aarch64", property, locale)) {
            return CPU.AARCH64;
        }
        if (Util.equalsIgnoreCase("mips64", property, locale) || Util.equalsIgnoreCase("mips64el", property, locale)) {
            return CPU.MIPS64EL;
        }
        for (CPU cpu2 : CPU.values()) {
            if (Util.equalsIgnoreCase(cpu2.name(), property, LOCALE)) {
                return cpu2;
            }
        }
        throw new RuntimeException("cannot determine CPU");
    }

    private static OS determineOS() {
        String str = System.getProperty("os.name").split(StringUtils.SPACE)[0];
        Locale locale = LOCALE;
        if (Util.startsWithIgnoreCase(str, "mac", locale) || Util.startsWithIgnoreCase(str, "darwin", locale)) {
            return OS.DARWIN;
        }
        if (Util.startsWithIgnoreCase(str, "linux", locale)) {
            return OS.LINUX;
        }
        if (Util.startsWithIgnoreCase(str, "sunos", locale) || Util.startsWithIgnoreCase(str, "solaris", locale)) {
            return OS.SOLARIS;
        }
        if (Util.startsWithIgnoreCase(str, "aix", locale)) {
            return OS.AIX;
        }
        if (Util.startsWithIgnoreCase(str, "os400", locale) || Util.startsWithIgnoreCase(str, "os/400", locale)) {
            return OS.IBMI;
        }
        if (Util.startsWithIgnoreCase(str, "openbsd", locale)) {
            return OS.OPENBSD;
        }
        if (Util.startsWithIgnoreCase(str, "freebsd", locale)) {
            return OS.FREEBSD;
        }
        if (Util.startsWithIgnoreCase(str, "dragonfly", locale)) {
            return OS.DRAGONFLY;
        }
        if (Util.startsWithIgnoreCase(str, "windows", locale)) {
            return OS.WINDOWS;
        }
        throw new RuntimeException("cannot determine operating system");
    }

    private static SecurityException digestMismatchError(File file) {
        return new SecurityException("digest mismatch: " + file + " does not match packaged library");
    }

    public static String dlExtension() {
        int i3 = AnonymousClass1.$SwitchMap$com$kenai$jffi$internal$StubLoader$OS[getOS().ordinal()];
        return i3 != 1 ? i3 != 2 ? "so" : "dylib" : "dll";
    }

    private static String getAlternateLibraryPath(String str) {
        if (str.endsWith("dylib")) {
            return str.substring(0, str.lastIndexOf("dylib")) + "jnilib";
        }
        return str.substring(0, str.lastIndexOf("jnilib")) + "dylib";
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getBootPath() {
        /*
            java.lang.String r0 = "jffi.boot.library.path"
            java.lang.String r1 = java.lang.System.getProperty(r0)
            if (r1 == 0) goto L_0x0009
            return r1
        L_0x0009:
            java.lang.String r1 = "boot.properties"
            java.io.InputStream r1 = getResourceAsStream(r1)
            r2 = 0
            if (r1 == 0) goto L_0x002a
            java.util.Properties r3 = new java.util.Properties
            r3.<init>()
            r3.load(r1)     // Catch:{ IOException -> 0x0027, all -> 0x0022 }
            java.lang.String r0 = r3.getProperty(r0)     // Catch:{ IOException -> 0x0027, all -> 0x0022 }
            r1.close()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            return r0
        L_0x0022:
            r0 = move-exception
            r1.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0026:
            throw r0
        L_0x0027:
            r1.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.internal.StubLoader.getBootPath():java.lang.String");
    }

    public static CPU getCPU() {
        if (cpu != null) {
            return cpu;
        }
        CPU determineCPU = determineCPU();
        cpu = determineCPU;
        return determineCPU;
    }

    public static final Throwable getFailureCause() {
        return failureCause;
    }

    public static OS getOS() {
        if (os != null) {
            return os;
        }
        OS determineOS = determineOS();
        os = determineOS;
        return determineOS;
    }

    public static String getPlatformName() {
        if (getOS().equals(OS.DARWIN)) {
            return "Darwin";
        }
        String str = System.getProperty("os.name").split(StringUtils.SPACE)[0];
        return getCPU().name().toLowerCase(LOCALE) + "-" + str;
    }

    private static InputStream getResourceAsStream(String str) {
        InputStream resourceAsStream;
        ClassLoader[] classLoaderArr = {ClassLoader.getSystemClassLoader(), StubLoader.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        for (int i3 = 0; i3 < 3; i3++) {
            ClassLoader classLoader = classLoaderArr[i3];
            if (classLoader != null && (resourceAsStream = classLoader.getResourceAsStream(str)) != null) {
                return resourceAsStream;
            }
        }
        return null;
    }

    private static String getStubLibraryName() {
        return stubLibraryName;
    }

    private static String getStubLibraryPath() {
        String n2 = OS.IBMI.equals(getOS()) ? a.n(new StringBuilder("lib"), stubLibraryName, ".so") : System.mapLibraryName(stubLibraryName);
        return "jni/" + getPlatformName() + "/" + n2;
    }

    private static InputStream getStubLibraryStream() {
        String stubLibraryPath = getStubLibraryPath();
        String[] strArr = {stubLibraryPath, c.a("/", stubLibraryPath)};
        for (int i3 = 0; i3 < 2; i3++) {
            String str = strArr[i3];
            InputStream resourceAsStream = getResourceAsStream(str);
            if (resourceAsStream == null && getOS() == OS.DARWIN) {
                resourceAsStream = getResourceAsStream(getAlternateLibraryPath(str));
            }
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
        }
        throw new UnsatisfiedLinkError("could not locate stub library in jar file.  Tried " + Arrays.deepToString(strArr));
    }

    private static int getVersionField(String str) {
        Class<Version> cls = Version.class;
        try {
            return ((Integer) cls.getField(str).get(cls)).intValue();
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public static final boolean isLoaded() {
        return loaded;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void load() {
        /*
            java.lang.String r0 = getStubLibraryName()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = getBootPath()
            if (r2 == 0) goto L_0x0016
            boolean r2 = loadFromBootPath(r0, r2, r1)
            if (r2 == 0) goto L_0x0016
            return
        L_0x0016:
            java.lang.String r2 = "java.library.path"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            if (r2 == 0) goto L_0x0025
            boolean r0 = loadFromBootPath(r0, r2, r1)
            if (r0 == 0) goto L_0x0025
            return
        L_0x0025:
            java.io.File r0 = jffiExtractDir
            if (r0 == 0) goto L_0x0049
            loadFromJar(r0)     // Catch:{ SecurityException -> 0x0047, all -> 0x002d }
            return
        L_0x002d:
            r0 = move-exception
            java.lang.UnsatisfiedLinkError r1 = new java.lang.UnsatisfiedLinkError
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "could not load jffi library from "
            r2.<init>(r3)
            java.io.File r3 = jffiExtractDir
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            r1.initCause(r0)
            throw r1
        L_0x0047:
            r0 = move-exception
            throw r0
        L_0x0049:
            r0 = 0
            loadFromJar(r0)     // Catch:{ SecurityException -> 0x009c, all -> 0x004e }
            return
        L_0x004e:
            java.io.File r0 = new java.io.File     // Catch:{ SecurityException -> 0x005f, all -> 0x005d }
            java.lang.String r2 = "user.dir"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch:{ SecurityException -> 0x005f, all -> 0x005d }
            r0.<init>(r2)     // Catch:{ SecurityException -> 0x005f, all -> 0x005d }
            loadFromJar(r0)     // Catch:{ SecurityException -> 0x005f, all -> 0x005d }
            goto L_0x0064
        L_0x005d:
            r0 = move-exception
            goto L_0x0061
        L_0x005f:
            r0 = move-exception
            goto L_0x009b
        L_0x0061:
            r1.add(r0)
        L_0x0064:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x009a
            java.util.Collections.reverse(r1)
            java.io.CharArrayWriter r0 = new java.io.CharArrayWriter
            r0.<init>()
            java.io.PrintWriter r2 = new java.io.PrintWriter
            r2.<init>(r0)
            java.util.Iterator r1 = r1.iterator()
        L_0x007b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x008b
            java.lang.Object r3 = r1.next()
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r3.printStackTrace(r2)
            goto L_0x007b
        L_0x008b:
            java.lang.UnsatisfiedLinkError r1 = new java.lang.UnsatisfiedLinkError
            java.lang.String r2 = new java.lang.String
            char[] r0 = r0.toCharArray()
            r2.<init>(r0)
            r1.<init>(r2)
            throw r1
        L_0x009a:
            return
        L_0x009b:
            throw r0
        L_0x009c:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.internal.StubLoader.load():void");
    }

    private static boolean loadFromBootPath(String str, String str2, Collection<Throwable> collection) {
        String[] split = str2.split(File.pathSeparator);
        for (int i3 = 0; i3 < split.length; i3++) {
            String mapLibraryName = System.mapLibraryName(str);
            File file = new File(new File(split[i3], getPlatformName()), mapLibraryName);
            if (!file.isFile()) {
                file = new File(new File(split[i3]), mapLibraryName);
            }
            String absolutePath = file.getAbsolutePath();
            if (file.isFile()) {
                try {
                    System.load(absolutePath);
                    return true;
                } catch (UnsatisfiedLinkError e3) {
                    collection.add(e3);
                }
            }
            if (getOS() == OS.DARWIN) {
                String alternateLibraryPath = getAlternateLibraryPath(absolutePath);
                if (new File(alternateLibraryPath).isFile()) {
                    try {
                        System.load(alternateLibraryPath);
                        return true;
                    } catch (UnsatisfiedLinkError e4) {
                        collection.add(e4);
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    private static void loadFromJar(File file) throws IOException, LinkageError {
        InputStream stubLibraryStream;
        String str = jffiExtractName;
        try {
            stubLibraryStream = getStubLibraryStream();
            File calculateExtractPath = calculateExtractPath(file, str);
            if (str == null || !calculateExtractPath.exists()) {
                unpackLibrary(calculateExtractPath, stubLibraryStream);
            } else {
                verifyExistingLibrary(calculateExtractPath, stubLibraryStream);
            }
            if (stubLibraryStream != null) {
                stubLibraryStream.close();
            }
            try {
                System.load(calculateExtractPath.getAbsolutePath());
                if (str == null) {
                    calculateExtractPath.delete();
                    return;
                }
                return;
            } catch (UnsatisfiedLinkError e3) {
                throw tempLoadError(e3);
            }
        } catch (IOException e4) {
            throw tempReadonlyError(e4);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static SecurityException sizeMismatchError(File file, int i3, int i4) {
        StringBuilder sb = new StringBuilder("file size mismatch: ");
        sb.append(file);
        sb.append(" (");
        sb.append(i4);
        sb.append(") does not match packaged library (");
        return new SecurityException(a.m(sb, ")", i3));
    }

    private static UnsatisfiedLinkError tempLoadError(UnsatisfiedLinkError unsatisfiedLinkError) {
        return new UnsatisfiedLinkError(TMPDIR_EXEC_ERROR + StringUtils.SPACE + TMPDIR_RECOMMENDATION + "\n" + unsatisfiedLinkError.getLocalizedMessage());
    }

    private static IOException tempReadonlyError(IOException iOException) {
        return new IOException(TMPDIR_WRITE_ERROR + StringUtils.SPACE + TMPDIR_RECOMMENDATION, iOException);
    }

    private static void unpackLibrary(File file, InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            ReadableByteChannel newChannel = Channels.newChannel(inputStream);
            long j2 = 0;
            while (inputStream.available() > 0) {
                j2 += fileOutputStream.getChannel().transferFrom(newChannel, j2, (long) Math.max(4096, inputStream.available()));
            }
            fileOutputStream.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static void verifyExistingLibrary(File file, InputStream inputStream) throws IOException {
        FileInputStream fileInputStream;
        int available = inputStream.available();
        try {
            fileInputStream = new FileInputStream(file);
            int available2 = fileInputStream.available();
            if (available2 == available) {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                MessageDigest instance2 = MessageDigest.getInstance("SHA-256");
                DigestInputStream digestInputStream = new DigestInputStream(inputStream, instance);
                DigestInputStream digestInputStream2 = new DigestInputStream(fileInputStream, instance2);
                byte[] bArr = new byte[8192];
                while (inputStream.available() > 0) {
                    digestInputStream.read(bArr);
                    digestInputStream2.read(bArr);
                }
                if (Arrays.equals(instance.digest(), instance2.digest())) {
                    fileInputStream.close();
                    return;
                }
                throw digestMismatchError(file);
            }
            throw sizeMismatchError(file, available, available2);
        } catch (NoSuchAlgorithmException e3) {
            throw new IOException(e3);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static File calculateExtractPath(File file) throws IOException {
        File file2;
        if (file == null) {
            file2 = File.createTempFile("jffi", JwtUtilsKt.JWT_DELIMITER + dlExtension());
        } else {
            file2 = File.createTempFile("jffi", JwtUtilsKt.JWT_DELIMITER + dlExtension(), file);
        }
        file2.deleteOnExit();
        return file2;
    }
}
