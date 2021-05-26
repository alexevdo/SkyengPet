package com.sano.skyengpet.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sano.skyengpet.R
import com.sano.skyengpet.databinding.FragmentMainBinding
import com.sano.skyengpet.presentation.exhaustive
import com.sano.skyengpet.presentation.main.MainIntent.Translate
import com.sano.skyengpet.presentation.observe
import com.sano.skyengpet.presentation.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val binding: FragmentMainBinding by viewBinding()

    private val progress = binding.pbProgress

    private lateinit var translatedWordsAdapter: TranslatedWordsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translatedWordsAdapter = TranslatedWordsAdapter()
        with(binding) {
            rvTranslations.adapter = translatedWordsAdapter

            btnTranslate.setOnClickListener {
                viewModel.process(
                    Translate(etWordToTranslate.text.toString())
                )
            }
        }

        subscribeToStateChanges()
    }

    private fun subscribeToStateChanges() = observe(viewModel.screenState) {
        when (this) {
            is MainViewScreenState.Translated -> {
                progress.hide()
                binding.tvTranslation.text = translation
                translatedWordsAdapter.setItems(translatedWords)
            }
            is MainViewScreenState.Loading -> {
                progress.show()
            }
            is MainViewScreenState.NotFound -> {
                progress.hide()
                showErrorToast()
            }
            is MainViewScreenState.Error -> {
                progress.hide()
                showErrorToast(error)
            }
        }.exhaustive
    }

    private fun showErrorToast(error: Throwable? = null) = toast(
        if (error == null || error is NoSuchElementException) {
            R.string.word_not_found
        } else {
            R.string.smth_went_wrong_label
        }
    )
}