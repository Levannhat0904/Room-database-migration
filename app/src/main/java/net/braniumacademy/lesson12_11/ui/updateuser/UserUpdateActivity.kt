package net.braniumacademy.lesson12_11.ui.updateuser

import android.content.Intent
import android.os.Bundle
import net.braniumacademy.lesson12_11.ui.BaseActivity
import net.braniumacademy.lesson12_11.ui.dialog.UpdateDialogFragment
import net.braniumacademy.lesson12_11.R
import net.braniumacademy.lesson12_11.databinding.ActivityUpdateUserBinding

class UserUpdateActivity : BaseActivity() {
    private lateinit var binding: ActivityUpdateUserBinding
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.containerLayout.btnAdd.text = getString(R.string.text_btn_update)
        fillData(intent)
        addListeners()
    }

    private fun addListeners() {
        binding.containerLayout.btnAdd.setOnClickListener {
            val fullName = binding.containerLayout.editFullname.text.toString()
            val email = binding.containerLayout.editEmail.text.toString()
            val displayName = binding.containerLayout.editDisplayName.text.toString()
            UpdateDialogFragment(object : UpdateUserListener {
                override fun onUpdate() {
                    viewModel.updateUser(userId, fullName, email, displayName)
                    finish()
                }

                override fun onCancel() {
                    finish()
                }
            }).show(supportFragmentManager, "TAG")
        }
        binding.containerLayout.btnCancel.setOnClickListener { finish() }
    }

    private fun fillData(intent: Intent) {
        userId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.getUser(userId).observe(this) {
            binding.containerLayout.editFullname.setText(it?.fullName)
            binding.containerLayout.editEmail.setText(it?.email)
            binding.containerLayout.editDisplayName.setText(it?.displayName)
        }
    }

    interface UpdateUserListener {
        fun onUpdate()
        fun onCancel()
    }

    companion object {
        const val EXTRA_ID = "net.braniumacademy.kotlin.ui.updateuser.EXTRA_ID"
    }
}