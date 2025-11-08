package com.google.devtools.ksp.processing;

import java.util.List;
import java.util.Map;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B3\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bBQ\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0011BW\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/google/devtools/ksp/processing/SymbolProcessorEnvironment;", "", "options", "", "", "kotlinVersion", "Lkotlin/KotlinVersion;", "codeGenerator", "Lcom/google/devtools/ksp/processing/CodeGenerator;", "logger", "Lcom/google/devtools/ksp/processing/KSPLogger;", "(Ljava/util/Map;Lkotlin/KotlinVersion;Lcom/google/devtools/ksp/processing/CodeGenerator;Lcom/google/devtools/ksp/processing/KSPLogger;)V", "apiVersion", "compilerVersion", "platforms", "", "Lcom/google/devtools/ksp/processing/PlatformInfo;", "(Ljava/util/Map;Lkotlin/KotlinVersion;Lcom/google/devtools/ksp/processing/CodeGenerator;Lcom/google/devtools/ksp/processing/KSPLogger;Lkotlin/KotlinVersion;Lkotlin/KotlinVersion;Ljava/util/List;)V", "kspVersion", "(Ljava/util/Map;Lkotlin/KotlinVersion;Lcom/google/devtools/ksp/processing/CodeGenerator;Lcom/google/devtools/ksp/processing/KSPLogger;Lkotlin/KotlinVersion;Lkotlin/KotlinVersion;Ljava/util/List;Lkotlin/KotlinVersion;)V", "getApiVersion", "()Lkotlin/KotlinVersion;", "getCodeGenerator", "()Lcom/google/devtools/ksp/processing/CodeGenerator;", "getCompilerVersion", "getKotlinVersion", "getKspVersion", "getLogger", "()Lcom/google/devtools/ksp/processing/KSPLogger;", "getOptions", "()Ljava/util/Map;", "getPlatforms", "()Ljava/util/List;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SymbolProcessorEnvironment {
    @NotNull
    private final KotlinVersion apiVersion;
    @NotNull
    private final CodeGenerator codeGenerator;
    @NotNull
    private final KotlinVersion compilerVersion;
    @NotNull
    private final KotlinVersion kotlinVersion;
    @NotNull
    private final KotlinVersion kspVersion;
    @NotNull
    private final KSPLogger logger;
    @NotNull
    private final Map<String, String> options;
    @NotNull
    private final List<PlatformInfo> platforms;

    public SymbolProcessorEnvironment(@NotNull Map<String, String> map, @NotNull KotlinVersion kotlinVersion2, @NotNull CodeGenerator codeGenerator2, @NotNull KSPLogger kSPLogger, @NotNull KotlinVersion kotlinVersion3, @NotNull KotlinVersion kotlinVersion4, @NotNull List<? extends PlatformInfo> list, @NotNull KotlinVersion kotlinVersion5) {
        Intrinsics.checkNotNullParameter(map, "options");
        Intrinsics.checkNotNullParameter(kotlinVersion2, "kotlinVersion");
        Intrinsics.checkNotNullParameter(codeGenerator2, "codeGenerator");
        Intrinsics.checkNotNullParameter(kSPLogger, "logger");
        Intrinsics.checkNotNullParameter(kotlinVersion3, "apiVersion");
        Intrinsics.checkNotNullParameter(kotlinVersion4, "compilerVersion");
        Intrinsics.checkNotNullParameter(list, "platforms");
        Intrinsics.checkNotNullParameter(kotlinVersion5, "kspVersion");
        this.options = map;
        this.kotlinVersion = kotlinVersion2;
        this.codeGenerator = codeGenerator2;
        this.logger = kSPLogger;
        this.apiVersion = kotlinVersion3;
        this.compilerVersion = kotlinVersion4;
        this.platforms = list;
        this.kspVersion = kotlinVersion5;
    }

    @NotNull
    public final KotlinVersion getApiVersion() {
        return this.apiVersion;
    }

    @NotNull
    public final CodeGenerator getCodeGenerator() {
        return this.codeGenerator;
    }

    @NotNull
    public final KotlinVersion getCompilerVersion() {
        return this.compilerVersion;
    }

    @NotNull
    public final KotlinVersion getKotlinVersion() {
        return this.kotlinVersion;
    }

    @NotNull
    public final KotlinVersion getKspVersion() {
        return this.kspVersion;
    }

    @NotNull
    public final KSPLogger getLogger() {
        return this.logger;
    }

    @NotNull
    public final Map<String, String> getOptions() {
        return this.options;
    }

    @NotNull
    public final List<PlatformInfo> getPlatforms() {
        return this.platforms;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SymbolProcessorEnvironment(@NotNull Map<String, String> map, @NotNull KotlinVersion kotlinVersion2, @NotNull CodeGenerator codeGenerator2, @NotNull KSPLogger kSPLogger) {
        this(map, kotlinVersion2, codeGenerator2, kSPLogger, kotlinVersion2, kotlinVersion2, CollectionsKt.emptyList(), new KotlinVersion(1, 0));
        Intrinsics.checkNotNullParameter(map, "options");
        Intrinsics.checkNotNullParameter(kotlinVersion2, "kotlinVersion");
        Intrinsics.checkNotNullParameter(codeGenerator2, "codeGenerator");
        Intrinsics.checkNotNullParameter(kSPLogger, "logger");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SymbolProcessorEnvironment(@org.jetbrains.annotations.NotNull java.util.Map<java.lang.String, java.lang.String> r11, @org.jetbrains.annotations.NotNull kotlin.KotlinVersion r12, @org.jetbrains.annotations.NotNull com.google.devtools.ksp.processing.CodeGenerator r13, @org.jetbrains.annotations.NotNull com.google.devtools.ksp.processing.KSPLogger r14, @org.jetbrains.annotations.NotNull kotlin.KotlinVersion r15, @org.jetbrains.annotations.NotNull kotlin.KotlinVersion r16, @org.jetbrains.annotations.NotNull java.util.List<? extends com.google.devtools.ksp.processing.PlatformInfo> r17) {
        /*
            r10 = this;
            java.lang.String r0 = "options"
            r2 = r11
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "kotlinVersion"
            r3 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "codeGenerator"
            r4 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "logger"
            r5 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "apiVersion"
            r6 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.lang.String r0 = "compilerVersion"
            r7 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "platforms"
            r8 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            kotlin.KotlinVersion r9 = new kotlin.KotlinVersion
            r0 = 1
            r1 = 0
            r9.<init>(r0, r1)
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.processing.SymbolProcessorEnvironment.<init>(java.util.Map, kotlin.KotlinVersion, com.google.devtools.ksp.processing.CodeGenerator, com.google.devtools.ksp.processing.KSPLogger, kotlin.KotlinVersion, kotlin.KotlinVersion, java.util.List):void");
    }
}
