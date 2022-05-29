package course_work.view

import course_work.model.Model
import course_work.model.ModelChangeListener

class ConsoleUi(private val model: Model) {
    init {
        val listener = object : ModelChangeListener {
            override fun onModelChanged() {
                repaint()
            }
        }
        model.addModelChangeListener(listener)

        repaint()
    }

    private fun repaint() {
        Runtime.getRuntime().exec("clear")
        println(model)
    }
}