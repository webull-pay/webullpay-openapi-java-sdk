package com.webullpay.openapi.endpoint;

import com.webullpay.openapi.common.ApiModule;
import com.webullpay.openapi.common.DefaultHost;
import com.webullpay.openapi.common.Region;
import com.webullpay.openapi.endpoint.EndpointResolver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class EndpointResolverTest {

    @Test
    public void whenUnknownRegionThenEmptyHost() {
        Optional<String> host = EndpointResolver.getDefault().resolve("unknown", ApiModule.API);
        Assert.assertFalse(host.isPresent());

        host = EndpointResolver.getDefault().resolve("unknown", ApiModule.QUOTES);
        Assert.assertFalse(host.isPresent());

        host = EndpointResolver.getDefault().resolve("unknown", ApiModule.EVENTS);
        Assert.assertFalse(host.isPresent());
    }

    @Test
    public void whenRegionIsUs_CryptoThenGetUsHost() {
        Optional<String> host = EndpointResolver.getDefault().resolve(Region.us.name(), ApiModule.API);
        Assert.assertTrue(host.isPresent());
        Assert.assertEquals(DefaultHost.API_US, host.get());

        host = EndpointResolver.getDefault().resolve(Region.us.name(), ApiModule.QUOTES);
        Assert.assertTrue(host.isPresent());
        Assert.assertEquals(DefaultHost.QUOTES_US, host.get());

        host = EndpointResolver.getDefault().resolve(Region.us.name(), ApiModule.EVENTS);
        Assert.assertTrue(host.isPresent());
        Assert.assertEquals(DefaultHost.EVENTS_US, host.get());
    }
}
