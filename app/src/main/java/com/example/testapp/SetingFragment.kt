package com.example.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.Item
import com.example.testapp.databinding.SetingListBinding

class SetingFragment: Fragment(),SetingAdapter.Visibility,SetingAdapter.OnTouchIcon {
    private val viewModel : ItemViewModel by activityViewModels()
    private var _binding : SetingListBinding? = null
    private val binding get() = _binding!!
    val adapter = SetingAdapter(this, this)
    val itemTouchHelper =  ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0
    ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            viewModel.muveItem(viewHolder.adapterPosition, target.adapterPosition)
            adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

        override fun isLongPressDragEnabled(): Boolean = false


    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = SetingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listOfItem.observe(this.viewLifecycleOwner, { list ->
            list.let { adapter.setList(list) }
        })

        binding.setingrecyclerview.adapter = adapter
        binding.setingrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        itemTouchHelper.attachToRecyclerView(binding.setingrecyclerview)
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_setingFragment_to_listFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.notifyDataSetChanged()
    }

    override fun visibilityOn(item: Item) {
        viewModel.visabilityOn(item)
    }

    override fun visibilityOff(item: Item) {
        viewModel.visabilityOff(item)
    }

    override fun onTouch(viewHolder: SetingAdapter.SetingViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }


}