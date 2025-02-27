package com.example.lab5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.databinding.FragmentCardListBinding


class CardListFragment : Fragment() {
    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CustomRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardListBinding.inflate(layoutInflater, container, false)
        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CustomRecyclerAdapter(action).apply {
            cards = Model.cards
            refreshCardsViewWith(Model.cards)
        }
        recyclerView.adapter = adapter
        binding.addbuttonid.setOnClickListener {
            val action = CardListFragmentDirections.actionCardListFragmentToEditCardFragment("-1")
            findNavController().navigate(action)
        }

        return binding.root
    }

    private val action = object : ActionInterface {
        override fun onItemClick(cardId: String) {
            val action = CardListFragmentDirections
                .actionCardListFragmentToSeeCardFragment(cardId)
            findNavController().navigate(action)
        }

        override fun onDeleteCard(cardId: String) {
            Model.deleteCard(cardId)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
