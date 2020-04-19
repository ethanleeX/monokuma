package me.masteryi.monokuma.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.masteryi.monokuma.R
import me.masteryi.monokuma.base.BaseAdapter
import me.masteryi.monokuma.base.BaseViewHolder
import me.masteryi.monokuma.databinding.HomeCategoryItemBinding
import me.masteryi.monokuma.model.CategoryItem
import me.masteryi.monokuma.ui.morsecode.MorseCodeActivity

/**
 * @author Ethan Lee
 */
class CategoryAdapter(context: Context) : BaseAdapter(context) {
    inner class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val binding = DataBindingUtil.bind<HomeCategoryItemBinding>(itemView)!!

        override fun onBind(position: Int) {
            binding.categoryItem = categoryItem[position]
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                // TODO: 换成 router
                val intent = Intent(context, MorseCodeActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    private val categoryItem = arrayListOf(
        CategoryItem("摩斯电码", "摩斯电码编码/转码")
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_category_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}