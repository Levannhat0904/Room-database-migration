package net.braniumacademy.lesson12_11.factory

import android.content.Context
import android.content.Intent
import net.braniumacademy.lesson12_11.data.model.User
import net.braniumacademy.lesson12_11.ui.detail.UserDetailActivity
import net.braniumacademy.lesson12_11.ui.newuser.NewUserActivity
import net.braniumacademy.lesson12_11.ui.updateuser.UserUpdateActivity

class IntentFactoryImpl<T>(private val context: Context) : IntentFactory<T> {
    override fun createIntent(actionType: IntentActionType, obj: T?): Intent {
        return when(actionType) {
            IntentActionType.CREATE -> createIntentToAddNew()
            IntentActionType.EDIT -> createIntentToUpdate(obj)
            IntentActionType.VIEW_DETAIL -> createIntentToViewDetail(obj)
        }
    }

    private fun createIntentToAddNew(): Intent {
        return Intent(context, NewUserActivity::class.java)
    }

    private fun createIntentToUpdate(obj: T?): Intent {
        return Intent(context, UserUpdateActivity::class.java).apply {
            val user = obj as? User
            putExtra(UserUpdateActivity.EXTRA_ID, user?.id)
        }
    }

    private fun createIntentToViewDetail(obj: T?): Intent {
        return Intent(context, UserDetailActivity::class.java).apply {
            val user = obj as? User
            putExtra(UserDetailActivity.EXTRA_ID, user?.id)
        }
    }
}