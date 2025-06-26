package com.example.chatme

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chat.models.User
import kotlinx.coroutines.delay


@Composable
fun AppScaffold(authKey: String) {
    val navController = rememberNavController()
    val items = listOf("home", "chat")

    var selectedItem by remember { mutableStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {

                                navController.navigate(item)
                        },
                        icon = {
                            if (item == "home") Icon(Icons.Default.Home, contentDescription = "Home")
                            else Icon(Icons.Default.Email, contentDescription = "Chat")
                        },
                        label = { Text(item) }
                    )


                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("chat") {
                ChatLauncher(
                    navController,

                    uid = "ved21",
                    authKey = authKey,
                )
            }
        }
    }
}


//add and modify want you want............................
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text("Welcome to DSA App", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Use bottom navigation to join the group chat.")
    }
}



//main chat screen.........................................
@Composable
fun ChatLauncher(
    navController: NavHostController,
    uid: String,
    authKey: String,

    ) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loginOrCreateUserAndJoinGroup(context, uid,  authKey)
    }
    LaunchedEffect(Unit)
            {
            delay(2000)
            navController.popBackStack()

            }

    Text("Joining group chat...", modifier = Modifier.padding(16.dp))

}


fun loginOrCreateUserAndJoinGroup(
    context: Context,
    uid: String,
    authKey: String,

) {
    CometChat.login(uid, authKey, object : CometChat.CallbackListener<User>() {
        override fun onSuccess(user: User?) {

            // Launch Tab-Based Chat Experience (Chats, Calls, Users, Groups)
            val intent = Intent(context, TabbedActivity::class.java)
            context.startActivity(intent)

        }

        override fun onError(e: CometChatException?) {}
    })
}

