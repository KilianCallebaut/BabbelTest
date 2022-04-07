package com.example.babbelwordgame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.babbelwordgame.Objects.Response
import com.example.babbelwordgame.Objects.ResponseModel
import com.example.babbelwordgame.Objects.Translation
import com.example.babbelwordgame.databinding.GamescreenBinding
import com.example.babbelwordgame.databinding.IntroductionBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class GameScreen: Fragment() {

    private var _binding: GamescreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var _word_trans_combos: List<Translation>
    private val _responses: ResponseModel by activityViewModels()

    // variables
    private val chanceToRandom = 0.5

    // game state
    private  var _currentWordId: Int =0
    private  var _currentPossibleId: Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GamescreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _word_trans_combos = readData(view)
        binding.startgamebutton.setOnClickListener{
            startButton(view)
        }
        binding.rightbutton.setOnClickListener {
            responseButton(view, true)
        }
        binding.wrongbutton.setOnClickListener {
            responseButton(view, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // game logic
    private fun readData(view: View): List<Translation> {
        val jsonString = getJsonDataFromAsset(view.context, "wordtranslations.json")
        val gson = Gson()
        val translationListType = object : TypeToken<List<Translation>>(){}.type
        return gson.fromJson(jsonString, translationListType)
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString

    }

    private fun nextWord(view: View) {
        val indexes = fetchWordPairs(view)
        _currentWordId = indexes[0]
        _currentPossibleId = indexes[1]

        drawWords(view, _currentWordId, _currentPossibleId)

    }

    private fun fetchWordPairs(view: View) : List<Int> {
        val random_index = (0.._word_trans_combos.size-1).random()
//        val word_to_translate = _word_trans_combos[random_index]
        val possible_translation_index = if ((1..10).random()<=chanceToRandom*10) random_index else (0.._word_trans_combos.size-1).random()
//        val possible_translation = _word_trans_combos[possible_translation_index]
        return listOf<Int>(random_index, possible_translation_index)
    }

    private fun drawWords(view: View, index_to_translate: Int, index_possible_translation: Int) {
        binding.fallingWord.text = _word_trans_combos[index_possible_translation].text_spa
        binding.toTranslate.text = _word_trans_combos[index_to_translate].text_eng
    }

    // buttons
    private fun startButton(view: View) {
        binding.startgamebutton.visibility = View.GONE
        binding.rightbutton.visibility = View.VISIBLE
        binding.wrongbutton.visibility = View.VISIBLE
        binding.fallingWord.visibility = View.VISIBLE
        nextWord(view)
    }

    private fun responseButton(view: View, right: Boolean) {
        val resp = Response(_word_trans_combos[_currentPossibleId], _word_trans_combos[_currentWordId], right)
        _responses.add_response(resp)
        nextWord(view)
    }

    // move word down
    // timer
    // progress
}