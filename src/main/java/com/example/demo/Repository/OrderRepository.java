package com.example.demo.Repository;

import com.example.demo.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query("""
                 select count(o)
                 from Orders o
                 Where o.customer.customerId=:customerId""")
    Long countOrder(@Param("customerId") Long customerId);

    @Query("""
                select sum(o.totalPrice)
                from Orders o
                Where o.customer.customerId=:customerId""")
    Double totalAmount(@Param("customerId") Long customerId);

    @Query("""
                select sum(o.totalPrice)
                from Orders o""")
    Double totalRevenue();

}