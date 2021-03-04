package br.com.greenlight.ui.splash

import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        GlobalScope.launch {
            delay(3000)
            mutableLiveData.postValue(SplashState.MainActivity)
        }
    }
}
sealed class SplashState {
    object MainActivity : SplashState()
}