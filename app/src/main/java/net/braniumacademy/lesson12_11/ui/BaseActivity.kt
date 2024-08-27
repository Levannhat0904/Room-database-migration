package net.braniumacademy.lesson12_11.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.braniumacademy.lesson12_11.data.database.AppDatabase
import net.braniumacademy.lesson12_11.data.repository.UserRepository
import net.braniumacademy.lesson12_11.ui.viewmodel.UserViewModel

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { UserRepository(database.getUserDao()) }
        viewModel = UserViewModel(repository)
    }
}