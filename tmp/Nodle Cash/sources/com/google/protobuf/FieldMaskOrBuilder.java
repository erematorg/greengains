package com.google.protobuf;

import java.util.List;

public interface FieldMaskOrBuilder extends MessageLiteOrBuilder {
    String getPaths(int i3);

    ByteString getPathsBytes(int i3);

    int getPathsCount();

    List<String> getPathsList();
}
