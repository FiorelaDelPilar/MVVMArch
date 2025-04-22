package com.example.mvvmarch.accountModule.model

import com.example.mvvmarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.mvvmarch.common.entities.FirebaseUser

class AccountRepository(private val auth: FakeFirebaseAuth) {
    suspend fun signOut(): Boolean {
        return auth.signOut()
    }

    suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }
}