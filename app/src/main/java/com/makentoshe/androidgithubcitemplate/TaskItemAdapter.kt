package com.makentoshe.androidgithubcitemplate

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition:Int, toPosition:Int):Boolean
    fun onItemDismiss(position:Int)
}