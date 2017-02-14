package com.uysalk.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class DataModelTest {
    @Test
    public void run() throws Exception {

        DataModel model = new DataModel();
        model.run("input.txt");

        assertEquals (model.getRouteModel().values().size(),5); // there are 5 routes in the sample file...

    }

}