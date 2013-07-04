import play.api._
import play.api.mvc.RequestHeader
import play.api.mvc.Result
import play.api.mvc.Results
import play.api.libs.concurrent.Akka
import com.app.util.AkkaExtensions
import com.app.util.Main

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    // Ensure the Auvik Extensions get loaded
    val system = Akka.system(app)
    AkkaExtensions(system)
    Main(system)
  }
}