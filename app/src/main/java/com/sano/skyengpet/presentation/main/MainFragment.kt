package com.sano.skyengpet.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sano.skyengpet.R
import com.sano.skyengpet.databinding.FragmentMainBinding
import com.sano.skyengpet.presentation.exhaustive
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val binding: FragmentMainBinding by viewBinding()

    private lateinit var translatedWordsAdapter: TranslatedWordsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translatedWordsAdapter = TranslatedWordsAdapter()
        binding.translationsRv.adapter = translatedWordsAdapter

        binding.translateBtn.setOnClickListener {
            viewModel.process(MainIntent.Translate(binding.wordToTranslateEt.text.toString()))
        }

        subscribeToStateChanges()
    }

    private fun subscribeToStateChanges() {
        viewModel.subscribeToScreenStateChanges().observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is MainViewScreenState.Translated -> {
                    showProgress(progressIsVisible = false)
                    binding.translationTv.text = screenState.translation.word
                    translatedWordsAdapter.setItems(screenState.translatedWords)
                }
                is MainViewScreenState.Loading -> {
                    showProgress(progressIsVisible = true)
                }
                is MainViewScreenState.NotFound -> {
                    showProgress(progressIsVisible = false)
                    showErrorToast()
                }
                is MainViewScreenState.Error -> {
                    showProgress(progressIsVisible = false)
                    showErrorToast(screenState.error)
                }
            }.exhaustive
        }
    }

    private fun showErrorToast(error: Throwable? = null) {
        if (error == null || error is NoSuchElementException) {
            Toast.makeText(context, getString(R.string.word_not_found), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, getString(R.string.smth_went_wrong_label), Toast.LENGTH_LONG)
                    .show()
        }
    }

    private fun showProgress(progressIsVisible: Boolean) {
        if (progressIsVisible) {
            binding.progressBar.show()
        } else {
            binding.progressBar.hide()
        }
    }
}