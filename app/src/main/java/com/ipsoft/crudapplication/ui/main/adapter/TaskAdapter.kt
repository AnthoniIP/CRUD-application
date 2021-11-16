package com.ipsoft.crudapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ipsoft.crudapplication.databinding.RowTaskBinding
import com.ipsoft.crudapplication.datasource.model.Task
import com.ipsoft.crudapplication.ui.main.MainFragmentDirections


class TasksAdapter(
    private val tasks: List<Task>
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RowTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val task = tasks[position]

        holder.taskName.text = task.title
        holder.taskDesc.text = task.description
        holder.taskDate.text = task.date
        holder.taskTime.text = task.time

        holder.row.setOnClickListener {
            //val task = task.id?.let { it1 -> TestDataSource.getTaskById(it1) }
            val navController: NavController?
            navController = Navigation.findNavController(holder.itemView)
            val action = MainFragmentDirections.actionMainFragmentToNewTaskFragment(task.id!!)
            navController.navigate(action)


        }


    }

    override fun getItemCount() = tasks.size

    inner class ViewHolder(itemView: RowTaskBinding) : RecyclerView.ViewHolder(itemView.root) {

        val row: ConstraintLayout = itemView.rowTask


        val taskName: TextView = itemView.txvName
        val taskDesc: TextView = itemView.txvDescription
        val taskDate: TextView = itemView.txvDate
        val taskTime: TextView = itemView.txvTime

    }
}