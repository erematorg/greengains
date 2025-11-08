package com.google.protobuf;

import com.google.protobuf.Api;
import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/google/protobuf/ApiKt;", "", "()V", "Dsl", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ApiKt {
    @NotNull
    public static final ApiKt INSTANCE = new ApiKt();

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0018\b\u0007\u0018\u0000 R2\u00020\u0001:\u0004RSTUB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010/\u001a\u000200H\u0001J\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u000202J\u0006\u00104\u001a\u000202J\u0006\u00105\u001a\u000202J\u0006\u00106\u001a\u000207J%\u00108\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b9J%\u00108\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\u000f\u001a\u00020\fH\u0007¢\u0006\u0002\b:J%\u00108\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00062\u0006\u0010\u000f\u001a\u00020\u0017H\u0007¢\u0006\u0002\b;J+\u0010<\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00070>H\u0007¢\u0006\u0002\b?J+\u0010<\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\f0>H\u0007¢\u0006\u0002\b@J+\u0010<\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00170>H\u0007¢\u0006\u0002\bAJ\u001d\u0010B\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0007¢\u0006\u0002\bCJ\u001d\u0010B\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0006H\u0007¢\u0006\u0002\bDJ\u001d\u0010B\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0006H\u0007¢\u0006\u0002\bEJ&\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000f\u001a\u00020\u0007H\n¢\u0006\u0002\bGJ,\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00070>H\n¢\u0006\u0002\bHJ&\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\u000f\u001a\u00020\fH\n¢\u0006\u0002\bIJ,\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\f0>H\n¢\u0006\u0002\bJJ&\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00062\u0006\u0010\u000f\u001a\u00020\u0017H\n¢\u0006\u0002\bKJ,\u0010F\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00170>H\n¢\u0006\u0002\bLJ.\u0010M\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010N\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002¢\u0006\u0002\bOJ.\u0010M\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010N\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\fH\u0002¢\u0006\u0002\bPJ.\u0010M\u001a\u000202*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00062\u0006\u0010N\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u0017H\u0002¢\u0006\u0002\bQR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u00068F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108G@GX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00068F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\nR$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u001a8G@GX\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020 8G@GX\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010'\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020&8G@GX\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R$\u0010,\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108G@GX\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0013\"\u0004\b.\u0010\u0015¨\u0006V"}, d2 = {"Lcom/google/protobuf/ApiKt$Dsl;", "", "_builder", "Lcom/google/protobuf/Api$Builder;", "(Lcom/google/protobuf/Api$Builder;)V", "methods", "Lcom/google/protobuf/kotlin/DslList;", "Lcom/google/protobuf/Method;", "Lcom/google/protobuf/ApiKt$Dsl$MethodsProxy;", "getMethods", "()Lcom/google/protobuf/kotlin/DslList;", "mixins", "Lcom/google/protobuf/Mixin;", "Lcom/google/protobuf/ApiKt$Dsl$MixinsProxy;", "getMixins", "value", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "options", "Lcom/google/protobuf/Option;", "Lcom/google/protobuf/ApiKt$Dsl$OptionsProxy;", "getOptions", "Lcom/google/protobuf/SourceContext;", "sourceContext", "getSourceContext", "()Lcom/google/protobuf/SourceContext;", "setSourceContext", "(Lcom/google/protobuf/SourceContext;)V", "Lcom/google/protobuf/Syntax;", "syntax", "getSyntax", "()Lcom/google/protobuf/Syntax;", "setSyntax", "(Lcom/google/protobuf/Syntax;)V", "", "syntaxValue", "getSyntaxValue", "()I", "setSyntaxValue", "(I)V", "version", "getVersion", "setVersion", "_build", "Lcom/google/protobuf/Api;", "clearName", "", "clearSourceContext", "clearSyntax", "clearVersion", "hasSourceContext", "", "add", "addMethods", "addMixins", "addOptions", "addAll", "values", "", "addAllMethods", "addAllMixins", "addAllOptions", "clear", "clearMethods", "clearMixins", "clearOptions", "plusAssign", "plusAssignMethods", "plusAssignAllMethods", "plusAssignMixins", "plusAssignAllMixins", "plusAssignOptions", "plusAssignAllOptions", "set", "index", "setMethods", "setMixins", "setOptions", "Companion", "MethodsProxy", "MixinsProxy", "OptionsProxy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
    @ProtoDslMarker
    public static final class Dsl {
        @NotNull
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        @NotNull
        private final Api.Builder _builder;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¨\u0006\u0007"}, d2 = {"Lcom/google/protobuf/ApiKt$Dsl$Companion;", "", "()V", "_create", "Lcom/google/protobuf/ApiKt$Dsl;", "builder", "Lcom/google/protobuf/Api$Builder;", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @PublishedApi
            public final /* synthetic */ Dsl _create(Api.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, (DefaultConstructorMarker) null);
            }

            private Companion() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/ApiKt$Dsl$MethodsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class MethodsProxy extends DslProxy {
            private MethodsProxy() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/ApiKt$Dsl$MixinsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class MixinsProxy extends DslProxy {
            private MixinsProxy() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/ApiKt$Dsl$OptionsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class OptionsProxy extends DslProxy {
            private OptionsProxy() {
            }
        }

        public /* synthetic */ Dsl(Api.Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
            this(builder);
        }

        @PublishedApi
        public final /* synthetic */ Api _build() {
            GeneratedMessageLite build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "_builder.build()");
            return (Api) build;
        }

        @JvmName(name = "addAllMethods")
        public final /* synthetic */ void addAllMethods(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllMethods(iterable);
        }

        @JvmName(name = "addAllMixins")
        public final /* synthetic */ void addAllMixins(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllMixins(iterable);
        }

        @JvmName(name = "addAllOptions")
        public final /* synthetic */ void addAllOptions(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllOptions(iterable);
        }

        @JvmName(name = "addMethods")
        public final /* synthetic */ void addMethods(DslList dslList, Method method) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(method, "value");
            this._builder.addMethods(method);
        }

        @JvmName(name = "addMixins")
        public final /* synthetic */ void addMixins(DslList dslList, Mixin mixin) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(mixin, "value");
            this._builder.addMixins(mixin);
        }

        @JvmName(name = "addOptions")
        public final /* synthetic */ void addOptions(DslList dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            this._builder.addOptions(option);
        }

        @JvmName(name = "clearMethods")
        public final /* synthetic */ void clearMethods(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearMethods();
        }

        @JvmName(name = "clearMixins")
        public final /* synthetic */ void clearMixins(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearMixins();
        }

        public final void clearName() {
            this._builder.clearName();
        }

        @JvmName(name = "clearOptions")
        public final /* synthetic */ void clearOptions(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearOptions();
        }

        public final void clearSourceContext() {
            this._builder.clearSourceContext();
        }

        public final void clearSyntax() {
            this._builder.clearSyntax();
        }

        public final void clearVersion() {
            this._builder.clearVersion();
        }

        public final /* synthetic */ DslList getMethods() {
            List<Method> methodsList = this._builder.getMethodsList();
            Intrinsics.checkNotNullExpressionValue(methodsList, "_builder.getMethodsList()");
            return new DslList(methodsList);
        }

        public final /* synthetic */ DslList getMixins() {
            List<Mixin> mixinsList = this._builder.getMixinsList();
            Intrinsics.checkNotNullExpressionValue(mixinsList, "_builder.getMixinsList()");
            return new DslList(mixinsList);
        }

        @NotNull
        @JvmName(name = "getName")
        public final String getName() {
            String name = this._builder.getName();
            Intrinsics.checkNotNullExpressionValue(name, "_builder.getName()");
            return name;
        }

        public final /* synthetic */ DslList getOptions() {
            List<Option> optionsList = this._builder.getOptionsList();
            Intrinsics.checkNotNullExpressionValue(optionsList, "_builder.getOptionsList()");
            return new DslList(optionsList);
        }

        @NotNull
        @JvmName(name = "getSourceContext")
        public final SourceContext getSourceContext() {
            SourceContext sourceContext = this._builder.getSourceContext();
            Intrinsics.checkNotNullExpressionValue(sourceContext, "_builder.getSourceContext()");
            return sourceContext;
        }

        @NotNull
        @JvmName(name = "getSyntax")
        public final Syntax getSyntax() {
            Syntax syntax = this._builder.getSyntax();
            Intrinsics.checkNotNullExpressionValue(syntax, "_builder.getSyntax()");
            return syntax;
        }

        @JvmName(name = "getSyntaxValue")
        public final int getSyntaxValue() {
            return this._builder.getSyntaxValue();
        }

        @NotNull
        @JvmName(name = "getVersion")
        public final String getVersion() {
            String version = this._builder.getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "_builder.getVersion()");
            return version;
        }

        public final boolean hasSourceContext() {
            return this._builder.hasSourceContext();
        }

        @JvmName(name = "plusAssignAllMethods")
        public final /* synthetic */ void plusAssignAllMethods(DslList<Method, MethodsProxy> dslList, Iterable<Method> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllMethods(dslList, iterable);
        }

        @JvmName(name = "plusAssignAllMixins")
        public final /* synthetic */ void plusAssignAllMixins(DslList<Mixin, MixinsProxy> dslList, Iterable<Mixin> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllMixins(dslList, iterable);
        }

        @JvmName(name = "plusAssignAllOptions")
        public final /* synthetic */ void plusAssignAllOptions(DslList<Option, OptionsProxy> dslList, Iterable<Option> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllOptions(dslList, iterable);
        }

        @JvmName(name = "plusAssignMethods")
        public final /* synthetic */ void plusAssignMethods(DslList<Method, MethodsProxy> dslList, Method method) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(method, "value");
            addMethods(dslList, method);
        }

        @JvmName(name = "plusAssignMixins")
        public final /* synthetic */ void plusAssignMixins(DslList<Mixin, MixinsProxy> dslList, Mixin mixin) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(mixin, "value");
            addMixins(dslList, mixin);
        }

        @JvmName(name = "plusAssignOptions")
        public final /* synthetic */ void plusAssignOptions(DslList<Option, OptionsProxy> dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            addOptions(dslList, option);
        }

        @JvmName(name = "setMethods")
        public final /* synthetic */ void setMethods(DslList dslList, int i3, Method method) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(method, "value");
            this._builder.setMethods(i3, method);
        }

        @JvmName(name = "setMixins")
        public final /* synthetic */ void setMixins(DslList dslList, int i3, Mixin mixin) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(mixin, "value");
            this._builder.setMixins(i3, mixin);
        }

        @JvmName(name = "setName")
        public final void setName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            this._builder.setName(str);
        }

        @JvmName(name = "setOptions")
        public final /* synthetic */ void setOptions(DslList dslList, int i3, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            this._builder.setOptions(i3, option);
        }

        @JvmName(name = "setSourceContext")
        public final void setSourceContext(@NotNull SourceContext sourceContext) {
            Intrinsics.checkNotNullParameter(sourceContext, "value");
            this._builder.setSourceContext(sourceContext);
        }

        @JvmName(name = "setSyntax")
        public final void setSyntax(@NotNull Syntax syntax) {
            Intrinsics.checkNotNullParameter(syntax, "value");
            this._builder.setSyntax(syntax);
        }

        @JvmName(name = "setSyntaxValue")
        public final void setSyntaxValue(int i3) {
            this._builder.setSyntaxValue(i3);
        }

        @JvmName(name = "setVersion")
        public final void setVersion(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            this._builder.setVersion(str);
        }

        private Dsl(Api.Builder builder) {
            this._builder = builder;
        }
    }

    private ApiKt() {
    }
}
