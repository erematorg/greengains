package com.google.android.datatransport.runtime;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.proto.AtProtobuf;

public final /* synthetic */ class a implements TransportScheduleCallback {
    public static FieldDescriptor a(int i3, FieldDescriptor.Builder builder) {
        return builder.withProperty(AtProtobuf.builder().tag(i3).build()).build();
    }

    public void onSchedule(Exception exc) {
        TransportImpl.lambda$send$0(exc);
    }
}
