package net.braniumacademy.lesson12_11.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import net.braniumacademy.lesson12_11.data.model.User
import net.braniumacademy.lesson12_11.factory.IntentActionType
import net.braniumacademy.lesson12_11.factory.IntentFactoryImpl
import net.braniumacademy.lesson12_11.ui.adapter.UserAdapter
import net.braniumacademy.lesson12_11.ui.dialog.DeleteDialogFragment
import net.braniumacademy.lesson12_11.R
import net.braniumacademy.lesson12_11.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupListener()
    }

    private fun setupListener() {
        myLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::handleResult
        )
        binding.btnAddNew.setOnClickListener {
            val intent = IntentFactoryImpl<Any>(this)
                .createIntent(IntentActionType.CREATE, null)
            myLauncher.launch(intent)
        }
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            Snackbar.make(
                binding.root,
                getString(R.string.text_add_success),
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            Snackbar.make(
                binding.root,
                getString(R.string.text_add_failed),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    private fun setupRecyclerView() {
        binding.recyclerUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(viewModel.users.value,
            object : OptionMenuClickListener {
                override fun update(user: User) {
                    val intent = IntentFactoryImpl<User>(baseContext)
                        .createIntent(IntentActionType.EDIT, user)
                    startActivity(intent)
                }

                override fun delete(user: User) {
                    DeleteDialogFragment(object : ActionConfirmListener {
                        override fun confirm() {
                            viewModel.deleteUser(user)
                        }

                        override fun cancel() {
                            // do nothing
                        }
                    }).show(supportFragmentManager, "TAG")
                }

                override fun viewDetail(user: User) {
                    val intent = IntentFactoryImpl<User>(baseContext)
                        .createIntent(IntentActionType.VIEW_DETAIL, user)
                    startActivity(intent)
                }

            })
        binding.recyclerUser.adapter = adapter
        viewModel.users.observe(this) {
            adapter.updateData(it)
            adapter.notifyDataSetChanged()
        }
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        getDrawable(R.drawable.divider)?.let { itemDecoration.setDrawable(it) }
        binding.recyclerUser.addItemDecoration(itemDecoration)
    }

    interface ActionConfirmListener {
        fun confirm()
        fun cancel()
    }

    interface OptionMenuClickListener {
        fun update(user: User)
        fun delete(user: User)
        fun viewDetail(user: User)
    }
}