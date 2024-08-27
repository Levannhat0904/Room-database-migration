package net.braniumacademy.lesson12_11.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.braniumacademy.lesson12_11.data.model.User
import net.braniumacademy.lesson12_11.data.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users: LiveData<List<User>> = repository.users.asLiveData()

    fun getUser(id: Int): LiveData<User?> {
        val response: MutableLiveData<User?> = MutableLiveData(null)
        viewModelScope.launch(Dispatchers.IO) {
            val retUser = repository.getUserById(id)
            response.postValue(retUser)
        }
        return response
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUser(user)
    }

    fun updateUser(userId: Int, fullName: String, email: String, displayName: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(userId, fullName, email, displayName)
            repository.updateUser(user)
        }
}