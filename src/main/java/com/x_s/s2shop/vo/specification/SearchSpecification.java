package com.x_s.s2shop.vo.specification;

import com.x_s.s2shop.common.annotation.DynamicSearch;
import lombok.var;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface SearchSpecification {

    default Specification buildSpecification() throws IllegalAccessException {
        final Class clazz = this.getClass();
        final Object that = this;
        System.out.println(clazz);
        return (root, query, criteriaBuilder) -> {
            Field[] fields = clazz.getDeclaredFields();
            List<Predicate> predicates = new ArrayList<>(fields.length);
            for (var field : fields) {
                field.setAccessible(true);
                DynamicSearch annotation = field.getAnnotation(DynamicSearch.class);
                if (annotation != null) {
                    Object value = null;
                    try {
                        value = field.get(that);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    String column = annotation.column();
                    if ("".equals(column)){
                        column = field.getName();
                    }
                    switch (annotation.value()) {
                        case GE:
                            break;
                        case GT:
                            break;
                        case LE:
                            System.out.println("le:" + value + "--" + column);
                            break;
                        case LT:
                            break;
                        case LIKE:
                            break;
                        case EQUAL:
                            System.out.println("eq:" + value + "--" + column);
                            break;
                        default:
                    }
                }
            }
            System.out.println("----");
            return null;
        };

//        BaseQuery outerThis = this;
//        return (root, criteriaQuery, cb) -> {
//            Class clazz = outerThis.getClass();
//            //获取查询类Query的所有字段,包括父类字段
//            List<Field> fields = getAllFieldsWithRoot(clazz);
//            List<Predicate> predicates = new ArrayList<>(fields.size());
//            for (Field field : fields) {
//                //获取字段上的@QueryWord注解
//                QueryCondition qw = field.getAnnotation(QueryCondition.class);
//                if (qw == null)
//                    continue;
//
//                // 获取字段名
//                String column = qw.column();
//                //如果主注解上colume为默认值"",则以field为准
//                if (column.equals(""))
//                    column = field.getName();
//
//                field.setAccessible(true);
//
//                try {
//
//                    // nullable
//                    Object value = field.get(outerThis);
//                    //如果值为null,注解未标注nullable,跳过
//                    if (value == null && !qw.nullable())
//                        continue;
//
//                    // can be empty
//                    if (value != null && String.class.isAssignableFrom(value.getClass())) {
//                        String s = (String) value;
//                        //如果值为"",且注解未标注emptyable,跳过
//                        if (s.equals("") && !qw.emptyable())
//                            continue;
//                    }
//
//                    //通过注解上func属性,构建路径表达式
//                    Path path = root.get(column);
//                    switch (qw.func()) {
//                        case equal:
//                            predicates.add(cb.equal(path, value));
//                            break;
//                        case like:
//                            predicates.add(cb.like(path, "%" + value + "%"));
//                            break;
//                        case gt:
//                            predicates.add(cb.gt(path, (Number) value));
//                            break;
//                        case lt:
//                            predicates.add(cb.lt(path, (Number) value));
//                            break;
//                        case ge:
//                            predicates.add(cb.ge(path, (Number) value));
//                            break;
//                        case le:
//                            predicates.add(cb.le(path, (Number) value));
//                            break;
//                        case notEqual:
//                            predicates.add(cb.notEqual(path, value));
//                            break;
//                        case notLike:
//                            predicates.add(cb.notLike(path, "%" + value + "%"));
//                            break;
//                        case greaterThan:
//                            predicates.add(cb.greaterThan(path, (Comparable) value));
//                            break;
//                        case greaterThanOrEqualTo:
//                            predicates.add(cb.greaterThanOrEqualTo(path, (Comparable) value));
//                            break;
//                        case lessThan:
//                            predicates.add(cb.lessThan(path, (Comparable) value));
//                            break;
//                        case lessThanOrEqualTo:
//                            predicates.add(cb.lessThanOrEqualTo(path, (Comparable) value));
//                            break;
//                    }
//                } catch (Exception e) {
//                    continue;
//                }
//            }
//            Predicate p = null;
//            if (logicType == null || logicType.equals("") || logicType.equals("and")) {
//                p = cb.and(predicates.toArray(new Predicate[predicates.size()]));//and连接
//            } else if (logicType.equals("or")) {
//                p = cb.or(predicates.toArray(new Predicate[predicates.size()]));//or连接
//            }
//            return p;
//        };
    }
}
