package com.sano.skyengpet.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sano.skyengpet.R
import com.sano.skyengpet.presentation.state.MainViewScreenState
import com.sano.skyengpet.presentation.viewmodel.MainIntent
import com.sano.skyengpet.presentation.viewmodel.MainViewModel

internal class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var translateBtn: Button
    private lateinit var wordToTranslate: EditText
    private lateinit var translation: TextView
    private lateinit var translatedWordsRecyclerView: RecyclerView
    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var translatedWordsAdapter: TranslatedWordsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translateBtn = view.findViewById(R.id.translate_btn)
        wordToTranslate = view.findViewById(R.id.word_to_translate_et)
        translation = view.findViewById(R.id.translation_tv)
        translatedWordsRecyclerView = view.findViewById(R.id.translations_rv)
        progressBar = view.findViewById(R.id.progress_bar)

        translatedWordsAdapter = TranslatedWordsAdapter()
        translatedWordsRecyclerView.layoutManager = LinearLayoutManager(context)
        translatedWordsRecyclerView.adapter = translatedWordsAdapter

        translateBtn.setOnClickListener {
            viewModel.process(MainIntent.Translate(wordToTranslate.text.toString()))
        }

        subscribeToStateChanges()

        Toast.makeText(context, "onViewCreated", Toast.LENGTH_LONG).show()
    }

    private fun subscribeToStateChanges() {
        viewModel.subscribeToScreenStateChanges().observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is MainViewScreenState.Translated -> {
                    showProgress(progressIsVisible = false)
                    translation.text = screenState.translation.word
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
        if(error == null || error is NoSuchElementException) {
            Toast.makeText(context, getString(R.string.word_not_found), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, getString(R.string.smth_went_wrong_label), Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgress(progressIsVisible: Boolean) {
        if (progressIsVisible) {
            progressBar.show()
        } else {
            progressBar.hide()
        }
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }
}