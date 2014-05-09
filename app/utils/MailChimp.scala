package utils

/**
 * Created by SaumilP on 2014/05/09.
 *
 * Inherited from https://github.com/rorp/scala-landing-page
*/
import com.ecwid.mailchimp.{MailChimpException, MailChimpClient}
import com.ecwid.mailchimp.method.list.{ListsMethodFilters, ListsMethod}
import play.api.{Play, Logger}
import scala.collection.JavaConversions._

object MailChimp {

  val successMessage = "Thanks! We'll update you through Email!"
  val errorMessage = "Oops! Something wrong happened. Try again later."

  val apiKey = configString("mailchimp.apikey")
  val listName = configString("mailchimp.listname")

  private lazy val mailChimpClient = new MailChimpClient()

  def subscribe(email: String): Either[String, String] = {
    try {
      listInfo match {
        case Some(info) => {
          MailChimp.subscribeForList(info.id, email)
          Right(successMessage)
        }
        case _ => {
          Left(errorMessage)
        }
      }
    } catch {
      case ex: MailChimpException => {
        Logger.error(errorMessage, ex)
        ex.code match {
          case 502 => Left("Valid email required")
          case _ => Left(errorMessage)
        }
      }
    }
  }

  private lazy val listInfo = {
    val listsMethod = new ListsMethod()
    listsMethod.apikey = apiKey
    listsMethod.filters = new ListsMethodFilters()
    listsMethod.filters.list_name = listName
    listsMethod.filters.exact = true
    listsMethod.start = 0
    listsMethod.limit = 1
    try {
      mailChimpClient.execute(listsMethod).data.headOption match {
        case None => {
          Logger.error("Cannot find list named '" + listName + "'")
          None
        }
        case whatever => whatever
      }
    } catch {
      case e: Throwable => {
        Logger.error("Cannot load info for list '" + listName + "': ", e)
        None
      }
    }
  }

  private def subscribeForList(listId: String, email: String) {
    import com.ecwid.mailchimp.method.list.ListSubscribeMethod
    val listSubscribeMethod = new ListSubscribeMethod()
    listSubscribeMethod.apikey = apiKey
    listSubscribeMethod.id = listId
    listSubscribeMethod.email_address = email
    listSubscribeMethod.double_optin = false
    listSubscribeMethod.update_existing = true
    mailChimpClient.execute(listSubscribeMethod)
  }

  private def configString(key: String): String = {
    Play.current.configuration.getString(key).getOrElse("-UNKNOWN-")
  }

}