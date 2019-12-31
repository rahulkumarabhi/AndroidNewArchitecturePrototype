package com.example.topratedmoviespersistent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topratedmoviespersistent.sealed.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {
    val mutableLiveOfStateData = MutableLiveData<State>()
    init {
        viewModelScope.launch {
            delay(2000)
            mutableLiveOfStateData.postValue(State.MainActivity())
        }
    }

}