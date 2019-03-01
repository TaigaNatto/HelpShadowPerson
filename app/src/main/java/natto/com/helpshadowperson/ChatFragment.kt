package natto.com.helpshadowperson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import natto.com.helpshadowperson.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    lateinit var adapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)

        context?.let {
            adapter = ChatAdapter(it)
            adapter.messageAdd(ChatMessage(true, "", "しりとりしよう！", "", ""))
            for (i in 0..20) {
                adapter.messageAdd(ChatMessage(false, "", "トンボ", "", ""))
                adapter.messageAdd(ChatMessage(true, "", "ボルト", "", ""))
            }
            binding.listChat.adapter = adapter
        }
        binding.listChat.setSelection(adapter.count)

        return binding.root
    }
}