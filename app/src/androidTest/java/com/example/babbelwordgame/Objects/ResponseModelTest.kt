package com.example.babbelwordgame.Objects

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ResponseModelTest {

    private lateinit var response: ResponseModel
    private lateinit var translation1: Translation
    private lateinit var translation2: Translation

    @Before
    fun setUp() {
        response  = ResponseModel()
        translation1 = Translation("t", "t2")
        translation2 = Translation("t3", "t4")

    }

    @Test
    fun getResponses() {
        assertNotNull(response.responses)
    }

    @Test
    fun setResponses() {
        response.responses = mutableListOf<Response>(Response(translation1, translation2, null))
        assertEquals(response.responses, mutableListOf<Response>(Response(translation1, translation2, null)))
    }

    @Test
    fun add_response() {
        response.add_response(Response(translation1, translation2, null))
        assertEquals(response.responses, mutableListOf<Response>(Response(translation1, translation2, null)))

    }

    @Test
    fun size() {
        for (i in (0..10)) {
            assertEquals(response.size(),i)
            response.add_response(Response(translation1, translation2, null))

        }
        assertEquals(response.size(), response.responses.size)
        assertEquals(response.size(), 11)
    }

    @Test
    fun reset_responses() {
        for (i in (0..10)) {
            assertEquals(response.size(),i)
            response.add_response(Response(translation1, translation2, null))
        }
        val s = response.size()
        response.reset_responses()
        assertNotEquals(s, response.size())
        assertEquals(response.size(), 0)
    }
}