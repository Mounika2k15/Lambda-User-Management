package com.dash.aws.lambda.respository;

import com.dash.aws.lambda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
User findByName(String name);

@Modifying(clearAutomatically = true)
@Query("update User myusers set myusers.name=:name where myusers.id=:id")
@Transactional
void updateUsers(@Param("name") String name, @Param("id") String id);


//    @Modifying
//    @Query(value = "insert into  myorders (id,name) VALUES (:id1,:name1)", nativeQuery = true)
//    @Transactional
//    void saveCustom(@Param("id") String id1, @Param("name") String name1);


//    public List<Order> buildOrders(){
//        return Stream.of(
//                new Order(101, "Mobile", 20000, 1),
//                new Order(102, "Book", 999, 2),
//                new Order(278, "Book", 1466, 3),
//                new Order(953, "Jeans", 4499, 1)
//        ).collect(Collectors.toList());
//    }
}
