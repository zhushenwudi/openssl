package com.alley.openssl

import android.content.Context

class JniUtils {

    init {
        System.loadLibrary("cipher")
    }

    /**
     * RSA 验证
     */
    external fun checkLicence(keys: ByteArray): Int

    /**
     * 校验apk签名是否有效
     */
    external fun verifySha1OfApk(context: Context): Boolean
}