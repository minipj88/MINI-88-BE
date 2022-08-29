package com.ujutechnology.api8.biz.domain;

import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {
    public static Specification<Product> withCompanyName(String financialCompanyName) {
        return (Specification<Product>) ((root, query, builder) ->
                builder.equal(root.get("financialCompanyName"), financialCompanyName)
        );
    }

    public static Specification<Product> withJob(String job) {
        return (Specification<Product>) ((root, query, builder) ->
                builder.equal(root.get("job"), job)
        );
    }

    public static Specification<Product> withProductName(String productName) {
        return (Specification<Product>) ((root, query, builder) ->
                builder.equal(root.get("productName"), productName)
        );
    }

    public static Specification<Product> withRate(Double rate) {
        return (Specification<Product>) ((root, query, builder) ->
                builder.lessThanOrEqualTo(root.get("rate"), rate)
        );
    }

    public static Specification<Product> withAge(Integer age) {
        return (Specification<Product>) ((root, query, builder) ->
                builder.equal(root.get("age"), age)
        );
    }

}