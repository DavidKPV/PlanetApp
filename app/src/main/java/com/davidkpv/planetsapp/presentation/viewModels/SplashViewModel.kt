package com.davidkpv.planetsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidkpv.planetsapp.data.dao.UserDao
import com.davidkpv.planetsapp.data.entities.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(
    private val userDao: UserDao,
) : ViewModel() {
    val getUser =
        userDao.getUser().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            User(id = 2, name = "", lastName = "", birthDate = "", country = ""),
        )
}
