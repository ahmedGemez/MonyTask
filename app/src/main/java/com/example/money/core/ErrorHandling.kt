package com.example.money.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.reactivex.functions.BiConsumer
import retrofit2.HttpException
import java.io.IOException
import java.lang.annotation.Inherited
import javax.inject.Inject

class ErrorHandling  @Inject constructor(var context: Context):
    BiConsumer<Throwable?, String?> {

    @Throws(IOException::class)
    override fun accept(throwable: Throwable?, msg: String?) {
        if (throwable is HttpException) {
            val httpException = throwable
            val code = httpException.code()
            if (code == 400) {
                Log.e("error400 user error", httpException.message!!)
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            } else if (code == 401) {
                Log.e("error401 unauthorized", httpException.message!!)
            } else if (code == 500) {
                Log.e(" 500 server error", httpException.message!!)
            }
        } else Log.e("Connection_Time_Out", "Connection Time Out")
    }

}