package com.google.protobuf;

import com.google.protobuf.Type;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/google/protobuf/TypeKt;", "", "()V", "Dsl", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TypeKt {
    @NotNull
    public static final TypeKt INSTANCE = new TypeKt();

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0018\b\u0007\u0018\u0000 M2\u00020\u0001:\u0004MNOPB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010+\u001a\u00020,H\u0001J\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020.J\u0006\u00101\u001a\u000202J%\u00103\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000b\u001a\u00020\u0007H\u0007¢\u0006\u0002\b4J%\u00103\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u0006\u0010\u000b\u001a\u00020\u0016H\u0007¢\u0006\u0002\b5J%\u00103\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\b6J+\u00107\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000709H\u0007¢\u0006\u0002\b:J+\u00107\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001609H\u0007¢\u0006\u0002\b;J+\u00107\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\f09H\u0007¢\u0006\u0002\b<J\u001d\u0010=\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0007¢\u0006\u0002\b>J\u001d\u0010=\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0006H\u0007¢\u0006\u0002\b?J\u001d\u0010=\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u0006H\u0007¢\u0006\u0002\b@J&\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000b\u001a\u00020\u0007H\n¢\u0006\u0002\bBJ,\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000709H\n¢\u0006\u0002\bCJ&\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u0006\u0010\u000b\u001a\u00020\u0016H\n¢\u0006\u0002\bDJ,\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001609H\n¢\u0006\u0002\bEJ&\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\u000b\u001a\u00020\fH\n¢\u0006\u0002\bFJ,\u0010A\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\f09H\n¢\u0006\u0002\bGJ.\u0010H\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010I\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¢\u0006\u0002\bJJ.\u0010H\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u0006\u0010I\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020\u0016H\u0002¢\u0006\u0002\bKJ.\u0010H\u001a\u00020.*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010I\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\bLR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8G@GX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00068F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00068F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\nR$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00198G@GX\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u001f8G@GX\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010&\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020%8G@GX\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006Q"}, d2 = {"Lcom/google/protobuf/TypeKt$Dsl;", "", "_builder", "Lcom/google/protobuf/Type$Builder;", "(Lcom/google/protobuf/Type$Builder;)V", "fields", "Lcom/google/protobuf/kotlin/DslList;", "Lcom/google/protobuf/Field;", "Lcom/google/protobuf/TypeKt$Dsl$FieldsProxy;", "getFields", "()Lcom/google/protobuf/kotlin/DslList;", "value", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "oneofs", "Lcom/google/protobuf/TypeKt$Dsl$OneofsProxy;", "getOneofs", "options", "Lcom/google/protobuf/Option;", "Lcom/google/protobuf/TypeKt$Dsl$OptionsProxy;", "getOptions", "Lcom/google/protobuf/SourceContext;", "sourceContext", "getSourceContext", "()Lcom/google/protobuf/SourceContext;", "setSourceContext", "(Lcom/google/protobuf/SourceContext;)V", "Lcom/google/protobuf/Syntax;", "syntax", "getSyntax", "()Lcom/google/protobuf/Syntax;", "setSyntax", "(Lcom/google/protobuf/Syntax;)V", "", "syntaxValue", "getSyntaxValue", "()I", "setSyntaxValue", "(I)V", "_build", "Lcom/google/protobuf/Type;", "clearName", "", "clearSourceContext", "clearSyntax", "hasSourceContext", "", "add", "addFields", "addOptions", "addOneofs", "addAll", "values", "", "addAllFields", "addAllOptions", "addAllOneofs", "clear", "clearFields", "clearOptions", "clearOneofs", "plusAssign", "plusAssignFields", "plusAssignAllFields", "plusAssignOptions", "plusAssignAllOptions", "plusAssignOneofs", "plusAssignAllOneofs", "set", "index", "setFields", "setOptions", "setOneofs", "Companion", "FieldsProxy", "OneofsProxy", "OptionsProxy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
    @ProtoDslMarker
    public static final class Dsl {
        @NotNull
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        @NotNull
        private final Type.Builder _builder;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¨\u0006\u0007"}, d2 = {"Lcom/google/protobuf/TypeKt$Dsl$Companion;", "", "()V", "_create", "Lcom/google/protobuf/TypeKt$Dsl;", "builder", "Lcom/google/protobuf/Type$Builder;", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @PublishedApi
            public final /* synthetic */ Dsl _create(Type.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, (DefaultConstructorMarker) null);
            }

            private Companion() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/TypeKt$Dsl$FieldsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class FieldsProxy extends DslProxy {
            private FieldsProxy() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/TypeKt$Dsl$OneofsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class OneofsProxy extends DslProxy {
            private OneofsProxy() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/protobuf/TypeKt$Dsl$OptionsProxy;", "Lcom/google/protobuf/kotlin/DslProxy;", "()V", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class OptionsProxy extends DslProxy {
            private OptionsProxy() {
            }
        }

        public /* synthetic */ Dsl(Type.Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
            this(builder);
        }

        @PublishedApi
        public final /* synthetic */ Type _build() {
            GeneratedMessageLite build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "_builder.build()");
            return (Type) build;
        }

        @JvmName(name = "addAllFields")
        public final /* synthetic */ void addAllFields(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllFields(iterable);
        }

        @JvmName(name = "addAllOneofs")
        public final /* synthetic */ void addAllOneofs(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllOneofs(iterable);
        }

        @JvmName(name = "addAllOptions")
        public final /* synthetic */ void addAllOptions(DslList dslList, Iterable iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            this._builder.addAllOptions(iterable);
        }

        @JvmName(name = "addFields")
        public final /* synthetic */ void addFields(DslList dslList, Field field) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(field, "value");
            this._builder.addFields(field);
        }

        @JvmName(name = "addOneofs")
        public final /* synthetic */ void addOneofs(DslList dslList, String str) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(str, "value");
            this._builder.addOneofs(str);
        }

        @JvmName(name = "addOptions")
        public final /* synthetic */ void addOptions(DslList dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            this._builder.addOptions(option);
        }

        @JvmName(name = "clearFields")
        public final /* synthetic */ void clearFields(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearFields();
        }

        public final void clearName() {
            this._builder.clearName();
        }

        @JvmName(name = "clearOneofs")
        public final /* synthetic */ void clearOneofs(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearOneofs();
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

        public final /* synthetic */ DslList getFields() {
            List<Field> fieldsList = this._builder.getFieldsList();
            Intrinsics.checkNotNullExpressionValue(fieldsList, "_builder.getFieldsList()");
            return new DslList(fieldsList);
        }

        @NotNull
        @JvmName(name = "getName")
        public final String getName() {
            String name = this._builder.getName();
            Intrinsics.checkNotNullExpressionValue(name, "_builder.getName()");
            return name;
        }

        @NotNull
        public final DslList<String, OneofsProxy> getOneofs() {
            List<String> oneofsList = this._builder.getOneofsList();
            Intrinsics.checkNotNullExpressionValue(oneofsList, "_builder.getOneofsList()");
            return new DslList<>(oneofsList);
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

        @JvmName(name = "plusAssignAllFields")
        public final /* synthetic */ void plusAssignAllFields(DslList<Field, FieldsProxy> dslList, Iterable<Field> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllFields(dslList, iterable);
        }

        @JvmName(name = "plusAssignAllOneofs")
        public final /* synthetic */ void plusAssignAllOneofs(DslList<String, OneofsProxy> dslList, Iterable<String> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllOneofs(dslList, iterable);
        }

        @JvmName(name = "plusAssignAllOptions")
        public final /* synthetic */ void plusAssignAllOptions(DslList<Option, OptionsProxy> dslList, Iterable<Option> iterable) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "values");
            addAllOptions(dslList, iterable);
        }

        @JvmName(name = "plusAssignFields")
        public final /* synthetic */ void plusAssignFields(DslList<Field, FieldsProxy> dslList, Field field) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(field, "value");
            addFields(dslList, field);
        }

        @JvmName(name = "plusAssignOneofs")
        public final /* synthetic */ void plusAssignOneofs(DslList<String, OneofsProxy> dslList, String str) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(str, "value");
            addOneofs(dslList, str);
        }

        @JvmName(name = "plusAssignOptions")
        public final /* synthetic */ void plusAssignOptions(DslList<Option, OptionsProxy> dslList, Option option) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(option, "value");
            addOptions(dslList, option);
        }

        @JvmName(name = "setFields")
        public final /* synthetic */ void setFields(DslList dslList, int i3, Field field) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(field, "value");
            this._builder.setFields(i3, field);
        }

        @JvmName(name = "setName")
        public final void setName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            this._builder.setName(str);
        }

        @JvmName(name = "setOneofs")
        public final /* synthetic */ void setOneofs(DslList dslList, int i3, String str) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(str, "value");
            this._builder.setOneofs(i3, str);
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

        private Dsl(Type.Builder builder) {
            this._builder = builder;
        }
    }

    private TypeKt() {
    }
}
