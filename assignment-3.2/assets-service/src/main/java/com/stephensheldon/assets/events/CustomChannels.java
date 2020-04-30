package com.stephensheldon.assets.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Author: Stephen Sheldon
 **/

public interface CustomChannels {
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}
