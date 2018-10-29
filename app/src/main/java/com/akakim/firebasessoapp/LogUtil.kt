package com.akakim.firebasessoapp

import android.content.Context
import android.util.Log

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-10-29
 */

open class LogUtil(){


    companion object {
        fun LogVerbose(tagContext : Context, msg : String){
            Log.d( tagContext.javaClass.simpleName, msg)
        }

    }
}