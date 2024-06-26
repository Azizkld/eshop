package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.PhoneTypeRequest;
import com.billcom.eshop.Responce.PhoneTypeResponse;

public interface InterfacePhoneTypeService {
    PhoneTypeResponse ajoutTypePhone(PhoneTypeRequest phoneTypeRequest);
}
