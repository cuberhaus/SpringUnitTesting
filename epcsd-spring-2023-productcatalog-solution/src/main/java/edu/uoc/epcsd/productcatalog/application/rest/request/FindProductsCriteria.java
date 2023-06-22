package edu.uoc.epcsd.productcatalog.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class FindProductsCriteria {

    private final String name;

    private final Long categoryId;

}
