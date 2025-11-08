package com.google.firebase.logger;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.browser.trusted.c;
import androidx.compose.animation.core.a;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000f\b&\u0018\u0000 %2\u00020\u0001:\u0004$%&'B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ9\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0017\"\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J9\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0017\"\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J9\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0017\"\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J9\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00032\u0010\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H&¢\u0006\u0002\u0010 J;\u0010!\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00032\u0012\b\u0002\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010 J9\u0010\"\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0017\"\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001c\u0010\"\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J9\u0010#\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0017\"\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001c\u0010#\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {"Lcom/google/firebase/logger/Logger;", "", "tag", "", "enabled", "", "minLevel", "Lcom/google/firebase/logger/Logger$Level;", "(Ljava/lang/String;ZLcom/google/firebase/logger/Logger$Level;)V", "getEnabled", "()Z", "setEnabled", "(Z)V", "getMinLevel", "()Lcom/google/firebase/logger/Logger$Level;", "setMinLevel", "(Lcom/google/firebase/logger/Logger$Level;)V", "getTag", "()Ljava/lang/String;", "debug", "", "format", "args", "", "throwable", "", "(Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)I", "msg", "error", "info", "log", "level", "(Lcom/google/firebase/logger/Logger$Level;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)I", "logIfAble", "verbose", "warn", "AndroidLogger", "Companion", "FakeLogger", "Level", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLogger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger\n+ 2 ArrayIntrinsics.kt\nkotlin/ArrayIntrinsicsKt\n*L\n1#1,196:1\n26#2:197\n*S KotlinDebug\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger\n*L\n78#1:197\n*E\n"})
public abstract class Logger {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ConcurrentHashMap<String, Logger> loggers = new ConcurrentHashMap<>();
    private boolean enabled;
    @NotNull
    private Level minLevel;
    @NotNull
    private final String tag;

