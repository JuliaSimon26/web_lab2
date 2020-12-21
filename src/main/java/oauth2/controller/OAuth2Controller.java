package oauth2.controller;
import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author dderose
 *
 */
@Controller
public class OAuth2Controller {

	public static final String clientId = "7650563";
	public static final String clientSecret = "uS0b5XB2JbDy5eCqCSIm";
	public static final OAuth20Service service = new ServiceBuilder(clientId)
			.apiSecret(clientSecret)
			.defaultScope("wall,offline") // replace with desired scope
			.callback("http://localhost:8080/callback")
			.build(VkontakteApi.instance());
	public static final String customScope = "wall,offline,email";
	public static final String authorizationUrl = service.createAuthorizationUrlBuilder().scope(customScope).build();
	    
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/signInWithVK")
	public View signInWithVK() {
		return new RedirectView(authorizationUrl);
	}

}
