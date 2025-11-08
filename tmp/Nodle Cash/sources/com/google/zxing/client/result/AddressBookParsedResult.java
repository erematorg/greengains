package com.google.zxing.client.result;

public final class AddressBookParsedResult extends ParsedResult {
    private final String[] addressTypes;
    private final String[] addresses;
    private final String birthday;
    private final String[] emailTypes;
    private final String[] emails;
    private final String[] geo;
    private final String instantMessenger;
    private final String[] names;
    private final String[] nicknames;
    private final String note;

    /* renamed from: org  reason: collision with root package name */
    private final String f7194org;
    private final String[] phoneNumbers;
    private final String[] phoneTypes;
    private final String pronunciation;
    private final String title;
    private final String[] urls;

    public AddressBookParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7) {
        this(strArr, (String[]) null, (String) null, strArr2, strArr3, strArr4, strArr5, (String) null, (String) null, strArr6, strArr7, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }

    public String[] getAddressTypes() {
        return this.addressTypes;
    }

    public String[] getAddresses() {
        return this.addresses;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.names, sb);
        ParsedResult.maybeAppend(this.nicknames, sb);
        ParsedResult.maybeAppend(this.pronunciation, sb);
        ParsedResult.maybeAppend(this.title, sb);
        ParsedResult.maybeAppend(this.f7194org, sb);
        ParsedResult.maybeAppend(this.addresses, sb);
        ParsedResult.maybeAppend(this.phoneNumbers, sb);
        ParsedResult.maybeAppend(this.emails, sb);
        ParsedResult.maybeAppend(this.instantMessenger, sb);
        ParsedResult.maybeAppend(this.urls, sb);
        ParsedResult.maybeAppend(this.birthday, sb);
        ParsedResult.maybeAppend(this.geo, sb);
        ParsedResult.maybeAppend(this.note, sb);
        return sb.toString();
    }

    public String[] getEmailTypes() {
        return this.emailTypes;
    }

    public String[] getEmails() {
        return this.emails;
    }

    public String[] getGeo() {
        return this.geo;
    }

    public String getInstantMessenger() {
        return this.instantMessenger;
    }

    public String[] getNames() {
        return this.names;
    }

    public String[] getNicknames() {
        return this.nicknames;
    }

    public String getNote() {
        return this.note;
    }

    public String getOrg() {
        return this.f7194org;
    }

    public String[] getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public String[] getPhoneTypes() {
        return this.phoneTypes;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public String getTitle() {
        return this.title;
    }

    public String[] getURLs() {
        return this.urls;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddressBookParsedResult(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String[] strArr7, String[] strArr8, String str4, String str5, String str6, String[] strArr9, String[] strArr10) {
        super(ParsedResultType.ADDRESSBOOK);
        String[] strArr11 = strArr3;
        String[] strArr12 = strArr4;
        String[] strArr13 = strArr5;
        String[] strArr14 = strArr6;
        String[] strArr15 = strArr7;
        String[] strArr16 = strArr8;
        if (strArr11 != null && strArr12 != null && strArr11.length != strArr12.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        } else if (strArr13 != null && strArr14 != null && strArr13.length != strArr14.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        } else if (strArr15 == null || strArr16 == null || strArr15.length == strArr16.length) {
            this.names = strArr;
            this.nicknames = strArr2;
            this.pronunciation = str;
            this.phoneNumbers = strArr11;
            this.phoneTypes = strArr12;
            this.emails = strArr13;
            this.emailTypes = strArr14;
            this.instantMessenger = str2;
            this.note = str3;
            this.addresses = strArr15;
            this.addressTypes = strArr16;
            this.f7194org = str4;
            this.birthday = str5;
            this.title = str6;
            this.urls = strArr9;
            this.geo = strArr10;
        } else {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
    }
}
