package com.example.mvvmarch.favouriteModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.R
import com.example.mvvmarch.common.entities.Wine
import com.example.mvvmarch.common.utils.Constants
import com.example.mvvmarch.favouriteModule.model.FavouriteRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: FavouriteRepository) : ViewModel() {
    private val _inProgess = MutableLiveData<Boolean>()
    val inProgess: LiveData<Boolean> = _inProgess

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _wines = MutableLiveData<List<Wine>>()
    val wines: LiveData<List<Wine>> = _wines

    private fun getAllWines() {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                val result = repository.getAllWines()
                if (result != null) {
                    _wines.value = result!!
                } else {
                    _snackbarMsg.value = R.string.room_request_fail
                }
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }

    private fun addWine(wine: Wine) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                val result = repository.addWine(wine)
                if (result != -1L) {
                    _snackbarMsg.value = R.string.room_save_fail
                } else {
                    _snackbarMsg.value = R.string.room_save_success

                }
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }

    private fun deleteWine(wine: Wine) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                val result = repository.deleteWine(wine)
                if (result == 0) {
                    _snackbarMsg.value = R.string.room_save_fail
                } else {
                    _snackbarMsg.value = R.string.room_save_success

                }
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }


}