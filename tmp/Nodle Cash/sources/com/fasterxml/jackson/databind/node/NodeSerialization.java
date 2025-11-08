package com.fasterxml.jackson.databind.node;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

class NodeSerialization implements Serializable, Externalizable {
    protected static final int LONGEST_EAGER_ALLOC = 100000;
    private static final long serialVersionUID = 1;
    public byte[] json;

    public NodeSerialization() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003c, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] _read(java.io.ObjectInput r5, int r6) throws java.io.IOException {
        /*
            r4 = this;
            r4 = 0
            r0 = 100000(0x186a0, float:1.4013E-40)
            if (r6 > r0) goto L_0x000c
            byte[] r0 = new byte[r6]
            r5.readFully(r0, r4, r6)
            return r0
        L_0x000c:
            com.fasterxml.jackson.core.util.ByteArrayBuilder r1 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r1.<init>((int) r0)
            byte[] r0 = r1.resetAndGetFirstSegment()     // Catch:{ all -> 0x002b }
        L_0x0015:
            r2 = r4
        L_0x0016:
            int r3 = r0.length     // Catch:{ all -> 0x002b }
            int r3 = r3 - r2
            int r3 = java.lang.Math.min(r3, r6)     // Catch:{ all -> 0x002b }
            r5.readFully(r0, r4, r3)     // Catch:{ all -> 0x002b }
            int r6 = r6 - r3
            int r2 = r2 + r3
            if (r6 != 0) goto L_0x002d
            byte[] r4 = r1.completeAndCoalesce(r2)     // Catch:{ all -> 0x002b }
            r1.close()
            return r4
        L_0x002b:
            r4 = move-exception
            goto L_0x0035
        L_0x002d:
            int r3 = r0.length     // Catch:{ all -> 0x002b }
            if (r2 != r3) goto L_0x0016
            byte[] r0 = r1.finishCurrentSegment()     // Catch:{ all -> 0x002b }
            goto L_0x0015
        L_0x0035:
            throw r4     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x003f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.NodeSerialization._read(java.io.ObjectInput, int):byte[]");
    }

    public static NodeSerialization from(Object obj) {
        try {
            return new NodeSerialization(InternalNodeMapper.valueToBytes(obj));
        } catch (IOException e3) {
            throw new IllegalArgumentException("Failed to JDK serialize `" + obj.getClass().getSimpleName() + "` value: " + e3.getMessage(), e3);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        this.json = _read(objectInput, objectInput.readInt());
    }

    public Object readResolve() {
        try {
            return InternalNodeMapper.bytesToNode(this.json);
        } catch (IOException e3) {
            throw new IllegalArgumentException(a.h(e3, new StringBuilder("Failed to JDK deserialize `JsonNode` value: ")), e3);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.json.length);
        objectOutput.write(this.json);
    }

    public NodeSerialization(byte[] bArr) {
        this.json = bArr;
    }
}
