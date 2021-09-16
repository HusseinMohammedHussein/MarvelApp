package com.husseinmohammed.marvelapp.ui.fragments.chractersearch

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.husseinmohammed.marvelapp.databinding.FragmentCharactersSearchBinding

class CharactersSearchFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCharactersSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharactersSearchViewModel

    companion object {
        fun newInstance(): CharactersSearchFragment {
            return CharactersSearchFragment()
        }
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnShowListener { dialogInterface ->
//            val bottomSheetDialog = dialogInterface as BottomSheetDialog
//            setFullHeight(bottomSheetDialog)
//        }
//        return dialog
//    }

    private fun setFullHeight(bottomSheetDialog: BottomSheetDialog) {

//        val layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams

//        val windowHeight = getWindowHeight()
//        layoutParams.height = windowHeight
//        bottomSheet.layoutParams = layoutParams
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        val behavior = BottomSheetBehavior.from(binding.clBottomsheet.root)
        behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isDraggable = false
    }
}