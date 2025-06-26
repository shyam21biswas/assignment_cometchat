package com.example.chatme

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cometchat.chat.constants.CometChatConstants
import com.cometchat.chat.core.UsersRequest
import com.cometchat.chat.models.User
import com.cometchat.chatuikit.databinding.CometchatListBaseItemsBinding
import com.cometchat.chatuikit.shared.viewholders.UsersViewHolderListener
import com.cometchat.chatuikit.users.CometChatUsers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UsersFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        val usersView = view.findViewById<CometChatUsers>(R.id.users_view)

        // 🔍 Show only online users
        val builder = UsersRequest.UsersRequestBuilder()
            .setUserStatus(CometChatConstants.USER_STATUS_ONLINE) // Filter online only
        usersView.setUsersRequestBuilder(builder)

        // 🏷Set custom subtitle view
        usersView.setSubtitleView(object : UsersViewHolderListener() {
            override fun createView(
                context: Context?,
                listItem: CometchatListBaseItemsBinding?
            ): View {
                return TextView(context)
            }

            override fun bindView(
                context: Context,
                createdView: View,
                user: User,
                holder: RecyclerView.ViewHolder,
                userList: List<User>,
                position: Int
            ) {
                val tvSubtitle = createdView as TextView
                val sdf = SimpleDateFormat("dd/MM/yyyy, HH:mm:ss", Locale.getDefault())
                val lastActive = user.lastActiveAt * 1000L
                tvSubtitle.text = "Last Active at: ${sdf.format(Date(lastActive))}"
                tvSubtitle.setTextColor(Color.BLACK)
            }
        })

        return view
    }
}