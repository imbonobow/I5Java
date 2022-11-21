package com.rioc.ws.repositories;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iBankDetailRepository extends JpaRepository<BankDetail, Integer>
{

}
