package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.CpuInfo;
import com.kenai.jnr.x86asm.RelocData;
import java.util.LinkedList;
import java.util.List;

@Deprecated
public final class Assembler extends Serializer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final CPU I386 = CPU.I386;
    public static final CPU X86_64 = CPU.X86_64;
    private static final int[] nop1 = {144};
    private static final int[] nop10 = {102, 102, 15, 31, 132, 0, 0, 0, 0, 0};
    private static final int[] nop11 = {102, 102, 102, 15, 31, 132, 0, 0, 0, 0, 0};
    private static final int[] nop2 = {102, 144};
    private static final int[] nop3 = {15, 31, 0};
    private static final int[] nop4 = {15, 31, 64, 0};
    private static final int[] nop5 = {15, 31, 68, 0, 0};
    private static final int[] nop6 = {102, 15, 31, 68, 0, 0};
    private static final int[] nop7 = {15, 31, 128, 0, 0, 0, 0};
    private static final int[] nop8 = {15, 31, 132, 0, 0, 0, 0, 0};
    private static final int[] nop9 = {102, 15, 31, 132, 0, 0, 0, 0, 0};
    private final CodeBuffer _buffer = new CodeBuffer();
    private final Logger _logger = null;
    private int _properties = 0;
    private final List<RelocData> _relocData = new LinkedList();
    int _trampolineSize;
    private final CPU cpu;
    private final CpuInfo cpuInfo = CpuInfo.GENERIC;

    /* renamed from: com.kenai.jnr.x86asm.Assembler$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup;
        static final /* synthetic */ int[] $SwitchMap$com$kenai$jnr$x86asm$RelocData$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(92:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|(3:99|100|102)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(93:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|(3:99|100|102)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(96:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|102) */
        /* JADX WARNING: Can't wrap try/catch for region: R(97:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|102) */
        /* JADX WARNING: Can't wrap try/catch for region: R(98:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|102) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x009b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00a7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00b3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00bf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00cb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00d7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00e3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00ef */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00fb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0107 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0113 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x011f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x012b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0137 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0143 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x014f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x015b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0167 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x0173 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x017f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x018b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x0197 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x01a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x01af */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x01bb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x01c7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x01d3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x01df */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x01eb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x01f7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x0203 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x020f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x021b */
        static {
            /*
                com.kenai.jnr.x86asm.RelocData$Type[] r0 = com.kenai.jnr.x86asm.RelocData.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$kenai$jnr$x86asm$RelocData$Type = r0
                r1 = 1
                com.kenai.jnr.x86asm.RelocData$Type r2 = com.kenai.jnr.x86asm.RelocData.Type.ABSOLUTE_TO_ABSOLUTE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$kenai$jnr$x86asm$RelocData$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.kenai.jnr.x86asm.RelocData$Type r3 = com.kenai.jnr.x86asm.RelocData.Type.RELATIVE_TO_ABSOLUTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$kenai$jnr$x86asm$RelocData$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.kenai.jnr.x86asm.RelocData$Type r4 = com.kenai.jnr.x86asm.RelocData.Type.ABSOLUTE_TO_RELATIVE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$kenai$jnr$x86asm$RelocData$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.kenai.jnr.x86asm.RelocData$Type r5 = com.kenai.jnr.x86asm.RelocData.Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.kenai.jnr.x86asm.InstructionGroup[] r4 = com.kenai.jnr.x86asm.InstructionGroup.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup = r4
                com.kenai.jnr.x86asm.InstructionGroup r5 = com.kenai.jnr.x86asm.InstructionGroup.I_EMIT     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x004e }
                com.kenai.jnr.x86asm.InstructionGroup r4 = com.kenai.jnr.x86asm.InstructionGroup.I_ALU     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_BSWAP     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_BT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x006d }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_CALL     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_CRC32     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_ENTER     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x008f }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_IMUL     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x009b }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_INC_DEC     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00a7 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_J     // Catch:{ NoSuchFieldError -> 0x00a7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a7 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a7 }
            L_0x00a7:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00b3 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_JMP     // Catch:{ NoSuchFieldError -> 0x00b3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b3 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b3 }
            L_0x00b3:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00bf }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_LEA     // Catch:{ NoSuchFieldError -> 0x00bf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bf }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00bf }
            L_0x00bf:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00cb }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_M     // Catch:{ NoSuchFieldError -> 0x00cb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cb }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cb }
            L_0x00cb:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00d7 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MOV     // Catch:{ NoSuchFieldError -> 0x00d7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d7 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d7 }
            L_0x00d7:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00e3 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MOV_PTR     // Catch:{ NoSuchFieldError -> 0x00e3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e3 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e3 }
            L_0x00e3:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00ef }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MOVSX_MOVZX     // Catch:{ NoSuchFieldError -> 0x00ef }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ef }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ef }
            L_0x00ef:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x00fb }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MOVSXD     // Catch:{ NoSuchFieldError -> 0x00fb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fb }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fb }
            L_0x00fb:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0107 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_PUSH     // Catch:{ NoSuchFieldError -> 0x0107 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0107 }
            L_0x0107:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0113 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_POP     // Catch:{ NoSuchFieldError -> 0x0113 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0113 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0113 }
            L_0x0113:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x011f }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_R_RM     // Catch:{ NoSuchFieldError -> 0x011f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x011f }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x011f }
            L_0x011f:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x012b }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_RM_B     // Catch:{ NoSuchFieldError -> 0x012b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012b }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012b }
            L_0x012b:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0137 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_RM     // Catch:{ NoSuchFieldError -> 0x0137 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0137 }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0137 }
            L_0x0137:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0143 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_RM_R     // Catch:{ NoSuchFieldError -> 0x0143 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0143 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0143 }
            L_0x0143:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x014f }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_RET     // Catch:{ NoSuchFieldError -> 0x014f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x014f }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x014f }
            L_0x014f:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x015b }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_ROT     // Catch:{ NoSuchFieldError -> 0x015b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015b }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015b }
            L_0x015b:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0167 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_SHLD_SHRD     // Catch:{ NoSuchFieldError -> 0x0167 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0167 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0167 }
            L_0x0167:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0173 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_TEST     // Catch:{ NoSuchFieldError -> 0x0173 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0173 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0173 }
            L_0x0173:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x017f }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_XCHG     // Catch:{ NoSuchFieldError -> 0x017f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x017f }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x017f }
            L_0x017f:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x018b }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MOVBE     // Catch:{ NoSuchFieldError -> 0x018b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x018b }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x018b }
            L_0x018b:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0197 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_X87_FPU     // Catch:{ NoSuchFieldError -> 0x0197 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0197 }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0197 }
            L_0x0197:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01a3 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_X87_STI     // Catch:{ NoSuchFieldError -> 0x01a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a3 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01a3 }
            L_0x01a3:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01af }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_X87_FSTSW     // Catch:{ NoSuchFieldError -> 0x01af }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01af }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01af }
            L_0x01af:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01bb }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_X87_MEM_STI     // Catch:{ NoSuchFieldError -> 0x01bb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01bb }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01bb }
            L_0x01bb:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01c7 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_X87_MEM     // Catch:{ NoSuchFieldError -> 0x01c7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c7 }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01c7 }
            L_0x01c7:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01d3 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_MOV     // Catch:{ NoSuchFieldError -> 0x01d3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d3 }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01d3 }
            L_0x01d3:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01df }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_MOVD     // Catch:{ NoSuchFieldError -> 0x01df }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01df }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01df }
            L_0x01df:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01eb }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_MOVQ     // Catch:{ NoSuchFieldError -> 0x01eb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01eb }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01eb }
            L_0x01eb:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x01f7 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_PREFETCH     // Catch:{ NoSuchFieldError -> 0x01f7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01f7 }
                r2 = 38
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01f7 }
            L_0x01f7:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0203 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_PEXTR     // Catch:{ NoSuchFieldError -> 0x0203 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0203 }
                r2 = 39
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0203 }
            L_0x0203:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x020f }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_RMI     // Catch:{ NoSuchFieldError -> 0x020f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x020f }
                r2 = 40
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x020f }
            L_0x020f:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x021b }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_RM_IMM8     // Catch:{ NoSuchFieldError -> 0x021b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x021b }
                r2 = 41
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x021b }
            L_0x021b:
                int[] r0 = $SwitchMap$com$kenai$jnr$x86asm$InstructionGroup     // Catch:{ NoSuchFieldError -> 0x0227 }
                com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_MMU_RM_3DNOW     // Catch:{ NoSuchFieldError -> 0x0227 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0227 }
                r2 = 42
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0227 }
            L_0x0227:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kenai.jnr.x86asm.Assembler.AnonymousClass1.<clinit>():void");
        }
    }

    public Assembler(CPU cpu2) {
        this.cpu = cpu2;
    }

    private static final int intValue(boolean z2) {
        return z2 ? 1 : 0;
    }

    public final void _emitByte(int i3) {
        this._buffer.emitByte((byte) i3);
    }

    public final void _emitDWord(int i3) {
        this._buffer.emitDWord(i3);
    }

    public LinkData _emitDisplacement(Label label, long j2, int i3) {
        LinkData linkData = new LinkData(offset(), j2, -1);
        label.link(linkData);
        if (i3 == 1) {
            _emitByte(1);
        } else {
            _emitDWord(67372036);
        }
        return linkData;
    }

    public void _emitFpu(int i3) {
        _emitOpCode(i3);
    }

    public void _emitFpuMEM(int i3, int i4, Mem mem) {
        _emitSegmentPrefix(mem);
        int i5 = -16777216 & i3;
        if (i5 != 0) {
            _emitByte(i5 >> 24);
        }
        if (is64()) {
            _emitRexRM(0, i4, (Operand) mem);
        }
        int i6 = 16711680 & i3;
        if (i6 != 0) {
            _emitByte(i6 >> 16);
        }
        int i7 = 65280 & i3;
        if (i7 != 0) {
            _emitByte(i7 >> 8);
        }
        _emitByte(i3 & 255);
        _emitModM(i4, mem, 0);
    }

    public void _emitFpuSTI(int i3, int i4) {
        _emitOpCode(i3 + i4);
    }

    public void _emitImmediate(Immediate immediate, int i3) {
        if (i3 == 1) {
            _emitByte(immediate.byteValue());
        } else if (i3 == 2) {
            _emitWord(immediate.shortValue());
        } else if (i3 == 4) {
            _emitDWord(immediate.intValue());
        } else if (i3 != 8) {
            throw new IllegalArgumentException("invalid immediate operand size");
        } else if (is64()) {
            _emitQWord(immediate.longValue());
        } else {
            throw new IllegalArgumentException("64 bit immediate values not supported for 32bit");
        }
    }

    public final void _emitInt32(int i3) {
        this._buffer.emitDWord(i3);
    }

    public void _emitJmpOrCallReloc(InstructionGroup instructionGroup, long j2) {
        if (is64()) {
            this._trampolineSize += 14;
        }
        this._relocData.add(new RelocData(RelocData.Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE, 4, offset(), j2));
        _emitInt32(0);
    }

    public void _emitMmu(int i3, int i4, int i5, Operand operand, int i6) {
        _emitSegmentPrefix(operand);
        int i7 = -16777216 & i3;
        if (i7 != 0) {
            _emitByte(i7 >> 24);
        }
        if (is64()) {
            _emitRexRM(i4, i5, operand);
        }
        int i8 = 16711680 & i3;
        if (i8 != 0) {
            _emitByte(i8 >> 16);
        }
        _emitByte((65280 & i3) >> 8);
        _emitByte(i3 & 255);
        if (operand.isReg()) {
            _emitModR(i5, ((BaseReg) operand).code());
        } else {
            _emitModM(i5, (Mem) operand, i6);
        }
    }

    public void _emitMod(int i3, int i4, int i5) {
        _emitByte((byte) (((i3 & 3) << 6) | ((i4 & 7) << 3) | (i5 & 7)));
    }

    public void _emitModM(int i3, Mem mem, int i4) {
        int i5 = i3;
        int base = mem.base() & 7;
        int index = mem.index() & 7;
        long displacement = mem.displacement();
        int shift = mem.shift();
        int i6 = 2;
        if (!mem.hasBase() || mem.hasIndex()) {
            if (!mem.hasBase() || !mem.hasIndex()) {
                if (!is64()) {
                    if (mem.hasIndex()) {
                        _emitMod(0, i5, 4);
                        _emitSib(shift, index, 5);
                    } else {
                        _emitMod(0, i5, 5);
                    }
                    if (mem.hasLabel()) {
                        Label label = mem.label();
                        int size = this._relocData.size();
                        if (label.isBound()) {
                            displacement += (long) label.position();
                            _emitInt32(0);
                        } else {
                            _emitDisplacement(label, (long) (-4 - i4), 4).relocId = size;
                        }
                        this._relocData.add(new RelocData(RelocData.Type.RELATIVE_TO_ABSOLUTE, 4, offset(), displacement));
                        return;
                    }
                    _emitInt32((int) (mem.target() + displacement));
                } else if (mem.hasLabel()) {
                    Label label2 = mem.label();
                    if (!mem.hasIndex()) {
                        _emitMod(0, i5, 5);
                        long j2 = displacement - ((long) (i4 + 4));
                        if (label2.isBound()) {
                            _emitInt32((int) (j2 + ((long) (offset() - label2.position()))));
                        } else {
                            _emitDisplacement(label2, j2, 4);
                        }
                    } else {
                        throw new IllegalArgumentException("illegal addressing");
                    }
                } else {
                    _emitMod(0, i5, 4);
                    if (mem.hasIndex()) {
                        _emitSib(shift, index, 5);
                    } else {
                        _emitSib(0, 4, 5);
                    }
                    long target = mem.target() + displacement;
                    if (target > 4294967295L) {
                        this._logger.log("; Warning: Absolute address truncated to 32 bits\n");
                    }
                    _emitInt32((int) target);
                }
            } else if (base != 5 && displacement == 0) {
                _emitMod(0, i5, 4);
                _emitSib(shift, index, base);
            } else if (Util.isInt8(displacement)) {
                _emitMod(1, i5, 4);
                _emitSib(shift, index, base);
                _emitByte((byte) ((int) displacement));
            } else {
                _emitMod(2, i5, 4);
                _emitSib(shift, index, base);
                _emitInt32((int) displacement);
            }
        } else if (base == 4) {
            int i7 = (displacement > 0 ? 1 : (displacement == 0 ? 0 : -1));
            if (i7 == 0) {
                i6 = 0;
            } else if (Util.isInt8(displacement)) {
                i6 = 1;
            }
            _emitMod(i6, i5, 4);
            _emitSib(0, 4, 4);
            if (i7 == 0) {
                return;
            }
            if (Util.isInt8(displacement)) {
                _emitByte((byte) ((int) displacement));
            } else {
                _emitInt32((int) displacement);
            }
        } else if (base != 5 && displacement == 0) {
            _emitMod(0, i5, base);
        } else if (Util.isInt8(displacement)) {
            _emitMod(1, i5, base);
            _emitByte((byte) ((int) displacement));
        } else {
            _emitMod(2, i5, base);
            _emitInt32((int) displacement);
        }
    }

    public void _emitModR(int i3, int i4) {
        _emitMod(3, i3, i4);
    }

    public void _emitModRM(int i3, Operand operand, int i4) {
        if (operand.op() == 1) {
            _emitModR(i3, ((BaseReg) operand).code());
        } else {
            _emitModM(i3, (Mem) operand, i4);
        }
    }

    public final void _emitOpCode(int i3) {
        int i4 = -16777216 & i3;
        if (i4 != 0) {
            _emitByte((byte) (i4 >> 24));
        }
        int i5 = 16711680 & i3;
        if (i5 != 0) {
            _emitByte((byte) (i5 >> 16));
        }
        int i6 = 65280 & i3;
        if (i6 != 0) {
            _emitByte((byte) (i6 >> 8));
        }
        _emitByte((byte) (i3 & 255));
    }

    public final void _emitQWord(long j2) {
        this._buffer.emitQWord(j2);
    }

    public void _emitRexR(int i3, int i4, int i5) {
        if (is64()) {
            boolean z2 = false;
            boolean z3 = (i4 & 8) != 0;
            if ((i5 & 8) != 0) {
                z2 = true;
            }
            if (i3 != 0 || z3 || z2 || (this._properties & 2) != 0) {
                _emitByte((i3 << 3) | 64 | (intValue(z3) << 2) | intValue(z2));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _emitRexRM(int r6, int r7, com.kenai.jnr.x86asm.Operand r8) {
        /*
            r5 = this;
            boolean r0 = r5.is64()
            if (r0 == 0) goto L_0x0076
            r7 = r7 & 8
            r0 = 1
            r1 = 0
            if (r7 == 0) goto L_0x000e
            r7 = r0
            goto L_0x000f
        L_0x000e:
            r7 = r1
        L_0x000f:
            boolean r2 = r8.isReg()
            if (r2 == 0) goto L_0x0021
            com.kenai.jnr.x86asm.BaseReg r8 = (com.kenai.jnr.x86asm.BaseReg) r8
            int r8 = r8.code()
            r8 = r8 & 8
            if (r8 == 0) goto L_0x004e
            r8 = r0
            goto L_0x004f
        L_0x0021:
            boolean r2 = r8.isMem()
            if (r2 == 0) goto L_0x004e
            com.kenai.jnr.x86asm.Mem r8 = (com.kenai.jnr.x86asm.Mem) r8
            int r2 = r8.index()
            r2 = r2 & 8
            r3 = 255(0xff, float:3.57E-43)
            if (r2 == 0) goto L_0x003b
            int r2 = r8.index()
            if (r2 == r3) goto L_0x003b
            r2 = r0
            goto L_0x003c
        L_0x003b:
            r2 = r1
        L_0x003c:
            int r4 = r8.base()
            r4 = r4 & 8
            if (r4 == 0) goto L_0x004b
            int r8 = r8.base()
            if (r8 == r3) goto L_0x004b
            r1 = r0
        L_0x004b:
            r8 = r1
            r1 = r2
            goto L_0x004f
        L_0x004e:
            r8 = r1
        L_0x004f:
            if (r6 != 0) goto L_0x005d
            if (r7 != 0) goto L_0x005d
            if (r1 != 0) goto L_0x005d
            if (r8 != 0) goto L_0x005d
            int r2 = r5._properties
            r2 = r2 & 2
            if (r2 == 0) goto L_0x0076
        L_0x005d:
            int r6 = r6 << 3
            r6 = r6 | 64
            int r7 = intValue(r7)
            int r7 = r7 << 2
            r6 = r6 | r7
            int r7 = intValue(r1)
            int r7 = r7 << r0
            r6 = r6 | r7
            int r7 = intValue(r8)
            r6 = r6 | r7
            r5._emitByte(r6)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jnr.x86asm.Assembler._emitRexRM(int, int, com.kenai.jnr.x86asm.Operand):void");
    }

    public void _emitSegmentPrefix(Operand operand) {
        SEGMENT segmentPrefix;
        if (operand.isMem() && (segmentPrefix = ((Mem) operand).segmentPrefix()) != SEGMENT.SEGMENT_NONE) {
            _emitByte(segmentPrefix.prefix());
        }
    }

    public void _emitSib(int i3, int i4, int i5) {
        _emitByte((byte) (((i3 & 3) << 6) | ((i4 & 7) << 3) | (i5 & 7)));
    }

    public final void _emitSysInt(long j2) {
        if (is64()) {
            this._buffer.emitQWord(j2);
        } else {
            this._buffer.emitDWord((int) j2);
        }
    }

    public final void _emitWord(int i3) {
        this._buffer.emitWord((short) i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1171:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1196:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x06b6, code lost:
        if (r20.isMem() == false) goto L_0x0709;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:438:0x06b8, code lost:
        r0 = (com.kenai.jnr.x86asm.Mem) r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:439:0x06c0, code lost:
        if (r20.size() != 2) goto L_0x06d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:441:0x06c5, code lost:
        if ((r6.o1Flags & 2) == 0) goto L_0x06d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:442:0x06c7, code lost:
        r1 = (r6.opCode1 & androidx.core.view.ViewCompat.MEASURED_STATE_MASK) >> 24;
        r3 = r6.opCodeR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:0x06d1, code lost:
        r1 = 0;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:445:0x06d7, code lost:
        if (r20.size() != 4) goto L_0x06e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:447:0x06dc, code lost:
        if ((r6.o1Flags & 4) == 0) goto L_0x06e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:0x06de, code lost:
        r1 = (r6.opCode1 & 16711680) >> 16;
        r3 = r6.opCodeR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:0x06ea, code lost:
        if (r20.size() != 8) goto L_0x06fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:0x06ef, code lost:
        if ((r6.o1Flags & 8) == 0) goto L_0x06fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:453:0x06f1, code lost:
        r1 = r6.opCode1;
        r3 = r1 & 255;
        r1 = (65280 & r1) >> 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:454:0x06fc, code lost:
        if (r1 == 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:455:0x06fe, code lost:
        _emitSegmentPrefix(r0);
        _emitByte(r1);
        _emitModM(r3, r0, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:456:0x0708, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:458:0x0710, code lost:
        throw new java.lang.IllegalArgumentException("not x87 mem");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:729:0x0b81, code lost:
        if (r20.isReg() == false) goto L_0x0b95;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:730:0x0b83, code lost:
        _emitX86Inl(r6.opCode1, r5.isRegType(16), 0, ((com.kenai.jnr.x86asm.Register) r5).code());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:731:0x0b94, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:733:0x0b99, code lost:
        if (r20.isMem() == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:734:0x0b9b, code lost:
        r1 = r6.opCode2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:735:0x0ba2, code lost:
        if (r20.size() != 2) goto L_0x0ba6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:736:0x0ba4, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:737:0x0ba6, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:738:0x0ba7, code lost:
        _emitX86RM(r1, r2, 0, r6.opCodeR, r20, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:739:0x0bb2, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:1137:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:638:0x0a1e  */
    /* JADX WARNING: Removed duplicated region for block: B:639:0x0a21  */
    /* JADX WARNING: Removed duplicated region for block: B:642:0x0a29  */
    /* JADX WARNING: Removed duplicated region for block: B:645:0x0a31  */
    /* JADX WARNING: Removed duplicated region for block: B:648:0x0a3b  */
    /* JADX WARNING: Removed duplicated region for block: B:649:0x0a3d  */
    /* JADX WARNING: Removed duplicated region for block: B:652:0x0a44  */
    /* JADX WARNING: Removed duplicated region for block: B:653:0x0a47  */
    /* JADX WARNING: Removed duplicated region for block: B:656:0x0a5b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _emitX86(com.kenai.jnr.x86asm.INST_CODE r19, com.kenai.jnr.x86asm.Operand r20, com.kenai.jnr.x86asm.Operand r21, com.kenai.jnr.x86asm.Operand r22) {
        /*
            r18 = this;
            r7 = r18
            r0 = r19
            r5 = r20
            r8 = r21
            r9 = r22
            com.kenai.jnr.x86asm.InstructionDescription r6 = com.kenai.jnr.x86asm.InstructionDescription.find(r19)
            int[] r1 = com.kenai.jnr.x86asm.Assembler.AnonymousClass1.$SwitchMap$com$kenai$jnr$x86asm$InstructionGroup
            com.kenai.jnr.x86asm.InstructionGroup r2 = r6.group
            int r2 = r2.ordinal()
            r1 = r1[r2]
            java.lang.String r13 = "illegal instruction"
            r14 = 32
            r15 = 96
            r2 = 8
            r4 = 112(0x70, float:1.57E-43)
            r10 = 4
            r11 = 16
            r3 = 48
            r12 = 1
            switch(r1) {
                case 1: goto L_0x130c;
                case 2: goto L_0x120c;
                case 3: goto L_0x11e0;
                case 4: goto L_0x1184;
                case 5: goto L_0x1123;
                case 6: goto L_0x10e3;
                case 7: goto L_0x10c3;
                case 8: goto L_0x0f9a;
                case 9: goto L_0x0f3b;
                case 10: goto L_0x0e84;
                case 11: goto L_0x0dfa;
                case 12: goto L_0x0dd3;
                case 13: goto L_0x0dbc;
                case 14: goto L_0x0cca;
                case 15: goto L_0x0c4d;
                case 16: goto L_0x0bdf;
                case 17: goto L_0x0bb3;
                case 18: goto L_0x0b50;
                case 19: goto L_0x0b7d;
                case 20: goto L_0x0b20;
                case 21: goto L_0x0b0c;
                case 22: goto L_0x0ad7;
                case 23: goto L_0x0a99;
                case 24: goto L_0x0a62;
                case 25: goto L_0x09ec;
                case 26: goto L_0x0998;
                case 27: goto L_0x08b1;
                case 28: goto L_0x083f;
                case 29: goto L_0x07f0;
                case 30: goto L_0x0763;
                case 31: goto L_0x0741;
                case 32: goto L_0x0711;
                case 33: goto L_0x068e;
                case 34: goto L_0x06b2;
                case 35: goto L_0x058a;
                case 36: goto L_0x051a;
                case 37: goto L_0x039f;
                case 38: goto L_0x037e;
                case 39: goto L_0x0292;
                case 40: goto L_0x0164;
                case 41: goto L_0x0058;
                case 42: goto L_0x002d;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x130b
        L_0x002d:
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x130b
            boolean r0 = r8.isRegType(r15)
            if (r0 != 0) goto L_0x003f
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x130b
        L_0x003f:
            int r1 = r6.opCode1
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 1
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            int r0 = r6.opCode2
            r7._emitByte(r0)
            return
        L_0x0058:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x015e
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x0069
            int r0 = r6.o1Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x015e
        L_0x0069:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0074
            int r0 = r6.o1Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x015e
        L_0x0074:
            boolean r0 = r5.isRegType(r14)
            if (r0 == 0) goto L_0x007f
            int r0 = r6.o1Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x015e
        L_0x007f:
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x008a
            int r0 = r6.o1Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x015e
        L_0x008a:
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x0095
            int r0 = r6.o2Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x015e
        L_0x0095:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x00a0
            int r0 = r6.o2Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x015e
        L_0x00a0:
            boolean r0 = r8.isRegType(r14)
            if (r0 == 0) goto L_0x00ab
            int r0 = r6.o2Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x015e
        L_0x00ab:
            boolean r0 = r8.isRegType(r3)
            if (r0 == 0) goto L_0x00b6
            int r0 = r6.o2Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x015e
        L_0x00b6:
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x00c2
            int r0 = r6.o2Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x015e
        L_0x00c2:
            boolean r0 = r22.isImm()
            if (r0 == 0) goto L_0x015e
            int r0 = r6.o1Flags
            r0 = r0 & r3
            if (r0 != r3) goto L_0x00d3
            boolean r0 = r5.isRegType(r4)
            if (r0 != 0) goto L_0x00de
        L_0x00d3:
            int r0 = r6.o2Flags
            r0 = r0 & r3
            if (r0 != r3) goto L_0x00e1
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x00e1
        L_0x00de:
            r16 = 1711276032(0x66000000, float:1.5111573E23)
            goto L_0x00e3
        L_0x00e1:
            r16 = 0
        L_0x00e3:
            int r0 = r6.o1Flags
            int r1 = r6.o2Flags
            r0 = r0 | r1
            r0 = r0 & r12
            if (r0 == 0) goto L_0x00ed
            r2 = 0
            goto L_0x0104
        L_0x00ed:
            boolean r0 = r5.isRegType(r3)
            if (r0 != 0) goto L_0x00fd
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x00fa
            goto L_0x00fd
        L_0x00fa:
            r17 = 0
            goto L_0x00ff
        L_0x00fd:
            r17 = r12
        L_0x00ff:
            int r0 = intValue(r17)
            r2 = r0
        L_0x0104:
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x0131
            int r0 = r6.o2Flags
            r0 = r0 & 60
            if (r0 == 0) goto L_0x012b
            int r0 = r6.opCode1
            r1 = r0 | r16
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.BaseReg r4 = (com.kenai.jnr.x86asm.BaseReg) r4
            r5 = 1
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            r0 = r9
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            return
        L_0x012b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0131:
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x130b
            int r0 = r6.o2Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x0158
            int r0 = r6.opCode1
            r1 = r0 | r16
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 1
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            r0 = r9
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            return
        L_0x0158:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x015e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0164:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x028c
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x0175
            int r0 = r6.o1Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x028c
        L_0x0175:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0180
            int r0 = r6.o1Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x028c
        L_0x0180:
            boolean r0 = r5.isRegType(r14)
            if (r0 == 0) goto L_0x018b
            int r0 = r6.o1Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x028c
        L_0x018b:
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x0196
            int r0 = r6.o1Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x028c
        L_0x0196:
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x01a1
            int r0 = r6.o2Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x028c
        L_0x01a1:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x01ac
            int r0 = r6.o2Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x028c
        L_0x01ac:
            boolean r0 = r8.isRegType(r14)
            if (r0 == 0) goto L_0x01b7
            int r0 = r6.o2Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x028c
        L_0x01b7:
            boolean r0 = r8.isRegType(r3)
            if (r0 == 0) goto L_0x01c2
            int r0 = r6.o2Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x028c
        L_0x01c2:
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x01ce
            int r0 = r6.o2Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x028c
        L_0x01ce:
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x01da
            int r0 = r6.o2Flags
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x028c
        L_0x01da:
            int r0 = r6.o1Flags
            r0 = r0 & r3
            if (r0 != r3) goto L_0x01e5
            boolean r0 = r5.isRegType(r4)
            if (r0 != 0) goto L_0x01f0
        L_0x01e5:
            int r0 = r6.o2Flags
            r0 = r0 & r3
            if (r0 != r3) goto L_0x01f3
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x01f3
        L_0x01f0:
            r16 = 1711276032(0x66000000, float:1.5111573E23)
            goto L_0x01f5
        L_0x01f3:
            r16 = 0
        L_0x01f5:
            int r0 = r6.o1Flags
            int r1 = r6.o2Flags
            r0 = r0 | r1
            r0 = r0 & r12
            if (r0 == 0) goto L_0x01ff
            r2 = 0
            goto L_0x0216
        L_0x01ff:
            boolean r0 = r5.isRegType(r3)
            if (r0 != 0) goto L_0x020f
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x020c
            goto L_0x020f
        L_0x020c:
            r17 = 0
            goto L_0x0211
        L_0x020f:
            r17 = r12
        L_0x0211:
            int r0 = intValue(r17)
            r2 = r0
        L_0x0216:
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x023d
            int r0 = r6.o2Flags
            r0 = r0 & 60
            if (r0 == 0) goto L_0x0237
            int r0 = r6.opCode1
            r1 = r0 | r16
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.BaseReg r4 = (com.kenai.jnr.x86asm.BaseReg) r4
            r5 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0237:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x023d:
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0264
            int r0 = r6.o2Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x025e
            int r0 = r6.opCode1
            r1 = r0 | r16
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x025e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0264:
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            int r0 = r6.o2Flags
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0286
            int r0 = r6.opCode2
            r1 = r0 | r16
            int r3 = r6.opCodeR
            r4 = r5
            com.kenai.jnr.x86asm.BaseReg r4 = (com.kenai.jnr.x86asm.BaseReg) r4
            r5 = 1
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            return
        L_0x0286:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x028c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0292:
            boolean r1 = r20.isRegMem()
            if (r1 == 0) goto L_0x0378
            boolean r1 = r8.isRegType(r4)
            if (r1 != 0) goto L_0x02a8
            com.kenai.jnr.x86asm.INST_CODE r1 = com.kenai.jnr.x86asm.INST_CODE.INST_PEXTRW
            if (r0 != r1) goto L_0x0378
            boolean r1 = r8.isRegType(r15)
            if (r1 == 0) goto L_0x0378
        L_0x02a8:
            boolean r1 = r22.isImm()
            if (r1 == 0) goto L_0x0378
            int r1 = r6.opCode1
            boolean r11 = r5.isRegType(r14)
            if (r11 != 0) goto L_0x02c0
            boolean r11 = r5.isRegType(r3)
            if (r11 == 0) goto L_0x02bd
            goto L_0x02c0
        L_0x02bd:
            r17 = 0
            goto L_0x02c2
        L_0x02c0:
            r17 = r12
        L_0x02c2:
            com.kenai.jnr.x86asm.INST_CODE r11 = com.kenai.jnr.x86asm.INST_CODE.INST_PEXTRB
            if (r0 != r11) goto L_0x02db
            int r11 = r20.size()
            if (r11 == 0) goto L_0x02db
            int r11 = r20.size()
            if (r11 == r12) goto L_0x02db
            if (r17 == 0) goto L_0x02d5
            goto L_0x02db
        L_0x02d5:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x02db:
            com.kenai.jnr.x86asm.INST_CODE r11 = com.kenai.jnr.x86asm.INST_CODE.INST_PEXTRW
            if (r0 != r11) goto L_0x02f5
            int r11 = r20.size()
            if (r11 == 0) goto L_0x02f5
            int r11 = r20.size()
            r14 = 2
            if (r11 == r14) goto L_0x02f5
            if (r17 == 0) goto L_0x02ef
            goto L_0x02f5
        L_0x02ef:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x02f5:
            com.kenai.jnr.x86asm.INST_CODE r11 = com.kenai.jnr.x86asm.INST_CODE.INST_PEXTRD
            if (r0 != r11) goto L_0x030e
            int r11 = r20.size()
            if (r11 == 0) goto L_0x030e
            int r11 = r20.size()
            if (r11 == r10) goto L_0x030e
            if (r17 == 0) goto L_0x0308
            goto L_0x030e
        L_0x0308:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x030e:
            com.kenai.jnr.x86asm.INST_CODE r10 = com.kenai.jnr.x86asm.INST_CODE.INST_PEXTRQ
            if (r0 != r10) goto L_0x0327
            int r0 = r20.size()
            if (r0 == 0) goto L_0x0327
            int r0 = r20.size()
            if (r0 == r2) goto L_0x0327
            if (r17 == 0) goto L_0x0321
            goto L_0x0327
        L_0x0321:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0327:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x0331
            r0 = 1711276032(0x66000000, float:1.5111573E23)
            r0 = r0 | r1
            r1 = r0
        L_0x0331:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0359
            int r0 = r6.opCodeR
            boolean r2 = r5.isRegType(r3)
            int r2 = intValue(r2)
            r2 = r2 | r0
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r5
            com.kenai.jnr.x86asm.BaseReg r4 = (com.kenai.jnr.x86asm.BaseReg) r4
            r5 = 1
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            r0 = r9
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            return
        L_0x0359:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            int r2 = r6.opCodeR
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r5
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 1
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            r0 = r9
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            return
        L_0x0378:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x037e:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            r4 = r5
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            long r0 = r0.value()
            int r3 = (int) r0
            r5 = 0
            r1 = 3864(0xf18, float:5.415E-42)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x039f:
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x03bf
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x03bf
            r0 = r5
            com.kenai.jnr.x86asm.MMRegister r0 = (com.kenai.jnr.x86asm.MMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.MMRegister r4 = (com.kenai.jnr.x86asm.MMRegister) r4
            r5 = 0
            r1 = 3951(0xf6f, float:5.537E-42)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x03bf:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x03e0
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x03e0
            r0 = r5
            com.kenai.jnr.x86asm.XMMRegister r0 = (com.kenai.jnr.x86asm.XMMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.XMMRegister r4 = (com.kenai.jnr.x86asm.XMMRegister) r4
            r5 = 0
            r1 = -218099842(0xfffffffff3000f7e, float:-1.0146E31)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x03e0:
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x0401
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x0401
            r0 = r5
            com.kenai.jnr.x86asm.MMRegister r0 = (com.kenai.jnr.x86asm.MMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.XMMRegister r4 = (com.kenai.jnr.x86asm.XMMRegister) r4
            r5 = 0
            r1 = -234876970(0xfffffffff2000fd6, float:-2.5365264E30)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0401:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0422
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x0422
            r0 = r5
            com.kenai.jnr.x86asm.XMMRegister r0 = (com.kenai.jnr.x86asm.XMMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.MMRegister r4 = (com.kenai.jnr.x86asm.MMRegister) r4
            r5 = 0
            r1 = -218099754(0xfffffffff3000fd6, float:-1.0146106E31)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0422:
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x0442
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0442
            r0 = r5
            com.kenai.jnr.x86asm.MMRegister r0 = (com.kenai.jnr.x86asm.MMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r1 = 3951(0xf6f, float:5.537E-42)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0442:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0463
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0463
            r0 = r5
            com.kenai.jnr.x86asm.XMMRegister r0 = (com.kenai.jnr.x86asm.XMMRegister) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r1 = -218099842(0xfffffffff3000f7e, float:-1.0146E31)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0463:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x0483
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x0483
            r0 = r8
            com.kenai.jnr.x86asm.MMRegister r0 = (com.kenai.jnr.x86asm.MMRegister) r0
            int r3 = r0.code()
            r4 = r5
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r1 = 3967(0xf7f, float:5.559E-42)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0483:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x04a4
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x04a4
            r0 = r8
            com.kenai.jnr.x86asm.XMMRegister r0 = (com.kenai.jnr.x86asm.XMMRegister) r0
            int r3 = r0.code()
            r4 = r5
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r1 = 1711280086(0x66000fd6, float:1.5118876E23)
            r2 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x04a4:
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r5.isRegType(r15)
            if (r0 != 0) goto L_0x04b6
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x04c3
        L_0x04b6:
            boolean r0 = r8.isRegType(r3)
            if (r0 != 0) goto L_0x04fb
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x04c3
            goto L_0x04fb
        L_0x04c3:
            boolean r0 = r5.isRegType(r3)
            if (r0 != 0) goto L_0x04cf
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
        L_0x04cf:
            boolean r0 = r8.isRegType(r15)
            if (r0 != 0) goto L_0x04db
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x130b
        L_0x04db:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x04e6
            r0 = 1711279998(0x66000f7e, float:1.5118717E23)
        L_0x04e4:
            r1 = r0
            goto L_0x04e9
        L_0x04e6:
            r0 = 3966(0xf7e, float:5.558E-42)
            goto L_0x04e4
        L_0x04e9:
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r6 = 0
            r2 = 1
            r0 = r18
            r4 = r20
            r5 = r6
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x04fb:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0506
            r0 = 1711279982(0x66000f6e, float:1.5118688E23)
        L_0x0504:
            r1 = r0
            goto L_0x0509
        L_0x0506:
            r0 = 3950(0xf6e, float:5.535E-42)
            goto L_0x0504
        L_0x0509:
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r5 = 0
            r2 = 1
            r0 = r18
            r4 = r21
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x051a:
            boolean r0 = r5.isRegType(r15)
            if (r0 != 0) goto L_0x0526
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0533
        L_0x0526:
            boolean r0 = r8.isRegType(r14)
            if (r0 != 0) goto L_0x056b
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0533
            goto L_0x056b
        L_0x0533:
            boolean r0 = r5.isRegType(r14)
            if (r0 != 0) goto L_0x053f
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
        L_0x053f:
            boolean r0 = r8.isRegType(r15)
            if (r0 != 0) goto L_0x054b
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x130b
        L_0x054b:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x0556
            r0 = 1711279998(0x66000f7e, float:1.5118717E23)
        L_0x0554:
            r1 = r0
            goto L_0x0559
        L_0x0556:
            r0 = 3966(0xf7e, float:5.558E-42)
            goto L_0x0554
        L_0x0559:
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r6 = 0
            r2 = 0
            r0 = r18
            r4 = r20
            r5 = r6
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x056b:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x0576
            r0 = 1711279982(0x66000f6e, float:1.5118688E23)
        L_0x0574:
            r1 = r0
            goto L_0x0579
        L_0x0576:
            r0 = 3950(0xf6e, float:5.535E-42)
            goto L_0x0574
        L_0x0579:
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r5 = 0
            r2 = 0
            r0 = r18
            r4 = r21
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x058a:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x0596
            int r0 = r6.o1Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x05fb
        L_0x0596:
            boolean r0 = r5.isRegType(r15)
            if (r0 == 0) goto L_0x05a1
            int r0 = r6.o1Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x05fb
        L_0x05a1:
            boolean r0 = r5.isRegType(r4)
            if (r0 == 0) goto L_0x05ac
            int r0 = r6.o1Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x05fb
        L_0x05ac:
            boolean r0 = r5.isRegType(r14)
            if (r0 == 0) goto L_0x05b7
            int r0 = r6.o1Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x05fb
        L_0x05b7:
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x05c2
            int r0 = r6.o1Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x05fb
        L_0x05c2:
            boolean r0 = r8.isRegType(r15)
            if (r0 == 0) goto L_0x05cd
            int r0 = r6.o2Flags
            r0 = r0 & r11
            if (r0 == 0) goto L_0x05fb
        L_0x05cd:
            boolean r0 = r8.isRegType(r4)
            if (r0 == 0) goto L_0x05d8
            int r0 = r6.o2Flags
            r0 = r0 & r14
            if (r0 == 0) goto L_0x05fb
        L_0x05d8:
            boolean r0 = r8.isRegType(r14)
            if (r0 == 0) goto L_0x05e3
            int r0 = r6.o2Flags
            r0 = r0 & r10
            if (r0 == 0) goto L_0x05fb
        L_0x05e3:
            boolean r0 = r8.isRegType(r3)
            if (r0 == 0) goto L_0x05ee
            int r0 = r6.o2Flags
            r0 = r0 & r2
            if (r0 == 0) goto L_0x05fb
        L_0x05ee:
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0601
            int r0 = r6.o2Flags
            r0 = r0 & 64
            if (r0 == 0) goto L_0x05fb
            goto L_0x0601
        L_0x05fb:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r13)
            throw r0
        L_0x0601:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x0614
            boolean r0 = r21.isMem()
            if (r0 != 0) goto L_0x060e
            goto L_0x0614
        L_0x060e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r13)
            throw r0
        L_0x0614:
            int r0 = r6.o1Flags
            int r1 = r6.o2Flags
            r0 = r0 | r1
            r0 = r0 & r12
            if (r0 == 0) goto L_0x061e
            r2 = 0
            goto L_0x0631
        L_0x061e:
            boolean r0 = r5.isRegType(r3)
            if (r0 != 0) goto L_0x062c
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x062b
            goto L_0x062c
        L_0x062b:
            r12 = 0
        L_0x062c:
            int r0 = intValue(r12)
            r2 = r0
        L_0x0631:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0650
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x0650
            int r1 = r6.opCode1
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.BaseReg r4 = (com.kenai.jnr.x86asm.BaseReg) r4
            r5 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x0650:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x066f
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x066f
            int r1 = r6.opCode1
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r8
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x066f:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
            int r1 = r6.opCode2
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r3 = r0.code()
            r4 = r5
            com.kenai.jnr.x86asm.Mem r4 = (com.kenai.jnr.x86asm.Mem) r4
            r5 = 0
            r0 = r18
            r0._emitMmu(r1, r2, r3, r4, r5)
            return
        L_0x068e:
            r0 = 80
            boolean r0 = r5.isRegType(r0)
            if (r0 == 0) goto L_0x06b2
            int r0 = r6.opCode2
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r1
            int r0 = r0 >> 24
            r7._emitByte(r0)
            int r0 = r6.opCode2
            r1 = 16711680(0xff0000, float:2.3418052E-38)
            r0 = r0 & r1
            int r0 = r0 >> r11
            r1 = r5
            com.kenai.jnr.x86asm.X87Register r1 = (com.kenai.jnr.x86asm.X87Register) r1
            int r1 = r1.index()
            int r1 = r1 + r0
            r7._emitByte(r1)
            return
        L_0x06b2:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x0709
            r0 = r5
            com.kenai.jnr.x86asm.Mem r0 = (com.kenai.jnr.x86asm.Mem) r0
            int r1 = r20.size()
            r3 = 2
            if (r1 != r3) goto L_0x06d1
            int r1 = r6.o1Flags
            r1 = r1 & r3
            if (r1 == 0) goto L_0x06d1
            int r1 = r6.opCode1
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1 = r1 & r3
            int r1 = r1 >> 24
            int r3 = r6.opCodeR
            goto L_0x06d3
        L_0x06d1:
            r1 = 0
            r3 = 0
        L_0x06d3:
            int r4 = r20.size()
            if (r4 != r10) goto L_0x06e6
            int r4 = r6.o1Flags
            r4 = r4 & r10
            if (r4 == 0) goto L_0x06e6
            int r1 = r6.opCode1
            r3 = 16711680(0xff0000, float:2.3418052E-38)
            r1 = r1 & r3
            int r1 = r1 >> r11
            int r3 = r6.opCodeR
        L_0x06e6:
            int r4 = r20.size()
            if (r4 != r2) goto L_0x06fc
            int r4 = r6.o1Flags
            r4 = r4 & r2
            if (r4 == 0) goto L_0x06fc
            int r1 = r6.opCode1
            r3 = 65280(0xff00, float:9.1477E-41)
            r3 = r3 & r1
            int r2 = r3 >> 8
            r3 = r1 & 255(0xff, float:3.57E-43)
            r1 = r2
        L_0x06fc:
            if (r1 == 0) goto L_0x130b
            r7._emitSegmentPrefix(r0)
            r7._emitByte(r1)
            r1 = 0
            r7._emitModM(r3, r0, r1)
            return
        L_0x0709:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "not x87 mem"
            r0.<init>(r1)
            throw r0
        L_0x0711:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x072c
            r0 = r5
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r1 = r0.type()
            if (r1 > r3) goto L_0x072c
            int r0 = r0.index()
            if (r0 != 0) goto L_0x072c
            int r0 = r6.opCode2
            r7._emitOpCode(r0)
            return
        L_0x072c:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            int r1 = r6.opCode1
            int r4 = r6.opCodeR
            com.kenai.jnr.x86asm.Mem r5 = (com.kenai.jnr.x86asm.Mem) r5
            r6 = 0
            r2 = 0
            r3 = 0
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (int) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0741:
            r0 = 80
            boolean r0 = r5.isRegType(r0)
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.X87Register r0 = (com.kenai.jnr.x86asm.X87Register) r0
            int r0 = r0.index()
            int r1 = r6.opCode1
            r3 = 65280(0xff00, float:9.1477E-41)
            r1 = r1 & r3
            int r1 = r1 >> r2
            r7._emitByte(r1)
            int r1 = r6.opCode1
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 + r0
            r7._emitByte(r1)
            return
        L_0x0763:
            r1 = 80
            boolean r3 = r5.isRegType(r1)
            if (r3 == 0) goto L_0x07b4
            r3 = r5
            com.kenai.jnr.x86asm.X87Register r3 = (com.kenai.jnr.x86asm.X87Register) r3
            int r3 = r3.index()
            com.kenai.jnr.x86asm.INST_CODE r4 = com.kenai.jnr.x86asm.INST_CODE.INST_FCOM
            if (r0 == r4) goto L_0x0790
            com.kenai.jnr.x86asm.INST_CODE r4 = com.kenai.jnr.x86asm.INST_CODE.INST_FCOMP
            if (r0 == r4) goto L_0x0790
            boolean r0 = r8.isRegType(r1)
            if (r0 == 0) goto L_0x0788
            r0 = r8
            com.kenai.jnr.x86asm.X87Register r0 = (com.kenai.jnr.x86asm.X87Register) r0
            int r12 = r0.index()
            goto L_0x0791
        L_0x0788:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "not x87 reg"
            r0.<init>(r1)
            throw r0
        L_0x0790:
            r12 = 0
        L_0x0791:
            int r0 = r6.opCode1
            if (r3 != 0) goto L_0x079b
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r1
            int r0 = r0 >> 24
            goto L_0x079f
        L_0x079b:
            r1 = 16711680(0xff0000, float:2.3418052E-38)
            r0 = r0 & r1
            int r0 = r0 >> r11
        L_0x079f:
            r7._emitByte(r0)
            int r0 = r6.opCode1
            if (r3 != 0) goto L_0x07ad
            r1 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r1
            int r0 = r0 >> r2
            int r0 = r0 + r12
            goto L_0x07b0
        L_0x07ad:
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 + r3
        L_0x07b0:
            r7._emitByte(r0)
            return
        L_0x07b4:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            int r0 = r20.size()
            if (r0 == r10) goto L_0x07c6
            int r0 = r20.size()
            if (r0 != r2) goto L_0x130b
        L_0x07c6:
            boolean r0 = r21.isNone()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Mem r0 = (com.kenai.jnr.x86asm.Mem) r0
            r7._emitSegmentPrefix(r0)
            int r1 = r20.size()
            if (r1 != r10) goto L_0x07e0
            int r1 = r6.opCode1
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1 = r1 & r2
            int r1 = r1 >> 24
            goto L_0x07e6
        L_0x07e0:
            int r1 = r6.opCode1
            r2 = 16711680(0xff0000, float:2.3418052E-38)
            r1 = r1 & r2
            int r1 = r1 >> r11
        L_0x07e6:
            r7._emitByte(r1)
            int r1 = r6.opCodeR
            r2 = 0
            r7._emitModM(r1, r0, r2)
            return
        L_0x07f0:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0818
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x0818
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r5 = r8
            com.kenai.jnr.x86asm.Mem r5 = (com.kenai.jnr.x86asm.Mem) r5
            r6 = 0
            r1 = 997616(0xf38f0, float:1.397958E-39)
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0818:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r2 = r8.isRegType(r11)
            boolean r3 = r8.isRegType(r3)
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            com.kenai.jnr.x86asm.Mem r5 = (com.kenai.jnr.x86asm.Mem) r5
            r6 = 0
            r1 = 997617(0xf38f1, float:1.397959E-39)
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x083f:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            boolean r1 = r0.isRegType(r11)
            if (r1 == 0) goto L_0x0859
            r1 = 102(0x66, float:1.43E-43)
            r7._emitByte(r1)
        L_0x0859:
            r7._emitSegmentPrefix(r5)
            boolean r1 = r18.is64()
            if (r1 == 0) goto L_0x086d
            boolean r1 = r0.isRegType(r3)
            int r2 = r0.code()
            r7._emitRexRM((boolean) r1, (int) r2, (com.kenai.jnr.x86asm.Operand) r5)
        L_0x086d:
            int r1 = r20.op()
            if (r1 != r12) goto L_0x0889
            int r1 = r20.size()
            if (r1 <= r12) goto L_0x0889
            r1 = r5
            com.kenai.jnr.x86asm.Register r1 = (com.kenai.jnr.x86asm.Register) r1
            int r2 = r1.code()
            if (r2 == 0) goto L_0x088b
            int r2 = r0.code()
            if (r2 != 0) goto L_0x0889
            goto L_0x088b
        L_0x0889:
            r1 = 0
            goto L_0x089b
        L_0x088b:
            int r1 = r1.code()
            int r0 = r0.code()
            r0 = r0 | r1
            int r0 = r0 + 144
            byte r0 = (byte) r0
            r7._emitByte(r0)
            return
        L_0x089b:
            boolean r2 = r0.isRegType(r1)
            r2 = r2 ^ r12
            int r2 = intValue(r2)
            int r2 = r2 + 134
            r7._emitByte(r2)
            int r0 = r0.code()
            r7._emitModRM(r0, r5, r1)
            return
        L_0x08b1:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x08f0
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x08f0
            int r0 = r21.size()
            if (r0 == r12) goto L_0x08c5
            r0 = r12
            goto L_0x08c6
        L_0x08c5:
            r0 = 0
        L_0x08c6:
            int r0 = intValue(r0)
            int r1 = r0 + 132
            int r0 = r21.size()
            r3 = 2
            if (r0 != r3) goto L_0x08d5
            r3 = r12
            goto L_0x08d6
        L_0x08d5:
            r3 = 0
        L_0x08d6:
            int r0 = r21.size()
            if (r0 != r2) goto L_0x08dd
            goto L_0x08de
        L_0x08dd:
            r12 = 0
        L_0x08de:
            r0 = r8
            com.kenai.jnr.x86asm.BaseReg r0 = (com.kenai.jnr.x86asm.BaseReg) r0
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x08f0:
            r0 = 0
            boolean r1 = r5.isRegIndex(r0)
            if (r1 == 0) goto L_0x093f
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x093f
            int r0 = r20.size()
            if (r0 > r10) goto L_0x0907
            int r10 = r20.size()
        L_0x0907:
            int r0 = r20.size()
            r1 = 2
            if (r0 != r1) goto L_0x0913
            r0 = 102(0x66, float:1.43E-43)
            r7._emitByte(r0)
        L_0x0913:
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x0927
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0922
            r0 = r12
        L_0x0920:
            r1 = 0
            goto L_0x0924
        L_0x0922:
            r0 = 0
            goto L_0x0920
        L_0x0924:
            r7._emitRexRM((boolean) r0, (int) r1, (com.kenai.jnr.x86asm.Operand) r5)
        L_0x0927:
            int r0 = r20.size()
            if (r0 == r12) goto L_0x092e
            goto L_0x092f
        L_0x092e:
            r12 = 0
        L_0x092f:
            int r0 = intValue(r12)
            int r0 = r0 + 168
            r7._emitByte(r0)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r10)
            return
        L_0x093f:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            int r0 = r20.size()
            if (r0 > r10) goto L_0x0955
            int r10 = r20.size()
        L_0x0955:
            int r0 = r20.size()
            r1 = 2
            if (r0 != r1) goto L_0x0961
            r0 = 102(0x66, float:1.43E-43)
            r7._emitByte(r0)
        L_0x0961:
            r7._emitSegmentPrefix(r5)
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x0979
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0973
            r1 = r12
            r0 = 0
            goto L_0x0975
        L_0x0973:
            r0 = 0
            r1 = 0
        L_0x0975:
            r7._emitRexRM((boolean) r1, (int) r0, (com.kenai.jnr.x86asm.Operand) r5)
            goto L_0x097a
        L_0x0979:
            r0 = 0
        L_0x097a:
            int r1 = r20.size()
            if (r1 == r12) goto L_0x0983
            r17 = r12
            goto L_0x0985
        L_0x0983:
            r17 = r0
        L_0x0985:
            int r1 = intValue(r17)
            int r1 = r1 + 246
            r7._emitByte(r1)
            r7._emitModRM(r0, r5, r10)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r10)
            return
        L_0x0998:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r22.isImm()
            if (r0 != 0) goto L_0x09b6
            boolean r0 = r22.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r9.isRegCode(r12)
            if (r0 == 0) goto L_0x130b
        L_0x09b6:
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r1 = r6.opCode1
            boolean r2 = r22.isReg()
            int r2 = intValue(r2)
            int r1 = r1 + r2
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            boolean r0 = r22.isImm()
            int r6 = intValue(r0)
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            boolean r0 = r22.isImm()
            if (r0 == 0) goto L_0x09eb
            r0 = r9
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
        L_0x09eb:
            return
        L_0x09ec:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r8.isRegCode(r12)
            if (r0 != 0) goto L_0x09fe
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
        L_0x09fe:
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x0a1b
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            long r3 = r0.value()
            r9 = 1
            int r1 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x0a19
            com.kenai.jnr.x86asm.RELOC_MODE r0 = r0.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r1 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r0 == r1) goto L_0x0a1b
        L_0x0a19:
            r9 = r12
            goto L_0x0a1c
        L_0x0a1b:
            r9 = 0
        L_0x0a1c:
            if (r9 == 0) goto L_0x0a21
            r0 = 192(0xc0, float:2.69E-43)
            goto L_0x0a23
        L_0x0a21:
            r0 = 208(0xd0, float:2.91E-43)
        L_0x0a23:
            int r1 = r20.size()
            if (r1 == r12) goto L_0x0a2b
            r0 = r0 | 1
        L_0x0a2b:
            int r1 = r21.op()
            if (r1 != r12) goto L_0x0a33
            r0 = r0 | 2
        L_0x0a33:
            r1 = r0
            int r0 = r20.size()
            r3 = 2
            if (r0 != r3) goto L_0x0a3d
            r3 = r12
            goto L_0x0a3e
        L_0x0a3d:
            r3 = 0
        L_0x0a3e:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0a47
            r17 = r12
            goto L_0x0a49
        L_0x0a47:
            r17 = 0
        L_0x0a49:
            int r4 = r6.opCodeR
            int r6 = intValue(r9)
            r0 = r18
            r2 = r3
            r3 = r17
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            if (r9 == 0) goto L_0x0a61
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
        L_0x0a61:
            return
        L_0x0a62:
            boolean r0 = r20.isNone()
            if (r0 == 0) goto L_0x0a6e
            r0 = 195(0xc3, float:2.73E-43)
            r7._emitByte(r0)
            return
        L_0x0a6e:
            boolean r0 = r20.isImm()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            long r1 = r0.value()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0a8f
            com.kenai.jnr.x86asm.RELOC_MODE r1 = r0.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r2 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r1 != r2) goto L_0x0a8f
            r0 = 195(0xc3, float:2.73E-43)
            r7._emitByte(r0)
            goto L_0x0a98
        L_0x0a8f:
            r1 = 194(0xc2, float:2.72E-43)
            r7._emitByte(r1)
            r1 = 2
            r7._emitImmediate(r0, r1)
        L_0x0a98:
            return
        L_0x0a99:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r1 = r6.opCode1
            int r2 = r0.type()
            if (r2 == 0) goto L_0x0ab2
            r2 = r12
            goto L_0x0ab3
        L_0x0ab2:
            r2 = 0
        L_0x0ab3:
            int r2 = intValue(r2)
            int r1 = r1 + r2
            int r2 = r0.type()
            if (r2 != r11) goto L_0x0ac0
            r2 = r12
            goto L_0x0ac1
        L_0x0ac0:
            r2 = 0
        L_0x0ac1:
            int r4 = r0.type()
            if (r4 != r3) goto L_0x0ac9
            r3 = r12
            goto L_0x0aca
        L_0x0ac9:
            r3 = 0
        L_0x0aca:
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0ad7:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            int r0 = r6.opCode1
            int r1 = r20.size()
            if (r1 == r12) goto L_0x0ae7
            r1 = r12
            goto L_0x0ae8
        L_0x0ae7:
            r1 = 0
        L_0x0ae8:
            int r1 = intValue(r1)
            int r1 = r1 + r0
            int r0 = r20.size()
            r3 = 2
            if (r0 != r3) goto L_0x0af6
            r3 = r12
            goto L_0x0af7
        L_0x0af6:
            r3 = 0
        L_0x0af7:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0afe
            goto L_0x0aff
        L_0x0afe:
            r12 = 0
        L_0x0aff:
            int r4 = r6.opCodeR
            r6 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0b0c:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            int r1 = r6.opCode1
            r4 = 0
            r6 = 0
            r2 = 0
            r3 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0b20:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r1 = r6.opCode1
            int r2 = r0.type()
            if (r2 != r11) goto L_0x0b39
            r2 = r12
            goto L_0x0b3a
        L_0x0b39:
            r2 = 0
        L_0x0b3a:
            int r4 = r0.type()
            if (r4 != r3) goto L_0x0b42
            r3 = r12
            goto L_0x0b43
        L_0x0b42:
            r3 = 0
        L_0x0b43:
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0b50:
            boolean r0 = r20.isImm()
            if (r0 == 0) goto L_0x0b7d
            r0 = r5
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            long r1 = r0.value()
            boolean r1 = com.kenai.jnr.x86asm.Util.isInt8(r1)
            if (r1 == 0) goto L_0x0b74
            com.kenai.jnr.x86asm.RELOC_MODE r1 = r0.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r2 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r1 != r2) goto L_0x0b74
            r1 = 106(0x6a, float:1.49E-43)
            r7._emitByte(r1)
            r7._emitImmediate(r0, r12)
            goto L_0x0b7c
        L_0x0b74:
            r1 = 104(0x68, float:1.46E-43)
            r7._emitByte(r1)
            r7._emitImmediate(r0, r10)
        L_0x0b7c:
            return
        L_0x0b7d:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0b95
            int r0 = r6.opCode1
            boolean r1 = r5.isRegType(r11)
            r2 = r5
            com.kenai.jnr.x86asm.Register r2 = (com.kenai.jnr.x86asm.Register) r2
            int r2 = r2.code()
            r3 = 0
            r7._emitX86Inl((int) r0, (boolean) r1, (int) r3, (int) r2)
            return
        L_0x0b95:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            int r1 = r6.opCode2
            int r0 = r20.size()
            r2 = 2
            if (r0 != r2) goto L_0x0ba6
            r2 = r12
            goto L_0x0ba7
        L_0x0ba6:
            r2 = 0
        L_0x0ba7:
            int r4 = r6.opCodeR
            r6 = 0
            r3 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (int) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0bb3:
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x0bd9
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r6 = 0
            r1 = 99
            r2 = 0
            r3 = 1
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (int) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0bd9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L_0x0bdf:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            r1 = 0
            boolean r2 = r0.isRegType(r1)
            if (r2 != 0) goto L_0x0c45
            int r1 = r21.size()
            if (r1 == r12) goto L_0x0c0b
            int r1 = r21.size()
            r2 = 2
            if (r1 != r2) goto L_0x0c03
            goto L_0x0c0c
        L_0x0c03:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "src.size !=1 && src.size != 2"
            r0.<init>(r1)
            throw r0
        L_0x0c0b:
            r2 = 2
        L_0x0c0c:
            int r1 = r21.size()
            if (r1 != r2) goto L_0x0c21
            boolean r1 = r0.isRegType(r11)
            if (r1 != 0) goto L_0x0c19
            goto L_0x0c21
        L_0x0c19:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "not gpw"
            r0.<init>(r1)
            throw r0
        L_0x0c21:
            int r1 = r6.opCode1
            int r2 = r21.size()
            if (r2 == r12) goto L_0x0c2a
            goto L_0x0c2b
        L_0x0c2a:
            r12 = 0
        L_0x0c2b:
            int r2 = intValue(r12)
            int r1 = r1 + r2
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0c45:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "not gpb"
            r0.<init>(r1)
            throw r0
        L_0x0c4d:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0c59
            boolean r0 = r21.isImm()
            if (r0 != 0) goto L_0x0c65
        L_0x0c59:
            boolean r0 = r20.isImm()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x130b
        L_0x0c65:
            int r0 = r20.op()
            if (r0 != r12) goto L_0x0c6d
            r0 = r12
            goto L_0x0c6e
        L_0x0c6d:
            r0 = 0
        L_0x0c6e:
            if (r0 != 0) goto L_0x0c73
            r1 = 160(0xa0, float:2.24E-43)
            goto L_0x0c75
        L_0x0c73:
            r1 = 162(0xa2, float:2.27E-43)
        L_0x0c75:
            if (r0 != 0) goto L_0x0c79
            r3 = r5
            goto L_0x0c7a
        L_0x0c79:
            r3 = r8
        L_0x0c7a:
            com.kenai.jnr.x86asm.Register r3 = (com.kenai.jnr.x86asm.Register) r3
            if (r0 != 0) goto L_0x0c7f
            r5 = r8
        L_0x0c7f:
            com.kenai.jnr.x86asm.Immediate r5 = (com.kenai.jnr.x86asm.Immediate) r5
            int r0 = r3.index()
            if (r0 != 0) goto L_0x0cc2
            boolean r0 = r3.isRegType(r11)
            if (r0 == 0) goto L_0x0c92
            r0 = 102(0x66, float:1.43E-43)
            r7._emitByte(r0)
        L_0x0c92:
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x0ca6
            int r0 = r3.size()
            if (r0 != r2) goto L_0x0ca1
            r0 = r12
        L_0x0c9f:
            r4 = 0
            goto L_0x0ca3
        L_0x0ca1:
            r0 = 0
            goto L_0x0c9f
        L_0x0ca3:
            r7._emitRexR((boolean) r0, (int) r4, (int) r4)
        L_0x0ca6:
            int r0 = r3.size()
            if (r0 == r12) goto L_0x0cad
            goto L_0x0cae
        L_0x0cad:
            r12 = 0
        L_0x0cae:
            int r0 = intValue(r12)
            int r1 = r1 + r0
            r7._emitByte(r1)
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x0cbd
            goto L_0x0cbe
        L_0x0cbd:
            r2 = r10
        L_0x0cbe:
            r7._emitImmediate(r5, r2)
            return
        L_0x0cc2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "reg.index() != 0"
            r0.<init>(r1)
            throw r0
        L_0x0cca:
            int r0 = r20.op()
            int r0 = r0 << r10
            int r1 = r21.op()
            r0 = r0 | r1
            r1 = 33
            if (r0 == r1) goto L_0x0d98
            r1 = 35
            if (r0 == r1) goto L_0x0d5b
            switch(r0) {
                case 17: goto L_0x0d37;
                case 18: goto L_0x0d37;
                case 19: goto L_0x0ce1;
                default: goto L_0x0cdf;
            }
        L_0x0cdf:
            goto L_0x130b
        L_0x0ce1:
            com.kenai.jnr.x86asm.Immediate r8 = (com.kenai.jnr.x86asm.Immediate) r8
            int r0 = r20.size()
            boolean r1 = r18.is64()
            if (r1 == 0) goto L_0x0d15
            if (r0 != r2) goto L_0x0d15
            long r1 = r8.value()
            boolean r1 = com.kenai.jnr.x86asm.Util.isInt32(r1)
            if (r1 == 0) goto L_0x0d15
            com.kenai.jnr.x86asm.RELOC_MODE r1 = r8.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r2 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r1 != r2) goto L_0x0d15
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            r4 = 0
            r6 = 0
            r1 = 199(0xc7, float:2.79E-43)
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            goto L_0x0d33
        L_0x0d15:
            int r1 = r20.size()
            if (r1 != r12) goto L_0x0d1e
            r1 = 176(0xb0, float:2.47E-43)
            goto L_0x0d20
        L_0x0d1e:
            r1 = 184(0xb8, float:2.58E-43)
        L_0x0d20:
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            r4 = r5
            com.kenai.jnr.x86asm.Register r4 = (com.kenai.jnr.x86asm.Register) r4
            int r4 = r4.code()
            r7._emitX86Inl((int) r1, (boolean) r2, (boolean) r3, (int) r4)
            r10 = r0
        L_0x0d33:
            r7._emitImmediate(r8, r10)
            return
        L_0x0d37:
            r0 = 0
            boolean r0 = r5.isRegType(r0)
            r0 = r0 ^ r12
            int r0 = intValue(r0)
            int r1 = r0 + 138
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0d5b:
            int r0 = r20.size()
            if (r0 > r10) goto L_0x0d65
            int r10 = r20.size()
        L_0x0d65:
            int r0 = r20.size()
            if (r0 == r12) goto L_0x0d6d
            r0 = r12
            goto L_0x0d6e
        L_0x0d6d:
            r0 = 0
        L_0x0d6e:
            int r0 = intValue(r0)
            int r1 = r0 + 198
            int r0 = r20.size()
            r3 = 2
            if (r0 != r3) goto L_0x0d7d
            r3 = r12
            goto L_0x0d7e
        L_0x0d7d:
            r3 = 0
        L_0x0d7e:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0d85
            goto L_0x0d86
        L_0x0d85:
            r12 = 0
        L_0x0d86:
            r4 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r20
            r6 = r10
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r10)
            return
        L_0x0d98:
            r0 = 0
            boolean r0 = r8.isRegType(r0)
            r0 = r0 ^ r12
            int r0 = intValue(r0)
            int r1 = r0 + 136
            boolean r2 = r8.isRegType(r11)
            boolean r3 = r8.isRegType(r3)
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0dbc:
            boolean r0 = r20.isMem()
            if (r0 == 0) goto L_0x130b
            int r1 = r6.opCode1
            int r0 = r6.opCode2
            byte r3 = (byte) r0
            int r4 = r6.opCodeR
            com.kenai.jnr.x86asm.Mem r5 = (com.kenai.jnr.x86asm.Mem) r5
            r6 = 0
            r2 = 0
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (int) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0dd3:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isMem()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            r5 = r8
            com.kenai.jnr.x86asm.Mem r5 = (com.kenai.jnr.x86asm.Mem) r5
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            r6 = 0
            r1 = 141(0x8d, float:1.98E-43)
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0dfa:
            boolean r1 = r20.isRegMem()
            if (r1 == 0) goto L_0x0e0e
            r4 = 4
            r6 = 0
            r1 = 255(0xff, float:3.57E-43)
            r2 = 0
            r3 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0e0e:
            boolean r1 = r20.isImm()
            r2 = 233(0xe9, float:3.27E-43)
            if (r1 == 0) goto L_0x0e26
            r0 = r5
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitByte(r2)
            com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_JMP
            long r2 = r0.value()
            r7._emitJmpOrCallReloc(r1, r2)
            return
        L_0x0e26:
            boolean r1 = r20.isLabel()
            if (r1 == 0) goto L_0x130b
            r1 = r5
            com.kenai.jnr.x86asm.Label r1 = (com.kenai.jnr.x86asm.Label) r1
            com.kenai.jnr.x86asm.INST_CODE r3 = com.kenai.jnr.x86asm.INST_CODE.INST_JMP_SHORT
            if (r0 != r3) goto L_0x0e36
            r17 = r12
            goto L_0x0e38
        L_0x0e36:
            r17 = 0
        L_0x0e38:
            boolean r0 = r1.isBound()
            if (r0 == 0) goto L_0x0e6e
            int r0 = r1.position()
            int r1 = r18.offset()
            int r0 = r0 - r1
            int r1 = r0 + -2
            long r3 = (long) r1
            boolean r3 = com.kenai.jnr.x86asm.Util.isInt8(r3)
            if (r3 == 0) goto L_0x0e5a
            r0 = 235(0xeb, float:3.3E-43)
            r7._emitByte(r0)
            byte r0 = (byte) r1
            r7._emitByte(r0)
            goto L_0x0e83
        L_0x0e5a:
            if (r17 == 0) goto L_0x0e65
            com.kenai.jnr.x86asm.Logger r1 = r7._logger
            if (r1 == 0) goto L_0x0e65
            java.lang.String r3 = "; WARNING: Emitting long jump, but short jump instruction forced!"
            r1.log(r3)
        L_0x0e65:
            r7._emitByte(r2)
            int r0 = r0 + -5
            r7._emitInt32(r0)
            goto L_0x0e83
        L_0x0e6e:
            if (r17 == 0) goto L_0x0e7b
            r0 = 235(0xeb, float:3.3E-43)
            r7._emitByte(r0)
            r2 = -1
            r7._emitDisplacement(r1, r2, r12)
            goto L_0x0e83
        L_0x0e7b:
            r7._emitByte(r2)
            r2 = -4
            r7._emitDisplacement(r1, r2, r10)
        L_0x0e83:
            return
        L_0x0e84:
            boolean r1 = r20.isLabel()
            if (r1 == 0) goto L_0x130b
            r1 = r5
            com.kenai.jnr.x86asm.Label r1 = (com.kenai.jnr.x86asm.Label) r1
            int r2 = r19.ordinal()
            com.kenai.jnr.x86asm.INST_CODE r3 = com.kenai.jnr.x86asm.INST_CODE.INST_J_SHORT
            int r3 = r3.ordinal()
            if (r2 < r3) goto L_0x0ea8
            int r0 = r19.ordinal()
            com.kenai.jnr.x86asm.INST_CODE r2 = com.kenai.jnr.x86asm.INST_CODE.INST_JMP_SHORT
            int r2 = r2.ordinal()
            if (r0 > r2) goto L_0x0ea8
            r17 = r12
            goto L_0x0eaa
        L_0x0ea8:
            r17 = 0
        L_0x0eaa:
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x0ebd
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            long r2 = r0.value()
            int r0 = (int) r2
            com.kenai.jnr.x86asm.HINT r0 = com.kenai.jnr.x86asm.HINT.valueOf((int) r0)
            goto L_0x0ebf
        L_0x0ebd:
            com.kenai.jnr.x86asm.HINT r0 = com.kenai.jnr.x86asm.HINT.HINT_NONE
        L_0x0ebf:
            com.kenai.jnr.x86asm.HINT r2 = com.kenai.jnr.x86asm.HINT.HINT_TAKEN
            if (r0 == r2) goto L_0x0ecc
            com.kenai.jnr.x86asm.HINT r2 = com.kenai.jnr.x86asm.HINT.HINT_NOT_TAKEN
            if (r0 != r2) goto L_0x0ed3
            int r2 = r7._properties
            r2 = r2 & r10
            if (r2 == 0) goto L_0x0ed3
        L_0x0ecc:
            int r0 = r0.value()
            r7._emitByte(r0)
        L_0x0ed3:
            boolean r0 = r1.isBound()
            if (r0 == 0) goto L_0x0f17
            int r0 = r1.position()
            int r1 = r18.offset()
            int r0 = r0 - r1
            int r1 = r0 + -2
            long r2 = (long) r1
            boolean r2 = com.kenai.jnr.x86asm.Util.isInt8(r2)
            if (r2 == 0) goto L_0x0ef8
            int r0 = r6.opCode1
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | r4
            r7._emitByte(r0)
            byte r0 = (byte) r1
            r7._emitByte(r0)
            goto L_0x0f3a
        L_0x0ef8:
            if (r17 == 0) goto L_0x0f03
            com.kenai.jnr.x86asm.Logger r1 = r7._logger
            if (r1 == 0) goto L_0x0f03
            java.lang.String r2 = "; WARNING: Emitting long conditional jump, but short jump instruction forced!"
            r1.log(r2)
        L_0x0f03:
            r1 = 15
            r7._emitByte(r1)
            int r1 = r6.opCode1
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | 128(0x80, float:1.794E-43)
            r7._emitByte(r1)
            int r0 = r0 + -6
            r7._emitInt32(r0)
            goto L_0x0f3a
        L_0x0f17:
            if (r17 == 0) goto L_0x0f27
            int r0 = r6.opCode1
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | r4
            r7._emitByte(r0)
            r2 = -1
            r7._emitDisplacement(r1, r2, r12)
            goto L_0x0f3a
        L_0x0f27:
            r0 = 15
            r7._emitByte(r0)
            int r0 = r6.opCode1
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | 128(0x80, float:1.794E-43)
            r7._emitByte(r0)
            r2 = -4
            r7._emitDisplacement(r1, r2, r10)
        L_0x0f3a:
            return
        L_0x0f3b:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r18.is64()
            if (r0 != 0) goto L_0x0f6b
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x0f6b
            boolean r0 = r5.isRegType(r11)
            if (r0 != 0) goto L_0x0f59
            boolean r0 = r5.isRegType(r14)
            if (r0 == 0) goto L_0x0f6b
        L_0x0f59:
            int r0 = r6.opCode1
            boolean r1 = r5.isRegType(r11)
            r2 = r5
            com.kenai.jnr.x86asm.BaseReg r2 = (com.kenai.jnr.x86asm.BaseReg) r2
            int r2 = r2.code()
            r3 = 0
            r7._emitX86Inl((int) r0, (boolean) r1, (int) r3, (int) r2)
            return
        L_0x0f6b:
            int r0 = r6.opCode2
            int r1 = r20.size()
            if (r1 == r12) goto L_0x0f75
            r1 = r12
            goto L_0x0f76
        L_0x0f75:
            r1 = 0
        L_0x0f76:
            int r1 = intValue(r1)
            int r1 = r1 + r0
            int r0 = r20.size()
            r3 = 2
            if (r0 != r3) goto L_0x0f84
            r3 = r12
            goto L_0x0f85
        L_0x0f84:
            r3 = 0
        L_0x0f85:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0f8c
            goto L_0x0f8d
        L_0x0f8c:
            r12 = 0
        L_0x0f8d:
            int r4 = r6.opCodeR
            r6 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0f9a:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x0fd9
            boolean r0 = r21.isNone()
            if (r0 == 0) goto L_0x0fd9
            boolean r0 = r22.isNone()
            if (r0 == 0) goto L_0x0fd9
            int r0 = r20.size()
            if (r0 == r12) goto L_0x0fb4
            r0 = r12
            goto L_0x0fb5
        L_0x0fb4:
            r0 = 0
        L_0x0fb5:
            int r0 = intValue(r0)
            int r1 = r0 + 246
            int r0 = r20.size()
            r3 = 2
            if (r0 != r3) goto L_0x0fc4
            r3 = r12
            goto L_0x0fc5
        L_0x0fc4:
            r3 = 0
        L_0x0fc5:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x0fcc
            goto L_0x0fcd
        L_0x0fcc:
            r12 = 0
        L_0x0fcd:
            r4 = 5
            r6 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x0fd9:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x105d
            boolean r0 = r21.isNone()
            if (r0 != 0) goto L_0x105d
            boolean r0 = r22.isNone()
            if (r0 == 0) goto L_0x105d
            com.kenai.jnr.x86asm.Register r5 = (com.kenai.jnr.x86asm.Register) r5
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x100a
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            int r4 = r5.code()
            r6 = 0
            r1 = 4015(0xfaf, float:5.626E-42)
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x100a:
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            com.kenai.jnr.x86asm.Immediate r8 = (com.kenai.jnr.x86asm.Immediate) r8
            long r0 = r8.value()
            boolean r0 = com.kenai.jnr.x86asm.Util.isInt8(r0)
            if (r0 == 0) goto L_0x103c
            com.kenai.jnr.x86asm.RELOC_MODE r0 = r8.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r1 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r0 != r1) goto L_0x103c
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            int r4 = r5.code()
            r6 = 1
            r1 = 107(0x6b, float:1.5E-43)
            r0 = r18
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r8, r12)
            goto L_0x105c
        L_0x103c:
            boolean r0 = r5.isRegType(r11)
            if (r0 == 0) goto L_0x1044
            r12 = 2
            goto L_0x1045
        L_0x1044:
            r12 = r10
        L_0x1045:
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            int r4 = r5.code()
            r1 = 105(0x69, float:1.47E-43)
            r0 = r18
            r6 = r12
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r8, r12)
        L_0x105c:
            return
        L_0x105d:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r22.isImm()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            com.kenai.jnr.x86asm.Immediate r9 = (com.kenai.jnr.x86asm.Immediate) r9
            long r1 = r9.value()
            boolean r1 = com.kenai.jnr.x86asm.Util.isInt8(r1)
            if (r1 == 0) goto L_0x10a0
            com.kenai.jnr.x86asm.RELOC_MODE r1 = r9.relocMode()
            com.kenai.jnr.x86asm.RELOC_MODE r2 = com.kenai.jnr.x86asm.RELOC_MODE.RELOC_NONE
            if (r1 != r2) goto L_0x10a0
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            r6 = 1
            r1 = 107(0x6b, float:1.5E-43)
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r9, r12)
            goto L_0x10c2
        L_0x10a0:
            boolean r1 = r0.isRegType(r11)
            if (r1 == 0) goto L_0x10a8
            r12 = 2
            goto L_0x10a9
        L_0x10a8:
            r12 = r10
        L_0x10a9:
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            r1 = 105(0x69, float:1.47E-43)
            r0 = r18
            r5 = r21
            r6 = r12
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r9, r12)
        L_0x10c2:
            return
        L_0x10c3:
            boolean r0 = r20.isImm()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            r0 = 200(0xc8, float:2.8E-43)
            r7._emitByte(r0)
            r0 = r5
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r1 = 2
            r7._emitImmediate(r0, r1)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r7._emitImmediate(r0, r12)
            goto L_0x130b
        L_0x10e3:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isRegMem()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r1 = r6.opCode1
            int r3 = r21.size()
            if (r3 == r12) goto L_0x10fc
            r3 = r12
            goto L_0x10fd
        L_0x10fc:
            r3 = 0
        L_0x10fd:
            int r3 = intValue(r3)
            int r1 = r1 + r3
            int r3 = r21.size()
            r4 = 2
            if (r3 != r4) goto L_0x110b
            r3 = r12
            goto L_0x110c
        L_0x110b:
            r3 = 0
        L_0x110c:
            int r4 = r0.type()
            if (r4 != r2) goto L_0x1113
            goto L_0x1114
        L_0x1113:
            r12 = 0
        L_0x1114:
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r2 = r3
            r3 = r12
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x1123:
            boolean r0 = r18.is64()
            if (r0 == 0) goto L_0x112a
            r14 = r3
        L_0x112a:
            boolean r0 = r5.isRegMem(r14)
            if (r0 == 0) goto L_0x113e
            r4 = 2
            r6 = 0
            r1 = 255(0xff, float:3.57E-43)
            r2 = 0
            r3 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x113e:
            boolean r0 = r20.isImm()
            if (r0 == 0) goto L_0x1156
            r0 = r5
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            r1 = 232(0xe8, float:3.25E-43)
            r7._emitByte(r1)
            com.kenai.jnr.x86asm.InstructionGroup r1 = com.kenai.jnr.x86asm.InstructionGroup.I_CALL
            long r2 = r0.value()
            r7._emitJmpOrCallReloc(r1, r2)
            return
        L_0x1156:
            boolean r0 = r20.isLabel()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Label r0 = (com.kenai.jnr.x86asm.Label) r0
            boolean r1 = r0.isBound()
            if (r1 == 0) goto L_0x1179
            int r0 = r0.position()
            int r1 = r18.offset()
            int r0 = r0 - r1
            r1 = 232(0xe8, float:3.25E-43)
            r7._emitByte(r1)
            int r0 = r0 + -5
            r7._emitInt32(r0)
            goto L_0x1183
        L_0x1179:
            r1 = 232(0xe8, float:3.25E-43)
            r7._emitByte(r1)
            r1 = -4
            r7._emitDisplacement(r0, r1, r10)
        L_0x1183:
            return
        L_0x1184:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x11aa
            boolean r0 = r21.isReg()
            if (r0 == 0) goto L_0x11aa
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r1 = r6.opCode1
            boolean r2 = r0.isRegType(r11)
            boolean r3 = r0.isRegType(r3)
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x11aa:
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            com.kenai.jnr.x86asm.Immediate r8 = (com.kenai.jnr.x86asm.Immediate) r8
            int r1 = r6.opCode2
            int r0 = r8.size()
            r3 = 2
            if (r0 != r3) goto L_0x11c3
            r3 = r12
            goto L_0x11c4
        L_0x11c3:
            r3 = 0
        L_0x11c4:
            int r0 = r8.size()
            if (r0 != r2) goto L_0x11cd
            r17 = r12
            goto L_0x11cf
        L_0x11cd:
            r17 = 0
        L_0x11cf:
            int r4 = r6.opCodeR
            r6 = 1
            r0 = r18
            r2 = r3
            r3 = r17
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r8, r12)
            return
        L_0x11e0:
            boolean r0 = r20.isReg()
            if (r0 == 0) goto L_0x130b
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            boolean r1 = r18.is64()
            if (r1 == 0) goto L_0x11ff
            int r1 = r0.type()
            if (r1 != r3) goto L_0x11f7
            r1 = r12
            goto L_0x11f8
        L_0x11f7:
            r1 = 0
        L_0x11f8:
            int r2 = r0.code()
            r7._emitRexR((boolean) r1, (int) r12, (int) r2)
        L_0x11ff:
            r1 = 15
            r7._emitByte(r1)
            int r0 = r0.code()
            r7._emitModR((int) r12, (int) r0)
            return
        L_0x120c:
            int r0 = r6.opCode1
            int r4 = r6.opCodeR
            boolean r1 = r20.isMem()
            if (r1 == 0) goto L_0x123f
            boolean r1 = r21.isReg()
            if (r1 == 0) goto L_0x123f
            r1 = 0
            boolean r1 = r8.isRegType(r1)
            r1 = r1 ^ r12
            int r1 = intValue(r1)
            int r1 = r1 + r0
            boolean r2 = r8.isRegType(r11)
            boolean r3 = r8.isRegType(r3)
            r0 = r8
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r20
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x123f:
            boolean r1 = r20.isReg()
            if (r1 == 0) goto L_0x1270
            boolean r1 = r21.isRegMem()
            if (r1 == 0) goto L_0x1270
            r1 = 2
            int r0 = r0 + r1
            r1 = 0
            boolean r1 = r5.isRegType(r1)
            r1 = r1 ^ r12
            int r1 = intValue(r1)
            int r1 = r1 + r0
            boolean r2 = r5.isRegType(r11)
            boolean r3 = r5.isRegType(r3)
            r0 = r5
            com.kenai.jnr.x86asm.Register r0 = (com.kenai.jnr.x86asm.Register) r0
            int r4 = r0.code()
            r6 = 0
            r0 = r18
            r5 = r21
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            return
        L_0x1270:
            r0 = 0
            boolean r1 = r5.isRegIndex(r0)
            if (r1 == 0) goto L_0x12b6
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x12b6
            boolean r0 = r5.isRegType(r11)
            if (r0 == 0) goto L_0x1289
            r0 = 102(0x66, float:1.43E-43)
            r7._emitByte(r0)
            goto L_0x1294
        L_0x1289:
            boolean r0 = r5.isRegType(r3)
            if (r0 == 0) goto L_0x1294
            r0 = 72
            r7._emitByte(r0)
        L_0x1294:
            int r0 = r4 << 3
            r1 = 0
            boolean r1 = r5.isRegType(r1)
            r1 = r1 ^ r12
            int r1 = intValue(r1)
            int r1 = r1 + r10
            r0 = r0 | r1
            r7._emitByte(r0)
            r0 = r8
            com.kenai.jnr.x86asm.Immediate r0 = (com.kenai.jnr.x86asm.Immediate) r0
            int r1 = r20.size()
            if (r1 > r10) goto L_0x12b2
            int r10 = r20.size()
        L_0x12b2:
            r7._emitImmediate(r0, r10)
            return
        L_0x12b6:
            r1 = 0
            boolean r0 = r20.isRegMem()
            if (r0 == 0) goto L_0x130b
            boolean r0 = r21.isImm()
            if (r0 == 0) goto L_0x130b
            com.kenai.jnr.x86asm.Immediate r8 = (com.kenai.jnr.x86asm.Immediate) r8
            long r13 = r8.value()
            boolean r0 = com.kenai.jnr.x86asm.Util.isInt8(r13)
            if (r0 == 0) goto L_0x12d1
            r10 = r12
            goto L_0x12db
        L_0x12d1:
            int r0 = r20.size()
            if (r0 > r10) goto L_0x12db
            int r10 = r20.size()
        L_0x12db:
            int r0 = r6.opCode2
            int r3 = r20.size()
            if (r3 == r12) goto L_0x12e9
            if (r10 == r12) goto L_0x12e7
            r3 = r12
            goto L_0x12ea
        L_0x12e7:
            r3 = 3
            goto L_0x12ea
        L_0x12e9:
            r3 = r1
        L_0x12ea:
            int r3 = r3 + r0
            int r0 = r20.size()
            r6 = 2
            if (r0 != r6) goto L_0x12f4
            r6 = r12
            goto L_0x12f5
        L_0x12f4:
            r6 = r1
        L_0x12f5:
            int r0 = r20.size()
            if (r0 != r2) goto L_0x12fc
            goto L_0x12fd
        L_0x12fc:
            r12 = r1
        L_0x12fd:
            r0 = r18
            r1 = r3
            r2 = r6
            r3 = r12
            r5 = r20
            r6 = r10
            r0._emitX86RM((int) r1, (boolean) r2, (boolean) r3, (int) r4, (com.kenai.jnr.x86asm.Operand) r5, (int) r6)
            r7._emitImmediate(r8, r10)
        L_0x130b:
            return
        L_0x130c:
            int r0 = r6.opCode1
            r7._emitOpCode(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jnr.x86asm.Assembler._emitX86(com.kenai.jnr.x86asm.INST_CODE, com.kenai.jnr.x86asm.Operand, com.kenai.jnr.x86asm.Operand, com.kenai.jnr.x86asm.Operand):void");
    }

    public void _emitX86Inl(int i3, boolean z2, boolean z3, int i4) {
        _emitX86Inl(i3, z2, intValue(z3), i4);
    }

    public void _emitX86RM(int i3, boolean z2, boolean z3, int i4, Operand operand, int i5) {
        _emitX86RM(i3, z2, intValue(z3), i4, operand, i5);
    }

    public void align(long j2) {
        int i3;
        int[] iArr;
        int i4;
        int[] iArr2;
        long j3 = j2;
        Logger logger = this._logger;
        if (logger != null) {
            logger.logAlign(j3);
        }
        if (j3 >= 1 && j3 <= 64) {
            int offset = (int) (j3 - (((long) offset()) % j3));
            if (((long) offset) != j3) {
                if ((this._properties & 1) != 0) {
                    CpuInfo cpuInfo2 = this.cpuInfo;
                    CpuInfo.Vendor vendor = cpuInfo2.vendor;
                    if (vendor == CpuInfo.Vendor.INTEL) {
                        int i5 = cpuInfo2.family;
                        if ((i5 & 15) == 6 || (i5 & 15) == 15) {
                            do {
                                switch (offset) {
                                    case 1:
                                        iArr2 = nop1;
                                        i4 = 1;
                                        break;
                                    case 2:
                                        iArr2 = nop2;
                                        i4 = 2;
                                        break;
                                    case 3:
                                        iArr2 = nop3;
                                        i4 = 3;
                                        break;
                                    case 4:
                                        iArr2 = nop4;
                                        i4 = 4;
                                        break;
                                    case 5:
                                        iArr2 = nop5;
                                        i4 = 5;
                                        break;
                                    case 6:
                                        iArr2 = nop6;
                                        i4 = 6;
                                        break;
                                    case 7:
                                        iArr2 = nop7;
                                        i4 = 7;
                                        break;
                                    case 8:
                                        iArr2 = nop8;
                                        i4 = 8;
                                        break;
                                    default:
                                        iArr2 = nop9;
                                        i4 = 9;
                                        break;
                                }
                                offset -= i4;
                                int i6 = 0;
                                while (i4 > 0) {
                                    _emitByte(iArr2[i6]);
                                    i6++;
                                    i4--;
                                }
                            } while (offset > 0);
                            return;
                        }
                    }
                    if (vendor == CpuInfo.Vendor.AMD && cpuInfo2.family >= 15) {
                        do {
                            switch (offset) {
                                case 1:
                                    iArr = nop1;
                                    i3 = 1;
                                    break;
                                case 2:
                                    iArr = nop2;
                                    i3 = 2;
                                    break;
                                case 3:
                                    iArr = nop3;
                                    i3 = 3;
                                    break;
                                case 4:
                                    iArr = nop4;
                                    i3 = 4;
                                    break;
                                case 5:
                                    iArr = nop5;
                                    i3 = 5;
                                    break;
                                case 6:
                                    iArr = nop6;
                                    i3 = 6;
                                    break;
                                case 7:
                                    iArr = nop7;
                                    i3 = 7;
                                    break;
                                case 8:
                                    iArr = nop8;
                                    i3 = 8;
                                    break;
                                case 9:
                                    iArr = nop9;
                                    i3 = 9;
                                    break;
                                case 10:
                                    iArr = nop10;
                                    i3 = 10;
                                    break;
                                default:
                                    iArr = nop11;
                                    i3 = 11;
                                    break;
                            }
                            offset -= i3;
                            int i7 = 0;
                            while (i3 > 0) {
                                _emitByte(iArr[i7]);
                                i7++;
                                i3--;
                            }
                        } while (offset > 0);
                        return;
                    } else if (!is64()) {
                        do {
                            if (offset != 1) {
                                if (offset != 2) {
                                    if (offset != 3) {
                                        _emitByte(102);
                                        offset--;
                                    }
                                    _emitByte(102);
                                    offset--;
                                }
                                _emitByte(102);
                                offset--;
                            }
                            _emitByte(144);
                            offset--;
                        } while (offset > 0);
                    }
                }
                while (true) {
                    int i8 = offset - 1;
                    if (offset > 0) {
                        _emitByte(144);
                        offset = i8;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final int codeSize() {
        return trampolineSize() + this._buffer.offset();
    }

    public final byte getByteAt(int i3) {
        return this._buffer.getByteAt(i3);
    }

    public final int getDWordAt(int i3) {
        return this._buffer.getDWordAt(i3);
    }

    public final int getInt32At(int i3) {
        return this._buffer.getDWordAt(i3);
    }

    public final long getQWordAt(int i3) {
        return this._buffer.getQWordAt(i3);
    }

    public final short getWordAt(int i3) {
        return this._buffer.getWordAt(i3);
    }

    public boolean is64() {
        return this.cpu == CPU.X86_64;
    }

    public final int offset() {
        return this._buffer.offset();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void relocCode(java.nio.ByteBuffer r13, long r14) {
        /*
            r12 = this;
            r12.codeSize()
            com.kenai.jnr.x86asm.CodeBuffer r0 = r12._buffer
            r0.copyTo(r13)
            java.util.List<com.kenai.jnr.x86asm.RelocData> r0 = r12._relocData
            java.util.Iterator r0 = r0.iterator()
        L_0x000e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00b6
            java.lang.Object r1 = r0.next()
            com.kenai.jnr.x86asm.RelocData r1 = (com.kenai.jnr.x86asm.RelocData) r1
            int[] r2 = com.kenai.jnr.x86asm.Assembler.AnonymousClass1.$SwitchMap$com$kenai$jnr$x86asm$RelocData$Type
            com.kenai.jnr.x86asm.RelocData$Type r3 = r1.type
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 1
            r4 = 4
            r5 = 0
            if (r2 == r3) goto L_0x0069
            r6 = 2
            if (r2 == r6) goto L_0x0064
            r6 = 3
            if (r2 == r6) goto L_0x003a
            if (r2 != r4) goto L_0x0032
            goto L_0x003a
        L_0x0032:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "invalid relocation type"
            r12.<init>(r13)
            throw r12
        L_0x003a:
            long r6 = r1.destination
            int r2 = r1.offset
            long r8 = (long) r2
            long r8 = r8 + r14
            r10 = 4
            long r8 = r8 + r10
            long r6 = r6 - r8
            boolean r2 = r12.is64()
            if (r2 == 0) goto L_0x0062
            com.kenai.jnr.x86asm.RelocData$Type r2 = r1.type
            com.kenai.jnr.x86asm.RelocData$Type r8 = com.kenai.jnr.x86asm.RelocData.Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE
            if (r2 != r8) goto L_0x0062
            boolean r2 = com.kenai.jnr.x86asm.Util.isInt32(r6)
            if (r2 != 0) goto L_0x0062
            int r2 = r13.position()
            long r5 = (long) r2
            int r2 = r1.offset
            int r2 = r2 + r4
            long r7 = (long) r2
            long r6 = r5 - r7
            goto L_0x006c
        L_0x0062:
            r3 = r5
            goto L_0x006c
        L_0x0064:
            long r2 = r1.destination
            long r6 = r14 + r2
            goto L_0x0062
        L_0x0069:
            long r6 = r1.destination
            goto L_0x0062
        L_0x006c:
            int r2 = r1.size
            if (r2 == r4) goto L_0x0082
            r4 = 8
            if (r2 != r4) goto L_0x007a
            int r2 = r1.offset
            r13.putLong(r2, r6)
            goto L_0x0088
        L_0x007a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "invalid relocation size"
            r12.<init>(r13)
            throw r12
        L_0x0082:
            int r2 = r1.offset
            int r4 = (int) r6
            r13.putInt(r2, r4)
        L_0x0088:
            boolean r2 = r12.is64()
            if (r2 == 0) goto L_0x000e
            if (r3 == 0) goto L_0x000e
            com.kenai.jnr.x86asm.Logger r2 = r12._logger
            if (r2 == 0) goto L_0x00af
            int r3 = r1.offset
            long r3 = (long) r3
            long r3 = r3 + r14
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            long r4 = r1.destination
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "; Trampoline from %x -> %x\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.log(r3)
        L_0x00af:
            long r1 = r1.destination
            com.kenai.jnr.x86asm.TrampolineWriter.writeTrampoline(r13, r1)
            goto L_0x000e
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jnr.x86asm.Assembler.relocCode(java.nio.ByteBuffer, long):void");
    }

    public final void setByteAt(int i3, byte b3) {
        this._buffer.setByteAt(i3, b3);
    }

    public final void setDWordAt(int i3, int i4) {
        this._buffer.setDWordAt(i3, i4);
    }

    public final void setInt32At(int i3, long j2) {
        this._buffer.setDWordAt(i3, (int) j2);
    }

    public final void setQWordAt(int i3, long j2) {
        this._buffer.setQWordAt(i3, j2);
    }

    public final void setVarAt(int i3, long j2, boolean z2, int i4) {
        if (i4 == 1) {
            setByteAt(i3, (byte) ((int) j2));
        } else if (i4 == 2) {
            setWordAt(i3, (short) ((int) j2));
        } else if (i4 != 4) {
            if (i4 == 8) {
                setQWordAt(i3, j2);
            }
            throw new IllegalArgumentException("invalid size");
        } else {
            setDWordAt(i3, (int) j2);
        }
    }

    public final void setWordAt(int i3, short s3) {
        this._buffer.setWordAt(i3, s3);
    }

    public int trampolineSize() {
        return this._trampolineSize;
    }

    public void _emitModR(int i3, BaseReg baseReg) {
        _emitMod(3, i3, baseReg.code());
    }

    public void _emitX86Inl(int i3, boolean z2, int i4, int i5) {
        if (z2) {
            _emitByte(102);
        }
        int i6 = -16777216 & i3;
        if (i6 != 0) {
            _emitByte(i6 >> 24);
        }
        if (is64()) {
            _emitRexR(i4, 0, i5);
        }
        int i7 = 16711680 & i3;
        if (i7 != 0) {
            _emitByte(i7 >> 16);
        }
        int i8 = 65280 & i3;
        if (i8 != 0) {
            _emitByte(i8 >> 8);
        }
        _emitByte((i3 & 255) + (i5 & 7));
    }

    public void _emitX86RM(int i3, boolean z2, int i4, int i5, Operand operand, int i6) {
        if (z2) {
            _emitByte(102);
        }
        _emitSegmentPrefix(operand);
        int i7 = -16777216 & i3;
        if (i7 != 0) {
            _emitByte(i7 >> 24);
        }
        if (is64()) {
            _emitRexRM(i4, i5, operand);
        }
        int i8 = 16711680 & i3;
        if (i8 != 0) {
            _emitByte((byte) (i8 >> 16));
        }
        int i9 = 65280 & i3;
        if (i9 != 0) {
            _emitByte((byte) (i9 >> 8));
        }
        _emitByte((byte) (i3 & 255));
        _emitModRM(i5, operand, i6);
    }

    public void _emitRexR(boolean z2, int i3, int i4) {
        _emitRexR(intValue(z2), i3, i4);
    }

    public void _emitRexRM(boolean z2, int i3, Operand operand) {
        _emitRexRM(intValue(z2), i3, operand);
    }
}
