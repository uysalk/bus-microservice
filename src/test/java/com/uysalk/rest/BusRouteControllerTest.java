package com.uysalk.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by uysal.kara on 14.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusRouteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void direct() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/direct?pickup_id=0&dropoff_id=4",
                String.class)).contains("\"has_direct_route\":true");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/direct?pickup_id=3&dropoff_id=8",
                String.class)).contains("\"has_direct_route\":false");

    }


    @Test
    public void indirect() throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/indirect?pickup_id=3&dropoff_id=8",
                String.class)).contains("\"has_indirect_route\":true");

    }


}