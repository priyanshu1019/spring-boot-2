package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.repositories;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//<EntityName , Type of ID>
//jpaRepo gives us some basic crud operations inbuilt which makes it very useful for us
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    //if you want to create your own crud operation do it here
}
