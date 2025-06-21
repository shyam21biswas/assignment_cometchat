package com.example.chatme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatme.ui.theme.ChatMeTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ChatMeTheme {
//
//            }
//        }
//    }
//}


import android.util.Log

import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chat.models.User
import com.cometchat.chatuikit.shared.cometchatuikit.CometChatUIKit
import com.cometchat.chatuikit.shared.cometchatuikit.UIKitSettings





class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private val appID = "277709b44bcaf7b9" // Replace with your App ID
    private val region = "in" // Replace with your App Region
    private val authKey = "1cb758b88b0b45d0cbfa7dde80e292aee1a694cc" // Replace with your Auth Key or leave blank if you are authenticating using Auth Token

    private val uiKitSettings = UIKitSettings.UIKitSettingsBuilder()
        .setRegion(region)
        .setAppId(appID)
        .setAuthKey(authKey)
        .subscribePresenceForAllUsers()
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        CometChatUIKit.init(this, uiKitSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(successString: String?) {

                Log.d(TAG, "Initialization completed successfully")

                loginUser()
            }

            override fun onError(e: CometChatException?) {}
        })
    }

    private fun loginUser() {
        CometChatUIKit.login("cometchat-uid-1", object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User) {

               // val intent = Intent(this@MainActivity, MessageActivity::class.java)
                // Launch Conversation List + Message View (Split-Screen Style)
               // startActivity(Intent(this@MainActivity, ConversationActivity::class.java))


                 //Launch One-to-One or Group Chat Screen
                val intent = Intent(this@MainActivity, MessageActivity::class.java)
                intent.putExtra("uid", "cometchat-uid-1")
                startActivity(intent)
                startActivity(Intent(this@MainActivity, ConversationActivity::class.java))
//               //  Launch Tab-Based Chat Experience (Chats, Calls, Users, Groups)
                startActivity(Intent(this@MainActivity, TabbedActivity::class.java))



            }

            override fun onError(e: CometChatException) {
                // Handle login failure (e.g. show error message or retry)
                Log.e("Login", "Login failed: ${e.message}")
            }
        })
    }
}


