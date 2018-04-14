package com.jxhem.uicentric.ui.views.mainview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jxhem.uicentric.R
import com.jxhem.uicentric.datalayer.net.Model

class CommentsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.name)
    private val email: TextView = view.findViewById(R.id.email)

    private var comment: Model.Comment? = null

    fun bind(comment: Model.Comment) {
        this.comment = comment
        name.text = comment.name
        email.text = comment.email
    }

    fun setItemListener(itemListener: ItemListener) {
        view.setOnClickListener {
            itemListener.onItemSelected(comment?.id!!)
        }
    }

    companion object {
        fun create(parent: ViewGroup): CommentsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.comment_item,
                    parent,
                    false)
            return CommentsViewHolder(view)
        }
    }

}
