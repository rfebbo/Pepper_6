package com.example.pepper_6

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.conversation.BaseChatbotReaction
import com.aldebaran.qi.sdk.`object`.conversation.SpeechEngine

class EmptyChatbotReaction internal constructor(context: QiContext)
    : BaseChatbotReaction(context) {

    override fun runWith(speechEngine: SpeechEngine) {}
    override fun stop() {}
}