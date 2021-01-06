package com.sano.skyengpet.presentation

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sano.skyengpet.R
import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.domain.model.Translation
import com.sano.skyengpet.presentation.viewmodel.MainIntent
import com.sano.skyengpet.presentation.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

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

        initializeObservers()

        Toast.makeText(context, "onViewCreated", Toast.LENGTH_LONG).show()
    }

    private fun initializeObservers() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            if(it.isProgressIndicator) {
                progressBar.show()
            } else {
                progressBar.hide()
            }

            when(it.intentId) {
                MainInteractor.searchWordIntentId -> {
                    if(it.isFailed) {
                        Toast.makeText(context, getString(R.string.smth_went_wrong_label), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            translation.text = viewState.translation?.word
            translatedWordsAdapter.setItems(viewState.translatedWords)
        }
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }
}

data class MainViewState(
    val searchWord: String? = null,
    val translation: Translation? = null,
    val translatedWords: List<String>? = null
)