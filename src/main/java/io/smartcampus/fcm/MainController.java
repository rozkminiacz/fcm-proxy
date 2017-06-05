package io.smartcampus.fcm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class MainController {

    public MainController() {
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }

    @RequestMapping("/")
    public ModelAndView hello() {
        return new ModelAndView("it's working");
    }

    @RequestMapping(value = "/property", method = RequestMethod.POST)
    public ResponseEntity<FcmResponse> pushMessage(@RequestHeader("fcm-token") String token, @RequestBody String body) {
        System.out.println(body);
        JsonObject response = new Push().push(token, new Gson().fromJson(body, FcmNotification.class))
                .toBlocking()
                .first();
        FcmResponse fcmResponse = new Gson().fromJson(response, FcmResponse.class);
        return new ResponseEntity<FcmResponse>(fcmResponse, HttpStatus.ACCEPTED);
    }

}