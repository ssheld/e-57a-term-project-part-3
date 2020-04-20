package com.stephensheldon.specialroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Stephen Sheldon
 **/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRouteFound extends RuntimeException{
}
