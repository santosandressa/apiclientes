package com.apiclientes.feign.service;

import com.apiclientes.feign.model.Cep;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "https://viacep.com.br/ws")
public interface  CepService {

    @GetMapping("/{cep}/json")
    @Headers("Content-Type: application/json")
    Cep getEnderecoPorCep(@PathVariable("cep") String cep);
}
