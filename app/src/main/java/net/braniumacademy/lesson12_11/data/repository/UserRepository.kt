package net.braniumacademy.lesson12_11.data.repository

import kotlinx.coroutines.flow.Flow
import net.braniumacademy.lesson12_11.data.dao.UserDao
import net.braniumacademy.lesson12_11.data.model.User

class UserRepository(private val userDao: UserDao) {
    val users: Flow<List<User>> = userDao.getAll()

    suspend fun save(user: User) {
        userDao.insertAll(user)
    }

    fun getUserById(id: Int): User? {
        return userDao.findById(id)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }
}