package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface EnumOrBuilder extends MessageLiteOrBuilder {
    EnumValue getEnumvalue(int i3);

    int getEnumvalueCount();

    List<EnumValue> getEnumvalueList();

    String getName();

    ByteString getNameBytes();

    Option getOptions(int i3);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
