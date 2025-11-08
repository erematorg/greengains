package com.google.firebase.auth.internal;

import android.support.v4.media.session.a;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import java.util.Arrays;
import java.util.List;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public final class zzal {
    @NonNull
    private static Status zza(String str, @Nullable String str2) {
        int i3;
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -2130504259:
                if (str.equals("USER_CANCELLED")) {
                    c3 = 0;
                    break;
                }
                break;
            case -2065866930:
                if (str.equals("INVALID_RECIPIENT_EMAIL")) {
                    c3 = 1;
                    break;
                }
                break;
            case -2014808264:
                if (str.equals("WEB_CONTEXT_ALREADY_PRESENTED")) {
                    c3 = 2;
                    break;
                }
                break;
            case -2005236790:
                if (str.equals("INTERNAL_SUCCESS_SIGN_OUT")) {
                    c3 = 3;
                    break;
                }
                break;
            case -2001169389:
                if (str.equals("INVALID_IDP_RESPONSE")) {
                    c3 = 4;
                    break;
                }
                break;
            case -1944433728:
                if (str.equals("DYNAMIC_LINK_NOT_ACTIVATED")) {
                    c3 = 5;
                    break;
                }
                break;
            case -1800638118:
                if (str.equals("QUOTA_EXCEEDED")) {
                    c3 = 6;
                    break;
                }
                break;
            case -1774756919:
                if (str.equals("WEB_NETWORK_REQUEST_FAILED")) {
                    c3 = 7;
                    break;
                }
                break;
            case -1699246888:
                if (str.equals("INVALID_RECAPTCHA_VERSION")) {
                    c3 = 8;
                    break;
                }
                break;
            case -1603818979:
                if (str.equals("RECAPTCHA_NOT_ENABLED")) {
                    c3 = 9;
                    break;
                }
                break;
            case -1587614300:
                if (str.equals("EXPIRED_OOB_CODE")) {
                    c3 = 10;
                    break;
                }
                break;
            case -1584641425:
                if (str.equals("UNAUTHORIZED_DOMAIN")) {
                    c3 = 11;
                    break;
                }
                break;
            case -1583894766:
                if (str.equals("INVALID_OOB_CODE")) {
                    c3 = 12;
                    break;
                }
                break;
            case -1458751677:
                if (str.equals("MISSING_EMAIL")) {
                    c3 = CharUtils.CR;
                    break;
                }
                break;
            case -1421414571:
                if (str.equals("INVALID_CODE")) {
                    c3 = 14;
                    break;
                }
                break;
            case -1345867105:
                if (str.equals("TOKEN_EXPIRED")) {
                    c3 = 15;
                    break;
                }
                break;
            case -1340100504:
                if (str.equals("INVALID_TENANT_ID")) {
                    c3 = 16;
                    break;
                }
                break;
            case -1242922234:
                if (str.equals("ALTERNATE_CLIENT_IDENTIFIER_REQUIRED")) {
                    c3 = 17;
                    break;
                }
                break;
            case -1232010689:
                if (str.equals("INVALID_SESSION_INFO")) {
                    c3 = 18;
                    break;
                }
                break;
            case -1202691903:
                if (str.equals("SECOND_FACTOR_EXISTS")) {
                    c3 = 19;
                    break;
                }
                break;
            case -1112393964:
                if (str.equals("INVALID_EMAIL")) {
                    c3 = 20;
                    break;
                }
                break;
            case -1063710844:
                if (str.equals("ADMIN_ONLY_OPERATION")) {
                    c3 = 21;
                    break;
                }
                break;
            case -974503964:
                if (str.equals("MISSING_OR_INVALID_NONCE")) {
                    c3 = 22;
                    break;
                }
                break;
            case -863830559:
                if (str.equals("INVALID_CERT_HASH")) {
                    c3 = 23;
                    break;
                }
                break;
            case -828507413:
                if (str.equals("NO_SUCH_PROVIDER")) {
                    c3 = 24;
                    break;
                }
                break;
            case -749743758:
                if (str.equals("MFA_ENROLLMENT_NOT_FOUND")) {
                    c3 = 25;
                    break;
                }
                break;
            case -736207500:
                if (str.equals("MISSING_PASSWORD")) {
                    c3 = 26;
                    break;
                }
                break;
            case -646022241:
                if (str.equals("CREDENTIAL_TOO_OLD_LOGIN_AGAIN")) {
                    c3 = 27;
                    break;
                }
                break;
            case -595928767:
                if (str.equals("TIMEOUT")) {
                    c3 = 28;
                    break;
                }
                break;
            case -505579581:
                if (str.equals("INVALID_REQ_TYPE")) {
                    c3 = 29;
                    break;
                }
                break;
            case -406804866:
                if (str.equals("INVALID_LOGIN_CREDENTIALS")) {
                    c3 = 30;
                    break;
                }
                break;
            case -380728810:
                if (str.equals("INVALID_RECAPTCHA_ACTION")) {
                    c3 = 31;
                    break;
                }
                break;
            case -333672188:
                if (str.equals("OPERATION_NOT_ALLOWED")) {
                    c3 = ' ';
                    break;
                }
                break;
            case -294485423:
                if (str.equals("WEB_INTERNAL_ERROR")) {
                    c3 = '!';
                    break;
                }
                break;
            case -217128228:
                if (str.equals("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                    c3 = '\"';
                    break;
                }
                break;
            case -122667194:
                if (str.equals("MISSING_MFA_ENROLLMENT_ID")) {
                    c3 = '#';
                    break;
                }
                break;
            case -75433118:
                if (str.equals("USER_NOT_FOUND")) {
                    c3 = '$';
                    break;
                }
                break;
            case -52772551:
                if (str.equals("CAPTCHA_CHECK_FAILED")) {
                    c3 = '%';
                    break;
                }
                break;
            case -40686718:
                if (str.equals("WEAK_PASSWORD")) {
                    c3 = Typography.amp;
                    break;
                }
                break;
            case 15352275:
                if (str.equals("EMAIL_NOT_FOUND")) {
                    c3 = '\'';
                    break;
                }
                break;
            case 210308040:
                if (str.equals("UNSUPPORTED_FIRST_FACTOR")) {
                    c3 = '(';
                    break;
                }
                break;
            case 269327773:
                if (str.equals("INVALID_SENDER")) {
                    c3 = ')';
                    break;
                }
                break;
            case 278802867:
                if (str.equals("MISSING_PHONE_NUMBER")) {
                    c3 = '*';
                    break;
                }
                break;
            case 408411681:
                if (str.equals("INVALID_DYNAMIC_LINK_DOMAIN")) {
                    c3 = SignatureVisitor.EXTENDS;
                    break;
                }
                break;
            case 423563023:
                if (str.equals("MISSING_MFA_PENDING_CREDENTIAL")) {
                    c3 = AbstractJsonLexerKt.COMMA;
                    break;
                }
                break;
            case 429251986:
                if (str.equals("UNSUPPORTED_PASSTHROUGH_OPERATION")) {
                    c3 = SignatureVisitor.SUPER;
                    break;
                }
                break;
            case 483847807:
                if (str.equals("EMAIL_EXISTS")) {
                    c3 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                    break;
                }
                break;
            case 491979549:
                if (str.equals("INVALID_ID_TOKEN")) {
                    c3 = JsonPointer.SEPARATOR;
                    break;
                }
                break;
            case 492072102:
                if (str.equals("WEB_STORAGE_UNSUPPORTED")) {
                    c3 = '0';
                    break;
                }
                break;
            case 492515765:
                if (str.equals("MISSING_CLIENT_TYPE")) {
                    c3 = '1';
                    break;
                }
                break;
            case 530628231:
                if (str.equals("MISSING_RECAPTCHA_VERSION")) {
                    c3 = '2';
                    break;
                }
                break;
            case 542728406:
                if (str.equals("PASSWORD_LOGIN_DISABLED")) {
                    c3 = '3';
                    break;
                }
                break;
            case 582457886:
                if (str.equals("UNVERIFIED_EMAIL")) {
                    c3 = '4';
                    break;
                }
                break;
            case 605031096:
                if (str.equals("REJECTED_CREDENTIAL")) {
                    c3 = '5';
                    break;
                }
                break;
            case 745638750:
                if (str.equals("INVALID_MFA_PENDING_CREDENTIAL")) {
                    c3 = '6';
                    break;
                }
                break;
            case 786916712:
                if (str.equals("INVALID_VERIFICATION_PROOF")) {
                    c3 = '7';
                    break;
                }
                break;
            case 799258561:
                if (str.equals("INVALID_PROVIDER_ID")) {
                    c3 = '8';
                    break;
                }
                break;
            case 819646646:
                if (str.equals("CREDENTIAL_MISMATCH")) {
                    c3 = '9';
                    break;
                }
                break;
            case 844240628:
                if (str.equals("WEB_CONTEXT_CANCELED")) {
                    c3 = AbstractJsonLexerKt.COLON;
                    break;
                }
                break;
            case 886186878:
                if (str.equals("REQUIRES_SECOND_FACTOR_AUTH")) {
                    c3 = ';';
                    break;
                }
                break;
            case 895302372:
                if (str.equals("MISSING_CLIENT_IDENTIFIER")) {
                    c3 = Typography.less;
                    break;
                }
                break;
            case 922685102:
                if (str.equals("INVALID_MESSAGE_PAYLOAD")) {
                    c3 = SignatureVisitor.INSTANCEOF;
                    break;
                }
                break;
            case 989000548:
                if (str.equals("RESET_PASSWORD_EXCEED_LIMIT")) {
                    c3 = Typography.greater;
                    break;
                }
                break;
            case 1034932393:
                if (str.equals("INVALID_PENDING_TOKEN")) {
                    c3 = '?';
                    break;
                }
                break;
            case 1072360691:
                if (str.equals("INVALID_CUSTOM_TOKEN")) {
                    c3 = '@';
                    break;
                }
                break;
            case 1094975491:
                if (str.equals("INVALID_PASSWORD")) {
                    c3 = 'A';
                    break;
                }
                break;
            case 1107081238:
                if (str.equals("<<Network Error>>")) {
                    c3 = 'B';
                    break;
                }
                break;
            case 1113992697:
                if (str.equals("INVALID_RECAPTCHA_TOKEN")) {
                    c3 = 'C';
                    break;
                }
                break;
            case 1141576252:
                if (str.equals("SESSION_EXPIRED")) {
                    c3 = 'D';
                    break;
                }
                break;
            case 1199811910:
                if (str.equals("MISSING_CODE")) {
                    c3 = 'E';
                    break;
                }
                break;
            case 1226505451:
                if (str.equals("FEDERATED_USER_ID_ALREADY_LINKED")) {
                    c3 = 'F';
                    break;
                }
                break;
            case 1308491624:
                if (str.equals("MISSING_RECAPTCHA_TOKEN")) {
                    c3 = 'G';
                    break;
                }
                break;
            case 1388786705:
                if (str.equals("INVALID_IDENTIFIER")) {
                    c3 = 'H';
                    break;
                }
                break;
            case 1433767024:
                if (str.equals("USER_DISABLED")) {
                    c3 = 'I';
                    break;
                }
                break;
            case 1442968770:
                if (str.equals("INVALID_PHONE_NUMBER")) {
                    c3 = 'J';
                    break;
                }
                break;
            case 1494923453:
                if (str.equals("INVALID_APP_CREDENTIAL")) {
                    c3 = 'K';
                    break;
                }
                break;
            case 1497901284:
                if (str.equals("TOO_MANY_ATTEMPTS_TRY_LATER")) {
                    c3 = 'L';
                    break;
                }
                break;
            case 1803454477:
                if (str.equals("MISSING_CONTINUE_URI")) {
                    c3 = 'M';
                    break;
                }
                break;
            case 1898790704:
                if (str.equals("MISSING_SESSION_INFO")) {
                    c3 = 'N';
                    break;
                }
                break;
            case 2063209097:
                if (str.equals("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                    c3 = 'O';
                    break;
                }
                break;
            case 2082564316:
                if (str.equals("UNSUPPORTED_TENANT_OPERATION")) {
                    c3 = 'P';
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                i3 = 18001;
                break;
            case 1:
                i3 = 17033;
                break;
            case 2:
                i3 = 17057;
                break;
            case 3:
                i3 = 17091;
                break;
            case 4:
            case 30:
            case '?':
                i3 = FirebaseError.ERROR_INVALID_CREDENTIAL;
                break;
            case 5:
                i3 = 17068;
                break;
            case 6:
                i3 = 17052;
                break;
            case 7:
                i3 = 17061;
                break;
            case 8:
                i3 = 17206;
                break;
            case 9:
                i3 = 17200;
                break;
            case 10:
                i3 = 17029;
                break;
            case 11:
                i3 = 17038;
                break;
            case 12:
                i3 = 17030;
                break;
            case 13:
                i3 = 17034;
                break;
            case 14:
                i3 = 17044;
                break;
            case 15:
                i3 = FirebaseError.ERROR_USER_TOKEN_EXPIRED;
                break;
            case 16:
                i3 = 17079;
                break;
            case 17:
                i3 = 18002;
                break;
            case 18:
                i3 = 17046;
                break;
            case 19:
                i3 = 17087;
                break;
            case 20:
            case 'H':
                i3 = FirebaseError.ERROR_INVALID_EMAIL;
                break;
            case 21:
                i3 = 17085;
                break;
            case 22:
                i3 = 17094;
                break;
            case 23:
                i3 = 17064;
                break;
            case 24:
                i3 = FirebaseError.ERROR_NO_SUCH_PROVIDER;
                break;
            case 25:
                i3 = 17084;
                break;
            case 26:
                i3 = 17035;
                break;
            case 27:
                i3 = FirebaseError.ERROR_REQUIRES_RECENT_LOGIN;
                break;
            case 28:
            case 'B':
                i3 = FirebaseError.ERROR_NETWORK_REQUEST_FAILED;
                break;
            case 29:
                i3 = 17207;
                break;
            case 31:
                i3 = 17203;
                break;
            case ' ':
            case '3':
                i3 = FirebaseError.ERROR_OPERATION_NOT_ALLOWED;
                break;
            case '!':
                i3 = 17062;
                break;
            case '\"':
                i3 = 17088;
                break;
            case '#':
                i3 = 17082;
                break;
            case '$':
            case '\'':
                i3 = FirebaseError.ERROR_USER_NOT_FOUND;
                break;
            case '%':
                i3 = 17056;
                break;
            case '&':
                i3 = FirebaseError.ERROR_WEAK_PASSWORD;
                break;
            case '(':
                i3 = 17089;
                break;
            case ')':
                i3 = 17032;
                break;
            case '*':
                i3 = 17041;
                break;
            case '+':
                i3 = 17074;
                break;
            case ',':
                i3 = 17081;
                break;
            case '-':
                i3 = 17095;
                break;
            case '.':
                i3 = FirebaseError.ERROR_EMAIL_ALREADY_IN_USE;
                break;
            case '/':
                i3 = FirebaseError.ERROR_INVALID_USER_TOKEN;
                break;
            case '0':
                i3 = 17065;
                break;
            case '1':
                i3 = 17204;
                break;
            case '2':
                i3 = 17205;
                break;
            case '4':
                i3 = 17086;
                break;
            case '5':
                i3 = 17075;
                break;
            case '6':
                i3 = 17083;
                break;
            case '7':
                i3 = 17049;
                break;
            case '8':
                i3 = 17071;
                break;
            case '9':
                i3 = FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH;
                break;
            case ':':
                i3 = 17058;
                break;
            case ';':
                i3 = 17078;
                break;
            case '<':
                i3 = 17093;
                break;
            case '=':
                i3 = 17031;
                break;
            case '>':
            case 'L':
                i3 = FirebaseError.ERROR_TOO_MANY_REQUESTS;
                break;
            case '@':
                i3 = FirebaseError.ERROR_INVALID_CUSTOM_TOKEN;
                break;
            case 'A':
                i3 = FirebaseError.ERROR_WRONG_PASSWORD;
                break;
            case 'C':
                i3 = 17202;
                break;
            case 'D':
                i3 = 17051;
                break;
            case 'E':
                i3 = 17043;
                break;
            case 'F':
                i3 = FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE;
                break;
            case 'G':
                i3 = 17201;
                break;
            case 'I':
                i3 = FirebaseError.ERROR_USER_DISABLED;
                break;
            case 'J':
                i3 = 17042;
                break;
            case 'K':
                i3 = FirebaseError.ERROR_APP_NOT_AUTHORIZED;
                break;
            case 'M':
                i3 = 17040;
                break;
            case 'N':
                i3 = 17045;
                break;
            case 'O':
                i3 = 17090;
                break;
            case 'P':
                i3 = 17073;
                break;
            default:
                i3 = 17499;
                break;
        }
        if (i3 != 17499) {
            return new Status(i3, str2);
        }
        if (str2 != null) {
            return new Status(i3, a.n(str, ":", str2));
        }
        return new Status(i3, str);
    }

    @NonNull
    public static Status zza(@Nullable String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return new Status(FirebaseError.ERROR_INTERNAL_ERROR);
        }
        String[] split = str.split(":", 2);
        split[0] = split[0].trim();
        if (split.length > 1 && (str2 = split[1]) != null) {
            split[1] = str2.trim();
        }
        List asList = Arrays.asList(split);
        if (asList.size() > 1) {
            return zza((String) asList.get(0), (String) asList.get(1));
        }
        return zza((String) asList.get(0), (String) null);
    }
}
