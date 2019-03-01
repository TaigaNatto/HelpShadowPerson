package natto.com.helpshadowperson.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import natto.com.helpshadowperson.ChatListDeserializer
import natto.com.helpshadowperson.R
import natto.com.helpshadowperson.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    lateinit var adapter: ChatAdapter

    private val GET_URL =
        "https://webs-hack-gahaku-gahaku.c9users.io/response"

    private val POST_URL =
        "https://webs-hack-gahaku-gahaku.c9users.io/request"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)

        context?.let {
            adapter = ChatAdapter(it)
//            adapter.messageAdd(ChatMessage(true, "", "しりとりしよう！", "", ""))
//            for (i in 0..20) {
//                adapter.messageAdd(ChatMessage(false, "さいとう", "トンボ", "", ""))
//                adapter.messageAdd(ChatMessage(true, "", "ボルト", "", ""))
//            }
            binding.listChat.adapter = adapter
        }
        binding.listChat.setSelection(adapter.count)

        binding.editMessage.onFocusChangeListener = View.OnFocusChangeListener { view, isFocus ->
            if (isFocus) {
                binding.btnCamera.visibility = View.GONE
                binding.btnPicture.visibility = View.GONE
                binding.btnMic.visibility = View.GONE
                binding.btnUp.setImageResource(R.drawable.ic_send_blue_24dp)
            } else {
                binding.btnCamera.visibility = View.VISIBLE
                binding.btnPicture.visibility = View.VISIBLE
                binding.btnMic.visibility = View.VISIBLE
                binding.btnUp.setImageResource(R.drawable.ic_thumb_up_glay_24dp)
            }
        }

        binding.btnUp.setOnClickListener {
            val text = binding.editMessage.text.toString()
            if (binding.editMessage.isFocusable && text.isNotEmpty()) {

                val messege = ChatMessage(
                    -1,
                    "me",
                    text,
                    "me",
                    1
                )

                adapter.messageAdd(messege)
                adapter.notifyDataSetChanged()
                binding.listChat.setSelection(adapter.count)

                val adapter = Moshi.Builder().build().adapter(ChatMessage::class.java)
                val jsonText = adapter.toJson(messege)

                val header: HashMap<String, String> = hashMapOf("Content-Type" to "application/json")
                val s = POST_URL.httpPost().header(header).body(jsonText)
                    .responseObject(ChatListDeserializer()) { request, response, result ->
                        when (result) {
                            is Result.Success -> {
                                println(response.toString())
                            }
                            is Result.Failure -> {
                                println(response.toString())
                            }
                        }
                        binding.editMessage.setText("")
                    }
            }
        }

        // 非同期処理
        GET_URL.httpGet().responseObject(ChatListDeserializer())
        { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val (list, err) = result
                    list?.let {
                        adapter.clear()
                        for (item in it) {
                            adapter.messageAdd(item)
                        }
                        binding.listChat.post {
                            // ここに処理
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
                is Result.Failure -> {
                    println("通信に失敗しました。")
                }
            }
        }
        return binding.root
    }
}