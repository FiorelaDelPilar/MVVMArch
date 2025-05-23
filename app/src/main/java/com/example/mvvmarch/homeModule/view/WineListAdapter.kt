package com.example.mvvmarch.homeModule.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarch.BR
import com.example.mvvmarch.R
import com.example.mvvmarch.common.utils.OnClickListener
import com.example.mvvmarch.common.entities.Wine
import com.example.mvvmarch.databinding.ItemWineBinding

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
open class WineListAdapter : ListAdapter<Wine, RecyclerView.ViewHolder>(WineDiff()) {

    lateinit var listener: OnClickListener
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wine, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wine = getItem(position)
        (holder as ViewHolder).run {
            setListener(wine)
            binding?.setVariable(BR.wine, wine)
            binding?.executePendingBindings()
        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val binding = ItemWineBinding.bind(view)
        val binding = DataBindingUtil.bind<ItemWineBinding>(view) //inflar la vista

        fun setListener(wine: Wine) {
            binding?.root?.setOnLongClickListener {
                listener.onLongClick(wine)
                true
            }
            binding?.cbFavorite?.setOnClickListener {
                listener.onFavorite(wine)
            }
        }
    }

    private class WineDiff : DiffUtil.ItemCallback<Wine>() {
        override fun areItemsTheSame(oldItem: Wine, newItem: Wine) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Wine, newItem: Wine) = oldItem == newItem
    }
}