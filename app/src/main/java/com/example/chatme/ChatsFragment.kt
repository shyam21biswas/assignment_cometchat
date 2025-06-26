package com.example.chatme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cometchat.chat.constants.CometChatConstants
import com.cometchat.chat.models.Conversation
import com.cometchat.chat.models.Group
import com.cometchat.chat.models.User
import com.cometchat.chatuikit.conversations.CometChatConversations

class ChatsFragment : Fragment() {

    private lateinit var conversationsView: CometChatConversations

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_chats, container, false)

        conversationsView = view.findViewById(R.id.conversations_view)

        conversationsView.setOnItemClick { _, _, conversation ->
            val intent = Intent(requireContext(), MessageActivity::class.java)

            when (conversation.conversationType) {
                CometChatConstants.CONVERSATION_TYPE_USER -> {
                    val user = conversation.conversationWith as User
                    intent.putExtra("uid", user.uid)
                }

                CometChatConstants.CONVERSATION_TYPE_GROUP -> {
                    val group = conversation.conversationWith as Group
                    intent.putExtra("guid", group.guid)
                }
            }

            startActivity(intent)
        }

        return view
    }
}
