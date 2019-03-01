package natto.com.helpshadowperson.chat

class ChatMessage(
    val isMe: Boolean,
    val name: String,
    val message: String,
    val imageUrl: String,
    val date: String
) {
}