    @SourceDebugExtension({"SMAP\nLogger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger$AndroidLogger\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,196:1\n1#2:197\n*E\n"})
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ9\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0010\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/logger/Logger$AndroidLogger;", "Lcom/google/firebase/logger/Logger;", "tag", "", "enabled", "", "minLevel", "Lcom/google/firebase/logger/Logger$Level;", "(Ljava/lang/String;ZLcom/google/firebase/logger/Logger$Level;)V", "log", "", "level", "format", "args", "", "", "throwable", "", "(Lcom/google/firebase/logger/Logger$Level;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)I", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AndroidLogger extends Logger {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.google.firebase.logger.Logger$Level[] r0 = com.google.firebase.logger.Logger.Level.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.google.firebase.logger.Logger$Level r1 = com.google.firebase.logger.Logger.Level.VERBOSE     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.google.firebase.logger.Logger$Level r1 = com.google.firebase.logger.Logger.Level.DEBUG     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.google.firebase.logger.Logger$Level r1 = com.google.firebase.logger.Logger.Level.INFO     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.google.firebase.logger.Logger$Level r1 = com.google.firebase.logger.Logger.Level.WARN     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.google.firebase.logger.Logger$Level r1 = com.google.firebase.logger.Logger.Level.ERROR     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.logger.Logger.AndroidLogger.WhenMappings.<clinit>():void");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AndroidLogger(@NotNull String str, boolean z2, @NotNull Level level) {
            super(str, z2, level, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
            Intrinsics.checkNotNullParameter(level, "minLevel");
        }

        public int log(@NotNull Level level, @NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
            Intrinsics.checkNotNullParameter(level, FirebaseAnalytics.Param.LEVEL);
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            if (objArr.length != 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
                str = a.t(copyOf, copyOf.length, str, "format(format, *args)");
            }
            int i3 = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i3 == 1) {
                String tag = getTag();
                return th != null ? Log.v(tag, str, th) : Log.v(tag, str);
            } else if (i3 == 2) {
                String tag2 = getTag();
                return th != null ? Log.d(tag2, str, th) : Log.d(tag2, str);
            } else if (i3 == 3) {
                String tag3 = getTag();
                return th != null ? Log.i(tag3, str, th) : Log.i(tag3, str);
            } else if (i3 == 4) {
                String tag4 = getTag();
                return th != null ? Log.w(tag4, str, th) : Log.w(tag4, str);
            } else if (i3 == 5) {
                String tag5 = getTag();
                return th != null ? Log.e(tag5, str, th) : Log.e(tag5, str);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @SourceDebugExtension({"SMAP\nLogger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger$Companion\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,196:1\n73#2,2:197\n1#3:199\n*S KotlinDebug\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger$Companion\n*L\n180#1:197,2\n180#1:199\n*E\n"})
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/google/firebase/logger/Logger$Companion;", "", "()V", "loggers", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/google/firebase/logger/Logger;", "getLogger", "tag", "enabled", "", "minLevel", "Lcom/google/firebase/logger/Logger$Level;", "setupFakeLogger", "Lcom/google/firebase/logger/Logger$FakeLogger;", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Logger getLogger$default(Companion companion, String str, boolean z2, Level level, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                z2 = true;
            }
            if ((i3 & 4) != 0) {
                level = Level.INFO;
            }
            return companion.getLogger(str, z2, level);
        }

        public static /* synthetic */ FakeLogger setupFakeLogger$default(Companion companion, String str, boolean z2, Level level, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                z2 = true;
            }
            if ((i3 & 4) != 0) {
                level = Level.DEBUG;
            }
            return companion.setupFakeLogger(str, z2, level);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
            r0 = new com.google.firebase.logger.Logger.AndroidLogger(r2, r3, r4);
         */
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.firebase.logger.Logger getLogger(@org.jetbrains.annotations.NotNull java.lang.String r2, boolean r3, @org.jetbrains.annotations.NotNull com.google.firebase.logger.Logger.Level r4) {
            /*
                r1 = this;
                java.lang.String r1 = "tag"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                java.lang.String r1 = "minLevel"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
                java.util.concurrent.ConcurrentHashMap r1 = com.google.firebase.logger.Logger.loggers
                java.lang.Object r0 = r1.get(r2)
                if (r0 != 0) goto L_0x0021
                com.google.firebase.logger.Logger$AndroidLogger r0 = new com.google.firebase.logger.Logger$AndroidLogger
                r0.<init>(r2, r3, r4)
                java.lang.Object r1 = r1.putIfAbsent(r2, r0)
                if (r1 != 0) goto L_0x0020
                goto L_0x0021
            L_0x0020:
                r0 = r1
            L_0x0021:
                java.lang.String r1 = "loggers.getOrPut(tag) { …tag, enabled, minLevel) }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                com.google.firebase.logger.Logger r0 = (com.google.firebase.logger.Logger) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.logger.Logger.Companion.getLogger(java.lang.String, boolean, com.google.firebase.logger.Logger$Level):com.google.firebase.logger.Logger");
        }

        @JvmStatic
        @NotNull
        @VisibleForTesting
        public final FakeLogger setupFakeLogger(@NotNull String str, boolean z2, @NotNull Level level) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
            Intrinsics.checkNotNullParameter(level, "minLevel");
            FakeLogger fakeLogger = new FakeLogger(str, z2, level);
            Logger.loggers.put(str, fakeLogger);
            return fakeLogger;
        }

        private Companion() {
        }
    }

    @SourceDebugExtension({"SMAP\nLogger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger$FakeLogger\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,196:1\n1747#2,3:197\n1747#2,3:200\n1#3:203\n*S KotlinDebug\n*F\n+ 1 Logger.kt\ncom/google/firebase/logger/Logger$FakeLogger\n*L\n144#1:197,3\n148#1:200,3\n*E\n"})
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0003H\u0007J\u001c\u0010\u000f\u001a\u00020\u00052\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0011H\u0007J9\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00032\u0010\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00180\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0002\u0010\u001bJ9\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00032\u0010\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00180\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010\u001dR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/google/firebase/logger/Logger$FakeLogger;", "Lcom/google/firebase/logger/Logger;", "tag", "", "enabled", "", "minLevel", "Lcom/google/firebase/logger/Logger$Level;", "(Ljava/lang/String;ZLcom/google/firebase/logger/Logger$Level;)V", "record", "", "clearLogMessages", "", "hasLogMessage", "message", "hasLogMessageThat", "predicate", "Lkotlin/Function1;", "log", "", "level", "format", "args", "", "", "throwable", "", "(Lcom/google/firebase/logger/Logger$Level;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)I", "toLogMessage", "(Lcom/google/firebase/logger/Logger$Level;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)Ljava/lang/String;", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @VisibleForTesting
    public static final class FakeLogger extends Logger {
        @NotNull
        private final List<String> record = new ArrayList();

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FakeLogger(@NotNull String str, boolean z2, @NotNull Level level) {
            super(str, z2, level, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
            Intrinsics.checkNotNullParameter(level, "minLevel");
        }

        private final String toLogMessage(Level level, String str, Object[] objArr, Throwable th) {
            if (objArr.length != 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
                str = a.t(copyOf, copyOf.length, str, "format(format, *args)");
            }
            if (th != null) {
                String str2 = level + ' ' + str + ' ' + Log.getStackTraceString(th);
                if (str2 != null) {
                    return str2;
                }
            }
            return level + ' ' + str;
        }

        @VisibleForTesting
        public final void clearLogMessages() {
            this.record.clear();
        }

        @VisibleForTesting
        public final boolean hasLogMessage(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Iterable<String> iterable = this.record;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (String s3 : iterable) {
                if (StringsKt__StringsKt.contains$default((CharSequence) s3, (CharSequence) str, false, 2, (Object) null)) {
                    return true;
                }
            }
            return false;
        }

        @VisibleForTesting
        public final boolean hasLogMessageThat(@NotNull Function1<? super String, Boolean> function1) {
            Intrinsics.checkNotNullParameter(function1, "predicate");
            Iterable<Object> iterable = this.record;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (Object invoke : iterable) {
                if (function1.invoke(invoke).booleanValue()) {
                    return true;
                }
            }
            return false;
        }

        public int log(@NotNull Level level, @NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
            Intrinsics.checkNotNullParameter(level, FirebaseAnalytics.Param.LEVEL);
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            String logMessage = toLogMessage(level, str, objArr, th);
            System.out.println(c.a("Log: ", logMessage));
            this.record.add(logMessage);
            return logMessage.length();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/google/firebase/logger/Logger$Level;", "", "priority", "", "(Ljava/lang/String;II)V", "getPriority$com_google_firebase_firebase_common", "()I", "VERBOSE", "DEBUG", "INFO", "WARN", "ERROR", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Level {
        VERBOSE(2),
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6);
        
        private final int priority;

        private Level(int i3) {
            this.priority = i3;
        }

        public final int getPriority$com_google_firebase_firebase_common() {
            return this.priority;
        }
    }

    public /* synthetic */ Logger(String str, boolean z2, Level level, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z2, level);
    }

    public static /* synthetic */ int debug$default(Logger logger, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                th = null;
            }
            return logger.debug(str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: debug");
    }

    public static /* synthetic */ int error$default(Logger logger, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                th = null;
            }
            return logger.error(str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
    }

    @JvmStatic
    @NotNull
    public static final Logger getLogger(@NotNull String str, boolean z2, @NotNull Level level) {
        return Companion.getLogger(str, z2, level);
    }

    public static /* synthetic */ int info$default(Logger logger, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                th = null;
            }
            return logger.info(str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
    }

    private final int logIfAble(Level level, String str, Object[] objArr, Throwable th) {
        if (!this.enabled || (this.minLevel.getPriority$com_google_firebase_firebase_common() > level.getPriority$com_google_firebase_firebase_common() && !Log.isLoggable(this.tag, level.getPriority$com_google_firebase_firebase_common()))) {
            return 0;
        }
        return log(level, str, objArr, th);
    }

    public static /* synthetic */ int logIfAble$default(Logger logger, Level level, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                objArr = new Object[0];
            }
            return logger.logIfAble(level, str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logIfAble");
    }

    @JvmStatic
    @NotNull
    @VisibleForTesting
    public static final FakeLogger setupFakeLogger(@NotNull String str, boolean z2, @NotNull Level level) {
        return Companion.setupFakeLogger(str, z2, level);
    }

    public static /* synthetic */ int verbose$default(Logger logger, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                th = null;
            }
            return logger.verbose(str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verbose");
    }

    public static /* synthetic */ int warn$default(Logger logger, String str, Object[] objArr, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                th = null;
            }
            return logger.warn(str, objArr, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
    }

    @JvmOverloads
    public final int debug(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return debug$default(this, str, (Throwable) null, 2, (Object) null);
    }

    @JvmOverloads
    public final int error(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return error$default(this, str, (Throwable) null, 2, (Object) null);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    @NotNull
    public final Level getMinLevel() {
        return this.minLevel;
    }

    @NotNull
    public final String getTag() {
        return this.tag;
    }

    @JvmOverloads
    public final int info(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return info$default(this, str, (Throwable) null, 2, (Object) null);
    }

    public abstract int log(@NotNull Level level, @NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th);

    public final void setEnabled(boolean z2) {
        this.enabled = z2;
    }

    public final void setMinLevel(@NotNull Level level) {
        Intrinsics.checkNotNullParameter(level, "<set-?>");
        this.minLevel = level;
    }

    @JvmOverloads
    public final int verbose(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return verbose$default(this, str, (Throwable) null, 2, (Object) null);
    }

    @JvmOverloads
    public final int warn(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return warn$default(this, str, (Throwable) null, 2, (Object) null);
    }

    private Logger(String str, boolean z2, Level level) {
        this.tag = str;
        this.enabled = z2;
        this.minLevel = level;
    }

    public static /* synthetic */ int debug$default(Logger logger, String str, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                th = null;
            }
            return logger.debug(str, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: debug");
    }

    public static /* synthetic */ int error$default(Logger logger, String str, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                th = null;
            }
            return logger.error(str, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
    }

    public static /* synthetic */ int info$default(Logger logger, String str, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                th = null;
            }
            return logger.info(str, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
    }

    public static /* synthetic */ int verbose$default(Logger logger, String str, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                th = null;
            }
            return logger.verbose(str, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verbose");
    }

    public static /* synthetic */ int warn$default(Logger logger, String str, Throwable th, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                th = null;
            }
            return logger.warn(str, th);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
    }

    @JvmOverloads
    public final int debug(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return debug$default(this, str, objArr, (Throwable) null, 4, (Object) null);
    }

    @JvmOverloads
    public final int error(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return error$default(this, str, objArr, (Throwable) null, 4, (Object) null);
    }

    @JvmOverloads
    public final int info(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return info$default(this, str, objArr, (Throwable) null, 4, (Object) null);
    }

    @JvmOverloads
    public final int verbose(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return verbose$default(this, str, objArr, (Throwable) null, 4, (Object) null);
    }

    @JvmOverloads
    public final int warn(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return warn$default(this, str, objArr, (Throwable) null, 4, (Object) null);
    }

    @JvmOverloads
    public final int debug(@NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return logIfAble(Level.DEBUG, str, objArr, th);
    }

    @JvmOverloads
    public final int error(@NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return logIfAble(Level.ERROR, str, objArr, th);
    }

    @JvmOverloads
    public final int info(@NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return logIfAble(Level.INFO, str, objArr, th);
    }

    @JvmOverloads
    public final int verbose(@NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return logIfAble(Level.VERBOSE, str, objArr, th);
    }

    @JvmOverloads
    public final int warn(@NotNull String str, @NotNull Object[] objArr, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return logIfAble(Level.WARN, str, objArr, th);
    }

    @JvmOverloads
    public final int debug(@NotNull String str, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return logIfAble$default(this, Level.DEBUG, str, (Object[]) null, th, 4, (Object) null);
    }

    @JvmOverloads
    public final int error(@NotNull String str, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return logIfAble$default(this, Level.ERROR, str, (Object[]) null, th, 4, (Object) null);
    }

    @JvmOverloads
    public final int info(@NotNull String str, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return logIfAble$default(this, Level.INFO, str, (Object[]) null, th, 4, (Object) null);
    }

    @JvmOverloads
    public final int verbose(@NotNull String str, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return logIfAble$default(this, Level.VERBOSE, str, (Object[]) null, th, 4, (Object) null);
    }

    @JvmOverloads
    public final int warn(@NotNull String str, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        return logIfAble$default(this, Level.WARN, str, (Object[]) null, th, 4, (Object) null);
    }
}
