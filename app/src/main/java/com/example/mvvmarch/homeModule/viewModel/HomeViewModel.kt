package com.example.mvvmarch.homeModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.R
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.entities.Wine
import com.example.mvvmarch.common.utils.Constants
import com.example.mvvmarch.homeModule.model.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _inProgess = MutableLiveData<Boolean>()
    val inProgess: LiveData<Boolean> = _inProgess

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _wines = MutableLiveData<List<Wine>>()
    val wines: LiveData<List<Wine>> = _wines

    init {
        getAllWines()
    }

    fun getAllWines() {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                withContext(Dispatchers.IO) {
                    repository.getAllWines { wines ->
                        _wines.value = wines
                    }
                }
            } catch (e: MyException) {
                _snackbarMsg.postValue(e.resMsg)
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }

    fun addWine(wine: Wine) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                withContext(Dispatchers.IO) {
                    repository.addWine(wine) {
                        _snackbarMsg.postValue(R.string.room_save_success)
                    }
                }
            } catch (e: MyException) {
                _snackbarMsg.postValue(e.resMsg)
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }
}