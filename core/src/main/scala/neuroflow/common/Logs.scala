package neuroflow.common

import java.io.{File, FileOutputStream, PrintWriter}

import breeze.util.LazyLogger
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author bogdanski
  * @since 29.09.15
  */

/**
  * Base trait for any loggers.
  */
trait Loggable[Return] {

  def warn(message: String): Return
  def error(message: String): Return
  def info(message: String): Return

}

/**
  * Logs trait using slf4j.
  */
trait Logs extends Loggable[Unit] {

  private val logger = new LazyLogger(LoggerFactory.getLogger(this.getClass))
  private val datePattern = "dd.MM.yyyy HH:mm:ss:SSS"
  private def format(s: String) = s"[${DateTime.now.toString(datePattern)}] $s"

  def warn(message: String): Unit = logger.warn(format(message))
  def error(message: String): Unit = logger.error(format(message))
  def info(message: String): Unit = logger.info(format(message))

}

