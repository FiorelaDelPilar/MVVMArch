package com.example.mvvmarch.updateModule.model

import com.example.mvvmarch.WineApplication
import com.example.mvvmarch.common.dataAccess.room.WineDao
import com.example.mvvmarch.common.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun getWineById(id: Double) = dao.getWineById(id)
    fun updateWined(wine: Wine) = dao.updateWine(wine)
}