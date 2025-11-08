package com.uber.h3core;

import d.b;
import java.util.ArrayList;
import java.util.List;

final class NativeMethods {
    public native int compact(long[] jArr, long[] jArr2);

    public native double edgeLengthKm(int i3);

    public native double edgeLengthM(int i3);

    public native int experimentalH3ToLocalIj(long j2, long j3, int[] iArr);

    public native long experimentalLocalIjToH3(long j2, int i3, int i4);

    public native long geoToH3(double d2, double d3, int i3);

    public native long getDestinationH3IndexFromUnidirectionalEdge(long j2);

    public native void getH3IndexesFromUnidirectionalEdge(long j2, long[] jArr);

    public native long getH3UnidirectionalEdge(long j2, long j3);

    public native int getH3UnidirectionalEdgeBoundary(long j2, double[] dArr);

    public native void getH3UnidirectionalEdgesFromHexagon(long j2, long[] jArr);

    public native long getOriginH3IndexFromUnidirectionalEdge(long j2);

    public native void getPentagonIndexes(int i3, long[] jArr);

    public native void getRes0Indexes(long[] jArr);

    public native int h3Distance(long j2, long j3);

    public native int h3GetBaseCell(long j2);

    public native void h3GetFaces(long j2, int[] iArr);

    public native boolean h3IndexesAreNeighbors(long j2, long j3);

    public native boolean h3IsPentagon(long j2);

    public native boolean h3IsValid(long j2);

    public native int h3Line(long j2, long j3, long[] jArr);

    public native int h3LineSize(long j2, long j3);

    public native void h3SetToLinkedGeo(long[] jArr, ArrayList<List<List<b>>> arrayList);

    public native long h3ToCenterChild(long j2, int i3);

    public native void h3ToChildren(long j2, int i3, long[] jArr);

    public native void h3ToGeo(long j2, double[] dArr);

    public native int h3ToGeoBoundary(long j2, double[] dArr);

    public native boolean h3UnidirectionalEdgeIsValid(long j2);

    public native double hexAreaKm2(int i3);

    public native double hexAreaM2(int i3);

    public native int hexRange(long j2, int i3, long[] jArr);

    public native int hexRing(long j2, int i3, long[] jArr);

    public native void kRing(long j2, int i3, long[] jArr);

    public native void kRingDistances(long j2, int i3, long[] jArr, int[] iArr);

    public native int maxFaceCount(long j2);

    public native int maxH3ToChildrenSize(long j2, int i3);

    public native int maxKringSize(int i3);

    public native int maxPolyfillSize(double[] dArr, int[] iArr, double[] dArr2, int i3);

    public native int maxUncompactSize(long[] jArr, int i3);

    public native long numHexagons(int i3);

    public native void polyfill(double[] dArr, int[] iArr, double[] dArr2, int i3, long[] jArr);

    public native int uncompact(long[] jArr, int i3, long[] jArr2);
}
