package com.example.pepper_6

import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.conversation.BaseChatbot
import com.aldebaran.qi.sdk.`object`.conversation.Phrase
import com.aldebaran.qi.sdk.`object`.conversation.ReplyPriority
import com.aldebaran.qi.sdk.`object`.conversation.StandardReplyReaction
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.softbanksrobotics.jokeswithdialogflow.data.DialogFlowDataSource
import java.io.InputStream
import java.util.*

class DialogflowChatbot internal constructor(context: QiContext,
                                             credentialsStream : InputStream
)
    : BaseChatbot(context) {
    companion object {
        private val TAG = "DialogflowChatbot"
    }
    private var dialogflowSessionId = "chatbot-" + UUID.randomUUID().toString()
    private val dataSource = DialogFlowDataSource(credentialsStream)

    override fun replyTo(phrase: Phrase, locale: Locale): StandardReplyReaction {
        val input = phrase.text.toString()
        val language = locale.language.toString()
        var answer : String? = null
        try {
            answer = dataSource.detectIntentTexts(input, dialogflowSessionId, language)
            Log.i(TAG, "Got answer: '$answer'")
        } catch (e: Exception) {
            Log.e(TAG, "error", e)
        }
        return if (answer != null) {
            StandardReplyReaction(
                SimpleSayReaction(qiContext, answer), ReplyPriority.NORMAL
            )
        } else {
            StandardReplyReaction(
                EmptyChatbotReaction(qiContext), ReplyPriority.FALLBACK
            )
        }
    }
}