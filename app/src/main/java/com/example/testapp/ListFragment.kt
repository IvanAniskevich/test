package com.example.testapp

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.databinding.ListFragmentBinding


class ListFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()
    { ItemViewModelFactory((activity?.application as BaseApplication).repository) }
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.invalidateOptionsMenu()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           R.id.settings -> findNavController().navigate(R.id.action_listFragment_to_setingFragment)
            R.id.home -> activity?.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.findViewById<TextView>(R.id.toolbar_title)?.text = "Курсы валют"
        viewModel.listOfItem.observe(this.viewLifecycleOwner, { list ->
            list.let { adapter.setList(list) }
        })
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.today.text =viewModel.getFirstDate()
        binding.tomorrow.text =viewModel.getSecondDate()

    }
}