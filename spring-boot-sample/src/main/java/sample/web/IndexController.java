package sample.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController implements ErrorController {
    private final static String PATH = "/error";

    @Override
    @RequestMapping(value = PATH, method = { RequestMethod.GET, RequestMethod.POST }, headers = "Accept=*/*", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getErrorPath() {
        return "No Mapping Found";
    }

}

