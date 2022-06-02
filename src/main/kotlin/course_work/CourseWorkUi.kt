package course_work

import java.awt.BorderLayout
import java.awt.Component
import java.awt.GridLayout
import java.time.LocalDate
import javax.swing.*
import javax.swing.table.DefaultTableModel


private const val GAP = 15

class CourseWorkUi : JFrame("Notes"), ModelChangeListener {
    private val statusLabel = JLabel("Notes", JLabel.CENTER)

    private val model = DefaultTableModel()
    private var table = JTable(model)

    //конструктор главной панели
    init {
        setSize(400, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        val name: Array<String> = arrayOf("Вид заметки", "Название", "Дата создания")
        model.setColumnIdentifiers(name)
        model.addRow(name)
        updateFont(statusLabel, 20.0f)
        rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(table, BorderLayout.CENTER)
            add(createCreateButton(), BorderLayout.SOUTH)
        }
    }


    private fun createCreateButton(): Component {
        val createButton = JButton("+")
        updateFont(createButton, 20.0f)
        createButton.addActionListener {
                createSwitchWindow()
        }
        return createButton
    }

    private fun createSwitchWindow() {
        val switchWindow = JDialog(this, "Create Note")
        switchWindow.setSize(250, 250)
        switchWindow.layout = GridLayout(4, 1, 20, 0)
        switchWindow.isResizable = false
        switchWindow.setLocationRelativeTo(null)

        //Текстовая заметка
        val buttonTextNote = JButton("Text Note")
        buttonTextNote.addActionListener {
            switchWindow.isVisible = false
            //новое окно для ввода заметки
            val noteWindow = JDialog(this, "Text Note")
            //текстовое поле
            val windowTextNote = JTextArea(200, 250)
            val titleTextNote = JTextField(250)
            //кнопка сохранения
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val dataTime = LocalDate.now()
                val name: Array<String> = arrayOf("Text Note", titleTextNote.text, "$dataTime")
                model.addRow(name)
            }
            //табуляция
            windowTextNote.lineWrap = true
            //скроллинг
            val scrollPane = JScrollPane(windowTextNote)
            //расположение элементов
            noteWindow.add(scrollPane, BorderLayout.CENTER)
            noteWindow.add(titleTextNote, BorderLayout.NORTH)
            noteWindow.add(saveButton, BorderLayout.SOUTH)
            noteWindow.setSize(250, 350)
            noteWindow.isVisible = true
            noteWindow.setLocationRelativeTo(null)

        }
        switchWindow.add(buttonTextNote, BorderLayout.AFTER_LAST_LINE)

        //Задача
        val buttonToDoList = JButton("To do list")
        buttonToDoList.addActionListener {
            switchWindow.isVisible = false
            //новое окно для ввода заметки
            val noteWindow = JDialog(this, "To do list")

            //создание таблицы для задач
            val dm = DefaultTableModel()
            dm.getColumnClass(0)
            val name: Array<Any> = arrayOf("", "")
            dm.setColumnIdentifiers(name)
            val jTable1 = JTable()
            jTable1.model = dm
            jTable1.getColumn("").cellEditor = DefaultCellEditor(JCheckBox())

            //кнопки добавления и сохранения
            val addButton = JButton("+")
            addButton.addActionListener {
                dm.addRow(name)
            }
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val dataTime = LocalDate.now()
                val data: Array<String> = arrayOf("To do list", jTable1.getValueAt(0, 1).toString(), "$dataTime")
                model.addRow(data)
            }

            //добавление кнопок в одну структуру
            val grid = JPanel()
            val lay = GridLayout(1, 0, 5, 12)
            grid.layout = lay
            grid.add(saveButton)
            grid.add(addButton)

            noteWindow.add(grid, BorderLayout.SOUTH)
            noteWindow.add(jTable1)
            noteWindow.setSize(250, 350)
            noteWindow.isVisible = true
            noteWindow.setLocationRelativeTo(null)

        }
        switchWindow.add(buttonToDoList)


        //Ссылка
        val buttonLink = JButton("Link")
        buttonLink.addActionListener {
            switchWindow.isVisible = false
            //новое окно для ввода заметки
            val noteWindow = JDialog(this, "Link")
            //текстовое поле
            val windowTextNote = JTextArea(200, 250)
            val titleTextNote = JTextField(250)
            //кнопка сохранения
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val dataTime = LocalDate.now()
                val name: Array<String> = arrayOf("Link", titleTextNote.text, "$dataTime")
                model.addRow(name)
            }
            //табуляция
            windowTextNote.lineWrap = true
            //скроллинг
            val scrollPane = JScrollPane(windowTextNote)
            noteWindow.add(scrollPane, BorderLayout.CENTER)
            noteWindow.add(titleTextNote, BorderLayout.NORTH)
            noteWindow.add(saveButton, BorderLayout.SOUTH)
            noteWindow.setSize(250, 350)
            noteWindow.isVisible = true
            noteWindow.setLocationRelativeTo(null)

        }
        switchWindow.add(buttonLink)

        //Картинка
        val buttonImage = JButton("Image")
        buttonImage.addActionListener {
            switchWindow.isVisible = false
            // Создание экземпляра JFileChooser
            val fileChooser = JFileChooser()
            fileChooser.dialogTitle = "Сохранение файла"
            // Определение режима - только файл
            fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
            val result = fileChooser.showSaveDialog(JDialog())
            // Если директория выбрана, покажем ее в сообщении
            if (result == JFileChooser.APPROVE_OPTION) {
                fileChooser.isVisible = false
                val dataTime = LocalDate.now()
                val data: Array<String> = arrayOf("Image", fileChooser.selectedFile.toString(), "$dataTime")
                model.addRow(data)
            }

        }
        switchWindow.add(buttonImage)
        switchWindow.isFocusable = true
        switchWindow.isVisible = true
    }


    private fun updateFont(component: JComponent, newFontSize: Float) {
        val font = component.font
        val derivedFont = font.deriveFont(newFontSize)
        component.font = derivedFont
    }

    override fun onModelChanged() {

    }
}