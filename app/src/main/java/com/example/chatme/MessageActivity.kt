package com.example.chatme

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chat.models.Group
import com.cometchat.chat.models.User
import com.cometchat.chatuikit.messagecomposer.CometChatMessageComposer
import com.cometchat.chatuikit.messageheader.CometChatMessageHeader
import com.cometchat.chatuikit.messagelist.CometChatMessageList

class MessageActivity : AppCompatActivity() {
    private lateinit var messageHeader: CometChatMessageHeader
    private lateinit var messageList: CometChatMessageList
    private lateinit var messageComposer: CometChatMessageComposer

    private var uid: String? = null
    private var guid: String? = null

    private val TAG = "MessageActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_message)

        initializeViews()
        setupChat()
        setupHeaderBackButton()
    }

    private fun initializeViews() {
        messageHeader = findViewById(R.id.message_header)
        messageList = findViewById(R.id.message_list)
        messageComposer = findViewById(R.id.message_composer)
    }

    private fun setupChat() {
        uid = intent.getStringExtra("uid")
        guid = intent.getStringExtra("guid")

        when {
            uid != null -> setupUserChat(uid!!)
            guid != null -> setupGroupChat(guid!!)
            else -> {
                Log.e(TAG, "No user ID or group ID provided")
                showError("Missing user ID or group ID")
                finish()
            }
        }
    }

    private fun setupUserChat(userId: String) {
        CometChat.getUser(userId, object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User) {
                Log.d(TAG, "Successfully loaded user: ${user.uid}")
                messageHeader.setUser(user)
                messageList.setUser(user)
                messageComposer.setUser(user)
            }

            override fun onError(e: CometChatException?) {
                Log.e(TAG, "Error loading user: ${e?.message}")
                showError("Could not find user: ${e?.message}")
                finish()
            }
        })
    }

    private fun setupGroupChat(groupId: String) {
        CometChat.getGroup(groupId, object : CometChat.CallbackListener<Group>() {
            override fun onSuccess(group: Group) {
                Log.d(TAG, "Successfully loaded group: ${group.guid}")
                messageHeader.setGroup(group)
                messageList.setGroup(group)
                messageComposer.setGroup(group)
            }

            override fun onError(e: CometChatException?) {
                Log.e(TAG, "Error loading group: ${e?.message}")
                showError("Could not find group: ${e?.message}")
                finish()
            }
        })
    }

    private fun setupHeaderBackButton() {
        messageHeader.setOnBackButtonPressed {
            finish()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}