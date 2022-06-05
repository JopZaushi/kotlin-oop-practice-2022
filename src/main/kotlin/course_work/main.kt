package course_work

import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val ticTacToeUi = CourseWorkUi()
        ticTacToeUi.setLocationRelativeTo(null)
        ticTacToeUi.isVisible = true
    }
}