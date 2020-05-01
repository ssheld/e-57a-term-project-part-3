package com.stephensheldon.organization.events.source;

import com.stephensheldon.organization.events.models.OrganizationChangeModel;
import com.stephensheldon.organization.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Author: Stephen Sheldon
 **/

@Component
public class SimpleSourceBean {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    private Source source;

    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId) {
        logger.debug("Sending Kafka message {} for Organization Id: {}", action, orgId);

        // Create instance of OrganizationChangeModel
        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId());

        // When we're ready to publish a method we will use the send() method on
        // our MessageChannel class returned from the source.output() method. Our
        // OrganizationChangeModel will be our payload.
        source.output().send(MessageBuilder.withPayload(change).build());

        // The configuration that does the mapping of our service's Spring Cloud Stream
        // to a Kafka message broker and a message topic in Kafka is done on
        // organization-service's application.yml
    }
}
