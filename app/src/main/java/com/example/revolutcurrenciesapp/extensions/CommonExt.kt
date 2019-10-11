package com.example.revolutcurrenciesapp.extensions

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.data.error.NoConnectivityException
import java.net.*

abstract class TextWatcherAbstract : TextWatcher {
    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Throwable.isConnectivityException():Boolean{
    return when (this) {
        is NoConnectivityException,
        is ConnectException,
        is SocketException,
        is SocketTimeoutException,
        is UnknownHostException,
        is ProtocolException -> true
        else -> false
    }
}
