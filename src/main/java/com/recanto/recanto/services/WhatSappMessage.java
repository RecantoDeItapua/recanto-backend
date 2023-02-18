package com.recanto.recanto.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@AllArgsConstructor
@Getter
@Setter
public class WhatSappMessage {
    private String  numberApi ;
    private String message;


    public void sendMessageByWhatsapp() {


    }
}
