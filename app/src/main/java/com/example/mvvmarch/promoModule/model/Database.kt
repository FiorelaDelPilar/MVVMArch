package com.example.mvvmarch.promoModule.model

import com.example.mvvmarch.common.dataAccess.local.getAllPromos

class Database {
    fun getPromos() = getAllPromos()
}