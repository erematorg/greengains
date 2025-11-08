package com.google.firebase.encoders.proto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

class ProtobufValueEncoderContext implements ValueEncoderContext {
    private boolean encoded = false;
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean skipDefault = false;

    public ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext) {
        this.objEncoderCtx = protobufDataEncoderContext;
    }

    private void checkNotUsed() {
        if (!this.encoded) {
            this.encoded = true;
            return;
        }
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    @NonNull
    public ValueEncoderContext add(@Nullable String str) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, (Object) str, this.skipDefault);
        return this;
    }

    public void resetContext(FieldDescriptor fieldDescriptor, boolean z2) {
        this.encoded = false;
        this.field = fieldDescriptor;
        this.skipDefault = z2;
    }

    @NonNull
    public ValueEncoderContext add(float f2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, f2, this.skipDefault);
        return this;
    }

    @NonNull
    public ValueEncoderContext add(double d2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, d2, this.skipDefault);
        return this;
    }

    @NonNull
    public ValueEncoderContext add(int i3) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, i3, this.skipDefault);
        return this;
    }

    @NonNull
    public ValueEncoderContext add(long j2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, j2, this.skipDefault);
        return this;
    }

    @NonNull
    public ValueEncoderContext add(boolean z2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, z2, this.skipDefault);
        return this;
    }

    @NonNull
    public ValueEncoderContext add(@NonNull byte[] bArr) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, (Object) bArr, this.skipDefault);
        return this;
    }
}
