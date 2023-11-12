package com.example.assignment5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.assignment5.databinding.ItemProductBinding

class ProductsAdapter(val productsClicked: (ProductItem)-> Unit, val quantityChanged: (ProductItem) -> Unit) : ListAdapter<ProductItem, ProductsAdapter.ItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemProductBinding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(
            binding,
            productsClicked,
            quantityChanged
        )
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: ItemProductBinding, val productsClicked: (ProductItem) -> Unit, val quantityChanged: (ProductItem) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem) = with(itemView) {
            binding.ivProductImage.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.product.productImage
                )
            )

            binding.ivProductLogo.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.product.productLogo
                )
            )

            binding.tvProductName.text = item.product.productName
            binding.tvProductPrice.text = "$${item.product.cost}"
            binding.tvProductDesc.text = item.product.productDescription
            binding.quantity.text = item.quantity.toString()

            binding.btnAdd.setOnClickListener {
                quantityChanged(item.copy(quantity = item.quantity+ 1))
            }

            binding.btnAddItem.setOnClickListener {
                quantityChanged(item.copy(quantity = item.quantity+ 1))
            }

            binding.btnReduceItem.setOnClickListener {
                quantityChanged(item.copy(quantity = item.quantity-1))
            }


            if(item.quantity <= 0) {
                binding.btnAdd.visibility = View.VISIBLE
                binding.vgAddRemove.visibility = View.GONE
            }
            else {
                binding.btnAdd.visibility = View.GONE
                binding.vgAddRemove.visibility = View.VISIBLE

            }


            setOnClickListener {
                productsClicked(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.product.productId == newItem.product.productId
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}