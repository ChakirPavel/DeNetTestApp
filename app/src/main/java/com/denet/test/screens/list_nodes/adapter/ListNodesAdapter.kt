package com.denet.test.screens.list_nodes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denet.domain.models.Node
import com.denet.test.R

class NodeDiffCallback : DiffUtil.ItemCallback<Node>() {
    override fun areItemsTheSame(
        old: Node,
        new: Node
    ) = old.id == new.id

    override fun areContentsTheSame(
        old: Node,
        new: Node
    ) = old.name == new.name
}

class ListNodesAdapter(
    private val onItemClick: (Node) -> Unit,
    private val onDeleteClick: (Node) -> Unit
) : ListAdapter<Node, ListNodesAdapter.NodeVH>(NodeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNodesAdapter.NodeVH =
        NodeVH(LayoutInflater.from(parent.context).inflate(R.layout.node_item, parent, false))


    override fun onBindViewHolder(holder: ListNodesAdapter.NodeVH, position: Int) =
        holder.bind(getItem(position))

    inner class NodeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val delete: View = itemView.findViewById(R.id.deleteBtn)
        private val name: TextView = itemView.findViewById(R.id.nameTv)

        fun bind(node: Node) {
            name.text = node.name

            itemView.setOnClickListener { onItemClick(node) }
            delete.setOnClickListener { onDeleteClick(node) }
        }
    }

}