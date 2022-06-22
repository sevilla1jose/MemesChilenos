package com.jsevilla.memeschilenos.feature.adapter

import android.content.Context
import android.view.LayoutInflater
import com.jsevilla.memeschilenos.BR
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.ItemLayoutMemesBinding
import com.jsevilla.memeschilenos.domain.entity.ChildrenEntity

class RvAdapterListMemes(
    private val list: MutableList<ChildrenEntity>,
    private val c: Context
) : RecyclerView.Adapter<RvAdapterListMemes.ListMemesHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMemesHomeViewHolder {
        val binding: ItemLayoutMemesBinding? = DataBindingUtil.bind(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_layout_memes,
                    parent,
                    false
                )
        )
        return ListMemesHomeViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ListMemesHomeViewHolder, position: Int) =
        holder.setItem(list = list[position])

    override fun getItemCount(): Int = list.size

    inner class ListMemesHomeViewHolder(private val itemList: ItemLayoutMemesBinding) :
        RecyclerView.ViewHolder(itemList.root) {
        fun setItem(list: ChildrenEntity) {
            itemList.imgUrl.load(list.data.url)
            itemList.txtTitleChildren.text = list.data.title
            itemList.txtCommentsChildren.text =
                c.getString(R.string.txtCommentsChildren, list.data.numComments)
            itemList.txtScoreChildren.text =
                c.getString(R.string.txtScoreChildren, list.data.score)
        }
    }

    fun update(listChildren: MutableList<ChildrenEntity>) {
        list.clear()
        list.addAll(listChildren)
        notifyDataSetChanged()
    }
}
