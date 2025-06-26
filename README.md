
# ☄️ChatMe - CometChat Android App

ChatMe is an Android messaging app that uses the [CometChat UIKit](https://www.cometchat.com/pro) to provide real-time **group chat**, **private chat**, and **audio/video calling** functionality with support for UI customization and user authentication.

## ✨ Features

- ✅ Login & Create Users
- ✅ Group Messaging
- ✅ One-to-One (Private) Messaging
- ✅ Audio & Video Calling (via CometChat UIKit)
- ✅ Custom Chat Bubble Colors (Incoming/Outgoing)
- ✅ Custom Backgrounds for Message List, Header, and Composer
- ✅ Bottom Navigation for Chats, Users, and Groups
- ✅ Tap on a user to start a private chat
- ✅ Modify Footer Views (e.g., Notes, Saved Links)

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/chatme.git
cd chatme
```

### 2. Create a CometChat App

- Go to [CometChat Dashboard](https://app.cometchat.com/)
- Create a new app
- Get the following:
  - `App ID`
  - `Region`
  - `Auth Key`
  - `API Key`

### 3. Add Dependencies

In your `app/build.gradle`:

```gradle
dependencies {
    implementation 'com.cometchat:chat-uikit-android:<latest_version>'
}
```

Check the latest version [here](https://github.com/cometchat-pro/android-ui-kit).

## 🔌 Enable Extensions in CometChat

To enable Audio/Video Calling:

1. Go to **CometChat Dashboard**
2. Navigate to **Extensions**
3. Enable:
   - **Audio Calling**
   - **Video Calling**

## 🎨 Customization

### Message Bubble Colors (in `res/values/styles.xml`)

```xml
<style name="CustomOutgoingMessageBubbleStyle" parent="CometChatOutgoingMessageBubbleStyle">
    <item name="cometchatMessageBubbleBackgroundColor">#9966FF</item>
</style>

<style name="CustomIncomingMessageBubbleStyle" parent="CometChatIncomingMessageBubbleStyle">
    <item name="cometchatMessageBubbleBackgroundColor">#383838</item>
</style>

<style name="CustomCometChatMessageListStyle" parent="CometChatMessageListStyle">
    <item name="cometchatMessageListBackgroundColor">#121212</item>
    <item name="cometchatMessageListOutgoingMessageBubbleStyle">@style/CustomOutgoingMessageBubbleStyle</item>
    <item name="cometchatMessageListIncomingMessageBubbleStyle">@style/CustomIncomingMessageBubbleStyle</item>
</style>
```

Apply in layout XML:

```xml
<com.cometchat.chatuikit.messagelist.CometChatMessageList
    ...
    style="@style/CustomCometChatMessageListStyle" />
```

## 📱 Navigation

- `TabbedActivity` - hosts Bottom Navigation (`Chats`, `Users`, `Groups`)
- `MessageActivity` - shows the CometChatMessageHeader, CometChatMessageList, and Composer
- `ConversationActivity` - shows list of 1:1 chats

## 🧪 Testing

You can login as test users like:

```kotlin
val uid = "user1"
val name = "Alice"
val authKey = "YOUR_AUTH_KEY"
```

Join a group:

```kotlin
CometChat.joinGroup("group_id", CometChatConstants.GROUP_TYPE_PUBLIC, "", callback)
```

## 📄 License

This project is MIT licensed.

## 🙋‍♂️ Support

For issues or feature requests, create an issue on the GitHub repository or contact [CometChat support](https://support.cometchat.com).
