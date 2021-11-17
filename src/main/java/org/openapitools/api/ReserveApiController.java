package org.openapitools.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-15T21:12:04.614312100+01:00[Europe/Prague]")
@Controller
@RequestMapping("${openapi.swaggerFlightdatabase.base-path:/v2}")
public class ReserveApiController implements ReserveApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ReserveApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
