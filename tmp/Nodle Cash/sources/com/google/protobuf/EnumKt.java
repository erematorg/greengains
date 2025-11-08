package com.google.protobuf;

import com.google.protobuf.Enum;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/google/protobuf/EnumKt;", "", "()V", "Dsl", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class EnumKt {
    @NotNull
    public static final EnumKt INSTANCE = new EnumKt();

    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0012\b\u0007\u0018\u0000 D2\u00020\u0001:\u0003DEFB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010(\u001a\u00020)H\u0001J\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020+J\u0006\u0010-\u001a\u00020+J\u0006\u0010.\u001a\u00020/J%\u00100\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000b\u001a\u00020\u0007H\u0007¢\u0006\u0002\b1J%\u00100\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00062\u0006\u0010\u000b\u001a\u00020\u0013H\u0007¢\u0006\u0002\b2J+\u00103\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000705H\u0007¢\u0006\u0002\b6J+\u00103\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00062\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001305H\u0007¢\u0006\u0002\b7J\u001d\u00108\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0007¢\u0006\u0002\b9J\u001d\u00108\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0006H\u0007¢\u0006\u0002\b:J&\u0010;\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000b\u001a\u00020\u0007H\n¢\u0006\u0002\b<J,\u0010;\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000705H\n¢\u0006\u0002\b=J&\u0010;\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00062\u0006\u0010\u000b\u001a\u00020\u0013H\n¢\u0006\u0002\b>J,\u0010;\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00062\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001305H\n¢\u0006\u0002\b?J.\u0010@\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010A\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¢\u0006\u0002\bBJ.\u0010@\u001a\u00020+*\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00062\u0006\u0010A\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\u0013H\u0002¢\u0006\u0002\bCR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8G@GX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\nR$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u00168G@GX\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\u001c8G@GX\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\"8G@GX\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006G"}, d2 = {"Lcom/google/protobuf/EnumKt$Dsl;", "", "_builder", "Lcom/google/protobuf/Enum$Builder;", "(Lcom/google/protobuf/Enum$Builder;)V", "enumvalue", "Lcom/google/protobuf/kotlin/DslList;", "Lcom/google/protobuf/EnumValue;", "Lcom/google/protobuf/EnumKt$Dsl$EnumvalueProxy;", "getEnumvalue", "()Lcom/google/protobuf/kotlin/DslList;", "value", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "options", "Lcom/google/protobuf/Option;", "Lcom/google/protobuf/EnumKt$Dsl$OptionsProxy;", "getOptions", "Lcom/google/protobuf/SourceContext;", "sourceContext", "getSourceContext", "()Lcom/google/protobuf/SourceContext;", "setSourceContext", "(Lcom/google/protobuf/SourceContext;)V", "Lcom/google/protobuf/Syntax;", "syntax", "getSyntax", "()Lcom/google/protobuf/Syntax;", "setSyntax", "(Lcom/google/protobuf/Syntax;)V", "", "syntaxValue", "getSyntaxValue", "()I", "setSyntaxValue", "(I)V", "_build", "Lcom/google/protobuf/Enum;", "clearName", "", "clearSourceContext", "clearSyntax", "hasSourceContext", "", "add", "addEnumvalue", "addOptions", "addAll", "values", "", "addAllEnumvalue", "addAllOptions", "clear", "clearEnumvalue", "clearOptions", "plusAssign", "plusAssignEnumvalue", "plusAssignAllEnumvalue", "plusAssignOptions", "plusAssignAllOptions", "set", "index", "setEnumvalue", "setOptions", "Companion", "EnumvalueProxy", "OptionsProxy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
    @ProtoDslMarker
    public static final class Dsl {
        @NotNull
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        @NotNull
        private final Enum.Builder _builder;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¨\u0006\u0007"}, d2 = {"Lcom/google/protobuf/EnumKt$Dsl$Companion;", "", "()V", "_create", "Lcom/google/protobuf/EnumKt$Dsl;", "builder", "Lcom/google/protobuf/Enum$Builder;", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @PublishedApi
            public final /* synthetic */ Dsl _create(Enum.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, (DefaultConstructorMarker) null);
            }

            private Companion() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/EnumKt$Dsl$EnumvalueProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class EnumvalueProxy extends DslProxy {
            private EnumvalueProxy() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/EnumKt$Dsl$OptionsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class OptionsProxy extends DslProxy {
            private OptionsProxy() {
            }
        }

        public /* synthetic */ Dsl(Enum.Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
            this(builder);
        }

        @PublishedApi
        public final /* synthetic */ Enum _build() {
            GeneratedMessageLite build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "_builder.build()");
            return (Enum) build;
        }

        @JvmName(name = "addAllEnumvalue")
        public final /* synthetic */ void addAllEnumvalue(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllEnumvalue(iterable);
        }

        @JvmName(name = "addAllOptions")
        public final /* synthetic */ void addAllOptions(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllOptions(iterable);
        }

        @JvmName(name = "addEnumvalue")
        public final /* synthetic */ void addEnumvalue(DslList dslList, EnumValue enumValue) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(enumValue, "value");
            this._builder.addEnumvalue(enumValue);
        }

        @JvmName(name = "addOptions")
        public final /* synthetic */ void addOptions(DslList dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            this._builder.addOptions(option);
        }

        @JvmName(name = "clearEnumvalue")
        public final /* synthetic */ void clearEnumvalue(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearEnumvalue();
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

        public final /* synthetic */ DslList getEnumvalue() {
            List<EnumValue> enumvalueList = this._builder.getEnumvalueList();
            Intrinsics.checkNotNullExpressionValue(enumvalueList, "_builder.getEnumvalueList()");
            return new DslList(enumvalueList);
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

        public final boolean hasSourceContext() {
            return this._builder.hasSourceContext();
        }

        @JvmName(name = "plusAssignAllEnumvalue")
        public final /* synthetic */ void plusAssignAllEnumvalue(DslList<EnumValue, EnumvalueProxy> dslList, Iterable<EnumValue> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllEnumvalue(dslList, iterable);
        }

        @JvmName(name = "plusAssignAllOptions")
        public final /* synthetic */ void plusAssignAllOptions(DslList<Option, OptionsProxy> dslList, Iterable<Option> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllOptions(dslList, iterable);
        }

        @JvmName(name = "plusAssignEnumvalue")
        public final /* synthetic */ void plusAssignEnumvalue(DslList<EnumValue, EnumvalueProxy> dslList, EnumValue enumValue) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(enumValue, "value");
            addEnumvalue(dslList, enumValue);
        }

        @JvmName(name = "plusAssignOptions")
        public final /* synthetic */ void plusAssignOptions(DslList<Option, OptionsProxy> dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            addOptions(dslList, option);
        }

        @JvmName(name = "setEnumvalue")
        public final /* synthetic */ void setEnumvalue(DslList dslList, int i3, EnumValue enumValue) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(enumValue, "value");
            this._builder.setEnumvalue(i3, enumValue);
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

        private Dsl(Enum.Builder builder) {
            this._builder = builder;
        }
    }

    private EnumKt() {
    }
}
