package com.examples.photogalleryapp.Util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Common {
    fun hideSoftKeyboard(activity: Activity,view: View){
        val imm:InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken,0)
    }
}