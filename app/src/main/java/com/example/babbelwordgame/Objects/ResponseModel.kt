package com.example.babbelwordgame.Objects

import androidx.lifecycle.ViewModel


data class Response(val possible: Translation, val correct: Translation, val answer: Boolean?){
    fun isCorrect() : Boolean {
        return if (answer == true) possible == correct else possible != correct
    }
}

class ResponseModel : ViewModel() {
    var responses : MutableList<Response> = mutableListOf<Response>()

    fun add_response(response: Response) {
        responses.add(response)
    }

    fun size(): Int{
        return responses.size
    }

    fun reset_responses(){
        responses = mutableListOf<Response>()
    }
}