package com.ipsoft.crudapplication.ui.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.ipsoft.crudapplication.R
import com.ipsoft.crudapplication.databinding.NewTaskFragmentBinding
import com.ipsoft.crudapplication.datasource.model.Task
import com.ipsoft.crudapplication.utils.extensions.format
import com.ipsoft.crudapplication.utils.extensions.text
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NewTaskFragment : Fragment() {

    private var _binding: NewTaskFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: NewTaskFragmentArgs by navArgs()
    private val newTaskViewModel: NewTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (args.taskId >= 0) {
            configFields(newTaskViewModel.getTaskById(args.taskId))
            binding.toolbarBackButton.setTitle(R.string.task_edit)
            binding.btnDeleteTask.visibility = View.VISIBLE
            binding.btnCreateTask.visibility = View.GONE
        }
        setScreen()
        setListeners()
    }

    private fun configFields(taskById: Task) {

        binding.edtTitle.setText(taskById.title)
        binding.edtDesc.setText(taskById.description)
        binding.edtDate.setText(taskById.date)
        binding.edtTime.setText(taskById.time)

    }


    private fun setScreen() {

        binding.toolbarBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setListeners() {


        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            val timeZone = TimeZone.getDefault()
            val offset = timeZone.getOffset(Date().time) * -1
            datePicker.addOnPositiveButtonClickListener {
                binding.tilDate.text = Date(it + offset).format()

            }
            activity?.supportFragmentManager?.let { supportFragmentManager ->
                datePicker.show(
                    supportFragmentManager,
                    "DATE_PICKER_TAG"
                )

            }
        }
        binding.tilTime.editText?.setOnClickListener {

            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {

                val hour =
                    if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute =
                    if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.tilTime.text = "${hour}:${minute}"

            }
            activity?.supportFragmentManager?.let { supportFragmentManager ->
                timePicker.show(supportFragmentManager, null)
            }
        }
        binding.btnCancelTask.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnCreateTask.setOnClickListener {
            if (binding.tilTitle.text.isNotBlank()) {
                if (args.taskId >= 0) {
                    updateTask()
                } else {
                    saveTask()
                }
            }
        }
        binding.btnDeleteTask.setOnClickListener {
            deleteTask()
        }


    }

    private fun updateTask() {

        val task = Task(
            id = args.taskId,
            title = binding.edtTitle.text.toString(),
            description = binding.edtDesc.text.toString(),
            date = binding.edtDate.text.toString(),
            time = binding.edtTime.text.toString()
        )
        newTaskViewModel.updateTask(task)
        Toast.makeText(requireContext(), "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()

    }

    private fun saveTask() {

        val task = Task(
            title = binding.edtTitle.text.toString(),
            description = binding.edtDesc.text.toString(),
            date = binding.edtDate.text.toString(),
            time = binding.edtTime.text.toString()
        )

        newTaskViewModel.addTask(task)
        Toast.makeText(requireContext(), "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()

    }

    private fun deleteTask() {

        val task = Task(
            id = args.taskId,
            title = binding.edtTitle.text.toString(),
            description = binding.edtDesc.text.toString(),
            date = binding.edtDate.text.toString(),
            time = binding.edtTime.text.toString()
        )
        newTaskViewModel.deleteTask(task)
        Toast.makeText(requireContext(), "Tarefa deletada com sucesso", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}