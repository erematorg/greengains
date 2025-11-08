package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface TypeOrBuilder extends MessageLiteOrBuilder {
    Field getFields(int i3);

    int getFieldsCount();

    List<Field> getFieldsList();

    String getName();

    ByteString getNameBytes();

    String getOneofs(int i3);

    ByteString getOneofsBytes(int i3);

    int getOneofsCount();

    List<String> getOneofsList();

    Option getOptions(int i3);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
