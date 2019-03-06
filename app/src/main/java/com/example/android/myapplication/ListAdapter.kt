package com.example.android.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.databinding.ItemStringBinding

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, data:List<ListItem>?)
{
    val adapter = recyclerView.adapter
    if(adapter is StringListAdapter) {
        adapter.setData(data)
    }
}


class StringListAdapter(context: Context) : ListAdapter<ListItem, StringHolder>(diffCallback)
{
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringHolder {
        return StringHolder(ItemStringBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: StringHolder, position: Int) {
        holder.setText(getItem(position))
    }

    fun setData(data:List<ListItem>?)
    {
        submitList(data)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem.data == newItem.data

            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
        }
    }
}

class StringHolder(private val itemStringBinding: ItemStringBinding) : RecyclerView.ViewHolder(itemStringBinding.root)
{
    fun setText(item:ListItem)
    {
        itemStringBinding.textView.text = item.data
    }
}