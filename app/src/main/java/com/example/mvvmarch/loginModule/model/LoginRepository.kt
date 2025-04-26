package com.example.mvvmarch.loginModule.model

import com.example.mvvmarch.common.dataAccess.local.FakeFirebaseAuth

class LoginRepository(private val auth: FakeFirebaseAuth) {
    suspend fun checkAuth(): Boolean {
        return auth.isValidAuth()
    }

    suspend fun login(username: String, pin: String): Boolean {
        return auth.login(username, pin)
    }
}