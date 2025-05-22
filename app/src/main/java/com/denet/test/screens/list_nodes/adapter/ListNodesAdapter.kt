package com.denet.test.screens.list_nodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denet.domain.models.Node
import com.denet.test.databinding.NodeItemBinding

class NodeDiffCallback : DiffUtil.ItemCallback<Node>() {
    override fun areItemsTheSame(old: Node, new: Node) = old.id == new.id
    override fun areContentsTheSame(old: Node, new: Node) = old.name == new.name
}

class ListNodesAdapter(
    private val onItemClick: (Node) -> Unit,
    private val onDeleteClick: (Node) -> Unit
) : ListAdapter<Node, ListNodesAdapter.NodeVH>(NodeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNodesAdapter.NodeVH {
        val itemBinding = NodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NodeVH(itemBinding)
    }

    override fun onBindViewHolder(holder: ListNodesAdapter.NodeVH, position: Int) =
        holder.bind(getItem(position))

    inner class NodeVH(private val itemBinding: NodeItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(node: Node) {
            itemBinding.nameTv.text = node.name

            itemView.setOnClickListener { onItemClick(node) }
            itemBinding.deleteBtn.setOnClickListener { onDeleteClick(node) }
        }
    }

}