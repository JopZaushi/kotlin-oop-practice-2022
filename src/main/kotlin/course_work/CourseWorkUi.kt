package course_work

import java.awt.BorderLayout
import java.awt.Component
import java.awt.GridLayout
import java.io.File
import java.io.InputStream
import java.time.LocalDate
import javax.swing.*
import javax.swing.table.DefaultTableModel


private const val GAP = 15

class CourseWorkUi : JFrame("Notes"), ModelChangeListener {
    private val statusLabel = JLabel("Notes", JLabel.CENTER)
    private val model = DefaultTableModel()
    private val table = JTable(model)
    private val listText = mutableListOf<String>()
    private val lineList = mutableListOf<String>()

    //конструктор главной панели
    init {
        setSize(400, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        val name: Array<String> = arrayOf("Вид заметки", "Название", "Дата создания")
        model.setColumnIdentifiers(name)
        model.addRow(name)

        //чтение с файла
        val inputStream: InputStream = File("src/main/kotlin/course_work/readTable.txt").inputStream()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }

        println(lineList.size)
        for (i in 0 until lineList.size step 3) {
            val data: Array<String> = arrayOf(lineList[i], lineList[i + 1], lineList[i + 2])
            model.addRow(data)
        }
        //ОЧИСТКА ФАЙЛА
        //FileOutputStream("src/main/kotlin/course_work/readTable.txt")
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
                lineList.add("Text Note")
                lineList.add(titleTextNote.text)
                lineList.add("$dataTime")
                //Запись в файл
                File("src/main/kotlin/course_work/readTable.txt").bufferedWriter().use { out ->
                    for (i in 0 until lineList.size) {
                        out.write(lineList[i])
                        out.write("\n")
                    }
                }

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
            val modelCheckBox: DefaultTableModel
            modelCheckBox = object : DefaultTableModel() {
                override fun getColumnClass(columnIndex: Int): Class<*> {
                    return when (columnIndex) {
                        0 -> java.lang.Boolean::class.java
                        //1 -> String::class.java
                        else -> Any::class.java
                    }
                }
            }

            val name: Array<Any> = arrayOf(false, "")
            modelCheckBox.setColumnIdentifiers(name)
            val jTable1 = JTable()
            jTable1.model = modelCheckBox

            //Настройка шириины 1 столбца
            val columnModel = jTable1.columnModel.getColumn( 0 )
            columnModel.maxWidth = 15

            //кнопки добавления и сохранения
            val addButton = JButton("+")
            addButton.addActionListener {
                modelCheckBox.addRow(name)
            }
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val dataTime = LocalDate.now()
                val data: Array<String> = arrayOf("To do list", jTable1.getValueAt(0, 1).toString(), "$dataTime")
                model.addRow(data)
                lineList.add("To do list")
                lineList.add(jTable1.getValueAt(0, 1).toString())
                lineList.add("$dataTime")
                //Запись в файл
                File("src/main/kotlin/course_work/readTable.txt").bufferedWriter().use { out ->
                    for (i in 0 until lineList.size) {
                        out.write(lineList[i])
                        out.write("\n")
                    }
                }
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
                lineList.add("Link")
                lineList.add(titleTextNote.text)
                lineList.add("$dataTime")
                //Запись в файл
                File("src/main/kotlin/course_work/readTable.txt").bufferedWriter().use { out ->
                    for (i in 0 until lineList.size) {
                        out.write(lineList[i])
                        out.write("\n")
                    }
                }
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
                lineList.add("Image")
                lineList.add(fileChooser.selectedFile.toString())
                lineList.add("$dataTime")
                //Запись в файл
                File("src/main/kotlin/course_work/readTable.txt").bufferedWriter().use { out ->
                    for (i in 0 until lineList.size) {
                        out.write(lineList[i])
                        out.write("\n")
                    }
                }
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