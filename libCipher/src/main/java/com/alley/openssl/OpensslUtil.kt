package com.alley.openssl

import android.content.Context
import android.util.Log

object OpensslUtil {
    private var jniUtils: JniUtils? = null
    private const val VERIFY_FIRST = "you need verify first !!!"
    private const val HACK_APK = "this apk is hacked !!!"
    private const val PUBLIC_KEY_ERROR = "this publicKey is incorrect"

    /**
     * 校验 apk 是否是正版
     */
    fun verify(context: Context): Boolean {
        jniUtils = JniUtils()
        val isOwner = jniUtils!!.verifySha1OfApk(context)
        if (!isOwner) {
            throw RuntimeException(HACK_APK)
        }
        return isOwner
    }

    /**
     * 校验公钥正确性
     */
    fun checkLicence(publicKey: String): Boolean {
        var result = 0
        jniUtils?.apply {
            result = checkLicence(publicKey.toByteArray())
            if (result != 1) {
                Log.e(OpensslUtil::class.java.simpleName, PUBLIC_KEY_ERROR)
            }
        } ?: Log.e(OpensslUtil::class.java.simpleName, VERIFY_FIRST)
        return result == 1
    }
}