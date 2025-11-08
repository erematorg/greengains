package com.google.common.io;

import androidx.camera.camera2.internal.C0118y;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
abstract class TempFileCreator {
    static final TempFileCreator INSTANCE = pickSecureCreator();

    public static final class JavaIoCreator extends TempFileCreator {
        private static final int TEMP_DIR_ATTEMPTS = 10000;

        private JavaIoCreator() {
            super();
        }

        public File createTempDir() {
            File file = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value());
            String str = System.currentTimeMillis() + "-";
            for (int i3 = 0; i3 < 10000; i3++) {
                File file2 = new File(file, str + i3);
                if (file2.mkdir()) {
                    return file2;
                }
            }
            throw new IllegalStateException(C0118y.g("Failed to create directory within 10000 attempts (tried ", str, "0 to ", str, "9999)"));
        }

        public File createTempFile(String str) throws IOException {
            return File.createTempFile(str, (String) null, (File) null);
        }
    }

    @IgnoreJRERequirement
    public static final class JavaNioCreator extends TempFileCreator {
        private static final PermissionSupplier directoryPermissions;
        private static final PermissionSupplier filePermissions;

        @IgnoreJRERequirement
        public interface PermissionSupplier {
            FileAttribute<?> get() throws IOException;
        }

        static {
            Set<String> supportedFileAttributeViews = FileSystems.getDefault().supportedFileAttributeViews();
            if (supportedFileAttributeViews.contains("posix")) {
                filePermissions = new a(0);
                directoryPermissions = new a(1);
            } else if (supportedFileAttributeViews.contains("acl")) {
                PermissionSupplier userPermissions = userPermissions();
                directoryPermissions = userPermissions;
                filePermissions = userPermissions;
            } else {
                a aVar = new a(2);
                directoryPermissions = aVar;
                filePermissions = aVar;
            }
        }

        private JavaNioCreator() {
            super();
        }

        private static String getUsername() {
            String value = StandardSystemProperty.USER_NAME.value();
            Objects.requireNonNull(value);
            try {
                Class<?> cls = Class.forName("java.lang.ProcessHandle");
                Class<?> cls2 = Class.forName("java.lang.ProcessHandle$Info");
                Class<?> cls3 = Class.forName("java.util.Optional");
                Method method = cls.getMethod("current", (Class[]) null);
                Method method2 = cls.getMethod("info", (Class[]) null);
                Object invoke = cls3.getMethod("orElse", new Class[]{Object.class}).invoke(cls2.getMethod("user", (Class[]) null).invoke(method2.invoke(method.invoke((Object) null, (Object[]) null), (Object[]) null), (Object[]) null), new Object[]{value});
                Objects.requireNonNull(invoke);
                return (String) invoke;
            } catch (ClassNotFoundException unused) {
                return value;
            } catch (InvocationTargetException e3) {
                Throwables.throwIfUnchecked(e3.getCause());
                return value;
            } catch (IllegalAccessException | NoSuchMethodException unused2) {
                return value;
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ FileAttribute lambda$static$2() throws IOException {
            throw new IOException("unrecognized FileSystem type " + FileSystems.getDefault());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ FileAttribute lambda$userPermissions$3(FileAttribute fileAttribute) throws IOException {
            return fileAttribute;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ FileAttribute lambda$userPermissions$4(IOException iOException) throws IOException {
            throw new IOException("Could not find user", iOException);
        }

        /* access modifiers changed from: private */
        public static PermissionSupplier userPermissions() {
            try {
                final ImmutableList of = ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(getUsername())).setPermissions(EnumSet.allOf(AclEntryPermission.class)).setFlags(new AclEntryFlag[]{AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT}).build());
                return new b(new FileAttribute<ImmutableList<AclEntry>>() {
                    public String name() {
                        return "acl:acl";
                    }

                    public ImmutableList<AclEntry> value() {
                        return ImmutableList.this;
                    }
                }, 0);
            } catch (IOException e3) {
                return new b(e3, 1);
            }
        }

        public File createTempDir() {
            try {
                return Files.createTempDirectory(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), (String) null, new FileAttribute[]{directoryPermissions.get()}).toFile();
            } catch (IOException e3) {
                throw new IllegalStateException("Failed to create directory", e3);
            }
        }

        public File createTempFile(String str) throws IOException {
            return Files.createTempFile(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), str, (String) null, new FileAttribute[]{filePermissions.get()}).toFile();
        }
    }

    public static final class ThrowingCreator extends TempFileCreator {
        private static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

        private ThrowingCreator() {
            super();
        }

        public File createTempDir() {
            throw new IllegalStateException(MESSAGE);
        }

        public File createTempFile(String str) throws IOException {
            throw new IOException(MESSAGE);
        }
    }

    private static TempFileCreator pickSecureCreator() {
        try {
            return new JavaNioCreator();
        } catch (ClassNotFoundException unused) {
            try {
                return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null)).intValue() < ((Integer) Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get((Object) null)).intValue() ? new ThrowingCreator() : new JavaIoCreator();
            } catch (NoSuchFieldException unused2) {
                return new ThrowingCreator();
            } catch (ClassNotFoundException unused3) {
                return new ThrowingCreator();
            } catch (IllegalAccessException unused4) {
                return new ThrowingCreator();
            }
        }
    }

    @IgnoreJRERequirement
    @VisibleForTesting
    public static void testMakingUserPermissionsFromScratch() throws IOException {
        JavaNioCreator.userPermissions().get();
    }

    public abstract File createTempDir();

    public abstract File createTempFile(String str) throws IOException;

    private TempFileCreator() {
    }
}
