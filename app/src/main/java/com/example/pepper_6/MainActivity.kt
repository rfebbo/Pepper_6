package com.example.pepper_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Chat
import com.aldebaran.qi.sdk.builder.ChatBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity


class MainActivity : RobotActivity(), RobotLifecycleCallbacks {
    private lateinit var chat : Chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this)
    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        // The robot focus is gained.
        val credentials = applicationContext.resources.openRawResource(R.raw.credentials)
        val dialogflowChatbot = DialogflowChatbot(qiContext, credentials)
        chat = ChatBuilder.with(qiContext).withChatbot(dialogflowChatbot).build()
        chat.async().run()
    }

    override fun onRobotFocusLost() {
        // The robot focus is lost.
    }

    override fun onRobotFocusRefused(reason: String) {
        // The robot focus is refused.
    }
}

