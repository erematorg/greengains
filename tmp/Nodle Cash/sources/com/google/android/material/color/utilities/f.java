package com.google.android.material.color.utilities;

import io.zksync.crypto.eip712.Eip712Encoder;
import io.zksync.crypto.eip712.Eip712Struct;
import io.zksync.protocol.account.Wallet;
import io.zksync.utils.ContractDeployer;
import java.lang.reflect.Field;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.rlp.RlpString;

public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6650a;

    public /* synthetic */ f(int i3) {
        this.f6650a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f6650a) {
            case 0:
                return MaterialDynamicColors.lambda$onSurface$32((DynamicScheme) obj);
            case 1:
                return MaterialDynamicColors.lambda$surfaceContainerHighest$30((DynamicScheme) obj);
            case 2:
                return ((DynamicScheme) obj).primaryPalette;
            case 3:
                return MaterialDynamicColors.lambda$primaryFixed$104((DynamicScheme) obj);
            case 4:
                return ((DynamicScheme) obj).primaryPalette;
            case 5:
                return MaterialDynamicColors.lambda$primaryFixedDim$107((DynamicScheme) obj);
            case 6:
                return ((DynamicScheme) obj).tertiaryPalette;
            case 7:
                return MaterialDynamicColors.lambda$tertiaryContainer$86((DynamicScheme) obj);
            case 8:
                return Eip712Encoder.encodeValue((Type) obj);
            case 9:
                return Eip712Struct.lambda$encodeType$0((Pair) obj);
            case 10:
                return Wallet.lambda$estimateAndSend$10((EthSendTransaction) obj);
            case 11:
                return ((EthGasPrice) obj).getGasPrice();
            case 12:
                return RlpString.create((byte[]) obj);
            case 13:
                return ContractDeployer.hashBytecode((byte[]) obj);
            case 14:
                return new Bytes32((byte[]) obj);
            case 15:
                return ((Bytes4) obj).getValue();
            case 16:
                return ((Bytes4) obj).getValue();
            case 17:
                return ((Uint256) obj).getValue();
            case 18:
                return ((Uint256) obj).getValue();
            case 19:
                return ((Uint256) obj).getValue();
            case 20:
                return ((Uint256) obj).getValue();
            case 21:
                return ((DynamicBytes) obj).getValue();
            case 22:
                return ((DynamicBytes) obj).getValue();
            case 23:
                return ((Bytes4) obj).getValue();
            case 24:
                return ((Bytes4) obj).getValue();
            case 25:
                return ((Uint256) obj).getValue();
            case 26:
                return ((Uint256) obj).getValue();
            case 27:
                return ((Uint256) obj).getValue();
            case 28:
                return ((Uint256) obj).getValue();
            default:
                return ((Field) obj).getName();
        }
    }
}
