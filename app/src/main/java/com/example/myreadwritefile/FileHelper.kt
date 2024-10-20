package com.example.myreadwritefile

import android.content.Context

object FileHelper {

    fun writeToFile(filemodel: FileModel, context: Context) {
        context.openFileOutput(filemodel.filename, Context.MODE_PRIVATE).use {
            it.write(filemodel.data?.toByteArray())
        }
    }

    fun readFromFile(context: Context, fileName: String): FileModel {
        val filemodel = FileModel()
        filemodel.filename = fileName
        filemodel.data = context.openFileInput(fileName).bufferedReader().useLines {
            lines -> lines.fold("") {some, text ->
                "$some$text\n"
            }
        }
        return filemodel
    }
}