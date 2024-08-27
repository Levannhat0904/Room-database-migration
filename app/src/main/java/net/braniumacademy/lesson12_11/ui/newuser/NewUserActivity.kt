package net.braniumacademy.lesson12_11.ui.newuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.braniumacademy.lesson12_11.data.database.AppDatabase
import net.braniumacademy.lesson12_11.data.repository.UserRepository
import net.braniumacademy.lesson12_11.databinding.ActivityNewUserBinding
import net.braniumacademy.lesson12_11.ui.viewmodel.NewUserViewModel

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    private lateinit var viewModel: NewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupListener()
    }

    private fun setupViewModel() {
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { UserRepository(database.getUserDao()) }
        viewModel = NewUserViewModel(repository)
    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val fullName = binding.editFullname.text.toString()
            val displayName = binding.editDisplayName.text.toString()
            viewModel.saveUser(fullName, email, displayName)
            setResult(RESULT_OK, Intent())
            finish()
        }
        binding.btnCancel.setOnClickListener { finish() }
    }

}