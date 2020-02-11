// AlipayRemoteService.aidl
package com.itheima.alipay;

// Declare any non-default types here with import statements

interface AlipayRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    boolean forwardPayMoney(float money);
}
