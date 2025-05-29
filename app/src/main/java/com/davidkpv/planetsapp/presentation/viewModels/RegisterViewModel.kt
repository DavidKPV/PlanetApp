package com.davidkpv.planetsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidkpv.planetsapp.data.dao.UserDao
import com.davidkpv.planetsapp.data.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val userDao: UserDao,
) : ViewModel() {
    fun insertUser(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insertUser(user)
            }
        }
    }
}
