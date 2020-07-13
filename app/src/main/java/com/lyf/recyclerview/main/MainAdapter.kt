package com.lyf.recyclerview.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.recyclerview.widget.RecyclerView
import com.lyf.recyclerview.R
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder

class MainAdapter(var images: ArrayList<Int>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemCLick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: AppCompatImageView = itemView.findViewById(R.id.imageView)
    }

    /*class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), AnimateViewHolder {
        val imageView: AppCompatImageView = itemView.findViewById(R.id.imageView)
        override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder?) {
            itemView.translationY = -itemView.height * 0.3f
            itemView.alpha = 0f
        }

        override fun preAnimateRemoveImpl(holder: RecyclerView.ViewHolder?) {

        }

        override fun animateAddImpl(
            holder: RecyclerView.ViewHolder?,
            listener: ViewPropertyAnimatorListener?
        ) {
            ViewCompat.animate(itemView).apply {
                translationY(0f)
                alpha(1f)
                duration = 250
                setListener(listener)
            }.start()
        }

        override fun animateRemoveImpl(
            holder: RecyclerView.ViewHolder?,
            listener: ViewPropertyAnimatorListener?
        ) {
            ViewCompat.animate(itemView).apply {
                translationY(-itemView.height * 0.3f)
                alpha(0f)
                duration = 250
                setListener(listener)
            }.start()
        }
    }*/

    /**
     * 添加项
     */
    fun addData(position: Int) {
        images.add(position, R.drawable.img_5)
        notifyItemInserted(position)
    }

    /**
     * 删除项
     */
    fun deleteData(position: Int) {
        images.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val imageRes = images[position]
        val imageDrawable = holder.imageView.context.resources.getDrawable(imageRes)
        val width = holder.imageView.context.resources.displayMetrics.widthPixels / 2
        val height =
            imageDrawable.intrinsicHeight.toFloat() / imageDrawable.intrinsicWidth * width
        holder.imageView.layoutParams.height = height.toInt()
        holder.imageView.setImageDrawable(imageDrawable)

        if (mOnItemClickListener != null) {
            holder.imageView.setOnClickListener {
                val pos = holder.layoutPosition
                mOnItemClickListener?.onItemCLick(holder.imageView, pos)
            }
            holder.imageView.setOnLongClickListener {
                val pos = holder.layoutPosition
                mOnItemClickListener?.onItemLongClick(holder.imageView, pos)
                return@setOnLongClickListener true
            }
        }
    }
}