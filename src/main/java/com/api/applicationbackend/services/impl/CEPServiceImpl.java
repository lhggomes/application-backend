package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.model.Address;
import com.api.applicationbackend.services.CEPService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class CEPServiceImpl implements CEPService {

    @Value("${cep.api.url}")
    private String url;


    public CEPServiceImpl() {

    }

    @Override
    public Address searchCEPAtBrazilianProvider(String cep) {

        Gson gson = new Gson();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(this.url + "/" + cep))
                    .version(HttpClient.Version.HTTP_2)
                    .headers("Accept", "application/json")
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .GET()
                    .build();


            HttpResponse<String> response = HttpClient.newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Address address = gson.fromJson(response.body(), Address.class);
            return address;

        } catch (URISyntaxException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }

    }


}
