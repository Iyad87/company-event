package com.mobiquity.codingevents.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomeControllerTest {


    HomeController homeController;
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks ( this );
        homeController = new HomeController ();

    }

    @Test
    public void getIndexPage() throws Exception{

        assertEquals ( "index", homeController.index () );
    }
}