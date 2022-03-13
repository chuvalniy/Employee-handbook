package com.example.kode_test_app.presentation.user_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

class MainScreenDialogFragment : DialogFragment() {

    private val options = arrayOf("By alphabet", "By age")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(options, 0) { _, i ->
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(KEY_RESPONSE to i)
                )
                dismiss()
            }
            .create()
    }

    companion object {
        @JvmStatic
        private val TAG = MainScreenDialogFragment::class.java.simpleName

        @JvmStatic
        private val REQUEST_KEY = "$TAG:defaultRequestKey"

        @JvmStatic
        private val KEY_RESPONSE = "RESPONSE"

        fun show(manager: FragmentManager) {
            val dialogFragment = MainScreenDialogFragment()
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Int) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner
            ) { _, result ->
                listener.invoke(result.getInt(KEY_RESPONSE))
            }
        }
    }
}


