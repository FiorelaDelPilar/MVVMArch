package com.example.mvvmarch.promoModule.model

import com.example.mvvmarch.R
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.entities.Promo
import com.example.mvvmarch.common.utils.Constants

class PromoRepository(private val db: Database) {
    fun getPromos(): List<Promo> {
        val result = db.getPromos()
        return result.ifEmpty {
            throw MyException(Constants.EC_REQUEST, R.string.promo_request_fail)
        }
    }
}