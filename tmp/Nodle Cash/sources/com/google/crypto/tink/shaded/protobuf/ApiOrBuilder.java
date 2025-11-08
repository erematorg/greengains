package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface ApiOrBuilder extends MessageLiteOrBuilder {
    Method getMethods(int i3);

    int getMethodsCount();

    List<Method> getMethodsList();

    Mixin getMixins(int i3);

    int getMixinsCount();

    List<Mixin> getMixinsList();

    String getName();

    ByteString getNameBytes();

    Option getOptions(int i3);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasSourceContext();
}
