package com.example.babbelwordgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.babbelwordgame.Objects.Response
import com.example.babbelwordgame.Objects.ResponseModel
import com.example.babbelwordgame.databinding.ResultScreenBinding

class Results: Fragment() {
    private var _binding: ResultScreenBinding? = null
    private val binding get() = _binding!!


    private val _responses: ResponseModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ResultScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultsTable.weightSum = (_responses.size()+0.5).toFloat()
        Thread(Runnable {
            for(resp in _responses.responses) {
                writeResult(view, resp)
            }
        }).start()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun writeResult(view: View, response: Response) {
        val row = layoutInflater.inflate(R.layout.result_screen_row, binding.resultsTable, false)
        row.findViewById<TextView>(R.id.original).text = response.correct.text_eng
        row.findViewById<TextView>(R.id.chosen).text = response.possible.text_spa
        row.findViewById<TextView>(R.id.correct).text = response.correct.text_spa
        row.findViewById<TextView>(R.id.answer).text = if(response.answer==true) "Right" else if(response.answer==false) "Wrong" else "No answer"
        if (response.isCorrect()){
            row.setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
        } else {
            row.setBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
        }

        binding.resultsTable.post {
            binding.resultsTable.addView(row)
        }
    }
}
