package com.example.chatme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.util.Log
import androidx.activity.compose.setContent
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
        CometChatUIKit.login("ved21",object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User)
            {



                setContent{
                    //naviagtion control..................
                    AppScaffold(authKey)
                }


            }


            override fun onError(e: CometChatException) {

                // Handle login failure (e.g. show error message or retry)
                Log.e("Login", "Login failed: ${e.message}")

            }
        })
    }
}


