package br.com.greenlight.ui.user.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.database.dao.UserDao
import java.lang.IllegalArgumentException

class UserProfileViewModelFactory(private val userDao: UserDao, private val
application: Application):
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java))
            return UserProfileViewModel(userDao, application) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}