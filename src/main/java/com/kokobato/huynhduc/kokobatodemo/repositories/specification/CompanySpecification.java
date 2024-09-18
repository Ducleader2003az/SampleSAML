package com.kokobato.huynhduc.kokobatodemo.repositories.specification;

import com.kokobato.huynhduc.kokobatodemo.models.Company;
import com.kokobato.huynhduc.kokobatodemo.models.Floor;
import com.kokobato.huynhduc.kokobatodemo.models.Office;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CompanySpecification implements Specification<Company> {

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public static Specification<Company> findCompaniesByOfficeId(int officeId) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Company, Office> officeJoin = root.join("office", JoinType.INNER);

            if (officeId != 0) {
                predicate = criteriaBuilder.equal(officeJoin.get("id"), officeId);
            }
            ;

            query.where(predicate);

            return query.getRestriction();
        });
    }

    public static Specification<Company> findCompaniesByFloorId(int floorId) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Company, Floor> floorJoin = root.join("floor", JoinType.INNER);

            if (!StringUtils.isEmpty(floorId)) {
                predicate = criteriaBuilder.equal(floorJoin.get("id"), floorId);
            }
            ;

            query.where(predicate);

            return query.getRestriction();
        });
    }
}
