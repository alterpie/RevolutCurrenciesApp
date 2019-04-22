package com.example.revolutcurrenciesapp.util

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.domain.error.AppError
import com.example.revolutcurrenciesapp.R

abstract class TextWatcherAbstract : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

fun AppError.transformToMessage(context: Context): String = when(this){
    is AppError.Network-> context.getString(R.string.error_check_connectivity)
    is AppError.Unexpected->context.getString(R.string.error_unexpected)
    is AppError.Api-> this.error?.error ?: context.getString(R.string.error_unexpected)
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}