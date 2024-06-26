package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceRateplanService;
import com.billcom.eshop.Request.RateplanRequest;
import com.billcom.eshop.Responce.RateplanResponse;
import com.billcom.eshop.commons.entities.Rateplan;
import com.billcom.eshop.commons.entities.ServiceName;
import com.billcom.eshop.commons.mappers.RateplanMapper;
import com.billcom.eshop.commons.repositories.RateplanRepository;
import com.billcom.eshop.commons.repositories.ServiceNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RateplanService implements InterfaceRateplanService {

    @Autowired
    private RateplanRepository rateplanRepository;

    @Autowired
    private ServiceNameRepository serviceNameRepository;

    @Autowired
    private RateplanMapper rateplanMapper;

    @Override
    public RateplanResponse addRateplan(RateplanRequest rateplanRequest) {
        Rateplan rateplan = new Rateplan();
        rateplan.setRpDesc(rateplanRequest.getRpDesc());
        rateplan.setRpValidationDays(rateplanRequest.getRpValidationDays());
        rateplan.setRpPrice(Long.valueOf(rateplanRequest.getRpPrice()));

        // Récupérer les services associés à partir des IDs et les ajouter au Rateplan
        Set<ServiceName> services = getServiceNames(rateplanRequest.getServiceIds());
        rateplan.setServices(services);

        rateplan = rateplanRepository.save(rateplan);

        RateplanResponse rateplanResponse = new RateplanResponse();
        rateplanResponse.setIsSuccessfull(true);
        rateplanResponse.setRateplan(rateplan);

        // Ajouter les noms des services dans la réponse
        List<String> serviceNames = services.stream().map(ServiceName::getSnName).collect(Collectors.toList());
        rateplanResponse.setServiceNames(serviceNames);

        return rateplanResponse;
    }

    @Override
    public RateplanResponse deleteRateplan(Long id) {
        RateplanResponse rateplanResponse = new RateplanResponse();
        if (rateplanRepository.existsById(id)) {
            rateplanRepository.deleteById(id);
            rateplanResponse.setIsSuccessfull(true);
        } else {
            rateplanResponse.setIsSuccessfull(false);
            rateplanResponse.setMessage("Rateplan not found with id: " + id);
        }
        return rateplanResponse;
    }
    @Override
    public RateplanResponse findAllRateplans() {
        List<Rateplan> rateplans = rateplanRepository.findAll();
        RateplanResponse rateplanResponse = new RateplanResponse();
        if (rateplans.isEmpty()) {
            rateplanResponse.setIsSuccessfull(false);
            rateplanResponse.setMessage("No rateplans found");
        } else {
            rateplanResponse.setIsSuccessfull(true);

            List<Map<String, Object>> rateplanList = new ArrayList<>();
            for (Rateplan rateplan : rateplans) {
                Map<String, Object> rateplanMap = new HashMap<>();
                rateplanMap.put("id", rateplan.getId());
                rateplanMap.put("rpDesc", rateplan.getRpDesc());
                rateplanMap.put("rpPrice", rateplan.getRpPrice());
                rateplanMap.put("rpValidationDays", rateplan.getRpValidationDays());

                // Collect all service names for this rateplan
                List<String> serviceNames = rateplan.getServices().stream()
                        .map(ServiceName::getSnName)
                        .collect(Collectors.toList());
                rateplanMap.put("serviceNames", serviceNames);

                rateplanList.add(rateplanMap);
            }

            rateplanResponse.setRateplans(rateplanList);
        }
        return rateplanResponse;
    }

    @Override
    public RateplanResponse findRateplanById(Long id) {
        Optional<Rateplan> rateplanOptional = rateplanRepository.findById(id);
        RateplanResponse rateplanResponse = new RateplanResponse();
        if (rateplanOptional.isPresent()) {
            rateplanResponse.setIsSuccessfull(true);
            rateplanResponse.setRateplan(rateplanOptional.get());
            // Ajouter les noms des services dans la réponse
            List<String> serviceNames = rateplanOptional.get().getServices().stream()
                    .map(ServiceName::getSnName)
                    .collect(Collectors.toList());
            rateplanResponse.setServiceNames(serviceNames);
        } else {
            rateplanResponse.setIsSuccessfull(false);
            rateplanResponse.setMessage("Rateplan not found with id: " + id);
        }
        return rateplanResponse;
    }

    @Override
    public RateplanResponse updateRateplan(Long id, RateplanRequest rateplanRequest) {
        RateplanResponse rateplanResponse = new RateplanResponse();
        Optional<Rateplan> optionalRateplan = rateplanRepository.findById(id);
        if (optionalRateplan.isPresent()) {
            Rateplan existingRateplan = optionalRateplan.get();
            existingRateplan.setRpDesc(rateplanRequest.getRpDesc());
            existingRateplan.setRpPrice(Long.valueOf(rateplanRequest.getRpPrice()));
            Set<ServiceName> services = getServiceNames(rateplanRequest.getServiceIds());
            existingRateplan.setServices(services);
            rateplanRepository.save(existingRateplan);
            rateplanResponse.setIsSuccessfull(true);
            rateplanResponse.setRateplan(existingRateplan);
            // Ajouter les noms des services dans la réponse
            List<String> serviceNames = services.stream().map(ServiceName::getSnName).collect(Collectors.toList());
            rateplanResponse.setServiceNames(serviceNames);
        } else {
            rateplanResponse.setIsSuccessfull(false);
            rateplanResponse.setMessage("Rateplan not found with id: " + id);
        }
        return rateplanResponse;
    }

    private Set<ServiceName> getServiceNames(Set<Long> serviceIds) {
        Set<ServiceName> services = new HashSet<>();
        for (Long serviceId : serviceIds) {
            Optional<ServiceName> optionalServiceName = serviceNameRepository.findById(serviceId);
            optionalServiceName.ifPresent(services::add);
        }
        return services;
    }

}
