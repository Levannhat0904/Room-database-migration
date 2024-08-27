package net.braniumacademy.lesson12_11.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.braniumacademy.lesson12_11.data.model.User
import net.braniumacademy.lesson12_11.data.repository.UserRepository
import java.lang.StringBuilder

class NewUserViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveUser(fullName: String, email: String, displayName: String) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.save(User(0, fullName, email, displayName))
    }
}