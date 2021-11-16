package com.ipsoft.crudapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipsoft.crudapplication.R
import com.ipsoft.crudapplication.databinding.MainFragmentBinding
import com.ipsoft.crudapplication.ui.main.adapter.TasksAdapter
import com.ipsoft.crudapplication.utils.extensions.changeColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.changeColor(R.color.black)

    }

    private fun setListeners() {
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newTaskFragment)
        }
    }

    private fun setRecycler() {
        binding.rcvTasks.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = TasksAdapter(mainViewModel.getTasks())
        }
    }

}