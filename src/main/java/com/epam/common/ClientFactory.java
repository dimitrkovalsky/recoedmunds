package com.epam.common;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by Volodymyr_Kychak on 9/29/2015.
 */
@Dependent
public class ClientFactory {
    @Produces
    Client createClient() {
        return ClientBuilder.newClient();
    }
}
