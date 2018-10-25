package ReactSlate.Reflow

external fun require(moduleId: String): dynamic

val normalizeLayoutProps = require("../build/layout/lib/normalizeLayoutProps").default

class View(parent: Node): Node() {
    var layoutProps: dynamic? = null
    var normalizedLayoutProps: dynamic? = null
    var styleProps: dynamic? = null
    var borderProps: dynamic? = null
    override var layout: dynamic = ContainerLayout(this)

    init {
        this.parent = parent
    }


    @JsName("setLayoutProps")
    fun setLayoutProps(layoutProps: dynamic?) {
        this.layoutProps = layoutProps;
        if (this.layoutProps) {
            this.normalizedLayoutProps = normalizeLayoutProps(this.layoutProps)
        } else {
            this.normalizedLayoutProps = null
        }
    }

    @JsName("setStyleProps")
    fun setStyleProps(styleProps: dynamic?) {
        this.styleProps = styleProps
    }

    @JsName("findChild")
    fun findChild(child: Node): Int {
        return this.children.indexOf(child);
    }

    @JsName("prependChild")
    fun prependChild(child: Node, position: Int? = null) {
        child.parent = this
        child.layout.parent = this.layout
        if (position != null) {
            this.children.add(position, child)
            this.layout.children.splice(position, 0, child.layout)
        } else {
            this.children.add(child)
            this.layout.children.push(child.layout)
        }
    }

    @JsName("appendChild")
    fun appendChild(child: Node, position: Int? = null) {
        child.parent = this
        child.layout.parent = this.layout
        if (position != null) {
            this.children.add(position + 1, child)
            this.layout.children.splice(position + 1, 0, child.layout)
        } else {
            this.children.add(child)
            this.layout.children.push(child.layout)
        }
    }

    @JsName("removeChild")
    fun removeChild(child: Node) {
        throw Error("no implementation")
    }
}

