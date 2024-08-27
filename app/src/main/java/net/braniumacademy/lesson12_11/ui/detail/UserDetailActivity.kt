package net.braniumacademy.lesson12_11.ui.detail

import android.os.Bundle
import net.braniumacademy.lesson12_11.ui.BaseActivity
import net.braniumacademy.lesson12_11.databinding.ActivityUserDetailBinding

class UserDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillData()
        setAction()
    }

    private fun fillData() {
        val userId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.getUser(userId).observe(this) {
            binding.editNameDetail.setText(it?.fullName)
            binding.editEmailDetail.setText(it?.email)
            binding.editDisplayNameDetail.setText(it?.displayName)
        }
    }

    private fun setAction() {
        binding.btnOk.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_ID = "net.braniumacademy.kotlin.ui.detail.EXTRA_ID"
    }
}