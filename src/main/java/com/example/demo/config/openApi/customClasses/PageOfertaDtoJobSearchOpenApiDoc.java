package com.example.demo.config.openApi.customClasses;

import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.example.demo.domain.ofertas.OfertaDtoJobSearch;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageOfertaDtoJobSearchOpenApiDoc")
public class PageOfertaDtoJobSearchOpenApiDoc extends PageImpl<OfertaDtoJobSearch> {

    protected PageOfertaDtoJobSearchOpenApiDoc() {
        super(List.of());
    }

}
