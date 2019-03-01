package natto.com.helpshadowperson.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import natto.com.helpshadowperson.R

class FriendsAdapter(context: Context) : ArrayAdapter<Friend>(context,
    R.layout.layout_list_card
) {
    private val list: ArrayList<Friend> = ArrayList()
    private val inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View

        if (convertView != null) {
            view = convertView
        } else {
            view = inflator.inflate(R.layout.layout_list_card, null)
        }

        // リストビューに表示する要素を取得
        val item = list[position]

        val nameView = view.findViewById<TextView>(R.id.name_profile)
        nameView.text = item.name

        val subView = view.findViewById<TextView>(R.id.sub_chat)
        subView.text = item.newestMessage

        val dateView = view.findViewById<TextView>(R.id.date_chat)
        dateView.text = item.newestDate

        return view
    }

    override fun getCount(): Int {
        return list.size
    }

    fun friendAdd(item: Friend) {
        list.add(item)
    }
}