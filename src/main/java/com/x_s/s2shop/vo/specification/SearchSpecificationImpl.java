package com.x_s.s2shop.vo.specification;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpecificationImpl<T> {

    private static Specification buildSpecification() {
//        return new Specification<SysRole>() {
//            @Nullable
//            @Override
//            public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        };

        return (root, query, criteriaBuilder) -> {
            return null;
        };
    }
}
