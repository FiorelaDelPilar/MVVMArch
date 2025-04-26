package com.example.mvvmarch.loginModule.model

import com.example.mvvmarch.R
import com.example.mvvmarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.utils.Constants

class LoginRepository(private val auth: FakeFirebaseAuth) {
    suspend fun checkAuth(): Boolean {
        return auth.isValidAuth()
    }

    suspend fun login(username: String, pin: String): Boolean {
        val result = auth.login(username, pin)
        if (!result) throw MyException(Constants.EC_LOGIN, R.string.login_login_fail)
        return true
    }
}