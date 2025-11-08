package com.fasterxml.jackson.databind.cfg;

public enum CoercionAction {
    Fail,
    TryConvert,
    AsNull,
    AsEmpty
}
