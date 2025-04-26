package com.example.mvvmarch.loginModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.common.entities.MyException
import com.example.mvvmarch.common.utils.Constants
import com.example.mvvmarch.loginModule.model.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _inProgess = MutableLiveData<Boolean>()
    val inProgess: LiveData<Boolean> = _inProgess

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _isAuthValid = MutableLiveData<Boolean>()
    val isAuthValid: LiveData<Boolean> = _isAuthValid

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                _isAuthValid.value = repository.checkAuth()
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }

    fun login(username: String, pin: String) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                _isAuthValid.value = repository.login(username, pin)
            } catch (e: MyException) {
                _snackbarMsg.value = e.resMsg

            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }
}