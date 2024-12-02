package by.it_academy.jd2._107.classifier_service.repository.api;

import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICurrencyRepository extends JpaRepository <EntityCurrency, UUID> {

}
