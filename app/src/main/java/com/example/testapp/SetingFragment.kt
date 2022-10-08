package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.Item
import com.example.testapp.databinding.SetingListBinding

class SetingFragment : Fragment(), SetingAdapter.Visibility, SetingAdapter.OnTouchIcon {
    private val viewModel: ItemViewModel by activityViewModels()
    {
        ItemViewModelFactory((activity?.application as BaseApplication).repository)
    }
    private var _binding: SetingListBinding? = null
    private val binding get() = _binding!!
    val adapter = SetingAdapter(this, this)
    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0
    ) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.invalidateOptionsMenu()
//        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
//        toolbar?.findViewById<TextView>(R.id.toolbar_title)?.text = activity?.actionBar?.title
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                findNavController().navigate(R.id.action_setingFragment_to_listFragment)
                viewModel.update()
            }
        }
        return super.onOptionsItemSelected(item)
    }

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
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.findViewById<TextView>(R.id.toolbar_title)?.text = "Настройка валют"
        viewModel.listOfItem.observe(this.viewLifecycleOwner, { list ->
            list.let { adapter.setList(list) }
        })
        binding.setingrecyclerview.adapter = adapter
        binding.setingrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        itemTouchHelper.attachToRecyclerView(binding.setingrecyclerview)
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