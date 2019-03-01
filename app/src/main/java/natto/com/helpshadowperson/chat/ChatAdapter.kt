package natto.com.helpshadowperson.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import natto.com.helpshadowperson.R

class ChatAdapter(context: Context) : ArrayAdapter<ChatMessage>(context,
    R.layout.layout_chat_me
) {
    private var list: ArrayList<ChatMessage> = ArrayList()
    private val inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View

        val item = list[position]

//        if (convertView != null) {
//            view = convertView
//        } else {
        if (item.sender==1) {
            view = inflator.inflate(R.layout.layout_chat_me, null)
        } else {
            view = inflator.inflate(R.layout.layout_chat_other, null)

            val nameView = view.findViewById<TextView>(R.id.name_text)
            nameView.text = item.name
//            val imgProfile = view.findViewById<CircleImageView>(R.id.message_sender_img)
//            imgProfile.text = item.newestDate
        }
        //}

        val messageView = view.findViewById<TextView>(R.id.message_text)
        messageView.text = item.messages

        return view
    }

    override fun getCount(): Int {
        return list.size
    }

    fun messageAdd(item: ChatMessage) {
        list.add(item)
    }

    fun setList(list:ArrayList<ChatMessage>){
        this.list=list
    }
}