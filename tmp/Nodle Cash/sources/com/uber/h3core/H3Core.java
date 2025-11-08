package com.uber.h3core;

import b.C0204a;
import b.C0205b;
import c.C0207a;
import c.C0208b;
import c.C0209c;
import c.d;
import com.amplitude.api.DeviceInfo;
import com.uber.h3core.a;
import d.C0222a;
import d.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class H3Core {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long H3_DIGIT_MASK = 35184372088831L;
    private static final long H3_RES_MASK = 67553994410557440L;
    private static final long H3_RES_MASK_NEGATIVE = -67553994410557441L;
    private static final long H3_RES_OFFSET = 52;
    private static final long INVALID_INDEX = 0;
    private static final int MAX_CELL_BNDRY_VERTS = 10;
    private static final int NUM_BASE_CELLS = 122;
    private static final int NUM_PENTAGONS = 12;
    private final NativeMethods h3Api;

    private H3Core(NativeMethods nativeMethods) {
        this.h3Api = nativeMethods;
    }

    private static void checkResolution(int i3) {
        if (i3 < 0 || i3 > 15) {
            throw new IllegalArgumentException(String.format("resolution %d is out of range (must be 0 <= res <= 15)", new Object[]{Integer.valueOf(i3)}));
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.ToLongFunction] */
    private static long[] collectionToLongArray(Collection<Long> collection) {
        return collection.stream().mapToLong(new Object()).toArray();
    }

    /* access modifiers changed from: private */
    public List<String> h3ToStringList(Collection<Long> collection) {
        return (List) collection.stream().map(new b(this, 2)).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$h3GetFaces$0(int i3) {
        return i3 != -1;
    }

    public static H3Core newInstance(a.C0067a aVar, String str) {
        return new H3Core(a.a(aVar, str));
    }

    public static H3Core newSystemInstance() {
        System.loadLibrary("h3-java");
        return new H3Core(new NativeMethods());
    }

    private static List<Long> nonZeroLongArrayToList(long[] jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j2 : jArr) {
            if (j2 != 0) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        return arrayList;
    }

    private static int packGeofenceVertices(double[] dArr, List<b> list, int i3) {
        for (int i4 = 0; i4 < list.size(); i4++) {
            b bVar = list.get(i4);
            int i5 = i4 * 2;
            dArr[i5 + i3] = Math.toRadians(bVar.f7662a);
            dArr[i5 + 1 + i3] = Math.toRadians(bVar.f7663b);
        }
        return androidx.constraintlayout.core.state.b.c(list, 2, i3);
    }

    private List<Long> stringToH3List(Collection<String> collection) {
        return (List) collection.stream().map(new b(this, 1)).collect(Collectors.toList());
    }

    public List<Long> compact(Collection<Long> collection) {
        int size = collection.size();
        long[] jArr = new long[size];
        if (this.h3Api.compact(collectionToLongArray(collection), jArr) == 0) {
            return nonZeroLongArrayToList(jArr);
        }
        throw new IllegalArgumentException("Bad input to compact");
    }

    public List<String> compactAddress(Collection<String> collection) {
        return h3ToStringList(compact(stringToH3List(collection)));
    }

    public double edgeLength(int i3, C0205b bVar) {
        checkResolution(i3);
        if (bVar == C0205b.km) {
            return this.h3Api.edgeLengthKm(i3);
        }
        if (bVar == C0205b.m) {
            return this.h3Api.edgeLengthM(i3);
        }
        throw new IllegalArgumentException("Invalid unit: " + bVar);
    }

    public C0222a experimentalH3ToLocalIj(long j2, long j3) {
        int[] iArr = new int[2];
        int experimentalH3ToLocalIj = this.h3Api.experimentalH3ToLocalIj(j2, j3, iArr);
        if (experimentalH3ToLocalIj == 0) {
            return new C0222a(iArr[0], iArr[1]);
        }
        if (experimentalH3ToLocalIj == 1) {
            throw new IllegalArgumentException("Incompatible origin and index.");
        } else if (experimentalH3ToLocalIj == 3 || experimentalH3ToLocalIj == 4 || experimentalH3ToLocalIj == 5) {
            throw new d("Encountered possible pentagon distortion");
        } else {
            throw new C0209c("Local IJ coordinates undefined for this origin and index pair. The index may be too far from the origin.");
        }
    }

    public long experimentalLocalIjToH3(long j2, C0222a aVar) {
        long experimentalLocalIjToH3 = this.h3Api.experimentalLocalIjToH3(j2, aVar.f7660a, aVar.f7661b);
        if (experimentalLocalIjToH3 != 0) {
            return experimentalLocalIjToH3;
        }
        throw new C0209c("Index not defined for this origin and IJ coordinates pair. IJ coordinates may be too far from origin, or pentagon distortion was encountered.");
    }

    public long geoToH3(double d2, double d3, int i3) {
        checkResolution(i3);
        long geoToH3 = this.h3Api.geoToH3(Math.toRadians(d2), Math.toRadians(d3), i3);
        if (geoToH3 != 0) {
            return geoToH3;
        }
        throw new IllegalArgumentException("Latitude or longitude were invalid.");
    }

    public String geoToH3Address(double d2, double d3, int i3) {
        return h3ToString(geoToH3(d2, d3, i3));
    }

    public long getDestinationH3IndexFromUnidirectionalEdge(long j2) {
        return this.h3Api.getDestinationH3IndexFromUnidirectionalEdge(j2);
    }

    public List<Long> getH3IndexesFromUnidirectionalEdge(long j2) {
        long[] jArr = new long[2];
        this.h3Api.getH3IndexesFromUnidirectionalEdge(j2, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public long getH3UnidirectionalEdge(long j2, long j3) {
        long h3UnidirectionalEdge = this.h3Api.getH3UnidirectionalEdge(j2, j3);
        if (h3UnidirectionalEdge != 0) {
            return h3UnidirectionalEdge;
        }
        throw new IllegalArgumentException("Given indexes are not neighbors.");
    }

    public List<b> getH3UnidirectionalEdgeBoundary(long j2) {
        double[] dArr = new double[20];
        int h3UnidirectionalEdgeBoundary = this.h3Api.getH3UnidirectionalEdgeBoundary(j2, dArr);
        ArrayList arrayList = new ArrayList(h3UnidirectionalEdgeBoundary);
        for (int i3 = 0; i3 < h3UnidirectionalEdgeBoundary; i3++) {
            int i4 = i3 * 2;
            arrayList.add(new b(Math.toDegrees(dArr[i4]), Math.toDegrees(dArr[i4 + 1])));
        }
        return arrayList;
    }

    public List<Long> getH3UnidirectionalEdgesFromHexagon(long j2) {
        long[] jArr = new long[6];
        this.h3Api.getH3UnidirectionalEdgesFromHexagon(j2, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public long getOriginH3IndexFromUnidirectionalEdge(long j2) {
        return this.h3Api.getOriginH3IndexFromUnidirectionalEdge(j2);
    }

    public Collection<Long> getPentagonIndexes(int i3) {
        checkResolution(i3);
        long[] jArr = new long[12];
        this.h3Api.getPentagonIndexes(i3, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public Collection<String> getPentagonIndexesAddresses(int i3) {
        return h3ToStringList(getPentagonIndexes(i3));
    }

    public Collection<Long> getRes0Indexes() {
        long[] jArr = new long[122];
        this.h3Api.getRes0Indexes(jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public Collection<String> getRes0IndexesAddresses() {
        return h3ToStringList(getRes0Indexes());
    }

    public List<List<List<b>>> h3AddressSetToMultiPolygon(Collection<String> collection, boolean z2) {
        return h3SetToMultiPolygon(stringToH3List(collection), z2);
    }

    public int h3Distance(long j2, long j3) {
        int h3Distance = this.h3Api.h3Distance(j2, j3);
        if (h3Distance >= 0) {
            return h3Distance;
        }
        throw new C0207a("Distance not defined between the two indexes.");
    }

    public int h3GetBaseCell(long j2) {
        return this.h3Api.h3GetBaseCell(j2);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, java.util.function.IntPredicate] */
    public Collection<Integer> h3GetFaces(long j2) {
        int[] iArr = new int[this.h3Api.maxFaceCount(j2)];
        this.h3Api.h3GetFaces(j2, iArr);
        return (Collection) IntStream.of(iArr).filter(new Object()).boxed().collect(Collectors.toList());
    }

    public int h3GetResolution(long j2) {
        return (int) ((j2 & H3_RES_MASK) >> 52);
    }

    public boolean h3IndexesAreNeighbors(long j2, long j3) {
        return this.h3Api.h3IndexesAreNeighbors(j2, j3);
    }

    public boolean h3IsPentagon(long j2) {
        return this.h3Api.h3IsPentagon(j2);
    }

    public boolean h3IsResClassIII(long j2) {
        return h3GetResolution(j2) % 2 != 0;
    }

    public boolean h3IsValid(long j2) {
        return this.h3Api.h3IsValid(j2);
    }

    public List<Long> h3Line(long j2, long j3) {
        int h3LineSize = this.h3Api.h3LineSize(j2, j3);
        if (h3LineSize >= 0) {
            long[] jArr = new long[h3LineSize];
            if (this.h3Api.h3Line(j2, j3, jArr) == 0) {
                return nonZeroLongArrayToList(jArr);
            }
            throw new C0208b("Could not compute line between cells");
        }
        throw new C0208b("Could not compute line size between cells");
    }

    public List<List<List<b>>> h3SetToMultiPolygon(Collection<Long> collection, boolean z2) {
        long[] collectionToLongArray = collectionToLongArray(collection);
        ArrayList arrayList = new ArrayList();
        this.h3Api.h3SetToLinkedGeo(collectionToLongArray, arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (List list : (List) it.next()) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    b bVar = (b) list.get(i3);
                    list.set(i3, new b(Math.toDegrees(bVar.f7662a), Math.toDegrees(bVar.f7663b)));
                }
                if (z2 && list.size() > 0) {
                    list.add(list.get(0));
                }
            }
        }
        return arrayList;
    }

    public long h3ToCenterChild(long j2, int i3) {
        checkResolution(i3);
        long h3ToCenterChild = this.h3Api.h3ToCenterChild(j2, i3);
        if (h3ToCenterChild != 0) {
            return h3ToCenterChild;
        }
        throw new IllegalArgumentException(String.format("childRes %d must be between %d and 15, inclusive", new Object[]{Integer.valueOf(i3), Integer.valueOf(h3GetResolution(j2))}));
    }

    public List<Long> h3ToChildren(long j2, int i3) {
        checkResolution(i3);
        long[] jArr = new long[this.h3Api.maxH3ToChildrenSize(j2, i3)];
        this.h3Api.h3ToChildren(j2, i3, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public b h3ToGeo(long j2) {
        double[] dArr = new double[2];
        this.h3Api.h3ToGeo(j2, dArr);
        return new b(Math.toDegrees(dArr[0]), Math.toDegrees(dArr[1]));
    }

    public List<b> h3ToGeoBoundary(long j2) {
        double[] dArr = new double[20];
        int h3ToGeoBoundary = this.h3Api.h3ToGeoBoundary(j2, dArr);
        ArrayList arrayList = new ArrayList(h3ToGeoBoundary);
        for (int i3 = 0; i3 < h3ToGeoBoundary; i3++) {
            int i4 = i3 * 2;
            arrayList.add(new b(Math.toDegrees(dArr[i4]), Math.toDegrees(dArr[i4 + 1])));
        }
        return arrayList;
    }

    public long h3ToParent(long j2, int i3) {
        int i4 = (int) ((H3_RES_MASK & j2) >> 52);
        if (i3 < 0 || i3 > i4) {
            throw new IllegalArgumentException(String.format("res (%d) must be between 0 and %d, inclusive", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        } else if (i3 == i4) {
            return j2;
        } else {
            long j3 = ((long) i3) << 52;
            long j4 = H3_DIGIT_MASK;
            for (int i5 = 0; i5 < i3; i5++) {
                j4 >>= 3;
            }
            return (j2 & H3_RES_MASK_NEGATIVE) | j3 | j4;
        }
    }

    public String h3ToParentAddress(String str, int i3) {
        return h3ToString(h3ToParent(stringToH3(str), i3));
    }

    public String h3ToString(long j2) {
        return Long.toHexString(j2);
    }

    public boolean h3UnidirectionalEdgeIsValid(long j2) {
        return this.h3Api.h3UnidirectionalEdgeIsValid(j2);
    }

    public double hexArea(int i3, C0204a aVar) {
        checkResolution(i3);
        if (aVar == C0204a.km2) {
            return this.h3Api.hexAreaKm2(i3);
        }
        if (aVar == C0204a.m2) {
            return this.h3Api.hexAreaM2(i3);
        }
        throw new IllegalArgumentException("Invalid unit: " + aVar);
    }

    public List<List<Long>> hexRange(long j2, int i3) {
        int maxKringSize = this.h3Api.maxKringSize(i3);
        long[] jArr = new long[maxKringSize];
        if (this.h3Api.hexRange(j2, i3, jArr) == 0) {
            ArrayList arrayList = new ArrayList(i3 + 1);
            int i4 = 0;
            ArrayList arrayList2 = null;
            int i5 = 0;
            for (int i6 = 0; i6 < maxKringSize; i6++) {
                if (i6 == i5) {
                    arrayList2 = new ArrayList();
                    arrayList.add(arrayList2);
                    int i7 = i4 == 0 ? 1 : (i4 * 6) + i5;
                    i4++;
                    i5 = i7;
                }
                arrayList2.add(Long.valueOf(jArr[i6]));
            }
            return arrayList;
        }
        throw new d("A pentagon was encountered while computing hexRange.");
    }

    public List<Long> hexRing(long j2, int i3) {
        long[] jArr = new long[(i3 == 0 ? 1 : i3 * 6)];
        if (this.h3Api.hexRing(j2, i3, jArr) == 0) {
            return nonZeroLongArrayToList(jArr);
        }
        throw new d("A pentagon was encountered while computing hexRing.");
    }

    public List<Long> kRing(long j2, int i3) {
        long[] jArr = new long[this.h3Api.maxKringSize(i3)];
        this.h3Api.kRing(j2, i3, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public List<List<Long>> kRingDistances(long j2, int i3) {
        int maxKringSize = this.h3Api.maxKringSize(i3);
        long[] jArr = new long[maxKringSize];
        int[] iArr = new int[maxKringSize];
        this.h3Api.kRingDistances(j2, i3, jArr, iArr);
        ArrayList arrayList = new ArrayList(i3 + 1);
        for (int i4 = 0; i4 <= i3; i4++) {
            arrayList.add(new ArrayList());
        }
        for (int i5 = 0; i5 < maxKringSize; i5++) {
            long j3 = jArr[i5];
            if (j3 != 0) {
                ((List) arrayList.get(iArr[i5])).add(Long.valueOf(j3));
            }
        }
        return arrayList;
    }

    public List<List<String>> kRings(String str, int i3) {
        ArrayList arrayList = new ArrayList(i3 + 1);
        arrayList.add(Collections.singletonList(str));
        for (int i4 = 1; i4 <= i3; i4++) {
            arrayList.add(kRing(str, i4));
        }
        return arrayList;
    }

    public long numHexagons(int i3) {
        checkResolution(i3);
        return this.h3Api.numHexagons(i3);
    }

    public List<Long> polyfill(List<b> list, List<List<b>> list2, int i3) {
        double[] dArr;
        int[] iArr;
        checkResolution(i3);
        double[] dArr2 = new double[(list.size() * 2)];
        packGeofenceVertices(dArr2, list, 0);
        int[] iArr2 = new int[0];
        double[] dArr3 = new double[0];
        if (list2 != null) {
            int[] iArr3 = new int[list2.size()];
            int i4 = 0;
            for (int i5 = 0; i5 < list2.size(); i5++) {
                i4 = androidx.constraintlayout.core.state.b.c(list2.get(i5), 2, i4);
                iArr3[i5] = list2.get(i5).size() * 2;
            }
            double[] dArr4 = new double[i4];
            int i6 = 0;
            for (int i7 = 0; i7 < list2.size(); i7++) {
                i6 = packGeofenceVertices(dArr4, list2.get(i7), i6);
            }
            iArr = iArr3;
            dArr = dArr4;
        } else {
            iArr = iArr2;
            dArr = dArr3;
        }
        long[] jArr = new long[this.h3Api.maxPolyfillSize(dArr2, iArr, dArr, i3)];
        this.h3Api.polyfill(dArr2, iArr, dArr, i3, jArr);
        return nonZeroLongArrayToList(jArr);
    }

    public List<String> polyfillAddress(List<b> list, List<List<b>> list2, int i3) {
        return h3ToStringList(polyfill(list, list2, i3));
    }

    public long stringToH3(String str) {
        return Long.parseUnsignedLong(str, 16);
    }

    public List<Long> uncompact(Collection<Long> collection, int i3) {
        checkResolution(i3);
        long[] collectionToLongArray = collectionToLongArray(collection);
        long[] jArr = new long[this.h3Api.maxUncompactSize(collectionToLongArray, i3)];
        if (this.h3Api.uncompact(collectionToLongArray, i3, jArr) == 0) {
            return nonZeroLongArrayToList(jArr);
        }
        throw new IllegalArgumentException("Bad input to uncompact");
    }

    public List<String> uncompactAddress(Collection<String> collection, int i3) {
        return h3ToStringList(uncompact(stringToH3List(collection), i3));
    }

    public static H3Core newInstance() {
        a.C0067a aVar;
        String property = System.getProperty("java.vendor");
        String property2 = System.getProperty("os.name");
        if (property.toLowerCase().contains(DeviceInfo.OS_NAME)) {
            aVar = a.C0067a.ANDROID;
        } else {
            String lowerCase = property2.toLowerCase();
            aVar = lowerCase.contains("mac") ? a.C0067a.DARWIN : lowerCase.contains("win") ? a.C0067a.WINDOWS : a.C0067a.LINUX;
        }
        String property3 = System.getProperty("os.arch");
        if (property3.equals("amd64") || property3.equals("x86_64")) {
            property3 = "x64";
        } else if (property3.equals("i386") || property3.equals("i486") || property3.equals("i586") || property3.equals("i686") || property3.equals("i786") || property3.equals("i886")) {
            property3 = "x86";
        } else if (property3.equals("aarch64")) {
            property3 = "arm64";
        }
        return new H3Core(a.a(aVar, property3));
    }

    public C0222a experimentalH3ToLocalIj(String str, String str2) {
        return experimentalH3ToLocalIj(stringToH3(str), stringToH3(str2));
    }

    public String experimentalLocalIjToH3(String str, C0222a aVar) {
        return h3ToString(experimentalLocalIjToH3(stringToH3(str), aVar));
    }

    public String getDestinationH3IndexFromUnidirectionalEdge(String str) {
        return h3ToString(getDestinationH3IndexFromUnidirectionalEdge(stringToH3(str)));
    }

    public List<String> getH3IndexesFromUnidirectionalEdge(String str) {
        return h3ToStringList(getH3IndexesFromUnidirectionalEdge(stringToH3(str)));
    }

    public String getH3UnidirectionalEdge(String str, String str2) {
        return h3ToString(getH3UnidirectionalEdge(stringToH3(str), stringToH3(str2)));
    }

    public List<b> getH3UnidirectionalEdgeBoundary(String str) {
        return getH3UnidirectionalEdgeBoundary(stringToH3(str));
    }

    public List<String> getH3UnidirectionalEdgesFromHexagon(String str) {
        return h3ToStringList(getH3UnidirectionalEdgesFromHexagon(stringToH3(str)));
    }

    public String getOriginH3IndexFromUnidirectionalEdge(String str) {
        return h3ToString(getOriginH3IndexFromUnidirectionalEdge(stringToH3(str)));
    }

    public int h3Distance(String str, String str2) {
        return h3Distance(stringToH3(str), stringToH3(str2));
    }

    public int h3GetBaseCell(String str) {
        return h3GetBaseCell(stringToH3(str));
    }

    public Collection<Integer> h3GetFaces(String str) {
        return h3GetFaces(stringToH3(str));
    }

    public int h3GetResolution(String str) {
        return h3GetResolution(stringToH3(str));
    }

    public boolean h3IndexesAreNeighbors(String str, String str2) {
        return h3IndexesAreNeighbors(stringToH3(str), stringToH3(str2));
    }

    public boolean h3IsPentagon(String str) {
        return h3IsPentagon(stringToH3(str));
    }

    public boolean h3IsResClassIII(String str) {
        return h3IsResClassIII(stringToH3(str));
    }

    public boolean h3IsValid(String str) {
        return h3IsValid(stringToH3(str));
    }

    public List<String> h3Line(String str, String str2) {
        return h3ToStringList(h3Line(stringToH3(str), stringToH3(str2)));
    }

    public String h3ToCenterChild(String str, int i3) {
        return h3ToString(h3ToCenterChild(stringToH3(str), i3));
    }

    public List<String> h3ToChildren(String str, int i3) {
        return h3ToStringList(h3ToChildren(stringToH3(str), i3));
    }

    public b h3ToGeo(String str) {
        return h3ToGeo(stringToH3(str));
    }

    public List<b> h3ToGeoBoundary(String str) {
        return h3ToGeoBoundary(stringToH3(str));
    }

    public boolean h3UnidirectionalEdgeIsValid(String str) {
        return h3UnidirectionalEdgeIsValid(stringToH3(str));
    }

    public List<List<String>> hexRange(String str, int i3) {
        return (List) hexRange(stringToH3(str), i3).stream().map(new b(this, 0)).collect(Collectors.toList());
    }

    public List<String> hexRing(String str, int i3) {
        return h3ToStringList(hexRing(stringToH3(str), i3));
    }

    public List<String> kRing(String str, int i3) {
        return h3ToStringList(kRing(stringToH3(str), i3));
    }

    public List<List<String>> kRingDistances(String str, int i3) {
        return (List) kRingDistances(stringToH3(str), i3).stream().map(new b(this, 0)).collect(Collectors.toList());
    }
}
