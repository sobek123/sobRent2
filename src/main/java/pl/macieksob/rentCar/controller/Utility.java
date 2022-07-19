package pl.macieksob.rentCar.controller;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    public static String getURL(HttpServletRequest httpServletRequest){
        String url = httpServletRequest.getRequestURL().toString();
        return url.replace(httpServletRequest.getServletPath(),"");
    }
}
