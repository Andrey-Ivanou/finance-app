package by.it_academy.jd2._107.classifier_service.repository.api;

import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICategoryRepository extends JpaRepository <EntityCategory, UUID> {

}
