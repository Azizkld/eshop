package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.RateplanRequest;
import com.billcom.eshop.Responce.RateplanResponse;

public interface InterfaceRateplanService {
    RateplanResponse addRateplan(RateplanRequest rateplanRequest);

    RateplanResponse deleteRateplan(Long id);
    RateplanResponse findAllRateplans();
    RateplanResponse findRateplanById(Long id);
    RateplanResponse updateRateplan(Long id, RateplanRequest rateplanRequest);
}

