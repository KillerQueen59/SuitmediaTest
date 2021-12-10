package com.ojanbelajar.suitmediatest.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManagement(var context: Context) {
    companion object{
        private val PREF_NAME = "suitmediatest"

        val KEY_GUEST = "guest"
        val KEY_EVENT = "event"
        val KEY_NAME = "name"

    }

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    val name: String
        get() {
            return pref.getString(KEY_NAME,"").toString()
        }

    val event: String
        get() {
            return pref.getString(KEY_EVENT,"").toString()
        }

    val guest: String
        get() {
            return pref.getString(KEY_GUEST,"").toString()
        }

    fun updateName(name: String){
        editor.putString(KEY_NAME,name)
        editor.commit()
    }

    fun updateGuest(guest: String){
        editor.putString(KEY_GUEST,guest)
        editor.commit()
    }

    fun updateEvent(event: String){
        editor.putString(KEY_EVENT,event)
        editor.commit()
    }

    fun clearSession(){
        editor.remove(KEY_GUEST)
        editor.remove(KEY_EVENT)
        editor.commit()

    }


}