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
                adapter.messageAdd(ChatMessage(false, "さいとう", "トンボ", "", ""))
                adapter.messageAdd(ChatMessage(true, "", "ボルト", "", ""))
            }
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
            val text = binding.editMessage.text
            if (binding.editMessage.isFocusable && text.toString().isNotEmpty()) {
                adapter.messageAdd(ChatMessage(true, "", text.toString(), "", ""))
                adapter.notifyDataSetChanged()
                binding.listChat.setSelection(adapter.count)

                binding.editMessage.setText("")
            }
        }
        return binding.root
    }
}