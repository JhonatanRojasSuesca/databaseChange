package com.example.database

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.database.SettingsPreferences.Companion.DATABASE_NAME_KEY
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipInputStream

class MainActivity : AppCompatActivity() {

    private val DATABASE_FOLDER = "/databases/"
    private lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        val unzip = findViewById<Button>(R.id.desempaquetarButton)
        val changeDataBase = findViewById<Button>(R.id.cambiardebasedatos)
        val rawQuery = findViewById<Button>(R.id.consultarBasededatos)

        unzip.setOnClickListener {
            unzipFile(this,
                context.applicationContext.resources.assets.open("version1.zip"))?.let { it ->
                settingsPreferences.setString(DATABASE_NAME_KEY,
                    it
                )
            }
            ManagerOpenHelperDatabase.getInstance().updateDatabase()
            Toast.makeText(this, "unzip", Toast.LENGTH_LONG).show()
        }
        changeDataBase.setOnClickListener {
            unzipFile(this,
                context.applicationContext.resources.assets.open("version2.zip"))?.let { it ->
                settingsPreferences.setString(DATABASE_NAME_KEY,
                    it
                )
            }
            ManagerOpenHelperDatabase.getInstance().updateDatabase()
            Toast.makeText(this, "changeDataBase", Toast.LENGTH_LONG).show()
        }
        rawQuery.setOnClickListener {
            var cursor = ManagerOpenHelperDatabase.getInstance().getReadable().rawQuery("select * from products", null)
            if (cursor.moveToFirst()){
                Toast.makeText(this, "todo bien con la consulta", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "todo bien con la consulta", Toast.LENGTH_LONG).show()
            }
            cursor.close()
        }
    }


    fun unzipFile(context: Context, zipFilePath: InputStream?): String? {
        return try {
            createDatabaseFolder(context)
            val buffer = ByteArray(1024)
            val zis = ZipInputStream(zipFilePath)
            val zipEntry = zis.nextEntry
            val destFile =
                context.applicationInfo.dataDir + DATABASE_FOLDER + zipEntry.name
            val newFile = File(destFile)
            val fos = FileOutputStream(newFile)
            var len: Int
            while (zis.read(buffer).also { len = it } > 0) {
                fos.write(buffer, 0, len)
            }
            fos.close()
            zis.closeEntry()
            zis.close()
            destFile
        } catch (io: IOException) {
            io.printStackTrace()
            ""
        }
    }

    private fun createDatabaseFolder(context: Context): Boolean? {
        return File(context.applicationInfo.dataDir +DATABASE_FOLDER)
            .mkdirs()
    }
}
