package com.example.rssapp

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XMLParser {
    private val questions = ArrayList<Questions>()
    private var text: String? = null
    private var title = ""
    fun parse(inputStream: InputStream): ArrayList<Questions> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType // notify the start or end of tag
            while (eventType != XmlPullParser.END_DOCUMENT) { // if not end of file complete
                val tagName = parser.name // to know the name of parseer
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", ignoreCase = true) -> {
                            title = text.toString()
                        }

                        tagName.equals("entry", ignoreCase = true) ->
                        { questions.add(Questions(title))
                        }
                    else->{

                                }
                    }

                    else -> {
                    }
                }
                eventType = parser.next() // to go next line in file
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return questions
    }
}