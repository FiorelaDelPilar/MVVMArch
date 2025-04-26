package com.example.mvvmarch.homeModule.viewModel

import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.R
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.entities.Wine
import com.example.mvvmarch.common.utils.Constants
import com.example.mvvmarch.common.viewModel.BaseWineViewModel
import com.example.mvvmarch.homeModule.model.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel() {
    init {
        getAllWines()
    }

    override fun getAllWines() {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.getAllWines { wines ->
                        setWines(wines)
                    }
                }
            } catch (e: MyException) {
                setSnackbarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }

    override fun addWine(wine: Wine) {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.addWine(wine) {
                        setSnackbarMsg(R.string.room_save_success)
                    }
                }
            } catch (e: MyException) {
                setSnackbarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }
}