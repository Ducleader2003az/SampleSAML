package com.kokobato.huynhduc.kokobatodemo.repositories.specification;

import com.kokobato.huynhduc.kokobatodemo.models.Floor;
import com.kokobato.huynhduc.kokobatodemo.models.Office;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class FloorSpecification implements Specification<Floor> {
    @Override
    public Predicate toPredicate(Root<Floor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public static Specification<Floor> findFloorsByOfficeId(int officeId) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Floor, Office> join = root.join("office", JoinType.INNER);
            
            if (!StringUtils.isEmpty(officeId)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("id"), officeId));
            }

            query.where(predicate);

            return query.getRestriction();
        };
    }
}
