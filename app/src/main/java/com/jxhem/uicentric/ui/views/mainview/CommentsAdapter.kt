package com.jxhem.uicentric.ui.views.mainview

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jxhem.uicentric.datalayer.net.Model

class CommentsAdapter(
        private val itemListener: ItemListener,
        private var comments: List<Model.Comment>
) : RecyclerView.Adapter<CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder.create(parent)
    }

    fun setComments(comments: List<Model.Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments.get(position))
        holder.setItemListener(itemListener)
    }
}
