package com.example.mvvmarch.favouriteModule.model

import com.example.mvvmarch.WineApplication
import com.example.mvvmarch.common.dataAccess.room.WineDao
import com.example.mvvmarch.common.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun getAllWines() = dao.getAllWines()

    fun addWine(wine: Wine) = dao.addWine(wine)

    fun deleteWine(wine: Wine) = dao.deleteWine(wine)
}