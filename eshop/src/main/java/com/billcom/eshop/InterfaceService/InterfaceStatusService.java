package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.StatusRequest;
import com.billcom.eshop.Responce.StatusResponse;

public interface InterfaceStatusService {
    StatusResponse changeUserStatus(StatusRequest statusRequest);

}
