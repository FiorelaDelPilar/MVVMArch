package com.example.mvvmarch.updateModule.model

import com.example.mvvmarch.R
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.entities.Wine
import com.example.mvvmarch.common.utils.Constants

class UpdateRepository(private val db: RoomDatabase) {
    fun requestWine(id: Double, callback: (Wine) -> Unit) {
        try {
            val result = db.getWineById(id)
            callback(result)
        } catch (e: MyException) {
            throw MyException(Constants.EC_GET_WINE, R.string.room_request_fail)
        }
    }

    fun updateWine(wine: Wine, callback: () -> Unit) {
        val result = db.updateWined(wine)

        if (result == 0) {
            throw MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)
        } else {
            callback()
        }
    }
}