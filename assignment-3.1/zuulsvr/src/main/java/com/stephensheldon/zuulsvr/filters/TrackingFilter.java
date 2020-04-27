package com.stephensheldon.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: Stephen Sheldon
 **/

// Filter to inject the correlation ID into incoming http calls through Zuul
@Component
public class TrackingFilter extends ZuulFilter {

    private static final int      FILTER_ORDER =  1;
    private static final boolean  SHOULD_FILTER=true;
    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    //Commonly used functions that are used across all your filters have been encapsulated in the FilterUtils class.
    @Autowired
    FilterUtils filterUtils;

    //The filterType() method is used to tell Zuul whether the filter is a pre-, route, or post filter.
    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    //The filterOrder() method returns an integer value indicating what order Zuul should send requests
    //through the different filter types.
    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    //The shouldFilter() method returns a Boolean indicating whether or not the filter should be active.
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    //The helper methods that actually check if the tmx-correlation-id is present
    private boolean isCorrelationIdPresent(){
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }

    //Generate a correlation ID GUIID value
    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    //The run() method is the code that is executed every time a service passes through the filter.
    //In your run() function, you check to see if the tmx-correlation-id is present and if it isn’t,
    //you generate a correlation value and set the tmx-correlation-id.
    public Object run() {

        if (isCorrelationIdPresent()) {
            logger.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
        }
        else{
            filterUtils.setCorrelationId(generateCorrelationId());
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
        return null;
    }
}