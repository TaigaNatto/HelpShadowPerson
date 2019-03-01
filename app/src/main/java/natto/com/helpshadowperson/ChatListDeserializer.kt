package natto.com.helpshadowperson

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import natto.com.helpshadowperson.chat.ChatMessage

class ChatListDeserializer : ResponseDeserializable<List<ChatMessage>> {
    override fun deserialize(content: String): List<ChatMessage>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, ChatMessage::class.java)
        val listAdapter: JsonAdapter<List<ChatMessage>> = moshi.adapter(type)

        return listAdapter.fromJson(content)
    }
}