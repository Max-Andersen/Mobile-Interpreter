package com.example.myapplication.structs.tree

import com.example.myapplication.structs.queue.ArrayListQueue

typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T){

    val children : MutableList<TreeNode<T>> = mutableListOf()

    var parent: TreeNode<T>? = null

    fun add(child: TreeNode<T>) {
        children.add(child)
        child.parent = this
    }

    fun remove(child: TreeNode<T>){
        if(this == child)
            this.parent?.children?.remove(this)
        else
            for( i in children)
                i.remove(child)
    }

    fun forEachDepthFirst(visit: Visitor<T>){
        visit(this)

        children.forEach{
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevelOrder(visit: Visitor<T>){
        visit(this)

        val queue = ArrayListQueue<TreeNode<T>>()

        children.forEach{
            queue.enqueue(it)
        }

        var node = queue.dequeue()

        while(node!=null){
            visit(node)

            node.children.forEach{queue.enqueue(it)}

            node = queue.dequeue()
        }
    }

    fun search(value: T): TreeNode<T>?{
        var result: TreeNode<T>? = null

        forEachDepthFirst {
            if(it.value == value)
                result = it
        }

        return result
    }

    fun printEachLevel(){
        val queue = ArrayListQueue<TreeNode<T>>()

        var nodesLeftCurrentLevel = 0

        queue.enqueue(this)

        while(queue.isEmpty.not()){
            nodesLeftCurrentLevel = queue.count

            while(nodesLeftCurrentLevel > 0){
                val node = queue.dequeue()

                if(node!=null){
                    println("${node.value}")

                    node.children.forEach{queue.enqueue(it)}

                    nodesLeftCurrentLevel--
                }
                else{
                    break
                }
            }

            println()
        }
    }
}