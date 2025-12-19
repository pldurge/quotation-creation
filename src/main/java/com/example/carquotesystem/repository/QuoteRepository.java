
package com.example.carquotesystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carquotesystem.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote,Long>{}
