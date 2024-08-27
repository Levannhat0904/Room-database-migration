package net.braniumacademy.lesson12_11.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import net.braniumacademy.lesson12_11.ui.MainActivity
import net.braniumacademy.lesson12_11.R

class DeleteDialogFragment(
    private val listener: MainActivity.ActionConfirmListener
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage(getString(R.string.text_confirm_delete))
        dialog.setPositiveButton(getString(R.string.text_confirm)) { _, _ ->
            listener.confirm()
        }
        dialog.setNegativeButton(getString(R.string.text_cancel)) { _, _ ->
            listener.cancel()
        }
        return dialog.create()
    }
}