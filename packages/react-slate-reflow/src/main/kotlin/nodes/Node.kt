package ReactSlate.Reflow

abstract class Node {
    var parent: Node? = null
    var children: ArrayList<Node> = ArrayList()
    abstract var layout: dynamic
}

