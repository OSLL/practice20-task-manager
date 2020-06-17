package com.makentoshe.androidgithubcitemplate.items

import android.app.Application
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.MainActivity
import com.makentoshe.androidgithubcitemplate.items.TaskDao
import com.makentoshe.androidgithubcitemplate.items.TaskDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder


class SwipeCallback(adapter: GroupAdapter<ViewHolder>, application: Application) :
    ItemTouchHelper.SimpleCallback(0,0) {
    val app = application

    private fun removeItem(pos: Int, adapter: GroupAdapter<ViewHolder>) {
    }
    private val mAdapter: GroupAdapter<ViewHolder> = adapter
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val pos = viewHolder.adapterPosition
        return ItemTouchHelper.Callback.makeMovementFlags(
            createDragFlags(pos),
            createSwipeFlags(pos)
        )
    }
    private fun createDragFlags(pos: Int) : Int {
        return if (pos == 0) 0 else ItemTouchHelper.UP or ItemTouchHelper.DOWN
    }
    private fun createSwipeFlags(pos: Int) : Int {
        return if (pos == 0) 0 else ItemTouchHelper.START or ItemTouchHelper.END
    }

    //This method is not needed, hence return false
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        //Log.v("Pos:", position.toString())
       // MainActivity().updateAdapter(MainActivity().groupAdapter, app)
    }

}