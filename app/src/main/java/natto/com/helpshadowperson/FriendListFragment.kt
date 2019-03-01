package natto.com.helpshadowperson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import natto.com.helpshadowperson.databinding.FragmentFriendListBinding


class FriendListFragment : Fragment() {

    lateinit var adapter: FriendsAdapter
    private lateinit var binding: FragmentFriendListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friend_list, container, false)

        context?.let {
            adapter = FriendsAdapter(it)

            for (i in 0..20) {
                adapter.friendAdd(Friend("田中", ""))
                adapter.friendAdd(Friend("佐藤", ""))
                adapter.friendAdd(Friend("ゴロー", ""))
            }

            binding.listFriends.adapter = adapter
        }

        binding.listFriends.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, pos, long ->
            Navigation.findNavController(view).navigate(R.id.action_friendListFragment_to_chatFragment)
        }

        return binding.root
    }
}
