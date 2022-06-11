package geonote.core.app.util

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Some useful and not methods
 *
 * Totally just trash
 */
object CodeHelper {
  private val formatter = new SimpleDateFormat("ddMMyyyy-hh:mm")
  def timeToString(millis: Long): String = {
    val date = new Date(millis)
    formatter.format(date)
  }

  def notImplemented[T](msg: String = ""): T = throw new RuntimeException("Not implemented: " + msg)
}
