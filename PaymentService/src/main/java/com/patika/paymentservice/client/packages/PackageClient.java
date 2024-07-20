package com.patika.paymentservice.client.packages;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value ="packageservicepayment", url ="localhost:8084/api/v1/packages")
public interface PackageClient {

}
